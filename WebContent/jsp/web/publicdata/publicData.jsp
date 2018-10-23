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
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/publicdata/js/publicData.js"></script>
</head>
<body>
	<span style="display:none;" id="roleCode">${LOGIN_INFO.vUserDetailInfo.role_code}</span>
	<div style="width:99%;margin: 0 auto;" >
	<div style="margin:5px 0px;float:left;">
           &nbsp;关键字 ：<input type="text" id="searchStr" class="form-control" placeholder="请输入关键字" style="width:350px;display:inline-block">&nbsp;
    <button class="btn btn-info btnSearch"  onclick="keywordSearch();">搜索</button>
    </div>
      <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_KJK_ADMIN'}"  > 
    <div style="margin:15px 100px;float:left;">
    	<a href="javascript:void(0);" onclick="changingOver();" id="allOrMine">我发布的资料</a>
    </div>
    </c:if>
    <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code eq 'R_KJK_ADMIN' || LOGIN_INFO.vUserDetailInfo.role_code eq 'R_SYS_ADMIN' }"  >
    <div style="margin:5px 0px;float:right;">
    	<button class="btn btn-info btnSearch" onclick="releasePublicData();">+发布资料</button>
    </div>
    </c:if>
    <div style="clear:both;"></div>
		<table id="PublicDataGrid" style="margin-top:4px;"></table>
   		<div id="PublicDataToolbar"></div>
    </div>
</body>
</html>