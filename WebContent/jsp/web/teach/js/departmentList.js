$(function() {
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
	    pageInfo("");
});

function selectByOrga(){
	var orga_name=$('#orga_name').val();
	pageInfo(orga_name);
}

function resOrga(){
	$('#orga_name').val("");
	pageInfo("");
}

function pageInfo(orga_name){
	// 查询页面信息
	$.ajax({
				type : 'POST',
				url : ctx + '/teachweb/orgaPageInfo.action',
				dataType : 'json',
				data : {
					orga_name:orga_name
					
				},
				async : false,
				success : function(data) {
					var str = "";
					if (data.length != 0) {
						for ( var i = 0; i < data.length; i++) {
							str += ' <div class="departInfo">'
								    +'<input type="hidden" class="orga_id" value="'+data[i].orga_id+'" /> '
									+ ' <div class="hospName">'
									+ '   <b>'
									+ data[i].orga_name
									+ '    </b>'
									+ '  </div>'
									+ '  <div class="hospInfo">'
									+ '   <div class="hospInfoCont">'
									+ '      <p>'
									+ '           学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生：'
									+ '       <span class="remind">'
									+ '         <b>'+data[i].wstu_num+'</b>人'
									+ '      </span>'
									+ '      <span class="time">'
									+ '           <b>'+data[i].zstu_num+'</b>人'
									+ '      </span>'
									+ '      <span class="complete">'
									+ '        <b>'+data[i].ystu_num+'</b>人'
									+ '      </span>'
									+ '      </p>'
									+ '      <p>'
									+ '        带教老师：<span>'+data[i].tea_num+'</span>人'
									+ '      </p>'
									+ '       <p class="attenNum"><input type="hidden" class="orga_id" value="'+data[i].orga_id+'"  />'
									+ '         今日考勤：<span class="glyphicon glyphicon-ok">'+data[i].d_num+'</span>人'
									+ '           <span class="glyphicon glyphicon-remove">'+data[i].q_num+'</span>人'
									+ '        </p>'
									+ '     </div>'
									+ ' </div>'
									+ '  <div class="btns">'
									+ '   <div>'
									+ '      <a class="resetting detailsBtn" href="#">详情</a><br/>'
//									+ '       <a class="resetting">比较</a>'
									+ '     </div>' + '   </div>' + ' </div>';
						}
					}else{
	                	 str+='<div style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">'+
	                      '<span class="glyphicon glyphicon-exclamation-sign" style="font-size: 35px"></span>目前暂无数据</div>';
	                }
	                $("#pageInfo").html(str);
				}
			});

	$(".detailsBtn").click(function() {
		var orga_id = $(this).parents(".departInfo").children(".orga_id").val();
		var bText = $(this).parents(".departInfo").children(".hospName").children().text();
	
	  
		$(this).attr("href",ctx+ "/jsp/web/userauth/stuComprehensiveInquiry.jsp?id="+ bText + "&orga_id="+orga_id);
	});
	$(".glyphicon-remove").click(function() {
    	var num = $(this).text();
    	if(num==0){
    		return false;
    	}
    	var orga_id = $(this).parents(".attenNum").children(".orga_id").val();
        layer.open({
            type: 2,
            title:'未考勤学生列表',
            content:ctx+ '/jsp/web/teach/stuAttNumLayer.jsp?orga_id='+orga_id,
            area: ['1020px', '470px']
        });
    });
	
}
