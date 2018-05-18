var Inspect = function () {
    return {
        init:function () {
            $('#table').datagrid({
                url: '/api/v1/device/inspectlist',
                method: 'get',
                idField: 'id',
                fit: true,
                pagination: true,
                pageNumber: 1,
                singleSelect: false,
                pageSize: 15,
                pageList: [15, 20, 50, 100],
                queryParams: Inspect.datagridQuery(),
                toolbar: "#toolbar",
                frozenColumns:[[
                    {
                        field: 'id',
                        checkbox: 'true',
                        width: 30
                    },{
                        field: 'deviceCode',
                        title: '设备编码',
                        width: 100,
                        align: 'center'
                    },
                    {
                        field: 'linename',
                        title: '线路名称',
                        width: 50,
                        align: 'center'
                    }, {
                        field: 'departmentname',
                        title: '机构名称',
                        width: 120,
                        align: 'center'
                    },{
                        field:'S',
                        title:'操作',
                        width:60,
                        align:'center',
                        formatter:function (value,row) {
                            return "<a class='easyui-linkbutton' href='/device/set?id="+row.deviceid+"'>主机设置</a>";
                        }
                    }
                ]],
                columns: [[{
                    field: 'videotape',title: '录像状态',width: 70,align: 'center',
                    formatter:function (value,row,index) {
                        return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                    }
                }, {
                    field: 'video',title: '视频状态',width: 70,align: 'center',
                    formatter:function (value,row,index) {
                        return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                    }
                }, {
                    field: 'harddisk',title: '硬盘状态',width: 70,align: 'center',
                    formatter:function (value,row) {
                        return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                    }
                }, {
                    field: 'sdcard',title: 'SD卡状态',width: 70,align: 'center',
                    formatter:function (value,row) {
                        return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                    }
                }, {
                        field: 'onlineState',title: '设备状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"在线":"<font color='red'>离线</font>":"--";
                        }
                    },
                    {
                        field: 'cpuuserate',title: 'CPU使用率',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"%":"--";
                        }
                    }, {
                        field: 'cputemp',title: 'CPU温度',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"℃":"--";
                        }
                    }, {
                        field: 'mermoryuserate',title: 'CPU温度',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"℃":"--";
                        }
                    },
                    {
                        field: 'cputemp',title: 'CPU温度',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"℃":"--";
                        }
                    }, {
                        field: 'surplusdisksize',title: '硬盘剩余',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"G":"--";
                        }
                    }, {
                        field: 'surplussdcardsize',title: 'SD卡剩余',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value==null?"--":value+"G":"--";
                        }
                    },
                    {
                        field: 'gpsstate',title: 'Gps状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'canstate',title: 'Can状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'internetstate',title: '网络状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?"正常":"<font color='red'>异常</font>";
                        }
                    },
                    {
                        field: 'gpssignelstate',title: 'Gps信号状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'simbalance',title: 'SIM余额',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?"￥"+(value==undefined?"--":value):"--";
                        }
                    },
                    {
                        field: 'gpsinspectstate',title: 'Gps巡检状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'gcaninspectstate',title: 'Can巡检状态',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'behaviorinspectstate',title: '行为识别',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'adasinspectstate',title: 'ADAS',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    },
                    {
                        field: 'radarinspectstate',title: '雷达',width: 70,align: 'center',
                        formatter:function (value,row) {
                            return row.onlineState?value?"正常":"<font color='red'>异常</font>":"--";
                        }
                    }
                ]],
                loadFilter: function (data) {
                    if (data.success) {
                        return {
                            total: data.result.totalNum,
                            rows: data.result.items
                        };
                    }
                },
                
            });
            parent.Http.Ajax({
                url:'/api/v1/basic/departmentcombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#departmentId').combotree({
                    prompt:'选择机构',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            parent.Http.Ajax({
                url:'/api/v1/operation/linecombo?userid='+parent.User.GetUserInfo().id,
                type:'get'
            },function (result) {
                var data = result.result;
                $('#lineId').combotree({
                    prompt:'选择线路',
                    data:data,
                    valueField:'id',
                    textField:'text'
                });
            });
            $('#txtkey').searchbox({
                prompt:'车辆自编码',
                searcher:function (value,name) {
                    Inspect.Search(value);
                }
            });
        },
        datagridQuery:function () {
            return {
                userid:parent.User.GetUserInfo().id,
                departmentid:0,
                lineid:0,
                type:0,
                keywords:''
            }
        },
        Search:function (value) {
            var query = $('#table').datagrid('options').queryParams;
            var dpid = $('#departmentId').combotree('getValue');
            var lid = $('#lineId').combotree('getValue');
            query.departmentid = dpid==''?0:dpid;
            query.lineid = lid==''?0:lid;
            query.keywords = value;
            $('#table').datagrid('reload');
        }
    }
}();
Inspect.init();