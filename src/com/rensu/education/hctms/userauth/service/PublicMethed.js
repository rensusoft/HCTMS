/***
 * @param url -- ajax相应地址
 * @param postData -- 参数
 * @param type -- 回调函数类型（1：普通模式，成功弹出提示框；2：自定义回调函数，对应后面的successFun）
 * @param successFun -- 自定义回调函数
 * @returns
 */
function doAjax(url,postData,type,successFun){
	var pHeight = $(document).height()-165;
	var pWidth = $(document).width();
	var laSh = layer.load(0,{shade:[0.5,'#fff']}); //遮罩效果
	$.ajax({
		type: "POST",
        url: url,
        data:postData,
        success: function(res){
        	setTimeout(function(){
	        	layer.close(laSh);
	            if(res.success==true){
	            	if(type==1){
	                	myalert_success(res.data);
	            	}else{
	            		successFun();
	            	}
	            }else{
					myalert_error(res.data);
	            }
        	},200);
        },
        error: function(){
        	layer.close(laSh);
            myalert_error("异常错误....",(pHeight-150)/2+"px",(pWidth-200)/2+"px");
        }
	});
}

/**
 * 若字符串oriStr以trim结尾，则去除trim部分
 * @param oriStr
 * @param trim
 * @returns
 * @date 2015年11月17日
 * @autor chen xiaoxiao
 */
 function rtrim(oriStr, trim) {
	if (!trim || !oriStr) {
		return oriStr;
	}
	while (endWith(oriStr, trim)) {
		oriStr = oriStr.substr(0, oriStr.length - trim.length);
	}
	return oriStr;
}

/**
 * 若字符串oriStr以trim开头，则去除trim部分
 * @param oriStr
 * @param trim
 * @returns
 * @date 2015年11月17日
 * @autor chen xiaoxiao
 */
 function ltrim(oriStr, trim) {
	if (!trim || !oriStr) {
		return oriStr;
	}
	while (startWith(oriStr, trim)) {
		oriStr = oriStr.substr(trim.length);
	}
	return oriStr;
}

/**
 * 判断str1是否是以str2开始的 
 * @param str1
 * @param str2
 * @returns {Boolean}
 * @date 2015年11月17日
 * @autor chen xiaoxiao
 */
function startWith(str1, str2) {
	if (!str1 || !str2) {
		return false;
	}
	if (str1.length < str2.length) {
		return false;
	}
	if (str1.substr(0, str2.length) == str2) {
		return true;
	}
	return false;
}

/**
 * 判断str1是否是以str2结束的。
 * @param str1
 * @param str2
 * @returns {Boolean}
 * @date 2015年11月17日
 * @autor chen xiaoxiao
 */
function endWith(str1, str2) {
	if (!str1 || !str2) {
		return false;
	}
	if (str1.length < str2.length) {
		return false;
	}
	if (str1.substr(str1.length-str2.length, str1.length) == str2) {
		return true;
	}
	return false;
}

/**
 * 将面板中html标签用户输入的值转换为html标签
 * @param {} panelCmp 该面板对象一定是通过Ext.getCmp()方法获取
 */
function setCmpVal(panelCmp) {
	return setElVal(panelCmp.getEl());
}

/**
 * 将面板中html标签用户输入的值转换为html标签
 * @param {} panelCmp 该面板对象一定是通过Ext.get()方法获取
 */
function setElVal(panelEl) {
	var item, dom, i, resList;
	
	//input标签
	try {
		resList = panelEl.select("input");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				dom = item.dom;
				if (dom.type == "checkbox"
						|| dom.type == "radio") {
					if (dom.checked == true) {
						item.set({"checked": true});
					} else {
						dom.removeAttribute('checked');
					}
				}
				else {
					item.set({"value": item.getValue()});
				}
			}
		}
	} catch (e) {}
	
	//select标签
	try {
		resList = panelEl.select("select");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				dom = item.dom;
				var options = dom.options;
				var seleIndex = dom.selectedIndex;
				for (var j = 0; options && j < options.length; j++) {
					options[j].removeAttribute("selected");
				}
				options[seleIndex].setAttribute("selected", true);
			}
		}
	} catch (e) {}
	
	//textarea标签
	try {
		resList = panelEl.select("textarea");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				var _newVal = item.getValue();
				Ext.core.DomHelper.overwrite(item, _newVal);
			}
		}
	} catch (e) {}
	
	return panelEl.dom.innerHTML;
}

