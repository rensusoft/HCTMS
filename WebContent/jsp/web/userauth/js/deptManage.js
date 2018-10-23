var content="";
var type="";
var orgaNameSelect="";
$(function(){
	deptManageGridInit();
});


/**
 * 机构管理表初始化
 */
function deptManageGridInit(){
	$("#DeptManageGrid").jqGrid(
	{
        url : ctx+'/userauthweb/selectOrgaInfoList.action',
        datatype : "json",
        colNames : ['orga_id','机构名称','机构类别','上级机构','机构状态','机构代码','上限人数'],
        colModel : [
        	 {name : 'orga_id',index:'orga_id',fixed:true,hidden:true,key:true},
             {name : 'orga_name',index:'orga_name',align:'center'},
             {name : 'orga_types',index:'orga_types',align:'center'},
             {name : 'sup_name',index:'sup_name',align:'center'},
        	 {name : 'state',index:'state',align:'center'},
             {name : 'orga_code1',index:'orga_code1',align:'center'},
        	 {name : 'limit_num',index:'limit_num',align:'center'}
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100],
        pager : '#DeptManageToolbar',
        gridview: true,
        sortname: 'invdate',
        viewrecords : true,
        postData: {
        	state: "Y"
        }, 
        width: pWidth,
        height: pHeight-80,
		autowidth:true,
		shrinkToFit:true,
        resizable:true,
		modal:true,
		multiselect: true,
	});
    $("#DeptManageGrid").jqGrid().navGrid("#DeptManageToolbar", {edit : false,add : false,del : false,search : false,refresh : true})
	.navButtonAdd("#DeptManageToolbar",{caption: "新增", buttonicon:"ui-icon-plus", onClickButton: addOrgaInfo,title : "新增"})
    .navButtonAdd('#DeptManageToolbar',{ caption:"修改", buttonicon:"ui-icon-pencil",onClickButton: updateOrgaInfo,title : "修改" })
    .navButtonAdd('#DeptManageToolbar',{ caption:"删除", buttonicon:"ui-icon-trash", onClickButton: delOrgaInfo,title : "删除" });
}
//上级机构选择框预加载
function supId(){
	 $.ajax({
	 		type: 'POST',
		     	url :  ctx+'/userauthweb/selectOragName.action',
		    	dataType: 'json',
		    	data:{},
		  	    async: false,
		        success:function(data) {
		        	data = eval(data.rows);
					orgaNameSelect = '<select  id="sup_id" class="form-control" >'+
					 '<option value =0>无</option>';
					if(data.length>0){
					for (var i=0;i<data.length;i++){
						orgaNameSelect += ' <option value ='+data[i].orga_id+'>'+data[i].orga_name+'</option>';
					}
					}
					orgaNameSelect += '</select>';
				}
		});
}


//新增弹出层
function addWindow(){
//上级机构选择框	
str = '<select  id="orga_type" class="form-control" onchange="upnumhidden()"><option value ="1">教学</option>'+
'<option value ="2">行政</option><option value ="3">后勤</option></select>';

     content = '<div class="print"><table style="border:0;">'+
	'<tr height="35px"><td width="100px"><span style="color: red;">*&nbsp;</span>机构名称:</td><td><input id="orga_name"  name="orga_name"class="form-control" type="text"  value=""  /></td>'+
		'<td > 上级机构:</td><td>'+orgaNameSelect+'</td></tr><tr><tr/>'+
	'<tr height="35px"><td> 机构类别:</td><td>'+str+'</td>'+
	    '<td> 机构代码:</td><td><input id="orga_code1" name="orga_code1"class="form-control" type="text" style="ime-mode:disabled"   onKeyPress="return verifyDigital(event);"/></td></tr>'+
	'<tr id="upnum" height="35px"><td> 上限人数(数字):</td><td><input id="limit_num"  name="limit_num" class="form-control" type="text" style="ime-mode:disabled"   onKeyPress="return verifyDigital(event);" /></td>'+     
	    '<td></td><td></td></tr>'+
	'</table></div>'+
	'<div style="width: 100%; text-align: center; margin-top: 7px;">'+
		'<button class="layui-layer-btn0" onclick="submitAddOrgaInfo();">提交</button></div><input id="orga_id" name="orga_id" type="hidden"  value=""  />';
}


function upnumhidden(){
	if($("#orga_type").val()!=1){
		$("#upnum").hide();
	}else{
		$("#upnum").show();
	}
}

