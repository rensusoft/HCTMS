<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title></title>
	<tm:jsandcss/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotatePlan.css"/>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/rotateLayeout.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/jquery.tablednd.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateLayeout.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/layoutProgress.css"/>
	<script src="<tm:ctx/>/jsp/web/teach/js/layOut.js"></script>
</head>
<body>
    <div>
        <!-- 上半部分div -->
        <div class="stuInfo" style="height: 38px;">
            <span>届次：<i id="stuClassName"></i></span>
            <span>学生：<i id="userCount"></i>个</span>
            <div style="float: right;margin: 5px 10px;">
	        <!-- <a class="layui-layer-btn1">重置</a> -->
	       </div>
	    <form id="exportForm" action="<tm:ctx/>/userauthweb/downUserTrainPlan.action">
	    <input type="hidden" id="stuClass" name="stuClass"/>
		</form>
	    </div>
        </div>
        <!-- 下半部分div -->
        <div>
            <!-- 左边部分列表 -->
            <div class="left_btn">
                <table id="userInfoGrid"></table>
        		<div id="userInfoToolbar"></div>
            </div>
            <div class="process" id="planPic">
	            <ul>
	                <li>
	                    <div class="processStep" style="padding-top: 27px" onclick="doTrainPlanBefore()">点击开始预编排</div>
	                    <div class="arrow">
	                        <i></i><img src="<tm:ctx/>/jsp/web/teach/img/arrowp.png" alt=""/>
	                    </div>
	                </li>
	                <li>
	                    <div class="processStep1" style="padding-top:28px">选择学生双击查看预编排结果</div>
	                    <div class="arrow1">
	                        <i></i><img src="<tm:ctx/>/jsp/web/teach/img/arrop.png" alt=""/>
	                    </div>
	                </li>
	                <li>
	                    <div class="processStep2"  style="padding-top: 25px">生成轮转计划</div>
	                </li>
	            </ul>
	        </div>
            <!-- 右边部分jqgrid -->
            <div id="planGrid" class="jqueryTable" style="display: none;">
                <table id="planBeforeGrid"></table>
                <div id="planBeforeToolbar"></div>
            </div>
        </div>
    </div>
</body>
</html>