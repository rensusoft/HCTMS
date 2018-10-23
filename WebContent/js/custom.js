$(function(){
//弹出菜单
	$('.down').hover(function(){
    	$(this).css('background','#485582');
    	$(this).find('.down-menu').stop().slideDown('fast');
    },function(){
    	$(this).css('background','none');
    	$(this).find('.down-menu').stop().slideUp('fast'); 
    });

	$('#submenu1').hover(function(){
        $('#show1').addClass('show');
	});
	$('#submenu1').siblings().hover(function(){
        $('#show1').removeClass('show');
	});

	$('#submenu2').hover(function(){
        $('#show2').addClass('show');
	});
	$('#submenu2').siblings().hover(function(){
        $('#show2').removeClass('show');
	});

	$('#submenu3').hover(function(){
        $('#show3').addClass('show');
	});
	$('#submenu3').siblings().hover(function(){
        $('#show3').removeClass('show');
	});

	var bb = 0;
    var aa = 0;

    $('.menu-L').last().find('a').mouseenter(function(){
        if ( bb == 1 ){
            var H_L = aa;
	        var H_R = $(this).parents('.down-menu').find('.menu-R.show').height();
	        if( H_L < H_R ){
	        	$(this).parent().height(H_R);
	        }else{
	        	$(this).parent().height(H_L);
	        }
        }else{
            var H_L = $(this).parent().height();
            aa = H_L;
	        var H_R = $(this).parents('.down-menu').find('.menu-R.show').height();
	        if( H_L < H_R ){
	        	$(this).parent().height(H_R);
	        }
	        bb = 1;
        }
    });

    var clientWidth = $(window).width();
    if(clientWidth <= 1360) {
    	$('.user span').addClass('text-hide');
	}

	var clientHeight = $(window).height();
    if(clientHeight <= 768) {
    	$('#myTabContent').height("90.5%");
	}

	$('#show3').css({
		'position':'absolute',
		'left':'-250px',
		'background':'#fff',
		'border-left':'1px solid #505f91',
		'border-bottom':'1px solid #505f91'
	});

//全选事件
	    
	$('#selAll').click(function(){
	    var _checked = $(this).prop('checked');
	    if(_checked){
	    	$('input[name="selItem"]').prop('checked',true);
	    }else{
	    	$('input[name="selItem"]').prop('checked',false);
	    }
	});


//区分全选事件
	    
	$('#selsonAll').click(function(){
	    var _checked = $(this).prop('checked');
	    if(_checked){
	    	$('input[name="selsonItem"]').prop('checked',true);
	    }else{
	    	$('input[name="selsonItem"]').prop('checked',false);
	    }
	});


//新建页面全选事件

    $('input[name="selAll"]').click(function() {
    	var _checked = $(this).prop('checked');
    	if(_checked) {
            $(this).parents('table').find('input[name="selItem"]').prop('checked',true);
    	}else {
    		$(this).parents('table').find('input[name="selItem"]').prop('checked',false);
    	}
    });


//控制列显示
    
    $('.colSelBtn').on('click',function(e) {
    	$('.colBox').show();
    	$('.set-column').addClass('move');
        $(document).one("click", function(){
	        $(".colBox").hide();
	        $('.set-column').removeClass('move');
	    });
	    e.stopPropagation();
    });
    $('.colBox').on('click', function(e){
	    e.stopPropagation();
	});

	$('.col-hide').siblings().click(function(){
        var _checked = $(this).siblings().prop('checked');
        var colTitle = $.trim($(this).text());
        if(_checked){
        	$(this).siblings().prop('checked',false);
        	$('table th').each(function(index, el) {
    			var theadTitle = $.trim($(this).text());
    			if(colTitle == theadTitle){
    				$(this).hide();
                    $('table').find('tr').each(function() {
                    	$(this).find('td').eq(index).hide();
                    });
    			}
    		});
        }else {
        	$(this).siblings().prop('checked',true);
        	$('table th').each(function(index, el) {
    			var theadTitle = $.trim($(this).text());
    			if(colTitle == theadTitle){
    				$(this).show();
                    $('table').find('tr').each(function() {
                    	$(this).find('td').eq(index).show();
                    });
    			}
    		});
        }
	});

    $('.col-hide').click(function() {
    	var _checked = $(this).prop('checked');
    	var colTitle = $.trim($(this).siblings().text());
    	if(!_checked){
    		$('table th').each(function(index, el) {
    			var theadTitle = $.trim($(this).text());
    			if(colTitle == theadTitle){
    				$(this).hide();
                    $('table').find('tr').each(function() {
                    	$(this).find('td').eq(index).hide();
                    });
    			}
    		});
    	}else {
    		$('table th').each(function(index, el) {
    			var theadTitle = $.trim($(this).text());
    			if(colTitle == theadTitle){
    				$(this).show();
                    $('table').find('tr').each(function() {
                    	$(this).find('td').eq(index).show();
                    });
    			}
    		});
    	}
    });

//批量操作
    
    $('.dropBtn').on('click',function(e) {
		var dropOpen = $(this).parent('.drop-con').hasClass('dropBox-open');
		if (dropOpen){
			$('.drop-con').removeClass('dropBox-open');
		}else{
			$('.drop-con').removeClass('dropBox-open');
			$(this).parent('.drop-con').addClass('dropBox-open');
			$(document).one("click", function(){
				$(".drop-con").removeClass('dropBox-open');
			});
		}
	    e.stopPropagation();
    });
    $('.dropBox').on('click', function(e){
	    e.stopPropagation();
	});

// 复选框选中显示

    $('.shareSta').click(function() {
    	var _checked = $(this).prop('checked');
    	if(_checked) {
    		$('.tree-box.display').css('visibility','visible');
    	}else {
    		$('.tree-box.display').css('visibility','hidden');
    	}
    });

// 展开收起功能
	$('.toggleInfo').click(function() {
		var coins = $(this).find('.glyphicon').hasClass('glyphicon-chevron-up');
		var text = $(this).find('.infoText').text();
		if(coins && text == '收起更多详情') {
			$(this).find('.glyphicon-chevron-up').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
			$(this).find('.infoText').text('展开更多详情');
		}
		if(!coins && text == '展开更多详情') {
			$(this).find('.glyphicon-chevron-down').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
			$(this).find('.infoText').text('收起更多详情');
		}
	});









//添加关键词

	$('.addKeyWords').click(function(){
		var keyString = $('.keyString').val();
		if(keyString != ''){
			var arry = keyString.split(/，|；|、|,| /);
			var str = '';
			for(i=0;i<arry.length;i++){
				var str = str + ('<span>'+ arry[i] + '<a href="javascript:;" class="glyphicon glyphicon-remove"></a></span>');	
			}
			$(str).appendTo('.keywords');
			$('.keyString').val('');
		}else{
			alert('请您输入关键字后再添加！');
		}
		$('.keywords .glyphicon-remove').click(function(){
			$(this).parent().remove();
		});
	});


//复选框选中显示

    $('.showBtn').click(function() {
        var _checked = $(this).prop('checked');
        if(_checked){
            $(this).parent().children('.btnBox, .showTable, .tip, form').show();
        }else {
            $(this).parent().children('.btnBox, .showTable, .infoBox, .tip, form').hide();
        }
    });

    $('.showBtn2').click(function() {
        $(this).parent().siblings('.infoBox').show();
    });

    $('body').on('click','.showBtn3',function() {
    	var box = $(this).parents('.downBox').find('span');
        $(this).parents('table').siblings('.infoBox').show().find('table input').prop('checked',false);

        $('#upBtn5').one('click',function(e) {
            var num = $(this).siblings('.infoTable').find('input[type="radio"]:checked').length;
            if(num=1){
            	var scoreName = $(this).siblings('.infoTable').find('input[type="radio"]:checked').parents('tr').find('td').eq(2).html();
            	box.html(scoreName);
            }else {

            	alert('请先选择要绑定的评分表！');
            }
            $('.infoBox').hide();
        });

    });

    $('.closeBtn').click(function() {
        $(this).parents('.infoBox').hide();
    });

// 文本框获取焦点失去焦点触发事件

    $('body').on('focus','.focusBox',function() {
    	$(this).addClass('borderBox');
    });
    $('body').on('blur','.focusBox',function() {
        $(this).removeClass('borderBox');
    });

//含有spinner单元格样式

    $('.spinner,.spinner2').parents('td').css('padding','3px 8px');

// sp需求添加功能

    $('.needsCon').on('click','.glyphicon-plus',function(){
        var Html = $(this).parents('.form-group').clone();
            $(this).parents('.form-group').after(Html);
        });
        $('.needsCon').on('click','.glyphicon-remove',function(){
            var num = $('.needsCon').children().length;
            if(num > 1 ){
                $(this).parents('.form-group').remove();
            }else {
            	alert('只剩一条了，不能再删了！');
        }
    });

//点击铅笔按钮实现下拉选择功能

    $('body').on('click','.downBox .glyphicon-pencil',function(e) {
    	$('.downCon').hide();
    	$(this).siblings('.downCon').show();
        $(document).one('click', function(){
	        $('.downCon').hide();
	    });
	    e.stopPropagation();
    });

    $('body').on('click', '.downCon a',function(e){
    	var content = $(this).text();
    	if(content == '病例评分表') {
    		$(this).parents('.downBox').children('span').html('<a href="scoreInfo.html" target="_blank">' + content + '</a>');
    	}
        $('.downCon').hide();
	    e.stopPropagation();
	});


});