function addOrgaInfo(){
	supId();
	 addWindow();
	type="1";
addpup = mypopdiv(1,"新增机构",'700px','220px',(pHeight-220-80)/2,(pWidth-700)/2,'Y',content);
}
function submitAddOrgaInfo(){
	
	var orga_name=$("#orga_name").val();
	var sup_id=$("#sup_id").val();
	var sup_name=$("#sup_id").find("option:selected").text(); 
	var orga_type=$("#orga_type").val();
	var orga_code1=$("#orga_code1").val();
	var limit_num=$("#limit_num").val();
	if(orga_name==""){
		myalert_error('机构名称不能为空！', '30%', '40%');
	     return false;	
	}
	if(checkStr(orga_name,2)==false){
		myMsg("机构名称不能含有特殊字符！",'50%','50%');
		return false;
	}
	if(orga_name==sup_name){
		myalert_error('机构名称与上级机构不能相同！', '30%', '40%');
		return false;	
	}
	if(orga_code1!=""&&(isNaN(orga_code1)||checkStr(orga_code1,1)==false)){
		myMsg("机构代码输入错误，请输入正整数！",'50%','50%');
		return false;
	}
	if(limit_num!=""&&!(/^\+?[1-9][0-9]*$/).test(limit_num)){
		myalert_error('上限人数只能为正整数！', '30%', '40%');
		return false;	
	}
	if(type=='1'){
		 $.ajax({
	    		type: 'POST',
	  	     	url :  ctx+'/userauthweb/addOrgaInfo.action',
	  	    	dataType: 'json',
	  	    	data:{
	  	    		ids:ids,
	  	    		orga_name:orga_name,
	  	    		sup_id:sup_id,
	  	    		orga_type:orga_type,
	  	    		orga_code1:orga_code1,
	  	    		limit_num:limit_num
	  	    	},
	  	  	    async: false,
	  	        success:function(data) {
		 	         	reloadGrid();
		 	        	myalert_success('操作成功！', '30%', '40%');
			    },
			    error : function() {
			        	myalert_error('操作异常！', '30%', '40%');
			}
	  	});	
	}else if(type=='2'){
		var ids= $("#orga_id").val();
		 $.ajax({
	    		type: 'POST',
	  	     	url :  ctx+'/userauthweb/updateOrgaInfo.action',
	  	    	dataType: 'json',
	  	    	data:{
	  	    		ids:ids,
	  	    		orga_name:orga_name,
	  	    		sup_id:sup_id,
	  	    		orga_type:orga_type,
	  	    		orga_code1:orga_code1,
	  	    		limit_num:limit_num
	  	    	},
	  	  	    async: false,
	  	        success:function(data) {
		 	         	reloadGrid();
		 	        	myalert_success('操作成功！', '30%', '40%');
			    },
			    error : function() {
			        	myalert_error('操作异常！', '30%', '40%');
			}
	  	});	
	}
	closeMyWindows();
}


function updateOrgaInfo(){
	supId();
	addWindow();
	var ids = jQuery("#DeptManageGrid").jqGrid('getGridParam', 'selarrrow');
	if (ids == "") {
		myMsg('请选择机构！', '40%', '45%', 3000);
		return false;
	}
	if ( ids.length  > 1) {
		myalert_error('只能选择一个机构进行修改！', '40%', '45%');
		return false;
	}
	$.ajax({
		type: 'POST',
		url :  ctx+'/userauthweb/selectOragName.action',
		dataType: 'json',
		data:{
			ids:ids.join(","),
		},
		async: false,
		success:function(data) {
			data = eval(data.rows);
			type="2";
			addpup = mypopdiv(1,"修改机构",'700px','220px',(pHeight-220-80)/2,(pWidth-700)/2,'Y',content);
			$("#orga_name").val(data[0].orga_name);
			$("#orga_type").val(data[0].orga_type);
			$("#orga_code1").val(data[0].orga_code1); 
			$("#limit_num").val(data[0].limit_num); 
			$("#sup_id").val(data[0].sup_id);
			$("#orga_id").val(ids); 
		}
	});

}
function delOrgaInfo(){
	var ids = jQuery("#DeptManageGrid").jqGrid('getGridParam', 'selarrrow');
	     if (ids == "") {
			 myMsg('请选择机构！', '40%', '45%', 3000);
			 return false;
		 }
	     myConfirm("是否要删除？","30%","40%",
			function(index){
	   	  $.ajax({
	    		type: 'POST',
	  	     	url :  ctx+'/userauthweb/updateOrgaInfo.action',
	  	    	dataType: 'json',
	  	    	data:{
	  	    		ids:ids.join(","),
	  				state:"X"
	  	    	},
	  	  	    async: false,
	  	        success:function(data) {
		 	         	reloadGrid();
		 	        	myalert_success('操作成功！', '30%', '40%');
			    },
			    error : function() {
			        	myalert_error('操作异常！', '30%', '40%');
			}
	  	});	
	},
		function(){
		   layer.closeAll();
			}
 );
}

//重新加载
function reloadGrid(){
	$("#DeptManageGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//关闭弹出层
function closeMyWindows(){
	layer.close(addpup);
}