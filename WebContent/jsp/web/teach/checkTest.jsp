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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/checkTest.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/checkTest.js"></script>
    <title></title>
</head>
<body>
    <section>
    <div class="addDiv">
        <div class="leftBtn">
            <span></span><br/>
            考<br/>
            试<br/>
            列<br/>
            表<br/>
        </div>
        <div id="wrap" style="padding: 10px 10px 10px 55px">
            <div class="dtlBox" style="width: 100%;margin: 0;position: relative">
                <div class="delTitle">
                    <div class="hr"></div>
                    <h5>基本信息</h5><input id="qeid" type="hidden" />
                </div>
                <div class="quesAnsw">
                    <table id="queConten"></table>
                </div>
                <div class="scoreDiv">
                    <span id="score"></span>分
                </div>
            </div>
            <div class="dtlBox" style="width: 100%;margin: 0;position: relative">
                <div class="delTitle">
                    <div class="hr"></div>
                    <h5>答题详情</h5>
                    <input id="stu_result" type="hidden"  />
                    <a class="btn btn-info btnSearch wrongQues" onclick="lookWrong()">全部错题</a>
                </div>
                <div class="answerCont" id="quesAnswCon"></div>
            </div>
        </div>
    </div>
</section>
</body>
</html>