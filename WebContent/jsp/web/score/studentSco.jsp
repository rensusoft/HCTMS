<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<tm:jsandcss />	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css"/>
	
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/js/select2.js"></script>	
	<script src="<tm:ctx/>/js/custom.js"></script>	
    <script src="<tm:ctx/>/js/form.js"></script>
	
	
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/jsp/web/score/js/studentSco.js"></script>
	
	
</head>
<body>
<div style="width:99%;">
<br>
&nbsp;&nbsp;关键字查询 ：<input type="text" id="stu_name" class="form-control" placeholder="请输入关键字" style="width:500px;display:inline-block">

<button class="btn btn-info btnSearch" id="qBtn" onclick="queryStuSco();">搜索</button>
&nbsp;&nbsp;&nbsp;&nbsp;
 <div class="editControl drop-con" style="max-width:126px; float:right; margin-right:10px;">
        <a class="btn btn-primary drop_btn" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批量导入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span></a>
        <ul class="dropBox" style="width:126px;">
             <li><a href="javascript:;" onclick="downStuscoExcel()">学生成绩数据模板下载</a></li>
            <li>
            <a href="javascript:;" onclick="uploadStuscoExcel()">学生成绩数据上传</a></li>
        </ul>
    </div>
    <form id="exportForm" action="<tm:ctx/>/scoreweb/downStuscoInfo.action"></form>
   <div >
			<div style="clear:both;margin-top: 3px" >
				<table id="stuScoInfoGrid" ></table>
   		    	<div id="informationToolbar"></div>
			</div>
		</div>
  </div> 
</body>
</html>