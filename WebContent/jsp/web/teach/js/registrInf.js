var id;
var sad_id;
var action;
$(function () {
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
	sad_id = Request['sad_id']; 
	action = Request['action']; 
	if(action == "view"){
		$(".btns").attr("style","display:none;");
	}
	if(action == "edit" || action == "view"){
		$.ajax({
		  	type: 'POST',
			url : ctx + "/teachweb/updateStuActiveData.action",
			data : {
				    id:sad_id
				},
			dataType: 'json',
		  	success:function(res) {
			 $("#div_content").html(res.data.content);
			 if(action == "view"){
					$("textarea").attr("disabled",true);
			 }
		  	}
		});
	}
});

function save(){
	var a=$("#a").val();
	var b=$("#b").val();
	var c=$("#c").val();
	var d=$("#d").val();
	var url=ctx+'/teachweb/saveStuActiveData.action';
	var htmlText="<table> <tr><td>序号：</td><td><input style='width: 380px;' type='text' id='a' value='"+a+"' /></td></tr>" +
			              "<tr><td>出诊日期：</td><td><input style='width: 380px;' type='text' id='b' value='"+b+"' /></td></tr>" +
			              "<tr><td>病种：</td><td><input style='width: 380px;' type='text' id='c' value='"+c+"' /></td></tr>" +
			              "<tr><td>例数：</td><td><input style='width: 380px;' type='text' id='d' value='"+d+"' /></td></tr>" +
			              "</table>";
	var postData={
		content:htmlText,
		id:id,
		sad_id:sad_id,
		action:action
	};
   doAjax(url,postData,2,function(res){
	   parent.closeMyWindows(res);
   });
}




function reset(){
	$("#a").val("");
	$("#b").val("");
	$("#c").val("");
	$("#d").val("");
}