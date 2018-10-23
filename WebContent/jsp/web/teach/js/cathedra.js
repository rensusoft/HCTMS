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
        events: function(start, end, callback){//生成日历
              var events = [];
              $.ajax({
                  'url':ctx+'/teachweb/selectCathedraPlan.action',
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
                        	  id: doc[i].cathedraPlan.id,//必须要有
                        	  title: doc[i].cathedraPlan.cath_title,//必须要有
                        	  start: new Date(doc[i].cathedraPlan.cath_time),//必须要有
                        	  cath_title: doc[i].cathedraPlan.cath_title,
                              cath_content: doc[i].cathedraPlan.cath_content,
                              speaker: doc[i].cathedraPlan.speaker,
                              cath_date: doc[i].cathedraPlan.cath_date,
                              cath_time: doc[i].cathedraPlan.cath_time,
                              cath_place: doc[i].cathedraPlan.cath_place,
                              create_time: doc[i].cathedraPlan.create_time,
                              creater_authid: doc[i].cathedraPlan.creater_authid,
                              class_condition_id: doc[i].class_condition_id,
                              type_condition_id: doc[i].type_condition_id,
                              class_condition_type: doc[i].class_condition_type,
                              class_condition_value: doc[i].class_condition_value,
                              type_condition_type: doc[i].type_condition_type,
                              type_condition_value: doc[i].type_condition_value,
                              backgroundColor: getRandomColor(i),//必须要有
                              allDay: false//必须要有
                          });    
                      });
                      callback(events);//
                  }
              });                        
            },
            
        eventClick: function(event) {
            //console.log('eventClick中选中Event的id属性值为：', event.id);
            //console.log('eventClick中选中Event的title属性值为：', event.title);
            //console.log('eventClick中选中Event的color属性值为：', event.color);
        	var id = event.id;
            layer.open({
                skin:'calendar-css',
                area: ['1000px', '425px'],
                type: 2,
                content: 'newSchedule.jsp?id='+id,
            });
        },
        
        dayClick : function(date, allDay, jsEvent, view) {
            //console.log(date);  //当前日期
        	var  day = $.fullCalendar.formatDate(date, "yyyy-MM-dd");
            var day1 = $.fullCalendar.formatDate(date, "yyyy/MM/dd");
            if(new Date(day1) >= new Date(getServerDate().toLocaleDateString())){//根据日期设置可点击添加日程
            	layer.open({
                    skin:'calendar-css',
                    shadeClose:true,
                    title:'新增讲座',
                    type: 2,
                    content: 'newSchedule.jsp?day='+day,
                    area: ['1000px', '425px']
                });
            }else{
            	myalert_error("当日之前的日期不能添加讲座编排!","30%","40%");
            }
        }
    });
    var select_year = parseInt(getServerDate().getFullYear());
    var option_str = '<option value="' + (select_year-1) + '">' + (select_year-1) + '</option>' +
    				 '<option value="' + select_year + '">' + select_year + '</option>' +
    				 '<option value="' + (select_year+1) + '">' + (select_year+1) + '</option>';
    $("#cath_year").append(option_str);
    var view_title = $('#calendar').fullCalendar('getView').title;
    $("#cath_year").val(view_title.substring(0,4));
    changeCathDate();
