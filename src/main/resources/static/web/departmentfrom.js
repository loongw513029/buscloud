var DepartmentFrom = function () {
    return{
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#parentId').combotree({
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            if($('#Id').val()!=0){
                $('#parentId').combotree('setValue',parseInt($('#parentId').val()));
            }
        },
        getData:function () {
            return $('#Id').val();
        },
        saveData:function (pt,part) {
            var saveResult = false;
            //var v =$('#form').form('validate');
            var partnetid = $('#parentId').combotree('getValue');
            var code = $('#departmentcode').val();
            var name = $('#departmentname').val();
            if(name==''){
                pt.TramDalog.ErrorAlert('请填写机构名称',true);
                return;
            }
            var obj = {
                id: $('#Id').val(),
                code: code,
                departmentname: name,
                parentid: partnetid==''?0:partnetid,
                contactname: $('#contactname').val(),
                contactphone: $('#contactphone').val(),
                remark: $('#remark').val(),
                islookcan: $('#ishavecan').prop('checked') ? 1 : 0,
                ishavevedio: $('#ishavevideo').prop('checked') ? 1 : 0,
                appname: $('#appname').val()
            };
            parent.Http.Ajax({
                url: '/api/v1/basic/savedepartment',
                type: 'post',
                data: obj,
                cache:false,
            }, function (result) {
                if (!result.success) {
                    pt.TramDalog.ErrorAlert(result.info, true);
                    saveResult = false;
                } else {
                    pt.TramDalog.SuccessAlert(result.info, true);
                    part.closeWindow();
                    part.reLoad();
                }
            });
            return saveResult;
        }
    }
}();
DepartmentFrom.init();