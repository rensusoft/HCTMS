var flagOfPlace = '';
$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('content',{scaleEnabled:true});
//	var url=ctx+'/teachweb/pressSelect.action';
//	 var postData = {
//			};
//	 doAjax(url,postData,2,function(res){
//		 var str='<option value=""></option>';
//		 for(var i =0;i<res.data.length;i++){
//			str +='<option value="'+res.data[i].id+'">'+res.data[i].proc_name+'</option>';
//		 }
//		 $("#pressSelect").html(str);
//		 });
	$("#content").attr("style","height:"+(pHeight-280)+"px");
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
	flagOfPlace = Request['flagOfPlace'];
	
  $('#se').selectOrDie();
});

//发帖
function publishTheme(){
	var type_code = $("#type_code").val();
	var title = $("#title").val();
	var content = UE.getEditor('content').getContent();
	if(type_code == "" || type_code == null){
		myalert_error("类型不能为空!","30%","40%");
		return false;
	}
	if(title == "" || title == null){
		myalert_error("标题不能为空!","30%","40%");
		return false;
	}
	if(checkStr(title,2)==false){
		myalert_error("标题不能含有特殊字符!","30%","40%");
		return false;
	}

	if(content == "" || content == null){
		myalert_error("帖子内容不能为空!","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}
	var url=ctx+'/publicdataweb/publishTheme.action';
    var postData = {
    		type_code:type_code,
    		title:title,
    		content:content
    };
    doAjax(url,postData,2,function(res){
    	if(res.success == true){
			 myalert_success(res.data,"30%","40%",function(){//点击确认不关闭
				 if(flagOfPlace != '' && flagOfPlace == 0){
					 parent.closeMyWindows(0);
				 }else if(flagOfPlace != '' && flagOfPlace == 1){
					 parent.closeWindowsAndLocation();
				 }
			 });
		 }
    });
}