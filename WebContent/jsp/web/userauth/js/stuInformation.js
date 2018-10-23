$(function(){
	 $.ajax({
		  	type: 'POST',
			url : ctx + "/userauthweb/selectStuInfomation.action",
			data : {
	            userCode:$("#userCode").val()
				},
			dataType: 'json',
		  	success:function(data) {
			  $("#user_code").val(data.user_code);
			  $("#stu_name").val(data.stu_name);
			  $("#stu_sex").val(data.stu_sex);
			  $("#stu_age").val(data.stu_age);
			  $("#stu_school_name").val(data.stu_school_name);
			  $("#stu_num").val(data.stu_num);
			  $("#stu_edu_level").val(data.stu_edu_level);
			  $("#stu_major_name").val(data.stu_major_name);
			  $("#stu_type").val(data.stu_type);
			  $("#sup_doc_name").val(data.sup_doc_name);
			  $("#creator").val(data.creator);
			  $("#remark").val(data.remark);
			  $("#stu_birthday").val(data.stu_birthday);
			  $("#stu_country").val(data.stu_country);
			  $("#stu_nationality").val(data.stu_nationality);
			  $("#stu_native").val(data.stu_native);
			  $("#stu_phone").val(data.stu_phone);
			  $("#stu_hk_address").val(data.stu_hk_address);
			  $("#stu_home_address").val(data.stu_home_address);
			  $("#stu_class").val(data.stu_class);
			  $("#political_status").val(data.political_status);
			  $("#interesting_speciality").val(data.interesting_speciality);
			  $("#stu_position").val(data.stu_position);  
			  $("#stu_major_direction").val(data.stu_major_direction);  
			  $("#tutor_workplace").val(data.tutor_workplace);  
			  $("#tutor_name").val(data.tutor_name);  
			  $("#stu_edu_level_code").val(data.stu_edu_level_code);  
			  $("#stu_status").val(data.stu_status);  
		 }
		
	});	
});
