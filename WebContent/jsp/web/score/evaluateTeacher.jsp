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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/score/css/evaluate.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/jsp/web/score/js/evaluateTeacher.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>    
</head>
<body>
<section>
    <p class="ulList">
        <span class="clicked">出科审核</span>
        <span>审核流程</span>
    </p>
    <ul id="cont">
        <li class="show" style="margin-bottom:50px;">
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
             <div class="form" id="formName">
        </div>
         <div class="lTitleDiv">
            <div class="hr"></div>
            <h5 class="lTitle">审核意见</h5>
        </div>
       <div class="comment" style="margin-bottom:10px">	       
            <textarea name="" id="textId" cols="30" rows="7"style="width: 100%;margin-top:10px"></textarea> 
            <br/>
            <br/>
             <div class="score">
	        	<h5 style="border:0;display:inline;">理论考试分数:</h5><input type="text"  id="theory_sco" onKeyPress="return verifyDigital(event);"/><b style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*分数只能是百分制</b>&nbsp;&nbsp;
	        	<h5 style="border:0;display:inline;">临床考试分数:</h5><input type="text"  id="skill_sco" onKeyPress="return verifyDigital(event);"/><b style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*分数只能是百分制</b>
	        </div>           
        </div>
         <div class="btns">
            <a class="layui-layer-btn0" onclick="goGrade(1)">出科</a>&nbsp;&nbsp;
            <a class="layui-layer-btn1" onclick="turnDown(-1)">驳回</a>
        </div>
        </li>
        <li>
        <div class="progressDiv"></div>
        <!--  
        <div class="panel panel-default">
	        	
	     </div>
		<div class="about4" style="padding: 10px">
                <div class="lTitleDiv">
                    <div class="hr"></div>
                    <h5 class="lTitle">审批流程</h5>
                </div>
                <div id="process_top">
                    <table style="width:100%">
                        <tbody>
                        <tr class="flow_chart">
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="lTitleDiv">
                    <div class="hr"></div>
                    <h5 class="lTitle">审批记录</h5>
                </div>
                <div class="about4_main">
                    <div class="line">
                        <img src="<tm:ctx/>/jsp/web/score/images/stepblarrow.png" alt=""/>
                    </div>
                    <ul id="ulID">
                    </ul>
                </div>
            </div>
			-->
        </li>
    </ul>
</section>
</body>
</html>