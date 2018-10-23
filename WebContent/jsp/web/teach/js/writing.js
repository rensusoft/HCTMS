$(function(){
//	datepickerTime(weekly_begin_duration,weekly_end_duration);
//    datepickerTime(daily_duration);
//    datepickerTime(monthly_duration);
	$("#daily_content").attr("style","height:"+(pHeight-250)+"px");//使用pHeight设置textarea高度
	$("#weekly_content").attr("style","height:"+(pHeight-250)+"px");
	$("#monthly_content").attr("style","height:"+(pHeight-250)+"px");
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
	var id=""; 
	var type_id_str="";
	id = Request['id']; 
	type_id_str = Request['type_id_str'];
	//编辑时选择日志类型
	if(type_id_str == null || type_id_str == ""){//新增日志，初始化日报的Editor
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		ue = UE.getEditor('daily_content',{scaleEnabled:true});
	}else{
		if(type_id_str == "日报"){
			$("#leftDaily").addClass("select");
			$("#mainDaily").addClass("show_block");
			$("#leftWeekly").removeAttr("onclick");//去掉点击事件
			$("#leftMonthly").removeAttr("onclick");
			ue = UE.getEditor('daily_content',{scaleEnabled:true});
			if(id!="" && id!=null){
				$("#daily_flag").val(id);
				 $.ajax({
					  	type: 'POST',
						url : ctx + "/teachweb/updateDaily.action",
						data : {
							    id:id
							},
						dataType: 'json',
					  	success:function(data) {
						 //判断ueditor 编辑器是否创建成功
						 ue.addListener("ready", function () {
						    	// editor准备好之后才可以使用
						    	ue.setContent(data.content);
						    });
						 $("#daily_duration").val(data.duration);
					 }
				});
			}
		}
		if(type_id_str == "周报"){
			$("#leftWeekly").addClass("select");
			$("#mainWeekly").addClass("show_block");
			$("#leftDaily").removeClass("select");
			$("#mainDaily").removeClass("show_block");
			$("#leftDaily").removeAttr("onclick");//去掉点击事件
			$("#leftMonthly").removeAttr("onclick");
			ue = UE.getEditor('weekly_content',{scaleEnabled:true});
			if(id!="" && id!=null){
			$("#weekly_flag").val(id);
			 $.ajax({
				  	type: 'POST',
					url : ctx + "/teachweb/updateDaily.action",
					data : {
						    id:id
						},
					dataType: 'json',
				  	success:function(data) {
					 //判断ueditor 编辑器是否创建成功
					 ue.addListener("ready", function () {
					    	// editor准备好之后才可以使用
					    	ue.setContent(data.content);
					    });
					 var duration = data.duration.split("--");
					 var weekly_number = null;
					 var begin_duration = null;
					 var end_duration = null;
					 for(var i = 0; i < duration.length; i++){
						 weekly_number = duration[0];
						 begin_duration = duration[1];
						 end_duration = duration[2];
					 }
					 $("#weekly_number").val(weekly_number);
					 $("#weekly_begin_duration").val(begin_duration);
					 $("#weekly_end_duration").val(end_duration);
				 }
			});
		  }
		}
		if(type_id_str == "月报"){
			$("#leftMonthly").addClass("select");
			$("#mainMonthly").addClass("show_block");
			$("#leftDaily").removeClass("select");
			$("#mainDaily").removeClass("show_block");
			$("#leftDaily").removeAttr("onclick");//去掉点击事件
			$("#leftWeekly").removeAttr("onclick");
			ue = UE.getEditor('monthly_content',{scaleEnabled:true});
			if(id!="" && id!=null){
			$("#monthly_flag").val(id);
			 $.ajax({
				  	type: 'POST',
					url : ctx + "/teachweb/updateDaily.action",
					data : {
						    id:id
						},
					dataType: 'json',
				  	success:function(data) {
					 //判断ueditor 编辑器是否创建成功
					 ue.addListener("ready", function () {
					    	// editor准备好之后才可以使用
					    	ue.setContent(data.content);
					    });
					 $("#monthly_duration").val(data.duration);
				 }
				
			});
			}
		}
	}
	
});
//保存日报
function saveDaily(){
	var flag = $("#daily_flag").val();
	var type_id = $("#daily_type_id").val();
	var duration = $("#daily_duration").val();
	var content = UE.getEditor('daily_content').getContent();
	if(duration == "" || duration == null){
		myalert_error("时间不能为空!","30%","40%");
		return false;
	}
	if(content == "" || content == null){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}

	//进行form表单提交
	 var url=ctx + "/teachweb/saveDaily.action";
	 var postData = {
			 	flag:flag,
			 	duration:duration, 
			 	content:content,
			 	type_id:type_id
			};
	 doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
					 window.location.href= ctx + "/jsp/web/teach/daily.jsp";
			 });
		 }
	 });
}
//保存周报
function saveWeekly(){
	var flag = $("#weekly_flag").val();
	var type_id = $("#weekly_type_id").val();
	var weekly_number = $("#weekly_number").val();
	var weekly_begin_duration = $("#weekly_begin_duration").val();
	var weekly_end_duration = $("#weekly_end_duration").val();
	var duration = weekly_number + "--" + weekly_begin_duration + "--" + weekly_end_duration;
	var content = UE.getEditor('weekly_content').getContent();
	if(weekly_number == "" || weekly_number == null){
		myalert_error("周数不能为空!","30%","40%");
		return false;
	}
	if(weekly_begin_duration == "" || weekly_begin_duration == null){
		myalert_error("开始时间不能为空!","30%","40%");
		return false;
	}
	if(weekly_end_duration == "" || weekly_end_duration == null){
		myalert_error("结束时间不能为空!","30%","40%");
		return false;
	}
	var begin_duration_time = new Date(weekly_begin_duration.replace("-", "/").replace("-", "/"));
	var end_duration_time = new Date(weekly_end_duration.replace("-", "/").replace("-", "/"));
	if(begin_duration_time > end_duration_time){
		myalert_error("开始时间不能大于结束时间!","30%","40%");
		return false;
	}
	if(content == "" || content == null){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}

	//进行form表单提交
	 var url=ctx + "/teachweb/saveDaily.action";
	 var postData = {
			 	flag:flag,
			 	duration:duration, 
			 	content:content,
			 	type_id:type_id
			};
	 doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){//点击确认不关闭
					 window.location.href= ctx + "/jsp/web/teach/daily.jsp";
			 });
		 }
	 });
}
//保存月报
function saveMonthly(){
	var flag = $("#monthly_flag").val();
	var type_id = $("#monthly_type_id").val();
	var duration = $("#monthly_duration").val();
	var content = UE.getEditor('monthly_content').getContent();
	if(duration == "" || duration == null){
		myalert_error("时间不能为空!","30%","40%");
		return false;
	}
	if(content == "" || content == null){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}
	//进行form表单提交
	 var url=ctx + "/teachweb/saveDaily.action";
	 var postData = {
			 	flag:flag,
			 	duration:duration, 
			 	content:content,
			 	type_id:type_id
			};
	 doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){//点击确认不关闭
//				 if(flag == null || flag == ""){
//					 $("#duration").val("");
//					 //怎么清空内容？
////					 ue = UE.getEditor('content',{scaleEnabled:true});
////					//判断ueditor 编辑器是否创建成功
////					 ue.addListener("ready", function () {
////					    	// editor准备好之后才可以使用
////					    	ue.setContent("");
////					    });
////					 $("#content").val("");
//				 }else{
					 window.location.href= ctx + "/jsp/web/teach/daily.jsp";
//				 }
			 });
		 }
	 });
}
//关闭
function closeDaily(){
	var daily_flag = $("#daily_flag").val();
	var weekly_flag = $("#weekly_flag").val();
	var monthly_flag = $("#monthly_flag").val();
//	var flag = daily_flag!=null?daily_flag:weekly_flag!=null?weekly_flag:monthly_flag;//是""不是null
	var flag = daily_flag == ""?(weekly_flag == ""?monthly_flag:weekly_flag):daily_flag;
	if(flag == null || flag == ""){//新增时
		$("#leftDaily").removeClass("select");
		$("#mainDaily").removeClass("show_block");
		$("#leftWeekly").removeClass("select");
		$("#mainWeekly").removeClass("show_block");
		$("#leftMonthly").removeClass("select");
		$("#mainMonthly").removeClass("show_block");
	}else{
		window.location.href= ctx + "/jsp/web/teach/daily.jsp";
	}
}
//切换到日报
function changeDaily(){
	$("#leftWeekly").removeClass("select");
	$("#mainWeekly").removeClass("show_block");
	$("#leftMonthly").removeClass("select");
	$("#mainMonthly").removeClass("show_block");
	$("#leftDaily").addClass("select");
	$("#mainDaily").addClass("show_block");
}
//切换到周报
function changeWeekly(){
	$("#leftDaily").removeClass("select");
	$("#mainDaily").removeClass("show_block");
	$("#leftMonthly").removeClass("select");
	$("#mainMonthly").removeClass("show_block");
	$("#leftWeekly").addClass("select");
	$("#mainWeekly").addClass("show_block");
	ue = UE.getEditor('weekly_content',{scaleEnabled:true});
}
//切换到月报
function changeMonthly(){
	$("#leftDaily").removeClass("select");
	$("#mainDaily").removeClass("show_block");
	$("#leftWeekly").removeClass("select");
	$("#mainWeekly").removeClass("show_block");
	$("#leftMonthly").addClass("select");
	$("#mainMonthly").addClass("show_block");
	ue = UE.getEditor('monthly_content',{scaleEnabled:true});
}