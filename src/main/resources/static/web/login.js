/**
 * 登录模块
 * @type {{init, startLogin}}
 */
var Login = function(){
    return {
        init:function () {
            $(document).on('click','.supplier',function () {
                Login.startLogin();
            });
            var cookie_username = $.cookie("cookie_username");
            if(cookie_username!=null)
                $("input[name='username']").val(cookie_username);
        },
        startLogin:function () {
            var ischeck = $("input[name='getpwd']").prop("checked");
            var data={
                username:$("input[name='username']").val(),
                password:$("input[name='password']").val(),
                clientid:'',
                logintype:3
            };
            Http.Ajax({
                url:'/api/v1/account/login',
                type:'post',
                data:data
            },function (result) {
                if(result.success){
                    if(ischeck)
                        $.cookie("cookie_username",data.username);
                    var user=result.result;
                    var userStr=JSON.stringify(user);
                    $.cookie("userinfo",userStr);
                    $.cookie("access_token",user.accessToken);
                    location.href="/index";
                }
                else{
                    layer.alert(result.info,{icon:2})
                }
            },function (error) {
                
            })
        }
    }
}();
Login.init();