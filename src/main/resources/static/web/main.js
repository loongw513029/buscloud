var stompClient = null;
var treeData = null;
var mainPlatform = {
    //只能单选的页面
    signleArray:function(){
        return ['/can/preview','/video/history'];
    },
    multiArray:function () {
        return ['/map/preview'];
    },
    //必选先选设备的页面
    mustCheckArray:function () {
        return ['/video/history'];
    },
    init: function(){

        this.bindEvent();
        this.initWebSocket();
        // this.render(menu['home']);
    },

    bindEvent: function(){
        var self = this;
        var userInfo = User.GetUserInfo();
        // 顶部大菜单单击事件
        $(document).on('click', '.pf-nav-item', function() {
            $('.pf-nav-item').removeClass('current');
            $(this).addClass('current');

            // // 渲染对应侧边菜单
            // var m = $(this).data('menu');
            // self.render(menu[m]);
        });
        $(document).on('click', '.sider-nav li', function() {
            $('.sider-nav li').removeClass('current');
            $(this).addClass('current');
            //$('iframe').attr('src', $(this).data('src'));
        });
        $(document).on('click', '.pf-logout', function() {
            layer.confirm('您确定要退出吗?',{icon:3,title:'退出登录'},function (index) {
                Http.Ajax({
                    url:'/api/v1/account/loginout',
                    type:'get'
                },function (result) {
                    layer.close(index);
                    if(result.success)
                        location.href= '/login';
                },function (error) {});
            });
        });
        //左侧菜单收起
        $(document).on('click', '.toggle-icon', function() {
            $(this).closest("#pf-bd").toggleClass("toggle");
            setTimeout(function(){
                $(window).resize();
            },300)
        });
        $(document).on('click', '.pf-notice-item', function() {
            $('#pf-page').find('iframe').eq(0).attr('src', 'backend/notice.html')
        });
        $(document).on('click','.nav-item',function () {
            var text = $(this).attr("title");
            var url = $(this).attr("_href");
            if($.inArray(url,mainPlatform.mustCheckArray())>=0){
                var nodes = $('#easyui-tree').tree('getChecked');
                if(nodes.length==0) {
                    TramDalog.ErrorAlert('请先选择设备', true);
                    return;
                }
                if(nodes.length>1){
                    TramDalog.ErrorAlert('只能选择一台设备',true);
                    return;
                }
            }
            mainPlatform.addTab(text,url);
        });
        $.ajax({
            url:"/api/v1/tree/list?userId="+userInfo.id,
            type:"get",
            beforeSend:function () {
                $(".loading").show();
            },
            success:function (data) {
                $(".loading").hide();
                treeData = data;
                $('#easyui-tree').tree({
                    lines: true,
                    animate: true,
                    cascadeCheck:false,
                    data: data,
                    onCheck:function (node,checked) {
                        var index = mainPlatform.getCurrentIframeIndex();
                        var src = $('.page-iframe:eq('+index+')').attr("src");
                        if(checked){
                            if($.inArray(src,mainPlatform.signleArray())>=0) {
                                window.frames[index].Can.TransferData(node.id);
                            }
                        }
                        if($.inArray(src,mainPlatform.multiArray())>=0){
                            var nodes = $('#easyui-tree').tree('getChecked');
                            window.frames[index].TMap.ReviceParentAlarm(nodes);
                        }
                        if(src.indexOf('/map/history')>=0){
                            window.frames[index].MapHistory.AcceptParentData(node);
                        }
                        if(src.indexOf('/video/preview')>=0){
                            window.frames[index].VideoPreview.Preview(node.id,checked,node.attributes.channel,node);
                        }
                    },
                    onBeforeCheck:function (node,checked) {
                        //if(!node.attributes.isdevice)
                            //return false;
                        if(checked) {
                            var index = mainPlatform.getCurrentIframeIndex();
                            var src = $('.page-iframe:eq(' + index + ')').attr("src");
                            if ($.inArray(src, mainPlatform.signleArray()) >= 0) {
                                var tree = $('#easyui-tree').tree('getRoot');
                                $('#easyui-tree').tree('uncheck', tree.target);
                            }
                        }
                        return node.edit;
                    },
                    onClick:function (node) {
                        if(node.attributes.state) {
                            Http.Ajax({
                                url: '/api/v1/device/getstatus?deviceId=' + node.id,
                                type: 'get'
                            }, function (result) {
                                var data = result.result;
                                var doms = [];
                                doms.push("<p class='ds-item " + (data.online ? 'active' : '') + "'>状 态: <i class='layui-icon'>&#xe617;</i></p>");
                                doms.push("<p class='ds-item " + (data.canState ? 'active' : '') + "'>CAN: <i class='layui-icon'>&#xe617;</i></p>");
                                doms.push("<p class='ds-item " + (data.gpsState ? 'active' : '') + "'>GPS: <i class='layui-icon'>&#xe617;</i></p>");
                                doms.push("<p class='ds-item'>司 机: " + (data.driverName == undefined ? '无' : data.driverName) + "</p>");
                                layer.tips(doms.join(''), "#" + node.domId + " span:eq(6)", {
                                    tips: [2, '#428bca'],
                                    time: 5000
                                });
                            }, function (err) {
                                console.log(err);
                            });
                        }
                    }
                });
            }
        });
        
        
    },
    /**
     *
     * @returns {jQuery}
     */
    getCurrentIframeIndex:function(){
      return $('.tabs-panels>div:visible').index();
    },
    render: function(menu){
        var current,
            html = ['<h2 class="pf-model-name"><span class="pf-sider-icon"></span><span class="pf-name">'+ menu.title +'</span></h2>'];

        html.push('<ul class="sider-nav">');
        for(var i = 0, len = menu.menu.length; i < len; i++){
            if(menu.menu[i].isCurrent){
                current = menu.menu[i];
                html.push('<li class="current" title="'+ menu.menu[i].title +'" data-src="'+ menu.menu[i].href +'"><a href="javascript:;"><img src="'+ menu.menu[i].icon +'"><span class="sider-nav-title">'+ menu.menu[i].title +'</span><i class="iconfont"></i></a></li>');
            }else{
                html.push('<li data-src="'+ menu.menu[i].href +'" title="'+ menu.menu[i].title +'"><a href="javascript:;"><img src="'+ menu.menu[i].icon +'"><span class="sider-nav-title">'+ menu.menu[i].title +'</span><i class="iconfont"></i></a></li>');
            }
        }
        html.push('</ul>');

        $('iframe').attr('src', current.href);
        $('#pf-sider').html(html.join(''));
    },
    addTab:function(title,url){
        if($(".easyui-tabs1").tabs('exists',title)){
            $(".easyui-tabs1").tabs('select',title);
        }else{
            var content = '<iframe class="page-iframe" src="'+url+'" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto">';
            $(".easyui-tabs1").tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    },
    //初始化websocket
    initWebSocket:function () {
        var socket = new SockJS("/endpointWisely");
        stompClient = Stomp.over(socket);
        stompClient.connect({login:userId},function (frame) {
            stompClient.subscribe('/topic/getResponse',function (response) {
                var obj = JSON.parse(response.body);
                mainPlatform.analyAlarm(obj);
            });
            stompClient.subscribe('/user/'+userId+'/msg',function (response) {
                var obj = JSON.parse(response.body);
                mainPlatform.analyAlarm(obj);
            });
        })
    },
    analyAlarm:function (obj) {
        if(obj.type==1)
            mainPlatform.fiterDeviceStatus(obj.msgInfo);
        if(obj.type==2)
            mainPlatform.filterAlarm(obj.msgInfo);
    },
    //执行对数行菜单设备状态的填充
    fiterDeviceStatus:function (obj) {
        var nodes = $('#easyui-tree').tree('getChildren');
        for(var i=0;i<nodes.length;i++){
            var node = nodes[i];
            if(node.attributes.level == 4&&node.text == obj.code) {
                if(obj.online){
                    node.attributes.state = true;
                    if(obj.hostSoftType == 0)
                        $('#easyui-tree').tree('update', {target: node.target,iconCls: 'device-nvr-online'});
                    if(obj.hostSoftType == 1)
                        $('#easyui-tree').tree('update', {target: node.target,iconCls: 'device-dvr-online'});
                    $("#"+node.domId).find("span:eq(5)").show();
                    $("#"+node.domId).find("span:eq(3)").attr("class",node.state =="open"?"tree-hit tree-expanded":"tree-hit tree-collapsed");
                }else {
                    node.attributes.state = false;
                    $('#easyui-tree').tree('update', {target: node.target, iconCls: 'device-offline'});
                    $("#"+node.domId).find("span:eq(5)").hide();
                    $("#"+node.domId).find("span:eq(3)").attr("class","tree-indent tree-joinbottom");
                    if(node.state == 'open'){
                        $("#easyui-tree").tree('collapse',node.target);
                    }
                }
            }
        }
    },
    filterAlarm:function (obj) {
        if(layerIndex)
            layer.close(layerIndex);
        if(obj.customId==12) {
            var arr = obj.extras.split(',');
            var title = obj.alarmName + "-" + obj.deviceCode +"[车速："+arr[0]+"Km/h, 车距："+arr[1]+"米, 刹车："+(arr[2]==1?"有":"没有")+"]";
            parent.TramDalog.OpenIframeAndNoBtn(title, 652, 538, "/alarm/video?id=" + obj.id);
        }
        else
            parent.TramDalog.OpenIframeAndNoBtn(obj.alarmName+"-"+obj.deviceCode,652,538,"/alarm/view?id="+obj.id);
    },
    openAdminInfo:function () {
        parent.TramDalog.OpenIframe(650,405,'用户信息',"/basic/memberfrom?id="+User.GetUserInfo().id,function (layerno,index) {
        layer.close(layerno);
        });
    },
    unCheckNode:function (target) {
        $('#easyui-tree').tree('uncheck',target);
    },
    getCheckedNodes:function () {
        return $('#easyui-tree').tree('getChecked');
    },
    ModifyPwd:function () {
        layer.open({
            type:1,
            area:['400px','300px'],
            title:'修改密码',
            shade:0.6,
            maxmin:false,
            anim:1,
            content:$('#editPwd'),
            btn:['确定','取消'],
            yes:function (index,layero) {
                var oldpwd = $('input[name="oldpwd"]').val(),
                    newpwd = $('input[name="newpwd"]').val(),
                    newpwd1 = $('input[name="newpwd1"]').val();
                if(oldpwd==''||newpwd==''||newpwd1==''){
                    TramDalog.ErrorAlert('输入信息不完整!',true);
                    return;
                }
                if(newpwd1!=newpwd){
                    TramDalog.ErrorAlert('两次密码输入不一致!',true);
                    return;
                }
                var data = {
                    userid:User.GetUserInfo().id,
                    oldpwd:oldpwd,
                    newpwd:newpwd1
                };
                Http.Ajax({
                    type:'post',
                    data:data,
                    url:'/api/v1/account/ModifyPwd'
                },function (result) {
                   if(result.success){
                       TramDalog.SuccessAlert('修改密码成功,请重新登录！',true);
                   }else
                       TramDalog.ErrorAlert(result.info,true);
                },function () {
                });
            },
            btn2:function (index,layero) {
                layer.close(index);
            }
        })
    }
};

mainPlatform.init();