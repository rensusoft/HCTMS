$(function(){
	$(".last_td").width(30);
	var ulWidth = $(".rotatestep ul").width();
	var iWidth = (ulWidth - 30) /2 - 40;
	$(".rotatestep ul i").width(iWidth);
	var flag = true;//  只做一次刷新
	$('.rotatestep li').click(function () {
		if($(this).find("b").attr('class')!="gray"){
	        var i = $(this).index();//下标第一种写法
	        if(i == 1 && flag){
	        	$("#beforeIframe").attr("src",ctx+"/jsp/web/teach/layOut.jsp");
	        	flag = false;
	        }
	        $('.content_ul li').eq(i).show().siblings().hide();
		}
	});
	var bHeight = $("body").height();
	var liHeight=bHeight-180;
	$(".content_ul>li").height(liHeight);
	var left=ulWidth/2;
	$(".child3 img").css({'right':left});
	$(".left_btns").height(pHeight-105);
});
function removeGrayColor(trainPlanBefore){
	$("#planConfig").val(trainPlanBefore.tsc_id);
	$("#planConfig").text(trainPlanBefore.tsc_name);
	//
	$('.rotatestep li').eq(1).find("b").removeClass('gray');
	$('.rotatestep li').eq(0).find("i").removeClass('gray');
	$('.rotatestep li').eq(2).find("b").removeClass('gray');
	$('.rotatestep li').eq(1).find("i").removeClass('gray');
}
function addGrayColor(){
	$("#planConfig").val("");
	$("#planConfig").text("");
	//
	$('.rotatestep li').eq(1).find("b").addClass('gray');
	$('.rotatestep li').eq(0).find("i").addClass('gray');
	$('.rotatestep li').eq(2).find("b").addClass('gray');
	$('.rotatestep li').eq(1).find("i").addClass('gray');
	$('.rotatestep li').eq(3).find("b").addClass('gray');
	$('.rotatestep li').eq(2).find("i").addClass('gray');
	$('.rotatestep li').eq(4).find("b").addClass('gray');
}
function next(i){
//	if(i==1){
//		$("#planIframe").attr("src",ctx+"/jsp/web/teach/planChoose.jsp");
		if($("#stuCount").val()>0){
			$("#planIframe").attr("src",ctx+"/jsp/web/teach/planChoose.jsp");
		}else{
			myMsg('请导入学生信息', '40%', '45%', 3000);
			return;
		}
//	}
	if(i==1){
		if($("#stuCount").val()>0){
			$("#beforeIframe").attr("src",ctx+"/jsp/web/teach/layOut.jsp");
		}else{
			myMsg('请导入学生信息', '40%', '45%', 3000);
			return;
		}
	}
	if(i==2){
		if(!beforeIframe.window.checkPlanBefore()){
			return;
		}
	}
	$('.rotatestep li').eq(i).find("b").removeClass('gray');
	$('.rotatestep li').eq(i-1).find("i").removeClass('gray');
	$('.content_ul li').eq(i).show().siblings().hide();
}

function popdiv(type,title,state,content){
	mypopdiv(type,title,'1000px',(pHeight-100)+'px','50px',(pWidth-1000)/2+'px',state,content);
}
//微调
function resetTrainPlanBefore(stu_auth_id,stu_name){
	mypopdiv(2,"微调 [" + stu_name + "]",'1100px',(pHeight-120)+'px','50px',(pWidth-1000)/2,'N',ctx + '/jsp/web/teach/sharpTuning.jsp?stu_auth_id='+ stu_auth_id,function(){
		beforeIframe.window.reloadGrid(stu_auth_id);
	});
}

function stuAdd(stu_class){
	mypopdiv(2,"添加用户","1000px",(pHeight-100)+'px',"","","N",
			ctx + '/userauthweb/addStudent.action?stu_class='+stu_class);	
}

function reload(){
	stuIframe.window.reloadGrid();
	layer.closeAll();
}
