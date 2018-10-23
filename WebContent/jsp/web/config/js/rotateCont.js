var content1;
$(function() {
	$.ajax({
				type : 'POST',
				url : ctx + '/configweb/getStuType.action',
				dataType : 'json',
				success : function(data) {
					var option = "&nbsp;&nbsp;&nbsp;<label class='control-label'>&nbsp;&nbsp;&nbsp;&nbsp;人员类型：</label><select style='width:150px;display:inline-block;' name='identity' class='form-control'onchange='change();' id='stuTypeChangId'>";
					option+="<option value='-1'>--请选择--</option>";
					for ( var int = 0; int < data.stuTypelist.length; int++) {
						option += "<option value=" + data.stuTypelist[int].type_name
								+ " >" + data.stuTypelist[int].type_name
								+ "</option>";
					}
					option += "</select>&nbsp;&nbsp;&nbsp;&nbsp;";
					// 得到人员类型的下拉
					document.getElementById("StuType").innerHTML = option;
					var option1 = "<label class='control-label'>培训专业：</label><select style='width:150px;display:inline-block;' name='identity' class='form-control'onchange='change();' id='zhuanYeChangId'>";
					option1+="<option value='-1'>--请选择--</option>";
					for ( var int = 0; int < data.sysDictSubs.length; int++) {
						option1 += "<option value="
								+ data.sysDictSubs[int].item_type_name + " >"
								+ data.sysDictSubs[int].item_type_name + "</option>";
					}
					option1 += "</select>";
					// 得到培训专业的下拉
					document.getElementById("ZhuanYe").innerHTML = option1;
				}
			});
	// 页面加载完成之后执行
	pageInit();
	
	//右击菜单触发事件
	$('#content').contextMenu('myMenu', {
		bindings: 
        {
          'delete': function(){
  			deleteTrainConfig();
          }, 
    
      'using': function(){
			updateAvail();
      },
  'noUsing': function(){
		updateNoAvail();
  }
}
});
//	//右击菜单触发事件
//	$('#content').contextMenu('myMenu', {
//	});
//	
//	//右击菜单触发事件
//	$('#content').contextMenu('myMenu', {
//});
});
function pageInit() {
	// 创建jqGrid组件
	$("#content").jqGrid(
		{
			url : ctx + '/configweb/getTrainConfig.action',// 组件创建完成之后请求数据的url
			datatype : "json",// 请求数据返回的类型。可选json,xml,txt
			colNames : [ '状态','是否启用','序号', '类型', '培训方案名称', '培训专业', '年限', '操作' ],// jqGrid的列显示名字
		    colModel : [ // jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				{name : 'state',index : 'state',width : 50,align : "center",hidden: true},
				{name : 'availability',index : 'availability',width : 50,align : "center",hidden: true},
				{name : 'id',index : 'id',width : 50,align : "center",key : true}, 
				{name : 'stu_type_name',index : 'stu_type_name',width : 90,align : "center"}, 
				{name : 'name',index : 'name',width : 120,align : "center"}, 
				{name : 'major',index : 'major',width : 80,align : "center"},
				{name : 'time_long',index : 'time_long',width : 50,align : "center",sortable:false},
				{name : 'act',index : 'act',width : 80,align : "center",sortable:false}
				],
						rowNum : 50,// 一页显示多少条
						rowList : [ 50, 100, 150 ],// 可供用户选择一页显示多少条
						viewrecords : true,
						mtype: 'POST', 
						pager : '#contentPager',// 表格页脚的占位符(一般是div)的id
						postData:{ stuTypeId:"-1",item_code:"-1"},
						width : pWidth-10,
						height : pHeight - 170,
						resizable:false,
						modal:true,
						loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
						sortable: true,
						sortname: 'id', //设置默认的排序列
						sortorder : "asc",// 排序方式,可选desc,asc
						mtype : "post",// 向后台请求数据的ajax的类型。可选post,get
						gridComplete : function() {
							var ids = jQuery("#content").jqGrid("getDataIDs");
							for ( var i = 0; i < ids.length; i++) {
								var id = ids[i];
								var rowData=$("#content").jqGrid("getRowData",id);
								var availability=rowData.availability;
								var myurl="rotateMain.jsp?id="+id+"&name="+$("#content").jqGrid("getRowData",id).name+"";
								var onclick="onclick=\"location='"+encodeURI(myurl)+"'\">";
								if(availability=="X"){
									$('#'+id).css("background-color","#C7C7C7");
									$('#'+id).css("color","#FAFAFA");
									onclick=">";
								};
								var a="<b class='qm'>？</b>";
								if(rowData.state=='Y'){
									a="";		
								};
								var re = "<a title='' class='btnClick' style='color:#7C87AD'"
										+ onclick+a+"轮转规则</a>&nbsp;&nbsp;&nbsp;&nbsp;";								
								
								jQuery("#content").jqGrid("setRowData", id, {
									act : re 
								});
							}
						}
					});
	$("#content").jqGrid().navGrid("#contentPager", {edit : false,add : false,del : false,search : false,refresh : true});
}

//改变下拉      换jqgrid信息   刷新
function  change(){
	var postDate={
		stuTypeId:$("#stuTypeChangId").val(),
		item_code:$("#zhuanYeChangId").val()
	};
	$("#content").jqGrid('setGridParam',{
		datatype:'json',
		postData:postDate,//发送数据
		page:1
	}).trigger("reloadGrid");
};


//添加轮转规则 
function addTrain(){
	content1 = mypopdiv(2,"新增轮转规则",'300px','300px',(pHeight-220-80)/2,(pWidth-300)/2,'Y',ctx+"/jsp/web/config/addScheme.jsp",function endfun(){
		change();
	});
}

function deleteTrainConfig(){
	myalert_success("是否确认删除？","","",function(){
		var ids1=$("#content").jqGrid('getGridParam','selrow');
		$.ajax({
			type:"POST",
			url:ctx+'/configweb/deleteTrainConfig.action',
			data:{id:ids1},
			dataType : 'json',
			success:function(data){

				change();
				myalert_success(data.data,"","",function(){
					layer.closeAll();
					change();
				});	
				layer.closeAll();
			}		
		});
	});
};

function updateAvail(){
	var ids2=$("#content").jqGrid('getGridParam','selrow');
	$.ajax({
		type:"POST",
		url:ctx+'/configweb/updateAvail.action',
		data:{id:ids2},
		dataType : 'json',
		success:function(data){
			change();
			myalert_success(data.data,"","",function(){
				layer.closeAll();
				change();
			});	
		}		
	});
};

function updateNoAvail(){
	var ids3=$("#content").jqGrid('getGridParam','selrow');
	$.ajax({
		type:"POST",
		url:ctx+'/configweb/updateNoAvail.action',
		data:{id:ids3},
		dataType : 'json',
		success:function(data){
			change();
			myalert_success(data.data,"","",function(){
				layer.closeAll();
				change();
			});	
		}		
	});
}


function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			layer.closeAll();
		});
	}else{
		myalert_error(res.data,'','');
	}
	
	
}


