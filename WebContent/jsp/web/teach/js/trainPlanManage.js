var result = false;
$(function(){
	stuClsaaInit();
});
var lastSel;
var stu_name;

function stuClsaaInit(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/getStuClassList.action',
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		data = eval(data.rows);
  	 		var str ='';
  	 		str+="<option value='-1'>--请选择学生届次--</option>";
			if(data.length>0){
				for (var i=data.length-1;i>=0;i--){
					str += '<option value="'+data[i].id+'">'+data[i].stu_class+'</option>';
				}
				$("#select").html(str);
			}
     	}
	});
}

function changeSelect(){
	pageInit();
	planInit();
}
function pageInit() {
	$("#userInfoGrid").jqGrid(
	{
		url : ctx+'/userauthweb/getStudentInfoForTrain.action',
		datatype : "json",
		colNames : ['用户ID','学号','state','','姓名'],
		colModel : [
		            {name : 'stu_auth_id',index:'stu_auth_id',width:5,fixed:true,hidden:true,key:true},
		            {name : 'user_code',index:'user_code',hidden:true},
		            {name : 'state',index:'state',hidden:true},
		            {name : 'act',index:'act',width: 50,align: "center"},
		            {name : 'stu_name',index:'stu_name',align:'center'}
	            ],
        rowNum : 200,
        rowList : [200, 500, 1000, 2000, 5000],
//        pager : '#userInfoToolbar',
        viewrecords : true,
        mtype: 'POST',
        postData: {
        	stuClass: $("#select").val()
        }, 
        width: pWidth,
        height: pHeight-110,
		autowidth:true,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		gridComplete:function(){
			var ids=$('#userInfoGrid').jqGrid('getDataIDs');
			for(var i = 0; i < ids.length ; i ++){
			    var id = ids[i];
			    var state = jQuery("#userInfoGrid").jqGrid('getRowData',id).state;
			    var picture='';
                if(state=="Y"){
                	picture="<img src='../teach/img/ok.png'>";
                }else if(state=="N"){
                	picture="<img src='../teach/img/question.png'>";
                }
                jQuery("#userInfoGrid").jqGrid("setRowData", id, {act : picture});
                //
			    $("#"+id).attr("style","cursor:pointer");
	        }
	    },
	    ondblClickRow: function(id){
		    	$("#planGrid").css('display','block');
	        if(id && id!==lastSel){
	        	reloadGrid(id);
	        	$('#userInfoGrid '+'#'+id).find("td").css("background-color","#5bc0de");
	        	$('#userInfoGrid '+'#'+id).find("td").css("color","#FFFFFF");
	        	$('#userInfoGrid '+'#'+lastSel).find("td").css("background-color","")
	        	$('#userInfoGrid '+'#'+lastSel).find("td").css("color","#000");
	        	lastSel=id; 
	        	stu_name=jQuery("#userInfoGrid").jqGrid('getRowData',id).stu_name;
	        }
	    }
	});
}
function refreshGrid(){
	$("#userInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	stuClass: $("#stuClass",window.parent.document).val()
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}


function planInit() {
	$("#planBeforeGrid").jqGrid({
		url : ctx+'/configweb/getTrainPlanNow.action',
		datatype : "json",
		colNames: ['ID','config_id','科室','科室ID', '开始时间', '结束时间','时长（天）'],
        colModel: [
            {name : 'id',index:'id',fixed:true,hidden:true,key:true},
           	{name : 'config_id',index:'config_id',hidden:true},
            {name: 'orga_name', index: 'orga_name', align: "center"},
            {name: 'train_dept_id', index: 'train_dept_id', align: "center",hidden:true},
            {name: 'train_start_date', index: 'train_start_date', align: "center"},
            {name: 'train_end_date', index: 'train_end_date', align: "center"},
            {name: 'days', index: 'days', align: "center"}
        ],
        rowNum : 20,
        rowList : [20, 50, 100],
        pager : '#planBeforeToolbar',
        viewrecords : true,
        mtype: 'POST',
        width: pWidth-200,
        height: pHeight-140,
//		autowidth:true,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		gridComplete:function(){
	    	
			var ids=$('#planBeforeGrid').jqGrid('getDataIDs');
			for(var i = 0; i < ids.length ; i ++){
			    var id = ids[i];
			    $("#"+id).attr("style","cursor:pointer");
	        }
	    }
	});
	
	$("#planBeforeGrid").jqGrid().navGrid("#planBeforeToolbar", {edit: false, add: false, del: false, search: false, refresh: false}).navButtonAdd(
			"#planBeforeToolbar", {caption: "微调", buttonicon:"ui-icon-pencil", onClickButton: resetTrainPlan, position: "first"});
}


function reloadGrid(stu_auth_id){
	$("#planBeforeGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	stu_auth_id: stu_auth_id
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}


//微调
function resetTrainPlan(){
	var stu_auth_id = lastSel;
	mypopdiv(2,"微调 [" + stu_name + "]",'1100px',(pHeight-120)+'px','50px',(pWidth-1000)/2,'N',ctx + '/jsp/web/teach/sharpTuningTrain.jsp?stu_auth_id='+ stu_auth_id)
}


function checkPlanBefore(){
	return createTrainPlan();
}


function createTrainPlan(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/configweb/createTrainPlan.action',
	  	data:{
	  		stuClass:$("#stuClass",window.parent.document).val()
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		if(data.success==true){
  	 			myMsg("生成轮转计划成功！","15%","40%");
  	 			result = true;
  	 		}else{
  	 			myMsg("生成轮转计划失败！","30%","40%");
  	 			result = false;
  	 		}
     	}
	});
	return result;
}


function changeTrainPlan(){
	var ids=$('#planBeforeGrid').jqGrid('getDataIDs');
	var sub=[];
	for(var i = 0; i < ids.length ; i ++){
		var rowData = $("#planBeforeGrid").jqGrid('getRowData', ids[i]);
		var item={};
		item.config_id=rowData['config_id'];
		item.train_dept_id=rowData['train_dept_id'];
		sub.push(item);
    }
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/configweb/changeTrainPlanBefore.action',
	  	data:{
	  		data:JSON.stringify(sub),
	  		stu_auth_id: lastSel,
	  		time:$("#stuClass",window.parent.document).text()
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		if(data.success==true){
				reloadGrid(lastSel);
  	 			myMsg("预编排微调成功！","15%","40%");
  	 		}else{
  	 			myMsg("预编排微调失败！","30%","40%");
  	 		}
     	}
	});
}

