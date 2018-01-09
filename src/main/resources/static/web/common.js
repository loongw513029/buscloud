var Http=function(){
    return {
        //统一请求函数
        Ajax:function (obj,callback,errcallback) {
            var token=$.cookie("access_token");
            $.ajax({
                url:obj.url,
                type:obj.type,
                data:obj.data,
                beforeSend:function (request) {
                    request.setRequestHeader("timespan",Date.parse(new Date())/1000);
                    request.setRequestHeader("access_token",token);
                },
                success:function (result) {
                    callback(result);
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
                layer.msg(title,{icon:2});
            else
                layer.alert(title,{icon:2});
        },
        ErrorAlert:function(title,isMsg){
            if(isMsg)
                layer.msg(title,{icon:1});
            else
                layer.alert(title,{icon:1});
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
        OpenWindow:function(width,height,title,content){
            layer.open({
                type: 1 //Page层类型
                ,area: [width+'px', height+'px']
                ,title: title
                ,shade: 0.6 //遮罩透明度
                ,maxmin: true //允许全屏最小化
                ,anim: 2 //0-6的动画形式，-1不开启
                ,content: content
            });
        },
        OpenIframe:function(width,height,title,url){
            layer.open({
                type: 2 //Page层类型
                ,area: [width+'px', height+'px']
                ,title: title
                ,shade: 0.6 //遮罩透明度
                ,maxmin: true //允许全屏最小化
                ,anim: 2 //0-6的动画形式，-1不开启
                ,content: url
            });
        }
    }
}();