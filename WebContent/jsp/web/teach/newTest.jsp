<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/newTest.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.core-3.5.js"></script>
    <!-- <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.excheck-3.5.js"></script> -->
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/newTest.js"></script>
    <title></title>
</head>
<body>
    <section>
        <div class="contDiv">
            <div class="leftDiv">
                <div class="titleP">
                    知识点
                </div>
                <div class="zTreeDiv">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="rightDiv dtlBox" style="padding-left: 15px;margin: 0">
                <div class="delTitle" >
                    <div class="hr"></div>
                    <h5>知识点所占比重</h5>
                </div>
                <div class="propDiv">
                    <table id="sek"></table>
                </div>
                <div class="delTitle" >
                    <div class="hr"></div>
                    <h5>题目总数</h5>
                </div>
                <div class="propDiv">
                    <p>
                        <input type="radio" name="quesNum" value="20" />20&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="quesNum" value="50" />50&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="quesNum" value="100" />100&nbsp;&nbsp;&nbsp;&nbsp;
	                    <input type="radio" name="quesNum" checked="checked" value="其他" />
                       	 其他：<input id="quesNum" type="number" style="display: inline-block;width: 80px" min="0" onKeyPress="return verifyDigital(event);" />
                    </p>
                    <div class="btns">
                        <a class="layui-layer-btn0" onclick="submitExer()" >提交</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>