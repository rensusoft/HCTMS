$(function () {
    //页面加载完成之后执行
    pageInit();
    $(".leftBtn").click(
        function(){
            $(this).parent(".addDiv").addClass("displayDiv").siblings(".jqDiv").removeClass("displayDiv");
        }
    );
    $(".finBtn").click(
        function(){
            var str="<table class='scoreTable'><tr><td>得分：</td><td>80分</td></tr><tr><td>答对：</td><td>48</td></tr><tr><td>答错：</td><td>2</td></tr><tr><td colspan='2' style='text-align: center;padding: 15px 0'><a class='layui-layer-btn0 backBtn'>完成</a></td></tr></table>";
            $("#wrap").html(str);
        }
    );
    $(".addBtn").click(
        function(){
            layer.open({
                type: 2,
                title:"新增测试",
                content: ctx+"/jsp/web/teach/newTest.jsp",
                area: ['800px', '480px']
            });
        }
    )
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#StuQuesInfoGrid").jqGrid(
        {
            url: ctx+'/exerweb/queryExerInfo.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id','时间', '题目', '分数','状态','','操作'],//jqGrid的列显示名字
            colModel: [
                   {name : 'id',index:'id',key:true,hidden:true},
                   {name: 'time', index: 'time', width: 30,align: "center"},
                   {name: 'title', index: 'title',  align: "center"},
                   {name: 'ques_sco', index: 'ques_sco', width: 30, align: "center"},
                   {name: 'cstate', index: 'cstate', width: 30, align: "center"},
                   {name: 'state', index: 'state', width: 30,hidden:true},
                   {name: 'operation', index: 'studentType', width: 30, align: "center",sortable:false}
            ],
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: '#StuQuesInfoPager',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: pWidth-30,
            height: pHeight-100,
            loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            gridComplete: function () {
                var ids = jQuery("#StuQuesInfoGrid").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var rowData = $("#StuQuesInfoGrid").jqGrid('getRowData', id);
                    var re = "";
    	    		if(rowData['state']=="Y"){
    	    			re = "<a style='color: #505f91;cursor: pointer' onclick='answer("+id+")'>解答</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='delStuQues("+id+")'>删除</a>";
                    }
    	    		if(rowData['state']=="E"){
    	    			re = "<a style='color: #505f91;cursor: pointer' href='"+ctx+"/jsp/web/teach/checkTest.jsp?qeid="+id+"'>查看</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='delStuQues("+id+")'>删除</a>";
                    }
                    jQuery("#StuQuesInfoGrid").jqGrid("setRowData", id, {operation: re});
                }
            }
        });
    jQuery("#StuQuesInfoGrid").jqGrid('navGrid', '#StuQuesInfoPager', {edit: false, add: false, del: false,search:false});
}
function reloadGrid(){
	$("#StuQuesInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {}, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
function closeMyWindows(){
	layer.closeAll();
}
function answer(id){
	if(id!=""){
		 $("#seqid").val(id);
		 $.ajax({
			type: 'POST',
			url :  ctx+'/exerweb/getExerQuesInfo.action',
			dataType: 'json',
			async:false, 
			data:{
				qeid:id
			},
			success:function(data) {
				if(data.success){
					data = eval(data.data);
		   	      	if(data != null && data.length>0){
		   	      		var countStr = '';
	   	 				var str ='';
	   	 				$("#tNum").val(data.length);
		   	      		for(var i=0;i<data.length; i++){
		   	      			var id = data[i].qq_id;
		   	      			countStr += '<span id="spid'+(i+1)+'" onclick="btnClick('+(i+1)+')">'+(i+1)+'</span><input type="hidden" id="sp'+(i+1)+'" value="'+id+'" />';
		   	      			if(i==0){
		   	      				addQuesStr((i+1),id);
		   	      				addOptionStr(id);
		   	      			}
		   	      		}
		   	      		$("#quesCount").html(countStr);
   	      				$("#spid"+1).attr("class","clicked");
		   	      	}
				}
			}
		});
	}
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
}
function addQuesStr(i,id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQuesInfoById.action',
		dataType: 'json',
		async:false, 
		data:{
			id:id
		},
		success:function(data) {
   	      	if(data.data!=null){
   	      		data = data.data;
   	      		$("#quesCon").text(i+'.'+data.ques_content);
   	      		$("#qNum").val(i);
			}
		}
	});
	var str='';
	if(i==1&&$("#tNum").val()>1){
		str = '<a class="layui-layer-btn0" onclick="nextClick()">下一题</a>';
	}else if(i==$("#tNum").val()){
		if($("#tNum").val()>1){
			str = '<a class="layui-layer-btn0" onclick="backClick()">上一题</a>&nbsp;&nbsp;&nbsp;&nbsp;'+
	         	'<a class="layui-layer-btn0 finBtn" onclick="submitClick()">提交</a>';
		}else{
			str = '<a class="layui-layer-btn0 finBtn" onclick="submitClick()">提交</a>';
		}
	}else{
		str = '<a class="layui-layer-btn0" onclick="backClick()">上一题</a>&nbsp;&nbsp;&nbsp;&nbsp;'+
         	'<a class="layui-layer-btn0" on onclick="nextClick()">下一题</a>'
	}
	$('.quesbtn').html(str);
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getAnswerInfoByQqid.action',
		dataType: 'json',
		async:false, 
		data:{
			qid:id
		},
		success:function(data) {
   	      	if(data.data!=null){
   	      		data = data.data;
   	      		$("#aid").val(data.id);
			}
		}
	});
}
function addOptionStr(id){
	 $.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getOptionInfoByQqid.action',
		dataType: 'json',
		async:false, 
		data:{
			qid:id
		},
		success:function(data) {
      		var str='';
   	      	if(data.success){
   	      		data = eval(data.data);
   	      		for(var i=0;i<data.length; i++){
   	      			str += '<p id='+data[i].id+'>'+
   	      						data[i].option_flag+'.'+data[i].option_content+
                     '</p>';
   	      		}
			}
      		$("#quesOpt").html(str);
		}
	});
	 $(".answerDiv p").click(function(){
		 $(this).addClass("clicked").siblings().removeClass("clicked");
		 $("#selAnswer").val($(this).attr("id"));
	 });
}
function btnClick(num){
	var onum = $("#qNum").val();
	$('.quesNum span').each(function(index){
		$(this).removeAttr("class");
	});
	$("#spid"+num).attr("class","clicked");
	var id = $("#sp"+num).val();
	addQuesStr(num,id);
	addOptionStr(id);
	saveAnswer(onum);
}
function nextClick(){
	var num = parseInt($("#qNum").val())+1;
	btnClick(num);
}
function backClick(){
	var num = parseInt($("#qNum").val())-1;
	btnClick(num);
}
function saveAnswer(num){
	if($("#selAnswer").val()!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/exerweb/answerExerQues.action',
			dataType: 'json',
			data:{
				seqid:$("#seqid").val(),
				stu_answer:$("#selAnswer").val(),
				aid:$("#aid").val()
			},
			success:function(data) {
	   	      	if(data.success){
	   	      		var str = $("#spid"+num+"").html();
	   	      		$("#spid"+num+"").html('<b>√</b>'+str);
	   	      		$("#selAnswer").val("");
				}
			}
		});
	}
}
function submitClick(){
	saveAnswer($("#qNum").val());
	$.ajax({
		type: 'POST',
		url :  ctx+'/exerweb/finishExerQues.action',
		dataType: 'json',
		data:{
			seid:$("#seqid").val()
		},
		success:function(data) {
   	      	if(data.success){
				myalert_success("提交成功！","30%","40%",function(index){
	   	      		$(".jqDiv").removeClass("displayDiv");
	   	      		$(".addDiv").addClass("displayDiv");
	   	      		reloadGrid();
	   	      		closeMyWindows();
				});
			}else{
				myalert_error("提交失败！","30%","40%",function(index){
					closeMyWindows();
				});
			}
		}
	});
}
function delStuQues(id){
	if(id!=""){
		 $.ajax({
			type: 'POST',
			url :  ctx+'/exerweb/deleteExerInfo.action',
			dataType: 'json',
			data:{
				seid:id,
				state:"X"
			},
			success:function(data) {
	   	      	if(data.success){
		   	      	myMsg("删除成功！",'30%','30%');
	   	      		reloadGrid();
				}else{
		   	      	myMsg("删除失败！",'30%','30%');
				}
			}
		});
	}
}
