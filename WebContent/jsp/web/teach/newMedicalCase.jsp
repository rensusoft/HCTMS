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
    <link rel="stylesheet"  href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqueryui/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/newMedicalCase.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/newMedicalCase.js"></script>
</head>
<body>
	<section>
        <div class="newDiv">
            <div class="leftDiv">
                <table class="chooseTable">
                    <tr>
                        <td style="text-align: right;width: 110px;vert-align: middle">选择相关病人：</td>
                        <td><input id="inp_in_patient_info" type="text" class="form-control" style="display: inline-block;width:450px;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;width: 110px;vert-align: middle">选择阐述人：</td>
                        <td>
                            <select id="sel_exponent_auth_id" class="form-control" style="width:450px">
                                <option value="">11111111</option>
                                <option value="">22222222</option>
                                <option value="">33333333</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;width: 110px;vert-align: middle">期望完成时间：</td>
                        <td>
                        	<input type="text" id="inp_hope_finish_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate form-control" style="width:450px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;width: 110px;vert-align: middle">病例简要说明：</td>
                        <td><textarea name="exponent_content" id="exponent_content" ></textarea></td>
                    </tr>
                </table>
            </div>
            <div class="rightDiv">
                <b>选择讨论人：</b><br/>
                <div class="zTreeDemoBackground left">
                    <ul id="discussantTree" class="ztree"></ul>
                </div>
                <div class="stuNameDiv" style="height:115px"></div><!--学生姓名div-->
            </div>
        </div>
        <div class="btns">
            <a href="javascript:void(0);" class="layui-layer-btn0" onclick="submitData();">保存</a>
        </div>
    </section>
</body>
</html>