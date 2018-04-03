
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="/css/base.css" >
    <style>
        .tabs-header, .tabs-tool{background-color: transparent;border: 0px}

    </style>
</head>
<body>

<input type="hidden" id="Id" value="${obj.id}" />
<div id="tview" class="easyui-tabs">
    <div title="全景图片" ><img src="${obj.value.split(',')[0]}" width="638px" height="450px"/> </div>
    <div title="前路面" style="display: none;"><img src="${obj.value.split(',')[1]}" width="638px" height="450px"/></div>
    <div title="详细信息" data-options="colsable:true" style="display: none;padding:5px">
                <table class="kv-table">
                    <tr>
                        <td class="kv-label">设备自编号</td>
                        <td class="kv-content">${obj.devicecode}</td>
                        <td class="kv-label">车牌号</td>
                        <td class="kv-content">${obj.busnumber}</td>
                    </tr>
                    <tr>
                        <td class="kv-label">机构名称</td>
                        <td class="kv-content">${obj.departmentname}</td>
                        <td class="kv-label">线路名称</td>
                        <td class="kv-content">${obj.linename}</td>
                    </tr>
                    <tr>
                        <td class="kv-label">报警类型</td>
                        <td class="kv-content">${obj.alarmname}</td>
                        <td class="kv-label">报警时间</td>
                        <td class="kv-content">${obj.updatetime}</td>
                    </tr>
                    <tr>
                        <td class="kv-label">报警地点</td>
                        <td class="kv-content" colspan="3" id="address"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">当前车速</td>
                        <td class="kv-content">${obj.speed} KM/H</td>
                        <td class="kv-label">前车车距</td>
                        <td class="kv-content">${obj.distance} M</td>
                    </tr>
                    <tr style="${obj.path==''?"display:none":"display:block"}">
                        <td class="kv-label">报警视频</td>
                        <td colspan="3" class="kv-content">
                            <a href="javascript:;">展开>></a>
                            <object id="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height: 56.3%;display: none;">
                                <param name="_Version" value="1.1.0">
                                <param name="_ExtentX" value="2646">
                                <PARAM name="_ExtentY" value="1323">
                                <PARAM name="_StockProps" value="0" width="100%" height="100%">
                            </object>
                        </td>
                    </tr>
                </table>
            </div>

</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
</body>
</html>
