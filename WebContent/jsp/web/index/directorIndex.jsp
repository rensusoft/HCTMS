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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/index/css/teaIndex.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
   <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<tm:ctx/>/jsp/web/index/js/directorIndex.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/subaddTab.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
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
            <div class="list_p">
                <p>
                <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/students.png"/></i>
                    <b>
                    <span style="font-size: 20px;">
                    <a style="display:inline-block;border-bottom:1px solid #ddd;line-height:32px;vertical-align:bottom;">待入科学生数:<b id="enrollStuCounts" class="orangeNum"></b>人</a><br/>
                    <a style="display:inline-block;border-bottom:1px solid #ddd;line-height:32px;vertical-align:bottom;">轮转中学生数:<b id="stuCount" class="orangeNum" ></b>人</a><br/>
                    <a style="display:inline-block;vertical-align:bottom;line-height:32px;">待出科学生数:<b id="gradStuCount" class="orangeNum"></b>人</a><br/>
                  </span>  
                    </b>
                
                </p>
				<p onclick="shuJu()" style="cursor:pointer;">
                    <i class="bg_img">
                    	<img src="<tm:ctx/>/jsp/web/index/images/check.png"/>
                    </i>
                    <b>
                        <span><a id="teaCount" style="color:#E6821B"></a></span>
                        <span>带教老师</span>
                    </b>
                </p>              
            </div>
        </li>
        <li>
            <div class="list_p">
            	
                <p onclick="chuKe()"  style="cursor:pointer; ">
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/graduation.png"/>
                        <b id="bell"><a onclick="chuKe()"><span id="_bell" class="glyphicon glyphicon-bell belldark"></span></a></b>
                    </i>
                    <b>
                        <span><a id="chuKe" style="color:#E6821B">0</a></span>
                        <span>出科审核</span>
                    </b>
                </p>
                  <p   style="cursor:pointer; ">
                    <i class="bg_img"><img src="<tm:ctx/>/jsp/web/index/images/leave1.png"/></i>                
                    <b>
                        <span><a  style="color:#E6821B">0</a></span>
                        <span>请假审核</span>
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
                    <a href="javascript:;" addtabs="更多消息" url="<tm:ctx/>/jsp/web/message/news.jsp" title="更多消息" target="_self">更多>></a>
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
                    <h3 class="panel-title">学生详情</h3>
                </div>
                <div class="panel-body" id="studentDetails">  
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