$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('reply_content',{scaleEnabled:true});
	$("#reply_content").attr("style","height:"+(pHeight-310)+"px");
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
	var cite_id = Request['cite_id'];
	if(cite_id != "" && cite_id != null){
		 $.ajax({
			  	type: 'POST',
				url : ctx + "/publicdataweb/selectForumSubById.action",
				data : {
					cite_id:cite_id
					},
				dataType: 'json',
			  	success:function(res) {
			  	  var cite_content = res.data.cite_content.replace(/<img.*?\/>/,"");
			  	  if(cite_content.length > 30){
	    			  cite_content = cite_content.substring(0,30);
	    			  cite_content += "...";
	    		  }
			  	  $("#cite_user_name").text(res.data.cite_user_name);
				  $("#cite_publish_time_str").text(res.data.cite_publish_time_str);
				  $("#cite_content").html(cite_content);
			 }
		});
		}
});

//发布回复
function publishReply(){
	var reply_content = UE.getEditor('reply_content').getContent(); 
	if(reply_content == "" || reply_content == null){
		myalert_error("帖子回复内容不能为空!","30%","40%");
		return false;
	}
	var id = '';
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
	var cite_id = Request['cite_id'];
	if(id != "" && id != null){
		var url=ctx+'/publicdataweb/publishReply.action';
	    var postData = {
	    		id:id,
	    		cite_id:cite_id,
	    		reply_content:reply_content
	    };
	    doAjax(url,postData,2,function(res){
	    	if(res.success == true){
				 myalert_success(res.data,"30%","40%",function(){//点击确认不关闭
					 parent.closeMyWindows();
				 });
			 }
	    });
	}
}