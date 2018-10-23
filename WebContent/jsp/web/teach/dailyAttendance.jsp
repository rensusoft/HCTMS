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
	
	    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/selectordie_theme_01.css"/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/dailyAttendance.css"/>
	
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/dailyAttendance.js"></script>
</head>
<body>
    <section>
        <div class="leftBtns">
            <div>
                点击以下按钮进行今日考勤<span class="glyphicon glyphicon-hand-down" style="font-size: 35px;vertical-align: middle;"></span>
                <p style="margin-top: 50px">
                    <a class="attenBtn manualMttendance"><span class="glyphicon glyphicon-list-alt"></span>人工考勤</a>
                </p>
                <p id="p_QRCode" style="display: none;">
                    <a class="attenBtn QRCode"><span class="glyphicon glyphicon-qrcode"></span>二维码考勤</a>
                </p>
            </div>
        </div>
        <div class="stuList">
            <div class="timeAtten">
                <p>
                    日期：<span class="year"></span>年<span class="month"></span>月<span class="day"></span>日
                </p>
                <span class="stateList">
                    考勤状态：
                    <a class="clicked" id="the_whole" onclick="switchAttendance(3);">全部 <span></span></a>
                    <a id="the_attendance" onclick="switchAttendance(1);">已考勤 <span></span></a>
                    <a id="no_attendance" onclick="switchAttendance(0);">未考勤 <span></span></a>
                    <a id="the_leave" onclick="switchAttendance(2);">请假中 <span></span></a>
                </span>
            </div>
            <div class="content">
                <table id="check"></table>
                <div id="pager1"></div>
            </div>
        </div>
    </section>
</body>
</html>