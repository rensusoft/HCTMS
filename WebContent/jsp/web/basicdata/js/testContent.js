$(function(){
	qkbInit();
	init();
	var qid = getUrlParam("id");
	if(qid!=""&&qid!=null){
		$("#qid").val(qid);
		quesContent(qid);
		$("#quesType").attr("disabled",true);
		optionContent(qid);
		answerContent(qid);
	}else{
		$("#quesType").attr("disabled",false);
	}
    var choose=["A","B","C","D","E","F","G"];
    $(".add").click(
        function(){
            var pNum=$("#tb").children().children().length;
            console.log(pNum);
            var type="radio";
            if($("#quesType").val()==2)
            	 type="checkbox";
            var str='<tr>' +
                '<td>' +
                '<input type="'+type+'" name="radOption" value="' +choose[pNum]+'" /><span>' +choose[pNum]+
                '</span><input id="optid'+choose[pNum]+'" type="hidden" value="" /></td>' +
                '<td>' +
                '<input type="text" class="answerInput" value="" id="optcon'+choose[pNum]+'" />' +
                '</td>' +
                '<td>' +
                ' <a class="removeBtn arrupw"></a>' +
                '</td>' +
                '<td>' +
                ' <a class="removeBtn arrdownw"></a>' +
                '</td>' +
                '</tr>';
            $("#tb").children().append(str);
            arrup();
            arrdown();
        }
    );
    $(".remove").click(
        function(){
            $("#tb").children().children().last().remove();
        }
    );
    arrup();
    arrdown();
})
function arrup(){
    //上移
	$(".arrupw").unbind('click'); 
    $(".arrupw").bind("click",function(){
        var $this  = $(this);
        var curTr = $this.parents("tr");
        var curTrTd=$this.parents("tr").children("td:nth-child(2)").children("input[type='text']").val();
        var changeTd;
        var nextTrTd=$this.parents("tr").prev().children("td:nth-child(2)").children("input[type='text']").val();
        var prevTr = $this.parents("tr").prev();
        if(prevTr.length == 0){
            return;
        }else{
            $this.parents("tr").children("td:nth-child(2)").children("input[type='text']").val(nextTrTd);
            changeTd=curTrTd;
            $this.parents("tr").prev().children("td:nth-child(2)").children("input[type='text']").val(changeTd);
//            prevTr.before(curTr);
        }
    });
}
function arrdown(){
    //下移
	$(".arrdownw").unbind('click'); 
    $(".arrdownw").bind("click",function(){
        var $this  = $(this);
        var curTr = $this.parents("tr");
        var curTrTd=$this.parents("tr").children("td:nth-child(2)").children("input[type='text']").val();
        var changeTd;
        var nextTrTd=$this.parents("tr").next().children("td:nth-child(2)").children("input[type='text']").val();
        var nextTr = $this.parents("tr").next();
        if(nextTr.length == 0){
            return;
        }else{
            $this.parents("tr").children("td:nth-child(2)").children("input[type='text']").val(nextTrTd);
            changeTd=curTrTd;
            $this.parents("tr").next().children("td:nth-child(2)").children("input[type='text']").val(changeTd);
        }
    });
}
////////////////////////
function qkbInit(){
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQeknowledgeList.action',
		dataType: 'json', 
		async:false, 
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
	   	      		var str = "";
		      		for(var i=0;i<data.length; i++){
		      			str += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
		      		}
		      		$("#knowledge").html(str);
	   	      	}
			}else{
				myMsg("知识库为空！",'30%','30%');
				return;
			}
		}
	});
}
function init(){
	$.ajax({
		type: 'POST',
		url :  ctx+'/quesweb/getQuesTypeList.action',
		dataType: 'json',
		success:function(data) {
			if(data.success){
				data = eval(data.data);
	   	      	if(data != null && data.length>0){
	   	      		var str = "<option value=''>全部</option>";
	   	      		for(var i=0;i<data.length; i++){
	   	      			str += "<option value='"+data[i].id+"'>"+data[i].type_name+"</option>";
	   	      		}
	   	      		$("#quesType").html(str);
	   	      	}
			}else{
				myMsg("题型为空！",'30%','30%');
				return;
			}
		}
	});
}
function quesContent(id){
	if(id!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/quesweb/getQuesInfoById.action',
			dataType: 'json',
			async:false, 
			data:{
				id:id
			},
			success:function(data) {
	   	      	if(data.data != null){
	   	      		data = data.data;
	   	      		$("#knowledge").val(data.qkb_id);
	   	      		$("#quesType").val(data.type_id);
	   	      		$("#require").val(data.teaching_require);
	   	      		$("#sco_num").val(data.sco_num);
	   	      		$("#difficulty_num").val(data.difficulty_num);
	   	      		$("#different_num").val(data.different_num);
	   	      		$("#content").val(data.ques_content);
	   	      	}
			}
		});
	}
}
function optionContent(id){
	if(id!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/quesweb/getOptionInfoByQqid.action',
			dataType: 'json',
			async:false, 
			data:{
				qid:id
			},
			success:function(data) {
				if($("#quesType").val()==1||$("#quesType").val()==2)
					$("#optionList").removeAttr("style").attr("style", "display: block;");
				var type = "radio";
				if($("#quesType").val()==2)
					type = "checkbox";
   	      		var str = "<tr>"+
				                "<td>"+
				                "<input type='"+type+"' name='radOption' value='A' /><span>A</span><input id='optidA' type='hidden' value='' />"+
				            "</td>"+
				            "<td>"+
				                "<input type='text' class='answerInput' value='' id='optconA' />"+
				            "</td>"+
				            "<td>"+
				                "<a class='removeBtn arrupw'></a>"+
				            "</td>"+
				            "<td>"+
				                "<a class='removeBtn arrdownw'></a>"+
				            "</td>"+
				        "</tr>";
				if(data.success){
					data = eval(data.data);
		   	      	if(data != null && data.length>0){
		   	      		str = "";
		   	      		for(var i=0;i<data.length; i++){
		   	      			str += "<tr>"+
		                        "<td>"+
		                            "<input type='"+type+"' name='radOption' value='"+data[i].option_flag+"' /><span>"+data[i].option_flag+"</span><input id='optid"+data[i].option_flag+"' type='hidden' value='"+data[i].id+"' />"+
		                        "</td>"+
		                        "<td>"+
		                            "<input type='text' class='answerInput' id='optcon"+data[i].option_flag+"' value='"+data[i].option_content+"'/>"+
		                        "</td>"+
		                        "<td>"+
		                            "<a class='removeBtn arrupw'></a>"+
		                        "</td>"+
		                        "<td>"+
		                            "<a class='removeBtn arrdownw'></a>"+
		                        "</td>"+
		                    "</tr>";
		   	      		}
		   	      	}
				}
				$("#tb").html(str);
			}
		});
	}
}
function answerContent(id){
	if(id!=""){
		$.ajax({
			type: 'POST',
			url :  ctx+'/quesweb/getAnswerInfoByQqid.action',
			dataType: 'json',
			data:{
				qid:id
			},
			success:function(data) {
	   	      	if(data.data != null){
	   	      		data = data.data;
	   	      		$("#answer").val(data.answer);
	   	      		$("#aid").val(data.id);
	   	      	}
			}
		});
	}
}
function optionChange(){
	if($("#quesType").val()==1||$("#quesType").val()==2){
		$("#optionList").removeAttr("style").attr("style", "display: block;");
		var type="radio";
		if($("#quesType").val()==2)
			type = "checkbox";
		var str = "<tr>"+
	            "<td>"+
	            	"<input type='"+type+"' name='radOption' value='A' /><span>A</span><input id='optidA' type='hidden' value='' />"+
	            "</td>"+
	            "<td>"+
	                "<input type='text' class='answerInput' id='optconA' value=''/>"+
	            "</td>"+
	            "<td>"+
	                "<a class='removeBtn arrupw'></a>"+
	            "</td>"+
	            "<td>"+
	                "<a class='removeBtn arrdownw'></a>"+
	            "</td>"+
	        "</tr>";
		$("#tb").html(str);
	}else{
		$("#optionList").removeAttr("style").attr("style", "display: none;");
	}
}
function submitContent(){
	var url = "";
	if($("#qid").val()==""){
		url = "/quesweb/addQuesInfo.action";
	}else{
		url = "/quesweb/modifyQuesInfo.action";
	}
	if(url!=""){
		var optList=[];
		var type = "radio";
		if($("#quesType").val()==2)
			type = "checkbox";
		 $('#tb').find('input[type="'+type+'"]').each(function(){
			 var item={};
			 item.id=$("#optid"+this.value).val();
			 item.qq_id=$("#qid").val();
			 item.option_flag=this.value;
			 item.option_content=$("#optcon"+this.value).val();
			 item.state="Y";
			 optList.push(item);
		});
		 if($("#content").val()==""||$("#quesType").val()==""
			 ||$("#knowledge").val()==""||$("#require").val()==""
			 ||$("#sco_num").val()==""||$("#difficulty_num").val()==""
			 ||$("#different_num").val()==""){
			 myMsg("题目详情不能为空！",'30%','30%');
			 return;
		 }
		$.ajax({
			type: 'POST',
			url :  ctx + url,
			dataType: 'json',
			data:{
				qid:$("#qid").val(),
				type_id:$("#quesType").val(),
				qkb_id:$("#knowledge").val(),
				sco_num:$("#sco_num").val(),
				difficulty_num:$("#difficulty_num").val(),
				different_num:$("#different_num").val(),
				content:$("#content").val(),
				require:$("#require").val(),
				aid:$("#aid").val(),
				answer:$("#answer").val(),
				optList:JSON.stringify(optList)
			},
			success:function(data) {
	   	      	if(data.success){
					myalert_success("编辑成功！","30%","40%",function(index){
		   	      		parent.reloadGrid();
		   	      		parent.closeMyWindows();
					});
				}else{
					myalert_error("编辑失败！","30%","40%",function(index){
					});
				}
			}
		});
	}
}