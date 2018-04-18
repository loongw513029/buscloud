var FaceSimilar = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/facesmailr/facelist',
                method: 'get',
                idField: 'id',
                fit: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: {name:''},
                toolbar: "#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    }, {
                        field: 'deviceCode',
                        title: '设备编号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'driverName',
                        title: '司机名称',
                        width: 100,
                        align: 'center'
                    },
                    {
                        field: 'fingerPrintPass',
                        title: '指纹验证',
                        width: 60,
                        align: 'center',
                        formatter:function (value,row,index) {
                            return value?"/images/zw1.png":"/images/zw.png";
                        }
                    }, {
                        field: 'drunkDrive',
                        title: '站点名称',
                        width: 60,
                        align: 'center',
                        formatter:function (value,row,index) {
                            return value?"/images/jg1.png":"/images/jg.png";
                        }
                    }, {
                        field: 'faceCompairison',
                        title: '地址',
                        width: 60,
                        align: 'center',
                        formatter:function (value,row,index) {
                            return value?"/images/fc1.png":"/images/fc.png";
                        }
                    }
                    , {
                        field: 'similar',
                        title: '相似度',
                        width: 100,
                        align: 'center'
                    },{
                        field: 'updateTime',
                        title: '数据时间',
                        width: 120,
                        align: 'center'
                    },{
                        field: 'driverPics',
                        title: '图片',
                        width: 100,
                        align: 'center',
                        formatter:function (value,row,index) {
                            var arr = value.split(',');
                            var d =[];
                            for(var i=0;i<arr.length;i++){
                                d.push('<img src="/images/pic.png" onclick="parent.TramDalog.OpenImage(\"'+arr[i]+'\")" />&nbsp;&nbsp;');
                            }
                            return d.join("");
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
            $('#txtkey').searchbox({
                prompt:'司机姓名',
                searcher:function (value,name) {
                    FaceSimilar.Search(value);
                }
            });
        },
        Search:function (val) {
            var query = $('#table').datagrid('options').queryParams;
            query.name = val;
            $('#table').datagrid('reload');
        }
    }
}();
FaceSimilar.init();