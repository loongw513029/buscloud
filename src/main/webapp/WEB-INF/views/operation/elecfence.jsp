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
<table id="table" style="margin-top: 10px"></table>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="javascript:;" class="easyui-linkbutton fl" iconCls="icon-add" plain="true" onclick="ElecFence.Add()">增加</a>
        <div class="datagrid-btn-separator"></div>
        <a href="javascript:;" class="easyui-linkbutton fl" iconCls="icon-edit" plain="true" onclick="ElecFence.Edit()">编辑</a>
        <div class="datagrid-btn-separator"></div>
        <a href="javascript:;" class="easyui-linkbutton fl" iconCls="icon-save" plain="true" onclick="ElecFence.Remove()">删除</a>

    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/operation/elecfence.js"></script>
</body>
</html>
