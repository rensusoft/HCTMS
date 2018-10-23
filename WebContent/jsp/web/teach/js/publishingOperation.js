var num='';
var a_id='';
var p_state='';
var b_id='';
var stu_num='';
$(function () {
    //页面加载完成之后执行
    pageInit();
    $('.left_btns li').click(function() {
        var i = $(this).index();//下标第一种写法
        $(this).addClass('select').siblings().removeClass('select');
        $('#con li').eq(i).show().siblings().hide();
    });
    $(".checkBtn").click(
        function(){
            $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
        }
    );
    $(".leftBtn").click(
        function(){
            $(this).parent().addClass("displayDiv").siblings().removeClass("displayDiv");
        }
    );
    $("#stuList").on("click","li",function(){
    	$(this).addClass("badgec").siblings().removeClass('badgec');
    	var _id=$(this).children("[class='_id']").text();
    	a_id=_id;
        var url=ctx+'/teachweb/getStuTaskById.action';    	
    	var postData={
    			id:_id
    	};
    	doAjax(url,postData,2,function(res){
    		var str='';
    			str+=
                 '<ul id="con">'+
                     '<li class="show_block">'+
                         '<div class="form_cont dtlBox">'+
                             '<div class="delTitle">'+
                                 '<div class="hr"></div>'+
                                 '<h5>作业内容</h5>'+
                                 '<a class="toggleBtn"><img src="img/arrowT.png"/>收起</a>'+
                             '</div>'+
                             '<div class="rejectCont">'+
                                 '<p id="contentTea">'+res.data.content_teach+'</p></div>'+
                            ' <div class="delTitle">'+
                                 '<div class="hr"></div>'+
                                 '<h5>学生回答</h5>'+
                                 '<a class="toggleBtn"><img src="img/arrowT.png"/>收起</a></div>'+
                             '<div class="rejectCont">'+
    			               '<p id="content">'+res.data.content+'</p></div>'+
                             '<div class="delTitle">'+
                                 '<div class="hr"></div>'+
                                 '<h5>其他</h5>'+
                                 '<a class="toggleBtn"><img src="img/arrowT.png"/>收起</a> </div>'+
                             '<div class="rejectCont" style="margin-bottom:50px;">'+
                                ' 评分：<input type="text" class="form-control" style="display: inline-block;width:150px;" id="sco_teach"><p id="contents_teach">';
    			for(var i=0;i<res.data.contents.length;i++){
    				str+='<i><input type="checkbox" name="contents" value='+res.data.contents[i].content+'>'+res.data.contents[i].content+'</i>';
    			}
    			        str+= '</p>'+
                                 '其他：<input type="text" class="form-control" style="display: inline-block;width:90%;" id="appraise_teach"> </div> </div>'+
                         '<div class="btns" style="text-align: center">'+
                             '<a class="layui-layer-btn0" onclick="saveSco();">保存</a></div></li></ul>';
    		$("#wrap").html(str);
    		$(".btns").width($("#con").width());
    		$(".toggleBtn").click(
    		        function(e){
    		            $(this).parents(".delTitle").next(".rejectCont").toggleClass("extend");
    		            var closeText=$(this).text();
    		            //console.log(closeText);
    		            if(closeText=='收起'){
    		                $(this).html("<img src='img/arrowB.png'/>展开");
    		            }else{
    		                $(this).html("<img src='img/arrowT.png'/>收起");
    		            }
    		        }
    		    );
    	});
    });
    $(".addNew").click(
    		
        function(){
            layer.open({
                type: 2,
                title:"发布作业",
                content: 'homeworkCont.jsp',
                area: ['1020px', (pHeight-80) + 'px']
            });
        });
//    
//    $(".toggleBtn").click(
//        function(e){
//            $(this).parents(".delTitle").next(".rejectCont").toggleClass("extend");
//            var closeText=$(this).text();
//            //console.log(closeText);
//            if(closeText=='收起'){
//                $(this).html("<img src='img/arrowB.png'/>展开");
//            }else{
//                $(this).html("<img src='img/arrowT.png'/>收起");
//            }
//        }
//    );
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#rotateRule").jqGrid(
        {
            url: ctx+'/teachweb/getTaskPub.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','标题', '类型', '状态','', '发布时间','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', align: "center",key:true,hidden:true},
                {name: 'title', index: 'title', align: "center"},
                {name: 'task_type_name', index: 'task_type_name',width: 100,  align: "center"},
                {name: 'progress_stateStr', index: 'progress_stateStr', width: 100, align: "center"},
                {name: 'progress_state', index: 'progress_state', align: "center",hidden:true},
                {name: 'publish_timeStr', index: 'publish_timeSr', width: 130, align: "center"},
                {name: 'operation', index: 'operation', width: 120, align: "center",sortable: false}
            ],
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            //sortname: 'id',//初始化的时候排序的字段
            //sortorder: "asc",//排序方式,可选desc,asc
            mtype: "post",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            loadonce:true, //一次加载全部数据到客户端，由客户端进行排序。
			sortable: true,
			sortname: 'id', //设置默认的排序列
			sortorder : "asc",// 排序方式,可选desc,asc
            postData: {
            	state: "Y"
            },
            width: pWidth-49,
            height:pHeight-120,
            gridComplete: function () {
                var ids = jQuery("#rotateRule").jqGrid("getDataIDs");
                
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var progress_state = jQuery("#rotateRule").jqGrid('getRowData',id).progress_state;
                    var ee = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',upd('" + id + "','" + progress_state + "'));\">修改</a>&nbsp;&nbsp;&nbsp;";
                    var se = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',corrre('" + id + "','" + progress_state + "'));\">批改</a>&nbsp;&nbsp;&nbsp;";
                    var re = "<a title='' style='color: #505f91;cursor: pointer'" + "onclick=\"$('#rotateRule').jqGrid('saveRow','" + id + "',del('" + id + "','" + progress_state + "'));\">删除</a>";
                    jQuery("#rotateRule").jqGrid("setRowData", id, {operation: ee+se+re});
                }
            }
        });
    jQuery("#rotateRule").jqGrid('navGrid', '#pager1', {edit: false, add: true, del: true});
}


