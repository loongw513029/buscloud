var UserFrom = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#ownershipid').combotree({
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/rolecombo',
                type:'get'
            },function (result) {
                var data = result.result;
                $('#roleid').combotree({
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            if($('#Id').val()!=0){
                $('#ownershipid').combotree('setValue',parseInt($('#ownershipid').val()));
                $('#roleid').combotree('setValue',parseInt($('#roleid').val()));
            }
        },
        saveData:function (parenta,User) {
            var username = $('#username').val();
            var realname = $('#realname').val();
            var ownershipid = $('#ownershipid').combotree('getValue');
            var roleid = $('#roleid').combotree('getValue');
            var phone = $('#phone').val();
            var status = $('#status').prop('checked');
            if(username == ''){
                parenta.TramDalog.ErrorAlert('请输入用户名',true);
                return;
            }
            if(realname == ''){
                parenta.TramDalog.ErrorAlert('请输入姓名',true);
                return;
            }
            if(ownershipid == ''){
                parenta.TramDalog.ErrorAlert('请选择机构',true);
                return;
            }
            if(roleid == ''){
                parenta.TramDalog.ErrorAlert('请选择角色',true);
                return;
            }
            var obj = {
                id:$('#Id').val(),
                username:username,
                realname:realname,
                ownershipid:ownershipid==""?0:ownershipid,
                roleid:roleid==""?0:roleid,
                phone:phone,
                status:status?1:0,
                photo:$('#image').attr("src")=='/images/upload_img.png'?"":$('#image').attr("src")
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/basic/saveuser'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    User.closeWindow();
                    User.reLoad();
                }
            })
        },
        Cover:function (obj) {
            UserFrom.upload(obj.id,function (data) {
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
                url:'/api/v1/upload/uploadfile?type=2',
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
UserFrom.init();