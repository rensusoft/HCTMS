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
    <link rel="stylesheet" type="text/css	" href="<tm:ctx/>/css/style.css" />
     <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/news.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>	
    <script src="<tm:ctx/>/jsp/web/message/js/news.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>
<body>
<section>
    <!-- 左侧表 -->
    <div class="left_btns">
        <!-- 表头-->
        <h3>消息中心</h3>
        <!-- 消息 -->
        <ul>
            <li id="li_AllMessage">
                <a href="javascript:void(0)" onclick="selectAllMessage()">
                    全部消息
                </a>
            </li>
            <li class="checked" id="li_UnreadMessage">
                <a href="javascript:void(0)" onclick="selectUnreadMessage()">
                    未读消息
                    <span class="badge" id="unread">19</span>
                </a>
            </li>
            <li id="li_ReadMessage">
                <a href="javascript:void(0)" onclick="selectReadMessage()">
                    已读消息
                </a>
            </li>
        </ul>
    </div>
    <!-- tab -->
    <div id="wrap">
        <!-- 隐藏按钮 -->
        <p style="margin-top:5px;">
            <a class="hide_btn">
                <i class="glyphicon glyphicon-menu-hamburger"></i>
                <span style="color:#505f91;font-size:20px;line-height:24px;" id="messageLei">全部消息</span>
                
            </a>
        </p>
        <div id="tit">
            <p class="select" onclick="selectLittle()" id="allMessage">全部消息</p>
            <p onclick="selectGeneralNews()">普通消息</p>
            <p onclick="selectSystemMessage()">系统消息</p>            |           
            <p onclick="selectMessage()">我发起的</p>
            <i class="send_news" onclick="add()">发送消息</i>
            <input type="text" hidden="hidden" id="roleId">
        </div>
        <ul id="con">
            <li id="allNews">
                <div class="all_news">
                    <table id="all_news"></table>
                    <div id="pager1"></div>
                </div>
            </li>
            <li id="myNews">
                <div class="sys_news">
                    <table id="my_news"></table>
                    <div id="pager4"></div>
                </div>
            </li>
        </ul>
    </div>
</section>
</body>
</html>