var num='';
$(function () {
	ue = UE.getEditor('contents',{scaleEnabled:true});	
    //页面加载完成之后执行
    pageInit();
    $(".homeworkCont").width($("section").width() * 0.6);
    //if ($(".homeworkCont").is(":visible") == true) {
    //    $(".rightBtnSpan").click(
    //        function(){
    //            $(".homeworkCont").hide();
    //        }
    //    )
    //}
    if ($(".homeworkCont").is(":hidden")) {
        $(".rightBtnSpan").click(
            function(){
                $(".homeworkCont").toggle();
                $(".rightBtn").addClass("displayDiv");
            }
        );
        //$(".lf_cont").width(1850);
    }
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#rotateRule").jqGrid(
        {
        	url: ctx+'/teachweb/getTaskPubStu.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','标题', '类型', '状态', '发布时间','', '发布人','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', align: "center",key:true,hidden:true},
                {name: 'title', index: 'title', align: "center"},
                {name: 'task_type_name', index: 'task_type_name', width: 100,align: "center"},
                {name: 'progress_stateStr', index: 'progress_stateStr', width: 100, align: "center"},
                {name: 'publish_time', index: 'publish_time', width: 130, align: "center"},
                {name: 'progress_state', index: 'progress_state', align: "center",hidden:true},
                {name: 'user_name', index: 'user_name', width:100, align: "center"},
                {name: 'operation', index: 'operation', width:120, align: "center"}
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
            width: pWidth-49,
            height:pHeight-120,
            gridComplete: function () {
                var ids = jQuery("#rotateRule").jqGrid("getDataIDs");
                
                for (var i = 0; i < ids.length; i++) {
                  var   id = ids[i];
                  var progress_state = jQuery("#rotateRule").jqGrid('getRowData',id).progress_state;
                    var re = "<a style='color: #505f91;cursor: pointer'"+"onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',answer('" + id + "','" + progress_state + "'));\">作答</a>";
                    jQuery("#rotateRule").jqGrid("setRowData", id, {operation: re});
                }
            }
        });
    jQuery("#rotateRule").jqGrid('navGrid', '#pager1', {edit: false, add: true, del: true});
}
function answer(id,progress_state){
	num=id;
   
    if(id == ''){
		myalert_error('请选择作业！');
		return false;
	}
	if(progress_state =="2"){
		myalert_error('此作业已作答!');
		return false;
	}
	if(progress_state=="3"){
		myalert_error('此作业已评分!');
		return false;
	}
	if(progress_state=="1"){
		 $(".rightBtn").removeClass("displayDiv");
		    $(".homeworkCont").toggle();
		    if ($(".homeworkCont").is(":visible")) {
		        //$(".lf_cont").width(950);
		        $(".homeworkCont").fadeIn();
		    }	
	}
    $.ajax({
       type:'post',
       url:ctx+"/teachweb/answerTask.action",
       data:{
    	 id:id  
       },
       dataType:'json',
       success:function(res){
    	   $("#title").text(res.data.title);
    	   $("#content").html(res.data.content);
    	   UE.getEditor('contents').setContent("");
       }
    });
}

function saveContent() {
	var content=UE.getEditor('contents').getContent();
	if(content==''){
		myalert_error('请输入作答内容！');
		return false;
	}
	if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!");
		return false;
	}
	var url=ctx+"/teachweb/saveStuContent.action";
	var postData={
			id:num,
			content:content
	};
	
	doAjax(url,postData,2,function(res){
	 if(res.success == true){
		 myalert_success(res.data,"30%","40%",function(){
			 $(".rightBtn").addClass("displayDiv");
			 orgaInit();
			 layer.closeAll();
		 });
	 }else{
		 myalert_error(res.data,"30%","40%");
	 }
});
}

function orgaInit(){
	$("#rotateRule").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//搜索
function keywordSearch() {
	   var task_type_code=$("#task_type_code").val();
	   var progress_state=$("#progress_state").val(); 
	   var start_date= $("#start_date").val();
	   var end_date= $("#end_date").val();
	   $("#rotateRule").jqGrid('setGridParam',{
	        datatype: 'json',
	        postData: {
	        	start_date: start_date,
	        	end_date: end_date,
	        	task_type_code:task_type_code,
	        	progress_state:progress_state,
	        	state: "Y",
	        }, //发送数据
	        page: 1
	    }).trigger("reloadGrid"); //重新载入
}