$(function(){
//共享对象
	$('.itemShare,.batchShare').click(function(){
		layer.open({
			type:2,
			title:'共享病例',
			area:['25%','44%'],
			content:'./caseShare.html',
			btn:['确认','取消']
		});
	});

	$('.itemShare2,.batchShare2').click(function(){
		layer.open({
			type:2,
			title:'共享评分表',
			area:['25%','44%'],
			content:'./scoreShare.html',
			btn:['确认','取消']
		});
	});

	$('.itemShare3,.batchShare3').click(function(){
		layer.open({
			type:2,
			title:'共享反馈表',
			area:['25%','44%'],
			content:'./feedbackShare.html',
			btn:['确认','取消']
		});
	});

	$('.itemShare4,.batchShare4').click(function(){
		layer.open({
			type:2,
			title:'共享课程模板',
			area:['25%','44%'],
			content:'./courseTempletShare.html',
			btn:['确认','取消']
		});
	});

//删除对象
	$('.del,.batchDel').click(function(){
		layer.confirm('<i class="layui-layer-ico layui-layer-ico3"></i> 确认删除？', {
			title:'提示',
			area:['25%','35%'],
			btn: ['确认','取消']
		})
	});


//删除对象
	$('.disable').click(function(){
		layer.confirm('<i class="layui-layer-ico layui-layer-ico3"></i> 确认禁用？', {
			area:['25%','35%'],
			btn: ['确认','取消']
		})
	});


//取消对象
	$('.cancel,.batchCancel').click(function(){
		layer.confirm('<i class="layui-layer-ico layui-layer-ico3"></i> 确定取消？', {
			title:'提示',
			area:['25%','35%'],
			btn: ['确定','取消']
		})
	});



//批量设置状态
    $('.batchState').click(function(){
    	layer.open({
			type:2,
			title:'设置状态',
			area:['25%','44%'],
			content:'./batchState.html',
			btn:['确认','取消']
	    });
    });



//成绩管理 - 填写反馈表 - 提交

	$('.markState').click(function(){
    	layer.open({
			type:2,
			title:'设置状态',
			area:['25%','44%'],
			content:'./markState.html',
			btn:['确认','取消']
	    });
    });




//复制对象
	$('.copy_item').click(function(){
		var copyV = $(this).parents('tr').find('td').eq(2).text();
		layer.open({
		    type: 1,
		    area:['25%','44%'],
		    content: '病例名称：<input type="text" class="form-control" value="'+ copyV + '_copy">',
		    btn:['确认','取消']
		});
	});
	$('.copy_item2').click(function(){
		var copyV = $(this).parents('tr').find('td').eq(2).text();
		layer.open({
		    type: 1,
		    area:['25%','44%'],
		    content: '评分表名称：<input type="text" class="form-control" value="'+ copyV + '_copy">',
		    btn:['确认','取消']
		});
	});
	$('.copy_item3').click(function(){
		var copyV = $(this).parents('tr').find('td').eq(2).text();
		layer.open({
		    type: 1,
		    area:['25%','44%'],
		    content: '反馈表名称：<input type="text" class="form-control" value="'+ copyV + '_copy">',
		    btn:['确认','取消']
		});
	});
	$('.copy_item4').click(function(){
		var copyV = $(this).parents('tr').find('td').eq(2).text();
		layer.open({
		    type: 1,
		    area:['25%','44%'],
		    content: '课程模板名称：<input type="text" class="form-control" value="'+ copyV + '_copy">',
		    btn:['确认','取消']
		});
	});

//导出对象
	$('.export').click(function() {
		layer.open({
		    type: 1,
		    area:['25%','44%'],
		    content: '<label class="radio-inline"><input type="radio" name="export"> <img src="img/pdf.png" alt="pdf格式" class="margin_right_40"></label><label class="radio-inline"><input type="radio" name="export"> <img src="img/execl.png" alt="pdf格式" class="margin_right_40"></label>',
		    btn:['确认','取消']
		});
	});

// 下载表格类型

    $('.styleModel').click(function() {
    	layer.open({
			type:2,
			title:'下载模板',
			area:['50%','44%'],
			content:'styleModel.html',
			btn:['确认','取消']
	    });
    });

//报失对象
	$('.miss').click(function(){
		layer.confirm('真的要报失吗？', {
			title:'资源报失',
			area:['25%','35%'],
			btn: ['确认','取消']
		})
	});

//报废对象
	$('.scrap').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','44%'],
			content:'./' + link + '.html',
			btn:['确认','取消']
		});
	});

