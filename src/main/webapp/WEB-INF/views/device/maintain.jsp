<%--
  Created by IntelliJ IDEA.
  User: 肖梓康
  Date: 2018/4/16
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="/fonts/iconfont.css">
    <link href="/css/base.css" rel="stylesheet">
    <style>
        .layui-layer-content{padding: 0px}
    </style>
</head>
<body>
<table id="table" style="margin-top: 10px"></table>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <div style="display: inline-block">
            <input class="easyui-text" style="width:12%" id="departmentId">
            <input class="easyui-text" style="width:10%" id="lineId">
            <input class="easyui-datebox" style="width:10%" id="date1" placeholder="开始时间"/>
            <input class="easyui-datebox" style="width:10%" id="date2" placeholder="截止时间"/>
            <input id="txtkey"/>
        </div>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/device/maintain.js"></script>
</body>
</html>
