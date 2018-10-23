<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@taglib uri="/hctms" prefix="tm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>医院临床教学综合管理系统</title>
<tm:jsandcss/>
<link rel="stylesheet" href="<tm:ctx/>/jsp/web/login/css/login.css"/>

<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
<script src="<tm:ctx/>/js/PublicMethed.js"></script>

<script src='<tm:ctx/>/js/MD5Util.js' type="text/javascript"></script>
<script src='<tm:ctx/>/js/SHA1Util.js' type="text/javascript"></script>
<script src='<tm:ctx/>/jsp/web/login/js/login.js' type="text/javascript"></script>

</head>
<body>
	
	<div class="div">
        <div class="div">
            <div class="top">
                <img src="<tm:ctx/>/img/login_01_01.jpg" alt=""/>
            </div>
        </div>
        <div class="div">
            <div class="c_form">
                <form action="">
                    <i>用户名：</i>
                    <span class="user">
                        <input type="text" class="loginInput" id="usercode" placeholder="用户名" value=""/>
                    </span>
                    <i>密码：</i>
                    <span class="pwd">
                        <input type="password" class="loginInput" id="password" placeholder="密码" value=""/>
                    </span>
                    <a href="javascript:;" class="login" id="loginBtn">登陆</a>
                </form>
            </div>
        </div>
        <div class="div">
            <div class="bottom">
                <img src="<tm:ctx/>/img/logo2.png" alt=""/>
            </div>
        </div>
    </div>
	
</body>

</html>
