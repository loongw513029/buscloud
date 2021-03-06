<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/map.css" rel="stylesheet">
</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;margin: 5px">
    <div data-options="region:'west',title:'请选择设备',split:false" style="width:200px;padding: 5px">
        <div class="easyui-panel" style="height: 300px;overflow: hidden" title="设备列表">
            <ul id="easyui-tree" class="easyui-tree" checkbox="true" data-options="lines:true"></ul>
        </div>
        <br/>
        <input type="text" class="easyui-datebox" id="time1" placeholder="查询开始时间"/><br/><br/>
        <input type="text" class="easyui-datebox" id="time2" placeholder="查询截止时间"/><br/><br/>
        <a href="javascript:;" class="easyui-linkbutton" id="sh">查询</a><br/><br/>
    </div>
    <div data-options="region:'center',title:'地图'">
        <div id="map" style="width: 100%;height: 100%;"></div>
    </div>
    <div data-options="region:'east',title:'查询结果',split:false" style="width:200px;padding: 5px">
        <table id="table"></table>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a09d2b412c8984e51700a2a3b24e40b1" type="text/javascript"></script>
<script src="http://cache.amap.com/lbs/static/addToolbar.js" type="text/javascript"></script>
<script type="text/javascript" src="/web/map/history.js"></script>
</body>
</html>
