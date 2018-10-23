<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/jsp/web/login/css/showSelRole.css'/>

	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/login/js/showSelRole.js"></script>
</head>
<body style="padding:0px;margin:0px;">
	<!-- ${param.authList} -->
	<div style="clear: both;"></div>
	${authList }
	
</body>
</html>
