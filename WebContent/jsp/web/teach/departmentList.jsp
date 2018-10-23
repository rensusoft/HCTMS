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
    <link rel="stylesheet" href="<tm:ctx/>/css/jquery-ui.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/selectordie_theme_01.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/js/datepicker.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/selectordie.min.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/departmentList.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/departmentList.js"></script>
    <title></title>
</head>
<body>
<section>
<c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_SYS_SCHOOL'}">
    <div class="left_nav" onclick="location='<tm:ctx/>/jsp/web/teach/hospitalList.jsp'">
        <span title="点击展开"> >></span>
        <b>
            附<br/>属<br/>医<br/>院<br/>列<br/>表
        </b>
    </div>
  </c:if>
    <div class="content">
        <div class="inquiry">
            科室名称：<input id="orga_name" type="text" class="form-control" style="margin-right: 20px;height: 34px"/>
            <a class="layui-layer-btn0" onclick="selectByOrga()" style="margin-left:30px;position:relative;top:-2px">搜索</a>
            <a class="layui-layer-btn1" onclick="resOrga()" style="position:relative;top:-2px">重置</a>
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_SYS_SCHOOL'}">
         <b><span class="glyphicon glyphicon-home" style="font-size: 20px"></span>当前医院：<span class="departName">暂无</span></b>
         </c:if>
        </div>
        <div class="departmentList" style="position:relative;height:100%;" id="pageInfo">
        </div>
    </div>
</section>
<script>

</script>
</body>
</html>