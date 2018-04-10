
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
        <td class="kv-label">上传头像
        </td>
        <td colspan="3" style="padding: 3px">
            <img id="image" class="cover-radius" src="/images/upload_img.png" width="40%" style="cursor: pointer;margin-left: 5px" />
            <input id="pic_upload" name="file" type="file" onchange="DriverFrom.Cover(this)" style="position: absolute;left: 0px;top: 0px;
width: 100%;height:124px;opacity: 0;cursor: pointer"/>
        </td>
    </tr>
    <tr>
        <td class="kv-label">司机姓名:</td>
        <td height="35" class="kv-content">
            <input type="text" id="drivername" value="${obj.drivername}">
        </td>
        <td class="kv-label">性别</td>
        <td class="kv-content">
            <input type="text" id="slgender" value="${obj.gender}"/>
        </td>
    </tr>
    <tr>
        <td class="kv-label">所属机构:</td>
        <td height="35" class="kv-content">
            <input id="departmentid"  class="esayui-combotree">
            <input type="hidden" id="hdDepartmentId" value="${obj.departmentid}" />
        </td>

        <td class="kv-label">联系电话</td>
        <td class="kv-content">
            <input type="text" id="contactphone" value="${obj.contactphone}">
        </td>
    </tr>
    </tbody>
</table>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/dist/ajaxfileupload.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/operation/driverfrom.js"></script>
<script type="text/javascript">
    $(function () {
        var logo = '${obj.driverheaderimg}';
        $("#image").attr("src",logo==""?"/images/upload_img.png":logo);
    })
</script>
</body>
</html>
