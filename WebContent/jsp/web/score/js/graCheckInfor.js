$(function(){
	function GetRequest() { 
		var url = decodeURI(location.search); //获取url中"?"符后的字串 
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
	var  stuId = Request['stuId']; 
	var  stuName = Request['stuName'];
	$("#name").text(stuName);
	var  typeName = Request['typeName'];
	$("#stuType").text(typeName);
	var  lastDay = Request['lastDay'];
	$("#lastDay").text(lastDay);
	var  completion_rate = Request['completion_rate'];
	$("#completion_rate").text(completion_rate+'%');
	pageInto(stuId);
	
});


//初始化
function pageInto(stuId){
//	alert(stuId);
	var postData={
			stuId:stuId
	};
	//得到这个学生的相应的信息
	doAjax(ctx+"/scoreweb/getStuInfor.action",postData,2,function success(){
		
	});
	
	
	
}