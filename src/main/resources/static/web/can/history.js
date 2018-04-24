var SelIndex=1;
var SelLineId=0;
var SelBusCode="";
var CanHistory = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                for(var i=0;i<data.length;i++){
                    if(data[i].id!=0){
                        SelLineId = data[i].id;
                        break;
                    }
                }
                $('#lineId').combotree({
                    prompt:'请选择线路',
                    data:data,
                    multiple:true,
                    checkbox:true,
                    onSelect:function (node) {
                        lineId = $('#lineId').combotree('getValues');
                        SelLineId = node.id;
                        CanHistory.getCharts();
                    },
                    onChange: function (lineId) {
                        parent.Http.Ajax({
                            url:'/api/v1/basic/getCanHistoryCode?LineIds='+lineId,
                            type:'get'
                        },function (result) {
                            $('#deviceCode').combotree({
                                data:result.result,
                                onSelect:function (node){
                                    SelBusCode = node.text;
                                    CanHistory.getCharts();
                                }
                            });
                        });
                    }
                });
                $('#lineId').combotree('setValue',SelLineId);
            });
            $('#tips a').click(function () {
                var index = $(this).index();
                SelIndex= index+1;
                $('#tips a:eq('+index+')').addClass("active").siblings().removeClass("active");
                CanHistory.getCharts();
            })
            CanHistory.getCharts();
        },
        getCharts:function () {
            parent.TramDalog.Loading2();
            if(SelBusCode=="")
                var url = '/api/v1/can/getcanhistory?lineId=' + SelLineId + '&dayType=' + SelIndex;
            else
                var url = '/api/v1/can/getcanhistorybus?code=' + SelBusCode + '&dayType=' + SelIndex;
                parent.Http.Ajax({
                        type: 'get',
                        url: url,
                    }, function (result) {
                        parent.TramDalog.CloseLoading();
                        var obj = result.result;
                        $(".top_header li:eq(0)").find("span").text(obj.totalNumber + "辆");
                        $(".top_header li:eq(1)").find("span").text(obj.operaterNumber + "辆");
                        $(".top_header li:eq(2)").find("span").text(obj.totalMileage + "KM");
                        $(".top_header li:eq(3)").find("span").text(obj.fuelEconomy + "L");
                        $(".top_header li:eq(4)").find("span").text(obj.elecEconomy + "KW/H");
                        $(".top_header li:eq(5)").find("span").text(obj.gasEconomy + "L");
                        $(".top_header li:eq(6)").find("span").text(obj.faultNumber + "次");
                        $(".top_header li:eq(7)").find("span").text(obj.faultBus + "次");
                        var xalias = obj.xlias;
                        var title = "";
                        if (xalias.length == 1)
                            title = xalias[0];
                        else
                            title = xalias[0] + "-" + xalias[xalias.length - 1];
                        var data1 = [], data2 = [];
                        for (var i = 0; i < obj.faultXalias.length; i++) {
                            data1.push(new CanHistory.SeriesObj(obj.faultXalias[i], obj.faults[i]));
                        }
                        for (var j = 0; j < obj.unsafeXalias.length; j++) {
                            data2.push(new CanHistory.SeriesObj(obj.unsafeXalias[j], obj.unsafes[j]));
                        }
                        CanHistory.initHighCharts("#container1", "CAN报警", title, xalias, "单位(次)", data1)
                        CanHistory.initHighCharts("#container2", "不安全报警", title, xalias, "单位(次)", data2)
                    })
        },
        initHighCharts:function (container,title,subtitle,categories,ytitle,data) {
            $(container).highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: title
                },
                subtitle: {
                    text: subtitle
                },
                credits:{
                    text:'',
                    href:''
                },
                xAxis: {
                    categories: categories,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ytitle
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        borderWidth: 0
                    }
                },
                series: data
            });
        },
        SeriesObj:function (name,data) {
            this.name = name;
            this.data = data;
        }
    }
}();
CanHistory.init();