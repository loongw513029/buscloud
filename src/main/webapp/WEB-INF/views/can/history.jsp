<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/fonts/iconfont.css" rel="stylesheet">
    <link href="/css/can-history.css" rel="stylesheet">
    <style>
        .combo{height: 26px;border-radius: 14px}
    </style>
</head>
<body>
<div class="tips"><input type="text" id="lineId" style="height: 26px;line-height: 26px"/>
    <div id="tips" style="display: inline">
        <a href="#" class="active">昨天</a><a href="#">近7天</a><a href="#">本月</a><a href="#">上月</a><a href="#">今年</a><a href="#">上年</a>
    </div>
</div>
<div class="top_header">
    <ul>
        <li class="li1"><p>车辆总数</p><span>10辆</span></li>
        <li class="li2"><p>运营车辆</p><span>10辆</span></li>
        <li class="li3"><p>行驶里程</p><span>10KM</span></li>
        <li class="li4"><p>油平均能耗</p><span>10L</span></li>
        <li class="li5"><p>电平均能耗</p><span>10KW/H</span></li>
        <li class="li6"><p>气平均能耗</p><span>10L</span></li>
        <li class="li7"><p>故障异常</p><span>10次</span></li>
        <li class="li8"><p>故障车辆</p><span>10次</span></li>
    </ul>
</div>
<div style="float: left;width: 49.5%;margin-top: 10px" >
    <div class="easyui-panel" style="height: 340px">
        <div id="container1" style="width: 100%;height: 300px"></div>
    </div>
</div>
<div style="float: right;width: 49.5%;margin-top: 10px" >
    <div class="easyui-panel" style="height: 340px">
        <div id="container2" style="width: 100%;height: 300px"></div>
    </div>

</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/dist/jquery.cookie.js"></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script type="text/javascript" src="/dist/highcharts.js"></script>
<script type="text/javascript" src="/dist/exporting.js"></script>
<script type="text/javascript" src="/dist/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="/web/common.js"></script>
<script type="text/javascript" src="/web/can/history.js"></script>
</body>
</html>