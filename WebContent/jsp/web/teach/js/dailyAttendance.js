$(function(){
	var flag = 0;
	var myDate = new Date();
	var year=myDate.getFullYear();
	var month=myDate.getMonth()+1;
	var day=myDate.getDate();
	$(".manualMttendance").click(
			function(){
				layer.open({
					type: 2,
					title:'人工考勤——学生列表',
					content: ctx+ '/jsp/web/teach/manualMttendance.jsp',
					area: ['1100px', pHeight-30 + 'px'],
					end: function () {
						orgaInit();
		            }
				});
			}
	);
	//考勤二维码开关是否开启
	$.ajax({
		type: 'POST',
		url : ctx + "/teachweb/getAbilityOfQRCode.action",
		data : {},
		dataType: 'json',
		async: false,
		success:function(res) {
			flag = res.data;
		}
	});
	if(flag == -1){
		$("#p_QRCode a").addClass('disabled');
		$("#p_QRCode a").removeClass('QRCode');
	}
	$(".QRCode").click(
			function(){
				layer.open({
					type: 2,
					title:'二维码考勤 ['+year+"/"+month+"/"+day+"]",
					content:ctx+ '/jsp/web/teach/QRCode.jsp',
					area: ['460px', '450px' ],
					end: function () {
						orgaInit();
		            }
				});
			}
	);
    $(".year").text(year);
    $(".month").text(month);
    $(".day").text(day);
    $(".stateList a").click(
        function(){
            $(this).addClass("clicked").siblings().removeClass("clicked");
        }
    );
    //
    getAttendanceCount();
    //
    var attend_state = 3;//  考勤状态（3：全部、2：请假、1：到勤、0：缺勤）
    jpGridInit(attend_state);
    
});
//获取已考勤人数、未考勤人数、请假人数及全部人数
function getAttendanceCount(){
	$.ajax({
		type : 'POST',
		url : ctx+'/teachweb/getAttendanceCount.action',
		dataType : 'json',
		data : {},
    	success:function(res) {
    		$("#the_whole span").text(res.whole_count);
    		$("#the_attendance span").text(res.attendance_count);
    		$("#no_attendance span").text(res.no_attendance_count);
    		$("#the_leave span").text(res.leave_count);
    	}
	});
}
//列表初始化
function jpGridInit(attend_state){
	var mydata1 = [
	              {
	                  id: "1",
	                  stu_name: "张三",
	                  type_name:'111',
	                  attend_state_str:"请假",
	                  amount: "<a class='stateChange' id='11111'><img src='"+ctx+"/jsp/web/teach/img/x.png'></a>"
	              },
	              {
	                  id: "2",
	                  stu_name: "李四",
	                  type_name:'222',
	                  attend_state_str:"未考勤",
	                  amount: "<a class='stateChange' id='222222'><img src='"+ctx+"/jsp/web/teach/img/x.png'></a>"
	              },
	              {
	                  id: "3",
	                  stu_name: "王二麻子",
	                  type_name:'333',
	                  attend_state_str:"已考勤",
	                  amount: "<a class='stateChange' id='333333'><img src='"+ctx+"/jsp/web/teach/img/pass.png'></a>"
	              }
	          ]
	
	      var mydata = selectStuAttendanceInfoList(attend_state);
	
	      $("#check").jqGrid({
	          datatype: 'local',
	          data: mydata,
	          colNames: ['id', 'stu_auth_id', 'attend_state', '学生姓名', '学生类型', '考勤结果', '操作'],
	          colModel: [
	              {name: 'id', index: 'id', width: 50, align: 'center', fixed: true, hidden: true, key: true},
	              {name: 'stu_auth_id', index: 'stu_auth_id', width: 50, align: 'center', hidden: true},
	              {name: 'attend_state', index: 'attend_state', width: 50, align: 'center', hidden: true},
	              {name: 'stu_name', index: 'stu_name', width: 50, align: "center"},
	              {name: 'type_name', index: 'type_name', width: 50, align: 'center'},
	              {name: 'attend_state_str', index: 'attend_state_str', width: 50, align: 'center'},
	              {name: 'amount', index: 'amount', width: 30, sortable: false, align: "center"}
	          ],
	          rowNum: 20,//一页显示多少条
	          rowList: [20, 50, 100],//可供用户选择一页显示多少条
	          pager: '#pager1',
	          gridview: true,
	          sortname: 'invdate',
	          viewrecords: true,
	          sortorder: 'desc',
	          width: 800,
	          height: pHeight-180,
	          resizable:false,
	    	  modal:true,
		      gridComplete: function () {
	              var ids = jQuery("#check").jqGrid("getDataIDs");
	              for (var i = 0; i < ids.length; i++) {
	                  var id = ids[i];
	                  var stu_name = jQuery("#check").jqGrid('getRowData',id).stu_name;//或得每一行的学生姓名
	                  var stu_auth_id = jQuery("#check").jqGrid('getRowData',id).stu_auth_id;//或得每一行的学生权限id
	                  var attend_state = jQuery("#check").jqGrid('getRowData',id).attend_state;//或得每一行的学生考勤状况
	                  var se = "<a onclick=\"$('#check').jqGrid('saveRow','" + id + "',changeAttendanceState('" + id + "','" + stu_name + "','" + stu_auth_id + "','" + attend_state + "'));\"><span class='glyphicon glyphicon-pencil'></span></a>";
	                  jQuery("#check").jqGrid("setRowData", id, {amount: se});
	              }
	          }
	      });
}
//获取考勤信息列表的数据
function selectStuAttendanceInfoList(attend_state){
	var attendanceDataStr = '';
	//添加加载层
	var divPop = myLoading();
	$.ajax({
		async: false,
	  	type: 'POST',
		url : ctx+'/teachweb/selectStuAttendanceInfoList.action',
		data : {
			attend_state : attend_state
			},
		dataType: 'json',
	  	success:function(res) {
	  		//去掉加载层
			closeMyLoading(divPop);
	  		attendanceDataStr += '[';
	    	for(var i = 0; i < res.data.length; i++){
	    		attendanceDataStr += 
	    			'{' +
	    				'"id" : ';
	    				if((res.data)[i].id == null){
	    					attendanceDataStr += '"line' + i + '"';
	    				}else{
	    					attendanceDataStr += (res.data)[i].id;
	    				}
	    				attendanceDataStr += 
	    				',"stu_auth_id" : '+ (res.data)[i].stu_auth_id + ',' +
	    				'"attend_state" : '+ (res.data)[i].attend_state + ',' +
	    				'"stu_name" : "'+ (res.data)[i].stu_name + '",' +
	    				'"type_name" : "'+ (res.data)[i].type_name + '",' +
	    				'"attend_state_str" : "';
	    				if((res.data)[i].attend_state_str == '未考勤'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/x.png\'>' + (res.data)[i].attend_state_str;
	    				}else if((res.data)[i].attend_state_str == '已考勤'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/pass.png\'>' + (res.data)[i].attend_state_str;
	    				}else if((res.data)[i].attend_state_str == '请假' || (res.data)[i].attend_state_str == '请假（上午）' || (res.data)[i].attend_state_str == '请假（下午）'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/x.png\'>' + (res.data)[i].attend_state_str;
	    				}
	    				attendanceDataStr += '"' +
	    			'},';
	    	}
	    	attendanceDataStr += ']';
	    	if(attendanceDataStr.indexOf("},]") != -1){
	    		attendanceDataStr = attendanceDataStr.replace(/},]/, "}]");
	    	}
//	    	alert(attendanceDataStr);
	  	}
	});
	var attendanceData = $.parseJSON(attendanceDataStr);
