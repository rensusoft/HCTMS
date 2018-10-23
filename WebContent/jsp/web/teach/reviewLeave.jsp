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
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/reviewLeave.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateProcess.css"/>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/news.css"/>
<style type="text/css">
     	.ui-jqgrid-btable .ui-widget-content td p{
		  overflow: hidden;
		  white-space: nowrap;
		  text-overflow : ellipsis;
		  width: 70%;
		  text-align:center;
		  margin:0 auto;
		}
		.ui-jqgrid-btable .ui-widget-content td {
		  overflow: hidden;
		  white-space: nowrap;
		  text-overflow : ellipsis;
		  width: 70%;
		  text-align:center;
		  margin:0 auto;
		}
</style>
</head>
<body style="overflow:hidden;">     
	<div id="tit">
		<p class="select" onclick="div1()" id="button1">待审批</p>
        <p onclick="div2()" id="button2">已审批</p>
    </div>
    <div id="div1" style="width:99.9%;margin: 0 auto;"class="main_list">
       <div class="panel panel-default process">
		   <div id="press_info" style="font-size: 15px;">
		   	   <table id="reviewLeave"></table>
	           <div id="pager1"></div>
		   </div>
	   </div>
	</div>
</body>
</html>