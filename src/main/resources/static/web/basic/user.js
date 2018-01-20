var currentIndex;
var User =function () {
    return {
        init:function () {
            $('#table').datagrid({
                url:'/api/v1/basic/userlist',
                method:'get',
                idField:'id',
                fit:true,
                fitColumns:true,
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:{userId:parent.User.GetUserInfo().id,username:'',departments:''},
                toolbar:[
                    {
                        text:'增加',
                        iconCls:'icon-add',
                        handler:function () {
                            parent.TramDalog.OpenIframe(650,405,'新增用户',"/basic/memberfrom?id=0",function (layerno,index) {
                                User.saveData(layerno,index);
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
                                parent.TramDalog.OpenIframe(650,405,'编辑用户',"/basic/memberfrom?id="+id,function (layerno,index) {
                                    User.saveData(layerno,index);
                                });
                            }
                        }
                    },'-',{
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            User.Remove();
                        }
                    },'-',{
                        text:'<input type="text" id="serachbox" class="serachbox" />'
                    },{
                        iconCls:'icon-search',
                        handler:function () {
                            User.Serach();
                        }
                    }

                ],
                columns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'username',
                        title: '用户名',
                        width: 70
                    },{
                        field: 'departmentname',
                        title: '所属机构',
                        width: 120
                    },
                    {
                        field: 'rolename',
                        title: '权限名称',
                        width: 120
                    },{
                        field: 'realname',
                        title: '联系人',
                        width: 120
                    },{
                        field: 'phone',
                        title: '联系电话',
                        width: 120
                    },{
                        field: 'status',
                        title: '状态',
                        width: 50,
                        formatter:function (value,row) {
                            if(value == 1)
                                return "正常"
                            else
                                return "异常";
                        }
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
        saveData:function (layerno,index) {
            currentIndex = layerno;
            var wd = parent.window.frames["layui-layer-iframe"+layerno];
            wd.UserFrom.saveData(parent,User);
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
                    User.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })
        },
        Search:function () {
            var query = $('#table').datagrid('options').queryParams;
            var key =$('#serachbox').val();
            if(key!='')
                query.username = key;
            //var departments = $('#')
            User.reLoad();
        }
    }
}();
User.init();