var SelDeviceId=0;
var Poliys=[];
var marker;
var map;//map对象
var passedPolyline;
var MapHistory = function () {
    return {
        init:function () {
            var treedata = parent.treeData;
            $('#easyui-tree').tree({
                lines: true,
                animate: true,
                data: treedata,
                onCheck: function (node, checked) {
                    SelDeviceId = node.id;
                    if(!checked){
                        SelDeviceId = 0;
                    }
                },
                onLoadSuccess:function (node,param) {
                    $("#easyui-tree").tree('collapseAll');
                },
                onBeforeCheck:function (node,checked) {
                    $('#easyui-tree').tree('getChecked', 'unchecked');
                }
            });

            map = new AMap.Map('map',{
                resizeEnable: true,
                center: [113.325723,23.10328],
                zoom: 13
            });
            passedPolyline= new AMap.Polyline({
                map:map,
                strokeColor:'#F00',
                strokeWeight: 3
            });
            $('#sh').click(function () {
                if(SelDeviceId == 0){
                    parent.TramDalog.ErrorAlert('未选择设备',true);
                    return;
                }
                var startTime = $('#time1').datebox('getValue');
                var endTime = $('#time2').datebox('getValue');
                if(startTime==''||endTime==''){
                    parent.TramDalog.ErrorAlert('请选择时间',true);
                    return;
                }
                parent.TramDalog.Loading2();
                MapHistory.initTable();
                Poliys = [];
            });
        },
        AcceptParentData:function (obj) {
            SelDeviceId = obj.id;

        },
        datagridQuery:function () {
            var startTime = $('#time1').datebox('getValue');
            var endTime = $('#time2').datebox('getValue');
            return {
                deviceId:SelDeviceId,
                startTime:startTime,
                endTime:endTime
            };
        },
        initTable:function () {
            $('#table').datagrid({
                url: '/api/v1/map/getWebMapHistory',
                method: 'get',
                idField: 'id',
                fit:true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: MapHistory.datagridQuery(),
                toolbar: "#toolbar",
                columns: [[
                    {
                        field: 'updateTime',
                        width: 80,
                        title: '时间',
                        align: 'center'
                    }, {
                        field: 'speed',
                        title: '速度',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 's',
                        title: '坐标',
                        width: 80,
                        align: 'center',
                        formatter:function (value,row,index) {
                            return row.longitude+","+row.latitude;
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
                onLoadSuccess:function (data) {
                },
                onDblClickRow:function (rowIndex, rowData) {
                    
                }
            });
            parent.TramDalog.CloseLayer();
            MapHistory.DrawPath();
            var p = $('#table').datagrid('getPager');
            $(p).pagination({
                showPageList: false,
                showRefresh: false,
                displayMsg: '',
                layout:['prev','next']
            });
        },
        //绘制地图轨迹
        DrawPath:function () {
            parent.Http.Ajax({
                url:'/api/v1/map/getLocations',
                data:MapHistory.datagridQuery(),
                type:'get'
            },function (data) {
                var arrs= data.result;
                for(var i=0;i<arrs.length;i++){
                    var obj = arrs[i].split(',');
                    var location = {lon:obj[0],lat:obj[1]};// MapHistory.ConvertGpsToAmap(obj[0]+","+obj[1]);

                    Poliys.push([location.lon,location.lat]);
                    if(i==0){
                        marker = new AMap.Marker({
                            map: map,
                            position: [location.lon,location.lat],
                            icon: new AMap.Icon({
                                image: 'http://webapi.amap.com/images/car.png',
                                size: new AMap.Size(16,16)
                            }),
                            offset: new AMap.Pixel(-26,-13),
                            autoRotation: true
                        });
                        marker.on('moving',function (e) {
                            passedPolyline.setPath(e.passedPath);
                        })
                    }
                }
                new AMap.Polyline({
                    map: map,
                    path: Poliys,
                    strokeColor: '#0c81bf',
                    strokeWeight: 3
                });
            })
        },
        ConvertGpsToAmap:function (location) {
            var loc ="";
            var url="http://restapi.amap.com/v3/assistant/coordinate/convert?locations="+location+"&coordsys=gps&output=json&key=e30e5e9f5e8b3132a56321bd016aa1e3";
            $.ajaxSettings.async = false;
            $.getJSON(url,function (data) {
                loc = data.locations;
            });
            $.ajaxSettings.async = true;
            return loc;
        }
    }
}();
MapHistory.init();