var authArr=new Array();
$(function(){
	//检索
	$("#exactInput").autocomplete({ source: function( request, response ) {
        $.ajax({
            url: ctx+'/messageweb/selectNameList.action',
            dataType: "json",
            data:{
            	username: request.term
            },
            success: function( data ) {
            data = eval(data.rows);
       	      if(null != data){
              var arr =  new Array();
              for(var i=0;i<data.length; i++){
            	  arr[i]={
            			  key:data[i].auth_id,
            			  value:data[i].user_name
            	  }; 
              }
              response(arr);
            }}
        });
    },
    minLength: 1,
//  autoFill :true,
  select: function(event,ui) {
  	$("#exactInput").html(ui.item.value);
  	$("#authId").val(ui.item.key);
  	
  }});

	//加载学生类型
	$.ajax({
		type : 'POST',
		url : ctx + '/messageweb/getRoleType.action',
		dataType : 'json',
		success : function(res) {
		var a1="<br/>";
		for ( var int = 0; int < res.stuTypelist.length; int++) {
			a1+= "<input name='userName' type='checkbox' value=" + res.stuTypelist[int].id
					+ "><span>" + res.stuTypelist[int].type_name+"</span>";
		};
		a1+="<br/>非学生类型：";
		for ( var int = 0; int < res.roleLists.length; int++) {
			a1+= "<br><input name='userName' type='checkbox' value=" + res.roleLists[int].role_id
					+ "><span>" + res.roleLists[int].role_name+"</span>";
		}
		$("#stu").append(a1);
		}
	});
	
	
	

	var url=ctx+'/messageweb/getOrga.action';
	var postData={};
	//ztree
	doAjax(url,postData,2,function(res){
		var a1="";
		for ( var int = 0; int < res.data.length; int++) {
		a1+= "<input name='orga' type='checkbox' value=" + res.data[int].orga_id
		+ "><span>" + res.data[int].orga_name+"</span><br/>";
		}
		$("#orga").append(a1);
	});
	

	
	
	
	
    //tab
    $('#tit p').click(function () {
        var i = $(this).index();//下标第一种写法
        $(this).addClass('select').siblings().removeClass('select');
        $('#con>li').eq(i).show().siblings().hide();
    });
});
//复选框全选事件
function cli(Obj) {
    var collid = document.getElementById("all");
    var coll = document.getElementsByName(Obj);
    if (collid.checked) {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = true;
    } else {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = false;
    }
}
//增加div内内容
function addUser(){
            var value1 = $("#exactInput").val();
            var authid=$("#authId").val();
            authArr.push({key:authid, value:value1});
            var formStr="<span><a ref=''>"+ value1 + "</a>&nbsp;<a style='font-weight:bold;' href='javascript:void(0)' onclick='reForm(this)'></a></span>";
            $("#form_content").append(formStr);
        };

function reForm(target){
    $(target).parent().remove();
}
//置空
function resetDan(){
	 $("#form_content").html("");
	 $("#exactInput").val("");
}

//保存
function saveDan(){
	parent.addName(authArr,3);
}

//下拉框内容
var list = ['张三','李四','王二麻子'];

$('[name="nameInput"]').autocomplete({
    source: list
});

function onCheck(e, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("tree"),
            nodes = treeObj.getCheckedNodes(true),
            v = "";
    for (var i = 0; i < nodes.length; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    var cityObj = $("#deInput");
    cityObj.attr("value", v);
}
//向父页面传值
$("#deKeep").click(function(){
    var content=$("#deInput").val();
    parent.close(content);
});

//保存某类人物
function addClass(){
	 $("input[name='userName']:checked").each(function(){
		 authArr.push({key:$(this).val(),value:$(this).next().html()});
	 });
	 parent.addName(authArr,1);
}

//重置某类人物
function resetClass(){
	 $("input[name='userName']:checked").each(function(){
		 $(this).attr("checked",false);
	 });
}



//保存科室
function addOrga(){
	 $("input[name='orga']:checked").each(function(){
	 authArr.push({key:$(this).val(),value:$(this).next().html()});
});
parent.addName(authArr,2);
}


//重置科室
function resetOrga(){
	 $("input[name='orga']:checked").each(function(){
		 $(this).attr("checked",false);
	 });
}


