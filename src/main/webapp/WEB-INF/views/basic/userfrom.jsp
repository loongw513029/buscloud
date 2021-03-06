
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

    <input type="hidden" id="Id" value="${obj.id}">
    <table cellpadding="5" width="100%" class="kv-table">
        <tbody>
        <tr>
            <td class="kv-label">上传头像
            </td>
            <td colspan="3" style="padding: 3px">
                <img id="image" class="cover-radius" src="${(obj.photo==null||obj.photo=='')?'/images/upload_img.png':obj.photo}" width="40%" style="cursor: pointer;margin-left: 5px" />
                <input id="pic_upload" name="file" type="file" onchange="UserFrom.Cover(this)" style="position: absolute;left: 0px;top: 0px;
width: 100%;height:124px;opacity: 0;cursor: pointer"/>
            </td>
        </tr>
        <tr>
            <td class="kv-label">用户名:</td>
            <td height="35" class="kv-content">
                <input type="text" id="username" value="${obj.username}">
            </td>
            <td class="kv-label">真实姓名</td>
            <td class="kv-content">
                <input type="text" id="realname" value="${obj.realname}">
            </td>
        </tr>
        <tr>
            <td class="kv-label">所属机构:</td>
            <td height="35" class="kv-content">
                <input id="ownershipid" value="${obj.ownershipid}" class="esayui-combotree">
            </td>
            <td class="kv-label">选择角色</td>
            <td class="kv-content">
                <input id="roleid" value="${obj.roleid}" class="esayui-combotree">
            </td>
        </tr>
        <tr>
            <td class="kv-label">联系电话:</td>
            <td height="35" class="kv-content">
                <input type="text" id="phone" value="${obj.phone}">
            </td>
            <td class="kv-label"></td>
            <td class="kv-content"></td>
        </tr>
        <tr>
            <td class="kv-label">用户状态:</td>
            <td height="35" class="kv-content">
                <input type="checkbox" id="status" ${obj.status==1?"checked='checked'":""}>正常
            </td>
            <td class="kv-label"></td>
            <td class="kv-content">

            </td>
        </tr>
        </tbody>
    </table>
    </div>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/dist/ajaxfileupload.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/web/basic/userfrom.js"></script>
</body>
</html>
