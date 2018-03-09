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
                singleSelect:true,
                pageNumber:1,
                pageSize:50,
                pageList:[50,100],
                updateUrl:'www.baidu.com',
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
                        width: 50,
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'id',
                                textField:'value',
                                data:[{"id":"1","value":"一级报警"},{"id":"2","value":"二级报警"},{"id":"3","value":"三级报警"}],
                                required:true
                            }
                        }
                    },
                    {
                        field: 'turn',
                        title: '开关',
                        width: 50,
                        formatter:function (value) {
                            return value?'已启用':'已关闭';
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'id',
                                textField:'value',
                                data:[{"id":"true","value":"打开"},{"id":"false","value":"关闭"}],
                                required:true
                            }
                        }
                    },{
                        field: 'ispush',
                        title: '推送',
                        width: 50,
                        formatter:function (value) {
                            return value?'已启用':'已关闭';
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'id',
                                textField:'value',
                                data:[{"id":"true","value":"打开"},{"id":"false","value":"关闭"}],
                                required:true
                            }
                        }
                    },{
                        field: 'threshold',
                        title: '阈值',
                        width: 50,
                        editor:{
                            type:'text'
                        }
                    },{
                        field: 'customid',
                        title: '自定义ID',
                        width: 100,
                        editor:{
                            type:'numberbox',
                            value:0
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
                },
                onAfterEdit: function (rowIndex, rowData, changes) {
                    editRow = undefined;
                    var row = rowIndex;
                    //像后台更新记录
                    parent.Http.Ajax({
                        url:'/api/v1/operation/updatealarmconfig',
                        type:'put',
                        data:row
                    },function (result) {
                        if(result.success){

                        }
                    })
                },
                onDblClickRow: function (rowIndex, rowData) {
                    if (editRow != undefined) {
                        $("#table").treegrid('endEdit', editRow.id);
                    }

                    if (editRow == undefined) {
                        $("#table").treegrid('beginEdit', rowIndex.id);
                        editRow = rowIndex;
                    }
                },
                onClickRow: function (rowIndex, rowData) {
                    if (editRow != undefined) {
                        $("#table").treegrid('endEdit', editRow.id);

                    }

                }
            });
        }
    }
}();
var editRow = undefined;
Basic.init();