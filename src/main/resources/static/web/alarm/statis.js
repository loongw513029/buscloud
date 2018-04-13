var AlarmStatics = function () {
    return {
        init:function () {
            AlarmStatics.initComboTree();
            parent.Http.Ajax({
                url:'/api/v1/alarm/getalarmcharts1?lineId='+AlarmStatics.datagridQuery().lineId+'&type1='+AlarmStatics.datagridQuery().type1+'&type2='+AlarmStatics.datagridQuery().type2+
                '&date2='+AlarmStatics.datagridQuery().date1+'&date3='+AlarmStatics.datagridQuery().date2+'&code='+AlarmStatics.datagridQuery().code+'&departmentId='+AlarmStatics.datagridQuery().departmentId+
                '&userid='+AlarmStatics.datagridQuery().userId,
                type:'get'
            },function (result) {
                var obj = result.result;
                var xalias = obj.xalias;
                var title = "";
                // if(xalias.length==1)
                //     title = xalias[0];
                // else
                //     title = xalias[0]+"-"+xalias[xalias.length-1];
                var data1=[];
                for(var j=0;j<obj.unsafeXalias.length;j++){
                    data1.push(new AlarmStatics.SeriesObj(obj.unsafeXalias[j],obj.unsafes[j]));
                }
                AlarmStatics.initHighCharts("#chart0","报警统计",title,xalias,"单位(次)",data1);
            })
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {
                    AlarmStatics.Load(AlarmStatics.Search(value));
                }
            });
        },
        initComboTree:function () {
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineId').combotree({
                    data:data
                });
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentId').combotree({
                    data:data
                });
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/getalarmtypelist?parentid=0',
                type:'get'
            },function (result) {
                var data = result.result;
                $('#type1').combotree({
                    data:data,
                    onSelect:function (node) {
                        parent.Http.Ajax({
                            url:'/api/v1/basic/getalarmtypelist?parentid='+node.id,
                            type:'get'
                        },function (result) {
                            $('#type2').combotree({data:result.result});
                        });
                    }
                });
            });
            $('#type2').combotree({data:[{id:0,text:'请选择二级分类'}]});
        },
        // ShowAlarm:function (id) {
        //     parent.TramDalog.OpenIframeAndNoBtn(652,538,'/alarm/view?id='+id);
        // },
        datagridQuery:function () {
        return {
            departmentId:0,
            lineId:0,
            type1:0,
            type2:0,
            date1:'',
            date2:'',
            keywords:'',
            userId:parent.User.GetUserInfo().id,
        };
},
        Load:function(value){

        },
        initHighCharts:function (container,title,subtitle,categories,ytitle,data,dataname) {
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
                    series: [{
                        name:dataname
                    },data]
                });
        },
        Load:function () {

        },
        SeriesObj:function (name,data) {
            this.name = name;
            this.data = data;
        },
        Search:function (value) {
            var lineId = $('#lineId').combotree('getValue');
            var type1 = $('#type1').combotree('getValue');
            var type2 = $('#type2').combotree('getValue');
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            query.departmentId = departmentId==''?0:departmentId;
            query.lineId = lineId==''?0:lineId;
            query.type1 = type1==''?0:type1;
            query.type2 = type2==''?0:type2;
            query.date1 = date1;
            query.date2 = date2;
            query.userId = parent.User.GetUserInfo().id;
            query.code = value;
            return query;
        }
    }
}();
AlarmStatics.init();