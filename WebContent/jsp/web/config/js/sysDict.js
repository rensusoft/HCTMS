var type="";
$(function () {
    //页面加载完成之后执行
    pageInit();
    
	//右击菜单触发事件
	$('#SysDictMainList').contextMenu('myMenu', {
		bindings: {
	          'updateDictMain': function(){
	        	  updateDictMain();
	          },
	          'delectDictMain': function(){
	        	  delectDictMain();
	          }
		}
	});
	$('#SysDictSubList').contextMenu('myMenu', {
		bindings: {
			'updateDictMain': function(){
				updateDictSub();
			},
			'delectDictMain': function(){
				delectDictSub();
			}
		}
	});  
});
function pageInit() {
    //创建jqGrid组件
    jQuery("#SysDictMainList").jqGrid(
        {
            url: ctx+'/configweb/selectDictMainList.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['项目代码', '项目名称', '拼音码', '排序码', '描述', '其他代码'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'item_code', index: 'item_code', align: "center",key:true},
                {name: 'item_name', index: 'item_name', align: "center"},
                {name: 'py_code', index: 'py_code', align: "center"},
                {name: 'ordinal', index: 'ordinal', align: "center"},
                {name: 'item_describe', index: 'item_describe', align: "center"},
                {name: 'other_code', index: 'other_code', align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少条
            pager: '#pager1',//表格页脚的占位符(一般是div)的id
            sortname: 'ident',//初始化的时候排序的字段
            sortorder: "desc",//排序方式,可选desc,asc
            mtype: "post",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            postData: {
            	state: "Y",
            	availability:"1"
            }, 
            width: pWidth/2-50,
            height: pHeight-100,
            shrinkToFit: true,
            onSelectRow: function (rowid) {
                var rowDatas = $("#SysDictMainList").jqGrid('getRowData', rowid);
                var item_code = rowDatas['item_code'];
                type="";
        		$("#item_code_span").text("");
                $("#sup_code").val(item_code);
                $("#item_code_old").val(item_code);
                $("#item_code1").val(item_code);
                $("#item_name1").val(rowDatas['item_name']);
                $("#item_code").val(rowDatas['item_code']);
    			$("#item_name").val(rowDatas['item_name']);
    			$("#py_code").val(rowDatas['py_code']);
    			$("#ordinal").val(rowDatas['ordinal']);
    			$("#item_describe").val(rowDatas['item_describe']);
    			$("#other_code").val(rowDatas['other_code']);
                var postData = {
                	item_code: item_code,
                	state: "Y"
                };
                $("#SysDictSubList").jqGrid('setGridParam', {
                    datatype: 'json',
                    postData: postData, //发送数据
                    page: 1
                }).trigger("reloadGrid");
            }

        });
    /*创建jqGrid的操作按钮容器*/
    /*可以控制界面上增删改查的按钮是否显示*/
    jQuery("#SysDictMainList").jqGrid('navGrid', '#pager1', {edit: false, add: false, del: false,search:false});
    jQuery("#SysDictSubList").jqGrid(
        {
            url:  ctx+'/configweb/selectDictSubList.action',//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['主表项目代码', '项目类型说明', '项目类型名称 ', '排序码'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'sup_code', index: 'sup_code', align: "center"},
                {name: 'item_type_code', index: 'item_type_code', align: "center",key:true},
                {name: 'item_type_name', index: 'item_type_name', align: "center"},
                {name: 'ordinal', index: 'ordinal', align: "center"}
            ],
            rowNum: 20,//一页显示多少条
            rowList: [20, 50, 100],//可供用户选择一页显示多少49
            pager: '#pager2',//表格页脚的占位符(一般是div)的id
            sortname: 'type',//初始化的时候排序的字段
            sortorder: "desc",//排序方式,可选desc,asc
            mtype: "get",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            width: pWidth/2-50,
            height: pHeight-100,
            shrinkToFit: true,
            onSelectRow: function (rowid) {
                var rowDatas = $("#SysDictSubList").jqGrid('getRowData', rowid);
                var item_type_code_old = rowDatas['item_type_code'];
                $("#item_type_code_old").val(item_type_code_old);
                $("#item_type_code").val(rowDatas["item_type_code"]);
                $("#item_type_name").val(rowDatas["item_type_name"]);
                $("#ordinal2").val(rowDatas["ordinal"]);
            }
        });
    jQuery("#SysDictSubList").jqGrid('navGrid', '#pager2', {edit: false, add: false, del: false,search:false});
}

