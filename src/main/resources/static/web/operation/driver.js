var Driver= function(){
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/operation/getdriverlist',
                method:'get',
                idField:'id',
                fit:true,
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:Driver.datagridQuery(),
                toolbar:"#toolbar",
                columns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'drivername',
                        title: '司机名称',
                        width: 70
                    },
                    {
                        field: 'departmentname',
                        title: '所属机构',
                        width: 120
                    },{
                        field: 'gender',
                        title: '性别',
                        width: 50,
                        formatter:function (value,row,index) {
                            return value == 1 ? "男" : "女";
                        }
                    },{
                        field: 'contactphone',
                        title: '联系电话',
                        width: 100
                    }
                ]],
                loadFilter:function (data) {
                    if(data.success){
                        return {
                            total:data.result.totalNum,
                            rows:data.result.items
                        };
                    }
                }
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentId').combotree({
                    prompt:'选择机构',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            $('#txtkey').searchbox({
                prompt:'司机名称',
                searcher:function (value,name) {
                    Driver.Search(value);
                }
            })
        },
        datagridQuery:function () {
            var params = {
                drivername:"",
                departmentid:0
            };
            return params;
        },
        AddLine:function () {
            parent.TramDalog.OpenIframe(650,405,'新增司机',"/operation/driverfrom?id=0",function (layerno,index) {
                Driver.saveData(layerno);
            });
        },
        EditLine:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length>1||row.length==0)
                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
            else{
                var id = row[0].id;
                parent.TramDalog.OpenIframe(650,405,'编辑司机',"/operation/driverfrom?id="+id,function (layerno,index) {
                    Driver.saveData(layerno);
                });
            }
        },
        RemoveLine:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length==0)
                parent.TramDalog.ErrorAlert("请选择数据！",true);
            var Ids = [];
            for(var i=0;i<row.length;i++){
                Ids.push(row[i].id);
            }
            parent.Http.Ajax({
                url:'/api/v1/operation/removedriver?ids='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    Driver.onLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.DriverFrom.saveData(parent,Driver);
        },
        Search:function (value) {
            var query = $('#table').datagrid('options');
            var seldpid = $('#departmentId').combotree('getValue');
            if(seldpid!=0){
                query.queryParams.departmentid  = seldpid;
            }
            query.queryParams.drivername = value;
            Driver.onLoad();
        },
        closeWindow:function () {
            parent.layer.close(currentIndex);
        },
        onLoad:function () {
            $('#table').datagrid('reload');
        }
    }
}();
Driver.init();