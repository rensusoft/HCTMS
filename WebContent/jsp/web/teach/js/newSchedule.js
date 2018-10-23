$(function(){
	//检索？？？？？？？
	$("#speaker_name").autocomplete({ source: function( request, response ) {
        $.ajax({
            url: ctx+'/teachweb/selectNameList.action',
            type:'post',
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
  	$("#speaker_name").html(ui.item.value);
  	$("#speaker").val(ui.item.key);
  	
  }});
	//点击获得的日期
	function GetRequest() { //取URL参数
		var url = window.location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);//unescape--》decodeURI防止参数乱码 
				} 
			} 
		return theRequest; 
		} 
	var Request = new Object(); 
	Request = GetRequest(); 
	var day=""; 
	day = Request['day'];
	$(".clickDay").text(day);//设置日期的值
	//拼接届次复选框
	$.ajax({
		url:ctx+'/teachweb/stuClassCheckbox.action',
		async:false,
		data:{},
		dataType: 'json',
		success:function(res) {
			 var str = "<input name='stuClass' type='checkbox' value='' id='all1' onclick='cli();'> 全选: ";
			 for(var i =0;i<res.data.length;i++){
				str+="<input name='stuClass1' type='checkbox' value='" + res.data[i].stu_class + "' onclick='clickOne1();' />" + res.data[i].stu_class;
			 }
			 $("#stu_class").html(str);
		}
	});
	//拼接学生类型复选框
	$.ajax({
		url:ctx+'/teachweb/stuTypeCheckbox.action',
		async:false,
		data:{},
		dataType: 'json',
		success:function(res) {
			var str = "<input name='stuType' type='checkbox' value='' id='all2' onclick='cli2();'> 全选: ";
			 for(var i =0;i<res.data.length;i++){
				str+="<input name='stuType1' type='checkbox' value='" + res.data[i].type_name + "' onclick='clickOne2();' />" + res.data[i].type_name;
			 }
			 $("#stu_type").html(str);
		}
	});
	 	//实例化编辑器
	 	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		ue = UE.getEditor('content',{scaleEnabled:true});
		
		var id = "";
		id = Request['id'];
		$.ajax({//编辑前拿数据，为输入框赋值
		  	type: 'POST',
		  	async:false,//同步
			url : ctx + '/teachweb/updateSchedule.action',
			data : {
				    id:id
				},
			dataType: 'json',
		  	success:function(data) {
				$("#flag").val(data.cathedraPlan.id);
				$("#class_condition_id").val(data.class_condition_id);
				$("#type_condition_id").val(data.type_condition_id);
				$("#cath_title").val(data.cathedraPlan.cath_title);
				if(data.cathedraPlan.speaker != null && data.cathedraPlan.speaker != -1){
					$("#speaker").val(data.cathedraPlan.speaker);
					$("#speaker_name").val(data.cathedraPlan.speaker_name);
				}else{
					$("#select_position").val("outside");
					$("#change_input").html('<input type="text" id="speaker_name_outside" placeholder="主讲人姓名"/>');
					$("#speaker_name_outside").val(data.cathedraPlan.speaker_name);
				}
				//数据库cath_date的数据的格式变化，这里就会有问题，关键数据库里类型为Integer
				var cath_date = data.cathedraPlan.cath_date.toString().substring(0,4) + "-" + data.cathedraPlan.cath_date.toString().substring(4,6)+ "-" + data.cathedraPlan.cath_date.toString().substring(6);
				$("#cath_date").text(cath_date);
				if(new Date(data.cathedraPlan.cath_time).getMinutes() == 0){
					$("#cath_time").val(new Date(data.cathedraPlan.cath_time).getHours() + ":00");
				}else{
					$("#cath_time").val(new Date(data.cathedraPlan.cath_time).getHours() + ":" + new Date(data.cathedraPlan.cath_time).getMinutes());
				}
				$("#cath_place").val(data.cathedraPlan.cath_place);
				//判断ueditor 编辑器是否创建成功
				 ue.addListener("ready", function () {
				    	// editor准备好之后才可以使用
				    	ue.setContent(data.cathedraPlan.cath_content);
				 });
				var stuClass_ids = data.class_condition_value;
				var stuClass_ids_arr = stuClass_ids.split(",");
				var stuClasscoll = $("input[name='stuClass1']");
				for (var i = 0; i < stuClasscoll.length; i++) {
					for(var j = 0; j < stuClass_ids_arr.length; j++) {
						if(stuClasscoll[i].value == stuClass_ids_arr[j]){
							stuClasscoll[i].checked = true;
						}
					} 
				}
				var stuType_ids = data.type_condition_value;
				var stuType_ids_arr = stuType_ids.split(",");
				var stuTypecoll1 = $("input[name='stuType1']");
				for (var l = 0; l < stuTypecoll1.length; l++) {
					for(var m = 0; m < stuType_ids_arr.length; m++) {
						if(stuTypecoll1[l].value == stuType_ids_arr[m]){
							stuTypecoll1[l].checked = true;
						}
					} 
				}
		 }
	});
		//隐藏按钮    
		var flag = $("#flag").val();
		if(flag == null || flag == ""){//添加日程去掉删除按钮
			$("#btn_delete").css("display","none");
		}else{//编辑时，现在之前的日程不可编辑
			var time = $("#cath_date").text() + " " + $("#cath_time").val() + ":00";
			var str = time.toString();
			str = str.replace(/-/g,"/");
			if(new Date(str) <= getServerDate()){
				$("#but_save").css("display","none");
				$("#but_reset").css("display","none");
				$("#btn_delete").css("display","none");
			}
		}
		
	});
