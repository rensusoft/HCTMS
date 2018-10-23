var id="";
$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
//	ue = UE.getEditor('content',{scaleEnabled:true});	
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
	if(id!="" && id!=null){
	$("#id").val(id);
	if(pHeight< 768){
		$(".newsContent").height(280);
	}else{
		$(".newsContent").height(450);
	}
	var data={
			 id:id
	};
	doAjax(ctx + "/messageweb/getMessagebyId.action",data,2,function(res){
		$("#title").html(res.data.title);
		$("#sendName").html(res.data.sendName);
		$("#sendTimeStr").html(res.data.sendTimeStr);
		
		$("#text").append(res.data.content);
//		ue.setContent(res.data.content);
	});
	}
});

