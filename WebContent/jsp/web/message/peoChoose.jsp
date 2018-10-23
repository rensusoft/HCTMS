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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/message/css/news.css"/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/js/custom.js"></script>
    <script src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>

    <script src="<tm:ctx/>/js/jsonSelectTree.js"></script>
    <script src="<tm:ctx/>/js/singleSelectTree.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/peoChoose.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.core-3.5.js"></script>
    <script src="<tm:ctx/>/jsp/web/message/js/jquery.ztree.excheck-3.5.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	
   
</head>
<body>
<!--管理员-->
<div style="width: 600px;height: 300px;overflow: auto;padding:0 15px">
    <!-- 隐藏按钮 -->
    <div id="tit" style="text-align: center">
        <p class="select">简便选择</p>
        <p>科室选择</p>
        <p>精确选择</p>
    </div>
    <ul id="con">
        <li class="show_block">
            <div>
               	<div id="stu">
                    	学生类型:
                </div>
                <div style="text-align: center" class="btns">
                    <a class="layui-layer-btn0" onclick="addClass()">保存</a>&nbsp;&nbsp;&nbsp;
                    <a class="layui-layer-btn1" onclick="resetClass()">重置</a>
                </div>
            </div>
        </li>
        <li>
            <div>
                <div id="orga">         
                </div>
                <div style="text-align: center" class="btns">
                    <a class="layui-layer-btn0" onclick="addOrga()">保存</a>&nbsp;&nbsp;&nbsp;
                    <a class="layui-layer-btn1" onclick="resetOrga()">重置</a>
                </div>
            </div>
        </li>
        <li>
            <div>
                <table style="width: 100%;text-align: center">
                    <tr>
                        <td>请输入关键字：</td>
                        <td>
                            <input class="myInput" name="nameInput" id="exactInput" placeholder="关键字查询" style="width: 200px;text-align:center;margin-right:20px;"/>             
                 			<input type="hidden" id="authId" name="authId" />
                            <a class="btn btn-info btnSearch" style="margin: 0 15px" onclick="addUser()">添加</a>
                        </td>
                    </tr>                   
                    <tr>
                        <td colspan="2" style="text-align: left">
                            <div id="form_content"></div>
                        </td>
                    </tr>
                </table>
                <div style="text-align: center" class="btns">
                    <a class="layui-layer-btn0" onclick="saveDan()">保存</a>&nbsp;&nbsp;&nbsp;
                    <a class="layui-layer-btn1"  onclick="resetDan()">重置</a>
                </div>
            </div>
        </li>

    </ul>
</div>
</body>
</html>