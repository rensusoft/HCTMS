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
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/stuManage.css"/>
	
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/stuManage.js"></script>
</head>
<body>
<section>
    <div class="search">
        学生姓名：<input type="text" class="form-control" id="stu_name"/> <a class="btn btn-info btnSearch" style="margin-left: 20px" onclick="keySearch()">查询</a>
        <a class="resetting" onclick="reset()">重置</a>
        <span class="stateList">
            学生类型：
            <a class="clicked" id="all">全部</a>
            <a id="round_robin">轮转中</a>
            <a id="stay_out">待出科</a>
        </span>
    </div>
    
    <div class="stuInforList" id="stuInforList">
    <!-- 
    
        <div class="stuInfor">
            <b class="green"><i>轮转中</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/pass.png" alt=""/>已签到</a></i>
                </p>
            </div>
        </div>
        
        <div class="stuInfor">
            <b class="green"><i>轮转中</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/x.png" alt=""/>未签到</a></i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <b class="green"><i>轮转中</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/x.png" alt=""/>未签到</a></i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <b class="orange"><i>待出科</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/pass.png" alt=""/>已签到</a></i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <b class="orange"><i>待出科</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/x.png" alt=""/>未签到</a></i>
                </p>
            </div>
        </div>
        <div class="stuInfor">
            <b class="orange"><i>待出科</i></b>

            <div class="imgInfor">
                <span>
                    <img src="<tm:ctx/>/jsp/web/teach/img/userb.png" alt=""/>
                </span>

                <p>
                    <b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>张三</i><br/>
                    <b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>学生类型类型</i><br/>
                    <b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>2016.12.12-2017.2.12</i><br/>
                    <b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer"><span class="glyphicon glyphicon-user"></span> 张老师</a></i><br/>
                    <b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer"><img src="<tm:ctx/>/jsp/web/teach/img/pass.png" alt=""/>已签到</a></i>
                </p>
            </div>
        </div>
         -->
    </div>
</section>
</body>
</html>