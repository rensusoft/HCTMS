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
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/basicdata/css/testDatabase.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/jsp/web/basicdata/js/testDatabase.js"></script>
    <title></title>
</head>
<body>
    <section>
        <div class="leftDiv">
            <p>知识库</p>
            <div>
                <ul id="treeDemo" class="ztree" style="width:260px; overflow:auto;"></ul>
            </div>
        </div>
        <div class="rightDiv">
            <p class="cotTitle">
               	 第二节
            </p>
            <input id="qkbId" name="qkbId" type="hidden" />
            <div class="searchDiv form-group">
                <label>题型：</label>
                <select id="quesType" class="form-control" style="display: inline-block;width: 150px;margin-right: 30px"></select>
                <label>分值单位：</label>
                <span style="display: inline-block;margin-right: 30px">
                    <input type="number" min="0" max="120" class="numInput" id="ssco_num" name="ssco_num" />至<input type="number" min="0" max="120" class="numInput" id="esco_num" name="esco_num" />
                </span>
                <a class="layui-layer-btn0" style="padding: 4px 26px !important;" onclick="reloadGrid()">检索</a>
                <a class="btn btn-info btnSearch" style="padding: 3px 15px;" onclick="addQues()" >新增</a>
                <a id="import_btn" class="btn btn-info btnSearch" style="padding: 3px 15px;">批量导入</a>
                <form id="exportForm" action="<tm:ctx/>/quesweb/downQuesExcel.action">
				</form>
                <a class="arr arrup"></a>
                <div class="displayDiv" style="margin-top: 5px">
                    <label>难度：</label>
                    <span style="display: inline-block;margin-right: 30px">
                        <input type="number" min="0" max="100" class="numInput" id="sdifficulty_num" />%至<input type="number" min="0" max="100" class="numInput" id="edifficulty_num" name="edifficulty_num" />%
                    </span>
                        <label>区分度：</label>
                    <span style="display: inline-block;margin-right: 30px">
                        <input type="number" min="0" max="100" class="numInput" id="sdifferent_num" name="sdifferent_num" />%至<input type="number" min="0" max="100" class="numInput" id="edifferent_num" name="edifferent_num" />%
                    </span>
                </div>
            </div>
            <div>
                <table id="QuesInfoGrid"></table>
                <div id="QuesInfoPager"></div>
            </div>
            <div class="testCont">
                <p class="testContTitle">
                   	问题详情
                    <a class="arr2 arrup2"></a>
                </p>
                <div id="quesContent" class="displayDiv"></div>

            </div>
        </div>
    </section>
</body>
</html>