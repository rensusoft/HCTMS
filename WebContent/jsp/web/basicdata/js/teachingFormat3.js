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
	ftc_id = Request['ftc_id'];     //  教学模板配置id
	stu_id = Request['stu_id'];     //学生类型 Id
	orga_id = Request['orga_id']; 	//科室id
	
    //页面加载完成之后执行
    pageInit();
    
});

var name="";
function pageInit() {
    //创建jqGrid组件
    jQuery("#form").jqGrid(
        {
            url: ctx+"/basicdataweb/getForm.action",//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id','表单名称', '','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', width: 70, align: "center",key:true,hidden:true},
                {name: 'name', index: 'name', width: 70, align: "center"},
                {name: 'type_id', index: 'type_id', width: 70, align: "center",hidden:true},
                {name: 'checkBtn', index: 'checkBtn', width:40, align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            postData:{
            	type_id:"2"
            },
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: 1150,
            height: 500,
            gridComplete: function () {
                var ids = jQuery("#form").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var rowData=$("#form").jqGrid("getRowData",id);  //行数据
                    var name = rowData.name;
                    var typeId=rowData.type_id;
                    var re = "<a style='color: #505f91;cursor: pointer' class='_red'>预览</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' class='_onclick'>绑定" +
                        "<span style='display:none' class='_name'>"+name+"</span><span style='display:none' class='typeId'>"+typeId+"</span><span style='display:none' class='_formId'>"+rowData.id+"</span></a>";
                    jQuery("#form").jqGrid("setRowData", id, {checkBtn: re});                    
                }
                $("._onclick").click(function(){
                    name=$(this).children("[class='_name']").text();
                    formId=$(this).children("[class='_formId']").text();
                    var postData={
                    		ftc_id:ftc_id,
                    		stu_id:stu_id,
                    		formId:formId,
                    		orga_id:orga_id
                    };
                    doAjax(ctx+"/basicdataweb/addTDR.action",postData,2,function (res){                    
                    		parent.add(name,orga_id,stu_id,res);                   	
                    });
                });
                
                $("._red").click(function(){
                	formId=$(this).next().children("[class='_formId']").text();
                	var typeId=$(this).next().children("[class='typeId']").text();
        			if(typeId==1){
        				relesepup = mypopdiv(2,"表单查看",'1020px',(pHeight-160)+'px',(pHeight-800)/2+'px',(pWidth-1000)/2+'px','Y',ctx+"/jsp/web/basicdata/scoreInfo.jsp?id="+formId);	
        			}else if(typeId==2){
        				relesepup = mypopdiv(2,"表单查看",'1020px',(pHeight-160)+'px',(pHeight-800)/2+'px',(pWidth-1000)/2+'px','Y',ctx+"/jsp/web/basicdata/htmlForm.jsp?id="+formId);
        			}
                    });
            }
        });
    jQuery("#form").jqGrid('navGrid', '#pager1', {edit: false, add: false, del: false,refresh:false,search:false});
}








function searchName(){
	var postData={
			name:$("#text").val(),
			type_id:"2"
	};
	$("#form").jqGrid('setGridParam',{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		datatype:'json',
		postData:postData,
		page:1
	}).trigger("reloadGrid");  //重新载入
}


function resetName(){
	$("#text").val("");
}
