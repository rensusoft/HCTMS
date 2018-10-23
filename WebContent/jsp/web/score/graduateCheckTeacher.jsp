<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>评分表详情</title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/score/css/graduateCheck.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/score/js/graduateCheckTeacher.js"></script>
</head>
<body style="height: 100%;">
<section >
    <div class="search" style="margin-bottom:15px;">
        <span style="font-weight:bold;">学生姓名：</span><input id="stuName" type="text" style="width:300px;"/> <a class="layui-layer-btn0"style="margin-left: 10px" onclick="findByName()">查询</a><a class="layui-layer-btn1" style="margin-left: 10px">重置</a>
    </div>
    <div id="tabled">
    <div class="stuInforList">
        <div class="stuInfor">
        </div>
    </div>
    </div>
</section>
</body>
</html>