$(function(){

// 添加学生弹层
    $('#allStu').click(function(){
    	var _checked = $(this).prop('checked');
    	if(_checked){
    		$('input[name="stuInfo"]').prop('checked',true);
    	}else{
    		$('input[name="stuInfo"]').prop('checked',false);
    	}
    });

    $('#addStuInfo').click(function(){
        var item = $('input[name="stuInfo"]:checked:enabled').length;          
        if(item !=0){
        	$('input[name="stuInfo"]:checked:enabled').parents('tr').each(function(){
        		$(this).clone().html(function(index,oldHtml){
        			return oldHtml + '<td><a herf="javascript:;" onclick="del();">删除</a></td>';
        		}).prependTo('#addStu tbody');
        		$(this).find('input').prop('disabled',true);
        		$('#addStu tbody').find('tr input').attr('name','newStuInfo');
                $('#allStu2').click(function(){
                    var _checked = $(this).prop('checked');
                    if(_checked){
                        $('input[name="newStuInfo"]').prop('checked',true);
                    }else{
                        $('input[name="newStuInfo"]').prop('checked',false);
                    }
                });
        	});
        }else{
        	alert('您还未挑选学生');
        }
        $('#addStu tbody tr').each(function(num){
    		$(this).find('td').eq(1).text(num + 1);
            if(num%2 ==0){
                $(this).css('background','none');
            }else{
                $(this).css('background','#edeff5');
            }
    	});

    });

//编辑模型弹层

    $('#allModel').click(function(){
    	var _checked = $(this).prop('checked');
    	if(_checked){
    		$('input[name="modelInfo"]').prop('checked',true);
    	}else{
    		$('input[name="modelInfo"]').prop('checked',false);
    	}
    });
    
    $('#addModelInfo').click(function(){
    	var item = $('input[name="modelInfo"]:checked:enabled').length;   
    	if(item !=0){
    		$('input[name="modelInfo"]:checked:enabled').parents('tr').each(function(){
    			$(this).clone().html(function(){
                    $(this).find('td:first').remove();
                    var newValue =  $(this).find('.ui-spinner input').attr('aria-valuenow');
                    $(this).find('td').eq(3).html('<input class="newModelSpinner" value="' + newValue +'">');
                    return $(this).html() + '<td><a herf="javascript:;" onclick="del();">删除</a></td>';
    			}).prependTo('#addModel tbody');
                $(this).find('input').prop('disabled',true);
    		});
    	}else{
    		alert('您还未挑选模型');
    	}
    	$('#addModel tbody tr').each(function(num){
            $('.newModelSpinner').spinner();
    		$(this).find('td').first().text(num + 1);
            if(num%2 ==0){
                $(this).css('background','#edeff5');
            }else{
                $(this).css('background','none');
            }
    	});
    });
    
//添加/编辑耗材弹层

    $('#loss-editor .btn-group').find('a').click(function(){
    	$(this).addClass('btn-info');
    	$(this).removeClass('btn-default');
    	$(this).siblings().addClass('btn-default');
    	$(this).siblings().removeClass('btn-info');
    });

    $('#allLoss').click(function(){
    	var _checked = $(this).prop('checked');
    	if(_checked){
    		$('input[name="lossInfo"]').prop('checked',true);
    	}else{
    		$('input[name="lossInfo"]').prop('checked',false);
    	}
    });    

    $('#addLossInfo').click(function(){
    	var item = $('input[name="lossInfo"]:checked:enabled').length;   
    	if(item !=0){
    		$('input[name="lossInfo"]:checked:enabled').parents('tr').each(function(){
    			$(this).clone().html(function(){
                    $(this).find('td:first').remove();
                    var newValue =  $(this).find('.ui-spinner input').attr('aria-valuenow');
                    $(this).find('td').eq(3).html('<input class="newLossSpinner" value="' + newValue +'">');
                    $(this).find('.ui-spinner input').attr('value',newValue);
                    return $(this).html() + '<td><a herf="javascript:;" onclick="del();">删除</a></td>';
    			}).prependTo('#addLoss tbody');
                $(this).find('input').prop('disabled',true);
    		});
    	}else{
    		alert('您还未挑选耗材');
    	}
    	$('#addLoss tbody tr').each(function(num){
            $('.newLossSpinner').spinner();
            $(this).find('td').first().text(num + 1);
            if(num%2 ==0){
                $(this).css('background','#edeff5');
            }else{
                $(this).css('background','none');
            }
    	});
    });

//添加教师弹层

    $('#allTea').click(function(){
    	var _checked = $(this).prop('checked');
    	if(_checked){
    		$('input[name="teaInfo"]').prop('checked',true);
    	}else{
    		$('input[name="teaInfo"]').prop('checked',false);
    	}
    });

    $('#addTeaInfo').click(function(){
        var item = $('input[name="teaInfo"]:checked:enabled').length;          
        if(item !=0){
        	$('input[name="teaInfo"]:checked:enabled').parents('tr').each(function(){
        		$(this).clone().html(function(){
                    $(this).find('td:first').remove();
                    return $(this).html() + '<td><a herf="javascript:;" onclick="del();">删除</a></td>';
        		}).prependTo('#addTea tbody');
        		$(this).find('input').prop('disabled',true);
        		$('#addTea tbody').find('tr input').attr('name','newTeaInfo');
        	});
        }else{
        	alert('您还未挑选教师');
        }
        $('#addTea tbody tr').each(function(num){
            $(this).find('td').first().text(num + 1);
            if(num%2 ==0){
                $(this).css('background','#edeff5');
            }else{
                $(this).css('background','none');
            }
    	});
    });

//添加病例/评分表弹层

    $('#allCase').click(function(){
        var _checked = $(this).prop('checked');
        if(_checked){
            $('input[name="caseInfo"]').prop('checked',true);
        }else{
            $('input[name="caseInfo"]').prop('checked',false);
        }
    });

    $('#addCaseInfo').click(function(){
        var item = $('input[name="caseInfo"]:checked:enabled').length;          
        if(item !=0){
            $('input[name="caseInfo"]:checked:enabled').parents('tr').each(function(){
                $(this).clone().html(function(index,oldHtml){
                    var script = '<td><select class="caseTable"><option>剧本1</option><option>剧本2</option><option>剧本3</option><option>剧本4</option></select><select class="ifSp"><option>需要SP</option><option>不需要SP</option></select></td><td><select class="scoreTable"multiple="multiple"><option>评分表1</option><option>评分表2</option><option>评分表3</option></select></td><td><a herf="javascript:;"onClick="del();">删除</a></td>';
                    return oldHtml + script;
                }).prependTo('#addCase tbody');
                $(this).find('input').prop('disabled',true);
                $('#addCase tbody').find('tr input').attr('name','newCaseInfo');
            });
        }else{
            alert('您还未挑选病例');
        }
        $('#addCase tbody tr').each(function(num){
            $(this).find('td').eq(1).text(num + 1);
        });

        $('.caseTable').multiselect({
            buttonWidth: '48%'
        });
        $('.ifSp').multiselect({
            buttonWidth: '48%'
        });
        $('.scoreTable').multiselect({
            buttonWidth: '100%'
        });

        $('#allAdd').click(function(){
            var _checked = $(this).prop('checked');
            if(num%2 ==0){
                $(this).css('background','#edeff5');
            }else{
                $(this).css('background','none');
            }
        });

    });
    
    $('#addScoreInfo').click(function(){
        var item = $('input[name="newCaseInfo"]:checked:enabled').length;          
        if(item !=0){
            $('input[name="newCaseInfo"]:checked:enabled').parents('tr').each(function(){
                var Html = '<tr><td><input type="checkbox" name="scoreInfo"></td><td>1</td><td>评分表3</td><td><input class="weight" value="5"></td><td><a herf="javascript:;"onClick="del();">删除</a></td></tr>';
                $(Html).prependTo('#addscore tbody');
                $(this).find('input').prop('disabled',true);
            });
        }else{
            alert('您还未挑选评分表');
        }
        
        $('#addscore .weight').spinner();
        $('#addscore tbody tr').each(function(num){
            var flag = $('#addscore tbody tr:contains(设备评分)');
            if(!flag){
                $(this).find('td').eq(1).text(num + 1);
            }
        });
    });

    $('#equScore').click(function(){
        var _checked = $(this).prop('checked');
        if(_checked){
            var Html = '<tr><td colspan="3" align="center">设备评分</td><td colspan="2"><input class="weight" value="5"></td></tr>';
            $(Html).appendTo('#addscore tbody');
        }else{
            if($('#addscore tbody tr:last:contains(设备评分)')){
                $('#addscore tbody tr:last').remove();
            }
        }
        $('#addscore .weight').spinner();
    });

    $('#allscore').click(function(){
        var _checked = $(this).prop('checked');
        if(_checked){
            $('input[name="scoreInfo"]').prop('checked',true);
        }else{
            $('input[name="scoreInfo"]').prop('checked',false);
        }
    });

    $('#batchSet').click(function(){
        var item = $('input[name="scoreInfo"]:checked:enabled').length;   
        if(item !=0){
            $('input[name="scoreInfo"]:checked:enabled').parents('tr').each(function(){
                $(this).html(function(){
                    $(this).find('td').eq(3).remove();
                }).prependTo('#batchScore tbody');

                $(this).find('input').prop('disabled',true);
                $('#batchScore tbody').find('tr input').attr('name','newScoreInfo');
            });

            $('#batchScore').find('tr').first().find('td').eq(2).after('<td rowspan="' + item + '"><input class="weight" value="5"></td>');

        }else{
            alert('您还未挑选评分表');
        }
        $('#batchScore tbody tr').each(function(num){
            $(this).find('td').eq(1).text(num + 1);
        });

        $('#batchScore .weight').spinner();
    });
    

    
});