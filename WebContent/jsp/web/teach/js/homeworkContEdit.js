var senderStu = new Array();  
var leixing=0;
var id;
$(function(){
	 $("#contents").attr("style","height:"+(pHeight-300)+"px");
	 ue = UE.getEditor('contents',{scaleEnabled:true});	
	//跳转页面拿值
	 function GetRequest() { 
			var url = location.search; //获取url中"?"符后的字串 
			var theRequest = new Object(); 
			if (url.indexOf("?") != -1) { 
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
			} 
			} 
			return theRequest; 
			} 
		var Request = new Object(); 
		Request = GetRequest(); 
		id = Request['id']; 
			$.ajax({
				type:'post',
				url:ctx+"/teachweb/updateTask.action",
				data:{
					id:id
				},
				dataType:'json',
			    success:function(res){
			    	$("#title").val(res.data.title);
			    	$("#task_type_code").val(res.data.task_type_code);
			    	$("#title").val(res.data.title);
			    	$("#name").val(res.data.stuList);
			    	//判断ueditor 编辑器是否创建成功
			  		UE.getEditor('contents').addListener("ready", function () {
					    // editor准备好之后才可以使用
			  			UE.getEditor('contents').setContent(res.data.content);
					});
			    }
				
			});
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
	if(UE.getEditor('contents').getContent()=="" || $("#title").val() == ""){
		myalert_error("标题或消息内容不能为空！","20%","18%");
		return false;
	}
	if($("#title").val().length>20 && UE.getEditor('content').getContent()!=""){
		myalert_error("标题不能超过20个字！","20%","18%");
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
	if($("#title").val().length<21 &&UE.getEditor('contents').getContent()!=""&&$("#task_type_code").val()!=""){		
      var data={
    		  id:id,
    		  content:content,
			  title:$("#title").val(),
			  senderStu:JSON.stringify(senderStu),
			  task_type_code:$("#task_type_code").val(),
			  progress_state:"1",
      };
	  $.ajax({
		   type:'post',
		   url:ctx+"/teachweb/sendHomeworkEditToStu.action",
		   dataType:'json',
		   data:data,
		   success:function(res) {
	  	 		parent.closeMyWindows(res);
	  	 		parent.orgaInit();
	  	 	}
	  });
	
	}
}