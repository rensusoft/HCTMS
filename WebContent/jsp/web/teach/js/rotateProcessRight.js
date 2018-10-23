function info_standard(){
	var tpc_id=$("#tpc_id").val();
	var url=ctx+'/teachweb/selectReq_content.action';
    var postData={
    		tpc_id:tpc_id	
    };
    $.ajax({
  		type: 'POST',
	  	url :url,
	  	data:postData,
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		var htmlContent="<div style='padding:15px;'>"+data.req_content+"</div>";
  	 		if(htmlContent!=""||htmlContent!=null){
  	 			if(roleCode == "R_STU"){
  	 				parent.showDivInfo("科室轮转规范",htmlContent);
  	 			}else{
  	 				showDivInfo("科室轮转规范",htmlContent);
  	 			}
  	 		}else{
  	 			myalert_error('未查询到相关数据...');
  	 		}
  	 	},
        error:function(data) {
        	myalert_error('未查询到相关数据...');
	 	}
    });
}

function indeptEducInfo(){
	 var url=ctx+'/teachweb/getIndeptEducInfo.action';
	 var postData={
			stu_auth_id:$("#stu_auth_id").val(),
			dept_id:$("#dept_id").val(),
			type_id:1
	 };
	 doAjax(url,postData,2,function(res){
		 if(res.success == true){
			var htmlContent="<div style='padding:15px;'>"+res.data+"</div>";
  	 		if(htmlContent!=""||htmlContent!=null){
  	 			if(roleCode == "R_STU"){
  	 				parent.showDivInfo("入科宣教信息",htmlContent);
  	 			}else{
  	 				showDivInfo("入科宣教信息",htmlContent);
  	 			}
  	 		}else{
  	 			myalert_error('未查询到相关数据...');
  	 		}
		 }else{
			 myalert_error('未查询到相关数据...');
		 }
	 });
}
//
function showDivInfo(title,htmlContent){
	//弹出层展现数据信息
	mypopdiv(1,title,"1100px",(pHeight-50)+"px","","","N","<div style='height:"+(pHeight-95)+"px;overflow:auto;padding:10px;'>"+htmlContent+"</div>");
}

var s_user_auth_id = '';
var s_orga_id = '';
var or_id = '';
var flag = '';

