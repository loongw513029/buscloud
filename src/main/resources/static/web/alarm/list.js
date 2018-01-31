var Alarm = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/alarm/gettablelist',
                method: 'get',
                idField: 'id',
                fit: true,
                fitColumns: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: Alarm.datagridQuery(),
                toolbar: "#toolbar",
                columns: [[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'devicecode',
                        title: '设备编码',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'busnumber',
                        title: '车牌号码',
                        width: 100,
                        align: 'center'
                    },
                    {
                        field: 'linename',
                        title: '线路名称',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 'departmentname',
                        title: '机构名称',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'alarmname',
                        title: '报警类型',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'location',
                        title: '报警位置',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'speed',
                        title: '车速',
                        width: 50,
                        align: 'center',
                        formatter: function (value) {
                            return value + "Km/h";
                        }
                    }, {
                        field: 'distance',
                        title: '车距',
                        width: 50,
                        align: 'center',
                        formatter: function (value) {
                            return value + "M";
                        }
                    }, {
                        field: 'isbrake',
                        title: '刹车状态',
                        width: 50,
                        formatter: function (value, row, index) {
                            return value ? "是" : "否";
                        },
                        align: 'center'
                    }, {
                        field: 'updatetime',
                        title: '报警时间',
                        width: 120
                    }, {
                        field: 'value',
                        title: '操作',
                        width: 100,
                        formatter: function (value, row, index) {
                            var h = [];
                            if(value.indexOf('/storage/')>-1)
                                h.push("<a href='javascript:;' class='alarm-icon'><span class='iconfont'>&#xf0137;</span></a> ");
                            if(row.path != '')
                                h.push("<a href='javascript:;' class='alarm-icon'><span class='iconfont'>&#xf01cb;</span></a> ");
                            h.push("<a href='javascript:;' class='alarm-icon'><span class='iconfont'>&#xf0175;</span></a> ");
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
                    parent.TramDalog.OpenIframeAndNoBtn(652,538,"/alarm/view?id="+id);
                }
            });
            Alarm.initComboTree();
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {
                    Alarm.Search(value);
                }
            });
        },
        initComboTree:function () {
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
            parent.Http.Ajax({
                url:'/api/v1/basic/getalarmtypelist?parentid=0',
                type:'get'
            },function (result) {
                var data = result.result;
                $('#type1').combotree({
                    data:data,
                    onSelect:function (node) {
                        parent.Http.Ajax({
                            url:'/api/v1/basic/getalarmtypelist?parentid='+node.id,
                            type:'get'
                        },function (result) {
                            $('#type2').combotree({data:result.result});
                        });
                    }
                });
            });
            $('#type2').combotree({data:[{id:0,text:'请选择二级分类'}]});
        },
        datagridQuery:function () {
            return {
                departmentId:0,
                lineId:0,
                type1:0,
                type2:0,
                date1:'',
                date2:'',
                keywords:'',
                userId:parent.User.GetUserInfo().id,
            };
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        },
        Search:function (value) {
            var query = $('#table').datagrid('options').queryParams;
            var departmentId = $('#departmentId').combotree('getValue');
            var lineId = $('#lineId').combotree('getValue');
            var type1 = $('#type1').combotree('getValue');
            var type2 = $('#type2').combotree('getValue');
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            query.departmentId = departmentId==''?0:departmentId;
            query.lineId = lineId==''?0:lineId;
            query.type1 = type1==''?0:type1;
            query.type2 = type2==''?0:type2;
            query.date1 = date1;
            query.date2 = date2;
            query.keywords = value;
            Alarm.reLoad();
        }
    }
}();
Alarm.init();