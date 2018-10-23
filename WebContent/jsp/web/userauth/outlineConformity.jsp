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
	<link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/rotateProcess.css"/>
</head>
<body style="padding: 10px;">
<div class="main_list">
    <div class="panel panel-default percent">
        <div class="panel-body">
        <table>
           <c:forEach items="${supMenuList}" var="supMenuList"> 
             <c:choose>  
              <c:when test="${supMenuList.type_id==0}">  
                <tr>
                    <td><span>${supMenuList.order_name}：</span></td>
                    <td>要求数：<b>${supMenuList.order_num}${supMenuList.order_num_unit}</b></td>
                    <td>完成：<a href="">${supMenuList.finnish_num}</a>&nbsp;&nbsp;例</td>
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
                    <td>完成：<a href="">${childMenuList.finnish_num}</a>&nbsp;&nbsp;例</td>
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
        $(".process .panel-body div.step table tr td i").width(iWidth+43);
    }

</script>
</body>
</html>