
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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
<table cellpadding="5" width="100%" class="kv-table">
    <tbody>
    <tr>
        <td class="kv-label">线路代码:</td>
        <td height="35" class="kv-content">
            <input type="text" id="username" value="${obj.linecode}">
        </td>
        <td class="kv-label">线路名称</td>
        <td class="kv-content">
            <input type="text" id="realname" value="${obj.linename}">
        </td>
    </tr>
    <tr>
        <td class="kv-label">所属机构:</td>
        <td height="35" class="kv-content">
            <input id="departmentid"  class="esayui-combotree">
        </td>
        <td class="kv-label"></td>
        <td class="kv-content">
        </td>
    </tr>
    <tr>
        <td class="kv-label">上行里程:</td>
        <td height="35" class="kv-content">
            <input type="text" id="lineupmileage" value="${obj.lineupmileage}">
        </td>
        <td class="kv-label">下行里程</td>
        <td class="kv-content">
            <input type="text" id="linedownmileage" value="${obj.linedownmileage}">
        </td>
    </tr>
    <tr>
        <td class="kv-label">上行站点:</td>
        <td height="35" class="kv-content">
            <input type="text" id="upsitenum" value="${obj.upsitenum}">
        </td>
        <td class="kv-label">下行里程</td>
        <td class="kv-content">
            <input type="text" id="downsitenum" value="${obj.downsitenum}">
        </td>
    </tr>
    </tbody>
</table>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/operation/linefrom.js"></script>
</body>
</html>
