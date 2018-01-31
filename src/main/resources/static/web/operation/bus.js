var currentIndex;
var Device = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/operation/buslist',
                method:'get',
                idField:'id',
                fit:true,
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:Device.datagridQuery(),
                toolbar:"#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30,
                        align:'center'
                    },{
                        field: 'deviceCode',
                        title: '自编号',
                        width: 70,
                        align:'center'
                    },{
                        field: 'busNumber',
                        title: '车牌号',
                        width: 100,
                        align:'center'
                    },
                    {
                        field: 'departmentName',
                        title: '所属机构',
                        width: 100,
                        align:'center'
                    },{
                        field: 'lineName',
                        title: '所属线路',
                        width: 50,
                        align:'center'
                    },{
                        field:'driverName',
                        title:'司机名称',
                        width:70,
                        align:'center'
                    }
                ]],
                columns:[[
                    {
                        field:'clientIp',
                        title:'设备Ip',
                        width:100,
                        align:'center'
                    },{
                        field:'hostSoftType',
                        title:'监控软件类型',
                        width:100,
                        formatter:function (value,index,row) {
                            return value==0?"NVR":"DVR";
                        },
                        align:'center'
                    },{
                        field:'deviceMode',
                        title:'软件型号',
                        width:100,
                        align:'center'
                    },{
                        field:'videoSupport',
                        title:'视频',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        },
                        align:'center'
                    },{
                        field:'can',
                        title:'CAN',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        },
                        align:'center'
                    },{
                        field:'radar',
                        title:'雷达',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        },
                        align:'center'
                    },{
                        field:'aerialView',
                        title:'全景',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        },
                        align:'center'
                    },{
                        field:'supportBehavior',
                        title:'行为识别',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        }
                    },{
                        field:'supportAdas',
                        title:'ADAS',
                        width:70,
                        formatter:function(value,index,row){
                            return value?"支持":"不支持";
                        },
                        align:'center'
                    },
                    {
                        field:'deviceStatus',
                        title:'在线状态',
                        width:70,
                        formatter:function(value){
                            return value==1?"在线":"离线";
                        },
                        align:'center'
                    },{
                        field:"lastOnlineTime",
                        title:'最后在线时间',
                        width:200,
                        align:'center'
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
            $('#devicetype').combotree({
                data:[{id:-1,text:'-监控软件类型-'},{id:0,text:'NVR'},{id:1,text:'DVR'}],
                valueField:'id',
                textField:'text'
            });
            $('#devicetype').combotree('setValue',-1);
            $('#status').combotree({
                data:[{id:-100,text:'-设备状态-'},{id:1,text:'在线'},{id:-1,text:'离线'},{id:0,text:'五天不在线'}],
                valueField:'id',
                textField:'text'
            });
            $('#status').combotree('setValue',-100);
            $('#txtkey').searchbox({
                prompt:'线路编码或线路名称',
                searcher:function (value,name) {
                    Device.Search(value);
                }
            });
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineId').combotree({
                    prompt:'选择线路',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
        },
        datagridQuery:function () {
            var params = {
                userId:parent.User.GetUserInfo().id,
                lineId:0,
                departmentId:0,
                devicetype:-1,
                status:-100,
                keywords:''
            };
            return params;
        },
        AddBus:function () {
            parent.TramDalog.OpenIframe(1000,520,'新增车辆',"/operation/busfrom?id=0",function (layerno,index) {
                Device.saveData(layerno);
            });
        },
        EditBus:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length>1||row.length==0)
                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
            else{
                var id = row[0].id;
                parent.TramDalog.OpenIframe(1000,520,'编辑车辆',"/operation/busfrom?id="+id,function (layerno,index) {
                    Device.saveData(layerno);
                });
            }
        },
        RemoveBus:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length==0)
                parent.TramDalog.ErrorAlert("请选择数据！",true);
            var Ids = [];
            for(var i=0;i<row.length;i++){
                Ids.push(row[i].id);
            }
            parent.Http.Ajax({
                url:'/api/v1/operation/removeuser?deviceids='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    User.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.BusFrom.saveData(parent,Device);
        },
        Search:function (value) {
            var query = $('#table').datagrid('options');
            var departmentId = $('#departmentId').combotree('getValue');
            query.queryParams.departmentId  = departmentId==''?0:departmentId;
            var devicetype =$('#devicetype').combotree('getValue');
            query.queryParams.devicetype = devicetype;
            var lineId = $('#lineId').combotree('getValue');
            query.queryParams.lineId = lineId==''?0:lineId;
            var status = $('#status').combotree('getValue');
            query.queryParams.status = status;
            query.queryParams.keywords = value;
            Device.reLoad();
        },
        closeWindow:function () {
            parent.layer.close(currentIndex);
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        }
    }
}();
Device.init();
