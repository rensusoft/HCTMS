<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/hctms" prefix="tm" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/homeworkStu.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
     <script src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/homeworkStu.js"></script>
     <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <title></title>
</head>
<body>
    <section>
    <div class="jqDiv">
        <div class="searchDiv">
            任务类型：
            <select class="form-control" id="task_type_code" style="display: inline-block;width: 150px;margin-right: 30px">
                <option value="">----请选择----</option>
                <option value="1">课前</option>
                <option value="2">课中</option>
                <option value="3">课后</option>
            </select>
            发布时间：<input type="text" id="start_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="display: inline-block;width:150px; height: 26px;">--<input type="text"  id="end_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="display: inline-block;width:150px;height: 26px;">
            状态：
            <select class="form-control" id="progress_state" style="display: inline-block;width: 150px;margin-right: 30px">
                <option value="">----请选择----</option>
                <option value="1">未处理</option>
                <option value="2">已处理</option>
                <option value="3">已评分</option>
            </select>
            <a class="btn btn-info btnSearch" onclick="keywordSearch();">查询</a>
        </div>
        <div>
            <table id="rotateRule"></table>
            <div id="pager1"></div>
        </div>
        <div class="rightBtn displayDiv">
            <div class="rightBtnSpan">
                <span><img src="img/arr2.png" alt=""/></span><br/>
                作<br/>
                业<br/>
                做<br/>
                答<br/>
            </div>
            <div class="homeworkCont displayDiv">
                <table>
                    <tr>
                        <td style="width: 100px;text-align: right">作业标题：</td>
                        <td style="padding: 5px 0;" id="title"></td>
                    </tr>
                    <tr>
                        <td style="width: 100px;text-align: right">作业要求：</td>
                        <td style="padding: 5px 0;" id="content"></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 30px;text-align: left" colspan="2">学生作答：</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding:0 30px"><textarea name="contents" id="contents" style="width: 100%;height: 250px"></textarea></td>
                    </tr>
                </table>
                <div class="btns">
                    <a class="layui-layer-btn0" onclick="saveContent();" >保存</a>
                </div>
            </div>
        </div>
    </div>
</section>
    
</body>
</html>