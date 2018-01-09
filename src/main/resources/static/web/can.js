var Can=function () {
    return{
        init:function () {
            layui.use('form', function(){
                var form = layui.form;
                //监听提交
                form.on('submit(formDemo)', function(data){
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
            });
        }
    }
}();
Can.init();
