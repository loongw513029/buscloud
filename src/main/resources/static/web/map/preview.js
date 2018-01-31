var globalMap;//map对象
var interval;//定时器对象
var unterval2;
var markerArr = [];//标注集合
var startTime="";//地图下发时间
var devices="";//当前设备id集合
var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -6) });
Date.prototype.format = function (fromat) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}
var TMap = function () {
    return {
        init:function () {
            var clientHeight = document.body.clientHeight || window.innerHeight;
            $('#map').css('height',clientHeight+"px");
            globalMap = new AMap.Map('map',{
                resizeEnable: true,
                center: [113.325723,23.10328],
                zoom: 13
            });
            TMap.initMapAlarm();
        },
        //初始化报警列表
        initMapAlarm:function () {
            $('#table2').datagrid({
                url: '/api/v1/alarm/getmapalarmlist',
                method: 'get',
                idField: 'id',
                fit: true,
                fitColumns: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: {devices:'',starttime:''},
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
        initDeviceList:function () {
            $('#table1').datagrid({
                url: '/api/v1/operation/mapdevicelist',
                method: 'get',
                idField: 'id',
                fit: true,
                fitColumns: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: {devices:devices},
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
                        field: 'updatetime',
                        title: '更新时间',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'speed',
                        title: '车速',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'dertion',
                        title: '方向',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 'address',
                        title: '车距',
                        width: 200,
                        align: 'center'
                    }, {
                        field: 'dispatch',
                        title: '调度',
                        width: 50,
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
                }
            });
        },
        //刷新报警列表
        reLoadAlarmTable:function () {
            var query = $('#table').datagrid('options').queryParams;
            query.devices = devices;
            query.starttime = startTime;
            $('#table').datagrid('reload');
        },
        //Gps坐标转高德坐标
        ConvertGpsToAmapLocation:function (location) {
            var loc = "";
            var url = "http://restapi.amap.com/v3/assistant/coordinate/convert?locations=" + location + "&coordsys=gps&output=json&key=e30e5e9f5e8b3132a56321bd016aa1e3";
            $.ajaxSettings.async = false;
            $.getJSON(url, function (data) {
                loc = data.locations;
            });
            $.ajaxSettings.async = true;
            return loc;
        },
        //高德地图坐标转地址
        ConvertAmapToAddress:function (location) {
            var address = "";
            var url = "http://restapi.amap.com/v3/geocode/regeo?key=e30e5e9f5e8b3132a56321bd016aa1e3&location=" + location + "&poitype=&radius=1&extensions=base&batch=false&roadlevel=0";
            $.ajaxSettings.async = false;
            $.getJSON(url, function (data) {
                address = data.regeocode.formatted_address;
            });
            $.ajaxSettings.async = true;
            return address;
        },
        //创建地图窗口
        markerClick:function (e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
            it = 0;
            interval2 = null;
            interval2 = setInterval(function () {
                if (it > 4) {
                    clearInterval(interval2);
                    globalMap.clearInfoWindow();
                }
                it += 1;
            }, 1000);
        },
        //清空标注
        ClearMarker:function () {
            globalMap.clear();
        },
        //接受从父框架传下来的报警推送
        ReviceParentAlarm:function (obj) {
            var deviceIdArr = [];
            for(var i=0;i<obj.length;i++){
                deviceIdArr.push(obj[i].id);
            }
            devices = deviceIdArr.join(',');
            TMap.initDeviceList();
            //TMap.RequestData();
        },
        //开始请求数据
        RequestData:function () {
            startTime = new Date().format('yyyy-MM-dd h:m:s');
            parent.Http.Ajax({
                url:'/api/v1/map/realtime?devices='+devices+'&time='+startTime,
                type:'get'
            },function (result) {
                
            });
        }
    }
}();
TMap.init();