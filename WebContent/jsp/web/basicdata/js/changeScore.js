var parentid="";
var parenttype = 0;
$(function(){
	var subdetailid = getUrlParam("subdetailid");
	var submainid = getUrlParam("submainid");
	if(subdetailid!=null){
		$("#subdetailid").val(subdetailid);
		parentid = subdetailid;
		parenttype = 1;
	}else if(submainid!=null){
		var submainid = getUrlParam("submainid");
		$("#submainid").val(submainid);
		parentid = submainid;
		parenttype = 0;
	}
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
        var num = spinner.eq(1).spinner( "value" );
        if(num==0){
    		myMsg('分值必须大于0！', '40%', '45%', 3000);
    		return false;
        }else{
	    	if(parenttype==1){
		    	$('#'+parentid, window.parent.document).find('.styleBox').html('<input type="text" class="spinner_item" readonly="readonly">');
		    	$('#'+parentid, window.parent.document).find('.styleBox').find('.spinner_item').spinner().spinner( "value", num );
		    	$('#'+parentid, window.parent.document).find('input[name="btnType"]').val("NUM");
	    	}
	    	if(parenttype==0){
	    		$('#'+parentid, window.parent.document).find('ol li').each(function(i){
	    			$(this).find('.styleBox').html('<input type="text" class="spinner_item" readonly="readonly">');
	    			$(this).find('.styleBox').find('.spinner_item').spinner().spinner( "value", num );
	    			$(this).find('input[name="btnType"]').val("NUM");
	    		});
	    	}
	    	parent.countScore();
			parent.closeMyWindows();
        }
    });
    $('#styleBtn2').click(function() {
    	var textNo1 = $('#textNoY').val();
        var textNo2 = $('#textNoN').val();
        var scoreNo1 = $('#scoreNoY').val();
        var scoreNo2 = $('#scoreNoN').val();
        if(textNo1 !='' && textNo2 !='' && scoreNo1 != '' && scoreNo2 != ''){
        	if(parenttype==1){
	        	$('#'+parentid, window.parent.document).find('.styleBox').html('<input type="radio" name="boolStyle" title="' + textNo1 + '" value="' + scoreNo1 + '"> ' + textNo1 + '[' + scoreNo1 + '] <input type="radio" name="boolStyle" title="' + textNo2 + '" value="' + scoreNo2 + '"> ' + textNo2 + '[' + scoreNo2 + ']');
	        	$('#'+parentid, window.parent.document).find('input[name="btnType"]').val("YESNO");
        	}
        	if(parenttype==0){
        		$('#'+parentid, window.parent.document).find('ol li').each(function(i){
        			$(this).find('.styleBox').html('<input type="radio" name="boolStyle'+i+'"  title="' + textNo1 + '" value="' + scoreNo1 + '"> ' + textNo1 + '[' + scoreNo1 + '] <input type="radio" name="boolStyle'+i+'" title="' + textNo2 + '" value="' + scoreNo2 + '"> ' + textNo2 + '[' + scoreNo2 + ']');
        			$(this).find('input[name="btnType"]').val("YESNO");
        		});
        	}
        	parent.countScore();
			parent.closeMyWindows();
        }else {
            myMsg('请先填完整信息！', '40%', '45%', 3000);
            return;
        }
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
        	if(parenttype==1){
        		$('#'+parentid, window.parent.document).find('.styleBox').html(_Html).find('input').attr('name','increased');
	        	$('#'+parentid, window.parent.document).find('input[name="btnType"]').val("PROPORTION");
        	}
        	if(parenttype==0){
        		$('#'+parentid, window.parent.document).find('ol li').each(function(i){
                    var increased = 'increased' + i;
        			$(this).find('.styleBox').html(_Html).find('input').attr('name',increased);
        			$(this).find('input[name="btnType"]').val("PROPORTION");
        		});
        	}
        	parent.countScore();
			parent.closeMyWindows();
        }else {
        	myMsg('请先填完整信息！', '40%', '45%', 3000);
            return;
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
