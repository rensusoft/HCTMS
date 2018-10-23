var index=0;
$(function(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/getTrainSchemeList.action',
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		data = eval(data.rows);
			if(data.length>0){
				var str ='';
				for (var i=0;i<data.length;i++){
					str += '<p><i><input type="hidden" id="plan'+data[i].id+'" value="'+data[i].id+'" title="'+data[i].name+'" /></i><span style="float: left;">'+data[i].name+'</span> <a class="main" onclick="lookInfo('+data[i].id+')">查看详情>></a></p>';
				}
				$("#planChoose").html(str);
			}
     	}
	});
    var planId = $("#planConfig",window.parent.document).val();
	if(planId!=""){
		$('.radioGroup>p').each(function(i){
			if($(this).find('input').val()==planId){
				index = planId;
				$(this).addClass("tick").siblings().removeClass("tick");
			}
		});
	}else{
		$(".radioGroup>p:eq(0)").addClass("tick").siblings().removeClass("tick");
		index = $(".radioGroup>p:eq(0)").find('input').val();
	}
    $(".radioGroup>p").click(function () {
        $(this).addClass("tick").siblings().removeClass("tick");
	    	index = $(this).find('input').val();
	    }
    );
});

function lookInfo(id){
	parent.popdiv(2,"轮转方案详情查看",'Y',ctx+"/jsp/web/teach/planMain.jsp?id="+id);
}

function checkPlan(){
	$("#planConfig",window.parent.document).val($("#plan"+index).val());
	$("#planConfig",window.parent.document).text($("#plan"+index).attr("title"));
}