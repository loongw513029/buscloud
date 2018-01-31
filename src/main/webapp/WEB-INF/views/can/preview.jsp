<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link href="/fonts/iconfont.css" rel="stylesheet">
    <link href="/css/can.css" rel="stylesheet">
</head>
<body>

<div class="header-box">
    <div class="timebox">
        00:00
    </div>
    <label>监控时长</label>
    <select name="interest" lay-filter="aihao">
        <option value="1">1分钟</option>
        <option value="3">3分钟</option>
        <option value="5">5分钟</option>
        <option value="10">10分钟</option>
    </select>
    <label>时间间隔</label>
    <select name="interest" lay-filter="aihao">
        <option value="3">3秒</option>
        <option value="10">10秒</option>
        <option value="15">15秒</option>
    </select>
    <button id="start">下发</button>
</div>
<div class="uptime">
    2018-01-15 12:00:00
</div>
<div class="meter-wrap">
    <div class="monitor-wrap ui-draggable ui-draggable-handle ui-resizable" id="map" style="z-index: 99;top:60px;line-height:0px">

    </div>
    <div class="map-wrap ui-draggable ui-draggable-handle ui-resizable" style="z-index: 99;top:0px;padding:0px;;background:url(/images/can/ocx.png) no-repeat">
        <OBJECT id="WebOcx" classid=clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4 style="width:304px;height:206px;display:none">
            <PARAM NAME="_Version" VALUE="65536">
            <PARAM NAME="_ExtentX" VALUE="2646">
            <PARAM NAME="_ExtentY" VALUE="1323">
            <PARAM NAME="_StockProps" VALUE="0" width="100%" height="100%">
        </OBJECT>
    </div>
    <div class="avoidance-wrap ui-draggable ui-draggable-handle ui-resizable" style="z-index: 99;top:60px;padding:0px;line-height:0px;width:auto; background:#000;width:304px; overflow:inherit">
        <div class="radar">
            <i class="radar1"></i>
            <i class="radar2"></i>
            <i class="radar3"></i>
            <i class="radar4"></i>
            <i class="radar5"></i>
            <i class="radar6"></i>
            <i class="radar7"></i>
            <i class="radar8"></i>
            <i class="radar9"></i>
            <i class="radar10"></i>
            <i class="radar11"></i>
            <i class="radar12"></i>
        </div>
        <div class="adas">
            <div class="s118 item">
                <img src="/images/can/HMW.png" />
                <label>车距预警</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s119 item">
                <img src="/images/can/HMW.png" />
                <label>危险车距</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s120 item">
                <img src="/images/can/LDW.png" />
                <label>压左道</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s121 item">
                <img src="/images/can/LDW.png" />
                <label>压右道</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s122 item">
                <img src="/images/can/FCW.png" />
                <label>低速碰撞</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s123 item">
                <img src="/images/can/FCW.png" />
                <label>前向碰撞</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
            <div class="s124 item">
                <img src="/images/can/PCW.png" />
                <label>行人报警</label>
                <span><img src="/images/can/online.png" /></span>
            </div>
        </div>
        <div class="Aerial" style="display:none;width:304px;height:206px;background:url(/images/can/ocx.png) no-repeat">
            <OBJECT id="WebOcx2" classid=clsid:4EBC3418-4FF1-3A8F-8D1C-BDB084E6E0D4 style="width:304px;height:206px">
                <PARAM NAME="_Version" VALUE="65536">
                <PARAM NAME="_ExtentX" VALUE="2646">
                <PARAM NAME="_ExtentY" VALUE="1323">
                <PARAM NAME="_StockProps" VALUE="0" width="100%" height="100%">
            </OBJECT>
        </div>
    </div>
    <div class="station-info-wrap" id="detail-info">
        <h3 id="line-info">线路:250 自编号:N131</h3>
        <div class="station-info clearfix">
            <div class="prev-station arrive">
                <i class="icon-station"></i><label id="prev-station" data-toggle="tooltip" title=""></label>
            </div>
            <div class="arrow">
                <span>开往</span>
            </div>
            <div class="next-station arrive">
                <i class="icon-station"></i><label id="next-station" data-toggle="tooltip" title=""></label>
            </div>
        </div>
    </div>
    <!--传统车辆-->
