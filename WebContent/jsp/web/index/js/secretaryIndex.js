var count=0;
var content="<table style='width:100%;position: absolute;top: 0;' >";

//饼图
function drawCircle(canvasId, data_arr, color_arr, text_arr)
{
    var c = document.getElementById(canvasId);
    var ctx = c.getContext("2d");

    var radius = c.height / 2 - 20; //半径
    var ox = radius + 20, oy = radius + 20; //圆心

    var width = 30, height = 10; //图例宽和高
    var posX = ox * 2 + 20, posY = 30;   //
    var textX = posX + width + 5, textY = posY + 10;

    var startAngle = 0; //起始弧度
    var endAngle = 0;   //结束弧度
    for (var i = 0; i < data_arr.length; i++)
    {
        //绘制饼图
        endAngle = endAngle + data_arr[i] * Math.PI * 2; //结束弧度
        ctx.fillStyle = color_arr[i];
        ctx.beginPath();
        ctx.moveTo(ox, oy); //移动到到圆心
        ctx.arc(ox, oy, radius, startAngle, endAngle, false);
        ctx.closePath();
        ctx.fill();
        startAngle = endAngle; //设置起始弧度

        //绘制比例图及文字
        ctx.fillStyle = color_arr[i];
        ctx.fillRect(posX, posY + 20 * i, width, height);
        ctx.moveTo(posX, posY + 20 * i);
        ctx.font = 'bold 12px 微软雅黑';    //斜体 30像素 微软雅黑字体
        ctx.fillStyle = color_arr[i]; //"#000000";
        var percent = text_arr[i] + "：" + 100 * data_arr[i] + "%";
        ctx.fillText(percent, textX, textY + 20 * i);
    }
}



$(function () {	
	if($("p").height()<200){
		$(".aLine").attr("style","line-height:28px;border-bottom:1px solid #ddd;");
	}else{
		$(".aLine").attr("style","line-height:32px;border-bottom:1px solid #ddd;");
	}
	getChuKeNum();
	getApprNum();
	getTable();
    getStuCount();
    getAudit();
    //日历日期
    var mydate = getServerDate();
    var year=mydate.getFullYear();
    var month=mydate.getMonth();
    var day=mydate.getDate();
    var weekDay=mydate.getDay();
    $(".yearNum").text(year);
    $(".monthNum").text(month+1);
    $(".day").text(day);
    switch(weekDay)
    {
    case 1:
    	 $(".weekNum").text("一");	
    break;
    case 2:
    	 $(".weekNum").text("二");	
    break;
    case 3:
    	 $(".weekNum").text("三");	
        break;
    case 4:
    	 $(".weekNum").text("四");
        break;
    case 5:
    	 $(".weekNum").text("五");
        break;
    case 6:
    	 $(".weekNum").text("六");
        break;
    default:
    	 $(".weekNum").text("日");
    break;
    } 
    $(".atten_img .panel").height($(".news .panel").height());
    if(parseInt($("#date").height())<=350){
		$(".day").attr("style","font-size:6em");
		$(".year").attr("style","top:29%");
	}
  //canvas宽高
    $("#canvas_circle").height($(".atten_img").height()*0.68);
    $("#canvas_circle").width($(".atten_img").width()*0.68);
    if($(".atten_img").height()<250){
    	$("#canvas_circle").height($(".atten_img").height()*0.68);
        $("#canvas_circle").width($(".atten_img").width()*0.60);
    }
    $(".atten_img .panel").height($(".news .panel").height());
});

function getChuKeNum(){
	var url=ctx+"/scoreweb/getGradeInit.action"; 
	var postData={
			trainStart:0	
	};
	doAjax(url,postData,2,function(res){	
		count= res.data.length;
		if(count==0){
			$("#chuKe").attr("style","color:#505F91;");
		}
		if(count>0){
			$("#_bell").removeClass("belldark");
		}
		$("#chuKe").text(count);
	});
}

//得到该秘书 下的  学生数目 和  学生详情
function getStuCount(){
	var url=ctx+"/teachweb/getSecreDetails.action"; 
	doAjax(url,"",2,function(res){
		if(res.stuCount==0){
			$("#stuCount").attr("style","color:#505F91;");
		}
		if(res.enrollStuCounts==0){
			$("#enrollStuCounts").attr("style","color:#505F91;");
		}
		if(res.gradStuCount==0){
			$("#gradStuCount").attr("style","color:#505F91;");
		}
		$("#stuCount").text(res.stuCount);
		$("#enrollStuCounts").text(res.enrollStuCounts);
		$("#gradStuCount").text(res.gradStuCount);
		$("#chuKe").text(res.chuKeCounts);
		//秘书界面学生详情里面的内容
		var color=["#008000", "#FFAA00", "#00AABB","#9AC0CD","#8F8F8F","#8DEEEE","#4A4A4A","#00EEEE","#008B45","#8B1C62"];
		var data_arr =[];
		var text_arr=[];
		var color_arr = [];
		for ( var i = 0; i < res.lists.length; i++) {
			data_arr.push(res.lists[i].completion_type);
			text_arr.push(res.lists[i].typeName);
			color_arr.push(color[i]);
		}
		 drawCircle("canvas_circle", data_arr, color_arr, text_arr);
	});
}



//得到消息
function getTable(){
	var url=ctx+"/teachweb/getTodo.action"; 
	doAjax(url,"",2,function(res){
		var table ="";
		for ( var i = 0; i < res.data.length; i++) {
			table+=" <tr><td>[<i>"+res.data[i].type_Name+"</i>]</td><td>"+res.data[i].title+"</td>" +
					"<td>"+res.data[i].sendTimeStr+"</td></tr>";
		}
		$("#toDoTable").html(table);
	});
}

////查询 离出科天数小于7天的学生
//function chuKe(){
//	if(count>0){
//	mypopdiv(1,"出科学生",'500px','300px','','','N',content);
//	}
//}


//得到请假审批数
function getApprNum(){
	var url=ctx+"/teachweb/getApprNum.action"; 
	doAjax(url,"",2,function(res){		
		$("#approvalNum").text(res.data);
		if(res.data==0){
			$("#approvalNum").attr("style","color:#505F91;");
		}
	});
};


function qingJia(){
	 clickToUrlTab("请假审批","请假审批",ctx+"/jsp/web/teach/reviewLeave.jsp?role=director");
};


function shuJu(){
	clickToUrlTab("数据审核","数据审核",ctx+"/jsp/web/teach/pending.jsp");
};



function getAll(){
	getChuKeNum();
	getApprNum();
	getTable();
	getStuCount();
}

//得到数据审核数
function getAudit(){
	var url=ctx+"/teachweb/getAudit.action"; 
	doAjax(url,"",2,function(res){	
		$("#dataAudit").text(res.data);
		if(res.data==0){
			$("#dataAudit").attr("style","color:#505F91;");
		}
	});
}

//出科
function chuKe(){
	clickToUrlTab("出科审核","出科审核",ctx+"/jsp/web/score/graduateCheckTeacher.jsp");
}

function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}