//批改
function corrre(id,progress_state){
	if(progress_state =="1"){
		myalert_error('此作业学生暂未解答！');
		return false;
	}
	p_state=progress_state;
	b_id=id;
    $(".addDiv").removeClass("displayDiv");
    $(".jqDiv").addClass("displayDiv");
     var url=ctx+'/teachweb/getStuWithTask.action';
	 var postData={
			 id:id
	 };
	 doAjax(url,postData,2,function(res){
			var stu = "";
			stu_num=res.data.length;
			for ( var i = 0; i < res.data.length; i++) {
				stu+="<li><a class='stuName'>"+res.data[i].stuName+"</a>" +
				"<span style='display:none;' class='_stuauthid' >"+res.data[i].receiver_auth_id+"</span>" +
				"<span style='display:none;' class='_id' >"+res.data[i].id+"</span>" +
				"</li>";
		}
			 if(stu_num==0){
				 var str='<span class="glyphicon glyphicon-exclamation-sign" style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">已完成提交作业的学生的批改</span>';
				 $("#wrap").html(str);
			 }else{
				 var str='<span class="glyphicon glyphicon-exclamation-sign" style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">请选择左侧学生</span>';
				 $("#wrap").html(str);
			 }
			 $("#stuList").html(stu);
	 }); 
	
}

