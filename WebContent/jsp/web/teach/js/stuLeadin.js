var stateMCount='';
var trainPlanBefore='';
var stateMN='';
$(function(){
	init();
});

function init(){
	stuClsaaInit();
	$("#stuClass",window.parent.document).val($("#select").val());
	$("#stuClass",window.parent.document).text($("#select").find("option:selected").text());
	getStudentInfoState();
	if(stateMCount > 0){
		parent.removeGrayColor(trainPlanBefore);
	}else{
		parent.addGrayColor();
	}
	pageInit();
}

function stuClsaaInit(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/getStuClassList.action',
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		data = eval(data.rows);
			if(data.length>0){
				var str ='';
				for (var i=data.length-1;i>=0;i--){
					str += '<option value="'+data[i].id+'">'+data[i].stu_class+'</option>';
				}
				$("#select").html(str);
			}
     	}
	});
}

function changeSelect(){
	$("#stuClass",window.parent.document).val($("#select").val());
	$("#stuClass",window.parent.document).text($("#select").find("option:selected").text());
	getStudentInfoState();
	if(stateMCount > 0){
		parent.removeGrayColor(trainPlanBefore);
	}else{
		parent.addGrayColor();
	}
	reloadGrid();
}
//
function getStudentInfoState(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/getStudentInfoState.action',
	  	data: {
	  		stuClass: $("#select").val()
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		stateMCount = data.stateMCount;
  	 		trainPlanBefore = data.trainPlanBefore;
  	 		var stateNCount = data.stateNCount;
  	 		//有预编排的学生就查询预编排的
  	 		if(stateMCount > 0){
  	 			if(stateNCount > 0){
  	 				stateMN = "N";
  	 			}else{
  	 				stateMN = "M";
  	 			}
  	 		}else{
  	 			stateMN = "N";
  	 		}
     	}
	});
}

function pageInit() {
	$("#studentGrid").jqGrid(
	{
		url : ctx+'/userauthweb/queryStudentInfo.action',
		datatype : "json",
		colNames : ['ID','工号','姓名','性别','院校','轮转方案','操作'],
		colModel : [
		            {name : 'id',index:'id',width:5,fixed:true,hidden:true,key:true},
		            {name : 'user_code',index:'user_code',width : 200},
		            {name : 'stu_name',index:'stu_name',width : 200,align:'center'},
		            {name : 'stu_sex',index:'stu_sex',width : 200,align:'center'},
		            {name : 'stu_school_name',index:'stu_school_name',width : 200,align:'center'},
		            {name : 'tsc_name',index:'tsc_name',width : 200,align:'center'},
		            {name: 'operation', index: 'operation', width: 160, align: "center"}
	            ],
        rowNum : 200,
        rowList : [200, 500, 1000, 2000, 5000],
        pager : '#studentToolbar',
        viewrecords : true,
        mtype: 'POST',
        postData: {
        	stuClass: $("#select").val(),
        	state: stateMN
        }, 
        width: pWidth-20,
        height: pHeight-350,
		autowidth:true,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		gridComplete:function(){
			var ids=$('#studentGrid').jqGrid('getDataIDs');
			for(var i = 0; i < ids.length ; i ++){
			    var id = ids[i];
			    $("#"+id).attr("style","cursor:pointer");
			    var re = "<a style='color: #505f91;cursor: pointer' onclick='deleteStu("+id+")'>删除</a>";
                jQuery("#studentGrid").jqGrid("setRowData", id, {operation: re});
	        }
			$("#stuCount",window.parent.document).val(ids.length);
	    }
	}).navGrid("#studentToolbar", {edit : false,add : false,del : false,search:false,refreshstate:'current'}).navButtonAdd("#studentToolbar", {caption: "新增用户", buttonicon:"ui-icon-plus", onClickButton: userAdd, position: "first"});
	
}


//添加学生
function userAdd(){
	var stu_class=$("#select").val();
 parent.stuAdd(stu_class);
}

