$(function(){
	var qeid = getUrlParam("qeid");
	if(qeid!=""&&qeid!=null){
		initStuExerKnowlwdge(qeid);
	}
})
function initStuExerKnowlwdge(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/exerweb/getExerKnowledgeById.action',
		dataType: 'json',
		async:false, 
		data:{
			qeid:id
		},
		success:function(data) {
			if(data.success){
				var str='';
   	      		data = eval(data.data);
   	      		for(var i=0;i<data.length; i++){
   	      			str += initKnowlwdge(i+1,data[i].qkb_id,data[i].proportion);
   	      		}
   	      		$("#optList").html(str);
			}
		}
	});
}
function initKnowlwdge(i,id,proportion){
	var str ='';
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQeknowledgeById.action',
		dataType: 'json',
		async:false, 
		data:{
			id:id
		},
		success:function(data) {
			if(data.data!=null){
   	      		data = data.data;
      			str = '<tr>'+
   	                    '<td>'+i+'.'+data.name+'</td>'+
   	                    '<td>'+proportion+'%</td>'+
   	                '</tr>';
			}
		}
	});
	return str;
}