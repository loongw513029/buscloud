<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>车辆云管理</title>
    <link href="/css/base.css" rel="stylesheet">
    <link href="/fonts/iconfonts.css" rel="stylesheet">
    <link href="/css/login/login.css" rel="stylesheet">

</head>
<body>
<img src="/images/loginbg.jpg" class="login-bgimg">
<div class="login-hd">
    <div class="left-bg"></div>
    <div class="right-bg"></div>
    <div class="hd-inner">
        <span class="logo"></span>
        <span class="split"></span>
        <span class="sys-name">车辆云管理</span>
    </div>
</div>
<div class="login-bd">
    <div class="bd-inner">
        <div class="inner-wrap">
            <div class="lg-zone">
                <div class="lg-box">
                    <div class="lg-label"><h4>用户登录</h4></div>
                    <div class="alert alert-error hide">
                        <i class="iconfont">&#xf02bc;</i>
                        <span>请输入用户名</span>
                    </div>
                    <form>
                        <div class="lg-username input-item clearfix">
                            <i class="iconfont">&#xf00ec;</i>
                            <input type="text" placeholder="账号/邮箱" name="username">
                        </div>
                        <div class="lg-password input-item clearfix">
                            <i class="iconfont">&#xf00c9;</i>
                            <input type="password" placeholder="请输入密码" name="password" value="123456">
                        </div>
                        <div class="tips clearfix">
                            <label><input type="checkbox" checked="checked" name="getpwd">记住用户名</label>
                            <a href="javascript:;" class="forget-pwd">忘记密码？</a>
                        </div>
                        <div class="enter">
                            <a href="javascript:;" class="supplier">登 录</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="lg-poster"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script type="text/javascript" src="/dist/jquery.cookie.js"></script>
<script type="text/javascript" src="/web/common.js"></script>
<script type="text/javascript" src="/web/login.js"></script>
</body>
</html>


