$(function(){
	var id =getUrlParam("id");
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getFormInfoById.action',
		dataType: 'json',
		data:{
			id:id
		},
		async: false,
		success:function(data) {
			data = data.data;
			$("#form_state").html(data.form_state);
			$("#availability").val(data.availability);
			$("#time").html(data.time);
			$("#name").val(data.name);
			$("#content").val(data.t_content);
			$("#id").val(data.related_id);
		}
	});
});

function submitData(){
	var formName = $("#name").val();
	if(formName==""){
		myMsg("请填写表单名称！","30%","40%");
		return;
	}
	var content = CKEDITOR.instances.content.getData();
	if(content==""){
		myMsg("请填写表单内容！","30%","40%");
		return;
	}
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/updateHtmlTemplat.action',
		dataType: 'json',
		data:{
			id:$("#id").val(),
			name:formName.replace(/(^\s+)|(\s+$)/g, ""),
			content:content
		},
		success:function(data) {
			if(data.success==true){
				myalert_success("编辑成功！","30%","40%",function(index){
					parent.formInit();
					parent.closeMyWindows();
				});
			}else{
				myalert_error("编辑失败！","30%","40%",function(index){
					 parent.closeMyWindows();
				});
			}
		}
	});
}