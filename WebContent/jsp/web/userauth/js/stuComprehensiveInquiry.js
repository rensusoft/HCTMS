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
	    id = Request['id'];
	    orga_id = Request['orga_id'];
	   
	    if(id!="" && id!=null){
	        $(".departName").text(id);
	    }
	    if(orga_id!="" && orga_id!=null){
	    	$("#orga_id").val(orga_id);
	    }
	   
	//学生类型select填充
	selectInfo();
	  //页面展示
	 pageInfo("","");
});
function seachInfo(){
var name=$('#stu_name').val();
var stu_type=$('#stu_type').val();
	pageInfo(name,stu_type);
}
function resSeach(){
	location.reload();
}

function pageInfo(name,stu_type){
    $.ajax({
 		type: 'POST',
	     	url :  ctx+'/userauthweb/selectPageInfo.action',
	    	dataType: 'json',
	    	data:{
	    		name:name,
	    		stu_type:stu_type,
	    		orga_id:$("#orga_id").val()
	    	},
	  	    async: false,
	        success:function(data) {
                var str='';
                if(data.length>0){
                	
	        	for(var i = 0 ; i<data.length ; i++){
	        		if(i==0){
	        			str+= '<div class="delTitle">'+
	        			' <div class="hr"></div>'+
	        			' <h5>'+data[i].tea_name+'</h5>'+
	        			'  <a class="toggleBtn"><img src="'+ctx+'/jsp/web/userauth/img/arrowT.png"/>收起</a>'+
	        			'  </div>'+
	        			'  <div class="stuContent">'+
	        			'   <div class="stuList">'+
	        			'  <div class="stuImg">';
	        			if(data[i].train_status==52){
	        				str+='  <span class="rotate">'+
		        			'   <i class="yellow"></i>'+
		        			'     <b>待入科</b>'+
		        			' </span>';
	        		   	}else if(data[i].train_status==53){
	        		   		str+='  <span class="rotate">'+
		        			'   <i class="green"> </i>'+
		        			'     <b>轮转中</b>'+
		        			' </span>';
	        		   	}else{
	        		   		str+='<span class="rotate">'+
		        			'   <i></i>'+
		        			'     <b>待出科</b>'+
		        			' </span>';
	        		   	}
	        			str+='<img onerror=\'this.src="'+ctx+'/jsp/web/userauth/img/usePhoto2.png"\' src="'+ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+data[i].stuNum+'.jpg'+'"/>'+
	        			'   </div>'+
	        			'   <div class="basicInfo">'+
	        			'      <table>'+
	        			'         <tr>'+
	        			'            <td><b><span class="glyphicon glyphicon-user"></span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</b></td>'+
	        			'         </tr>'+
	        			'         <tr>'+
	        			'            <td><i>'+data[i].stu_name+'</i></td>'+
	        			'        </tr>'+
	        			'        <tr>'+
	        			'    <td><b><span class="glyphicon glyphicon-tags"></span>学生类型：</b></td>'+
	        			'      </tr>'+
	        			'       <tr>'+
	        			'       <td><i>'+data[i].stu_type+'</i></td>'+
	        			'     </tr>'+
	        			'     <tr>'+
	        			'       <td><b><span class="glyphicon glyphicon-home"></span>所属院校：</b></td>'+
	        			'   </tr>'+
	        			'  <tr>'+
	        			'    <td><i>'+data[i].stu_school_name+'</i></td>'+
	        			'  </tr>'+
	        			' </table>'+
	        			'  </div>'+
	        			'  <div class="atten">'+
	        			'   <p><a class="attenNum">'+data[i].que_count+'</a></p>'+
	        			'   <p>缺勤天数</p><input type="hidden"  class="orga_name" value="'+data[i].orga_name+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input type="hidden"  class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input type="hidden"  class="orga_id" value="'+data[i].train_dept_id+'" />'+
	        			'  </div>'+
	        			'<div class="syllabus">'+
	        			'  <p><a class="syllabusNum">'+data[i].outlineCom+'%</a></p>'+ 
	        			'   <p>大纲吻合度</p><input type="hidden"  class="syllabus_Num" value="'+data[i].outlineCom+'" />'+
	        			'<input type="hidden"  class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input type="hidden"  class="train_plan_id" value="'+data[i].train_plan_id+'" />'+
	        			'<input type="hidden"  class="train_dept_id" value="'+data[i].train_dept_id+'" />'+
	        			' </div>'+
	        			' <div class="days">'+
	        			'      <p>'+data[i].over_day+'</p>'+
	        			'      <p>剩余天数</p>'+
	        			'  </div>'+
//	        			'  <div class="compare">'+
//	        			'      <a href="" class="resetting">比较</a>'+
//	        			'   </div>'+
	        			'</div>';
	        		}else if(data[i].tea_name==data[i-1].tea_name){
	        			str+= '   <div class="stuList">'+
	        			'  <div class="stuImg">';
	        			if(data[i].train_status==52){
	        				str+='  <span class="rotate">'+
		        			'   <i class="yellow"></i>'+
		        			'     <b>待入科</b>'+
		        			' </span>';
	        		   	}else if(data[i].train_status==53){
	        		   		str+='  <span class="rotate">'+
		        			'   <i class="green"></i>'+
		        			'     <b>轮转中</b>'+
		        			' </span>';
	        		   	}else{
	        		   		str+='<span class="rotate">'+
		        			'   <i></i>'+
		        			'     <b>待出科</b>'+
		        			' </span>';
	        		   	}
	        			str+='<img onerror=\'this.src="'+ctx+'/jsp/web/userauth/img/usePhoto2.png"\' src="'+ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+data[i].stuNum+'.jpg'+'"/>'+
	        			'   </div>'+
	        			'   <div class="basicInfo">'+
	        			'      <table>'+
	        			'         <tr>'+
	        			'            <td><b><span class="glyphicon glyphicon-user"></span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</b></td>'+
	        			'         </tr>'+
	        			'         <tr>'+
	        			'            <td><i>'+data[i].stu_name+'</i></td>'+
	        			'        </tr>'+
	        			'        <tr>'+
	        			'    <td><b><span class="glyphicon glyphicon-tags"></span>学生类型：</b></td>'+
	        			'      </tr>'+
	        			'       <tr>'+
	        			'       <td><i>'+data[i].stu_type+'</i></td>'+
	        			'     </tr>'+
	        			'     <tr>'+
	        			'       <td><b><span class="glyphicon glyphicon-home"></span>所属院校：</b></td>'+
	        			'   </tr>'+
	        			'  <tr>'+
	        			'    <td><i>'+data[i].stu_school_name+'</i></td>'+
	        			'  </tr>'+
	        			' </table>'+
	        			'  </div>'+
	        			'  <div class="atten">'+
	        			'   <p><a class="attenNum">'+data[i].que_count+'</a></p>'+
	        			'   <p>缺勤天数</p><input type="hidden"  class="orga_name" value="'+data[i].orga_name+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input  type="hidden" class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input  type="hidden" class="orga_id" value="'+data[i].train_dept_id+'" />'+
	        			'  </div>'+
	        			'<div class="syllabus">'+
	        			'  <p><a class="syllabusNum">'+data[i].outlineCom+'%</a></p>'+
	        			'   <p>大纲吻合度</p><input type="hidden"  class="syllabus_Num" value="'+data[i].outlineCom+'" />'+
	        			'<input type="hidden"  class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input type="hidden"  class="train_plan_id" value="'+data[i].train_plan_id+'" />'+
	        			'<input type="hidden"  class="train_dept_id" value="'+data[i].train_dept_id+'" />'+
	        			' </div>'+
	        			' <div class="days">'+
	        			'      <p>'+data[i].over_day+'</p>'+
	        			'      <p>剩余天数</p>'+
	        			'  </div>'+
//	        			'  <div class="compare">'+
//	        			'      <a href="" class="resetting">比较</a>'+
//	        			'   </div>'+
	        			'</div>';
	        		}else{
	        			str+= '</div><div class="delTitle">'+
	        			' <div class="hr"></div>'+
	        			' <h5>'+data[i].tea_name+'</h5>'+
	        			'  <a class="toggleBtn"><img src="'+ctx+'/jsp/web/userauth/img/arrowT.png"/>收起</a>'+
	        			'  </div>'+
	        			'  <div class="stuContent">'+
	        			'   <div class="stuList">'+
	        			'  <div class="stuImg">';
	        			if(data[i].train_status==52){
	        				str+='  <span class="rotate">'+
		        			'   <i class="yellow"></i>'+
		        			'     <b>待入科</b>'+
		        			' </span>';
	        		   	}else if(data[i].train_status==53){
	        		   		str+='  <span class="rotate">'+
		        			'   <i class="green"></i>'+
		        			'     <b>轮转中</b>'+
		        			' </span>';
	        		   	}else{
	        		   		str+='<span class="rotate">'+
		        			'   <i></i>'+
		        			'     <b>待出科</b>'+
		        			' </span>';
	        		   	}
	        			str+='<img onerror=\'this.src="'+ctx+'/jsp/web/userauth/img/usePhoto2.png"\' src="'+ctx+'/ueditor(附件文件夹千万不能删)/userImg/'+data[i].stuNum+'.jpg'+'"/>'+
	        			'   </div>'+
	        			'   <div class="basicInfo">'+
	        			'      <table>'+
	        			'         <tr>'+
	        			'            <td><b><span class="glyphicon glyphicon-user"></span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</b></td>'+
	        			'         </tr>'+
	        			'         <tr>'+
	        			'            <td><i>'+data[i].stu_name+'</i></td>'+
	        			'        </tr>'+
	        			'        <tr>'+
	        			'    <td><b><span class="glyphicon glyphicon-tags"></span>学生类型：</b></td>'+
	        			'      </tr>'+
	        			'       <tr>'+
	        			'       <td><i>'+data[i].stu_type+'</i></td>'+
	        			'     </tr>'+
	        			'     <tr>'+
	        			'       <td><b><span class="glyphicon glyphicon-home"></span>所属院校：</b></td>'+
	        			'   </tr>'+
	        			'  <tr>'+
	        			'    <td><i>'+data[i].stu_school_name+'</i></td>'+
	        			'  </tr>'+
	        			' </table>'+
	        			'  </div>'+
	        			'  <div class="atten">'+
	        			'   <p><a class="attenNum">'+data[i].que_count+'</a></p>'+
	        			'   <p>缺勤天数</p><input type="hidden"  class="orga_name" value="'+data[i].orga_name+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input  type="hidden" class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input type="hidden"  class="orga_id" value="'+data[i].train_dept_id+'" />'+
	        			'  </div>'+
	        			'<div class="syllabus">'+
	        			'  <p><a class="syllabusNum">'+data[i].outlineCom+'%</a></p>'+
	        			'   <p>大纲吻合度</p><input type="hidden"  class="syllabus_Num" value="'+data[i].outlineCom+'" />'+
	        			'<input type="hidden"  class="stu_auth" value="'+data[i].stu_auth_id+'" />'+
	        			'<input type="hidden"  class="stu_name" value="'+data[i].stu_name+'" />'+
	        			'<input type="hidden"  class="train_plan_id" value="'+data[i].train_plan_id+'" />'+
	        			'<input type="hidden"  class="train_dept_id" value="'+data[i].train_dept_id+'" />'+
	        			' </div>'+
	        			' <div class="days">'+
	        			'      <p>'+data[i].over_day+'</p>'+
	        			'      <p>剩余天数</p>'+
	        			'  </div>'+
//	        			'  <div class="compare">'+
//	        			'      <a href="" class="resetting">比较</a>'+
//	        			'   </div>'+
	        			'</div>';
	        		}
	        	}
                }else{
                	 str+='</div><div style="color:#505f91;font-size: 40px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">'+
                      '<span class="glyphicon glyphicon-exclamation-sign" style="font-size: 35px"></span>目前暂无数据</div>';
                }
//                alert(str);
                $("#pageInfo").html(str);
	        }
 });
    $('#se').selectOrDie();
    $(".atten").click(
        function(){
        	var stu_auth=$(this).children(".stu_auth").val();
        	var orga_id=$(this).children(".orga_id").val();
        	var orga_name=$(this).children(".orga_name").val();
        	var stu_name=$(this).children(".stu_name").val();
        	relesepup = mypopdiv(2,"考勤信息",'1050px','470px','','','N',ctx+ '/jsp/web/teach/stuAtten.jsp?width=1050&height=440&stu_auth='+stu_auth+'&orga_id='+orga_id+'&tea_type=tea'+'&stu_name='+stu_name+'&orga_name='+orga_name);
        }
    );
    $(".syllabus").click(
        function(){
        	var train_plan_id=$(this).children(".train_plan_id").val();
//        	var syllabusNum=$(this).children(".syllabus_Num").val();
        	var train_dept_id=$(this).children(".train_dept_id").val();
        	var stu_auth=$(this).children(".stu_auth").val();
        	var stu_name=$(this).children(".stu_name").val();
//        	if(syllabusNum==0){
//        		return false;
//        	}
        	clickToUrlTab("轮转详情_"+stu_auth,"轮转详情 ["+stu_name+"]",ctx+'/teachweb/selectRotateProcessRight.action?id='+train_plan_id+'&train_dept_id='+train_dept_id+'&stu_auth='+stu_auth);
        }
    );
    $(".toggleBtn").click(
        function(e){
            $(this).parents(".delTitle").next(".stuContent").toggleClass("extend");
            var closeText=$(this).text();
            //console.log(closeText);
            if(closeText=='收起'){
                $(this).html("<img src='img/arrowB.png'/>展开");
            }else{
                $(this).html("<img src='img/arrowT.png'/>收起");
            }
        }
    );
    
}
//学生类型select填充
function selectInfo(){
	 $.ajax({
	 		type: 'POST',
		     	url :  ctx+'/userauthweb/selectStuType.action',
		    	dataType: 'json',
		    	data:{},
		  	    async: false,
		        success:function(data) {
	                var str='<option value="">——请选择学生类型——</option>';
		        	for(var i = 0 ; i<data.length ; i++){
		        		str+='<option value="'+data[i].type_code+'">——'+data[i].type_name+'——</option>';
		        	}
		        	$("#stu_type").html(str);
		        }
	 });
	}

function backOrgaInfo(){
	location=ctx+ '/jsp/web/teach/departmentList.jsp';
}
