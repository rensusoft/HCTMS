$(function(){
	
	
	//选择一个角色后调用父页面函数
	$(".roleSelDiv").click(function(){
		var authId = $(this).children("span").text();
		parent.loginByAuth(authId);
	});
	
});
