$(document).ready(function () {
	$(".start").click(
			function(){
				$(this).parents("li.ui-state-default").removeClass("opacity2");
				$(this).parents("div.step_main_p").removeClass("opacity2");
				$(this).siblings('a[name="addBut"]').attr("onclick","add(this);");//恢复编辑按钮点击事件
			}
	);
	$(".ban").click(
			function(){
				$(this).parents("li.ui-state-default").addClass("opacity2");
				$(this).parents("div.step_main_p").addClass("opacity2");
				$(this).siblings('a[name="addBut"]').removeAttr("onclick");//移除编辑按钮点击事件
			}
	);
    $(".main_content").height(parseInt($("body").height() - 60));
});
//编辑
function add(valssss) {
	var end_proc_id =$(valssss).attr("item");
	mypopdiv(2,"编辑",'585px','370px','','','N',
			ctx +'/jsp/web/basicdata/addForm.jsp?end_proc_id='+end_proc_id);
//    layer.open({
//        type: 2,
//        content: ctx +'/jsp/web/basicdata/addForm.jsp?end_proc_id='+end_proc_id,
//        area: ['500px', '310px'],
//    });
}

//禁用
function disable1(valssss){
	var end_proc_id =$(valssss).attr("item");
	var url=ctx +'/basicdataweb/updateProcessState.action';
	 var postData={
			 end_proc_id:end_proc_id,
			 type_code:$("#type_code").val(),
			state:"X"
	 };
	 doAjax(url,postData,2,function(res){
	 });
}

//启用
function enable1(valssss){
	var end_proc_id =$(valssss).attr("item");
	var url=ctx +'/basicdataweb/updateProcessState.action';
	 var postData={
			 end_proc_id:end_proc_id,
			 type_code:$("#type_code").val(),
			state:"Y"
	 };
	 doAjax(url,postData,2,function(res){
	 });
}

//关闭弹出层
function closeMyWindows(res){
	myalert_success(res.data,(pHeight-180)/2+"px",(pWidth-580)/2+"px",function(index){
		window.location.reload();
		layer.closeAll();
	});
}

function reForm(name){
	var content = '<div>1234567890</div>';
relesepup = mypopdiv(1, "表单预览", '400px', '200px', '', '', 'Y',content);
}

function addForm(){
	myalert_error("该表单已关联！！！");
}
function nullreturn(){
	myalert_error("请点击选择下拉框里提示的表单！！！");
}
function submitProcess(){
	myalert_error("流程名称和角色对象不能未空！！！");
}
