$(function () {
	UE.getEditor('exponentText',{scaleEnabled:true});
	UE.getEditor('discussContent',{scaleEnabled:true});
    //页面加载完成之后执行
    pageInit();
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
    $(".leftBtn").click(
        function(){
            $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
            $(".textDiv").removeClass("displayDiv");
        }
    );
    $(".toggleBtn").click(
        function(e){
            $(this).parents(".delTitle").next(".tableDiv").toggleClass("extend");
            var closeText=$(this).text();
            //console.log(closeText);
            if(closeText=='收起'){
                $(this).html("<img src='img/arrowB.png'/>展开")
            }else{
                $(this).html("<img src='img/arrowT.png'/>收起")
            }
        }
    );
    //$(".textDiv").width($(".rightDiv").width());
    $(".chargeUl").height($(".rightBtn").height()-250-$(".stuCharge").height());
    //$(".textDiv").width($(".rightDiv").width());
    $('[data-toggle="tooltip"]').tooltip();//人数提示框
    
    
    if(pHeight>500){
    	$("#exponentText").height(400);
    	$("#discussContent").height(400);
    }else{
    	$("#exponentText").height(30);
    	$("#discussContent").height(30);
    }
});

function pageInit() {
    //创建jqGrid组件
    jQuery("#rotateRule").jqGrid(
        {
        	url: ctx+'/teachweb/findDiscuss.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['id','病例讨论说明','阐述人Id', '阐述人','阐述内容', '病人住院号', '讨论开始时间','讨论结束时间','创建时间','发起人Id','发起人','状态','','病例讨论阐述','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id',index:'id',fixed:true,hidden:true,key:true},
                {name: 'content', index: 'content', width: 200, align: "center"},
                {name: 'exponent_auth_id', index: 'exponent_auth_id', align: "center",hidden:true},
                {name: 'exponent_auth_id_str', index: 'exponent_auth_id_str', align: "center"},
                {name: 'exponent_content', index: 'exponent_content', width: 200,  align: "center"},
                {name: 'in_patient_info', index: 'in_patient_info',align: "center"},
                {name: 'start_time_str', index: 'start_time_str', width: 180, align: "center"},
                {name: 'finish_time_str', index: 'finish_time_str',width: 180, align: "center"},
                {name: 'create_time_str', index: 'create_time', align: "center",hidden:true},
                {name: 'creator_auth_id', index: 'creator_auth_id', hidden:true, align: "center"},
                {name: 'creator_auth_id_str', index: 'creator_auth_id_str',align: "center"},
                {name: 'discussStatus', index: 'discussStatus', align: "center"},
                {name: 'status', index: 'status', align: "center",hidden:true,},
                {name: 'exponent_content', index: 'exponent_content',align: "center",hidden:true},
                {name: 'operation', index: 'studentType', width: 160, align: "center"}
            ],
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData:{
            	in_patient_info:$("#admissionNum").val(),
            	beginTime:$("#classtime-from").val(),
            	endTime:$("#timeTo").val(),
            	status:$("#status  option:selected").val()
            },
            width: pWidth-30,
            height: pHeight-120,
            gridComplete: function () {
                var ids = jQuery("#rotateRule").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var re = "<a style='color: #505f91;cursor: pointer' onclick='rightDiv("+id+")'>阐述</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='discuss("+id+")'>讨论</a>&nbsp;&nbsp;&nbsp;<a style='color: #505f91;cursor: pointer' onclick='check("+id+")'>查看</a>";
                    jQuery("#rotateRule").jqGrid("setRowData", id, {operation: re});
                }
            }
        });
}

//搜索重新加载页面
function query(){
	$("#rotateRule").jqGrid('setGridParam',{
        datatype: 'json',
        postData:{
        	in_patient_info:$("#admissionNum").val(),
        	beginTime:$("#classtime-from").val(),
        	endTime:$("#timeTo").val(),
        	status:$("#status  option:selected").val()
        }
    }).trigger("reloadGrid"); //重新载入
}

