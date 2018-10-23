$(function () {
    //页面加载完成之后执行
    pageInit();
});
//页面加载
function pageInit() {
	var role=""; 
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
	role = Request['role']; 
    //创建jqGrid组件
    jQuery("#reviewLeave").jqGrid(
        {
            url: ctx+'/teachweb/selectReviewLeave.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', 'proc_result','学生姓名', '', '科室', '请假类型', '', '请假时间', '请假天数', '概述', '操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			    {name: 'id',index:'id',fixed:true,hidden:true,key:true},
			    {name: 'proc_result',index:'proc_result',fixed:true,hidden:true},
			    {name: 'user_name', index: 'user_name', align: "center",sortable:false},
			    {name: 'stu_auth_id', index: 'stu_auth_id', hidden: true,sortable:false},
                {name: 'orga_name', index: 'orga_name', align: "center",sortable:false},
                {name: 'vacate_type_name', index: 'vacate_type_name', align: "center",sortable:false},
                {name: 'vacate_status', index: 'vacate_status', hidden: true},
                {name: 'vacate_time', index: 'vacate_time', align: "center"},
                {name: 'vacate_date_num_str', index: 'vacate_date_num_str', align: "center"},
                {name: 'content', index: 'content', width: 270, align: "center",sortable:false},
                {name: 'operation', index: 'operation', width: 250, align: "center",fixed:true,sortable:false}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            gridview: true,//?
        //    loadonce:true, //一次加载全部数据到客户端，由客户端进行排序，会影响分页。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            viewrecords : true,
            postData: {
            	proc_result: "",
            	role:role
            }, 
            width: pWidth-40,
            height: pHeight-150,
            resizable:false,
    		modal:true,
            gridComplete: function () {
                var ids = jQuery("#reviewLeave").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var proc_result = jQuery("#reviewLeave").jqGrid('getRowData',id).proc_result;//或得每一行的请假申请的当前状态
                    var vacate_status = jQuery("#reviewLeave").jqGrid('getRowData',id).vacate_status;
                    var stu_auth_id = jQuery("#reviewLeave").jqGrid('getRowData',id).stu_auth_id;
                    var se = "<a style='color: #505f91;cursor: pointer'" + "onclick=\"$('#reviewLeave').jqGrid('saveRow','" + id + "',review('" + id + "','1',''));\">审批</a>&nbsp;&nbsp;&nbsp;";
                    var ce = "<a style='color: #505f91;cursor: pointer' href='#'" + "onclick=\"$('#reviewLeave').jqGrid('saveRow','" + id + "',viewProcess('" + id + "'));\">查看流程</a>";
                    var re = "<a style='color: #505f91;cursor: pointer' href='#'" + "onclick=\"$('#reviewLeave').jqGrid('saveRow','" + id + "',reportBackCheck('" + id + "','" + stu_auth_id + "'));\">销假审批</a>&nbsp;&nbsp;&nbsp;";
                    if(proc_result!=1&&proc_result!=-1){//小心变动！！！
                    	if(vacate_status == 4){
                    		jQuery("#reviewLeave").jqGrid("setRowData", id, {operation: re + ce});
                    	}else{
                    		jQuery("#reviewLeave").jqGrid("setRowData", id, {operation: se + ce});
                    	}
                    }else{
                    	jQuery("#reviewLeave").jqGrid("setRowData", id, {operation: ce});
                    }
                }
            }
        });
}
//重新加载
function orgaInit(proc_result){
	var role=""; 
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
	role = Request['role'];
	$("#reviewLeave").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	proc_result: proc_result,
        	role: role
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//审批
function review(id,flagOfExamineType,stu_auth_id){
	if (id == "") {
		myMsg('请选择请假申请！', '', '', 3000);
		return false;
	}
	var role=""; 
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
	role = Request['role'];
	var title = '';
	if(flagOfExamineType == 1){
		title = "请假审批";
	}else if(flagOfExamineType == -1){
		title = "销假审批";
	}
	relesepup = mypopdiv(2,title,'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'Y',ctx + '/jsp/web/teach/approveLeave.jsp?id='+ id + '&role=' + role + '&flagOfExamineType=' + flagOfExamineType + '&stu_auth_id=' + stu_auth_id);
}
//销假审批
function reportBackCheck(id,stu_auth_id){
	var flagOfExamineType = -1;
	review(id,flagOfExamineType,stu_auth_id);
}
//审批流程
function viewProcess(id){
	if (id == "") {
		myMsg('请选择请假申请！', '', '', 3000);
		return false;
	}
	relesepup = mypopdiv(2,"审批流程",'1025px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + '/jsp/web/teach/readLeave.jsp?id='+ id);
//	relesepup = mypopdiv(2,"审批流程",'1025px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'Y',ctx + '/jsp/web/teach/viewLeaveProcess.jsp?id='+ id);
}
//关闭弹出层
function closeMyWindows(){
	orgaInit();
	layer.closeAll();
}

function div1(){
	$("#button1").addClass("select"); 
	$("#button2").removeClass("select"); 
	orgaInit("");
	}
function div2(){
	$("#button2").addClass("select"); 
	$("#button1").removeClass("select");
	orgaInit("1");
}