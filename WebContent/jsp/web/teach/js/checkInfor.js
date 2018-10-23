var _id=-1;
var id="";
var name="";
var number=0;
$(function () {
	function GetRequest() { 
		var url = decodeURI(location.search); //获取url中"?"符后的字串 
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
	name= Request['name'];
	number= Request['number'];
	addTable();
	$(".judge,.btns").attr("style","display:none;");
});




function addTable(){
	var url=ctx+'/teachweb/getStuInputById.action';
	var postData={
			stuAuthId:id	
	};
	doAjax(url,postData,2,function(res){
		var tr="<tr><th>编写时间</th><th>标题</th></tr>";
		for ( var i = 0; i < res.data.length; i++) {
		   tr+="<tr onclick='openById("+res.data[i].id+")' style='cursor:pointer'><td>"+res.data[i].create_time_str+"</td>" +
		   		"<td title='"+res.data[i].order_name+"'>"+res.data[i].order_name+"</td></tr>";
		}		
		$("#contenTable").html(tr);
		var stu="<b>学生姓名：</b><span>"+name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<b>待审核记录：</b><span>"+number+"</span>";
		$("#stu").html(stu);
	});	
}

function openById(i){
	$(".judge,.btns").removeAttr("style","display:none;");
	_id=i;
	var url=ctx+'/teachweb/getActiveDataById.action';
	var postData={
			id:i
	};
	doAjax(url,postData,2,function(res){
		$("#addInfor").html(res.data);
		$("#text").val("");
	});
}



function auditThrough(){
	var url=ctx+'/teachweb/auditThrough.action';
	if(_id==-1){
		myMsg("请选择审核的信息！");
		return false;
	};
//	if($("#text").val()==""||$("#text").val()==null){
//		myMsg("请输入评价信息！");
//		return false;
//	};
	var postData={
			id:_id,
			content:$("#text").val()
	};
	doAjax(url,postData,2,function(res){
		myalert_success(res.data,"","",function(){
			number=number-1;
			addTable();
			$("#addInfor").html("");
			$("#text").val("");
			$(".judge,.btns").attr("style","display:none;");
			layer.closeAll();
		});
	});
}

function auditNotApproved(){
	var content = 
		organizeCheckBoxOfReason() + 
		'<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他原因：' +
		'<textarea name="exam_content" id="exam_content" style="width:90%;height:45%;resize: none;margin-left: 25px;margin-top:10px"></textarea>' +
		'<div style="width:100%;margin: 0 auto;margin-top:10px;text-align:center">' + 
			'<button class="layui-layer-btn0" onclick="submitData();" style="">确定</button>' +
		'</div>';
	mypopdiv(1,"审核不通过原因",'500px','400px','','','N',content);
}
//拼接审核不通过原因复选框
function organizeCheckBoxOfReason(){
	var str = '<br/>';
	$.ajax({
    	type: 'POST',
    	async:false,
    	url :ctx+'/scoreweb/organizeCheckBoxOfReason.action', 
    	data : {

    	},
    	dataType: 'json',
    	success:function(res) {
    		data = res.rows;
    		if(data.length > 0){
    			for(var i = 0; i < data.length; i++){
//    				if((i+1) % 2 == 0){
//    					str += '<input id="mycheckbox' + i + '" type="checkbox" class="checkbix" data-color="blue" data-text="' + data[i].content + '" value="' + data[i].content + '" >'+'<br/>';
//    				}else{
//    					str += '<input id="mycheckbox' + i + '" type="checkbox" class="checkbix" data-color="blue" data-text="' + data[i].content + '" value="' + data[i].content + '" >'+'&nbsp;&nbsp;&nbsp;';
//    				}
    				if((i+1) % 2 == 0){
    					str+="<input name='content' style='display:inline-blcok;margin-left:25px' type='checkbox' value='" + data[i].content + "' />" + data[i].content+"<br/>";
    				}else{
        				str+="<input name='content' style='display:inline-blcok;margin-left:25px' type='checkbox' value='" + data[i].content + "' />" + data[i].content+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    				}
    			}
//    			str += '<script>$(".checkbix").init();</script>';
    		}
    	}
    });
//	alert(str);
	return str;
}
//审核不通过数据提交
function submitData(){
	var content = "";
	$("[name='content']:checked").each(function(i){
		if(0==i){
			content = $(this).val();
		       }else{
		    	   content += ("；"+$(this).val());
		       }
	});
	var exam_content =  $("#exam_content").val();
	if((content == null || content == '') && (exam_content == null || exam_content == '')){
		myalert_error("审核不通过原因不能为空!","30%","40%");
		return false;
	}
	if(content != null && content != ''){
		if(exam_content != null && exam_content != ''){
			content += "；" + exam_content;
		}
	}else{
		if(exam_content != null && exam_content != ''){
			content = exam_content;
		}
	}
//	alert(content);
	var url=ctx+'/teachweb/auditNotApproved.action';
	if(_id==-1){
		myMsg("请选择审核的信息！");
		return false;
	};
	var postData={
			id:_id,
			content:content
	};
	doAjax(url,postData,2,function(res){
		myalert_success(res.data,"","",function(){
			number=number-1;
			addTable();
			$("#addInfor").html("");
			$("#text").val("");
			$(".judge,.btns").attr("style","display:none;");
			layer.closeAll();
		});
	});
}