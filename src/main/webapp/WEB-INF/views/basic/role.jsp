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
<body class="easyui-layout" style="margin: 5px" ondragstart="window.event.returnValue=false" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false">
    <div data-options="region:'west',title:'角色列表（右键删除*）',split:true" style="width:250px;">
        <ul id="easyui-tree" class="easyui-tree" checkbox="true" data-options="lines:true">

        </ul>
        <div id="parentNode" class="easyui-menu" style="width: 120px;">
            <div onclick="Role.removeRole()" iconcls="icon-remove">删除角色</div>
        </div>
    </div>
    <div data-options="region:'center',title:'角色编辑'" style="padding:5px;">
        <input type="hidden" id="Id" value="0"/>
        <table id="table1" class="kv-table">
            <tr>
                <td class="kv-label">角色名称</td>
                <td class="kv-content">
                    <input id="rolename" type="text">
                </td>
            </tr>
            <tr>
                <td class="kv-label">角色说明</td>
                <td class="kv-content">
                    <input id="remark" type="text" style="width: 300px">
                </td>
            </tr>
            <tr>
                <td class="kv-label">上级角色</td>
                <td class="kv-content">
                    <input id="parentId" type="text">
                </td>
            </tr>
            <tr>
                <td class="kv-label"></td>
                <td class="kv-content">
                    右侧可对该新增的角色或编辑的角色授权
                </td>
            </tr>
            <tr>
                <td class="kv-label"></td>
                <td class="kv-content">
                    <input type="button" value="添加" class="l-btn" onclick="Role.AddRole()" id="addbtn"/>
                </td>
            </tr>
        </table>
    </div>
    <div data-options="region:'east',title:'角色授权'" style="padding: 5px;width: 300px">
        <ul id="easyui-tree1" class="easyui-tree" checkbox="true" data-options="lines:true">

        </ul>
    </div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/basic/role.js"></script>
</body>
</html>