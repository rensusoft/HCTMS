$(function () {
	$.ajax({
		type : 'POST',
		url : ctx + '/configweb/getStuType.action',
		dataType : 'json',
		success : function(data) {
			var a1="<option value='-1'>--请选择--</option>";
			for ( var int = 0; int < data.stuTypelist.length; int++) {
				a1+= "<option value=" + data.stuTypelist[int].type_code
						+ " >" + data.stuTypelist[int].type_name
						+ "</option>";
			}
			$("#stuTypeCode").html(a1);
			var a2= "<option value='-1'>--请选择--</option>";
			for ( var int = 0; int < data.sysDictSubs.length; int++) {
				a2+= "<option value="
						+ data.sysDictSubs[int].item_type_name + " >"
						+ data.sysDictSubs[int].item_type_name + "</option>";
			}
			$("#major").html(a2);
		}
	});

});

//提交新增的轮转规则
function addtrI(){
//	alert("22222");
	if($("#name").val()==null||$("#name").val()==""){
		myMsg("方案名称不能为空！",'50%','50%');
		return false;
	}
	if(checkStr($("#name").val(),2)==false){
		myMsg("方案名称不能含有特殊字符！",'50%','50%');
		return false;
	}
	if($("#stuTypeCode").val()=="-1"){
		myMsg("请选择学生类型！",'50%','50%');
		return false;
	}
	if($("#major").val()=="-1"){
		myMsg("请选择培训专业！",'50%','50%');
		return false;
	}
	if($("#timelong").val()==null||$("#timelong").val()==""){
		myMsg("年限不能为空！",'50%','50%');
		return false;
	}
	if(isNaN($("#timelong").val())||checkStr($("#timelong").val(),1)==false){
			myMsg("年限格式输入错误，请输入正整数！",'50%','50%');
			return false;
		};
	var url=ctx+'/configweb/addTrain.action';
	var post={
			stuTypeCode:$("#stuTypeCode").val(),
			major:$("#major").val(),
			name:$("#name").val(),
			timelong:$("#timelong").val()
	};
	doAjax(url,post,2,function(res){
		parent.closeMyWindows(res);
	});
//	$.post(
//			url, 
//			post, 
//			function (res) {
//				if(res.success==true){
//					myalert_success(res.data,'10%');
//					parent.closeMyWindows();
//				}else{
//					myalert_error(res.data,'10%');
//				}
//			}
//		
//		);
}


//重置
function reset(){
	$("input[type=text]").val("");
	$("#name").focus();
	$('select').prop('selectedIndex', 0);
}
