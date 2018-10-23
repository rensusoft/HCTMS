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
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/leaveApplication.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div style="width:99%;margin: 0 auto;margin-top:4px;">
	<div style="margin-bottom:4px;"> 请假类型：<select class="form-control" id="code_type" style="width:250px;display:inline-block;">
                  <option value="1">事假</option>  
                  <option value="2">病假</option>     
                  <option value="3">其他</option>     
                 </select>
                 <input type="hidden" id="auth_ids" value=""  />
                <span style="margin-left:133px;">审批人：</span><input  id="name" class="form-control" style="width:250px;display:inline-block;margin-right:20px;background:#EEEEEE;" value=""  readonly />
                <input id="id" class="form-control"value="" type="hidden"  />
      </div>          
               开始时间：<input type="text" id="start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:250px;display:inline-block;border:1px solid #ccc;height:26px;" value=""/>
      <span style="margin-left:110px;">结束时间：</span><input type="text" id="end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:250px;display:inline-block;border:1px solid #ccc;height:26px;"/>
              <span style="margin-left:20px;"> 请假天数：</span><span id="day" style="width:20px;display:inline-block;"></span>
              <select id="select_half_day" style="display:none;margin-left:10px;" onchange="changeHalfDay();">
              	<option value="1">全天</option>
              	<option value="-10">上午</option>
              	<option value="-20">下午</option>
              </select>
		<textarea name="contentProcess" id="content" style="margin-top:18px;width:100%;height:450px;"></textarea>
		<div style="width:100px;margin: 0 auto;margin-top:4px;">
		<button class="layui-layer-btn0" onclick="submitData();">确定</button>
		</div>
	</div>
</body>
</html>