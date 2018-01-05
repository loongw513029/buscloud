var mainPlatform = {

    init: function(){

        this.bindEvent();
        // this.render(menu['home']);
    },

    bindEvent: function(){
        var self = this;
        var userInfo = User.GetUserInfo();
        // 顶部大菜单单击事件
        $(document).on('click', '.pf-nav-item', function() {
            $('.pf-nav-item').removeClass('current');
            $(this).addClass('current');

            // 渲染对应侧边菜单
            var m = $(this).data('menu');
            self.render(menu[m]);
        });

        $(document).on('click', '.sider-nav li', function() {
            $('.sider-nav li').removeClass('current');
            $(this).addClass('current');
            $('iframe').attr('src', $(this).data('src'));
        });

        $(document).on('click', '.pf-logout', function() {
            layer.confirm('您确定要退出吗？', {
                icon: 4,
                title: '确定退出' //按钮
            }, function(index){
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
                $('#easyui-tree').tree({
                    data: data
                });
            }
        })
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
    }

};

mainPlatform.init();