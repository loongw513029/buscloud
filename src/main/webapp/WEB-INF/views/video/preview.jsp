<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link href="/fonts/iconfont.css" rel="stylesheet">
    <link href="/css/video.css" rel="stylesheet">
</head>
<body>
    <div class="video-box">
        <div class="video-item">
            <div class="header"><span></span><a href="javascript:;" class="play"></a><a href="javascript:;" class="full"></a> </div>
            <object id="ocx0" class="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000"></object>
        </div>
        <div class="video-item">
            <div class="header"><span></span><a href="javascript:;" class="play"></a><a href="javascript:;" class="full"></a> </div>
            <object id="ocx1" class="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000"></object>
        </div>
        <div class="video-item">
            <div class="header"><span></span><a href="javascript:;" class="play"></a><a href="javascript:;" class="full"></a> </div>
            <object id="ocx2" class="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000"></object>
        </div>
        <div class="video-item">
            <div class="header"><span></span><a href="javascript:;" class="play"></a><a href="javascript:;" class="full"></a> </div>
            <object id="ocx3" class="ocx" classid="clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4" style="width: 100%;height:100%;background: #000000"></object>
        </div>
    </div>
    <div class="ptzLayout">
        <div class="yt">云台控制</div>
        <div class="circe">
            <div class="angle left" _n="5" k="3"></div>
            <div class="angle lefttop" _n="4" k="5"></div>
            <div class="angle top" _n="3" k="1"></div>
            <div class="angle topright" _n="2" k="6"></div>
            <div class="angle right" _n="1" k="4"></div>
            <div class="angle rightbottom" _n="8" k="8"></div>
            <div class="angle bottom" _n="7" k="2"></div>
            <div class="angle bottomleft" _n="6" k="7"></div>
        </div>
        <div class="item">
            <button class="guangquanleft" _data="14"></button>
            <label>光圈</label>
            <button class="guangquanright" _data="13"></button>
        </div>
        <div class="item">
            <button class="jujiaoleft" _data="12"></button>
            <label>聚焦</label>
            <button class="jujiaoright" _data="11"></button>
        </div>
        <div class="item">
            <button class="suofangleft" _data="10"></button>
            <label>缩放</label>
            <button class="suofangright" _data="9"></button>
        </div>

    </div>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/web/video/preview.js"></script>
</body>
</html>