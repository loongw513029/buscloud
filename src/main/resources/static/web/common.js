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
        OpenIframeAndNoBtn:function(title,width,height,url){
            layerIndex=layer.open({
                type: 2 //Page层类型
                ,title:title
                ,skin:'layui-layer-alarm'
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
        Loading2:function () {
            layerIndex=layer.msg('数据加载中...', {
                icon: 16
                ,shade: 0.5
            });
        },
        CloseLoading:function () {
            layer.close(layerIndex);
        },
        /**
         * 普通图片报警
         * @param width 宽度
         * @param height 高度
         * @param titleArr tab名称集合
         * @param contentArr 内容集合
         * @constructor
         */
        LayerTab:function (title,width,height,titleArr,contentArr) {
            // for(var i=0;i<titleArr,length;i++){
            //     $('#alarm-pics>ul>li:eq('+i+')').text(titleArr[i]);
            //     $('#alarm-pics .layui-tab-item:eq('+i+')').text(contentArr[i]);
            // }
            // $('#alarm-pics').removeClass('hidden');
            layer.open({
                type:1,
                title:title,
                skin:'layui-layer-demo',
                area:[width+'px',height+'px'],
                content:$('#alarm-pics')
            });
        },
        LayerVideoAlarm:function () {

        },
        tabObj:function (title,content) {
            this.title = title;
            this.content = content;
        },
        fullScreenVideo:function (code,type,clientip,channel,closecallback) {
            layer.open({
                type: 1,
                title: false,
                closeBtn:1,
                area: ['800px','450px'],
                skin: 'layui-layer-nobg',
                shadeClose: true,
                content:$('#ocxbox'),
                cancel:function () {
                    $('#ocxbox').hide();
                    var ocx = document.getElementById('ocx');
                    try{
                        ocx.Stop();
                    }catch (err){

                    }
                    closecallback();
                },
                success: function(layero, index){
                    $('#ocxbox').show();
                    var ocx = document.getElementById('ocx');
                    try{
                        ocx.StartPreview(parent.Main.getServerIP(),type,code,clientip,channel,1);
                    }catch (err){

                    }
                }
            });

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
        },
        getServerIP:function () {
            return "112.74.192.192";
        },
        getContentHeight:function () {
            return {width: $('.tabs-panels').width(),height:$('.tabs-panels').height()};
        }

    }
}();

var GPS = {
    PI : 3.14159265358979324,
    x_pi : 3.14159265358979324 * 3000.0 / 180.0,
    delta : function (lat, lon) {
        // Krasovsky 1940
        //
        // a = 6378245.0, 1/f = 298.3
        // b = a * (1 - f)
        // ee = (a^2 - b^2) / a^2;
        var a = 6378245.0; //  a: 卫星椭球坐标投影到平面地图坐标系的投影因子。
        var ee = 0.00669342162296594323; //  ee: 椭球的偏心率。
        var dLat = this.transformLat(lon - 105.0, lat - 35.0);
        var dLon = this.transformLon(lon - 105.0, lat - 35.0);
        var radLat = lat / 180.0 * this.PI;
        var magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        var sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);
        return {'lat': dLat, 'lon': dLon};
    },

    //GPS---高德
    gcj_encrypt : function ( wgsLat , wgsLon ) {
        if (this.outOfChina(wgsLat, wgsLon))
            return {'lat': wgsLat, 'lon': wgsLon};

        var d = this.delta(wgsLat, wgsLon);
        return {'lat' : wgsLat + d.lat,'lon' : wgsLon + d.lon};
    },
    outOfChina : function (lat, lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    },
    transformLat : function (x, y) {
        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    },
    transformLon : function (x, y) {
        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
        return ret;
    }
};