<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>评分表详情</title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/scoreInfo.js"></script>
</head>
<body>
	<div class="dtlBox">
		<div style="text-align: center;font-size:22px;font-weight:bold;" id="name">
			
		</div>
		<!-- <a href="javascript:;" class="btn btn-primary pull-right export">导出信息</a> -->
		<div class="delTitle">
			<div class="hr"></div>
		    <h5>基本信息</h5>
		</div>
		<div class="delCon" id="main">
		</div>
        <div class="delTitle">
			<div class="hr"></div>
		    <h5>评分表</h5>
		    <a href="javascript:;" data-toggle="collapse" data-target="#detail1" class="toggleInfo">
                <span class="glyphicon glyphicon-chevron-up"></span>
		        <span class="infoText">收起更多详情</span>
		    </a>
		</div>
		<div id="detail1" class="collapse in">
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>项目</th>
						<th>评分规则</th>
						<th>分值</th>
						<th>评分</th>
					</tr>
				</thead>
				<tbody id="sub">
				</tbody>
			</table>
		</div>
    </div>
</body>
</html>