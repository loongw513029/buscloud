<%--
  Created by IntelliJ IDEA.
  User: 肖梓康
  Date: 2018/4/13
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <META HTTP-EQUIV="Expires" CONTENT="0">
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
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <div style="display: inline-block">
            <input class="easyui-text" style="width:12%" id="departmentId">
            <input class="easyui-text" style="width:10%" id="lineId">
            <input class="easyui-text" style="width:13%" id="type1" />
            <input class="easyui-text" style="width:13%" id="type2" />
            <input class="easyui-datebox" style="width:13%" id="date1" placeholder="开始时间"/>
            <input class="easyui-datebox" style="width:13%" id="date2" placeholder="截止时间"/>
            <input id="txtkey"/>
        </div>
    </div>
</div>
<div class="chart-chart" id="chart0"></div>
<div style="width: 26%;margin-left: auto;margin-right: auto;padding-left: auto;padding-right: auto;">
    <a class="easyui-linkbutton" id="line">线性图</a>
    <a class="easyui-linkbutton" id="area">面积图</a>
    <a class="easyui-linkbutton" id="column">树状图</a>
    <a class="easyui-linkbutton" id="bar">条形图</a>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/dist/highcharts.js"></script>
<script type="text/javascript" src="/dist/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/alarm/statis.js"></script>
</body>
</html>
