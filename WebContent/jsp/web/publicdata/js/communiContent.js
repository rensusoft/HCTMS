var searchCount = '';
$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('reply_content',{scaleEnabled:true});
	$("#reply_content").attr("style","height:200px;width:100%");//使用pHeight设置textarea高度
	addCheckedNum();
	var id = '';
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
	if(id != "" && id != null){
		 $.ajax({
			  	type: 'POST',
				url : ctx + "/publicdataweb/selectForumsById.action",
				data : {
					    id:id
					},
				dataType: 'json',
			  	success:function(res) {
			  	  $("#forum_info_id").val(res.data.id);
				  $("#item_name").text(res.data.item_name);
				  $("#title").text(res.data.title);
				  $("#user_name").text(res.data.user_name);
				  if(res.data.role_id==10){
					  $("#image").attr('src',ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+res.data.stu_num+'.jpg'); 
					  
				  }else{
					  $("#image").attr('src',ctx+'/jsp/web/publicdata/img/personbig.png');
				  }
				  if(res.data.type_name != null){
					  $("#type_name").text(res.data.type_name);
				  }
				  $("#orga_name").text(res.data.orga_name);
				  $("#publish_time_str").text(res.data.publish_time_str);
				  $("#content").html(res.data.content);
			 }
		});
		}
	selectReplyInfo();
	
	//回复
	$(".fastre").click(
            function () {
            	var id = '';
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
            	var cite_id = this.id;
            	layer.open({
                  title: "参与/回复主题",
                  type: 2,
                  content: ctx + '/jsp/web/publicdata/replyContent.jsp?id=' + id + '&cite_id=' + cite_id,
                  area: ['1020px', (pHeight-50) + 'px']
              });
            }
        ); 
	
        //支持
        $(".replyadd").click(
                function () {
                	var forum_id = '';
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
                	forum_id = Request['id'];
                	var id = this.id.substring(3);
                	$.ajax({
        			  	type: 'POST',
        				url : ctx + "/publicdataweb/changeSupOrAgaNum.action",
        				data : {
        					    id:id,
        					    forum_id:forum_id,
        					    flag:1
        					},
        				dataType: 'json',
        			  	success:function(res) {
        			  	if(res.success == true){
        			  	  var supp_id = "support" + id;
          				  $("#" + supp_id).text(res.data.support_num);
        			  	}
        			 }
        		});
                }
            );
        
        //反对
        $(".replysubtract").click(
                function () {
                	var forum_id = '';
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
                	forum_id = Request['id'];
                	var id = this.id.substring(8);
                	$.ajax({
        			  	type: 'POST',
        				url : ctx + "/publicdataweb/changeSupOrAgaNum.action",
        				data : {
        					    id:id,
        					    forum_id:forum_id,
        					    flag:-1
        					},
        				dataType: 'json',
        			  	success:function(res) {
        			  	if(res.success == true){
        			  	  var against_id = "against" + id;
          				  $("#" + against_id).text(res.data.against_num);
        			  	}
        			 }
        		});
                }
            );
});


//关闭弹窗并跳转
function closeWindowsAndLocation(){
	layer.closeAll();
	window.location.href= ctx + "/jsp/web/publicdata/communicate.jsp";
}

//关闭弹窗
function closeMyWindows(){
//	selectReplyCount();
//	selectReplyInfo();
	layer.closeAll();
	document.location.reload();
}

//浏览量增加
function addCheckedNum(){
	var id = '';
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
	if(id != "" && id != null){
		var url=ctx+'/publicdataweb/addCheckedNum.action';
	    var postData = {id:id};
	    doAjax(url,postData,2,function(res){
	    	if(res.success == true){
	    		selectReplyCount();
	    	}
	    });
	}

}

//查询跟帖数目
function selectReplyCount(){
	var id = '';
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
	if(id != "" && id != null){
		var url=ctx+'/publicdataweb/selectReplyCount.action';
	    var postData = {id:id};
	    doAjax(url,postData,2,function(res){
	    	$('#checked_count').text(res.data.checked_num);
	    	$('#replay_count').text(res.data.reply_count);
	    });
	}
}

