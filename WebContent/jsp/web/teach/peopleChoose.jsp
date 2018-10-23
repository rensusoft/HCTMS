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
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/publishingOperation.css"/>
    <script src="<tm:ctx/>/js/jquery-1.12.3.js"></script>
    <script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script src="<tm:ctx/>/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script src="<tm:ctx/>/jsp/web/teach/js/peopleChoose.js"></script>
    <title></title>
</head>
<body>
   <div style="overflow:auto;font-size: 14px; font-family: '微软雅黑'">
    <div id="renWu" style="width:100%;padding:10px;height:80%;overflow:auto;margin-bottom:50px;">
        <table>
         
            <tbody>
            <tr style="line-height: 25px;vertical-align:top;">
                <td style="width:75px;"><strong>学生选择:</strong></td>
              
                <td id="stuName">
                  <!-- 
                <input name="userName" type="checkbox"><span>吴天昊</span>
                    <input name="userName" type="checkbox"><span>刘嘉雯</span>
                    <input name="userName" type="checkbox"><span>李迪楠</span>
                    <input name="userName" type="checkbox"><span>陈书迪</span>
                    <input name="userName" type="checkbox"><span>何庆芬</span>
                    <input name="userName" type="checkbox"><span>凯如歌2</span>
                -->
                </td>
                 
            </tr>
            </tbody>
           
        </table>
    </div>
    <div style="text-align: center;position: fixed;bottom:0;width: 100%;background: #fff;padding: 15px 0;">
        <a class="layui-layer-btn0" style="height: 28px !important;line-height: 18px !important;padding: 3px 22px !important;" onclick="save();">保存</a>&nbsp;&nbsp;&nbsp;
        <a class="layui-layer-btn1" style="height: 28px !important;line-height: 18px !important;padding: 3px 22px !important;" onclick="reset();">重置</a>
    </div>
</div>
</body>
</html>