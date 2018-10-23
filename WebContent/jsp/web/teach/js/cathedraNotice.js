$(function() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    var getRandomColor = function(i){    
		  if(i % 3 == 0){
			  return '#dd8d58';
		  }
		  if(i % 3 == 1){
			  return '#69aed2';
		  }
		  return '#81cc5d';     
		}
    $('#calendar').fullCalendar({
        header: {
            center: 'prev,title,next,today'
        },
        contentHeight:pHeight,
        firstDay:1,
        timeFormat: 'H:mm',
        axisFormat: 'H:mm',
//        height: pHeight,
      //生成日历，获取数据源
        events: function(start, end, callback){
              var events = [];
              $.ajax({
                  'url':ctx+'/teachweb/selectCathedraNotice.action',
                  'data': {                
                      timeStart:$.fullCalendar.formatDate(start,"yyyy-MM-dd HH:mm:ss"),
                      timeEnd:$.fullCalendar.formatDate(end,"yyyy-MM-dd HH:mm:ss"),
                      status:'Y'
                  },
                  'dataType': 'json',
                  'type': 'post',
                  'error': function(data){
                      alert("保存失败");
                       return false;
                   },
                  'success': function(doc) {
                      $(doc).each(function(i) {
                          events.push({
                        	  id: doc[i].id,//必须要有
                        	  title: doc[i].cath_title,//必须要有
                        	  start: new Date(doc[i].cath_time),//必须要有
                              backgroundColor: getRandomColor(i),//必须要有
                              allDay: false//必须要有
                          });    
                      });
                      callback(events);//
                  }
              });                        
            },
        //点击查看日程详情    
        eventClick: function(event) {
            //console.log('eventClick中选中Event的id属性值为：', event.id);
            //console.log('eventClick中选中Event的title属性值为：', event.title);
            //console.log('eventClick中选中Event的color属性值为：', event.color);
        	var id = event.id;
            layer.open({
                skin:'calendar-css',
                area: ['1000px', (pHeight-50) + 'px'],
                type: 2,
                content: ctx + '/jsp/web/teach/cathedraNoticeDetail.jsp?id='+id,
            });
        }
    });
    var select_year = parseInt(getServerDate().getFullYear());
    var option_str = '<option value="' + (select_year-1) + '">' + (select_year-1) + '</option>' +
    				 '<option value="' + select_year + '">' + select_year + '</option>' +
    				 '<option value="' + (select_year+1) + '">' + (select_year+1) + '</option>';
    $("#cath_year").append(option_str);
    //年份下拉框设值
    var view_title = $('#calendar').fullCalendar('getView').title;
    $("#cath_year").val(view_title.substring(0,4));
    //拼接左侧日期导航栏的月份
    changeCathDate();
    //根据日历头的title，设置左侧日期导航栏的月份被选中
	var view = $('#calendar').fullCalendar('getView');
	$(".left_btns ul li a").each(function(){
		if($(this).text().trim().substring(0,8) == view.title.trim()){
			$(this).closest('.limonth').addClass('bg_color');
		}
	  });
	//统计每月的总日程条数
	count();
	//添加左侧日期导航栏月份的点击事件，使得日历跳转到相应的日期
	$(".left_btns ul li").click(function(){
		$(this).addClass('bg_color');
	    $(this).siblings().removeClass('bg_color');
	    var year = "";
	    var month = "";
	    var text = $(this).text().trim();
	    if(text.indexOf("年") != -1){
	    	year = text.substring(0, text.indexOf("年"));
	    	if(text.indexOf("月") != -1){
	    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
	    	}
	    }
	    $('#calendar').fullCalendar('gotoDate', year, month, '');
	    //alert($(".fc-header-title").children().val());
	    //$(".fc-header-title").children().val($(this).text().trim());
	   // $('#calendar').fullCalendar('getView').title($(this).text().trim());
	        }
	    );
	
    $(".fc-header-right").html("");
    $(".fc-header-left").html("");
    //左侧日期导航栏的显示和隐藏
    $(".hide_btn").click(
        function () {
            $(".left_btns").toggle();
            if ($(".left_btns").is(":visible")) {
            	$(".section_box").width(pWidth-180);
                $(".left_btns").fadeIn();
            } else {
            	$(".left_btns").fadeOut();
                $(".section_box").width(pWidth-30);
            }
        }
    );
    
    //左侧的日期导航栏的项选中与否随头部的上一月按钮变化而变化，给上一月按钮重写点击事件
    $('.fc-button-prev span').click(function(){
    	var view = $('#calendar').fullCalendar('getView');
    	var title = view.title.trim();
    	var cath_date = "";
    	if(title.substring(5,7) == "01"){//一月份特殊考虑
    		cath_date = (parseInt(title.substring(0,4)) - 1) + "年" + 12;
    		
    		var str = "";
        	var cath_year = parseInt(title.substring(0,4)) -1;
        	$("#cath_year").val(cath_year);
        	for(var i = 1; i <= 12; i++){
        		if(i.toString().length == 1){
        			str += "<li class='limonth'><a>" + cath_year + "年0" + i + "月" + "<span class='badge'></span></a></li>";
        		}else{
        			str += "<li class='limonth'><a>" + cath_year + "年" + i + "月" + "<span class='badge'></span></a></li>";
        		}
        	}
        	$("#ulmonth").html(str);
        	
        	count();
        	
        	//点击函数里放个点击事件，不知可否？
            $(".left_btns ul li").click(function(){
        		$(this).addClass('bg_color');
        	    $(this).siblings().removeClass('bg_color');
        	    var year = "";
        	    var month = "";
        	    var text = $(this).text().trim();
        	    if(text.indexOf("年") != -1){
        	    	year = text.substring(0, text.indexOf("年"));
        	    	if(text.indexOf("月") != -1){
        	    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
        	    	}
        	    }
        	    $('#calendar').fullCalendar('gotoDate', year, month, '');
        	    //alert($(".fc-header-title").children().val());
        	    //$(".fc-header-title").children().val($(this).text().trim());
        	   // $('#calendar').fullCalendar('getView').title($(this).text().trim());
        	        }
        	    );
    	}else{
    		if((parseInt(title.substring(5,7)) - 1).toString().length == 1){
    			cath_date = title.substring(0,5) + "0" + (parseInt(title.substring(5,7)) - 1);
    		}else{
    			cath_date = title.substring(0,5) + (parseInt(title.substring(5,7)) - 1);
    		}
    	}
    	
    	$(".left_btns ul li a").each(function(){
    		if($(this).text().trim().substring(0,7) == cath_date.toString().trim()){
    			$(this).closest('.limonth').addClass('bg_color');
    			$(this).closest('.limonth').siblings().removeClass('bg_color');
    		}
    	  });
  });
    
  //左侧的日期导航栏的项选中与否随头部的下一月按钮变化而变化，给下一月按钮重写点击事件
    $('.fc-button-next span').click(function(){
    	var view = $('#calendar').fullCalendar('getView');
    	var title = view.title.trim();
    	var cath_date = "";
    	if(title.substring(5,7) == "12"){//12月份特殊考虑
    		cath_date = (parseInt(title.substring(0,4)) + 1) + "年01";
    		
    		var str = "";
        	var cath_year = parseInt(title.substring(0,4)) + 1;
        	$("#cath_year").val(cath_year);
        	for(var i = 1; i <= 12; i++){
        		if(i.toString().length == 1){
        			str += "<li class='limonth'><a>" + cath_year + "年0" + i + "月" + "<span class='badge'></span></a></li>";
        		}else{
        			str += "<li class='limonth'><a>" + cath_year + "年" + i + "月" + "<span class='badge'></span></a></li>";
        		}
        	}
        	$("#ulmonth").html(str);
        	

            count();
            
            //换一年，就要重新添加一次点击事件
            $(".left_btns ul li").click(function(){
        		$(this).addClass('bg_color');
        	    $(this).siblings().removeClass('bg_color');
        	    var year = "";
        	    var month = "";
        	    var text = $(this).text().trim();
        	    if(text.indexOf("年") != -1){
        	    	year = text.substring(0, text.indexOf("年"));
        	    	if(text.indexOf("月") != -1){
        	    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
        	    	}
        	    }
        	    $('#calendar').fullCalendar('gotoDate', year, month, '');
        	    //alert($(".fc-header-title").children().val());
        	    //$(".fc-header-title").children().val($(this).text().trim());
        	   // $('#calendar').fullCalendar('getView').title($(this).text().trim());
        	        }
        	    );
    	}else{
    		if((parseInt(title.substring(5,7)) + 1).toString().length == 1){
    			cath_date = title.substring(0,5) + "0" + (parseInt(title.substring(5,7)) + 1);
    		}else{
    			cath_date = title.substring(0,5) + (parseInt(title.substring(5,7)) + 1);
    		}
    	}
    	
    	$(".left_btns ul li a").each(function(){
    		if($(this).text().trim().substring(0,7) == cath_date.toString().trim()){
    			$(this).closest('.limonth').addClass('bg_color');
    			$(this).closest('.limonth').siblings().removeClass('bg_color');
    		}
    	  });
    });
    
  //左侧的日期导航栏的项选中与否随头部的今天按钮变化而变化，给今天按钮重写点击事件
    $('.fc-button-today').click(function(){
    	var view = $('#calendar').fullCalendar('getView');
    	var title = view.title.trim();
    	var cath_date = title.substring(0,7);
    	
    	var str = "";
    	var cath_year = parseInt(title.substring(0,4));
    	$("#cath_year").val(cath_year);
    	for(var i = 1; i <= 12; i++){
    		if(i.toString().length == 1){
    			str += "<li class='limonth'><a>" + cath_year + "年0" + i + "月" + "<span class='badge'></span></a></li>";
    		}else{
    			str += "<li class='limonth'><a>" + cath_year + "年" + i + "月" + "<span class='badge'></span></a></li>";
    		}
    	}
    	$("#ulmonth").html(str);
    	

        count();
        
        //换一年，就要重新添加一次点击事件，因为刚一加载
        $(".left_btns ul li").click(function(){
    		$(this).addClass('bg_color');
    	    $(this).siblings().removeClass('bg_color');
    	    var year = "";
    	    var month = "";
    	    var text = $(this).text().trim();
    	    if(text.indexOf("年") != -1){
    	    	year = text.substring(0, text.indexOf("年"));
    	    	if(text.indexOf("月") != -1){
    	    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
    	    	}
    	    }
    	    $('#calendar').fullCalendar('gotoDate', year, month, '');
    	    //alert($(".fc-header-title").children().val());
    	    //$(".fc-header-title").children().val($(this).text().trim());
    	   // $('#calendar').fullCalendar('getView').title($(this).text().trim());
    	        }
    	    );
        
    	$(".left_btns ul li a").each(function(){
    		if($(this).text().trim().substring(0,7) == cath_date.trim()){
    			$(this).closest('.limonth').addClass('bg_color');
    			$(this).closest('.limonth').siblings().removeClass('bg_color');
    		}
    	  });
    });
    
});

