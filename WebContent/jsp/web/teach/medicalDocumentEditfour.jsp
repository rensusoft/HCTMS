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
	<title></title>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
   <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocumentEdit.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/medicalDocumentEditfour.js"></script>
    
</head>
<body>
    <section>
    <div class="leftBtn">
    <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
         <a href="<tm:ctx/>/jsp/web/teach/medicalDocumentTea.jsp">
            <span></span><br/>
            医<br/>
            疗<br/>
            文<br/>
            书<br/>
            列<br/>
            表<br/>
        </a>
        </c:if>
           <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
         <a href="<tm:ctx/>/jsp/web/teach/medicalDocument.jsp">
            <span></span><br/>
            医<br/>
            疗<br/>
            文<br/>
            书<br/>
            列<br/>
            表<br/>
        </a>
        </c:if>
    </div>
   
    <div class="medicalDocumentCont">
        <span class="stateImg">
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
            <img src="<tm:ctx/>/jsp/web/teach/img/change2.png" alt=""/>
         </c:if>
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
            <img src="<tm:ctx/>/jsp/web/teach/img/change.png" alt=""/>
         </c:if>   
        </span>
            <h3><input type="text" id="mr_name" class="form-control" style="width:550px;display: inline-block"/></h3>
            <div class="perInfor form-group">
                姓名：<input type="text" id="p_name" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                性别：<input type="text" id="p_sex" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                年龄：<input type="text" id="p_age" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                科室：<input type="text" id="p_deptname" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                床号：<input type="text" id="p_bednum" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                住院号：<input type="text" id="p_pid" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
            </div>
            <div class="textarea">
                <textarea name="content" id="content" style="width: 100%;height: 500px"></textarea>
            </div>
        </div>
        <div class="btns">
            <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
            <a class="layui-layer-btn0" onclick="saveTea();">保存</a>&nbsp;&nbsp;&nbsp;
            <a class="layui-layer-btn1" onclick="resetTea();">重置</a>
            </c:if>
            <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
            <a class="layui-layer-btn0" onclick="save();">保存</a>&nbsp;&nbsp;&nbsp;
            <a class="layui-layer-btn1" onclick="reset();">重置</a>
            </c:if>
        </div>
    </section>
</body>
</html>