<div class="generous-meter-wrap new-energe-wrap mix-energe mix-energe2" id="mixin-energe" style="overflow: hidden;">
    <div class="light-flag-wrap">
        <div class="row1 clearfix">
            <i class="icon-flag icon-flag1" title="左转向灯"></i>
            <i class="icon-flag icon-flag20" style="position: relative;top: 1px;" title="钥匙档位"></i>
            <i class="icon-flag icon-flag2" title="前雾灯"></i>
            <i class="icon-flag icon-flag3" title="后雾灯"></i>
            <i class="icon-flag icon-flag4" title="近光灯"></i>
            <i class="icon-flag icon-flag5" title="远光灯"></i>
            <i class="icon-flag icon-flag6" title="ABS故障"></i>
            <i class="icon-flag icon-flag7" title="驻车制动器指示灯"></i>
            <i class="icon-flag icon-flag10" title="脚刹指示灯"></i>
            <i class="icon-flag icon-flag21" title="发动机预热"></i>
            <i class="icon-flag icon-flag9" title="右转向灯"></i>
        </div>
        <div class="row2 clearfix">
            <i class="icon-flag icon-flag22" title="发动机启动"></i>
            <i class="icon-flag icon-flag15" title="电池充电指示灯"></i>
            <i class="icon-flag icon-flag11" title="安全带指示灯"></i>
            <i class="icon-flag icon-flag16" title="后仓门开信号"></i>
            <i class="icon-flag icon-flag17" title="位置灯指示"></i>
            <i class="icon-flag icon-flag18" title="前车门"></i>
            <i class="icon-flag icon-flag19" title="中门"></i>
            <i class="icon-flag icon-flag23" title="AC空调信号"></i>
        </div>
    </div>
    <div class="light-flag-wrap2">
        <div class="row3 clearfix">
            <i class="icon-flag icon-flag2-1" title="严重故障"></i>
            <i class="icon-flag icon-flag2-2" title="蓄电池指示灯"></i>
            <i class="icon-flag icon-flag2-3" title="充电连接故障"></i>
            <i class="icon-flag icon-flag2-4" title="胎压报警"></i>
            <i class="icon-flag icon-flag2-5" title="电机过热"></i>
            <i class="icon-flag icon-flag2-6" title="电机超速报警"></i>
            <i class="icon-flag icon-flag2-7" style="position: relative;top: 4px;" title="整车控制器故障"></i>
            <i class="icon-flag icon-flag2-8" title="下车铃指示灯"></i>
        </div>
        <div class="row4 clearfix">
            <i class="icon-flag icon-flag2-9" title="干燥器"></i>
            <i class="icon-flag icon-flag2-10 ml0" style="margin-right: 3px" title="左蹄片报警"></i>
            <i class="icon-flag icon-flag2-11 ml0" style="margin-right: 3px" title="右蹄片报警"></i>
            <i class="icon-flag icon-flag2-12 ml0" title="ASR故障"></i>
            <i class="icon-flag icon-flag2-13" style="margin-left: 1px;" title="制动器故障"></i>
            <i class="icon-flag icon-flag2-14" title="动力电池故障"></i>
            <i class="icon-flag icon-flag2-15" style="margin-left: 3px;" title="系统故障"></i>
            <i class="icon-flag icon-flag2-16" style="margin-left: 3px;" title="空滤报警指示灯"></i>
        </div>
    </div>
    <div class="vol-wrap">
        <p id="battery-voltage-text-3">0<span>v</span></p>
        <div class="chart-box" title="蓄电池电压">
            <div class="top-bg"></div>
            <div class="num-area" style="height: 0%;" id="battery-voltage-3"></div>
        </div>
    </div>
    <div class="vol-wrap vol-wrap2">
        <p id="soc-text-3">0.0<span>%</span></p>
        <div class="chart-box" title="SOC">
            <div class="top-bg"></div>
            <div class="num-area" style="height: 0%;" id="soc-3"></div>
        </div>
    </div>
    <div class="vol-wrap vol-wrap3" id="engineOilPressure-3">
        <p>0<span>mpa</span></p>
        <div class="chart-box">
            <div class="top-bg"></div>
            <div class="num-area" style="height: 0%;"></div>
        </div>
    </div>
    <div class="vol-wrap vol-wrap4" id="water-temp-3">
        <p>0<span>℃</span></p>
        <div class="chart-box">
            <div class="top-bg"></div>
            <div class="num-area" style="height: 0%;"></div>
        </div>
    </div>
    <div class="info-text3">
        <div class="ready" id="state-3">READY</div>
        <div class="degree" id="gear-3">D档</div>
    </div>
    <div class="info-text4">
        <div>
            <h3>瞬时能耗</h3>
            <p id="instEnergy-3">0.0<span>L/H</span></p>
        </div>

    </div>
    <div class="info-text2 clearfix">
        <p class="clearfix p-s">
            <span id="subTotalMileage-3"><i>0km</i></span>小计里程
        </p>
        <p class="p-s">
            <span id="totalMileage-3"><i>0km</i></span> 总里程
        </p>
        <p class="clearfix p-s">
            <span style="margin-right: 11px;" id="totalEnergy-3"><i>0L</i></span>总能耗
        </p>
    </div>
    <div class="rate-wrap">
        <h4>车速 km/h</h4>
        <p class="p1" id="speed-text-3">0</p>
    </div>
    <div class="rate-wrap rate-wrap2">
        <h4>转速 r/min</h4>
        <p class="p1" id="rpm-text-3">0</p>
    </div>
    <div class="pressure-wrap">
        <p class="p1">
            <span id="frontAir-p-3">0.00</span>
        </p>
        <p class="p3">前气压/mpa</p>
    </div>
    <div class="pressure-wrap pressure-wrap2">
        <p class="p1">
            <span id="backAir-p-3">0.00</span>
        </p>
        <p class="p3">后气压/mpa</p>
    </div>
    <div class="pressure-wrap pressure-wrap3">
        <p class="p1">
            <span id="bcc-text-3">0</span>
        </p>
        <p class="p3">电池总电流/A</p>
    </div>
    <div class="pressure-wrap pressure-wrap4">
        <p class="p1">
            <span id="bvc-text-3">0</span>
        </p>
        <p class="p3">电池总电压/V</p>
    </div>
    <div class="total-system mixin">
        <div>
            <span>整车控制系统</span>
            <p></p>
            <i id="whole-car-text"></i>
        </div>
        <div>
            <span>发动机</span>
            <p></p>
            <i id="engine-text"></i>
        </div>
        <div>
            <span>电机</span>
            <p></p>
            <i id="electrical-text"></i>
        </div>
        <div>
            <span>电机转速</span>
            <p></p>
            <i><em id="electricMachineryRotateSpeed" name="electricMachineryRotateSpeed">0</em>r/min</i>
        </div>
    </div>
    <div class="gasbag mixin hide" id="left-gas-bag">
        <p></p>
        <div class="gasbag-box">
            <div class="top-bg"></div>
            <div class="num-area" style="width:17px"></div>
        </div>
        <p class="p2">左侧气囊气压</p>
    </div>
    <div class="gasbag gasbag2 mixin hide" id="right-gas-bag">
        <p></p>
        <div class="gasbag-box">
            <div class="top-bg"></div>
            <div class="num-area" style="width:17px"></div>
        </div>
        <p class="p2">右侧气囊气压</p>
    </div>
    <div class="foot-panel foot-panel3" id="mixin-oil">
        <div class="clearfix">
            <div class="foot-panel-box">
                <div class="top-bg"></div>
                <div class="num-area" style="width: 0%;"></div>
            </div>
            <p class="p1">0%</p>
        </div>
        <p class="p2">油量</p>
    </div>
    <div class="foot-panel mixin" id="brake-pedal">
        <div class="clearfix">
            <div class="foot-panel-box">
                <div class="top-bg"></div>
                <div class="num-area" style="width: 0%;"></div>
            </div>
            <p class="p1">0%</p>
        </div>
        <p class="p2">制动踏板</p>
    </div>
    <div class="foot-panel foot-panel2 mixin" id="pull-pedal">
        <div class="clearfix">
            <div class="foot-panel-box">
                <div class="top-bg"></div>
                <div class="num-area" style="width: 0%;"></div>
            </div>
            <p class="p1">0%</p>
        </div>
        <p class="p2">牵引踏板</p>
    </div>
    <div class="light-box mixin" id="contactor-signal">
        <div class="light light1" title="电容主接触器"></div>
        <div class="light light2" title="电容预充电接触器"></div>
        <div class="light light3" title="电池主接触器"></div>
        <div class="light light4" title="电池预充电接触器"></div>
        <div class="light light5" title="电制动"></div>
    </div>
    <!--车速-->
    <div id="container-speed-3" style="width: 401px; height: 221px;position: absolute;left: 21px;top: 103px;" data-highcharts-chart="0"><div id="highcharts-k8nqa00-0" class="highcharts-container " style="position: relative; overflow: hidden; width: 401px; height: 221px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="401" height="221" viewBox="0 0 401 221"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-1"><rect x="0" y="0" width="381" height="196" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="401" height="221" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="381" height="196"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 94.41188803640627 198.65000000000003 A 122.5 122.5 0 1 1 306.64930890933385 198.54388128072034 L 304.52632273114716 197.32100365510593 A 120.05 120.05 0 1 0 96.53365027567816 197.425 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 200.5 137.4 L 94.41188803640627 198.65000000000003" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="381" height="196"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 94.41188803640627 198.65000000000003 A 122.5 122.5 0 1 1 306.64930890933385 198.5438812807203 M 200.5 137.4 A 0 0 0 1 0 200.5 137.4 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-1)"><path fill="rgb(45,187,239)" d="M 84.41188803640627 188.65000000000003 A 122.5 122.5 0 0 1 84.4731910702495 188.7560574692848 L 86.5937272488445 187.52893631989912 A 120.05 120.05 0 0 0 86.53365027567816 187.425 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
    <!--转速-->
    <div id="container-rpm-3" style="width: 401px; height: 222px;position: absolute;left: 596px;top: 102px;" data-highcharts-chart="1"><div id="highcharts-k8nqa00-2" class="highcharts-container " style="position: relative; overflow: hidden; width: 401px; height: 222px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="401" height="222" viewBox="0 0 401 222"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-3"><rect x="0" y="0" width="381" height="197" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="401" height="222" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="381" height="197"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 93.87062215904099 199.6125 A 123.125 123.125 0 1 1 307.19088701601413 199.5058398586832 L 305.05706927569383 198.27672306150953 A 120.6625 120.6625 0 1 0 96.00320971586018 198.38125000000002 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 200.5 138.05 L 93.87062215904099 199.6125" opacity="1"></path><path fill="none" class="highcharts-grid-line" d="M 200.5 138.05 L 321.75445458962815 116.6695681247593" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="381" height="197"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 93.87062215904099 199.6125 A 123.125 123.125 0 1 1 307.19088701601413 199.50583985868317 M 200.5 138.05 A 0 0 0 1 0 200.5 138.05 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-3)"><path fill="rgb(45,187,239)" d="M 83.87062215904099 189.6125 A 123.125 123.125 0 0 1 83.93223796346506 189.71909857882198 L 86.06359320419575 188.48571660724554 A 120.6625 120.6625 0 0 0 86.00320971586018 188.38125000000002 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
    <!--动力电池电压-->
    <div id="battery-voltage-chart-3" style="width: 161px; height: 181px;position: absolute;left: 26px;top: 307px;" data-highcharts-chart="2"><div id="highcharts-k8nqa00-4" class="highcharts-container " style="position: relative; overflow: hidden; width: 161px; height: 181px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="161" height="181" viewBox="0 0 161 181"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-5"><rect x="0" y="0" width="141" height="156" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="161" height="181" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="141" height="156"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789342 42.62949005112146 L 133.38157392353557 43.53690025009903 A 69.09 69.09 0 0 0 27.57398942490981 43.58980404675699 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 26.493866760112056 42.68347351709897" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="141" height="156"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789345 42.629490051121465 M 80.5 88 A 0 0 0 0 0 80.5 88 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-5)"><path fill="rgb(85,191,59)" d="M 16.493866760112056 32.68347351709897 A 70.5 70.5 0 0 1 16.448577244246287 32.73750229959918 L 17.529605699361355 33.64275225360719 A 69.09 69.09 0 0 0 17.57398942490981 33.58980404675699 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
    <!--动力电池电压-->
    <div id="battery-circuit-chart-3" style="width: 161px; height: 181px;position: absolute;left: 161px;top: 307px;" data-highcharts-chart="3"><div id="highcharts-k8nqa00-6" class="highcharts-container " style="position: relative; overflow: hidden; width: 161px; height: 181px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="161" height="181" viewBox="0 0 161 181"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-7"><rect x="0" y="0" width="141" height="156" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="161" height="181" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="141" height="156"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789342 42.62949005112146 L 133.38157392353557 43.53690025009903 A 69.09 69.09 0 0 0 27.57398942490981 43.58980404675699 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 80.5 17.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="141" height="156"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789345 42.629490051121465 M 80.5 88 A 0 0 0 0 0 80.5 88 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-7)"><path fill="rgb(85,191,59)" d="M 16.493866760112056 32.68347351709897 A 70.5 70.5 0 0 1 70.42950001175001 7.500035249997069 L 70.43091001151501 8.910034544997117 A 69.09 69.09 0 0 0 17.57398942490981 33.58980404675699 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
    <!--前气压-->
    <div id="frontAirPressureChart-3" style="width: 161px; height: 181px;position: absolute;left: 697px;top: 307px;" data-highcharts-chart="4"><div id="highcharts-k8nqa00-8" class="highcharts-container " style="position: relative; overflow: hidden; width: 161px; height: 181px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="161" height="181" viewBox="0 0 161 181"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-9"><rect x="0" y="0" width="141" height="156" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="161" height="181" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="141" height="156"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789342 42.62949005112146 L 133.38157392353557 43.53690025009903 A 69.09 69.09 0 0 0 27.57398942490981 43.58980404675699 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 26.493866760112056 42.68347351709897" opacity="1"></path><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 134.50613323988796 42.683473517098975" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="141" height="156"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789345 42.629490051121465 M 80.5 88 A 0 0 0 0 0 80.5 88 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-9)"><path fill="rgb(52,226,89)" d="M 16.493866760112056 32.68347351709897 A 70.5 70.5 0 0 1 16.448577244246287 32.73750229959918 L 17.529605699361355 33.64275225360719 A 69.09 69.09 0 0 0 17.57398942490981 33.58980404675699 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
    <!--后气压-->
    <div id="backAirPressureChart-3" style="width: 161px; height: 181px;position: absolute;left: 831px;top: 307px;" data-highcharts-chart="5"><div id="highcharts-k8nqa00-10" class="highcharts-container " style="position: relative; overflow: hidden; width: 161px; height: 181px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="161" height="181" viewBox="0 0 161 181"><desc>Created with Highcharts 5.0.9</desc><defs><clipPath id="highcharts-k8nqa00-11"><rect x="0" y="0" width="141" height="156" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="161" height="181" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="141" height="156"></rect><g class="highcharts-plot-bands-0"><path fill="none" class="highcharts-plot-band highcharts-pane" stroke="#cccccc" stroke-width="1" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789342 42.62949005112146 L 133.38157392353557 43.53690025009903 A 69.09 69.09 0 0 0 27.57398942490981 43.58980404675699 Z"></path></g><g class="highcharts-grid highcharts-yaxis-grid "><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 26.493866760112056 42.68347351709897" opacity="1"></path><path fill="none" class="highcharts-grid-line" d="M 80.5 88 L 134.50613323988796 42.683473517098975" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" x="10" y="10" width="141" height="156"></rect><g class="highcharts-axis highcharts-yaxis "><path fill="none" class="highcharts-axis-line" d="M 26.493866760112056 42.68347351709897 A 70.5 70.5 0 0 1 134.46078971789345 42.629490051121465 M 80.5 88 A 0 0 0 0 0 80.5 88 "></path></g><g class="highcharts-series-group"><g class="highcharts-series highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined highcharts-tracker" transform="translate(10,10) scale(1 1)" clip-path="url(#highcharts-k8nqa00-11)"><path fill="rgb(52,226,89)" d="M 16.493866760112056 32.68347351709897 A 70.5 70.5 0 0 1 16.448577244246287 32.73750229959918 L 17.529605699361355 33.64275225360719 A 69.09 69.09 0 0 0 17.57398942490981 33.58980404675699 Z" class="highcharts-point" sweep-flag="0" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="highcharts-markers highcharts-series-0 highcharts-solidgauge-series highcharts-color-undefined " transform="translate(10,10) scale(1 1)" clip-path="none"></g></g><g class="highcharts-legend"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8" visibility="hidden"></rect><g><g></g></g></g><g class="highcharts-axis-labels highcharts-yaxis-labels "></g></svg></div></div>
