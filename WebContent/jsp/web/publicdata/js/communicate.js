var searchCount = '';
$(function() {
	selectForumsCount();
	var flag = 0;// 0：全部主题    1：自己的
	var mydata = selectForumsList(flag);
    var mydata1 = [
            {
                id: "1",
                name: "<img src='img/person.png'>",
                amount: "<a href='" + ctx + "/jsp/web/publicdata/communiContent.jsp'><i>[类型]</i>标题</a><br/><span>楼主：<i>xxx</i><i>发表时间</i>最后发表：<i>xxxx</i></span>"
            },
            {
                id: "3",
                name: "<img src='img/person.png'>",
                amount: "<a href='#'><i>[类型]</i>标题2</a><br/><span>楼主：<i>xxx</i><i>发表时间</i>最后发表：<i>xxxx</i></span>"
            },
            {
                id: "2",
                name: "<img src='img/person.png'>",
                amount: "<a href='#'><i>[类型]</i>标题3</a><br/><span>楼主：<i>xxx</i><i>发表时间</i>最后发表：<i>xxxx</i></span>"
            }
        ],
        grid = $("#list");

    grid.jqGrid({
        datatype: 'local',
        data: mydata,
        colNames: ['Inv No', 'Client A', 'amount'],
        colModel: [
            {name: 'id', index: 'id', width: 10, align: 'center', hidden: true},
            {
                name: 'name', index: 'name', width: 10, align: 'center',
            },
            {
                name: 'amount',
                index: 'amount',
                width: 140,
                sortable: false,
                align: "left",
            }
        ],
        rowNum: 20,//一页显示多少条
        rowList: [20, 30, 50],
        pager: '#pager',
        gridview: true,
        sortname: 'invdate',
        viewrecords: true,
        sortorder: 'desc',
        width: pWidth-40,
        height: pHeight-160,
        resizable:false,
		modal:true,
        gridComplete: function () {
            //②在gridComplete调用合并方法
            var gridName = "list";
            //Merger(gridName, 'name');
        }
    });
    //表头
    $(".ui-jqgrid-htable").html("");
    
    $('#all_forums').click(function () {
        $(this).removeClass('white').siblings().addClass('white');
        $("#search_str").val("");
        orgaInit(0);
    });
    
    $('#my_forums').click(function () {
        $(this).removeClass('white').siblings().addClass('white');
        $("#search_str").val("");
        orgaInit(1);
    });
    
    $(".button").click(
            function () {
                layer.open({
                    title: "发表新帖",
                    type: 2,
                    content: ctx + '/jsp/web/publicdata/writeNew.jsp?flagOfPlace=0',
                    area: ['1020px', (pHeight-50) + 'px']
                });
            }
        );
});

//获取当前发表论坛的数目
function selectForumsCount(){
	var url=ctx+'/publicdataweb/selectForumsCount.action';
    var postData = {};
    doAjax(url,postData,2,function(res){
    	$('#my_forums span').text(res.data.mineCount);
    });
}

