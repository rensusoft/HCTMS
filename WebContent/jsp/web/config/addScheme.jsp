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
    <link rel="stylesheet" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/config/css/addScheme.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/js/custom.js"></script>
    <script src="<tm:ctx/>/jsp/web/config/js/addScheme.js"></script>
</head>
<body>
<div class="addOrga">
	<div id="addOrga">
	</div>
	方案名称：<input type='text' id='name'/><br/><br/>
	学生类型：<select id='stuTypeCode'></select><br/><br/>
	培训专业：<select id='major'></select><br/><br/>
	&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;限&nbsp;&nbsp;：<input type='text' id='timelong'/>
	<div style="text-align:center;margin-top:15px;">
		<input type='button' class='layui-layer-btn0' value='重置' onclick='reset()'/>
		<input type="button" class='layui-layer-btn1' value='提交' onclick='addtrI()'/>
	</div>
</div>
</body>
</html>