<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>工作台</title>
    <link href="/css/base.css" rel="stylesheet">
    <link href="/fonts/iconfont.css" rel="stylesheet">
    <link rel="stylesheet" href="/easyui/uimaker/easyui.css">
    <link rel="stylesheet" href="/css/workbench.css">
</head>
<body>
<div class="container">
    <div id="hd">

    </div>

    <div id="bd">
        <div class="bd-content">
            <div class="right-zone">
                <div class="inform item-box">
                    <div class="inform-hd">
                        <label>报警通知</label>
                        <a href="javascript:;">更多<span>&gt;</span></a>
                    </div>
                    <ul>
                        <c:forEach items="${obj.alarmList}" var="item">
                            <li>
                                <span></span>
                                <a href="javascript:;" class="ellipsis" onclick="Welcome.ShowAlarm(${item.id})">${item.alarmName}<i></i></a>
                                <label>${item.updateTime}</label>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="price item-box">
                    <div class="inform-hd">
                        <label>维保信息</label>
                        <a href="javascript:;">更多<span>&gt;</span></a>
                    </div>
                    <ul>
                        <c:forEach items="${mncs}" var="item">
                            <li>
                                <span></span>
                                <a href="javascript:;" class="ellipsis">车辆：${item.deviceCode}&nbsp;&nbsp;里程：${item.mtMileage}</a>
                                <label>${item.mtDate}</label>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <%--<div class="attached item-box">--%>
                    <%--<div class="inform-hd">--%>
                        <%--<label>常用附件下载</label>--%>
                        <%--<a href="javascript:;">更多<span>&gt;</span></a>--%>
                    <%--</div>--%>
                    <%--<div class="attached-tab">--%>
                        <%--<a href="javascript:;" class="current item-left" attached="public-attached">公开附件</a>--%>
                        <%--<a href="javascript:;" class="item-right" attached="inner-attached">内部附件</a>--%>
                    <%--</div>--%>
                    <%--<ul class="public-attached">--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">界面设计作品PSD源文件免费下载</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">uimaker版权所有禁止转载发布</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">意见建议反馈内容模版</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">系统错误修复文档下载分布</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">采集信息管理系统后台界面</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">用户管理信息文件同步</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                    <%--<ul class="inner-attached hide">--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">意见建议反馈内容模版</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">这里显示的不同内容</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">界面设计作品PSD源文件免费下载</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">系统错误修复文档下载分布</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">采集信息管理系统后台界面</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span></span>--%>
                            <%--<a href="javascript:;" class="ellipsis">用户管理信息文件同步</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            </div>
            <div class="center-part">
                <div class="center-items todo">
                    <div class="calendar-part" style="width: 225px">
                        <div class="easyui-calendar" style="width:212px;height:232px;"></div>
                    </div>
                    <ul class="work-items clearfix">
                        <li>
                            <div class="work-inner">
                                <div class="work-item blue">
                                    <i class="iconfont">&#xf00ca;</i>
                                    <span class="num">${obj.totelNum}&nbsp;<span class="unit">台</span></span>
                                    <label>车辆总数</label>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="work-inner">
                                <div class="work-item purple">
                                    <i class="iconfont">&#xf00e8;</i>
                                    <span title="2000,00万" class="num">${obj.lineNum}&nbsp;<span class="unit">条</span></span>
                                    <label>线路条数</label>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="work-inner">
                                <div class="work-item gray">
                                    <i class="iconfont">&#xf017b;</i>
                                    <span class="num">${obj.unsafeNum}&nbsp;<span class="unit">次</span></span>
                                    <label>不良行为总数</label>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="work-inner">
                                <div class="work-item green">
                                    <i class="iconfont">&#xf00ca;</i>
                                    <span class="num">${obj.onlineNum}&nbsp;<span class="unit">台</span></span>
                                    <label>在线台数</label>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="work-inner">
                                <div class="work-item red">
                                    <i class="iconfont">&#xf00d0;</i>
                                    <span class="num">${obj.todayPrecent}%&nbsp;</span>
                                    <label>当天在线率</label>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="work-inner">
                                <div class="work-item yellow">
                                    <i class="iconfont">&#xf00d0;</i>
                                    <span class="num">${obj.fiveDayPrecent}%&nbsp;</span>
                                    <label>五天在线率</label>
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
                <div class="center-items chart0 clearfix">
                    <div class="chart0-item">
                        <div class="item-inner">
                            <div class="item-content">
                                <div class="content-hd">车辆故障趋势图</div>
                                <div class="chart-chart" id="chart0"></div>
                            </div>
                        </div>
                    </div>
                    <div class="chart0-item">
                        <div class="item-inner">
                            <div class="item-content">
                                <div class="content-hd">ADAS报警趋势</div>
                                <div class="chart-chart" id="chart1"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="center-items chart1">--%>
                    <%--<div class="chart1-inner">--%>
                        <%--<div class="item-hd">询价降本率</div>--%>
                        <%--<div class="chart1-chart" id="chart3"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>

    <div id="ft"></div>
</div>
<%--<div class="todo-panel">--%>
    <%--<div class="todo-title">--%>
        <%--<i class="iconfont">&#xe61f;</i>--%>
        <%--<span class="num">14&nbsp;<span class="unit">个</span></span>--%>
        <%--<label>待办未处理</label>--%>
    <%--</div>--%>
    <%--<div class="todo-items">--%>
        <%--<ul>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理<i></i></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>10条</span>供应商开发申请未处理<i></i></a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>0条</span>供应商开发申请未处理，请及时审批<i></i></a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>1条</span>供应商开发申请未处理，请及时审批</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>4条</span>供应商开发申请未处理，请及时审批</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>6条</span>供应商开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，会导致失效</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<span></span>--%>
                <%--<a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批</a></a>--%>
                <%--<label>04-13</label>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>

<%--</div>--%>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script type="text/javascript" src="/dist/highcharts.js"></script>
<script type="text/javascript" src="/dist/exporting.js"></script>
<script type="text/javascript" src="/dist/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="/web/common.js"></script>
<script type="text/javascript" src="/web/welcome.js"></script>
<script type="text/javascript">
    //公开附件tab事件处理
    $(".attached-tab").on("click","a",function(){
        $(this).closest(".attached-tab").find("a").removeClass("current");
        $(this).addClass("current");
        $(this).closest(".attached").find("ul").addClass("hide");
        $(this).closest(".attached").find("ul." + $(this).attr("attached")).removeClass("hide");
    })
    $(window).resize(function(){
        var obj = parent.Main.getContentHeight();
        $('.center-part').width(obj.width-326);
    }).resize();
</script>
</body>
</html>
