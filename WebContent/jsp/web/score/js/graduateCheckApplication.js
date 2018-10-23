var s_user_auth_id = '';
var s_orga_id = '';
var flag = '';
var applyAgainFlag = '';

var formFinishFlag = false;
$(function(){
	s_user_auth_id = $("#s_user_auth_id").text();
	s_orga_id = $("#s_orga_id").text();
	flag = $("#flag").text();
	applyAgainFlag = $("#applyAgainFlag").text();
	
	var flagOfBtn = $("#flagOfBtn").text();
	if(flagOfBtn == 1){
		$(".btns").css("display","block");
	}
    //个人基础信息
    $.ajax({
	  	type: 'POST',
	  	async:false, 
	  	url :ctx+'/scoreweb/getBasicInformation.action', 
		data : {
			},
		dataType: 'json',
	  	success:function(res) {
	  		$("#name").text(res.data.name);
	  		$("#orga_name").text(res.data.orga_name);
	  		$("#deptReceiveStr").text(res.data.deptReceiveStr);
	  		$("#teach_name").text(res.data.teach_name);
	  		$("#train_start_str").text(res.data.train_start_str);
	  		$("#train_end_str").text(res.data.train_end_str);
	  		$("#daysAwayFromTrainEndTime").text(res.data.daysAwayFromTrainEndTime);
	  		$("#progresses").val(res.data.completion_rate);
	  		$("#completion_rate").text(res.data.completion_rate + "%");//  大纲吻合度
	  	}
	});
    //
    formListInit();
    // 
    groupsInit();
    
    $(".arr").click(function (){
	    var body = $(this).parent().siblings();
	    $(body).slideToggle();
	    $(this).toggleClass("arrdown");
    });
});
//表单配置
function formListInit(){
	//添加加载层
	var divPop = myLoading();
    $.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/getFormList.action', 
    	data : {
    		s_user_auth_id:s_user_auth_id,
    		s_orga_id:s_orga_id,
    		applyAgainFlag:applyAgainFlag
    	},
    	dataType: 'json',
    	success:function(res) {
    		var str = '';
    		for (var i = 0; i < res.data.length; i++) {
    			var flag = res.data[i].flag;
    			str += '' +
    			'<span>' +
    			"<a href=\"javascript:void(0);\" onclick=\"formManage('-10','-100','"+res.data[i].form_id+"','"+res.data[i].name+"','"+res.data[i].type_id+"','"+flag+"','"+res.data[i].sfm_id+"')\">";
    			if (res.data[i].flag == 1) {//  完成
    				str += '<img src="' + ctx + '/jsp/web/score/images/finished.png" alt=""/>';
    			}else if (res.data[i].flag == -1) {//  未完成
    				str += '<img src="' + ctx + '/jsp/web/score/images/unfinished.png" alt=""/>';
    				formFinishFlag = true;
    			}
    			str += '</a>' +
    			'<i>' + res.data[i].name + '</i>';
    			str += '<input type="hidden" class="hidden_form_id" value="' + res.data[i].form_id + '"/>' +
    				'</span>';
    		}
    		//去掉加载层
    		closeMyLoading(divPop);
//    	  	alert(str);
    		$("#div_form").html(str);
    	}
    });
}
//组次初始化
function groupsInit(){
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
    			//添加加载层
    			var divPop = myLoading();
    			$(".progressDiv").html('');
    			for (var i = 0; i < data.length; i++) {
    				str += '<div class="panel panel-default">';
    				if(i == data.length-1){
    					str += '<div class="panel-heading panel-heading-color">' +
			    					'<b class="title">第' + data[i].pub_num + '次审核记录</b>' +
			        				'<a class="arr arrup "></a>' +
			        		   '</div>';
    				}else{
    					str += '<div class="panel-heading">' +
			    					'<b class="title">第' + data[i].pub_num + '次审核记录</b>' +
			        				'<a class="arr arrup arrdown"></a>' +
			        		   '</div>';
    				}
        			if(i == data.length-1){
    					str += '<div class="panel-body">';
    				}else{
    					str += '<div class="panel-body grayTitle" style="display: none;background: #f5f5f5;">';
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
    			//去掉加载层
        		closeMyLoading(divPop);
//        	  	alert(str);
        	  	$(".progressDiv").html(str);
    		}
//    		else{
//    			str += '<div class="panel panel-default">' +
//    			'<div class="panel-heading panel-heading-color">' +
//    				'<b class="title">第1次审核记录</b>' +
//    				'<a class="arr arrup"></a>' +
//    			'</div>' +
//    			'<div class="panel-body">' +
//    				'<div class="about4">' +
//    					'<div class="lTitleDiv">' +
//    						'<div class="hr"></div>' +
//    						'<h5 class="lTitle">审批流程</h5>' +
//    					'</div>' +
//    					'<div class="process_top">' +
//    						'<table><tbody><tr>' + flowChartInit(1) + '</tr></tbody></table>' +
//    					'</div>' +
//    				'</div>' +
//    			'</div>' +
//    		'</div>';
//    		}
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
//    		var flag_color = null;//  每个节点的颜色
//    		if(res.flag != null && res.flag == -1){//  未驳回(正出科+出科完成)
//    			for(var i = 0; i < res.processSelList.length; i++){
//    				flag_color = res.processSelList[i].flag_color;
//        			str += '' +
//        				'<td>' +
//        					'<div>';
//        					if(res.processSelList[i].flag_color != null && res.processSelList[i].flag_color == 1){
//        						str += '<b>' + (i+1) + '</b>';
//        						if(i+1 < res.processSelList.length && res.processSelList[i+1].flag_color != null && res.processSelList[i+1].flag_color == 1){
//        							str += '<i  class="iWidth"></i>';
//        						}else if(i+1 < res.processSelList.length && res.processSelList[i+1].flag_color == null){
//        							str += '<i  class="iWidth gray"></i>';
//        						}else if(i+1 == res.processSelList.length){
//        							str += '<i  class="iWidth"></i>';
//        						}
//        					}else if(res.processSelList[i].flag_color == null){
//        						str += '<b class="gray">' + (i+1) + '</b>' +
//        						'<i  class="iWidth gray"></i>';
//        					}
//        					str +=
//        					'</div>' +
//        					'<div class="divv">' +
//        						'<span class="proc_name">' + res.processSelList[i].proc_name + '</span>' +
//        					'</div>' +
//        				'</td>';
//        		}
//    			if(flag_color != null && flag_color == 1){
//    				str += '<td class="finish_td" ><div><b>✔</b></div>' +
//    				'<div class="divv"><span>出科</span></div></td>';
//    			}else if(flag_color == null){
//    				str += '<td class="finish_td" ><div><b class="gray">✔</b></div>' +
//    				'<div class="divv"><span>出科</span></div></td>';
//    			}
//    		}else if(res.flag != null && res.flag == 1){//  驳回
//    			for(var i = 0; i < res.processSelList.length; i++){
//        			str += '' +
//        				'<td>' +
//        					'<div>' +
//        						'<b class="gray">' + (i+1) + '</b>' +
//        						'<i  class="iWidth gray"></i>' +
//        					'</div>' +
//        					'<div class="divv">' +
//        						'<span class="proc_name">' + res.processSelList[i].proc_name + '</span>' +
//        					'</div>' +
//        				'</td>';
//        		}
//    			str += '<td class="finish_td" ><div><b class="gray">X</b></div>' +
//    			'<div class="divv"><span>驳回</span></div></td>';
//    		}
////    		alert(str);
////    		$(".flow_chart").html(str);
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
    		pub_num : pub_num
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
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listStu[int].create_auth_id+"','"+res.listStu[int].form_id+"','"+res.listStu[int].form_name+"','"+res.listStu[int].form_type+"','1','"+res.listStu[int].id+"')\">"+res.listStu[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listStu[int].create_auth_id+"','"+res.listStu[int].form_id+"','"+res.listStu[int].form_name+"','"+res.listStu[int].form_type+"','1','"+res.listStu[int].id+"')\">"+res.listStu[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>申&nbsp;&nbsp;请&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    						"<strong>出科申请</strong>："+res.outlists[i].exam_content+
    						"<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
    			}else if(res.outlists[i].roleName == "带教老师" || res.outlists[i].roleName == "教学秘书"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listTea.length; int++) {
    					if(int==res.listTea.length-1){
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listTea[int].create_auth_id+"','"+res.listTea[int].form_id+"','"+res.listTea[int].form_name+"','"+res.listTea[int].form_type+"','1','"+res.listTea[int].id+"')\">"+res.listTea[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listTea[int].create_auth_id+"','"+res.listTea[int].form_id+"','"+res.listTea[int].form_name+"','"+res.listTea[int].form_type+"','1','"+res.listTea[int].id+"')\">"+res.listTea[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    			lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    					"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>";
    					if(res.flag != null && res.flag == -1) {
    						lis +=
    						"<strong>理论分数</strong>：<strong style='color:#FEA356;'>"+res.outlists[i].theory_sco+"</strong><br/>" +
    						"<strong>技能分数</strong>：<strong style='color:#FEA356;'>"+res.outlists[i].skill_sco+"</strong><br/>";
    					}
    					lis +=
        					"<strong>审核意见</strong>：";
        					if(res.outlists[i].exam_result == 1){
        						lis += exam_content;
        					}else if(res.outlists[i].exam_result == -1 || res.outlists[i].exam_result == 0){
        						lis += "驳回<br/>" +
        						"<strong>驳回原因</strong>：" + exam_content;
        					}
        					lis += "<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
    			}else if(res.outlists[i].roleName == "科主任"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listScea.length; int++) {
    					if(int==res.listScea.length-1){
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listScea[int].create_auth_id+"','"+res.listScea[int].form_id+"','"+res.listScea[int].form_name+"','"+res.listScea[int].form_type+"','1','"+res.listScea[int].id+"')\">"+res.listScea[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listScea[int].create_auth_id+"','"+res.listScea[int].form_id+"','"+res.listScea[int].form_name+"','"+res.listScea[int].form_type+"','1','"+res.listScea[int].id+"')\">"+res.listScea[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    				"<strong>审核意见</strong>：";
					if(res.outlists[i].exam_result == 1){
						lis += exam_content;
					}else if(res.outlists[i].exam_result == -1 || res.outlists[i].exam_result == 0){
						lis += "驳回<br/>" +
						"<strong>驳回原因</strong>：" + exam_content;
					}
//    						"<br/><strong>完成表单</strong>："+formRoleStr+
					lis += "</i></li>";
    			}else if(res.outlists[i].roleName == "科教科"){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listKe.length; int++) {
    					if(int==res.listKe.length-1){
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listKe[int].create_auth_id+"','"+res.listKe[int].form_id+"','"+res.listKe[int].form_name+"','"+res.listKe[int].form_type+"','1','"+res.listKe[int].id+"')\">"+res.listKe[int].form_name+"</a>&nbsp;&nbsp;";
    					}else{
    						formRoleStr+="<a onclick=\"formManage('10','"+res.listKe[int].create_auth_id+"','"+res.listKe[int].form_id+"','"+res.listKe[int].form_name+"','"+res.listKe[int].form_type+"','1','"+res.listKe[int].id+"')\">"+res.listKe[int].form_name+"</a>&nbsp;；&nbsp;";
    					}
    				}
    				if(formRoleStr==""){
    					formRoleStr+="无";
    				}
    				lis+="<li><b>"+res.outlists[i].dateTimeStr+"<br/>"+res.outlists[i].dateTimeStrEnd+"</b><span></span>" +
    				"<i><strong>审&nbsp;&nbsp;核&nbsp;人</strong>："+res.outlists[i].userName+"【"+res.outlists[i].roleName+"】<br/>" +
    				"<strong>审核意见</strong>：";
					if(res.outlists[i].exam_result == 1){
						lis += exam_content;
					}else if(res.outlists[i].exam_result == -1 || res.outlists[i].exam_result == 0){
						lis += "驳回<br/>" +
						"<strong>驳回原因</strong>：" + exam_content;
					}
//    						"<br/><strong>完成表单</strong>："+formRoleStr+
					lis += "</i></li>";
    			}
    		}
//    		alert(lis);
//    		$(".ulID").html(lis);
    	}
	});
	return lis;
}
//表单配置填写前的展示
function formManage(flag_read,create_auth_id,form_id,form_name,type_id,flag,sfm_id){//  flag_read:从流程记录处触发的标识(-10：表单列表、10：审核记录)、create_auth_id：创建人id、flag：编辑标识
	if(form_id == null || form_id == ''){
		myMsg("请选择表单！",'50%','50%');
		return false;
	}
	//判断是评分表单还是普通表单
	if(type_id==1){//  评分表单
		url = ctx+"/jsp/web/score/formWithMark.jsp?flag_read="+flag_read+"&type_id="+type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+s_user_auth_id+"&s_orga_id="+s_orga_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
		mypopdiv(2,"表单信息",'1200px',(pHeight-80)+'px','','','N',url);
	}else if(type_id==2){//  普通表单
		if(flag_read == 10){//  已经出科，从流程图处触发的 
			showHtmlForm(form_id,create_auth_id,sfm_id);
		}else{//  未出科
			url = ctx+"/jsp/web/score/formWithHtml.jsp?form_name="+form_name+"&type_id="+type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+s_user_auth_id+"&s_orga_id="+s_orga_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
			mypopdiv(2,"表单信息",'1200px',(pHeight-80)+'px','','','N',url);
		}
	}
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
//发起出科
function applyForGraduateCheck(){
	if(formFinishFlag){
		myalert_error("有表单未填写，不能进行出科！");
		return false;
	}
	var form_id_str = '';
	$(".hidden_form_id").each(function(){
		form_id_str += $(this).val() + '-';
	});
	var postData={};
	if(typeof(form_id_str) == "undefined" || form_id_str == ""){
		postData={
				s_user_auth_id : s_user_auth_id,
				s_orga_id : s_orga_id
				}; 
	}else{
		postData={
				form_id_str	: form_id_str,
				s_user_auth_id : s_user_auth_id,
				s_orga_id : s_orga_id
				}; 
	}
	myConfirm("是否要发起出科审核？","","",
			function(){
			var url=ctx + "/scoreweb/applyForGraduateCheck.action";
//			var postData = {
//				form_id_str	: form_id_str,
//				s_user_auth_id : s_user_auth_id,
//				s_orga_id : s_orga_id
//			};
			doAjax(url,postData,2,function(res){
				 if(res.success == true){
					 window.location.href=ctx + "/scoreweb/graduateCheckApplicationPage.action";
					 myalert_success(res.data);
				 }else{
					 myalert_error(res.data);
				 }
			 });
			}
		);
}
//重载
function loadAgain(){
	formFinishFlag = false;
	formListInit();
	layer.closeAll();
}
//重新发起申请
function applyForGraduateCheckAgain(){
	myConfirm("是否要重新发起出科审核？","","",
			function(){
				window.location.href=ctx + "/scoreweb/graduateCheckApplicationPage.action?flagApplyAgain=yes";
			}
		);
}