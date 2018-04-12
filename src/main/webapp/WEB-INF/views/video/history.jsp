<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link href="/css/base.css" rel="stylesheet">
</head>
<body>
<div id="cc" class="easyui-layout" style="width:99%;height:99%;margin: .5%">
    <div data-options="region:'west',title:'查询条件',split:false" style="width:200px;padding: 5px">
        <div class="alearySelectDevice">已选择设备：N130</div>
        <br/>
        <input type="text" id="slChannel" style="width: 178px">
        <br/> <br/>
        <input type="text" class="easyui-datebox" id="time" placeholder="查询时间" data-options="prompt:'查询时间'" required="required" style="width: 178px"/><br/><br/>
        <div class="easyui-panel" style="width: 180px;border: 0px;" id="vh-panel">
            <a href="javascript:;" category="*" class="easyui-linkbutton" data-options="toggle:true,group:'g1'">全部</a>
            <a href="javascript:;" category="1" class="easyui-linkbutton" data-options="toggle:true,group:'g1'">防撞</a>
            <a href="javascript:;" category="2" class="easyui-linkbutton" data-options="toggle:true,group:'g1'">紧急</a>
            <a href="javascript:;" category="3" class="easyui-linkbutton" data-options="toggle:true,group:'g1'">行为</a>
            <a href="javascript:;" category="4" class="easyui-linkbutton" data-options="toggle:true,group:'g1'">ADAS</a>
        </div>
        <br/>
        <a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="sh">查询</a><br/><br/>
    </div>
    <div data-options="region:'center',title:'视频'">
        <object id="ocx" class="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:99.5%;background: #000000"></object>
    </div>
    <div data-options="region:'east',title:'查询结果',split:false" style="width:200px;">
        <table id="table" class="easyui-datagrid" style="width:100%;height:100%" data-options="pagination:false,fit:true,onDblClickRow:VideoHistory.onTableDblClickRow">
            <thead>
            <tr>
                <th data-options="field:'StartTime',width:60,formatter:VideoHistory.formatDate">开始时间</th>
                <th data-options="field:'StopTime',width:60,formatter:VideoHistory.formatDate">结束时间</th>
                <th data-options="field:'Length',width:60">时长</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/video/history.js"></script>
</body>
</html>
