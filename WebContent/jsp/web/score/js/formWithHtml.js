var form_id = '';
var type_id = '';
var form_name = '';
var flag = '';
var s_user_auth_id = '';
var s_orga_id = '';
var create_auth_id = '';
var sfm_id = '';

var id = '';
$(function(){
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
	form_id = Request['form_id'];
	form_name = Request['form_name'];
	type_id = Request['type_id'];
	flag = Request['flag'];
	s_user_auth_id = Request['s_user_auth_id'];
	s_orga_id = Request['s_orga_id'];
	create_auth_id = Request['create_auth_id'];
	sfm_id = Request['sfm_id'];
	var form_type = '';
	if(type_id != null && type_id == 1){
		form_type = '评分表单';
	}else if(type_id != null && type_id == 2){
		form_type = '普通表单';
	}
	$("#name").html(form_name);
	$("#form_type").text(form_type);
	//
	initTable();
});
//初始化表格
function initTable(){
	//加载普通表单的HTML表单数据
	$.ajax({
		type: 'POST',
		url :  ctx+'/scoreweb/getTableContent.action',
		dataType: 'json',
		async: false,
		data:{
			form_id : form_id,
			flag : flag,
			s_user_auth_id : s_user_auth_id,
			s_orga_id : s_orga_id,
			create_auth_id : create_auth_id,
			sfm_id : sfm_id
		},
		success:function(res) {
			id = res.data.sfm_id;
			//模板显示
			$("#contentHtml").html(res.data.t_content);
			//改变HTML内容
			TxtChangeHTML();
		}
	});
	
}
//将内容改变为HTML
function TxtChangeHTML(){
	//把所有checkbox勾选的变成钩
	$('.yes').each(function(){
		$(this).parent().html("<input type=\"checkbox\" checked=\"checked\" />");
	});
	$('.non').each(function(){
		$(this).parent().html("<input type=\"checkbox\" />");
	});
//	//把所有单行输入框变成普通文字
//	$('input[type=text]').each(function(){
//		var val = $(this).val();
//		$(this).parent().html("<span>"+val+"</span>");
//	});
//	//把所有多行输入框变成普通文字
//	$('textarea').each(function(){
//		var val = $(this).val();
//		$(this).parent().html("<span>"+val+"</span>");
//	});
//	//把所有多行下拉框变成普通文字
//	$('select').each(function(){
//		var val = $(this).val();
//		$(this).parent().html("<span>"+val+"</span>");
//	});
}
//保存填写的表单内容
function saveData(){
	var dataType = '';
	if(type_id != null && type_id == 1){
		dataType = 'mark';
	}else if(type_id != null && type_id == 2){
		dataType = 'html';
	}
//	myConfirm("确定保存表单？","","",function(){
		//改变HTML内容
		HTMLChangeTxt();
		//存贮html表单数据，并给此学生办理入科
		$.ajax({
			type: 'POST',
			url :  ctx+'/scoreweb/stuIndept.action',
			dataType: 'json',
			async: false,
			data:{
				dataType:dataType,
				form_id : form_id,
				content:$("#contentHtml").html(),
				form_name:form_name,
				flag:flag,
				id:id,
				s_user_auth_id:s_user_auth_id,
				s_orga_id:s_orga_id
			},
			success:function(data) {
				if(data.success){
					myalert_success(data.data,"","",function(){
						parent.loadAgain();
					});
				}else{
					myalert_error(data.data,"","");
				}
			}
		});
//	});
}