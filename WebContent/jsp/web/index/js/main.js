var content="";
$(function() {
	
	//根据角色切换主界面。
	switchRole();
	
	$("#logout").click(function(){
		logout();
	});
	
	/**
	 * 点击 消息中心 去掉上面数字小红点提示
	 */
	$("#msgCountDiv").click(function(){
		$(".numAll").html("");
		$(".numAll").removeClass("numAll");
		$(this).children("span").html("");
		$(this).children("span").removeClass("num");
	});
	
	
});

/**
 * 根据角色切换主界面。
 * 
 * @date 2016年12月23日
 * @autor yuanb
 */
function switchRole(){
	var roleCode = $('#roleCode').val();
	var url = "";
	if (roleCode == "R_STU") {
		url = "/jsp/web/index/stuIndex.jsp";
	} else if (roleCode == "R_TEA") {//教师
		url = "/jsp/web/index/teaIndex.jsp";
	} else if (roleCode == "R_MASTER") {//班主任
	} else if (roleCode == "R_INSTRUCTOR") {//辅导员
		
	} else if (roleCode == "R_KS_DIRECTOR") {//教研室主任
		url = "/jsp/web/index/directorIndex.jsp";
	} else if (roleCode == "R_KS_ADMIN") {//学院秘书
		url = "/jsp/web/index/secretaryIndex.jsp";
	} else if (roleCode == "R_EDUCATIONAL") {//教务处
		
	} else if (roleCode == "R_SYSADMIN") {//系统管理员
		
	} else if (roleCode == "R_KJK_ADMIN") {//科教科
		url = "/jsp/web/index/teaResearchIndex.jsp";
	}
	if (!url) {
		url = "/jsp/web/index/commonIndex.jsp";
	}
	menuClick(url);
}

/**
 * 二级菜单点击操作。
 * 点击后替换iframe的src属性。
 * @param url
 * @date 2016年12月23日
 * @autor yuanb
 */
function menuClick(div_url) {
	if (div_url) {
		$('#mainContainner').attr('src', ctx + div_url);
	}
	else {
		alert('菜单未正确配置地址，请先配置菜单地址！');
	}
}


/**
 * 首页退出登录。
 * 
 * @date 2017年1月5日
 * @autor 
 */
function logout() {
	myConfirm("您确定要退出系统吗？",'','',
		function(){
			$.post(ctx + '/loginweb/logout.action', {}, function(res) {
				if (res.success == true) {
					window.location.href = ctx;
				}
			});
		},function(){
			return;
		}
	);
}

function information(){
	var roleCode = $('#roleCode').val();
	if (roleCode == "R_STU") {
		relesepup = mypopdiv(2,"个人信息",'1100px',(pHeight-200)+'px','','','N',ctx + '/jsp/web/userauth/stuInformation.jsp');
	}else{
		relesepup = mypopdiv(2,"个人信息",'1000px',(pHeight-200)+'px','','','N',ctx + '/jsp/web/userauth/teaInformation.jsp');
	}
}



function updatePassword(){
	content=mypopdiv(2,"密码修改","500px","220px","30%","30%","N",
			ctx + '/userauthweb/getPassword.action?userId='+ $("#userId").val());
}

/***
 * 点击操作手册，进行下载
 * yuanb
 * 2017-3-10
 */
function operationManual(){
	location.href=encodeURI(ctx +'/operationManual/医院临床教学管理系统--操作手册.docx');
}

function closeMyWindows(){
	layer.close(content);
}

function closeMyAll(){
	layer.close(relesepup);
}


//点击首页刷新
function refresh(){
	window.frames["iframeName"].getAll(); 
}