var roleCode = '';
var stuTypeId = '';
$(function(){
	s_user_auth_id = $("#stu_auth_id").val();
	s_orga_id = $("#dept_id").val();
	or_id = $("#or_id").text();
	flag = $("#flag").text();
	
	roleCode = $("#span_roleCode").text();
	stuTypeId = $("#span_stuTypeId").text();
	var flagOfBtn = $("#flagOfBtn").text();
	if(flagOfBtn == 1){
		$(".btns").css("display","block");
	}
   
    
    
    // 
    groupsInit();
    
    $(".arr").click(function (){
	    var body = $(this).parent().siblings();
	    $(body).slideToggle();
	    $(this).toggleClass("arrdown");
    });
});
//组次初始化
function groupsInit(){
	//添加加载层
	var divPop = myLoading();
	$.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/getGroups.action', 
    	data : {
    		s_user_auth_id:s_user_auth_id,
    		s_orga_id:s_orga_id
    	},
    	dataType: 'json',
    	success:function(res) {
    		data = res.rows;
    		var str = '';
    		if(data.length > 0){
    			for (var i = 0; i < data.length; i++) {
    				str += '<div class="panel panel-default">';
    				if(i == data.length-1){
    					str += '<div class="proTitle">' +
			    					'<b class="title">第' + data[i].pub_num + '次审核记录</b>' +
			        				'<a class="arr arrup "></a>' +
			        		   '</div>';
    				}else{
    					str += '<div class="proTitle">' +
			    					'<b class="title">第' + data[i].pub_num + '次审核记录</b>' +
			        				'<a class="arr arrup arrdown"></a>' +
			        		   '</div>';
    				}
        			if(i == data.length-1){
    					str += '<div class="panel-body">';
    				}else{
    					str += '<div class="panel-body" style="display: none;background: #f5f5f5;">';
    				}
        			 str += '<div class="about4">' +
	        					'<div class="lTitleDiv">' +
	        						'<div class="hr"></div>' +
	        						'<h5 class="lTitle">审批流程</h5>' +
	        					'</div>' +
	        					'<div class="process_top">' +
	        						'<table><tbody><tr>' + flowChartInit(data[i].pub_num) + '</tr></tbody></table>' +
	        					'</div>';
        			 		if(prosessInit(data[i].pub_num) != ''){
        			 			str += 
	        			 			'<div class="lTitleDiv">' +
	        			 				'<div class="hr"></div>' +
	        			 				'<h5 class="lTitle">审批记录</h5>' +
	        			 			'</div>' +
	        			 			'<div class="about4_main">' +
	        			 				'<div class="line">' +
	        			 					'<img src="' + ctx + '/jsp/web/score/images/stepblarrow.png" alt=""/>' +
	        			 				'</div>' +
	        			 				'<ul>' + prosessInit(data[i].pub_num) + '</ul>' +
	        			 			'</div>';
        			 		}
        			 	str +=
        				'</div>' +
        			'</div>' +
        		'</div>';
    			}
    		}
    		//去掉加载层
			closeMyLoading(divPop);
    	  	$(".progressDiv").html(str);
    	  	
//    	  	$(".process_top table").width(700);
    	  	$('.progressDiv').each(function(){
    	  		var lWidth=$(this).width()-50;
    	  		if(lWidth != 0){
    	  			console.log(lWidth);
    	  			$(this).find('.process_top table').width(lWidth);
//    	  			$(".process_top table").width(lWidth);
    	  			var tWidth=$(this).find(".process_top table").width();
    	  			$(this).find(".finish_td").width(50);
    	  			var tdsWidth=tWidth-$(this).find(".finish_td").width();
    	  			console.log(tdsWidth);
    	  			$(this).find('.process_top table').each(function(){
//    	  				alert($(this).find("tr td").size());
    	  				if($(this).find("tr td").size() == 4){
    	  					$(this).find(".iWidth").width(tdsWidth/3-35);
    	  				}else if($(this).find("tr td").size() == 5){
    	  					$(this).find(".iWidth").width(tdsWidth/4-38);
    	  				}else if($(this).find("tr td").size() ==3){
    	  					$(this).find(".iWidth").width(tdsWidth/2-35);
    	  				}
    	  			});
    	  		}
    		});
    	}
    });
}
//流程图初始化
function flowChartInit(pub_num){
	var str = '';
	$.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/getflowChartInfo.action', 
    	data : {
    		pub_num : pub_num,
    		s_user_auth_id : s_user_auth_id,
    		s_orga_id : s_orga_id
    	},
    	dataType: 'json',
    	success:function(res) {
    		var data = res.data;
    		if(data.length > 0){
    			if(data[data.length-1].exam_result == -1){
    				for(var i = 0; i < data.length; i++){
            			str += '' +
            				'<td>' +
            					'<div>' +
            						'<b class="gray">' + (i+1) + '</b>' +
            						'<i  class="iWidth gray"></i>' +
            					'</div>' +
            					'<div class="divv">';
            					if(data[i].exam_role_id == 10){
            						str += '<span class="proc_name">' + '学生出科申请' + '</span>';
            					}else if(data[i].exam_role_id == 20 || data[i].exam_role_id == 30){
            						str += '<span class="proc_name">' + '老师/教学秘书审核' + '</span>';
            					}else if(data[i].exam_role_id == 40){
            						str += '<span class="proc_name">' + '科主任审核' + '</span>';
            					}else if(data[i].exam_role_id == 50){
            						str += '<span class="proc_name">' + '科教科审核' + '</span>';
            					}
            				str +=
            					'</div>' +
            				'</td>';
            		}
        			str += '<td class="finish_td" ><div><b class="gray">✘</b></div>' +
        			'<div class="divv"><span>驳回</span></div></td>';
    			}else{
    				for(var i = 0; i < data.length; i++){
            			str += '' +
            				'<td>' +
            					'<div>';
            					if(data[i].exam_result == 1){
            						str += '<b>' + (i+1) + '</b>';
            						if(i < data.length-1 && data[i+1].exam_result == 1){
            							str += '<i  class="iWidth"></i>';
            						}else if(i < data.length-1 && (data[i+1].exam_result == -1 || data[i+1].exam_result == null)){
            							str += '<i  class="iWidth gray"></i>';
            						}else if(i = data.length-1){
            							str += '<i  class="iWidth"></i>';
            						}
            					}else{
            						str += '<b class="gray">' + (i+1) + '</b>' +
            							   '<i  class="iWidth gray"></i>';
            					}
            					str += 
            					'</div>' +
            					'<div class="divv">';
            					if(data[i].exam_role_id == 10){
            						str += '<span class="proc_name">' + '学生出科申请' + '</span>';
            					}else if(data[i].exam_role_id == 20 || data[i].exam_role_id == 30){
            						str += '<span class="proc_name">' + '老师/教学秘书审核' + '</span>';
            					}else if(data[i].exam_role_id == 40){
            						str += '<span class="proc_name">' + '科主任审核' + '</span>';
            					}else if(data[i].exam_role_id == 50){
            						str += '<span class="proc_name">' + '科教科审核' + '</span>';
            					}
            				str +=
            					'</div>' +
            				'</td>';
            		}
    				if(data[data.length-1].exam_result == 1){
        				str += '<td class="finish_td" ><div><b>✔</b></div>' +
        				'<div class="divv"><span>出科</span></div></td>';
        			}else if(data[data.length-1].exam_result == null){
        				str += '<td class="finish_td" ><div><b class="gray">✔</b></div>' +
        				'<div class="divv"><span>出科</span></div></td>';
        			}
    			}
    		}
    	}
    });
	return str;
}

