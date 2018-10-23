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
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/daily.css"/>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/daily.js"></script>
</head>
<body>
	<div class="search form-group" style="margin: 0px 0px 0px 20px;">
        <label>关键字检索：</label>
        <input type="text"class="form-control" id="searchStr" placeholder="请输入关键字" style="width:300px;display: inline-block"/>
        &nbsp;&nbsp;&nbsp;&nbsp;<label>类型：</label>
        <select class="form-control" style="display: inline-block;width: 200px" id="type_id">
        	<option value="">--请选择--</option>
            <option value="1">日报</option>
            <option value="2">周报</option>
            <option value="3">月报</option>
        </select>&nbsp;&nbsp;
        <button class="btn btn-info btnSearch"  onclick="keywordSearch();">搜索</button>
        <button id="btnAdd" class="layui-layer-btn0" style="float: right;margin-right: 15px" onclick="writeDaily();">日志书写</button>
    </div>
    <div style="margin: 5px 0px 0px 20px;">
        <table id="daily"></table>
        <div id="pager1"></div>
    </div>
</body>
</html>