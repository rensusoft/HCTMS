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
	<script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css"/>
	
	<script type="text/javascript" src="<tm:ctx/>/js/datepicker.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/select2.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/teach/css/cathedra.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/fullcalendar.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/moment.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/newSchedule.js"></script>
</head>
<body>
<table class="lecture">
    <tr>
        <td>讲座标题：</td>
        <td>
            <input type="text" id="cath_title"/>
        </td>
        <td>主讲人：<select onChange="changeToInput(this)" id="select_position"><option value="inside">院内</option><option value="outside">院外</option></select></td>
        <td id="change_input">
            <input class="myInput" name="nameInput" placeholder="关键字查询" id="speaker_name" style="width: 200px;text-align:center;margin-right:20px;"/>
            <input type="hidden" id="speaker" name="speaker" />
        </td>
    </tr>
    <tr>
        <td>
            讲座日期：
        </td>
        <td>
            <span class="clickDay" id="cath_date"></span>
        </td>
        <td>
            讲座时间：
        </td>
        <td>
            <select name="" id="cath_time">
                <option value="7:00">7:00</option>
                <option value="7:30">7:30</option>
                <option value="7:30">8:00</option>
                <option value="8:30">8:30</option>
                <option value="9:00">9:00</option>
                <option value="9:30">9:30</option>
                <option value="10:00">10:00</option>
                <option value="10:30">10:30</option>
                <option value="11:00">11:00</option>
                <option value="11:30">11:30</option>
                <option value="12:00">12:00</option>
                <option value="12:30">12:30</option>
                <option value="13:00">13:00</option>
                <option value="13:30">13:30</option>
                <option value="14:00">14:00</option>
                <option value="14:30">14:30</option>
                <option value="15:00">15:00</option>
                <option value="15:30">15:30</option>
                <option value="16:00">16:00</option>
                <option value="16:30">16:30</option>
                <option value="17:00">17:00</option>
                <option value="17:30">17:30</option>
                <option value="18:00">18:00</option>
                <option value="18:30">18:30</option>
                <option value="19:00">19:00</option>
                <option value="19:30">19:30</option>
                <option value="20:00">20:00</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>讲座地点：</td>
        <td colspan="3">
            <input type="text" id="cath_place"/>
        </td>
    </tr>
    <tr>
        <td>学生届次：</td>
        <td colspan="3" id="stu_class">
        </td>
    </tr>
    <tr>
        <td>学生类型：</td>
        <td colspan="3" id="stu_type">
        </td>
    </tr>
    <tr>
        <td>讲座内容：</td>
        <td colspan="3" id="cath_content">
            <textarea name="content" id="content" style="width: 100%;height: 100%"></textarea>
            <input type="hidden" id="flag" />
            <input type="hidden" id="class_condition_id" />
            <input type="hidden" id="type_condition_id" />
        </td>
    </tr>
</table>
<div class="btns">
    <a class="layui-layer-btn0" onclick="saveCathedraPlan()" id="but_save">保存</a>&nbsp;&nbsp;&nbsp;
    <a class="layui-layer-btn1" onclick="reset()" id="but_reset">重置</a>&nbsp;&nbsp;&nbsp;
    <a class="btn btn-info btnSearch" id="btn_delete" onclick="removeId()">删除</a>
</div>	
</body>
</html>