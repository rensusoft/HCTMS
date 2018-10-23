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
	

	
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
	 <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
		<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style2.css"/>
	
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/userauth/js/deptManage.js"></script>
	
	<style type="text/css">
		.print table{border:0; border-width:1px 0px 0px 1px;text-align: center; width: 98%;margin: 13px 0.5%;}
		.print tr{border:0; border-width:1px 0px 0px 1px;text-align: right;}
		.print td{border:0; border-width:0px 1px 1px 0px;}
		.print ipuut{border:0; border-width:0px 1px 1px 0px;width: 100%}
        .layui-layer-btn0,
        .layui-layer-btn1,
        .layui-layer-btn2{
	    background: #4c6293 !important;
        border: 1px solid #2d3d62 !important;
        border-radius: 4px;
        color: #fff !important;
        font-weight: normal !important;
        height: 36px !important;
        line-height: 18px !important;
        padding: 8px 26px !important;
        }
        .layui-layer-btn1,
        .layui-layer-btn2{
	    background: #f1f2f6 !important;
        border: 1px solid #505f91 !important;
        color: #505f91  !important;
        }
        .layui-layer-btn {
        background: #fff;
	    width: 100%;
	    padding-bottom: 20px !important;
        }
       </style>

</head>
<body>
	<div style="width:96%;margin: 0 auto;">
		<table id="DeptManageGrid"></table>
   		<div id="DeptManageToolbar"></div>
	</div>
</body>
</html>