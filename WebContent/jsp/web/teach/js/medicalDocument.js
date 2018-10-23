var type_id='';

var businessplanmag_iRow;
var businessplanmag_iCol;

var str1='';
var str2='';
var str3='';
var str4='';
$(function () {
	$("#content1").attr("style","height:"+(pHeight-329)+"px");
	$("#content2").attr("style","height:"+(pHeight-257)+"px");
	$("#content3").attr("style","height:"+(pHeight-257)+"px");
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue1 = UE.getEditor('content1',{scaleEnabled:true});
	ue2 = UE.getEditor('content2',{scaleEnabled:true});
	ue3 = UE.getEditor('content3',{scaleEnabled:true});
    //切换到编辑保存页面
    $(".addBtn").click(
        function(){
            $(this).parent().parent().addClass("displayDiv").siblings().removeClass("displayDiv");
        }
    );
    $(".rightBtn").click(
            function(){
                $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
            }
    );
    //切换到jqGrid页面
    $(".leftBtn").click(
            function(){
                $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
            }
    );
    //
    $('.left_btns li').click(function() {
        var i = $(this).index();//下标第一种写法
        $(this).addClass('select').siblings().removeClass('select');
        $('#con li').eq(i).show().siblings().hide();
    });
    //医嘱
    $(".addTableBtn").click(
        function(){
            var str='<tr class="tr_data">' +
                '<td><input type="text" class="longInput form-control advice_name"/></td>' +
                '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>' +
                '<td><input type="text" class="shortInput form-control advice_dose"/></td>' +
                '<td><select class="form-control shortInput dose_unit_code" style="width: 85px">' +
                str1 +
                '</select></td>' +
                '<td><select class="form-control shortInput path_code" style="width: 105px"> ' +
                str2 +
                '</select></td>' +
                '<td><select class="form-control shortInput frequency_code" style="width: 105px">' +
                str3 +
                '</select></td>' +
                '<td><input type="text" class="shortInput form-control total_dose"/></td>' +
                '<td><select class="form-control shortInput total_dose_unit_code" style="width: 85px">' +
                str4 +
                '</select></td>' +
                '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
                '</tr>';
            $(".tableDiv table").children().append(str);
        }
    );
    //
    pageInit();
    //初始化医嘱部分计量单位、用药途径等下拉框
    formOpt();
    //
    $("#sel_dose_unit_code").html(str1);
    $("#sel_path_code").html(str2);
    $("#sel_frequency_code").html(str3);
    $("#sel_total_dose_unit_code").html(str4);
});
function pageInit() {
    //创建主页面医疗文书jqGrid组件
    jQuery("#medicalDocument").jqGrid(
        {
            url: ctx+'/teachweb/selectMedicalDocument.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', '类型', 'type_id', '病人姓名', '住院号', '对应大纲', '书写时间', '审批人', '审批状态', 'correct_status', '操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', fixed: true, hidden: true, key: true},
                {name: 'type_id_str', index: 'type_id_str', width: 110, align: "center"},
                {name: 'type_id', index: 'type_id', hidden: true},
                {name: 'p_name', index: 'p_name', align: "center"},
                {name: 'p_pid', index: 'p_pid', align: "center"},
                {name: 'value', index: 'value', align: "center"},
                {name: 'create_time_str', index: 'create_time_str', width: 130, align: "center"},
                {name: 'correct_auth_id_str', index: 'correct_auth_id_str', align: "center"},
                {name: 'correct_status_str', index: 'correct_status_str', width: 100, align: "center"},
                {name: 'correct_status', index: 'correct_status', hidden: true},
                {name: 'operation', index: 'operation', width: 120, align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            gridview: true,
            viewrecords: true,
            postData: {
            	state: "Y"
            },
            width: pWidth-49,
            height: pHeight-120,
            resizable:false,
            modal:true,
            gridComplete: function () {
                var ids = jQuery("#medicalDocument").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var type_id = jQuery("#medicalDocument").jqGrid('getRowData',id).type_id;
                    var correct_status = jQuery("#medicalDocument").jqGrid('getRowData',id).correct_status;
                    var ee = "<a title='' href='#' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDocument').jqGrid('saveRow','" + id + "',view('" + id + "','" + type_id + "','" + correct_status + "'));\">查看</a>&nbsp;&nbsp;&nbsp;";
                    var se = "<a title='' href='#' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDocument').jqGrid('saveRow','" + id + "',upd('" + id + "','" + type_id + "','" + correct_status + "'));\">编辑</a>&nbsp;&nbsp;&nbsp;";
                    var ce = "<a title='' href='#' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDocument').jqGrid('saveRow','" + id + "',del('" + id + "','" + type_id + "','" + correct_status + "'));\">删除</a>";
                    jQuery("#medicalDocument").jqGrid("setRowData", id, {operation: ee + se + ce});
                }
            }
        });
    //创建子页面诊断jqGrid组件
    jQuery("#medicalDiagnose").jqGrid(
        {
            url: '',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', '诊断内容', 'ICD码','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', fixed: true, hidden: true, key: true},
                {name: 'diag_name', index: 'diag_name', width: 30, editable: true, align: "center"},
                {name: 'icd_code', index: 'icd_code', width: 30, editable: true, align: "center"},
                {name: 'act', index: 'act', width: 30, align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager2',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: 1100,
            height:pHeight-370,//200,
            cellEdit: true,
            cellsubmit: 'clientArray',
            gridComplete: function () {
                var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var ce = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDiagnose').jqGrid('saveRow','" + id + "',remove('" + id + "'));\">删除</a>";
                    jQuery("#medicalDiagnose").jqGrid("setRowData", id, {act: ce});
                }
            },
            beforeEditCell:function(rowid,cellname,value,iRow,iCol){
                businessplanmag_iRow=iRow;
                businessplanmag_iCol=iCol;
            }
        });
}
//刷新
function orgaInit(){
	$("#medicalDocument").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//搜索
function search(){
	var start_date = $("#start_date").val();
	var end_date = $("#end_date").val();
	$("#medicalDocument").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y",
        	start_date: start_date,
        	end_date: end_date
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//诊断jqGrid添加新的一行
function addRow(){
	var diag_name = $("#diag_name").val();
	var icd_code = $("#icd_code").val();
	if(diag_name == ''){
		myalert_error("诊断内容不能为空！");
		return;
	}
	 if(checkStr(diag_name,2)==false){
 		myalert_error("诊断内容不能含有特殊字符！");
 		return false;
 	}
	if(icd_code == ''){
		myalert_error("ICD码不能为空！");
		return;
	}
	var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
	//获得新添加行的行号（数据编号）
	var newrowid = 0;
	if(ids.length > 0){
		newrowid = parseInt(ids[0]) + 1;
	}
    var dataRow = {  
	    id: newrowid,
	    diag_name: diag_name,
	    icd_code: icd_code,
	    act: "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDiagnose').jqGrid('saveRow','" + newrowid + "',remove('" + newrowid + "'));\">删除</a>"
    };    
    //将新添加的行插入到第一列
    $("#medicalDiagnose").jqGrid("addRowData", newrowid, dataRow, "first");
    $("#diag_name").val("");
	$("#icd_code").val("");
}
//诊断jqGrid的删除
function remove(rowId) {
	myConfirm("是否删除？", "", "", function(){
		$("#medicalDiagnose").jqGrid("delRowData", rowId);
		layer.closeAll();
	});
}
//拼接组织下拉框选项
function formOpt(){
	$.ajax({
		async: false,
	  	type: 'POST',
		url: ctx + "/teachweb/formMedicalAdviceOpt.action",
		data: {
			
		},
		dataType: 'json',
	  	success:function(res){
	  		var data = res.data;
	  		str1 = '<option value="">-请选择-</option>';
	  		str2 = '<option value="">---请选择---</option>';
	  		str3 = '<option value="">---请选择---</option>';
	  		str4 = '<option value="">-请选择-</option>';
	  		for(var i = 0; i < data.length; i++){
	  			if(data[i].sup_code == 'dose_unit_code'){
	  				str1 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'path_code'){
	  				str2 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'frequency_code'){
	  				str3 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'total_dose_unit_code'){
	  				str4 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}
	  		}
	  	}
	});
}
//保存（除医嘱外）
function save(object){
	var type_id = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".hid_type_id").val();
	var mr_name = "";
	var content = "";
	var medicalDiagnoseList = [];
	if(type_id == 1){
		$("#medicalDiagnose").jqGrid("saveCell",businessplanmag_iRow,businessplanmag_iCol);//  取消编辑
		mr_name = "入院记录";
		content = UE.getEditor('content1').getContent();
		var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
        for (var i = 0; i < ids.length; i++) {
        	var medicalDiagnose = {};
        	var id = ids[i];
        	var diag_name = jQuery("#medicalDiagnose").jqGrid('getRowData',id).diag_name;
            if(diag_name == ''){
            	myalert_error("第"+(i+1)+"行诊断内容不能为空！");
    			return;
            }
            if(checkStr(diag_name,2)==false){
         		myalert_error("第"+(i+1)+"诊断内容不能含有特殊字符！");
         		return false;
         	}
            medicalDiagnose.diag_name = diag_name;
            var icd_code = jQuery("#medicalDiagnose").jqGrid('getRowData',id).icd_code;
            if(icd_code == ''){
            	myalert_error("第"+(i+1)+"行ICD码不能为空！");
    			return;
            }
            medicalDiagnose.icd_code = icd_code;
            medicalDiagnoseList.push(medicalDiagnose);
        }
	}else if(type_id == 2){
		mr_name = $("#mr_name2").val();
		content = UE.getEditor('content2').getContent();
	}else if(type_id == 3){
		mr_name = $("#mr_name3").val();
		content = UE.getEditor('content3').getContent();
	}
	var p_name = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_name").val();
	var p_sex = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_sex").val();
	var p_age = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_age").val();
	var p_deptname = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_deptname").val();
	var p_bednum = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_bednum").val();
	var p_pid = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_pid").val();
	if(mr_name == ''){
		myalert_error("医疗文书名称不能为空！");
		return;
	}
	if(checkStr(mr_name,2)==false){
		myalert_error("医疗文书不能含有特殊字符！");
		return false;
	}
	if(p_name == ''){
		myalert_error("姓名不能为空！");
		return false;
	}
	if(checkStr(p_name,2)==false){
		myalert_error("姓名不能含有特殊字符！");
		return false;
	}
	if(p_sex == ''){
		myalert_error("性别不能为空！");
		return;
	}
	if(checkStr(p_sex,2)==false){
		myalert_error("性别不能含有特殊字符！");
		return ;
	}
	if(p_age == ''){
		myalert_error("年龄不能为空！");
		return;
	}
	if(checkStr(p_age,1)==false){
		myalert_error("年龄格式输入错误，请输入正整数！");
		return;
	}
	if(p_deptname == ''){
		myalert_error("科室不能为空！");
		return;
	}
	if(checkStr(p_deptname,2)==false){
		myalert_error("科室不能含有特殊字符！");
		return ;
	}
	if(p_bednum == ''){
		myalert_error("床号不能为空！");
		return;
	}
	if(checkStr(p_bednum,2)==false){
		myalert_error("床号不能含有特殊字符！");
		return ;
	}
	if(p_pid == ''){
		myalert_error("住院号不能为空！");
		return;
	}
	if(checkStr(p_pid,2)==false){
		myalert_error("住院号不能含有特殊字符！");
		return ;
	}
	if(content == ''){
		myalert_error("内容不能为空！");
		return;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}
	var url=ctx + "/teachweb/saveMedicalRecord.action?action=add";
	var postData = {
			    type_id:type_id,
				mr_name:mr_name, 
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
				content:content,
			 	rowData:JSON.stringify(medicalDiagnoseList)
			};
	doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
				 reset(object);
				 $(".li_first").addClass('select').siblings().removeClass('select');
			     $('#con li').eq(0).show().siblings().hide();
				 $(".rightBtn").parent().removeClass("displayDiv");
				 $(".leftBtn").parent().addClass("displayDiv");
				 orgaInit();
				 layer.closeAll();
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}
//清空（除医嘱外）
function reset(object){
	var type_id = $(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".hid_type_id").val();
	if(type_id == 1){
		UE.getEditor('content1').setContent("");
		jQuery("#medicalDiagnose").jqGrid("clearGridData");
//		var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
//		var len = ids.length;  
//        for (var i = 0; i < len; i++) {
//        	$("#medicalDiagnose").jqGrid('delRowData',ids[0]);
//        }
	}else if(type_id == 2){
		$("#mr_name2").val("");
		UE.getEditor('content2').setContent("");
	}else if(type_id == 3){
		$("#mr_name3").val("");
		UE.getEditor('content3').setContent("");
	}
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_name").val("");
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_sex").val("");
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_age").val("");
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_deptname").val("");
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_bednum").val("");
	$(object).parent(".btns").prev(".medicalDocumentCont").children(".perInfor").children(".p_pid").val("");
	$("#diag_name").val("");
	$("#icd_code").val("");
}
//保存医嘱
function saveMedicalAdvice(){
	var p_name = $("#p_name").val();
	var p_sex = $("#p_sex").val();
	var p_age = $("#p_age").val();
	var p_deptname = $("#p_deptname").val();
	var p_bednum = $("#p_bednum").val();
	var p_pid = $("#p_pid").val();
	if(p_name == ''){
		myalert_error("姓名不能为空！");
		return;
	}
	if(checkStr(p_name,2)==false){
		myalert_error("姓名不能含有特殊字符！");
		return ;
	}
	if(p_sex == ''){
		myalert_error("性别不能为空！");
		return;
	}
	if(checkStr(p_sex,2)==false){
		myalert_error("性别不能含有特殊字符！");
		return ;
	}
	if(p_age == ''){
		myalert_error("年龄不能为空！");
		return;
	}
	if(checkStr(p_age,1)==false){
		myalert_error("年龄格式输入错误，请输入正整数！");
		return;
	}
	if(p_deptname == ''){
		myalert_error("科室不能为空！");
		return;
	}
	if(checkStr(p_deptname,2)==false){
		myalert_error("科室不能含有特殊字符！");
		return ;
	}
	if(p_bednum == ''){
		myalert_error("床号不能为空！");
		return;
	}
	if(checkStr(p_bednum,2)==false){
		myalert_error("床号不能含有特殊字符！");
		return ;
	}
	if(p_pid == ''){
		myalert_error("住院号不能为空！");
		return;
	}
	if(checkStr(p_pid,2)==false){
		myalert_error("住院号不能含有特殊字符！");
		return ;
	}
	var medicalAdviceSubList = [];
	var i = 0;
	var flag = false;
	var error = "";
	$(".tr_data").each(function(){
		i++;
		var medicalAdviceSub = {};
		var advice_name = $(this).find(".advice_name").val();
		if(advice_name == ''){
			error = "医嘱明细第" + i + "行的药品名称不能为空！"
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_name = advice_name;
		var advice_spec = $(this).find(".advice_spec").val();
		if(advice_spec == ''){
			error = "医嘱明细第" + i + "行的药品规格不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_spec = advice_spec;
		var advice_dose = $(this).find(".advice_dose").val();
		if(advice_dose == ''){
			error = "医嘱明细第" + i + "行的剂量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(advice_dose)){
			error = "医嘱明细第" + i + "行的剂量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_dose = advice_dose;
		var dose_unit_code = $(this).find(".dose_unit_code").val();
		if(dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的剂量单位不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.dose_unit_code = dose_unit_code;
		var path_code = $(this).find(".path_code").val();
		if(path_code == ''){
			error = "医嘱明细第" + i + "行的用药途径不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.path_code = path_code;
		var frequency_code = $(this).find(".frequency_code").val();
		if(frequency_code == ''){
			error = "医嘱明细第" + i + "行的用药频率不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.frequency_code = frequency_code;
		var total_dose = $(this).find(".total_dose").val();
		if(total_dose == ''){
			error = "医嘱明细第" + i + "行的总量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(total_dose)){
			error = "医嘱明细第" + i + "行的总量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.total_dose = total_dose;
		var total_dose_unit_code = $(this).find(".total_dose_unit_code").val();
		if(total_dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的总量单位不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.total_dose_unit_code = total_dose_unit_code;
		medicalAdviceSubList.push(medicalAdviceSub);
	});
	if(flag){
		myalert_error(error,"30%","40%");
		return;
	}
	var url=ctx + "/teachweb/saveMedicalAdvice.action?action=add";
	var postData = {
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
			 	rowData:JSON.stringify(medicalAdviceSubList)
			};
	doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
				 resetMedicalAdvice();
				 $(".li_first").addClass('select').siblings().removeClass('select');
			     $('#con li').eq(0).show().siblings().hide();
				 $(".rightBtn").parent().removeClass("displayDiv");
				 $(".leftBtn").parent().addClass("displayDiv");
				 orgaInit();
				 layer.closeAll();
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}

function resetMedicalAdvice(){
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	var str=
	'<tr class="tr_title"><td>药品名称</td><td>药品规格</td><td>剂量</td><td>剂量单位</td><td>用药途径</td><td>用药频率</td><td>总量</td><td>总量单位</td></tr>' +
	'<tr class="tr_data">' +
    '<td><input type="text" class="longInput form-control advice_name"/></td>' +
    '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>' +
    '<td><input type="text" class="shortInput form-control advice_dose"/></td>' +
    '<td><select id="sel_dose_unit_code" class="form-control shortInput dose_unit_code" style="width: 85px">' +
    str1 +
    '</select></td>' +
    '<td><select id="sel_path_code" class="form-control shortInput path_code" style="width: 105px"> ' +
    str2 +
    '</select></td>' +
    '<td><select id="sel_frequency_code" class="form-control shortInput frequency_code" style="width: 105px">' +
    str3 +
    '</select></td>' +
    '<td><input type="text" class="shortInput form-control total_dose"/></td>' +
    '<td><select id="sel_total_dose_unit_code" class="form-control shortInput total_dose_unit_code" style="width: 85px">' +
    str4 +
    '</select></td>' +
    '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
    '</tr>';
	$(".tableDiv table").html("");
	$(".tableDiv table").html(str);
} 

//查看
function view(id,type_id,correct_status){
	if(id == ''){
		myalert_error('请选择医疗文书！');
		return false;
	}
	if(correct_status == 'N'){
		var url = "";
		if(type_id == 1 || type_id == 2 || type_id == 3){
			url = ctx + "/teachweb/selectMedicalRecordById.action?id=" + id + "&type_id=" + type_id;
		}else{
			url = ctx + "/teachweb/selectMedicalAdviceById.action?id=" + id;
		}
		mypopdiv(2,"查看医疗文书",'1150px',(pHeight-50)+'px','50px',(pWidth-1000)/2,'N',url);
	}else{//  作对比
		mypopdiv(2,"查看医疗文书",'1100px',(pHeight-50)+'px','50px',(pWidth-1000)/2,'N',ctx + '/teachweb/medicalDocumentComparison.action?id='+id+'&type_id='+type_id);
	}
}
//编辑
function upd(id,type_id,correct_status){
	if(id == ''){
		myalert_error('请选择医疗文书！');
		return false;
	}
	if(correct_status == 'Y'){
		myalert_error('此医疗文书已被批改，不可作编辑！');
		return false;
	}
	var url = "";
	if(type_id == 1){
		url = ctx + "/jsp/web/teach/medicalDocumentEdittwo.jsp?mr_id=" + id;
	}else if(type_id == 2){
		url = ctx + "/jsp/web/teach/medicalDocumentEditthr.jsp?mr_id=" + id;
	}else if(type_id == 3){
		url = ctx + "/jsp/web/teach/medicalDocumentEditfour.jsp?mr_id=" + id;
	}else{
		url = ctx + "/jsp/web/teach/medicalDocumentEditone.jsp?mam_id=" + id;
	}
	window.location.href = url;
}
//删除
function del(id,type_id,correct_status){
	if(id == ''){
		myalert_error('请选择医疗文书！');
		return false;
	}
	if(correct_status == 'Y'){
		myalert_error('此医疗文书已被批改，不可作删除！');
		return false;
	}
	myConfirm("是否要删除？","","",
			function(index){
				 var url=ctx+'/teachweb/delMedicalDocument.action';
				 var postData={
						id:id,
						type_id:type_id,
						state:"X"
				 };
				 doAjax(url,postData,2,function(res){
					 if(res.success == true){
						 orgaInit();
						 myalert_success(res.data);
					 }else{
						 myalert_error(res.data);
					 }
				 });
			}
	);
}
//医嘱删除一行
function deleteCurrentRow(obj){
	myConfirm("是否要删除？","","",
			function(){
				var tr=obj.parentNode.parentNode;
				var tbody=tr.parentNode;
				//只剩行首时保留一行
			    if(tbody.rows.length == 2){
			    	myalert_error('必须保留一行！');
					return;
			    }
			    tbody.removeChild(tr);
			    layer.closeAll();
			}
	);
}