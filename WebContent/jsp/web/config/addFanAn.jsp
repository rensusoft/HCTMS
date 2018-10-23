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
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/config/css/addFanAn.css"/>
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/addFanAn.js"></script>
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/lang/zh-cn/zh-cn.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
</head>
<body style="padding: 10px">
<div id="tit">
    <span class="select">轮转方案</span>
    <span id="nextSpan">轮转大纲</span>
</div>
<ul class="tab" id="con" style="width:100%;margin: 0 auto;padding-top:20px;">
   <li class="showtab">
   	<div class="div_boder" style="padding:10px;position:relative;margin-bottom:40px;">
   	<i class=arrow style="left:33px;"><img src="<tm:ctx/>/jsp/web/config/img/arrowsmall.png"></i>
       <div style="margin-top:4px;overflow:auto;height:100%;" id="div_1">
                <input id="id" class="form-control"value="" type="hidden"  />
		<textarea name="contentProcess" id="content" style="margin-top:4px;width:100%;height:400px;padding-bottom:20px;"></textarea>
		</div>
		<div style="width:100%;text-align:center;margin: 0 auto;margin-top:4px;position:fixed;bottom:0;background:#fff;padding-bottom:10px;left:-1px">
			      <button class="layui-layer-btn0" onclick="submitData();">保存</button>
	            </div>
	</div>
    </li>
    <li >
    	<div class="div_boder" style="padding:10px;position:relative;margin-bottom:40px;">
    	<i class=arrow style="left:134px;"><img src="<tm:ctx/>/jsp/web/config/img/arrowsmall.png"></i>
	        <div class="tableCont" >
	            <table id="list">
	                <tr class="ct">
	                    <td><span value="1">病种和技能</span>：</td>
	                    <td></td>
	                    <td>
	                        要求数：<input class="" size="3" value="0">
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="2">其他轮转信息</span>：</td>
	                    <td></td>
	                    <td>
	                        要求数：<input class="" size="3" value="0">
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="3">病历书写</span>：</td>
	                    <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="4">门急诊工作</span></td>
	                     <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="5">阅读参考书刊</span>：</td>
	                     <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="6">参加学术会议</span>：</td>
	                     <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="7">参加科研</span>：</td>
	                     <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	                <tr class="ct">
	                    <td><span value="8">获奖情况</span>：</td>
	                     <td></td>
	                    <td>
	                        要求数：<input  class="" size="3" value="0" />
	                    </td>
	                    <td>
	                        <a class="enable">启用</a>
	                        <a class="disable1">禁用</a>
	                    </td>
	                </tr>
	            </table>
	            
	        </div>
	        <div style="width:100%;text-align:center;margin: 0 auto;margin-top:4px;position:fixed;bottom:0;background:#fff;padding-bottom:10px;left:-1px">
			      <button class="layui-layer-btn0" onclick="submitData();">保存</button>
	            </div>
        </div>
    </li>
</ul>


</body>
</html>