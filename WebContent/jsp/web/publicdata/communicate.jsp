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
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/style.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/publicdata/css/communicate.css" />
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/publicdata/js/communicate.js"></script>
	
</head>
<body style="padding: 15px">
	<div class="btns">
        <a class="button"><span></span>发表主题</a>
        关键字搜索：<input type="text" id="search_str"/>
        <a class="layui-layer-btn0" onclick="keywordSearch()" style="position: relative;top: -4px;">搜索</a>&nbsp;&nbsp;
        <a class="layui-layer-btn1" onclick="reset()"style="position: relative;top: -4px;">重置</a>
        <b>|</b>
        <ul>
            <li id="all_forums">全部主题</li>
            <li class="white" id="my_forums">我发出的(<span></span>)</li>
        </ul>
    </div>
    <div class="titleList">
        <table id="list"></table>
        <div id="pager"></div>
    </div>
</body>
</html>