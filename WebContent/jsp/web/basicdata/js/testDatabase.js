$(function(){
	qkbInit();
	init();
    pageInit();
    $(".arr").click(
        function () {
            var body = $(this).next();
            $(body).slideToggle();
            $(this).toggleClass("arrdown");
        }
    );
    $(".arr2").click(
        function () {
        	if($(this).hasClass("arrdown2")){
	            var body = $(this).parent().next();
	            $(body).slideToggle();
	            $(this).toggleClass("arrdown2");
				$("#QuesInfoGrid").setGridHeight(pHeight-200);
        	}
        }
    );
	//导入
	$("#import_btn").click(function(){
		var content = '<div style="float:left; margin: 30px 10px 30px 30px;">'+
							'<span style="float:left; line-height:25px">导入excel文件：</span>'+
							'<input style="float:left;" name="file" type="file" id="file" />'+
			   				'<button class="btn btn-info btnSearch" style="float:left; margin-top: 20px; width: 100px;height: 25px;padding: 3px 15px;" id="export_btn" onclick="downExcel()">导出模板</button>'+
							'<button class="btn btn-info btnSearch" style="float:left; margin-left: 50px; margin-top: 20px; width: 100px;height: 25px;padding: 3px 15px;" id="ok_btn" onclick="uploadExcel()">上传模板</button>'+
					   '</div>';
		importpup = mypopdiv(1,"教学任务导入",'350px','200px','15%','30%','Y',content);
	});
})
function downExcel() {
	$("#exportForm").submit();
}
function qkbInit(){
	var setting = {
	    check:{
	        enable:true
	    },
	    data:    {
	    	simpleData:{
	        	enable:true
	    	}
	    },
	    callback:{
	        onClick:function (e,treeId,treeNode){
	        	$(".cotTitle").text(treeNode.name);
	        	if(treeNode.id==0)
	        		treeNode.id="";
  	  			$("#qkbId").val(treeNode.id);
      	  		reloadGrid();
	        }
	    }
	};
	var zNodes ="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQeknowledgeList.action',
		dataType: 'json', 
		async:false, 
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
	   	      		var str ='{"id":0, "pId":"", "name":"全部", "open":true},';
	   	      		for(var i=0;i<data.length; i++){
	   	      			var open = false;
	   	      			if(data[i].pid==0)
	   	      				open = true;
	      				str += '{"id":'+data[i].id+', "pId":'+data[i].pid+', "name":"'+data[i].name+'", "open":'+open+'},';
	   	      		}
		   	      	if(str!=""){
		   	       		zNodes = "["+str.substring(0,str.length-1)+"]";
		   	       	}
	   	      	}
			}else{
				myMsg("知识库为空！",'30%','30%');
				return;
			}
		}
	});
	$.fn.zTree.init($("#treeDemo"), setting, eval(zNodes));
}
function init(){
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQuesTypeList.action',
		dataType: 'json',
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
	   	      		var str = "<option value=''>全部</option>";
	   	      		for(var i=0;i<data.length; i++){
	   	      			str += "<option value='"+data[i].id+"'>"+data[i].type_name+"</option>";
	   	      		}
	   	      		$("#quesType").html(str);
	   	      	}
			}else{
				myMsg("题型为空！",'30%','30%');
				return;
			}
		}
	});
	$(".cotTitle").text("全部");
	$("#qkbId").val("");
}
function pageInit() {
    //创建jqGrid组件
    jQuery("#QuesInfoGrid").jqGrid(
        {
            url: ctx+'/quesweb/queryQuesInfo.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['题号', '题型', '分值','难度','区分度','抽取次数','题目内容','更新日期','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', width: 30, align: "center"},
//                {name: 'group_num', index: 'group_num', width: 30, align: "center"},
                {name: 'type_name', index: 'type_name', width: 30, align: "center"},
                {name: 'sco_num', index: 'sco_num', width: 30, align: "center"},
                {name: 'difficulty_num', index: 'difficulty_num', width: 30, align: "center"},
                {name: 'different_num', index: 'different_num', width: 30, align: "center"},
                {name: 'use_num', index: 'use_num', width: 30, align: "center"},
                {name: 'ques_content', index: 'ques_content', width: 50, align: "center"},
                {name: 'update_time', index: 'update_time', width: 30, align: "center"},
                {name: 'operation', index: 'studentType', width: 40, align: "center"}
            ],
            rowNum: 100,//一页显示多少条
            rowList: [100, 200, 500,1000],//可供用户选择一页显示多少条
            pager: '#QuesInfoPager',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: pWidth*0.736,
            height: pHeight-210,
            multiselect: true,
            gridComplete: function () {
                var ids = jQuery("#QuesInfoGrid").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var re = "<a style='color: #505f91;cursor: pointer' class='checkBtn' onclick='testContBlock("+id+")'>查看</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='editQues("+id+")'>编辑</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='delQues("+id+")'>删除</a>";
                    jQuery("#QuesInfoGrid").jqGrid("setRowData", id, {operation: re});
                }
            }
        });
    jQuery("#QuesInfoGrid").jqGrid('navGrid', '#QuesInfoPager', {edit: false, add: false, del: false,search:false});
}
function reloadGrid(){
	$("#QuesInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y",
        	type_id: $("#quesType").val(),
        	qkb_id: $("#qkbId").val(),
        	ssco_num: $("#ssco_num").val(),
        	esco_num: $("#esco_num").val(),
        	sdifficulty_num: $("#sdifficulty_num").val(),
        	edifficulty_num: $("#edifficulty_num").val(),
        	sdifferent_num: $("#sdifferent_num").val(),
        	edifferent_num: $("#edifferent_num").val()
        	
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

function quesContent(id){
//	if(id==""){
//		var ids=$("#QuesInfoGrid").jqGrid('getGridParam','selarrrow');
//		if(ids!=""){
//			if(ids.length==1){
//				id = ids.join("");
//			}else{
//				myMsg("请选择单个问题查看问题详情！",'30%','30%');
//				return false;
//			}
//		}else{
//			myMsg("请选择问题查看问题详情！",'30%','30%');
//			return false;
//		}
//	}
	if(id!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/quesweb/getQuesInfoById.action',
			dataType: 'json',
			data:{
				id:id
			},
			success:function(data) {
				var str ="";
	   	      	if(data.data != null){
					data = data.data;
	   	      		str += "<table>"+
	   	      			"<tr><td>题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td><td>"+data.id+"</td></tr>"+
	   	      			"<tr><td>所属分类：</td><td>"+data.type_id+"</td></tr>"+
	   	      			"<tr><td>指&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标：</td><td>"+data.id+"</td></tr>"+
	   	      			"<tr><td>题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目：</td></td><td>"+data.ques_content+"</td></tr>"+
	   	      			"</table>";
	   	      	}
	      		$("#quesContent").html(str);
			}
		});
		return true;
	}
	return false;
}

