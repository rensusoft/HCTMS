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
					$("input[type=text]").attr("disabled",true);
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
	var e=$("#e").val();
	var f=$("#f").val();
	var g=$("#g").val();
	var url=ctx+'/teachweb/saveStuActiveData.action';
	var htmlText="<table> <tr><td>病人姓名：</td><td><input style='width: 380px;' type='text' id='a' value='"+a+"' /></td></tr>" +
			              "<tr><td>病历号：</td><td><input style='width: 380px;' type='text' id='b' value='"+b+"' /></td></tr>" +
			              "<tr><td>操作时间：</td><td><input style='width: 380px;' type='text' id='c' value='"+c+"' /></td></tr>" +
			              "<tr><td>主要诊断：</td><td> <textarea name='' id='d' cols='50' rows='3'>"+d+"</textarea></td></tr>" +
			              "<tr><td>次要诊断：</td><td> <textarea name='' id='e' cols='50' rows='3'>"+e+"</textarea></td></tr>" +
			              "<tr><td>治疗方案：</td><td> <textarea name='' id='f' cols='50' rows='3'>"+f+"</textarea></td></tr>" +
			              "<tr><td>技能操作：</td><td> <textarea name='' id='g' cols='50' rows='3'>"+g+"</textarea></td></tr>" +
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
	$("#e").val("");
	$("#f").val("");
	$("#g").val("");
}