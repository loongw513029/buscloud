var AlarmStatics = function () {
    return {
        init:function () {
            AlarmStatics.initComboTree();
            var charttype = 'line';
            AlarmStatics.HighLoad(AlarmStatics.datagridQuery(),charttype);
            $('.easyui-linkbutton').each(function (i) {
                $(this).click(function () {
                    //$(this).addClass("l-btn-selected").siblings(".l-btn-selected").removeClass("l-btn-selected");
                    charttype = $(this).attr("id");
                    AlarmStatics.HighLoad(AlarmStatics.Search(),charttype);
                });
            });
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {
                    AlarmStatics.HighLoad(AlarmStatics.Search(value),charttype);
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
            $('#date1').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                    return date<=d2-1;
                }
            });
            $('#date2').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                    return date<=d2;
                }
            });
            $('#line').linkbutton({
                toggle: true,
                group: "charttype",
                selected:true
            });
            $('#area').linkbutton({
                toggle: true,
                group: "charttype"
            });
            $('#column').linkbutton({
                toggle: true,
                group: "charttype"
            });
            $('#bar').linkbutton({
                toggle: true,
                group: "charttype"
            });
        },
        datagridQuery:function () {
        return {
            departmentId:0,
            lineId:0,
            type1:0,
            type2:0,
            date1:'',
            date2:'',
            keywords:''
        };
        },
        HighLoad:function(value,value1){
            parent.Http.Ajax({
                url:'/api/v1/alarm/getalarmcharts1?lineId='+value.lineId+'&type1='+value.type1+'&type2='+value.type2+
                '&date2='+value.date1+'&date3='+value.date2+'&code='+value.code+'&departmentId='+value.departmentId,
                type:'get'
            },function (result) {
                var obj = result.result;
                var xalias = obj.xalias;
                var title = "";
                if(xalias.length==1)
                    title = xalias[0];
                else
                    title = xalias[0]+"-"+xalias[xalias.length-1];
                var data1=[];
                for(var i=0;i<obj.unsafeXalias.length;i++){
                    data1.push(new AlarmStatics.SeriesObj(obj.unsafeXalias[i],obj.unsafes[i]));
                }
                AlarmStatics.initHighCharts("#chart0",value1,"报警统计",title,xalias,"单位(次)",data1);
            });
        },
        initHighCharts:function (container,charts,title,subtitle,categories,ytitle,data) {
                $(container).highcharts({
                    chart:{
                        type:charts
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
        Load:function () {

        },
        SeriesObj:function (name,data) {
            this.name = name;
            this.data = data;
        },
        Search:function (value) {
            var query = {};
            var lineId = $('#lineId').combotree('getValue');
            var type1 = $('#type1').combotree('getValue');
            var type2 = $('#type2').combotree('getValue');
            var date1 = $('#date1').datebox('getValue');
            var date2 = $('#date2').datebox('getValue');
            var departmentId = $('#departmentId').combotree('getValue');
            query.departmentId = departmentId==''?0:departmentId;
            query.lineId = lineId==''?0:lineId;
            query.type1 = type1==''?0:type1;
            query.type2 = type2==''?0:type2;
            query.date1 = date1;
            query.date2 = date2;
            query.code = value;
            return query;
        }
    }
}();
AlarmStatics.init();