</div>
    <!--BMS纯电动车-->
    <div class="generous-meter-wrap new-energe-wrap new-energe-wrap4" id="bms" style="display: none;">

        <div class="light-flag-wrap">
            <div class="row1 clearfix">
                <i class="icon-flag icon-flag1" title="左转向灯"></i>
                <i class="icon-flag icon-flag2" title="前雾灯"></i>
                <i class="icon-flag icon-flag3" title="后雾灯"></i>
                <i class="icon-flag icon-flag4" title="近光灯"></i>
                <i class="icon-flag icon-flag5" title="远光灯"></i>
                <i class="icon-flag icon-flag6" title="ABS故障"></i>
                <i class="icon-flag icon-flag7" title="驻车制动器指示灯"></i>
                <i class="icon-flag icon-flag10" title="脚刹指示灯"></i>
                <i class="icon-flag icon-flag9" title="右转向灯"></i>
            </div>
            <div class="row2 clearfix">
                <i class="icon-flag icon-flag15" title="电池充电指示灯"></i>
                <i class="icon-flag icon-flag11" title="安全带指示灯"></i>
                <i class="icon-flag icon-flag16" title="后仓门开信号"></i>
                <i class="icon-flag icon-flag17" title="位置灯指示"></i>
                <i class="icon-flag icon-flag18" title="前车门"></i>
                <i class="icon-flag icon-flag19" title="中门"></i>
            </div>
        </div>

        <div class="vol-wrap soc2">
            <p>0<span>%</span></p>

            <div class="chart-box">
                <div class="top-bg"></div>
                <div class="num-area"></div>
            </div>
        </div>
        <div class="vol-wrap vol-wrap2 soc1">
            <p>0<span>%</span></p>

            <div class="chart-box">
                <div class="top-bg"></div>
                <div class="num-area"></div>
            </div>
        </div>
        <div class="info-text1">
            <div class="ready">
                READY
            </div>
            <div class="degree">
                N档
            </div>
        </div>
        <div class="info-text2 clearfix">
            <p class="clearfix p-s">
                <span id="leftmode">关闭</span>左电机模式
            </p>
            <p class="clearfix p-s">
                <span id="rightmode">关闭</span>右电机模式
            </p>
            <p class="p-s">
                <span id="totalMileage-4">0.0 <i>km</i></span> 总里程
            </p>
        </div>
        <div class="rate-wrap">
            <h4>车速 km/h</h4>

            <p class="p1 speed-text">
                0<span>
                                        <i>/</i> 限55
                                    </span>
            </p>

        </div>

        <div class="rate-wrap rate-wrap2">
            <h4>转速 r/min</h4>

            <p class="p1 rpm-text"  style="padding-top:30px;font-size:30px">
                <span>左</span>0/0<span>右</span>
            </p>

        </div>
        <div class="pressure-wrap">
            <p class="p1 dldcdl2"><span>0 </span></p>

            <p class="p3" style="width:105px;margin-left: -10px;">动力电池电流/A</p>
        </div>
        <div class="pressure-wrap pressure-wrap2" style="right:97px">
            <p class="p1 dldcdy2"><span>0 </span></p>

            <p class="p3" style="width:105px;margin-left: -10px;">动力电池电压/V</p>
        </div>
        <div class="pressure-wrap pressure-wrap3">
            <p class="p1 dldcdl1"><span>0</span></p>

            <p class="p3">动力电池电流/A</p>
        </div>
        <div class="pressure-wrap pressure-wrap4" style="right:816px">
            <p class="p1 dldcdy1"><span>0</span></p>

            <p class="p3">动力电池电压/V</p>
        </div>
        <div class="battery battery1">
            <div class="fl">
                <p title="单体最高电池电压">单体电压最高值5.0V</p>

                <p title="单体最低电池电压">单体电压最低值0.0V</p>
            </div>
            <div class="fr">
                <p title="左电机绕组温度">左电机绕组温度35℃</p>

                <p title="左电机控制器温度">左电机控制器温度35℃</p>
            </div>
        </div>
        <div class="battery  battery2">
            <div class="fl">
                <p title="单体最高电池电压">单体电压最高值5.0V</p>

                <p title="单体最低电池电压">单体电压最低值0.0V</p>
            </div>
            <div class="fr">
                <p title="右电机绕组温度">右电机绕组温度35℃</p>

                <p title="右电机控制器温度">右电机控制器温度35℃</p>
            </div>
        </div>

        <div class="gasbag front-gasbag">
            <p>0.0MPA</p>

            <div class="gasbag-box">
                <div class="top-bg"></div>
                <div class="num-area" style="width:17px"></div>
            </div>
            <p class="p2">前气压</p>
        </div>
        <div class="gasbag gasbag2 black-gasbag">
            <p>0.0MPA</p>

            <div class="gasbag-box">
                <div class="top-bg"></div>
                <div class="num-area" style="width:17px"></div>
            </div>
            <p class="p2">后气压</p>
        </div>
        <div class="foot-panel" style="top:287px;left:198px">
            <div class="clearfix totalDY" title="蓄电池电压">
                <div class="foot-panel-box">
                    <div class="top-bg"></div>
                    <div class="num-area"></div>
                </div>
                <p class="p1">0v</p>
            </div>
        </div>
        <div class="foot-panel foot-panel2 mixin" id="pull-pedal2" style="right:155px">
            <div class="clearfix">
                <div class="foot-panel-box" style="width:45px;margin-left:1px">
                    <div class="top-bg"></div>
                    <div class="num-area"></div>
                </div>
                <p class="p1"></p>
            </div>
            <p class="p2">牵引踏板</p>
        </div>
        <!--车速-->
        <div class="speed-meter" style="width: 401px; height: 222px;position: absolute;left: 21px;top: 102px;"></div>

        <!--转速-->
        <div class="rpm-meter" style="width: 401px; height: 222px;position: absolute;left: 596px;top: 102px;"></div>

        <!--动力电池电压1-->
        <div class="battery-voltage-meter1" style="width: 160px; height: 181px;position: absolute;left: 53px;top: 307px;"></div>

        <!--动力电池电流1-->
        <div class="battery-circuit-meter1" style="width: 161px; height: 181px;position: absolute;left: 181px;top: 307px;"></div>

        <!--动力电池电流2-->
        <div class="battery-circuit-meter2" style="width: 161px; height: 181px;position: absolute;left: 677px;top: 307px;"></div>

        <!--动力电池电压2-->
        <div class="battery-voltage-meter2" style="width: 160px; height: 181px;position: absolute;left: 807px;top: 307px;"></div>

    </div>
</div>

<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/dist/jquery.cookie.js"></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script type="text/javascript" src="/dist/highcharts.js"></script>
<script type="text/javascript" src="/dist/highcharts-more.js"></script>
<script type="text/javascript" src="/dist/solid-gauge.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a09d2b412c8984e51700a2a3b24e40b1" type="text/javascript"></script>
<script src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

<script type="text/javascript" src="/web/can.js"></script>

</body>
</html>