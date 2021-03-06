var currentIndex;
var Line = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/operation/linelist',
                method:'get',
                idField:'id',
                fit:true,
                fitColumns:true,
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:Line.datagridQuery(),
                toolbar:"#toolbar",
                columns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'linecode',
                        title: '线路编码',
                        width: 70
                    },{
                        field: 'linename',
                        title: '线路名称',
                        width: 120
                    },
                    {
                        field: 'departmentname',
                        title: '所属机构',
                        width: 120
                    },{
                        field: 'lineupmileage',
                        title: '上行里程',
                        width: 50
                    },{
                        field: 'linedownmileage',
                        title: '下行里程',
                        width: 50
                    },{
                        field: 'upsitenum',
                        title: '上线站点',
                        width: 50
                    },{
                        field: 'downsitenum',
                        title: '下行站点',
                        width: 50
                    },{
                        field: 'sort',
                        title: '排序',
                        width: 50
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
                prompt:'线路编码或线路名称',
                searcher:function (value,name) {
                    Line.Search(value);
                }
            })
        },
        datagridQuery:function () {
            var params = {
                userId:parent.User.GetUserInfo().id,
                linename:"",
                departmentId:0
            };
            return params;
        },
        AddLine:function () {
            parent.TramDalog.OpenIframe(650,405,'新增路线',"/operation/linefrom?id=0",function (layerno,index) {
                Line.saveData(layerno);
            });
        },
        EditLine:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length>1||row.length==0)
                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
            else{
                var id = row[0].id;
                parent.TramDalog.OpenIframe(650,405,'编辑路线',"/operation/linefrom?id="+id,function (layerno,index) {
                    Line.saveData(layerno);
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
                url:'/api/v1/operation/removeline?lineIds='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    Line.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.LineFrom.saveData(parent,Line);
        },
        Search:function (value) {
            var query = $('#table').datagrid('options');
            var seldpid = $('#departmentId').combotree('getValue');
            if(seldpid!=0){
                query.queryParams.departmentId  = seldpid;
            }
            query.queryParams.linename = value;
            Line.reLoad();
        },
        closeWindow:function () {
            parent.layer.close(currentIndex);
        },
        onLoad:function () {
            $('#table').datagrid('reload');
        }
    }
}();
Line.init();
