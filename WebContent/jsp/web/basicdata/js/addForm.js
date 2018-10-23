$(function() {

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
	var end_proc_id=""; 
	end_proc_id = Request['end_proc_id']; 
	//ID不为空则是修改申请
	if(end_proc_id!="" && end_proc_id!=null){
        $("#end_proc_id").val(end_proc_id);
		var url=ctx+'/basicdataweb/selectProcessRole.action';
		 var postData={
				 end_proc_id:end_proc_id,
				state:"Y"
		 };
		 doAjax(url,postData,2,function(res){
			 $("#type_code").val(res.data[0].type_code);
			 $("#rpr_id").val(res.data[0].ids);
			 if(res.data[0].type_code=="VACATE"){
				 var str='<td><i>*&nbsp;</i>最终审核权的天数：</td>'+
					 '<td><input id="require_datenum" type="text" /></td>';
				 $("#fristDiv").html(str); 
				 $("#require_datenum").val(res.data[0].require_datenum); 
				 $("#divHide").hide(); 
			 }else{
				 var str = '<td>表单选择：</td>'+
		            '<td>'+
	                '<input id="namestr" type="text" style="width: 200px"/>'+
	                '<input id="type_id" type="hidden" />'+
	                '<input id="from_id" type="hidden" />'+
	                '<a class="btn btn-info btnSearch" id="addForm" onclick="addForm();">添加</a></td>';
				 $("#fristDiv").html(str); 
				 
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
			    	        			  key : data[i].related_id,     
			    	                      type_id : data[i].type_id,     
			    	                      value : data[i].name  
			    	                  };  
			    	          }
			    	         response(arr);
			    	   	    }
			              }   
			    	 }); 
			     },
			     select: function( event, ui ) {
			         // event 是当前事件对象
			    	 $("#type_id").val(ui.item.key);
			         $("#from_id").val(ui.item.type_id);
			         // ui对象仅有一个item属性，它表示当前被选中的菜单项对应的数据源对象
			         // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
			     }
			});
			 }
             $("#proc_name").val(res.data[0].proc_name);
             var val = res.data[0].role_ids.split(",");
             var boxes = document.getElementsByName("role");
             for(var i=0;i<boxes.length;i++){
                 for(var j=0;j<val.length;j++){
                     if(boxes[i].value == val[j]){
                         boxes[i].checked = true;
                         continue;
                     }
                 }
             }
             var url=ctx+'/basicdataweb/formRelation.action';
    		 var postData={
    				 end_proc_id:end_proc_id,
    				state:"Y"
    		 };
    		 doAjax(url,postData,2,function(res){
    			 for(var i =0;i<res.data.length;i++){
    				 var formStr="<span><a ref='' onclick='reForm0(this);'>"+ res.data[i].name + "</a>&nbsp;<a style='font-weight:bold;' href='javascript:void(0)' onclick='reForm(this)'>"+
    				 "<input name='type_id'  type='hidden'value="+res.data[i].type_id+"  /><input name='from_type'  type='hidden'value="+res.data[i].form_type+" /><img src='img/x.png'/></a></span>";
    				    $("#form_content").append(formStr);
    			 }
    		 });
		 });
	}
});

function addForm() {
    var value = $("#namestr").val();
    var type_id = $("#type_id").val();
    var from_type = $("#from_id").val();
    var inputs = $("input[name='type_id']");
    var inputss = $("input[name='from_type']");
    $("#namestr").val("");
    if(type_id==""||type_id==null){
	     parent.nullreturn();
	    return false;
    }
	for (var i = 0, j = inputs.length; i < j; i++){
		if(type_id==inputs[i].value&&from_type==inputss[i].value){
		}
    };
    var formStr="<span><a href='#' onclick='reForm0(this);' item="+value+">"+ value + "</a>&nbsp;<a style='font-weight:bold;' href='javascript:void(0)' onclick='reForm(this)'>"+
    "<input name='type_id'  type='hidden'value="+type_id+"  /><input name='from_type'  type='hidden'value="+from_type+"  /><img src='img/x.png'/></a></span>";
    $("#form_content").append(formStr);
}
function reForm0(res){
	var name =$(res).attr("item");
	 parent.reForm(name);
}
function reForm(target){
	$(target).parent().remove();
}

function submitProcess(){
	//表单的ID
	var type_ids="";
	var inputs = $("input[name='type_id']");
	for (var i = 0, j = inputs.length; i < j; i++){
		 if(0==i){
			 type_ids = inputs[i].value;
		       }else{
		    	   type_ids += (","+inputs[i].value);
		       }
    };
    var from_types="";
    var inputss = $("input[name='from_type']");
    for (var i = 0, j = inputss.length; i < j; i++){
    	if(0==i){
    		from_types = inputss[i].value;
    	}else{
    		from_types += (","+inputss[i].value);
    	}
    };
	var role_ids = "";
	$("[name='role']:checked").each(function(i){
		 if(0==i){
			 role_ids = $(this).val();
		       }else{
		    	   role_ids += (","+$(this).val());
		       }
    });
	if(role_ids==""||role_ids==null||$("#proc_name").val()==""||$("#proc_name").val()==null){
		 parent.submitProcess();
		 return false;
	}
	var require_datenum=$("#require_datenum").val();
	if($("#type_code").val()=="VACATE"){
	    if(require_datenum==""||require_datenum==null){
		   myalert_error("请输入正确的天数！");
		   return false;
	}
	    if(!/^[+-]?\d*\.?\d*$/.test(require_datenum)){
	    	myalert_error("请输入正确的天数！");
	    	  return false;
    	}
	}
	var url=ctx+'/basicdataweb/updateProcessState.action';
	 var postData={
			 proc_name:$("#proc_name").val(),
			 end_proc_id:$("#end_proc_id").val(),
			 type_code:$("#type_code").val(),
			 rpr_id:$("#rpr_id").val(),
			 state:"Y",
			 role_ids:role_ids,
			 type_ids:type_ids,
			 require_datenum:require_datenum,
			 from_types:from_types
	 };
	 doAjax(url,postData,2,function(res){
			 parent.closeMyWindows(res);
	 });
}