//新增系统字典

function updateDictMain(){
	var id = jQuery("#SysDictMainList").jqGrid('getGridParam', 'selrow');
	if(id==""){
		myMsg('请选择项目！', '40%', '45%', 3000);
		return false;	
	}
	$(".table_body").toggle();
	  if ($(".table_body").is(":visible")) {
		  $("#div_height").css("height", pHeight/2-100);
          $("#SysDictMainList").setGridHeight(pHeight/2-100);
          $(".table_body").fadeIn();
          $(".table_head a").html("项目修改<span><img src='<ctx>/img/select_xlf.png'/></span>");
          $(".table_head").css({
              "border-bottom-left-radius": "0",
              "border-bottom-right-radius": "0"
          });
          $(".table_head a span img").attr( {src:ctx+"/img/select_xlf.png"});
          $("#item_code").focus();
      }else {
          $(".table_body").fadeOut();
          $("#SysDictMainList").setGridHeight(pHeight-100);
          $(".table_head").css({
              "border-bottom-left-radius": "3px",
              "border-bottom-right-radius": "3px"
          });
          $(".table_head a").html("项目新增<span><img src='<ctx>/img/select_xlf.png'/></span>");
          $(".table_head a span img").attr( {src:ctx+"/img/select_xl.png"});
      }
}
function updateDictSub(){
	var id = jQuery("#SysDictSubList").jqGrid('getGridParam', 'selrow');
	if(id==""){
		myMsg('请选择项目明细！', '40%', '45%', 3000);
		return false;	
	}
	$(".table_body2").toggle();
	if($(".table_body2").is(":visible")){
        $(".table_head2").css({
            "border-bottom-left-radius": "0",
            "border-bottom-right-radius": "0"
        });
        $(".table_head2 a").html("项目明细修改<span><img src='<ctx>/img/select_xlf.png'/></span>");
        $("#div_height2").css("height", pHeight/2-100);
        $("#SysDictSubList").setGridHeight(pHeight/2-100);
        $(".table_head2 a span img").attr( {src:ctx+"/img/select_xlf.png"});
        $("#item_type_code").focus();
    }else{
        $(".table_head2").css({
            "border-bottom-left-radius": "3px",
            "border-bottom-right-radius": "3px"
        });
        $("#SysDictSubList").setGridHeight(pHeight-100);
        $(".table_head2 a").html("项目明细新增<span><img src='<ctx>/img/select_xlf.png'/></span>");
        $(".table_head2 a span img").attr( {src:ctx+"/img/select_xl.png"});
    }
}

