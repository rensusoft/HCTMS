var stu_class='';
var f;
$(function(){
	function GetRequest() { //取URL参数
		var url = window.location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
				} 
			} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	stu_class=Request['stu_class'];
	$("#stu_class").val(stu_class);
	$("#userCode").focus();
	var a=$("#inform").val();
	if(a=='T'){
		$("#information").attr("style","display:none");
		
		$("#stuType").attr("style","display:none");	
		$("#stuTypeName").attr("style","display:none");	
	}else{
		$("#teaInformation").attr("style","display:none");		
	};

	if($("#textalis").val()!=""){
		myMsg($("#textalis").val(),'50%','50%');
	}
	
});




function validate(form) {
		var a=$("#inform").val();
		if(a=='S'){
		var stuType = $('#stu_type_id').val();
		if (stuType==-1) {
			myMsg('请选择学生类型！','50%','50%');
			return false;
		}			
		}
		var age=$("#age").val();
		if(age!=""){
		if(isNaN(age)){
			myMsg("年龄格式输入错误",'50%','50%');
			return false;
		}}
	return true;
}





function addDiv(){
	var a=$("#inform").val();
    if(a=='T'){
		$("#information").attr("style","display:none");
		$("#stuType").attr("style","display:none");	
		$("#stuTypeName").attr("style","display:none");	
		$("#teaInformation").attr("style","display:block");
	}else{
		$("#teaInformation").attr("style","display:none");
		$("#information").attr("style","display:block");

		$("#stuType").attr("style","display:inline-block");	
		$("#stuTypeName").attr("style","display:inline-block");	
	}
}


function stuSave(){
	var userCode = $('#userCode').val();
	if(userCode==""){
		myMsg("请输入工号",'50%','50%');
		return false;
	}
	var userName = $('#userName').val();
	if(userName==""){
		myMsg("请输入用户名称",'50%','50%');
		return false;
	}
	var stuType = $('#stu_type_id').val();
	if (stuType==-1) {
		myMsg('请选择学生类型！','50%','50%');
		return false;
	}
	var trainPlanId=$('#configs_id').val();
	if (trainPlanId==-1) {
		myMsg('请轮转方案！','50%','50%');
		return false;
	}
	
	var age=$("#stu_age").val();
	if(age!=""){
	if(isNaN(age)){
		myMsg("年龄格式输入错误",'50%','50%');
		return false;
	}}
	selectUserCode();
	if(f==true){
		myMsg("工号已存在",'50%','50%');
		return false;	
	}else{
		$.ajax({
	  		type: 'POST',
		  	url : ctx+'/userauthweb/stuSave.action',
		  	dataType: 'json',
		  	data:{
		  		stu_class:stu_class,
		  		userCode:$("#userCode").val(),
		  		userName:$("#userName").val(),
		  		mobile:$("#mobile").val(),
		  		stu_type_id:$("#stu_type_id").val(),
		  		stu_sex:$("#stu_sex").val(),
		  		stu_age:$("#stu_age").val(),
		  		stu_birthday:$("#stu_birthday").val(),
		  		stu_country:$("#stu_country").val(),//国籍
		  		stu_nationality:$("#stu_nationality").val(),//民族
		  		stu_native:$("#stu_native").val(),//籍贯
		  		stu_hk_address:$("#stu_hk_address").val(),//户口地址
		  		stu_home_address:$("#stu_home_address").val(),//居住地址
		  		political_status:$("#political_status").val(),//政治面貌
				interesting_speciality:$("#interesting_speciality").val(),//兴趣特长
		  		stu_school_name:$("#stu_school_name").val(),//所属院校
		  		stu_num:$("#stu_num").val(),
		  		stu_status:$("#stu_status").val(),
		  		stu_position:$("#stu_position").val(),
		  		stu_edu_level:$("#stu_edu_level").val(),
		  		stu_major_name:$("#stu_major_name").val(),
		  		stu_major_direction:$("#stu_major_direction").val(),
		  		sup_doc_name:$("#sup_doc_name").val(),
		  		tutor_name:$("#tutor_name").val(),
		  		tutor_workplace:$("#tutor_workplace").val(),
		  		tsc_id:$('#configs_id').val()
		  	},
		  	async: false,
	  	 	success:function(data) {
	  	 		if(data.success==true){
					myalert_success("新增成功！","15%","40%");
					parent.reload();
	  	 		}else{
					myalert_error("新增失败！","30%","40%");
	  	 		}
	     	}
		});
	}
	
}




function selectUserCode(){
	var userCode = $('#userCode').val();
	if(userCode!=''){
		$.ajax({
	  		type: 'POST',
		  	url : ctx+'/userauthweb/selectUserCode.action',
		  	dataType: 'json',
		  	data:{
		  		userCode:userCode,
		  	},
		  	async: false,
	  	 	success:function(data) {
	  	 		f= data.success;
	     	}
		});
	}
	
}

