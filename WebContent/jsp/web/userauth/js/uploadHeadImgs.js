$(function(){
	$("#uploadify").uploadify({      
        'debug'     : false, //开启调试  
        'auto'           : false, //是否自动上传     
        'swf'            : '../../../js/uploadify/uploadify.swf',  //引入uploadify.swf    
        'uploader'       : ctx + '/userauthweb/uploadHeadImgs.action',//请求路径    
        'queueID'        : 'fileQueue',//队列id,用来展示上传进度的    
        'width'     : '85',  //按钮宽度    
        'height'    : '25',  //按钮高度  
        'queueSizeLimit' : 3,  //同时上传文件的个数    
        'fileTypeDesc'   : '图片文件',    //可选择文件类型说明  
        'fileTypeExts'   : '*.jpg;', //控制可上传文件的扩展名    
        'multi'          : true,  //允许多文件上传    
        'buttonText'     : '选择头像图片',//按钮上的文字    
        'fileSizeLimit' : '10MB', //设置单个文件大小限制     
        'fileObjName' : 'headImgs',  //<input type="file"/>的name
        'simUploadLimit' : 5, //多文件上传一次上传几个文件
        'method' : 'post',    
        'removeCompleted' : true,//上传完成后自动删除队列    
        'onFallback':function(){     
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");      
        },
        'onUploadSuccess' : function(file, data, response){//单个文件上传成功触发
        	
        },'onQueueComplete' : function(){//所有文件上传完成
        	
        }    
        });
	
});

    
    