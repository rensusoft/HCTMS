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
<tm:jsandcss />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css"/>
	
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>	
    <script src="<tm:ctx/>/js/form.js"></script>
	
	
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/jsp/web/userauth/js/userManage.js"></script>	
	
</head>
<body class="bgcolor">
<div style="width:1300px;border:1px solid #ddd;margin:0 auto;" >
<br>
&nbsp;&nbsp;关键字查询 ：<input type="text" id="userCode" class="form-control" placeholder="请输入关键字" style="width:150px;display:inline-block;height: 30px">
<span style="margin-left:10px">科室：</span>
<select  id="orga"  style="width:150px;display:inline-block;" class="form-control">
<option value="">全部</option>
</select>
 <span style="margin-left:10px">角色类型：</span><select  id="role" style="width:150px;display:inline-block;" class="form-control" >
    <option value="">全部</option>
    <option value="10">学生</option>
    <option value="20">临床导师</option>
    <option value="30">教学秘书</option>
    <option value="31">大科教学秘书</option>
    <option value="40">科主任</option>
    <option value="50">科教科</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
<button class="btn btn-info btnSearch" id="qBtn" onclick="queryUser();">搜索</button>
&nbsp;&nbsp;&nbsp;&nbsp;
<button class="btn btn-info btnSearch" id="clear" onclick="clearUser();">重置</button>
 <div class="editControl drop-con" style="max-width:126px; float:right; margin-right:10px;">
        <a class="btn btn-primary drop_btn" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批量导入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span></a>
        <ul class="dropBox" style="width:126px;">
            <!-- <li><a href="javascript:;" onclick="downExcel()">学生模板下载</a></li>
            <li>
            <a href="javascript:;" onclick="uploadExcel()">学生数据上传</a></li> -->
             <li><a href="javascript:;" onclick="downTeacherExcel()">教职工模板下载</a></li>
            <li>
            <a href="javascript:;" onclick="uploadTeacherExcel()">教职工数据上传</a></li>
        </ul>
    </div>
<form id="exportForm" action="<tm:ctx/>/userauthweb/downUserInfo.action">
</form>
<form id="exportTeachereForm" action="<tm:ctx/>/userauthweb/downTeacherUserInfo.action">
</form>
<hr style=" height:2px;border:none;border-top:1px solid #ddd; color:#FFF;"/>
		<div style="float:left;width: 600px;margin: 0 auto;margin-top:10px;">
			<div style="border: 0px solid #C0C0C0;" >
				<table id="userInfoGrid"></table>
   		    	<div id="userInfoToolbar"></div>
			</div>
		</div>
		<div style="border: 0px solid #C0C0C0;float:right;width: 695px;margin: 0 auto;margin-top:10px;" >
				<table id=informationGrid></table>
   		    	<div id="informationToolbar"></div>
		</div>
	</div>
	<div class="contextMenu" id="myMenu" style="display:none"> 
	    <ul style="width: 200px;">
	        <li id="userInfoUpdate"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana">修改</span> 
	        </li>
	         <li id="userInfoDelete"> 
	            <span class="ui-icon ui-icon-pencil" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana">删除</span> 
	        </li>
	    </ul> 
	</div> 
	<input type="text" value="${a}" id="a" hidden="hidden">
	<input type="text" value="${userId}" id="userId" hidden="hidden">
</body>
</html>
