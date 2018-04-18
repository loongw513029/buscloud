var Welcome = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url:'/api/v1/home/getWebTrends?userId='+parent.User.GetUserInfo().id,
                type:'get'
            },function(result){
                var obj = result.result;
                var xalias = obj.xalias;
                var title = "";
                if(xalias.length==1)
                    title = xalias[0];
                else
                    title = xalias[0]+"-"+xalias[xalias.length-1];
                var data1=[],data2=[];
                for(var i=0;i<obj.faultXalias.length;i++){
                    data1.push(new Welcome.SeriesObj(obj.faultXalias[i],obj.faults[i]));
                }
                for(var j=0;j<obj.unsafeXalias.length;j++){
                    data2.push(new Welcome.SeriesObj(obj.unsafeXalias[j],obj.unsafes[j]));
                }
                Welcome.initHighCharts("#chart0","CAN报警",title,xalias,"单位(次)",data1);
                Welcome.initHighCharts("#chart1","ADAS",title,xalias,"单位(次)",data2);
            })
        },
        ShowAlarm:function (id) {
            parent.TramDalog.OpenIframeAndNoBtn('报警详情',652,538,'/alarm/view?id='+id);
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
                lang: {
                    noData: "暂无数据"
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
                    '<td style="padding:0"><b>{point.y} 次 </b></td></tr>',
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
Welcome.init();
    