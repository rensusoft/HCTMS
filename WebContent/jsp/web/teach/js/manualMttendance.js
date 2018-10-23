$(function(){
	var mydate = new Date();
    var year=mydate.getFullYear();
    var month=mydate.getMonth();
    var day=mydate.getDate();
    $(".year").text(year);
    $(".month").text(month+1);
    $(".day").text(day);
//    $(".stuListDiv").height(pHeight-500 + 'px');
//    $(".leaveStuDiv").height(pHeight-650 + 'px');
	//请假学生
	$.ajax({
		type : 'POST',
		url : ctx + '/userauthweb/leaveStuInfo.action',
		dataType : 'json',
		data : {
		},
		async : false,
		success : function(data) {
			var str = "";
			if (data.length != 0) {
				for(var i =0;i<data.length;i++){
					str+='<div class="leStuDiv">'+
				         '<p class="stuName">'+data[i].stu_name+'</p>'+
				         '<p class="stuType">'+data[i].stu_type+'</p>'+
				         '</div>';
				}
			 }
//			alert(str);
			$("#leaveStuDiv").html(str);
//			$(".leStuDiv").height(pHeight-670 + 'px');
    		}
	});
	//
	selectAttendanceStuInfo();
	
	$(".layui-layer-btn0").click(
	    function(){
	        parent.closeLayer();
	    }
	);
	
});
//待考勤学生信息
function selectAttendanceStuInfo(){
	$.ajax({
		type : 'POST',
		url : ctx + '/teachweb/selectAttendanceStuInfo.action',
		data : {
			
		},
		dataType : 'json',
		async : false,
		success : function(res){
			var str = '';
			for(var i = 0; i < res.data.length; i++){
				str += '';
					if(res.data[i].attend_state_str == '未考勤'){
						str += '<div class="stuDiv dark">';
					}else if(res.data[i].attend_state_str == '已考勤'){
						str += '<div class="stuDiv">';
					}
					str += '';
						if(res.data[i].id != null){
							str += '<input class="hidden_id" type="hidden" value="' + res.data[i].id + '"/>';
						}else{
							str += '<input class="hidden_id" type="hidden" value="line' + i + '"/>';
						}
						str += 
						'<input class="hidden_stu_auth_id" type="hidden" value="' + res.data[i].stu_auth_id + '"/>' +
						'<p class="stuName">' + res.data[i].stu_name + '</p>' +
						'<p class="stuType">' + res.data[i].type_name + '</p>';
						if(res.data[i].attend_state_str == '未考勤'){
							str += 
								'<div class="stuDiv_img">' +
									'<img src="' + ctx + '/jsp/web/teach/img/absenteeism.png"/>' +
								'</div>';
						}else if(res.data[i].attend_state_str == '已考勤'){
							str +=
								'<div class="stuDiv_img display">' +
									'<img src="' + ctx + '/jsp/web/teach/img/absenteeism.png"/>' +
								'</div>';
						}
						str += '</div>';
			}
//			alert(str);
			$(".stuListDiv").html(str);
		}
	});
	$(".stuDiv").click(
	    function(){
	    	var id = $(this).children(".hidden_id").val();
	    	var stu_auth_id = $(this).children(".hidden_stu_auth_id").val();
	    	var stu_name = $(this).children(".stuName").text();
	        layer.open({
	        	type: 2,
	            title:'考勤修改 [' + stu_name + ']',
	            content: ctx+ '/jsp/web/teach/stuState.jsp?id=' + id + '&stu_auth_id=' + stu_auth_id + '&flag=-1',
	            area: ['250px', '128px']
	        });
	    }
	);
	$(".stuList").height($("section").height()-200);
	$(".stuListDiv").height($("section").height()-230);
}
//关闭窗口
function closeLayer(){
	layer.closeAll();
};
//窗口关闭刷新列表
//window.onbeforeunload = function(){
//	parent.orgaInit();
//}