var content = "";
var role_code = "";
var choosePeo;
$(function(){
	role_code = $("#roleCode").text();
	PublicDataGridInit();
});


/**
 * 共享资料列表初始化
 */
function PublicDataGridInit(){
	$("#PublicDataGrid").jqGrid(
	{
        url : ctx+'/publicdataweb/selectPublicDataList.action',
        datatype : "json",
        colNames : ['id','资料标题','资料类型','发布人','发布时间','操作'],
        colModel : [
        	 {name : 'id',index:'id',fixed:true,hidden:true,key:true},
             {name : 'title',index:'title',align:'center'},
             {name : 'type_code',index:'type_code',align:'center'},
             {name : 'publisher_auth_id',index:'publisher_auth_id',align:'center'},
        	 {name : 'time',index:'time',align:'center'},
        	 {name : 'act',index:'act',align:'center',width:250,fixed:true,sortable: false}
        ],
        rowNum : 20,
        rowList : [ 20, 50, 100],
        pager : '#PublicDataToolbar',
        gridview: true,
        loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
		sortable: true,
		sortname: 'id', //设置默认的排序列
		sortorder : "asc",// 排序方式,可选desc,asc
        viewrecords : true,
        postData: {
        	role_code: role_code,
        	state: "Y",
        	flag: 1//  全部资料和我发布的资料的标识（1：全部资料/2：我发布的资料）
        }, 
        width: pWidth,
        height: pHeight-120,
		autowidth:true,
		shrinkToFit:true,
        resizable:false,
		modal:true,
        gridComplete: function () {
         var ids = jQuery("#PublicDataGrid").jqGrid("getDataIDs");
         for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',red('" + id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
                    jQuery("#PublicDataGrid").jqGrid("setRowData", id, { act : re});
             }
         }
        }
	});
}
//发布资料按钮页面跳转
function releasePublicData(){
	 if(pHeight<500){
		 relesepup = mySelfpopdiv("publicdata",2,"发布资料",'1000px','440px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx + "/jsp/web/userauth/releasePublicData.jsp");
		}else{
			 relesepup = mySelfpopdiv("publicdata",2,"发布资料",'1000px','520px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx + "/jsp/web/userauth/releasePublicData.jsp");
		}
}

function red(id){
	if (id == "") {
		myMsg('请选择资料！', '40%', '45%', 3000);
		return false;
	}
	window.open(ctx+"/publicdataweb/selectOneById.action?id="+id);
//	 $.ajax({
//	 		type: 'POST',
//		     	url :  ctx+'/publicdataweb/selectOneById.action',
//		    	dataType: 'json',
//		    	data:{
//		    		id:id
//		    		},
//		  	    async: false,
//		        success:function(data) {
//	    content ='<div style="width:1150px;margin:0 auto;">'+
//	    '<div style="text-align:center;">'+
//		'<h3 id="title">'+data.title+'</h3>'+
//	    '</div>'+
//	    '<div style="width:1150px;margin:0 auto;font-size: 14px;text-align:right;color:#AAAAAA;">'+
//	    '<span style=" float:left;">资料类型：'+data.type_code+'</span>'+
//	    '<span style="margin-right:27%">发布人：'+data.publisher_auth_id+'</span>'+
//	    '<span style=" float:right;margin-right:5px">发布时间：'+data.time+'</span>'+
//	    '</div><hr style="margin:6px 0px;"/>'+
//	    '<div style="width:1150px;margin:0 auto;">'+data.content+'</div>'+
//        '</div>';
//       relesepup = mypopdiv(1,"资料查看",'1200px',(pHeight-100)+'px','50px',(pWidth-1000)/2+'px','N',content);
//				}
//		});
}


function del(id){
	if (id == "") {
		myMsg('请选择资料！', '40%', '45%', 3000);
		return false;
	}
	myConfirm("是否要删除？","30%","40%",
			function(index){
		var url=ctx+'/publicdataweb/delPublicDate.action';
		 var postData={
				id:id,
				state:"X"
		 };
		 doAjax(url,postData,2,function(){
			 window.close();
			 myalert_success("删除成功！","30%","40%");
			 orgaInit();
		 });
			},
			function(){
        		window.close();
        		
			}
		);
}
function upd(id){
	if (id == "") {
		myMsg('请选择资料！', '40%', '45%', 3000);
		return false;
	}
	if(pHeight<500){
		choosePeo = mySelfpopdiv("publicdata",2,"编辑资料",'1000px','440px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx + '/jsp/web/userauth/releasePublicData.jsp?id='+ id);
	}else{
		choosePeo = mySelfpopdiv("publicdata",2,"编辑资料",'1000px','520px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx + '/jsp/web/userauth/releasePublicData.jsp?id='+ id);
	}
}


//重新加载
function orgaInit(){
	$("#PublicDataGrid").jqGrid('setGridParam',{
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
   var type_code="";
	 if(searchStr.length<2&&searchStr.length!=0){
		 myalert_error("最少输入两个字符!","30%","40%");
		 return false;
	 }
	 if(searchStr=="学习"||searchStr=="学习资"||searchStr=="学习资料"){
		 type_code=1;
	 }else if(searchStr=="日常"||searchStr=="日常资"||searchStr=="日常资料"){
		 type_code=2;
	 }else if(searchStr=="其他"||searchStr=="其他资"||searchStr=="其他资料"){
		 type_code=3;
	 }
	 $("#PublicDataGrid").jqGrid('setGridParam',{
	        datatype: 'json',
	        postData: {
	        	searchStr: searchStr,
	        	state: "Y",
	        	type_code:type_code
	        }, //发送数据
	        page: 1
	    }).trigger("reloadGrid"); //重新载入
	
}

//切换为我发布的或全部资料
function changingOver(){
	var flag = "";
	if($("#allOrMine").text() == "我发布的资料"){
		flag = 2;
	}else if($("#allOrMine").text() == "全部的资料"){
		flag = 1;
	}
	$("#searchStr").val('');
//	PublicDataGridInit(flag);
	$("#PublicDataGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y",
        	role_code: role_code,
        	flag: flag,
        	searchStr: null,
        	type_code: null
        }, //发送数据
        page: 1,
        gridComplete: function () {
            var ids = jQuery("#PublicDataGrid").jqGrid("getDataIDs");
            for (var i = 0; i < ids.length; i++) {
                   var id = ids[i];
                   var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',red('" + id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
                   var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',upd('" + id + "'));\">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
                   var ce = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#PublicDataGrid').jqGrid('saveRow','" + id + "',del('" + id + "'));\">删除</a>";
                   if(flag == 1){//  全部资料
                   	jQuery("#PublicDataGrid").jqGrid("setRowData", id, { act : re});
                   }else if(flag == 2){//  我发布的资料
                   	jQuery("#PublicDataGrid").jqGrid("setRowData", id, { act : re + se + ce  });
                   }
            }
        }
    }).trigger("reloadGrid"); //重新载入
	
	if($("#allOrMine").text() == "我发布的资料"){
		$("#allOrMine").text("全部的资料");
	}else if($("#allOrMine").text() == "全部的资料"){
		$("#allOrMine").text("我发布的资料");
	}
}

function addPeo() {
	choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/publicdata/publicDataPeoole.jsp?");
}
function mySelfpopdiv(id,type,title,width,length,verticalNum,horizontalNum,shadeCloseFlag,content,endFun){
	if(id==null||id==""){
		id = -1;
	}
	if(type==null||type==""){
		type = 1;
	}
	if(title==null||title==""){
		title = false;
	}
	if(length==null||length==""){
		length = "400px";
	}
	if(width==null||width==""){
		width = "250px";
	}
	
	if(shadeCloseFlag==null||shadeCloseFlag==""){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="Y"){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="N"){
		shadeCloseFlag = false;
	}
	if(content==null||content==""){
		content = "无内容...";
	}
	var myLayer = layer.open({
		id: id,
		type: type,
		skin: 'layui-layer-lan',
		shade:[0.5,'#fff'],
		title:title,
		area: [width,length],
		//offset: [verticalNum,horizontalNum],
	    shadeClose: shadeCloseFlag, //点击遮罩关闭
	    content: content,
	    end:endFun
	});
	return myLayer;
}

function addNamesInput(orga_idList,orga_nameList){
	var frameId=document.getElementById('publicdata').getElementsByTagName("iframe")[0].id;
    $('#'+frameId)[0].contentWindow.addNamesInput(orga_idList,orga_nameList);
    layer.close(choosePeo);
}