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
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/pending.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script src="<tm:ctx/>/js/select2.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/pending.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/checkInfor.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
</head>
<body style="overflow: hidden;"> 
    	<div class="stuName" id="stu">
		</div>
    <div class="checkCont">
        <!-- 时间 审核情况 float：left -->
        <div class="status">
            <table id="contenTable">
            </table>
        </div>
        <!-- 登记信息 评价 float：right -->
        <div class="inforJudge">
            <!-- 登记信息 -->
            <div class="information" id="addInfor">              
            </div>
            <!-- 评价 -->
            <div class="judge" >
                <table>
                    <tr>
                        <th>带教老师评价</th>
                    </tr>
                    <tr>
                        <td>
                            <textarea id="text" cols="30" rows="10" style="resize:none;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="btns">
                <a class="layui-layer-btn0" onclick="auditThrough()">审核通过</a>&nbsp;&nbsp;&nbsp;
                <a class="layui-layer-btn1" onclick="auditNotApproved()">审核不通过</a>
            </div>
        </div>

    </div>
    
</body>
</html>