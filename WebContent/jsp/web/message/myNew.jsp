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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/news.css"/>
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/message/js/myNew.js"></script>

    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/news.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/news_cont.js"></script>
		
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	
</head>
<body>
    <table class="checkNews" style="font-size: 14px; font-family: '微软雅黑'">
        <tr>
            <td>标题：</td>
            <td><span id="title"></span></td>
        </tr>
        <tr>
            <td>接收人：</td>
            <td><span id="receiverName"></span></td>
        </tr>
        <tr>
            <td>发送时间：</td>
            <td><span id="sendTimeStr"></span></td>
        </tr>
        <tr>
        	<td>内容：</td>
        	<td>
        	<div class="newsContent" id="text">
    		</div>
        	</td>
        </tr>
    </table>
</body>
</html>