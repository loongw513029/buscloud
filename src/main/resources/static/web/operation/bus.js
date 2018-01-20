var Device = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/operation/buslist',
                method:'get',
                idField:'id',
                fit:true,
                fitColumns:true,
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
                data:[{id:-1,text:'-监控软件类型-'},{id:1,text:'NVR'},{id:0,text:'DVR'}],
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
        AddLine:function () {
            parent.TramDalog.OpenIframe(650,405,'新增路线',"/operation/linefrom?id=0",function (index,layerno) {
                Department.saveData(layerno);
            });
        },
        EditLine:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length>1||row.length==0)
                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
            else{
                var id = row[0].id;
                parent.TramDalog.OpenIframe(650,405,'编辑路线',"/operation/linefrom?id="+id,function (index,layerno) {
                    Department.saveData(layerno);
                });
            }
        },
        RemoveLine:function () {

        },
        saveData:function () {

        },
        Search:function (value) {
            var query = $('#table').datagrid('options');
            var seldpid = $('#departmentId').combotree('getValue');
            if(seldpid!=0){
                query.queryParams.departmentId  = seldpid;
            }
            query.queryParams.linename = value;
            $('#table').datagrid('reload');
        }
    }
}();
Device.init();
