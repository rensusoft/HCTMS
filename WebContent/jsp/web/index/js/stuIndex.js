

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

function tu(attendance,noAttendance,leave) {
	
    //绘制饼图
    //比例数据和颜色
    var data_arr = [leave, noAttendance,attendance];
    var color_arr = ["#008000", "#FFAA00", "#00AABB"];
    var text_arr = ["请假", "未考勤", "已考勤"];

    drawCircle("canvas_circle", data_arr, color_arr, text_arr);
}

$(function () {
	$(".atten_img .panel").width($(".top_content li:last-child .list_p").width()).attr("style","margin:");
	if(parseInt($("#date").height())<=350){
		$(".day").attr("style","font-size:6em");
		$(".year").attr("style","top:29%");
	}

 	$.post( 
			ctx+'/teachweb/getIndexPercent.action', 
			function(res) {
				tu(res.attendance,res.noAttendance,res.leave);
			}
		);
	
    var pHeight1=$(".list_p").children("p").height();
    //console.log(pHeight)
    $("progress").height(pHeight1);
    getTime();
    getOrge();
    getOutlineCom();
    getTable();
    //canvas宽高
    $("#canvas_circle").height($(".atten_img").height()*0.68);
    $("#canvas_circle").width($(".atten_img").width()*0.68);
    if($(".atten_img").height()<250){
    	$("#canvas_circle").height($(".atten_img").height()*0.68);
        $("#canvas_circle").width($(".atten_img").width()*0.58);
    }
    $(".atten_img .panel").height($(".news .panel").height());
    
  //点击事件
  $("#trainDeptDiv").click(function(){
	  clickToUrlTab("轮转计划","轮转计划",ctx+"/jsp/web/teach/rotatePlan.jsp");
  });
  
//  //判断是否做了入科宣教
//	var url=ctx+'/teachweb/getStuFormId.action';
//	var postData={};
//	doAjax(url,postData,2,function(res){
//		if(res.data!=null){
//		//判断是评分表单还是普通表单
//		var url = "";
//		if(res.data.type_id==1){
//			url = ctx+"/jsp/web/score/indeptEduc_M.jsp?id="+res.data.form_id;
//		}else if(res.data.type_id==2){
//			url = ctx+"/jsp/web/score/indeptEduc_HTML.jsp?id="+res.data.form_id;
//		}
//		mySelfpopdiv(2,"表单信息",'1200px',pHeight-70+'px','','','N',url);
//		}
//	});
});

function myCloseWindows(){
	 layer.closeAll();
}

//获得当前时间
function getTime(){
    //日历日期
    var mydate = getServerDate();
    var year=mydate.getFullYear();
    var month=mydate.getMonth();
    var day=mydate.getDate();
    var weekDay=mydate.getDay();
    $(".yearNum").text(year);
    $(".monthNum").text(month+1);
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
   
    $(".day").text(day);
}

//得到当前科室 和下一个科室  和轮转结束时间   带教老师 秘书的名字
function getOrge(){
	var url=ctx+"/teachweb/getOrgaNowAndNextTime.action"; 
	doAjax(url,"",2,function(res){
		$("#orgaIdNow").text(res.OrgaName);
//		if(res.NextOrgaName==null||res.NextOrgaName==""){
//			$("#orgaIdNext").text("已是最后一个科室");
//		}else{
//			$("#orgaIdNext").text("下一个："+res.NextOrgaName);
//		}
		
		if(res.days>0){
			$("#days").text(res.days);	
		}else{
			$("#noDays").html("已出科");		
		}
		
		$("#teacher").text(res.teacherName);
		$("#secretary").text(res.secretaryName);

	});
}

//得到大纲吻合度
function getOutlineCom(){
	var url=ctx+"/teachweb/getOutlineCom.action"; 
	doAjax(url,"",2,function(res){
		$("#outlineCom").html(res.data+"%");
		$("#progress").val(res.data);
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




function getOutLine(){
	 clickToUrlTab("轮转过程记录","轮转过程记录",ctx+"/jsp/web/teach/rotateProcess.jsp");
}

function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}


function mySelfpopdiv(type,title,width,length,verticalNum,horizontalNum,shadeCloseFlag,content,endFun){
	if(type==null||type==""){
		type = 1;
	}
	if(title==null||title==""){
		title = false;
	}
	if(length==null||length==""){
		length = "400px";
	}
	if(width==null||width==""){
		width = "250px";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = (pHeight-180)/2+"px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = (pWidth-300)/2+"px";
	}
	if(shadeCloseFlag==null||shadeCloseFlag==""){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="Y"){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="N"){
		shadeCloseFlag = false;
	}
	if(content==null||content==""){
		content = "无内容...";
	}
	var myLayer = layer.open({
//		id: id,
		type: type,
		skin: 'layui-layer-lan',
		shade:[0.5,'#fff'],
		title:title,
		closeBtn:0,
		area: [width,length],
		//offset: [verticalNum,horizontalNum],
	    shadeClose: shadeCloseFlag, //点击遮罩关闭
	    content: content,
	    end:endFun
	});
	return myLayer;
}

