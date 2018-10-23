var id = '';
var stu_auth_id = '';
var flag = 0;
$(function(){
	function GetRequest() {
	    var url = decodeURI(location.search);
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
    id = Request['id'];
    stu_auth_id = Request['stu_auth_id'];
    flag = Request['flag'];
});
//获得考勤状态
function getAttendState(attend_state){
	var url = ctx + '/teachweb/manageAttendState.action';
	var postData = {
			id : id,
			stu_auth_id : stu_auth_id,
			attend_state : attend_state
	};
	doAjax(url,postData,2,function(res){
		if(res.success == true){
			if(flag == 1){
				parent.orgaInit();
				parent.closeLayer();
			}else if(flag == -1){
				parent.selectAttendanceStuInfo();
				parent.closeLayer();
			}
		}
	});
}