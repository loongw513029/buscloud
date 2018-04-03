
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

</head>
<body>
<div id="tview">
    <object id="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height: 100%;display: none;">
        <param name="_Version" value="1.1.0">
        <param name="_ExtentX" value="2646">
        <PARAM name="_ExtentY" value="1323">
        <PARAM name="_StockProps" value="0" width="100%" height="100%">
    </object>
</div>
<script type="text/javascript">
    var serverIP = location.hostname;
    var path = '${obj.path}';
    var code = '${obj.devicecode}';
    var username = 'admin',password = 'admin';
    var ocx = document.getElementById("ocx");
    try{
        ocx.StartAlarmRecord(serverIP,code,username,password,path);
    }catch (err){
        document.getElementById("tview").innerText = '浏览器不支持视频插件，请在IE9+浏览器使用';
    }
</script>
</body>
</html>
