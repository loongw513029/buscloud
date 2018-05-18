var BusFrom = function () {
    return {
        init:function () {
            BusFrom.initComboTree();
            BusFrom.initVideoComboTree(channel);
            BusFrom.loadData();
            $('#videosupport').change(function () {
                BusFrom.videoChange();
            });
            $('#videochannel').keyup(function () {
                BusFrom.videoChange();
            });
        },
        videoChange:function () {
            var ck = $('#videosupport').prop("checked");
            var channel2 = $('#videochannel').val();
            if(ck&&channel2>0){
                BusFrom.initVideoComboTree(channel2);
            }else{
                BusFrom.initVideoComboTree(0);
            }
        },
        initComboTree:function () {
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentid').combotree({
                    prompt:'选择机构',
                    data:data,
                    valueField:'id',
                    textField:'text',
                    onSelect:function (node) {
                        if(node.id != 0){
                            parent.Http.Ajax({
                                url:'/api/v1/operation/getdrivercombo?departmentid='+node.id,
                                type:'get'
                            },function (result) {
                                $('#driverid').combotree({data:result.result});
                            });
                        }
                    }
                });
                var departmentId = $('#departmentid').val();
                $('#departmentid').combotree('setValue',departmentId);
            });
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineid').combotree({
                    prompt:'选择线路',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
                var lineid = $('#lineid').val();
                $('#lineid').combotree('setValue',lineid);
            });
            $('#driverid').combotree({data:[{id:0,text:'-请先选择机构-'}]});
            $('#hosttypesoft').combotree({data:[{id:1,text:'DVR'},{id:0,text:'NVR'}]});
            var soft = $('#hdHostTypeSoft').val();
            $('#hosttypesoft').combotree('setValue',soft);
            // $('#bustype').combotree({data:[{id:0,text:'-请选择车辆类型-'},{id:1,text:'传统车辆'},{id:2,text:'油电混合车'},{id:3,text:'气电混合车'},{id:4,text:'纯电动车'},{id:5,text:'双路BMS纯电车辆'}]});
            // var devicetype = $('#hdBusType').val();
            // $('#bustype').combotree('setValue',parseInt(devicetype));
        },
        saveData:function (parenta,Line) {
            var busnumber = $('#busnumber').val();
            var busframenumber = $('#busframenumber').val();
            var id = $('#Id').val();
            var busid = $('#busId').val();
            var bustype = $('#bustype').combobox('getValue');
            var departmentid = $('#departmentid').combotree('getValue');
            var lineid = $('#lineid').combotree('getValue');
            var driverid = $('#driverid').combotree('getValue');
            var devicecode = $('#devicecode').val();
            var devicename = $('#devicename').val();
            var clientip = $('#clientip').val();
            var devicemode = $('#devicemode').val();
            var disksize = $('#disksize').val();
            var sdcardsize = $('#sdcardsize').val();
            var videosupport = $('#videosupport').prop("checked");
            var videochannel = $('#videochannel').val();
            var aerialview = $('#aerialview').prop("checked");
            var can = $('#can').prop("checked");
            var radar = $('#radar').prop("checked");
            var passengerflow =$('#passengerflow').prop('checked');
            var supportbehavior = $('#supportbehavior').prop("checked");
            var supportadas = $('#supportadas').prop("checked");
            var aerialchannel = $('#aerialchannel').combotree('getValue');
            var dchannel = $('#dchannel').combotree('getValue');
            var carriagechannel = $('#carriagechannel').combotree('getValue');
            var hosttypesoft = $('#hosttypesoft').combotree('getValue');
            var channellist = [];
            for(var i=0;i<videochannel;i++){
                var name = $('#text'+(i+1)).val();
                var no = $('#text'+(i+1)).attr("no");
                var ck = $('#ck'+(i+1)).prop("checked");
                channellist.push(new BusFrom.ChannelListObj(no,name,ck));
            }
            if(bustype == 0){
                parenta.TramDalog.ErrorAlert('请选择设备类型',true);
                return;
            }
            if(departmentid == 0){
                parenta.TramDalog.ErrorAlert('请选择机构',true);
                return;
            }
            if(lineid == 0){
                parenta.TramDalog.ErrorAlert('请选择线路',true);
                return;
            }
            if(driverid == 0){
                parenta.TramDalog.ErrorAlert('请选择司机',true);
                return;
            }
            if(devicecode == ''){
                parenta.TramDalog.ErrorAlert('请输入设备编码',true);
                return;
            }
            if(devicename == ''){
                parenta.TramDalog.ErrorAlert('请输入设备条码',true);
                return;
            }
            if(!videosupport){
                channellist = [];
            }
            var obj = {
                id:id,
                busid:busid,
                busnumber:busnumber,
                busframenumber:busframenumber,
                bustype:bustype,
                departmentid:departmentid,
                lineid:lineid,
                driverid:driverid,
                devicecode:devicecode,
                devicename:devicename,
                clientip:clientip,
                devicemode:devicemode,
                disksize:disksize,
                sdcardsize:sdcardsize,
                videosupport:videosupport,
                videochannel:videochannel,
                aerialview:aerialview,
                can:can,
                radar:radar,
                supportbehavior:supportbehavior,
                supportadas:supportadas,
                passengerflow:passengerflow,
                aerialchannel:aerialchannel,
                dchannel:dchannel,
                carriagechannel:carriagechannel,
                channellist:JSON.stringify(channellist),
                hosttypesoft:hosttypesoft
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/operation/savebus'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    Line.closeWindow();
                    Line.reLoad();
                }
            })
        },
        ChannelObj:function (id,text) {
            this.id = id;
            this.text = text;
        },
        ChannelListObj:function (no,channelname,supportptz) {
            this.no = no;
            this.channelname = channelname;
            this.supportptz = supportptz;
        },
        initVideoComboTree:function (c) {
            var arr = [],arr1=[],arr2=[];
            if(c>0) {
                for (var i = 0; i < c; i++) {
                    arr.push(new BusFrom.ChannelObj(i, '通道' + (i + 1)));
                    arr1.push(new BusFrom.ChannelObj(i, '通道' + (i + 1)));
                    arr2.push(new BusFrom.ChannelObj(i, '通道' + (i + 1)));
                }
            }else{
                arr.push(new BusFrom.ChannelObj(0,"-请打开视频设置-"));
                arr1.push(new BusFrom.ChannelObj(0,"-请打开视频设置-"));
                arr2.push(new BusFrom.ChannelObj(0,"-请打开视频设置-"));
            }
            $('#aerialchannel').combotree({data:arr});
            $('#dchannel').combotree({data:arr1});
            $('#carriagechannel').combotree({data:arr2});
            BusFrom.initChannelLayout(c);
        },
        initChannelLayout:function (c) {
            if(c==0)
                $('.customChannel').hide();
            else
                $('.customChannel').show();
            var html = "";
            for(var i=1;i<=c;i++){
                if(i%4==1){
                    html+="<tr>";
                }
                html+="<td class='kv-label' width='12.5%'>通道"+i+"</td><td class='kv-content' width='12.5%'><input type='text' style='width:60%' placeholder='自定义名称' id='text"+i+"' no='"+(i-1)+"'/><input type='checkbox' id='ck"+i+"'>云台</td>";
                if(i%4==0){
                    html+="</tr>";
                }
            }
            $('#channelLayout').html(html);
        },
        loadData:function () {
            $('#aerialchannel').combotree('setValue',$('#hdaerialchannel').val());
            $('#dchannel').combotree('setValue',$('#hddchannel').val());
            $('#carriagechannel').combotree('setValue',$('#hdcarriagechannel').val());
            var channellist;
            if(channelliststr!='')
                channellist = JSON.parse(channelliststr);
            else
                channellist = [];
            for(var i =0;i<channellist.length;i++){
                $('#text'+(i+1)).val(channellist[i].channelname);
                $('#text'+(i+1)).attr("no",i);
                if(channellist[i].supportptz)
                    $('#ck'+(i+1)).prop("checked",true);
                else
                    $('#ck'+(i+1)).prop("checked",false);
            }
        }
    }
}();
BusFrom.init();