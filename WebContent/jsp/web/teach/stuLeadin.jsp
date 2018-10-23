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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotatePlan.css"/>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/rotateLayeout.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/jquery.tablednd.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateLayeout.css"/>
	<script src="<tm:ctx/>/jsp/web/teach/js/stuLeadin.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <div class="search form-group">
        <label><span style="color: red">*</span>届次：</label>
        <select class="form-control" style="display: inline-block;width: 200px" id="select" onchange="changeSelect()">
        </select>&nbsp;&nbsp;
        <a class="btn btn-info btnSearch" onclick="addStuClass()">新增</a>
	    <div style="float: right;margin: 5px 10px;">
	        <a class="layui-layer-btn0" onclick="import_btn();">批量导入</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <!-- <a class="layui-layer-btn1">重置</a> -->
	    </div>
	    <form id="exportForm" action="<tm:ctx/>/userauthweb/downUserInfo.action">
		</form>
    </div>
    <div>
        <table id="studentGrid"></table>
        <div id="studentToolbar"></div>
    </div>
</body>
</html>