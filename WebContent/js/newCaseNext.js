$(function(){
//新建病例下一步操作
    $('.page').css('visibility','hidden');
    $('.page').first().css('visibility','visible');
    $('#status').find('li').eq(0).addClass('aa');
    $('.page').find('.next').click(function(){
        var $page = $(this).parents('.page');
    	$page.css('visibility','hidden');
        $page.next().css('visibility','visible');
        var bb = $('.active').next().addClass('active');
        bb.addClass('aa');
    });
    $('.page').find('.prev').click(function(){
    	var $page = $(this).parents('.page');
    	$page.css('visibility','hidden');
    	$page.prev().css('visibility','visible');
    	$('.active').last().removeClass('active');
    });

    $('#status').find('li').click(function(){
        if($(this).hasClass('aa')){
            $(this).nextAll().removeClass('active');
            $(this).prevAll().addClass('active');
            $(this).addClass('active');
            var $t = $(this).index();
            $('.page').css('visibility','hidden');
            $('.page').eq($t).css('visibility','visible');
        }else{
            $(this).addClass('disabled');
        }
    });
//选择分享
    $('#caseShareBtn').click(function(){
    	var _checked = $(this).prop('checked');
    	if(_checked){
    		$('#caseShare').show();
    	}else{
    		$('#caseShare').hide();
    	}
    });
    $('#shareSelBtn').click(function(){
		var obj = {
			selTree: "shareTree",
			inputText: "shareObj",
			treeContent: "shareObjCon",
			selBtn:"shareSelBtn",
			znodes: [
				{id: 1, pId: 0, name: "天堰科技", open: true},
				{id: 101, pId: 1, name: "管理员"},
				{id: 102, pId: 101, name: "管理员1级"},
				{id: 103, pId: 101, name: "管理员2级"},
				{id: 104, pId: 1, name: "教职员"},
				{id: 105, pId: 104, name: "内科"},
				{id: 106, pId: 105, name: "呼吸内科"},
				{id: 107, pId: 106, name: "呼吸内科研究室1"},
				{id: 115, pId: 107, name: "1组"},
				{id: 108, pId: 104, name: "外科"},
				{id: 109, pId: 104, name: "妇产科"},
				{id: 110, pId: 104, name: "男科"},
				{id: 111, pId: 1, name: "学员"},
				{id: 112, pId: 111, name: "2012级学员"},
				{id: 113, pId: 112, name: "2012级内科"},
				{id: 114, pId: 113, name: "2012级呼吸内科"},
				{id: 115, pId: 113, name: "2012级消化内科"},
				{id: 116, pId: 113, name: "2012级神经内科"},
				{id: 117, pId: 112, name: "2012级外科"},
				{id: 118, pId: 117, name: "2012级泌尿外科"},
				{id: 119, pId: 117, name: "2012级心血管外科"},
				{id: 120, pId: 117, name: "2012级骨外科"},
				{id: 121, pId: 112, name: "2012级妇产科"},
				{id: 122, pId: 121, name: "2012级妇科"},
				{id: 123, pId: 121, name: "2012级产科"},
				{id: 124, pId: 121, name: "2012级计划生育"},
				{id: 125, pId: 1, name: "SP"},
				{id: 126, pId: 1, name: "自定义"},
			]
		}
		jsonSelectTree(obj);
	});
//科目类别
    $('#courseSelBtn2').click(function(){
		var obj = {
			selTree: "courseTree2",
			inputText: "courseText2",
			treeContent: "courseTreeCon2",
			selBtn:"courseSelBtn2",
			znodes: [
				{id: 1, pId: 0, name: "天堰科技", open: true},
				{id: 101, pId: 1, name: "管理员"},
				{id: 102, pId: 101, name: "管理员1级"},
				{id: 103, pId: 101, name: "管理员2级"},
				{id: 104, pId: 1, name: "教职员"},
				{id: 105, pId: 104, name: "内科"},
				{id: 106, pId: 105, name: "呼吸内科"},
				{id: 107, pId: 106, name: "呼吸内科研究室1"},
				{id: 115, pId: 107, name: "1组"},
				{id: 108, pId: 104, name: "外科"},
				{id: 109, pId: 104, name: "妇产科"},
				{id: 110, pId: 104, name: "男科"},
				{id: 111, pId: 1, name: "学员"},
				{id: 112, pId: 111, name: "2012级学员"},
				{id: 113, pId: 112, name: "2012级内科"},
				{id: 114, pId: 113, name: "2012级呼吸内科"},
				{id: 115, pId: 113, name: "2012级消化内科"},
				{id: 116, pId: 113, name: "2012级神经内科"},
				{id: 117, pId: 112, name: "2012级外科"},
				{id: 118, pId: 117, name: "2012级泌尿外科"},
				{id: 119, pId: 117, name: "2012级心血管外科"},
				{id: 120, pId: 117, name: "2012级骨外科"},
				{id: 121, pId: 112, name: "2012级妇产科"},
				{id: 122, pId: 121, name: "2012级妇科"},
				{id: 123, pId: 121, name: "2012级产科"},
				{id: 124, pId: 121, name: "2012级计划生育"},
				{id: 125, pId: 1, name: "SP"},
				{id: 126, pId: 1, name: "自定义"},
			]
		}
		singleSelectTree(obj);
	});
//适用对象
	$('#caseobjSelBtn2').click(function(){
		var obj = {
			selTree: "caseobjTree2",
			inputText: "caseobjText2",
			treeContent: "caseobjTreeCon2",
			selBtn:"caseobjSelBtn2",
			znodes: [
				{id: 1, pId: 0, name: "天堰科技", open: true},
				{id: 101, pId: 1, name: "管理员"},
				{id: 102, pId: 101, name: "管理员1级"},
				{id: 103, pId: 101, name: "管理员2级"},
				{id: 104, pId: 1, name: "教职员"},
				{id: 105, pId: 104, name: "内科"},
				{id: 106, pId: 105, name: "呼吸内科"},
				{id: 107, pId: 106, name: "呼吸内科研究室1"},
				{id: 115, pId: 107, name: "1组"},
				{id: 108, pId: 104, name: "外科"},
				{id: 109, pId: 104, name: "妇产科"},
				{id: 110, pId: 104, name: "男科"},
				{id: 111, pId: 1, name: "学员"},
				{id: 112, pId: 111, name: "2012级学员"},
				{id: 113, pId: 112, name: "2012级内科"},
				{id: 114, pId: 113, name: "2012级呼吸内科"},
				{id: 115, pId: 113, name: "2012级消化内科"},
				{id: 116, pId: 113, name: "2012级神经内科"},
				{id: 117, pId: 112, name: "2012级外科"},
				{id: 118, pId: 117, name: "2012级泌尿外科"},
				{id: 119, pId: 117, name: "2012级心血管外科"},
				{id: 120, pId: 117, name: "2012级骨外科"},
				{id: 121, pId: 112, name: "2012级妇产科"},
				{id: 122, pId: 121, name: "2012级妇科"},
				{id: 123, pId: 121, name: "2012级产科"},
				{id: 124, pId: 121, name: "2012级计划生育"},
				{id: 125, pId: 1, name: "SP"},
				{id: 126, pId: 1, name: "自定义"},
			]
		}
		jsonSelectTree(obj);
	});
//增减器
    $('.spinner').spinner();
//添加关键字
    $('#addKeyWords').click(function(){
    	var keyString = $('#keyString').val();
    	if(keyString != ''){
    		var arry = keyString.split(/，|；|、| /);
            var str = '';
    		for(i=0;i<arry.length;i++){
    			var str = str + ('<span>'+ arry[i] + '<a href="javascript:;" class="glyphicon glyphicon-remove"></a></span>');	
    		}
    		$(str).appendTo('#keywords');
    		$('#keyString').val('');
    	}else{
    		alert('请您输入关键字后再添加！');
    	}
    	$('#keywords .glyphicon-remove').click(function(){
    		$(this).parent().remove();
    	});
    });
//编辑SP需求
    $('.needsCon').on('click','.glyphicon-plus',function(){
        var $Html = $(this).parents('.needs').clone();
        $(this).parents('.needs').after($Html);
    });
    $('.needsCon').on('click','.glyphicon-remove',function(){
        var n = $('.needsCon').find('.needs').length;
        if(n>1){
            $(this).parents('.needs').remove();
        }else{
        	alert('只剩一条了，不能再删了！');
        }
    });
//重置按钮功能
    $('.pageReset').click(function(){
    	$(this).parents('form')[0].reset();
    });
//删除绑定评分表
    $('.bindInfo .glyphicon-remove').click(function(){
    	$(this).parents('.bindInfo').remove();
    });



});  