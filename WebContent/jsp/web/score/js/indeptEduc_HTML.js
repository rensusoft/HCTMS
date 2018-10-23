$(function(){
	var id = getUrlParam("id");
	initMain(id);
});

function initMain(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getFormInfoById.action',
		dataType: 'json',
		async: false,
		data:{
			id:id
		},
		success:function(data) {
			data = data.data;
			$("#name").text(data.name);
			if(data.availability=="Y"){
				availText = "有效";
			}else{
				availText = "无效";
			}
			var content ='<p>评分表类型：<span>HTML普通表单</span></p>'+
						'<p>撰写者： <span>'+data.user_name+'</span></p>'+
						'<p>状态：<span>'+availText+'</span></p>'+
						'<p>总分：<span id="score">'+data.total_sconum+'</span></p>';
			id = data.related_id;
			$("#main").html(content);
			initSub(id);
		}
	});
}

function initSub(id){
	//加载普通表单的HTML表单数据
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getHTMLInfo.action',
		dataType: 'json',
		async: false,
		data:{
			mmid:id
//			stuAuthId:_stuAuthId
		},
		success:function(data) {
			if(data.success){
				//加载标题栏
				$("#stu_name").html(data.stuData.user_name);
				$("#stu_type_name").html(data.stuData.stu_type_name);
				$("#dept_name").html(data.deptName);
				$("#indeptEduc_name").html(data.indeptEducName);
				//模板显示
				$("#contentHtml").html(data.htData.t_content);
			}else{
				myalert_error(data.data,"","");
			}
		}
	});
	
}


function saveData(){
	myConfirm("完成入科宣教，确认入科？","","",function(){
		//改变HTML内容
		HTMLChangeTxt();
		//存贮html表单数据，并给此学生办理入科
		$.ajax({
			type: 'POST',
			url :  ctx+'/teachweb/stuIndept.action',
			dataType: 'json',
			async: false,
			data:{
				content:$("#htmlDiv").html(),
				dataType:"html"
			},
			success:function(data) {
				if(data.success){
					myalert_success(data.data,"","",function(){
						parent.closeMyWindows();
					});
				}else{
					myalert_error(data.data,"","");
				}
			}
		});
	});
}



