$(function () {
    //页面加载完成之后执行
    pageInit();
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#check").jqGrid(
        {
            url: ctx+'/teachweb/getPendingAudit.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','姓名', '性别',  '学生类型', '轮转科室', '计划轮转时间', '入科时间', '培训数据审核',''],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'stu_auth_id', index: 'stu_auth_id', width: 40,key:true,hidden:true},        
                {name: 'name', index: 'name', width: 40, align: "center"},
                {name: 'sex', index: 'sex', width: 30, align: "center"},
                {name: 'typeName', index: 'typeName', width: 40, align: "center"},
                {name: 'orga_name', index: 'orga_name', width: 50, align: "center"},
                {name: 'train_start_str', index: 'train_start_str', width: 70, align: "center"},
                {name: 'deptReceiveStr', index: 'deptReceiveStr', width: 60, align: "center"},
                {name: 'checkBtn', index: 'checkBtn', width: 70, align: "center"},
                {name: 'counts', index: 'counts', width: 70, align: "center",hidden:true}
                
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "post",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData:{name:$("#keyWord").val()},
            width:pWidth-40,
            height:pHeight-130,
            resizable:false,
    		modal:true,
    		loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            gridComplete: function () {
                var ids = jQuery("#check").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var _id = ids[i];
                    var rowData=$("#check").jqGrid("getRowData",_id);  //行数据
                    var stuAuthId=rowData.stu_auth_id; //获取此学生的权限 id
                    var name=rowData.name;  //学生姓名
                    var re = "<a style='color: #505f91;cursor: pointer' class='en_btn'>审核 <span class='number'>"+rowData.counts+"</span>" +
                    			"<span style='display:none;' class='_name'>"+name+"</span>" +
                    			"<span style='display:none;' class='_id'>"+stuAuthId+"</span></a>";
                 
                        jQuery("#check").jqGrid("setRowData", _id, {checkBtn: re});
                        
                        $(".en_btn").click(function(){
                    		var name = $(this).children("[class='_name']").text();

                    		var stuAuthId = $(this).children("[class='_id']").text();

                    		var number = $(this).children("[class='number']").text();
                    		if(pHeight>550){
                    			height=550;
                    		}else{
                    			height=pHeight;
                    		}
                    		mypopdiv(2,"",'1000px',(height-50)+'px','','','N',encodeURI(ctx + "/jsp/web/teach/checkInfor.jsp?id="+stuAuthId+"&name="+name+"&number="+number),function (){                    		                    			
                    			location.reload();
                    		});
                    	});   
                }
            }
        });
    jQuery("#check").jqGrid('navGrid', '#pager1', {edit: false, add: false, del: false});
}




function searchCheck(){
	var postData={
    		name:$("#keyWord").val()
    };
	$("#check").jqGrid('setGridParam',
	        {
			datatype:'json',
			page:1,
			postData:postData
	        }).trigger("reloadGrid");	
}


function resert(){
	$("#keyWord").val("");
}

