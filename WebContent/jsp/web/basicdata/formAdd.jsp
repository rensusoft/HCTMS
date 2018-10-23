<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>新建评分表</title>
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/metro.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap-addtabs.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jsonSelectTree.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/singleSelectTree.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/formAdd.js"></script>
	<script type="text/javascript">
	    var editor = null;
	    window.onload = function(){
	        editor = CKEDITOR.replace('content',{
	        	height:650
	    	}); //参数‘content’是textarea元素的name属性值，而非id属性值
	    };
	</script>
</head>
<body class="addpage">
    <div class="add-box">
        <form class="form-inline form-fixed" role="form" action="">
            <div class="form-group">
                <label><span class="red">*</span>表单类型：</label>
                <select class="form-control" id="form_type">
                    <option value="1">评分表单</option>
                    <option value="2">普通表单</option>
                </select>
            </div>
            <div class="form-group">
                <label><span class="red">*</span>表单名称：</label>
                <input type="text" class="form-control" id="form_name">
            </div> 
            <div class="form-group">
                <label><span class="red">*</span>表单状态：</label>
                <select class="form-control" id="form_state">
                    <option value="Y">启用</option>
                    <option value="N">禁用</option>
                </select>
            </div>
	        <div class="toggle">
	           	 基本评分方式
	            <span class="glyphicon glyphicon-chevron-left" style="margin-top:10px;"></span>
	        </div>
        </form>
        <div id="markshee" class="pagecon">
            <div class="lSize pull-left">
                <h5 class="title">基本评分方式</h5>
                <select class="form-control" id="styleValue">
                    <option>分值类型</option>
                    <option>YES/NO类型</option>
                    <option>比例类型</option>
                </select>
                <div class="style1">
                    <div class="instance margin_top">
                        <span>示例：</span> 
                        <input type="text" class="spinner" value="1" min="1" onKeyPress="return verifyDigital(event);"><br>
                        <p>说明：请填写分值</p>
                    </div>
                    <div class="fillbox">
                        <label>分值：</label>
                        <input type="text" class="spinner" value="1" min="1" > 
                    </div>
                    <div class="text-center margin_top">
                        <a class="btn btn-info" href="javascript:;" id="styleBtn1">确定</a>
                    </div>
                </div>
                <div class="style2">
                    <div class="instance margin_top">
                        <span>示例：</span>
                        <input type="radio" name="option"> YES[5]
                        <input type="radio" name="option"> NO[0]
                        <br>
                        <p>说明：输入框内请键入评分选项，如：YES/NO</p>
                    </div>
                    <div class="fillbox">
                        <label>文字：</label>
                        <input id="textNoY" type="text" class="form-control" placeholder="例：YES">
                        <label>分值：</label>
                        <input id="scoreNoY" type="text" class="form-control" placeholder="例：5" onKeyPress="return verifyDigital(event);">
                        <label class="color_white">文字：</label>
                        <input id="textNoN" type="text" class="form-control" placeholder="例：NO">
                        <label>分值：</label>
                        <input id="scoreNoN" type="text" class="form-control" placeholder="例：0" onKeyPress="return verifyDigital(event);">
                    </div>
                    <!-- <div class="fillbox">
                        <label>评分规则：</label>
                        <input type="radio" name="scoreRules" checked> 分数累计<br>
                        <label class="color_white">评分规则：</label>
                        <input type="radio" name="scoreRules"> 一旦出现NO即视为不合格
                    </div> -->
                    <div class="text-center margin_top">
                        <a class="btn btn-info" href="javascript:;" id="styleBtn2">确定</a>
                    </div>
                </div>
                <div class="style3">
                    <div class="instance margin_top">
                        <span>示例：</span>
                        <input type="radio" name="option">不满意[1]
                        <input type="radio" name="option">满意[3]
                        <br>
                        <p>说明：请填写文字标签及分值，并选择量表层级</p>
                    </div>
                    <div class="fillbox margin_bottom">
                        <label>层级：</label>
                        <select class="form-control" id="numSel">
                            <option value="2" selected>量表层级2</option>
                            <option value="3">量表层级3</option>
                            <option value="4">量表层级4</option>
                            <option value="5">量表层级5</option>
                        </select>
                    </div>
                    <div class="fillbox">
                        <div id="numSel1"><label>标签：</label>
                        <input id="textNo1" type="text" class="form-control" placeholder="例：满意">
                        <label>分值：</label>
                        <input id="scoreNo1" type="text" class="form-control" placeholder="例：5" onKeyPress="return verifyDigital(event);"></div>
                        <div id="numSel2"><label class="color_white">标签：</label>
                        <input id="textNo2" type="text" class="form-control" placeholder="例：不满意">
                        <label>分值：</label>
                        <input id="scoreNo2" type="text" class="form-control" placeholder="例：0" onKeyPress="return verifyDigital(event);"></div>
                        <div id="numSel3" style="display: none;"><label class="color_white">标签：</label>
                        <input id="textNo3" type="text" class="form-control">
                        <label>分值：</label>
                        <input id="scoreNo3" type="text" class="form-control" onKeyPress="return verifyDigital(event);"></div>
                        <div id="numSel4" style="display: none;"><label class="color_white">标签：</label>
                        <input id="textNo4" type="text" class="form-control">
                        <label>分值：</label>
                        <input id="scoreNo4" type="text" class="form-control" onKeyPress="return verifyDigital(event);"></div>
                        <div id="numSel5" style="display: none;"><label class="color_white">标签：</label>
                        <input id="textNo5" type="text" class="form-control">
                        <label>分值：</label>
                        <input id="scoreNo5" type="text" class="form-control" onKeyPress="return verifyDigital(event);"></div>
                    </div>
                    <div class="text-center margin_top">
                        <a class="btn btn-info" href="javascript:;" id="styleBtn3">确定</a>
                    </div>
                </div>                
            </div>
            <div class="rSize pull-left">
                <h5 class="title">编辑考核项目及技术要点</h5>
                <!-- <a href="javascript:;" class="btn btn-primary pull-right">导入评分表</a>
                <a href="javascript:;" class="btn btn-primary pull-right  margin_right styleModel">下载评分表模板</a> -->
                <div class="itemEdit margin_top">
                    <ul class="sortable">
                        <li id="submain1" class="list-group">
                            <p>
                                <input type="text" class="form-control" placeholder="编辑评分项">
                                <span class="btnGroup">
                                    <a href="javascript:;" class="glyphicon glyphicon-plus"></a>
                                    <a href="javascript:;" class="glyphicon glyphicon-remove"></a>
                                    <a href="javascript:;" class="glyphicon glyphicon-cog"></a>
                                </span>
                            </p>
                            <ol class="sortable2">
                                <li id="submain1_subdetail1" class="sublist">
                                    <input type="text" class="form-control" placeholder="编辑评分细则">
                                    <span class="styleBox margin_left"></span>
                                    <span class="btnGroup">
                                        <a href="javascript:;" class="glyphicon glyphicon-plus"></a>
                                        <a href="javascript:;" class="glyphicon glyphicon-remove"></a>
                                        <a href="javascript:;" class="glyphicon glyphicon-cog"></a>
                                    </span>
                                    <textarea name="textarea" class="form-control" placeholder="编辑评分要点" rows="1" style="width: 58%; margin-top: 2px;"></textarea>
    								<input type="hidden" name="btnType" value="" />
                                </li>
                            </ol>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div id="template" class="pagecon" style="display: none;">
        	<textarea id="content" name="content"></textarea>
            <div class="clearfix"></div>
       	</div>
        <div class="pagefoot">
            <div class="scoreList">
                <p>
                    <strong>总分：</strong>
                    <span class="red" id="score">0</span>/
                    <input type="text" id="sconum" style="width: 50px;" value="100" onKeyPress="return verifyDigital(event);">
                </p>
            </div>
            <div class="text-center">
                <a href="javascript:;" id="save" class="btn btn-info btn-lg" >提交</a>
                <!-- <a href="javascript:;" class="btn btn-primary btn-lg" id="reset">重置</a>
                <a href="<tm:ctx/>/jsp/web/basicdata/scoreInfo.jsp?id=128&type_id=1" target="_blank" class="btn btn-primary btn-lg">预览评分表</a> -->
            </div>
        </div>
    </div>
</body>
</html>