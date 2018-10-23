$(function () {
	pageInit();
	$(".list li").click(
        function(){
            $(this).addClass('border');
            $(this).siblings().removeClass('border');
        }
    );
	
});
function pageInit() {
	doAjax(ctx+"/basicdataweb/getTeachForm.action","",2,function (res){	
		 var text="";
		 for ( var i = 0; i < res.data.length; i++) {
			 if(res.data[i].id==1){
			text+="<li><a class='en_btn'><i><img src='"+ctx+"/jsp/web/basicdata/img/teachers.png' alt=''/></i>" +
			"<span>"+res.data[i].tf_name+"</span><span style='display:none;' class='_id'>"+res.data[i].id+"</span></a></li>";
			 $("#teachForm").html(text);
		
			 }
			 if(res.data[i].id==2){
					text+="<li><a class='en_btn'><i><img src='"+ctx+"/jsp/web/basicdata/img/checkbook.png' alt=''/></i>" +
					"<span>"+res.data[i].tf_name+"</span><span style='display:none;' class='_id'>"+res.data[i].id+"</span></a></li>";
					 $("#teachForm").html(text);				
			 }
		 };		
		 $(".en_btn").click(function(){
				var id = $(this).children("[class='_id']").text();
				if(id==2){
					$("#iframeId").attr("src",ctx+"/jsp/web/basicdata/_teachingFormat2.jsp?id="+id);	
				}else if(id==1){
					$("#iframeId").attr("src",ctx+"/jsp/web/basicdata/teachingFormat2.jsp?id="+id);
				}
				
		  });
		 
		 
		 $("#teachForm li").click(
			        function(){
			            $(this).addClass("liBackground").siblings().removeClass("liBackground");
			        }
			    );

	});
};
