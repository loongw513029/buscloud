var intervalArray;
var VideoPreview = function () {
    return {
        init:function () {
            $(window).resize(function () {
                VideoPreview.resize();
            });
            VideoPreview.resize();
            $('.video-item').click(function(){
                $(this).css('border-color','#ff0000').siblings().css('border-color','#dedede');
            })
            $('a.play').click(function () {

            })
            $('a.full').click(function () {
                var i = $(this).parent().parent().index();
                var items = $('.video-item');
                var vid=$(items[i]).attr("did"),channel = $(items[i]).attr("channel");
                $('.ocx').hide();
                VideoPreview.StopPreview(i);
                parent.Http.Ajax({
                    url: '/api/v1/home/getdeviceconfig?deviceId=' + vid,
                    type: 'get',
                    cache: false
                }, function (result) {
                    var data= result.result;
                    var type = data.hosttype, clientip = data.clientip, code = data.devicecode;
                    parent.TramDalog.fullScreenVideo(code,type,clientip,channel,function () {
                        $('.ocx').show();
                        VideoPreview.StartPreview(parent.Main.getServerIP(),type,code,clientip,channel,i);
                    });
                }, function (err) {
                });
            })
        },
        resize:function () {
            var pwindow = parent.Main.getContentHeight();
            var w = (pwindow.width-6)/3;
            var h = w*9/16;
            var w2 = (w+2)*2;
            $('.video-box').css('width',w2+'px');
            $('.ptzLayout').css('height',pwindow.height+'px');
            $('.video-item').css('width',w+'px').css('height',h+30+'px');
            $('.ocx').css('height',h+'px');
        },
        Preview:function (id,check,channel,node) {
            var items = $('.video-item');
            var num = 0;
            for(var i=0;i<items.length;i++) {
                var vid=$(items[i]).attr("did"),vchannel = $(items[i]).attr("channel");
                if(vid!=id&&vchannel!=channel) {
                    if (check) {
                        parent.Http.Ajax({
                            url: '/api/v1/home/getdeviceconfig?deviceId=' + id,
                            type: 'get',
                            cache: false
                            }, function (result) {
                                var data= result.result;
                                var type = data.hosttype, clientip = data.clientip, code = data.devicecode, time = data.videoplayyime, cl = channel;
                                VideoPreview.StartPreview(parent.Main.getServerIP(),type,code,clientip,cl,i,node.text,time);
                                $(items[i]).attr("did", id).attr("channel", channel);
                            var hd = $('.video-item .header:eq('+i+')');
                            hd.find("a:eq(0)").show();
                            hd.find("a:eq(1)").show();
                            }, function (err) {
                            });
                        num++;
                        break;
                    }
                }else{
                    if(!check&&vid==id&&vchannel==channel) {
                        VideoPreview.StopPreview(i,node.text);
                        $(items[i]).removeAttr("did").removeAttr("channel");
                        break;
                    }
                }
            }
            if(num==4){
                parent.mainPlatform.unCheckNode(node.target);
                parent.TramDalog.ErrorAlert('无播放位置使用',true);
            }
        },
        StartPreview:function (serverIp,type,code,clientip,channel,index,cname,time) {
            try{
                var ocx = document.getElementById('ocx'+index);
                ocx.StartPreview(serverIp,type,code,clientip,channel,1);
                VideoPreview.CalcTime(index,time*60,code,cname);
            }catch(err) {}
        },
        StopPreview:function (index,code,cname) {
            try{
                var ocx = document.getElementById('ocx'+index);
                ocx.Stop();
                $('#ocx'+index).css('background-color','#000000');
                var hd = $('.video-item .header:eq('+index+')');
                hd.find("a:eq(0)").hide();
                hd.find("a:eq(1)").hide();
                $('.header:eq('+index+')').find("span").text("")
                VideoPreview.RemoveInterval(index);
            }catch(err) {}
        },
        interObj:function (index,interval) {
            this.index = index;
            this.interval = interval;
        },
        CalcTime:function (index,ti,code,cname) {
            var interval = setInterval(function () {
                ti--;
                var t = ti == 0 ? 0 : ti;
                var minute = t < 60 ? 0 : parseInt(t / 60),
                    sec = t % 60;
                var time = (parseInt(minute) < 10 ? "0" + minute : minute) + ":" + (parseInt(sec) < 10 ? "0" + sec : sec);
                $('.header:eq('+index+')').find("span").text(code + " " + cname + " " + time);
                if (ti <= 0) {
                    VideoPreview.StopPreview(index);
                }
            }, 1000);
            intervalArray.push(new VideoPreview.interObj(index,interval));
        },
        RemoveInterval:function (index) {
            for(var i=0;i<intervalArray.length;i++){
                if(intervalArray[i].index == index) {
                    intervalArray.slice(intervalArray[i]);
                    clearInterval(intervalArray[i].interval);
                }
            }
        }
    }
}();
$(function () { VideoPreview.init(); })