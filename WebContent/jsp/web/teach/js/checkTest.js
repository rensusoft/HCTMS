$(function(){
    $(".leftBtn").click(
        function(){
            location.href = ctx+"/jsp/web/teach/testlibrary.jsp";
        }
    );
	var qeid = getUrlParam("qeid");
	if(qeid!=""&&qeid!=null){
		$("#qeid").val(qeid);
		initStuExer(qeid);
		initStuQues(qeid);
	}
	
    $(".knowPoint").click(
        function(){
            layer.open({
                type: 2,
                title:"知识点比重查看",
                content: ctx+"/jsp/web/teach/knowPoint.jsp?qeid="+qeid,
                area: ['250px', '200px']
            });
        }
    );
})
function initStuExer(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/exerweb/getExerInfoById.action',
		dataType: 'json',
		async:false, 
		data:{
			id:id
		},
		success:function(data) {
   	      	if(data.data!=null){
   	      		data = data.data;
   	      		var str = '<tr>'+
			                    '<td>练习时间：</td>'+
			                    '<td>'+data.time+'</td>'+
			                    '<td>知识点：</td>'+
			                    '<td colspan="3"><a class="knowPoint">查看>></a></td>'+
			                '</tr>'+
			                '<tr>'+
			                    '<td>总题数：</td>'+
			                    '<td>'+data.ques_num+'</td>'+
			                    '<td>答对：</td>'+
			                    '<td>'+data.answer+'</td>'+
			                    '<td>答错：</td>'+
			                    '<td>'+(data.ques_num-data.answer)+'</td>'+
			                '</tr>';
   	      		$("#queConten").html(str);
   	      		$('#score').html(data.ques_sco);
			}
		}
	});
}
function initStuQues(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/exerweb/getExerQuesInfo.action',
		dataType: 'json',
		async:false, 
		data:{
			qeid:id,
			sturesult:$("#stu_result").val()
		},
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
   	 				var str ='';
	   	      		for(var i=0;i<data.length; i++){
	   	      			str += initQuesCon(i+1,data[i].qq_id,data[i].stu_answer);
	   	      		}
	   	      		$("#quesAnswCon").html(str);
	   	      	}
			}
		}
	});
}
function initQuesCon(i,id,stu_answer){
	var str = '';
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQuesInfoById.action',
		dataType: 'json',
		async:false, 
		data:{
			id:id
		},
		success:function(data) {
			if(data.data!=null){
				data = data.data;
      			str = '<div class="answProp">'+
                        '<p class="answerTitle">'+
	                        i+'.'+data.ques_content+
	                    '</p>'+
	                    '<div>'+initQuesOptCon(id,stu_answer)+'</div>'+
	                '</div>';
			}
		}
	});
	return str;
}
function initQuesOptCon(id,stu_answer){
	var anscon = '';
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getAnswerInfoByQqid.action',
		dataType: 'json',
		async:false, 
		data:{
			qid:id
		},
		success:function(data) {
   	      	if(data.data!=null){
   	      		data = data.data;
   	      		anscon = data.answer;
			}
		}
	});
	var str='';
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getOptionInfoByQqid.action',
		dataType: 'json',
		async:false, 
		data:{
			qid:id
		},
		success:function(data) {
   	      	if(data.success){
   	      		data = eval(data.data);
   	      		for(var i=0;i<data.length; i++){
   	      			if(stu_answer==anscon){
	   	      			str += '<p class="rightAnswer" id='+data[i].id+'>'+
	   	      				data[i].option_flag+'.'+data[i].option_content+
						'</p>';
   	      			}else if(data[i].id==anscon){
	   	      			str += '<p class="rightAnswer" id='+data[i].id+'>'+
     						data[i].option_flag+'.'+data[i].option_content+
 						'</p>';
   	      			}else if(data[i].id==stu_answer){
	   	      			str += '<p class="wrongAnswer" id='+data[i].id+'>'+
 						data[i].option_flag+'.'+data[i].option_content+
						'</p>';
	      			}else{
	   	      			str += '<p id='+data[i].id+'>'+
      						data[i].option_flag+'.'+data[i].option_content+
	                     '</p>';
   	      			}
   	      		}
			}
		}
	});
	return str;
}
function lookWrong(){
	if($("#stu_result").val()=="")
		$("#stu_result").val("N");
	else
		$("#stu_result").val("");
	initStuQues($("#qeid").val());
}