<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href='<tm:ctx/>/jsp/web/userauth/css/modifyPwd.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src='<tm:ctx />/js/MD5Util.js' type="text/javascript"></script>
	<script src='<tm:ctx />/js/SHA1Util.js' type="text/javascript"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/userauth/js/modifyPwd.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	
	
</head>
<body>
<br />
<form action="">
<div style="margin-left:25px ;">
  <table style="margin: 0 auto;">
	<tr height="30px"><td><span style="color:red;">* </span>旧密码：</td>
	<td><input type="password" name="pwd" id="pwd" class="form-control" required /></td>
	</tr>
	<tr height="30px"><td><span style="color:red;">* </span>新密码：</td>
	<td ><input type="password" name="new_pwd1" id="new_pwd1" class="form-control" required /></td>
	</tr>
	<tr height="30px"><td><span style="color:red;">* </span>确认新密码：</td>
	<td ><input type="password" name="new_pwd2" id="new_pwd2" class="form-control" required />
	<input type="text" hidden="hidden" id="userId" value="${userInfo.user_id }"></td>
	</tr>
  </table>
	<br/>
	<div style="width:250px;margin: 0 auto;">		
		<button id="btn_submit"  class="layui-layer-btn0">提&nbsp;&nbsp;交</button>
		<button id="btn_reset" style="margin-left: 20px;"  class="layui-layer-btn1">重&nbsp;&nbsp;置</button>
	</div>
</div>
</form>
</body>
</html>