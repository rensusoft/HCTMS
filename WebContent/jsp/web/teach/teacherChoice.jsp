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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/selectordie_theme_01.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/selectordie.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/teach/css/teacherChoice.css" />
    <script src="<tm:ctx/>/jsp/web/teach/js/teacherChoice.js"></script>
    <title></title>
</head>
<body>
<section style="width: 500px;height: 200px ">
    <div class="teaChoose">
        选择：
        <div class="select" id="se">
            <select class="myselect" id="select_teacher">
            <!-- 
                <option value="111">老师1</option>
                <option value="222">老师2</option>
                <option value="333">老师3</option>
                <option value="444">老师4</option>
            -->
            </select>
        </div>
    </div>
    <div class="btns">
        <a class="layui-layer-btn0" onclick="changeRotateTeacher()">确定</a>
    </div>
</section>
</body>
</html>