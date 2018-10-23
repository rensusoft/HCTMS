$(function(){
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
		var age1=$("#age1").val();
		if(age1!=""){
		if(isNaN(age1)){
			myMsg("年龄格式错误,请输入数字！",'50%','50%');
			return false;
		}}
		var stuClass=$("#stuClass").val();
		if(stuClass!=""){
		if(isNaN(stuClass)){
			myMsg("届次格式错误,请输入数字！",'50%','50%');
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