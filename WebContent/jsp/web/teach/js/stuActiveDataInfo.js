var stu_auth_id=""; 
var dept_id="";
var data_type_id="";
$(function () {
	function GetRequest() { //取URL参数
		var url = window.location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);//unescape--》decodeURI防止参数乱码 
				} 
			} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	stu_auth_id = Request['stu_auth_id']; 
	dept_id = Request['dept_id']; 
	data_type_id = Request['data_type_id']; 
    //页面加载完成之后执行
    pageInit();
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#entering_content").jqGrid(
        {
            url: ctx+'/teachweb/findStuActiveData.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', '名称', '提交日期', '审核人', '审核状态', '操作', 'sto_id', 'data_type_id', 'examine_state'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....,
                {name: 'id', index: 'id',hidden:true,key:true},
                {name: 'order_name', index: 'order_name', width: 100, align: "center"},
                {name: 'create_time_str', index: 'create_time_str', width: 70, align: "center"},
                {name: 'user_name', index: 'user_name', width: 70, align: "center"},
                {name: 'examineState', index: 'examineState', width: 40, align: "center"},
                {name: 'operation', index: 'operation', width: 40, align: "center"},
                {name: 'sto_id', index: 'sto_id',hidden:true},
                {name: 'data_type_id', index: 'data_type_id',hidden:true},
                {name: 'examine_state', index: 'examine_state',hidden:true}
            ],
            rowNum : 20,
            rowList : [20, 50, 100],
            pager : '#enteringPager',
            gridview: true,
            sortname: 'invdate',
            viewrecords : true,
            postData: {
            	stu_auth_id:stu_auth_id,
            	dept_id:dept_id,
            	data_type_id:data_type_id
            }, 
            width:960,
            height:pHeight-220,           
            autowidth:false,
            shrinkToFit:true,
            resizable:false,
    		modal:true,       
            gridComplete: function () {
                var ids = jQuery("#entering_content").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var rowData=$("#entering_content").jqGrid("getRowData",id);
                    var order_name=rowData.order_name;
                    var sto_id=rowData.sto_id;
                    var data_type_id=rowData.data_type_id;
                    var examine_state=rowData.examine_state;
                    //
                    var se = "<a title=''  style='color: #505f91;cursor: pointer'" + "onclick=\"$('#entering_content').jqGrid('saveRow','" + id + "',view('"+id+"','"+data_type_id+"','"+order_name+"','"+sto_id+"'));\">查看</a>";
                   
                    jQuery("#entering_content").jqGrid("setRowData", id, {operation: se});
                }		
            }
        });
    $("#entering_content").jqGrid().navGrid("#enteringPager", {edit : false,add : false,del : false,search : false,refresh : true});
}
//编辑
function view(id,data_type_id,order_name,sto_id){
	if(id == ""){
		myalert_error('请选择录入数据！');
		return false;
	}
	entering(sto_id,data_type_id,order_name,id);
}

function entering(id,data_type_id,order_name,sad_id){
	if(data_type_id==1){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/emergency.jsp?id="+id+"&sad_id="+sad_id+"&action=view");
	}else if(data_type_id==2){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/activities.jsp?id="+id+"&sad_id="+sad_id+"&action=view");	
	}else if(data_type_id==3){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/case.jsp?id="+id+"&sad_id="+sad_id+"&action=view");
	}else if(data_type_id==4){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/registrInf.jsp?id="+id+"&sad_id="+sad_id+"&action=view");	
	}else if(data_type_id==5){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/operation.jsp?id="+id+"&sad_id="+sad_id+"&action=view");	
	}else if(data_type_id==6){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/academicConference.jsp?id="+id+"&sad_id="+sad_id+"&action=view");
	}else if(data_type_id==7){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/scientificResearch.jsp?id="+id+"&sad_id="+sad_id+"&action=view");
    }
	 else if(data_type_id==8){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/awards.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
    }
	
}