//删除发布的作业
function del(id,progress_state) {
	if(id == ''){
		myalert_error('请选择作业！');
		return false;
	}
	if(progress_state =="2"){
		myalert_error('此作业已被处理，不可作删除！');
		return false;
	}
	if(progress_state=="3"){
		myalert_error('此作业已评分，不可作删除！');
		return false;
	}
	myConfirm("是否要删除？","","", function(index) {
		  var url=ctx+'/teachweb/delTask.action';
		  var postData={
				  id:id,
				  state:'X'
		  };
		  doAjax(url,postData,2,function(res){
				 if(res.success == true){
					 myalert_success(res.data);
					 orgaInit();
				 }else{
					 myalert_error(res.data);
				 }
			 });
	});
}
//修改发布的作业
function upd(id,progress_state){
	if(id == ''){
		myalert_error('请选择作业！');
		return false;
	}
	if(progress_state =="2"){
		myalert_error('此作业已被处理，不可作修改！');
		return false;
	}
	if(progress_state=="3"){
		myalert_error('此作业已评分，不可作修改！');
		return false;
	}
	mypopdiv(2,"作业修改",'1020px',(pHeight-80) + 'px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx + "/jsp/web/teach/homeworkContEdit.jsp?id="+id);
	}

function orgaInit(){
	$("#rotateRule").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
        	state: "Y"
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}

//搜索
function keywordSearch() {
	   var task_type_code=$("#task_type_code").val();
	   var progress_state=$("#progress_state").val(); 
	   var start_date= $("#start_date").val();
	   var end_date= $("#end_date").val();
	   $("#rotateRule").jqGrid('setGridParam',{
	        datatype: 'json',
	        postData: {
	        	start_date: start_date,
	        	end_date: end_date,
	        	task_type_code:task_type_code,
	        	progress_state:progress_state,
	        	state: "Y",
	        }, //发送数据
	        page: 1
	    }).trigger("reloadGrid"); //重新载入
}
function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			layer.closeAll();
		});
	}else{
		myalert_error(res.data,'','');
	}	
}

//保存老师评分！
function saveSco() {
	var sco=$("#sco_teach").val();
	var contentTea=$("#appraise_teach").val();
	if($("#sco_teach").val()!==''){
		if(isNaN($("#sco_teach").val())){
			myMsg("分数只能输入数字!",'50%','50%');
			return false;
		}else{
			if($("#sco_teach").val()>100||$("#sco_teach").val()<0){
				myMsg("分数只能是百分制!",'50%','50%');
				return false;
			}
		}
	}else{
		myMsg("请输入评分分数!",'50%','50%');
		return false;
	}
	var contents = "";
	$("[name='contents']:checked").each(function(i){
		if(0==i){
			contents = $(this).val();
		       }else{
		    	   contents += ("；"+$(this).val());
		       }
	});
	
	if((contents == null || content == '') && (contentTea == null || contentTea == '')){
		myalert_error("请添加评价内容!","30%","40%");
		return false;
	}
	
	if(contents != null && contents != ''){
		if(contentTea != null && contentTea  != ''){
			contents += "；" + contentTea ;
		}
	}else{
		if(contentTea!= null && contentTea!= ''){
			contents = contentTea ;
		}
	}
    var url=ctx+'/teachweb/saveScoTeach.action';    	
	var postData={
			id:a_id,
			content:contents,
			sco:sco
	};
	doAjax(url,postData,2,function(res){
		 if(res.success == true){
			 myalert_success(res.data);
			 corrre(b_id,p_state);
			 if(stu_num==1){
				 var str='<span class="glyphicon glyphicon-exclamation-sign" style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">已完成提交作业的学生的批改</span>';
				 $("#wrap").html(str);
			 }else{
				 var str='<span class="glyphicon glyphicon-exclamation-sign" style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">请选择左侧学生</span>';
				 $("#wrap").html(str);
			 }
			 clear();
			 orgaInit();
		 }else{
			 myalert_error(res.data);
		 }
	});
	
}

//清空
function clear(){
	$('input[type=text]').each(function(){
		$(this).val("");
	});
	$("#contentTea").text("");
	$("#content").text("");
	$("#contents_teach").text("");
};













