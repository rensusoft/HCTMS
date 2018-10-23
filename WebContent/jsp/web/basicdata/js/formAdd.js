$(function(){
	$('#save').click(function(){
		saveForm();
    });
    $('#styleValue').change(function(){
        var _selected = $(this).find('option:selected').text();
        if(_selected == '分值类型'){ 
            $('.style1').show();
            $('.style3,.style2').hide();
        }
        if(_selected == '比例类型'){ 
            $('.style3').show();
            $('.style1,.style2').hide();
        }
        if(_selected == 'YES/NO类型'){ 
            $('.style2').show();
            $('.style1,.style3').hide();
        }
    });
    var spinner = $('.spinner').spinner();
    $('#styleBtn1').click(function() {
    	var num1 = spinner.eq(0).spinner( "value" );
        var num = spinner.eq(1).spinner( "value" );
        if(num1==0||num1==null||num1>100){
    		myMsg('分数格式输入错误，必须为正整数,且小于或等于100！', '40%', '45%', 3000);
    		return false;
        }
        if(num==0||num==null||num1>100){
    		myMsg('分数格式输入错误，必须为正整数,且小于或等于100！', '40%', '45%', 3000);
    		return false;
        }else{
	        $('.itemEdit ol').find('li').each(function(){
	            $(this).find('.styleBox').html('<input type="text" class="spinner_item" readonly="readonly">');
	            $('.spinner_item').spinner().spinner( "value", num );
	            $(this).find('input[name="btnType"]').val("NUM");
	        });
	        $('.lSize').hide();
	        $('.scoreList').toggleClass('width100');
	        $('.rSize').toggleClass('width100');
	        $('.pagecon').toggleClass('nobg');
	        countScore();
        }
    });
    $('#styleBtn2').click(function() {
        var textNo1 = $('#textNoY').val();
        var textNo2 = $('#textNoN').val();
        var scoreNo1 = $('#scoreNoY').val();
        var scoreNo2 = $('#scoreNoN').val();
        if(textNo1 !='' && textNo2 !='' && scoreNo1 != '' && scoreNo2 != ''){
            $('.itemEdit ol').find('li').each(function(index) {
                $(this).find('.styleBox').html('<input type="radio" title="' + textNo1 + '" value="' + scoreNo1 + '" name="boolStyle' + index + '"> ' + textNo1 + '[' + scoreNo1 + '] <input type="radio" title="' + textNo2 + '" value="' + scoreNo2 + '" name="boolStyle' + index +'"> ' + scoreNo2 + '[' + scoreNo2 + ']');
                $(this).find('input[name="btnType"]').val("YESNO");
            });
        }else {
        	myMsg('请先填完整信息！', '40%', '45%', 3000);
            return;
        }
        $('.lSize').hide();
        $('.scoreList').toggleClass('width100');
        $('.rSize').toggleClass('width100');
        $('.pagecon').toggleClass('nobg');
        countScore();
    });
    $('#styleBtn3').click(function() {
        var num = $('#numSel').find('option:selected').val();
        var _Html = '';
        var flag = true;
        for(var i=1;i<=num;i++){
        	if($('#textNo'+i).val()==''||$('#scoreNo'+i).val()==''){
        		flag = false;
        		break;
        	}else{
            	_Html += ' <input type="radio" name="increased" title="'+ $('#textNo'+i).val() + '" value="' +$('#scoreNo'+i).val()+ '"> '+ $('#textNo'+i).val() + '[' +$('#scoreNo'+i).val()+ ']'
        	}
       	}
        if(flag){
			$('.itemEdit ol').find('li').each(function(index){
                var increased = 'increased' + index;
                $(this).find('.styleBox').html(_Html).find('input').attr('name',increased);
                $(this).find('input[name="btnType"]').val("PROPORTION");
            });
        }else {
        	myMsg('请先填完整信息！', '40%', '45%', 3000);
            return;
        }
        $('.lSize').hide();
        $('.scoreList').toggleClass('width100');
        $('.rSize').toggleClass('width100');
        $('.pagecon').toggleClass('nobg');
        countScore();
    }); 

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
        var num = Html.find('.spinner_item').first().attr('aria-valuenow');
        var bool = Html.find('input').hasClass('spinner_item');
        if(bool){
            Html.find('.ui-spinner').remove();
            Html.find('.styleBox').html('<input type="text" class="spinner_item">');
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
        var num = Html.find('.spinner_item').attr('aria-valuenow');
        var bool = Html.find('input').hasClass('spinner_item');
        if(bool){
            Html.find('.ui-spinner').remove();
            Html.find('.styleBox').html('<input type="text" class="spinner_item">');
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
    $('.toggle').click(function() {
        $('.lSize').toggle();
        $('.scoreList').toggleClass('width100');
        $('.rSize').toggleClass('width100');
        $('.pagecon').toggleClass('nobg');
    });
    $('#reset').click(function() {
        $('form')[0].reset();
    });
    $('#form_type').change(function(){
        var _selected = $(this).find('option:selected').val();
        if(_selected == "1"){
            $("#markshee").css('display','block'); 
            $("#template").css('display','none');
            $('.toggle').show();
            $('.scoreList').show();
        }
        if(_selected == "2"){
            $("#markshee").css('display','none'); 
            $("#template").css('display','block');
            $('.toggle').hide();
            $('.scoreList').hide();
        }
    });
    $('#numSel').change(function(){
        var _selected = $(this).find('option:selected').val();
        for(var i=5;i>0;i--){
        	if(i<=_selected)
        		$('#numSel'+i).css('display','block');
        	else
        		$('#numSel'+i).css('display','none');
        }
    });
});

function saveForm(){
	//var iframeid = $('#tab_评分表库', window.parent.document).find("iframe").attr("id");
//	if(iframeid!="评分表库"){
//		iframeid = "评分表库";
//		$('#tab_评分表库', window.parent.document).find("iframe").attr("id", iframeid);
//	}
	var iframeid = $("#menuId", window.parent.document).val();
	$('#tab_'+iframeid, window.parent.document).find("iframe").attr("id", iframeid);
	var formType = $("#form_type").val();
	var formName = $("#form_name").val();
	var formState = $("#form_state").val();
	if(formName==""){
		myMsg("请填写表单名称！","30%","40%");
		return;
	}
	if(formType=="1"){
		if($("#sconum").val()==""){
			myMsg("请填写表单总分！","30%","40%");
			return;
		}
		if(eval($("#sconum").val())!=eval($("#score").text())){
			myMsg("评分项总分不等于设置总分"+eval($("#sconum").val())+"！","30%","40%");
			return;
		}
		var form={};
		form.type_id=formType;
		form.state="Y";
		form.availability=formState;
		form.name=formName.replace(/(^\s+)|(\s+$)/g, "");
		form.total_sconum=$("#sconum").val();
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
				url :  ctx+'/basicdataweb/addMarksheet.action',
				dataType: 'json',
				async: false,
				data:{
					data:JSON.stringify(form),
				},
				success:function(data) {
					if(data.success==true){
						myalert_success("新建评分表单成功！","30%","40%",function(index){
							$('#'+iframeid, window.parent.document)[0].contentWindow.formInit(); 
							parent.closeTab("tab_新建评分表");
						});
					}else{
						myalert_error("新建评分表单失败！","30%","40%",function(index){
							$('#'+iframeid, window.parent.document)[0].contentWindow.formInit(); 
							parent.closeTab("tab_新建评分表");
						});
					}
				}
			});
		}
	}else if(formType=="2"){
		var content = CKEDITOR.instances.content.getData();
		if(content==""){
			myMsg("请填写表单内容！","30%","40%");
			return;
		}
		$.ajax({
			type: 'POST',
			url :  ctx+'/basicdataweb/addHtmlTemplat.action',
			dataType: 'json',
			data:{
				type:formType,
				name:formName.replace(/(^\s+)|(\s+$)/g, ""),
				validity:formState,
				content:content,
				state:"Y"
			},
			success:function(data) {
				if(data.success==true){
					myalert_success("新建普通表单成功！","30%","40%",function(index){
						$('#'+iframeid, window.parent.document)[0].contentWindow.formInit();
						parent.closeTab("tab_新建评分表");
					});
				}else{
					myalert_error("新建普通表单失败！","30%","40%",function(index){
						$('#'+iframeid, window.parent.document)[0].contentWindow.formInit(); 
						parent.closeTab("tab_新建评分表");
					});
				}
			}
		});
	}
}

function changeId(){
	$('.itemEdit>ul>li').each(function(index){
		 $(this).attr("id", "submain"+(index+1));
		 $(this).find('ol>li').each(function(i){
			 $(this).attr("id", "submain"+(index+1)+"_subdetail"+(i+1));
		 });
	});
}

function countScore(){
	var score = 0;
	$('.itemEdit>ul>li>ol>li').each(function(index){
		var type = $(this).find('input[name="btnType"]').val();
		if(type=="NUM"){
			score += eval($(this).find('.spinner_item').spinner().spinner("value"));
		}else{
			var num = 0;
			$(this).find('input[type="radio"]').each(function(){
				if(num<eval(this.value)){
					num = eval(this.value);
				}
			});
			score += num;
		}
	});
	$('#score').text(score);
	checkScore();
}
function checkScore(){
	if(eval($("#sconum").val())<eval($("#score").text())){
		myMsg("评分项总分不能大于设置总分"+eval($("#sconum").val())+"！","30%","40%");
		return;
	}
}

function closeMyWindows(){
	layer.closeAll();
}
