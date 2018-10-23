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
	
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/js/ueditor/third-party/video-js/video-js.min.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/ueditor/third-party/video-js/video.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/ueditor/third-party/video-js/html5media.min.js"></script>
	
<body>
<div style="width:800px;margin:0 auto;position:relative;">
			<c:if test="${stuDailyRecord.flag eq '1'}"  > 
				<img src="<tm:ctx/>/jsp/web/teach/img/yiyue.png" style="position:absolute;right:0;top:0">
    		</c:if>
		<div style="text-align:center;">
			<h3 >培训日志</h3>
		</div>
			<div style="width:800px;margin:0 auto;font-size: 15px;text-align:right;color:#AAAAAA;">
			<label  style=" float:left;">姓名:${stuDailyRecord.userName }</label>
			<span style="margin-right:30%" >类型：${stuDailyRecord.type_id_str }</span>
			<c:if test="${stuDailyRecord.type_id==1||stuDailyRecord.type_id==3 }">
			<label   style=" float:right;margin-right:5px">时间：${stuDailyRecord.duration }</label>
			</c:if>
			<c:if test="${stuDailyRecord.type_id==2}">
			<label   style=" float:right;margin-right:5px">时间：第${stuDailyRecord.week}周${stuDailyRecord.timeBegin}至${stuDailyRecord.timeEnd}</label>
			</c:if>
			
		</div>
		<hr style="border-top:1px solid #000 !important;">
		<!-- 
		<hr style="border-top:1px solid #000 !important;margin-top:0">
		 -->
		<div style="width:800px;margin:0 auto;">
		<h4>${stuDailyRecord.content}</h4>
		</div>
	</div>

</body>
</html>