$(function() {
	var id =getUrlParam("id");
	pageInit(id);
});

function pageInit(id) {
	$("#planMainGrid").jqGrid(
	{
		url : ctx+'/configweb/findRotaryDepartment.action',
		datatype : "json",
		colNames: ['','科室', '轮转时长', '单位', '序号','科室组次','人数上限','是否为空'],
		colModel: [
             {name : 'id',index:'id',key:true,hidden:true},
             {name: 'orga_name', index: 'deporga_name', width:100, align: "center"},
             {name: 'duration', index: 'duration', width: 50, align: "center",editable:true},
             {name: 'duration_unit', index: 'duration_unit', width: 50, align: "center"},
             {name: 'ordinal', index: 'ordinal', width:50, align: "center",editable:true},
             {name: 'group_num', index: 'group_num', width:50, align: "center",editable:true},
             {name: 'limit_num', index: 'limit_num', width:50, align: "center"},
             {name:'state',index:'state',hidden:true}
        ],
        rowNum : 200,
        rowList : [200, 500, 1000, 2000, 5000],
        pager : '#planMainToolbar',
        viewrecords : true,
        mtype: 'POST',
        postData: {
        	id:id,
        	durationunit:-1	
        },
        width: pWidth*0.98,
        height: (pHeight-210)+'px',
		autowidth:true,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		gridComplete:function(){
			var ids=$('#planMainGrid').jqGrid('getDataIDs');
			for(var i = 0; i < ids.length ; i ++){
			    var id = ids[i];
			    $("#"+id).attr("style","cursor:pointer");
	        }
		}
	})
	//.navGrid("#planMainToolbar", {edit : false,add : false,del : false,search:false,refreshstate:'current'});
}