//左侧日期导航栏的日期拼接根据年份的变动
function changeCathDate(){
	var str = "";
	var cath_year = $("#cath_year").val();
	for(var i = 1; i <= 12; i++){
		if(i.toString().length == 1){
			str += "<li class='limonth'><a>" + cath_year + "年0" + i + "月" + "<span class='badge'></span></a></li>";
		}else{
			str += "<li class='limonth'><a>" + cath_year + "年" + i + "月" + "<span class='badge'></span></a></li>";
		}
	}
	$("#ulmonth").html(str);
}

//年份选择变化
function changeAndCount(){
	
	changeCathDate();
	
	var cath_date = $("#cath_year").val() + "年01月";
	$(".left_btns ul li a").each(function(){
		if($(this).text().trim().substring(0,8) == cath_date.trim()){
			$(this).closest('.limonth').addClass('bg_color');
		}
	  });
	
	var year = "";
    var month = "";
    var text = cath_date.trim();
    if(text.indexOf("年") != -1){
    	year = text.substring(0, text.indexOf("年"));
    	if(text.indexOf("月") != -1){
    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
    	}
    }
    $('#calendar').fullCalendar('gotoDate', year, month, '');
    
    count();
    
    //重新加载日期导航栏就重新放个点击事件
    $(".left_btns ul li").click(function(){
		$(this).addClass('bg_color');
	    $(this).siblings().removeClass('bg_color');
	    var year = "";
	    var month = "";
	    var text = $(this).text().trim();
	    if(text.indexOf("年") != -1){
	    	year = text.substring(0, text.indexOf("年"));
	    	if(text.indexOf("月") != -1){
	    		month = text.substring(text.indexOf("年") + 1,text.indexOf("月"));
	    	}
	    }
	    $('#calendar').fullCalendar('gotoDate', year, month, '');
	    //alert($(".fc-header-title").children().val());
	    //$(".fc-header-title").children().val($(this).text().trim());
	   // $('#calendar').fullCalendar('getView').title($(this).text().trim());
	        }
	    );
}

