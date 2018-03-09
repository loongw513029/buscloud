var layerIndex;
var expandNodeIdArr=[];
var Http=function(){
    return {
        //统一请求函数
        Ajax:function (obj,callback,errcallback) {
            var token=$.cookie("access_token");
            $.ajax({
                url:obj.url,
                type:obj.type,
                data:obj.data,
                cache:obj.cache,
                contentType:obj.ctype,
                beforeSend:function (request) {
                    request.setRequestHeader("timespan",Date.parse(new Date())/1000);
                    request.setRequestHeader("access_token",token);
                },
                success:function (result) {
                    if(result.code == 405)//授权失败
                    {

                    }
                    else{
                        callback(result);
                    }
                },
                error:function (err) {
                    errcallback(err);
                }
            });
        }
    }
}();

var User = function () {
    return {
        //获得用户
        GetUserInfo:function () {
            var userStr=$.cookie("userinfo");
            if(userStr!=null){
                return JSON.parse(userStr);
            }
            else
                return null;
        }
    }
}();

var TramDalog=function(){
    return {
        SuccessAlert:function (title,isMsg) {
            if(isMsg)
                layer.msg(title,{icon:1});
            else
                layer.alert(title,{icon:1});
        },
        ErrorAlert:function(title,isMsg){
            if(isMsg)
                layer.msg(title,{icon:2});
            else
                layer.alert(title,{icon:2});
        },
        InfoAlert:function (title,isMsg) {
            if(isMsg)
                layer.msg(title,{icon:0});
            else
                layer.alert(title,{icon:0});
        },
        Confirm:function (title,btns,callback1,callback2) {
            layer.confirm(title,{btn:btns},callback1,function () {
                layer.close();
            });
        },
        /**
         * 打开自定义window
         * @param width 宽度
         * @param height 高度
         * @param title 标题
         * @param content 自定义HTML内容或者页面上的DOM对象
         * @param btns 底部按钮组
         * @param callback1 按钮1函数
         * @param callback2 按钮2函数
         * @param callback3 按钮3函数
         * @constructor
         */
        OpenWindow:function(width,height,title,content,btns,success,callback1,callback2){
            layer.open({
                type: 1 //Page层类型
                ,area: [width+'px', height+'px']
                ,title: title
                ,shade: 0.6 //遮罩透明度
                ,maxmin: true //允许全屏最小化
                ,anim: 2 //0-6的动画形式，-1不开启
                ,content: content
                ,btn:btns
                ,success:success
                ,yes:callback1
                ,cancel:callback2
            });
        },
        OpenIframe:function(width,height,title,url,callback1){
            layer.open({
                type: 2 //Page层类型
                ,area: [width+'px', height+'px']
                ,title: title
                ,shade: 0.6 //遮罩透明度
                ,maxmin: true //允许全屏最小化
                ,anim: 2 //0-6的动画形式，-1不开启
                ,content: url
                ,btn:["确认","取消"]
                ,yes:callback1
            });
        },
        OpenIframeAndNoBtn:function(width,height,url){
            layer.open({
                type: 2 //Page层类型
                ,title:false
                ,area: [width+'px', height+'px']
                ,shade: 0.6 //遮罩透明度
                ,anim: 2 //0-6的动画形式，-1不开启
                ,content: url
            });
        },
        CloseLayer:function () {
            layer.close();
        },
        //数据请求加载框
        Loading:function () {
            layerIndex=layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shade:0.5,
                area: '85px',
                skin: 'loading-bg', //没有背景色
                shadeClose: false,
                content: '<img src="/images/position-icon.png" width="75px"/>车辆定位中<br/>'
            });
        },
        CloseLoading:function () {
            layer.close(layerIndex);
        }
    }
}();
var Main =function () {
    return {
        toggleTree:function () {
            $("#pf-bd").find(".toggle-icon").toggleClass("toggle");
            setTimeout(function(){
                $(window).resize();
            },300)
        },
        /**
         * 转换gps坐标
         * @param gpsLocation
         * @returns {string}
         * @constructor
         */
        ConvertGpsToAmapLocation:function (gpsLocation) {
            var loc = "";
            var url = "http://restapi.amap.com/v3/assistant/coordinate/convert?locations=" + location + "&coordsys=gps&output=json&key=e30e5e9f5e8b3132a56321bd016aa1e3";
            $.ajaxSettings.async = false;
            $.getJSON(url, function (data) {
                loc = data.locations;
            });
            $.ajaxSettings.async = true;
            return loc;
        },
        /**
         * 根据高德坐标获得地址
         * @param amapLocation
         * @returns {string}
         */
        getAddressByAmapLocation:function (amapLocation) {
            var address = "";
            var url = "http://restapi.amap.com/v3/geocode/regeo?key=e30e5e9f5e8b3132a56321bd016aa1e3&location=" + location + "&poitype=&radius=1&extensions=base&batch=false&roadlevel=0";
            $.ajaxSettings.async = false;
            $.getJSON(url, function (data) {
                address = data.regeocode.formatted_address;
            });
            $.ajaxSettings.async = true;
            return address;
        }
    }
}();