//阐述页面
function rightDiv(id){
	var qagrid = $("#rotateRule").jqGrid('getRowData', id);
	if(qagrid.status!=1&&qagrid.status!=2){
		myMsg('当前病例已评论或已评分，无法阐述');
		return ;
	}
	
	$("#mcm_Id").val(id);
	if(qagrid.exponent_auth_id!=$("#auth_id").val()){
		myMsg('您不是阐述人，无法阐述');
		return ;
	}
		$("#creator").html(qagrid.creator_auth_id_str);
		$("#creatorId").val(qagrid.creator_auth_id);
	    $("#createTime").html(qagrid.create_time_str);
	    $("#startTime").html(qagrid.start_time_str);
	    $("#finishTime").html(qagrid.finish_time_str);
	    $("#exponent").html(qagrid.exponent_auth_id_str);
	    $("#in_patient_info").html(qagrid.in_patient_info);
	    UE.getEditor('exponentText').setContent(qagrid.exponent_content);
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
			$("#discussNum").html(str);
		});
	    
	    
	    
	    
    $(".rightBtn").removeClass("displayDiv");
    $(".homeworkCont").toggle();
    if ($(".homeworkCont").is(":visible")) {
        //$(".lf_cont").width(950);
        $(".homeworkCont").fadeIn();
    }
}
//提交阐述内容
function submit(){
 	var mcm_id = $("#mcm_Id").val();
	var content=UE.getEditor('exponentText').getContent();
	if(content==""||content==null){
		myMsg("内容不能为空");
		return;
	}
	var url=ctx+'/teachweb/submitExponent.action';
	var postData={
		mcm_id:mcm_id,
		exponent_content:content
	};
	doAjax(url,postData,2,function(res){
		myMsg(res.data);
		  $(".rightBtn").addClass("displayDiv");
		    $(".homeworkCont").toggle();
		    if ($(".homeworkCont").is(":visible")) {
		        //$(".lf_cont").width(950);
		        $(".homeworkCont").fadeOut();
		    }
		    query();
		return ;
	});
}

//讨论页面
function discuss(id){	
	var qagrid = $("#rotateRule").jqGrid('getRowData', id);
	$("#qagrid").val(qagrid);
	$("#mcm_Id").val(id);
	$("#exponent_auth_id").val(qagrid.exponent_auth_id);
	$("#creator_auth_id").val(qagrid.creator_auth_id);
	if(qagrid.status!=2&&qagrid.status!=3){
		myMsg('当前病例未阐述或已评分，无法评论');
		return ;
	}
	showInfo(id);
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
    if(pHeight>500){
    	ulHeight=$("section").height()-330;
    }else{
    	ulHeight=$("section").height()-269;
    }
    
    $(".rightDiv .chargeUl").attr("style","margin-bottom:0 !important;height:"+ulHeight+"px;overflow:auto");
}

//提交讨论内容
function submitDiscuss(){
	var mcm_id = $("#mcm_Id").val();
	var content=UE.getEditor('discussContent').getContent();
	if(content==""||content==null){
		myMsg("内容不能为空");
		return;
	}
	var url=ctx+'/teachweb/submitStuDiscuss.action';
	var postData={
		mcm_id:mcm_id,
		content:content,
		discuss_auth_id:$("#auth_id").val()
	};
	doAjax(url,postData,2,function(res){
		myMsg(res.data);
		 $(".addDiv").addClass("displayDiv");
		    $(".jqDiv").removeClass("displayDiv");
		   // $(".rightDiv .chargeUl").height($("section").height()-355);
		query();

	});
}


