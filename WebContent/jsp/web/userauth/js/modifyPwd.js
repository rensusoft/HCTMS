$(function(){
	//刚点开页面 第一个输入框获取焦点
	$("#pwd").focus();

	//点击重置
	$("#btn_reset").click(function(){
		$('input:password').val('');
		$("#pwd").focus();
	});

	//点击提交
	$("#btn_submit").click(function(){
		var pwd = $("#pwd").val();
		var new_pwd1 = $("#new_pwd1").val();
		var new_pwd2 = $("#new_pwd2").val();
		if(pwd==''||new_pwd1==''||new_pwd2==''){
			//layer.tips('不能有空项！', '#btn_submit');
//			myMsg('不能有空项！','12%');
			return;
		}
		$.post(
			ctx + '/userauthweb/modifypwd.action', 
			{
				pwd:hex_sha1(hex_md5(pwd)),
				newPwd1:hex_sha1(hex_md5(new_pwd1)),
				newPwd2:hex_sha1(hex_md5(new_pwd2)),
				userId:$("#userId").val()
			}, 
			function (res) {
				if(res.success==true){
					$("#pwd").val("");
					$("#new_pwd1").val("");
					$("#new_pwd2").val("");
					//提示框
					myalert_success(res.data,'10%');
					parent.closeMyWindows();
				}else{
					myalert_error(res.data,'10%');
				}
			}
		
		);
	});
	
});