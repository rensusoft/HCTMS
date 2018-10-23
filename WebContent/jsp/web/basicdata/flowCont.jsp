<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height:100%">
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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/basicdata/css/rotateProcess.css" />
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/flowCont.js" ></script>
</head>
<body>
    <div class="section_list" style="background-color: #FCFCFC;">
        <ul class="list">
            <li id="clickNum" >
                <a name="TRAINPROCESS" onclick="clickNum();" target="flowCont2" href="<tm:ctx/>/basicdataweb/selectProcessRole.action?type_code=TRAINPROCESS">
                    <i><img src="<tm:ctx/>/jsp/web/basicdata/img/rotate.png" alt=""/></i>
                    <span>轮转流程</span>
                </a>
            </li>
            <li>
                <a  name="VACATE" onclick="clickNum();" target="flowCont2" href="<tm:ctx/>/basicdataweb/selectProcessRole.action?type_code=VACATE">
                    <i><img src="<tm:ctx/>/jsp/web/basicdata/img/leave.png" alt=""/></i>
                    <span>请假流程</span>
                </a>
            </li>
        </ul>
    </div>
    <iframe name="flowCont2" src="<tm:ctx/>/jsp/web/basicdata/flowCont2.jsp" frameborder="0" style="width: 79%;float: right;height:100%;">
    </iframe>
</body>
</html>