//记录初始化
function prosessInit(pub_num){
	var lis="";
	$.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/getProcessInfor.action', 
    	data : {
    		pub_num : pub_num,
    		s_user_auth_id : s_user_auth_id,
    		s_orga_id : s_orga_id,
    		stuTypeId : stuTypeId
    	}, 
    	dataType: 'json',
    	success:function(res) {
    		for ( var i = 0; i < res.outlists.length; i++) {
    			var exam_content = '';
    			if(res.outlists[i].exam_content != null && res.outlists[i].exam_content != ''){
    				exam_content = res.outlists[i].exam_content;
    			}else{
    				exam_content = "无";
    			}
    			if(i==0){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listStu.length; int++) {
    					if(int==res.listStu.length-1){
    						formRoleStr+="<a onclick='formManage("+res.listStu[int].form_id+",10,"+res.listStu[int].create_auth_id+","+pub_num+")'>"+res.listStu[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick='formManage("+res.listStu[int].form_id+",10,"+res.listStu[int].create_auth_id+","+pub_num+")'>"+res.listStu[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(roleCode == "R_STU"){
      	 				if(formRoleStr==""){
        					formRoleStr+="无";
        				}
      	 			}else{
      	 				formRoleStr="无";
      	 			}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>申&nbsp;&nbsp;请&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    						"<strong>出科申请</strong>："+res.outlists[i].exam_content+
    						"<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
    			}else if(res.outlists[i].roleName == "带教老师" || res.outlists[i].roleName == "教学秘书"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listTea.length; int++) {
    					if(int==res.listTea.length-1){
    						formRoleStr+="<a onclick='formManage("+res.listTea[int].form_id+",10,"+res.listTea[int].create_auth_id+","+pub_num+")'>"+res.listTea[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick='formManage("+res.listTea[int].form_id+",10,"+res.listTea[int].create_auth_id+","+pub_num+")'>"+res.listTea[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(roleCode == "R_STU"){
      	 				if(formRoleStr==""){
        					formRoleStr+="无";
        				}
      	 			}else{
      	 				formRoleStr="无";
      	 			}
    			lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    					"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>";
    					if(res.flag != null && res.flag == -1) {
    						lis +=
    						"<strong>理论分数</strong>：<strong style='color:#FEA356;'>"+res.outlists[i].theory_sco+"</strong><br/>" +
    						"<strong>技能分数</strong>：<strong style='color:#FEA356;'>"+res.outlists[i].skill_sco+"</strong><br/>";
    					}
    					lis +=
    					"<strong>审核意见</strong>："+exam_content+
    					"<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
    					"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    							"<strong>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数</strong>：<strong style='color:#FEA356;'>"+res.outlists[i].exam_sco+"</strong><br/>" +
    							"<strong>审核意见</strong>:"+res.outlists[i].exam_content+
    							"<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
    			}else if(res.outlists[i].roleName == "科主任"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listScea.length; int++) {
    					if(int==res.listScea.length-1){
    						formRoleStr+="<a onclick='formManage("+res.listScea[int].form_id+",10,"+res.listScea[int].create_auth_id+","+pub_num+")'>"+res.listScea[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick='formManage("+res.listScea[int].form_id+",10,"+res.listScea[int].create_auth_id+","+pub_num+")'>"+res.listScea[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    						"<strong>审核意见</strong>："+exam_content+
//    						"<br/><strong>完成表单</strong>："+formRoleStr+
    						"</i></li>";
    			}else if(res.outlists[i].roleName == "科教科"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listKe.length; int++) {
    					if(int==res.listKe.length-1){
    						formRoleStr+="<a onclick='formManage("+res.listKe[int].form_id+",10,"+res.listKe[int].create_auth_id+","+pub_num+")'>"+res.listKe[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick='formManage("+res.listKe[int].form_id+",10,"+res.listKe[int].create_auth_id+","+pub_num+")'>"+res.listKe[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    						"<strong>审核意见</strong>："+exam_content+
//    						"<br/><strong>完成表单</strong>："+formRoleStr+
    						"</i></li>";
    			}
    		}
//    		alert(lis);
//    		$(".ulID").html(lis);
    	}
	});
	return lis;
}
//表单配置填写前的展示
function formManage(form_id,flag_read,create_auth_id,pub_num){ //form_id:表单id; flag_read:从流程记录处触发的标识; create_auth_id:创建人id
	if(form_id == null || form_id == ''){
		myMsg("请选择表单！",'50%','50%');
		return false;
	}
	var form_name = $("#hidden_name_" + form_id).val();
//	var flag = $("#hidden_flag_" + form_id).val();//  
	var url=ctx+'/scoreweb/getStuFormType.action';
	var postData={
			form_id : form_id,
			s_user_auth_id:s_user_auth_id,
    		s_orga_id:s_orga_id,
    		pub_num:pub_num,
    		create_auth_id:create_auth_id
	};
	doAjax(url,postData,2,function(res){
		var url = "";
		var flag = res.data.flag;//  是否编辑过
		var sfm_id = res.data.sfm_id;
		//判断是评分表单还是普通表单
		if(res.data.type_id==1){//  评分表单
			url = ctx+"/jsp/web/score/formWithMark.jsp?flag_read="+flag_read+"&type_id="+res.data.type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+s_user_auth_id+"&s_orga_id="+s_orga_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
			mypopdiv(2,"表单信息",'1200px',(pHeight-80)+'px','','','N',url);
		}else if(res.data.type_id==2){//  普通表单
			if(flag_read == 10){//  已经出科，从流程图处触发的 
				showHtmlForm(form_id,create_auth_id,sfm_id);
			}else{//  未出科
				url = ctx+"/jsp/web/score/formWithHtml.jsp?form_name="+form_name+"&type_id="+res.data.type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+s_user_auth_id+"&s_orga_id="+s_orga_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
				mypopdiv(2,"表单信息",'1200px',(pHeight-80)+'px','','','N',url);
			}
		}
	});
}
//普通表单的展示
function showHtmlForm(form_id,create_auth_id,sfm_id){
	$.ajax({
		type: 'POST',
    	async: false,
    	url: ctx+'/scoreweb/showHtmlForm.action', 
    	data: {
    		form_id : form_id,
    		s_user_auth_id:s_user_auth_id,
    		s_orga_id:s_orga_id,
    		create_auth_id:create_auth_id,
    		sfm_id:sfm_id
    	},
    	dataType: 'json',
    	success:function(res) {
			var content = 
				'<div style="text-align: center;font-size:22px;font-weight:bold;" id="name">' + res.data.t_name + '</div>' +
				'<div style="margin:10px 0px -10px 0px;">' + 
					'<div style="float:left;width:23%;"><strong>表单类型：</strong><span id="form_type">普通表单</span></div>' +
					'<div style="clear:both;"></div>' +
				'</div>' + 
				'<hr/>' +
				res.data.t_content;
			mypopdiv(1,"表单信息",'1200px',(pHeight-80)+'px','','','N',content);
    	}
	});
}
//数据录入详情
function stuActiveDataInfo(object){
	var finnish_num = $(object).nextAll(".span_finnishNum").text();
	var data_type_id = $(object).nextAll(".span_dataTypeId").text();
	if(finnish_num > 0){
		if(roleCode == "R_STU"){
				parent.showByParent(s_user_auth_id,s_orga_id,data_type_id);
			}else{
				mypopdiv(2,"数据录入详情","1000px",(pHeight-50)+"px","50px",(pWidth-1000)/2+"px","N",ctx + '/jsp/web/teach/stuActiveDataInfo.jsp?stu_auth_id='+s_user_auth_id+'&dept_id='+s_orga_id+'&data_type_id='+data_type_id);
			}
	}
}
