var num=0;

var ftc_id="";
var stuTypeId="";
var orga_id="";
var roleId="";
$(function() {
	 $("#namestr").autocomplete({
		 source: function(request, response ) { 
    	 $.ajax({
    	        url: ctx+'/basicdataweb/selectFormInfo.action',
    	        dataType: "json",
    	        type:'post',
    	        data:{
    	        	namestr: request.term,
    	        	state:"Y"
    	        },
    	        success: function( data ) {
    	        data = eval(data.data);
    	   	      if(null != data){
    	          var arr =  new Array();
    	          for(var i=0;i<data.length; i++){
    	        	  arr[i] = {     
    	        			  key : data[i].id,    //form表单的id 
    	                      type_id : data[i].type_id,      //form表单的类型
    	                      value : data[i].name      //名字
    	                  };  
    	          }
    	         response(arr);
    	   	    }
              }   
    	 }); 
     },
     select: function( event, ui ) {
         // event 是当前事件对象
    	 $("#type_id").val(ui.item.type_id);
         $("#from_id").val(ui.item.key);
         // ui对象仅有一个item属性，它表示当前被选中的菜单项对应的数据源对象
         // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
     }
});
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	ftc_id = Request['ftc_id'];     //  教学模板配置id
	stuTypeId = Request['stuTypeId'];     //学生类型 Id
	orga_id = Request['orga_id']; 	//科室id
	roleId = Request['roleId']; 	//角色id
	if(orga_id == null || orga_id == undefined || orga_id == ''){    //统一绑定   根据角色		
		$("#roleName").removeAttr("style");
		num=1;
	}else{
		num=0;
		var url=ctx+'/basicdataweb/selectTSR.action';
		 var postData={
				 ftc_id:ftc_id,
				 stuTypeId:stuTypeId,
				 orga_id:orga_id,
				 roleId:roleId
		 };
		 doAjax(url,postData,2,function(res){  		
   			 for(var i =0;i<res.data.length;i++){
   				 var formStr="<span><a ref='' onclick='reForm0(this);'>"+ res.data[i].name +"</a>&nbsp;&nbsp;";
   				 if(roleId == '10' || roleId == '20;30'){
   					 if(res.data[i].visual_flag == null || res.data[i].visual_flag == -1){
   						 formStr+="<a title='不可查看' href='javascript:void(0)' onclick='changeVisual(this)'><span style='cursor:pointer' class='glyphicon glyphicon-eye-close visual'><input class='hid_visual_flag' type='hidden' value='-1' /></span></a>";
   					 }else if(res.data[i].visual_flag == 1){
   						 formStr+="<a title='可查看' href='javascript:void(0)' onclick='changeVisual(this)'><span style='cursor:pointer' class='glyphicon glyphicon-eye-open visual'><input class='hid_visual_flag' type='hidden' value='1' /></span></a>";
   					 }
   				 }
   				formStr+="<a style='font-weight:bold;' href='javascript:void(0)' onclick='reForm(this)'>"+
  				 "<input name='from_id'  type='hidden'value="+res.data[i].id+" /><img src='img/x.png'/></a></span>";
   				$("#form_content").append(formStr);
   			 }
   		 });
	}
});
//
function addForm() {
    var value = $("#namestr").val();
    var form_id = $("#from_id").val();
    $("#namestr").val("");
    if(form_id==""||form_id==null){
	     parent.nullreturn();
	    return false;
    }
    var formStr="<span><a href='#' onclick='reForm0(this);' item="+value+">"+ value + "</a>&nbsp;&nbsp;";
    if(roleId == '10' || roleId == '20;30'){
    	formStr+="<a title='不可查看' href='javascript:void(0)' onclick='changeVisual(this)'><span style='cursor:pointer' class='glyphicon glyphicon-eye-close visual'><input class='hid_visual_flag' type='hidden' value='-1' /></span></a>";
    }
    formStr+="<a style='font-weight:bold;' href='javascript:void(0)' onclick='reForm(this)'>"+
    "<input name='from_id'  type='hidden'value="+form_id+"  /><img src='img/x.png'/></a></span>";
    $("#form_content").append(formStr);
}
//
function reForm0(res){
	var name =$(res).attr("item");
	 parent.reForm(name);
}
//
function reForm(target){
	$(target).parent().remove();
}
//切换可见不可见
function changeVisual(target){
	var visual_flag = $(target).children(".visual").children(".hid_visual_flag").val();
	var content = "";
	if(visual_flag == 1){
		if(roleId == '10'){
			content = "是否要将该表单配置为带教老师/教学秘书不可查看？";
		}else if(roleId == '20;30'){
			content = "是否要将该表单配置为学生不可查看？";
		}
	}else if(visual_flag == -1){
		if(roleId == '10'){
			content = "是否要将该表单配置为带教老师/教学秘书可查看？";
		}else if(roleId == '20;30'){
			content = "是否要将该表单配置为学生可查看？";
		}
	}
	myConfirm(content, "20%","30%",function(){
		if(visual_flag == 1){
			$(target).children(".visual").removeClass('glyphicon-eye-open'); 
			$(target).children(".visual").addClass('glyphicon-eye-close'); 
			$(target).children(".visual").children(".hid_visual_flag").val(-1);
		}else if(visual_flag == -1){
			$(target).children(".visual").removeClass('glyphicon-eye-close'); 
			$(target).children(".visual").addClass('glyphicon-eye-open');
			$(target).children(".visual").children(".hid_visual_flag").val(1);
		}
		layer.closeAll();
	});
}
//
function submitProcess(){
	check_val = [];    //角色id
	if(num==1){
	 obj = document.getElementsByName("role");
	   
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	    if(check_val== null || check_val == undefined || check_val == ''){
	    	myalert_error('请选择角色！', '30%', '40%');
	    	return false;
	    }
	}
	
	
	//表单的ID
	var type_ids="";
	var inputs = $("input[name='from_id']");

	for (var i = 0, j = inputs.length; i < j; i++){
		var formid=inputs[i].value;
		for (var n =i+1, m = inputs.length; n < m; n++){
			  if(inputs[n].value==formid){
				myalert_error('不能添加重复的表单！', '30%', '40%');
			    return false;  
			  }
		}
    };
    $("input[name='from_id']").each(function(){
    	if(roleId == '10' || roleId == '20;30'){
			var visualFlag = $(this).parent("a").prev("a").children(".visual").children(".hid_visual_flag").val();
			if(type_ids==""){
				type_ids = $(this).val()+'_'+visualFlag;
			}else{
				type_ids += ";"+($(this).val()+'_'+visualFlag);
			}
		}else{
			if(type_ids==""){
				type_ids = $(this).val();
			}else{
				type_ids += (";"+$(this).val());
			}
		}
    });
    if(type_ids==null||type_ids==undefined || type_ids==''){
    	myalert_error('请添加表单！', '30%', '40%');
    	return false;
    }
	var url=ctx+'/basicdataweb/updateTSR.action';
	 var postData={
			 ftc_id:ftc_id,
			 stuTypeId:stuTypeId,
			 orga_id:orga_id,
			 roleId:roleId,
			 form_id:type_ids,
			 check_val:JSON.stringify(check_val)
	 };
	 doAjax(url,postData,2,function(res){
		 parent.add(name,orga_id,stuTypeId,res);     
		 });
}
