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
	
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/selectordie_theme_01.css"/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/manualMttendance.css"/>
	
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/manualMttendance.js"></script>
</head>
<body>
    <section>
        <p style="margin:5px">
            日期：<b class="year"></b>年<b class="month"></b>月<b class="day"></b>日
        </p>
        <div class="leaveStu">
            <h4 style="margin:0">
                请假学生
            </h4>
            <div class="leaveStuDiv" id="leaveStuDiv" style="min-height: 90px;">
            </div>
        </div>
        <div class="stuList" style="margin-bottom:50px">
            <h4 style="margin:0">
                学生名单
            </h4>
            <div class="stuListDiv">
            	<!--  
                <div class="stuDiv dark" id="1">
                    <p class="stuName">李四</p>
                    <p class="stuType">学生类型类型</p>
                    <div class="stuDiv_img">
                        <img src="img/absenteeism.png"/>
                    </div>
                </div>
                <div class="stuDiv" id="2">
                    <p class="stuName">李四</p>
                    <p class="stuType">学生类型类型</p>
                    <div class="stuDiv_img display">
                        <img src="img/absenteeism.png"/>
                    </div>
                </div>
                -->
            </div>
        </div>
        <div class="btns" style="position:fixed;width:100%;backround:#fff;padding:15px 0;bottom:0">
            <a class="layui-layer-btn0">考勤结束</a>
        </div>
    </section>
</body>
</html>