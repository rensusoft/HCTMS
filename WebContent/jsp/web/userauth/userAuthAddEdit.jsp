<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院临床教学综合管理系统V1.0</title>
<tm:jsandcss />

<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />

<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/publicCss.css' />
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/cui.css' />

<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/style.css" />

<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
<script src="<tm:ctx/>/js/select2.js"></script>

<script src="<tm:ctx/>/js/custom.js"></script>
<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>


<script src="<tm:ctx />/jsp/web/userauth/js/userAuthAddEdit.js"></script>
</head>


<body style="text-align: center">
	<form
		action="<tm:ctx/>/userauthweb/userauthsave.action?authId=${authId }"
		onsubmit="return validate(this)"
		style="display: inline-block; margin: 0 auto; margin-top: 40px; width: 250px; position: absolute; top: 0; left: 50%; transform: translate(-50%, 0);">
		<input type="hidden" name="action" id="action" value="${action}">
		<input type="hidden" name="userId" id="userId" value="${userId}">
		<input type="hidden" name="authId" id="authId"
			value="${userDetail.auth_id}"> 
			<c:if test="${roleType=='teacher'}">
			角色名称：<select name="roleId" class="form-control" style="display: inline-block; width: 150px;" id="roleId">
					<option value="-1">----请选择角色----</option>
					<c:forEach items="${ teacherListRole}" var="i">
						<c:choose>
							<c:when test="${userDetail.role_id eq  i.role_id}">
								<option value="${i.role_id }" selected="selected">${i.role_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.role_id }">${i.role_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				</c:if>
			<c:if test="${roleType=='student'}">
			角色名称：<select name="typeId"
			class="form-control" style="display: inline-block; width: 150px;"
			id="typeId">
					<c:forEach items="${ stuTypeList}" var="i">
						<c:choose>
							<c:when test="${userDetail.stu_type_id eq  i.id}">
								<option value="${i.id }" selected="selected">${i.type_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.id }">${i.type_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
			</c:if> 
			<br> 
			<br> 组织机构：<select name="orgaId" class="form-control"
			style="display: inline-block; width: 150px;" id="orgaId">
			<option value="-1">-请选择组织机构-</option>
			<c:forEach items="${listOrga }" var="i">
				<c:choose>
					<c:when test="${userDetail.orga_id eq  i.orga_id}">
						<option value="${i.orga_id }" selected="selected">${i.orga_name}</option>
					</c:when>
					<c:otherwise>
						<option value="${i.orga_id }">${i.orga_name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select><br><br><br>
		<input type="submit"
			class="layui-layer-btn0" value="保存">&nbsp;&nbsp; <input
			type="reset" class="layui-layer-btn1" value="重置">&nbsp;&nbsp;
	</form>
</body>
</html>