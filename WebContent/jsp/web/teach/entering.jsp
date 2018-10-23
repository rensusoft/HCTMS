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
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/entering.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<script src="<tm:ctx/>/jsp/web/teach/js/entering.js"></script>	
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<tm:ctx/>/js/select2.js"></script>

</head>
<body  style="overflow: hidden;">
<div class="lf_cont">
    <div style="width: 1850px">
        <label>状态：</label>
        <select class="form-control" id="dssd" style="display:inline-block; width:150px;">
        	<option value="-2">---请选择---</option>
            <option value="1">审核通过</option>
            <option value="0">待审核</option>
            <option value="-1">审核未通过</option>
        </select>&nbsp;&nbsp;
        <label>日期：</label>
        <input type="text" id="classtime-from" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" 
        style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
    -- <input type="text" id="timeTo" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" 
       style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
        
        
        <label>关键字搜索：</label>
        <input type="text" id="text" class="form-control" placeholder="请输入关键字" style="width:200px;display: inline-block">&nbsp;&nbsp;
        <input type="button" class="layui-layer-btn0" id="search"   onclick="search()" value="搜索"/>
        <input type="button" class="layui-layer-btn1"   onclick="cResrt()" value="重置"/>
    </div>
    <div class="content">
        <table id="entering_content"></table>
        <div id="enteringPager"></div>
    </div>
    <div class="content" style="margin-left:15px;">
        <table id="teaching_content"></table>
        <div id="teachingPager"></div>
    </div>
</div>
<div class="add_content">
    <span class="click_a">
        <br/><br/><br/><i><img src="<tm:ctx/>/jsp/web/teach/img/select_op.png" alt=""/></i><br/>点 <br/>击 <br/>录 <br/>入 <br/>信 <br/>息 <br/>
    </span>
    <div class="table_cont">
        &nbsp;&nbsp;关键字搜索：<input type="text" class="form-control" placeholder="请输入关键字" style="width:250px;display: inline-block" id="txt">&nbsp;&nbsp;
        <a class="layui-layer-btn0" id="search_btn" onclick="query()">搜索</a>&nbsp;&nbsp;
        <a class="layui-layer-btn1" onclick="reset()">重置</a>
        <hr/>
        <table id="list">
   
        </table>
    </div>
</div>
</body>
</html>