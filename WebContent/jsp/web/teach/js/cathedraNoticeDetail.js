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
				$("#cath_title").text(data.cathedraPlan.cath_title);
				$("#speaker_name").text(data.cathedraPlan.speaker_name);
				$("#cath_place").text(data.cathedraPlan.cath_place);
				//数据库cath_date的数据的格式变化，这里就会有问题，关键数据库里类型为Integer
				var cath_date = data.cathedraPlan.cath_date.toString().substring(0,4) + "-" + data.cathedraPlan.cath_date.toString().substring(4,6)+ "-" + data.cathedraPlan.cath_date.toString().substring(6);
				$("#cath_date").text(cath_date);
				if(new Date(data.cathedraPlan.cath_time).getMinutes() == 0){
					$("#cath_time").text(new Date(data.cathedraPlan.cath_time).getHours() + ":00");
				}else{
					$("#cath_time").text(new Date(data.cathedraPlan.cath_time).getHours() + ":" + new Date(data.cathedraPlan.cath_time).getMinutes());
				}
				$("#cath_content").html(data.cathedraPlan.cath_content);
		 }
	});
  });