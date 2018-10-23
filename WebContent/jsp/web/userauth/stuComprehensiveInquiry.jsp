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
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/userauth/css/stuComprehensiveInquiry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/userauth/css/selectordie_theme_01.css"/>
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/js/selectordie.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/subaddTab.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/userauth/js/stuComprehensiveInquiry.js"></script>
</head>
<body>
<section>
  <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_KJK_ADMIN' or LOGIN_INFO.vUserDetailInfo.role_code eq 'R_SYS_SCHOOL'}">
   <!--<input type="hidden" id="hos_name" />
    <input type="hidden" id="hos_id" />  --> 
    <div class="left_nav" onclick="backOrgaInfo();">
        <span title="点击展开"> >></span>
        <b>
            科<br/>室<br/>列<br/>表
        </b>
    </div>
  </c:if>
    <div class="content">
        <div class="inquiry">
            学生姓名：<input id="stu_name" type="text" class="form-control" style="margin-right: 20px;height: 34px"/>
            学生类型：
            <div class="select" id="se">
            <select class="myselect" id="stu_type" >
            </select>
            </div>
            <a class="layui-layer-btn0" onclick="seachInfo();" style="margin-left:30px;position:relative;top:-2px">搜索</a>
            <a class="layui-layer-btn1" onclick="resSeach();" style="position:relative;top:-2px">重置</a>
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_KJK_ADMIN' or LOGIN_INFO.vUserDetailInfo.role_code eq 'R_SYS_SCHOOL'}">
            <b><span class="glyphicon glyphicon-home" style="font-size: 20px"></span>当前科室：<span class="departName">暂无</span></b>
            <input type="hidden" id="orga_id"  />
         </c:if>
        </div>
        <div class="form_cont dtlBox" id="pageInfo" style="position:relative;height:100%;">
        </div>
    </div>
</section>
</html>