/**
 * 根据给的html文本，将各个标签设置为只读或者不可用。
 * @param {} html
 * @return {}
 */
function getReadOnlyHtml(html) {
	var panel = Ext.create('Ext.panel.Panel', {
		border : 0,
		html: html,
		renderTo: Ext.getBody()
	});
	var panelEl = panel.getEl();
	var item, dom, i, resList;
	//input标签
	try {
		resList = panelEl.select("input");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				dom = item.dom;
				if (dom.type == "checkbox"
						|| dom.type == "radio") {
					item.set({"disabled": true});
				}
				else {
					item.set({"readOnly": true});
				}
			}
		}
	} catch (e) {}
	
	//select标签
	try {
		resList = panelEl.select("select");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				item.set({"disabled": true});
			}
		}
	} catch (e) {}
	
	//textarea标签
	try {
		resList = panelEl.select("textarea");
		if (resList && resList.getCount() > 0) {
			for (i = 0; i < resList.getCount(); i++) {
				item = resList.item(i);
				item.set({"readOnly": true});
			}
		}
	} catch (e) {}
	
	return panelEl.dom.innerHTML;
}

/**
 * 字符串中替换某字符全部字符的方法
 * @param {} str 完整字符串
 * @param {} sptr 被替换字符串
 * @param {} sptr1 替换的字符串
 * @return {} 替换过的字符串
 */
function replaceAll (str,sptr,sptr1) { 
	while (str.indexOf(sptr) >= 0 && sptr1.indexOf(sptr) < 0) { 
		str = str.replace(sptr,sptr1); 
	}
	return str; 
}

/**
 * 浏览器端返回操作。
 * 
 * @date 2016年5月10日
 * @autor chen xiaoxiao
 */
function back() {
	window.history.back();
}



/* --------以下为layer的封装方法---------- */
/***
 * 显示遮罩层
 */
function myLoading(){
	return layer.load(0,{
		shade:[0.5,'#fff']
	});
}

/***
 * 关闭遮罩层
 * @param {} loadId 需要关闭的遮罩层ID
 */
function closeMyLoading(loadId){
	layer.close(loadId);
}

/***
 * 关闭遮罩层（当你在iframe页面关闭自身时）
 * @param {}
 */
