var _progress_state=0;
var choosePeo;
$(function () {
	$("#allNews").attr("class","show_block");
	
	getCount();
	$("#messageLei").html("未读消息");
//    页面加载完成之后执行
	pageInit();

    $('#tit p').click(function() {
            $(this).addClass('select').siblings().removeClass('select');
        });
    $("#wrap").width(pWidth-177);
    $("#all_news,#my_news").setGridWidth(pWidth-200);
    $(".hide_btn").click(
            function () {
                $(".left_btns").toggle();
                if ($(".left_btns").is(":visible")) {
                    $("#wrap").width(pWidth-177);
                    $(".left_btns").fadeIn();
                    $("#all_news,#my_news").setGridWidth(pWidth-200);
                } else {
                    $(".left_btns").fadeOut();
                    $("#wrap").width(pWidth-50);
                    $("#all_news,#my_news").setGridWidth(pWidth-70);
                }
            }
        );
    $(".left_btns ul li").click(
    		function(){
    			$(this).addClass("checked");
    			$(this).siblings().removeClass("checked");
    		}
    );
});


function getCount(){
	var url=ctx+'/messageweb/getAllConunt.action';
	var postData={progress_state:_progress_state};
	doAjax(url,postData,2,function successfun(res){
//		$("#generalNews").html(res.generalNewsCount);
//		$("#systemMessage").html(res.systemMessageCount);
//		<span class="badge" id="generalNews"></span> 
//		<span class="badge" id="systemMessage"></span>
		$("#unread").html(res.unreadCount);
		$("#roleId").val(res.roleId);
	});
}



function pageInit() {
    //创建jqGrid组件
    //表1
	jQuery("#all_news").jqGrid(
        {
            url: ctx+'/messageweb/getAllNews.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','标题', '日期','发送人', '操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id',  align: "center",key:true,hidden:true},
                {name: 'title', index: 'title', width:30, align: "center"},
                {name: 'sendTimeStr', index: 'sendTimeStr', width:30, align: "center"},
                {name: 'sendName', index: 'sendName', width:30, align: "center"},
                {name: 'act', index: 'act', width: 30, align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            width: pWidth-100,
            height: pHeight-168,
            viewrecords: true,
            multiselect: false,   //去掉复选框
            autowidth: false,
            shrinkToFit: true,
            postData: {
    			progress_state:_progress_state,
    			type_id:null},
            gridComplete: function () {
                var ids = jQuery("#all_news").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var rr = "<a style='color:#505f91' onclick='lookById("+id+")'>查看</a>";
                    jQuery("#all_news").jqGrid("setRowData", id, {act: rr});
                }
            }
        });
    $("#all_news").jqGrid().navGrid("#pager1", {edit : false,add : false,del : false,search : false,refresh : false}).navButtonAdd(
			"#pager1", {caption: "全部已读", buttonicon:"ui-icon-folder-open", onClickButton: readAllNews, position: "first"});
    $("#all_news").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });   //强制删除滚动条
}
//全部已读
function readAllNews(){
	if($("#unread").text() == 0){
		myMsg("暂无未读消息！","40%","45%");
		return false;
	}
	myConfirm("确定全部已读？",'(pHeight/2)px','',
			function success(){
		var url=ctx + "/messageweb/readAllNews.action";
		var postData={
				
		};
		doAjax(url,postData,2,function(req){
			if(req.success == true){
				getCount();
				$("#messageLei").html("未读消息");
				$("#myNews").attr("class","");
				$("#allNews").attr("class","show_block");
				$("#li_AllMessage").attr("class","");
				$("#li_ReadMessage").attr("class","");
				$("#li_UnreadMessage").attr("class","checked");
				$("#all_news").jqGrid('setGridParam', {
					datatype: 'json',
					page:1,
					postData: {
						type_id:null,
						progress_state:0
					}, //发送数据
				}).trigger("reloadGrid"); //重新载入
				$("#allMessage").addClass('select').siblings().removeClass('select');
				myalert_success(req.data,"30%","40%");
			}else{
				myalert_error(req.data,"30%","40%");
			}
		});
	});
}

