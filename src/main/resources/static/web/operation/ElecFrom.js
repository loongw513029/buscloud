var map = null;
var ElecFrom = function () {
    return {
        init:function () {
            map = new AMap.Map('map',{
                resizeEnable: true,
                center: [113.325723,23.10328],
                zoom: 13
            });
            map.on('click', function(e) {
                $('#lng').val(e.lnglat.getLng());
                $('#lat').val(e.lnglat.getLat());
            });
            $('#radius').blur(function () {
                var lng = $('#lng').val(),
                    lat = $('#lat').val();
                if(lng!=''&&lat!='') {
                    map.clearMap();
                    var circle = new AMap.Circle({
                        center: new AMap.LngLat(lng, lat),// 圆心位置
                        radius: $('#radius').val(), //半径
                        strokeColor: "#F33", //线颜色
                        strokeOpacity: 1, //线透明度
                        strokeWeight: 1, //线粗细度
                        fillColor: "#ee2200", //填充颜色
                        fillOpacity: 0.35//填充透明度
                    });
                    circle.setMap(map);
                }
            });
            var lng = $('#lng').val(),
                lat = $('#lat').val();
            if(lng!=''&&lat!=''){
                map.setCenter(new AMap.LngLat(lng,lat));
                var circle = new AMap.Circle({
                    center: new AMap.LngLat(lng, lat),// 圆心位置
                    radius: $('#radius').val(), //半径
                    strokeColor: "#F33", //线颜色
                    strokeOpacity: 1, //线透明度
                    strokeWeight: 1, //线粗细度
                    fillColor: "#ee2200", //填充颜色
                    fillOpacity: 0.35//填充透明度
                });
                circle.setMap(map);
            }
        },
        saveData:function (parenta,Line) {
            var lng = $('#lng').val();
            var lat = $('#lat').val();
            var id = $('#Id').val();
            var radius = $('#radius').val();
            var inTrun = $('#inTrun').prop('checked');
            var outTrun = $('#outTrun').prop('checked');

            if(lng == ''||lat == ''){
                parenta.TramDalog.ErrorAlert('请选择中心点',true);
                return;
            }
            if(radius == ''){
                parenta.TramDalog.ErrorAlert('请输入半径',true);
                return;
            }
            var obj = {
                id:id,
                lng:lng,
                lat:lat,
                radius:radius,
                inTrun:inTrun,
                outTrun:outTrun
            };
            parent.Http.Ajax({
                type:'post',
                data:obj,
                url:'/api/v1/operation/saveElecFence'
            },function (result) {
                if(!result.success)
                    parenta.TramDalog.ErrorAlert(result.info,true);
                else{
                    parenta.TramDalog.SuccessAlert(result.info, true);
                    Line.closeWindow();
                    Line.onLoad();
                }
            })
        }
    }
}();
ElecFrom.init();