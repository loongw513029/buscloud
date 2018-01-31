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
<div id="map" style="width: 100%;height: 100%"></div>
<div id="datagrid-map" class="datagrid-map">
    <div id="box" class="easyui-tabs" style="width:100%;height:20px;">
        <div title="车辆列表" data-options="closable:true">
            <table id="table1"></table>
        </div>
        <div title="实时报警" data-options="iconCls:'icon-reload',closable:true">
            <div class="easyui-layout">
                <div data-options="region:'west',title:'报警分类',split:true" style="width:250px;">
                    <ul id="easyui-tree" class="easyui-tree" checkbox="true" data-options="lines:true">

                    </ul>
                </div>
            </div>
            <div data-options="region:'center',title:'报警列表'" style="padding:5px;">
                <table id="table2"></table>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a09d2b412c8984e51700a2a3b24e40b1" type="text/javascript"></script>
<script type="text/javascript" src="/web/map/preview.js"></script>
</body>
</html>