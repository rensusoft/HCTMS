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
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/score/js/formWithHtml.js"></script>
</head>
<body>
	<div class="dtlBox">
		<div id="htmlDiv">
			<div style="text-align: center;font-size:22px;font-weight:bold;" id="name">
				
			</div>
			<div style="margin:10px 0px -10px 0px;">
				<div style="float:left;width:23%;"><strong>表单类型：</strong><span id="form_type"></span></div>
				<div style="clear:both;"></div>
			</div>
			<hr/>
			<div id="detail2">
			<div id="detail1" class="collapse in" style="margin-bottom:60px;">
				<div id="contentHtml"></div>
			</div>
			</div>
		</div>
		<div class="btns">
            <a class="layui-layer-btn0" onclick="saveData()">保存</a>
        </div>
    </div>
</body>
</html>