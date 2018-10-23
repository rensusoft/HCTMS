$(function(){
	$("#review_content").attr("style","height:"+(pHeight-350)+"px;width:100%;margin:0 auto");//使用pHeight设置textarea高度
});	
function submit(){
	var content = $("#review_content").val();
	if(content == "" || content == null){
		myalert_error("审批意见不能为空!","30%","40%");
		return false;
	}
	var id="";
	var flag=""; 
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
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	id = Request['id'];
	flag = Request['flag']; 
	role = Request['role'];
	flagOfExamineType = Request['flagOfExamineType'];
	stu_auth_id = Request['stu_auth_id'];
	state_time = Request['state_time'];
	days = Request['days'];
	stu_name = Request['stu_name'];
	var url = '';
	var postData = {};
	if(flagOfExamineType == 1){
		url=ctx+'/teachweb/submitReviewLeaveRewrite.action';
		postData = {
	    		id:id,
	    		flag:flag,
	    		proc_content:content,
	    		role:role,
	    		state_time:state_time,
	    		days:days,
	    		stu_name:stu_name
		};
	}else if(flagOfExamineType == -1){
		url=ctx+'/teachweb/checkForReportBack.action';
		postData = {
				id:id,
				vacate_status:6,//  销假驳回状态
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