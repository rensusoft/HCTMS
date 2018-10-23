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
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
   <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocumentComparison.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/medicalDocumentComparison.js"></script>
</head>
<body>
	<section>
		<div>
			<span style="display:none" id="span_id">${stuMedicalDocument.id }</span>
			<span style="display:none" id="span_type_id">${stuMedicalDocument.type_id }</span>
		</div>
        <div class="inforDiv">
            <label>批改时间</label>：<span class="contSpan">${stuMedicalDocument.correct_time_str }</span>
            <label>医疗文书类型</label>：<span class="contSpan">${stuMedicalDocument.type_id_str }</span>
            <label>科室</label>：<span class="contSpan">${orga_name }</span>
            <label>学生</label>：<span class="contSpan">${stuMedicalDocument.stu_auth_id_str }</span>
            <label>病人信息</label>：<span class="contSpan">${stuMedicalDocument.p_pid } - ${stuMedicalDocument.p_name }</span>
        </div>
        <div class="contDiv" style="padding-bottom:50px;">
            <div class="stuDiv dtlBox">
            	<div class="delTitle" style="width:97%;padding:0">
	                 <div class="hr"></div>
	                <h5 style="padding: 0 10px 0 0;color:#5eb8da;">&nbsp;&nbsp;学生书写</h5>
                </div>	
                <div style="padding:0 10px">
	                <font style="font-size:14px;font-weight:bold;">医疗文书名称：${stuMedicalDocument.mr_name }</font><br />
	                <div>${stuMedicalDocument.content }</div>
	                <c:if test="${stuMedicalDocument.type_id eq '1'}">
	                	<font style="font-size:14px;font-weight:bold;" id="ext-gen1807">诊断：</font>
		                <table>
			                <tr>
			                    <td>诊断内容</td>
			                    <td>ICD码</td>
			                </tr>
			                <c:forEach items="${stuMedicalDiagnoseList }" var="stuMedicalDiagnose"> 
			                	<tr>
				                    <td>${stuMedicalDiagnose.diag_name }</td>
				                    <td>${stuMedicalDiagnose.icd_code }</td>
			                	</tr>
			      			</c:forEach>
		            	</table>
	            	</c:if>
	                <c:if test="${stuMedicalDocument.type_id eq '4'}">
		                <table>
			                <tr>
			                    <td>药品名称</td>
			                    <td>药品规格</td>
			                    <td width="60px">剂量</td>
			                    <td width="60px">剂量单位</td>
			                    <td width="60px">用药途径</td>
			                    <td width="60px">用药频率</td>
			                    <td width="60px">总量</td>
			                    <td width="60px">总量单位</td>
			                </tr>
			                <c:forEach items="${stuMedicalAdviceSubList }" var="stuMedicalAdviceSub"> 
			                	<tr>
				                    <td>${stuMedicalAdviceSub.advice_name}</td>
				                    <td>${stuMedicalAdviceSub.advice_spec}</td>
				                    <td>${stuMedicalAdviceSub.advice_dose}</td>
				                    <td>${stuMedicalAdviceSub.dose_unit_code_str}</td>
				                    <td>${stuMedicalAdviceSub.path_code_str}</td>
				                    <td>${stuMedicalAdviceSub.frequency_code_str}</td>
				                    <td>${stuMedicalAdviceSub.total_dose}</td>
				                    <td>${stuMedicalAdviceSub.total_dose_unit_code_str}</td>
			                	</tr>
			      			</c:forEach>
		            	</table>
	            	</c:if>
            	</div>
            </div>
            <div class="teaDiv dtlBox">
            	<div class="delTitle" style="width:97%;padding:0">
	                 <div class="hr"></div>
	                <h5 style="padding: 0 10px 0 0;color:#5eb8da;">&nbsp;&nbsp;老师批改</h5>
                </div>
                <div style="padding:0 10px">
	                <c:if test="${stuMedicalDocument.correct_status eq 'Y'}">
	                	<c:if test="${stuMedicalDocument.type_id eq '1' || stuMedicalDocument.type_id eq '2' || stuMedicalDocument.type_id eq '3'}">
	                		<font style="font-size:14px;font-weight:bold;">医疗文书名称：${teaMedicalRecord.mr_name }</font><br />
	                		<div>${teaMedicalRecord.content }</div>
	                		<c:if test="${stuMedicalDocument.type_id eq '1'}">
	                			<font style="font-size:14px;font-weight:bold;" id="ext-gen1807">诊断：</font>
				                <table>
					                <tr>
					                    <td width="60%">诊断内容</td>
					                    <td width="40%">ICD码</td>
					                </tr>
					                <c:forEach items="${teaMedicalDiagnoseList }" var="teaMedicalDiagnose"> 
					                	<tr>
						                    <td>${teaMedicalDiagnose.diag_name }</td>
						                    <td>${teaMedicalDiagnose.icd_code }</td>
					                	</tr>
					      			</c:forEach>
				            	</table>
	            			</c:if>
	                	</c:if>
	                	<c:if test="${stuMedicalDocument.type_id eq '4'}">
	                		<font style="font-size:14px;font-weight:bold;">医疗文书名称：医嘱</font><br />
	                		<table>
					        	<tr>
					            	<td>药品名称</td>
					                <td>药品规格</td>
					                <td width="60px">剂量</td>
					                <td width="60px">剂量单位</td>
					                <td width="60px">用药途径</td>
					                <td width="60px">用药频率</td>
					                <td width="60px">总量</td>
					                <td width="60px">总量单位</td>    	
					            </tr>
					            <c:forEach items="${teaMedicalAdviceSubList }" var="teaMedicalAdviceSub"> 
					                	<tr>
						                    <td>${teaMedicalAdviceSub.advice_name}</td>
						                    <td>${teaMedicalAdviceSub.advice_spec}</td>
						                    <td>${teaMedicalAdviceSub.advice_dose}</td>
						                    <td>${teaMedicalAdviceSub.dose_unit_code_str}</td>
						                    <td>${teaMedicalAdviceSub.path_code_str}</td>
						                    <td>${teaMedicalAdviceSub.frequency_code_str}</td>
						                    <td>${teaMedicalAdviceSub.total_dose}</td>
						                    <td>${teaMedicalAdviceSub.total_dose_unit_code_str}</td>
					                	</tr>
					      		</c:forEach> 		
				            </table>
	                	</c:if>
	                </c:if>
                </div>
            </div>
        </div>
        <c:if test="${stuMedicalDocument.correct_status eq 'Y'}">
        	<div class="btns" style="position:fixed;bottom:0;width:100%;background:#fff;text-align:center;padding:15px;">
            	<button class="layui-layer-btn0" onclick="comparison()">对比</button>
            </div>
        </c:if>
	</section>
	<script>
    	$(".stuDiv").attr("style","min-height:" + (pHeight-250) + "px");
    	$(".teaDiv").attr("style","min-height:" + (pHeight-250) + "px");
    </script>
</body>
</html>