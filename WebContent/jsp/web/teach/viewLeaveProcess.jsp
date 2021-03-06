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
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateProcess.css"/>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/viewLeaveProcess.js"></script>
	
	<style>
   .score td{
       color: #505F91;
   }
   .score td i{
       font-style: normal;
   }
   </style>
</head>
<body>
	<div id="div2" style="width:99.9%;margin: 0 auto;"class="main_list">
	<div  class="process">
    <div style="text-align:center;">
     <h3>申请流程</h3>
    </div>
     <div class="panel-body" style="width:100%;">
     <div id="process_top"class="step">
        </div>
        <div id="leave_info"></div>
        </div></div></div>
<script type="text/javascript">
	function addcss(){
    var width=997-$(".finish_td").width();
    $(".process .panel-body div.step table td").width(width);
    console.log($(".finish_td").width());
    console.log(width);
    var cWidth=(width)/($(".process .panel-body div.step table tr td").size()-1);
    var iWidth=cWidth-$(".process .panel-body div.step table tr td:first-child b").width();
    if($(".process .panel-body div.step table tr td").size()==6){
        $(".process .panel-body div.step table tr td i").width(iWidth-5);
    }else if($(".process .panel-body div.step table tr td").size()==5){
        $(".process .panel-body div.step table tr td i").width(iWidth+5);
    }else if($(".process .panel-body div.step table tr td").size()==4){
        $(".process .panel-body div.step table tr td i").width(iWidth+15);
    }else if($(".process .panel-body div.step table tr td").size()==3){
        $(".process .panel-body div.step table tr td i").width(iWidth+18);
    }
	};
   </script>
</body>
</html>