//报修对象
	$('.repair').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','44%'],
			content:'./' + link + '.html',
			btn:['确定','取消']
		});
	});

//回收对象
	$('.recovery').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','44%'],
			content:'./' + link + '.html',
			btn:['确认','取消']
		});
	});
	
//回收对象
	$('.order').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','50%'],
			content:'./' + link + '.html',
			btn:['确认','取消']
		});
	});


//成绩对象
	$('.markobj').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['60%','64%'],
			content:'./' + link + '.html',
			btn:['确定','关闭']
		});
	});


//成绩管理-成绩修改-操作日志
	$('.markhistory').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['85%','90%'],
			content:'./' + link + '.html',
			btn:['确定','关闭']
		});
	});


//厂商管理
	$('.manufacturer').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['40%','55%'],
			content:'./' + link + '.html',
			btn:['确认','取消']
		});
	});


//资源管理-申请驳回
	$('.resource_fail').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','40%'],
			content:'./' + link + '.html',
			btn:['关闭']
		});
	});

//资源管理-审批通过
	$('.resource_passed').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['25%','30%'],
			content:'./' + link + '.html',
			btn:['关闭']
		});
	});


//打印设备标签
	$('.print').click(function(){
		var Num = $(".deviceCode input:checkbox:checked").length;
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		if (Num == 1){
			layer.open({
				type:2,
				title:title,
				area:['25%','44%'],
				content:'./' + link + '.html'
			});
		}else if (Num > 1){
			layer.open({
				type:2,
				title:title,
				area:['60%','80%'],
				content:'./batch' + link + '.html'
			});
		}else {
			layer.msg('请先选择一项设备', {
				icon: 0
			});
		}
	});
