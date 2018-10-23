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
    <link rel="stylesheet"  href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/stuMedicalCaseDiscuss.css"/>
    
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    
     <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/stuMedicalCaseDiscuss.js"></script>
</head>
<body>
	<section>
	<input id="mcm_Id" style="display:none">
	<input id="qagrid" style="display:none">
	<input type="hidden" id="auth_id" value="${LOGIN_INFO.vUserDetailInfo.auth_id}" >
	<input type="hidden" id="exponent_auth_id" >
	<input type="hidden" id="creator_auth_id"  >
        <div class="jqDiv" >
            <div class="searchDiv">
                病人住院号：<input type="text" id= "admissionNum" class="form-control" style="display: inline-block;width:150px;">
          <!--       发布时间：<input type="text" class="form-control" style="display: inline-block;width:150px;">--<input type="text" class="form-control" style="display: inline-block;width:150px;"> -->
          讨论创建时间：<input type="text" id="classtime-from" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" 
        style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
    -- <input type="text" id="timeTo" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" 
       style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>      
               
                状态：
                <select class="form-control" id="status" style="display: inline-block;width: 150px;margin-right: 30px">
                    <option value="">---请选择---</option>
                    <option value="1">新建讨论</option>
                    <option value="2">阐述人阐述</option>
                    <option value="3">学生讨论</option>
                    <option value="4">结束（已评分）</option>
                </select>
                <a class="btn btn-info btnSearch" onClick="query()">查询</a>
            </div>
            <div>
                <table id="rotateRule"></table>
                <div id="pager1"></div>
            </div>
            <div class="rightBtn displayDiv">
                <div class="rightBtnSpan">
                    <span></span><br/>
                    病<br/>
                    例<br/>
                    阐<br/>
                    述<br/>
                </div>
                <div class="homeworkCont displayDiv"   style="padding: 0 15px 0 60px;overflow:auto;">
                    <div class="dtlBox" style="width: 100%;margin-bottom:50px" >
                        <div class="delTitle">
                            <div class="hr" style="z-index: 1"></div>
                            <h5 style="z-index: 999;position: relative;">问题信息</h5>
                            <a class="toggleBtn" style="z-index: 9"><img src="<tm:ctx/>/jsp/web/teach/img/arrowT.png"/>收起</a>
                        </div>
                        <div class="tableDiv">
                        <input id="creatorId" style="display:none">
                            <table class="inforTable">
                                <tr>
                                    <td><b>发&nbsp;&nbsp;布&nbsp;&nbsp;人:</b></td>
                                    <td id="creator"></td>
                                    <td><b>发布时间:</b></td>
                                    <td id=createTime></td>
                                </tr>
                                <tr>
                                    <td><b>开始日期:</b></td>
                                    <td id="startTime"></td>
                                    <td><b>结束日期:</b></td>
                                    <td id="finishTime"></td>
                                </tr>
                                <tr>
                                    <td><b>讨论人数:</b></td>
                                    <td id="discussNum"></td>
                                    <td><b></b></td>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                        <div class="delTitle">
                            <div class="hr" style="z-index: 1"></div>
                            <h5 style="z-index: 999;position: relative;">阐述信息</h5>
                            <a class="toggleBtn" style="z-index: 9"><img src="<tm:ctx/>/jsp/web/teach/img/arrowT.png"/>收起</a>
                        </div>
                        <div class="tableDiv">
                            <table style="width: 100%">
                                <tr>
                                    <td style="width: 120px;padding: 5px 0;padding-left: 25px"><b>阐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人：</b ><span id="exponent"></span></td>
                                    <td style="width: 120px;padding: 5px 0;"><b>病人住院流水号：</b ><span id="in_patient_info"></span></td>
                                </tr>
                                <tr>
                                    <td style="width: 120px;padding: 5px 0;padding-left: 25px" colspan="2"><b>病例讨论阐述：</b></td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: right;padding: 5px 0;padding-left: 35px;width: 100%">
                                        <textarea id="exponentText" style="width: 100%;"></textarea>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="btns" >
                        <a class="layui-layer-btn0" onClick="submit()">提交</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="addDiv displayDiv">
            <div class="leftBtn">
                <span></span><br/>
                病<br/>
                例<br/>
                讨<br/>
                论<br/>
                列<br/>
                表<br/>
            </div>
            <div id="wrap">
                <div class="leftDiv">
                    <div class="dtlBox" style="width: 100%">
                        <div class="delTitle">
                            <div class="hr"></div>
                            <h5>问题信息</h5>
                            <a class="toggleBtn"><img src="<tm:ctx/>/jsp/web/teach/img/arrowT.png"/>收起</a>
                        </div>
                        <div class="tableDiv">
                            <table class="inforTable">
                                <tr>
                                    <td><b>发&nbsp;&nbsp;布&nbsp;&nbsp;人:</b></td>
                                    <td id="creator1"></td>
                                    <td><b >发布时间:</b></td>
                                    <td id=createTime1></td>
                                </tr>
                                <tr>
                                    <td><b>开始日期:</b></td>
                                    <td id="startTime1"></td>
                                    <td><b>结束日期:</b></td>
                                    <td id="finishTime1"></td>
                                </tr>
                                <tr>
                                    <td><b>讨论人数:</b></td>
                                    <td id="discussNum1"></td>
                                    <td><b></b></td>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                        <div class="delTitle">
                            <div class="hr"></div>
                            <h5>阐述信息</h5>
                            <a class="toggleBtn"><img src="<tm:ctx/>/jsp/web/teach/img/arrowT.png"/>收起</a>
                        </div>
                        <div class="tableDiv">
                            <table style="width: 100%">
                                <tr>
                                    <td style="width: 120px;text-align: right;padding: 5px 0;"><b>阐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人：</b></td>
                                    <td style="text-align: left" id="exponent1"></td>
                                    <td style="width: 120px;text-align: right;padding: 5px 0;"><b>病人住院流水号：</b></td>
                                    <td style="text-align: left" id="in_patient_info1"></td>
                                </tr>
                                <tr>
                                    <td style="width: 120px;text-align: right;padding: 5px 0;"><b>病例讨论说明：</b></td>
                                    <td colspan="3" id="content1"></td>
                                </tr>
                                <tr>
                                    <td style="width: 120px;text-align: right;padding: 5px 0;"><b>病例讨论阐述：</b></td>
                                    <td colspan="3" id="exponent_content1"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="rightDiv dtlBox" style="padding-left: 15px">
                    <div class="delTitle" style="position: fixed;width: 60%;background: #fff;z-index: 1">
                        <div class="hr"></div>
                        <h5>讨论信息</h5>
                        <p class="colorp">
                            <a onClick="showInfo1(1)"><span class="normalP" ></span>其他学生&nbsp;&nbsp;&nbsp;&nbsp;</a>
                            <a onClick="showInfo1(2)"><span class="greenP" ></span>阐述学生&nbsp;&nbsp;&nbsp;&nbsp;</a>
                            <a onClick="showInfo1(3)"><span class="redP" ></span>老师</a>
                        </p>
                    </div>
                    <div class="rejectCont  chargeUl" style="margin-bottom: 0px !important;margin-top: 70px;overflow: auto;">
                        <div class="about4_main">
                            <div class="line">
                                <img src="<tm:ctx/>/jsp/web/teach/img/stepblarrow.png" alt=""/>
                            </div>
                            <ul id="discussInfomation">
                          
                            </ul>
                            <a class="btn btn-info btnSearch allBtn" onClick="showInfo1(4)"" style="position: fixed;top: 27px;right: 20px;z-index: 2;">只看自己</a>
                        </div>
                    </div>
                    <div class="dtlBox textDiv" style="width: 57%;position:fixed;bottom:0; ">
                        <div class="delTitle" style="padding:0">
                            <div class="hr"></div>
                            <h5>发表评论</h5>
                        </div>
                        <div class="rejectCont" style="margin-bottom: 50px;margin-top: 0">
                            <textarea id="discussContent" style="width: 100%;height: 120px"></textarea>
                        </div>
                        <div class="btns">
                            <a class="layui-layer-btn0" onClick="submitDiscuss()">保存</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>