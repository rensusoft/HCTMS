<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/hctms" prefix="tm" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
<html>
<head lang="en">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/publishingOperation.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script src="<tm:ctx/>/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/publishingOperation.js"></script>
    <title></title>
</head>
<body>
     <section>
        <div class="jqDiv" >
            <div class="searchDiv">
                任务类型：
                <select class="form-control" id="task_type_code" style="display: inline-block;width: 150px;margin-right: 30px">
                <option value="">----请选择----</option>
                <option value="1">课前</option>
                <option value="2">课中</option>
                <option value="3">课后</option>
                </select>
               发布时间：<input type="text" id="start_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="display: inline-block;width:150px; height: 26px;">--<input type="text"  id="end_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="display: inline-block;width:150px;height: 26px;">
                状态：
                <select class="form-control" style="display: inline-block;width: 150px;margin-right: 30px" id="progress_state">
                <option value="">----请选择----</option>
                <option value="1">已发布</option>
                <option value="2">已处理</option>
                <option value="3">完成</option>
                </select>
                <a class="btn btn-info btnSearch" onclick="keywordSearch();">查询</a>
                <a class="layui-layer-btn0 addNew">发布作业</a>
            </div>
            <div>
                <table id="rotateRule"></table>
                <div id="pager1"></div>
            </div>
        </div>
        <div class="addDiv displayDiv">
            <div class="leftBtn">
                <span></span><br/>
                作<br/>
                业<br/>
                列<br/>
                表<br/>
            </div>
            <div class="left_btns">
                <h3>学生列表</h3>
                <ul class="select" id="stuList">
                  
                </ul>
            </div>
            <div id="wrap">
            </div>
             <!--  
            <div id="wrap">
                <ul id="con">
                    <li class="show_block">
                        <div class="form_cont dtlBox">
                            <div class="delTitle">
                                <div class="hr"></div>
                                <h5>作业内容</h5>
                                <a class="toggleBtn"><img src="img/arrowT.png"/>收起</a>
                            </div>
                            <div class="rejectCont">
                                <p id="contentTea"></p>
                            </div>
                            <div class="delTitle">
                                <div class="hr"></div>
                                <h5>学生回答</h5>
                                <a class="toggleBtn"><img src="img/arrowT.png"/>收起</a>
                            </div>
                            <div class="rejectCont">
                                <p id="content"></p>
                            </div>
                            <div class="delTitle">
                                <div class="hr"></div>
                                <h5>其他</h5>
                                <a class="toggleBtn"><img src="img/arrowT.png"/>收起</a>
                            </div>
                            <div class="rejectCont">
                                评分：<input type="text" class="form-control" style="display: inline-block;width:150px;" id="sco_teach">
                                <p id="contents_teach">
                                </p>
                                评价：<input type="text" class="form-control" style="display: inline-block;width:90%;" id="appraise_teach">

                            </div>
                        </div>  
                        <div class="btns" style="text-align: center">
                            <a class="layui-layer-btn0" onclick="saveSco();">保存</a>
                        </div>
                    </li>
                </ul>
            </div>
            -->
        </div>
    </section>
    
</body>
</html>