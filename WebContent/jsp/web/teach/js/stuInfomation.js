var stuAuthId="";
var stuInfo;
var _kind="";
$(function () {
	$(".btns").width($('.dtlBox').width()-$('.stuList').width());

	pageInit();
	//学生列表下li的点击事件
	$("#stuListUL").on("click","li",function(){ 
		$(this).addClass("badgec").siblings().removeClass('badgec');
		var usercode = $(this).children("[class='_userCode']").text();
		var _stuauthid = $(this).children("[class='_stuauthid']").text();
		_kind = $(this).children("[class='_kind']").text();
		stuAuthId=_stuauthid;
		var url=ctx+'/teachweb/getStuMessageById.action';
		var postData={userCode:usercode,
				userCode:usercode,
				stu_auth_id:stuAuthId,
				_kind:_kind
		};
	
		
		doAjax(url,postData,2,function(res){
			stuInfo = res.stuInfo;
			$("#image").attr('src',ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+res.stuInfo.stu_num+'.jpg'); 
			$("#stu_name").val(res.stuInfo.stu_name);
			$("#stu_sex").val(res.stuInfo.stu_sex);
			$("#stu_age").val(res.stuInfo.stu_age);
			$("#stu_birthday").val(res.stuInfo.stu_birthday);
			$("#stu_phone").val(res.stuInfo.stu_phone);
			$("#stu_school_name").val(res.stuInfo.stu_school_name);
			$("#stu_num").val(res.stuInfo.stu_num);
			$("#stu_edu_level").val(res.stuInfo.stu_edu_level);
			$("#stu_major_name").val(res.stuInfo.stu_major_name);
			$("#stu_major_direction").val(res.stuInfo.stu_major_direction);
			$("#stuTypeName").val(res.stuInfo.stu_type_name);
			$("#stuClass").val(res.stuInfo.stu_class);
			var select="";
			for ( var i = 0; i < res.teacherList.length; i++) {
				select+=" <option value="+res.teacherList[i].teacher_auth_id+">"+res.teacherList[i].user_name+"</option>";
			}
			$("#teacherId").html(select);
			$("#teacherId")[0].selectedIndex = -1;
			$("#orgaName").val(res.orga);
			$("#startTime").val(res.train.train_start_str);
			$("#endTime").val(res.train.train_end_str);
		});
	});
	
});


function pageInit(){
	// $(".btns").width($(".stuInfor").width());
	    $("#stuListUL").html("");
		var url=ctx+'/teachweb/getTrainStu.action';
		doAjax(url,"",2,function(res){
			var str = "";
			for ( var i = 0; i < res.data.length; i++) {
				str+="<li><a class='stuMessage'>"+res.data[i].name+"</a>" +
				"<span style='display:none;' class='_userCode'>"+res.data[i].userCode+"</span>" +
				"<span style='display:none;' class='_stuauthid'>"+res.data[i].stu_auth_id+"</span>" +
				"<span style='display:none;' class='_kind'>1</span>" +
				"</li>";
		}
			$("#stuListUL").html(str);
			 
	});

}


function changStatus(){
	if($("#teacherId")[0].selectedIndex == -1){
		myMsg("请选择带教老师！",'50%','50%');
		return false;
	}
    var teacherId=$("#teacherId").val();
	myConfirm("确认入科？","","",function(){
		$.ajax({
			type: 'POST',
			url :  ctx+'/teachweb/intoOrga.action',
			dataType: 'json',
			async: false,
			data:{
//				content:$("#htmlDiv").html(),
				stuAuthId:stuAuthId,
				teacherId:teacherId,
				dataType:"html",
				_kind:_kind
			},
			success:function(data) {
				if(data.success){
					myalert_success("入科成功！","","",function(){
						resetForm(_kind);
					});
				}else{
					myalert_error(data.data,"","");
				}
			}
		});
	});

	
	
	
	
	
	
//	var url=ctx+'/teachweb/getStuFormId.action';
//	var postData={
//		stu_auth_id:stuAuthId		
//	};
//	
//	doAjax(url,postData,2,function(res){
//		//判断是评分表单还是普通表单
//		var url = "";
//		if(res.data.type_id==1){
//			url = ctx+"/jsp/web/score/indeptEduc_M.jsp?id="+res.data.form_id+"&_kind="+_kind;
//		}else if(res.data.type_id==2){
//			url = ctx+"/jsp/web/score/indeptEduc_HTML.jsp?id="+res.data.form_id+"&stuAuthId="+stuAuthId+"&teacherId="+$("#teacherId").val()+"&_kind="+_kind;
//		}
//		mypopdiv(2,"表单信息",'1200px',(pHeight-120)+'px','','','N',url);
//	});
}

function resetForm(_kind){
	//刷新左侧学生列表数据
	if(_kind==1){
		pageInit();	
	}else if(_kind==2){
		getAdvanceStuInfo();
	}
	
	//清空所有的输入框的值
	$('input[type=text]').each(function(){
		$(this).val("");
	});
	$("#teacherId").find("option").remove();
	//关闭弹出层
	layer.closeAll();
}
//改变下拉框获取提前入科和正常入科的学生列表
function fromStudentList(){
	if($("#sel_stuList").val() == 1){
		pageInit();
		clear();
	}else if($("#sel_stuList").val() == 2){
		getAdvanceStuInfo();
		clear();
	}
}
//获取提前入科的学生列表
function getAdvanceStuInfo() {
	$.ajax({
		async:false,
	    type:'POST',
	    url:ctx+"/teachweb/getAdvanceStuInfo.action",
	    data:{},
	    dataType:'json',
	    success:function(res){
	    	$("#stuListUL").html('');
	    	var str="";
	    	for(var i=0;i<res.data.length;i++){
	    		str+="<li><a class='stuMessage' title='当前科室为："+res.data[i].orga_name+"&#10;距离进入本科室的时间为："+res.data[i].days+"天'>"+res.data[i].name+"</a>" +  
				"<span style='display:none;' class='_userCode'>"+res.data[i].userCode+"</span>" +
				"<span style='display:none;' class='_stuauthid'>"+res.data[i].stu_auth_id+"</span>" +
				"<span style='display:none;' class='_kind'>2</span>" +
				"</li>";
	    	}
	    	$("#stuListUL").html(str);
	    }
	});
}

//清空
function clear(){
	$('input[type=text]').each(function(){
		$(this).val("");
	});
	$("#teacherId").find("option").remove();
}








