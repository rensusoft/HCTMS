var relesepup="";
$(function(){
	FormInfoGridInit();
});


/**
 * 待审核列表初始化
 */
function FormInfoGridInit(){
	$("#FormInfoGrid").jqGrid({
        url : ctx+'/basicdataweb/queryFormInfo.action',
        datatype : "json",
        colNames : ['id','序号','','评分表名称','评分表类型','总分','撰写人','创建时间','状态','','操作'],
        colModel : [
        	 {name : 'id',index:'id',fixed:true,hidden:true,key:true},
             {name : 'id',index:'id',align:'center'},
             {name : 'related_id',index:'related_id',hidden:true,sortable:false},
             {name : 'name',index:'name',align:'center',sortable:false},
             {name : 'type_id',index:'type_id',hidden:true},
             {name : 'total_sconum',index:'total_sconum',align:'center'},
             {name : 'user_name',index:'user_name',align:'center',sortable:false},
             {name : 'time',index:'time',align:'center'},
        	 {name : 'form_state',index:'form_state',align:'center',sortable:false},
             {name : 'availability',index:'availability',hidden:true},
        	 {name : 'act',index:'act',align:'center',width:250,fixed:true,sortable:false}
        ],
        rowNum : 20,
        rowList : [20,50,100],
        pager : '#FormInfoToolbar',
        gridview: true,
        loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
		sortable: true,
		sortname: 'id', //设置默认的排序列
		sortorder : "desc",// 排序方式,可选desc,asc
        viewrecords : true,
        postData: {
        	state: "Y"
        }, 
        width: pWidth,
        height: pHeight-120,
		autowidth:true,
		shrinkToFit:true,
        resizable:false,
		modal:true,
        gridComplete: function () {
	    	var ids = jQuery("#FormInfoGrid").jqGrid("getDataIDs");
	    	for (var i = 0; i < ids.length; i++) {
	    		var id = ids[i]; 
	    		var rowData = $("#FormInfoGrid").jqGrid('getRowData', id);
	    		var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',red('" + rowData['id'] + "','"+rowData['type_id']+"'));\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
	    		var ve = "";
	    		if(rowData['availability']=='Y'){
	    			ve = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',del('" + rowData['id'] + "','"+rowData['type_id']+"','','X','禁用'));\">禁用&nbsp;&nbsp;&nbsp;&nbsp;</a>";
	    		}else{
	    			ve = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',del('" + rowData['id'] + "','"+rowData['type_id']+"','','Y','启用'));\">启用&nbsp;&nbsp;&nbsp;&nbsp;</a>";
	    		}
	    		var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',upd('" + rowData['id'] + "','"+rowData['type_id']+"'));\">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
	    		var ce = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',del('" + rowData['id'] + "','"+rowData['type_id']+"','X','','删除'));\">删除</a>";
	    		jQuery("#FormInfoGrid").jqGrid("setRowData", id, { act : re + se + ve + ce  });
	    	}
        }
	}).navGrid("#FormInfoToolbar", {edit : false,add : false,del : false,search:false ,refreshstate:'current'});
}

function red(id,type){
	if (id == "") {
		myMsg('请选择表单！', '40%', '45%', 3000);
		return false;
	}
	if(type==1){
//		window.open(ctx+"/jsp/web/basicdata/scoreInfo.jsp?id="+id);
		relesepup = mypopdiv(2,"表单查看",'1000px',(pHeight-100)+'px',(pHeight-760)/2+'px',(pWidth-1000)/2+'px','Y',ctx+"/jsp/web/basicdata/scoreInfo.jsp?id="+id);
	}
	if(type==2){
		$.ajax({
			type: 'POST',
			url :  ctx+'/basicdataweb/getFormInfoById.action',
			dataType: 'json',
			data:{
				id:id
			},
			async: false,
			success:function(data) {
				data = data.data;
			    var content ='<div style="width:95%;margin: 0 auto;">'+
			    '<div style="text-align:center;">'+
				'<h3 id="title">'+data.name+'</h3>'+
			    '</div>'+
			    '<div style="font-size: 14px;text-align:right;color:#AAAAAA;">'+
			    '<span style=" float:left;">--表单类型：普通表单</span>'+
			    '<span style="margin-right:27%">--发布人：'+data.user_name+'</span>'+
			    '<span style=" float:right;margin-right:5px">--发布时间：'+data.time+'</span>'+
			    '</div><hr/>'+
			    '<div>'+data.t_content+'</div>'+
		        '</div>';
		    	relesepup = mypopdiv(1,"表单查看",'1000px',(pHeight-100)+'px',(pHeight-760)/2+'px',(pWidth-1000)/2+'px','Y',content);
			}
		});
	}
}


function del(id,type,state,validity,str){
	if (id == "") {
		myMsg('请选择表单！', '40%', '45%', 3000);
		return false;
	}
	myConfirm("是否要"+str+"？","20%","30%",
		function(){
			$.ajax({
				type: 'POST',
				url :  ctx+'/basicdataweb/modifyFormInfoState.action',
				dataType: 'json',
				data:{
					id:id,
					type:type,
					state:state,
					validity:validity
				},
				async: false,
				success:function(data) {
					if(data.success==true){
						myMsg('表单'+str+'成功！', '40%', '45%', 2000);
						formInit();
					}else{
						myMsg('表单'+str+'失败！', '40%', '45%', 3000);
						return;
					}
				}
			});
		},
		function(){
			return;
		});
}
function upd(id,type){
	if (id == "") {
		myMsg('请选择表单！', '40%', '45%', 3000);
		return false;
	}
	if(type==1){
		relesepup = mypopdiv(2,"编辑评分表单",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx+'/jsp/web/basicdata/marksheetEdit.jsp?id='+ id);
	}
	if(type==2){
		relesepup = mypopdiv(2,"编辑普通表单",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx+'/jsp/web/basicdata/htmlTemplateEdit.jsp?id='+ id);
	}
}


//重新加载
function formInit(){
	$("#FormInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//关闭弹出层
function closeMyWindows(){
	layer.closeAll();
}

function keywordSearch(){
	var searchStr= $("#searchStr").val();
//	 if(searchStr.length==""){
//		 myalert_error("关键字不能为空!","30%","40%");
//		 return false;
//	 }
	 $("#FormInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	name: searchStr
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}