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
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/pending.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/js/select2.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/pending.js"></script>
</head>
<body>
<div style="margin: 10px 0px 0px 20px;">
    <label style="font-weight:bold;">关键字检索：</label>
    <input type="text"class="form-control" id="keyWord" placeholder="请输入关键字" style="width:400px;display: inline-block;height:32px;"/>
    
    <button class="btnSearchb btn-infob"  onclick="searchCheck();">搜索</button>
    <button class="layui-layer-btn1" onclick="resert();">重置</button>
</div>
<div class="content">
    <table id="check"></table>
    <div id="pager1"></div>
</div>

</body>
</html>