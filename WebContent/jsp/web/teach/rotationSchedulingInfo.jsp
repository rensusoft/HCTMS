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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/config/css/trainPlanExam.css" />
    
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/rotationSchedulingInfo.js"></script>
</head>

<body style="padding:10px;overflow:auto;">
<span style="display:none;" id="role_code">${LOGIN_INFO.vUserDetailInfo.role_code}</span>
<div>
	<form id="exportForm" action="<tm:ctx/>/teachweb/exportStuTrainPlan.action">
		
		<a class="btn btn-info" href="javascript:;" id="exportBtn" onclick="exportStuTrainPlan()">导出</a>
	</form>
</div>
<div class="timeAxisDiv">
	<table class="timeAxisTableTit" id="stuTranPlanTit" style="position: fixed;background: #fff;top:38px;padding-top: 10px;table-layout:fixed;">
    </table>
    <table class="timeAxisTable" id="stuTranPlan">
    </table>
</div>
</body>
</html>