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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/subaddTab.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript"  src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/formInfo.js"></script>
</head>
<body>
	<div style="width:99%;margin: 0 auto;" >
	<div style="margin:5px 0px;float:left;">
        &nbsp;关键字 ：<input type="text" id="searchStr" class="form-control" placeholder="请输入关键字" style="width:350px;display:inline-block">&nbsp;
    	<button class="btn btn-info btnSearch"  onclick="keywordSearch();">搜索</button>
    </div>
    <div style="margin:5px 0px;float:right;">
		<a class="btn btn-info btnSearch" href="javascript:;" addtabs="新建评分表" url="<tm:ctx/>/jsp/web/basicdata/formAdd.jsp" title="新建评分表">+新建评分表</a>
    </div>
    <div style="clear:both;"></div>
		<table id="FormInfoGrid" style="margin-top:4px;"></table>
   		<div id="FormInfoToolbar"></div>
    </div>
</body>
</html>