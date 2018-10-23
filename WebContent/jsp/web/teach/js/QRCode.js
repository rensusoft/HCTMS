var flag = 0;//  考勤二维码开关开启标识
var active_time = 60;//  有效时间
var seconds = 60;
$(function(){
	//考勤二维码开关是否开启及获取有效时间
//	$.ajax({
//		type: 'POST',
//		url : ctx + "/teachweb/getQRCodeActiveTime.action",
//		data : {},
//		dataType: 'json',
//		async: false,
//		success:function(res) {
//			flag = res.flag;
//			if(flag == 1){
//				active_time = res.active_time;
//			}
//		}
//	});
	//
	getAttendanceQRStr();
	//
//	if(flag == -1){
//		$("#active_time").text("————");
//	}else if(flag == 1){
//		seconds = active_time;
//		countDown();
//	}
    
    $(".layui-layer-btn0").click(
        function(){
            parent.closeLayer();
        }
    );
})
//获取二维码
function getAttendanceQRStr(){
	$.ajax({
		async: false,
		type : 'POST',
		url : ctx+'/teachweb/getAttendanceQRStr.action',
		dataType : 'json',
		data : {},
    	success:function(res) {
    		$(".QRCode").html('');
    		$(".QRCode").qrcode({
    	        render: "table", 
    	        width: 200, 
    	        height:200, 
    	        text: res.data 
    	    });
    	}
	});
}
//倒计时
function countDown(){
	$("#active_time").text(formatSeconds(seconds));
	seconds--;  
    if(seconds == 0){
    	seconds = active_time;
    }
    setTimeout(function() {
    	countDown()
       },
       1000)
}
//将秒数转化为时分秒
function formatSeconds(seconds) {
    var second = parseInt(seconds);// 秒
    var min = 0;// 分
    var hour = 0;// 小时
    if(second > 60) {
    	min = parseInt(second / 60);
    	second = parseInt(second % 60);
            if(min > 60) {
            	hour = parseInt(min / 60);
            	min = parseInt(min % 60);
            }
    }
    var result = ""+parseInt(second)+"秒";
    if(min > 0) {
        result = ""+parseInt(min)+"分"+result;
    }
    if(hour > 0) {
        result = ""+parseInt(hour)+"时"+result;
    }
    return result;
}