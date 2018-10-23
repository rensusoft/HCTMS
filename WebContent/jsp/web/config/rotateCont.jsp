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
	
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript"src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/config/css/rotateMain.css"/>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/rotateCont.js"></script>
	
</head>
<body style="height: 100%;width: 100%;overflow:hidden;">
    <div style="margin: 0 auto;position: absolute;" id="table">
        <form action="" style="margin-top: 20px;height: 40px;">            
            <div class="form-group">  
            <div id="StuType"  style="display:inline">
            </div>     
            <div id="ZhuanYe"  style="display:inline">
            </div>           
             <span class="btn btn-info btnSearch" style="float: right;margin-right: 20px" onclick="addTrain()">新增轮转规则</span>
            </div>
        </form>
        <div>
            <table id="content"></table>
            <div id=contentPager></div>
        </div>
    </div>
    <div class="contextMenu" id="myMenu" style="display:none"> 
	    <ul style="width: 200px;">
	    	<li id="using"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana ">启用</span> 
	        </li>
	        <li id="noUsing"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana ">不启用</span> 
	        </li>
	        <li id="delete"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana ">删除</span> 
	        </li>
	    </ul> 
	</div> 
</body>
<script>

</script>
</html>