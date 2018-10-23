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
<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/index/css/stuIndex.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<tm:ctx/>/jsp/web/index/js/stuIndex.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/subaddTab.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>
<body style="overflow:hidden">
<section>
    <ul class="top_content">
        <li>
            <div id="date">
                <p class="year">
                    <i class="yearNum"></i>年
                    <i class="monthNum"></i>月
                </p>

                <p class="day">

                </p>

                <p class="week">
                    星期<i class="weekNum"></i>
                </p>
            </div>
        </li>
        <li>
            <div class="list_p">
                <p id="trainDeptDiv">
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/Department.png"/></i>
                    <b>
                        <a>
                            <span id="orgaIdNow"></span>
                            <span><i id="orgaIdNext"></i></span>
                        </a>
                    </b>
                </p>

                <p onclick="getOutLine()"  style="cursor:pointer; ">
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/content.png"/></i>
                    <b>
                        <a>
                            <span id="outlineCom"></span>
                            <span>大纲完成度</span>
                        </a>
                    </b>
                    <progress max="100" style="border:0;" id="progress"></progress>
                </p>
            </div>
        </li>
        <li>
            <div class="list_p">
                <p>
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/Teacher.png"/></i>
                    <b>
                        <span style="centre;font-size:25px">带教老师：<i id="teacher" style="font-size:35px"></i></span>
                    </b>
                </p>

                <p>
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/time.png"/></i>
                    <b>
                        <a>
                            <span id="noDays">剩余<i id="days" style="color:#7DC64F;"></i>天出科</span>
                            <span>轮转进程</span>
                        </a>
                    </b>
                </p>
            </div>
        </li>
    </ul>
    <div class="bottom_content">
        <div class="news">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        消息
                    </h3>
                    <a href="javascript:;" addtabs="消息中心" url="<tm:ctx/>/jsp/web/message/news.jsp" title="消息中心" target="_self">更多>></a>
                </div>
                <div class="panel-body">
                    <table id="toDoTable">
                    </table>
                </div>
            </div>
        </div>
        <div class="atten_img">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                    考勤记录
                    </h3>
                     <a href="javascript:;" addtabs="考勤查询" url="<tm:ctx/>/jsp/web/teach/stuAtten.jsp" title="考勤查询" target="_self" style="float:right;">查看详情</a>
                </div>
                <div class="panel-body">
                    <canvas id="canvas_circle" width="500" height="300">
                        浏览器不支持canvas
                    </canvas>
                </div>
            </div>

        </div>
    </div>
</section>
</body>
</html>