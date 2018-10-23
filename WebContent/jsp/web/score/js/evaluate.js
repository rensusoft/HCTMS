var stuId = '';
var train_dept_id = '';
var s_stu_type_id = '';
var num = 0;
var num1 = 0;

var formFinishFlag = false;
$(function(){
	
    $(".switchTable tr").hover(
        function(){
            $(this).children("td:last-child").children("a").show();
            $(this).siblings().children("td:last-child").children("a").hide();
        }
    );
    $('.ulList span').click(function() {
        var i = $(this).index();//�±��һ��д��
        $(this).addClass('clicked').siblings().removeClass('clicked');
        $('#cont li').eq(i).addClass("show").siblings().removeClass("show");
    });

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
	stuId = Request['stuId']; 
	train_dept_id = Request['train_dept_id']; 
	s_stu_type_id = Request['s_stu_type_id'];
	var  stuName = Request['stuName'];
	$("#name").text(stuName);
	var  typeName = Request['typeName'];
	$("#stuType").text(typeName);
	var  lastDay = Request['lastDay'];
	$("#lastDay").text(lastDay);
	var  completion_rate = Request['completion_rate'];
	$("#completion_rate").text(completion_rate+'%');
	var  stuTypeId = Request['stuTypeId'];
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
    		s_user_auth_id:stuId,
    		s_orga_id:train_dept_id,
    		s_stu_type_id:s_stu_type_id,
    		flag_teacher:1
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
    		$("#formName").html(str);
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
    		s_user_auth_id:stuId,
    		s_orga_id:train_dept_id
    	},
    	dataType: 'json',
    	success:function(res) {
    		data = res.rows;
    		var str = '';
    		if(data.length > 0){
    			for (var i = 0; i < data.length; i++) {
    				str += '<div class="panel panel-default">';
    				if(i == data.length-1){
    					str += '<div class="panel-heading panel-heading-color">';
    				}else{
    					str += '<div class="panel-heading">';
    				}
        			str += 
        				'<b class="title">第' + data[i].pub_num + '次审核记录</b>' +
        				'<a class="arr arrup"></a>' +
        			'</div>';
        			if(i == data.length-1){
    					str += '<div class="panel-body">';
    				}else{
    					str += '<div class="panel-body grayTitle" style="display: none;background: #f5f5f5;">';
    				}
        			 str += 
        				'<div class="about4">' +
        					'<div class="lTitleDiv">' +
        						'<div class="hr"></div>' +
        						'<h5 class="lTitle">审批流程</h5>' +
        					'</div>' +
        					'<div class="process_top">' +
        						'<table><tbody><tr>' + flowChartInit(data[i].pub_num) + '</tr></tbody></table>' +
        					'</div>';
		        			 if(pageInto(data[i].pub_num) != ''){
			 			 			str += 
			     			 			'<div class="lTitleDiv">' +
			     			 				'<div class="hr"></div>' +
			     			 				'<h5 class="lTitle">审批记录</h5>' +
			     			 			'</div>' +
			     			 			'<div class="about4_main">' +
			     			 				'<div class="line">' +
			     			 					'<img src="' + ctx + '/jsp/web/score/images/stepblarrow.png" alt=""/>' +
			     			 				'</div>' +
			     			 				'<ul>' + pageInto(data[i].pub_num) + '</ul>' +
			     			 			'</div>';
			 			 	}
		        		str +=
        				'</div>' +
        			'</div>' +
        		'</div>';
    			}
    		}
//    	  	alert(str);
    	  	$(".progressDiv").html(str);
    	  	
//    	  	$(".process_top table").width(1000);
//    	  	var tWidth=$(".process_top table").width();
//    	  	$(".finish_td").width(100);
//    	    var tdsWidth=parseInt(tWidth-$(".finish_td").width());
//    	    console.log(tdsWidth);
//    	    if($(".process_top table tr td").size() == 4){
//    	        $(".iWidth").width(tdsWidth/3-35);
//    	    }else if($(".process_top table tr td").size() == 5){
//    	        $(".iWidth").width(tdsWidth/4-18);
//    	    }
    	    
    	    $('.progressDiv').each(function(){
	  			$(this).find('.process_top table').width(1000);
//	  			$(".process_top table").width(lWidth);
	  			var tWidth=$(this).find(".process_top table").width();
	  			$(this).find(".finish_td").width(100);
	  			var tdsWidth=tWidth-$(this).find(".finish_td").width();
	  			console.log(tdsWidth);
	  			$(this).find('.process_top table').each(function(){
//	  				alert($(this).find("tr td").size());
	  				if($(this).find("tr td").size() == 4){
	  					$(this).find(".iWidth").width(tdsWidth/3-13);
	  				}else if($(this).find("tr td").size() == 5){
	  					$(this).find(".iWidth").width(tdsWidth/4-18);
	  				}else if($(this).find("tr td").size() ==3){
	  					$(this).find(".iWidth").width(tdsWidth/2-6);
	  				}
	  			});
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
    		s_user_auth_id : stuId,
    		s_orga_id : train_dept_id
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

//初始化
function pageInto(pub_num){
	var lis="" ;
	$.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/getStuInfor.action', 
    	data : {
			stuId:stuId,
			pub_num:pub_num,
			s_orga_id : train_dept_id,
			stuTypeId : s_stu_type_id
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
    			num1 = i + 1;
    			if(i==0){
    				var formRoleStr="";
    				for ( var int = 0; int < res.listStu.length; int++) {					
//    					if(int==res.listStu.length-1){
//    						formRoleStr+="<a onclick='formManage("+res.listStu[int].form_id+",10,"+res.listStu[int].create_auth_id+","+pub_num+")'>"+res.listStu[int].form_name+"</a>&nbsp;&nbsp;";
//    					}else{
//    						formRoleStr+="<a onclick='formManage("+res.listStu[int].form_id+",10,"+res.listStu[int].create_auth_id+","+pub_num+")'>"+res.listStu[int].form_name+"</a>&nbsp;；&nbsp;";
//    					}
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
//    						"<br/><strong>完成表单</strong>："+formRoleStr+
    						"</i></li>";
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
//							"<br/><strong>完成表单</strong>："+formRoleStr+
						lis+="</i></li>";
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
    				lis+="<br/><strong>完成表单</strong>："+formRoleStr+"</i></li>";
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
    				lis+="</i></li>";
    			}
    		}
//    		$("#ulID").html(lis);
    	}
    });
	return lis;
}

//重载
function loadAgain(){
	formFinishFlag = false;
	formListInit();
	layer.closeAll();
}

//表单配置填写前的展示
function formManage(flag_read,create_auth_id,form_id,form_name,type_id,flag,sfm_id){//  flag_read:从流程记录处触发的标识(-10：表单列表、10：审核记录)、create_auth_id：创建人id、flag：编辑标识
	if(form_id == null || form_id == ''){
		myMsg("请选择表单！",'50%','50%');
		return false;
	}
	//判断是评分表单还是普通表单
	if(type_id==1){//  评分表单
		url = ctx+"/jsp/web/score/formWithMark.jsp?flag_read="+flag_read+"&type_id="+type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+stuId+"&s_orga_id="+train_dept_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
		mypopdiv(2,"表单信息",'1200px',(pHeight-80)+'px','','','N',url);
	}else if(type_id==2){//  普通表单
		if(flag_read == 10){//  已经出科，从流程图处触发的 
			showHtmlForm(form_id,create_auth_id,sfm_id);
		}else{//  未出科
			url = ctx+"/jsp/web/score/formWithHtml.jsp?form_name="+form_name+"&type_id="+type_id+"&form_id="+form_id+"&flag="+flag+"&s_user_auth_id="+stuId+"&s_orga_id="+train_dept_id+"&create_auth_id="+create_auth_id+"&sfm_id="+sfm_id;
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
    		s_user_auth_id:stuId,
    		s_orga_id:train_dept_id,
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
			mypopdiv(1,"表单信息",'1150px',(pHeight-100)+'px','','','N',content);
    	}
	});
}




//出科
function goGrade(exam_result){
	var form_id_str = '';
	var EXAM_CONTENT = '';
	if(formFinishFlag && exam_result == 1){
		myalert_error("有表单未填写，不能进行出科审核！");
		return false;
	}
	if(exam_result == 1){
		$(".hidden_form_id").each(function(){
			form_id_str += $(this).val() + '-';
		});
		EXAM_CONTENT = $("#textId").val();
//		if($("#textId").val()==''){
//			myMsg("请输入审批意见!",'50%','50%');
//			return false;
//		}
	}else if(exam_result == -1){
		EXAM_CONTENT = $("#exam_content").val();
//		if($("#exam_content").val()==''){
//			myMsg("请输入审批意见!",'50%','50%');
//			return false;
//		}
		$(".hidden_form_id").each(function(){
			form_id_str += $(this).val() + '-';
		});
	}
//	$(".hidden_form_id").each(function(){
//		form_id_str += $(this).val() + '-';
//	});
//	if($("#textId").val()==''){
//		myMsg("请输入审批意见!",'50%','50%');
//		return false;
//	}
	var postData={};
	if(typeof(form_id_str) == "undefined" || form_id_str == ""){
		postData={
				stuId:stuId,
				EXAM_CONTENT:EXAM_CONTENT,
				s_orga_id:train_dept_id,
				exam_result:exam_result
				}; 
	}else{
		postData={
				stuId:stuId,
				EXAM_CONTENT:EXAM_CONTENT,
				form_id_str:form_id_str,
				s_orga_id:train_dept_id,
				exam_result:exam_result
				}; 
	}
 var url=ctx+"/scoreweb/beGrAdue.action";
	doAjax(url,postData,2,function success(res){
		parent.closeMyWindows(res);
	});		
}
//驳回出科申请
function turnDown(exam_result){
	var content = 
		'<textarea name="exam_content" id="exam_content" style="width:90%;height:75%;resize: none;position: absolute;top: 0;left: 50%;transform: translate(-50%, 0);margin-top:10px"></textarea>' +
		'<div style="width:100px;margin: 0 auto;margin-top:10px;">' + 
			'<button class="layui-layer-btn0" onclick="goGrade(' + exam_result + ');" style="margin-top:300px">确定</button>' +
		'</div>';
	mypopdiv(1,"驳回原因",'500px','400px','','','N',content);
}

function  openForm(a){
	parent. openForm(a);
}
