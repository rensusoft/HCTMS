var result = false;
$(function(){
    var width=$(".process").width();
    var iWidth=(width-500)/2;
    $(".process ul li i").width(iWidth);
	$("#stuClassName").text($("#stuClass",window.parent.document).text());
	$("#configName").text($("#planConfig",window.parent.document).text());
	$("#stuClass").val($("#stuClass",window.parent.document).val());
	pageInit();
	planInit();
});
var lastSel;
var stu_name;
function pageInit() {
	$("#userInfoGrid").jqGrid(
	{
		url : ctx+'/userauthweb/getStudentInfo.action',
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
        	stuClass: $("#stuClass",window.parent.document).val()
        }, 
        width: pWidth,
        height: pHeight-310,
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
                if(state=="M"){
                	picture="<img src='../teach/img/ok.png'>";
                }else if(state=="N"){
                	picture="<img src='../teach/img/question.png'>";
                }
                jQuery("#userInfoGrid").jqGrid("setRowData", id, {act : picture});
                //
			    $("#"+id).attr("style","cursor:pointer");
	        }
			$("#userCount").text(ids.length);
	    },
	    ondblClickRow: function(id){
	    	if($("#planPic").attr('display')!='none'){
		    	$("#planGrid").css('display','block');
		    	$("#planPic").css('display','none');  
		    	$("#planGrid").css('width',$(".process").width()*0.98); 
		    	$("#planBeforeGrid").setGridWidth($(".process").width()*0.98);
	    	}
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
		url : ctx+'/configweb/getTrainPlanBefore.action',
		datatype : "json",
		colNames: ['ID','config_id','科室','科室ID', '开始时间', '结束时间','时长（月）'],
        colModel: [
            {name : 'id',index:'id',width:5,fixed:true,hidden:true,key:true},
           	{name : 'config_id',index:'config_id',hidden:true},
            {name: 'orga_name', index: 'orga_name', align: "center"},
            {name: 'train_dept_id', index: 'train_dept_id', align: "center",hidden:true},
            {name: 'train_start_date', index: 'train_start_date', align: "center"},
            {name: 'train_end_date', index: 'train_end_date', align: "center"},
            {name: 'days', index: 'days', align: "center"}
        ],
        rowNum : 200,
        rowList : [200, 500, 1000, 2000, 5000],
        pager : '#planBeforeToolbar',
        viewrecords : true,
        mtype: 'POST',
        width: pWidth,
        height: pHeight-340,
		autowidth:true,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		gridComplete:function(){
	    	$("#_empty","#planBeforeGrid").addClass("nodrag nodrop");
	    	
			var ids=$('#planBeforeGrid').jqGrid('getDataIDs');
			for(var i = 0; i < ids.length ; i ++){
			    var id = ids[i];
			    $("#"+id).attr("style","cursor:pointer");
	        }
	    }
	});
	
	$("#planBeforeGrid").tableDnD({
		scrollAmount:100, 
		topIndex:3,
		startIndex:0,
		onDrop:function(a, b){
			//a 为 table，b为拖动的行
			if(this.startIndex!=$(b).index()){
				changeTrainPlan();
			}
		},
		onDragStart:function(a, b){
			//a 为 table，b为拖动的行
			this.startIndex = $(b).index();
		},
		/*
			@parm  a:拖动的行
			@parm  b:释放鼠标左键时的行
			@return boolean  是否可以拖动
		*/
		onAllowDrop:function(a, b){
			if($(b).index()==0){
				return false;
			}else{
				return true;
			}

//			var $b = $(b), $bIndex = $b.index();
//			if( this.startIndex > this.topIndex && $bIndex > this.topIndex && $bIndex !== 0 ){
//				return true;
//			}else if( this.startIndex <= this.topIndex && $bIndex <= this.topIndex && $bIndex !== 0) {
//				return true;
//			}else{
//				return false;
//			}
		}
	});
	$("#planBeforeGrid").jqGrid().navGrid("#planBeforeToolbar", {edit: false, add: false, del: false, search: false, refresh: false}).navButtonAdd(
			"#planBeforeToolbar", {caption: "微调", buttonicon:"ui-icon-pencil", onClickButton: resetTrainPlanBefore, position: "first"});
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
function resetTrainPlanBefore(){
	var stu_auth_id = lastSel;
	parent.resetTrainPlanBefore(stu_auth_id,stu_name);
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

function doTrainPlanBefore(){
	
	var content = '<div>'+
	    '<div style=" text-align:center;margin-top:60px;height:30px;" ><button class="myBtn1" style="margin-right:30px;width: 140px;height: 25px;" id="export_btn" onclick="trainPlanSystem()">系统自动编排</button><button class="myBtn1" style="margin-right:30px;width: 140px;height: 25px;" id="export_btn" onclick="trainPlanExcel()">导入Excel编排</button></div>'+
'</div>';
importpup = mypopdiv(1,"学生轮转方案编排",'500px','200px','','','Y',content);
}

function trainPlanSystem(){
	var laSh = layer.msg('预编排中，请稍候', {
	  icon: 16,
	  shade: [0.5,'#fff'],
	  skin: 'layui-layer-molv'
	});
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/configweb/doTrainPlanBefore.action',
	  	data:{
	  		stuClass:$("#stuClass",window.parent.document).val(),
	  		time:$("#stuClass",window.parent.document).text()
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		layer.close(laSh);
  	 		if(data.success==true){
  	 			myMsg("预编排成功！","15%","40%");
  	 			$("#layui-layer1").css('display','none');
        		$("#layui-layer-shade1").css('display','none');
                $(".processStep1").css("background","#505f91");
		    	$("#planPic").css('display','block');
		    	$("#planGrid").css('display','none');
		    	refreshGrid();
  	 			result = true;
  	 		}else{
  	 			myMsg("预编排失败！","30%","40%");
  	 			result = false;
  	 		}
     	}
	});
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

