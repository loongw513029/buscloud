String.prototype.replaceAll  = function(s1,s2){
    return this.replace(new RegExp(s1,"gm"),s2);
}
var Basic =function () {
    return {
        init:function () {
            $('#table').treegrid({
                url:'/api/v1/operation/alarmtypelist',
                method:'get',          //请求方式
                idField:'id',           //定义标识树节点的键名字段
                treeField:'alarmname',       //定义树节点的字段
                fit:true,               //网格自动撑满
                fitColumns:true,        //设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
                pagination:true,
                pageNumber:1,
                singleSelect:false,
                pageSize:15,
                pageList:[15,20,50,100],
                queryParams:{type:0,keywords:''},
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
                        field: 'alarmname',
                        title: '报警类型',
                        width: 100
                    },{
                        field: 'level',
                        title: '报警等级',
                        width: 50
                    },
                    {
                        field: 'turn',
                        title: '开关',
                        width: 50,
                        formatter:function (value) {
                            return value?'已启用':'已关闭';
                        }
                    },{
                        field: 'ispush',
                        title: '推送',
                        width: 50,
                        formatter:function (value) {
                            return value?'已启用':'已关闭';
                        }
                    },{
                        field: 'threshold',
                        title: '阈值',
                        width: 50
                    },{
                        field: 'customid',
                        title: '自定义ID',
                        width: 100
                    }
                ]],
                loadFilter:function (data) {
                    if(data.success){
                        var liststr = JSON.stringify(data.result.items);
                        liststr=liststr.replaceAll("parentid","_parentId");
                        var result = JSON.parse(liststr);
                        return {
                            total:data.result.totalNum,
                            rows:result
                        };
                    }
                }
            });
        }
    }
}();
Basic.init();