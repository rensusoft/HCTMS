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
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/operation.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/entering.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
</head>
<body>
<div class="form_cont dtlBox" style="height:200px;">
	<div id="div_content">
	    <table>
	        <tr>
	            <td>编号：</td>
	            <td>
	                <input type="text" id="a"/>
	            </td>
	        </tr>
	        <tr>
	            <td>书名：</td>
	            <td>
	                <input type="text" id="b"/>
	            </td>
	        </tr>
	        <tr>
	            <td>出版社：</td>
	            <td>
	                <input type="text" id="c"/>
	            </td>
	        </tr>
	        <tr>
	            <td>主编：</td>
	            <td>
	                <input type="text" id="d"/>
	            </td>
	        </tr>
	    </table>
	</div>
    <div class="btns">
        <a class="layui-layer-btn0" onclick="save()">保存</a>&nbsp;&nbsp;<a class="layui-layer-btn1" onclick="reset()">重置</a>
    </div>
</div>
</body>
</html>