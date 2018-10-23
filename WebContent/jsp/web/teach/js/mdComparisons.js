var id='';
var type_id='';
$(function () {
	id = getUrlParam('id');
	type_id = getUrlParam('type_id');
	comparison();
});
//对比查看
function comparison(){
	$.ajax({
	  	type: 'POST',
		url: ctx + "/teachweb/makeComparison.action",
		data: {
			id : id,
			type_id : type_id
		},
		dataType: 'json',
	  	success:function(res){
	  		var compareContent = res.compareContent;
	  		$("#div_compareContent").html(compareContent);
	  	}
	});
}
