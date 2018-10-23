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
	    	 var fristarry = (res.data).slice(0,1);//截取第一个对象
	    	 var strLeave = '<table style="margin-top:5px">';
			 for(var k1 = 0; k1 < fristarry.length; k1++){
				  content = (fristarry[k1].content);
				 var stu_create_time_str = fristarry[k1].stu_create_time_str;
				 strLeave+=
					 '<tr>'+
					    '<td rowspan="2" style="vertical-align: top"><img src="'+ctx+'/jsp/web/teach/img/person.png" alt=""/></td>'+
				  		'<td><b style="margin-left:10px">学生姓名：</b><span>'+fristarry[k1].stu_name+'</span></td>'+
				  		'<td><b style="margin-left:90px">科室：</b><span>'+fristarry[k1].orga_name+'</span></td>'+
				  		'<td><b style="margin-left:90px">请假类型：</b><span>'+fristarry[k1].vacate_type_name+'</span></td>'+
				  		'</tr>'+
				  		'<tr>'+
				  		'<td><b style="margin-left:10px">请假开始时间：</b><span>'+fristarry[k1].start_time_str+'</span></td>'+
				  		'<td><b style="margin-left:90px">请假结束时间：</b><span>'+fristarry[k1].end_time_str+'</span></td>'+
				  		'<td colspan="2"><a style="margin-left:105px" href="javascript:void(0);" onclick="getReason()">请假原因</a></td>'+
				  		'</tr>';
			 }
			strLeave = strLeave+'</table><hr/>';
	    	var tar= strLeave+'<table class="score" style="width:95%">';
	    	var htmlArry = $(".proc_name");
	    	var newHtmlArry = htmlArry.slice(0,1);//截取第一个对象
	    	var lastHtmlArry = htmlArry.slice(1);
	    	newHtmlArry.each(function(){
	        	$(this).parent(".divv").next().text(stu_create_time_str);
	    	});
		    for(var i =0;i<res.data.length;i++){
		    	var end_proc_name = res.data[i].end_proc_name;
		    	if(res.data[i].end_proc_name == '学生申请'){
		    		end_proc_name = '申请驳回';
		    	}
		    	var create_time_str = res.data[i].create_time_str;
		    	var role_id = res.data[i].role_id;
		    	if(res.data[i].related_id != null){
		    		if((res.data[i].proc_result_str == '审批通过' && res.data[i].role_id == 30) || res.data[i].proc_result_str == '审批不通过'){
		    			tar+='<tr style="background: #FF9E00;">'+
				           ' <td rowspan="2" style="vertical-align: top"><img src="'+ctx+'/jsp/web/teach/img/person.png" alt=""/></td>'+
				            '<td>审核人：<i>'+res.data[i].user_name+'</i></td>';
				            if(res.data[i].end_proc_name == '学生申请'){
				            	tar+='<td>审核状态：<i>'+'申请驳回'+'</i></td>';
				            }else{
				            	tar+='<td>审核状态：<i>'+res.data[i].end_proc_name+'</i></td>';
				            }
				            tar+='<td>审核时间：<i>'+res.data[i].create_time_str+'</i></td>'+
				            '<td>审核结果：<i>'+res.data[i].proc_result_str+'</i></td>'+
				        '</tr>'+
				        '<tr style="background: #FF9E00;">'+
				            '<td colspan="3" style="vertical-align: middle"> 审批意见：'+res.data[i].proc_content+'</td>'+
				        '</tr>';
		    		}else{
		    			tar+='<tr>'+
				           ' <td rowspan="2" style="vertical-align: top"><img src="'+ctx+'/jsp/web/teach/img/person.png" alt=""/></td>'+
				            '<td>审核人：<i>'+res.data[i].user_name+'</i></td>';
			    			if(res.data[i].end_proc_name == '学生申请'){
				            	tar+='<td>审核状态：<i>'+'申请驳回'+'</i></td>';
				            }else{
				            	tar+='<td>审核状态：<i>'+res.data[i].end_proc_name+'</i></td>';
				            }
			    			tar+='<td>审核时间：<i>'+res.data[i].create_time_str+'</i></td>'+
				            '<td>审核结果：<i>'+res.data[i].proc_result_str+'</i></td>'+
				        '</tr>'+
				        '<tr>'+
				            '<td colspan="3" style="vertical-align: middle"> 审批意见：'+res.data[i].proc_content+'</td>'+
				        '</tr>';
		    		}
		    	}
		        if(i<res.data.length-1){
		        	tar+='<tr><td colspan="5"><hr/></td></tr>';	
		        }
		        lastHtmlArry.each(function(){
		        	if(end_proc_name != null){
		        		if(end_proc_name.trim() == '申请驳回'){
			        		$(this).parent(".divv").next().text(create_time_str.trim());
			        		return false;
			        	}else if($(this).text().trim() == end_proc_name.trim()){
			    			$(this).parent(".divv").next().text(create_time_str.trim());
			    		}
		        	}
		    	});
		  }          
		   tar+='</table>';
		  $("#leave_info").html(tar);
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
	    for(var i =0;i<res.data.processInfoList.length;i++){
	    	if(res.data.processInfoList[i].proc_num != -1){
	    		if(res.data.processInfoList[i].proc_num <= parseInt(res.data.vacate_status_str)){
	    			if(res.data.processInfoList[i].proc_num == parseInt(res.data.vacate_status_str)){
	    				if(parseInt(res.data.vacate_status_str) == 2){
	    					str+= '<td><div><b >'+(i)+'</b><i></i></div>'+'<div class="divv"><span class="proc_name">'+res.data.processInfoList[i].proc_name+'</span></div><span></span></td>';
	    				}else{
	    					str+= '<td><div><b >'+(i)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name">'+res.data.processInfoList[i].proc_name+'</span></div><span></span></td>';
	    				}
	    			}else{
	    				str+= '<td><div><b>'+(i)+'</b><i></i></div>'+'<div class="divv"><span class="proc_name">'+res.data.processInfoList[i].proc_name+'</span></div><span></span></td>';
	    			}
	    		}else{
	    			str+= '<td><div><b class="gray">'+(i)+'</b><i class="gray"></i></div>'+'<div class="divv"><span class="proc_name">'+res.data.processInfoList[i].proc_name+'</span></div><span style="min-height:14px;display:inline-block;"></span></td>';
	    		}
	    	}
	  }
	    if(parseInt(res.data.vacate_status_str) == 2){
	    	str+= '<td class="finish_td"><div><b>✔</b></div><span>完成</span></td></tr></table><hr/>';
	    }else if(parseInt(res.data.vacate_status_str) == -1){
	    	str+= '<td class="finish_td"><div><b class="gray">X</b></div><span>驳回</span></td></tr></table><hr/>';
	    }else{
	    	str+= '<td class="finish_td"><div><b class="gray">✔</b></div><span>完成</span></td></tr></table><hr/>';
	    }
	  $("#process_top").html(str);
	  addcss();
  });
}