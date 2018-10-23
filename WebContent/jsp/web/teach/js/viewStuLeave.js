var content = "";
$(function(){
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
        colNames : ['id','科室','姓名','请假类型名称','请假天数','请假时间','操作时间','审核状态',''],
        colModel : [
        	 {name : 'id',index:'id',fixed:true,hidden:true,key:true},
             {name : 'orga_name',index:'orga_name',align:'center'},
             {name : 'user_name',index:'user_name',align:'center'},
             {name : 'vacate_type_name',index:'vacate_type_name',align:'center'},
             {name : 'vacate_date_num_str',index:'vacate_date_num_str',align:'center'},
             {name : 'vacate_time',index:'vacate_time',align:'center'},
             {name : 'create_time_str',index:'create_time_str',align:'center'},
        	 {name : 'proc_name',index:'proc_name',align:'center'},
        	 {name : 'vacate_status',index:'vacate_status',hidden:true}
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100],
        pager : '#PublicDataToolbar',
        gridview: true,
        sortname: 'invdate',
        viewrecords : true,
        postData: {
        	state: "Y",
        	flag:"1"
        		}, 
        width: pWidth,
        height: pHeight-120,
		autowidth:true,
		shrinkToFit:true,
        resizable:false,
		modal:true,
        gridComplete: function () {
         var ids = jQuery("#PublicDataGrid").jqGrid("getDataIDs");
     }
	});
}