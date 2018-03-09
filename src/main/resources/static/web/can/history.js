var CanHistory = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineId').combotree({
                    prompt:'请选择线路',
                    data:data,
                    multiple:true,
                    checkbox:true,
                    onSelect:function (node) {
                        
                    }
                });
            });
            $('.tips a').click(function () {
                var index = $(this).index();
            })
            //CanHistory.initHighCharts("#")
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
        }
    }
}();
CanHistory.init();