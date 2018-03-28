var AlarmStatics = function () {
    return {
        init:function () {
            AlarmStatics.initComboTree();
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
                            $('#type2').combotree({data:result.result,multiple:true});
                        });
                    }
                });
            });
            $('#type2').combotree({data:[{id:0,text:'请选择二级分类'}]});
            $('#txtkey').searchbox({
                prompt:'设备编码或车牌号码',
                searcher:function (value,name) {

                }
            });
        }
    }
}();
AlarmStatics.init();