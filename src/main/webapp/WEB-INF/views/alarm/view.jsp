<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="/css/base.css">
    <style>
        .tabs-header, .tabs-tool {
            background-color: transparent;
            border: 0px
        }

    </style>
</head>
<body>

<input type="hidden" id="Id" value="${obj.id}"/>
<div id="tview" class="easyui-tabs">
    <c:if test="${length==3}">
        <div title="前路面" data-options="iconCls:'alarmPic'" style="padding: 5px">
            <img src="${obj.value.split(',')[0]}" width="100%" height="445px"/>
        </div>
        <div title="驾驶员全景" data-options="iconCls:'alarmPic'" style="padding: 5px">
            <img src="${obj.value.split(',')[1]}" width="686px" height="445px"/>
        </div>
        <div title="行为分析设备图" data-options="iconCls:'alarmPic'" style="padding: 5px">
            <img src="${obj.value.split(',')[2]}" width="686px" height="445px"/>
        </div>
    </c:if>
    <c:if test="${length==2}">
        <div title="全景图片"  data-options="iconCls:'alarmPic'" style="padding: 5px">
            <img src="${obj.value.split(',')[0]}" width="686px" height="445px"/>
        </div>
        <div title="${obj.parentalarmtype==61?'司机正面':'前路面'}" data-options="iconCls:'alarmPic'" style="padding: 5px">
            <img src="${obj.value.split(',')[1]}" width="686px" height="445px"/>
        </div>
    </c:if>
    <div title="详细信息" data-options="iconCls:'alarmInfo'" style="display: none;padding:5px">
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
        </table>
    </div>
    <c:if test="${obj.path!=null}">
        <div title="驾驶员全景视频" class="alarm" data-options="iconCls:'alarmVideo'" style="display: none;padding:5px">
            <object id="ocx1" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4"
                    style="width: 677px;height: 388px;display: none;background: #0C0C0C">
                <param name="_Version" value="1.1.0">
                <param name="_ExtentX" value="2646">
                <PARAM name="_ExtentY" value="1323">
                <PARAM name="_StockProps" value="0" width="100%" height="100%">
            </object>
        </div>
    </c:if>
    <c:if test="${obj.alarmvideopath!=null}">
        <div title="前路面视频" class="alarm"  data-options="iconCls:'alarmVideo'" style="display: none;padding:5px">
            <object id="ocx2" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4"
                    style="width: 677px;height: 388px;display: none;background: #0C0C0C">
                <param name="_Version" value="1.1.0">
                <param name="_ExtentX" value="2646">
                <PARAM name="_ExtentY" value="1323">
                <PARAM name="_StockProps" value="0" width="100%" height="100%">
            </object>
        </div>
    </c:if>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    var path = '${obj.path}';
    var path2 = '${obj.alarmvideopath}';
    $('#tview').tabs({
        border:false,
        onSelect:function(title,index){
            closeOcx();
            var cls =  $("#tview .tabs li:eq("+index+")>a>span:eq(1)").attr("class");
            if(cls.indexOf('alarmVideo')>0){
                var ocx = $('#tview .tabs-panels>div:eq('+index+')').find('object');
                try{
                   ocx.show();
                   var id = ocx.attr('id');
                   var ocxt = document.getElementById(id);
                   var serverIp = parent.parent.Main.getServerIP();
                   ocxt.StartAlarmRecord(serverIp, '${obj.devicecode}', 'admin', 'admin', id=='ocx1'?path:path2);
                }catch (err){
                    console.log(err);
                }
            }
        }
    });
    function expandOCX() {
        var ocx = document.getElementById('ocx');
        var block = ocx.style.display;
        if (block == 'block') {
            ocx.Stop();
            ocx.style.display = 'none';
        } else {
            ocx.style.display = 'block';
            var serverIp = parent.parent.Main.getServerIP();
            ocx.StartAlarmRecord(serverIp, '${obj.devicecode}', 'admin', 'admin', path);
        }
    }
    window.onbeforeunload = function () {
        closeOcx();
    }
    function closeOcx(){
        var ocx1 = document.getElementById('ocx1'),
            ocx2 = document.getElementById('ocx2');
        try{
            ocx1.style.display = 'none';
            ocx2.style.display = 'none';
            ocx1.Stop();
            ocx2.Stop();
        }catch (err){
            console.log(err);
        }
    }
</script>
</body>
</html>
