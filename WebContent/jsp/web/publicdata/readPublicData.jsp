<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>共享资料查看</title>
	<tm:jsandcss/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/publicdata/js/readPublicData.js"></script>
	<!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
    <!-- ueditor视频播放相关 
    <script type="text/javascript" src="<tm:ctx/>/js/ueditor/third-party/video-js/video.js"></script>
	-->
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/js/ueditor/third-party/video-js/video-js.min.css"/>
	
	<script type="text/javascript" src="<tm:ctx/>/js/ueditor/dialogs/video/video.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/ueditor/third-party/video-js/html5media.min.js"></script>
	<style>
		.contDiv p{
			word-break:  break-all;
        	word-wrap:  break-word;
        	white-space:  pre-wrap;
		}
	</style>
	
</head>
<body>
	<div style="width:1150px;margin:0 auto;">
		<div style="text-align:center;">
			<h3 id="title">${publicData.title}</h3>
		</div>
		<div style="width:1150px;margin:0 auto;font-size: 14px;text-align:right;color:#AAAAAA;">
			<span style=" float:left;">资料类型：${publicData.type_code}</span>
			<span style="margin-right:27%">发布人：${publicData.publisher_auth_id}</span>
			<span style=" float:right;margin-right:5px">发布时间：${publicData.time}</span>
		</div>
		<hr style="margin:6px 0px;"/>
		<div style="width:1150px;margin:0 auto;" class="contDiv">
			${publicData.content}
		</div>
	</div>
	
</body>
</html>