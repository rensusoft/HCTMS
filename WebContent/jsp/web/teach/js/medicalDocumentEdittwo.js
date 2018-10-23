var mr_id='';

var businessplanmag_iRow;
var businessplanmag_iCol;
$(function () {
	$("#content").attr("style","height:"+(pHeight-308)+"px");
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('content',{scaleEnabled:true});
	function GetRequest() { //取URL参数
		var url = window.location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);//unescape--》decodeURI防止参数乱码 
				} 
			} 
		return theRequest; 
	} 
	var Request = new Object(); 
	Request = GetRequest(); 
	mr_id = Request['mr_id'];
	//
	$.ajax({
	  	type: 'POST',
		url : ctx + "/teachweb/updateMedicalRecord.action",
		data : {
			    id:mr_id
			},
		dataType: 'json',
	  	success:function(res) {
	  		$("#p_name").val(res.data.p_name);
	  		$("#p_sex").val(res.data.p_sex);
	  		$("#p_age").val(res.data.p_age);
	  		$("#p_deptname").val(res.data.p_deptname);
	  		$("#p_bednum").val(res.data.p_bednum);
	  		$("#p_pid").val(res.data.p_pid);
	  		//判断ueditor 编辑器是否创建成功
	  		UE.getEditor('content').addListener("ready", function () {
			    // editor准备好之后才可以使用
	  			UE.getEditor('content').setContent(res.data.content);
			});
	    }
	});
	//
    pageInit();
});
function pageInit() {
    //创建子页面诊断jqGrid组件
    jQuery("#medicalDiagnose").jqGrid(
        {
        	url: ctx+'/teachweb/selectMedicalDiagnose.action',//组件创建完成之后请求数据的url
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
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData: {
            	id: mr_id
            },
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
	var newrowid = 0;
	if(ids.length > 0){
		//获得当前最大行号（数据编号）
		var rowid = Math.max.apply(Math,ids);
		//获得新添加行的行号（数据编号）
		newrowid = rowid+1;
	}
    var dataRow = {  
	    id: newrowid,
	    diag_name: diag_name,
	    icd_code: icd_code,
	    act: "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#medicalDiagnose').jqGrid('saveRow','" + newrowid + "',remove('" + newrowid + "'));\">删除</a>"
    };  
    if($("#role_code").text() == 'R_TEA'){
    	//将新添加的行插入到最后一列
        $("#medicalDiagnose").jqGrid("addRowData", newrowid, dataRow, "last");
    }else if($("#role_code").text() == 'R_STU'){
    	//将新添加的行插入到第一列
        $("#medicalDiagnose").jqGrid("addRowData", newrowid, dataRow, "first");
    }
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
//保存
function save(){
	$("#medicalDiagnose").jqGrid("saveCell",businessplanmag_iRow,businessplanmag_iCol);//  取消编辑
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
	var content = UE.getEditor('content').getContent();
	if(content == ''){
		myalert_error("内容不能为空！");
		return;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!");
		return ;
	}
	var medicalDiagnoseList = [];
	var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
    for (var i = 0; i < ids.length; i++) {
    	var medicalDiagnose = {};
    	var id = ids[i];
    	var diag_name = jQuery("#medicalDiagnose").jqGrid('getRowData',id).diag_name;
        if(diag_name == ''){
        	myalert_error("第"+(i+1)+"行诊断内容不能为空！");
			return;
        }
        medicalDiagnose.diag_name = diag_name;
        var icd_code = jQuery("#medicalDiagnose").jqGrid('getRowData',id).icd_code;
        if(icd_code == ''){
        	myalert_error("第"+(i+1)+"行ICD码不能为空！");
			return;
        }
        if(checkStr(diag_name,2)==false){
    		myalert_error("第"+(i+1)+"诊断内容不能含有特殊字符！");
    		return false;
    	}
        medicalDiagnose.icd_code = icd_code;
        medicalDiagnoseList.push(medicalDiagnose);
    }
	var url=ctx + "/teachweb/saveMedicalRecord.action?action=edit";
	var postData = {
				id:mr_id,
				type_id:1,
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
				 window.location.href = ctx + "/jsp/web/teach/medicalDocument.jsp";
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}
function saveTea(){
	$("#medicalDiagnose").jqGrid("saveCell",businessplanmag_iRow,businessplanmag_iCol);//  取消编辑
	var mr_name=$("#mr_name").text();
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
	var content = UE.getEditor('content').getContent();
	if(content == ''){
		myalert_error("内容不能为空！");
		return;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!");
		return ;
	}
	var medicalDiagnoseList = [];
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
	var url=ctx + "/teachweb/saveMedicalRecordTea.action?action=add";
	var postData = {
				id:mr_id,
				type_id:1,
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
				 window.location.href = ctx + "/jsp/web/teach/medicalDocumentTea.jsp";
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}
//清空（除医嘱外）
function reset(){
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	UE.getEditor('content').setContent("");
	jQuery("#medicalDiagnose").jqGrid("clearGridData");
//	var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
//	var len = ids.length;  
//	for (var i = 0; i < len; i++) {
//		$("#medicalDiagnose").jqGrid('delRowData',ids[0]);
//	}
	$("#diag_name").val("");
	$("#icd_code").val("");
}

//清空（老师批改）（除医嘱外）
function resetTea(){
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	UE.getEditor('content').setContent("");
	jQuery("#medicalDiagnose").jqGrid("clearGridData");
//	var ids = jQuery("#medicalDiagnose").jqGrid("getDataIDs");
//	var len = ids.length;  
//	for (var i = 0; i < len; i++) {
//		$("#medicalDiagnose").jqGrid('delRowData',ids[0]);
//	}
	$("#diag_name").val("");
	$("#icd_code").val("");
}