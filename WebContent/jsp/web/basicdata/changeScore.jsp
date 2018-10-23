<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/hctms" prefix="tm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>改变评分方式</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<tm:jsandcss/>
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/metro.css" />
    <link rel="stylesheet" type="text/css" href="<tm:ctx/>/css/style.css" />
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/Layer-1.9.3/layer.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/custom.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/jsonSelectTree.js"></script>
    <script type="text/javascript" src="<tm:ctx/>/js/singleSelectTree.js"></script>
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/jsp/web/basicdata/js/changeScore.js"></script>
</head>
<body>
    <div class="changeScore">
    	<input type="hidden" id="submainid" value="" />
    	<input type="hidden" id="subdetailid" value="" />
        <span>评分方式：</span>
    	<select class="form-control" id="styleValue">
            <option>分值类型</option>
            <option>YES/NO类型</option>
            <option>比例类型</option>
        </select>
        <div class="style1">
            <div class="instance margin_top">
                <span>示例：</span>
                + <input type="text" value="0" class="spinner"> - <br>
                <span>说明：请填写分值</span>
            </div>
            <div class="fillbox">
                <label>分值：</label>
                + <input type="text" value="0" class="spinner"> - 
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
	            <span>说明：输入框内请键入评分选项，如：YES/NO</span>
	        </div>
	        <div class="fillbox">
                <label>文字：</label>
                <input id="textNoY" type="text" class="form-control" placeholder="例：YES">
                <label>分值：</label>
                <input id="scoreNoY" type="text" class="form-control" placeholder="例：5"><br />
                <label class="color_white">文字：</label>
                <input id="textNoN" type="text" class="form-control" placeholder="例：NO">
                <label>分值：</label>
                <input id="scoreNoN" type="text" class="form-control" placeholder="例：0">
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
	            <input id="scoreNo1" type="text" class="form-control" placeholder="例：5"></div>
	            <div id="numSel2"><label class="color_white">标签：</label>
	            <input id="textNo2" type="text" class="form-control" placeholder="例：不满意">
	            <label>分值：</label>
	            <input id="scoreNo2" type="text" class="form-control" placeholder="例：0"></div>
	            <div id="numSel3" style="display: none;"><label class="color_white">标签：</label>
	            <input id="textNo3" type="text" class="form-control">
	            <label>分值：</label>
	            <input id="scoreNo3" type="text" class="form-control"></div>
	            <div id="numSel4" style="display: none;"><label class="color_white">标签：</label>
	            <input id="textNo4" type="text" class="form-control">
	            <label>分值：</label>
	            <input id="scoreNo4" type="text" class="form-control"></div>
	            <div id="numSel5" style="display: none;"><label class="color_white">标签：</label>
	            <input id="textNo5" type="text" class="form-control">
	            <label>分值：</label>
	            <input id="scoreNo5" type="text" class="form-control"></div>
	        </div>
	        <div class="text-center margin_top">
	            <a class="btn btn-info" href="javascript:;" id="styleBtn3">确定</a>
	        </div>
	    </div> 
    </div>
</body>
</html>