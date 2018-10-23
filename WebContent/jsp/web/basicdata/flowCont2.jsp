<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height: 90%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<tm:jsandcss />
<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
<script src="<tm:ctx/>/js/custom.js"></script>
<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/publicCss.css' />
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/cui.css' />
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/bootstrap.min.css" />
<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
<script src="<tm:ctx />/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<tm:ctx/>/jsp/web/basicdata/js/flowCont2.js"></script>
<script src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/jsp/web/basicdata/css/rotateProcess.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
</head>
<body>
	<div class="main_list">
		<div style="margin-bottom: 20px" class="main_content">
		<input type="hidden" id="type_code" value="${type_code}" />
			<ul id="sortable" style="position: relative">
				<c:forEach items="${roleList}" var="roleList">
					<c:if test="${roleList.state=='X'}">
						<li class="ui-state-default opacity2">
							<div class="ot_step_main ">
								<div class="arrow">
							        <c:if test="${roleList.proc_num>0}">
									<img src="<tm:ctx />/jsp/web/basicdata/img/arrow2.png" alt="" />
								    </c:if>
								</div>
								<div class="step_main_p opacity2">
									<div class="div_num">${roleList.proc_num+1}</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<b class="proc_name">${roleList.proc_name}</b>
											 <c:if test="${roleList.proc_num>0}">
											 <a class="remove ban" onclick="disable1(this)"
												item="${roleList.end_proc_id}">禁用</a> <a
												class="conserve start" onclick="enable1(this)"
												item="${roleList.end_proc_id}">启用</a> <a class="conserve"
												name="addBut" item="${roleList.end_proc_id}">编辑</a>
												</c:if>
										</div>
										<div class="panel-body">
											<p>
												角色：<i>${roleList.name}</i>
											</p>
											<p>
												表单：<i>${roleList.form_name}</i>
											</p>
										</div>
									</div>
								</div>
							</div>
						</li>
					</c:if>
					<c:if test="${roleList.state=='Y'}">
						<li class="ui-state-default ">
							<div class="ot_step_main ">
								<div class="arrow">
							    <c:if test="${roleList.proc_num>0}">
									<img src="<tm:ctx />/jsp/web/basicdata/img/arrow2.png" alt="" />
								</c:if>
								</div>
								<div class="step_main_p ">
									<div class="div_num">${roleList.proc_num+1}</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<span style="width:200px;display:inline-block;" ><b class="proc_name">${roleList.proc_name}</b></span>
											 <c:if test="${roleList.proc_num>0}">
											 <c:if test="${roleList.require_datenum>0}">
											 <span style="margin-left:100px;color:red;">&lt;=${roleList.require_datenum}天，此审核最终通过</span>
											 </c:if>
											<a class="remove ban" onclick="disable1(this)"
												item="${roleList.end_proc_id}">禁用</a> <a
												class="conserve start" onclick="enable1(this)"
												item="${roleList.end_proc_id}">启用</a> <a class="conserve"
												name="addBut" onclick="add(this)"
												item="${roleList.end_proc_id}">编辑</a>
												</c:if>
										</div>
										<div class="panel-body">
											<p>
												角色：<i>${roleList.name}</i>
											</p>
											<p>
												表单：<i>${roleList.form_name}</i>
											</p>
										</div>
									</div>
								</div>
							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>