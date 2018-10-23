<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<tm:jsandcss/>
<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/jsp/web/userauth/css/table_information.css' />
<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/js/uploadify/uploadify.css'/>

<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
<script src="<tm:ctx/>/js/uploadify/jquery.uploadify.min.js"></script>
<script src="<tm:ctx/>/jsp/web/userauth/js/uploadHeadImgs.js"></script>	
</head>
<body style="width:950px;margin:0 auto;">
    <div class="info_table" style="margin-bottom:80px;">
        <div style="margin-bottom:5px;">
        	<span style="color:#ED942A;">*点击以下按钮进行头像图片的选择（可以多选）【1、注意只能jpg格式的图片；2、图片名称必须为学生学号】</span>
        </div>
        <div>
        	<input type="file" id="uploadify" name="headImgs">
        	<div id="fileQueue"></div>
        </div>
    </div>
    <div class="button_position">
			<input type="submit" class="layui-layer-btn0" value="上传" onclick="javascript:$('#uploadify').uploadify('upload','*')">&nbsp;&nbsp;
			<input type="reset" class="layui-layer-btn1" value="取消上传" onclick="javascript:$('#uploadify').uploadify('cancel','*')">
	</div>

</body>
</html>