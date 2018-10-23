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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/testlibrary.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/testlibrary.js"></script>
    <title></title>
</head>
<body>
    <section>
        <div class="jqDiv">
            <div class="searchDiv">
                <a class="btn btn-info btnSearch">智能分析</a>
                <a class="btn btn-info btnSearch addBtn">+新增</a>
            </div>
            <div>
                <table id="StuQuesInfoGrid"></table>
                <div id="StuQuesInfoPager"></div>
            </div>
        </div>
        <div class="addDiv displayDiv">
            <div class="leftBtn">
                <span></span><br/>
                考<br/>
                试<br/>
                列<br/>
                表<br/>
            </div>
            <div id="wrap" style="padding: 10px 10px 10px 55px">
                <div class="dtlBox" style="width: 100%;margin: 0;">
                    <div class="delTitle">
                        <div class="hr"></div>
                        <h5>练习题目</h5>
                    </div>
                    <div class="quesAnsw">
                    	<input type="hidden" id="qNum"/>
                    	<input type="hidden" id="tNum"/>
                    	<input type="hidden" id="selAnswer"/>
                        <ul id="con">
                            <li class="show">
                                <div>
                                    <p class="questionP" id="quesCon"></p>
                                    <input type="hidden" id="aid" />
                                    <input type="hidden" id="seqid"/>
                                    <div class="answerDiv" id="quesOpt"></div>
                                </div>
                                <div class="quesbtn"></div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="dtlBox" style="width: 100%;margin: 0;">
                    <div class="delTitle">
                        <div class="hr"></div>
                        <h5>答题信息</h5>
                    </div>
                    <div class="quesAnsw">
                        <div class="quesNum" id="quesCount"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>