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
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/score/css/indeptEduc_HTML.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/score/js/indeptEduc_HTML.js"></script>
</head>
<body>
	<div class="dtlBox">
		<div id="htmlDiv">
			<div style="text-align: center;font-size:22px;font-weight:bold;" id="name">
				
			</div>
			<div style="margin:10px 0px -10px 0px;">
				<div style="float:left;width:23%;"><strong>学生姓名：</strong><span id="stu_name"></span></div>
				<div style="float:left;width:27%;"><strong>学生类型：</strong><span id="stu_type_name"></span></div>
				<div style="float:left;width:25%;"><strong>宣教科室：</strong><span id="dept_name"></span></div>
				<div style="float:left;width:25%;"><strong>宣教人：</strong><span id="indeptEduc_name"></span></div>
				<div style="clear:both;"></div>
			</div>
			<hr/>
			<!-- <a href="javascript:;" class="btn btn-primary pull-right export">导出信息</a> -->
			<!--
			<div class="delTitle">
				<div class="hr"></div>
			    <h5>表单信息</h5>
			    <a href="javascript:;" data-toggle="collapse" data-target="#main" class="toggleInfo">
	                <span class="glyphicon glyphicon-chevron-up"></span>
			        <span class="infoText">收起表单信息</span>
			    </a>
			</div>
			<div class="delCon collapse in" id="main">
			</div>
	        <div class="delTitle">
				<div class="hr"></div>
			    <h5>评分表</h5>
			    <a href="javascript:;" data-toggle="collapse" data-target="#detail1" class="toggleInfo">
	                <span class="glyphicon glyphicon-chevron-up"></span>
			        <span class="infoText">收起评分表单</span>
			    </a>
			</div>
			-->
			<div id="detail1" class="collapse in" style="margin-bottom:60px;">
				<div id="contentHtml"></div>
			</div>
		</div>
		<div class="btns">
            <a class="layui-layer-btn0" onclick="saveData()">完成</a>
        </div>
    </div>
</body>
</html>