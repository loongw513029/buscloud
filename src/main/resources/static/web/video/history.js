var tdeviceId =0;
var devicecode ='';
var VideoHistory = function () {
    return {
        accept:function (node,checked) {
            if(checked) {
                tdeviceId = node.id;
                devicecode = node.text;
                $('.alearySelectDevice').text('已选择设备：' + devicecode);
            }
        },
        init:function () {
            var node = parent.mainPlatform.getCheckedNodes();
            tdeviceId = node[0].id;
            devicecode = node[0].text;
            $('.alearySelectDevice').text('已选择设备：'+devicecode);
            parent.Http.Ajax({
                type:'get',
                url:'/api/v1/device/getChannels?deviceId='+tdeviceId
            },function (result) {
                var data = result.result;
                $("#slChannel").combobox({
                    valueField:'no',
                    textField:'channelname',
                    data:data,
                    prompt:'请选择通道'
                });
            },function (err) {console.log(err);});
            
            $('#sh').click(function () {
                var time = $('#time').datebox('getValue');
                var channel =  $("#slChannel").combobox('getValue');
                var catedoms = $('#vh-panel a');
                var category="";
                for(var i=0;i<catedoms.length;i++){
                    var cls = catedoms[i].className;
                    if(cls.indexOf('l-btn-selected')>0){
                        category = $(catedoms[i]).attr("category");
                        break;
                    }
                }
                if(time == undefined || time == ''){
                    parent.TramDalog.ErrorAlert('请选择日期',true);
                    return;
                }
                if(channel == '' || channel == undefined){
                    parent.TramDalog.ErrorAlert('请选择通道',true);
                    return;
                }
                if(category ==''){
                    parent.TramDalog.ErrorAlert('请选择查询类型',true);
                    return;
                }
                parent.TramDalog.Loading2();
                parent.Http.Ajax({
                    type:'get',
                    url:'/api/v1/video/getvideohistoryfilelist?deviceId='+tdeviceId+'&time='+time+'&channel='+channel+'&category='+category,
                },function (result) {
                    var data = result.result.msgInfo;
                    if(data.Success){
                        parent.TramDalog.CloseLayer();
                        VideoHistory.FilterData(JSON.parse(data.Msg));
                    }else{
                        parent.TramDalog.ErrorAlert(data.Msg,true);
                    }
                },function (err) {
                    parent.TramDalog.CloseLayer();
                })
            });
            window.onbeforeunload = function () {
                var ocx = document.getElementById('ocx');
                ocx.Stop();
            }
        },
        FilterData:function (data) {
            var d = {"total":data.length,"rows":data};
            $('#table').datagrid('loadData', d);
        },
        formatDate:function (val,row) {
            return val.split('T')[1].split('.')[0];
        },
        onTableDblClickRow:function (rowIndex,rowData) {
            VideoHistory.StartPlayHistory(rowData.Name);
        },
        StartPlayHistory:function (file) {
            var ocx =document.getElementById('ocx');
            ocx.Stop();
            try{
                ocx.StartRecord(parent.Main.getServerIP(),devicecode,'admin','admin',file);
            }
            catch (err){

            }
        }
    }
}();
VideoHistory.init();