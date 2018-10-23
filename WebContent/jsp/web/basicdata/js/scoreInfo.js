var score=0;
$(function(){
	var id =getUrlParam("id");
		initMain(id);	
	$(".spinner").spinner().spinner( "value", score );
});

function initMain(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getFormInfoById.action',
		dataType: 'json',
		async: false,
		data:{
			id:id
		},
		success:function(data) {
			data = data.data;
			$("#name").text(data.name);
			var content ='<p>评分表类型：<span>评分表单</span></p>'+
						'<p>撰写者： <span>'+data.user_name+'</span></p>'+
						'<p>状态：<span>'+data.availability+'</span></p>'+
						'<p>总分：<span id="score">'+data.total_sconum+'</span></p>';
			id = data.related_id;
			$("#main").html(content);
			initSub(id);
		}
	});
}

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
										'<strong>'+data[i].title+'</strong><input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'">'+
									'</td>'+
								'</tr>';
					}else if(data[i].ms_id==msid){
						var str =initDetail(data[i].id,data[i].item_type_code);
						content+='<tr>'+
									'<td>'+data[i].sort_num+'.'+data[i].title+'<input type="hidden" value="'+data[i].id+'" id="mmid'+data[i].id+'" ></td>'+
									'<td style="width:300px;">'+data[i].text+'</td>'+str+
								'</tr>';
					}
				}
			}
			content+='<tr>'+
						'<td><strong>总分</strong></td>'+
						'<td colspan="3">'+$("#score").html()+'</td>'+
					'</tr>';
			$("#sub").html(content);
		}
	});
}

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
				str += '<td style="width:80px;">'+score+'</td>';
				str += '<td style="width:280px;">';
				for(var i=0;i<data.length;i++){
					if(type=="NUM"){
						str += '<input class="spinner" name="value">';
					}
					if(type=="YESNO"){
						str += '<input type="radio" name="boolStyle'+id+'" id="boolStyle'+id+i+'" >' + data[i].text + '[' + data[i].sco_num + ']'; 
					}
					if(type=="PROPORTION"){
						str += '<input type="radio" name="increased'+id+'" id="increased'+id+i+'" > '+ data[i].text + '[' +data[i].sco_num+ ']';
					}
				}
				str += '</td>';
			}
		}
	});
	return str;
}

