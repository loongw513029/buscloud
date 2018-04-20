var Config = function () {
    return{
        Init:function () {
            parent.Http.Ajax({
                url:"/api/v1/System/Getinfo",
                type:'Get'
            },function (result) {
                var data = result.result;
                Config.initComboTree(data);
            });
            $('.easyui-linkbutton').each(function (i) {
                $(this).click(function () {
                    Config.saveConfig(Config.Search());
                });
            });
        },
        initComboTree:function (date) {
            $('#MapSpeedUse').combotree({
                data:[{
                    id:0,
                    text:'Gps速度'
                },{
                    id:1,
                    text:'Can速度'
            }],
                onLoadSuccess:function () {
                }
            });
            $('#IndexUnit').combotree({
                data:[{
                    id:0,
                    text:'每百公里'
                },{
                    id:1,
                    text:'每天'
                },{
                    id:2,
                    text:'每周'
                },{
                    id:3,
                    text:'每月'
                }]
                ,onLoadSuccess:function () {
                }
            });
            $('#IndexUnit').combotree('setValue',date.IndexUnit);
            $('#MapSpeedUse').combotree('setValue',date.MapSpeedUse);
            $('#VideoPlayTime').textbox("setValue",date.videoPlayTime);
            $('#ServerIp').textbox("setValue",date.ServerIp);
            $('#Port').textbox("setValue",date.Port);
            $('#FlceRecognition').textbox("setValue",date.FlceRecognition);
            $('#ApkUrl').textbox("setValue",date.ApkUrl);
            $('#VerNotice').textbox("setValue",date.VerNotice);
            $('#AlarmTipTime').textbox("setValue",date.AlarmTipTime);
            $('#ADASTime').textbox("setValue",date.ADASTime);
            $('#AlarmRelTime').textbox("setValue",date.AlarmRelTime);
            $('#SMSReceiver').textbox("setValue",date.SMSReceiver);
            $('#AppVer').textbox("setValue",date.AppVer);
            $('#AutoPlay').switchbutton(date.AutoPlay==1?"check":"uncheck");
            $('#AlarmTurn').switchbutton(date.AlarmTurn==1?"check":"uncheck");
        },
        saveConfig:function (value) {
            parent.Http.Ajax({
                url:"/api/v1/System/Save?",
                type:'Post',
                data:{
                    VideoPlayTime:value.VideoPlayTime,
                    ServerIp:value.ServerIp,
                    Port:value.Port,
                    FlceRecognition:value.FlceRecognition,
                    MapSpeedUse:value.MapSpeedUse,
                    ApkUrl:value.ApkUrl,
                    VerNotice:value.VerNotice,
                    IndexUnit:value.IndexUnit,
                    AlarmTurn:value.AlarmTurn,
                    AlarmTipTime:value.AlarmTipTime,
                    ADASTime:value.ADASTime,
                    AutoPlay:value.AutoPlay,
                    AlarmRelTime:value.AlarmRelTime,
                    SMSReceiver:value.SMSReceiver,
                    AppVer:value.AppVer
                }
            },function (result) {
                if(result.success){
                    $.messager.alert('提示','保存成功！');
                }
                else {
                    $.messager.alert('提示','保存失败！','error');
                }
            });
        },
        Search:function () {
            var config = {};
            var VideoPlayTime = $('#VideoPlayTime').textbox("getValue");
            var ServerIp = $('#ServerIp').textbox("getValue");
            var Port = $('#Port').textbox("getValue");
            var FlceRecognition = $('#FlceRecognition').textbox("getValue");
            var MapSpeedUse = $('#MapSpeedUse').combobox("getValue");
            var ApkUrl = $('#ApkUrl').textbox("getValue");
            var VerNotice = $('#VerNotice').textbox("getValue");
            var IndexUnit = $('#IndexUnit').combobox("getValue");
            var AlarmTipTime = $('#AlarmTipTime').textbox("getValue");
            var ADASTime = $('#ADASTime').textbox("getValue");
            var AlarmRelTime = $('#AlarmRelTime').textbox("getValue");
            var SMSReceiver = $('#SMSReceiver').textbox("getValue");
            var AppVer = $('#AppVer').textbox("getValue");
            var AutoPlay = $('#AutoPlay').switchbutton("options").checked;
            var AlarmTurn = $('#AlarmTurn').switchbutton("options").checked;
            config.VideoPlayTime = VideoPlayTime==""?0:VideoPlayTime;
            config.ServerIp = ServerIp;
            config.Port = Port;
            config.FlceRecognition = FlceRecognition;
            config.MapSpeedUse = MapSpeedUse;
            config.ApkUrl = ApkUrl;
            config.VerNotice = VerNotice;
            config.IndexUnit = IndexUnit;
            config.AlarmTipTime = AlarmTipTime==""?0:AlarmTipTime;
            config.ADASTime = ADASTime==""?0:ADASTime;
            config.AlarmRelTime = AlarmRelTime==""?0:AlarmRelTime;
            config.SMSReceiver = SMSReceiver;
            config.AppVer = AppVer;
            config.AutoPlay = AutoPlay == true?1:0;
            config.AlarmTurn = AlarmTurn == true?1:0;
            return config;
        }
    }
}();
Config.Init();