//统计讲座编排条数
function count(){
	var dates = "";
	var months = $(".left_btns ul li a");
	for (var i = 0; i < months.length; i++){
		dates += ((months[i].text.trim().replace("年","-")).replace("月"," "));
	}
	$.ajax({
		url:ctx+'/teachweb/countCathedraNotice.action',
		data:{date:dates},
		dataType: 'json',
		success:function(res) {
			for(var j = 0; j < res.data.length; j++){
				$(".left_btns ul li a").each(function(){
					if(($(this).text().replace("年","")).replace("月","") == (res.data)[j].cath_date.toString().substring(0,6) && (res.data)[j].count != 0){
						$(this).children('.badge').text((res.data)[j].count);
					}
				});
			}
		}
	});
}

//关闭弹出的窗口与layer.open对应
function closeLayer(){
    layer.closeAll();
}

//保存操作后fullCalendar方式的更新日程表没成功，会刷新到本页面，有wenti 
function reload(id){
//	$('#calendar').fullCalendar('renderEvent', id);  //核心的更新代码
	closeLayer();
	window.location.href= ctx + "/jsp/web/teach/cathedra.jsp";
//	count();
}

//删除日程后更新日程表
function remove(_id){
    $("#calendar").fullCalendar("removeEvents",_id);//更应该用自带的刷新
    closeLayer();
    window.location.href= ctx + "/jsp/web/teach/cathedra.jsp";
//    count();不进去，为什么？
}
//服务器时间
function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}