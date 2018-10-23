$(function() {
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
	var id=""; 
	id = Request['id']; 
	if(id!="" && id!=null){
	$("#iframeId").attr("src","rotateRule.jsp?id="+id);
	};
	var name="";
	name=Request['name'];
	if(name!=""&& id!=null){
		$("#biaoTi").html(name);
	}
});





