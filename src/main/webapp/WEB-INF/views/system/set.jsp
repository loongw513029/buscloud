<%--
  Created by IntelliJ IDEA.
  User: 司机
  Date: 2018/4/17
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
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
<div class="panel"style="margin-left: 20px;margin-right: 20px;">
<div class="basic-info panel-body panel-body-noheader panel-body-noborder">
    <div class="column">
        <span class="current">基本设置</span>
    </div>
    <table class="kv-table">
        <tr>
            <td class="kv-label" style="width: 16.5%">地图速度优先使用</td>
            <td class="kv-content" style="width: 16.5%">
                <input class="easyui-text" id="MapSpeedUse" panelHeight="auto"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%">短信接受号码</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="SMSReceiver"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
        <tr>
            <td class="kv-label" style="width:16.5%">ADAS巡检时间</td>
            <td class="kv-content" style="width:16.5%">
                <input type="text" class="easyui-textbox" id="ADASTime"/>小时
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
    </table>
    <div class="column" style="margin-top: 10px;">
    <span class="current">视频设置</span>
</div>
    <table class="kv-table">
        <tr>
            <td class="kv-label" style="width: 16.5%">视频播放时间</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="VideoPlayTime"/>
            </td>
            <td class="kv-label" style="width: 16.5%">预览视频默认播放时间,单位(分)</td>
            <td class="kv-label" style="width: 16.5%">转发服务器地址</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="ServerIp"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
        <tr>
            <td class="kv-label" style="width: 16.5%">转发服务器端口</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="Port"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%">自动播放</td>
            <td class="kv-content" style="width: 16.5%">
                <input class="easyui-switchbutton" id="AutoPlay"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
    </table>
    <div class="column" style="margin-top: 10px;">
    <span class="current">App版本设置</span>
</div>
    <table class="kv-table">
        <tr>
            <td class="kv-label" style="width: 16.5%">App版本</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="AppVer"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" colspan="1" style="width: 16.5%">App版本说明</td>
            <td class="kv-content" rowspan="2" style="width: 16.5%">
                <input type="text" class="easyui-textbox" multiline="true" style="width: 90%;height: 50px" id="VerNotice"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>

        <tr>
            <td class="kv-label" style="width: 16.5%">App下载地址</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="ApkUrl"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
    </table>
    <div class="column" style="margin-top: 10px;">
        <span class="current">人脸识别</span>
    </div>
    <table class="kv-table">
        <tr>
            <td class="kv-label" style="width: 16.5%">面部识别参数</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="FlceRecognition"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"><span style="display: none;">指数统计单位</span></td>
            <td class="kv-content" style="width: 16.5%;display: none;">
                <input class="easyui-text" id="IndexUnit" panelHeight="auto"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
    </table>
    <div class="column" style="margin-top: 10px;">
        <span class="current">报警设置-设置之后需要刷新浏览器</span>
    </div>
    <table class="kv-table">
        <tr>
            <td class="kv-label" style="width: 16.5%">报警弹窗</td>
            <td class="kv-content" style="width: 16.5%">
                <input class="easyui-switchbutton" id="AlarmTurn"/>
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%">报警提示时间</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="AlarmTipTime"/>秒
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
        <tr style="display: none">
            <td class="kv-label" style="width: 16.5%"></td>
            <td class="kv-label" style="width: 16.5%">报警关联时间</td>
            <td class="kv-content" style="width: 16.5%">
                <input type="text" class="easyui-textbox" id="AlarmRelTime"/>秒
            </td>
            <td class="kv-label" style="width: 16.5%"></td>
        </tr>
    </table>
</div>
</div>
<div style="width: 24%;margin-left: auto;margin-right: auto;margin-top:10px;text-align:center;">
    <a class="easyui-linkbutton" style="width: 40%;" id="btn_Config">保存</a>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/system/set.js"></script>
</body>
</html>
