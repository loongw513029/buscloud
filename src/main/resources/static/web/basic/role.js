var menuArr,oldArr;
var Role = function () {
    return {
        init:function () {
            parent.Http.Ajax({
                url: "/api/v1/basic/rolecombo",
                type: "get",
            }, function (result) {
                var data = result.result;
                $('#easyui-tree').tree({
                    lines: true,
                    animate: true,
                    data: data,
                    onCheck:function (node,checked) {
                        if(checked){
                            Role.getRole(node.id);
                        }else{
                            Role.restData();
                        }
                    },
                    onBeforeCheck:function (node,checked) {
                        if(checked) {
                            var tree = $('#easyui-tree').tree('getRoot');
                            $('#easyui-tree').tree('uncheck', tree.target);
                        }
                    },
                    onContextMenu:function (e,node) {
                        $('#parentNode').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        });
                    }
                });
                $('#parentId').combotree({
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            Role.initMenu();
        },
        initMenu:function () {
            parent.Http.Ajax({
                url: "/api/v1/basic/menulist",
                type: "get",
            }, function (result) {
                var data = result.result;
                menuArr = data;
                oldArr = data;
                $('#easyui-tree1').tree({
                    lines: true,
                    animate: true,
                    data: data,
                    onCheck:function (node,checked) {
                        if(checked){

                        }
                    }
                });
            })
        },
        getRole:function (id) {
            for(var i=0;i<menuArr.length;i++){
                menuArr[i].checked = false;
            }
            parent.Http.Ajax({
                type:'get',
                url:'/api/v1/basic/roleinfo?id='+id
            },function (result) {
                $('#addbtn').val('编辑');
                var data = result.result;
                $('#Id').val(data.id);
                $('#rolename').val(data.rolename);
                $('#remark').val(data.remark);
                $('#parentId').combotree('setValue',data.parentId);
                var arr = data.roleIds.split(',');
                for(var i=0;i<menuArr.length;i++){
                    if(menuArr[i].children.length==0){
                        if ($.inArray(menuArr[i].id + "", arr) > -1) {
                            menuArr[i].checked = true;
                        }
                    }
                    for(var j=0;j<menuArr[i].children.length;j++) {
                        if ($.inArray(menuArr[i].children[j].id + "", arr) > -1) {
                            menuArr[i].children[j].checked = true;
                        }
                    }
                }
                $('#easyui-tree1').tree({
                    data:menuArr
                });
            })
        },
        AddRole:function () {
            var nodes = $('#easyui-tree1').tree('getChecked');
            var nodes2 = $('#easyui-tree1').tree('getChecked','indeterminate');
            var roleIds = [];
            for(var i=0;i<nodes.length;i++){
                roleIds.push(nodes[i].id);
            }
            for(var j=0;j<nodes2.length;j++){
                roleIds.push(nodes2[j].id);
            }
            if(roleIds.length==0){
                parent.TramDalog.ErrorAlert('请授权!',true);
                return;
            }
            var obj = {
                id:$('#Id').val(),
                rolename:$('#rolename').val(),
                remark:$('#remark').val(),
                parentId:$('#parentId').combotree('getValue'),
                roleIds:roleIds.join(',')
            };
            parent.Http.Ajax({
                url:'/api/v1/basic/updaterole',
                type:'post',
                data:obj
            },function (result) {
                if(result.success){
                    parent.TramDalog.SuccessAlert(result.info,true);
                    Role.init();
                    Role.restData();
                }
                else{
                    parent.TramDalog.ErrorAlert(result.info,true);
                }
            })

        },
        restData:function () {
            $('#addbtn').val('新增');
            $('#Id').val(0);
            $('#rolename').val('');
            $('#remark').val('');
            $('#easyui-tree1').tree({
                data:oldArr
            });
        }
    }
}();
Role.init();