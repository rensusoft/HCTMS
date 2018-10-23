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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/metro.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/peoChooseOther.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/checkbix.min.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/js/custom.js"></script>
	<!-- ztree控件 -->
	<script src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.core-3.5.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.excheck-3.5.js"></script>
	
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/checkbix.min.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/peoChooseOther.js"></script>
</head>
<body>
<div style="padding:10px;margin-bottom:45px">
<!--管理员-->
    <!-- 隐藏按钮 -->
   <c:choose>  
    <c:when test="${ LOGIN_INFO.vUserDetailInfo.role_code eq 'R_KJK_ADMIN' }"  > 
    <div id="tit" style="text-align: center;padding-top: 20px;">
        <p class="select" onclick="select1()">精确选择</p>
        <p onclick="select2()">简便选择</p>
    </div>
    <ul id="con"  style="margin-top:10px">
        <li class="show_block">
            <div class="zTreebg2">
                <ul id="tree" class="ztree"></ul>
            </div>
        </li>
        <li>
            <div>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部科主任" id="checkbox-demo1" value="40" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部教学秘书" id="checkbox-demo2" value="30" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部带教老师" id="checkbox-demo3" value="20" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部学生" id="checkbox-demo4" value="10" >
            </div>
        </li>
    </ul>
    </c:when>
    <c:otherwise>
    <ul id="con"  style="margin-top:10px;margin-left:70px;">
        <li class="show_block">
            <div class="zTreebg2">
                <ul id="tree" class="ztree"></ul>
            </div>
        </li>
        <li>
            <div>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部科主任" id="checkbox-demo1" value="40" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部教学秘书" id="checkbox-demo2" value="30" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部带教老师" id="checkbox-demo3" value="20" ><br/>
                <input type="checkbox" class="checkbix" data-color="blue" data-text="全部学生" id="checkbox-demo4" value="10" >
            </div>
        </li>
    </ul>
    </c:otherwise>
  </c:choose>
    <input id="leixing" type="hidden" value="3" />
    <div style="text-align: center" class="btns">
        <a class="layui-layer-btn0" onclick="addPeo()">保存</a>&nbsp;&nbsp;&nbsp;
        <a class="layui-layer-btn1" onclick="resetRen()">重置</a>
    </div>
</div>
</body>
</html>