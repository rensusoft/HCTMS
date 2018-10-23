$(function(){
	var id =getUrlParam("id");
	initMain(id);
	changeId();
    $('.sortable').on('click','p .glyphicon-cog',function(){
		var submainid = $(this).closest('li').attr("id");
		mypopdiv(2,"设置状态",pWidth*0.25+'px',(pHeight*0.5)+'px','25%','50%','N',ctx+'/jsp/web/basicdata/changeScore.jsp?submainid='+submainid);
    });
	$('.sortable').on('click','.sublist .glyphicon-cog',function(){
		var subdetailid = $(this).closest('li').attr("id");
		mypopdiv(2,"设置状态",pWidth*0.25+'px',(pHeight*0.5)+'px','25%','50%','N',ctx+'/jsp/web/basicdata/changeScore.jsp?subdetailid='+subdetailid);
    });
	$('.sortable').on('click','p .glyphicon-plus',function(){
        var Html = $(this).parents('.list-group').clone();
        Html.find('ol').children('li:not(:first-child)').remove();
        Html.find('input[type="text"]').val('');
        Html.find('textarea[name="textarea"]').val('');
        Html.find('span[name="sconum"]').text('0');
        var num = Html.find('.spinner_item').first().attr('aria-valuenow');
        var bool = Html.find('input').hasClass('spinner_item');
        if(bool){
            Html.find('.ui-spinner').remove();
            Html.find('.styleBox').html('<input type="text" class="spinner_item" readonly="readonly">');
        }
        $(this).parents('.list-group').after(Html);
        Html.find('.spinner_item').spinner().spinner( "value", num );
        $('.sortable').sortable({
            cursor: 'move',
            containment: 'parent'
        });
        changeId();
        countScore();
    });
    $('.sortable').on('click','.sublist .glyphicon-plus',function(){
    	var Html = $(this).parents('.sublist').clone();
        Html.find('input[type="text"]').val('');
        Html.find('textarea[name="textarea"]').val('');
        Html.find('span[name="sconum"]').text('0');
        var num = Html.find('.spinner_item').attr('aria-valuenow');
        var bool = Html.find('input').hasClass('spinner_item');
        if(bool){
            Html.find('.ui-spinner').remove();
            Html.find('.styleBox').html('<input type="text" class="spinner_item" readonly="readonly">');
        }
        $(this).parents('.sublist').after(Html);
        Html.find('.spinner_item').spinner().spinner( "value", num );
        $('.sortable2').sortable({
            cursor: 'move',
            containment: 'parent'
        });
        changeId();
        countScore();
    });

    $('.sortable').on('click','p .glyphicon-remove',function(){
        var num = $('.sortable').children().length;
        if(num>1){
            $(this).parents('.list-group').remove();
        }else {
            myMsg('只剩一条了，不能再删了！', '40%', '45%', 3000);
            return;
        }
        changeId();
        countScore();
    });
    $('.sortable').on('click','.sublist .glyphicon-remove',function(){
        var num = $('.list-group ol').children().length;
        if(num>1){
            $(this).parents('.sublist').remove();
        }else {
        	myMsg('只剩一条了，不能再删了！', '40%', '45%', 3000);
            return;
        }
        changeId();
        countScore();
    });
});

function initMain(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/getFormInfoById.action',
		dataType: 'json',
		async: false,
		data:{
			id:id
		},
		success:function(data) {
			data = data.data;
			$("#name").val(data.name);
			var content ='<p>评分表类型：<span>评分表单</span></p>'+
						'<p>撰写者： <span>'+data.user_name+'</span></p>'+
						'<p>状态：<span>'+data.form_state+'</span><input type="hidden" id="state" name="btnType" value="'+data.availability+'" /></p>'+
						'<p>总分：<span id="score">'+data.total_sconum+'</span></p>';
			id = data.related_id;
			$("#mmid").val(id);
			$("#main").html(content);
			initSub(id);
		}
	});
}

function initSub(id){
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/queryMarksheetSubList.action',
		dataType: 'json',
		async: false,
		data:{
			mmid:id,
			type:"0"
		},
		success:function(data) {
			data = data.rows;
			var content ="";
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					var str = initSubdetail(id,data[i].id);
					content+='<li id="submain1" class="list-group">'+
					'<p>'+
                        '<input type="text" class="form-control" value="'+data[i].title+'" style="width: 85%;" placeholder="编辑评分项">'+
                        '<span class="btnGroup">'+
                            '<a href="javascript:;" class="glyphicon glyphicon-plus"></a>'+
                            '<a href="javascript:;" class="glyphicon glyphicon-remove"></a>'+
                            '<a href="javascript:;" class="glyphicon glyphicon-cog"></a>'+
                        '</span>'+
                    '</p>'+
                    '<ol class="sortable2">' + 
                    	str +
                    '</ol></li>';
				}
				$(".sortable").html(content);
			}
		}
	});
}