function testContBlock(id){
	if(quesContent(id)){
		if(!$(".arr2").hasClass("arrdown2")){
			var body = $(".arr2").parent().next();
			$(body).slideToggle();
			$(".arr2").toggleClass("arrdown2");
			$("#QuesInfoGrid").setGridHeight(pHeight-350);
		}
	}else{
		myMsg("查看异常！",'30%','30%');
		return;
	}
}
function delQues(id){
	if(id!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/quesweb/modifyQuesInfo.action',
			dataType: 'json',
			data:{
				qid:id,
				state:"X"
			},
			success:function(data) {
				if(data.success){
		   	 		myMsg("删除成功！",'30%','30%');
		   	 		reloadGrid();
	   	      	}else{
		   	 		myMsg("删除异常！",'30%','30%');
		   			return;
		   		}
			}
		});
	}else{
		myMsg("删除异常！",'30%','30%');
		return;
	}
}
function editQues(id){
	 mypopdiv(2,"问题编辑",'900px',(pHeight-300)+'px','50px',(pWidth-1000)/2,'N',ctx+'/jsp/web/basicdata/testContent.jsp?id='+ id);
}
function addQues(){
	 mypopdiv(2,"问题编辑",'900px',(pHeight-300)+'px','50px',(pWidth-1000)/2,'N',ctx+'/jsp/web/basicdata/testContent.jsp');
}
function closeMyWindows(){
	layer.closeAll();
}
function uploadExcel(){
	var fileName = $("#file").val();
	if(fileName==""){
		myMsg('请选择上传excel文档！','30%','30%');
		return;
	}
	var laSh = layer.msg('上传中，请稍候', {
		  icon: 16,
		  shade: [0.5,'#fff'],
		  skin: 'layui-layer-molv'
		});
	$.ajaxFileUpload({
        url: ctx+'/quesweb/uploadQuesExcel.action', 
        type: 'post',
        secureuri: false,
        fileElementId: 'file', // 上传文件的id、name属性名
        data:{},//一同上传的数据  
        dataType: 'json',
        success: function(data){
        	if(data.success){
        		layer.close(laSh); 
        		myalert_success("上传成功！","30%","40%",function(index){
	   	      		reloadGrid();
	   	      		closeMyWindows();
				});
        	}else{
            	myalert_error("上传失败！","30%","40%",function(index){
	   	      		closeMyWindows();
    			});
        	}
        }
    });
	closeMyWindows();
}