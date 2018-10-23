$(function(){
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
	if(id!="" && id!=null){
	$("#id").val(id);
	 $.ajax({
		  	type: 'POST',
			url : ctx + "/publicdataweb/updatePageJump.action",
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
			  if(data.type_code=="1"){
				   $("#type_code").val("1");
			   }else if(data.type_code=="2"){
				   $("#type_code").val("2");
			   }else if(data.type_code=="3"){
				   $("#type_code").val("3");
			   }
			  $("#title").val(data.title);
		 }
		
	});
	}
});

/***
 * 提交数据
 */
function submitData(){
	if($("#title").val()==""||$("#title").val()==null){
		myalert_error("标题不能为空!","30%","40%");
		return false;
	}
	if(UE.getEditor('content').getContent()==""||UE.getEditor('content').getContent()==null){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}
	//进行form表单提交
	 var url=ctx + "/publicdataweb/insterPublicData.action";
	 var postData = {
			    id:$("#id").val(), 
				title:$("#title").val(),
				type_code:$("#type_code").val(),
				content: UE.getEditor('content').getContent()
			};
	 doAjax(url,postData,2,function(){
		 myalert_success("操作成功！","30%","40%",function(index){
			    parent.orgaInit();
				parent.closeMyWindows();
				}	
			);
		 });
}


