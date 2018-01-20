
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
    <link href="/css/can.css" rel="stylesheet">
</head>
<body>
<form id="form" class="easyui-form" method="post">
    <input type="hidden" id="Id" value="${obj.id}">
    <table cellpadding="5" width="100%" class="kv-table">
        <tbody>
        <tr>
            <td class="kv-label">机构编码:</td>
            <td height="35" class="kv-content">
                <input type="text" id="departmentcode" value="${obj.code}">
            </td>
            <td class="kv-label">机构名称</td>
            <td class="kv-content">
                <input type="text" id="departmentname" value="${obj.departmentname}" class="easyui-validatebox" data-options="required:true;prompt:'请输入机构名称'">
            </td>
        </tr>
        <tr>
            <td class="kv-label">上级机构:</td>
            <td height="35" class="kv-content">
                <input id="parentId" value="${obj.parentid}" class="esayui-combotree">
            </td>
            <td class="kv-label">联系人</td>
            <td class="kv-content">
                <input type="text" id="contactname" value="${obj.contactname}">
            </td>
        </tr>
        <tr>
            <td class="kv-label">联系电话:</td>
            <td height="35" class="kv-content">
                <input type="text" id="contactphone" value="${obj.contactphone}"  class="easyui-validatebox" data-options="validType:'mobile',prompt:'请输入机构名称'">
            </td>
            <td class="kv-label"></td>
            <td class="kv-content"></td>
        </tr>
        <tr>
            <td class="kv-label">备注:</td>
            <td height="35" class="kv-content" colspan="3">
                <input type="text" id="remark" style="width: 350px" value="${obj.remark}">
            </td>
        </tr>
        <tr>
            <td class="kv-label">CAN:</td>
            <td height="35" class="kv-content">
                <input type="checkbox" id="ishavecan" ${obj.islookcan==1?"checked='checked'":""}>有
            </td>
            <td class="kv-label">视频</td>
            <td class="kv-content">
                <input type="checkbox" id="ishavevideo" ${obj.ishavevedio==1?"checked='checked'":""}>有
            </td>
        </tr>
        <tr>
            <td class="kv-label">App名称:</td>
            <td height="35" class="kv-content" colspan="3">
                <input type="text" id="appname" style="width: 350px" value="${obj.appname}">
            </td>
        </tr>
        </tbody>
    </table>
</form>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/web/departmentfrom.js"></script>
</body>
</html>