function add() {
	var roleId=$("#roleId").val();
	if(pHeight<500){
		mySelfpopdiv("message",2,"信息",'1000px','440px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx+"/jsp/web/message/news_cont.jsp?id="+roleId);
		
	}else{
		mySelfpopdiv("message",2,"信息",'1000px','520px',(pHeight-500)/2,(pWidth-1000)/2,'N',ctx+"/jsp/web/message/news_cont.jsp?id="+roleId);
	}

    	
    }


//获取我发出的消息信息
function selectMessage(){
	$("#allNews").attr("class","");
	$("#myNews").attr("class","show_block");	
    $("#my_news").jqGrid(
        {
            url: ctx+'/messageweb/getMyNews.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['','标题内容', '日期','操作'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name:'id',index:'id', align: "center",key:true,hidden:true},
                {name: 'title', index: 'title', width: 70, align: "center"},
                {name: 'send_time_str', index: 'send_time_str', width: 35, align: "center"},
                {name: 'act', index: 'act', width: 35, align: "center"}
            ],
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: '#pager4',//表格页脚的占位符(一般是div)的id
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get            
            width: pWidth-185,
            height: pHeight-168,
            viewrecords: true,
            multiselect: true,
            autowidth: false,
            shrinkToFit: true,
            gridComplete: function () {
                var ids = jQuery("#my_news").jqGrid("getDataIDs");
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var re = "<a style='color:#505f91' onclick='lookMyNew("+id+")'>查看</a>&nbsp;&nbsp;<a style='color:#505f91' onclick='deleteByid("+id+")'>删除</a>";
                    jQuery("#my_news").jqGrid("setRowData", id, {act: re});
                }
            }
        });
    jQuery("#my_news").jqGrid('navGrid', '#pager4', {edit: false, add: false, del: false,refresh:false,search:false});
    $("#my_news").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });  
}


//点击全部消息
function selectAllMessage(){  
	$("#messageLei").html("全部消息");
	_progress_state="";
	var postData = {
        type_id:null,
        progress_state:_progress_state
};
	
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData: postData, //发送数据
    }).trigger("reloadGrid"); //重新载入
	$("#allMessage").addClass('select').siblings().removeClass('select');
};


//点普通消息
function selectGeneralNews(){
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	   var postData = {
               type_id:1,
               progress_state:_progress_state
           };
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData: postData, //发送数据
    }).trigger("reloadGrid"); //重新载入
}



//查看系统消息
function selectSystemMessage(){
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	   var postData = {
               type_id:2,
               progress_state:_progress_state
           };
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData: postData, //发送数据
    }).trigger("reloadGrid"); //重新载入
}


function lookById(id){   //消息查看
	var height=0;
	if(pHeight>500){
		height=650;
	}else{
		height=pHeight;
	}
	mypopdiv(2,"消息查看",'1000px',(height-50)+'px','0px',(pWidth-1000)/2,'Y',ctx + "/jsp/web/message/check.jsp?id="+id,function sccess(){
		if(_progress_state==0){
		getCount();	
		};
		$("#all_news").jqGrid('setGridParam', {
	        datatype: 'json',
	        page:1,
//	        postData: data, //发送数据
	    }).trigger("reloadGrid"); //重新载入
	});
}


//删除我发出的消息
function deleteByid(id){
	myConfirm("是否删除消息？",'(pHeight/2)px','',
			function success(){ 
	var url=ctx+"/messageweb/deleteMyMessageByid.action";
	var postData={
			id:id
	};
	doAjax(url,postData,2,function success(res){
		if(res.success==true){
			myalert_success(res.data);
		}else{
			myalert_error(res.data);	
		}
	});
	getCount();
	roladMyNews();
	layer.closeAll();
	});
}

//刷新我的消息
function roladMyNews(){
$("#my_news").jqGrid('setGridParam', {
    datatype: 'json',
    page:1
}).trigger("reloadGrid"); //重新载入
	
}


