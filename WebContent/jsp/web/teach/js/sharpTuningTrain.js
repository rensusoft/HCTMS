var stu_auth_id = "";

var stuClass = '';
var planConfig = '';

var lastrow;
var lastcell;
$(function(){
	function GetRequest() { //取URL参数
		var url = window.location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
				} 
			} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	stu_auth_id = Request['stu_auth_id'];
	stuClass = $("#stuClass",window.parent.document).text();
	planConfig = $("#planConfig",window.parent.document).val();
	//
	$( "#trainPlanBeforeGrid" ).tableDnD({
		scrollAmount : 0
		});
	planInit();
	
});
//
function planInit() {
	$("#trainPlanBeforeGrid").jqGrid({
		url : ctx+'/configweb/getTrainPlanNow.action',
		datatype : "json",
		colNames: ['ID','config_id','科室','科室ID', '开始时间', '结束时间','时长（天）','操作'],
        colModel: [
            {name: 'id',index:'id',fixed:true,hidden:true,key:true},
           	{name: 'config_id',index:'config_id',hidden:true},
            {name: 'orga_name', index: 'orga_name', align: "center",editable:true,edittype:'select',editoptions:{value:getOrga()}},
            {name: 'train_dept_id', index: 'train_dept_id', align: "center",hidden:true},
            {name: 'train_start_date', index: 'train_start_date', align: "center"},
            {name: 'train_end_date', index: 'train_end_date', align: "center"},
            {name: 'days', index: 'days', align: "center",editable:true},
            {name: 'operation', index: 'operation', align: "center"}
        ],
        rowNum : 200,
        rowList : [200, 500, 1000, 2000, 5000],
        pager : '#trainPlanBeforeToolbar',
        viewrecords : true,
        mtype: 'POST',
        postData: {
        	stu_auth_id: stu_auth_id
        },
        width: 1060,
        height: pHeight-280,
		shrinkToFit:true,
		resizable:false,
		modal:true,
		cellEdit: true,
        cellsubmit: 'clientArray',
        beforeEditCell:function(rowid,cellname,value,iRow,iCol){
        	lastrow = iRow;
        	lastcell = iCol;
        },
        gridComplete : function () {
        	var ids=$('#trainPlanBeforeGrid').jqGrid('getDataIDs');
        	for(var i = 0; i < ids.length ; i ++){
        		var id = ids[i];
        		$("#"+id).attr("style","cursor:pointer");
        		var re = "<a style='color: #505f91;cursor: pointer' onclick='deleteRow("+id+")'>删除</a>";
        		jQuery("#trainPlanBeforeGrid").jqGrid("setRowData", id, {operation: re});
        	}
        	$( "#_empty" , "#trainPlanBeforeGrid" ).addClass( "nodrag nodrop" ); //样式
        	$( "#trainPlanBeforeGrid" ).tableDnDUpdate(); //更新jquery.tablednd.js插件的方法。
        	},
	}).navGrid("#trainPlanBeforeToolbar", {edit : false,add : false,del : false,search:false,refreshstate:'current'}).navButtonAdd("#trainPlanBeforeToolbar", {caption: "新增",buttonicon:"ui-icon-plus", onClickButton: addRow, position: "first"});

    $("#trainPlanBeforeGrid").tableDnD({
        onDragClass:'highlight',
        onDrop:function(table,row){
        	var rowId="";
        	    rowId=$("#trainPlanBeforeGrid").jqGrid("getGridParam","selrow");
        	    if(rowId!=null){
        	    	var iRow = $('#' + rowId)[0].rowIndex;
                	$("#trainPlanBeforeGrid").jqGrid("saveCell",iRow,lastcell);	
        	    }
        }
  });
}


//function getday(){
//	$("#trainPlanBeforeGrid").jqGrid("saveCell",lastrow,lastcell);
//	var selectID=$("#trainPlanBeforeGrid").getGridParam("selrow");
//	var train_start_date=$("#trainPlanBeforeGrid").jqGrid("getCell",selectID,"train_start_date");//行ID和列名字,决定了一个单元格的位置，从而可以取出单元格的值
//	var train_end_date=$("#trainPlanBeforeGrid").jqGrid("getCell",selectID,"train_end_date");//行ID和列名字,决定了一个单元格的位置，从而可以取出单元格的值
//	if(train_start_date!=""&&train_end_date!=""){
//		var date1=train_start_date.substring(0,4)+"-"+train_start_date.substring(4,6)+"-"+train_start_date.substring(6,8);
//		var date2=train_end_date.substring(0,4)+"-"+train_end_date.substring(4,6)+"-"+train_end_date.substring(6,8);
//		var date1Str = date1.split("-");//将日期字符串分隔为数组,数组元素分别为年.月.日
//		//根据年 . 月 . 日的值创建Date对象
//		var date1Obj = new Date(date1Str[0],(date1Str[1]-1),date1Str[2]);
//		var date2Str = date2.split("-");
//		var date2Obj = new Date(date2Str[0],(date2Str[1]-1),date2Str[2]);
//		var t1 = date1Obj.getTime();
//		var t2 = date2Obj.getTime();
//		var dateTime = 1000*60*60*24; //每一天的毫秒数
//		var minusDays = Math.floor(((t2-t1)/dateTime))+1;//计算出两个日期的天数差
//		if(minusDays<0){
//			myalert_error("开始时间不能大于结束时间");
//			return;
//		}
//		 $("#trainPlanBeforeGrid").jqGrid("setCell",selectID,"days",minusDays);//设置单元格的值，需要行id，列名，以及对应列名的值
//	};
//}

