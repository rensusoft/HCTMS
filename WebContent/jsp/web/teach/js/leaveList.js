var content = "";
$(function(){
	var url=ctx+'/teachweb/pressSelect.action';
	 var postData = {
			};
	 doAjax(url,postData,2,function(res){
		 var str='<option value=""></option>';
		 for(var i =0;i<res.data.length;i++){
			str +='<option value="'+res.data[i].id+'">'+res.data[i].proc_name+'</option>';
		 }
		 $("#pressSelect").html(str);
		 });
	PublicDataGridInit();
});


/**
 * 待审核列表初始化
 */
function PublicDataGridInit(){
	$("#PublicDataGrid").jqGrid(
	{
        url : ctx+'/teachweb/selectVacateInfo.action',
        datatype : "json",
        colNames : ['id','科室','请假类型名称','请假天数','请假时间','操作时间','当前流程状态','','操作'],
        colModel : [
        	 {name : 'id',index:'id',fixed:true,hidden:true,key:true},
             {name : 'orga_name',index:'orga_name',align:'center'},
             {name : 'vacate_type_name',index:'vacate_type_name',align:'center'},
             {name : 'vacate_date_num_str',index:'vacate_date_num_str',align:'center'},
             {name : 'vacate_time',index:'vacate_time',align:'center'},
             {name : 'create_time_str',index:'create_time_str',align:'center'},
        	 {name : 'proc_name',index:'proc_name',align:'center'},
        	 {name : 'vacate_status',index:'vacate_status',hidden:true},
        	 {name : 'act',index:'act',align:'center',width:250,fixed:true}
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100],
        pager : '#PublicDataToolbar',
        gridview: true,
        sortname: 'invdate',
        viewrecords : true,
        postData: {
        	state: "Y"
        }, 
        width: pWidth,
        height: pHeight-120,
		autowidth:true,
		shrinkToFit:true,
        resizable:false,
		modal:true,
        gridComplete: function () {
         var ids = jQuery("#PublicDataGrid").jqGrid("getDataIDs");
         for (var i = 0; i < ids.length; i++) {
             var id = ids[i];
             var vacate_status = jQuery("#PublicDataGrid").jqGrid('getRowData',id).vacate_status;//或得每一行的流程状态
             var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',red('" + id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
		  	 var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',upd('" + id + "'));\">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
		  	 var ce = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',del('" + id + "'));\">删除</a>";
		  	 var ye = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',reportBack('" + id + "','" + vacate_status + "'));\">销假</a>";
		  	 if(vacate_status == 0 || vacate_status == 4 || vacate_status == 5 || vacate_status == 6){
		  		 jQuery("#PublicDataGrid").jqGrid("setRowData", id, { act : re + ye });
             }else{
            	 jQuery("#PublicDataGrid").jqGrid("setRowData", id, { act : re + se + ce });
             }   
         }
     }
	});
}
//请假申请按钮页面跳转
function releasePublicData(){
	 relesepup = mypopdiv(2,"请假申请",'1000px',(pHeight-50)+'px','50px',(pWidth-1000)/2,'N',ctx + "/jsp/web/teach/leaveApplication.jsp");
}

function red(id){
	if (id == "") {
		myMsg('请选择查看的申请！', '', '', 3000);
		return false;
	}
	relesepup = mypopdiv(2,"请假审批流程",'1025px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + '/jsp/web/teach/readLeave.jsp?id='+ id);
}

function del(id){
	if (id == "") {
		myMsg('请选择请假申请！', '', '', 3000);
		return false;
	}
	updateleave = $("#PublicDataGrid").jqGrid('getRowData', id);
	if(updateleave['proc_name']!="待老师审核"){
		 myalert_error("已进入审批流程不可删除！","","");
		return false;
	}
	myConfirm("是否要删除？","","",
			function(index){
		var url=ctx+'/teachweb/delVacateInfo.action';
		 var postData={
				id:id,
				state:"X"
		 };
		 doAjax(url,postData,2,function(res){
			 orgaInit();
			 myalert_success(res.data);
		 });
			},
			function(){
        		window.close();
        		
			}
		);
}
function upd(id){
	if (id == "") {
		myMsg('请选择请假申请！', '', '', 3000);
		return false;
	}
	updateleave = $("#PublicDataGrid").jqGrid('getRowData', id);
	if(updateleave['proc_name']!="待老师审核"){
		 myalert_error("已进入审批流程不可修改！","","");
		return false;
	}
	 relesepup = mypopdiv(2,"请假申请",'1000px',(pHeight-50)+'px','50px',(pWidth-1000)/2,'Y',ctx + '/jsp/web/teach/leaveApplication.jsp?id='+ id);
}
//销假申请
function reportBack(id,vacate_status){
	if(vacate_status == 4){
		 myalert_error("已经发起销假申请无需再次发起申请！","","");
		return false;
	}
	if(vacate_status == 5){
		 myalert_error("已经销假成功无需再次发起销假申请！","","");
		return false;
	}
	 var url=ctx+'/teachweb/reportBackAfteLeave.action';
	 var postData={
			id:id,
			vacate_status:4//  销假待审核状态
	 };
	 doAjax(url,postData,2,function(res){
		 if(res.success == true){
			 orgaInit();
			 myalert_success(res.data);
		 }else{
			myalert_error(res.data); 
		 }
	 });
}
//重新加载
function orgaInit(){
	$("#PublicDataGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//关闭弹出层
function closeMyWindows(){
	layer.closeAll();
}

function keywordSearch(){
   var vacate_status= $("#pressSelect").val();
   var vacate_type_code= $("#type_code").val();
   var start_time_str= $("#start_time_str").val();
   var end_time_str= $("#end_time_str").val();
   if(start_time_str != ''){
	   start_time_str = start_time_str+' 00:00:00';
   }
   if(end_time_str != ''){
	   end_time_str = end_time_str+' 23:59:59';
   }
	 $("#PublicDataGrid").jqGrid('setGridParam',{
	        datatype: 'json',
	        postData: {
	        	vacate_status: vacate_status,
	        	vacate_type_code: vacate_type_code,
	        	start_time_str: start_time_str,
	        	end_time_str: end_time_str,
	        	state: "Y",
	        }, //发送数据
	        page: 1
	    }).trigger("reloadGrid"); //重新载入
	
}
function keywordReset(){
	$("#pressSelect").val("");
	$("#type_code").val("");
	$("#start_time_str").val("");
	$("#end_time_str").val("");
	$("#PublicDataGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	vacate_status: $("#pressSelect").val(),
        	vacate_type_code: $("#type_code").val(),
        	start_time_str: $("#start_time_str").val(),
        	end_time_str: $("#end_time_str").val(),
        	state: "Y",
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}