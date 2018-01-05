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