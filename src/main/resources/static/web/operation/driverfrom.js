var DriverFrom = function () {
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
                var departmentId = $('#hdDepartmentId').val();
                $('#departmentid').combotree('setValue',departmentId);
            });
            $('#slgender').combotree({data:[{id:1,text:'男'},{id:0,text:'女'}]});
        },
        saveData:function (parenta,Driver) {
            pt=parenta;
            var drivername = $('#drivername').val();
            var gender = $('#slgender').combotree('getValue');
            var id = $('#Id').val();
            var departmentid = $('#departmentid').combotree('getValue');
            var contactphone = $('#contactphone').val();
            var driverheaderimg = $("#image").attr("src");
            if(drivername == ''){
                parenta.TramDalog.ErrorAlert('请输入线路名称',true);
                return;
            }
            if(departmentid == ''){
                parenta.TramDalog.ErrorAlert('请选择所属机构',true);
                return;
            }
            var obj = {
                id:id,
                drivername:drivername,
                gender:gender,
                departmentid:departmentid==""?0:departmentid,
                contactphone:contactphone,
                driverheaderimg:driverheaderimg
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/operation/savedriver'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    Driver.closeWindow();
                    Driver.onLoad();
                }
            })
        },
        Cover:function (obj) {
            DriverFrom.upload(obj.id,function (data) {
                 if(data.success){
                     $("#image").attr("src",data.result);
                 }else {
                     if(pt) pt.TramDalog.ErrorAlert(data.result, true);
                 }
            })
        },
        upload:function (feid,callback) {
            $.ajaxFileUpload({
                fileElementId:feid,
                url:'/api/v1/upload/uploadfile',
                type:'post',
                dataType:'json',
                secureuri:false,
                async:true,
                success:function (data) {
                    if(callback) callback.call(this,data);
                },
                error:function (data,status,e) {
                    console.error(e);
                }
            })
        }
    }
}();
var pt=null;
DriverFrom.init();