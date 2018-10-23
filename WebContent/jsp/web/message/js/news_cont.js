var senderAuthIds = new Array();  //接收人的的id
var leixing=0;
var roleId=0;
$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('content',{scaleEnabled:true});	
	//跳转页面拿值
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
	roleId = Request['id']; 
	if(pHeight<500){
		$("#content").height(220);
	}else{
		$("#content").height(280);
	}
});

function sendMsg(){
	if(UE.getEditor('content').getContent()=="" || $("#title").val() == ""){
		myalert_error("标题或消息内容不能为空！","20%","18%");
	}
	if($("#title").val().length>20 && UE.getEditor('content').getContent()!=""){
		myalert_error("标题不能超过20个字！","20%","18%");
	}
	var content= UE.getEditor('content').getContent();
	if($("#title").val().length<21 && UE.getEditor('content').getContent()!=""){		
	var data = {
			  content:content,
			  title:$("#title").val(),
			  sender_auth_id:JSON.stringify(senderAuthIds),
			  typeId:1
	    };
	if(leixing==3){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/messageweb/sendMsg.action',
	  	dataType: 'json',
		traditional: true,
	  	data:data,
  	 	success:function(res) {
  	 		parent.closeMyWindows(res);
  	 	}
	});
	}else if(leixing==1){
		$.ajax({
	  		type: 'POST',
		  	url : ctx+'/messageweb/sendMsgByRoleId.action',
		  	dataType: 'json',
			traditional: true,
		  	data:data,
	  	 	success:function(res) {
	  	 		parent.closeMyWindows(res);
	  	 	}
		});
	}else if(leixing==2){
		$.ajax({
	  		type: 'POST',
		  	url : ctx+'/messageweb/sendMsgByOrga.action',
		  	dataType: 'json',
			traditional: true,
		  	data:data,
	  	 	success:function(res) {
	  	 		parent.closeMyWindows(res);
	  	 	}
		});
	}else if(leixing==4){ //根据医院类型发送消息
		$.ajax({
	  		type: 'POST',
		  	url : ctx+'/messageweb/sendMsgByHopitalType.action',
		  	dataType: 'json',
			traditional: true,
		  	data:data,
	  	 	success:function(res) {
	  	 		parent.closeMyWindows(res);
	  	 	}
		});
	}
	}
}



function add() {
parent.addPeo(senderAuthIds);
} 

function addNamesInput(name,authIds,leixingNew){
	$("#name").val(name);
	senderAuthIds=authIds;
    leixing=leixingNew;
}