function addRow(){
	 var ids = jQuery("#trainPlanBeforeGrid").jqGrid('getDataIDs');
	 var rowid = Math.max.apply(Math,ids);
	 var newInd = rowid + 1;
	 //获得当前最大行号（数据编号）
	 var dataRow={
			id:newInd,
			config_id:"",
			orga_name:"",
			train_dept_id:"",
			train_start_date:"",
			train_end_date:"",
			days:0,
	 };
	 $("#trainPlanBeforeGrid").jqGrid("addRowData", newInd, dataRow, "last");
//	 var ids = jQuery("#trainPlanBeforeGrid").jqGrid('getDataIDs');
//	 for(var i=0;i<ids.length;i++){
//		 $('#trainPlanBeforeGrid').jqGrid('editRow', ids[i], false); 
//	 }
}

function getOrga(){
	var str="";
	$.ajax({
  		type: 'POST',
	  	url: ctx+'/teachweb/getOrgaInfo.action',
	  	data:{
	  		stu_auth_id:stu_auth_id
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 	 var jsonobj=eval(data);
         var length=jsonobj.length;
         for(var i=0;i<length;i++){
             if(i!=length-1){
              str+=jsonobj[i].dept_id+":"+jsonobj[i].orga_name+";";
             }else{
                str+=jsonobj[i].dept_id+":"+jsonobj[i].orga_name;// 这里是option里面的 value:label
             }
          }   
     	}
	});
	return str;
}
//
function reloadGrid(){
	$("#trainPlanBeforeGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	stu_auth_id: stu_auth_id
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

 
function dateFormate(date,days){
//	date="2018-08-30";
//	days=2;
	var dateStr = date.replace(/-/g, "/");//现将yyyy-MM-dd类型转换为yyyy/MM/dd
	var dateTime = Date.parse(dateStr);//将日期字符串转换为表示日期的秒数
	//注意：Date.parse(dateStr)默认情况下只能转换：月/日/年 格式的字符串，但是经测试年/月/日格式的字符串也能被解析
	var data = new Date(dateTime);//将日期秒数转换为日期格式
	daysInt=parseInt(days);
	var myDate=new Date(data.getTime()+(daysInt-1)*24*60*60*1000);
	var year=myDate.getFullYear();
	var month=myDate.getMonth()+1;
	var day=myDate.getDate();
	CurrentDate=year+"";
	if(month>=10)
	{
		CurrentDate=CurrentDate+month+"";
	}
	else
	{
		CurrentDate=CurrentDate+"0"+month+"";
	}
	if(day>=10)
	{
		CurrentDate=CurrentDate+day;
	}
	else
	{
		CurrentDate=CurrentDate+"0"+day;
	}
	return CurrentDate;
}

function addMonth(date, num)
{
    //date为格式化后的日期字符yyyy-MM-dd,num为增加的月份
    var mouthnum = parseInt(num);
    var year = parseInt(date.substring(0, 4));
    var mouth = parseInt(date.substring(5, 7));
    var day = parseInt(date.substring(8, 10));
    if (mouth + mouthnum > 12)
    {
        var newyear = year + ((mouth + mouthnum)%12);
        var newmouth = mouth + mouthnum - 12*((mouth + mouthnum)%12);
        var newday = day;
    }
    else
    {
        var newyear = year
        var newmouth = mouth + mouthnum;
        var newday = day;
    }
    var curDate = new Date(newyear,newmouth-1,newday);
    var preDate = new Date(curDate.getTime() - 24*60*60*1000); //前一天
    var year=preDate.getFullYear();
    var month=preDate.getMonth()+1;
    if(month<10){
    	month="0"+month
    }
    var day=preDate.getDate();
    if(day<10){
    	day="0"+day;
    }
    preDate=year+""+month+""+day;
    return preDate;
}
function resetTrainPlanBefore(){
//	var selectRow = jQuery("#trainPlanBeforeGrid").jqGrid('getGridParam','selrow');
//	var iRow = $('#' + selectRow)[0].rowIndex;
//	$("#trainPlanBeforeGrid").jqGrid("saveCell",iRow,lastcell);
	$("#trainPlanBeforeGrid").jqGrid("saveCell",lastrow,lastcell);
	var ids=$('#trainPlanBeforeGrid').jqGrid('getDataIDs');
	for(var i = 0; i < ids.length ; i ++){
		var days=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[i],"days");
		var orga_name=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[i],"orga_name");
		var train_start_date=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[0],"train_start_date");
		if(orga_name==0){
			myalert_error("第"+(i+1)+"行未选择科室");
			return;
		}
		if(train_start_date==""){
			myalert_error("新增轮转科室时不可放入第一行");
			return;
		}
		if(days==""){
			myalert_error("第"+(i+1)+"行时长不能为空");
			return;
		}
		if(days==0){
			myalert_error("第"+(i+1)+"行时长不能为0");
			return;
		}
		if(isNaN(days)){
			myalert_error("第"+(i+1)+"行时长输入错误");
			return;
		}
	}
	for(var i = 0; i < ids.length ; i ++){
		var train_begain_date=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[0],"train_start_date");
