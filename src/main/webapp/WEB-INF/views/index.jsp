
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>车辆云管理</title>
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/platform.css" rel="stylesheet">
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
</head>
<body>
<div class="container">
    <div id="pf-hd">
        <div class="pf-logo">
            <img src="images/main/main_logo.png" alt="logo">
        </div>

        <div class="pf-nav-wrap">
            <div class="pf-nav-ww">
                <ul class="pf-nav">
                    <li class="pf-nav-item home current" data-menu="sys-manage">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe63f;</span>
                            <span class="pf-nav-title">系统管理</span>
                        </a>
                    </li>
                    <li class="pf-nav-item project" data-menu="org-manage">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe60d;</span>
                            <span class="pf-nav-title">组织管理</span>
                        </a>
                    </li>
                    <li class="pf-nav-item static" data-menu="main-data">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe61e;</span>
                            <span class="pf-nav-title">主数据</span>
                        </a>
                    </li>
                    <li class="pf-nav-item manger" data-menu="supplier-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe620;</span>
                            <span class="pf-nav-title">供应商管理</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="supplier-dev">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe625;</span>
                            <span class="pf-nav-title">供应商开发</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="pur-source">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe64b;</span>
                            <span class="pf-nav-title">采购寻源</span>
                        </a>
                    </li>

                    <li class="pf-nav-item manger" data-menu="contract-mange">
                        <a href="javascript:;">
                            <span class="iconfont">&#xe64c;</span>
                            <span class="pf-nav-title">合同管理</span>
                        </a>
                    </li>
                </ul>
            </div>
            <a href="javascript:;" class="pf-nav-prev disabled iconfont">&#xe606;</a>
            <a href="javascript:;" class="pf-nav-next iconfont">&#xe607;</a>
        </div>



        <div class="pf-user">
            <div class="pf-user-photo">
                <img src="images/main/user.png" alt="">
            </div>
            <h4 class="pf-user-name ellipsis">uimaker</h4>
            <i class="iconfont xiala">&#xe607;</i>

            <div class="pf-user-panel">
                <ul class="pf-user-opt">
                    <li>
                        <a href="javascript:;">
                            <i class="iconfont">&#xe60d;</i>
                            <span class="pf-opt-name">用户信息</span>
                        </a>
                    </li>
                    <li class="pf-modify-pwd">
                        <a href="http://www.uimaker.com">
                            <i class="iconfont">&#xe634;</i>
                            <span class="pf-opt-name">修改密码</span>
                        </a>
                    </li>
                    <li class="pf-logout">
                        <a href="login.html">
                            <i class="iconfont">&#xe60e;</i>
                            <span class="pf-opt-name">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </div>

    <div id="pf-bd">
        <div id="pf-sider">
            <h2 class="pf-model-name">
                <span class="iconfont">&#xe64a;</span>
                <span class="pf-name">组织管理</span>
                <span class="toggle-icon"></span>
            </h2>

            <ul class="sider-nav">
                <li class="current">
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe620;</span>
                        <span class="sider-nav-title">供应商组织</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li class="active"><a href="#">供应商组织1</a></li>
                        <li><a href="#">供应商组织2</a></li>
                        <li><a href="#">供应商组织3</a></li>
                        <li><a href="#">供应商组织4</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe625;</span>
                        <span class="sider-nav-title">采购组织</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li class="active"><a href="#">供应商组织1</a></li>
                        <li><a href="#">供应商组织2</a></li>
                        <li><a href="#">供应商组织3</a></li>
                        <li><a href="#">供应商组织4</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe62f;</span>
                        <span class="sider-nav-title">专家库</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li class="active"><a href="#">供应商组织1</a></li>
                        <li><a href="#">供应商组织2</a></li>
                        <li><a href="#">供应商组织3</a></li>
                        <li><a href="#">供应商组织4</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe647;</span>
                        <span class="sider-nav-title">注册供应商</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                    <ul class="sider-nav-s">
                        <li class="active"><a href="#">供应商组织1</a></li>
                        <li><a href="#">供应商组织2</a></li>
                        <li><a href="#">供应商组织3</a></li>
                        <li><a href="#">供应商组织4</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe611;</span>
                        <span class="sider-nav-title">RFI动态信息</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="iconfont sider-nav-icon">&#xe633;</span>
                        <span class="sider-nav-title">资质过期</span>
                        <i class="iconfont">&#xe642;</i>
                    </a>
                </li>
            </ul>
        </div>

        <div id="pf-page">
            <div class="easyui-tabs1" style="width:100%;height:100%;">
                <div title="首页" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="workbench.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
                <div title="采购组织" style="padding:10px 5px 5px 10px;" data-options="closable:true">
                    <iframe class="page-iframe" src="index.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
                <div title="基本信息" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="basic_info.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
                <div title="供应商" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="providers.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
                <div title="业务流程" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="process.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
                <div title="表单管理" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="providers1.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
            </div>
        </div>
    </div>

    <div id="pf-ft">
        <div class="system-name">
            <i class="iconfont">&#xe6fe;</i>
            <span>信息管理系统&nbsp;v1.0</span>
        </div>
        <div class="copyright-name">
            <span>CopyRight&nbsp;2016&nbsp;&nbsp;uimaker.com&nbsp;版权所有</span>
            <i class="iconfont" >&#xe6ff;</i>
        </div>
    </div>
</div>



<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/web/main.js"></script>
<!--[if IE 7]>
<script type="text/javascript">
    $(function(){
        Tram.Init();
    });
    $(window).resize(function(){
        $('#pf-bd').height($(window).height()-76);
    }).resize();

</script>
<![endif]-->

<script type="text/javascript">
    $('.easyui-tabs1').tabs({
        tabHeight: 44,
        onSelect:function(title,index){
            var currentTab = $('.easyui-tabs1').tabs("getSelected");
            if(currentTab.find("iframe") && currentTab.find("iframe").size()){
                currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
            }
        }
    })
    $(window).resize(function(){
        $('.tabs-panels').height($("#pf-page").height()-46);
        $('.panel-body').height($("#pf-page").height()-76)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
        $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


        if($(this).hasClass('disabled')) return;
        if($(this).hasClass('pf-nav-next')){
            page++;
            $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
            if(page == pages){
                $(this).addClass('disabled');
                $('.pf-nav-prev').removeClass('disabled');
            }else{
                $('.pf-nav-prev').removeClass('disabled');
            }

        }else{
            page--;
            $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
            if(page == 0){
                $(this).addClass('disabled');
                $('.pf-nav-next').removeClass('disabled');
            }else{
                $('.pf-nav-next').removeClass('disabled');
            }

        }
    })

    // setTimeout(function(){
    //    $('.tabs-panels').height($("#pf-page").height()-46);
    //    $('.panel-body').height($("#pf-page").height()-76)
    // }, 200)
</script>
</body>
</html>
