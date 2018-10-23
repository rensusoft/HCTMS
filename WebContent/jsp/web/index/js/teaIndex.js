var count=0;
var content="<table style='width:100%;position: absolute;top: 0;' >";
$(function () { 
	getApprNum();
	getTable();
	 getStuCount();
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
});



//得到该教师下的  学生数目 和  学生详情
function getStuCount(){
	var url=ctx+"/teachweb/getStuAll.action"; 
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
		/*$("#stuCount").text(res.stuCount);
		$("#enrollStuCounts").text(res.enrollStuCounts);
		$("#gradStuCount").text(res.gradStuCount);*/
		$("#stuNum").text(parseInt(res.stuCount) + parseInt(res.gradStuCount));
		studentDetails="";    //学生详情里面的内容
		for ( var i = 0; i < res.trainPlans.length; i++) {			
			lastDay=0;
			progresValue=100;
			if(res.trainPlans[i].lastDay<=0){
				progresValue=100;
				lastDay="已出科";
				studentDetails+="<div><i style='color:#505F91;'>"+res.trainPlans[i].name+"</i><div class='progress_div'><p><progress value='"+res.trainPlans[i].completion_rate+"' max='100'></progress>" +
				"<b>大纲完成度&nbsp;&nbsp;<i>"+res.trainPlans[i].completion_rate+"%"+"</i></b> </p><p><progress value='100' max='100' class='dark'></progress>" +
				"<b>已&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科</b></p></div></div>";
			}else{
				var maxValue=0;
				if(res.trainPlans[i].lastDay>100){
					maxValue=1;
				}else{
					maxValue=100-res.trainPlans[i].lastDay;
				}
				progresValue=progresValue-res.trainPlans[i].lastDay;
				lastDay=res.trainPlans[i].lastDay;
				studentDetails+="<div><i style='color:#505F91;'>"+res.trainPlans[i].name+"</i><div class='progress_div'><p><progress value='"+res.trainPlans[i].completion_rate+"' max='100'></progress>" +
				"<b>大纲完成度&nbsp;&nbsp;<i>"+res.trainPlans[i].completion_rate+"%"+"</i></b> </p><p><progress value='"+maxValue+"' max='100' class='dark'></progress>" +
				"<b>轮转剩余天数&nbsp;&nbsp;<i>"+lastDay+"</i>天</b></p></div></div>";
			}
				
		
//			if(res.trainPlans[i].lastDay<=7){
//				count++;
//				content+="<tr style='border-bottom:1px solid #D6DEF0; background-color:#E7ECED;'><td><span style='color:#505f91;font-weight:bold;'>姓名:</span></td><td>"+res.trainPlans[i].name+"</td>" +
//						"<td><span style='color:#505f91;font-weight:bold;'>轮转剩余天数:</span></td><td>"+lastDay+"</td></tr>";
//			}
		}
		$("#studentDetails").html(studentDetails);
		$("#dataAudit").text(res.dataAudit);
		if(res.dataAudit>0){
			$("#dataAudit").attr("style","color:#E47D16;");
		}
//		content+="</table>";
//		if(count>0){
//			$("#_bell").removeClass("belldark");
//		}
		$("#chuKe").text(res.chuKeCount);
		if(res.chuKeCount>0){
			$("#chuKe").attr("style","color:#E47D16;");
			$("#_bell").removeClass("belldark");
		}
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


//查询 离出科天数小于7天的学生
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
		if(res.data>0){
			$("#approvalNum").attr("style","color:#E47D16;");
		}
	});
};


//function qingJia(){
//	 clickToUrlTab("学生请假数","请假审批",ctx+"/jsp/web/teach/viewStuLeave.jsp");
//};


function shuJu(){
	clickToUrlTab("数据审核","数据审核",ctx+"/jsp/web/teach/pending.jsp");
};



function getAll(){
	getApprNum();
	getTable();
	getStuCount();
}



//出科
function chuKe(){
	clickToUrlTab("出科审核","出科审核",ctx+"/jsp/web/score/graduateCheckTeacher.jsp");

}

function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}
