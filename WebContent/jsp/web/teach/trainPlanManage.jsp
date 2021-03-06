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
	<script src="<tm:ctx/>/jsp/web/teach/js/trainPlanManage.js"></script>
</head>
<body>
<div style="width: 100%;height: 100%">
    <div>
        <!-- 上半部分div -->
        <div class="stuInfo" style="height: 38px;">
        <label>届次：
        <select class="form-control" style="display: inline-block;width: 150px" id="select" onchange="changeSelect()"></select>
        </label>
	       </div>
        </div>
        <!-- 下半部分div -->
        <div>
            <!-- 左边部分列表 -->
            <div class="left_btn">
                <table id="userInfoGrid"></table>
        		<div id="userInfoToolbar"></div>
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