function closeMyWindows(res){
	if(res.success==true){
		myalert_success(res.data,'','',function(){
			roladMyNews();
			layer.closeAll();
		});
	}else{
		myalert_error(res.data,'','');
	}	}

//查看我发起的消息
function lookMyNew(id){
	var height=0;
	if(pHeight>550){
		height=550;
	}else{
		height=pHeight;
	}
	mypopdiv(2,"消息查看",'1000px',(height-50)+'px','0px',(pWidth-1000)/2,'Y',ctx + "/jsp/web/message/myNew.jsp?id="+id);
}


//查询所有未读消息
function selectUnreadMessage(){
	$("#messageLei").html("未读消息");
	_progress_state=0;
	var data={
			progress_state:_progress_state,
			type_id:null};
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData: data, //发送数据
    }).trigger("reloadGrid"); //重新载入
	$("#allMessage").addClass('select').siblings().removeClass('select');
}

//查询所有已经读过的消息
function selectReadMessage(){
	$("#messageLei").html("已读消息");
	_progress_state=1;
	var data={
			progress_state:_progress_state,
			type_id:null};
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData: data, //发送数据
    }).trigger("reloadGrid"); //重新载入
//	getCount();
	$("#allMessage").addClass('select').siblings().removeClass('select');
}

//小的全部消息
function selectLittle(){
	$("#myNews").attr("class","");
	$("#allNews").attr("class","show_block");
	$("#all_news").jqGrid('setGridParam', {
        datatype: 'json',
        page:1,
        postData:{
        	progress_state:_progress_state,
        	type_id:null
        }, //发送数据
    }).trigger("reloadGrid"); //重新载入
}


function addPeo(senderAuthIds) {
	var roleId=$("#roleId").val();
	var values="-1";  //用来保存  已经选择好的复选框的value
	if(senderAuthIds!=""&&senderAuthIds!=null){
		values="";
		for ( var i = 0; i < senderAuthIds.length; i++) {
			values+=senderAuthIds[i]+",";
		}	
	}
	if(roleId==10){  //学生
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values));
	}else if(roleId==20){   //带教老师
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values));
	}else if(roleId==30){    //教学秘书
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values));
	}else if(roleId==40){   //科主任
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values));
	}else if(roleId==50){   //临床教学处
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values)); 
	}else if(roleId==99){   //系统管理员
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values)); 
	}else if(roleId==60){   //学校管理员
		choosePeo=mySelfpopdiv("choosePeo",2,"接收人选择",'440px',(pHeight-80)+'px',(pHeight-600)/2,(pWidth-600)/2,'N',ctx + "/jsp/web/message/peoChooseOther.jsp?values="+encodeURI(values)); 
	}
} 

function addNamesInput(tossText,senderAuthIds,leixing){
	 var frameId=document.getElementById('message').getElementsByTagName("iframe")[0].id;
	    $('#'+frameId)[0].contentWindow.addNamesInput(tossText,senderAuthIds,leixing);
	    layer.close(choosePeo);
}




function mySelfpopdiv(id,type,title,width,length,verticalNum,horizontalNum,shadeCloseFlag,content,endFun){
	if(id==null||id==""){
		id = -1;
	}
	if(type==null||type==""){
		type = 1;
	}
	if(title==null||title==""){
		title = false;
	}
	if(length==null||length==""){
		length = "400px";
	}
	if(width==null||width==""){
		width = "250px";
	}
	
	if(shadeCloseFlag==null||shadeCloseFlag==""){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="Y"){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="N"){
		shadeCloseFlag = false;
	}
	if(content==null||content==""){
		content = "无内容...";
	}
	var myLayer = layer.open({
		id: id,
		type: type,
		skin: 'layui-layer-lan',
		shade:[0.5,'#fff'],
		title:title,
		area: [width,length],
		//offset: [verticalNum,horizontalNum],
	    shadeClose: shadeCloseFlag, //点击遮罩关闭
	    content: content,
	    end:endFun
	});
	return myLayer;
}


