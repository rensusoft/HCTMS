var _id;
var content1;
var businessplanmag_iRow;
var businessplanmag_iCol;
var updateTime=1;    //用来判断是否保存后（变为1），还是前（还是0），改变日期的
$(function () {
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	var id=""; 
	id = Request['id']; 
	if(id!="" && id!=null){
		_id=id;
	};
    //页面加载完成之后执行
    pageInit();
    
	//右击菜单触发事件
	$('#rotateRule').contextMenu('myMenu', {
		bindings: 
        {
          'delete': function(){
  			deleteOrga();   //删除科室
          },    
}
});
    
    
    
});
//同步科室
function addOrga() {
	myConfirm("是否恢复初始化，同步所有科室？",'(pHeight/2)px','',
			function success(){
			 $("#rotateRule").jqGrid("clearGridData"); //清空数据 
             var url = ctx+'/configweb/addTrainPlan.action';
             var postData = {
            		 durationunit:0,
            		 id:_id
             };
             $("#rotateRule").jqGrid('setGridParam',{
                 url: url,
                 datatype:'json',
                 postData: postData,
                 page: 1
             }).trigger("reloadGrid"); //重新载入
         	updateTime=0;
             layer.closeAll();
//             doAjax(url,postData,2,function(res){
//            	
//            	 alert(res.data);
//            	 $("#rotateRule").jqGrid('setGridParam',{  // 重新加载数据
//            		 datatype:'json',
//            	     data : res.data   //  res.data 是符合格式要求的需要重新加载的数据             	  
//            	}).trigger("reloadGrid");		//从新加载数据
//            	 layer.closeAll();
//             });		
			});
}
function reloadGrid(){
    $("#rotateRule").jqGrid('setGridParam',{
        url: ctx+'/configweb/findRotaryDepartment.action',
        postData: {id:_id,
        	durationunit:$("#time").val()
        },
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
function pageInit() {
    //创建jqGrid组件
    jQuery("#rotateRule").jqGrid({
            url: ctx+'/configweb/findRotaryDepartment.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['', '科室', 'dept_id', '轮转时长', '单位', '序号', '科室组次', '人数上限', '操作', '是否为空'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name : 'id',index:'id',key:true,hidden:true},
                {name: 'orga_name', index: 'orga_name', width:100, align: "center"},
                {name: 'dept_id', index: 'dept_id', hidden:true},
                {name: 'duration', index: 'duration', width: 50, align: "center",editable:true},
                {name: 'duration_unit', index: 'duration_unit', width: 50, align: "center"},
                {name: 'ordinal', index: 'ordinal', width:50, align: "center",editable:true},
                {name: 'group_num', index: 'group_num', width:50, align: "center",editable:true},
                {name: 'limit_num', index: 'limit_num', width:50, align: "center"},
                {name:'act',index:'act', width: 40,align: "center"},
                {name:'state',index:'state',hidden:true}
//                ,{name:'save',index:'save', width: 40,align: "center"}
            ],
            rowNum: 100,//一页显示多少条
            //rowList: [10, 20, 30],//可供用户选择一页显示多少条
            //pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段	
            //sortorder: "desc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData: {id:_id,
            	durationunit:-1	
            },
            width:pWidth-80,
            height:pHeight-250,
            multiselect: true,
            cellEdit: true,
            cellsubmit: 'clientArray',
            cellurl : ctx+'/configweb/updateCongfig.action',
            gridComplete: function () {
                var ids = jQuery("#rotateRule").jqGrid("getDataIDs");
                var allTime=0;
                var danWei="";
                if($("#rotateRule").jqGrid("getRowData",ids[ids.length-1]).duration_unit!=null){
                	if($("#rotateRule").jqGrid("getRowData",ids[ids.length-1]).duration_unit=='周'){
                		$("#time").val('W');
                	}else if($("#rotateRule").jqGrid("getRowData",ids[ids.length-1]).duration_unit=='月'){
                		$("#time").val('M');
                	}else if($("#rotateRule").jqGrid("getRowData",ids[ids.length-1]).duration_unit=='天'){
                		$("#time").val('D');
                	}
                	
                }
                for (var i = 0; i < ids.length; i++) {
                   var   id = ids[i];
                	var a="<b class='qm'>？</b>";
                	var rowData=$("#rotateRule").jqGrid("getRowData",id);
                	allTime+=parseInt(rowData.duration);
                	danWei=rowData.duration_unit;
					if(rowData.state!='X'){
						a="";		
					};
                    var re = "<a title='' class='btnClick' style='color:#7C87AD'" + "onclick=\"addFanAn("+id+") \">"+a+"方案说明</a>";
                    var ti = "<input type='number' style='width:100%' min='1'>";
                    jQuery("#rotateRule").jqGrid("setRowData", id, {time: ti});
                    jQuery("#rotateRule").jqGrid("setRowData", id, {act: re});
                }
                $("#allTime").html("轮转时间总计：<span style=\"color:red;\">"+allTime+"</span>"+danWei);
            },
            beforeEditCell:function(rowid,cellname,value,iRow,iCol){
                businessplanmag_iRow=iRow;
                businessplanmag_iCol=iCol;
            }
        });
    jQuery("#rotateRule").jqGrid('navGrid', '#pager1', {edit: true, add: false, del: false});
}


//时间刷新
function timeChange(){
	if(updateTime==0){
		var url = ctx+'/configweb/addTrainPlan.action';
		var postData={
				durationunit:$("#time").val(),
				id:_id
		};
		$("#rotateRule").jqGrid('setGridParam',{
			url:url,
			datatype:'json',
			postData:postData,//发送数据
			page:1
		}).trigger("reloadGrid");
	}else{
		var postData={
				durationunit:$("#time").val(),
				id:_id
		};
		$("#rotateRule").jqGrid('setGridParam',{
			datatype:'json',
			postData:postData,//发送数据
			page:1
		}).trigger("reloadGrid");
	}
	
	
}



//删除单个科室
function deleteOrga(){
	var id1=$("#rotateRule").jqGrid('getGridParam','selrow');
	if (!id1) {
		myMsg("请先选择要删除的科室！");
		return ;
	}
	myConfirm("是否删除科室？",'(pHeight/2)px','',
			function success(){ 
		$("#rotateRule").jqGrid("delRowData", id1);	
		layer.closeAll();
	});
}
//批量删除科室
function deleteMany(){
	var ids=$("#rotateRule").jqGrid("getGridParam","selarrrow");
	if(ids==null||ids==""){
		myMsg("请选择要删除的科室!",'50%','50%');
		return false;
	}
	myConfirm("是否删除科室？",'(pHeight/2)px','',function success(){
		var len = ids.length; 
		//alert(ids.length);
		for ( var i = 0; i < len; i++) {
			//alert(ids[i]);
			$("#rotateRule").jqGrid("delRowData", ids[0]);
		}
		layer.closeAll();
		myalert_success("删除成功！");
	});
}

//修改保存科室
function updateConfig(id){
	var ret = $("#rotateRule").jqGrid("getRowData", id);
	var duration = ret.duration;
	if(duration!=""){
	if(isNaN(duration)){
		myMsg("轮转时长只能输入数字!",'50%','50%');
		return false;
	}
	}
	
	var ordinal = ret.ordinal;
	if(ordinal!=""){
		if(isNaN(ordinal)){
			myMsg("序号只能输入数字!",'50%','50%');
			return false;
		}
	}
	
	var group_num=ret.group_num;
	
	var url = ctx+'/configweb/updateConfig.action';
	var postData = {
			id:id,
			duration:duration,
			ordinal:ordinal,
			group_num:group_num
	};
	doAjax(url,postData,2,function(res){
		myalert_success(res.data);
		timeChange();
	});
	
}

//保存所有
function addAll(){
	updateTime=1;
	$("#rotateRule").jqGrid("saveCell",businessplanmag_iRow,businessplanmag_iCol);
	var ret = $("#rotateRule").jqGrid("getRowData");//得到所有数据
//	var ok="ok";
	var rotate=new Array();
	var url = ctx+'/configweb/addConfig.action';
	for ( var i = 0; i < ret.length; i++) {	
		var duration = ret[i].duration;
		if(duration!=""){
		if(isNaN(duration)){
			myalert_error("第"+(i+1)+"行轮转时长只能输入数字!");
			return;
		}
		}
		var ordinal = ret[i].ordinal;
		if(ordinal!=""){
			if(isNaN(ordinal)){
				myalert_error("第"+(i+1)+"行序号只能输入数字!");
				return;
			}
		}
		if(duration!=""){
			if(duration==0){
				myalert_error("第"+(i+1)+"行时长不能为0!");
				return;
			}
		}
		rotate.push(ret[i]);
	}
	var postData={
			_id:_id,
			rotate:JSON.stringify(rotate)
	};
	$.ajax({
		type:"post",
		url:url,
		data:postData,
		dataType: "json", 
		traditional: true,
		success:function(res){
		 if(res.sucess==true){
				myalert_success(res.data);
				reloadGrid();
		 }else{
			 myalert_success(res.data);
				reloadGrid();
		 }	
		}
	});
}



function addFanAn(id){
	content1 = mypopdiv(2,"添加方案",'1000px',(pHeight-100)+'px','0px',(pWidth-1000)/2,'Y',ctx + "/jsp/web/config/addFanAn.jsp?id="+id);
}


function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			layer.closeAll();
			reloadGrid();
		});
	}else{
		myalert_error(res.data,'','',function(){
			layer.closeAll();
		});
	}
	
	
}

