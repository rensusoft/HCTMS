var count=0;
var _id1="";
var _id2="";
$(function () {
	
	pageInto();
});


function pageInto(_id2){
    //构造一个含有目标参数的正则表达式对象
    var reg = window.document.location.href;	
    var array=reg.split("="); 
    ftc_id=array[1];
	doAjax(ctx+"/basicdataweb/getOrgaListAndStuType.action","",2,function (res){
		var text="";
		for ( var i = 0; i < res.stuTypeList.length; i++) {
			count=res.OrgaLists.length*4;
			_id1="a"+res.stuTypeList[0].id;
			for ( var n = 0; n < res.listsTsr.length; n++) {		
					if(res.listsTsr[n].tfc_id==ftc_id&&res.stuTypeList[i].id==res.listsTsr[n].stu_type_id){
						if(n>0){
						  if(res.listsTsr[n].orga_id==res.listsTsr[n-1].orga_id&&res.listsTsr[n].role_id==res.listsTsr[n-1].role_id){
							  continue;
						  }	else{
							 count--;
						  }
						}else{
						count--;
						}
					}

				};
			text+="<div class='panel panel-default'><div class='panel-heading' style='background-color:#FCFCFC;'>"+
			" <b class='title' style='color:white;'>"+res.stuTypeList[i].type_name+"</b><a class='unify'>统一绑定" +
			"<span style='display:none;' class='stuTypeId'>"+res.stuTypeList[i].id+"</span></a>" +
			"<span>未绑定数：<b>"+count+"</b></span><a class='arr arrup'></a> </div>";
			text+="<div class='panel-body' style='display:none;background-color:#FCFCFC;' id='a"+res.stuTypeList[i].id+"'><table class='stuContent'>";
			for ( var j = 0; j < res.OrgaLists.length; j++) {
					var biaoDan0="";
					var bDId0=0;
					var biaoDan1="";
					var bDId1=0;
					var biaoDan2="";
					var bDId2=0;
					var biaoDan3="";
					var bDId3=0;
					var type_id=0;
					for ( var n = 0; n < res.listsTsr.length; n++) {
						type_id=res.listsTsr[n].type_id;
						if(res.listsTsr[n].tfc_id==ftc_id&&res.listsTsr[n].orga_id==res.OrgaLists[j].orga_id&&res.stuTypeList[i].id==res.listsTsr[n].stu_type_id){
							if(res.listsTsr[n].role_id=="10"){
								if(biaoDan0==""){
								biaoDan0+=res.listsTsr[n].name;
								bDId0+=res.listsTsr[n].form_id;
								}else{
									biaoDan0+=","+res.listsTsr[n].name;
									bDId0+=res.listsTsr[n].form_id;
								}
							}else if(res.listsTsr[n].role_id=="40"){
								if(biaoDan2==""){
								biaoDan2+=res.listsTsr[n].name;
								bDId2+=res.listsTsr[n].form_id;
								}else{
									biaoDan2+=","+res.listsTsr[n].name;
									bDId2+=res.listsTsr[n].form_id;
								}
							}else if(res.listsTsr[n].role_id=="50"){
								if(biaoDan3==""){
									biaoDan3+=res.listsTsr[n].name;
									bDId3+=res.listsTsr[n].form_id;
									}else{
										biaoDan3+=","+res.listsTsr[n].name;
										bDId3+=res.listsTsr[n].form_id;
									}
							}else{
								if(biaoDan1==""){
									biaoDan1+=res.listsTsr[n].name;
									bDId1+=res.listsTsr[n].form_id;
									}else{
										biaoDan1+=","+res.listsTsr[n].name;
										bDId1+=res.listsTsr[n].form_id;
									}
							}
							
						}
					}
					text+="<tr><td>"+ res.OrgaLists[j].orga_name+"</td></tr>" +
					"<tr><td><img src='"+ctx+"/jsp/web/teach/img/enter.png' alt=''/></td><td>角色类型：学生</td>" +
					"<td>表单选择：<a id="+res.stuTypeList[i].id+"a"+res.OrgaLists[j].orga_id+" class='table'>"+biaoDan0+"<span style='display:none;' class='bDId'>"+bDId0+"</span><span style='display:none;' class='type_id'>"+type_id+"</span></a></td><td><a class='btn btn-info btnSearch searchBtn'>绑定" +
					"<span style='display:none;' class='stuType_id'>"+res.stuTypeList[i].id+"</span>" +
					"<span style='display:none;' class='_roleId'>"+"10"+"</span>" +
					"<span style='display:none;' class='_orgaId'>"+res.OrgaLists[j].orga_id+"</span></a></td></tr>"+
					"<tr><td><img src='"+ctx+"/jsp/web/teach/img/enter.png' alt=''/></td><td>角色类型：带教老师/教学秘书</td>" +
					"<td>表单选择：<a id="+res.stuTypeList[i].id+"a"+res.OrgaLists[j].orga_id+" class='table'>"+biaoDan1+"<span style='display:none;' class='bDId'>"+bDId1+"</span><span style='display:none;' class='type_id'>"+type_id+"</span></a></td><td><a class='btn btn-info btnSearch searchBtn'>绑定" +
					"<span style='display:none;' class='stuType_id'>"+res.stuTypeList[i].id+"</span>" +
					"<span style='display:none;' class='_roleId'>"+"20;30"+"</span>" +
					"<span style='display:none;' class='_orgaId'>"+res.OrgaLists[j].orga_id+"</span></a></td></tr>"+
					"<tr><td><img src='"+ctx+"/jsp/web/teach/img/enter.png' alt=''/></td><td>角色类型：科主任</td>" +
					"<td>表单选择：<a id="+res.stuTypeList[i].id+"a"+res.OrgaLists[j].orga_id+" class='table'>"+biaoDan2+"<span style='display:none;' class='bDId'>"+bDId2+"</span><span style='display:none;' class='type_id'>"+type_id+"</span></a></td><td><a class='btn btn-info btnSearch searchBtn'>绑定" +
					"<span style='display:none;' class='stuType_id'>"+res.stuTypeList[i].id+"</span>" +
					"<span style='display:none;' class='_roleId'>"+"40"+"</span>" +
					"<span style='display:none;' class='_orgaId'>"+res.OrgaLists[j].orga_id+"</span></a></td></tr>"+
					"<tr><td><img src='"+ctx+"/jsp/web/teach/img/enter.png' alt=''/></td><td>角色类型：科教科</td>" +
					"<td>表单选择：<a id="+res.stuTypeList[i].id+"a"+res.OrgaLists[j].orga_id+" class='table'>"+biaoDan3+"<span style='display:none;' class='bDId'>"+bDId3+"</span><span style='display:none;' class='type_id'>"+type_id+"</span></a></td><td><a class='btn btn-info btnSearch searchBtn'>绑定" +
					"<span style='display:none;' class='stuType_id'>"+res.stuTypeList[i].id+"</span>" +
					"<span style='display:none;' class='_roleId'>"+"50"+"</span>" +
					"<span style='display:none;' class='_orgaId'>"+res.OrgaLists[j].orga_id+"</span></a></td></tr>";
			}
			text+="</table></div></div>";
	}
		$("#neiRong").html(text);  
		if(_id2!=null&&_id2!=""){
		$("#"+_id2+"").attr("style","display:block");
		}else{
			$("#"+_id1+"").attr("style","display:block");
		}
		
		$(".table").click(function(){
			formId=$(this).children("[class='bDId']").text();
			var typeId=$(this).children("[class='type_id']").text();
			if(typeId==1){
				relesepup = mypopdiv(2,"表单查看",'1020px',(pHeight-160)+'px',(pHeight-800)/2+'px',(pWidth-1000)/2+'px','Y',ctx+"/jsp/web/basicdata/scoreInfo.jsp?id="+formId);	
			}else if(typeId==2){
				relesepup = mypopdiv(2,"表单查看",'1020px',(pHeight-160)+'px',(pHeight-800)/2+'px',(pWidth-1000)/2+'px','Y',ctx+"/jsp/web/basicdata/htmlForm.jsp?id="+formId);
			}
		});
		
		$(".unify").click(
		        function () {
		        		 //取消事件冒泡 
		        		 var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
		        		 if (e && e.stopPropagation) { 
		        		  // this code is for Mozilla and Opera
		        		  e.stopPropagation(); 
		        		 } else if (window.event) { 
		        		  // this code is for IE 
		        		  window.event.cancelBubble = true; 
		        		 } ;
		        
		            	
		            var title = $(this).siblings().text();
		            var stuTypeId=$(this).children("[class='stuTypeId']").text();
		            mypopdiv(2,title,'600px','350px','','','N',
		            		ctx +'/jsp/web/basicdata/addGraForm.jsp?ftc_id='+ftc_id+"&stuTypeId="+stuTypeId);
		            
		        }	
		);
		$(".searchBtn").click(
		        function () {
		            var section = $(this).parents(".panel").children().children(".title").text();
		            var title = $(this).parents().children("td:nth-child(2)").text();
		            var roleId=$(this).children("[class='_roleId']").text();
		            var stuType_id=$(this).children("[class='stuType_id']").text();
		            var orga_id=$(this).children("[class='_orgaId']").text();
		            body = $(this).parent(".panel");
		            mypopdiv(2,"绑定[ " + section + " - " + title + "]",'600px','300px','','','N',
		            		ctx +'/jsp/web/basicdata/addGraForm.jsp?ftc_id='+ftc_id+"&roleId="+roleId+"&stuTypeId="+stuType_id+"&orga_id="+orga_id);
		        }
		);
		
		

		//点击下拉
		$(".panel-heading").click(   
		        function () {
//		            $(this).parent().next().attr("style","display:inline;");
		        	  body = $(this).next();
		              $(body).slideToggle();
		              $(this).children(".arr").toggleClass("arrdown");
		           
		        }
		);	
	});
	
}

function add(name,roleId,stuType_id,res) {
	_id1="a"+stuType_id;
	_id2=_id1;
	 pageInto(_id2);
	 if(res.success==true){
 		myalert_success(res.data,"","",function (){
 			 layer.closeAll();
 		});
 	}else{
 		myalert_success(res.data);
 	} 
}	



