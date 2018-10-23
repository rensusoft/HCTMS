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
      <link rel="stylesheet"href="<tm:ctx/>/jsp/web/score/css/gradProgress.css"/>
	<script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
</head>
<body>
<div class="about4" style="padding: 10px">
    <div class="lTitleDiv">
        <div class="hr"></div>
        <h5 class="lTitle">审批流程</h5>
    </div>
    <div id="process_top">
        <table>
            <tbody>
            <tr>
                <td>
                    <div>
                        <b>1</b>
                        <i class="gray"></i>
                    </div>
                    <div class="divv">
                        <span class="proc_name">老师/带教秘书审核</span>
                    </div>
                    <span class="graTime">2017-03-29 13:04:33</span>
                </td>
                <td>
                    <div>
                        <b class="gray">2</b>
                        <i class="gray"></i>
                    </div>
                    <div class="divv">
                        <span class="proc_name">科主任审核</span>
                    </div>
                    <span class="graTime"></span>
                </td>
                <td>
                    <div>
                        <b class="gray">3</b>
                        <i class="gray"></i>
                    </div>
                    <div class="divv">
                        <span class="proc_name">科教科审核</span>
                    </div>
                    <span class="graTime"></span>
                </td>
                <td class="finish_td">
                    <div>
                        <b class="gray">✔</b>
                    </div>
                    <div class="divv">
                        <span>出科</span>
                    </div>
                    <span class="graTime"></span>
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
            <img src="img/stepblarrow.png" alt=""/>
        </div>
        <ul>
            <li>
                <b>2017-01-01<br/>12:12:12</b>
                <span></span>
                <i>
                    第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步第一步
                </i>
            </li>
            <li>
                <b>2017-01-01<br/>12:12:12</b>
                <span></span>
                <i>
                    第二步
                </i>
            </li>
            <li>
                <b>2017-01-01<br/>12:12:12</b>
                <span></span>
                <i>
                    第三步
                </i>
            </li>
            <li>
                <b>2017-01-01<br/>12:12:12</b>
                <span></span>
                <i>
                    第四步
                </i>
            </li>
        </ul>
    </div>
</div>
<script>
    $(function addcss() {
        var width = 997 - $(".finish_td").width();
        $("#process_top table td").width(width);
        var cWidth = (width) / ($("#process_top table tr td").size() - 1);
        var iWidth = cWidth - $("#process_top table tr td:first-child b").width();
        if ($("#process_top table tr td").size() == 4) {
            $("#process_top table tr td i").width(iWidth + 12);
        }
    });
</script>
</body>
</html>