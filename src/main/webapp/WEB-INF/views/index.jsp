<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>车辆云管理</title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/fonts/iconfont.css" rel="stylesheet">
    <link href="/css/platform.css" rel="stylesheet">
    <link href="/layer/theme/default/layer.css" rel="stylesheet" />
    <link href="/layui/css/layui.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div id="pf-hd">
        <div class="pf-logo">
            <img src="/images/logo.png" alt="logo" width="100%">
        </div>
        <!--头部菜单-->
        <div class="pf-nav-wrap">
            <div class="pf-nav-ww">
                <ul class="pf-nav">
                    <c:forEach items="${navs}" var="item">
                        <li class="pf-nav-item" data-menu="sys-manage">
                            <a href="javascript:;" _href="${item.getUri()}" class="nav-item" title="${item.getMenu().size()==0?item.getText():item.getMenu().get(0).getText()}">
                                <span class="iconfont">${item.getGlyph()}</span>
                                <span class="pf-nav-title">${item.getText()}</span>
                            </a>
                            <ul class="dropdownmenu">
                                <c:forEach items="${item.getMenu()}" var="item2">
                                    <li>
                                        <a href="javascript:;" _href="${item2.getUri()}" class="chinditem nav-item" title="${item2.getText()}">${item2.getText()}</a>
                                        <ul class="threemenu">
                                            <c:forEach items="${item2.getMenu()}" var="item3">
                                                <li>
                                                    <a href="javascript:;" _href="${item3.getUri()}" class="chinditem nav-item" title="${item3.getText()}">
                                                        ${item3.getText()}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!--头部右侧用户布局-->
        <div class="pf-user">
            <div class="pf-user-photo">
                <img src="${(user.photo==null||user.photo=='')?'images/main/user.png':user.photo}" alt="" width="40px" height="40px">
            </div>
            <h4 class="pf-user-name ellipsis">${user.realName}</h4>
            <i class="iconfont xiala">&#xf0170;</i>
            <div class="pf-user-panel">
                <ul class="pf-user-opt">
                    <li>
                        <a href="javascript:;" onclick="mainPlatform.openAdminInfo()">
                            <i class="iconfont">&#xf00ec;</i>
                            <span class="pf-opt-name">用户信息</span>
                        </a>
                    </li>
                    <li class="pf-modify-pwd">
                        <a href="javascript:;" onclick="mainPlatform.ModifyPwd()">
                            <i class="iconfont">&#xf00c9;</i>
                            <span class="pf-opt-name">修改密码</span>
                        </a>
                    </li>
                    <li class="pf-logout">
                        <a href="javascript:;">
                            <i class="iconfont">&#xf0204;</i>
                            <span class="pf-opt-name">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="pf-bd" class="toggle">
        <!--左侧设备列表-->
        <div id="pf-sider">
            <h2 class="pf-model-name">
                <span class="iconfont">&#xf00fd;</span>
                <span class="pf-name">车辆列表</span>
                <span class="toggle-icon"></span>
            </h2>
            <div class="loading"><img src="/images/loading.gif" /> <br/>设备加载中...</div>
            <ul id="easyui-tree" class="easyui-tree" checkbox="true" data-options="lines:true">

            </ul>
        </div>
        <!--标签s-->
        <div id="pf-page">
            <div class="easyui-tabs1" style="width:100%;height:100%;">
                <div title="系统首页" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="/welcome" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                </div>
            </div>
        </div>
    </div>

    <div id="pf-ft">
        <div class="system-name">
            <i class="iconfont">&#xf02bc;</i>
            <span>车辆云管理&nbsp;v3.0 Build20180125</span>
        </div>
        <div class="copyright-name">
            <span>CopyRight&nbsp;2018&nbsp;&nbsp;深圳特维视科技有限公司&nbsp;版权所有</span>
            <i class="iconfont" >&#xf01a9;</i>
        </div>
    </div>
</div>
<div id="ocxbox" style="display: none; width: 790px;height: 440px">
    <object id="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000">
        <param name="_Version" value="1.1.0">
        <param name="_ExtentX" value="2646">
        <PARAM name="_ExtentY" value="1323">
        <PARAM name="_StockProps" value="0" width="100%" height="100%">
    </object>
</div>
<div id="editPwd" style="display: none;width: 92%">
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" name="oldpwd" lay-verify="oldpwd" autocomplete="off" placeholder="原密码" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="newpwd" lay-verify="newpwd" autocomplete="off" placeholder="新密码" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请确认</label>
        <div class="layui-input-block">
            <input type="password" name="newpwd1" lay-verify="newpwd1" autocomplete="off" placeholder="请确认新密码" class="layui-input" />
        </div>
    </div>
</div>
<script type="text/javascript">
    var userId = '${user.uuid}';
</script>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/dist/jquery.cookie.js"></script>
<script type="text/javascript" src="/dist/sockjs.min.js"></script>
<script type="text/javascript" src="/dist/stomp.min.js"></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script type="text/javascript" src="/web/common.js"></script>
<script type="text/javascript" src="/web/main.js"></script>
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
</script>
</body>
<!--报警图片DOM-->
<div id="alarm-pics">
    <div class="layui-tab layui-tab-card">
        <ul class="layui-tab-title">
            <li class="layui-this"></li><li></li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show"></div>
            <div class="layui-tab-item"></div>
        </div>
    </div>
</div>
</html>
