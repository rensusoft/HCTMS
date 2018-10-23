var senderStu = new Array();  
var leixing=0;
$(function(){
	 $("#contents").attr("style","height:"+(pHeight-300)+"px");
	 ue = UE.getEditor('contents',{scaleEnabled:true});	
});

function addName(res){
	layer.closeAll();
	senderStu=res;
	var str="";
	
	for ( var i = 0; i < res.length; i++) {
		if(i==res.length-1){
		str+=res[i].value;
		}else{
			if(res[0].value==""){
				res.splice(0,1);
				str+=res[i].value+",";
			} else{
				str+=res[i].value+",";
			}
		
		};
	}
	$("#name").val(str);
}



function add() {
	var values="-1";  //用来保存  已经选择好的复选框的value
	if(senderStu!=""&&senderStu!=null){
		values="";
		for ( var i = 0; i < senderStu.length; i++) {
			values+=senderStu[i].key+",";
		}	
	}
	mypopdiv(2,"接收人选择",'600px',(pHeight-160)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/teach/peopleChoose.jsp?values="+encodeURI(values));
} 

function sendToStu() {
	if($("#title").val() == ""){
		myalert_error("标题不能为空！","20%","18%");
		return false;
	}
	if($("#title").val().length>20 && UE.getEditor('content').getContent()!=""){
		myalert_error("标题不能超过20个字！","20%","18%");
		return false;
	}
	if(checkStr($("#title").val(),2)==false){
		myMsg("标题不能含有特殊字符！",'50%','50%');
		return false;
	}
	if($("#task_type_code").val()==""){
		myalert_error("请选择作业类型！","20%","18%");
		return false;
		}
	if($("#name").val()==""){
		myalert_error("请选择接收人！","20%","18%");
		return false;
	}
    var content= UE.getEditor('contents').getContent();
    if(UE.getEditor('contents').getContent()==""){
		myalert_error("作业内容不能为空！","20%","18%");
		return false;
	}
    if($("#edui1_wordcount").text()=="字数超出最大允许值，服务器可能拒绝保存！"){
		myalert_error("输入内容超出10000，无法保存!","30%","40%");
		return false;
	}

	if($("#title").val().length<21 &&UE.getEditor('contents').getContent()!=""&&$("#task_type_code").val()!=""){		
      var data={
    		  content:content,
			  title:$("#title").val(),
			  senderStu:JSON.stringify(senderStu),
			  task_type_code:$("#task_type_code").val(),
			  progress_state:"1",
			  state:"Y"
      };
	  $.ajax({
		   type:'post',
		   url:ctx+"/teachweb/sendHomeworkToStu.action",
		   dataType:'json',
		   data:data,
		   success:function(res) {
	  	 		parent.closeMyWindows(res);
	  	 		parent.orgaInit();
	  	 	}
	  });
	
	}
}