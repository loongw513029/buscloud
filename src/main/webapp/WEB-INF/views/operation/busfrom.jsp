
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
    <script type="text/javascript">
        var channel = ${obj.videochannel};
        var channelliststr = '${obj.channellist}';
    </script>
</head>
<body>

<input type="hidden" id="Id" value="${obj.id}" />
<input type="hidden" id="busId" value="${obj.busid}"/>
<div class="panel">
    <div class="basic-info panel-body panel-body-noheader panel-body-noborder" data-options="closable:false">
        <div class="column">
            <span class="current">车辆信息</span>
        </div>
        <table class="kv-table">
            <tr>
                <td class="kv-label" style="width: 16.5%">车牌号码</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="busnumber" value="${obj.busnumber}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">车架号</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="busframenumber" value="${obj.busframenumber}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">车辆类型</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="bustype"/>
                    <input type="hidden" id="hdBusType" value="${obj.bustype}" />
                </td>
            </tr>
            <tr>
                <td class="kv-label" style="width: 16.5%">所属机构</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="departmentid" value="${obj.departmentid}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">所属线路</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" id="lineid" value="${obj.lineid}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">选择司机</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" id="driverid" value="${obj.driverid}"/>
                </td>
            </tr>
        </table>
        <div class="column">
            <span class="current">设备信息</span>
        </div>
        <table class="kv-table">
            <tr>
                <td class="kv-label" style="width: 16.5%">自编号</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="devicecode" value="${obj.devicecode}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">设备条码</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="devicename" value="${obj.devicename}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">车辆IP</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="clientip" value="${obj.clientip}"/>
                </td>
            </tr>
            <tr>
                <td class="kv-label" style="width: 16.5%">设备型号</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" class="easyui-textbox" id="devicemode" value="${obj.devicemode}"/>
                </td>
                <td class="kv-label" style="width: 16.5%">硬盘大小</td>
                <td class="kv-content" style="width: 16.5%">
                    <select class="combo" id="disksize">
                        <option value="1024" ${obj.disksize==1024?"selected='selected'":""}>1024G</option>
                        <option value="500" ${obj.disksize==500?"selected='selected'":""}>500G</option>
                    </select>
                </td>
                <td class="kv-label" style="width: 16.5%">SD卡大小</td>
                <td class="kv-content"  style="width: 16.5%">
                    <select class="combo" id="sdcardsize">
                        <option value="64" ${obj.sdcardsize==64?"selected='selected'":""}>64G</option>
                        <option value="32" ${obj.sdcardsize==32?"selected='selected'":""}>32G</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="kv-label">监控软件类型</td>
                <td class="kv-content">
                    <input type="hidden" id="hdHostTypeSoft" value="${obj.hostsofttype}" />
                    <input type="text" id="hosttypesoft" />
                </td>
            </tr>
        </table>
        <div class="column" style="margin-top: 5px">
            <span class="current">设备配置</span> <input type="checkbox" id="videosupport"  ${obj.videosupport?"checked='checked'":""}/>支持视频
            总通道 <input type="text" id="videochannel" value="${obj.videochannel}" style="width: 50px;height: 14px"/>
        </div>
        <table class="kv-table">
            <tr>
                <td class="kv-label">
                    <input type="checkbox" id="aerialview" ${obj.aerialview?"checked='checked'":""} />鸟瞰支持
                </td>
                <td class="kv-label" style="width: 16.5%">
                    <input type="checkbox" id="barrier" ${obj.barrier?"checked='checked'":""} />防撞系统
                </td>
                <td class="kv-label">
                    <input type="checkbox" id="can" ${obj.can?"checked='checked'":""} />CAN设备
                </td>
                <td class="kv-label" style="width: 16.5%">
                    <input type="checkbox" id="radar" ${obj.radar?"checked='checked'":""} />雷达设备
                </td>
                <td class="kv-label">
                    <input type="checkbox" id="supportbehavior" ${obj.supportbehavior?"checked='checked'":""} />识别设备
                </td>
                <td class="kv-label" style="width: 16.5%">
                    <input type="checkbox" id="supportadas" ${obj.supportadas?"checked='checked'":""} />ADAS
                </td>
            </tr>
            <tr>
                <td class="kv-label" style="width: 16.5%">鸟瞰通道</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" id="aerialchannel"/>
                    <input type="hidden" id="hdaerialchannel" value="${obj.aerialchannel}" />
                </td>
                <td class="kv-label" style="width: 16.5%">车头通道</td>
                <td class="kv-content" style="width: 16.5%">
                    <input type="text" id="dchannel" />
                    <input type="hidden" id="hddchannel" value="${obj.dchannel}" />
                </td>
                <td class="kv-label"  style="width: 16.5%">车厢通道</td>
                <td class="kv-content"  style="width: 16.5%">
                    <input type="text" id="carriagechannel"/>
                    <input type="hidden" id="hdcarriagechannel" value="${obj.carriagechannel}" />
                </td>
            </tr>
        </table>
        <div class="customChannel" style="display: none">
            <div class="column"><span class="current">自定义通道</span></div>
            <table class="kv-table" id="channelLayout">
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/operation/busfrom.js"></script>
</body>
</html>
