var BuildFace = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/buildface/getbuildfacelist',
                method:'get',
                idField:'id',
                fit:true,
                fitColumns:true,
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:BuildFace.datagridQuery(),
                toolbar:[
                    {
                        text:'增加',
                        iconCls:'icon-add',
                        handler:function () {
                            parent.TramDalog.OpenIframe(650,405,'新增人员资料',"/buildface/from?id=0",function (layerno,index) {
                                BuildFace.saveData(layerno,index);
                            });
                        }
                    },'-',
                    {
                        text:'编辑',
                        iconCls:'icon-edit',
                        handler:function () {
                            var row = $('#table').treegrid('getSelections');
                            if(row.length>1||row.length==0)
                                parent.TramDalog.ErrorAlert("只能选择一条数据编辑",true);
                            else{
                                var id = row[0].id;
                                parent.TramDalog.OpenIframe(650,405,'编辑人员资料',"/buildface/from?id="+id,function (layerno,index) {
                                    BuildFace.saveData(layerno,index);
                                });
                            }
                        }
                    },'-',{
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            BuildFace.Remove();
                        }
                    },'-',{
                        text:'<input type="text" id="serachbox" class="serachbox" />'
                    },{
                        iconCls:'icon-search',
                        handler:function () {
                            BuildFace.Serach();
                        }
                    }

                ],
                columns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'name',
                        title: '姓名',
                        width: 70
                    },{
                        field: 'idCard',
                        title: '证件号码',
                        width: 120
                    },{
                        field: 'sex',
                        title: '性别',
                        width: 50,
                        formatter:function (value,row,index) {
                            return value==1?'男':'女';
                        }
                    },{
                        field: 'departmentname',
                        title: '所属公司',
                        width: 120
                    },
                    {
                        field: 'degree',
                        title: '学历',
                        width: 120
                    },{
                        field: 'homeAddress',
                        title: '地址',
                        width: 120
                    },{
                        field: 'createtime',
                        title: '视频',
                        width: 100
                    }
                ]],
                loadFilter:function (data) {
                    if(data.success){
                        return {
                            total:data.result.totalNum,
                            rows:data.result.items
                        };
                    }
                }
            });
        },
        datagridQuery:function () {
            return {
                userId:parent.User.GetUserInfo().id,
                keywords:'',
                departmentId:0,
                sex:-1
            };
        },
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.BuildFaceFrom.saveData(parent,BuildFace);
        },
        closeWindow:function () {
            parent.layer.close(currentIndex);
        },
        reLoad:function () {
            $('#table').datagrid('reload');
        },
        Remove:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length==0)
                parent.TramDalog.ErrorAlert("请选择数据！",true);
            var Ids = [];
            for(var i=0;i<row.length;i++){
                Ids.push(row[i].id);
            }
            parent.Http.Ajax({
                url:'/api/v1/basic/removeuser?userIds='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    BuildFace.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        Search:function () {
            var query = $('#table').datagrid('options').queryParams;
            var key =$('#serachbox').val();
            if(key!='')
                query.keywords = key;
            //var departments = $('#')
            BuildFace.reLoad();
        }
    }    
}();
BuildFace.init();
