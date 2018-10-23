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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/metro.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jsonSelectTree.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/singleSelectTree.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/htmlTemplateEdit.js"></script>
	<script type="text/javascript">
	    var editor = null;
	    window.onload = function(){
	        editor = CKEDITOR.replace('content',{
	        	height:450
	    	}); //参数‘content’是textarea元素的name属性值，而非id属性值
	    };
	</script>
</head>
<body>
<div style="width:98%;margin: 10px 10px;">
    <input id="id" value="" type="hidden"  />
	<div class="form-group" style="float: left;width: 33%">
   		<label>表单类型：普通表单</label>
   	</div>
   	<div class="form-group" style="float: left;width: 34%">
       	<label>表单状态：<span id="form_state"></span><input id="availability" value="" type="hidden" /></label>
   	</div>
   	<div class="form-group" style="float: left;width: 33%">
       	<label>创建时间：<span id="time"></span></label>
   	</div>
   	<div class="form-group">
    	<label><span class="red">*</span>表单名称：
    	<input type="text" id="name" class="form-control" style="width:900px;display:inline-block;"></label>
   	</div> 
</div>
<div style="width:98%;margin: 0 auto;margin-top:4px;">
	<textarea name="contentProcess" id="content" style="margin-top:4px;width:100%;height:480px;"></textarea>
	<div style="width:100px;margin: 0 auto;margin-top:4px;">
		<button class="layui-layer-btn0" onclick="submitData();">确定</button>
	</div>
</div>
</body>
</html>