//	alert(attendanceData);
	return attendanceData;
}
//操作考勤
function changeAttendanceState(id,stu_name,stu_auth_id,attend_state){
	if(attend_state == 2 || attend_state == -10 || attend_state == -20){
		myalert_error('请假状态的考勤不能作修改！', '30%', '40%');
	    return false;
	}
	layer.open({
      type: 2,
      title:'考勤修改 [' + stu_name + ']',
      content: ctx+ '/jsp/web/teach/stuState.jsp?id=' + id + '&stu_auth_id=' + stu_auth_id + '&flag=1',
      area: ['250px', '128px']
    });
}
//切换考勤状态
function switchAttendance(attend_state){
	var newdata = selectStuAttendanceInfoList(attend_state);
	$("#check").jqGrid('clearGridData');  //清空表格
	$("#check").jqGrid('setGridParam',{  // 重新加载数据
	      datatype:'local',
	      data : newdata,   //  newdata 是符合格式要求的需要重新加载的数据 
	      page:1
	}).trigger("reloadGrid");
}
//重新加载
function orgaInit(){
	$("#the_whole").addClass("clicked");
	$("#the_attendance").removeClass("clicked");
	$("#no_attendance").removeClass("clicked");
	$("#the_leave").removeClass("clicked");
	
	getAttendanceCount();
	
	var newdata = selectStuAttendanceInfoList(3);
	$("#check").jqGrid('clearGridData');  //清空表格
	$("#check").jqGrid('setGridParam',{  // 重新加载数据
	      datatype:'local',
	      data : newdata,   //  newdata 是符合格式要求的需要重新加载的数据 
	      page:1
	}).trigger("reloadGrid");
}
//关闭窗口
function closeLayer(){
    layer.closeAll();
};