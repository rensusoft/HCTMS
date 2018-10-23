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
    <link rel="stylesheet" href="<tm:ctx/>/css/jquery-ui.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />   
    <link rel="stylesheet" type="text/css"  href="<tm:ctx/>/jsp/web/teach/css/stuInfomation.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
    <script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/js/datepicker.js"></script>
    <script src="<tm:ctx/>/js/select2.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/stuInfomation.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
     <title></title>
</head>
<body>
    <div class="stuList" id="stuList">
    <p>
      <select id="sel_stuList" onchange="fromStudentList();">
           <option value="1">正常入科</option>        
           <option value="2">提前入科</option>
      </select>
    </p>
    <ul id='stuListUL'>
    </ul>
    </div>
    <div class="stuInfor">
        <div class="dtlBox">
            <div class="delTitle">
                <div class="hr"></div>
                <h5>基本信息</h5>
            </div>
            <div class="basicInfor">
                <table>
                    <tr>
                        <td rowspan=4>
                            <img id="image" src="img/user.png" onerror="this.src='<tm:ctx/>/jsp/web/teach/img/user.png'" style="height:150px;width:150px" alt=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" readonly id="stu_name"/></td>
                        <td>性别：</td>
                        <td><input type="text" readonly id="stu_sex"/></td>
                    </tr>
                    <tr>
                        <td>年龄：</td>
                        <td><input type="text" readonly id="stu_age"/></td>
                        <td>出生年月：</td>
                        <td><input type="text" readonly id="stu_birthday"/></td>
                    </tr>
                    <tr>
                        <td>手机号码：</td>
                        <td><input type="text" readonly id="stu_phone"/></td>
                    </tr>
                </table>
            </div>
            <div class="delTitle">
                <div class="hr"></div>
                <h5>学籍信息</h5>
            </div>
            <div class="rollInfor">
                <table>
                    <tr>
                        <td>所属院校：</td>
                        <td><input type="text" readonly id="stu_school_name"/></td>
                        <td>学号：</td>
                        <td><input type="text" readonly id="stu_num"/></td>
                        <td>学历：</td>
                        <td><input type="text" readonly id="stu_edu_level"/></td>
                    </tr>
                    <tr>
                        <td>专业：</td>
                        <td><input type="text" readonly id="stu_major_name"/></td>
                        <td>专业方向：</td>
                        <td><input type="text" readonly id="stu_major_direction"/></td>
                    </tr>
                </table>
            </div>
            <div class="delTitle">
                <div class="hr"></div>
                <h5>培训信息</h5>
            </div>
            <div class="trainInfor">
                <table>
                	<tr>
                        <td>学生类型：</td>
                        <td><input type="text" readonly id="stuTypeName"/></td>
                        <td>届次：</td>
                        <td><input type="text" readonly id="stuClass"/></td>
                    </tr>
                    <tr>
                        <td>培训科室：</td>
                        <td><input type="text" readonly id="orgaName"/></td>
                        <td><span style="color:red;font-weight:bold;">*</span>带教老师：</td>
                        <td>
                            <select id="teacherId">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>培训开始时间：</td>
                        <td><input type="text" readonly id="startTime"/></td>
                        <td>培训结束时间：</td>
                        <td><input type="text" readonly id="endTime"/></td>
                    </tr>
                </table>
            </div>
            <div class="btns">
                <a class="layui-layer-btn0" onclick="changStatus()">入科</a>
            </div>
        </div>
    </div>
</body>
</html>