var trainStart=0;
var name="";
$(function(){
    //初始化界面
    pageInit(trainStart,name);
  
});



//加载初始化界面
function pageInit(trainStart,name){
	var url=ctx+"/scoreweb/getGradeInit.action ";
	var postData={
		name:name,
		trainStart:trainStart
	};
	doAjax(url,postData,2,function success(res){
		var table="";
		for ( var i = 0; i < res.data.length; i++) {
			if(res.data[i].train_status==53){  //轮转中
				table+="<div class='stuInfor'><a class='aBtn'>出科>><span style='display:none;' class='type'>"+1+"</span><span style='display:none;' class='_stuId'>"+res.data[i].stu_auth_id+"</span>" +
						"<span style='display:none;' class='_stuName'>"+res.data[i].name+"</span><span style='display:none;' class='_typeName'>"+res.data[i].typeName+"</span>" +
						"<span style='display:none;' class='_lastDay'>"+res.data[i].lastDay+"</span>" +
						"<span style='display:none;' class='completion_rate'>"+res.data[i].completion_rate+"</span>" +
						"</a><b class='green'><i>轮转中</i></b><div class='imgInfor'>" +
						"<span><img src='../score/images/userb.png' alt=''/></span><p><b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b>" +
						"<i>"+res.data[i].name+"</i><br/><b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b>" +
						"<i>"+res.data[i].typeName+"</i><br/><b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b>" +
						"<i>"+res.data[i].dept_receive_str+"</i><br/><b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b>" +
						"<i>"+res.data[i].lastDay+"天</i><br/><b>大纲完成度：</b>" +
						"<i>"+res.data[i].completion_rate+"%</i></p></div></div>";
			}else{//待出科
				table+="<div class='stuInfor'><a class='aBtn'>查看详情>><span style='display:none;' class='type'>"+2+"</span><span style='display:none;' class='_stuId'>"+res.data[i].stu_auth_id+"</span>" +
						"<span style='display:none;' class='_stuName'>"+res.data[i].name+"</span><span style='display:none;' class='_typeName'>"+res.data[i].typeName+"</span>" +
						"<span style='display:none;' class='_lastDay'>"+res.data[i].lastDay+"</span>" +
						"<span style='display:none;' class='completion_rate'>"+res.data[i].completion_rate+"</span>" 
						+"</a><b class='orange'><i>待出科</i></b><div class='imgInfor'>" +
						"<span><img src='../score/images/userb.png' alt=''/></span><p>" +
						"<b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b><i>"+res.data[i].name+"</i><br/>" +
						"<b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b><i>"+res.data[i].typeName+"</i><br/>" +
						"<b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b><i>"+res.data[i].dept_receive_str+"</i><br/>" +
						"<b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b><i>"+res.data[i].lastDay+"天</i><br/>" +
						"<b>大纲完成度：</b><i>"+res.data[i].completion_rate+"%</i></p></div></div>";
			}
		}
		
		$("#tabled").html(table);
		if($("section").width()<1400){
	        $(".stuInfor").attr("style","width:31.5%");
	    };
	    $(".stateList a").click(
	            function(){
	                $(this).addClass("clicked").siblings().removeClass("clicked");
	            }
	        );
	    $("#tabled .stuInfor").hover(
	            function(){
	                $(this).children("a.aBtn").fadeToggle("fast");
	            }
	     );
	        //点击某个div层展现出科或查看详情
	     $("#tabled  .stuInfor").click(function(){
	        	
	     });	
	     
	     $(".aBtn").click(function(){
	    	 var type=$(this).children("[class='type']").text();
	    	 if(type==1){
	    		 var stuId=$(this).children("[class='_stuId']").text();
	    		 var stuName=$(this).children("[class='_stuName']").text();
	    		 var typeName=$(this).children("[class='_typeName']").text();
	    		 var dept_receive_str=$(this).children("[class='dept_receive_str']").text();
	    		 var lastDay=$(this).children("[class='_lastDay']").text();
	    		 var completion_rate=$(this).children("[class='completion_rate']").text();
	    		 mypopdiv(2,"",'1000px','700px','','','N',encodeURI(ctx+'/jsp/web/score/graCheckInfor.jsp?stuId='+stuId+'&stuName='
	    				 +stuName+'&typeName='+typeName+'&dept_receive_str='+dept_receive_str+'&lastDay='+lastDay+'&completion_rate='+completion_rate));
	    	 }
	    	 if(type==2){
	    		 var stuId=$(this).children("[class='_stu_auth_id']").text();
	    		 mypopdiv(2,"",'1000px','700px','','','N',ctx+'/jsp/web/score/gradProgress.jsp?stuId='+stuId);
	    	 }
	     });
	});
}



//根据名字查询
	function findByName(){	
	name=$("#stuName").val();
	 pageInit(trainStart,name);
};

//根据轮转状态 查询
$(".aVlue").click(function(){
	alert(2);
	    	trainStart=$(this).children("[class='txt']").text();
	    	 pageInit(trainStart,name);
	    });