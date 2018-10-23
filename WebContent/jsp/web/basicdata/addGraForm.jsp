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
	 <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
		<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/basicdata/css/rotateProcess.css" />
		<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/addGraForm.js"></script>
</head>
<body>
<div class="form_cont" style="height:190px">
    <table style="width:100%;margin:0 auto;">
       <tr style="display:none;" id="roleName">
            <td><i>*&nbsp;</i>对象角色：</td>
            <td>
             	<input name="role" type="checkbox" value="10"/>学生
                <input name="role" type="checkbox" value="20;30"/>带教老师/教学秘书
                <input name="role" type="checkbox" value="40"/>科室主任
                <input name="role" type="checkbox" value="50"/>科教科
            </td>
        </tr>
        <tr id="fristDiv">
        <td>表单选择：</td>
		<td><input id="namestr" type="text" style="width: 200px"/>
		<input id="type_id" type="hidden" />
		<input id="from_id" type="hidden" />
		<a class="btn btn-info btnSearch" id="addForm" onclick="addForm();">添加</a></td>
        </tr>
        <tr id="divHide">
            <td colspan="2" style="text-align: left">
                <div id="form_content"></div>
            </td>
        </tr>
    </table>
    <a class="layui-layer-btn0" onclick="submitProcess();" style="margin-top:15px">保存</a>
</div>
</body>
</html>