<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet"href="<tm:ctx/>/jsp/web/score/css/gradProgress.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/jsp/web/score/js/graCheckInfor.js"></script>
</head>
<body>
    <section style="width: 1000px;padding: 10px;">
        <div class="lTitleDiv">
            <div class="hr"></div>
            <h5 class="lTitle">基本信息</h5>
        </div>
        <div class="basicInformation">
            <table>
                <tr>
                    <td>学生姓名：</td>
                    <td><b id="name">张三</b></td>
                    <td>&nbsp;&nbsp;&nbsp;学生类型：</td>
                    <td><b id='stuType'></b></td>
                </tr>
                <tr>
                    <td>剩余天数：</td>
                    <td><b id='lastDay'>15天</b></td>
                    <td>大纲完成度：</td>
                    <td><b id='completion_rate'></b></td>
                </tr>
            </table>
        </div>
        <div class="lTitleDiv">
            <div class="hr"></div>
            <h5 class="lTitle">表单配置</h5>
        </div>
        <div class="form">
            <span>
                <a>
                    <img src="img/unfinished.png" alt=""/>
                </a>
                <i>表单名称名称名称</i>
            </span>
            <span>
                <a>
                    <img src="img/finished.png" alt=""/>
                </a>
                <i>表单名称名称名称</i>
            </span>
            <span>
                <a>
                    <img src="img/unfinished.png" alt=""/>
                </a>
                <i>表单名称名称名称</i>
            </span>
            <span>
                <a>
                    <img src="img/finished.png" alt=""/>
                </a>
                <i>表单名称名称名称</i>
            </span>
        </div>
        <div class="lTitleDiv">
            <div class="hr"></div>
            <h5 class="lTitle">审核意见</h5>
        </div>
        <div class="comment">
            <textarea name="" id="" cols="30" rows="10"style="width: 100%"></textarea>
        </div>
        <div class="btns">
            <a class="layui-layer-btn0">出科</a>
        </div>
    </section>
</body>
</html>