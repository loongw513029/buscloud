var BuildFaceFrom = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentId').combotree({
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            if($('#Id').val()!=0){
                $('#departmentId').combotree('setValue',parseInt($('#ownershipid').val()));
            }
        },
        saveData:function (parenta,BuildFace) {
            var name = $('#name').val();
            var sex = $('#sex').combobox('getValue');
            var departmentId = $('#departmentId').combotree('getValue');
            var oldName = $('#oldName').val();
            var status = $('#status').prop('checked');
            var birth = $('#birth').datebox('getValue');
            var credentialStype = $('#credentialStype').combobox('getValue');
            if(name == ''){
                parenta.TramDalog.ErrorAlert('请输入用户名',true);
                return;
            }
            if(departmentId == ''){
                parenta.TramDalog.ErrorAlert('请选择机构',true);
                return;
            }

            var obj = {
                id:$('#Id').val(),
                name:name,
                oldName:oldName,
                sex:sex,
                birth:birth,
                departmentId:departmentId==""?0:departmentId,
                credentialStype:credentialStype,
                degree:$('#degree').val(),
                idCard:$('#idCard').val(),
                memo:$('#memo').val(),
                images:$('#image').attr("src")=='/images/upload_img.png'?"":$('#image').attr("src")
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/buildface/saveperson'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    BuildFace.closeWindow();
                    BuildFace.reLoad();
                }
            },function () {

            });
        },
        Cover:function (obj) {
            BuildFaceFrom.upload(obj.id,function (data) {
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
                url:'/api/v1/upload/uploadfile?type=3',
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
BuildFaceFrom.init();