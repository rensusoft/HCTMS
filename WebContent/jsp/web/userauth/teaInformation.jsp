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
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/userauth/css/tea_information.css" />
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/userauth/js/teaInformation.js"></script>

	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
</head>
<body> 
 <div class="info_table">
 <input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_code }" id=userCode>
        <ul>
            <li>用户工号：</li>
            <li><input type="text" id="user_code" class="form-control" readonly/></li><li>用户名称：</li>
            <li><input type="text" id="name" class="form-control" readonly/></li><li>身份：</li>
            <li><select id="" id='identity' class="form-control" disabled>
                    <option value="">非学生</option>
                    <option value="">学生</option>
                </select>
            </li>
        </ul>
        <table>
				<tr>
					<td>年龄：</td>
					<td><input type="text" class="form-control" id="age"readonly></td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input type="text" class="form-control"id="birth"readonly></td>
				</tr>
				<tr>
					<td>籍贯：</td>
					<td><input type="text" class="form-control" id="staff_native"readonly></td>
				</tr>
				<tr>
					<td>出生地：</td>
					<td><input type="text" class="form-control"id="birth_place"readonly></td>
				</tr>
				<tr>
					<td>职称：</td>
					<td><input type="text" class="form-control" id="professional_title"readonly></td>
				</tr>
				<tr>
					<td>职位：</td>
					<td><input type="text" class="form-control" id="position"readonly></td>
				</tr>
				<tr>
					<td>政治面貌：</td>
					<td><input type="text" class="form-control" id="political_state"readonly></td>
				</tr>
				<tr>
					<td>文化水平：</td>
					<td><input type="text" class="form-control" id="edu_level"readonly></td>
				</tr>
			</table>
    </div>
</body>
</html>