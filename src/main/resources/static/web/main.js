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
            TramDalog.Confirm("您确定要退出吗",['确定','取消'],function(){
                Http.Ajax({
                    url:'/api/v1/account/loginout',
                    type:'get'
                },function (result) {
                    if(result.success)
                        location.href= '/login';
                },function (error) {

                })
            });
        });
        //左侧菜单收起
        $(document).on('click', '.toggle-icon', function() {
            $(this).closest("#pf-bd").toggleClass("toggle");
            setTimeout(function(){
                $(window).resize();
            },300)
        });
        $(document).on('click', '.pf-modify-pwd', function() {
            $('#pf-page').find('iframe').eq(0).attr('src', 'backend/modify_pwd.html')
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
                    if(obj.hostSoftType == 0)
                        $('#easyui-tree').tree('update', {target: node.target,iconCls: 'device-nvr-online'});
                    if(obj.hostSoftType == 1)
                        $('#easyui-tree').tree('update', {target: node.target,iconCls: 'device-dvr-online'});
                    $("#"+node.domId).find("span:eq(5)").show();
                    $("#"+node.domId).find("span:eq(3)").attr("class",node.state =="open"?"tree-hit tree-expanded":"tree-hit tree-collapsed");
                }else {
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
        if(obj.customId==12) {
            var arr = obj.extras.split(',');
            var title = obj.alarmName + "-" + obj.deviceCode +"[车速："+arr[0]+"Km/h, 车距："+arr[1]+"米, 刹车："+(arr[2]==1?"有":"没有")+"]";
            parent.TramDalog.OpenIframeAndNoBtn(title, 652, 538, "/alarm/video?id=" + obj.id);
        }
        else
            parent.TramDalog.OpenIframeAndNoBtn(obj.alarmName+"-"+obj.deviceCode,652,538,"/alarm/view?id="+obj.id);
    },
    openAdminInfo:function () {
        parent.TramDalog.OpenIframe(650,405,'用户信息',"/basic/memberfrom?id="+User.GetUserInfo.id,function (layerno,index) {

        });
    },
    unCheckNode:function (target) {
        $('#easyui-tree').tree('uncheck',target);
    },
    getCheckedNodes:function () {
        return $('#easyui-tree').tree('getChecked');
    }
};

mainPlatform.init();