$(document).ready(function(){
	qkbInit();
});
function qkbInit(){
	var setting = {
	    check:{
	        enable:true
	    },
	    data:    {
	    	simpleData:{
	        	enable:true
	    	}
	    },
	    callback:{
	        onClick:function (e,treeId,treeNode){
	        	var size = $('#sek').find('tr').length;
	        	var str = $("#sek").html();
    			if(size>0){
	        		var addStr = false;
		        	$('#sek').find('tr').each(function(index){
		        		if($(this).attr("id")==treeNode.id)
		        			addStr = true;
			   		});
	        		if(!addStr){
	        			str += '<tr id="'+treeNode.id+'">'+
				                '<td><span id="sp'+treeNode.id+'">'+(size+1)+'</span>.'+treeNode.name+'<input id="qkbid'+treeNode.id+'" type="hidden" value="'+treeNode.id+'" onKeyPress="return verifyDigital(event);"/></td>'+
				                '<td><input id="pro'+treeNode.id+'" type="number" class="form-control" style="display: inline-block" onKeyPress="return verifyDigital(event);"/>%</td>'+
				            '</tr>'
		                $('#treeDemo').find('a[title="'+treeNode.name+'"]').attr("style", "background-color:yellow");
	            		$("#sek").html(str);
	        		}else{
	        			var num=0;
	        			$('#sek').find('tr').each(function(index){
	        				num++;
	        				var trid = $(this).attr("id");
	        				if(trid==treeNode.id){
	        					$("#"+trid).remove();
	    	        			$('#treeDemo').find('a[title="'+treeNode.name+'"]').removeAttr("style");
	    	        			num--;
	        				}else{
	        					$("#sp"+trid).text(num);
	        				}
				   		});
	        		}
	        	}else{
	        		str = '<tr id="'+treeNode.id+'">'+
			                '<td><span id="sp'+treeNode.id+'">1</span>.'+treeNode.name+'<input id="qkbid'+treeNode.id+'" type="hidden" value="'+treeNode.id+'" onKeyPress="return verifyDigital(event);"/></td>'+
			                '<td><input id="pro'+treeNode.id+'" type="number" class="form-control" style="display: inline-block" min="0" max="100" onKeyPress="return verifyDigital(event);"/>%</td>'+
			            '</tr>';
	        		$('#treeDemo').find('a[title="'+treeNode.name+'"]').attr("style", "background-color:yellow");
	        		$("#sek").html(str);
	        	}
	        }
	    }
	};
	var zNodes ="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQeknowledgeList.action',
		dataType: 'json', 
		async:false, 
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
	   	      		var str ='';
	   	      		for(var i=0;i<data.length; i++){
	   	      			var open = false;
	   	      			if(data[i].pid==0){
	   	      				open = true;
	   	      			}
	      				str += '{"id":'+data[i].id+', "pId":'+data[i].pid+', "name":"'+data[i].name+'", "open":'+open+'},';
	   	      		}
		   	      	if(str!=""){
		   	       		zNodes = "["+str.substring(0,str.length-1)+"]";
		   	       	}
	   	      	}
			}else{
				myMsg("知识库为空！",'30%','30%');
				return;
			}
		}
	});
	$.fn.zTree.init($("#treeDemo"), setting, eval(zNodes));
}

function submitExer(){
	var ques_num = $('input:radio:checked').val();
	if(ques_num=="其他"){
		ques_num =$('#quesNum').val(); 
	}
	var seklist=[];
	$('#sek').find('tr').each(function(){
		 var id = $(this).attr("id");
		 if($("#qkbid"+id).val()!=""&&$("#pro"+id).val()!=""){
			 var item={};
			 item.qkb_id=$("#qkbid"+id).val();
			 item.proportion=$("#pro"+id).val();
			 seklist.push(item);
		 }
	 });
	 if(ques_num==""||seklist.length==0){
		 myMsg("知识点和题目总数不能为空！",'30%','30%');
		 return;
	 }
	 $.ajax({
		type: 'POST',
		url :  ctx+'/exerweb/addExerInfo.action',
		dataType: 'json',
		data:{
			ques_title:$("ques_title").val(),
			ques_num:ques_num,
			seklist:JSON.stringify(seklist)
		},
		success:function(data) {
   	      	if(data.success){
				myalert_success("新增成功！","30%","40%",function(index){
	   	      		parent.reloadGrid();
	   	      		parent.closeMyWindows();
				});
			}else{
				myalert_error("新增失败！","30%","40%",function(index){
	   	      		parent.closeMyWindows();
				});
			}
		}
	});
}