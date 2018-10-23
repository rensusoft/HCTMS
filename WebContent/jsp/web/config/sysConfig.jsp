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
	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />

	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateProcess.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/config/css/sysConfig.css" />
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/lc_sysConfig.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/sysConfig.js"></script>
</head>
<body>
<section>
	<a class="layui-layer-btn0" onclick="reload()" style="float:right;">重新刷新</a>
    <p class="ulList">
        <span class="clicked" id="span_system">系统开关</span>
        <span>教学配置</span>
    </p>
	<!-- -->
    <ul id="cont">
        <li class="show">
            <table class="switchTable" id="sysConfig">
            <!-- 
            <tr>
	            <th>编码</th>
	            <th>开关</th>
	            <th>内容</th>
	            <th>说明及保存</th>
            </tr>
            <tr>
	            <td class="config_num">1</td>
	            <td>
	            <p><input type="checkbox" name="check-1" class="lcs_check" autocomplete="off" /></p>
	            </td>
	            <td class="config_content">
	            	<div>距离可以提出出科审核申请的天数：
	            		<input id="days_awayFrom_graduateCheckApplication" type="number" min="1" style="width: 50px"/>
	            	</div>
	            		
	            	<div>线程每日启动时间：
	            		<select>
	            			<option value="00:00:00">00:00:00</option>
	            			<option value="00:15:00">00:15:00</option>
	            			<option value="00:30:00">00:30:00</option>
	            			<option value="00:45:00">00:45:00</option>
	            			<option value="01:00:00">01:00:00</option>
	            			<option value="01:15:00">01:15:00</option>
	            			<option value="01:30:00">01:30:00</option>
	            			<option value="01:45:00">01:45:00</option>
	            			<option value="02:00:00">02:00:00</option>
	            			<option value="02:15:00">02:15:00</option>
	            			<option value="02:30:00">02:30:00</option>
	            			<option value="02:45:00">02:45:00</option>
	            			<option value="03:00:00">03:00:00</option>
	            			<option value="03:15:00">03:15:00</option>
	            			<option value="03:30:00">03:30:00</option>
	            			<option value="03:45:00">03:45:00</option>
	            			<option value="04:00:00">04:00:00</option>
	            			<option value="04:15:00">04:15:00</option>
	            			<option value="04:30:00">04:30:00</option>
	            			<option value="04:45:00">04:45:00</option>
	            			<option value="05:00:00">05:00:00</option>
	            		</select>
	            	</div>
	            </td>
	            <td>
	            <a class="layui-layer-btn1" onclick="viewDetail()">开关说明</a>
	            <a class="layui-layer-btn0">保存</a>
	            </td>
            </tr>
            -->
            <!--  
                <tr>
                    <th>编码</th>
                    <th>开关</th>
                    <th>内容</th>
                    <th>说明及保存</th>
                </tr>
                <tr>
                    <td id="config_num">1</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-1" id="config_flag" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td id="config_content">
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-2" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-3" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-4" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-5" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
            </table>
        </li>
        <li>
            <table class="switchTable">
                <tr>
                    <th>编码</th>
                    <th>开关</th>
                    <th>内容</th>
                    <th>说明及保存</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-1" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-2" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-3" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-4" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>
                        <p>
                            <input type="checkbox" name="check-5" class="lcs_check" autocomplete="off" />
                        </p>
                    </td>
                    <td>
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                        内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                    </td>
                    <td>
                        <a class="layui-layer-btn1">开关说明</a>
                        <a class="layui-layer-btn0">保存</a>
                    </td>
                </tr>
                -->
            </table>
        </li>
    </ul>
</section>
</body>
</html>