function trainPlanExcel(){
	var content = '<div class="div1">'+
						'<div style=" margin-left: 70px;margin-top:30px;height:30px;" ><span style="float:left; line-height:25px">导入excel文件：</span>'+
						'<input style="float:left;" name="file" type="file" id="file" /></div>'+
		   				'<div style="height:40px;margin-top:30px;text-align:center;"><button class="myBtn1" style="margin-right:30px;width: 100px;height: 25px;" id="export_btn" onclick="uploadExcel()">上传</button>'+
		   				'<button class="myBtn1" style="width: 100px;height: 25px;" id="export_btn" onclick="downExcel()">下载</button></div>'+
				   '</div>';
	importpup = mypopdiv(1,"导入学生轮转方案",'450px','170px','','','Y',content);
}

function downExcel() {
	$("#exportForm").submit();
}

function uploadExcel(){
	var fileName = $("#file").val();
	if(fileName==""||fileName==null){
		myalert_error('请选择上传excel文档！','20%','30%');
		return ;
	}
	var laSh = layer.msg('上传中，请稍候', {
		  icon: 16,
		  shade: [0.5,'#fff'],
		  skin: 'layui-layer-molv'
		});
	$.ajaxFileUpload({
        url: ctx+'/userauthweb/uploadStuTrainPlanInfo.action?stuClass='+$("#stuClassName").text(), 
        type: 'post',
        secureuri: false,
        fileElementId: 'file', // 上传文件的id、name属性名
        dataType: 'json',
        success: function(res){
       	layer.close(laSh);
        	if(res.data=="上传成功"){
        		myalert_success(res.data,'43%','43%');
        		$("#layui-layer1").css('display','none');
        		$("#layui-layer-shade1").css('display','none');
		    	$("#planPic").css('display','block');
		    	$("#planGrid").css('display','none');
		    	refreshGrid();
  	 			result = true;
        	}else{
        		var strs1=new Array();
        		strs1=res.data.split(";");
        		var content1='<div style="width:100%;height:100%;overflow: auto;"><table cellspacing="0" style="text-align: center; width:100%"><tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 35px;"><th style="color:#1C9BCD;font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;line-height: 23px;">导入失败的行数及原因</th></tr>';
	  	 	 	for ( var int = 0; int < strs1.length; int++) {
	  	 	 		content1+='<tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 30px;"><td style="font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;">'+strs1[int]+'</td></tr>'; 			  	 		  
	  	 	 	}
	  	 	 	content1+='</table></div>';
        		importpup=mypopdiv(1,"上传错误","400px","300px",(pHeight-300)/2,(pWidth-400)/2,'Y',content1);
        	}
        },
        error: function(data){
        	myalert_error("上传异常！");
        }
    });
	closeMyLoading(importpup);
}
