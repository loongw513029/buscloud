var ElecFence = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/operation/elecfencelist',
                method: 'get',
                idField: 'id',
                fit: true,
                fitColumns: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: {},
                toolbar: "#toolbar",
                columns: [[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'lng',
                        title: '纬度',
                        width: 100
                    }, {
                        field: 'lat',
                        title: '经度',
                        width: 100
                    },
                    {
                        field: 'radius',
                        title: '围栏半径',
                        width: 100
                    }, {
                        field: 'inTrun',
                        title: '进围栏报警',
                        width: 50,
                        formatter: function (value, row, index) {
                            return value ? "是" : "否";
                        }
                    }, {
                        field: 'outTrun',
                        title: '出围栏报警',
                        width: 50,
                        formatter: function (value, row, index) {
                            return value ? "是" : "否";
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
                }
            });
        },
        Add:function () {
            parent.TramDalog.OpenIframe(650,455,'新增电子围栏',"/operation/elecfrom?id=0",function (layerno,index) {
                ElecFence.saveData(layerno);
            });
        },
        Edit:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length>1||row.length==0)
                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
            else{
                var id = row[0].id;
                parent.TramDalog.OpenIframe(650,455,'编辑电子围栏',"/operation/elecfrom?id="+id,function (layerno,index) {
                    ElecFence.saveData(layerno);
                });
            }
        },
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.ElecFrom.saveData(parent,ElecFence);
        },
        Remove:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length==0)
                parent.TramDalog.ErrorAlert("请选择数据！",true);
            var Ids = [];
            for(var i=0;i<row.length;i++){
                Ids.push(row[i].id);
            }
            parent.Http.Ajax({
                url:'/api/v1/operation/removeelec?lineIds='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    ElecFrom.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        closeWindow:function () {
            parent.layer.close(currentIndex);
        },
        onLoad:function () {
            $('#table').datagrid('reload');
        }
    }
}();
ElecFence.init();
