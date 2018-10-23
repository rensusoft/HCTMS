/**
 * 初始化操作。
 * 定义事件。
 * @date 2016年4月25日
 * @autor chen xiaoxiao
 */
var pHeight = $(window).height()-165;
	var pWidth = $(window).width();
$(document).ready(function() {
	//工号获取焦点
	$("#usercode").focus();
	
	$('#loginBtn').on('click', loginByUsercodeAndPass);
	//$('#dialogConfirm').on('click', loginByAuth);
//	$('#dialogCancel').on('click', function() {
//		$("#authDialog").dialog('close');
//	});
	
	//轮播图效果
	
});

//回车提交事件
$(document).keydown(function(event){
	if(event.keyCode==13){
		loginByUsercodeAndPass(); 
	} 
});

/**
 * 
 * 登录操作。
 * 用户名密码验证后先判断是否是多角色，
 * 若不是，则直接跳转首页；若是，返回多角色列表让用户选择。
 * @date 2016年4月25日
 * @autor chen xiaoxiao
 */
function loginByUsercodeAndPass() {
	var userCode = $('#usercode').val();
	var password = $('#password').val();
	if (!userCode) {
		myalert_error('请输入用户名！',"30%","40%");
		return;
	}
	if (!password) {
		myalert_error('请输入密码！',"30%","40%");
		return;
	}
	//密码加密
	password = hex_sha1(hex_md5(password));
	
	$.post(
		ctx + '/loginweb/loginbyusercodeandpass.action', 
		{userCode:userCode,password:password,pHeight:pHeight,pWidth:pWidth},
		function(res){
			if (res.success == false) {
				myalert_error(res.data,"30%","40%");
			}else{
				var loginType = res.loginType;
				if(loginType==1) {//只有一种角色，直接跳转主页面
					if(res.studentCode==1){
						 //判断是否做了入科宣教
						var url2=ctx+'/teachweb/getStuFormId.action';
						var postData={};
						doAjax(url2,postData,2,function(res2){
							if(res2.data!=null){
							//判断是评分表单还是普通表单
							var url1 = "";
							if(res2.data.type_id==1){
								url1 = ctx+"/jsp/web/score/indeptEduc_M.jsp?id="+res2.data.form_id;
							}else if(res2.data.type_id==2){
								url1 = ctx+"/jsp/web/score/indeptEduc_HTML.jsp?id="+res2.data.form_id;
							}
							content=mySelfpopdiv(2,"表单信息",'1200px',pHeight-70+'px','','','N',url1);
							}else{
								myalert_success("登录成功！","30%","40%");
								setTimeout(function(){
									if(res.student==3){
										window.location.href = ctx + "/jsp/web/index/syuIndex.jsp";
									}else{
									mainPage();
									}
								},500);
								
							}
					      });
					}else{
					myalert_success("登录成功！","30%","40%");
					setTimeout(function(){
						if(res.student==3){
							window.location.href = ctx + "/jsp/web/index/syuIndex.jsp";
						}else{
						mainPage();
						}
					},500);
					}
				}else if(loginType==2) {//多个角色，提示用户选择。
					var authInfo = {};
					var str = "";
					var authList = res.authList;
					for (var i = 0; i < authList.length; i++) {
						authInfo = authList[i];
						str += "<div class='roleSelDiv'><span style='display:none;'>"+authInfo.auth_id+"</span>"+authInfo.role_name+"</div>";
					}
					//弹出角色选择框
					mypopdiv(2,"选择角色",'300px','300px','30%','40%','N',
						ctx + '/loginweb/showSelRole.action?authList='+str);
				}else{
					myalert_error('非法状态！',"30%","40%");
				}
				}
		   }
	     );
}

/**
 * 根据用户权限进行登录
 * 
 * @date 2016年4月25日
 * @autor chen xiaoxiao
 */
function loginByAuth(authId) {
	var pHeight = $(window).height()-165;
	var pWidth = $(window).width();
	$.post(
			ctx + '/loginweb/loginbyauth.action', 
			{authId:authId,pHeight:pHeight,pWidth:pWidth}, 
			function(res) {
//				$("#authDialog").dialog('close');
				if (res.success == true) {
					mainPage();
				} else {
					myalert_error(res.data,"30%","40%");
				}
			}
	);
}

/**
 * 跳转到主页面
 * @date 2016年4月26日
 * @autor chen xiaoxiao
 */
function mainPage() {
	window.location.href = ctx + "/loginweb/mainpage.action";
}


function closeMyWindows(){
	layer.close(content);
	window.location.href = ctx + "/loginweb/mainpage.action";
}


function mySelfpopdiv(type,title,width,length,verticalNum,horizontalNum,shadeCloseFlag,content,endFun){
	if(type==null||type==""){
		type = 1;
	}
	if(title==null||title==""){
		title = false;
	}
	if(length==null||length==""){
		length = "400px";
	}
	if(width==null||width==""){
		width = "250px";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = (pHeight-180)/2+"px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = (pWidth-300)/2+"px";
	}
	if(shadeCloseFlag==null||shadeCloseFlag==""){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="Y"){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="N"){
		shadeCloseFlag = false;
	}
	if(content==null||content==""){
		content = "无内容...";
	}
	var myLayer = layer.open({
//		id: id,
		type: type,
		skin: 'layui-layer-lan',
		shade:[0.5,'#fff'],
		title:title,
		closeBtn:0,
		area: [width,length],
		//offset: [verticalNum,horizontalNum],
	    shadeClose: shadeCloseFlag, //点击遮罩关闭
	    content: content,
	    end:endFun
	});
	return myLayer;
}