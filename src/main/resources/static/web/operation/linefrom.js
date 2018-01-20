var LineFrom = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentid').combotree({
                    prompt:'选择机构',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
        }
    }
}();
LineFrom.init();