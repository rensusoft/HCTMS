var stu_auth_id_str = '';
var train_end_str = '';
$(function(){
	//
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
	stu_auth_id_str = Request['stu_auth_id_str']; 
	train_end_str = Request['train_end_str']; 
	//
	var mydata = selectAttendanceList();
	//
	selectNonAttendanceCount();
	var mydata1 = [
	              {
	                  id: "1",
	                  date:"2017-01-01",
	                  name: "<img src='img/pass.png'>",
	                  amount: "<a><span class='glyphicon glyphicon-pencil'></span></a>"
	              },
	              {
	                  id: "2",
	                  date:"2017-01-02",
	                  name: "<img src='img/x.png'>",
	                  amount: "<a><span class='glyphicon glyphicon-pencil'></span></a>"
	              },
	              {
	                  id: "3",
	                  date:"2017-01-03",
	                  name: "<img src='img/pass.png'>",
	                  amount: "<a><span class='glyphicon glyphicon-pencil'></span></a>"
	              }
	          ],
	          grid = $("#check");

	      grid.jqGrid({
	          datatype: 'local',
	          data: mydata,
	          colNames: ['id','日期', '状态', '操作'],
	          colModel: [
	              {name: 'id', index: 'id', width: 40, align: 'center', hidden: true},
	              {name: 'date', index: 'date', width: 40, align: "center"},
	              {name: 'name', index: 'name', width: 40, align: 'center'},
	              {name: 'amount', index: 'amount', width: 40, sortable: false, align: "center"}
	          ],
	          rowNum: 20,//一页显示多少条
	          rowList: [20, 50, 100],//可供用户选择一页显示多少条
	          pager: '#pager1',
	          gridview: true,
	          sortname: 'invdate',
	          viewrecords: true,
	          sortorder: 'desc',
	          width: 960,
	          height: pHeight-230,
	          resizable:false,
	    	  modal:true,
		      gridComplete: function () {
	              var ids = jQuery("#check").jqGrid("getDataIDs");
	              for (var i = 0; i < ids.length; i++) {
	                  var id = ids[i];
	                  var attend_time_str = jQuery("#check").jqGrid('getRowData',id).date;//或得每一行的日期
	                  var name = jQuery("#check").jqGrid('getRowData',id).name;//或得每一行的考勤状态
	                  var attend_state_str = name.substring(name.indexOf(">") + 1);
	                  
	                  var se = "<a onclick=\"$('#check').jqGrid('saveRow','" + id + "',changeAttendanceState('" + id + "','" + attend_time_str + "','" + attend_state_str + "'));\"><span class='glyphicon glyphicon-pencil'></span></a>";
	                  jQuery("#check").jqGrid("setRowData", id, {amount: se});
	              }
	          }
	      });
});
//获取个人未考勤天数
function selectNonAttendanceCount(){
	var stu_auth_id = stu_auth_id_str.split("-")[0];
	var train_start = stu_auth_id_str.split("-")[1];
	
	var train_start_year = train_start.split(".")[0];
	var train_start_month = train_start.split(".")[1];
	var train_start_day = train_start.split(".")[2];
	
	var train_end_year = train_end_str.split(".")[0];
	var train_end_month = train_end_str.split(".")[1];
	var train_end_day = train_end_str.split(".")[2];
	$.ajax({
 		type: 'POST',
	     	url :  ctx+'/teachweb/selectNonAttendanceCount.action',
	    	dataType: 'json',
	    	data:{
	    		stu_auth_id:stu_auth_id,
	    		train_start_year : train_start_year,
				train_start_month : train_start_month,
				train_start_day : train_start_day,
				train_end_year : train_end_year,
				train_end_month : train_end_month,
				train_end_day : train_end_day
	    		},
	  	    async: false,
	        success:function(res) {
	        	var attendance_count = res.attendance_count;
	        	if(attendance_count < 0){
	        		attendance_count = 0;
	        	}
	        	$("#span_attendance_count").text(attendance_count);
	        	$("#span_non_attendance_count").text(res.non_attendance_count);
	        }
	});
}
//获取个人考勤的信息列表
function selectAttendanceList(){
	var stu_auth_id = stu_auth_id_str.split("-")[0];
	var train_start = stu_auth_id_str.split("-")[1];
	
	var train_start_year = train_start.split(".")[0];
	var train_start_month = train_start.split(".")[1];
	var train_start_day = train_start.split(".")[2];
	
	var train_end_year = train_end_str.split(".")[0];
	var train_end_month = train_end_str.split(".")[1];
	var train_end_day = train_end_str.split(".")[2];
	
	var attendanceDataStr = '';
	$.ajax({
		async: false,
	  	type: 'POST',
		url : ctx+'/teachweb/selectAttendanceList.action',
		data : {
			stu_auth_id : stu_auth_id,
			train_start_year : train_start_year,
			train_start_month : train_start_month,
			train_start_day : train_start_day,
			train_end_year : train_end_year,
			train_end_month : train_end_month,
			train_end_day : train_end_day
			},
		dataType: 'json',
	  	success:function(res) {
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
//	    		+ (res.data)[i].id + ',' +
	    				attendanceDataStr += ',"date" : "'+ (res.data)[i].attend_time_str + '",' +
	    				'"name" : "';
	    				if((res.data)[i].attend_state_str == '未签到'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/x.png\'>' + (res.data)[i].attend_state_str;
	    				}else if((res.data)[i].attend_state_str == '已签到'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/pass.png\'>' + (res.data)[i].attend_state_str;
	    				}else if((res.data)[i].attend_state_str == '请假' || (res.data)[i].attend_state_str == '请假（上午）' || (res.data)[i].attend_state_str == '请假（下午）'){
	    					attendanceDataStr += '<img src=\'' + ctx + '/jsp/web/teach/img/x.png\'>' + (res.data)[i].attend_state_str;
	    				}
	    				attendanceDataStr += '"' +
//	    				,"amount" : "<a onclick=\'changeAttendanceState(' + (res.data)[i].id + ',' + (res.data)[i].attend_time_str + ')\'><span class=\'glyphicon glyphicon-pencil\'></span></a>"' +
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
//修改考勤记录
function changeAttendanceState(id,attend_time_str,attend_state_str){
	if(attend_state_str == "请假" || attend_state_str == "请假（上午）" || attend_state_str == "请假（下午）"){
		myalert_error('请假状态的考勤不能作修改！', '30%', '40%');
	    return false;
	}
	var attend_time_str_format = attend_time_str.substring(0,4) + "年" + attend_time_str.substring(5,7) + "月" + attend_time_str.substring(8) + "日";
	var attend_state_str_change = "";
	var attend_state = "";
	if(attend_state_str == "已签到"){
		attend_state_str_change = "未签到";
		attend_state = 0;
	}else if(attend_state_str == "未签到" || attend_state_str == "请假"){
		attend_state_str_change = "已签到";
		attend_state = 1;
	}
	if (id == "") {
		myMsg('请选择考勤记录！', '', '', 3000);
		return false;
	}
	var stu_auth_id = stu_auth_id_str.split("-")[0];
	myConfirm("是否要将" + attend_time_str_format + "的考勤修改为 [<font color='red'>" + attend_state_str_change + "</font>]？","","",
			function(index){
			 var url=ctx+'/teachweb/changeAttendanceState.action'; 
			 var postData={
					id:id,
					stu_auth_id:stu_auth_id,
					attend_time_str:attend_time_str,
					attend_state:attend_state
			 };
			 doAjax(url,postData,2,function(res){
				 if(res.success == true){
					 selectNonAttendanceCount();
					 orgaInit();
					 parent.orgaInitOfParent();
					 myalert_success(res.data);
				 }else{
					 myalert_error(res.data);
				 }
			 });
			}
		);

}
//重新加载
function orgaInit(){
	var newdata = selectAttendanceList();
	$("#check").jqGrid('clearGridData');  //清空表格
	$("#check").jqGrid('setGridParam',{  // 重新加载数据
	      datatype:'local',
	      data : newdata,   //  newdata 是符合格式要求的需要重新加载的数据 
	      page:1
	}).trigger("reloadGrid");
}