<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院临床教学综合管理系统</title>
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
<input type="hidden" id="menuId" value="">
    <div id="header" class="content_box">
    	<div id="logo" class="pull-left margin_left">
    		<img src="<tm:ctx/>/img/logo.png">
    	</div>
    	<div id="nav" class="pull-left" style="text-align: center;">
    	    <ul class="list-inline">
    	    	<c:forEach items="${LOGIN_INFO.menuList}" var="supMenu" varStatus="status">
    	    		<c:if test="${supMenu.menu_id==900}">
	    	    		<li class="down">
	    	    			${supMenu.menu_name}
	    	    			<c:if test="${LOGIN_INFO.msgCount > 0}">
   	    						<span class="numAll">${LOGIN_INFO.allMsgCount}</span>
   	    					</c:if>
	    	    			<div class="down-menu">
		    	    			<div class="menu-L">
		    	    				<c:forEach items="${supMenu.subMenuList}" var="subMenu">
	    	    				       <c:if test="${subMenu.menu_id==901}">
				    	    			    <div id="msgCountDiv">
				    	    					<a href="javascript:;" addtabs="${subMenu.menu_id}" url="<tm:ctx/>${subMenu.url}">${subMenu.menu_name}
				    	    					</a>
				    	    					<c:if test="${LOGIN_INFO.msgCount > 0}">
				    	    						<span class="num">${LOGIN_INFO.msgCount}</span>
				    	    					</c:if>
				    	    				</div>
			    	    				</c:if>
		    	    				    <c:if test="${subMenu.menu_id==903}">
				    	    			    <div>
				    	    					<a href="javascript:;" addtabs="${subMenu.menu_id}" url="<tm:ctx/>${subMenu.url}">${subMenu.menu_name}
				    	    					</a>
				    	    				</div>
			    	    				</c:if>
			    	    				<c:if test="${subMenu.menu_id!=901 && subMenu.menu_id!=903}">
				    	    			    <div>
				    	    					<a href="javascript:;" addtabs="${subMenu.menu_id}" url="<tm:ctx/>${subMenu.url}">${subMenu.menu_name}
				    	    					</a>
				    	    				</div>
			    	    				</c:if>
		    	    				</c:forEach>
						    	</div>
					    	</div>
	    	    		</li>
    	    		</c:if>
    	    		<c:if test="${supMenu.menu_id!=900}">
		   	    		<li class="down">
		   	    			${supMenu.menu_name}
		   	    			<div class="down-menu">
		    	    			<div class="menu-L">
		    	    				<c:forEach items="${supMenu.subMenuList}" var="subMenu">
		    	    					<a href="javascript:;" addtabs="${subMenu.menu_id}" url="<tm:ctx/>${subMenu.url}">${subMenu.menu_name}
		    	    					</a>
		    	    				</c:forEach>
						    	</div>
					    	</div>
		   	    		</li>
    	    		</c:if>
    	    	</c:forEach>
    		</ul>
    	</div>
    	<div class="user pull-right margin_right">
    		<!-- <img src="<tm:ctx/>/img/user.png"> -->
			<div style="float:left;">
				<span>欢迎您，${LOGIN_INFO.vUserDetailInfo.user_name}[${LOGIN_INFO.vUserDetailInfo.role_name}]</span>
	    		<input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_id }" id="userId">
	    		<input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_password }" id="userpassword">
			</div>
			<div style="float:left;margin-left:5px;">
				 <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
			      <img src="<tm:ctx/>/ueditor(附件文件夹千万不能删)/userImg/${LOGIN_INFO.vUserDetailInfo.stu_num}.jpg" onerror="this.src='<tm:ctx/>/jsp/web/publicdata/img/person.png'" style="border-radius:50%;width:50px;height:50px;border:2px solid #F3F3F3;">
			    </c:if>
			    <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code!='R_STU'}">
				<c:if test="${LOGIN_INFO.vUserDetailInfo.img_path==null}">
					<img src="<tm:ctx/>/img/user.png" style="width:45px;">
				</c:if>
				<c:if test="${LOGIN_INFO.vUserDetailInfo.img_path!=null}">
					<img src="<tm:ctx/>/${LOGIN_INFO.vUserDetailInfo.img_path}" style="border-radius:50%;width:50px;height:50px;border:2px solid #F3F3F3;">
				</c:if>
				</c:if>
			</div>
			<div style="clear:both;"></div>
			<div class="userEdit">
				<a href="#" onclick="information()">个人信息</a>
				<a href="#" onclick="operationManual()">操作手册</a>
				<a href="#" onclick="updatePassword()">修改密码</a>
				<a href="#" id="logout">退出</a>
			</div>
    	</div>
    </div>
    <div id="section">
    	<ul id="myTab" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active">
			   <a href="#schedule" data-toggle="tab" aria-controls="schedule" role="tab" onclick="refresh()">首页</a>
		    </li>
		</ul>
		<div id="myTabContent" class="tab-content margin_top">
		    <div role="tabpanel" class="tab-pane active" id="schedule" style="height:100%;">
		       	<iframe name="iframeName" id="mainContainner" src="<tm:ctx/>/jsp/web/index/commonIndex.jsp" style="border:none;" width="100%" height="99%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling-x="no" scrolling-y="auto" allowtransparency="yes"></iframe>
			</div>
		</div>
    </div>
    
    
    <div id="footer">
    	<div style="height:25px;background:url(<tm:ctx/>/img/logo2.png) center center no-repeat;">
    		
    	</div>
    </div>
   
    
</body>
</html>