//对象详情
	$('.detail').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['40%','90%'],
			content:'./' + link + '.html',
			btn:['关闭']
		});
	});
//对象日志
	$('.history').click(function(){
		var title = $(this).attr('title');
		var link = $(this).attr('name');
		layer.open({
			type:2,
			title:title,
			area:['60%','90%'],
			content:'./' + link + '.html',
			btn:['关闭']
		});
	});
//资源导入
	$('.deviceImport').click(function(){
		layer.open({
			type:2,
			title:'导入',
			area:['40%','44%'],
			content:'./deviceImport01.html',
			btn:['确认','取消'],
			yes: function(index, layero){
				layer.closeAll('iframe');
				layer.open({
					type:2,
					title:'导入',
					area:['60%','90%'],
					content:'./deviceImport02.html',
					btn:['保存','保存并继续导入','关闭']
				});
			},
		});
	});
	//绑定房间
	$('.roomBind').click(function(){
		layer.open({
			type:2,
			title:'绑定房间',
			area:['25%','50%'],
			content:'./roomBind.html',
			btn:['确认','取消'],
		});
	});

});

// 新建病例左侧导航的上下移动方法
function menuMove(i){
	var menuHeight = $('.stepMenu').height();
	var liNum = $('.stepMenu').find('li').length;
	var liHeight = $('.stepMenu').find('li').outerHeight();
	var liDistance = 25;
	$('.stepMenu li').each(function(index) {
		if (!(index == 0 || index == liNum-1)){
			if (index > i){
				$('.stepMenu li:eq(' + index + ')').stop().animate({
					top : (menuHeight - (liNum - index)*liHeight - (liNum - index - 1)*liDistance) + 'px'
				},300,'linear',function(){
					$('.stepMenu li').removeClass('current');
					$('.stepMenu li:eq(' + i + ')').addClass('current');
				});
			}else{
				$('.stepMenu li:eq(' + index + ')').stop().animate({
					top : (liHeight + liDistance)*index + 'px'
				},300,'linear',function(){
					$('.stepMenu li').removeClass('current');
					$('.stepMenu li:eq(' + i + ')').addClass('current');
				});
			}
		}
	});
}


//滚动监听
var prevTop = 0,currTop = 0,boxOffset = [];
function scollBox (){
	$('.step-box .step').each(function(index) {
		boxOffset[index] = $(this)[0].offsetTop - $(window).scrollTop();;
	});
	currTop = $(window).scrollTop();
	var boxTemp= 0;
	$.each(boxOffset,function(i,value) {
		if (value < 0){
			boxOffset.splice(i,1,'100000');
		}
	});
	var minOffset = Math.min.apply(Math, boxOffset);
	$.each(boxOffset,function(i,value) {
		if (value == minOffset){
			boxTemp = i;
		}
	});
	$('.step-box .step').removeClass('current');
	$('.step-box .step:eq(' + boxTemp + ')').addClass('current');
	menuMove(boxTemp);
	//prevTop = currTop; //IE下有BUG，所以用以下方式
	setTimeout(function(){prevTop = currTop},0);
}
