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
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/approveLeave.js"></script>
</head>
<body>
	<div style="width:99.9%;margin: 0 auto;">
         <div>
         	<div style="text-align:center;">
     			<h3 id="h_titile">请假申请详情</h3>
    		</div>
			<div id="leave_info" style="font-size: 15px;">
			</div>
		</div>
	</div>
	<div style="width:100%;text-align:center;position:fixed;padding:15px 0;bottom:0px;background:#fff;">
			<button class="layui-layer-btn0" onclick="go();">通过</button>&nbsp;&nbsp;<button class="layui-layer-btn1" onclick="notGo();">不通过</button>
	</div>
</body>
</html>