function delectDictMain(){
	 var url= ctx+'/configweb/addSysDictMain.action';
	 var postData={
			 item_code_old:$("#item_code_old").val(),
			 ordinal:$("#ordinal").val(),
			 state:"X"
	 };
	 myConfirm("是否要删除？","30%","40%",
				function(index){
			$.ajax({
		 		type: 'POST',
			     	url :  url,
			    	dataType: 'json',
			    	data:postData,
			  	    async: false,
			        success:function(data) {
			        	myalert_success('操作成功！', '30%', '40%',function(){
			        		layer.closeAll();
			        		reloadGrid();
			        	});
			        }
			});
				},
				function(){
					layer.closeAll();
				}
			);
}
function delectDictSub(){
	 var postData={
			 item_type_code_old:$("#item_type_code_old").val(),
			 sup_code:$("#sup_code").val(),
			 item_type_code:$("#item_type_code").val(),
	         item_type_name:$("#item_type_name").val(),
			 ordinal:$("#ordinal2").val(),
			 state:"X"
	 };
		myConfirm("是否要删除？","30%","40%",
				function(index){
			$.ajax({
		 		type: 'POST',
			     	url :  ctx+'/configweb/addSysDictSub.action',
			    	dataType: 'json',
			    	data:postData,
			  	    async: false,
			        success:function(data) {
			        	myalert_success('操作成功！', '30%', '40%',function(){
			        		layer.closeAll();
			        		reloadGrid();
			        	});
			        }
			});
				},
				function(){
					layer.closeAll();
				}
			);
}
function item_code_onblur(){
	$("#item_code_span").text("");
	$("#buttonSysDictMain").attr("disabled", false); 
	if(type==1){
	 var postData={
			 item_code:$("#item_code").val(),
			 state:"Y",
	 };
	 $.ajax({
	 		type: 'POST',
		     	url :  ctx+'/configweb/selectSysDictmainById.action',
		    	dataType: 'json',
		    	data:postData,
		  	    async: false,
		        success:function(data) {
		        	if(data.item_code!=null&&data.item_code!=""){
		        		$("#item_code_span").text("*项目代码已存在");
		        		$("#buttonSysDictMain").attr("disabled", true); 
		        	}
			           $("#item_code").val(data.item_code);
			      	   $("#item_name").val(data.item_name);
			    	   $("#py_code").val(data.py_code);
			    	   $("#ordinal").val(data.ordinal);
			    	   $("#item_describe").val(data.item_describe);
			           $("#other_code").val(data.other_code);
		        }
		});
		
	}
}
function submitSysDictMain(){
	if($("#item_code").val()==""||$("#item_name").val()==""){
		return false;
	}
		 var url= ctx+'/configweb/addSysDictMain.action';
		 var postData={
				 item_code:$("#item_code").val(),
				 item_code_old:$("#item_code_old").val(),
				 item_name:$("#item_name").val(),
				 py_code:$("#py_code").val(),
				 ordinal:$("#ordinal").val(),
				 item_describe:$("#item_describe").val(),
				 other_code:$("#other_code").val(),
				 state:"Y"
		 };
		 doAjax(url,postData,1);
//		 $.post({
//			     	url :  url,
//			    	dataType: 'json',
//			    	data:postData,
//			  	    async:false,
//			        success:function(data) {
//			        myalert_success('操作成功！', '30%', '40%',function(){
//		        		layer.closeAll();
//		        		reloadGrid();
//		        	});
//			        }
//			});
		}
function submitSysDictSub(){
	if($("#item_type_code").val()==""||$("#item_type_name").val()==""){
 		return false;
 	}
			 var url= ctx+'/configweb/addSysDictSub.action';
			 var postData={
					 item_type_code_old:$("#item_type_code_old").val(),
					 sup_code:$("#sup_code").val(),
					 item_type_code:$("#item_type_code").val(),
			         item_type_name:$("#item_type_name").val(),
					 ordinal:$("#ordinal2").val(),
					 state:"Y"
			 };
			 $.ajax({
			 		type: 'POST',
				     	url :  url,
				    	dataType: 'json',
				    	data:postData,
				  	    async: false,
				        success:function(data) {
//				        	myalert_success('操作成功！', '30%', '40%',function(){
				        		layer.closeAll();
				        		reloadGrid();
//				        	});
							}
				});
}

function reloadGrid() {
    $("#SysDictMainList").jqGrid('setGridParam', {
        datatype: 'json',
        page: 1
    }).trigger("reloadGrid"); //重新载入
    $("#SysDictSubList").jqGrid('setGridParam', {
    	datatype: 'json',
    	page: 1
    }).trigger("reloadGrid"); //重新载入
}