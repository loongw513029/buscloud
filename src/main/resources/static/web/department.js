var dialog_index;
String.prototype.replaceAll  = function(s1,s2){
    return this.replace(new RegExp(s1,"gm"),s2);
}
var Department = function () {
    return{
        init:function () {
            $('#table').treegrid({
                url:'/api/v1/basic/departmentlist',
                method:'get',          //请求方式
                idField:'id',           //定义标识树节点的键名字段
                treeField:'departmentname',       //定义树节点的字段
                fit:true,               //网格自动撑满
                fitColumns:true,        //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:{userId:parent.User.GetUserInfo().id,title:''},
                toolbar:[
                    {
                        text:'增加',
                        iconCls:'icon-add',
                        handler:function () {
                            parent.TramDalog.OpenIframe(650,405,'新增机构',"/basic/departmentfrom?id=0",function (index,layerno) {
                                Department.saveData(layerno,index);
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
                                parent.TramDalog.OpenIframe(650,405,'编辑机构',"/basic/departmentfrom?id="+id,function (index,layerno) {
                                    Department.saveData(layerno,index);
                                });
                            }
                        }
                    },'-',{
                        text:'删除',
                        iconCls:'icon-remove',
                        handler:function () {
                            Department.removeData();
                        }
                    },'-',{
                        text:'<input type="text" id="serachbox" class="serachbox" />'
                    },{
                        iconCls:'icon-search',
                        handler:function () {
                            Department.reLoad();
                        }
                    }

                ],
                columns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'code',
                        title: '机构编码',
                        width: 70
                    },{
                        field: 'departmentname',
                        title: '机构名称',
                        width: 120
                    },
                    {
                        field: '_parentId',
                        title: '父级机构',
                        width: 120
                    },{
                        field: 'contactname',
                        title: '联系人',
                        width: 120
                    },{
                        field: 'contactphone',
                        title: '联系电话',
                        width: 120
                    },{
                        field: 'islookcan',
                        title: 'CAN',
                        width: 50,
                        formatter:function (value,row) {
                            if(value == 1)
                                return "有"
                            else
                                return "否";
                        }
                    },{
                        field: 'ishavevedio',
                        title: '视频',
                        width: 50,
                        formatter:function (value,row) {
                            if(value == 1)
                                return "有"
                            else
                                return "否";
                        }
                    }
                ]],
                loadFilter:function (data) {
                    if(data.success){
                        var liststr = JSON.stringify(data.result.items);
                        liststr=liststr.replaceAll("parentid","_parentId");
                        var result = JSON.parse(liststr);
                        for(var i =0;i<result.length;i++){
                            if(result[i]._parentId==0)
                                result[i]._parentId=null;
                        }
                        return {
                            total:data.result.totalNum,
                            rows:result
                        };
                    }
                }
            });
        },
        saveData:function (layero,index) {
            dialog_index = index;
            var wd = parent.window.frames["layui-layer-iframe"+index];
            wd.DepartmentFrom.saveData(parent,Department);
        },
        removeData:function () {
            var row = $('#table').treegrid('getSelections');
            if(row.length==0)
                parent.TramDalog.ErrorAlert("请选择数据！",true);
            var Ids = [];
            for(var i=0;i<row.length;i++){
                Ids.push(row[i].id);
            }
            parent.Http.Ajax({
                url:'/api/v1/basic/removedepartment?departmentIds='+Ids.join(','),
                type:'delete'
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    Department.reLoad();
                }else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })

        },
        reLoad:function () {
            var query = $('#table').treegrid('options');
            var key = $('#serachbox').val();
            if(key!='')
                query.queryParams.title = key;
            $('#table').treegrid('reload');            
        },
        closeWindow:function () {
            parent.layer.close(dialog_index);
        }
    }
}();
    Department.init();