//查看
function check(id){	
	var qagrid = $("#rotateRule").jqGrid('getRowData', id);
	$("#mcm_Id").val(id);
	$("#exponent_auth_id").val(qagrid.exponent_auth_id);
	$("#creator_auth_id").val(qagrid.creator_auth_id);
	showInfo(id);		
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
    $(".textDiv").addClass("displayDiv");
    $(".rightDiv .chargeUl").height($(".jqDiv").height()).attr("style","margin-bottom:0 !important;");
}
//阐述和查看共用页面信息
function showInfo(id){
	var qagrid = $("#rotateRule").jqGrid('getRowData', id);
	$("#mcm_Id").val(id);
		$("#creator1").html(qagrid.creator_auth_id_str);		
	    $("#createTime1").html(qagrid.create_time_str);
	    $("#startTime1").html(qagrid.start_time_str);
	    $("#finishTime1").html(qagrid.finish_time_str);
	    $("#exponent1").html(qagrid.exponent_auth_id_str);
	    $("#in_patient_info1").html(qagrid.in_patient_info);
	    $("#content1").html(qagrid.content);
	    $("#exponent_content1").html(qagrid.exponent_content);
	    //右边树图信息
	    $("#exponent2").html(qagrid.exponent_auth_id_str);
	    $("#exponent_content2").html(qagrid.exponent_content);
	    $("#creator2").html(qagrid.creator_auth_id_str);	
	    $("#content2").html(qagrid.content);
	    
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
	

	
	
	 var url=ctx+'/teachweb/discussinfomation.action';
		var postData={
			mcm_id:id
		};
		doAjax(url,postData,2,function(res){
			var information="<li class='teaLi'>"+
					            "<b>"+qagrid.create_time_str.slice(0,10)+"<br/>"+qagrid.create_time_str.slice(12) +"<br/>"+
					               " <bold >"+qagrid.creator_auth_id_str+"</bold>"+
					            "</b>"+
					            "<span></span>"+
					            "<i >"+
					               qagrid.content+
					           " </i>"+
					       " </li>";
						
			if(qagrid.exponent_content!=""&&qagrid.exponent_content!=null){
				information+=	" <li class='myLi'>"+
						            "<b>"+qagrid.start_time_str.slice(0,10)+"<br/>"+qagrid.start_time_str.slice(10,19) +"<br/>"+
						               " <bold>"+qagrid.exponent_auth_id_str+"</bold>"+
						           " </b>"+
						           " <span></span>"+
						            "<i >"+
						               qagrid.exponent_content+
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
//树图时间格式转换
function timeStampString (time){
    var datetime = new Date();
     datetime.setTime(time);
     var year = datetime.getFullYear();
     var month = datetime.getMonth() + 1;
     var date = datetime.getDate();
     var hour = datetime.getHours();
     var minute = datetime.getMinutes();
     var second = datetime.getSeconds();
     var mseconds = datetime.getMilliseconds();
     return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second+"."+mseconds;
};

//不同角色点击切换树图
function showInfo1(typeId){
	var auth_id="";
	if(typeId==4){
		auth_id=$("#auth_id").val();
		queryTree(auth_id);
	}else if(typeId==3){
		auth_id=$("#creator_auth_id").val();
		queryTree(auth_id);
	}else if(typeId==2){
		auth_id=$("#exponent_auth_id").val();
		queryTree(auth_id);
	}
	if(typeId==1){
		auth_id=$("#auth_id").val();
		queryTreeOther(auth_id);
	}
	
	
}
//根据auth_id加载树图内容
function queryTree(auth_id){
	var qagrid = $("#rotateRule").jqGrid('getRowData', $("#mcm_Id").val());	
	var mcm_id=$("#mcm_Id").val();

	var url=ctx+'/teachweb/discussinfomation1.action';
	var postData={
		mcm_id:mcm_id,
		auth_id:auth_id
	};
	doAjax(url,postData,2,function(res){
		var information1="<li class='teaLi'>"+
					        "<b>"+qagrid.create_time_str.slice(0,10)+"<br/>"+qagrid.create_time_str.slice(11) +"<br/>"+
					           " <bold >"+qagrid.creator_auth_id_str+"</bold>"+
					        "</b>"+
					        "<span></span>"+
					        "<i >"+
					           qagrid.content+
					       " </i>"+
					   " </li>";
					
	 if(qagrid.exponent_content!=""&&qagrid.exponent_content!=null){
			information1+=	" <li class='myLi'>"+
					            "<b>"+qagrid.start_time_str.slice(0,10)+"<br/>"+qagrid.start_time_str.slice(10,19) +"<br/>"+
					               " <bold>"+qagrid.exponent_auth_id_str+"</bold>"+
					           " </b>"+
					           " <span></span>"+
					            "<i >"+
					               qagrid.exponent_content+
					           " </i>"+
					        "</li>";				
						}
		for(var i=0;i<res.data.length;i++){
			var discussTime=res.data[i].discuss_time_str;
			information1+= "<li>"+
				           " <b>"+discussTime.slice(0,10)+"<br/>"+discussTime.slice(11)+"<br/>"+
				               " <bold>"+res.data[i].user_name+"</bold>"+
				            "</b>"+
				            "<span></span>"+
				            "<i>"+
				            res.data[i].content+
				            "</i>"+
				        "</li>";
		}
		
		$("#discussInfomation").html(information1);
	});
}
////点击其他学生根据auth_id加载树图内容
function queryTreeOther(auth_id){
	var qagrid = $("#rotateRule").jqGrid('getRowData', $("#mcm_Id").val());	
	var mcm_id=$("#mcm_Id").val();
	var creator_id=$("#creator_auth_id").val();
	var url=ctx+'/teachweb/discussinfomation2.action';
	var postData={
		mcm_id:mcm_id,
		auth_id:auth_id,
		creator_id:creator_id
	};
	doAjax(url,postData,2,function(res){
		var information="<li class='teaLi'>"+
					        "<b>"+qagrid.create_time_str.slice(0,10)+"<br/>"+qagrid.create_time_str.slice(11) +"<br/>"+
					           " <bold >"+qagrid.creator_auth_id_str+"</bold>"+
					        "</b>"+
					        "<span></span>"+
					        "<i >"+
					           qagrid.content+
					       " </i>"+
					   " </li>";

		if(qagrid.exponent_content!=""&&qagrid.exponent_content!=null){
		  information+=	" <li class='myLi'>"+
			            "<b>"+qagrid.start_time_str.slice(0,10)+"<br/>"+qagrid.start_time_str.slice(10,19) +"<br/>"+
			               " <bold>"+qagrid.exponent_auth_id_str+"</bold>"+
			           " </b>"+
			           " <span></span>"+
			            "<i >"+
			               qagrid.exponent_content+
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


