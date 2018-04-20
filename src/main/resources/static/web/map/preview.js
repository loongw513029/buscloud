var globalMap;//map对象
var interval;//定时器对象
var intervalTime = 0;//请求间隔
var currentSeconds = 0;//请求了多少秒
var totalMin = 0;//请求总时长
var unterval2;
var markerArr = [];//标注集合
var startTime="";//地图下发时间
var devices="";//当前设备id集合
var tableSource;
var runing =false;
var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -6) });
Date.prototype.pattern = function (fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
        "H+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    var week = {
        "0" : "/u65e5",
        "1" : "/u4e00",
        "2" : "/u4e8c",
        "3" : "/u4e09",
        "4" : "/u56db",
        "5" : "/u4e94",
        "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}
var TMap = function () {
    return {
        init:function () {
            var nodes = parent.mainPlatform.getCheckedNodes();
            var arr4 = [];
            nodes.forEach(function (node) {
                    arr4.push(node.id);
            });
            devices = arr4.join(',');
            var clientHeight = document.body.clientHeight || window.innerHeight;
            $('#map').css('height',clientHeight+"px");
            globalMap = new AMap.Map('map',{
                resizeEnable: true,
                center: [113.325723,23.10328],
                zoom: 13
            });
            $('.expand').click(function () {
               var cls = $(this).attr("class");
               if(cls.indexOf("up")>0) {
                   $(this).removeClass("up").addClass("down");
                   $('#datagrid-map').animate({bottom: "0px"});
               }
               else {
                   $(this).removeClass("down").addClass("up");
                   $('#datagrid-map').animate({bottom: "-200px"});
               }
            });
            $('#start').click(function () {
                var t = $('#start').text();
                if(t=='下发') {
                    $('#start').text('停止');
                    if (interval != null)
                        clearInterval(interval);
                    intervalTime = $('#intervalTime').val();
                    totalMin = $('#totelMin').val();
                    currentSeconds = totalMin * 60;
                    TMap.initInterval();
                }else{
                    TMap.EndMap();
                }
            });
            TMap.initMapAlarm();
        },
        initInterval:function () {
            runing = true;
            TMap.RequestData()
            interval = setInterval(function () {
                TMap.RequestData()
                currentSeconds--;
                TMap.CalcCountDownTime(currentSeconds);
                if(currentSeconds == 0)
                    TMap.EndMap()
            },intervalTime*1000);
        },
        CalcCountDownTime:function (seconds) {
            var t = seconds == 0 ? 0 :seconds;
            var mins = t <60?0:parseInt(t/60),sec = t % 60;
            $('.timebox').text((parseInt(mins)<10?"0"+mins:mins)+":"+(parseInt(sec)<10?"0"+sec:sec));
        },
        EndMap:function () {
            runing = false;
            $('#start').text('下发');
            $('#totelMin').prop('disabled',false);
            $('#intervalTime').prop('disabled',false);
            $('.timebox').text('00:00');
            TMap.ClearMarker();
            clearInterval(interval);
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
                        title: '地址',
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
                        tableSource = data.result;
                        return {
                            total: data.result.length,
                            rows: data.result
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
            for(var i=0; i<markerArr.length;i++){
                globalMap.remove(markerArr[i]);
            }
        },
        //接受从父框架传下来的报警推送
        ReviceParentAlarm:function (obj) {
            var deviceIdArr = [];
            for(var i=0;i<obj.length;i++){
                deviceIdArr.push(obj[i].id);
            }
            devices = deviceIdArr.join(',');
            TMap.initDeviceList();
        },
        //开始请求数据
        RequestData:function () {
            startTime = new Date().pattern('yyyy-MM-dd hh:mm:ss');
            parent.Http.Ajax({
                url:'/api/v1/map/realtime?devices='+devices+'&time='+startTime,
                type:'get'
            },function (data) {
                if(data.success) {
                    var result = data.result.maps;
                    for (var i = 0; i < result.length; i++) {
                        if(runing)
                            TMap.SetMap(result[i], result.length);
                    }
                }
            });
        },
        SetMap:function (obj,num) {
            TMap.ClearMarker();
            var loc = TMap.ConvertGpsToAmapLocation(obj.location);
            var longitude = loc.split(',')[0]
                ,latitude = loc.split(',')[1];
            var marker = new AMap.Marker({
                position:[longitude,latitude],
                autoRotation:true,
                angle: parseFloat(obj.rotate),
                icon: new AMap.Icon({
                    image:'/images/map/'+obj.iconclass+'.png',
                    size: new AMap.Size(32,32)
                }),
                offset:new AMap.Pixel(-16, -5)
            });
            markerArr.push(marker);
            marker.setMap(globalMap);
            marker.setLabel({
                offset: new AMap.Pixel(28,30),
                content: (obj.state==1||obj.state==3) ? obj.code+(obj.dispatch == null ? "" : "<br/>"+obj.dispatch):obj.code
            });
            marker.setTitle(obj.code);
            for(var i=0;i<tableSource.length;i++){
                if(tableSource[i].devicecode == obj.code){
                    tableSource[i].updatetime = obj.updateTime;
                    tableSource[i].speed = obj.speed;
                    tableSource[i].dertion = TMap.SunDirect(obj.rotate);
                    tableSource[i].address = TMap.ConvertAmapToAddress(loc);
                    tableSource[i].dispatch = obj.dispatch;
                }
            }
            var d2 = {
                "total": tableSource.length,
                "rows": tableSource
            };
            $("#table1").datagrid("loadData",eval(d2));
            if(num==1)
                globalMap.setCenter([longitude,latitude]);
            else
                globalMap.setFitView();
        },
        SunDirect:function (angle) {
            angle = parseInt(angle);
            if (angle == 0)
                return "正北方";
            else if (angle > 0 && angle < 45)
                return "北偏东";
            else if (angle == 45)
                return "东北方";
            else if (angle > 45 && angle < 90)
                return "东偏北";
            else if (angle == 90)
                return "正东方";
            else if (angle > 90 && angle < 135)
                return "东偏南";
            else if (angle == 135)
                return "东南方";
            else if (angle > 135 && angle < 180)
                return "南偏东";
            else if (angle == 180)
                return "正南方";
            else if (angle > 180 && angle < 225)
                return "南偏西";
            else if (angle == 225)
                return "西南方";
            else if (angle > 225 && angle < 270)
                return "西偏南";
            else if (angle == 270)
                return "正西方";
            else if (angle > 270 && angle < 315)
                return "西偏北";
            else if (angle == 315)
                return "西北方";
            else if (angle > 315 && angle < 360)
                return "北偏西";
        }
        
    }
}();
TMap.init();