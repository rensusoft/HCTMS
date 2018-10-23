$(function(){
	//页面加载完成之后执行，获取系统开关页面数据 （1：系统开关   2：教学配置）
	selectSysConfig(1);
	
    $('.ulList span').click(function() {
        $(this).addClass('clicked').siblings().removeClass('clicked');
        if($(this).text().trim() != null && $(this).text().trim() == "系统开关"){
        	selectSysConfig(1);
        }else if($(this).text().trim() != null && $(this).text().trim() == "教学配置"){
        	selectSysConfig(2);
        }
    });
//
});
//页面加载
function selectSysConfig(config_type) {
	$.ajax({
		async:false, 
	  	type: 'POST',
		url : ctx + "/configweb/selectSysConfig.action",
		data : {
			availability: 1,
        	config_type: config_type
			},
		dataType: 'json',
	  	success:function(res) {
//	  		var arr ="";
	  		var str = '<tr>'+
				  		'<th>编码</th>' +
			            '<th>开关</th>' +
			            '<th>内容</th>' +
			            '<th>说明及保存</th>' +
			          '</tr>'; 
	  		for(var i = 0; i < res.data.length; i++){
	  			str += 
	                '<tr>' +
	                    '<td class="td_data">' + 
	                    	(res.data)[i].config_num + 
	                    	'<input type="hidden" id="hidden_config_type_' + (res.data)[i].config_code + '" value="' + (res.data)[i].config_type + '"/>' + 
	                    	'<input type="hidden" id="hidden_id_' + (res.data)[i].config_code + '" value="' + (res.data)[i].id + '"/>' + 
	                    	'<input type="hidden" id="hidden_config_explain_' + (res.data)[i].config_code + '" value="' + (res.data)[i].config_explain + '"/>' +
	                    	'<input type="hidden" id="hidden_config_data_' + (res.data)[i].config_code + '" value="' + (res.data)[i].config_data + '" class="hidden_configData"/>' +
	                    '</td>' + 
	                    '<td>' +
	                        '<p>';
	                        if((res.data)[i].config_flag != null && (res.data)[i].config_flag == 1){
	                        	str += '<input class="input_button" type="checkbox" id="checkbox_config_flag_' + (res.data)[i].config_code + '" class="lcs_check" autocomplete="off" checked="checked"/>';
	                        }else if((res.data)[i].config_flag != null && (res.data)[i].config_flag == -1){
	                        	str += '<input class="input_button"  type="checkbox" id="checkbox_config_flag_' + (res.data)[i].config_code + '" class="lcs_check" autocomplete="off" />';
	                        }
	                    str += 
	                    	'</p>' +
	                    '</td>' +
	                    '<td id="td_config_content_' + (res.data)[i].config_code + '" class="config_content">' +
	                    	(res.data)[i].config_content +
	                    '</td>' +
	                    '<td>' +
	                        '<a class="layui-layer-btn1" id="' + (res.data)[i].config_code + '" onclick="viewDetail(this)">开关说明</a>' +
	                        '<a class="layui-layer-btn0" id="' + (res.data)[i].config_code + '" onclick="saveSysConfig(this)">保存</a>' +
	                    '</td>' +
	                '</tr>';
//	                    if((res.data)[i].config_code=='stu_info_config'){
//	                    	if((res.data)[i].config_data!=null)
//	                    	 arr = (res.data)[i].config_data.split(';');
//	                    }
	  		}
//	  		alert(str);
	  		$("#sysConfig").html(str);
	  		//初始化数据
	  		//  config_content的html格式为：[ <div>线程每日启动时间： <select id="thread_start_time"> ]   
	  		//简单的，格式为：[ <div>线程每日启动时间： <select id="thread_start_time"> ]
	  		$(".config_content").each(function(){
				var id = $(this).children(":first").children(":first").attr("id");
				if(typeof(id) != "undefined"){
		  			initializeData();
		  		}else{//  复杂的（复选框），格式为：[ <div><input name="stu_info" value="stu_sex" type="checkbox">性别  ],没有用config_code做id
		  		//stu_info_config复选框初始状态未选中  学生登入是检测填写的基本信息是否完善
//			  		if(arr!=""){
//		            	for(var j=0;j<arr.length;j++){
//		            		$("input:checkbox[value='"+arr[j]+"']").prop('checked',true);
//		            	}
//			  		}
		  			var array = new Array();
		  			var config_data = $(this).prevAll(".td_data").children(".hidden_configData").val();
		  			if(config_data != null && config_data != ''){
		  				array = config_data.split(';');
		  			}
			  		if(array.length > 0){
		            	for(var j=0;j<array.length;j++){
		            		$(this).children("div").children("input:checkbox[value='"+array[j]+"']").prop('checked',true);
		            	}
			  		}
		  		}
		    });
	  		
	  		//开关样式
	  		$('.input_button').lc_switch();
	  		
	  		//【开关说明】【保存】按钮的显示和隐藏
	        $(".switchTable tr").hover(
	            function(){
	                $(this).children("td:last-child").children("a").show();
	                $(this).siblings().children("td:last-child").children("a").hide();
	            }
	        );
	  	}
	});
}
//初始化数据，复杂的没实现，例如学分比例配置
function initializeData(){
	$(".config_content").each(function(){
			var id = $(this).children(":first").children(":first").attr("id");
			if(typeof(id) != "undefined"){
				var config_data = $("#hidden_config_data_" + id).val();
				$(this).children(":first").children(":first").val(config_data);
			}
	  });
}
//开关说明详情
function viewDetail(this_config_code){
	var config_code = this_config_code.id;
	var config_explain = $("#hidden_config_explain_" + config_code).val();
	var str = "<div style='padding:15px;height:400px;overflow:auto;'>" + config_explain + "</div>";
	relesepup = mypopdiv(1,"系统开关说明",'500px',(pHeight-250)+'px','50px',(pWidth-1000)/2,'Y',str);
}
//保存系统配置
function saveSysConfig(this_config_code){
	
	var config_code = this_config_code.id;
	var id = $("#hidden_id_" + config_code).val();
	var config_type = $("#hidden_config_type_" + config_code).val();
	var config_content = $("#td_config_content_" + config_code).html();
	
	var config_data = $("#" + config_code).val();
	if($("#" + config_code).hasClass("number")){
		if(isNaN(config_data)||checkStr(config_data,1)==false){
			myMsg("格式输入错误，请输入正整数！",'50%','50%');
			return false;
	}
	}
	var postData = {};
	var str="";
	var config_flag = '';
	if($("#checkbox_config_flag_" + config_code).is(':checked')){
		config_flag = 1;
		//stu_info_config复选框初始状态未选中  学生登入是检测填写的基本信息是否完善
//		if(config_code=="stu_info_config"){
//			$("input:checkbox[name='stu_info']:checked").each(function() { // 遍历name=stu_info的多选框
//				  // 每一个被选中项的值
//				if(str==""){
//					str=$(this).val();
//				}else{
//					str+=";"+$(this).val();
//				}
//			});
//			postData = {
//					id:id,
//					config_flag:config_flag,
//					config_content:config_content,
//					config_data:str
//			};
//		}else{
//			postData = {
//					id:id,
//					config_flag:config_flag,
//					config_content:config_content,
//					config_data:config_data
//			};
//		}
		if(typeof($("#td_config_content_" + config_code).children(":first").children(":first").attr("id")) != "undefined"){
			postData = {
					id:id,
					config_flag:config_flag,
					config_content:config_content,
					config_data:config_data
			};
		}else{//  复杂的（多选框）
			$("#td_config_content_" + config_code).children("div").children("input:checkbox:checked").each(function() { // 遍历多选框
				  // 每一个被选中项的值
				if(str==""){
					str=$(this).val();
				}else{
					str+=";"+$(this).val();
				}
			});
			postData = {
					id:id,
					config_flag:config_flag,
					config_content:config_content,
					config_data:str
			};
		}
	}else{
		config_flag = -1;
		postData = {
				id:id,
				config_flag: config_flag
				};
	}
	var url=ctx + "/configweb/saveSysConfig.action";
	doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
				 selectSysConfig(config_type);
				 layer.closeAll();
			 });
		 }
	 });
}
//重新刷新
function reload(){
	$("#span_system").addClass('clicked').siblings().removeClass('clicked');
	selectSysConfig(1);
}
//$(document).ready(function(e) {
//    $('input').lc_switch();
//    $('body').delegate('.lcs_check', 'lcs-statuschange', function() {
//        var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
//        console.log('field changed status: '+ status );
//    });
//    $('body').delegate('.lcs_check', 'lcs-on', function() {
//        console.log('field is checked');
//    });
//    $('body').delegate('.lcs_check', 'lcs-off', function() {
//        console.log('field is unchecked');
//    });
//});