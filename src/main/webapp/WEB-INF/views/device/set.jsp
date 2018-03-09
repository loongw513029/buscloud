<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="/fonts/iconfont.css">
    <link rel="stylesheet" href="/css/device-set.css" />
</head>
<body>
<a class="easyui-linkbutton" href="/device/inspect">返回列表</a>
<div class="easyui-tabs set-tab" id="set" data-options="tabWidth:80,tabHeight:30" buttons="#dlg-buttons">
    <div title="系统设置" style="padding:5px;position:relative">
        <section>录像储存</section>
        <div class="item2 fl w100">
            <label>所有录像</label><label>分包间隔</label>
            <select  class="easyui-combobox" name="dept" style="width:80px;">
                <option value="1">1分钟</option>
                <option value="5">5分钟</option>
                <option value="10">10分钟</option>
                <option value="15">15分钟</option>
            </select>
        </div>
        <div class="item2 fl w100">
            <label>手动录像</label><input type="checkbox" /><label>限制时长</label>
            <select  class="easyui-combobox" name="dept" style="width:80px;">
                <option value="30">30分钟</option>
                <option value="60">60分钟</option>
                <option value="120">120分钟</option>
                <option value="180">180分钟</option>
                <option value="360">360分钟</option>
                <option value="720">720分钟</option>
                <option value="1440">1440分钟</option>
            </select>
        </div>
        <div class="item2 fl w100">
            <label>报警录像</label><label>预录时长</label>
            <select  class="easyui-combobox" name="dept" style="width:80px;">
                <option value="10">10分钟</option>
                <option value="15">15分钟</option>
                <option value="20">20分钟</option>
                <option value="40">40分钟</option>
            </select>
        </div>
        <div class="item2 fl w100">
            <label style="width:48px">&nbsp;</label><label>延续时长</label>
            <select  class="easyui-combobox" name="dept" style="width:80px;">
                <option value="1">1分钟</option>
                <option value="3">3分钟</option>
                <option value="5">5分钟</option>
                <option value="10">10分钟</option>
                <option value="15">15分钟</option>
            </select>
        </div>
        <div class="item2 fl w100">
            <label>存储策略</label><label>每盘保留</label>
            <select  class="easyui-combobox" name="dept" style="width:80px;">
                <option value="5">5GB</option>
                <option value="10">10GB</option>
                <option value="15">15GB</option>
                <option value="20">20GB</option>
                <option value="25">25GB</option>
                <option value="30">30GB</option>
                <option value="35">35GB</option>
                <option value="40">40GB</option>
            </select>
        </div>
        <div class="item2 fl w100">
            <label>覆盖策略</label><input type="checkbox" />
            <label>磁盘空间不足时自动覆盖</label>
        </div>
        <div class="item2 fl w100">
            <label style="width:48px">&nbsp;</label><input type="checkbox" />
            <label>超过保留期限强制删除</label>
        </div>

        <div class="storage">
            <div class=" easyui-panel" title="存储方案" tools='#storage-buttons'>
                <table class="s-table" colspan="0" cellspan="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>名称</th>
                        <th>容量</th>
                        <th>通道</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>01</td>
                        <td>H:\</td>
                        <td>300GB</td>
                        <td>01-16</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="sw" class="easyui-dialog" title="选择项目" data-options="closed:true" style="width:200px;height:400px;padding:5px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" style="text-align:center;height:32px">
                    <a class="easyui-linkbutton" href="javascript:void(0)" onclick="javascript:alert('ok')" style="width:80px">全部选中</a>
                    <a class="easyui-linkbutton" href="javascript:void(0)" onclick="javascript:alert('cancel')" style="width:80px">全部清除</a>
                </div>
                <div data-options="region:'center'" style="padding:5px;">
                    <ul class="selchannel">
                        <li><input type="checkbox"/>01:通道01</li>
                        <li><input type="checkbox"/>02:通道02</li>
                        <li><input type="checkbox"/>03:通道03</li>
                        <li><input type="checkbox"/>04:通道04</li>
                        <li><input type="checkbox"/>05:通道05</li>
                        <li><input type="checkbox"/>06:通道06</li>
                        <li><input type="checkbox"/>07:通道07</li>
                        <li><input type="checkbox"/>08:通道08</li>
                        <li><input type="checkbox"/>09:通道09</li>
                        <li><input type="checkbox"/>10:通道10</li>
                        <li><input type="checkbox"/>11:通道11</li>
                        <li><input type="checkbox"/>12:通道12</li>
                        <li><input type="checkbox"/>13:通道13</li>
                        <li><input type="checkbox"/>14:通道14</li>
                        <li><input type="checkbox"/>15:通道15</li>
                        <li><input type="checkbox"/>16:通道16</li>
                    </ul>
                </div>
                <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;height:32px">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:alert('ok')" style="width:80px">确定</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:alert('cancel')" style="width:80px">取消</a>
                </div>
            </div>
        </div>
    </div>
    <div title="通道设置" style="padding:5px">
        <div class="easyui-tabs set-channel" data-options="tabPosition:'left'">
            <div title="01:通道01" style="min-height:628px;padding-left:10px">
                <div class="item2 fl w100 h30">
                    <input type="checkbox" /><label>启用</label> <label>地址</label><input type="text" /> <label>类型</label> <select class="easyui-combobox"><option value="HV-1">HV-1</option><option value="HV-2">HV-2</option><option value="HV-3">HV-3</option><option value="HV-4">HV-4</option><option value="HV-5">HV-5</option><option value="HV-6">HV-6</option></select>
                </div>
                <div class="item2 fl w100 h30">
                    <input type="checkbox" /><label>预览</label> <label>名称</label><input type="text" /> <label>通道</label> <select class="easyui-combobox"><option value="1"> &nbsp;&nbsp;1   </option></select>
                </div>
                <div class="item2 fl w100 h30">
                    <input type="checkbox" /><label>全屏</label>
                </div>
                <div class="item2 fl w100 h30">
                    <input type="checkbox" /><label>主码流</label>
                </div>
                <div class="item2 fl w100 h30 line">
                    <label>录像保存</label><select class="easyui-combobox">
                    <option value="0">0天</option>
                    <option value="5">5天</option>
                    <option value="10">10天</option>
                    <option value="15">15天</option>
                    <option value="20">20天</option>
                    <option value="25">25天</option>
                    <option value="30" selected="selected">30天</option>
                    <option value="35">35天</option>
                </select>
                    <label>信号丢失</label><input type="checkbox" /><label>检测</label>
                </div>
                <div class="item2 fl w100 h30">
                    <label>主码流</label> &nbsp;&nbsp;<label style="width:47px">分辨率</label><select class="easyui-combobox" style="width:80px">
                    <option value="1080P">1080p</option>
                    <option value="960p">960p</option>
                    <option value="720p">720p</option>
                    <option value="D1">D1</option>
                </select>
                    &nbsp;&nbsp;
                    <label>帧率</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="5">5 帧/秒</option>
                        <option value="10">10 帧/秒</option>
                        <option value="15">15 帧/秒</option>
                        <option value="20">20 帧/秒</option>
                        <option value="25" selected="selected">25 帧/秒</option>
                        <option value="30">30 帧/秒</option>
                    </section>
                    &nbsp;&nbsp;
                    <label>I帧间隔</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="10">10帧</option>
                        <option value="20">20帧</option>
                        <option value="30">30帧</option>
                        <option value="40">40帧</option>
                        <option value="50">50帧</option>
                        <option value="60">60帧</option>
                        <option value="70">70帧</option>
                        <option value="80">80帧</option>
                        <option value="90">90帧</option>
                        <option value="100">100帧</option>
                        <option value="110">110帧</option>
                        <option value="120">120帧</option>
                        <option value="130">130帧</option>
                        <option value="140">140帧</option>
                        <option value="150">150帧</option>
                    </section>
                </div>
                <div class="item2 fl w100 h30 line" >
                    <label style="width:35px">&nbsp;</label> &nbsp;&nbsp;<label>码率模式</label><select class="easyui-combobox" style="width:80px">
                    <option value="0">可变</option>
                    <option value="1">固定</option>
                </select>
                    &nbsp;&nbsp;
                    <label>码率</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="225">225 兆/小时</option>
                        <option value="450">450 兆/小时</option>
                        <option value="900">900 兆/小时</option>
                        <option value="1350">1350 兆/小时</option>
                        <option value="1800" selected="selected">1800 兆/小时</option>
                        <option value="2250">2250 兆/小时</option>
                        <option value="2700">2700 兆/小时</option>
                    </section>
                </div>
                <div class="item2 fl w100 h30">
                    <label>次码流</label> &nbsp;&nbsp;<label style="width:47px">分辨率</label><select class="easyui-combobox" style="width:80px">
                    <option value="1080P">1080p</option>
                    <option value="960p">960p</option>
                    <option value="720p">720p</option>
                    <option value="D1">D1</option>
                </select>
                    &nbsp;&nbsp;
                    <label>帧率</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="5">5 帧/秒</option>
                        <option value="10">10 帧/秒</option>
                        <option value="15">15 帧/秒</option>
                        <option value="20">20 帧/秒</option>
                        <option value="25" selected="selected">25 帧/秒</option>
                        <option value="30">30 帧/秒</option>
                    </section>
                    &nbsp;&nbsp;
                    <label>I帧间隔</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="10">10帧</option>
                        <option value="20">20帧</option>
                        <option value="30">30帧</option>
                        <option value="40">40帧</option>
                        <option value="50">50帧</option>
                        <option value="60">60帧</option>
                        <option value="70">70帧</option>
                        <option value="80">80帧</option>
                        <option value="90">90帧</option>
                        <option value="100">100帧</option>
                        <option value="110">110帧</option>
                        <option value="120">120帧</option>
                        <option value="130">130帧</option>
                        <option value="140">140帧</option>
                        <option value="150">150帧</option>
                    </section>
                </div>
                <div class="item2 fl w100 h30">
                    <label style="width:35px">&nbsp;</label> &nbsp;&nbsp;<label>码率模式</label><select class="easyui-combobox" style="width:80px">
                    <option value="0">可变</option>
                    <option value="1">固定</option>
                </select>
                    &nbsp;&nbsp;
                    <label>码率</label>
                    <section class="easyui-combobox" style="width:80px">
                        <option value="225">225 兆/小时</option>
                        <option value="450">450 兆/小时</option>
                        <option value="900">900 兆/小时</option>
                        <option value="1350">1350 兆/小时</option>
                        <option value="1800" selected="selected">1800 兆/小时</option>
                        <option value="2250">2250 兆/小时</option>
                        <option value="2700">2700 兆/小时</option>
                    </section>
                </div>
            </div>
            <div title="02:通道02"></div>
            <div title="03:通道03"></div>
            <div title="04:通道04"></div>
            <div title="05:通道05"></div>
            <div title="06:通道06"></div>
            <div title="07:通道07"></div>
            <div title="08:通道08"></div>
            <div title="09:通道09"></div>
            <div title="10:通道10"></div>
            <div title="11:通道11"></div>
            <div title="12:通道12"></div>
            <div title="13:通道13"></div>
            <div title="14:通道14"></div>
            <div title="15:通道15"></div>
            <div title="16:通道16"></div>
        </div>

    </div>
    <div title="扩展设置" style="padding:5px">
        <div class="item2 fl h30 w100">
            <label class="w50">基本信息</label>
            <label style="width:80px;text-align:right">编号</label>
            <input type="text" />
        </div>
        <div class="item2 fl h30 w100">
            <label  class="w50">GPS</label>
            <label style="width:80px;text-align:right">发送频率</label>
            <input type="text" class="w80" value="5"/><label>秒</label>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label class="w50">重启日程</label>
            <label style="width:60px;text-align:right">时间</label>
            <input type="text" class="w80" value="01:00:00"/>
            <label style="width:80px;text-align:right">周期</label>
            <input type="text" class="w80" value="0"/>
        </div>
        <div class="item2 fl h30 w100">
            <label  class="w50">服务器</label>
            <label style="width:80px;text-align:right">IIS</label>
            <input type="text" value="192.168.0.154"/>
        </div>
        <div class="item2 fl h30 w100">
            <label  class="w50">*</label>
            <label style="width:80px;text-align:right">转发地址</label>
            <input type="text" value="192.168.0.154"/>
            <input type="checkbox" /><label>反向</label>
        </div>
        <div class="item2 fl h30 w100">
            <label  class="w50">串口信息</label>
            <label style="width:80px;text-align:right"><input type="checkbox" style="float:none"/>CAN</label>
            <input type="text" class="w50" value="COM1"/>
            <label class="w50 tr"><input type="checkbox" style="float:none"/>调度</label>
            <input type="text" class="w50" value="COM1"/>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label  class="w50">360全景</label>
            <label style="width:60px;text-align:right">串口</label>
            <input type="text" class="w50" value="COM1"/>
            <label  class="w50 tr">模式</label>
            <select class="easyui-combobox"><option value="A">模式A</option><option value="B">模式B</option></select>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label  class="w50">防撞预警</label>
            <label style="width:60px;text-align:right"><input type="checkbox" style="float:none"/>录像</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label  class="w50 tr">串口</label>
            <input type="text" class="w50" value="COM1"/>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label  class="w50">紧急按钮</label>
            <label style="width:60px;text-align:right"><input type="checkbox" style="float:none"/>录像</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label  class="w50 tr">GPIO70</label>
            <select class="easyui-combobox"><option value="0">低电平报警</option><option value="1">高电平报警</option></select>
            <label  class="w50 tr">GPIO72</label>
            <select class="easyui-combobox"><option value="0">低电平报警</option><option value="1">高电平报警</option></select>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label  class="w50">行为识别</label>
            <label style="width:60px;text-align:right"><input type="checkbox" style="float:none"/>录像</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label  class="w50 tr">串口</label>
            <input type="text" class="w50" value="COM1"/>
            <label class="w50">正面拍照</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label style="width:80px;text-align:right"><input type="checkbox" style="float:none"/>调试模式</label>
        </div>
        <div class="item2 fl h30 w100">
            <input type="checkbox" />
            <label  class="w50">ADAS</label>
            <label style="width:60px;text-align:right"><input type="checkbox" style="float:none"/>录像</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label  class="w50 tr">串口</label>
            <input type="text" class="w50" value="COM1"/>
            <label  class="w50 tr">模式</label>
            <select class="easyui-combobox"><option value="A">模式A</option><option value="B">模式B</option></select>
            <label style="width:80px;text-align:right"><input type="checkbox" style="float:none"/>调试模式</label>
            <label class="w50">正面通道拍照</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label class="w50">司机通道拍照</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
            <label class="w50">路面通道拍照</label>
            <select class="easyui-combobox">
                <option value="0">通道1</option>
                <option value="1">通道2</option>
                <option value="2">通道3</option>
                <option value="3">通道4</option>
                <option value="4">通道5</option>
                <option value="5">通道6</option>
                <option value="6">通道7</option>
                <option value="7">通道8</option>
                <option value="8">通道9</option>
                <option value="9">通道10</option>
                <option value="10">通道11</option>
                <option value="11">通道12</option>
                <option value="12">通道13</option>
                <option value="13">通道14</option>
                <option value="14">通道15</option>
                <option value="15">通道16</option>
            </select>
        </div>
    </div>
</div>
<div id="dlg-buttons" style="background-color: #e3e3e3;padding: 5px;border: solid 1px #bfbfbf;border-top: 0px;">
    <table cellpadding="0" cellspacing="0" style="width:100%" border="0">
        <tr>
            <td style="text-align:left">
                <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:alert('save')">保存</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close')">重置</a>
            </td>
        </tr>
    </table>
</div>
<div id="storage-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%" border="0">
        <tr>
            <td style="text-align:right">
                <a href="#" class="easyui-linkbutton" onclick="$('#sw').window('open')">分配通道</a>
                <a href="#" class="easyui-linkbutton">自动分配</a>
            </td>
        </tr>
    </table>
</div>
</div>

<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/web/device/set.js"></script>
</body>
</html>