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
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/css/fullcalendar.css"/>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/cathedra.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/moment.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/cathedra.js"></script>
</head>
<body>
	<div class="left_btns">
        <h3>时间</h3>
        <div style="text-align:center;">
			年份：  
			<select id="cath_year" onchange="changeAndCount()">
	        	<!-- <option value="2016">2016</option>
	        	<option value="2017">2017</option>
	        	<option value="2018">2018</option> -->
	        </select>      	
        </div>
        <hr />	
        <ul id="ulmonth">
        </ul>
    </div>
    <div class="section_box">
        <p>
            <a class="hide_btn">
                <i class="glyphicon glyphicon-menu-hamburger"></i>
            </a>
        </p>
        <div class="content_box">
            <div id="calendar"></div>
        </div>
    </div>
</body>
</html>