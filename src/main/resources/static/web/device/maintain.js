var Device = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/maintenance/Search',
                method: 'get',
                idField: 'id',
                fit: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: Device.datagridQuery(),
                toolbar: "#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'DeviceCode',
                        title: '设备编码',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'MtDate',
                        title: '维保时间',
                        width: 200,
                        align: 'center'
                    },
                    {
                        field: 'Project',
                        title: '维保项目',
                        width: 250,
                        align: 'center'
                    }, {
                        field: 'NextDate',
                        title: '下一次维保时间',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'NextMileage',
                        title: '维保里程',
                        width: 250,
                        align: 'center'
                    }, {
                        field: 'Description',
                        title: '描述',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'CreateTime',
                        title: '创建时间',
                        width: 250,
                        align: 'center'
                    }
                ]],
                loadFilter: function (data) {
                    if (data.success) {
                        return {
                            total: data.result.totalNum,
                            rows: data.result.items
                        };
                    }
                },
                onDblClickRow:function (index,row) {
                    var id = row.id;
                    parent.TramDalog.OpenIframeAndNoBtn(row.alarmname+"-"+row.devicecode,652,538,"/alarm/view?id="+id);
                }
            });
            Device.initComboTree();
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {
                    Device.Search(value);
                }
            });
        },
        initComboTree:function (){
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineId').combotree({
                    data:data
                });
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentId').combotree({
                    data:data
                });
            });
            $('#date1').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                    return date<=d2-1;
                }
            });
            $('#date2').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                    return date<=d2;
                }
            });
        },
        datagridQuery:function () {
            return {
                departmentId:0,
                lineId:0,
                date1:'',
                date2:'',
                keywords:''
            };
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        },
        Search:function (value) {
            var query = $('#table').datagrid('options').queryParams;
            var departmentId = $('#departmentId').combotree('getValue');
            var lineId = $('#lineId').combotree('getValue');
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            query.departmentId = departmentId==''?0:departmentId;
            query.lineId = lineId==''?0:lineId;
            query.date1 = date1;
            query.date2 = date2;
            query.keywords = value;
            Device.reLoad();
        }
    }
}();
Device.init();