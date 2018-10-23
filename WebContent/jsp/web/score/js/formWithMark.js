var score=0;
var form_id='';
var type_id='';
var flag_read='';
var flag='';
var s_user_auth_id='';
var s_orga_id='';
var create_auth_id='';
var sfm_id = '';
$(function(){
	flag_read = getUrlParam("flag_read");
	form_id =getUrlParam("form_id");
	type_id =getUrlParam("type_id");
	flag =getUrlParam("flag");
	s_user_auth_id =getUrlParam("s_user_auth_id");
	s_orga_id =getUrlParam("s_orga_id");
	create_auth_id =getUrlParam("create_auth_id");
	sfm_id =getUrlParam("sfm_id");
	if(flag_read == 10){//  已经出科，从流程图处触发的
		$(".btns").css("display","none");
	}
	//
	initMain();
});

function initMain(){
	var url = '';
	if(flag_read == 10){
		url = ctx+'/basicdataweb/getFormInfoByIdFromSFM.action';
	}else if(flag_read == -10){
		if(flag == -1){
			url = ctx+'/basicdataweb/getFormInfoById.action';
		}else{
			url = ctx+'/basicdataweb/getFormInfoByIdFromSFM.action';
			
		}
	}
	$.ajax({
		type: 'POST',
		url :  url,
		dataType: 'json',
		async: false,
		data:{
			id:form_id,
			flag:flag,
			flag_read:flag_read,
			s_user_auth_id:s_user_auth_id,
			s_orga_id:s_orga_id,
			create_auth_id:create_auth_id,
			sfm_id:sfm_id
		},
		success:function(data) {
			data = data.data;
			$("#name").text(data.name);
			var content ='<p>评分表类型：<span>评分表单</span></p>'+
						'<p>撰写者： <span>'+data.user_name+'</span></p>'+
						'<p>状态：<span>'+data.form_state+'</span></p>';
						if(flag_read == 10){
							content += '<p>总分：<span id="score">'+data.form_sco+'</span></p>' + 
							'<input type="hidden" value="' + data.get_sco + '" id="hidden_get_sco"/>';
						}else if(flag_read == -10){
							if(flag == -1){
								content += '<p>总分：<span id="score">'+data.total_sconum+'</span></p>';
							}else{
								content += '<p>总分：<span id="score">'+data.form_sco+'</span></p>' + 
								'<input type="hidden" value="' + data.get_sco + '" id="hidden_get_sco"/>';
							}
						}
			var id = data.related_id;
//			alert(content);
			$("#main").html(content);
			if(flag_read == 10){
				initScoFormSub(form_id);
			}else if(flag_read == -10){
				if(flag == -1){//  评分表单未编辑过，从配置表里取数据，展示列表
					initSub(id);
				}else if(flag == 1){//  评分表单编辑过，从数据表里取数据
					initScoFormSub(form_id);
				}
			}
		}
	});
}
//从配置表里取数据，初始化表单各项
function initSub(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/queryMarksheetSubList.action',
		dataType: 'json',
		async: false,
		data:{
			mmid:id
		},
		success:function(data) {
			data = data.rows;
			var content ="";
			if(data.length>0){
				var msid=0;
				for(var i=0;i<data.length;i++){
					if(data[i].type_code==0){
						msid = data[i].id;
						content+='<tr>'+
									'<td colspan="4">'+
										'<strong>'+data[i].title+'</strong><input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'" min="0" max="100" />'+
									'</td>'+
								'</tr>';
					}else if(data[i].ms_id==msid){
						var str =initDetail(data[i].id,data[i].item_type_code);
						content+='<tr class="tr_sub_title">'+
									'<td>'+data[i].sort_num+'.'+data[i].title+'<input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'" class="hidden_mmid" /></td>'+
									'<td style="width:300px;">'+data[i].text+'<input type="hidden" value="'+data[i].item_type_code+'" id="item_type_code'+data[i].id+'" class="hidden_item_type_code" /></td>'+
									str+
								'</tr>';
					}
				}
			}
			content+='<tr>'+
						'<td><strong>总分</strong></td>'+
						'<td>'+$("#score").html()+'</td>'+
						'<td><strong>得分</strong></td>'+
						'<td class="td_get_sco">0</td>'+
					'</tr>';
			$("#sub").html(content);
			$(".spinner").spinner().spinner( "value", 0 );
			
			$( ".spinner" ).spinner({
				  change: function( event, ui ) {
					  if(parseInt($(this).val()) > parseInt($(this).parent().parent().prev(".standard_sco_num").html())){
							myMsg("每项评分不可大于其分值！",'50%','50%');
					  };
					  countScore();
				  }
			});
		}
	});
}
//从存放数据的表里取数据，初始化评分表单分项列表
function initScoFormSub(form_id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/scoreweb/queryScoFormSubList.action',
		dataType: 'json',
		async: false,
		data:{
			form_id:form_id,
			s_user_auth_id:s_user_auth_id,
			s_orga_id:s_orga_id,
			create_auth_id:create_auth_id,
			sfm_id:sfm_id
		},
		success:function(data) {
			data = data.rows;
			var content ="";
			if(data.length>0){
				var sfsid=0;
				for(var i=0;i<data.length;i++){
					if(data[i].type_code==0){
						sfsid = data[i].id;
						content+='<tr>'+
									'<td colspan="4">'+
										'<strong>'+data[i].title+'</strong><input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'" />'+
									'</td>'+
								'</tr>';
					}else if(data[i].sfs_id==sfsid){//      缺少个sfs_id字段
						var str =initScoFormDetail(data[i].id,data[i].item_type_code);
						content+='<tr class="tr_sub_title">'+
									'<td>'+data[i].sort_num+'.'+data[i].title+'<input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'" class="hidden_mmid" /></td>'+
									'<td style="width:300px;">'+data[i].text+'<input type="hidden" value="'+data[i].item_type_code+'" id="item_type_code'+data[i].id+'" class="hidden_item_type_code" /></td>'+
									str+
								'</tr>';
					}
				}
			}
			content+='<tr>'+
						'<td><strong>总分</strong></td>'+
						'<td>'+$("#score").html()+'</td>'+
						'<td><strong>得分</strong></td>'+
						'<td class="td_get_sco">'+$("#hidden_get_sco").val()+'</td>'+
					'</tr>';
			$("#sub").html(content);
			//
			initData();
			
			$( ".spinner" ).spinner({
				  change: function( event, ui ) {
						if(parseInt($(this).val()) > parseInt($(this).parent().parent().prev(".standard_sco_num").html())){
							myMsg("每项评分不可大于其分值！",'50%','50%');
						}
					  countScore();
				  }
			});
//			$(".spinner").spinner().change(function(e, newVal, oldVal){
//				alert("jinlai");
//				countScore();
//			});
//			$(".spinner").spinner('changed', function(e, newVal, oldVal){
//				//spinner被改变的回调函数
//				alert("jinlai");
//				});
		}
	});
}
//从配置表里取数据，初始化表单各项的打分方式
function initDetail(id,type){
	var str="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/queryMarksheetDetailList.action',
		dataType: 'json',
		async: false,
		data:{
			msid:id
		},
		success:function(data) {
			data = data.rows;
			if(data.length>0){
				score = data[0].sco_num;
				str += '<td style="width:80px;" class="standard_sco_num">'+score+'</td>';
				str += '<td style="width:280px;" class="fact_sco_num">';
				for(var i=0;i<data.length;i++){
					if(type=="NUM"){
						str += '<input class="spinner" min="0" max="100" name="value" onKeyPress="return verifyDigital(event);" id="numStyle'+id+'">';
					}
					if(type=="YESNO"){
						str += '<input type="radio" name="boolStyle'+id+'" value="'+data[i].sco_num+'" oninput="countScore()" onpropertychange="countScore()">' + data[i].text + '[' + data[i].sco_num + ']'; 
					}
					if(type=="PROPORTION"){
						str += '<input type="radio" name="increased'+id+'" value="'+data[i].sco_num+'" oninput="countScore()" onpropertychange="countScore()"> '+ data[i].text + '[' +data[i].sco_num+ ']';
					}
				}
				str += '</td>';
			}
		}
	});
	return str;
}
//从存放数据的表里取数据，初始化表单各项的打分方式及分数
function initScoFormDetail(id,type){
	var str="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/scoreweb/queryScoFormDetailList.action',
		dataType: 'json',
		async: false,
		data:{
			sfs_id:id
		},
		success:function(data) {
			data = data.rows;
			if(data.length>0){
				score = data[0].sco_num;
				str += '<td style="width:80px;" class="standard_sco_num">'+score+'</td>';
				str += '<td style="width:280px;" class="fact_sco_num">';
				for(var i=0;i<data.length;i++){
					if(type=="NUM"){
						str += '<input class="spinner" name="value" id="numStyle'+id+'">';
					}
					if(type=="YESNO"){
						str += '<input type="radio" name="boolStyle'+id+'" value="'+data[i].sco_num+'" oninput="countScore()" onpropertychange="countScore()">' + data[i].text + '[' + data[i].sco_num + ']'; 
					}
					if(type=="PROPORTION"){
						str += '<input type="radio" name="increased'+id+'" value="'+data[i].sco_num+'" oninput="countScore()" onpropertychange="countScore()"> '+ data[i].text + '[' +data[i].sco_num+ ']';
					}
				}
				str += '</td>';
			}
		}
	});
	return str;
}
//初始化数据
function initData(){
	$.ajax({
		type: 'POST',
		url :  ctx+'/scoreweb/queryMarksheetData.action',
		dataType: 'json',
		async: false,
		data:{
			form_id:form_id,
			s_user_auth_id:s_user_auth_id,
			s_orga_id:s_orga_id,
			create_auth_id:create_auth_id,
			sfm_id:sfm_id
		},
		success:function(res) {
			for(var i=0;i<res.data.length;i++){
				if(res.data[i].item_type_code=="NUM"){
					if(flag_read == 10){
						$("#numStyle" + res.data[i].id).parent(".fact_sco_num").html(res.data[i].sco_num);
					}else{
						$("#numStyle" + res.data[i].id).spinner().spinner( "value", res.data[i].sco_num );
					}
				}
				if(res.data[i].item_type_code=="YESNO"){
					if(flag_read == 10){
						$(":radio[name=boolStyle" + res.data[i].id + "]").parent(".fact_sco_num").html(res.data[i].sco_num);
					}else{
						$(":radio[name=boolStyle" + res.data[i].id + "][value='" + res.data[i].sco_num + "']").attr("checked","true");
					}
				}
				if(res.data[i].item_type_code=="PROPORTION"){
					if(flag_read == 10){
						$(":radio[name=increased" + res.data[i].id + "]").parent(".fact_sco_num").html(res.data[i].sco_num);
					}else{
						$(":radio[name=increased" + res.data[i].id + "][value='" + res.data[i].sco_num + "']").attr("checked","true");
					}
				}
			}
		}
	});
}
//实时获取分数
function countScore(){
	var get_score = 0;//  总得分
	$(".spinner").each(function(){
		get_score += parseInt($(this).val());
	});
	$('.tr_sub_title').each(function(){
		if(typeof($(this).find('input[type="radio"]:checked').val()) != "undefined"){
			get_score += parseInt($(this).find('input[type="radio"]:checked').val());
		}
	});
	$(".td_get_sco").html(get_score);
}
//保存评分表单数据
function saveData(){
	var result = true;
	var dataType = '';
	if(type_id != null && type_id == 1){
		dataType = 'mark';
	}else if(type_id != null && type_id == 2){
		dataType = 'html';
	}
	var get_sco = 0;//  总得分
	$(".spinner").each(function(){
		get_sco += parseInt($(this).val());
	});
	$('.tr_sub_title').each(function(){
		if(typeof($(this).find('input[type="radio"]:checked').val()) != "undefined"){
			get_sco += parseInt($(this).find('input[type="radio"]:checked').val());
		}
	});
//		alert(get_sco);
	$('.tr_sub_title').each(function(){
		if(typeof($(this).find('.standard_sco_num').html()) != "undefined"){
			if(parseInt($(this).find(".spinner").val()) > parseInt($(this).find(".standard_sco_num").html())){
				myMsg("每项评分不可大于其分值！",'50%','50%');
				result = false;
			}
		}
	});
//	myConfirm("确定保存表单？","","",function(){//  保存不用提示【确定~~~~~】
		var id_score_str_array = new Array();
		var id_score_str = '';
		var sco_num = '';
		$(".tr_sub_title").each(function(){
			if($(this).find('.hidden_item_type_code').val() == 'NUM') {
				sco_num = $(this).find(".spinner").val();
			}else{
				sco_num = $(this).find('input[type="radio"]:checked').val();
			}
			if(typeof(sco_num) == "undefined" || sco_num == ''){
				myMsg("请给每个评分项打分！",'50%','50%');
				result = false;
			}
			id_score_str = $(this).find('.hidden_mmid').val() + "-" + sco_num;
			id_score_str_array.push(id_score_str);
		});
//			alert(JSON.stringify(id_score_str_array));
		if(result){
			$.ajax({
				type: 'POST',
				url :  ctx+'/scoreweb/stuIndept.action',
				dataType: 'json',
				async: false,
				data:{
					dataType:dataType,
					form_id:form_id,
					form_name:$("#name").text(),
					form_sco:$("#score").text(),
					get_sco:get_sco,
					s_user_auth_id:s_user_auth_id,
					s_orga_id:s_orga_id,
					flag:flag,
					id_score_str_array:JSON.stringify(id_score_str_array),
					sfm_id:sfm_id
				},
				success:function(data) {
					if(data.success){
						myalert_success(data.data,"","",function(){
							parent.loadAgain();
						});
					}else{
						myalert_error(data.data,"","");
					}
				}
			});
		}
//	});
}