$(function(){
	 function GetRequest() {
	        var url = decodeURI(location.search); //获取url中"?"符后的字串
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
	    orga_id = Request['orga_id'];
	    if(orga_id!=""&&orga_id!=null){
	    	//查询缺勤人信息
	    	$.ajax({
	    		type : 'POST',
	    		url : ctx + '/userauthweb/absenceStuInfo.action',
	    		dataType : 'json',
	    		data : {
	    			orga_id:orga_id
	    		},
	    		async : false,
	    		success : function(data) {
	    			if(data.length!=0){
	    			var str = '<table class="stuAttenTable" >'+
	                        '<tr>'+
	                        '<th>学生姓名</th>'+
	                        '<th>学生类型</th>'+
	                        '<th>当前科室</th>'+
	                        '<th>日期</th>'+
	                    '</tr>';
	    				for(var i =0;i<data.length;i++){
	    			   str+='<tr>'+
	    					' <td>'+data[i].stu_name+'</td>'+
	    					'<td>'+data[i].stu_type+'</td>'+
	    					'<td>'+data[i].orga_name+'</td>'+
	    					'<td>'+data[i].date_str+'</td>'+
	    					'</tr>';
	    				}
	    				str+= '</table>';
	    				$('#pageInfo').html(str);
	    			}
	    		}
	    	});
	    }
});