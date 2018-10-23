var content="";
var importpup="";
$(function() {
	
	if($("#a").val()==1){
	parent.closeMyWindonws();
	}
	pageInit();
	queryByOrga();
	$("#qBtn").trigger("click");
	
	//右击菜单触发事件
	$('#userInfoGrid').contextMenu('myMenu', {
		bindings: 
        {
          'userInfoUpdate': function(){
        	  var id=jQuery("#userInfoGrid").jqGrid('getGridParam','selrow');
        		if (!id) {
        			//alert("请先选择要修改的用户！");
        			myMsg("请先选择要修改的用户！");
        			return ;
        		}
        		var user = $("#userInfoGrid").jqGrid('getRowData', id);
        		if (user.state == 'X') {
        			myMsg('该用户已经被删除，不可修改！');
        			return ;
        		}
        		
        		content=mypopdiv(2,"添加页面",'1000px',(pHeight-100)+'px',"50px",(pWidth-1000)/2+"px","N",
        				ctx + '/userauthweb/usereditinit.action?action=edit&userId='+ user.user_id);
        		reloadGrid();
          },
    	  'userInfoDelete': function(){
    		  var id=jQuery("#userInfoGrid").jqGrid('getGridParam','selrow');
    			if (!id) {
    				myMsg("请先选择要删除的用户！");
    				return ;
    			}
    			var user = $("#userInfoGrid").jqGrid('getRowData', id);
    			if (user.state == 'X') {
    				myMsg('该用户已经被删除，不可重复删除！');
    				return ;
    			}
    			myConfirm("您确定要删除用户[" + user.user_name + "]吗？",(pHeight-200)/2+'px',(pWidth-320)/2+"px",function(){
    				$.post(
						ctx + '/userauthweb/userauth/userInfoDelete.action', 
						{userId: user.user_id, userName: user.user_name}, 
						function(res) {
							//删除成功，刷新列表
								myalert_success(res.data,"","",function(){
									layer.closeAll();
									reloadGrid();
									informationInit();
								});							
							}
					);
    			});
//    			if (res == true) {
//    				$.post(
//    					ctx + '/userauthweb/userauth/userInfoDelete.action', 
//    					{userId: user.user_id, userName: user.user_name}, 
//    					function(res) {
//    						alert(res.data);
//    						//删除成功，刷新列表
//    						if (res.success == true) {
//    							reloadGrid();
//    							informationInit();
//    						}
//    					}
//    				);
//    			}
    	  }
        }
	});
	
});

///***
// * 点击上传头像
// * @param stuUserId
// */
//function uploadHeadImgs(){
//	mypopdiv(2,"导入头像","1000px",(pHeight-150)+"px","","","N",'uploadHeadImgs.jsp');	
//}

/***
 * 每个人员后面点击绑定或未绑定
 * @param stuUserId
 */
//function bindHeadImg(stuUserId){
//	alert(stuUserId);
//}

