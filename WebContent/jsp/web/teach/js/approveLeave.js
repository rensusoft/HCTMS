var days = 0;
$(function(){
	var id=""; 
	var role="";
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
	id = Request['id']; 
	role = Request['role'];
	flagOfExamineType = Request['flagOfExamineType'];
	if(flagOfExamineType == -1){
		$(".layui-layer-btn0").html("销假-通过");
		$(".layui-layer-btn1").html("销假-不通过");
	}
	if(id!="" && id!=null){
		var url=ctx+'/teachweb/selectReviewLeaveById.action';
	    var postData = {
	    		id:id,
	    		role:role
	    };
	    doAjax(url,postData,2,function(res){
	    	days = res.data.vacate_date_num;
	    	var vacate_date_num = 0;
	    	if(res.data.vacate_date_num == -10 || res.data.vacate_date_num == -20){
	    		vacate_date_num = 0.5;
	    	}else{
	    		vacate_date_num = res.data.vacate_date_num;
	    	}
		  		var str = '<table style="margin-top:50px">'+
		  		'<tr>'+
		  		'<td><b style="margin-left:10px">学生姓名：</b><span id="stu_name">'+res.data.stu_name+'</span></td>'+
		  		'<td colspan="2"><b style="margin-left:90px">科室：</b><span>'+res.data.orga_name+'</span></td>'+
		  		'<td><b style="margin-left:90px">请假类型：</b><span>'+res.data.vacate_type_name+'</span></td>'+
		  		'</tr>'+
		  		'<tr><td colspan="3"><br/></td></tr>'+
		  		'<tr>'+
		  		'<td><b style="margin-left:10px">请假开始时间：</b><span id="state_time">'+res.data.start_time_str+'</span></td>'+
		  		'<td colspan="2"><b style="margin-left:90px">请假结束时间：</b><span>'+res.data.end_time_str+'</span></td>'+
		  		'<td><b style="margin-left:90px">请假天数：</b><span id="days">'+vacate_date_num+'</span></td>'+
		  		'</tr>'+
		  		'</table>'+
		  		'<hr/> <input type="hidden" id="id" value="'+res.data.id+'"/><b style="margin-top:50px;margin-left:5px">请假原因：</b>'+
		  		'<div style="margin-left:10px;margin-top:20px;height:300px;overflow:auto;">'+res.data.content+'</div>';
		  		 $("#leave_info").html(str);
		  	});
	}
});

//关闭弹出层
function closeMyWindows(state){
	parent.closeMyWindows(state);
}

//审核通过
function go(){
	var role="";
	var flagOfExamineType="";
	var stu_auth_id="";
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
	role = Request['role'];
	flagOfExamineType = Request['flagOfExamineType'];
	stu_auth_id = Request['stu_auth_id'];
	var id = $("#id").val();
	var state_time = $("#state_time").text();
	var stu_name = $("#stu_name").text();
//	relesepup = mypopdiv(2,"审批通过（审批意见：）",'500px',(pHeight-200)+'px','50px',(pWidth-1000)/2,'Y',ctx + '/jsp/web/teach/approvalOpinion.jsp?flag=yes&id=' + id + '&role=' + role + '&stu_name=' + stu_name + '&state_time=' + state_time + '&days=' + days);
	var flag='yes';
	var url = '';
	var postData = {};
	if(flagOfExamineType == 1){
		url=ctx+'/teachweb/submitReviewLeaveRewrite.action';
		postData = {
				id:id,
				flag:flag,
				role:role,
				state_time:state_time,
				days:days,
				stu_name:stu_name
		};
	}else if(flagOfExamineType == -1){
		url=ctx+'/teachweb/checkForReportBack.action';
		postData = {
				id:id,
				vacate_status:5,//  销假成功状态
				stu_auth_id:stu_auth_id
		}
	}
	    doAjax(url,postData,2,function(res){
	    	if(res.success == true){
	    		myalert_success(res.data,"30%","40%",function(){
	    			parent.closeMyWindows();
			 });
	    	}
	    });
}

//审核不通过
function notGo(){
	var role="";
	var flagOfExamineType="";
	var stu_auth_id="";
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
	role = Request['role'];
	flagOfExamineType = Request['flagOfExamineType'];
	stu_auth_id = Request['stu_auth_id'];
	var id = $("#id").val();
	var state_time = $("#state_time").text();
	var stu_name = $("#stu_name").text();
	relesepup = mypopdiv(2,"审批不通过（审批意见：）",'500px',(pHeight-200)+'px','50px',(pWidth-1000)/2,'Y',ctx + '/jsp/web/teach/approvalOpinion.jsp?flag=no&id=' + id + '&role=' + role + '&stu_name=' + stu_name +'&state_time=' + state_time + '&days=' + days + '&flagOfExamineType=' + flagOfExamineType + '&stu_auth_id=' + stu_auth_id);
}