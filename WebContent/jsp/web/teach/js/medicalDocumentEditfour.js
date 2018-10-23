var mr_id='';
$(function () {
	$("#content").attr("style","height:"+(pHeight-230)+"px");
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
	  		$("#mr_name").val(res.data.mr_name);
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
});
//保存
function save(){
	var mr_name = $("#mr_name").val();
	var p_name = $("#p_name").val();
	var p_sex = $("#p_sex").val();
	var p_age = $("#p_age").val();
	var p_deptname = $("#p_deptname").val();
	var p_bednum = $("#p_bednum").val();
	var p_pid = $("#p_pid").val();
	if(mr_name == ''){
		myalert_error("医疗文书名称不能为空！");
		return;
	}
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
	var url=ctx + "/teachweb/saveMedicalRecord.action?action=edit";
	var postData = {
				id:mr_id,
				type_id:3,
				mr_name:mr_name,
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
				content:content
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
//清空
function reset(){
	$("#mr_name").val("");
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	UE.getEditor('content').setContent("");
}
//老师批改后保存
function saveTea() {
	var mr_name = $("#mr_name").val();
	var p_name = $("#p_name").val();
	var p_sex = $("#p_sex").val();
	var p_age = $("#p_age").val();
	var p_deptname = $("#p_deptname").val();
	var p_bednum = $("#p_bednum").val();
	var p_pid = $("#p_pid").val();
	if(mr_name == ''){
		myalert_error("医疗文书名称不能为空！");
		return;
	}
	if(checkStr(mr_name,2)==false){
		myalert_error("医疗文书名称不能含有特殊字符！");
		return ;
	}
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

	var url=ctx + "/teachweb/saveMedicalRecordTea.action?action=add";
	var postData = {
				id:mr_id,
				type_id:3,
				mr_name:mr_name,
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
				content:content
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
//清空
function resetTea(){
	$("#mr_name").val("");
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	UE.getEditor('content').setContent("");
}

