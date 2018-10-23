var importpup="";
$(function() {
	 stuScoInit();
	
});

/**
 * 导出学生成绩模板
 */
function downStuscoExcel() {
		$("#exportForm").submit();
	}


function uploadStuscoExcel(){
	 importpup=mypopdiv(1,"上传学生成绩文件","400px","200px",(pHeight-200)/2,(pWidth-400)/2,'Y',"<center><span style='display:inline-block;'><strong>请选择文件:</strong></span><input style='display:inline-block;' name='file' type='file' id='file' /><br><br><br><a	class='btn btn-info btnSearch' onclick='uploadStusco()'>上传</a></center>");
 }


function uploadStusco(){
 	var fileName = $("#file").val();
 	if(fileName==""){
 		myalert_error('请选择上传excel文档！','43%','43%');
 		return false;
 	}
// 	var laSh = layer.load(0,{shade:[0.5,'#fff']}); //遮罩效果
 	var laSh = layer.msg('上传中，请稍候', {
 		  icon: 16,
 		  shade: [0.5,'#fff'],
 		  skin: 'layui-layer-molv'
 		});
 	$.ajaxFileUpload({
         url: ctx+'/scoreweb/uploadStoscoInfo.action', 
         type: 'post',
         secureuri: false,
         fileElementId: 'file', // 上传文件的id、name属性名
         dataType: 'json',
         success: function(res){
        	layer.close(laSh);
         	if(res.data=="上传成功"){
         		orgaInit();
 	            myalert_success(res.data,'43%','43%');
         	}else{
         		orgaInit();
         		var strs1=new Array();
         		strs1=res.data.split(";");
//         		var a="<center><h3>导入失败的行数及原因:</h3>";
//         		for(var i=0;i<strs1.length;i++){
//         			a+=strs1[i];
//         			a+="<br>";
//         		}
//         		a+="</center>";
         		var content1='<div style="width:100%;height:100%;"><table cellspacing="0" style="text-align: center; width:100%"><tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 35px;"><th style="color:#1C9BCD;font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;line-height: 23px;">导入失败的行数及原因</th></tr>';
		  	 	 for ( var int = 0; int < strs1.length; int++) {
		  	 		  content1+='<tr style="width: 100%;margin: 0px 2px; padding: 2px;line-height: 30px;"><td style="font-size: 14px;border-bottom: 1px solid #ddd;text-align: center;">'+strs1[int]+'</td></tr>'; 			  	 		  
					}
		  	 	 content1+='</table></div>';
         		importpup=mypopdiv(1,"上传错误","400px","300px",(pHeight-300)/2,(pWidth-400)/2,'Y',content1);
         		orgaInit() ;
         	}
         },
         error: function(data){ 
         	myalert_error("上传异常！");
         }
     });
 	closeMyLoading(importpup);
}


/**
 * 学生成绩信息初始化表
 * 
 */
function stuScoInit(){ 
	 $("#stuScoInfoGrid").jqGrid(
		      {
		url:ctx+'/scoreweb/getStuscoList.action',
		datatype:"json",
		colNames:['','学生姓名','学生工号','内科','外科','妇儿科','内科（补考）','外科（补考）','妇儿科（补考）','总考核'],
		colModel:[
		          {name:'id',index:'id',hidden:true,key:true},
		          {name:'stu_name',index:'stu_name',align:'center',sortable:false},
		          {name:'user_code',index:'user_code',align:'center',sortable:false},
		          {name:'medicalSco',index:'medicalSco',align:'center',sortable:false},
		          {name:'surgicalSco',index:'surgicalSco',align:'center',sortable:false},
		          {name:'especiallySco',index:'especiallySco',align:'center',sortable:false},
		          {name:'medicalScos',index:'medicalScos',align:'center',sortable:false},
		          {name:'surgicalScos',index:'surgicalScos',align:'center',sortable:false},
		          {name:'especiallyScos',index:'especiallyScos',align:'center',sortable:false},
		          {name:'finalSco',index:'finalSco',align:'center'}
		          ],
		          rowNum : 20,
		          rowList : [ 20, 50, 100],
		          pager : '#informationToolbar',
		          viewrecords : true, 
		          width:pWidth-40,
		          height:pHeight-170,
		          resizable:false,
		  		modal:true,
		  		sortname:"id",
		  		sortorder:"desc",
		  		mtype:'POST' , 
		  		postData: {
		  	        }  
	 });
};

//重新加载
function orgaInit(){
	$("#stuScoInfoGrid").jqGrid('setGridParam',{
        datatype: 'json',
        postData: {
           
        }, //发送数据
        page: 1
    }).trigger("reloadGrid"); //重新载入
}
//模糊查询

function queryStuSco()
{
var postData={
		stu_name:$('#stu_name').val(),
};	
$("#stuScoInfoGrid").jqGrid('setGridParam',{
    datatype:'json',
    postData: postData, //发送数据
    page: 1
}).trigger("reloadGrid"); //重新载入


}














