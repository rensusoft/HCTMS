var mcm_id = '';
$(function () {
    //页面加载完成之后执行
    pageInit();
    //实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('discuss_content',{scaleEnabled:true});
	//
    $(".homeworkCont").width($("section").width() * 0.6);
    if ($(".homeworkCont").is(":hidden")) {
        $(".rightBtnSpan").click(
            function(){
                $(".homeworkCont").toggle();
                $(".rightBtn").addClass("displayDiv");
            }
        );
        //$(".lf_cont").width(1850);
    }
    $(".toggleBtn").click(
        function(e){
            $(this).parents(".delTitle").next(".tableDiv").toggleClass("extend");
            var closeText=$(this).text();
            //console.log(closeText);
            if(closeText=='收起'){
                $(this).html("<img src='" + ctx + "/jsp/web/teach/img/arrowB.png'/>展开")
            }else{
                $(this).html("<img src='" + ctx + "/jsp/web/teach/img/arrowT.png'/>收起")
            }
        }
    );
    $(".leftBtn").click(
        function(){
            $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
            $(".textDiv").removeClass("displayDiv");
        }
    );
    $(".chargeUl").height($(".rightBtn").height()-301);
    //$(".textDiv").width($(".rightDiv").width());
    $('[data-toggle="tooltip"]').tooltip();//人数提示框
    if(pHeight>500){
    	$("#discuss_content").height(200);
    }else{
    	$("#discuss_content").height(30);
    }
});
//发布病例讨论
function releaseDiscuss(){
	relesepup = mypopdiv(2,"发布病例讨论",'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2,'N',ctx + '/jsp/web/teach/newMedicalCase.jsp');
}
//
function pageInit() {
    //创建jqGrid组件
    jQuery("#medicalCaseDiscuss").jqGrid(
        {
            url: ctx+'/teachweb/selectMedicalCaseDiscuss.action',
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id', '病例讨论说明', '发起人', '', '讨论开始时间', '讨论结束时间', '病人住院号', '阐述人', '', '状态', '', '', '', '', '操作'],//jqGrid的列显示名字
            colModel: [
                {name: 'id', index: 'id', fixed: true, hidden: true, key: true},
                {name: 'content', index: 'content', width: 230, align: "center"},
                {name: 'creator_auth_id_str', index: 'creator_auth_id_str', align: "center"},
                {name: 'creator_auth_id', index: 'creator_auth_id', hidden: true},
                {name: 'start_time_str', index: 'start_time_str', width: 135, align: "center"},
                {name: 'finish_time_str', index: 'finish_time_str', width: 135, align: "center"},
                {name: 'value', index: 'value', align: "center"},
                {name: 'exponent_auth_id_str', index: 'exponent_auth_id_str', align: "center"},
                {name: 'exponent_auth_id', index: 'exponent_auth_id', hidden: true},
                {name: 'status_str', index: 'status_str', width: 100, align: "center"},
                {name: 'status', index: 'status', hidden: true},
                {name: 'in_patient_info', index: 'in_patient_info', hidden: true},
                {name: 'exponent_content', index: 'exponent_content', hidden: true},
                {name: 'create_time_str', index: 'create_time_str', hidden: true},
                {name: 'operation', index: 'operation', width: 130, align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData: {
            	state: "Y"
            }, 
            width: pWidth-20,
            height: pHeight-125,
            gridComplete: function(){
                var ids = jQuery("#medicalCaseDiscuss").jqGrid("getDataIDs");
                for(var i = 0; i < ids.length; i++){
                    var id = ids[i];
                    var status = jQuery("#medicalCaseDiscuss").jqGrid('getRowData',id).status;
    		  		if(status == 1 || status == 4){
    		  			var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',view('" + id + "'));\">查看</a>";
    		  			jQuery("#medicalCaseDiscuss").jqGrid("setRowData", id, { operation : se });
    		  		}else if(status == 2){
    		  			var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',discuss('" + id + "'));\">讨论</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
        		  		var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',view('" + id + "'));\">查看</a>";
    		  			jQuery("#medicalCaseDiscuss").jqGrid("setRowData", id, { operation : re + se });
    		  		}else if(status == 3){
    		  			var re = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',discuss('" + id + "'));\">讨论</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
        		  		var se = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',view('" + id + "'));\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        		  		var ce = "<a title='' style='color:#7C87AD' href='#'"+ "onclick=\"$('#medicalCaseDiscuss').jqGrid('saveRow','" + id + "',grade('" + id + "'));\">评分</a>";
        		  		jQuery("#medicalCaseDiscuss").jqGrid("setRowData", id, { operation : re + se + ce });
    		  		}
                }
            }
        });
}
//刷新
function orgaInit(){
	$("#medicalCaseDiscuss").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//搜索
function keySearch(){
	var p_pid = $("#inp_p_pid").val();
	var search_start_time = $("#inp_start_time").val();
	var search_end_time = $("#inp_end_time").val();
	var status = $("#sel_status").val();
	$("#medicalCaseDiscuss").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y",
        	p_pid: p_pid,
        	search_start_time: search_start_time,
        	search_end_time: search_end_time,
        	status: status
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//讨论
function discuss(id){
	if (id == "") {
		myalert_error("请选择病例讨论！","30%","40%");
		return false;
	}
	mcm_id = id;
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
    var ulHeight = '';
    if(pHeight>500){
    	ulHeight=$("section").height()-420;
    }else{
    	ulHeight=$("section").height()-270;
    }
    $(".rightDiv .chargeUl").attr("style","margin-bottom:0 !important;height:"+ulHeight+"px;overflow:auto");
    //
    var rowData = $("#medicalCaseDiscuss").jqGrid('getRowData', id);
	$("#exponent_auth_id").val(rowData.exponent_auth_id);
	$("#creator_auth_id").val(rowData.creator_auth_id);
	showInfo(id);
}
//提交讨论
function submitDiscuss(){
	var discuss_content = UE.getEditor('discuss_content').getContent();
	if(discuss_content == ""){
		myalert_error("评论内容不能为空！","30%","40%");
		return false;
	}
	var url=ctx + "/teachweb/submitDiscuss.action";
	var postData = {
			id : mcm_id, 
			discuss_content : discuss_content
	};
	doAjax(url,postData,2,function(res){
		if(res.success == true){
			myalert_success(res.data,"30%","40%",function(){
				UE.getEditor('discuss_content').setContent("");
				layer.closeAll();
				$(".addDiv").addClass("displayDiv").siblings().removeClass("displayDiv");
	            orgaInit();
			});
		}else{
			myalert_error(res.data,"30%","40%");
		}
	});
}
//查看
function view(id){
	if (id == "") {
		myalert_error("请选择病例讨论！","30%","40%");
		return false;
	}
	mcm_id = id;
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
    $(".textDiv").addClass("displayDiv");
    $(".rightDiv .chargeUl").height($(".jqDiv").height()).attr("style","margin-bottom:0 !important;");
    //
    var rowData = $("#medicalCaseDiscuss").jqGrid('getRowData', id);
	$("#exponent_auth_id").val(rowData.exponent_auth_id);
	$("#creator_auth_id").val(rowData.creator_auth_id);
	showInfo(id);
}
//
function showInfo(id){
	var rowData = $("#medicalCaseDiscuss").jqGrid('getRowData', id);
	$("#creator1").html(rowData.creator_auth_id_str);		
    $("#createTime1").html(rowData.create_time_str);
    $("#startTime1").html(rowData.start_time_str);
    $("#finishTime1").html(rowData.finish_time_str);
    $("#exponent1").html(rowData.exponent_auth_id_str);
    $("#in_patient_info1").html(rowData.in_patient_info);
    $("#content1").html(rowData.content);
    $("#exponent_content1").html(rowData.exponent_content);
    //右边树图信息
    $("#exponent2").html(rowData.exponent_auth_id_str);
    $("#exponent_content2").html(rowData.exponent_content);
    $("#creator2").html(rowData.creator_auth_id_str);	
    $("#content2").html(rowData.content);
    
    var url=ctx+'/teachweb/discussStudentNum.action';
	var postData={
		mcm_id:id
	};
	doAjax(url,postData,2,function(res){
		$("#discussNum").val(res.data.length);
		var name="";
		for(var i=0;i<res.data.length;i++){
			
			name+=res.data[i].user_name+"  ";
		}
		var str="<i data-toggle='tooltip' data-placement='top' " +
				"title='"+name+"' style='font-style: normal;cursor: pointer;font-size: 16px;color: #505f91;font-weight: bold' >"+res.data.length+"</i>";
		$("#discussNum1").html(str);
	});	
	//
	showTree(id);	
}
//
function showTree(id){
	$("#div_img_tree").removeClass("displayDiv");
	var url=ctx+'/teachweb/discussinfomation.action';
	var postData={
		mcm_id:id
	};
	doAjax(url,postData,2,function(res){
		var rowData = $("#medicalCaseDiscuss").jqGrid('getRowData', id);
		var information="<li class='teaLi'>"+
				            "<b>"+rowData.create_time_str.slice(0,10)+"<br/>"+rowData.create_time_str.slice(11) +"<br/>"+
				               " <bold >"+rowData.creator_auth_id_str+"</bold>"+
				            "</b>"+
				            "<span></span>"+
				            "<i >"+
				            rowData.content+
				           " </i>"+
				       " </li>";
					if(rowData.exponent_content != null && rowData.exponent_content != ''){
						information +=
						" <li class='myLi'>"+
						"<b>"+rowData.start_time_str.slice(0,10)+"<br/>"+rowData.start_time_str.slice(11) +"<br/>"+
						" <bold>"+rowData.exponent_auth_id_str+"</bold>"+
						" </b>"+
						" <span></span>"+
						"<i >"+
						rowData.exponent_content+
						" </i>"+
						"</li>";
					}

		for(var i=0;i<res.data.length;i++){
			var discussTime=res.data[i].discuss_time_str;
			information+= "<li>"+
				           " <b>"+discussTime.slice(0,10)+"<br/>"+discussTime.slice(11)+"<br/>"+
				               " <bold>"+res.data[i].user_name+"</bold>"+
				            "</b>"+
				            "<span></span>"+
				            "<i>"+
				            res.data[i].content+
				            "</i>"+
				        "</li>";
		}
		$("#discussInfomation").html(information);
	});
}
//
function showInfo1(typeId){
	if(typeId==1){
		discussFlow(2,"","",2);
	}else if(typeId==2){
		discussFlow(3,"","",2);
	}else if(typeId==3){
		showTree(mcm_id);
	}
}
//评分
function grade(id){
	if (id == "") {
		myalert_error("请选择病例讨论！","30%","40%");
		return false;
	}
	mcm_id = id;
    $(".rightBtn").removeClass("displayDiv");
    $(".homeworkCont").toggle();
    if ($(".homeworkCont").is(":visible")) {
        $(".homeworkCont").fadeIn();
    }
    $.ajax({
		type: 'POST',
		async: false,
		url: ctx + "/teachweb/getDiscussants.action",
		data: {
			id : mcm_id
		},
		dataType: 'json',
		success:function(data) {
			var str = '';
			var discussants = data.discussants;
			if(discussants.length > 0){
				str += '<tr class="tr_grade">' +
				    '<td class="td_type" style="width: 80px;text-align: right"><b>阐述人：</b><input type="hidden" value="10" class="hid_auth_type" /></td>' +
					'<td class="td_data" style="width: 100px;">'+discussants[0].exponent_auth_id_str+'<input type="hidden" value="'+discussants[0].exponent_auth_id+'" class="hid_auth_id" /></td>' +
					'<td style="text-align: right;width: 60px"><b>分数：</b></td>' +
					'<td><input type="number"min="0" max="100" class="form-control inp_eval_score" style="display: inline-block;width:150px;"/></td>' +
					'<td style="text-align: right;width: 60px"><b>评语：</b></td>' +
					'<td><input type="text" class="form-control inp_eval_content" style="display: inline-block;width:250px;"/></td>' +
					'<td><a class="btn btn-info btnSearch btn_search">查看评论</a></td>' +
					'</tr>';
				for(var i = 0; i < discussants.length; i++){
					str += '<tr class="tr_grade">' +
					'<td class="td_type" style="width: 80px;text-align: right"><b>讨论人：</b><input type="hidden" value="20" class="hid_auth_type" /></td>' +
					'<td class="td_data" style="width: 100px;">'+discussants[i].stu_auth_id_str+'<input type="hidden" value="'+discussants[i].stu_auth_id+'" class="hid_auth_id" /></td>' +
					'<td style="text-align: right;width: 60px"><b>分数：</b></td>' +
					'<td><input type="number"min="0" max="100" class="form-control inp_eval_score" style="display: inline-block;width:150px;"/></td>' +
					'<td style="text-align: right;width: 60px"><b>评语：</b></td>' +
					'<td><input type="text" class="form-control inp_eval_content" style="display: inline-block;width:250px;"/></td>' +
					'<td><a class="btn btn-info btnSearch btn_search">查看评论</a></td>' +
					'</tr>';
				}
			}
//			alert(str);
			$("#tab_discussants").html(str);
		}
	});
    //
    discussFlow(1,"","",1);
    //
    $(".inp_eval_score").focus(function(){
    	var auth_type = $(this).parent().prevAll(".td_type").children(".hid_auth_type").val();
    	var auth_id = $(this).parent().prevAll(".td_data").children(".hid_auth_id").val();
    	discussFlow(1,auth_type,auth_id,1);
    });
    $(".inp_eval_content").focus(function(){
    	var auth_type = $(this).parent().prevAll(".td_type").children(".hid_auth_type").val();
    	var auth_id = $(this).parent().prevAll(".td_data").children(".hid_auth_id").val();
    	discussFlow(1,auth_type,auth_id,1);
    });
    $(".btn_search").click(function(){
    	var auth_type = $(this).parent().prevAll(".td_type").children(".hid_auth_type").val();
    	var auth_id = $(this).parent().prevAll(".td_data").children(".hid_auth_id").val();
    	discussFlow(1,auth_type,auth_id,1);
    });
}
//讨论记录流程显示
function discussFlow(content_type,auth_type,auth_id,flagReq){//  content_type：内容类型（1：全部，2：阐述内容，3：讨论内容） auth_type：（10：阐述人，20：讨论人） auth_id：权限id flagReq：请求出发处
	if(auth_type == 10){
		auth_id = '';
	}
	//添加加载层
	var divPop = myLoading();
	$.ajax({
		type: 'POST',
		async: false,
		url: ctx + "/teachweb/getDiscussRecords.action",
		data: {
			id : mcm_id,
			auth_id : auth_id
		},
		dataType: 'json',
		success:function(data) {
			var str = '';
			var discussRecords = data.discussRecords;
			if(discussRecords.length > 0){
				$("#div_img").removeClass("displayDiv");
				if(content_type == 1){
					if(auth_type == 10){
						str += '<li class="stuLi">' +
						'<b>' +
						discussRecords[0].start_time_str.substring(0,10) + '<br/>' + discussRecords[0].start_time_str.substring(11) + '<br/>' +
						'<bold>' + discussRecords[0].exponent_auth_id_str + '</bold>' +
						'</b>' +
						'<span></span>' +
						'<i>' + discussRecords[0].exponent_content + '</i>' +
						'</li>';
					}else{
						if(auth_id == ''){
							str += '<li class="stuLi">' +
							'<b>' +
							discussRecords[0].start_time_str.substring(0,10) + '<br/>' + discussRecords[0].start_time_str.substring(11) + '<br/>' +
							'<bold>' + discussRecords[0].exponent_auth_id_str + '</bold>' +
							'</b>' +
							'<span></span>' +
							'<i>' + discussRecords[0].exponent_content + '</i>' +
							'</li>';
						}
						for(var i = 0; i < discussRecords.length; i++){
							str += '<li>' +
							'<b>' +
							discussRecords[i].discuss_time_str.substring(0,10) + '<br/>' + discussRecords[i].discuss_time_str.substring(11) + '<br/>' +
							'<bold>' + discussRecords[i].discuss_auth_id_str + '</bold>' +
							'</b>' +
							'<span></span>' +
							'<i>' + discussRecords[i].discuss_content + '</i>' +
							'</li>';
						}
					}
				}else if(content_type == 2){
					if(discussRecords[0].start_time_str != null){
						$("#div_img_tree").removeClass("displayDiv");
						if(flagReq == 1){
							str += '<li class="stuLi">';
						}else if(flagReq == 2){
							str += '<li class="myLi">';
						}
						str += 
							'<b>' +
							discussRecords[0].start_time_str.substring(0,10) + '<br/>' + discussRecords[0].start_time_str.substring(11) + '<br/>' +
							'<bold>' + discussRecords[0].exponent_auth_id_str + '</bold>' +
							'</b>' +
							'<span></span>' +
							'<i>' + discussRecords[0].exponent_content + '</i>' +
							'</li>';
					}else{
						$("#div_img_tree").addClass("displayDiv");
					}
				}else if(content_type == 3){
					if(discussRecords[0].discuss_time_str != null){
						$("#div_img_tree").removeClass("displayDiv");
						for(var i = 0; i < discussRecords.length; i++){
								str += '<li>' +
								'<b>' +
								discussRecords[i].discuss_time_str.substring(0,10) + '<br/>' + discussRecords[i].discuss_time_str.substring(11) + '<br/>' +
								'<bold>' + discussRecords[i].discuss_auth_id_str + '</bold>' +
								'</b>' +
								'<span></span>' +
								'<i>' + discussRecords[i].discuss_content + '</i>' +
								'</li>';
						}
					}else{
						$("#div_img_tree").addClass("displayDiv");
					}
				}
			}else{
				$("#div_img").addClass("displayDiv");
			}
			//去掉加载层
    		closeMyLoading(divPop);
//			alert(str);
    		if(flagReq == 1){
    			$("#ul_discussRecords").html(str);
    		}else if(flagReq == 2){
    			$("#discussInfomation").html(str);
    		}
		}
	});
}
//提交评分
function submitGrade(){
	var sco_evalList = [];
	var i = 0;
	var flag = true;
	var myAlert = '';
	$(".tr_grade").each(function(){
		i++;
		var sco_eval = {};
		var auth_type = $(this).find(".hid_auth_type").val();
		sco_eval.auth_type = auth_type;
		var auth_id = $(this).find(".hid_auth_id").val();
		sco_eval.stu_auth_id = auth_id;
		var eval_score = $(this).find(".inp_eval_score").val();
		if(eval_score == ''){
			flag = false;
			myAlert = '学生分数第['+i+']行的分数不能为空或非数字！';
			return false;
		}
		sco_eval.eval_score = eval_score;
		var eval_content = $(this).find(".inp_eval_content").val();
		if(eval_content == ''){
			flag = false;
			myAlert = '学生分数第['+i+']行的评语不能为空！';
			return false;
		}
		sco_eval.eval_content = eval_content;
		sco_evalList.push(sco_eval);
	});
	if(!flag){
		myalert_error(myAlert,"30%","40%");
		return;
	}
	var url=ctx + "/teachweb/submitGrade.action";
	var postData = {
			id : mcm_id,
			data : JSON.stringify(sco_evalList)
	};
	doAjax(url,postData,2,function(res){
		if(res.success == true){
			myalert_success(res.data,"30%","40%",function(){
				layer.closeAll();
				$(".homeworkCont").toggle();
                $(".rightBtn").addClass("displayDiv");
                orgaInit();
			});
		}else{
			myalert_error(res.data,"30%","40%");
		}
	});
}
//关闭窗口
function closeMyWindows(){
	orgaInit();
	layer.closeAll();
}