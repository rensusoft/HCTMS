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
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocumentEdit.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/medicalDocumentedit.js"></script>
</head>
<body>
  <section>
    <div class="leftBtn">
    <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
         <a href="<tm:ctx/>/jsp/web/teach/medicalDocumentTea.jsp">
            <span></span><br/>
            医<br/>
            疗<br/>
            文<br/>
            书<br/>
            列<br/>
            表<br/>
        </a>
        </c:if>
           <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
         <a href="<tm:ctx/>/jsp/web/teach/medicalDocument.jsp">
            <span></span><br/>
            医<br/>
            疗<br/>
            文<br/>
            书<br/>
            列<br/>
            表<br/>
        </a>
        </c:if>
    </div>
   
    <div class="medicalDocumentCont">
        <span class="stateImg">
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
            <img src="img/change2.png" alt=""/>
         </c:if>
         <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
            <img src="img/change.png" alt=""/>
         </c:if>   
        </span>
            <h3 id="mr_name">医嘱</h3>
            <div class="perInfor form-group">
                姓名：<input type="text" id="p_name" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                性别：<input type="text" id="p_sex" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                年龄：<input type="text" id="p_age" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                科室：<input type="text" id="p_deptname" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                床号：<input type="text" id="p_bednum" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
                住院号：<input type="text" id="p_pid" class="form-control" style="width:100px;margin-right: 15px;display: inline-block">
            </div>
            <div class="tableDiv">
                <a class="layui-layer-btn0 addTableBtn">新增+</a>
                <table>
                    <!-- <tr class="tr_title">
                        <td>药品名称</td>
                        <td>药品规格</td>
                        <td>剂量</td>
                        <td>剂量单位</td>
                        <td>用药途径</td>
                        <td>用药频率</td>
                        <td>总量</td>
                        <td>总量单位</td>
                    </tr>
                    <tr class="tr_data">
                        <td><input type="text" class="longInput form-control advice_name"/></td>
                        <td><input type="text" class="longInput form-control advice_spec"/></td>
                        <td><input type="text" class="shortInput form-control advice_dose"/></td>
                        <td><select class="form-control shortInput dose_unit_code">
                            <option value="duc1">剂量单位1</option>
                            <option value="duc2">剂量单位2</option>
                            <option value="duc3">剂量单位3</option>
                        </select></td>
                        <td><select class="form-control shortInput path_code">
                            <option value="pc1">用药途径1</option>
                            <option value="pc2">用药途径2</option>
                            <option value="pc3">用药途径3</option>
                        </select></td>
                        <td><select class="form-control shortInput frequency_code">
                            <option value="fc1">用药频率1</option>
                            <option value="fc2">用药频率2</option>
                            <option value="fc3">用药频率3</option>
                        </select></td>
                        <td><input type="text" class="shortInput form-control total_dose"/></td>
                        <td><select class="form-control shortInput total_dose_unit_code">
                            <option value="tduc1">总量单位1</option>
                            <option value="tduc2">总量单位2</option>
                            <option value="tduc3">总量单位3</option>
                        </select></td>
                    </tr> -->
                </table>
            </div>
        </div>
        <div class="btns">
            <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_TEA'}">
            <a class="layui-layer-btn0" onclick="saveTea();">保存</a>&nbsp;&nbsp;&nbsp;
            <a class="layui-layer-btn1" onclick="resetTea();">重置</a>
            </c:if>
            <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
            <a class="layui-layer-btn0" onclick="save();">保存</a>&nbsp;&nbsp;&nbsp;
            <a class="layui-layer-btn1" onclick="reset();">重置</a>
            </c:if>
        </div>
    </section>
</body>
</html>