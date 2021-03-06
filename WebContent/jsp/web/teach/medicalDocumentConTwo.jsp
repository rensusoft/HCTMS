<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
	<title></title>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocumentCon.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/medicalDocumentConTwo.js"></script>
</head>
<body>
  <section>
    <div class="medicalDocumentCont">
        <h3>入院记录</h3>
        <div class="perInfor form-group">
            姓名：<span class="contSpan" id="p_name">${medicalRecord.p_name}</span>
            性别：<span class="contSpan" id="p_sex">${medicalRecord.p_sex}</span>
            年龄：<span class="contSpan" id="p_age">${medicalRecord.p_age}</span>
            科室：<span class="contSpan" id="p_deptname">${medicalRecord.p_deptname}</span>
            床号：<span class="contSpan" id="p_bednum">${medicalRecord.p_bednum}</span>
            住院号：<span class="contSpan" id="p_pid">${medicalRecord.p_pid}</span>
        </div>
        <div class="tableDiv" style="min-height:200px">
            ${medicalRecord.content}
        </div>
        <div>
            <table id="medicalDiagnose"></table>
            <div id="pager1"></div>
        </div>
    </div>
</section>
</body>
</html>