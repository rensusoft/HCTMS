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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/metro.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/news.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/js/custom.js"></script>
    <script src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>

    <script src="<tm:ctx/>/js/jsonSelectTree.js"></script>
    <script src="<tm:ctx/>/js/singleSelectTree.js"></script>

    <script src="<tm:ctx/>/jsp/web/message/js/peoChooseTeacher.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.core-3.5.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.excheck-3.5.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>
<!--带教老师-->
<body>
<div style="height:250px;overflow:auto;font-size: 14px; font-family: '微软雅黑'">
    <div id="renWu" style="
    position:absolute;
    left:50%;
    transform:translate(-50%,0);
    width:100%;
    padding:15px;
    height:80%;
    overflow:auto;
    ">
	</div>
</div>
<div style="text-align: center;position: fixed;bottom:0;width: 100%;background: #fff;" class="btns">
    <a class="layui-layer-btn0" id="deKeep" onclick="addRen()">保存</a>&nbsp;&nbsp;&nbsp;
    <a class="layui-layer-btn1" onclick="resetRen()">重置</a>
</div>
</body>
</html>