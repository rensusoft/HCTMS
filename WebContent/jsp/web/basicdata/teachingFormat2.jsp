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
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
     <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/basicdata/css/teachingFormat.css"/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript"  src="<tm:ctx/>/jsp/web/basicdata/js/teachingFormat2.js"></script>
</head>
<body>
<div style="padding: 8px 0 0 0;">
    <label style="font-weight:bold;">科室名称：</label>
    <input type="text" class="form-control" placeholder="请输入关键字" style="width:400px;display: inline-block;height:32px;" id="txt"/>
    <a class="btn btn-info btnSearch" onclick="query()">搜索</a>
</div>
<div class="contPanel" style="margin-right:10px; " id='neiRong'>
   
</div>
</body>
</html>