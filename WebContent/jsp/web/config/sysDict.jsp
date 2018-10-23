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
	
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/jsp/web/config/css/sysDict.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/bootstrap.min.css" />
 	<script type="text/javascript" src="<tm:ctx/>/jsp/web/config/js/sysDict.js"></script>

	<script type="text/javascript" src="<tm:ctx/>/js/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/grid.locale-cn.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<tm:ctx/>/css/jqgrid/redmond/jquery-ui-1.8.16.custom.css" />
	
	<script type="text/javascript" src="<tm:ctx />/js/PublicMethed.js"></script>	
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.common.menuAndGrid.js"></script>
	<script type="text/javascript" src="<tm:ctx/>/js/jqgrid/jquery.contextmenu.r2.js"></script>
</head>
<body>
<div style="margin:0 auto;width:96%">
    <div style="float: left;position: relative;">
        <div>
            <table id="SysDictMainList"></table>
            <div id="pager1"></div>
        </div>
        <div class="table_form">
            <div class="table_head">
                <a>项目新增<span><img src="<tm:ctx/>/img/select_xl.png" alt=""/></span></a>
            </div>
            <div class="table_body">
            	<div id="div_height" style="overflow:auto;margin-bottom: 45px;">
                <form action="">
                    <table>
                        <tr> 
                            <td><i>*</i>项目代码:</td>
                            <td><input id="item_code" type="text" onblur="item_code_onblur()" required  />  <input id="item_code_old" type="hidden" /></td>
                            <td><span id="item_code_span"></span></td>
                        </tr>
                        <tr>
                            <td><i>*</i>项目名称:</td>
                            <td>
                                <input id="item_name" type="text" required />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>拼音码:</td>
                            <td>
                                <input id="py_code" type="text"/>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>排序码(数字):</td>
                            <td>
                                <input id="ordinal" type="text" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
                            </td>
                            <td></td>
                        </tr>
                        <tr >
                            <td>描述:</td>
                            <td>
                                <input id="item_describe" type="text"/>
                            </td>
                            <td></td>
                        </tr>

                        <tr >
                            <td>其他代码:</td>
                            <td>
                                <input id="other_code" type="text"/>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                    <div class="btn_position">
                        <br/>
                        <button class="layui-layer-btn0" id="buttonSysDictMain" onclick="submitSysDictMain()">保存</button>
                        <button class="layui-layer-btn1"  type="reset">重置</button>
                    </div>
                </form>
               </div>
            </div>

        </div>
    </div>
   <div style="float: right;position: relative">
        <div>
            <table id="SysDictSubList"></table>
            <div id="pager2"></div>
        </div>
        <div class="table_form2">
        <div class="table_head2">
                <a>项目明细新增<span><img src="<tm:ctx/>/img/select_xl.png" alt=""/></span></a>
            </div>
            <div class="table_body2">
            <div id="div_height2" style="overflow:auto;margin-bottom: 45px;">
                <form action="">
                    <table>
                        <tr >
                            <td>主表项目代码：</td>
                            <td>
                                <input type="text" id="item_code1" class="form-control"  readonly/>
                                <input id="sup_code" type="hidden" value=""/>
                                <input id="item_type_code_old" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr >
                            <td>主表项目名称：</td>
                            <td>
                                <input type="text" id="item_name1" class="form-control" readonly />
                            </td>
                        </tr>
                        <tr >
                            <td><i>*</i>类型代码：</td>
                            <td>
                                <input id="item_type_code" type="text" required />
                            </td>
                        </tr>
                        <tr >
                            <td><i>*</i>类型名称：</td>
                            <td><input id="item_type_name" type="text" required /></td>
                        </tr>
                        <tr >
                            <td>排序码(数字)：</td>
                            <td>
                                <input id="ordinal2" type="text" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
                            </td>
                        </tr>
                    </table>
                    <div class="btn_position">
                        <br/>
                        <button class="layui-layer-btn0" onclick="submitSysDictSub()" >保存</button>
                        <button class="layui-layer-btn1" type="reset">重置</button>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenu" id="myMenu" style="display:none"> 
	    <ul style="width: 200px;">
	        <li id="updateDictMain"> 
	            <span class="ui-icon ui-icon-copy" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana">编辑</span> 
	        </li>
	        <li id="delectDictMain"> 
	            <span class="ui-icon ui-icon-pencil" style="float:left"></span> 
	            <span style="font-size:14px; font-family:Verdana">删除</span> 
	        </li>
	    </ul> 
</div> 
<script>
    $(".table_head").click(function () {
    	type=1;
    	 $("#item_code_span").text("");
        $("#item_code").val("");
        $("#item_code_old").val("");
	    $("#item_name").val("");
	    $("#py_code").val("");
	    $("#ordinal").val("");
	    $("#item_describe").val("");
	    $("#other_code").val("");
        $(".table_body").toggle();
        if ($(".table_body").is(":visible")) {
            $("#div_height").css("height", pHeight/2-100);
            $("#SysDictMainList").setGridHeight(pHeight/2-100);
            $(".table_body").fadeIn();
            $(".table_head").css({
                "border-bottom-left-radius": "0",
                "border-bottom-right-radius": "0"
            });
            $(".table_head a span img").attr( {src:"<tm:ctx/>/img/select_xlf.png"});
            $("#item_code").focus();
        } else {
        	$("#buttonSysDictMain").attr("disabled", false);
            $(".table_body").fadeOut();
            $("#SysDictMainList").setGridHeight(pHeight-100);
            $(".table_head").css({
                "border-bottom-left-radius": "3px",
                "border-bottom-right-radius": "3px"
            });
            $(".table_head a span img").attr( {src:"<tm:ctx/>/img/select_xl.png"});
            $(".table_head a").html("项目新增<span><img src='<tm:ctx/>/img/select_xl.png'/></span>");
        }
    });
    $(".table_head2").click(function () {
    	if($("#sup_code").val()==""){
    		myMsg('请选择项目！', '40%', '45%', 3000);
    		return false;	
    	}
    	$("#item_type_code").val("");
        $("#item_type_name").val("");
        $("#ordinal2").val("");
        $(".table_body2").toggle();
        if($(".table_body2").is(":visible")){
            $(".table_head2").css({
                "border-bottom-left-radius": "0",
                "border-bottom-right-radius": "0"
            });
            $("#div_height2").css("height", pHeight/2-100);
            $("#SysDictSubList").setGridHeight(pHeight/2-100);
            $(".table_head2 a span img").attr( {src:"<tm:ctx/>/img/select_xlf.png"});
            $("#item_type_code").focus();
        }else{
            $(".table_head2").css({
                "border-bottom-left-radius": "3px",
                "border-bottom-right-radius": "3px"
            });
            $("#SysDictSubList").setGridHeight(pHeight-100);
            $(".table_head2 a span img").attr( {src:"<tm:ctx/>/img/select_xl.png"});
            $(".table_head2 a").html("项目明细新增<span><img src='<tm:ctx/>/img/select_xl.png'/></span>");
        }
    });
</script>
</body>
</html>