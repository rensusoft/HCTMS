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
		for ( var i = 0; i < res.OrgaLists.length; i++) {
			count=res.stuTypeList.length;
			_id1="a"+res.OrgaLists[0].orga_id;
			for ( var j = 0; j < res.stuTypeList.length; j++) {				
				for ( var n = 0; n < res.tfcLists.length; n++) {
					if(res.tfcLists[n].tfc_id==ftc_id&&res.tfcLists[n].dept_id==res.OrgaLists[i].orga_id&&res.stuTypeList[j].id==res.tfcLists[n].stu_type_id){
						count--;
					}
				}
				};
			text+="<div class='panel panel-default'><div class='panel-heading' style='background-color:#FCFCFC;'>"+
			" <b class='title' style='color:white;'>"+res.OrgaLists[i].orga_name+"</b><a class='unify'>统一绑定" +
			"<span style='display:none;' class='orga_id'>"+res.OrgaLists[i].orga_id+"</span></a>" +
			"<span>未绑定数：<b>"+count+"</b></span><a class='arr arrup'></a> </div>";
			text+="<div class='panel-body' style='display:none;background-color:#FCFCFC;' id='a"+res.OrgaLists[i].orga_id+"'><table class='stuContent'>";
			for ( var j = 0; j < res.stuTypeList.length; j++) {
				var biaoDan="";
				var bDId=0;
				var type_id=0;
				for ( var n = 0; n < res.tfcLists.length; n++) {
					if(res.tfcLists[n].tfc_id==ftc_id&&res.tfcLists[n].dept_id==res.OrgaLists[i].orga_id&&res.stuTypeList[j].id==res.tfcLists[n].stu_type_id){
						biaoDan=res.tfcLists[n].name;
						bDId=res.tfcLists[n].form_id;
						if(res.tfcLists[n].type_id!=null){
							type_id=res.tfcLists[n].type_id;
						}
						
					}
				}
					text+="<tr><td>学生类型：</td><td>"+res.stuTypeList[j].type_name+"</td><td>表单选择：</td>" +
					"<td><a id="+res.OrgaLists[i].orga_id+"a"+res.stuTypeList[j].id+" class='table'>"+biaoDan+"<span style='display:none;' class='bDId'>"+bDId+"</span><span style='display:none;' class='type_id'>"+type_id+"</span></a></td><td><a class='btn btn-info btnSearch searchBtn'>绑定" +
					"<span style='display:none;' class='orga_id'>"+res.OrgaLists[i].orga_id+"</span>" +
					"<span style='display:none;' class='_id'>"+res.stuTypeList[j].id+"</span></a></td></tr>";
			}
			text+="</table></div> </div>";
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
		            var orga_id=$(this).children("[class='orga_id']").text();
		            mypopdiv(2,title,'1200px','700px','','','N',
		        			ctx +'/jsp/web/basicdata/teachingFormat3.jsp?ftc_id='+ftc_id+"&orga_id="+orga_id);
		            
		        }	
		);
		$(".searchBtn").click(
		        function () {
		            var section = $(this).parents(".panel").children().children(".title").text();
		            var title = $(this).parents().children("td:nth-child(2)").text();
		            var stu_id=$(this).children("[class='_id']").text();
		            var orga_id=$(this).children("[class='orga_id']").text();
		            body = $(this).parent(".panel");
		            mypopdiv(2,"绑定[ " + section + " - " + title + "]",'1200px','550px','','','N',
		        			ctx +'/jsp/web/basicdata/teachingFormat3.jsp?ftc_id='+ftc_id+"&stu_id="+stu_id+"&orga_id="+orga_id);
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














function query() {
    if ($.trim($("#txt").val()).length == 0) {
        $("#neiRong .panel").show();
    } else {
        $("#neiRong .panel")
                .hide()
                .filter(":contains('" + $("#txt").val() + "')")
                .show();
    }
};

function add(name,orga_id,stu_id,res) {
	_id1="a"+orga_id;
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


