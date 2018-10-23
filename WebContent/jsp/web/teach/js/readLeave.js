var content="";
$(function(){
	addprocess_top();
	var id=""; 
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
	if(id!="" && id!=null){
		var url=ctx+'/teachweb/selectLeaveProcessInfo.action';
	    var postData = {
	    		id:id
	    };
	    doAjax(url,postData,2,function(res){
	    	var tar="";
	    	 var fristarry = (res.data).slice(0,1);//截取第一个对象
			 for(var k1 = 0; k1 < fristarry.length; k1++){
				  content = (fristarry[k1].content);
				  var vacate_date_num = 0;
				  if(fristarry[k1].vacate_date_num == -10 || fristarry[k1].vacate_date_num == -20){
					  vacate_date_num = 0.5;
				  }else{
					  vacate_date_num = fristarry[k1].vacate_date_num;
				  }
				 var stu_create_time_str = fristarry[k1].stu_create_time_str;
				 tar = '<li>'+
				 '<b>'+stu_create_time_str.slice(0,10)+'</b><br/><b>'+stu_create_time_str.slice(11)+'</b>'+
				 '<span></span>'+
				 '<i style="position:absolute;top:2px;">'+
				 '<table style="margin-top:5px;width:100%">';
				 tar+=
					 '<tr>'+
					 '<td rowspan="2" style="vertical-align: top"><img style="height:48px;width:48px"  onerror=\'this.src="'+ctx+'/jsp/web/teach/img/person.png"\' src="'+ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+fristarry[k1].stu_num+'.jpg"  alt=""/></td>'+
				  		'<td><b >学生姓名：</b><span>'+fristarry[k1].stu_name+'</span></td>'+
				  		'<td><b >科室：</b><span>'+fristarry[k1].orga_name+'</span></td>'+
				  		'<td><b >请假类型：</b><span>'+fristarry[k1].vacate_type_name+'</span></td>'+
				  		'</tr>'+
				  		'<tr>'+
				  		'<td><b >请假开始时间：</b><span>'+fristarry[k1].start_time_str+'</span></td>'+
				  		'<td><b >请假结束时间：</b><span>'+fristarry[k1].end_time_str+'</span></td>'+
				  		'<td><b >请假天数：</b><span>'+vacate_date_num+'天</span></td>'+
				  		'<td colspan="2"><a href="javascript:void(0);" onclick="getReason()">请假原因</a></td>'+
				  		'</tr>';
			 }
			 tar = tar+'</table></i> </li>';
		    for(var i =0;i<res.data.length;i++){
		    	var proc_content = '';
		    	if(res.data[i].proc_content != null && res.data[i].proc_content != ''){
		    		proc_content = res.data[i].proc_content;
		    	}else{
		    		proc_content = '无';
		    	}
		    	if(res.data[i].proc_result != null){
		    		 tar+= '<li>'+
		                '<b>'+res.data[i].create_time_str.slice(0,10)+'</b><br/><b>'+res.data[i].create_time_str.slice(11)+'</b>'+
		                '<span></span>'+
		                '<i style="position:absolute;top:2px;">';
		                tar+='<table style="width:100%;">';
		    			tar+='<tr">'+
				           ' <td rowspan="2" style="vertical-align: top"><img src="'+ctx+'/jsp/web/teach/img/person.png" alt=""/></td>'+
				            '<td><b>审核人：</b><span>'+res.data[i].user_name+'</span></td>';
				            tar+='<td><b>审核状态：</b><span>'+res.data[i].end_proc_name+'</span></td>';
				            tar+='<td><b>审核时间：</b><span>'+res.data[i].create_time_str+'</span></td>'+
				            '<td><b>审核结果：</b><span>'+res.data[i].proc_result_str+'</span></td></tr>'+
				        '<tr><td colspan="3" style="vertical-align: middle"> <b>审批意见：</b><span>'+proc_content+'</span></td>'+
				        '</tr></table></i> </li>';
		    	}
		  }          
		    $("#firstDiv").html(tar);
		  	});
	}
	
});

function getReason(){
	var str = "<div style='padding:15px;height:400px;overflow:auto;'>" + content + "</div>";
	relesepup = mypopdiv(1,"请假原因",'500px',(pHeight-300)+'px','50px',(pWidth-1000)/2,'Y',str);
}

function addprocess_top(){
	var id=""; 
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
    var url=ctx+'/teachweb/pressLeaveSelect.action';
    var postData = {id:id};
    doAjax(url,postData,2,function(res){
	    var str=' <table><tr>';
	    var str2="";
	    for(var i =0;i<res.data.length;i++){
	    	if(res.data[i].proc_result == -1){
	    		 str2 ='<table><tr>';
	    		 for(var j =0;j<res.data.length;j++){
	    		if(j<res.data.length-1){
	  	    	  if(j==0){
	  	    		str2+= '<td><div><b class="gray">'+(j)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[j].end_proc_name+'</span></div></td>';
	  	    		}else{
	  	    			str2+= '<td><div><b class="gray">'+(j)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[j].end_proc_name+'</span></div></td>';
	  	    	  }
	      	}else{
	      		str2+= '<td><div><b class="gray">'+(j)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[j].end_proc_name+'</span></div></td>';
	      		str2+= '<td class="finish_td"><div><b class="gray">X</b></div>'+'<div class="divv"><span class="proc_name" style="display:inline-block;left:0">驳回</span></div></td></tr></table>';
	      	}
	    	}
	    	}else if(i<res.data.length-1){
	    		 if(i==0){
	  	    			str+= '<td><div><b >'+(i)+'</b><i ></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[i].end_proc_name+'</span></div></td>';
	  	    		}else{
	    	       if(res.data[i].proc_result == 1){
	    	    	   str+= '<td><div><b >'+(i)+'</b><i ></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[i].end_proc_name+'</span></div></td>';
	               }else{
	                   str+= '<td><div><b class="gray">'+(i)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[i].end_proc_name+'</span></div></td>';
	               }
	    	  }
    	}else if(i==res.data.length-1){
    		if(res.data[i].proc_result == 1){
    			str+= '<td><div><b >'+(i)+'</b><i ></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[i].end_proc_name+'</span></div></td>';
    			str+= '<td class="finish_td"><div><b >✔</b></div>'+'<div class="divv"><span class="proc_name" style="display:inline-block;left:0">完成</span></div></td></tr></table>';
    		}else{
    			str+= '<td><div><b class="gray">'+(i)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name" style="width:100px;display:inline-block;">'+res.data[i].end_proc_name+'</span></div></td>';
        		str+= '<td class="finish_td"><div><b class="gray">✔</b></div>'+'<div class="divv"><span class="proc_name" style="display:inline-block;left:0">完成</span></div></td></tr></table>';	
    		}
    	}
	    }
	    if(str2!=""){
	    	$("#process_top").html(str2);
	    }else{
	    	$("#process_top").html(str);
	    }
	  addcss();
  });
}