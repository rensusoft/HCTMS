var id='';
var type_id='';
$(function () {
	id = $("#span_id").text();
	type_id = $("#span_type_id").text();
});
//对比查看
function comparison(){
	mypopdiv(2,"对比结果",'800px',(pHeight-200)+'px','50px',(pWidth-1000)/2,'N',ctx+'/jsp/web/teach/mdComparisons.jsp?id='+id+'&type_id='+type_id);
}
