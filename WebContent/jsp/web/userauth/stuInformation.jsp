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
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/userauth/css/table_information.css" />
 	<script type="text/javascript" src="<tm:ctx/>/jsp/web/userauth/js/stuInformation.js"></script>

	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
</head>
<body>
 <div class="info_table">
  <input type="text" hidden="hidden" value="${LOGIN_INFO.vUserDetailInfo.user_code }" id=userCode>
  <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
			       <span class="userPhoto"><img src="<tm:ctx/>/ueditor(附件文件夹千万不能删)/userImg/${LOGIN_INFO.vUserDetailInfo.stu_num}.jpg" onerror="this.src='<tm:ctx/>/jsp/web/publicdata/img/personbig.png'" style="width:140px;height:140px;border:2px solid #F3F3F3;">
			       </span>
			    </c:if>
			    <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code!='R_STU'}">
				<c:if test="${LOGIN_INFO.vUserDetailInfo.img_path==null}">
					 <span class="userPhoto"><img src="<tm:ctx/>/img/user.png" style="width:140px;height:140px;border:2px solid #F3F3F3;">  </span>
				</c:if>
				<c:if test="${LOGIN_INFO.vUserDetailInfo.img_path!=null}">
					 <span class="userPhoto"><img src="<tm:ctx/>/${LOGIN_INFO.vUserDetailInfo.img_path}" style="width:140px;height:140px;border:2px solid #F3F3F3;">
				     </span>
				</c:if>
				</c:if>

        <ul>      	
            <li>用户工号：</li>
            <li><input type="text" id="user_code" class="form-control" readonly/></li><li>用户名称：</li>
            <li><input type="text" id="stu_name" class="form-control" readonly/></li><li>手机号码：</li>
            <li><input type="text" id="stu_phone" class="form-control" readonly/></li>
            <li>身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</li>
            <li><select id="" id='identity' class="form-control" disabled>
                    <option value="">学生</option>
                    <option value="">非学生</option>
                </select>
            </li>
        </ul>
        <table>
            <tr>
                <td>
                    性别：
                </td>
                <td>
                    <input class="form-control" type="text"  id="stu_sex"  readonly>
                </td>
            </tr>
            <tr>
                <td>
                    年龄：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_age" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    出生日期：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_birthday" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    国籍：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_country" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    民族：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_nationality" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    籍贯：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_native" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    户口地址：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_hk_address" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    居住地址：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_home_address" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    政治面貌：
                </td>
                <td>
                    <input class="form-control" type="text" id="political_status" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    兴趣特长：
                </td>
                <td>
                    <input class="form-control" type="text" id="interesting_speciality" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    所属院校名称：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_school_name" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    学号：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_num" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    届次：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_class" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    学生状态：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_status" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    职务：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_position" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    文化水平：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_edu_level" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    专业名称：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_major_name" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    专业方向：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_major_direction" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    规培生类型：
                </td>
                <td>
                    <input class="form-control" type="text" id="stu_type" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    上级医师名称：
                </td>
                <td>
                    <input class="form-control" type="text" id="sup_doc_name" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    导师姓名：
                </td>
                <td>
                    <input class="form-control" type="text" value="" id="tutor_name" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    导师单位：
                </td>
                <td>
                    <input class="form-control" type="text"  id="tutor_workplace" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    创建人：
                </td>
                <td>
                    <input class="form-control" type="text"  id="creator" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    备注：
                </td>
                <td>
                    <input class="form-control" type="text"  id="remark" readonly>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>