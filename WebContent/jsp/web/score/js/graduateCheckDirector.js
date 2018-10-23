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
			var lastDay = res.data[i].lastDay;
			if(lastDay < 0){
				lastDay = 0;
			}
				table+="<div class='stuInfor'><a class='aBtn'>出科>><span style='display:none;' class='type'>"+1+"</span><span style='display:none;' class='_stuId'>"+res.data[i].stu_auth_id+"</span>" +
						"<span style='display:none;' class='_stuName'>"+res.data[i].name+"</span><span style='display:none;' class='_typeName'>"+res.data[i].typeName+"</span>" +
						"<span style='display:none;' class='_lastDay'>"+lastDay+"</span>" +
						"<span style='display:none;' class='_train_dept_id'>"+res.data[i].train_dept_id+"</span>" +
						"<span style='display:none;' class='_s_stu_type_id'>"+res.data[i].stu_type_id+"</span>" +
						"<span style='display:none;' class='_stuTypeId'>"+res.data[i].stuTypeId+"</span>" +
						"<span style='display:none;' class='completion_rate'>"+res.data[i].completion_rate+"</span>" +
						"</a><b class='orange'><i>待出科</i></b><div class='imgInfor'>" +
						"<span><img style=\"height:128px;width:128px\" onerror='this.src=\""+ctx+"/jsp/web/score/images/userb.png\"' src='"+ctx+"/ueditor(附件文件夹千万不能删)/userImg/"+res.data[i].stu_num+".jpg'"+"/></span><p><b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;：</b>" +
						"<i>"+res.data[i].name+"</i><br/><b>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型&nbsp;：</b>" +
						"<i>"+res.data[i].typeName+"</i><br/><b>轮&nbsp;转&nbsp;周&nbsp;期&nbsp;：</b>" +
						"<i>"+res.data[i].dept_receive_str+"</i><br/><b>剩&nbsp;余&nbsp;天&nbsp;数&nbsp;：</b>" +
						"<i>"+lastDay+"天</i><br/><b>大纲完成度：</b>" +
						"<i>"+res.data[i].completion_rate+"%</i></p></div></div>";
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
	    		 var stuId=$(this).children("[class='_stuId']").text();
	    		 var stuName=$(this).children("[class='_stuName']").text();
	    		 var typeName=$(this).children("[class='_typeName']").text();
	    		 var dept_receive_str=$(this).children("[class='dept_receive_str']").text();
	    		 var lastDay=$(this).children("[class='_lastDay']").text();
	    		 var train_dept_id=$(this).children("[class='_train_dept_id']").text();
	    		 var s_stu_type_id=$(this).children("[class='_s_stu_type_id']").text();
	    		 var completion_rate=$(this).children("[class='completion_rate']").text();
	    		 var stuTypeId=$(this).children("[class='_stuTypeId']").text();
	    		 mypopdiv(2,"出科审核",'1200px',(pHeight-20)+"px",'','','N',encodeURI(ctx+'/jsp/web/score/evaluate.jsp?stuId='+stuId+'&train_dept_id='+train_dept_id+'&s_stu_type_id='+s_stu_type_id+'&stuName='
	    				 +stuName+'&stuTypeId='+stuTypeId+'&typeName='+typeName+'&dept_receive_str='+dept_receive_str+'&lastDay='+lastDay+'&completion_rate='+completion_rate));
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
	    	trainStart=0;
	    	 pageInit(trainStart,name);
	    });




//关闭弹出层
function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			layer.closeAll();
			pageInit(trainStart,name); //刷新
		});
	}else{
		myalert_error(res.data,'','');
	}
}



//打开表单   a 是表单id
function openForm(a){
	url = ctx+"/jsp/web/score/indeptEduc_M.jsp?id="+a;
	mypopdiv(2,"表单信息",'1200px',(pHeight-120)+'px','','','N',url);
}