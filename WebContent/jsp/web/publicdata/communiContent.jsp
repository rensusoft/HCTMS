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
	<script src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
	<script src="<tm:ctx/>/js/bootstrap.min.js"></script>
	<script src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
	<script src="<tm:ctx/>/js/fullcalendar.min.js"></script>
	<script src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
	<script src="<tm:ctx/>/js/custom.js"></script>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/select2.css" />
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/publicCss.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/selectordie_theme_01.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/cui.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<tm:ctx/>/css/style.css'/>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx />/js/selectordie.min.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<link rel="stylesheet" type="text/css" href="<tm:ctx/>/jsp/web/publicdata/css/communicate.css" />
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/publicdata/js/communiContent.js"></script>
	
</head>
<body style="background: #F2F2F2">
<span style="float: right;margin-right: 30px;"><a href="<tm:ctx/>/jsp/web/publicdata/communicate.jsp"><img src="img/backarrow.png" alt=""/></a></span>
<div class="reply_btns">
    <a class="report" href="javascript:void(0);" id="but_publish" onclick="publishTheme()">发帖</a>
    <a class="restore" href="#" onclick="skip()">回复</a>
</div>
<div class="postlist">
    <table>
        <tr>
            <td class="pls">
                <div class="hm">
                    <span class="xg1">查看:</span>
                    <span class="xi1" id="checked_count"></span>
                    <span class="pipe">|</span>
                    <span class="xg1">回复:</span>
                    <span class="xi1" id="replay_count"></span>
                </div>
            </td>
            <td class="plc">
                <h1 class="ts">
                    <a>[<font color="#00c6ff" id="item_name"></font>]</a>
                    <span id="title"></span>
                </h1>
            </td>
        </tr>
    </table>
    <!-- 楼层内容 -->
    <div>
        <table>
            <tr>
                <td class="pls" rowspan="2">
                    <div class="avatar">
                        <img id="image" style="height:128px;width:128px" onerror="this.src='<tm:ctx/>/jsp/web/publicdata/img/personbig.png'"/>
                    </div>
                    <h5><font id="user_name"></font><span id="type_name"></span></h5>

                    <p class="role"><span id="orga_name"></span></p>

                    <!--  <p class="department">你猜我是哪个科室的</p> -->
                </td>
                <td class="plcc">
                    <div class="pi">
                        <strong>
                            <a class="diban">
                                <em>1</em><sup>#</sup></a>
                        </strong>

                        <div class="pti">
                            <div class="authi">
                                <em>发表于 <span id="publish_time_str"></span></em>
                            </div>
                        </div>
                    </div>
                    <div id="content" class="plccCont"></div>
                   
                </td>
            </tr>
            <tr>
                <td class="po hin">
                    <div class="pob cl">
                        <em>
                            <a class="fastre1" href="#" onclick="skip()">回复 <span class="glyphicon glyphicon-comment"></span></a>
                        </em>
                    </div>
                </td>
            </tr>

        </table>
    </div>
    
    <div id="reply_info">
    </div>
    
    <!-- 回复 -->
    <div class="rc">
        发表回帖
    </div>
    <div class="reply_textarea" id="reply_textarea">
        <table>
            <tr>
                <td class="pls">
                    <div class="avatar">
                      <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code=='R_STU'}">
                      
			              <img src="<tm:ctx/>/ueditor(附件文件夹千万不能删)/userImg/${LOGIN_INFO.vUserDetailInfo.stu_num}.jpg" style="height:128px;width:128px" onerror="this.src='<tm:ctx/>/jsp/web/publicdata/img/personbig.png'" >
			          </c:if>
			          <c:if test="${LOGIN_INFO.vUserDetailInfo.role_code!='R_STU'}">
			              <img src="<tm:ctx/>/jsp/web/publicdata/img/personbig.png">
			          </c:if>
                    </div>
                </td>
                <td class="plcc">
                    <textarea name="reply_content" id="reply_content"></textarea>
                    <div style="text-align: center">
                        <a class="layui-layer-btn0 reply" onclick="publishReply()">发表回复</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="top">
        <a href="#top">
            <img src="<tm:ctx/>/jsp/web/publicdata/img/uparrow.png"/>
        </a>
    </div>
</div>
</body>
</html>