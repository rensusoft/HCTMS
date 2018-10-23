<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院临床教学综合管理系统V1.0</title>
<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/fullcalendar.css" />
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
	<script src="<tm:ctx/>/js/PublicMethed.js"></script>
	
	<script src="<tm:ctx/>/jsp/web/index/js/main.js"></script>
</head>
<body class="bgcolor">
<input type="hidden" id="roleCode" value="${LOGIN_INFO.vUserDetailInfo.role_code}">
    <div id="header" class="content_box">
    	<div id="logo" class="pull-left margin_left">
    		<img src="<tm:ctx/>/img/logo.png">
    	</div>
    	<div id="nav" class="pull-left" style="text-align: center;">
    	    <ul class="list-inline">
    	    	<c:forEach items="${LOGIN_INFO.menuList}" var="supMenu" varStatus="status">
    	    		<li class="down">
    	    			${supMenu.menu_name}
    	    			<div class="down-menu">
	    	    			<div class="menu-L">
	    	    				<c:forEach items="${supMenu.subMenuList}" var="subMenu">
	    	    					<a href="javascript:;" addtabs="${subMenu.menu_name}" url="<tm:ctx/>${subMenu.url}">${subMenu.menu_name}</a>
	    	    				</c:forEach>
					    	</div>
				    	</div>
    	    		</li>
    	    	</c:forEach>
    	    	
    			
    		</ul>
    	</div>
    	<div class="user pull-right margin_right">
    		<span>欢迎您，${LOGIN_INFO.vUserDetailInfo.user_name}</span>
    		<input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_id }" id="userId">
    		<input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_password }" id="userpassword">
			<img src="<tm:ctx/>/img/user.png">
			<div class="userEdit">
				<a href="#" onclick="information()">个人信息</a>
				<a href="#" onclick="updatePassword()">修改密码</a>
				<a href="#" id="logout">退出</a>
			</div>
    	</div>
    </div>
    <div id="section">
    	<ul id="myTab" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active">
			   <a href="#schedule" data-toggle="tab" aria-controls="schedule" role="tab">首页</a>
		    </li>
		</ul>
		<div id="myTabContent" class="tab-content margin_top">
		    <div  class="tab-pane active" id="schedule">
		       	<iframe id="mainContainner"  style="border: none;" width="100%" height="100%"></iframe>
			</div>
		</div>
    </div>
    
    
    <div id="footer">
    	<div style="height:25px;background:url(<tm:ctx/>/img/logo2.png) center center no-repeat;">
    		
    	</div>
    </div>
</body>
</html>
