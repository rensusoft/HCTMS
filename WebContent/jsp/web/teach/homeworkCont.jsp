<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/hctms" prefix="tm" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/publishingOperation.css"/>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/homeworkCont.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <title></title>
</head>
<body>
      <div class="newsCont">
        <p>
            <label>标题：</label>
            <input type="text" id="title" class="form-control" placeholder="请输入标题" style="width:200px;display: inline-block">&nbsp;&nbsp;
            <label>类型：</label>
            <select class="form-control" style="width: 200px;display: inline-block" id="task_type_code">
                <option value="">----请选择----</option>
                <option value="1">课前</option>
                <option value="2">课中</option>
                <option value="3">课后</option>
            </select>
        </p>
        <textarea name="contents" id="contents" cols="30" rows="10" style="margin-top:4px;width:100%;height:200px;padding-bottom:15px;"></textarea><br/>
        接收人：<input type="text" class="input" readonly="readonly" id="name"/>&nbsp;&nbsp;&nbsp;
        <a onclick="add()" class="layui-layer-btn1 addBtn">接收人选择</a>&nbsp;&nbsp;&nbsp;
        <a class="layui-layer-btn0" onClick="sendToStu()">发布作业</a>
    </div>
    <script>
    function close(content) {
        console.log(content);
        layer.closeAll();
        $("#people").val(content);
    }
</script>
</body>
</body>
</html>