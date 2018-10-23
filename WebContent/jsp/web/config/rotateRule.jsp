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
	<link rel="stylesheet" href="<tm:ctx/>/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/config/css/rotateMain.css"/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/rotateRule.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>	
	<script type="text/javascript"src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
	
</head>
<body>
    <div class="data_count">
                  时长单位：<select class="btn btn-primary dropBtn" name="editControlBtn" id="time" onchange="timeChange()">
            	<option value="D">天</option>
            	<option value="W">周</option>
            	<option value="M" selected="selected">月</option>
            </select>
    </div>
    <br/>
    <div>
        <table id="rotateRule"></table>
        <div id="pager1"></div>
    </div>
    <div class="btn_group">
    	<b style="color:red;float:left;font-size:16px;display:inline-block;margin-right:30px;">操作说明:<span style="font-weight:normal;">1.进行批量删除和同步科室操作后，要想保存数据，必须按保存数据;<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.方案说明只能在保存数据后操作，且完成后自动保存，不能按保存数据按钮;否则数据丢失！</span></b>
    	<a class="layui-layer-btn0" onclick="addAll()" style="float:none;">保存数据</a>
        <a class="layui-layer-btn1" onclick="deleteMany()">批量删除</a>
        <a class="layui-layer-btn0" onclick="addOrga()">同步科室</a>
    </div>
        <div class="contextMenu" id="myMenu" style="display:none"> 
	    <ul style="width: 200px;">
	        <li id="delete"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana ">删除</span> 
	        </li>
	    </ul> 
	</div> 
</body>
</html>