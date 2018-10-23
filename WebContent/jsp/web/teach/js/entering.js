var text;    //点击录入  当前行的信息
var content1;
$(function () {
    //页面加载完成之后执行
    pageInit();
    $(".click_a").click(
        function () {
            $(".table_cont").toggle();
            if ($(".table_cont").is(":visible")) {
                $(".lf_cont").width(950);
                $(".table_cont").fadeIn();
                $(".click_a i img").attr({src: ctx+"/jsp/web/teach/img/select_cl.png"});
            } else {
                $(".table_cont").fadeOut();
                $(".lf_cont").width(1850);
                $(".click_a i img").attr({src: ctx+"/jsp/web/teach/img/select_op.png"});
            }
        }
    );
//    $("#dssd").select2({
//        minimumResultsForSearch: Infinity
//    });
    
	var url=ctx+"/teachweb/getTable.action"; 
	 doAjax(url,"",2,function(res){   //拿值给表格填数据   
		var table="";
		for ( var i = 0; i < res.data.length; i++) {
			var finnish_num = 0;
			if(res.data[i].finnish_num != null && res.data[i].finnish_num != ''){
				finnish_num = res.data[i].finnish_num;
			}
			var j=res.data[i].data_type_id;    //数据类型id
			var id=res.data[i].id;   //表示id;
			if(res.data[i].type_id==0){
				table+="<tr class='ct gray_bg'  id='tr"+i+"'> <td><span>"+res.data[i].order_name
				+"：</span></td><td>"+finnish_num
				+"&nbsp;&nbsp;"+res.data[i].order_num_unit
				+"</td><td>完成比例：<i>";
				if(finnish_num>res.data[i].order_num){
					finnish_num=res.data[i].order_num;
				}
				table+=parseFloat((finnish_num/res.data[i].order_num)*100).toFixed(2)+"%"
				+"</i></td><td id='td'>要求数：<b>"+res.data[i].order_num
				+"</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;<a class='en_btn'>录入<span style='display:none;' class='_name'>"+res.data[i].order_name+"</span><span style='display:none;' class='_id'>"+id+"</span><span style='display:none;' class='_j'>"+j+"</span></a></td></tr>";
			}else if(res.data[i].type_id==1){
				table+="<tr class='ct"+i+"'  id='tr"+i+"'> <td><span>"+res.data[i].order_name
				+"：</span></td><td></td><td></td><td></td></tr>";	
			}else{
				table+= "<tr class='child gray_bg' id='tr"+i+"'><td><span>"+res.data[i].order_name
				+"：</span></td><td>"+finnish_num
				+"&nbsp;&nbsp;"+res.data[i].order_num_unit
				+"</td><td>完成比例：<i>";
				if(finnish_num>res.data[i].order_num){
					finnish_num=res.data[i].order_num;
				}
				table+=((finnish_num/res.data[i].order_num)*100).toFixed(2)+"%"
				+"</i></td><td id='td'>要求数：<b>"+res.data[i].order_num
				+"</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;<a class='en_btn'>录入<span style='display:none;' class='_name'>"+res.data[i].order_name+"</span><span style='display:none;' class='_id'>"+id+"</span><span style='display:none;' class='_j'>"+j+"</span></a></td></tr>";	
			};	
		};
		$("#list").html(table);
		 $(".en_btn").click(function(){

				var id = $(this).children("[class='_id']").text();

				var j = $(this).children("[class='_j']").text();
				if(j==1){
					mypopdiv(2,"病种和技能",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/emergency.jsp?id="+id+"&action=add");
				}else if(j==2){
					mypopdiv(2,"其他轮转信息",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/activities.jsp?id="+id+"&action=add");	
				}else if(j==3){
					mypopdiv(2,"病例书写",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/case.jsp?id="+id+"&action=add");
				}else if(j==4){
					mypopdiv(2,"门急诊工作",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/registrInf.jsp?id="+id+"&action=add");	
				}else if(j==5){
					mypopdiv(2,"阅读参考书刊",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/operation.jsp?id="+id+"&action=add");	
				}else if(j==6){
					mypopdiv(2,"参加学术会议 ",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/academicConference.jsp?id="+id+"&action=add");
				}else if(j==7){
					mypopdiv(2,"参加科研",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/scientificResearch.jsp?id="+id+"&action=add");
			    }
				 else if(j==8){
					mypopdiv(2,"获奖情况",'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/awards.jsp?id="+id+"&action=add");
			    }
			});
	 });
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#entering_content").jqGrid(
        {
            url: ctx+'/teachweb/findEntering.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: [ '','名称', '提交日期','审核人','审核状态','操作','id','sto_id','data_type_id','examine_state'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....,
                {name: 'act', index: 'act', width: 10, align: "center"},
                {name: 'order_name', index: 'order_name', width: 100, align: "center"},
                {name: 'create_time_str', index: 'create_time_str', width: 70, align: "center"},
                {name: 'user_name', index: 'user_name', width: 70, align: "center"},
                {name: 'examineState', index: 'examineState', width: 40, align: "center"},
                {name: 'operation', index: 'operation', width: 40, align: "center",sortable:false},
                {name: 'id', index: 'id',hidden:true,key:true},
                {name: 'sto_id', index: 'sto_id',hidden:true},
                {name: 'data_type_id', index: 'data_type_id',hidden:true},
                {name: 'examine_state', index: 'examine_state',hidden:true}
            ],
            rowNum : 20,
            rowList : [ 20, 50, 100],
            pager : '#enteringPager',
            gridview: true,
            sortname: 'invdate',
            viewrecords : true,
            postData: {
            	examineState:$("#dssd").val(),
            	startTime:$("#classtime-from").val(),
            	endTime:$("#timeTo").val(),
            	text:$("#text").val()
            }, 
            width:pWidth-100,
            height:pHeight-170,           
            autowidth:false,
            shrinkToFit:true,
            resizable:false,
    		modal:true,
    		loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            gridComplete: function () {
                var ids = jQuery("#entering_content").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var rowData=$("#entering_content").jqGrid("getRowData",id);
                    var order_name=rowData.order_name;
                    var sto_id=rowData.sto_id;
                    var data_type_id=rowData.data_type_id;
                    var examine_state=rowData.examine_state;
                    var state=rowData.examineState;
                    var tu;
                    if(state=="审核通过"){
                    	tu="<img src='../teach/img/ok.png'>";
                    }else if(state=="待审核"){
                    	tu="<img src='../teach/img/question.png'>";
                    }else {
                    	tu="<img src='../teach/img/wrong.png'>";
                    }
                    jQuery("#entering_content").jqGrid("setRowData", id, {act: tu});
                    //
                    var se = "<a title=''  style='color: #505f91;cursor: pointer'" + "onclick=\"$('#entering_content').jqGrid('saveRow','" + id + "',upd('"+id+"','"+data_type_id+"','"+order_name+"','"+sto_id+"','"+examine_state+"'));\">编辑</a>&nbsp;&nbsp;&nbsp;";
                    var ce = "<a title=''  style='color: #505f91;cursor: pointer' href='#'" + "onclick=\"$('#entering_content').jqGrid('saveRow','" + id + "',del('" + id + "','" + examine_state + "'));\">删除</a>";
                   
                    jQuery("#entering_content").jqGrid("setRowData", id, {operation: se + ce});
                }		
            }
        });
    $("#entering_content").jqGrid().navGrid("#enteringPager", {edit : false,add : false,del : false,search : false,refresh : true});
}
//编辑
function upd(id,data_type_id,order_name,sto_id,examine_state){
	if(id == ""){
		myalert_error('请选择录入数据！');
		return false;
	}
	if(examine_state != 0){
		myalert_error('录入数据已被审核过，不可作编辑！');
		return false;
	}
	entering(sto_id,data_type_id,order_name,id);
}
//删除
function del(id,examine_state){
	if (id == "") {
		myalert_error('请选择录入数据！');
		return false;
	}
	if(examine_state != 0){
		myalert_error('录入数据已被审核过，不可作删除！');
		return false;
	}
	myConfirm("是否要删除？","","",
			function(){
			 var url=ctx+'/teachweb/delStuActiveData.action';
			 var postData={
					id:id,
					state:"X"
			 };
			 doAjax(url,postData,2,function(res){
				 if(res.success == true){
					 orgaInit();
					 myalert_success(res.data);
				 }else{
					 myalert_error(res.data);
				 }
			 });
	});
}
//录入 搜索查询
function query() {
    if ($.trim($("#txt").val()).length == 0) {
        $("#list tr").show();
    } else {
        $("#list tr")
            .hide()
            .filter(":contains('" + $("#txt").val() + "')")
            .show();
    }
}

function search(){
	var postData={
    	examineState:$("#dssd").val(),
    	startTime:$("#classtime-from").val(),
    	endTime:$("#timeTo").val(),
    	text:$("#text").val()
    };
    	$("#entering_content").jqGrid('setGridParam',{
            datatype:'json',
            postData: postData, //发送数据
            page: 1
        }).trigger("reloadGrid"); //重新载入
}

function viewReason(id){
	var height=0;
	if(pHeight>500){
		height=500;
	}else{
		height=pHeight;
	}
	mypopdiv(2,"审核意见",'1000px',(height-50)+'px','0px',(pWidth-1000)/2,'Y',ctx + "/jsp/web/teach/viewExamineText.jsp?id="+id);
}


function entering(id,data_type_id,order_name,sad_id){
	if(data_type_id==1){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/emergency.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
	}else if(data_type_id==2){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/activities.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");	
	}else if(data_type_id==3){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/case.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
	}else if(data_type_id==4){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/registrInf.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");	
	}else if(data_type_id==5){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/operation.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");	
	}else if(data_type_id==6){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/academicConference.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
	}else if(data_type_id==7){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/scientificResearch.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
    }
	 else if(data_type_id==8){
		mypopdiv(2,order_name,'600px','300px',(pHeight-600)/2,(pWidth-600)/2,'Y',ctx + "/jsp/web/teach/awards.jsp?id="+id+"&sad_id="+sad_id+"&action=edit");
    }
	
}


//录入后关闭窗口
function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			layer.closeAll();
			search();
		});
	}else{
		myalert_error(res.data,'','');
	}
}



function reset(){
	$("#txt").val("");
}



function cResrt(){
	$("#timeTo").val("");
	$("#classtime-from").val("");
	$("#text").val("");
	$("#dssd").val("-2");
}
//重新加载
function orgaInit(){
	$("#entering_content").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