//届次全选
function cli() {
    var collid = document.getElementById("all1");
    var coll = $("input[name='stuClass1']");
    if (collid.checked) {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = true;
    } else {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = false;
    }
    
}
function clickOne1() {
    var collid = document.getElementById("all1");
    var coll = $("input[name='stuClass1']");	
    var f=true;
      for(var i=0;i<coll.length;i++){
    if(coll[i].checked!=f){
    	collid.checked=false;
   break;
 }
    collid.checked=f;
 }
}
//学生类型全选
function cli2() {
    var collid2 = document.getElementById("all2");
    var coll = $("input[name='stuType1']");
    if (collid2.checked) {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = true;
    } else {
        for (var i = 0; i < coll.length; i++)
            coll[i].checked = false;
    }
    for (var i = 0; i < coll.length; i++){
    if(coll[i].checked){
    	collid2.checked=fasle;
    }
    }
    
}
function clickOne2() {
    var collid = document.getElementById("all2");
    var coll = $("input[name='stuType1']");	
    var f=true;
      for(var i=0;i<coll.length;i++){
    if(coll[i].checked!=f){
    	collid.checked=false;
   break;
 }
    collid.checked=f;
 }
}
//保存
function saveCathedraPlan(){
	var speaker = "";
	var speaker_name = "";
	var flag = $("#flag").val();
	var cath_title = $("#cath_title").val();
	var select_position = $("#select_position").val();
	if(select_position == "inside"){
		speaker = $("#speaker").val();
		speaker_name = $("#speaker_name").val();
		if(speaker_name.indexOf("[") != -1){
			speaker_name = speaker_name.substring(0,speaker_name.indexOf("["));
		}
	}else if(select_position == "outside"){
		speaker = null;
		speaker_name = $("#speaker_name_outside").val();
	}
	var cath_date = $("#cath_date").text();
	var cath_time = $("#cath_time").val();
	var cath_place = $("#cath_place").val();
	var stuClass_ids = "";
	$("[name='stuClass1']:checked").each(function(i){
		if(0==i){
			stuClass_ids = $(this).val();
		       }else{
		    	   stuClass_ids += (","+$(this).val());
		       }
	});
	var stuType_ids = "";
	$("[name='stuType1']:checked").each(function(i){
		 if(0==i){
			 stuType_ids = $(this).val();
		       }else{
		    	   stuType_ids += (","+$(this).val());
		       }
    });
	var content = UE.getEditor('content').getContent();
	var class_condition_id = $("#class_condition_id").val();
	var type_condition_id = $("#type_condition_id").val();
	if(cath_title == null || cath_title == ""){
		myalert_error("标题不能为空!","30%","40%");
		return false;
	}
	if(select_position == "inside"){
		if(speaker == null || speaker == "" || speaker_name == null || speaker_name == ""){
			myalert_error("主讲人不能为空!","30%","40%");
			return false;
		}
	}else if(select_position == "outside"){
		if(speaker_name == null || speaker_name == ""){
			myalert_error("主讲人不能为空!","30%","40%");
			return false;
		}
	}
	if(cath_time == null || cath_time == ""){
		myalert_error("讲座时间不能为空!","30%","40%");
		return false;
	}else{
		cath_time = cath_date + " " + cath_time + ":00";
		var str = cath_time.toString();
		str = str.replace(/-/g,"/");
		if(new Date(str) <= new Date()){
			myalert_error("讲座时间不能早于当前时间!","30%","40%");
			return false;
		}
		var cath_time_str = str.replace(/\//g,"-");
	}
	if(cath_place == null || cath_place == ""){
		myalert_error("讲座地点不能为空!","30%","40%");
		return false;
	}
	if(stuClass_ids == null || stuClass_ids == ""){
		myalert_error("届次不能为空!","30%","40%");
		return false;
	}
	if(stuType_ids == null || stuType_ids == ""){
		myalert_error("学生类型不能为空!","30%","40%");
		return false;
	}
	if(content == null || content == ""){
		myalert_error("内容不能为空!","30%","40%");
		return false;
	}
	var url=ctx + "/teachweb/saveCathedraPlan.action";
	var postData = {
			 	flag:flag,
			 	cath_title:cath_title, 
			 	speaker:speaker,
			 	speaker_name:speaker_name,
			 	cath_date:cath_date,
			 	cath_time_str:cath_time_str,
			 	cath_place:cath_place,
			 	class_condition_type:1,
			 	stuClass_ids:stuClass_ids,
			 	type_condition_type:2,
			 	stuType_ids:stuType_ids,
			 	content:content,
			 	class_condition_id:class_condition_id,
			 	type_condition_id:type_condition_id
			};
	 doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
//				 alert(req.id);
				 parent.reload(req.id);
			 });
		 }
	 });
}
//重置
function reset(){
	$("#flag").val("");
	$("#cath_title").val("");
	var select_position = $("#select_position").val();
	if(select_position == "inside"){
		$("#speaker").val("");
		$("#speaker_name").val("");
	}else if(select_position == "outside"){
		$("#speaker_name_outside").val("");
	}
	$("#cath_time").val("");
	$("#cath_place").val("");
	var coll = $("input[name='stuClass']");
    for (var i = 0; i < coll.length; i++){
    	coll[i].checked = false;
    }
    var coll2 = $("input[name='stuType']");
    for (var i = 0; i < coll2.length; i++){
    	coll2[i].checked = false;
    }
	UE.getEditor('content',{scaleEnabled:true}).setContent("");
	$("#class_condition_id").val("");
	$("#type_condition_id").val("");
}
//事件id传到url 获取id
var _id="";
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    if (url.indexOf("?id") != -1) {    //判断是否有参数
        var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
        strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
    }
    var id= strs[1];        //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
    //console.log(id);
    _id=id;
}
//删除
function removeId(){
    GetRequest();
    //console.log(_id);
    myConfirm("是否要删除？","","",
			function(index){
    		var url = ctx + "/teachweb/deleteCathedraPlan.action";
			 var postData={
					id:_id,
					state:"X"
			 };
			 doAjax(url,postData,2,function(res){
				 if(res.success == true){
//					 orgaInit();
					 myalert_success(res.data);
					 parent.remove(_id);
				 }else{
					 myalert_error(res.data);
				 }
			 });
			}
		);
}
//通过选中的院内院外重置为文本框
function changeToInput(option){
	if(option.value == "outside"){
		$("#change_input").html('<input type="text" id="speaker_name_outside" placeholder="主讲人姓名"/>');
	}else if(option.value == "inside"){
		var str = '<input class="myInput" name="nameInput" placeholder="关键字查询" id="speaker_name" style="width: 200px;text-align:center;margin-right:20px;"/>' +
            '<input type="hidden" id="speaker" name="speaker" />';
		$("#change_input").html(str);
		//检索？？？？？？？
		$("#speaker_name").autocomplete({ source: function( request, response ) {
	        $.ajax({
	            url: ctx+'/teachweb/selectNameList.action',
	            type:'post',
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
	            			  key:data[i].user_id,
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
	  	$("#speaker_name").html(ui.item.value);
	  	$("#speaker").val(ui.item.key);
	  	
	  }});
	}
}
//服务器时间
function getServerDate(){
    return new Date($.ajax({async: false}).getResponseHeader("Date"));
}
