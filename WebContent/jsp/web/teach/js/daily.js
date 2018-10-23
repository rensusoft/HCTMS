$(function () {
    //页面加载完成之后执行
    pageInit();
//    $('.left_btns li').click(function() {
//        var i = $(this).index();//下标第一种写法
//        $(this).addClass('select').siblings().removeClass('select');
//        $('#con li').eq(i).show().siblings().hide();
//    });
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#daily").jqGrid(
        {
            url: ctx+'/teachweb/selectDailyRecord.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', '类型', '书写日期', '概述', '操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			    {name: 'id',index:'id',fixed:true,hidden:true,key:true},
			    {name: 'type_id_str', index: 'type_id_str', align: "center"},
                {name: 'create_time_str', index: 'create_time_str', align: "center"},
                {name: 'summary', index: 'summary', align: "center"},
                {name: 'operation', index: 'operation', width: 250, align: "center",fixed:true,sortable:false}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            gridview: true,//?
            loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            viewrecords : true,
            postData: {
            	state: "Y"
            }, 
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            width: pWidth-40,
            height: pHeight-120,
            resizable:false,
    		modal:true,
            gridComplete: function () {
                var ids = jQuery("#daily").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var type_id_str = jQuery("#daily").jqGrid('getRowData',id).type_id_str;//或得每一行的日志类型
                    var ee = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#daily').jqGrid('saveRow','" + id + "',look('" + id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;";
                    var se = "<a style='color: #505f91;cursor: pointer'" + "onclick=\"$('#daily').jqGrid('saveRow','" + id + "',upd('" + id + "','" + type_id_str + "'));\">编辑</a>&nbsp;&nbsp;&nbsp;";
                    var ce = "<a style='color: #505f91;cursor: pointer' href='#'" + "onclick=\"$('#daily').jqGrid('saveRow','" + id + "',del('" + id + "'));\">删除</a>";
                    jQuery("#daily").jqGrid("setRowData", id, {operation: ee+se + ce});
                }
            }
        });
//    jQuery("#daily").jqGrid('navGrid', '#pager1', {edit: false, add: false, del: false});
}
//重新加载
function orgaInit(){
	$("#daily").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//查看
function look(id)
{ 
	var flag = -1;
	mypopdiv(2,"培训日志",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + "/teachweb/selectDailyRecordById.action?id="+id+"&flag="+flag);

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
	   if(searchStr.length<1&&searchStr.length!=0){
			 myalert_error("最少输入一个字符!","30%","40%");
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
