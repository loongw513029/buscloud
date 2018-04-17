var PayCords = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/alarm/getPayRecords',
                method: 'get',
                idField: 'id',
                fit: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: PayCords.datagridQuery(),
                toolbar: "#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'devicecode',
                        title: '设备编号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'payCardNo',
                        title: '支付卡号',
                        width: 100,
                        align: 'center'
                    },
                    {
                        field: 'paytime',
                        title: '支付时间',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 'location',
                        title: '站点名称',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'siteName',
                        title: '地址',
                        width: 100,
                        align: 'center'
                    }
                    , {
                        field: 'passengerlmage',
                        title: '乘客照片',
                        width: 100,
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
            PayCords.initComboTree();
            $('#txtkey').searchbox({
                prompt:'站点名称',
                searcher:function (value,name) {
                    PayCords.Search(value);
                }
            });
        },
        initComboTree:function () {
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
                CardNo:0,
                date1:'',
                date2:'',
                keywords:'',
            };
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        },
        Search:function (value) {
            var query = $('#table').datagrid('options').queryParams;
            var departmentId = $('#CardNo').combotree('getValue');
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            query.CardNo = CardNo;
            query.date1 = date1;
            query.date2 = date2;
            query.keywords = value;
            PayCords.reLoad();
        }
    }
}();
PayCords.init();