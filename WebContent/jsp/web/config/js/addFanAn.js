var id="";
$(function(){
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
//	var edit = new baidu.editor.ui.Editor(); 
//	edit.render('content');
	ue = UE.getEditor('content',{scaleEnabled:true});	
	


		    $(".disable1").click(
		        function(){
		        	var i=0;
		            var nu=$(this).parents(".parent").children("td:first-child").children("span").attr("value");
		            $("#list tr").each(function(){
		            	var data_type_id= $(this).children("td:first-child").children("span").attr("value");
		            	if(nu==data_type_id){
		            		i++;
		            	}
		            });
		            if(i>1){
		            	 myalert_error("存在子项，不能禁用!");
		            	 return;
		            }
		            	$(this).parents(".parent,.ct").addClass("opacity");
		            	var thisInput=$(this).parents(".parent,.ct").children("td:nth-child(3)").children("input");
		            	thisInput.attr("disabled","disabled");
		            	var thisInput2=$(this).parents(".parent,.ct").children("td:nth-child(2)").children("input");
		            	thisInput2.attr("disabled","disabled");
		            	$("tr[value="+nu+"]").addClass("opacity");
		    });
		    $(".enable").click(
		        function(){
		            $(this).parents(".parent,.ct").removeClass("opacity");
		            //$(this).parents(".parent").children().children("a.add").removeAttr('onclick');
		            var nu=$(this).parents(".parent").children("td:first-child").children("span").attr("value");
		            var thisInput=$(this).parents(".parent,.ct").children("td:nth-child(3)").children("input");
		            thisInput.removeAttr('disabled');
		            var thisInput2=$(this).parents(".parent,.ct").children("td:nth-child(2)").children("input");
		            thisInput2.removeAttr('disabled');
		            thisInput.val("");
		            thisInput.focus();
		            $("tr[value="+nu+"]").removeClass("opacity");
		    });
		    $(".add").click(
		        function(){
		            var text=$.trim($(this).siblings("input").val());
		            var na=$(this).parents(".parent").children("td:first-child").children("span").attr("value");
		            if(text==""){
		            	return false;
		            }
		            var str='<tr class="child"> <td><span value="' +na+ '">'+ text +'</span>：</td> <td></td> <td>要求数：<input class="" size="3" value="0"  /></td> <td> <a class="remove">删除</a> </td> </tr>';
		            $(this).parents(".parent").after(str);
		            var thisInput=$(this).parents(".parent,.ct").children("td:nth-child(2)").children("input");
		            thisInput.val("");
		            $(".remove").click(
		    		        function(){
		    		            $(this).parents(".child").remove();
		    		        }
		    		    );
		    });
		    $('#tit span').click(function() {
		        var i = $(this).index();//下标第一种写法
		        //var i = $('tit').index(this);//下标第二种写法
		        $(this).addClass('select').siblings().removeClass('select');
		        $('#con li').eq(i).show().siblings().hide();
		    });
	
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
	$("#id").val(id);
//	 $.ajax({
//		  	type: 'POST',
//			url : ctx + "/configweb/getReqContent.action",
//			data : {
//				    id:id
//				},
//			dataType: 'json',
//		  	success:function(data) {
//		  		alert(11111);
//			  $("#content").val(data);
//		 }
//		
//	});
	var data={
			 id:id
	};
	doAjax(ctx + "/configweb/getReqContent.action",data,2,function(res){
//		$("#content").html(res.data);
		if(res.data!=-1){
//		ue.setContent();
		 //判断ueditor 编辑器是否创建成功
		 ue.ready(function () {
		    	// editor准备好之后才可以使用
		    	ue.setContent(res.data);
		    });
//			$("#content").html(res.data);
		};
	});
	}
	//轮转大纲展示页面
    outlineExhibition();
});


/***
 * 提交数据
 */
function submitData(){
	if(UE.getEditor('content').getContent()==null||UE.getEditor('content').getContent()==""){
		myalert_error("轮转方案不能为空！","40%","40%");
		return;
	}
	//进行form表单提交
	 var url=ctx + "/configweb/updateReqContent.action";
	 var postData = {
				content: UE.getEditor('content').getContent(),
				 id:id
			};
	 doAjax(url,postData,2,function(res){
		 var names="";
		 var type_ids="";
		 var nums="";
		 var flag = true;
		 $("tr").not(".opacity").children("td:first-child").children("span").each(function(){
			 names+=","+$(this).text();
			 type_ids+=","+$(this).attr("value");
		 });
		 $("tr").not(".opacity").children("td:nth-child(3)").children("input").each(function(){
			 if(isNaN($(this).val())||$(this).val()==0||!((/^-?\d+$/).test($(this).val()))){
				   flag = false;
			       return false;
			  }
			 nums+=","+$(this).val();
		 });
		 if(flag){
			 var url=ctx + "/configweb/insTrainTeaOrder.action";
			 var postData = {
					 names: names,
					 type_ids: type_ids,
					 nums: nums,
					 id:id
			 };
			 doAjax(url,postData,2,function(res){
				 parent.closeMyWindows(res);		
			 });
		 }else{
			  myalert_error('轮转大纲的数值只允许输入正整数!'); 
		 }
	 });
}
//轮转大纲展示页面
function outlineExhibition(){
	 var url=ctx + "/configweb/outlineExhibition.action";
	 var postData = {
				 id:id
			};
	 doAjax(url,postData,2,function(res){
		 if(res.data.length>0){
			 $("#list tr").each(function(){
				 var data_type_id= $(this).children("td:first-child").children("span").attr("value");
				 var order_name= $(this).children("td:first-child").children("span").text();
				 $(this).addClass("opacity");
				 var nu=$(this).children("td:first-child").children("span").attr("value");
				 var thisInput=$(this).children("td:nth-child(3)").children("input");
				 thisInput.attr("disabled","disabled");
				 $("tr[value="+nu+"]").addClass("opacity");
			     for(var i=0 ; i<res.data.length;i++){
					 //这里的$(this)就是每一个tr
					 if(data_type_id==res.data[i].data_type_id&&order_name==res.data[i].order_name&&res.data[i].type_id==0){
						   $(this).removeClass("opacity");
				            var nu=$(this).children("td:first-child").children("span").attr("value");
				            var thisInput=$(this).children("td:nth-child(3)").children("input");
				            thisInput.removeAttr('disabled');
				            var thisInput2=$(this).children("td:nth-child(2)").children("input");
				            thisInput2.removeAttr('disabled');
				            $("tr[value="+nu+"]").removeClass("opacity");
						   $(this).children("td:nth-child(3)").children("input").val(res.data[i].order_num);
						 continue;
					 }else if(data_type_id==res.data[i].data_type_id&&res.data[i].type_id==2){
						 var str='<tr class="child"> <td><span value="' +data_type_id+ '">'+ res.data[i].order_name +'</span>：</td> <td></td> <td>要求数：<input class="" size="3" value="'+res.data[i].order_num+'"  /></td> <td> <a class="remove">删除</a> </td> </tr>';
						 $(this).after(str); 
						 $(".remove").click(function(){
						 $(this).parents(".child").remove();});
						 continue;
					  }else if(data_type_id==res.data[i].data_type_id&&res.data[i].type_id==1){
						  $(this).removeClass("opacity");
					  }
			         }
			});
		 }
	 });
}

