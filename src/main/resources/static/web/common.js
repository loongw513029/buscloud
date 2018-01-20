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
        CloseLayer:function () {
            layer.close();
        }
    }
}();