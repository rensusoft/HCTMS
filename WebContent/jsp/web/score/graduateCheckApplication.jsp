<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<tm:jsandcss/>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/score/css/graduateCheckApplication.css" />
	
	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/score/js/graduateCheckApplication.js"></script>
</head>
<body style="height: 100%;">
<section>
	<div>
		<span style="display:none;" id="s_user_auth_id">${LOGIN_INFO.vUserDetailInfo.auth_id}</span>
		<span style="display:none;" id="s_orga_id">${LOGIN_INFO.vUserDetailInfo.orga_id}</span>
		<span style="display:none;" id="flagOfBtn">${requestScope.flagOfBtn}</span>
		<span style="display:none;" id="flag">${requestScope.flag}</span>
		<span style="display:none;" id="applyAgainFlag">${requestScope.applyAgainFlag}</span>
	</div>
	<c:choose>
    <c:when test="${requestScope.flag == -1}">
        <div class="displayBigDiv">
        	<p>
				未到发起出科审核的时间，你暂时不能发起出科审核申请！
			</p>
	    </div>
    </c:when>
    <c:when test="${requestScope.flag == 1}">
       	 	<div class="informationDiv" id="div_information">
		            <div class="lTitleDiv">
		                <div class="hr"></div>
		                <h5 class="lTitle">基本信息</h5>
		            </div>
		            <div class="basicInformation">
		                <table>
		                    <tr>
		                        <td><span class="glyphicon glyphicon-user"></span> 学生姓名：</td>
		                        <td><b id="name"></b></td>
		                        <td><span class="glyphicon glyphicon-home"></span> 轮转科室：</td>
		                        <td><b id="orga_name"></b></td>
		                    </tr>
		                    <tr>
		                        <td><span class="glyphicon glyphicon-time"></span> 入科时间：</td>
		                        <td><b id="deptReceiveStr"></b></td>
		                        <td><span class="glyphicon glyphicon-blackboard"></span> 带教老师：</td>
		                        <td><b id="teach_name"></b></td>
		                    </tr>
		                    <tr>
		                        <td><span class="glyphicon glyphicon-repeat"></span> 轮转时长：</td>
		                        <td><b id="train_start_str"></b>至<b id="train_end_str"></b></td>
		                        <td><span class="glyphicon glyphicon-question-sign"></span> 剩余天数：</td>
		                        <td><b id=daysAwayFromTrainEndTime></b></td>
		                    </tr>
		                    <tr>
		                        <td><span class="glyphicon glyphicon-ok-sign"></span> 大纲吻合度：</td>
		                        <td><p><progress max="100" id="progresses"></progress><i id="completion_rate"></i></p></td>
		                    </tr>
		                </table>
		            </div>
		            
		            <div class="lTitleDiv">
		                <div class="hr"></div>
		                <h5 class="lTitle">表单配置</h5>
		            </div>
		            <div class="form" style="margin-bottom: 60px;vertical-align:top;" id="div_form">
		            </div>
		            <div class="btns">
		                <a class="layui-layer-btn0" onclick="applyForGraduateCheck()">出科</a>
		            </div>
	        </div>
	        
	        <div class="progressDiv">
		        <div class="rightDiv">
				    <p>
						未发起出科，暂无审核记录！
					</p>
			    </div>
	        <!-- 
	        	<div class="panel panel-default" id="div_grouping1">
	        	
	        	</div>
	        	 
	            <div class="about4">
	                <div class="lTitleDiv">
	                    <div class="hr"></div>
	                    <h5 class="lTitle">审批流程</h5>
	                </div>
	                <div class="process_top">
	                    <table>
	                        <tbody style="width:80%">
	                        <tr class="flow_chart" style="width:80%">
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	           --> 
	        </div>
    </c:when>
    <c:when test="${requestScope.flag == 2}">
		<div class="displayDiv">
			<p>
			          出科审核已提交，查看流程图<span class="glyphicon glyphicon-hand-right"></span>
				<div class="btns" style="display:none;">
		        	<a class="layui-layer-btn0" onclick="applyForGraduateCheckAgain()">重新发起出科</a>
		        </div>
			</p>
	    </div>
	        
        <div class="progressDiv">
        <!-- 
	    	<div class="panel panel-default">
	    		 
            	<div class="panel-heading">
                    <b class="title">第一次审核记录</b>
                    <a class="arr arrup"></a>
                </div>
                
                <div class="panel-body" style="display: none">
                    <div class="about4">
                        <div class="lTitleDiv">
                            <div class="hr"></div>
                            <h5 class="lTitle">审批流程</h5>
                        </div>
                        
                        <div class="process_top">
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <div>
                                            <b>1</b>
                                            <i class="iWidth gray"></i>
                                        </div>
                                        <div class="divv">
                                            <span class="proc_name">学生申请出科</span>
                                        </div>
                                    </td>
                                    <td>
                                        <div>
                                            <b class="gray">2</b>
                                            <i class="iWidth gray"></i>
                                        </div>
                                        <div class="divv">
                                            <span class="proc_name">带教老师审核</span>
                                        </div>
                                    </td>
                                    <td>
                                        <div>
                                            <b class="gray">3</b>
                                            <i class="iWidth gray"></i>
                                        </div>
                                        <div class="divv">
                                            <span class="proc_name">科主任审核</span>
                                        </div>
                                    </td>
                                    <td class="finish_td">
                                        <div>
                                            <b class="gray">✔</b>
                                        </div>
                                        <div class="divv">
                                            <span>出科</span>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <div class="lTitleDiv">
                            <div class="hr"></div>
                            <h5 class="lTitle">审批记录</h5>
                        </div>
                        
                        <div class="about4_main">
                            <div class="line">
                                <img src="images/stepblarrow.png" alt=""/>
                            </div>
                            <ul>
                                <li>
                                    <b>2017-01-01<br/>12:12:12</b>
                                    <span></span>
                                    <i>
                                        <table>
                                            <tr>
                                                <td>发起人：</td>
                                                <td>张三[学生]</td>
                                            </tr>
                                            <tr>
                                                <td>表&nbsp;&nbsp;&nbsp;单：</td>
                                                <td>表单1，表单2，表单3</td>
                                            </tr>
                                            <tr>
                                                <td>内&nbsp;&nbsp;&nbsp;容：</td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </i>
                                </li>
                                <li>
                                    <b>2017-01-01<br/>12:12:12</b>
                                    <span></span>
                                    <i>
                                        <table>
                                            <tr>
                                                <td>发起人：</td>
                                                <td>李四[老师]</td>
                                            </tr>
                                            <tr>
                                                <td>表&nbsp;&nbsp;&nbsp;单：</td>
                                                <td>表单1.表单2，表单3，表单4</td>
                                            </tr>
                                            <tr>
                                                <td>内&nbsp;&nbsp;&nbsp;容：</td>
                                                <td>评价评价评价评价评价评价评价评价评价评价评价评价评价</td>
                                            </tr>
                                        </table>
                                    </i>
                                </li>
                                <li>
                                    <b>2017-01-01<br/>12:12:12</b>
                                    <span></span>
                                    <i>
                                        <table>
                                            <tr>
                                                <td>发起人：</td>
                                                <td>李四[老师]</td>
                                            </tr>
                                            <tr>
                                                <td>表&nbsp;&nbsp;&nbsp;单：</td>
                                                <td>表单1.表单2，表单3，表单4</td>
                                            </tr>
                                            <tr>
                                                <td>内&nbsp;&nbsp;&nbsp;容：</td>
                                                <td>评价评价评价评价评价评价评价评价评价评价评价评价评价</td>
                                            </tr>
                                        </table>
                                    </i>
                                </li>
                                <li>
                                    <b>2017-01-01<br/>12:12:12</b>
                                    <span></span>
                                    <i>
                                        <table>
                                            <tr>
                                                <td>发起人：</td>
                                                <td>李四[老师]</td>
                                            </tr>
                                            <tr>
                                                <td>表&nbsp;&nbsp;&nbsp;&nbsp;单：</td>
                                                <td>表单1.表单2，表单3，表单4</td>
                                            </tr>
                                            <tr>
                                                <td>内&nbsp;&nbsp;&nbsp;容：</td>
                                                <td>评价评价评价评价评价评价评价评价评价评价评价评价评价</td>
                                            </tr>
                                        </table>
                                    </i>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
              
            </div>
              -->
		</div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
	</c:choose>
</section>	
</body>
</html>