//查询回帖信息
function selectReplyInfo(){
	var id = '';
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
	if(id != "" && id != null){
		$.ajax({
			async: false,
		  	type: 'POST',
			url : ctx+'/publicdataweb/selectReplyInfo.action',
			data : {
				id:id
				},
			dataType: 'json',
		  	success:function(res) {
		    	var str = '';
		    	for(var i = 0; i < res.data.length; i++){
//		    		if((res.data)[i].content.indexOf("<img") != -1){
//		    			
//		    		}
		    		var type_name = (res.data)[i].type_name;
		    		if(type_name == null){
		    			type_name = "";
		    		}
		    		str += 
		    			'<table><tr>' +
		    				'<td class="pls" rowspan="2">' +
		    				'<div class="avatar"><img style="height:128px;width:128px" onerror=\'this.src="'+ctx+'/jsp/web/publicdata/img/personbig.png"\' src="'+ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+ (res.data)[i].stu_num+'.jpg'+'"/></div>' +
		    				'<h5>' + (res.data)[i].user_name + '<span>' + type_name + '</span></h5>' +
		    				'<p class="role"><span>' + (res.data)[i].orga_name + '</span></p>' +
		    				'</td>' +
		    				'<td class="plcc">' +
		    				'<div class="pi">' +
		    				'<strong><a class="diban"><em>' + (i + 2) + '</em><sup>#</sup></a></strong>' +
		    				'<div class="pti"><div class="authi"><em>发表于 ' + (res.data)[i].publish_time_str + '</em>' +
		    				'<span>支持<b id="support' + (res.data)[i].id + '">' + (res.data)[i].support_num + '</b> 反对<b id="against' + (res.data)[i].id + '">' + (res.data)[i].against_num + '</b></span>' +
		    				'</div></div>' +
		    				'</div>';
		    				if((res.data)[i].cite_state != null && (res.data)[i].cite_state == 1){
		    					var cite_content = (res.data)[i].cite_content.replace(/<img.*?\/>/,"");
		    		    		if(cite_content.length > 30){
		    		    			cite_content = cite_content.substring(0,30);
		    		    			cite_content += "...";
		    		    		}
//		    		    		alert(cite_content);
		    					str += 
		    						'<div class="quote">' +
		    						'<blockquote><font size="2"><a><font color="#999999">' + (res.data)[i].cite_user_name + ' 发表于 ' + (res.data)[i].cite_publish_time_str + '</font></a></font><br>' + 
		    						cite_content + 
		    						'</blockquote>' +
		    						'</div><div class="plccCont">' +
		    						(res.data)[i].content+'</div>';
		    				}else{
		    					str += '<div class="plccCont">' + (res.data)[i].content + '</div>';
		    				}
		    				str += '</td>' +
		    			'</tr>' +
		    			'<tr>' + 
		    				'<td class="po hin">' +
		    				'<div class="pob cl"><em>' +
		    				'<a class="fastre" id="' + (res.data)[i].id + '">回复 <span class="glyphicon glyphicon-comment"></span></a>' +
		    				'<a class="replyadd" id="add' + (res.data)[i].id + '">支持 <span class="glyphicon glyphicon-thumbs-up"></span></a>' +
		    				'<a class="replysubtract" id="subtract' + (res.data)[i].id + '">反对 <span class="glyphicon glyphicon-thumbs-down"></span></a>' +
		    				'</em></div>' +
		    				'</td>' +
		    			'</tr></table>';
		    				
		    	}	
//		    	alert(str);
		    	$("#reply_info").html(str);
		  		}
		  	});
	}
}

//本页面发帖弹出窗
function publishTheme(){
	layer.open({
        title: "发表新帖",
        type: 2,
        content: ctx + '/jsp/web/publicdata/writeNew.jsp?flagOfPlace=1',
        area: ['1020px', '440px']
    });
}

//回复帖子提交数据
function publishReply(){
	var reply_content = UE.getEditor('reply_content').getContent(); 
	if(reply_content == "" || reply_content == null){
		myalert_error("帖子回复内容不能为空!","30%","40%");
		return false;
	}
	var id = '';
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
	if(id != "" && id != null){
		var url=ctx+'/publicdataweb/publishReply.action';
	    var postData = {
	    		id:id,
	    		cite_id:null,
	    		reply_content:reply_content
	    };
	    doAjax(url,postData,2,function(res){
	    	if(res.success == true){
				 myalert_success(res.data,"30%","40%",function(){
//					 selectReplyCount();//点击确认不关闭点击确认不关闭点击确认不关闭点击确认不关闭点击确认不关闭
//					 selectReplyInfo();
//					 UE.getEditor('reply_content',{scaleEnabled:true}).setContent('');
					 document.location.reload();
				 });
			 }
	    });
	}
}

//跳转获得焦点
function skip(){
	$(".restore, .fastre1").attr("href","#reply_textarea");
	UE.getEditor('reply_content').focus();
}