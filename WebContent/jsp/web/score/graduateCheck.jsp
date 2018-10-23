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
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/score/css/graduateCheck.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/score/js/graduateCheck.js"></script>
</head>
<body style="height: 100%;">
<section >
    <div class="search">
        <span style="font-weight:bold;">学生姓名：</span><input id="stuName" type="text" style="width:300px;"/> <a class="layui-layer-btn0"style="margin-left: 10px" onclick="findByName()">查询</a><a class="layui-layer-btn1" style="margin-left: 10px">重置</a>
        <span class="stateList">
        <span style="font-weight:bold;">学生类型：</span>
            <a class="clicked aVlue">全部<span class="txt"   hidden="hidden">0</span></a>
            <a class="aVlue">轮转中<span class="txt" hidden="hidden">1</span></a>
            <a class="aVlue">待出科<span class="txt"   hidden="hidden">2</span></a>
        </span>
    </div>
    <div id="tabled">
    <div class="stuInforList">
        <div class="stuInfor">
            <a class="aBtn">出科>></a>
            <b class="green"><i>轮转中</i></b>
            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/score/images/userb.png" alt=""/>
                </span>
                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b><i>10天</i><br/>
                    <b>大纲完成度：</b><i>85%</i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <a class="aBtn">出科>></a>
            <b class="green"><i>轮转中</i></b>
            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/score/images/userb.png" alt=""/>
                </span>
                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b><i>10天</i><br/>
                    <b>大纲完成度：</b><i>85%</i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <a class="aBtn">查看详情>></a>
            <b class="orange"><i>待出科</i></b>
            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/score/images/userb.png" alt=""/>
                </span>
                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b><i>10天</i><br/>
                    <b>大纲完成度：</b><i>85%</i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <a class="aBtn">查看详情>></a>
            <b class="orange"><i>待出科</i></b>
            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/score/images/userb.png" alt=""/>
                </span>
                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b><i>10天</i><br/>
                    <b>大纲完成度：</b><i>85%</i>
                </p>
            </div>
        </div>
    </div>
    </div>
</section>
</body>
</html>