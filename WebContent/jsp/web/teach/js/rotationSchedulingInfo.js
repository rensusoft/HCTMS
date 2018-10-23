$(function(){

    init();
});

function init(){
	//添加加载层
	var divPop = myLoading();
	$.ajax({
  		type: 'POST',
  		url : ctx+'/teachweb/getStudentList.action',
  		data:{
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		data = eval(data.rows);
			if(data.length>0){
				$("#stuTranPlanTit").html("");
				$("#stuTranPlan").html("");
				var str = '';
				for(var i=1;i<52;i++){
					str += '<th>第'+i+'周</th>';
				}
				var str1 = '<tr><th></th>'+str+'</tr>';
				$("#stuTranPlanTit").html(str1);
	 			var content ='';
	  	 		for(var i=0;i<data.length;i++){
//	  	 			content +='<tr>'+
//			  	 	            '<td>'+data[i].stu_name+'</td>'+//<input type="hidden" value="'+data[i].gid+'" id="gid'+data[i].gid+'" />
//			  	 	            '<td colspan="51">'+tranPlan(data[i].gid)+'</td>'+
//			  	 	        '</tr>';
	  	 			content +="<tr><th>"+data[i].stu_name+"</th>"+tranPlan(data[i].stu_auth_id)+"</tr>";
//	  	 			content +='<tr><th>第'+data[i].group_no+'组</th>'+tranPlan(data[i].id)+'</tr>';
	  	 		}
	  	 		//去掉加载层
				closeMyLoading(divPop);
//	  	 		alert(content);
	  	 		$("#stuTranPlan").html(content);
			}else{
				//去掉加载层
				closeMyLoading(divPop);
				$("#stuTranPlanTit").html("");
				$("#stuTranPlan").html("");
				if($("#role_code").text() != "R_SYS_SCHOOL"){
					$(".timeAxisDiv>table").attr("style","border: 0px;");
					var str2 = '<div style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">'+
					'<span class="glyphicon glyphicon-exclamation-sign" style="font-size: 35px"></span>目前暂无数据</div>';
					$("#stuTranPlan").html(str2);
				}
			}
     	}
	});
    $('[data-toggle="tooltip"]').tooltip();
    var colors=["#3399ff","#3366ff","#3333ff","#3300ff","#0000CC","#0033cc","#0000cc","#000099","#0000AA","#0044BB","#003C9D"];
//    var colors = ["#054889","#A0C7E6","#80E6EA","#3286CB","#6A9CB8","#208FE1","#1466AD","#7DB3EB","#627493","#4D9DFC","#4B7198"];
    $('.divPart').find('td').each(function(index){
    	$(this).css('background', colors[index%11]);
    });
    if($("#role_code").text() == "R_SYS_SCHOOL"){
    	$(".timeAxisDiv").height(pHeight-100);
    	if(pHeight>700){
    		$(".timeAxisTable").attr("style","margin-top:42px;table-layout:fixed;");
    		
    	}else{
    		$(".timeAxisTable").attr("style","margin-top:55px;table-layout:fixed;");
    	}
    	$(".timeAxisTableTit").attr("style","position: fixed;background: #fff;top:45px;padding-top: 10px;");
    }else{
    	$(".timeAxisDiv").height(pHeight-100);
    	$(".timeAxisTableTit").attr("style","position: fixed;background: #fff;padding-top: 10px;");
    	if(pHeight>700){
    		$(".timeAxisTable").attr("style","margin-top:45px;table-layout:fixed;");
    	}else{
    		$(".timeAxisTable").attr("style","margin-top:70px;table-layout:fixed;");
    	}
    }
    $("#stuTranPlanTit").width($("#stuTranPlan").width());
}

function tranPlan(stu_auth_id){
	var str='';
	$.ajax({
  		type: 'POST',
  		url :  ctx+'/teachweb/getStuTrainPlan.action',
  		data:{
  			stu_auth_id:stu_auth_id,
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		data = eval(data.rows);
//  	 		str='<div class="divPart"><ul>';
//			if(data.length>0){
//	  	 		for(var i=0;i<data.length;i++){
//	  	 			var days = data[i].days;
//	  	 			if(parseInt(days)>0){
//	  	 				str += '<li style="width: '+days/7*100+'px;" data-toggle="tooltip" data-placement="top" title="'+data[i].train_start_date+'至'+data[i].train_end_date+'">'+data[i].orga_name+'</li>';
//	  	 			}
//	 			}
//  	 		}
//			str +='</ul></div>';
  	 		str +='<td colspan="51">';
			if(data.length>0){
//				$("#isCreateTranPlan",window.parent.parent.document).val(1);
				str +='<table class="divPart"><tr>';
	  	 		for(var i=0;i<data.length;i++){
	  	 			var days = data[i].days;
	  	 			if(parseInt(days)>0){
	  	 				str += '<td style="width: '+parseInt(days/7)*1.9+'%;" colspan="'+parseInt(days/7)+'" title="'+data[i].train_start_date+'至'+data[i].train_end_date+'">'+data[i].orga_name+'</td>';
	  	 			}
	 			}
	  	 		str +='</tr></table>';
  	 		}
			str+='</td>'
     	}
	});
	return str;
}
////获取医院下拉项及其完成轮转排班的学生数
//function getHospitalListAndCounts(){
//	//添加加载层
//	var divPop = myLoading();
//	//拼接医院下拉框
//	$.ajax({
//		url:ctx+'/teachweb/getHospitalListAndCounts.action',
//		async:false,
//		data:{
//			
//		}, 
//		dataType: 'json',
//		success:function(res) {
//			 //去掉加载层
//			 closeMyLoading(divPop);
//			 var str = '';
//			 for(var i = 0;i < res.data.length; i++){
//				 var student_count = '';
//				 if((res.data)[i].student_count != 0){
//					 student_count = " (" + (res.data)[i].student_count + ")";
//				 }
//				 str += '<option class="opt_hos" value="' + (res.data)[i].id + '">' + (res.data)[i].hos_name + student_count
//				 	'</option>';
//			 }
////			 alert(str);
//			 $("#sel_hospital").html(str);
//			 
//		}
//	});
//}
////医院下拉框变化
//function hospitalChange(){
//	init();
//}

function exportStuTrainPlan(){
	$("#exportForm").submit();
}