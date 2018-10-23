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
    <script src="<tm:ctx/>/jsp/web/teach/js/stuAtten.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/highcharts.js"></script>
    <title></title>
</head>
<body>
<input type="hidden" name="authId" id="authId" value="${LOGIN_INFO.vUserDetailInfo.auth_id}">
<input type="hidden" name="orgaId" id="orgaId" value="${LOGIN_INFO.vUserDetailInfo.orga_id}">
    <div style="position: absolute;top: transform: translate(-50%, 0);margin: 0 auto" id="divv">
        <form action="" class="form-inline" style="margin:15px 0 15px 30px;">
            <div class="form-group" id="keShi">
            </div>
        </form>
          <div class="attence_box" style="margin-left:40px;">
            <div class="table_box" style="display: inline-block;float:left;" >
                <table id="SelectKaoqinPanel"></table>
                <div id="pager1"></div>
            </div>
          	<div class="img_box" id='img'  style="float:right;display: inline-block;height: 650px;position: absolute;top:55px;magin-left:5px;">
                <div id="pie_box">
                </div>
            </div>
        </div>
    </div>
</body>
</html>