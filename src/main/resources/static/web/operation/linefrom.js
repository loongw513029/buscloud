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
                var departmentId = $('#hdDepartmentId').val();
                $('#departmentid').combotree('setValue',departmentId);
            });
        },
        saveData:function (parenta,Line) {
            var linename = $('#linename').val();
            var linecode = $('#linecode').val();
            var id = $('#Id').val();
            var departmentid = $('#departmentid').combotree('getValue');
            var lineupmileage = $('#lineupmileage').val();
            var linedownmileage = $('#linedownmileage').val();
            var upsitenum = $('#upsitenum').val();
            var downsitenum = $('#downsitenum').val();

            if(linename == ''){
                parenta.TramDalog.ErrorAlert('请输入线路名称',true);
                return;
            }
            if(departmentid == ''){
                parenta.TramDalog.ErrorAlert('请选择所属机构',true);
                return;
            }
            var obj = {
                id:id,
                linename:linename,
                linecode:linecode,
                departmentid:departmentid==""?0:departmentid,
                lineupmileage:lineupmileage,
                linedownmileage:linedownmileage,
                upsitenum:upsitenum,
                downsitenum:downsitenum
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/operation/saveline'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    Line.closeWindow();
                    Line.onLoad();
                }
            })
        }
    }
}();
LineFrom.init();