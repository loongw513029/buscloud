
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var serverip = '${serverip}';
        var clientip = '${clientip}';
        var type = ${type};
        var code = '${code}';
        var channel = ${channel};
        var ocx = document.getElementById('ocx');
        ocx.StartPreview(serverip,type,code,clientip,channel,1);
        window.onbeforeunload = function () {
            ocx.Stop();
        }
    </script>
</head>
<body >
<object id="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000">
    <param name="_Version" value="1.1.0">
    <param name="_ExtentX" value="2646">
    <PARAM name="_ExtentY" value="1323">
    <PARAM name="_StockProps" value="0" width="100%" height="100%">
</object>
</body>
</html>
