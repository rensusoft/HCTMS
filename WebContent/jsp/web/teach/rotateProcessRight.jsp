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
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateProce.css"/>
	   <script src="<tm:ctx/>/jsp/web/teach/js/rotateProcessRight.js"></script>
</head>
<body style="padding: 10px;">
<div class="main_list">
	<div>
		<span id="span_roleCode" style="display:none;">${LOGIN_INFO.vUserDetailInfo.role_code}</span>
		<span id="span_stuTypeId" style="display:none;">${trainPlan.stu_type_id}</span>
	</div>
    <div class="panel panel-default process">
        <div class="panel-heading">
            <b>入科</b>
            轮转周期： <i>[<span>${trainPlan.train_start_str}</span>至<span>${trainPlan.train_end_str}</span>]</i>&nbsp;&nbsp;
            入科报到时间： <i>[<span>${trainPlan.dept_receive_str}</span>]</i>&nbsp;&nbsp;
            带教老师： <i><span>${trainPlan.teach_name}</span></i>
    <!--  
            科主任： <i><span>${trainPlan.director_name}</span></i>
            教学秘书： <i><span>${trainPlan.secretary_name}</span></i>
    -->
        </div>
        <div class="panel-body">
        <c:choose>  
        <c:when test="${trainPlan.train_status==58}">  
         <div class="step">
                <table>
                    <tr>
                        <td>
                            <div>
                                <b>1</b>
                                <i></i>
                            </div>
                            <span>入科报到</span>
                            <div style="margin-left:-13px;height:20px;">${trainPlan.dept_receive_str} </div>
                        </td>
                        <td>
                            <div>
                                <b>2</b>
                                <i></i>
                            </div>
                            
                            <span>入科学习</span>
                            <div style="margin-left:-13px;height:20px;"></div>
                        </td>
                        <td>
                            <div>
                                <b>✔</b>
                            </div>
                            <span>完成</span>
                            <div style="margin-left:-45px;position:absolute;width:150px;height:20px;">${trainPlan.train_end_strTime} </div>
                        </td>
                    </tr>
                </table>
            </div>
          </c:when> 
          <c:when test="${trainPlan.train_status==51 or trainPlan.train_status=='' or trainPlan.train_status==null}">  
         <div class="step">
                <table>
                    <tr>
                        <td>
                            <div>
                                <b class="gray">1</b>
                                <i class="gray"></i>
                            </div>
                            <span>入科报到</span>
                            <div style="margin-left:-13px;height:20px;">${trainPlan.dept_receive_str} </div>
                        </td>
                        <td>
                            <div>
                                <b class="gray">2</b>
                                <i class="gray"></i>
                            </div>
                            <span>入科学习</span>
                            <div style="margin-left:-13px;height:20px;"></div>
                        </td>
                        <td>
                            <div>
                                <b class="gray">✔</b>
                            </div>
                            <span>完成</span>
                            <div style="margin-left:-45px;position:absolute;width:150px;height:20px;">${trainPlan.train_end_strTime} </div>
                        </td>
                    </tr>
                </table>
            </div>
          </c:when>   
    <c:otherwise>  
    <div class="step">
                <table>
                    <tr>
                        <td>
                            <div>
                                <b>1</b>
                                <i></i>
                            </div>
                            <span>入科报到</span>
                            <div style="margin-left:-13px;height:20px;">${trainPlan.dept_receive_str} </div>
                        </td>
                        <td>
                            <div>
                                <b>2</b>
                                <i class="gray"></i>
                            </div>
                            <span>入科学习</span>
                            <div style="margin-left:-13px;height:20px;"></div>
                        </td>
                        <td style="position:relative">
                            <div>
                                <b class="gray">✔</b>
                            </div>
                            <span>完成</span>
                            <div style="margin-left:-45px;position:absolute;width:150px;height:20px;">${trainPlan.train_end_strTime} </div>
                        </td>
                    </tr>
                </table>
            </div>
     </c:otherwise>  
     </c:choose>
     		<hr/>
            <p style="float:left;">
                <input type="hidden" value="${trainPlan.tpc_id}" id="tpc_id" />
                <a href="###" onclick="info_standard();">科室轮转规范</a>
            </p>
            <p style="float:left;margin-left:200px;">
                <input type="hidden" value="${trainPlan.train_dept_id}" id="dept_id" />
                <input type="hidden" value="${trainPlan.stu_auth_id}" id="stu_auth_id" />
                <a href="###" onclick="indeptEducInfo();">入科宣教</a>
            </p>
            <div style='clear:both;'></div>
        </div>
    </div>
    <div class="panel panel-default percent">
        <div class="panel-heading">
            <b>在科</b>
        </div>
        <div class="panel-body">
        <table>
           <c:forEach items="${supMenuList}" var="supMenuList"> 
             <c:choose>  
              <c:when test="${supMenuList.type_id==0}">  
                <tr>
                    <td><span>${supMenuList.order_name}：</span></td>
                    <td>要求数：<b>${supMenuList.order_num}${supMenuList.order_num_unit}</b></td>
                    <td>
                    	<c:if test="${supMenuList.finnish_num == null}">
                    		完成：<a href="###" onclick="stuActiveDataInfo(this);">0</a>&nbsp;&nbsp;例
                    	</c:if>
                    	<c:if test="${supMenuList.finnish_num != null}">
                    		完成：<a href="###" onclick="stuActiveDataInfo(this);">${supMenuList.finnish_num}</a>&nbsp;&nbsp;例
                    	</c:if>
                    	<span class="span_finnishNum" style="display:none;">${supMenuList.finnish_num}</span>
                    	<span class="span_dataTypeId" style="display:none;">${supMenuList.data_type_id}</span>
                    </td>
                    <td>完成比例：
                    	<c:if test="${supMenuList.completion_rate==100}">
                    		<i style="color:#89C997;font-weight:bold;">${supMenuList.completion_rate}%</i>
                    	</c:if>
                    	<c:if test="${supMenuList.completion_rate!=100}">
                    		<i style="color:#FF9E00;font-weight:bold;">${supMenuList.completion_rate}%</i>
                    	</c:if>
                    </td>
                    <td>
                    	<c:if test="${supMenuList.completion_rate==100}">
                    		<img src='<tm:ctx/>/jsp/web/teach/img/ok.png'>&nbsp;&nbsp;
                    	</c:if>
                    	<c:if test="${supMenuList.completion_rate!=100}">
                    		<img src='<tm:ctx/>/jsp/web/teach/img/question.png'>&nbsp;&nbsp;
                    	</c:if>
                    </td>
                </tr>
              </c:when>
              <c:when test="${supMenuList.type_id==1}">  
                <tr>
                    <td><span>${supMenuList.order_name}：</span></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                 <c:forEach items="${supMenuList.subMenuList}" var="childMenuList"> 
                 <tr class="child">
                    <td><span>${childMenuList.order_name}：</span></td>
                    <td>要求数：<b>${childMenuList.order_num}${childMenuList.order_num_unit}</b></td>
                    <td>
                       <c:if test="${childMenuList.finnish_num == null}">
	                    	完成：<a href="###" onclick="stuActiveDataInfo(this);">0</a>&nbsp;&nbsp;例
	                   </c:if>
	                   <c:if test="${childMenuList.finnish_num != null}">
	                    	完成：<a href="###" onclick="stuActiveDataInfo(this);">${childMenuList.finnish_num}</a>&nbsp;&nbsp;例
	                   </c:if>
                	  <span class="span_finnishNum" style="display:none;">${childMenuList.finnish_num}</span>
                      <span class="span_dataTypeId" style="display:none;">${childMenuList.data_type_id}</span>  
                    </td>
                    <td>完成比例：
                    	<c:if test="${childMenuList.completion_rate==100}">
                    		<i style="color:#89C997;font-weight:bold;">${childMenuList.completion_rate}%</i>
                    	</c:if>
                    	<c:if test="${childMenuList.completion_rate!=100}">
                    		<i style="color:#FF9E00;font-weight:bold;">${childMenuList.completion_rate}%</i>
                    	</c:if>
                    </td>
                    <td>
                    	<c:if test="${childMenuList.completion_rate==100}">
                    		<img src='<tm:ctx/>/jsp/web/teach/img/ok.png'>&nbsp;&nbsp;
                    	</c:if>
                    	<c:if test="${childMenuList.completion_rate!=100}">
                    		<img src='<tm:ctx/>/jsp/web/teach/img/question.png'>&nbsp;&nbsp;
                    	</c:if>
                    </td>
                </tr>
                 </c:forEach>
              </c:when>
        </c:choose>
      </c:forEach>
     </table>
    </div>
    </div>
     <div class="panel panel-default finish">
        <div class="panel-heading">
            <b>出科</b>
        </div>
        <div class="progressDiv">
        </div>
</div>
</div>
<script>
var width=$(".process .panel-body div.step table").width()-$(".process .panel-body div.step table td:last-child").width();
$(".process .panel-body div.step table td").width(width);
var cWidth=(width)/($(".process .panel-body div.step table tr td").size()-1);
var iWidth=cWidth-$(".process .panel-body div.step table tr td:first-child b").width();
if($(".process .panel-body div.step table tr td").size()==6){
    $(".process .panel-body div.step table tr td i").width(iWidth+15);
}else if($(".process .panel-body div.step table tr td").size()==5){
    $(".process .panel-body div.step table tr td i").width(iWidth+19);
}else if($(".process .panel-body div.step table tr td").size()==4){
    $(".process .panel-body div.step table tr td i").width(iWidth+27);
}else if($(".process .panel-body div.step table tr td").size()==3){
    $(".process .panel-body div.step table tr td i").width(iWidth+45);
}

</script>
</body>
</html>