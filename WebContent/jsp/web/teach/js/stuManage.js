var train_status_flag;
$(function(){
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	train_status_flag = Request['train_status_flag']; 
	pageInit(train_status_flag,'');
    $(".stateList a").click(
        function(){
        	$("#stu_name").val('');
            $(this).addClass("clicked").siblings().removeClass("clicked");
            var stu_name = '';
            train_status_flag = '';//  状态标识（1：轮转中、2：待出科）
            if($(this).text() == "轮转中"){
            	train_status_flag = '1';
            }else if($(this).text() == "待出科"){
            	train_status_flag = '2';
            }
            pageInit(train_status_flag,stu_name);
        }
    );
    
});
//初始化主页面
function pageInit(train_status_flag,stu_name){
	//添加加载层
	var divPop = myLoading();
	$.ajax({
		type: 'POST',
		url: ctx + '/teachweb/getStuManageInfo.action',
		dataType: 'json',
		async: false,
		data: {
			train_status_flag : train_status_flag,
			stu_name : stu_name
		},
		success: function(res){
			//去掉加载层
			closeMyLoading(divPop);
			var str = '';
			for (var i = 0; i < res.data.length; i++){
				str += 
					'<div class="stuInfor">';
					if ((res.data)[i].train_status_str == "轮转中"){
						str += '<b class="green"><i>' + (res.data)[i].train_status_str + '</i></b>';
					}else if ((res.data)[i].train_status_str == "待出科"){
						str += '<b class="orange"><i>' + (res.data)[i].train_status_str + '</i></b>';
					}
					str += 
						'<div class="imgInfor">' +
						"<span><img style=\"height:138px;width:136px\" onerror='this.src=\""+ctx+"/jsp/web/score/images/userb.png\"' src='"+ctx+"/ueditor(附件文件夹千万不能删)/userImg/"+(res.data)[i].stu_num+".jpg'"+"/></span>" +
							'<p>' +
								'<b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>' + (res.data)[i].name + '</i><br/>' +
								'<b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>' + (res.data)[i].typeName + '</i><br/>' +
								'<b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>' + (res.data)[i].train_start_str + '-' + (res.data)[i].train_end_str + '</i><br/>' +
								'<b>轮&nbsp;转&nbsp;老&nbsp;师&nbsp;：</b><i><a class="teaLayer" id="' + (res.data)[i].id + '"><span class="glyphicon glyphicon-user"></span>' + (res.data)[i].teach_name + '</a></i><br/>' +
								'<input type="hidden" id="hidden_id_' + (res.data)[i].id + '" value="' + (res.data)[i].teacher_auth_id + '" />' +
								'<input type="hidden" id="hidden_name_' + (res.data)[i].id + '" value="' + (res.data)[i].teach_name + '" />' +
								'<input type="hidden" class="hid_train_end_str" value="' + (res.data)[i].train_end_str + '" />' +
								'<b>今&nbsp;日&nbsp;考&nbsp;勤&nbsp;：</b><i><a class="attLayer" id="' + (res.data)[i].stu_auth_id + '-' + (res.data)[i].train_start_str + '">';
								if((res.data)[i].attend_state_str == null){
									str += '<img src="' + ctx + '/jsp/web/teach/img/pass.png" alt=""/>' + '已签到';
								}else{
									if((res.data)[i].attend_state_str == "已签到"){
										str += '<img src="' + ctx + '/jsp/web/teach/img/pass.png" alt=""/>' + (res.data)[i].attend_state_str; 
									}else if((res.data)[i].attend_state_str == "未签到"){
										str += '<img src="' + ctx + '/jsp/web/teach/img/x.png" alt=""/>' + (res.data)[i].attend_state_str;
									}else if((res.data)[i].attend_state_str == "请假"){
										str += '<img src="' + ctx + '/jsp/web/teach/img/x.png" alt=""/>' + (res.data)[i].attend_state_str;
									}
								}
								str += '</a></i>' +
							'</p>' +
						'</div>' +
					'</div>';
			}
//			alert(str);
			$("#stuInforList").html(str);
			
			 if($("section").width()<1900){
			        $(".stuInfor").attr("style","width:31.5%");
			    }
			 
			$(".teaLayer").click(
		        function(){
		        	var id = this.id;
		        	var teacher_auth_id = $("#hidden_id_" + id).val();
		        	var teach_name = $("#hidden_name_" + id).val();
		                layer.open({
		                    type: 2,
		                    title:"更换带教老师",
		                    content: ctx + '/jsp/web/teach/teacherChoice.jsp?id=' + id + '&teacher_auth_id=' + teacher_auth_id + '&teach_name=' + teach_name+'&train_status_flag='+train_status_flag,
		                    area: ['500px', '260px']
		                });
		            }
		        );
		        $(".attLayer").click(
		            function(){
		            	var stu_auth_id_str = this.id;
		                var stuName=$(this).parents(".imgInfor").children("p").children("b:first-child").next().text();
		                var train_end_str=$(this).closest("p").children(".hid_train_end_str").val();
		                layer.open({
		                    type: 2,
		                    title:"考勤管理[学生："+stuName+"]",
		                    content: ctx + '/jsp/web/teach/attendanceManage.jsp?stu_auth_id_str=' + stu_auth_id_str + '&train_end_str=' + train_end_str,  
		                    area: ['1000px', (pHeight-35) + 'px']
		                });
		            }
		        );
		}
	});
}
//检索
function keySearch(){
	var stu_name = $("#stu_name").val();
	pageInit('',stu_name);
	$("#all").addClass("clicked");
	$("#round_robin").removeClass("clicked");
	$("#stay_out").removeClass("clicked");
}
//重置
function reset(){
	$("#stu_name").val('');
	pageInit('','');
	$("#all").addClass("clicked");
	$("#round_robin").removeClass("clicked");
	$("#stay_out").removeClass("clicked");
}
//关闭弹出的窗口与layer.open对应
function closeLayer(train_status_flag){
    layer.closeAll();
    pageInit(train_status_flag,'');
}
//主页面刷新
function orgaInitOfParent(){
    pageInit('','');
}
