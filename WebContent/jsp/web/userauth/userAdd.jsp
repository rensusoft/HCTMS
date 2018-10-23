<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<tm:jsandcss />
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/publicCss.css' />
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/css/cui.css' />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
<link rel="stylesheet" type="text/css" media="screen"
	href='<tm:ctx/>/jsp/web/userauth/css/addUserInformation.css' />
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<tm:ctx/>/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css' />
<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css' />
<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
<script src='<tm:ctx />/js/MD5Util.js' type="text/javascript"></script>
<script src='<tm:ctx />/js/SHA1Util.js' type="text/javascript"></script>
<script src="<tm:ctx />/jsp/web/userauth/js/userAdd.js"></script>
<script src="<tm:ctx/>/js/select2.js"></script>
<script src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>

<body style="overflow: hidden;overflow-y: auto;">
<input type="text"  id="textalis" value="${tips}" hidden="hidden"/>
		
		<div class="info_table">
			<input type="hidden" name="action" id="action" value="${action}">
			<input type="hidden" name="userId" id="userId" value="${userInfo.user_id}"> 
			<input type="hidden" name="studentId" id="studentId" value="${studentInfo.id}">
		    <input type="hidden" name="staffid" id="staffid" value="${staffInfo.id }">
		    <input type="hidden" name="stu_class" id="stu_class" value="" >
			<ul>
				<li><span style="color:red;">* </span>工号：</li>
				<li><input class="form-control" name="userCode" id="userCode"></li>
				<li><span style="color:red;">* </span>用户名称：</li>
				<li><input class="form-control" name="userName" id="userName"></li>
				<li>手机号码：</li>
				<li><input class="form-control" name="mobile" id="mobile"></li>
				<li id="stuType"><span style="color:red;">* </span>学生类型：</li>
				<li id="stuTypeName"><select name="stu_type_id" class="form-control" id="stu_type_id">
				<option value="-1">--请选择学生类型--</option>
								<c:forEach items="${ stuTypeList}" var="i">
						<c:choose>
							<c:when test="${stuAuth.stu_type_id eq  i.id}">
								<option value="${i.id }" selected="selected">${i.type_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.id }">${i.type_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select></li>
				<li id="configName"><span style="color:red;">* </span>轮转方案：</li>
				<li id="configs">
				 <select name="configs" class="form-control" id="configs_id">
				<option value="-1">--请选择轮转方案--</option>
								<c:forEach items="${ configs}" var="i">
								<option value="${i.id }">${i.name}</option>
					</c:forEach>
				</select></li>
			</ul>
			<div id="information" class="info_box">
				<table>
					<tr>
						<td>性别：</td>
						<td><input class="form-control" type="text" id="stu_sex"></td>
					</tr>
					<tr>
						<td>年龄：</td>
						<td><input class="form-control" type="text"  id="stu_age"></td>
					</tr>
					<tr>
						<td>出生日期：</td>
						<td><input class="form-control" type="text" id="stu_birthday"></td>
					</tr>
					<tr>
						<td>国籍：</td>
						<td><input class="form-control" type="text" id="stu_country" ></td>
					</tr>
					<tr>
						<td>民族：</td>
						<td><input class="form-control" type="text" id="stu_nationality" ></td>
					</tr>
					<tr>
						<td>籍贯：</td>
						<td><input class="form-control" type="text" id="stu_native"></td>
					</tr>
					<tr>
						<td>户口地址：</td>
						<td><input class="form-control" type="text" id="stu_hk_address" ></td>
					</tr>
					<tr>
						<td>居住地址：</td>
						<td><input class="form-control" type="text" id="stu_home_address" ></td>
					</tr>
					<tr>
						<td>政治面貌：</td>
						<td><input class="form-control" type="text" id="political_status" ></td>
					</tr>
					<tr>
						<td>兴趣特长：</td>
						<td><input class="form-control" type="text" id="interesting_speciality"></td>
					</tr>
					<tr>
						<td>所属院校名称：</td>
						<td><input class="form-control" type="text" id="stu_school_name" ></td>
					</tr>
					<tr>
						<td>学号：</td>
						<td><input class="form-control" type="text" id="stu_num"></td>
					</tr>
					<tr>
						<td>学生状态：</td>
						<td><input class="form-control" type="text" id="stu_status"></td>
					</tr>
					<tr>
						<td>职务：</td>
						<td><input class="form-control" type="text" id="stu_position" ></td>
					</tr>
					<tr>
						<td>文化水平：</td>
						<td><input class="form-control" type="text" id="stu_edu_level" ></td>
					</tr>
					<tr>
						<td>专业名称：</td>
						<td><input class="form-control" type="text" id="stu_major_name" ></td>
					</tr>
					<tr>
						<td>专业方向：</td>
						<td><input class="form-control" type="text" id="stu_major_direction"></td>
					</tr>
					<tr>
						<td>上级医师名称：</td>
						<td><input class="form-control" type="text" id="sup_doc_name" ></td>
					</tr>
					<tr>
						<td>导师姓名：</td>
						<td><input class="form-control" type="text" id="tutor_name"></td>
					</tr>
					<tr>
						<td>导师单位：</td>
						<td><input class="form-control" type="text" id="tutor_workplace"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="button_position">
			<input type="submit" class="layui-layer-btn0" value="保存" onclick="stuSave();">
			<input type="reset" class="layui-layer-btn1"
				value="重置">
		</div>
</body>
</html>
