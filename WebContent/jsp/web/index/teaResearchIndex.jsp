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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/highcharts.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/highcharts.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/subaddTab.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/index/css/teaResearchIndex.css"/>
    <script src="<tm:ctx/>/jsp/web/index/js/teaResearchIndex.js"></script>
    <title></title>
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
            <div class="rotate">
                <div class="panel panel-default" style="width: 100%;">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            全院轮转概况
                        </h3>
                        <a></a>
                    </div>
                    <div class="panel-body" style="padding:0;">
                    <div>
                    	<div id="container" style="width:100%;height:100%">
                        	</div>
                    </div>
                    </div>
                </div>
            </div>
        </li>
    </ul>
    <div class="bottom_content">
        <div class="news">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        待办事宜
                    </h3>
                    <a href="javascript:void(0);" addtabs="更多消息" url="<tm:ctx/>/jsp/web/message/news.jsp" title="更多消息" target="_self">更多>></a>
                </div>
                <div class="panel-body">
                    <table id="table_news">
                    </table>
                </div>
            </div>
        </div>
        <div class="atten_img">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">学生详情</h3>
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