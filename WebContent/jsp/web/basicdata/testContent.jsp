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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/basicdata/css/testContent.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/jsp/web/basicdata/js/testContent.js"></script>
    <title></title>
</head>
<body>
    <section style="width: 800px">
        <div class="searchDiv form-group">
        	<input id="qid" name="qid" type="hidden" />
            <label>所属章节：</label>
            <select id="knowledge" class="form-control" style="display: inline-block;width: 150px;margin-right: 30px;margin-bottom: 7px;"></select>
            <label>题型：</label>
            <select id="quesType" class="form-control" style="display: inline-block;width: 150px;margin-right: 30px" onchange="optionChange()"></select>
            <label>教学要求：</label>
            <select id="require" class="form-control" style="display: inline-block;width: 150px;margin-right: 30px">
                <option value="1">掌握</option>
                <option value="2">熟悉</option>
                <option value="3">了解</option>
            </select>
            <label>分值单位：</label>
            <input id="sco_num" type="number" min="0" max="100" class="numInput"/>
            <label>难度：</label>
            <input id="difficulty_num" type="number" min="0" max="100" class="numInput" style="margin: 0"/>%
            <label style="margin-left: 30px">区分度：</label>
            <input id="different_num" type="number" min="0" max="100" class="numInput" style="margin: 0"/>%
        </div>
        <div class="dtlBox">
            <div class="delTitle" style="padding: 0">
                <div class="hr"></div>
                <h5>题目编辑</h5>
            </div>
            <div>
                <textarea id="content" name="" style="width: 100%;height: 50px"></textarea>
            </div>
        </div>
        <div class="dtlBox">
        	<div id="optionList" style="display: none;">
	            <div class="delTitle" style="padding: 0">
	                <div class="hr"></div>
	                <h5>
	                   	选项编辑
	                    <a class="addBtn add" style="right:27px ">+</a>
	                    <a class="addBtn remove" style="right:0 ">-</a>
	                </h5>
	            </div>
	            <div id="checkAndInverCheck">
	                <table id="tb"></table>
	            </div>
            </div>
            <div class="delTitle" style="padding: 0">
                <div class="hr"></div>
                <h5>
                   	 答案解析
                </h5>
            </div>
            <div>
                <textarea id="answer" name="" style="width: 100%;height: 50px"></textarea>
                <input id="aid" type="hidden" />
            </div>
            <div style="width: 100%;text-align: center;margin-top: 30px;margin-bottom: 10px;">
                <a class="layui-layer-btn0" onclick="submitContent()">提交</a>
            </div>
        </div>
    </section>
</body>
</html>