//获取当前发表论坛的信息列表
function selectForumsList(flag){
	var forumsDataStr = '';
	var searchStr = $("#search_str").val();
	if(searchStr.length<2 && searchStr.length!=0){
		myalert_error("最少输入两个字符!","30%","40%");
		return false;
	}
	$.ajax({
		async: false,
	  	type: 'POST',
		url : ctx+'/publicdataweb/selectForumsList.action',
		data : {
			flag:flag,
    		searchStr:searchStr,
    		state:'Y'
			},
		dataType: 'json',
	  	success:function(res) {
	  		forumsDataStr += '[';
	    	for(var i = 0; i < res.data.length; i++){
	    		var sub_user_name = (res.data)[i].sub_user_name;
	    		if(sub_user_name == null){
	    			sub_user_name = "暂无";
	    		}
	    		if((res.data)[i].stu_num==null){
	    		forumsDataStr += 
	    			'{' +
	    				'"id" : '+ (res.data)[i].id + ',' +
	    				'"name" : "<img src=\'' + ctx + '/jsp/web/publicdata/img/person.png\'  >",' +
	    				'"amount" : "<a href=\'' + ctx + '/jsp/web/publicdata/communiContent.jsp?id=' + (res.data)[i].id + '\'><i>[' + (res.data)[i].item_name + ']</i>' + (res.data)[i].title + '</a><br/><br/><span>楼主：<i>' +
	    				(res.data)[i].user_name + '</i>发表时间：<i>' + (res.data)[i].publish_time_str + '</i>最后发表人：<i>' + sub_user_name + '</i></span>"' +
	    			'},';
	    		}else{
	    			forumsDataStr += 
		    			'{' +
		    				'"id" : '+ (res.data)[i].id + ',' +
		    				'"name" : "<img  src=\'' + ctx + '/ueditor(附件文件夹千万不能删)/userImg/'+(res.data)[i].stu_num+'.jpg '+'\' style=\'height:64px;width:64px\' onerror=\'defaultSrc(this);\'  >",' +
		    				'"amount" : "<a href=\'' + ctx + '/jsp/web/publicdata/communiContent.jsp?id=' + (res.data)[i].id + '\'><i>[' + (res.data)[i].item_name + ']</i>' + (res.data)[i].title + '</a><br/><br/><span>楼主：<i>' +
		    				(res.data)[i].user_name + '</i>发表时间：<i>' + (res.data)[i].publish_time_str + '</i>最后发表人：<i>' + sub_user_name + '</i></span>"' +
		    			'},';
	    		}
	    	}
	    	forumsDataStr += ']';
	    	if(forumsDataStr.indexOf("},]") != -1){
	    		forumsDataStr = forumsDataStr.replace(/},]/, "}]");
	    	}
	  	}
	});
	var forumsData = $.parseJSON(forumsDataStr);
	return forumsData;
}
function defaultSrc(src){
	src.src=ctx+'/jsp/web/publicdata/img/person.png';
}
//重新加载
function orgaInit(flag){
	var newdata = selectForumsList(flag);
	$("#list").jqGrid('clearGridData');  //清空表格
	$("#list").jqGrid('setGridParam',{  // 重新加载数据
	      datatype:'local',
	      data : newdata,   //  newdata 是符合格式要求的需要重新加载的数据 
	      page:1
	}).trigger("reloadGrid");
}

//关闭弹出窗口
function closeMyWindows(flag){
	orgaInit(flag);
	$("#all_forums").removeClass('white').siblings().addClass('white');
	selectForumsCount();
	layer.closeAll();
}

//关键字搜索
function keywordSearch(){
	orgaInit(0);
	$("#all_forums").removeClass('white').siblings().addClass('white');
}

//重置
function reset(){
	$("#search_str").val("");
	orgaInit(0);
	$("#all_forums").removeClass('white').siblings().addClass('white');
}

//$(function () {
////    $('#all_forums').click(function () {
////        $(this).removeClass('white').siblings().addClass('white');
////        selectForums(0);
////    });
////    $('#my_forums').click(function () {
////        $(this).removeClass('white').siblings().addClass('white');
////        selectForums(1);
////    });
////    $(".button,.report").click(
////        function () {
////            layer.open({
////                title: "发表新帖",
////                type: 2,
////                content: ctx + '/jsp/web/publicdata/writeNew.jsp',
////                area: ['1020px', '425px']
////            });
////        }
////    );
//    $(".fastre").click(
//        function () {
//        	alert("okkk");
//            layer.open({
//                title: "参与/回复主题",
//                type: 2,
//                content: ctx + '/jsp/web/publicdata/replyContent.jsp',
//                area: ['1000px', '425px']
//            });
//        }
//    );
//    $('#se').selectOrDie();
//    $(window).scroll(function () {
//        if($(window).scrollTop()>100){
//            $(".top").show("slow");
//        }else{
//            $(".top").hide("slow");
//        }
//    });
//})