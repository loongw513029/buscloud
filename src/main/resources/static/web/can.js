var deviceId = 0;
var map;
var interval;
var tinterval;
var marker;
var markers = [];
var busType=0;
var intervalTime = 0;//请求间隔
var currentSeconds = 0;//请求了多少秒
var totalMin = 0;//请求总时长
var serverIp,hosttype,code,clientIp,channel;
var Can=function () {
    return{
        init:function () {
            var node= parent.mainPlatform.getCheckedNodes();
            if(node.length == 1){
                deviceId = node[0].id;
            }
            totalMin = $('#totelMin').val();
            intervalTime = $('#intervalTime').val();
            Can.initHighCharts();
            map = Can.initMap();
            Can.changeWidthSize();
            $(window).resize(function(){
                Can.changeWidthSize();
            });
            $('#start').click(function () {
                totalMin = $('#totelMin').val();
                intervalTime = $('#intervalTime').val();
                currentSeconds = totalMin * 60;
                if($(this).text()=="下发") {
                    parent.TramDalog.Loading();
                    //倒计时
                    tinterval = setInterval(function () {
                        if(totalMin * 60 == currentSeconds) {
                            Can.RequestData();
                            Can.SetOcx();
                        }
                        currentSeconds--;
                        Can.CalcCountDownTime(currentSeconds);
                        if(currentSeconds == 0)
                            Can.EndCan()
                    },1000);
                    currentSeconds = totalMin*60;
                    $(this).text('停止');
                    $('#totelMin').prop('disabled',true);
                    $('#intervalTime').prop('disabled',true);
                    interval = setInterval(function () {
                        if(currentSeconds <= 0)
                            Can.EndCan();
                        else
                            Can.RequestData();
                    },intervalTime*1000);
                }
                else {
                    Can.EndCan();
                }
            });
        },
        changeWidthSize:function () {
            var contentWidth = $('.meter-wrap').width();
            $('.ui-resizable:eq(1)').css('left',(contentWidth-306)/2+'px');
        },
        /**
         * 从左侧设备列表树形菜单传输过来的设备id
         * @param id
         * @constructor
         */
        TransferData:function (id) {
            //先停止正在的请求
            //Can.EndCan();
            parent.Http.Ajax({
                url:'/api/v1/device/info?deviceId='+id,
                type:'get'
            },function(data){
                var obj = data.result;
                deviceId = obj.id;
                busType=obj.bustype;
                code = obj.deviceCode;
                serverIp = location.hostname;
                hosttype = obj.hostSoftType == 0?"NVR":"TongLi";
                clientIp = obj.clientIp;
                channel = obj.dChannel;
                $('#start').show();
                $('#line-info').text('线路:'+obj.lineName+' 自编号:'+obj.deviceCode);
                if(obj.bustype == 5){
                    $('#mixin-energe').hide();
                    $('#bms').show();
                }
                else{
                    $('#mixin-energe').show();
                    $('#bms').hide();
                }
                if(obj.devicestatus == -1){
                    parent.TramDalog.ErrorAlert('设备不在线!',false);
                    $('#start').hide();
                }
            },function(){

            })
            //TramDalog.InfoAlert(id,true);
        },
        /**
         * 初始化地图
         * @returns {AMap.Map}
         */
        initMap:function () {
            var map = new AMap.Map('map',{
                resizeEnable: true,
                center: [113.325723,23.10328],
                zoom: 13
            });
            return map;
        },
        getGaugeOptions:function (op) {
          return {
              chart:{
                  type:'solidgauge',
                  plotBackgroundColor:null,
                  plotBackgroundImage:null,
                  plotBorderWidth:0,
                  plotShadow: false
              },
              exporting:{
                  enabled: false
              },
              colorByPoint: false,
              credits:{
                  enabled: false
              },
              title: null,
              tooltip:{
                  enabled: false
              },
              plotOptions:{
                  solidgauge:{
                      dataLabels:{
                          y: 5,
                          borderWidth: 0,
                          useHTML: true
                      }
                  }
              },
              pane:{
                  center: op.center,
                  size: op.size,
                  startAngle: op.startAngle,
                  endAngle: op.endAngle,
                  background:{
                      backgroundColor: null,
                      innerRadius: '98%',
                      outerRadiud: '100%',
                      shape: 'arc'
                  }
              },
              yAxis:{
                  min: op.min,
                  max: op.max,
                  stops: op.stops,
                  lineWidth: 0,
                  minorTickInterval: null,
                  tickPixelInterval: 400,
                  tickWidth: 0,
                  title:{
                      text: ''
                  },
                  labels:{
                      enabled: false
                  }
              },
              series:[{
                  data: [0],
                  innerRadius: 98,
                  dataLabels: {
                      enabled: false
                  }
              }]
          };
        },
        /**
         * 初始化页面图表
         */
        initHighCharts:function () {
            ///初始化传统车辆图表
            $('#container-speed-3').highcharts(Can.getGaugeOptions({
                min: 0,
                max: 180,
                center: ['50%','65%'],
                size: '125%',
                startAngle: -120,
                endAngle: 120,
                stops: [[0.2, '#2dbbef'],
                    [0.4, '#34E259'],
                    [0.6, '#FCD821'],
                    [0.9, '#FB3D3D']
                ]
            }));
            $('#container-rpm-3').highcharts(Can.getGaugeOptions({
                min: 0,
                max: 120,
                center: ['50%', '65%'],
                size: '125%',
                startAngle: -120,
                endAngle: 120,
                stops: [[0.2, '#2dbbef'],
                    [0.4, '#34E259'],
                    [0.6, '#FCD821'],
                    [0.9, '#FB3D3D']
                ]
            }));
            $('#battery-voltage-chart-3').highcharts(Can.getGaugeOptions({
                min: 0,
                max: 800,
                center: ['50%', '50%'],
                size: '100%',
                startAngle: -50,
                endAngle: 50,
                stops: [[400, '#55BF3B']]
            }));
            $('#battery-circuit-chart-3').highcharts(Can.getGaugeOptions({
                min: -600,
                max: 600,
                center: ['50%', '50%'],
                size: '100%',
                startAngle: -50,
                endAngle: 50,
                stops: [[400, '#55BF3B']]
            }));
            $('#frontAirPressureChart-3').highcharts(Can.getGaugeOptions({
                min: 0,
                max: 1,
                center: ['50%', '50%'],
                size: '100%',
                startAngle: -50,
                endAngle: 50,
                stops: [
                    [0.1, '#34E259'],
                    [0.4, '#FCD821'],
                    [0.7, '#55BF3B'],
                    [0.9, '#55BF3B']
                ]
            }));
            $('#backAirPressureChart-3').highcharts(Can.getGaugeOptions({
                min: 0,
                max: 1,
                center: ['50%', '50%'],
                size: '100%',
                startAngle: -50,
                endAngle: 50,
                stops: [
                    [0.1, '#34E259'],
                    [0.4, '#FCD821'],
                    [0.7, '#55BF3B'],
                    [0.9, '#55BF3B']
                ]
            }));
            ///BMS图表
            var $bmsContainer = $('#bms');
            // 车速
            $('.speed-meter', $bmsContainer).highcharts(Can.getGaugeOptions({
                min: 0,
                max: 180,
                center: ['50%', '65%'],
                size: '125%',
                startAngle: -120,
                endAngle: 120,
                stops: [[0.2, '#2dbbef'],
                    [0.4, '#34E259'],
                    [0.6, '#FCD821'],
                    [0.9, '#FB3D3D']
                ]
            }));
            // 转速
            $('.rpm-meter', $bmsContainer).highcharts(Can.getGaugeOptions({
                min: 0,
                max: 40,
                center: ['50%', '65%'],
                size: '125%',
                startAngle: -120,
                endAngle: 120,
                stops: [[0.2, '#2dbbef'],
                    [0.4, '#34E259'],
                    [0.6, '#FCD821'],
                    [0.9, '#FB3D3D']
                ]
            }));
            //动力电池电压、电流
            $('.battery-voltage-meter1,.battery-voltage-meter2', $bmsContainer).each(function () {
                $(this).highcharts(Can.getGaugeOptions({
                    min: 0,
                    max: 800,
                    center: ['50%', '50%'],
                    size: '100%',
                    startAngle: -50,
                    endAngle: 50,
                    stops: [[400, '#55BF3B']]
                }));
            });
            $('.battery-circuit-meter1,.battery-circuit-meter2', $bmsContainer).each(function () {
                $(this).highcharts(Can.getGaugeOptions({
                    min: -1000,
                    max: 1000,
                    center: ['50%', '50%'],
                    size: '100%',
                    startAngle: -50,
                    endAngle: 50,
                    stops: [[0, '#55BF3B']]
                }));
            });
        },
        /**
         * 设置指示灯样式
         * @param selector {string|Query} 指示灯选择器
         * @param val {string|number} 指示灯具体值 2：打开 1：关闭 0,3 无效
         */
        setLightStyle:function (selector,val) {
            if(typeof selector == 'string'){
                selector = $(selector);
            }
            if(val == 1){
                selector.removeClass('active');
            }else if(val == 2){
                selector.addClass('active');
            }
        },
        /**
         * 设置车速
         * @param value
         * @constructor
         */
        SetSpeed:function (value) {
            var chart = $('#container-speed-3').highcharts(), point = chart.series[0].points[0];
            point.update(parseInt(value));
            $('#speed-text-3').empty().append(parseInt(value) + '<span> <i>/</i> 限' + 55 + '</span>');
        },
        /**
         * 设置转速
         * @param value
         * @constructor
         */
        SetRpm:function (value) {
            var chart = $('#container-rpm-3').highcharts(), point = chart.series[0].points[0];
            var rote = parseFloat(value) / 1000;
            //console.log(rote)
            point.update(rote);
            $('#rpm-text-3').text(parseInt(value));
        },
        /**
         * 动力电池电压
         * @param value
         * @constructor
         */
        SetBatteryVoltage:function (value) {
            var bvcChart = $('#battery-voltage-chart-3').highcharts(),
                bvcPoint = bvcChart.series[0].points[0];
            var bvcVal = parseFloat(value);
            if (bvcVal < 0) {
                bvcVal = 0;
            } else if (bvcVal > 800) {
                bvcVal = 800;
            }
            bvcPoint.update(parseInt(bvcVal));
            $('#bvc-text-3').text(bvcVal);
        },
        /**
         * 动力电池电流
         * @constructor
         */
        SetBatteryCircuit:function (value) {
            var bccChart = $('#battery-circuit-chart-3').highcharts(),
                bccPoint = bccChart.series[0].points[0];
            var bccVal = Number((value || 0).toFixed(1));
            bccPoint.update(bccVal);
            $('#bcc-text-3').text(bccVal);
        },
        /**
         * 设置前气压
         * @constructor
         */
        SetFrontAirPressure:function (value) {
            var frontAirChart = $('#frontAirPressureChart-3').highcharts(),
                frontAirPoint = frontAirChart.series[0].points[0];
            var press1 = parseFloat(value) / 1000;
            frontAirPoint.update(press1);
            $('#frontAir-p-3').text(press1.toFixed(2)*100);
        },
        /**
         * 设置后气压
         * @constructor
         */
        SetBackAirPressure:function (value) {
            var backAirChart = $('#backAirPressureChart-3').highcharts(),
                backAirPoint = backAirChart.series[0].points[0];
            var press = parseFloat(value) / 1000;
            backAirPoint.update(press);
            $('#backAir-p-3').text(press.toFixed(2)*100);
        },
        /**
         * 电池电压图表
         * @constructor
         */
        SetBatteryVoltage3:function (value) {
            $('#battery-voltage-text-3').empty().append(value + '<span>v</span>');
            $('#battery-voltage-3').animate({ height: (value * 100 / 30) + '%' }, 800, 'easeOutBounce');
        },
        /**
         * 设置水温
         * @constructor
         */
        SetWaterTemp:function (value) {
            var $waterTemp = $('#water-temp-3'),
                waterTemp = value || 0;
            $('.num-area', $waterTemp).animate({ height: waterTemp > 100 ? '100' : waterTemp + '%' }, 800, 'easeOutBounce');
            $('p', $waterTemp).empty().append(waterTemp + '<span>℃</span>');
        },
        /**
         * 机油压力
         * @constructor
         */
        SetEngineOilPressure:function (value) {
            var press = parseFloat(value) / 1000;
            var $engineOilPressure = $('#engineOilPressure-3');
            $('.num-area', $engineOilPressure).animate({ height: (press * 100) + '%' }, 800, 'easeOutBounce');
            $('p', $engineOilPressure).empty().append(press + '<span>mpa</span>');
        },
        /**
         * SOC
         * @constructor
         */
        SetSOC:function (value) {
            var soc = Number(value || 0).toFixed(1);
            if (soc < 0) {
                soc = 0;
            }
            if (soc > 100) {
                soc = 100;
            }
            $('#soc-text-3').empty().append(soc + '<span>%</span>');
            $('#soc-3').animate({ height: soc + '%' }, 800, 'easeOutBounce');
        },
        /**
         * 设置Gps信号
         * @param selector
         * @param val
         * @param charAtIndex
         */
        setGpsSignalStyle:function (selector,val,charAtIndex) {
            if(typeof  selector == 'string'){
                selector = $(selector);
            }
            if(!val || (!charAtIndex || val.charAt(val.length - charAtIndex) % 2 ===0 )){
                selector.removeClass('online').attr('title','GPS离线');
            }else{
                selector.addClass('online').attr('title','GPS在线');
            }
        },
        /**
         * 设置油量
         * @param value
         * @constructor
         */
        SetoilMass:function (value) {
            var $oilNum = $('#oil-num'),
                oilNum = value || 0;
            $oilNum.empty().append(oilNum + '<i>%</i>');
            $oilNum.parent().siblings('.num-area').animate({ height: oilNum + '%' }, 800, 'easeOutBounce');
            $('#mixin-oil').find('.p1').text((value || 0) + '%').end().find('.num-area').css({ width: (value || 0) + '%' });
        },
        /**
         * 制动踏板
         * @param value
         * @constructor
         */
        SetBrakePedal:function (value) {
            $('#brake-pedal').find('.p1').text((value || 0) + '%').end().find('.num-area').css({ width: (value || 0) + '%' });
        },
        /**
         * 牵引踏板
         * @param value
         * @constructor
         */
        SetPullPedal:function (value) {
            $('#pull-pedal').find('.p1').text((value || 0) + '%').end().find('.num-area').css({ width: (value || 0) + '%' });
        },
        /**
         * 双路BMS牵引踏板
         * @param value
         * @constructor
         */
        SetPullPedal2:function (value) {
            $('#pull-pedal2').find('.p1').text((value || 0) + '%').end().find('.num-area').css({ width: (value || 0) + '%' });
        },
        /**
         * 电机驱动模式
         * @param val
         * @returns {string}
         * @constructor
         */
        SetWorkMode:function (val) {
            var retr = "";
            switch (val) {
                case 1:
                    retr = "驱动模式"; break;
                case 2:
                    retr = "回馈模式"; break;
                case 3:
                    retr = "关闭"; break;
                case 4:
                    retr = "准备"; break;
            }
            return retr;
        },
        /**
         * 加载BMS车辆数据
         * @param data
         */
        loadBMSDataView:function (data) {
            var $bmsContainer = $('#bms');
            $('.uptime').text(data.time);
            var act=data.canstatinfo;
            //右转向灯
            Can.setLightStyle('#bms .icon-flag9', act.rightturn);
            //左转向
            Can.setLightStyle('#bms .icon-flag1', act.leftturn);
            // 后雾灯
            Can.setLightStyle('#bms .icon-flag3', act.afterfoglampsturn);
            // 前雾
            Can.setLightStyle('#bms .icon-flag2', act.beforefoglampsturn);
            //近光灯
            Can.setLightStyle('#bms .icon-flag4', act.dippedlightsturn);
            //远光灯
            Can.setLightStyle('#bms .icon-flag5', act.highbeamturn);
            //ABS
            Can.setLightStyle('#bms .icon-flag6', act.absturn);
            //驻车制动器
            Can.setLightStyle('#bms .icon-flag7', act.parkingbraketurn);
            //脚刹指示灯
            Can.setLightStyle('#bms .icon-flag10', act.footbraketurn);
            //电池统电指示灯
            Can.setLightStyle('#bms .icon-flag15', 1);
            //安全带指示灯
            Can.setLightStyle('#icon-flag11', act.safetybelt);
            //后仓门开信号
            Can.setLightStyle('#bms .icon-flag16', act.lastdoorturn);
            //位置灯指示
            Can.setLightStyle('#bms .icon-flag17', act.positionlampturn);
            //前车门
            Can.setLightStyle('#bms .icon-flag18',act.headerdoorturn);
            //中门
            Can.setLightStyle('#bms .icon-flag19', act.middledoorturn);
            //空调状态
            Can.setLightStyle('#bms .icon-flag23',act.acturn);
            var obj = data.caninfo;

            //车速
            var chart = $('.speed-meter', $bmsContainer).highcharts(),
                point = chart.series[0].points[0];

            var speedVal = obj.speed || 0;
            point.update(speedVal);
            $('.speed-text', $bmsContainer).empty().append(speedVal + '<span> <i>/</i> 限55</span>');

            //转速
            var rpmChart = $('.rpm-meter', $bmsContainer).highcharts(),
                rmpPoint = rpmChart.series[0].points[0];
            var rpmVal = (obj.ratespeed || 0) / 100;
            rmpPoint.update(rpmVal);
            $('.rpm-text', $bmsContainer).html("<span>左</span>" + (obj.elec.LeftElecRote || 0) + "/" + (obj.elec.RightElecRote || 0) + "<span>右</span>")
            Can.SetBMSGear(obj.bustall);
            //小计里程
            var subTotalMileage = Number((data.subtotalMile || 0).toFixed(2));
            $('#subTotalMileage-4').empty().append(subTotalMileage + ' <i>km</i>');
            //总里程
            $('#totalMileage-4').empty().append(obj.totalmileage + ' <i>km</i>');
            //前气压
            var frontAirPressure = parseFloat(((obj.beforepressure || 0)).toFixed(2));
            $('.front-gasbag .num-area', $bmsContainer).css({ height: frontAirPressure * 100 + '%' });
            $('.front-gasbag>p:first', $bmsContainer).text(frontAirPressure + 'MPA');
            //后气压
            var backAirPressure = parseFloat(((obj.afterpressure || 0)).toFixed(2));
            $('.black-gasbag .num-area', $bmsContainer).css({ height: backAirPressure * 100 + '%' });
            $('.black-gasbag>p:first', $bmsContainer).text(backAirPressure + 'MPA');
            //动力电池电压1、2
            var bvcVal = Number((obj.elec.LeftElecInputVoltage || 0));
            if (bvcVal < 0) {
                bvcVal = 0;
            } else if (bvcVal > 800) {
                bvcVal = 800;
            }
            if(bvcVal!=0){
                $('.dldcdy1', $bmsContainer).text(bvcVal);
                Can.updateMeterValue($('.battery-voltage-meter1', $bmsContainer), bvcVal);
            }
            var bvcVal2 = Number((obj.elec.RightElecInputVoltage || 0));
            if (bvcVal2 < 0) {
                bvcVal2 = 0;
            } else if (bvcVal2 > 800) {
                bvcVal2 = 800;
            }
            if (bvcVal2 != 0) {
                $('.dldcdy2', $bmsContainer).text(bvcVal2);
                Can.updateMeterValue($('.battery-voltage-meter2', $bmsContainer), bvcVal2);
            }
            //动力电池电流1、2
            var bccVal = Number((obj.elec.LeftElecCurrent || 0));
            if (bccVal > 1000)
                bccVal = 1000;
            if(bccVal!=0){
                Can.updateMeterValue($('.battery-circuit-meter1', $bmsContainer), bccVal);
                $('.dldcdl1', $bmsContainer).text(bccVal);
            }
            var bccVal2 = Number((obj.elec.RightElecCurrent || 0));
            if (bccVal2 > 1000)
                bccVal2 = 1000;
            if (bccVal2 != 0) {
                Can.updateMeterValue($('.battery-circuit-meter2', $bmsContainer), bccVal2);
                $('.dldcdl2', $bmsContainer).text(bccVal2);
            }
            //soc1、2
            var soc = Number(obj.soc || 0).toFixed(1);
            if (soc < 0) {
                soc = 0;
            }
            if (soc > 100) {
                soc = 100;
            }
            var $soc1 = $('.soc1', $bmsContainer);
            $('.num-area', $soc1).css({ height: soc + '%' });
            $('>p', $soc1).html(soc + '<span>%</span>');

            var soc2 = Number(obj.soc || 0).toFixed(1);
            if (soc2 < 0) {
                soc2 = 0;
            }
            if (soc2 > 100) {
                soc2 = 100;
            }
            var $soc2 = $('.soc2', $bmsContainer);
            $('.num-area', $soc2).css({ height: soc2 + '%' });
            $('>p', $soc2).html(soc2 + '<span>%</span>');

            //蓄电池电压
            var batteryVoltage = Number((data.batteryElectricQuantity || 0).toFixed(1));
            if (batteryVoltage > 32) {
                batteryVoltage = 32;
            }
            $('.totalDY .num-area', $bmsContainer).css({ width: (batteryVoltage * 100 / 30) + '%' });
            $('.totalDY p', $bmsContainer).text(batteryVoltage + 'v');
            var pedal = Number(obj.elec.AcceleratorPedal || 0);
            //油门踏板
            Can.SetPullPedal2(pedal);
            //工作模式
            var m1=Number(obj.elec.LeftElecMode||0)
            if (m1 != 0) {
                $("#leftmode").html('<i>'+Can.SetWorkMode(m1)+'</i>');
            }
            var m2 = Number(obj.elec.RightElecMode || 0)
            if (m2 != 0) {
                $("#rightmode").html('<i>' + Can.SetWorkMode(m2) + '</i>');
            }
            // 单体最高电压电池
            $('#single-high-voltage').text((data.highestVoltageBoxNO || 0) + '(箱)/' + (data.highestVoltageInboxLocation || 0) + '(个)/' + (data.highestBatteryVoltage || 0).toFixed(2) + '(V)');
            //单体最低电压电池
            $('#single-low-voltage').text((data.lowestVoltageBoxNO || 0) + '(箱)/' + (data.lowestVoltageInboxLocation || 0) + '(个)/' + (data.lowestBatteryVoltage || 0).toFixed(2) + '(V)');
            //单体最高温度电池
            $('#single-high-temp').text((data.highestTemperatureBoxNO || 0) + '(箱)/' + (data.hightestTemperatureInboxLocation || 0) + '(个)/' + (data.highestTemperature || 0) + '(℃)');
            //单体最低温度电池
            $('#single-low-temp').text((data.lowestTemperatureBoxNO || 0) + '(箱)/' + (data.lowestTemperatureInboxLocation || 0) + '(个)/' + (data.lowestTemperature || 0) + '(℃)');
            //电池箱体状态1、2
            var _battery1Html = '<div class="fl">' +
                '           <p title="单体最高电池电压">单体电压最高值5.0V</p>' +
                '           <p title="单体最低电池电压">单体电压最低值0.0V</p>' +
                '      </div>' +
                '      <div class="fr">' +
                '           <p title="左电机绕组温度">左电机绕组温度' + obj.elec.LeftElecTemp + '℃</p>' +
                '           <p title="左电机控制器温度">左电机控制器温度' + obj.elec.LeftElecContrTemp + '℃</p>' +
                '      </div>';
            $('.battery1', $bmsContainer).empty().append(_battery1Html);
            var _battery2Html = '<div class="fl">' +
                '           <p title="单体最高电池电压">单体电压最高值5.0V</p>' +
                '           <p title="单体最低电池电压">单体电压最低值0.0V</p>' +
                '      </div>' +
                '      <div class="fr">' +
                '           <p title="右电机绕组温度">右电机绕组温度' + obj.elec.RightElecTemp + '℃</p>' +
                '           <p title="右电机控制器温度">右电机控制器温度' + obj.elec.RightElecContrTemp + '℃</p>' +
                '      </div>';
            $('.battery2', $bmsContainer).empty().append(_battery2Html);
        },
        /**
         * 更新图表数据
         * @param obj
         * @param val
         */
        updateMeterValue:function(obj, val) {
            obj.highcharts().series[0].points[0].update(val);
        },
        /**
         * 加载传统车辆数据
         */
        loadNormalView:function (data) {
            $('.uptime').text(data.time);
            var obj = data.caninfo;
            if (obj.shortmileage)
                $("#subTotalMileage-3").html(obj.shortmileage + "<i>km</i>");
            if (obj.totalmileage)
                $("#totalMileage-3").html(obj.totalmileage+ "<i>km</i>");
            if (obj.oilconsumption)
                $("#totalEnergy-3").html(obj.oilconsumption+ "<i>L</i>");
            var act = data.canstatinfo;
            Can.setLightStyle("#mixin-energe .icon-flag1", act.leftturn);
            Can.setLightStyle("#mixin-energe .icon-flag9", act.rightturn);
            Can.setLightStyle("#mixin-energe .icon-flag20", act.accturn);
            Can.setLightStyle("#mixin-energe .icon-flag2", act.beforefoglampsturn);
            Can.setLightStyle("#mixin-energe .icon-flag3", act.afterfoglampsturn);
            Can.setLightStyle("#mixin-energe .icon-flag4", act.dippedlightsturn);
            Can.setLightStyle("#mixin-energe .icon-flag5", act.highbeamturn);
            Can.setLightStyle("#mixin-energe .icon-flag6", act.absturn);
            Can.setLightStyle("#mixin-energe .icon-flag7", act.parkingbraketurn);
            Can.setLightStyle("#mixin-energe .icon-flag10", act.footbraketurn);
            Can.setLightStyle("#mixin-energe .icon-flag21", act.enginepreheatingturn);
            Can.setLightStyle("#mixin-energe .icon-flag22", act.engineworkturn);
            Can.setLightStyle("#mixin-energe .icon-flag11", act.safetybelt);
            Can.setLightStyle("#mixin-energe .icon-flag18", act.headerdoorturn);
            Can.setLightStyle("#mixin-energe .icon-flag19", act.middledoorturn);
            Can.setLightStyle("#mixin-energe .icon-flag16", act.lastdoorturn);
            Can.setLightStyle("#mixin-energe .icon-flag17", act.positionlampturn);
            Can.setLightStyle("#mixin-energe .icon-flag23", act.acturn);
            Can.SetDispatch(data.dispatchinfo);
            if (obj.speed)
                Can.SetSpeed(obj.speed);
            if (obj.ratespeed)
                Can.SetRpm(obj.ratespeed);
            try {
                if (obj.totalbatteryvoltage)
                    Can.SetBatteryVoltage(obj.totalbatteryvoltage);
            } catch (err) { console.log(err); }
            try {
                if (obj.totalbatterygenerator)
                    Can.SetBatteryCircuit(obj.totalbatterygenerator);
            } catch (err) { console.log(err); }
            try {
                if (obj.beforepressure)
                    Can.SetFrontAirPressure(obj.beforepressure);
            } catch (err) { console.log(err); }
            try {
                if (obj.afterpressure)
                    Can.SetBackAirPressure(obj.afterpressure);
            } catch (err) { console.log(err); }
            try {
                if (obj.totalbatteryvoltage)
                    Can.SetBatteryVoltage3(obj.totalbatteryvoltage);
            } catch (err) { console.log(err); }
            try {
                if (obj.watertemp)
                    Can.SetWaterTemp(obj.watertemp);
            } catch (err) { console.log(err); }
            try {
                if (obj.oilpressure)
                    Can.SetEngineOilPressure(obj.oilpressure);
            } catch (err) { console.log(err); }
            try {
                if (obj.soc)
                    Can.SetSOC(obj.soc);
            } catch (err) { console.log(err); }
            try {
                if (obj.oilmass)
                    Can.SetoilMass(obj.oilmass);
            } catch (err) { console.log(err); }
        },
        /**
         * 重置BMS界面数据
         * @constructor
         */
        RestBMSView:function () {
            var $bmsContainer = $('#bms');
            //右转向灯
            Can.setLightStyle('#bms .icon-flag9', 1);
            //左转向
            Can.setLightStyle('#bms .icon-flag1', 1);
            // 后雾灯
            Can.setLightStyle('#bms .icon-flag3', 1);
            // 前雾
            Can.setLightStyle('#bms .icon-flag2', 1);
            //近光灯
            Can.setLightStyle('#bms .icon-flag4', 1);
            //远光灯
            Can.setLightStyle('#bms .icon-flag5', 1);
            //ABS
            Can.setLightStyle('#bms .icon-flag6', 1);
            //驻车制动器
            Can.setLightStyle('#bms .icon-flag7', 1);
            //脚刹指示灯
            Can.setLightStyle('#bms .icon-flag10', 1);
            //电池统电指示灯
            Can.setLightStyle('#bms .icon-flag15', 1);
            //安全带指示灯
            Can.setLightStyle('#icon-flag11', 1);
            //后仓门开信号
            Can.setLightStyle('#bms .icon-flag16', 1);
            //位置灯指示
            Can.setLightStyle('#bms .icon-flag17', 1);
            //前车门
            Can.setLightStyle('#bms .icon-flag18', 1);
            //中门
            Can.setLightStyle('#bms .icon-flag19', 1);
            Can.setLightStyle('#bms .icon-flag23', 1);
            //车速
            var chart = $('.speed-meter', $bmsContainer).highcharts(),
                point = chart.series[0].points[0];

            var speedVal = 0;
            point.update(speedVal);
            $('.speed-text', $bmsContainer).empty().append(speedVal + '<span> <i>/</i> 限55</span>');

            //转速
            var rpmChart = $('.rpm-meter', $bmsContainer).highcharts(),
                rmpPoint = rpmChart.series[0].points[0];
            var rpmVal = 0 / 100;
            rmpPoint.update(rpmVal);
            $('.rpm-text', $bmsContainer).html("<span>左</span>0/0<span>右</span>")
            Can.SetBMSGear(4);
            //总里程
            $('#totalMileage-4').empty().append('0<i>km</i>');
            //前气压
            var frontAirPressure = 0;// parseFloat(((obj.beforepressure || 0)).toFixed(2));
            $('.front-gasbag .num-area', $bmsContainer).css({height: frontAirPressure * 100 + '%'});
            $('.front-gasbag>p:first', $bmsContainer).text(frontAirPressure + 'MPA');
            //后气压
            var backAirPressure = 0;// parseFloat(((obj.afterpressure || 0)).toFixed(2));
            $('.black-gasbag .num-area', $bmsContainer).css({height: backAirPressure * 100 + '%'});
            $('.black-gasbag>p:first', $bmsContainer).text(backAirPressure + 'MPA');
            //动力电池电压1、2

            $('.dldcdy1', $bmsContainer).text(0);
            Can.updateMeterValue($('.battery-voltage-meter1', $bmsContainer), 0);
            $('.dldcdy2', $bmsContainer).text(0);
            Can.updateMeterValue($('.battery-voltage-meter2', $bmsContainer), 0);

            //动力电池电流1、2

            Can.updateMeterValue($('.battery-circuit-meter1', $bmsContainer), 0);
            $('.dldcdl1', $bmsContainer).text(0);


            Can.updateMeterValue($('.battery-circuit-meter2', $bmsContainer), 0);
            $('.dldcdl2', $bmsContainer).text(0);

            //soc1、2

            var $soc1 = $('.soc1', $bmsContainer);
            $('.num-area', $soc1).css({height: 0 + '%'});
            $('>p', $soc1).html(0 + '<span>%</span>');
            var $soc2 = $('.soc2', $bmsContainer);
            $('.num-area', $soc2).css({height: 0 + '%'});
            $('>p', $soc2).html(0 + '<span>%</span>');

            //蓄电池电压
            var batteryVoltage = 0;// Number((data.batteryElectricQuantity || 0).toFixed(1));
            if (batteryVoltage > 32) {
                batteryVoltage = 32;
            }
            $('.totalDY .num-area', $bmsContainer).css({width: (batteryVoltage * 100 / 30) + '%'});
            $('.totalDY p', $bmsContainer).text(batteryVoltage + 'v');
            //油门踏板
            Can.SetPullPedal2(0);
            //工作模式
            var m1 = 3;// Number(obj.elec.LeftElecMode || 0)
            if (m1 != 0) {
                $("#leftmode").html('<i>' + Can.SetWorkMode(m1) + '</i>');
            }
            var m2 = 3;// Number(obj.elec.RightElecMode || 0)
            if (m2 != 0) {
                $("#rightmode").html('<i>' + Can.SetWorkMode(m2) + '</i>');
            }
            //电池箱体状态1、2
            var _battery1Html = '<div class="fl">' +
                '           <p title="单体最高电池电压">单体电压最高值5.0V</p>' +
                '           <p title="单体最低电池电压">单体电压最低值0.0V</p>' +
                '      </div>' +
                '      <div class="fr">' +
                '           <p title="左电机绕组温度">左电机绕组温度0℃</p>' +
                '           <p title="左电机控制器温度">左电机控制器温度0℃</p>' +
                '      </div>';
            $('.battery1', $bmsContainer).empty().append(_battery1Html);
            var _battery2Html = '<div class="fl">' +
                '           <p title="单体最高电池电压">单体电压最高值5.0V</p>' +
                '           <p title="单体最低电池电压">单体电压最低值0.0V</p>' +
                '      </div>' +
                '      <div class="fr">' +
                '           <p title="右电机绕组温度">右电机绕组温度0℃</p>' +
                '           <p title="右电机控制器温度">右电机控制器温度0℃</p>' +
                '      </div>';
            $('.battery2', $bmsContainer).empty().append(_battery2Html);
        },
        /**
         * 重置传统车辆界面
         * @constructor
         */
        RestNormalView:function () {
            $("#mixin-energe i.icon-flag").each(function () {
                $(this).removeClass("active");
            });
            Can.SetSpeed(0);
            Can.SetRpm(0);
            Can.SetBatteryVoltage(0);
            Can.SetBatteryCircuit(0);
            Can.SetFrontAirPressure(0);
            Can.SetBackAirPressure(0);
            Can.SetBatteryVoltage3(0);
            Can.SetWaterTemp(0);
            Can.SetEngineOilPressure(0);
            Can.SetSOC(0);
            Can.SetoilMass(0);
            Can.SetBrakePedal(0);
            Can.SetPullPedal(0);
            $("#subTotalMileage-3").html("<i>0km</i>");
            $("#totalMileage-3").html("<i>0km</i>");
            $("#totalEnergy-3").html("<i>0L</i>");
        },
        /**
         * 设置BMS档位
         * @param value
         * @constructor
         */
        SetBMSGear:function (value) {
            var gear = value || 0;
            if (gear == 1) {
                $('#gear-3').text('R档');
            } else if (gear == 2) {
                $('#gear-3').text('N档');
                $("#bms .ready").text("READY");
            } else if (gear == 3) {
                $('#gear-3').text('D档');
                $("#bms .ready").text("RUNING");
            } else if (gear == 4) {
                $('#gear-3').text('P档');
            }
        },
        /**
         * 设置调度信息
         * @constructor
         */
        SetDispatch:function (obj) {
            $("#prev-station").text(obj.currentsite==undefined?"":obj.currentsite);
            $("#next-station").text(obj.nextsite==undefined?"":obj.nextsite);
            if (obj.sitetype == 2) {
                if (obj.inoroutsite == 0) {
                    $("#next-station").attr("title", "正在进站...");
                    //$("#next-station").tooltip('show');
                    //$("#prev-station").tooltip('hide');
                } else {
                    $("#prev-station").attr("title", "正在出站...");
                    //$("#prev-station").tooltip('show');
                    //$("#next-station").tooltip('hide');
                }
            }
        },
        /**
         * 加载地图
         * @constructor
         */
        SetMap:function (obj) {
            map.remove(markers);
            var location = obj.caninfo.location;
            var angle = obj.roate;
            if(location != '')
            {
                location = parent.Main.ConvertGpsToAmapLocation(location);
                var long = location.split(',')[0],lat = location.split(',')[1];
                map.setCenter([long,lat]);
                marker = new AMap.Marker({
                    map:map,
                    position:[long,lat],
                    icon: new AMap.Icon({
                        image:'http://webapi.amap.com/images/car.png',
                        size:new AMap.Size(36,36),
                        imageSize:new AMap.Size(36,21)
                    }),
                    angle: parseFloat(angle)-90,
                    offset:new AMap.Pixel(-15,0),
                    autoRotation:false
                });
                markers.push(marker);
            }
            },
        /**
         * 播放视频
         * @constructor
         */
        SetOcx:function () {
            try{
                WebOcx.StartPreview(serverIp,hosttype,code,clientIp,channel,1);
            }catch (err){console.log('browser not support activex');}
        },
        /**
         * 开始请求数据
         * @constructor
         */
        RequestData:function () {
            parent.Http.Ajax({
                type:'get',
                url:'/api/v1/can/realtimebyid?deviceId='+deviceId
            },function (result) {
                parent.TramDalog.CloseLoading();
                var data = result.result;
                $('.uptime').text(data.time);
                if(busType == 5)
                    Can.loadBMSDataView(data);
                else
                    Can.loadNormalView(data);
                //Can.removeMap();
                Can.SetMap(result.result);
            })
        },
        EndCan:function () {
            $('#start').text('下发');
            $('#totelMin').prop('disabled',false);
            $('#intervalTime').prop('disabled',false);
            $('.timebox').text('00:00');
            clearInterval(interval);
            clearInterval(tinterval);
            parent.TramDalog.InfoAlert('监控结束',true);
            Can.CalcCountDownTime(0);
            Can.RestBMSView();
            Can.RestNormalView();
            try{
                WebOcx.Stop();
            }catch (err){console.log('browser not support activex');}

        },
        //倒计时统计
        CalcCountDownTime:function (seconds) {
            var t = seconds == 0 ? 0 :seconds;
            var mins = t <60?0:parseInt(t/60),sec = t % 60;
            $('.timebox').text((parseInt(mins)<10?"0"+mins:mins)+":"+(parseInt(sec)<10?"0"+sec:sec));
        }
    }
}();
Can.init();