//删除导入的学生
function deleteStu(id){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/deleteStu.action',
	  	dataType: 'json',
	  	data:{id:id},
	  	async: false,
  	 	success:function(data) {
  	 		if(data.success==true){
				myalert_success("删除成功！","15%","40%",function(index){
					reloadGrid();
					layer.closeAll();
				});
  	 		}else{
				myalert_error("删除失败！","30%","40%",function(index){
					layer.closeAll();
				});
  	 		}
     	}
	});
}

//重新加载
function reloadGrid(){
	$("#studentGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	stuClass: $("#select").val(),
        	state: stateMN
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

function import_btn(){
	var content = '<div>'+
						'<div style=" margin-left: 70px;margin-top:30px;height:30px;" ><span style="float:left; line-height:25px">导入excel文件：</span>'+
						'<input style="float:left;" name="file" type="file" id="file" /></div>'+
		   				'<div style="height:40px;margin-top:30px;text-align:center;"><button class="myBtn1" style="margin-right:30px;width: 100px;height: 25px;" id="export_btn" onclick="uploadExcel()">上传</button>'+
		   				'<button class="myBtn1" style="width: 100px;height: 25px;" id="export_btn" onclick="downExcel()">下载</button></div>'+
				   '</div>';
	importpup = mypopdiv(1,"导入学生",'450px','170px','','','Y',content);
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
        url: ctx+'/userauthweb/uploadStuInfo.action?state=N&&stuClassId='+$("#select").val()+'&&stuClass='+$("#select").text(), 
        type: 'post',
        secureuri: false,
        fileElementId: 'file', // 上传文件的id、name属性名
        dataType: 'json',
        success: function(res){
       	layer.close(laSh);
        	if(res.data=="上传成功"){
	            myalert_success(res.data,'43%','43%');
	            getStudentInfoState();
	        	if(stateMCount > 0){
	        		parent.removeGrayColor(trainPlanBefore);
	        	}else{
	        		parent.addGrayColor();
	        	}
	            reloadGrid() ;
        	}else{
        		var strs1=new Array();
        		strs1=res.data.split(";");
        		var content1='<div style="width:100%;height:100%;overflow: auto;"><table cellspacing="0" style="text-align: center; width:100%"><tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 35px;"><th style="color:#1C9BCD;font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;line-height: 23px;">导入失败的行数及原因</th></tr>';
	  	 	 	for ( var int = 0; int < strs1.length; int++) {
	  	 	 		content1+='<tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 30px;"><td style="font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;">'+strs1[int]+'</td></tr>'; 			  	 		  
	  	 	 	}
	  	 	 	content1+='</table></div>';
        		importpup=mypopdiv(1,"上传错误","400px","300px",(pHeight-300)/2,(pWidth-400)/2,'Y',content1);
        		getStudentInfoState();
	        	if(stateMCount > 0){
	        		parent.removeGrayColor(trainPlanBefore);
	        	}else{
	        		parent.addGrayColor();
	        	}
        		reloadGrid() ;
        	}
        },
        error: function(data){ 
        	myalert_error("上传异常！");
        }
    });
	closeMyLoading(importpup);
}

function addStuClass(){
	var content = '<div style="float:left; margin: 30px 10px 30px 20px;">'
		+ '届次：<input id="stuclass" name="stuclass" type="text" onclick='
		+'"WdatePicker({startDate:\'%y-%M-%d %h:%m:%s\',dateFmt:\'yyyyMMdd\',alwaysUseStartDate:true})"'
		+ 'readonly="readonly" class="myDateInput" placeholder="点击选择届次..."/>'
		+'<a style="margin-left:15px;" class="layui-layer-btn0" onclick="submitStuClass()">确定</a>'
		+ '</div>';
	mypopdiv(1, "新增届次", '400px', '150px','30%', '20%', 'Y', content);
}

function submitStuClass(){
	$.ajax({
  		type: 'POST',
	  	url : ctx+'/userauthweb/addStuClassInfo.action',
	  	dataType: 'json',
	  	data:{stuClass:$("#stuclass").val()},
	  	async: false,
  	 	success:function(data) {
  	 		if(data.success==true){
				myalert_success("新增届次成功！","15%","40%",function(index){
					init();
					layer.closeAll();
				});
  	 		}else{
				myalert_error("新增届次失败！","30%","40%",function(index){
					layer.closeAll();
				});
  	 		}
     	}
	});
}
