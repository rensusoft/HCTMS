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
<script src="<tm:ctx />/jsp/web/userauth/js/userAddEdit.js"></script>
<script src="<tm:ctx/>/js/select2.js"></script>
<script src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>

<body style="overflow: hidden;overflow-y: auto;">
<input type="text"  id="textalis" value="${tips}" hidden="hidden"/>
	<form action="<tm:ctx/>/userauthweb/usersave.action"
		onsubmit="return validate(this)" method="post">
		<div class="info_table">
			<input type="hidden" name="action" id="action" value="${action}">
			<input type="hidden" name="userId" id="userId"
				value="${userInfo.user_id}"> <input type="hidden"
				name="studentId" id="studentId" value="${studentInfo.id}"> <input
				type="hidden" name="staffid" id="staffid" value="${staffInfo.id }">
			<ul>
				<li><span style="color:red;">* </span>用户工号：</li>
				<li>
				<c:choose>
					<c:when test="${userInfo.user_code eq null}">
				<input class="form-control" name="userCode" id="userCode"
					value="${userInfo.user_code}" required></li>
					</c:when>
					<c:otherwise>
					<input class="form-control" name="userCode" id="userCode" readonly="readonly"
					value="${userInfo.user_code}" required></li>
					</c:otherwise>
				</c:choose>
				<li><span style="color:red;">* </span>用户名称：</li>
				<li><input class="form-control" name="userName" id="userName"
					value="${userInfo.user_name}" required></li>
				<li>手机号码：</li>
				<li><input class="form-control" name="mobile" id="mobile"
					value="${userInfo.mobile}"></li>
				<li><span style="color:red;">* </span>身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</li>
				<li><select name='identity' class="form-control"
					onchange="addDiv();" id="inform">
						<c:choose>
							<c:when test="${userInfo.identity eq  'T'}">
								<option value='S'>学生</option>
								<option value='T' selected="selected">非学生</option>
							</c:when>
							<c:otherwise>
								<option value='S' selected="selected">学生</option>
								<option value='T'>非学生</option>
							</c:otherwise>
						</c:choose>
				</select></li>
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
			</ul>
			<div id="information" class="info_box">
				<table>
					<tr>
						<td>性别：</td>
						<td><input class="form-control" type="text" name="stu_sex"
							value="${studentInfo.stu_sex}"></td>
					</tr>
					<tr>
						<td>年龄：</td>
						<td><input class="form-control" type="text" name="stu_age"  id="age"
							value="${studentInfo.stu_age}"></td>
					</tr>
					<tr>
						<td>出生日期：</td>
						<td><input class="form-control" type="text"
							name="stu_birthday" value="${studentInfo.stu_birthday}"></td>
					</tr>
					<tr>
						<td>国籍：</td>
						<td><input class="form-control" type="text"
							name="stu_country" value="${studentInfo.stu_country}"></td>
					</tr>
					<tr>
						<td>民族：</td>
						<td><input class="form-control" type="text"
							name="stu_nationality" value="${studentInfo.stu_nationality}"></td>
					</tr>
					<tr>
						<td>籍贯：</td>
						<td><input class="form-control" type="text" name="stu_native"
							value="${studentInfo.stu_native}"></td>
					</tr>


					<tr>
						<td>户口地址：</td>
						<td><input class="form-control" type="text"
							name="stu_hk_address" value="${studentInfo.stu_hk_address}"></td>
					</tr>
					<tr>
						<td>居住地址：</td>
						<td><input class="form-control" type="text"
							name="stu_home_address" value="${studentInfo.stu_home_address}"></td>
					</tr>
					<tr>
						<td>政治面貌：</td>
						<td><input class="form-control" type="text"
							name="political_status" value="${studentInfo.political_status}"></td>
					</tr>
					<tr>
						<td>兴趣特长：</td>
						<td><input class="form-control" type="text"
							name="interesting_speciality"
							value="${studentInfo.interesting_speciality}"></td>
					</tr>
					<tr>
						<td>所属院校名称：</td>
						<td><input class="form-control" type="text"
							name="stu_school_name" value="${studentInfo.stu_school_name}"></td>
					</tr>
					<tr>
						<td>学号：</td>
						<td><input class="form-control" type="text" name="stu_num"
							value="${studentInfo.stu_num}"></td>
					</tr>
					<tr>
						<td>届次：</td>
						<td><input class="form-control" type="text" name="stu_class" id="stuClass"
							value="${studentInfo.stu_class}"></td>
					</tr>
					<tr>
						<td>学生状态：</td>
						<td><input class="form-control" type="text" name="stu_status"
							value="${studentInfo.stu_status}"></td>
					</tr>
					<tr>
						<td>职务：</td>
						<td><input class="form-control" type="text"
							name="stu_position" value="${studentInfo.stu_position}"></td>
					</tr>
					<tr>
						<td>文化水平：</td>
						<td><input class="form-control" type="text"
							name="stu_edu_level" value="${studentInfo.stu_edu_level}"></td>
					</tr>
					<tr>
						<td>专业名称：</td>
						<td><input class="form-control" type="text"
							name="stu_major_name" value="${studentInfo.stu_major_name}"></td>
					</tr>
					<tr>
						<td>专业方向：</td>
						<td><input class="form-control" type="text"
							name="stu_major_direction"
							value="${studentInfo.stu_major_direction}"></td>
					</tr>
					<tr>
						<td>规培生类型：</td>
						<td><input class="form-control" type="text" name="stu_type"
							value="${studentInfo.stu_type}"></td>
					</tr>
					<tr>
						<td>上级医师名称：</td>
						<td><input class="form-control" type="text"
							name="sup_doc_name" value="${studentInfo.sup_doc_name}"></td>
					</tr>
					<tr>
						<td>导师姓名：</td>
						<td><input class="form-control" type="text" name="tutor_name"
							value="${studentInfo.tutor_name}"></td>
					</tr>
					<tr>
						<td>导师单位：</td>
						<td><input class="form-control" type="text"
							name="tutor_workplace" value="${studentInfo.tutor_workplace}"></td>
					</tr>
					<tr>
						<td>创建人：</td>
						<td><input class="form-control" type="text" name="creator"
							value="${studentInfo.creator}"></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="form-control" type="text" name="remark"
							value="${studentInfo.remark}"></td>
					</tr>
				</table>
			</div>
			<div id="teaInformation" class="info_box">
				<table>
					<tr>
						<td>年龄：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.age}" name="age" id="age1"></td>
					</tr>
					<tr>
						<td>出生日期：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.birth}" name="birth"></td>
					</tr>
					<tr>
						<td>籍贯：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.staff_native}" name="staff_native"></td>
					</tr>
					<tr>
						<td>出生地：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.birth_place}" name="birth_place"></td>
					</tr>
					<tr>
						<td>职称：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.professional_title}" name="professional_title"></td>
					</tr>
					<tr>
						<td>职位：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.position}" name="position"></td>
					</tr>
					<tr>
						<td>政治面貌：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.political_state}" name="political_state"></td>
					</tr>
					<tr>
						<td>文化水平：</td>
						<td><input type="text" class="form-control"
							value="${staffInfo.edu_level}" name="edu_level"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="button_position">
			<input type="submit" class="layui-layer-btn0" value="保存">
			<input type="reset" class="layui-layer-btn1"
				value="重置">
		</div>
	</form>
</body>
</html>
