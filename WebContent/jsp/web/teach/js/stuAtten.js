var tea_type="";
$(function () {
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
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
	if(Request['width']!=null&&Request['width']!=""){
		pWidth = Request['width'];
		pHeight = Request['height'];
		stu_auth = Request['stu_auth'];
		orga_id = Request['orga_id'];
		tea_type = Request['tea_type'];
		stu_name = Request['stu_name'];
		orga_name = Request['orga_name'];
		if(stu_auth!=null&&stu_auth!="")
			$('#authId').val(stu_auth);
		if(orgaId!=null&&orgaId!="")
			$('#orgaId').val(orga_id);
	}
	$(".img_box").height($("#divv").height());
	$.ajax({
	  	type: 'POST',
	  	url :ctx+'/teachweb/getTrainOrga.action', 
		data : {
			authId:$("#authId").val()
			},
		dataType: 'json',
	  	success:function(data) {
	  		var option="<label class='control-label' style='font-weight:bold;'>轮转科室：</label><div class='select' id='_selectId'><select name='identity' class='form-control'onchange='changeOffice();' id='orgaChangId'>";
	  		for ( var int = 0; int < data.trainList.length; int++){
	  			if(data.trainList[int].train_dept_id==$("#orgaId").val()){
	  				option+="<option value="+data.trainList[int].train_dept_id+" selected='selected'>"+data.trainList[int].orga_name+"</option>";
	  			}else{
	  				option+="<option value="+data.trainList[int].train_dept_id+">"+data.trainList[int].orga_name+"</option>";	
	  			}
			}
	  		option+="</select></div>";
	  		//拿到当前学生教学轮转表的下拉
	  		document.getElementById("keShi"). innerHTML=option; 
	  		$('#_selectId').selectOrDie();
	  		//老师查询去掉下拉框，显示当前学生
  		if(tea_type=='tea'){
	  			var str='<span style="margin-left:15px;color:#505f91;">当前科室:  '+orga_name+'</span>'+'<span style="margin-left:50px;color:#505f91;">当前学生:  ' +stu_name+'</span>';
	  			document.getElementById("keShi"). innerHTML=str; 
	  		}
	  	}
	});
    //页面加载完成之后执行	
    pageInit();
    $("#img").css("width",pWidth/2.5);
});
function reloadGrid() {
    $("#SelectKaoqinPanel").jqGrid('setGridParam', {
        datatype: 'json',
        page: 1
    }).trigger("reloadGrid"); //重新载入
};
function pageInit() {
    //创建jqGrid组件
    jQuery("#SelectKaoqinPanel").jqGrid(
        {
            url: ctx+'/teachweb/attendance.action?authId='+$("#authId").val()+"&orgaId="+$("#orgaId").val(),//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','考勤日期', '考勤状态',''],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', align: "center",key:true,hidden:true},       
                {name: 'attendeDate_Str', attend_date: 'attendeDate_Str', align: "center"},
                {name: 'attend_name', index: 'attend_name', align: "center"},
                {name: 'state_id', index: 'state_id', align: "center",hidden:true}
            ],
            rowNum: 500,//一页显示多少条
            //rowList: [500],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            sortname: 'ident',//初始化的时候排序的字段
            sortorder: "desc",//排序方式,可选desc,asc
            postData:{
            	orga:$("#orgaId").val()
            },
            mtype: "post",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: pWidth/2,
            height: pHeight-165,
            shrinkToFit: true,
            onSelectRow: function (rowid) {
                var rowDatas = $("#SysDictMainList").jqGrid('getRowData', rowid);
                var row = rowDatas['ident'];
                var postData = {
                    tpId: row
                };
                //console.log(postData);
                $("#SysDictSubList").jqGrid('setGridParam', {
                    datatype: 'json',
                    postData: postData, //发送数据
                    page: 1
                }).trigger("reloadGrid");
            },
            gridComplete: function() { 
//            	$('#SelectKaoqinPanel').css("background-color","#848484");
            	$.ajax({
        			url:ctx+'/teachweb/getPercent.action',
        			dataType: 'json',
        			 data:{},
        	         async: false,
            		type: 'POST',
            	     	success:function(res) {
            				tu(res.attendance,res.noAttendance,res.leave);
        				}
            	});
            	changeColor();
            	
            }

        });
    /*创建jqGrid的操作按钮容器*/
    /*可以控制界面上增删改查的按钮是否显示*/
    jQuery("#SysDictMainList").jqGrid('navGrid', '#pager1', {edit: false, add: true, del: false});
}

function changeOffice(){
	var postDate={
			orga:$("#orgaChangId").val()
	};
    $("#SelectKaoqinPanel").jqGrid('setGridParam', {
        datatype: 'json',
        postData: postDate, //发送数据
        page: 1
    }).trigger("reloadGrid");
}

function tu(attendance,noAttendance,leave){
	var title = {
            text: '考勤比例图'
        };
        var chart = {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            height:pWidth/3
        };
        var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };
        var series= [{
            type: 'pie',
            size:pWidth/6,
            name: '百分比',
            data: [
                ['已考勤', attendance],
                ['未考勤', noAttendance],
                ['请假',leave]
            ]
        }];
        var json = {};
        json.title = title;
        json.chart = chart;
        json.tooltip = tooltip;
        json.series = series;
        $('#pie_box').highcharts(json);
};
//改变字体颜色
function changeColor(){
	var ids=$("#SelectKaoqinPanel").jqGrid("getDataIDs");
	for ( var i = 0; i < ids.length; i++) {
		var rowData=$("#SelectKaoqinPanel").jqGrid("getRowData",ids[i]);
		var attend_name=rowData.attend_name;
		if(attend_name=="请假"){
			$('#'+ids[i]).css("background-color","#90ED7D");
		}else if(rowData.attend_name=="未考勤"){
			$('#'+ids[i]).css("background-color","#CCCCCC");
		}else{
			$('#'+ids[i]).css("background-color","#FFFFFF");
		}
	}
}
