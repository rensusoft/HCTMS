$(function () {
    //页面加载完成之后执行
    pageInit();
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#dailyRecord").jqGrid(
        {
            url: ctx+'/teachweb/selectReviewedDaily.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', 'create_auth_id', '学生姓名', '日志类型', '概述', '提交时间', '操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			    {name: 'id',index:'id',fixed:true,hidden:true,key:true},
			    {name: 'create_auth_id',index:'create_auth_id',fixed:true,hidden:true},
			    {name: 'userName', index: 'userName', align: "center"},
                {name: 'type_id_str', index: 'type_id_str', align: "center"},
                {name: 'summary', index: 'summary', align: "center"},
                {name: 'create_time_str', index: 'create_time_str', align: "center"},
                {name: 'operation', index: 'operation', width: 250, align: "center",fixed:true}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            gridview: true,//?
            sortname: 'invdate',
            viewrecords : true,
            postData: {
            	state: "N"
            }, 
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            width: pWidth-40,
            height: pHeight-120,
            resizable:false,
    		modal:true,
    		multiselect: true,
            gridComplete: function () {
                var ids = jQuery("#dailyRecord").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var create_auth_id = jQuery("#dailyRecord").jqGrid('getRowData',id).create_auth_id;
                    var se = "<a title=''  style='color: #505f91;cursor: pointer'" + "onclick=\"$('#dailyRecord').jqGrid('saveRow','" + id + "',reviewDailyRecord('" + id + "','" + create_auth_id + "'));\">审阅</a>";
                   
                    jQuery("#dailyRecord").jqGrid("setRowData", id, {operation: se });
                }
            }
        });
}
//审阅
function reviewDailyRecord(id,create_auth_id){
	var flag = 1;
	mypopdiv(2,"培训日志",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + "/teachweb/selectDailyRecordById.action?id="+id+"&flag="+flag,
		function(){
		var url=ctx+'/teachweb/delDailyRecord.action';
		var postData={
				id:id,
				state:"Y",
				create_auth_id:create_auth_id
		 };
		 doAjax(url,postData,2,function(res){
			 if(res.success == true){
				 orgaInit();
			 }else{
				 myalert_error(res.data);
			 }
		 });
		}
	);
}
//批量审阅
function batchReview(){
	var ids=$("#dailyRecord").jqGrid("getGridParam","selarrrow");
	if(ids==null||ids==""){
		myMsg("请选择要审阅的日志！",'50%','50%');
		return false;
	}
	var id_create_auth_id_arry = '';
	for(var i = 0; i < ids.length; i++){
		var create_auth_id = jQuery("#dailyRecord").jqGrid('getRowData',ids[i]).create_auth_id;
		id_create_auth_id_arry = (ids[i] + '-' + create_auth_id) + ";" + id_create_auth_id_arry;
	}
//	alert(id_create_auth_id_arry);
	myConfirm("是否批量审阅日志？",'(pHeight/2)px','',function(){
		 var url=ctx+'/teachweb/batchReview.action';
		 var postData={
				 id_create_auth_id_arry:id_create_auth_id_arry,
				 state:"Y"
		 };
		 doAjax(url,postData,2,function(res){
			 if(res.success == true){
				 orgaInit();
				 myalert_success(res.data);
			 }else{
				 myalert_error(res.data);
			 }
		 });
	});

}
//重新加载
function orgaInit(){
	$("#dailyRecord").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "N"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}


//查看
function look(id)
{ 
	mypopdiv(2,"培训日志",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + "/teachweb/selectDailyRecordById.action?id="+id);

}
	
//编辑
function upd(id,type_id_str){
	if (id == "") {
		myMsg('请选择培训日志！', '', '', 3000);
		return false;
	}
	window.location.href=ctx + "/jsp/web/teach/writing.jsp?id=" + id + "&type_id_str=" + type_id_str;
}
//删除培训日志
function del(id){
	if (id == "") {
		myMsg('请选择培训日志！', '', '', 3000);
		return false;
	}
	myConfirm("是否要删除？","","",
			function(index){
			 var url=ctx+'/teachweb/delDailyRecord.action';
			 var postData={
					id:id,
					state:"X"
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
		);
}
//搜索
function keywordSearch(){
	   var searchStr= $("#searchStr").val();
	   var type_id= $("#type_id").val();
	   if(searchStr.length<2&&searchStr.length!=0){
			 myalert_error("最少输入两个字符!","30%","40%");
			 return false;
		 }
	   if(type_id == null || type_id == ""){
		   if(searchStr=="日报"){
			   type_id=1;
			 }else if(searchStr=="周报"){
				 type_id=2;
			 }else if(searchStr=="月报"){
				 type_id=3;
			 }
	   }
		 $("#daily").jqGrid('setGridParam',{
		        datatype: 'json',
		        postData: {
		        	searchStr: searchStr,
		        	type_id: type_id,
		        	state: "Y",
		        }, //发送数据
		        page: 1
		    }).trigger("reloadGrid"); //重新载入
		
	}
//跳转到日志书写页面
function writeDaily(){
	window.location.href=ctx + "/jsp/web/teach/writing.jsp";
}