//模糊查
function queryUser() {
	var postData = {
		userCode: $('#userCode').val(), 
		orga_id:$('#orga').val(),
		role_id:$('#role').val()
	};
	$("#userInfoGrid").jqGrid('setGridParam',{
        datatype:'json',
        postData: postData, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}


//刷新 UserInfo表
function reloadGrid() {
	$("#userInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        page: 1
    }).trigger("reloadGrid"); //重新载入
};


/**
 * 初始化
 */
function pageInit(){
	userInfoInit();
	informationInit();
	$("#userInfoGrid").jqGrid().navGrid("#userInfoToolbar", {edit : false,add : false,del : false,search : false,refresh : true})
	.navButtonAdd("#userInfoToolbar", {caption: "新增用户", buttonicon:"ui-icon-plus", onClickButton: userAdd, position: "first"});
	$("#informationGrid").jqGrid().navGrid("#informationToolbar", {edit : false,add : false,del : false,search : false,refresh : false})
	.navButtonAdd("#informationToolbar", {caption: "删除权限", buttonicon:"ui-icon-trash", onClickButton: authorityDelete, position: "first"})
	.navButtonAdd("#informationToolbar", {caption: "修改权限", buttonicon:"ui-icon-pencil", onClickButton: authorityUpdate, position: "first"})
	.navButtonAdd("#informationToolbar", {caption: "新增权限", buttonicon:"ui-icon-plus", onClickButton: authorityAdd, position: "first"})
	;
}

/**
 * 用户信息初始化表
 */
function userInfoInit(){
	$("#userInfoGrid").jqGrid(
      {
        url : ctx+'/userauthweb/userauth/userInfolist.action',
        datatype : "json",
        colNames : ['','用户工号','用户名称','手机号码','状态','头像','创建时间'],
        colModel : [
        	 {name : 'user_id',index:'user_id',fixed:true,hidden:true,key:true},
             {name : 'user_code',index:'user_code',width:150,align:'center',fixed:true,searchoptions:{sopt:["bw"]}},
             {name : 'user_name',index:'user_name',width : 150,align:'center',fixed:true},
             {name : 'mobile',index:'mobile',width : 150,align:'center',fixed:true},
             {name : 'state',index:'state',width : 60,align:'center',fixed:true},
             {name : 'img_path',index:'img_path',align:'center'},
             {name : 'create_time_str',index:'create_time_str',width :197,align:'center',hidden:true}
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100],
        pager : '#userInfoToolbar',
        viewrecords : true,
        postData: {}, 
        width:600,
        height:pHeight-170,
        resizable:false,
		modal:true,
		sortname:"user_id",
		sortorder:"desc",
		mtype:'POST', 
		gridComplete:function(){
            var ids=$('#userInfoGrid').jqGrid('getDataIDs');
            for(var i = 0; i < ids.length ; i++){
            	//鼠标移上去显示手型
                var id = ids[i];
                $("#"+id).attr("style","cursor:pointer");
                //每行头像显示
	    		var rowData = $("#userInfoGrid").jqGrid('getRowData', id);
	            
	    		var ve = "";
	    		if(rowData['img_path']!=""){
	    			ve = "<a title='' style='color:#5EB8DA' href='#'"+ "onclick=\"$('#userInfoGrid').jqGrid('saveRow','" + id + "',bindHeadImg('" + rowData['user_id'] + "'));\">已绑定</a>";
	    		}else{
	    			ve = "<a title='' style='color:#ED942A' href='#'"+ "onclick=\"$('#userInfoGrid').jqGrid('saveRow','" + id + "',bindHeadImg('" + rowData['user_id'] + "'));\">未绑定</a>";
	    		}
	    		jQuery("#userInfoGrid").jqGrid("setRowData", id, { img_path : ve});
            }
        },
        onSelectRow : function(rowid) {
			var rowDatas = $("#userInfoGrid").jqGrid('getRowData', rowid);
        	var row = rowDatas['user_id']; 
        	var postData = {
					tpId: row 
				};
				$("#informationGrid").jqGrid('setGridParam',{
        			datatype: 'json',
        			postData: postData, //发送数据
        			page: 1
    			}).trigger("reloadGrid");
		}
      }
	);
}



/**
 * 详细信息列表初始化
 */
function informationInit(){
	$("#informationGrid").jqGrid(
      {
        url : ctx+'/userauthweb/userauth/information.action',
        datatype : "json",
        colNames : ['','权限标识','用户名称','角色名称','学生类型','机构名称','状态'],
        colModel : [
        	 {name : 'userid',index:'user_id',fixed:true,hidden:true,key:true},
             {name : 'auth_id',index:'auth_id',width : 80,align:'center',fixed:true},
             {name : 'user_name',index:'user_name',width : 120,align:'center',fixed:true},
             {name : 'role_name',index:'role_name',width : 138,align:'center',fixed:true},
             {name : 'type_name',index:'type_name',width :200,align:'center'},
             {name : 'orga_name',index:'orga_name',width : 100,align:'center',fixed:true},
             {name : 'state',index:'state',width : 60,align:'center',fixed:true}
             
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100 ],
        pager : '#informationToolbar',
        viewrecords : true,
        mtype: 'POST',
        postData: {
        	tpId:"-1"
        },
        width: 695,
        height:pHeight-170,
        resizable:false,
		modal:true,
		gridComplete:function(){
            var ids=$('#informationGrid').jqGrid('getDataIDs');
            for(var i = 0; i < ids.length ; i++){
                var id = ids[i];
                $("#"+id).attr("style","cursor:pointer");
            }
        }
        
      }
	);
}

function userAdd() {
	content=mypopdiv(2,"添加用户","1000px",(pHeight-100)+'px',"","","N",
			ctx + '/userauthweb/findOrginAndRole.action');	
	reloadGrid();
}
	//新增权限
	function authorityAdd() {
		var userId = "";
		var id = $("#userInfoGrid").jqGrid('getGridParam', 'selrow');
		//权限表无数据再看用户表是否有数据被选中			
		if (!id) {
			myalert_error("请先选择用户再添加权限！");
			return;
		}
		var user = $("#userInfoGrid").jqGrid('getRowData', id);
		userId = user.user_id;
		var stu=$("#informationGrid").jqGrid("getRowData",userId);
			if(stu.role_name=="学生"){
				myalert_error("此学生已有权限不可添加!");
				return ;
			}
			
			
			content=mypopdiv(2,"修改权限","400px","300px",(pHeight-300)/2,(pWidth-400)/2,"N",
					ctx + "/userauthweb/userautheditinit.action?action=add&userId=" + userId);
			queryUserAuth(userId);
	}
	
	//修改权限
	function authorityUpdate(){
		var id = $("#informationGrid").jqGrid('getGridParam', 'selrow');
		if (!id) {
			myalert_error("请先选择要修改的权限数据！");
			return ;
		}
		var userAuth = $("#informationGrid").jqGrid('getRowData', id);
		content=mypopdiv(2,"修改权限","400px","300px",(pHeight-300)/2,(pWidth-400)/2,"N",
				ctx + "/userauthweb/userautheditinit.action?action=edit&authId=" + userAuth.auth_id);
		queryUserAuth(userAuth.user_id);
}
	
	//删除权限
	function authorityDelete(){
		var id = $("#informationGrid").jqGrid('getGridParam', 'selrow');
		if (!id) {
			myalert_error("请先选择要删除的权限！");
			return ;
		}
		var userAuth = $("#informationGrid").jqGrid('getRowData', id);
		if (userAuth.state == 'X') {
			myalert_error('该权限已经被删除，不可重复删除！');
			return ;
		}
		myConfirm("您确定要删除权限[" + userAuth.role_name + "]吗？",(pHeight-200)/2+'px',(pWidth-320)/2+"px",function(){
			var userId=userAuth.user_id;
			var url = ctx + '/userauthweb/userauthdel.action';
			var postData = {
				authId:userAuth.auth_id
			};
			doAjax(url,postData,2,function(){
				//删除成功，刷新列表
				queryUserAuth(userId);
				myalert_success('操作成功！');
			});
		
		});	
	}

  

	/**
	 * 权限变刷新
	 * @param userId
	 */
	function queryUserAuth(userId) {
		$("#informationGrid").jqGrid('setGridParam', {
	        datatype:'json',
	        postData: {userId: userId}, //发送数据
	        page: 1
	    }).trigger('reloadGrid');
	}


 
 function closeMyWindonws(){
		layer.close(content);
		reloadGrid();
		if($("#userId").val()!=null){
			queryUserAuth($("#userId").val());
		}else{
			informationInit();
		}
 }

/**
 * 导出学生模板
 */
 function downExcel() {
		$("#exportForm").submit();
	}
 
 
 /**
  * 导出教师模板
  */
 function downTeacherExcel() {
		$("#exportTeachereForm").submit();
	}

/**
 * 上传学生数据
 */
 function uploadExcel(){
	 importpup=mypopdiv(1,"上传学生文件","400px","200px",(pHeight-200)/2,(pWidth-400)/2,'Y',"<center><span style='display:inline-block;'><strong>请选择文件:</strong></span><input style='display:inline-block;' name='file' type='file' id='file' /><br><br><br><a	class='btn btn-info margin_right' onclick='upload()'>上传</a></center>");
 }


 /**
  * 上传教职工数据
  */
  function uploadTeacherExcel(){
 	 importpup=mypopdiv(1,"上传教职工文件","400px","200px",(pHeight-200)/2,(pWidth-400)/2,'Y',"<center><span style='display:inline-block;'><strong>请选择文件:</strong></span><input style='display:inline-block;' name='file' type='file' id='file' /><br><br><br><a	class='btn btn-info margin_right' onclick='uploadTeacher()'>上传</a></center>");
  }
  
 function upload(){
	 	var fileName = $("#file").val();
	 	if(fileName==""){
	 		myalert_error('请选择上传excel文档！','43%','43%');
	 		return false;
	 	}
//	 	var laSh = layer.load(0,{shade:[0.5,'#fff']}); //遮罩效果
	 	var laSh = layer.msg('上传中，请稍候', {
	 		  icon: 16,
	 		  shade: [0.5,'#fff'],
	 		  skin: 'layui-layer-molv'
	 		});
	 	$.ajaxFileUpload({
	         url: ctx+'/userauthweb/uploadUserInfo.action?roleId=1', 
	         type: 'post',
	         secureuri: false,
	         fileElementId: 'file', // 上传文件的id、name属性名
	         dataType: 'json',
	         success: function(res){
	        	layer.close(laSh);
	         	if(res.data=="上传成功"){
	 	            myalert_success(res.data,'43%','43%');
	 	            reloadGrid() ;
	         	}else{
	         		var strs1=new Array();
	         		strs1=res.data.split(";");
//	         		var a="<center><h3>导入失败的行数及原因:</h3>";
//	         		for(var i=0;i<strs1.length;i++){
//	         			a+=strs1[i];
//	         			a+="<br>";
//	         		}
//	         		a+="</center>";
	         		var content1='<div style="width:100%;height:100%;"><table cellspacing="0" style="text-align: center; width:100%"><tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 35px;"><th style="color:#1C9BCD;font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;line-height: 23px;">导入失败的行数及原因</th></tr>';
			  	 	 for ( var int = 0; int < strs1.length; int++) {
			  	 		  content1+='<tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 30px;"><td style="font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;">'+strs1[int]+'</td></tr>'; 			  	 		  
						}
			  	 	 content1+='</table></div>';
	         		importpup=mypopdiv(1,"上传错误","400px","300px",(pHeight-300)/2,(pWidth-400)/2,'Y',content1);
	         		reloadGrid() ;
	         	}
	         },
	         error: function(data){ 
	         	myalert_error("上传异常！");
	         }
	     });
	 	closeMyLoading(importpup);
 }
 
 function uploadTeacher(){
	 	var fileName = $("#file").val();
	 	if(fileName==""){
	 		myalert_error('请选择上传excel文档！','43%','43%');
	 		return false;
	 	}
//	 	var laSh = layer.load(0,{shade:[0.5,'#fff']}); //遮罩效果
	 	var laSh = layer.msg('上传中，请稍候', {
	 		  icon: 16,
	 		  shade: [0.5,'#fff'],
	 		  skin: 'layui-layer-molv'
	 		});
	 	$.ajaxFileUpload({
	         url: ctx+'/userauthweb/uploadUserInfo.action?roleId=2', 
	         type: 'post',
	         secureuri: false,
	         fileElementId: 'file', // 上传文件的id、name属性名
	         dataType: 'json',
	         success: function(res){
	        	layer.close(laSh);
	         	if(res.data=="上传成功"){
	 	            myalert_success(res.data,'43%','43%');
	 	            reloadGrid() ;
	         	}else{
	         		var strs1=new Array();
	         		strs1=res.data.split(";");
//	         		var a="<center><h3>导入失败的行数及原因:</h3>";
//	         		for(var i=0;i<strs1.length;i++){
//	         			a+=strs1[i];
//	         			a+="<br>";
//	         		}
//	         		a+="</center>";
	         		var content1='<div style="width:100%;height:100%;"><table cellspacing="0" style="text-align: center; width:100%"><tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 35px;"><th style="color:#1C9BCD;font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;line-height: 23px;">导入失败的行数及原因</th></tr>';
			  	 	 for ( var int = 0; int < strs1.length; int++) {
			  	 		  content1+='<tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 30px;"><td style="font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;">'+strs1[int]+'</td></tr>'; 			  	 		  
						}
			  	 	 content1+='</table></div>';
	         		importpup=mypopdiv(1,"上传错误","400px","300px",(pHeight-300)/2,(pWidth-400)/2,'Y',content1);
	         		reloadGrid() ;
	         	}
	         },
	         error: function(data){ 
	         	myalert_error("上传异常！");
	         }
	     });
	 	closeMyLoading(importpup);
}
function queryByOrga(){
	 
	 $.ajax({
			url:ctx+'/userauthweb/queryByOrga.action',
			async:false,
			data:{},
			dataType:'json',
			success:function(res){
				var str='';
				for(var i=0;i<res.data.length;i++){
					str += '<option value="' +(res.data)[i].orga_id + '">' + (res.data)[i].orga_name + '</option>';
				}
				$("#orga").append(str);
			}
		});
 }

function clearUser(){
	  $("#userCode").val("");
    $("#orga").val("");
    $("#role").val("");
    jQuery("#informationGrid").jqGrid("clearGridData");
}
 
 
 