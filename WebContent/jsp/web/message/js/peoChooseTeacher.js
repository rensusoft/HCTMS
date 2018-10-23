var authArr=new Array();
$(function(){
	var url=ctx + '/messageweb/getTeachingType.action';
	var postData={};
	
	doAjax(url,postData,2,function(res){
		var a="<table><tr style='line-height: 25px;vertical-align:top;'><td style='width:75px;'><strong>所带学生:</strong></td><td>";
		for ( var i = 0; i < res.stuWith.length; i++) {
			a+="<input name='userName' type='checkbox' value=" + res.stuWith[i].stu_auth_id
			+ "><span>" + res.stuWith[i].name+"</span>";
		}
		a+="</td></tr><tr style='line-height: 25px;vertical-align:top;'><td  style='width:75px;'><strong>所有学生:</strong></td><td>";
		for ( var i = 0; i < res.allStu.length; i++) {
			a+="<input name='userName' type='checkbox' value=" + res.allStu[i].stu_auth_id
			+ "><span>" + res.allStu[i].name+"</span>";
		}
		a+="</td></tr><tr style='line-height: 25px;vertical-align:top;'><td  style='width:75px;'><strong>所有老师:</strong></td><td>";
		for ( var i = 0; i < res.teacherWith.length; i++) {
			a+="<input name='userName' type='checkbox' value=" + res.teacherWith[i].auth_id
			+ "><span>" + res.teacherWith[i].name+"</span>";
		}
		a+="</td></tr><tr style='line-height: 25px;vertical-align:top;'><td style='width:75px;'><strong>教学秘书:</strong></td><td>";
		for ( var i = 0; i < res.teachingSecretary.length; i++) {
			a+="<input name='userName' type='checkbox' value=" + res.teachingSecretary[i].auth_id
			+ "><span>" + res.teachingSecretary[i].name+"</span>";
		}
		a+="</td></tr><tr style='line-height: 25px;vertical-align:top;'><td style='width:75px;'><strong>&nbsp;&nbsp;&nbsp;科主任:</strong></td><td>";
		for ( var i = 0; i < res.teacherDirector.length; i++) {
			a+="<input name='userName' type='checkbox' value=" + res.teacherDirector[i].auth_id
			+ "><span>" + res.teacherDirector[i].name+"</span>";
		}
		$("#renWu").html(a);
	});
});


//添加人物
function addRen(){
	 $("input[name='userName']:checked").each(function(){
	 authArr.push({key:$(this).val(),value:$(this).next().html()});	
});
	 parent.addName(authArr,3);
}

//重置
function resetRen(){
	 $("input[name='userName']:checked").each(function(){
		 $(this).attr("checked",false);
	 });
}