//    $.ajax({
//    	async:false,
//		url:ctx + "/teachweb/getCathDate.action",
//		data:{},
//		dataType: 'json',
//		success:function(req) {
//			 var fristarry = (req.data).slice(0,1);//截取第一个对象
//			 var str = "";
//			 for(var k1 = 0; k1 < fristarry.length; k1++){
//				 var firstmon = parseInt(fristarry[k1].cath_date_str.substring(5));
//				 var firstyer = parseInt(fristarry[k1].cath_date_str.substring(0,4));
//				 if(firstmon == 12){
//					 firstyer = firstyer + 1;
//					 firstmon = 1;
//				 }else{
//					 firstmon = firstmon + 1;
//				 }
//				 if(firstmon.toString().length == 1){
//					 str = "<li class='limonth'><a>" + firstyer + "年0" + firstmon + "月" + "<span class='badge'></span></a></li>" +
//					 "<li class='limonth'><a>" + fristarry[k1].cath_date_str.replace("-","年") + "月" + "<span class='badge'></span></a></li>";
//				 }else{
//					 str = "<li class='limonth'><a>" + firstyer + "年" + firstmon + "月" + "<span class='badge'></span></a></li>" +
//					 "<li class='limonth'><a>" + fristarry[k1].cath_date_str.replace("-","年") + "月" + "<span class='badge'></span></a></li>";
//				 }
//			 }
////			 alert("ok1");
////			 alert(str);
//			 var datecount = 2;
//			 for(var k = 0; k < req.data.length; k++){
//				//上下两个对象日期不是同一年，且上一个不是一月或下一个不是12月
//				 if(k+1 != req.data.length && (req.data)[k].cath_date_str.substring(0,4) != (req.data)[k+1].cath_date_str.substring(0,4) && 
//						 ((req.data)[k].cath_date_str.substring(5) != "01" || (req.data)[k+1].cath_date_str.substring(5) != "12")){
//					 var mon1 = parseInt((req.data)[k].cath_date_str.substring(5))-1;
//					 for(var num = mon1; num > 0; num--){
//						 if(num.toString().length == 1){
//							 str += "<li class='limonth'><a>" + (req.data)[k].cath_date_str.substring(0,4) + "年0" + num + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }else{
//							 str += "<li class='limonth'><a>" + (req.data)[k].cath_date_str.substring(0,4) + "年" + num + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }
//					 }
//					 var mon2 = parseInt((req.data)[k+1].cath_date_str.substring(5));
//					 for(var nub = 12; nub > mon2; nub--){
//						 if(nub.toString().length == 1){
//							 str += "<li class='limonth'><a>" + (req.data)[k+1].cath_date_str.substring(0,4) + "年0" + nub + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }else{
//							 str += "<li class='limonth'><a>" + (req.data)[k+1].cath_date_str.substring(0,4) + "年" + nub + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }
//					 }
//				 }
////				 alert("ok2");
////				 alert(str);
//				 //同一年份但是月份间有间隔
//				 if(k+1 != req.data.length && (req.data)[k].cath_date_str.substring(0,4) == (req.data)[k+1].cath_date_str.substring(0,4) && 
//						 (req.data)[k].cath_date_str.substring(5) != (req.data)[k+1].cath_date_str.substring(5)){
//					 var mon3 = parseInt((req.data)[k].cath_date_str.substring(5))-1;
//					 for(var ner = mon3; ner > parseInt((req.data)[k+1].cath_date_str.substring(5)); ner--){
//						 if(ner.toString().length == 1){
//							 str += "<li class='limonth'><a>" + (req.data)[k].cath_date_str.substring(0,4) + "年0" + ner + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }else{
//							 str += "<li class='limonth'><a>" + (req.data)[k].cath_date_str.substring(0,4) + "年" + ner + "月" + "<span class='badge'></span></a></li>";
//							 datecount++;
//						 }
//					 }
//				 }
//				 if(k+1 != req.data.length && datecount < 10){
//					 str += "<li class='limonth'><a>" + (req.data)[k+1].cath_date_str.replace("-","年") + "月" + "<span class='badge'></span></a></li>";
//					 datecount++;
//				 }
//			 }
//			 //日期导航栏不够十个
//			 if(datecount < 10){
//				 var lastarry = (req.data).slice(req.data.length-1);//取最后一个日期对象
//				 for(var k2 = 0; k2 < lastarry.length; k2++){
//					 var lastmon = parseInt(lastarry[k2].cath_date_str.substring(5))-1;
//					 var lastyer = parseInt(lastarry[k2].cath_date_str.substring(0,4));
//					 for(var knum = (10-datecount); knum > 0; knum--){
//						 if(lastmon == 0){
//							 lastmon = 12;
//							 lastyer = lastyer -1;
//							 str += "<li class='limonth'><a>" + lastyer + "年" + lastmon + "月" + "<span class='badge'></span></a></li>";
//						 }else{
//							 if(lastmon.toString().length == 1){
//								 str += "<li class='limonth'><a>" + lastyer + "年0" + lastmon + "月" + "<span class='badge'></span></a></li>";
//							 }else{
//								 str += "<li class='limonth'><a>" + lastyer + "年" + lastmon + "月" + "<span class='badge'></span></a></li>";
//							 }
//						 }
//						 lastmon--;
//					 }
//				 }
//			 }
////			 alert(str);
//			 $("#ulmonth").html(str);
//		}
//	});

	var view = $('#calendar').fullCalendar('getView');
	$(".left_btns ul li a").each(function(){
		if($(this).text().trim().substring(0,8) == view.title.trim()){
			$(this).closest('.limonth').addClass('bg_color');
		}
	  });

	count();
	
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
    
    //左侧的日期导航栏的项选中与否随头部的上一月按钮变化而变化
    $('.fc-button-prev span').click(function(){
    	var view = $('#calendar').fullCalendar('getView');
    	var title = view.title.trim();
    	var cath_date = "";
    	if(title.substring(5,7) == "01"){
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
        	
        	//函数里放个点击事件，不知可否？
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
    
  //左侧的日期导航栏的项选中与否随头部的下一月按钮变化而变化
    $('.fc-button-next span').click(function(){
    	var view = $('#calendar').fullCalendar('getView');
    	var title = view.title.trim();
    	var cath_date = "";
    	if(title.substring(5,7) == "12"){
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
    
  //左侧的日期导航栏的项选中与否随头部的今天按钮变化而变化
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
    
    //函数里放个点击事件，不知可否？
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
		url:ctx+'/teachweb/countCathedraPlan.action',
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
//干什么用的?视图用?
function main() {
    layer.open({
        title:'讲座详情',
        type: 2,
        content: 'lecture.html',
        area: ['1000px', '500px']
    });
}
//服务器时间
function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}