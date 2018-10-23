<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocument.css"/>
     <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/medicalDocumentTea.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <title></title>
</head>
<body>
 <section>
        <div class="jqDiv" style="margin: 10px 0px 0px 20px;" >
            <div class="searchDiv">
                书写时间 :<input type="text" id="start_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
    -- <input type="text" id="end_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
              <button class="btnSearchb btn-infob"  onclick="keywordSearch();">搜索</button>
            </div>
            <div>
                <table id="rotateRule"></table>
                <div id="pager1"></div>
            </div>
        </div>
    </section>
</body>
</html>