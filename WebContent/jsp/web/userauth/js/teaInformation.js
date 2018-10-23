$(function(){
	 $.ajax({
		  	type: 'POST',
			url : ctx + "/userauthweb/selectTeaInfomation.action",
			data : {
	            userCode:$("#userCode").val()
				},
			dataType: 'json',
		  	success:function(data) {
			  $("#user_code").val(data.user_code);
			  $("#name").val(data.name);
			  $("#age").val(data.age);
			  $("#birth").val(data.birth);
			  $("#staff_native").val(data.staff_native);
			  $("#birth_place").val(data.birth_place);
			  $("#professional_title").val(data.professional_title);
			  $("#position").val(data.position);
			  $("#political_state").val(data.political_state);
			  $("#edu_level").val(data.edu_level);
		 }
		
	});	
});
