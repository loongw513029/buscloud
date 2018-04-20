var Rapair=function () {
    return {
        Init:function () {
            $('#table').datagrid({
                url: '/api/v1/device/repairlist',
                method: 'get',
                idField: 'id',
                fit: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: Rapair.datagridQuery(),
                toolbar: "#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'Number',
                        title: '流水号',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'devicecode',
                        title: '车号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'Title',
                        title: '报修主题',
                        width: 100,
                        align: 'center'
                    },
                    {
                        field: 'FaultType',
                        title: '维修类型',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'RealName',
                        title: '维修员',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 'LimitTime',
                        title: '时效',
                        width: 50,
                        align: 'center'
                    }
                ]],
                columns: [[
                    {
                        field: 'speed',
                        title: '审核状态',
                        width: 100,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value == 0 ? "未审核" : value == 1 ? "已审核" : "不需要审核";
                        }
                    }, {
                        field: 'distance',
                        title: '维修状态',
                        width: 100,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value == 0 ? "--" : value == 1 ? "正在维修" : "维修完成";
                        }
                    }, {
                        field: 'isbrake',
                        title: '申请时间',
                        width: 150,
                        formatter: function (value, row, index) {
                            return value == null ? "" : FormatData(value);
                        },
                        align: 'center'
                    }, {
                        field: 'updatetime',
                        title: '处理时间',
                        width: 150,
                        formatter: function (value, row, index) {
                            return value == null ? "" : FormatData(value);
                        },
                    }, {
                        field: 'value',
                        title: '操作',
                        width: 100,
                        formatter: function (value, row, index) {
                            var h = [];
                            if(row.RealName!=null)
                                h.push("<a href='javascript:;' class='alarm-icon'><span class='iconfont'>查看</span></a> ");
                            else
                                h.push("<a href='javascript:;' class='alarm-icon'><span class='iconfont'>分派</span></a> ");
                            return h.join('');
                        }
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
            Rapair.initComboTree();
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {
                    Rapair.Search(value);
                }
            });
        },
        initComboTree:function (){
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
                data1:'',
                data2:'',
                code:''
            };
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        },
        Search:function (value){
            var query = $('#table').datagrid('options').queryParams;
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            query.date1 = date1;
            query.date2 = date2;
            query.code = value;
            Rapair.reLoad();
        }
    }
}();
Rapair.Init();