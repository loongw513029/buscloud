
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
                <img id="image" class="cover-radius" src="${(images==null||images=='')?'/images/upload_img.png':images}" width="40%" style="cursor: pointer;margin-left: 5px" />
                <input id="pic_upload" name="file" type="file" onchange="BuildFaceFrom.Cover(this)" style="position: absolute;left: 0px;top: 0px;
width: 100%;height:124px;opacity: 0;cursor: pointer"/>
            </td>
        </tr>
        <tr>
            <td class="kv-label">姓名:</td>
            <td height="35" class="kv-content">
                <input type="text" id="name" value="${obj.name}">
            </td>
            <td class="kv-label">曾用名</td>
            <td class="kv-content">
                <input type="text" id="oldName" value="${obj.oldName}">
            </td>
        </tr>
        <tr>
            <td class="kv-label">所属机构:</td>
            <td height="35" class="kv-content">
                <input id="departmentId" value="${obj.departmentId}" class="esayui-combotree">
            </td>
            <td class="kv-label">性别</td>
            <td class="kv-content">
                <select id="sex" class="easyui-combobox">
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="kv-label">生日:</td>
            <td height="35" class="kv-content">
                <input type="text" id="birth" class="easyui-datebox" value="${obj.birth}">
            </td>
            <td class="kv-label">学历</td>
            <td class="kv-content">
                <input type="text" maxlength="18" id="degree"/>
            </td>
        </tr>
        <tr>
            <td class="kv-label">证件类型:</td>
            <td height="35" class="kv-content">
                <select id="credentialStype" class="easyui-combobox">
                    <option value="1">身份证</option>
                    <option value="2">军官证</option>
                    <option value="3">护照</option>
                </select>
            </td>
            <td class="kv-label">证件号码</td>
            <td class="kv-content">
                <input type="text" maxlength="18" id="idCard"/>
            </td>
        </tr>
        <tr>
            <td class="kv-label">备注说明:</td>
            <td height="35" colspan="3" class="kv-content">
                <textarea id="memo" style="width: 80%;height: 120px;"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    </div>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/dist/ajaxfileupload.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/web/buildface/from.js"></script>
</body>
</html>
