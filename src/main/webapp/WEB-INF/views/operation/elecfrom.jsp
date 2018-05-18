<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/7
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link href="/css/base.css" rel="stylesheet">
</head>
<body>
<input type="hidden" id="Id" value="${obj.id}">
<table cellpadding="1" width="100%" class="kv-table">
    <tbody>
    <tr>
        <td class="kv-label" width="100px">纬度:</td>
        <td height="35" class="kv-content">
            <input type="text" id="lng" value="${obj.lng}">
        </td>
        <td class="kv-label">经度</td>
        <td class="kv-content">
            <input type="text" id="lat" value="${obj.lat}">
        </td>
    </tr>
    <tr>
        <td class="kv-label">半径:</td>
        <td height="35" class="kv-content">
            <input type="text" id="radius" value="${obj.radius}">
        </td>
        <td class="kv-label"></td>
        <td class="kv-content">
        </td>
    </tr>
    <tr>
        <td class="kv-label">进围栏报警:</td>
        <td height="35" class="kv-content">
            <input type="checkbox" id="inTrun" ${obj.inTrun?"checked='checked'":""}/>
        </td>
        <td class="kv-label">出围栏报警</td>
        <td class="kv-content">
            <input type="checkbox" id="outTrun" ${obj.outTrun?"checked='checked'":""}>
        </td>
    </tr>
    </tbody>
</table>
<div style="width: 638px;height: 240px;border:solid 1px #dedede" id="map">
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a09d2b412c8984e51700a2a3b24e40b1" type="text/javascript"></script>
<script type="text/javascript" src="/web/operation/ElecFrom.js"></script>
</body>
</html>
