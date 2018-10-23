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
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotatePlan.css"/>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script src="<tm:ctx/>/jsp/web/teach/js/rotateLayeout.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateLayeout.css"/>
</head>
<body>
<div>
    <div class="rotatestep">
        <p>学生轮转方案编排步骤</p>
        <ul>
            <li>
                <a>
                    <div>
                        <b>✔</b>
                        <i class="gray"></i>
                    </div>
                    <span>&nbsp;导入学生</span>
                </a>
            </li>
            <li>
                <a>
                    <div>
                        <b class="gray">？</b>
                        <i class="gray"></i>
                    </div>
                    <span>&nbsp;&nbsp;&nbsp;预编排</span>
                </a>
            </li>
            <li class="last_td">
                <a>
                    <div>
                        <b class="gray">？</b>
                    </div>
                    <span>完成</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="default">
    	<input type="hidden" id="stuClass" />
    	<input type="hidden" id="planConfig" />
    	<input type="hidden" id="stuCount" />
        <ul class="content_ul">
            <li class="show_block">
                <div>
                    <img src="<tm:ctx/>/jsp/web/teach/img/horn.png"/>
                    <iframe name="stuIframe" id="stuIframe" src="<tm:ctx/>/jsp/web/teach/stuLeadin.jsp" frameborder="0" style="width: 100%;height: 100%"></iframe>
                    <p>
                        <a class="layui-layer-btn0" onclick="next(1)">下一步>></a>
                    </p>
                </div>
            </li>
            <li>
                <div class="child3">
                    <img src="<tm:ctx/>/jsp/web/teach/img/horn.png"/>
                    <iframe id="beforeIframe" name="beforeIframe" src="" frameborder="0" style="width: 100%;height: 100%"></iframe>
                </div>
                <p>
                    <a class="layui-layer-btn0" onclick="next(2)">生成轮转计划</a>
                </p>
            </li>
            <li>
                <div>
                    <img src="<tm:ctx/>/jsp/web/teach/img/horn.png"/>
                    <p>所有学生的轮转编排都已完成</p>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
</html>