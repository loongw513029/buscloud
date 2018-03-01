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
    <link href="/css/map.css" rel="stylesheet">
</head>
<body>
<div class="header-box" style="margin-top:8px">
    <div class="timebox">
        00:00
    </div>
    <label>监控时长</label>
    <select name="interest" id="totelMin">
        <option value="1">1分钟</option>
        <option value="3">3分钟</option>
        <option value="5">5分钟</option>
        <option value="10">10分钟</option>
    </select>
    <label>时间间隔</label>
    <select name="interest" id="intervalTime">
        <option value="3">3秒</option>
        <option value="10">10秒</option>
        <option value="15">15秒</option>
    </select>
    <button id="start">下发</button>
</div>
<div id="map" style="width: 100%;height: 100%"></div>

<div id="datagrid-map" class="datagrid-map">
    <div class="expand up"></div>
    <div id="box" class="easyui-tabs" style="width:100%;height:200px;">
        <div title="车辆列表">
            <table id="table1"></table>
        </div>
        <div title="实时报警">
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
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a09d2b412c8984e51700a2a3b24e40b1" type="text/javascript"></script>
<script type="text/javascript" src="/web/map/preview.js"></script>
</body>
</html>