function initSubdetail(id,msid){
	var content ="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/queryMarksheetSubList.action',
		dataType: 'json',
		async: false,
		data:{
			mmid:id,
			msid:msid,
			type:"1"
		},
		success:function(data) {
			data = data.rows;
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					var str = initDetail(data[i].id,data[i].item_type_code);
					content +='<li id="submain1_subdetail1" class="sublist">'+
					'<input class="form-control" style="width: 50%;" value="'+data[i].title+'" type="text" placeholder="编辑评分细则">'+
					str+
					'<span class="btnGroup">'+
	                    '<a href="javascript:;" class="glyphicon glyphicon-plus"></a>'+
	                    '<a href="javascript:;" class="glyphicon glyphicon-remove"></a>'+
	                    '<a href="javascript:;" class="glyphicon glyphicon-cog"></a>'+
	                '</span>'+
	                '<textarea name="textarea" class="form-control" placeholder="编辑评分要点" rows="1" style="width: 50%; margin-top: 2px;">'+data[i].text+'</textarea>'+
					'<input type="hidden" name="btnType" value="'+data[i].item_type_code+'" />'+
	                '</li>';
				}
			}
		}
	});
	return content;
}

function initDetail(id,type){
	var str="";
	$.ajax({
		type: 'POST',
		url :  ctx+'/basicdataweb/queryMarksheetDetailList.action',
		dataType: 'json',
		async: false,
		data:{
			msid:id
		},
		success:function(data) {
			data = data.rows;
			if(data.length>0){
				str += '<span name="sconum" style="width: 100px;display: inline-block;text-align: center;margin-left: 20px;">'+data[0].sco_num+'</span>';
				str += '<span class="styleBox margin_left">';
				for(var i=0;i<data.length;i++){
					if(type=="NUM"){
						str += '<input type="text" class="spinner_item" readonly="readonly">';
					}
					if(type=="YESNO"){
						str += '<input type="radio" name="boolStyle'+id+'" id="boolStyle'+id+i+'" title="'+data[i].text+'" value="'+data[i].sco_num+'">' + data[i].text + '[' + data[i].sco_num + ']'; 
					}
					if(type=="PROPORTION"){
						str += '<input type="radio" name="increased'+id+'" id="increased'+id+i+'" title="'+data[i].text+'" value="'+data[i].sco_num+'"> '+ data[i].text + '[' +data[i].sco_num+ ']';
					}
				}
				str +='</span>';
			}
		}
	});
	return str;
}

function changeId(){
	$('.itemEdit>ul>li').each(function(index){
		 $(this).attr("id", "submain"+(index+1));
		 $(this).find('ol>li').each(function(i){
			 $(this).attr("id", "submain"+(index+1)+"_subdetail"+(i+1));
			 if($(this).find('input[name="btnType"]').val()=="NUM"){
				 var num = $(this).find('span[name="sconum"]').text();
				 $(this).find(".spinner_item").spinner().spinner("value",num);
			 }
		 });
	});
}

function countScore(){
	var score = 0;
	$('.itemEdit>ul>li>ol>li').each(function(index){
		var type = $(this).find('input[name="btnType"]').val();
		var num = 0;
		if(type=="NUM"){
			num = eval($(this).find('.spinner_item').spinner().spinner("value"));
			score += num;
		}else{
			$(this).find('input[type="radio"]').each(function(){
				if(num<eval(this.value)){
					num = eval(this.value);
				}
			});
			score += num;
		}
		$(this).find('span[name="sconum"]').text(num);
	});
	$('#score').text(score);
}

function closeMyWindows(){
	layer.closeAll();
}

function submitData(){
	var formName = $("#name").val();
	if(formName==""){
		myMsg("请填写表单名称！","30%","40%");
		return;
	}
	var form={};
	form.related_id=$("#mmid").val();
	form.name=formName.replace(/(^\s+)|(\s+$)/g, "");
	form.total_sconum=$("#score").text();
	var sub=[];
	var result = true;
	$('.itemEdit>ul>li').each(function(index){
		var item={};
		item.id=index;
		item.type_code=0;
		item.sort_num=index+1;
		item.item_type_code=$("#btnType").val();
		item.state="Y";
		var title = $(this).find('input').val();
		if(title==""){
			myMsg("请先填完整信息！","30%","40%");
			result = false;
			return;
		}
		item.title=title;
		item.text="";
		sub.push(item);
		$(this).find('ol>li').each(function(i){
			var item={};
			item.type_code=1;
			item.sort_num=i+1;
			var type_code = $(this).find('input[name="btnType"]').val();
			item.item_type_code=type_code;
			item.state="Y";
			var title = $(this).find('input').val();
			var text = $(this).find('textarea').val();
			if(title==""){
				myMsg("请先填完整信息！","30%","40%");
				result = false;
				return;
			}
			item.title=title;
			item.text=text;
			item.ms_id=index;
			var details=[];
			if(type_code=="NUM"){
		        var detail={};
		        detail.sco_num=$(this).find('.spinner_item').spinner().spinner("value");
		        detail.state="Y";
				details.push(detail);
			}else{
				$(this).find('input[type="radio"]').each(function(){
					var detail={};
					detail.text=this.title;
					detail.sco_num=this.value;
					detail.state="Y";
	        		details.push(detail);
				});
			}
			item.details=details;
			sub.push(item);
		});
    });
	form.subs=sub;
	if(result){
		$.ajax({
			type: 'POST',
			url :  ctx+'/basicdataweb/updateMarksheet.action',
			dataType: 'json',
			async: false,
			data:{
				data:JSON.stringify(form),
			},
			success:function(data) {
				if(data.success==true){
					myalert_success("编辑评分表单成功！","30%","40%",function(index){
						parent.formInit();
						parent.closeMyWindows();
					});
				}else{
					myalert_error("编辑评分表单失败！","30%","40%",function(index){
						 parent.closeMyWindows();
					});
				}
			}
		});
	}
}