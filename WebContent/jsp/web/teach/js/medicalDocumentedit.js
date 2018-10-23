var mam_id='';

var str1='';
var str2='';
var str3='';
var str4='';
$(function(){
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
	mam_id = Request['mam_id'];
	//
	formOpt();
	//
	$.ajax({
	  	type: 'POST',
		url : ctx + "/teachweb/updateMedicalAdvice.action",
		data : {
			    id:mam_id
			},
		dataType: 'json',
	  	success:function(res) {
	  		$("#p_name").val(res.data.p_name);
	  		$("#p_sex").val(res.data.p_sex);
	  		$("#p_age").val(res.data.p_age);
	  		$("#p_deptname").val(res.data.p_deptname);
	  		$("#p_bednum").val(res.data.p_bednum);
	  		$("#p_pid").val(res.data.p_pid);
	  		var medicalAdviceSubList = res.data.medicalAdviceSubList;
	  		var str = '<tr class="tr_title"><td>药品名称</td><td>药品规格</td><td>剂量</td><td>剂量单位</td><td>用药途径</td><td>用药频率</td><td>总量</td><td>总量单位</td></tr>';
	  		for(var i = 0; i < medicalAdviceSubList.length; i++){
	  			str += '<tr class="tr_data">' +
	                '<td><input type="text" class="longInput form-control advice_name" value="'+medicalAdviceSubList[i].advice_name+'"/></td>' +
	                '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px" value="'+medicalAdviceSubList[i].advice_spec+'"/></td>' +
	                '<td><input type="text" class="shortInput form-control advice_dose" value="'+medicalAdviceSubList[i].advice_dose+'"/></td>' +
	                '<td><select class="form-control shortInput dose_unit_code" style="width: 85px" id="dose_unit_code_'+i+'">' +
	                str1 +
	                '</select></td>' +
	                '<td><select class="form-control shortInput path_code" style="width: 105px" id="path_code_'+i+'">' +
	                str2 +
	                '</select></td>' +
	                '<td><select class="form-control shortInput frequency_code" style="width: 105px" id="frequency_code_'+i+'">' +
	                str3 +
	                '</select></td>' +
	                '<td><input type="text" class="shortInput form-control total_dose" value="'+medicalAdviceSubList[i].total_dose+'"/></td>' +
	                '<td><select class="form-control shortInput total_dose_unit_code" style="width: 85px" id="total_dose_unit_code_'+i+'">' +
	                str4 +
	                '</select></td>' +
	                '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
	            '</tr>';
	  		}
	  		$('.tableDiv table').html(str);
	  		for(var i = 0; i < medicalAdviceSubList.length; i++){
	  			$("#dose_unit_code_"+i).val(medicalAdviceSubList[i].dose_unit_code);
	  			$("#path_code_"+i).val(medicalAdviceSubList[i].path_code);
	  			$("#frequency_code_"+i).val(medicalAdviceSubList[i].frequency_code);
	  			$("#total_dose_unit_code_"+i).val(medicalAdviceSubList[i].total_dose_unit_code);
	  		}
	    }
	});
    $(".addTableBtn").click(
        function(){
            var str='<tr class="tr_data">' +
                '<td><input type="text" class="longInput form-control advice_name"/></td>' +
                '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>' +
                '<td><input type="text" class="shortInput form-control advice_dose"/></td>' +
                '<td><select class="form-control shortInput dose_unit_code" style="width: 85px">' +
                str1 +
                '</select></td>' +
                '<td><select class="form-control shortInput path_code" style="width: 105px"> ' +
                str2 +
                '</select></td>' +
                '<td><select class="form-control shortInput frequency_code" style="width: 105px">' +
                str3 +
                '</select></td>' +
                '<td><input type="text" class="shortInput form-control total_dose"/></td>' +
                '<td><select class="form-control shortInput total_dose_unit_code" style="width: 85px">' +
                str4 +
                '</select></td>' +
                '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
                '</tr>';
            $(".tableDiv table").children().append(str);
        }
    );
});
//拼接组织下拉框选项
function formOpt(){
	$.ajax({
		async: false,
	  	type: 'POST',
		url: ctx + "/teachweb/formMedicalAdviceOpt.action",
		data: {
			
		},
		dataType: 'json',
	  	success:function(res){
	  		var data = res.data;
	  		str1 = '<option value="">-请选择-</option>';
	  		str2 = '<option value="">---请选择---</option>';
	  		str3 = '<option value="">---请选择---</option>';
	  		str4 = '<option value="">-请选择-</option>';
	  		for(var i = 0; i < data.length; i++){
	  			if(data[i].sup_code == 'dose_unit_code'){
	  				str1 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'path_code'){
	  				str2 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'frequency_code'){
	  				str3 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}else if(data[i].sup_code == 'total_dose_unit_code'){
	  				str4 += 
	  					'<option value="'+data[i].item_type_code+'">'+data[i].item_type_name+'</option>';
	  			}
	  		}
	  	}
	});
}
//保存医嘱
function save(){
	var p_name = $("#p_name").val();
	var p_sex = $("#p_sex").val();
	var p_age = $("#p_age").val();
	var p_deptname = $("#p_deptname").val();
	var p_bednum = $("#p_bednum").val();
	var p_pid = $("#p_pid").val();
	if(p_name == ''){
		myalert_error("姓名不能为空！");
		return false;
	}
	if(checkStr(p_name,2)==false){
		myalert_error("姓名不能含有特殊字符！");
		return false;
	}
	if(p_sex == ''){
		myalert_error("性别不能为空！");
		return;
	}
	if(checkStr(p_sex,2)==false){
		myalert_error("性别不能含有特殊字符！");
		return ;
	}
	if(p_age == ''){
		myalert_error("年龄不能为空！");
		return;
	}
	if(checkStr(p_age,1)==false){
		myalert_error("年龄格式输入错误，请输入正整数！");
		return;
	}
	if(p_deptname == ''){
		myalert_error("科室不能为空！");
		return;
	}
	if(checkStr(p_deptname,2)==false){
		myalert_error("科室不能含有特殊字符！");
		return ;
	}
	if(p_bednum == ''){
		myalert_error("床号不能为空！");
		return;
	}
	if(checkStr(p_bednum,2)==false){
		myalert_error("床号不能含有特殊字符！");
		return ;
	}
	if(p_pid == ''){
		myalert_error("住院号不能为空！");
		return;
	}
	if(checkStr(p_pid,2)==false){
		myalert_error("住院号不能含有特殊字符！");
		return ;
	}
	var medicalAdviceSubList = [];
	var i = 0;
	var flag = false;
	var error = "";
	$(".tr_data").each(function(){
		i++;
		var medicalAdviceSub = {};
		var advice_name = $(this).find(".advice_name").val();
		if(advice_name == ''){
			error = "医嘱明细第" + i + "行的药品名称不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_name = advice_name;
		var advice_spec = $(this).find(".advice_spec").val();
		if(advice_spec == ''){
			error = "医嘱明细第" + i + "行的药品规格不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_spec = advice_spec;
		var advice_dose = $(this).find(".advice_dose").val();
		if(advice_dose == ''){
			error = "医嘱明细第" + i + "行的剂量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(advice_dose)){
			error = "医嘱明细第" + i + "行的剂量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_dose = advice_dose;
		var dose_unit_code = $(this).find(".dose_unit_code").val();
		if(dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的剂量单位不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.dose_unit_code = dose_unit_code;
		var path_code = $(this).find(".path_code").val();
		if(path_code == ''){
			error = "医嘱明细第" + i + "行的用药途径不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.path_code = path_code;
		var frequency_code = $(this).find(".frequency_code").val();
		if(frequency_code == ''){
			error = "医嘱明细第" + i + "行的用药频率不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.frequency_code = frequency_code;
		var total_dose = $(this).find(".total_dose").val();
		if(total_dose == ''){
			error = "医嘱明细第" + i + "行的总量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(total_dose)){
			error = "医嘱明细第" + i + "行的总量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.total_dose = total_dose;
		var total_dose_unit_code = $(this).find(".total_dose_unit_code").val();
		if(total_dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的总量单位不能为空！";
			flag = true;
			return false;//  跳出循环
		}
		medicalAdviceSub.total_dose_unit_code = total_dose_unit_code;
		medicalAdviceSubList.push(medicalAdviceSub);
	});
	if(flag){
		myalert_error(error,"30%","40%");
		return;
	}
	var url=ctx + "/teachweb/saveMedicalAdvice.action?action=edit";
	var postData = {
				id:mam_id,
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
			 	rowData:JSON.stringify(medicalAdviceSubList)
			};
	doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
				 window.location.href = ctx + "/jsp/web/teach/medicalDocument.jsp";
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}
//重置医嘱
function reset(){
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	var str=
	'<tr class="tr_title"><td>药品名称</td><td>药品规格</td><td>剂量</td><td>剂量单位</td><td>用药途径</td><td>用药频率</td><td>总量</td><td>总量单位</td></tr>' +
	'<tr class="tr_data">' +
    '<td><input type="text" class="longInput form-control advice_name"/></td>' +
    '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>' +
    '<td><input type="text" class="shortInput form-control advice_dose"/></td>' +
    '<td><select class="form-control shortInput dose_unit_code" style="width: 85px">' +
    str1 +
    '</select></td>' +
    '<td><select class="form-control shortInput path_code" style="width: 105px"> ' +
    str2 +
    '</select></td>' +
    '<td><select class="form-control shortInput frequency_code" style="width: 105px">' +
    str3 +
    '</select></td>' +
    '<td><input type="text" class="shortInput form-control total_dose"/></td>' +
    '<td><select class="form-control shortInput total_dose_unit_code" style="width: 85px">' +
    str4 +
    '</select></td>' +
    '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
    '</tr>';
	$(".tableDiv table").html("");
	$(".tableDiv table").html(str);
} 


//保存老师批改后的医嘱
function saveTea(){
	var p_name = $("#p_name").val();
	var p_sex = $("#p_sex").val();
	var p_age = $("#p_age").val();
	var p_deptname = $("#p_deptname").val();
	var p_bednum = $("#p_bednum").val();
	var p_pid = $("#p_pid").val();
	if(p_name == ''){
		myalert_error("姓名不能为空！");
		return;
	}
	if(checkStr(p_name,2)==false){
		myalert_error("姓名不能含有特殊字符！");
		return ;
	}
	if(p_sex == ''){
		myalert_error("性别不能为空！");
		return;
	}
	if(checkStr(p_sex,2)==false){
		myalert_error("性别不能含有特殊字符！");
		return ;
	}
	if(p_age == ''){
		myalert_error("年龄不能为空！");
		return;
	}
	if(checkStr(p_age,1)==false){
		myalert_error("年龄格式输入错误，请输入正整数！");
		return;
	}
	if(p_deptname == ''){
		myalert_error("科室不能为空！");
		return;
	}
	if(checkStr(p_deptname,2)==false){
		myalert_error("科室不能含有特殊字符！");
		return ;
	}
	if(p_bednum == ''){
		myalert_error("床号不能为空！");
		return;
	}
	if(checkStr(p_bednum,2)==false){
		myalert_error("床号不能含有特殊字符！");
		return ;
	}
	if(p_pid == ''){
		myalert_error("住院号不能为空！");
		return;
	}
	if(checkStr(p_pid,2)==false){
		myalert_error("住院号不能含有特殊字符！");
		return ;
	}
	var medicalAdviceSubList = [];
	var i = 0;
	var flag = false;
	var error = "";
	$(".tr_data").each(function(){
		i++;
		var medicalAdviceSub = {};
		var advice_name = $(this).find(".advice_name").val();
		if(advice_name == ''){
			error = "医嘱明细第" + i + "行的药品名称不能为空！"
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_name = advice_name;
		var advice_spec = $(this).find(".advice_spec").val();
		if(advice_spec == ''){
			error = "医嘱明细第" + i + "行的药品规格不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_spec = advice_spec;
		var advice_dose = $(this).find(".advice_dose").val();
		if(advice_dose == ''){
			error = "医嘱明细第" + i + "行的剂量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(advice_dose)){
			error = "医嘱明细第" + i + "行的剂量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.advice_dose = advice_dose;
		var dose_unit_code = $(this).find(".dose_unit_code").val();
		if(dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的剂量单位不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.dose_unit_code = dose_unit_code;
		var path_code = $(this).find(".path_code").val();
		if(path_code == ''){
			error = "医嘱明细第" + i + "行的用药途径不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.path_code = path_code;
		var frequency_code = $(this).find(".frequency_code").val();
		if(frequency_code == ''){
			error = "医嘱明细第" + i + "行的用药频率不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.frequency_code = frequency_code;
		var total_dose = $(this).find(".total_dose").val();
		if(total_dose == ''){
			error = "医嘱明细第" + i + "行的总量不能为空！";
			flag = true;
			return false;
		}else if(isNaN(total_dose)){
			error = "医嘱明细第" + i + "行的总量不能为非数字！";
			flag = true;
			return false;
		}
		medicalAdviceSub.total_dose = total_dose;
		var total_dose_unit_code = $(this).find(".total_dose_unit_code").val();
		if(total_dose_unit_code == ''){
			error = "医嘱明细第" + i + "行的总量单位不能为空！";
			flag = true;
			return false;
		}
		medicalAdviceSub.total_dose_unit_code = total_dose_unit_code;
		medicalAdviceSubList.push(medicalAdviceSub);
	});
	if(flag){
		myalert_error(error,"30%","40%");
		return;
	}
	var url=ctx + "/teachweb/saveMedicalAdviceTea.action?action=add";
	var postData = {
				id:mam_id,
				p_name:p_name,
				p_sex:p_sex,
				p_age:p_age,
				p_deptname:p_deptname,
				p_bednum:p_bednum,
				p_pid:p_pid,
			 	rowData:JSON.stringify(medicalAdviceSubList)
			};
	doAjax(url,postData,2,function(req){
		 if(req.success == true){
			 myalert_success(req.data,"30%","40%",function(){
				 window.location.href = ctx + "/jsp/web/teach/medicalDocumentTea.jsp";
			 });
		 }else{
			 myalert_error(req.data,"30%","40%");
		 }
	});
}

//重置医嘱
function resetTea(){
	$("#p_name").val("");
	$("#p_sex").val("");
	$("#p_age").val("");
	$("#p_deptname").val("");
	$("#p_bednum").val("");
	$("#p_pid").val("");
	var str=
		'<tr class="tr_title"><td>药品名称</td><td>药品规格</td><td>剂量</td><td>剂量单位</td><td>用药途径</td><td>用药频率</td><td>总量</td><td>总量单位</td></tr>' +
		'<tr class="tr_data">' +
	    '<td><input type="text" class="longInput form-control advice_name"/></td>' +
	    '<td><input type="text" class="longInput form-control advice_spec" style="width: 220px"/></td>' +
	    '<td><input type="text" class="shortInput form-control advice_dose"/></td>' +
	    '<td><select class="form-control shortInput dose_unit_code" style="width: 85px">' +
	    str1 +
	    '</select></td>' +
	    '<td><select class="form-control shortInput path_code" style="width: 105px"> ' +
	    str2 +
	    '</select></td>' +
	    '<td><select class="form-control shortInput frequency_code" style="width: 105px">' +
	    str3 +
	    '</select></td>' +
	    '<td><input type="text" class="shortInput form-control total_dose"/></td>' +
	    '<td><select class="form-control shortInput total_dose_unit_code" style="width: 85px">' +
	    str4 +
	    '</select></td>' +
	    '<td><a style="font-size:22px;" title="删除" href="javascript:void(0);" onclick="deleteCurrentRow(this);">✘</a></td>' +
	    '</tr>';
	$(".tableDiv table").html("");
	$(".tableDiv table").html(str);
} 
//医嘱删除一行
function deleteCurrentRow(obj){
	myConfirm("是否要删除？","","",
			function(){
				var tr=obj.parentNode.parentNode;
				var tbody=tr.parentNode;
				//只剩行首时保留一行
			    if(tbody.rows.length == 2){
			    	myalert_error('必须保留一行！');
					return;
			    }
			    tbody.removeChild(tr);
			    layer.closeAll();
			}
	);
}