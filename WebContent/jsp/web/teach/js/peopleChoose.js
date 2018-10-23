var stuArr=new Array();
$(function() {
	peopleChoose();
});

function peopleChoose() {
	
	$.ajax({
		url:ctx+"/teachweb/peopleChoose.action",
		type: 'POST',
		async:false,
		data:{},
		dataType:'json',
		success:function(res){
			var str="<input name='' type='checkbox' value='' id='everyOne' onclick='all1();'> <span>全选</span> ";
			
			for (var i=0;i<res.data.length;i++){
				str+="<input name='userName' type='checkbox' value=" + res.data[i].stu_auth_id + "><span>" +  res.data[i].name+"</span>";
			}
			$("#stuName").html(str);
		}
	});
}

function all1(){
	var people=document.getElementById("everyOne");
	var peoples=$("input[name='userName']");
	if (people.checked) {
        for (var i = 0; i < peoples.length; i++)
        	peoples[i].checked = true;
    } else {
        for (var i = 0; i < peoples.length; i++)
        	peoples[i].checked = false;
    }
}

function reset(){
	 $("input[name='userName']:checked").each(function(){
		 $(this).attr("checked",false);
	 });
}

function save() {
	 $("input[name='userName']:checked").each(function(){
			 stuArr.push({key:$(this).val(),value:$(this).next().html()});
	});
		 parent.addName(stuArr);
	}
