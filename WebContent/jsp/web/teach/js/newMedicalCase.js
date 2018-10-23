$(function(){
	$("#exponent_content").attr("style","width: 450px;height:"+(pHeight-450)+"px");//使用pHeight设置textarea高度
	if(pHeight < 756){
		$("#discussantTree").height($("#exponent_content").height()+86);
	}else{
		$("#discussantTree").height($("#exponent_content").height()+55);
	}
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('exponent_content',{scaleEnabled:true});
	//加载阐述人和讨论人信息
	var setting = {
		check: {
	        enable: true
	    },
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    callback: {
	        onCheck: onCheck
	    }
	};
	var zNodes = formDiscussantInfo();
	var zNodes1 =[
        { id:1, pId:0, name:"随意勾选 1", open:true},
		{ id:11, pId:1, name:"随意勾选 1-1", open:true},
		{ id:111, pId:11, name:"随意勾选 1-1-1"},
		{ id:112, pId:11, name:"随意勾选 1-1-2"},
		{ id:12, pId:1, name:"随意勾选 1-2", open:true},
		{ id:121, pId:12, name:"随意勾选 1-2-1"},
		{ id:122, pId:12, name:"随意勾选 1-2-2"},
		{ id:2, pId:0, name:"随意勾选 2",  open:true},
		{ id:21, pId:2, name:"随意勾选 2-1"},
		{ id:22, pId:2, name:"随意勾选 2-2", open:true},
		{ id:221, pId:22, name:"随意勾选 2-2-1"},
		{ id:222, pId:22, name:"随意勾选 2-2-2"},
		{ id:23, pId:2, name:"随意勾选 2-3"}
	];
	$.fn.zTree.init($("#discussantTree"), setting, zNodes);
});
//选择讨论人
function onCheck(e,treeId,treeNode){
	$(".stuNameDiv").html("");
	var stuNameStr = "";
    var treeObj=$.fn.zTree.getZTreeObj("discussantTree");
    var nodes=treeObj.getCheckedNodes(true);
    for(var i=0;i<nodes.length;i++){
    	if(nodes[i].pId != null){
    		stuNameStr += "<span class='stuSpan'>"+nodes[i].name+"</span>" +
						  "<input type='hidden' value='"+nodes[i].id+"' class='hid_stu_auth_id' />";
    	}
    }
    $(".stuNameDiv").html(stuNameStr);
 }
//加载讨论人和阐述人信息
function formDiscussantInfo(){
	var treeStr = '';
	$.ajax({
		type: 'POST',
		async: false,
		url: ctx + "/teachweb/formDiscussantInfo.action",
		data: {
			
		},
		dataType: 'json',
		success:function(data) {
			var exponents = data.exponents;//  阐述人
			var discussants = data.discussants;
			var orga_name = data.orga_name;
			treeStr += '[{"id" : 1, "pId" : 0, "name" : "'+orga_name+'", "open" : true},';
			//拼接阐述人下拉框
			var optStr = '<option value="">---请选择---</option>';
			for(var i = 0; i < exponents.length; i++){
				optStr += '<option value="'+exponents[i].stu_auth_id+'">'+exponents[i].name+'</option>';
				treeStr += '{"id" : '+exponents[i].stu_auth_id+', "pId" : 1, "name" : "'+exponents[i].name+'", "open" : true},';
			}
			$("#sel_exponent_auth_id").html(optStr);
			//组织讨论人
			treeStr += '{"id" : 2, "pId" : 0, "name" : "其他学生", "open" : false},';
			for(var j = 0; j < discussants.length; j++){
				treeStr += '{"id" : '+discussants[j].stu_auth_id+', "pId" : 2, "name" : "'+discussants[j].name+'", "open" : false},';
			}
			treeStr += ']';
	    	if(treeStr.indexOf("},]") != -1){
	    		treeStr = treeStr.replace(/},]/, "}]");
	    	}
//	    	alert(treeStr);
		}
	});
	var zNodes = $.parseJSON(treeStr);
	return zNodes;
}
//保存
function submitData(){
	var in_patient_info = $("#inp_in_patient_info").val();
	var exponent_auth_id = $("#sel_exponent_auth_id").val();
	var hope_finish_time = $("#inp_hope_finish_time").val();
	var exponent_content = UE.getEditor('exponent_content').getContent();
	if(in_patient_info == ''){
		myalert_error("病人信息不能为空！","30%","40%");
		return false;
	}
	if(exponent_auth_id == ''){
		myalert_error("阐述人不能为空！","30%","40%");
		return false;
	}
	if(hope_finish_time == ''){
		myalert_error("期望完成时间不能为空！","30%","40%");
		return false;
	}
	if(exponent_content == ''){
		myalert_error("病例简要说明不能为空！","30%","40%");
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}

	if($(".stuNameDiv").html() == ''){
		myalert_error("讨论人不能为空！","30%","40%");
		return false;
	}
	var stu_auth_ids = '';
	$(".hid_stu_auth_id").each(function(){
		if(stu_auth_ids == ''){
			stu_auth_ids = $(this).val();
		}else{
			stu_auth_ids += ',' + $(this).val();
		}
	});
	var url=ctx + "/teachweb/releaseMedicalCaseDiscuss.action";
	var postData = {
			in_patient_info : in_patient_info, 
			exponent_auth_id : exponent_auth_id, 
			hope_finish_time : hope_finish_time,
			exponent_content : exponent_content,
			stu_auth_ids : stu_auth_ids
	};
	doAjax(url,postData,2,function(res){
		if(res.success == true){
			myalert_success(res.data,"30%","40%",function(){
				 parent.closeMyWindows();
			});
		}else{
			myalert_error(res.data,"30%","40%");
		}
	});
}


