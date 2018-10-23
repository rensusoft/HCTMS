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
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/basicdata/css/teachingFormat.css"/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/jsp/web/basicdata/js/teachingFormat3.js"></script>	
</head>
<body style="padding: 5px">
    <div style="padding: 10px 0">
        <label>关键字搜索：</label>
        <input type="text" id="text" class="form-control" placeholder="请输入关键字" style="width:400px;display: inline-block"/>
        <a class="layui-layer-btn0" onclick="searchName()">搜索</a>
        <a class="layui-layer-btn1" onclick="resetName()">重置</a>
    </div>
    <div>
        <table id="form"></table>
        <div id="pager1"></div>
    </div>
</body>
</html>