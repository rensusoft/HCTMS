var flagOfHalfDayLeave = 0;
$(function(){
	//请假天数可以是半天的开关是否开启
	$.ajax({
	    type: 'POST',
	    url : ctx + "/teachweb/getAbilityOfHalfDayLeave.action",
		data : {
			},
		dataType: 'json',
		async: false,
	  	success:function(res) {
	  		flagOfHalfDayLeave = res.data;
	  	}
	});
	//审批人
	$.ajax({
		type: 'POST',
		url : ctx + "/teachweb/getVacateApprover.action",
		data : {
			proc_num:1
		},
		dataType: 'json',
		async: false,
		success:function(data) {
			if(data.name==null||data.name==""){
				myalert_error("暂无审批老师，请勿申请!");
			}else{
				$("#name").val(data.name);
				$("#auth_ids").val(data.auth_ids);
			}
		}
	});
	$("#content").attr("style","height:"+(pHeight-255)+"px");
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('content',{scaleEnabled:true});	
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
	var id=""; 
	id = Request['id']; 
	//ID不为空则是修改申请
	if(id!="" && id!=null){
	$("#id").val(id);
	 $.ajax({
		  	type: 'POST',
			url : ctx + "/teachweb/selectVacateById.action",
			data : {
				    id:id
				},
			dataType: 'json',
			  async: false,
		  	success:function(data) {
			 //判断ueditor 编辑器是否创建成功
			 ue.addListener("ready", function () {
			    	// editor准备好之后才可以使用
			    	ue.setContent(data.data.content);
			    });
			  $("#start_time").val(data.data.start_time_str);
			  if(data.data.vacate_date_num == -10 || data.data.vacate_date_num == -20){
				  $("#day").html(0.5);
			  }else{
				  $("#day").html(data.data.vacate_date_num);
			  }
			  if(flagOfHalfDayLeave == 1){
				  if(data.data.vacate_date_num == 1 || data.data.vacate_date_num == -10 || data.data.vacate_date_num == -20){
					  $("#select_half_day").css("display", "inline-block");
					  $("#select_half_day").val(data.data.vacate_date_num);
				  }
			  }
			  $("#end_time").val(data.data.end_time_str);
			  if(data.data.vacate_type_code=="1"){
				   $("#code_type").val("1");
			   }else if(data.data.vacate_type_code=="2"){
				   $("#code_type").val("2");
			   }else if(data.data.vacate_type_code=="3"){
				   $("#code_type").val("3");
			   }
		 }
	});
	}
	var i=0;
	$('#end_time').focus(function(){ 
		if($("#start_time").val()==""||$("#start_time").val()==null){
				myalert_error("开始时间不能为空!","30%","40%");
			$("#end_time").val("");
			$("#start_time").focus();
			return false;
		}
		 var sdate = new Date($("#end_time").val().replace(/-/g, "/"));  
		 var now = new Date($("#start_time").val().replace(/-/g, "/"));  
	     var days = sdate.getTime() - now.getTime();  
	     var day = parseInt(days / (1000 * 60 * 60 * 24))+1;
	     if(day<=0&&i==0){
	  			myalert_error("结束时间必须大于开始时间!","30%","40%");
	  			 $("#day").html("");
	  			i++;
	  			return false; 
	     }
	     if(day>0){
	    	 $("#day").html(day);
	    	 if(flagOfHalfDayLeave == 1){
	    		 if(day == 1){
	    			 $("#select_half_day").css("display", "inline-block");
	    			 $("#select_half_day").val(1);
	    		 }else{
	    			 $("#select_half_day").css("display", "none");
	    		 }
	    	 }
	     }
	     i=0;
	});
});

/***
 * 提交数据
 */
function submitData(){
	if($("#name").val()==""||$("#name").val()==null){
		myalert_error("暂无审批老师，无法申请!");
		return false;
	}
	if($("#start_time").val()==""||$("#start_time").val()==null){
		myalert_error("开始时间不能为空!","30%","40%");
		return false;
	}
	if($("#end_time").val()==""||$("#end_time").val()==null){
		myalert_error("结束时间不能为空!","30%","40%");
		return false;
	}
	if($("#end_time").val()<$("#start_time").val()){
		myalert_error("结束时间必须大于开始时间!","30%","40%");
		return false;
	}
	if(flagOfHalfDayLeave == -1){
		if($("#day").html() == "0.5"){
			myalert_error("请假天数不能是半天!","30%","40%");
			return false;
		}
	}
	 var sdate = new Date($("#end_time").val().replace(/-/g, "/"));  
	 var now = new Date($("#start_time").val().replace(/-/g, "/"));  
     var days = sdate.getTime() - now.getTime();  
     var day = parseInt(days / (1000 * 60 * 60 * 24))+1; 
     if(flagOfHalfDayLeave == 1){
    	 if(day == 1){
    		 var half_day = $("#select_half_day").val();
    		 if(half_day != 1){
    			 day = half_day;
    		 }
    	 }
     }
//     if(5<parseInt(day)){
//    	 myalert_error("5天以上请通过学校相关部门申请!","30%","40%");
//    	 return;
//     }
	if(UE.getEditor('content').getContent()==""||UE.getEditor('content').getContent()==null){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	//进行内容提交
	var options=$("#code_type option:selected");  //获取选中的项 
	 var url=ctx + "/teachweb/addVacateInfo.action";
	 var postData = {
			    id:$("#id").val(), 
				vacate_type_code:options.val(),   //拿到选中项的值 
				vacate_type_name:options.text(),   //拿到选中项的文本 
				start_time:$("#start_time").val(),
				end_time:$("#end_time").val(),
				state:"Y",
				day:day,
				auth_ids:$("#auth_ids").val(),
				content: UE.getEditor('content').getContent()
			};
	 doAjax(url,postData,2,function(){
		 parent.orgaInit();
		 myalert_success("操作成功！","30%","40%",function(index){
			 parent.closeMyWindows();
				});	
		 });
}
//半天全天下拉框选中变化
function changeHalfDay(){
	var half_day = $("#select_half_day").val();
	if(half_day == -10 || half_day == -20){
		$("#day").html("0.5");
	}else{
		$("#day").html("1");
	}
	
}

