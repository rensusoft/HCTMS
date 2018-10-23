var type_id='';
$(function () {
    //页面加载完成之后执行
    pageInit();
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#rotateRule").jqGrid(
        {
            url: ctx+'/teachweb/selectMedDocByStu.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id','学生姓名','type_id','类型','病人姓名','住院号','对应大纲','书写时间','审批状态','correct_status','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id',index:'id',fixed:true,hidden:true,key:true},
                {name: 'stu_auth_id_str', index: 'stu_auth_id_str', align: "center"},
                {name: 'type_id',index:'type_id',hidden:true},
                {name: 'type_id_str', index: 'type_id_str', width: 100, align: "center"},
                {name: 'p_name', index: 'p_name', align: "center"},
                {name: 'p_pid', index: 'p_pid', align: "center"},
                {name: 'value', index: 'value', align: "center"},
                {name: 'create_time_str', index: 'create_time_str', width: 130, align: "center"},
                {name: 'correct_status_str', index: 'correct_status_str', width: 80, align: "center"},
                {name: 'correct_status',index:'correct_status',hidden:true},
                {name: 'operation', index: 'operation', width: 100, align: "center"}
            ],
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData: {
            	state: "Y"
            }, 
            width:pWidth-40,
            height:pHeight-130,
            resizable:false,
    		modal:true,
            gridComplete: function () {
                var ids = jQuery("#rotateRule").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var type_id = jQuery("#rotateRule").jqGrid('getRowData',id).type_id;
                    var correct_status = jQuery("#rotateRule").jqGrid('getRowData',id).correct_status;
                    var ee = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',view('" + id + "','" + type_id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;";
                    var se = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',upd('" + id + "','" + type_id + "','" + correct_status + "'));\">批改</a>";
                    jQuery("#rotateRule").jqGrid("setRowData", id,{operation: ee+se});
                }
            }
        });
}
//批改
function upd(id,type_id,correct_status) {
	var url="";
	if (id == "") {
		myalert_error('请选择医疗文书！');
		return false;
	}
	
	if(type_id==1){
		url =ctx + '/jsp/web/teach/medicalDocumentEdittwo.jsp?mr_id='+ id;
	}
	else if (type_id==2){
		url =ctx + '/jsp/web/teach/medicalDocumentEditthr.jsp?mr_id='+ id;
	}
	else if(type_id==3){
		url =ctx + '/jsp/web/teach/medicalDocumentEditfour.jsp?mr_id='+ id;
	}
	else{
		url =ctx + '/jsp/web/teach/medicalDocumentEditone.jsp?mam_id='+ id;
	}
	window.location.href=url;
}
//搜索
function keywordSearch() {
	   var start_date= $("#start_date").val();
	   var end_date= $("#end_date").val();
	   $("#rotateRule").jqGrid('setGridParam',{
	        datatype: 'json',
	        postData: {
	        	start_date: start_date,
	        	end_date: end_date,
	        	state: "Y",
	        }, //发送数据
	        page: 1
	    }).trigger("reloadGrid"); //重新载入
}
//查看对比
function view(id,type_id){
	if (id == "") {
		myalert_error('请选择医疗文书！');
		return false;
	}
	mypopdiv(2,"查看医疗文书",'1100px',(pHeight-65)+'px','50px',(pWidth-1000)/2,'N',ctx + '/teachweb/medicalDocumentComparison.action?id='+id+'&type_id='+type_id);
}