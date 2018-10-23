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
	var url=ctx+'/teachweb/saveStuActiveData.action';
	var str="";
	if(c=="疑难病历讨论"){
		str="<select id='c'><option value='疑难病历讨论' selected = 'selected'>疑难病历讨论</option><option value='教学查房' >教学查房</option>"+
        "<option value='小讲课' >小讲课</option><option value='病理讨论' >疑难病历讨论</option> </select>";
	}else if(c=="教学查房"){
		str="<select id='c'><option value='疑难病历讨论' >疑难病历讨论</option><option value='教学查房' selected = 'selected'>教学查房</option>"+
        "<option value='小讲课' >小讲课</option><option value='病理讨论' >病理讨论</option> </select>";
	}else if(c=="病理讨论"){
		str="<select id='c'><option value='疑难病历讨论' >疑难病历讨论</option><option value='教学查房' >教学查房</option>"+
        "<option value='小讲课' >小讲课</option><option value='病理讨论' selected = 'selected'>病理讨论</option> </select>";
	}
	
	var htmlText= "<table><tr><td>时间：</td><td><input style='width: 380px;' type='text' id='a' value='"+a+"' /></td></tr>" +
			"<tr><td>地点：</td><td><input style='width: 380px;' type='text' id='b' value='"+b+"' /></td></tr>" +
			"<tr><td>活动名称：</td><td>"+str+"</td></tr>" +     
			"<tr><td>内容：</td><td><textarea name='' id='d' cols='50' rows='3'>"+d+"</textarea></td></tr>" +
			"<tr><td>主讲人：</td><td><input style='width: 380px;' type='text' id='e' value='"+e+"' /></td></tr>"+
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
	$("#c").val("疑难病历讨论");
	$("#d").val("");
	$("#e").val("");	
}