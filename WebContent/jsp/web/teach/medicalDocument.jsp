<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/hctms" prefix="tm" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<tm:jsandcss/>
	<title></title>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css"/>
    <link rel="stylesheet" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
    <link rel="stylesheet" href="<tm:ctx/>/jsp/web/teach/css/medicalDocument.css"/>
    
    <!-- ueditor核心js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/ueditor.all.js"> </script>
	<!-- 引用公式js -->
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="<tm:ctx/>/js/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/grid.locale-cn.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>
    <script type="text/javascript" src="<tm:ctx />/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/jsp/web/teach/js/medicalDocument.js"></script>
    
</head>
<body>
   <section>
        <div class="jqDiv">
            <div class="searchDiv">
                书写时间：
                <input id="start_date" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>
                 -- 
                <input id="end_date" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate form-control" style="width:150px;display:inline-block;border:1px solid #ccc;height:29px;" value=""/>&nbsp;&nbsp;
                <button class="btn btn-info btnSearch"  onclick="search();">搜索</button>
                <a class="btn btn-info btnSearch addBtn" style="float: right;margin-right: 80px">新增</a>
            </div>
            <div>
                <table id="medicalDocument"></table>
                <div id="pager1"></div>
            </div>
            <div class="rightBtn">
                <span><img src="<tm:ctx/>/jsp/web/teach/img/arr2.png" alt=""/></span><br/>
                新<br/>
                增<br/>
                医<br/>
                疗<br/>
                文<br/>
                书<br/>
            </div>
        </div>
        <div class="addDiv displayDiv">
            <div class="leftBtn">
                <span></span><br/>
                医<br/>
                疗<br/>
                文<br/>
                书<br/>
                列<br/>
                表<br/>
            </div>
            <div class="left_btns">
                <h3>医疗文书</h3>
                <ul>
                    <li class="select li_first">
                        入院记录
                    </li>
                    <li>
                        病程记录
                    </li>
                    <li>
                        其他单据
                    </li>
                    <li>
                        医嘱
                    </li>
                </ul>
            </div>
            <div id="wrap">
                <ul id="con">
                    <li class="show_block">
                        <div class="">
                            <div class="medicalDocumentCont">
                                <span class="stateImg">
                                    <img src="<tm:ctx/>/jsp/web/teach/img/writeNew.png" alt=""/>
                                </span>
                                <h3>入院记录</h3>
                                <div class="perInfor form-group">
                                <input type="hidden" value="1" class="hid_type_id" />
                                   姓名：<input type="text" class="form-control p_name" style="width:100px;margin-right: 15px;display: inline-block">
                                    性别：<input type="text" class="form-control p_sex" style="width:100px;margin-right: 15px;display: inline-block">
                                    年龄：<input type="text" class="form-control p_age" style="width:100px;margin-right: 15px;display: inline-block">
                                    科室：<input type="text" class="form-control p_deptname" style="width:100px;margin-right: 15px;display: inline-block">
                                    床号：<input type="text" class="form-control p_bednum" style="width:100px;margin-right: 15px;display: inline-block">
                                    住院号：<input type="text" class="form-control p_pid" style="width:100px;margin-right: 15px;display: inline-block">
                                </div>
                                <div class="textarea">
                                    <textarea name="content1" id="content1" style="width: 100%;height: 500px"></textarea>
                                </div>
                                <div>
                                    <p class="titleP">诊断</p>
                                    <div class="addInput">
                                        诊断内容：<input type="text" class="form-control" id="diag_name" style="width:400px;margin-right: 30px;display: inline-block;">
                                        ICD码：<input type="text" id="icd_code" class="form-control" style="width:400px;margin-right: 15px;display: inline-block">
                                        <a class="layui-layer-btn0" style="padding: 4px 26px !important;height: 23px !important;" onclick="addRow();">新增</a>
                                    </div>
                                    <table id="medicalDiagnose"></table>
                                    <div id="pager2"></div>
                                </div>
                            </div>
                            <div class="btns">
                                <a class="layui-layer-btn0" onclick="save(this)">保存</a>&nbsp;&nbsp;&nbsp;
                                <a class="layui-layer-btn1" onclick="reset(this)">重置</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="">
                            <div class="medicalDocumentCont">
                                <span class="stateImg">
                                    <img src="<tm:ctx/>/jsp/web/teach/img/writeNew.png" alt=""/>
                                </span>
                                <h3>类型：
                                    <select class="form-control" id="mr_name2" style="display: inline-block;width: 150px">
                                        <option value="病程记录1">病程记录1</option>
                                        <option value="病程记录2">病程记录2</option>
                                        <option value="病程记录3">病程记录3</option>
                                    </select>
                                </h3>
                                <div class="perInfor form-group">
                                <input type="hidden" value="2" class="hid_type_id" />
                                    姓名：<input type="text" class="form-control p_name" style="width:100px;margin-right: 15px;display: inline-block">
                                    性别：<input type="text" class="form-control p_sex" style="width:100px;margin-right: 15px;display: inline-block">
                                    年龄：<input type="text" class="form-control p_age" style="width:100px;margin-right: 15px;display: inline-block">
                                    科室：<input type="text" class="form-control p_deptname" style="width:100px;margin-right: 15px;display: inline-block">
                                    床号：<input type="text" class="form-control p_bednum" style="width:100px;margin-right: 15px;display: inline-block">
                                    住院号：<input type="text" class="form-control p_pid" style="width:100px;margin-right: 15px;display: inline-block">
                                </div>
                                <div class="textarea">
                                    <textarea name="content2" id="content2" style="width: 100%;height: 500px"></textarea>
                                </div>
                            </div>
                            <div class="btns">
                                <a class="layui-layer-btn0" onclick="save(this)">保存</a>&nbsp;&nbsp;&nbsp;
                                <a class="layui-layer-btn1" onclick="reset(this)">重置</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="">
                            <div class="medicalDocumentCont">
                                <span class="stateImg">
                                    <img src="<tm:ctx/>/jsp/web/teach/img/writeNew.png" alt=""/>
                                </span>
                                <h3><input type="text" id="mr_name3" class="form-control" style="width:550px;display: inline-block"/></h3>
                                <div class="perInfor form-group">
                                <input type="hidden" value="3" class="hid_type_id" />
                                    姓名：<input type="text" class="form-control p_name" style="width:100px;margin-right: 15px;display: inline-block">
                                    性别：<input type="text" class="form-control p_sex" style="width:100px;margin-right: 15px;display: inline-block">
                                    年龄：<input type="text" class="form-control p_age" style="width:100px;margin-right: 15px;display: inline-block">
                                    科室：<input type="text" class="form-control p_deptname" style="width:100px;margin-right: 15px;display: inline-block">
                                    床号：<input type="text" class="form-control p_bednum" style="width:100px;margin-right: 15px;display: inline-block">
                                    住院号：<input type="text" class="form-control p_pid" style="width:100px;margin-right: 15px;display: inline-block">
                                </div>
                                <div class="textarea">
                                     <textarea name="content3" id="content3" style="width: 100%;height: 500px"></textarea>
                                </div>
                            </div>
                            <div class="btns">
                                <a class="layui-layer-btn0" onclick="save(this)">保存</a>&nbsp;&nbsp;&nbsp;
                                <a class="layui-layer-btn1" onclick="reset(this)">重置</a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="">
                            <div class="medicalDocumentCont">
                                <span class="stateImg">
                                    <img src="<tm:ctx/>/jsp/web/teach/img/writeNew.png" alt=""/>
                                </span>
                                <h3>医嘱</h3>
                                <div class="perInfor form-group">
                                    姓名：<input type="text" class="form-control" id="p_name" style="width:100px;margin-right: 15px;display: inline-block">
                                    性别：<input type="text" class="form-control" id="p_sex" style="width:100px;margin-right: 15px;display: inline-block">
                                    年龄：<input type="text" class="form-control" id="p_age" style="width:100px;margin-right: 15px;display: inline-block">
                                    科室：<input type="text" class="form-control" id="p_deptname"  style="width:100px;margin-right: 15px;display: inline-block">
                                    床号：<input type="text" class="form-control" id="p_bednum" style="width:100px;margin-right: 15px;display: inline-block">
                                    住院号：<input type="text" class="form-control" id="p_pid" style="width:100px;margin-right: 15px;display: inline-block">
                                </div>
                                <div class="tableDiv">
                                    <a class="layui-layer-btn0 addTableBtn">新增+</a>
                                    <table>
                                        <tr class="tr_title">
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
                                            <td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>
                                            <td><input type="text" class="shortInput form-control advice_dose"/></td>
                                            <td><select id="sel_dose_unit_code" class="form-control shortInput dose_unit_code" style="width: 85px">
                                                <option value="">-请选择-</option>
                                                <option value="duc1">/mg</option>
                                                <option value="duc2">/g</option>
                                                <option value="duc3">/kg</option>
                                            </select></td>
                                            <td><select id="sel_path_code" class="form-control shortInput path_code" style="width: 105px">
                                                <option value="pc1">---请选择---</option>
                                                <option value="pc1">用药途径额</option>
                                                <option value="pc2">用药途径2</option>
                                                <option value="pc3">用药途径3</option>
                                            </select></td>
                                            <td><select id="sel_frequency_code" class="form-control shortInput frequency_code" style="width: 105px">
                                            	<option value="pc1">---请选择---</option>
                                                <option value="fc1">用药频率1</option>
                                                <option value="fc2">用药频率2</option>
                                                <option value="fc3">用药频率3</option>
                                            </select></td>
                                            <td><input type="text" class="shortInput form-control total_dose"/></td>
                                            <td><select id="sel_total_dose_unit_code" class="form-control shortInput total_dose_unit_code" style="width: 85px">
                                            	<option value="">-请选择-</option>
                                                <option value="tduc1">总量单位1</option>
                                                <option value="tduc2">总量单位2</option>
                                                <option value="tduc3">总量单位3</option>
                                            </select></td>
                                            <td><a title="删除" style="font-size:22px;" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="btns">
                                <a class="layui-layer-btn0" onclick="saveMedicalAdvice()">保存</a>&nbsp;&nbsp;&nbsp;
                                <a class="layui-layer-btn1" onclick="resetMedicalAdvice()">重置</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</body>
</html>