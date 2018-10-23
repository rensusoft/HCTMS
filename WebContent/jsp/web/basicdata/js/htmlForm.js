
$(function(){
	var id =getUrlParam("id");
	red(id);
});

function red(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getFormInfoById.action',
		dataType: 'json',
		data:{
			id:id
		},
		async: false,
		success:function(data) {
			data = data.data;
			  var content =
			    '<div style="text-align:center;">'+
				'<h3 id="title">'+data.name+'</h3>'+
			    '</div>'+
			    '<div style="font-size: 14px;text-align:right;color:#AAAAAA;">'+
			    '<span style=" float:left;">--表单类型：普通表单</span>'+
			    '<span style="margin-right:27%">--发布人：'+data.user_name+'</span>'+
			    '<span style=" float:right;margin-right:5px">--发布时间：'+data.time+'</span>'+
			    '</div><hr/>'+
			    '<div>'+data.t_content+'</div>';
		      $("#formInfo").html(content);  
		    
		}
	});
}