function closeMyLoadingPar(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

/***
 * 透明消息提示框（无按钮，会自动关闭）
 * @param {} content 展现的内容
 * @param {} verticalNum -- 垂直（两种形式：'200px';'50%'）
 * @param {} horizontalNum -- 水平（两种形式：'200px';'50%'）
 */
function myMsg(content,verticalNum,horizontalNum){
	if(content==null||content==""){
		content = "";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = (pHeight-180)/2+"px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = (pWidth-300)/2+"px";
	}
	layer.msg(content, {time: 3000,offset :[verticalNum,horizontalNum]});
}

/***
 * alert弹出提示框（成功）
 * @param {} content -- alert展现的文字内容(SUCCESS)
 * @param {} verticalNum -- 垂直（两种形式：'200px';'50%'）
 * @param {} horizontalNum -- 水平（两种形式：'200px';'50%'）
 * @param {} conYes -- conYes 点击‘确定’后的回调函数
 */
function myalert_success(content,verticalNum,horizontalNum,conYes,timeNum){
	if(content==null||content==""){
		content = "";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = (pHeight-180)/2+"px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = (pWidth-300)/2+"px";
	}
	if(timeNum==null||timeNum==""){
		timeNum = 0;
	}
	//提示框
	layer.alert(content, {
		title:"提示信息",
		time: timeNum,
		icon: 6,
		skin: 'layui-layer-lan',
		offset : [verticalNum,horizontalNum],
	    closeBtn: 1
	},conYes);
}

/***
 * alert弹出提示框（失败）
 * @param {} data -- alert展现的文字内容(ERROR)
 * @param {} verticalNum -- 垂直（两种形式：'200px';'50%'）
 * @param {} horizontalNum -- 水平（两种形式：'200px';'50%'）
 * @param {} conYes -- conYes 点击‘确定’后的回调函数
 */
function myalert_error(data,verticalNum,horizontalNum,conYes){
	if(data==null||data==""){
		data = "";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = (pHeight-180)/2+"px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = (pWidth-300)/2+"px";
	}
	//提示框
	layer.alert(data, {
		title:"提示信息",
		icon: 5,
		skin: 'layui-layer-lan',
		offset : [verticalNum,horizontalNum],
	    closeBtn: 1
	},conYes);
}


/***
 * 询问框 （显示‘确定’与‘取消’两个按钮）
 * @param {} content 提示的内容
 * @param {} verticalNum -- 垂直（两种形式：'200px';'50%'）
 * @param {} horizontalNum -- 水平（两种形式：'200px';'50%'）
 * @param {} conYes 点击‘确定’后的回调函数
 * @param {} conNo 点击‘取消’后的回调函数
 */
function myConfirm(content,verticalNum,horizontalNum,conYes,conNo){
	if(content==null||content==""){
		content = "";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = "50%";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = "40%";
	}
	layer.confirm(content, {
	  icon: 3,
	  shade:[0.5,'#fff'],
	  skin: 'layui-layer-lan',
	  offset : [verticalNum,horizontalNum],
	  btn: ['确定','取消'] //按钮
	},conYes,conNo
	
	);
}

/***
 * 弹出层
 * @param {} type -- 1:对应下面content放入html代码；2：嵌入一个iframe，对应下面content放jsp的url
 * @param {} title --标题
 * @param {} width -- 长度（两种形式：'200px';'50%'）
 * @param {} length -- 高度（两种形式：'200px';'50%'）
 * @param {} verticalNum -- 垂直（两种形式：'200px';'50%'）
 * @param {} horizontalNum -- 水平（两种形式：'200px';'50%'）
 * @param {} shadeCloseFlag -- 是否点击页面其他地方关闭（'Y';'N'，默认'Y'）
 * @param {} content -- 弹出层div中的内容（html代码）
 * @param {} endFun -- 当层销毁时调用的回调函数
 */
function mypopdiv(type,title,width,length,verticalNum,horizontalNum,shadeCloseFlag,content,endFun){
	if(type==null||type==""){
		type = 1;
	}
	if(title==null||title==""){
		title = false;
	}
	if(length==null||length==""){
		length = "400px";
	}
	if(width==null||width==""){
		width = "250px";
	}
	if(horizontalNum==null||horizontalNum==""){
		horizontalNum = "30%";
	}
	if(verticalNum==null||verticalNum==""){
		verticalNum = "20%";
	}
	if(shadeCloseFlag==null||shadeCloseFlag==""){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="Y"){
		shadeCloseFlag = true;
	}else if(shadeCloseFlag=="N"){
		shadeCloseFlag = false;
	}
	if(content==null||content==""){
		content = "无内容...";
	}
	var myLayer = layer.open({
//		id: id,
		type: type,
		skin: 'layui-layer-lan',
		shade:[0.5,'#fff'],
		title:title,
		area: [width,length],
		offset: [verticalNum,horizontalNum],
	    shadeClose: shadeCloseFlag, //点击遮罩关闭
	    content: content,
	    end:endFun
	});
	return myLayer;
}

/***
 * 用作得到URL传过来的参数数据
 * @param {} name
 * @return {}
 */
function getUrlParam(name){
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r!=null) return unescape(r[2]);
    return null;
} 