//		var train_start_date = jQuery("#trainPlanBeforeGrid").jqGrid('getRowData',ids[i]).train_start_date;//或得每一行的流程状态
		if(train_begain_date==""){
			train_begain_date=stuClass;
			$("#trainPlanBeforeGrid").jqGrid("setCell",ids[0],"train_start_date",train_begain_date);
		}
		if(train_begain_date>stuClass){
			train_begain_date=stuClass;
			$("#trainPlanBeforeGrid").jqGrid("setCell",ids[0],"train_start_date",train_begain_date);
		}
		var train_start_date=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[i],"train_start_date");
		var date1=train_start_date.substring(0,4)+"-"+train_start_date.substring(4,6)+"-"+train_start_date.substring(6,8);
//		var train_end_date=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[i],"train_end_date");
		var days=$("#trainPlanBeforeGrid").jqGrid("getCell",ids[i],"days");
		//预计结束日期=开始日期+期限
		var train_end_day = dateFormate(date1,parseInt(days));
		$("#trainPlanBeforeGrid").jqGrid("setCell",ids[i],"train_end_date",train_end_day);
		var date2=train_end_day.substring(0,4)+"-"+train_end_day.substring(4,6)+"-"+train_end_day.substring(6,8);
		$("#trainPlanBeforeGrid").jqGrid("setCell",ids[i+1],"train_start_date",dateFormate(date2,2));
	}
	
	
//	$("#trainPlanBeforeGrid").jqGrid("saveCell",businessplanmag_iRow,businessplanmag_iCol);
	
//	for(var i = 0; i < ids.length ; i ++){
//		$("#trainPlanBeforeGrid").jqGrid("saveCell",iRow,iCol);
//		var rowData = $("#trainPlanBeforeGrid").jqGrid('getRowData', ids[i]);
//		var train_start_date = rowData['train_start_date'];
//    }
	var sub=[];
	for(var k = 0; k < ids.length ; k ++){
		if(k==0){
			var day= $("#trainPlanBeforeGrid").jqGrid('getRowData', ids[0]);
			if(parseInt(stuClass)-parseInt(day)<0){
				myMsg("轮转开始时间不能小于届次！","15%","40%");
				return;
			}
		}
		var item={};
		var rowData1 = $("#trainPlanBeforeGrid").jqGrid('getRowData', ids[k]);
		item.orga_name=rowData1['orga_name'];
		item.train_start_date=rowData1['train_start_date'];
		item.train_end_date=rowData1['train_end_date'];
		sub.push(item);
	}
	if(sub.length == 0){
		return;
	}
	alert(sub);
	$.ajax({
  		type: 'POST',
	  	url: ctx+'/configweb/resetTrainPlan.action',
	  	data:{
	  		data:JSON.stringify(sub),
	  		stu_auth_id:stu_auth_id,
	  		tscId:planConfig,
	  		time:stuClass
	  	},
	  	dataType: 'json',
	  	async: false,
  	 	success:function(data) {
  	 		if(data.success==true){
  	 			reloadGrid(stu_auth_id);
  	 			myMsg("微调成功！","15%","40%");
  	 		}else{
  	 			myMsg("微调失败！","30%","40%");
  	 		}
     	}
	});
}

 function deleteRow(id) {
 if(id==""){
	 myMsg("请选择删除的行！","15%","40%");
		return;
 }else{          
     $("#trainPlanBeforeGrid").jqGrid("delRowData", id);     
 }
}