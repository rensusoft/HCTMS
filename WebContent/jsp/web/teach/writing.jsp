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
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/teach/css/daily.css" />
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/writing.js"></script>
</head>
<body>
<div class="left_btns">
    <h3>新增日志</h3>
    <ul>
        <li class="select" id="leftDaily" onclick="changeDaily()">
            日报
        </li>
        <li id="leftWeekly" onclick="changeWeekly()">
            周报
        </li>
        <li id="leftMonthly" onclick="changeMonthly()">
            月报
        </li>
    </ul>
</div>
<div id="wrap">
    <span style="float: right;margin-right: 30px;"><a href="<tm:ctx/>/jsp/web/teach/daily.jsp"><img src="img/backarrow.png" alt=""/></a></span>
    <ul id="con">
        <li class="show_block" id="mainDaily">
            <div class="">
                <div>
                    <table>
                        <tr>
                            <td>类型：</td>
                            <td>日报
                            </td>
                        </tr>
                        <tr>
                            <td>日期：</td>
                            <td>
                            	<input id="daily_duration" type="text" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                            <input id="classtime-from" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value="" type="text">
                            </td>
                        </tr>
                    </table>
                    <textarea name="daily_content" id="daily_content" style="width: 100%;"></textarea>
                    <input id="daily_flag" class="form-control"value="" type="hidden"  />
                    <input id="daily_type_id" class="form-control"value="1" type="hidden"  />
                </div>
                <div class="btns">
                	<button id="btnSaveDaily" class="layui-layer-btn0" onclick="saveDaily()">保存</button>&nbsp;&nbsp;&nbsp;
                	<button id="btnCloseDaily" class="layui-layer-btn1" onclick="closeDaily()">关闭</button>
                </div>
            </div>
        </li>
        <li id="mainWeekly">
            <div class="">
                <div>
                    <table>
                        <tr>
                            <td>类型：</td>
                            <td>周报
                            </td>
                        </tr>
                        <tr>
                            <td>日期：</td>
                            <td>
                                第 <input id="weekly_number" type="number" min="1" style="width: 50px"/> 周
                                <input id="weekly_begin_duration" type="text" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                                至
                                <input id="weekly_end_duration" type="text" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                            </td>
                        </tr>
                    </table>
                    <textarea name="weekly_content" id="weekly_content" style="width: 100%;"></textarea>
                    <input id="weekly_flag" class="form-control"value="" type="hidden"  />
                    <input id="weekly_type_id" class="form-control"value="2" type="hidden"  />
                </div>
                <div class="btns">
                    <button id="btnSaveWeekly" class="layui-layer-btn0" onclick="saveWeekly()">保存</button>&nbsp;&nbsp;&nbsp;
                	<button id="btnCloseWeekly" class="layui-layer-btn1" onclick="closeDaily()">关闭</button>
                </div>
            </div>
        </li>
        <li id="mainMonthly">
            <div class="">
                <div>
                    <table>
                        <tr>
                            <td>类型：</td>
                            <td>月报
                            </td>
                        </tr>
                        <tr>
                            <td>年月：</td>
                            <td>
                                <input id="monthly_duration" name="monthly_duration" type="text" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                            </td>
                        </tr>
                    </table>
                    <textarea name="monthly_content" id="monthly_content" style="width: 100%;"></textarea>
                    <input id="monthly_flag" class="form-control"value="" type="hidden"  />
                    <input id="monthly_type_id" class="form-control"value="3" type="hidden"  />
                </div>
                <div class="btns">
                    <button id="btnSaveMonthly" class="layui-layer-btn0" onclick="saveMonthly()">保存</button>&nbsp;&nbsp;&nbsp;
                	<button id="btnCloseMonthly" class="layui-layer-btn1" onclick="closeDaily()">关闭</button>
                </div>
            </div>
        </li>
    </ul>
</div>
	
</body>
</html>