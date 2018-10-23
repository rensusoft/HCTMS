$(function(){
	var teacher_auth_id = getUrlParam("teacher_auth_id");
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
	var teach_name = Request['teach_name'];
	train_status_flag=Request['train_status_flag'];
	//拼接带教老师下拉框
	$.ajax({
		url:ctx+'/teachweb/formTeacherSelect.action',
		async:false,
		data:{}, 
		dataType: 'json',
		success:function(res) {
			 var str = '';
			 str += '<option value="' + teacher_auth_id + '">' + teach_name + '</option>';
			 for(var i = 0;i < res.data.length; i++){
				 if((res.data)[i].auth_id != teacher_auth_id){
					 str += '<option value="' + (res.data)[i].auth_id + '">' + (res.data)[i].user_name + '</option>';
				 }
			 }
//			 alert(str);
			 $("#select_teacher").html(str);
		}
	});
	
	$('#se').selectOrDie();
});
//选择老师
function changeRotateTeacher(){
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	var id = Request['id']; 
	var teacher_auth_id = $("#select_teacher").val(); 
	var url = ctx + "/teachweb/changeRotateTeacher.action";
	var postData = {
			teacher_auth_id : teacher_auth_id,
			id : id
			};
	doAjax(url,postData,2,function(res){
		if(res.success == true){
			myalert_success(res.data, "30%", "40%", function(){
				parent.closeLayer(train_status_flag);
		 });
		}
	});
	
}

