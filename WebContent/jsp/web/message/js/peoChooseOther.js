var toss = "";
var tossText = "";
var zNodes;
var authArr = new Array();

// ztree
var setting = {
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};

$(function() {
	var Request = new Object();
	Request = GetRequest();
	var value = Request['values']; // 字符
	var url = ctx + '/messageweb/getPeoByOrga.action';
	var postData = {
		senderAuthIds : value
	};
	doAjax(url, postData, 2, function(res) {
		zNodes = res.lstTree;
		zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes); // 根据后台传过来的List<Map(String,String>>数据组装成树结构，map中包含id,pid,name，checked等key。
		// 树状结构,默认展开被勾选的 start
		var tree = $.fn.zTree.getZTreeObj("tree");
		
		var nodes1 = tree.getNodes();
		for (var i = 0; i < nodes1.length; i++) { //设置第一层节点展开
			tree.expandNode(nodes1[i], true, false, true);
		}
		var nodes = tree.getCheckedNodes(true);
		for ( var j = 0; j < nodes.length; j++) {
			var node = tree.getNodeByParam("id", nodes[j].id);
			var parent = node.getParentNode();
			if (!parent.open) {
				tree.expandNode(parent, true, true);
			}
			tree.checkNode(node, true, true);
		}
		// end
	});
	$("#con").height(pHeight - 230);
	// tab
	$('#tit p').click(function() {
		var i = $(this).index();// 下标第一种写法
		$(this).addClass('select').siblings().removeClass('select');
		$('#con>li').eq(i).show().siblings().hide();
	});
});

Checkbix.init();// 复选框
// 复选框全选事件
function cli(Obj) {
	var collid = document.getElementById("all");
	var coll = document.getElementsByName(Obj);
	if (collid.checked) {
		for ( var i = 0; i < coll.length; i++)
			coll[i].checked = true;
	} else {
		for ( var i = 0; i < coll.length; i++)
			coll[i].checked = false;
	}
}

function onCheck(e, treeId, treeNode) {
	tossText = "";
	toss = "";
	senderAuthIds = [];
	// 得到所有选中的节点
	var treeObj = $.fn.zTree.getZTreeObj("tree"), nodes = treeObj
			.getCheckedNodes(true);
	// for (var i=0;i<nodes.length;i++) {
	// var halfCheck = nodes[i].getCheckStatus();
	// if (!halfCheck.half){ //--不为半选
	// tossText = tossText + "," + " <"+nodes[i].name+"> ";
	// }
	// toss = toss +","+nodes[i].id;
	// }
	for ( var i = 0, k = 0; i < nodes.length; i++) {
		var halfCheck = nodes[i].getCheckStatus();
		if (!halfCheck.half) { // --不为半选
			// 判断是否存在子节点
			if (nodes[i] != null && nodes[i].children != null
					&& nodes[i].children.length != null
					&& nodes[i].children.length > 0) {
				var childrenNodes = nodes[i].children;
				// 字符串中子节点的名称，ID
				if (nodes[i].isParent == true) {
					for ( var j = 0; j < childrenNodes.length; j++) {
					}
				}
			} else if (nodes[i].id >0) {
				senderAuthIds[k] = nodes[i].id;
				k++;
				if (tossText == "") {
					tossText = nodes[i].name;
					toss = nodes[i].id;
				} else {
					tossText += "," + nodes[i].name;
					toss += "," + nodes[i].id;
				}
			}
		}
	}
}

// 重置
function resetRen() {
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.checkAllNodes(false);
}

// $("input[name='userName']:checked").each(function(){
// $(this).attr("checked",false);
// });

function addPeo() {
	senderAuthIds = [];
	var leixing = $("#leixing").val();
	if (leixing == 3) {
		onCheck();
	} else if (leixing == 1) {
		var k = 0;
		tossText == "";
		$(".checkbix").each(function() {
			if ($(this).is(":checked")) {
				if (tossText == "") {
					tossText = $(this).attr("data-text");
				} else {
					tossText += "," + $(this).attr("data-text");
				}
				senderAuthIds[k] = $(this).val();
				k++;
			}
		});
	}
	parent.addNamesInput(tossText, senderAuthIds, leixing);
}

function select1() {
	$("#leixing").val(3);
}
function select2() {
	$("#leixing").val(1);
}

function GetRequest() {
	var url = decodeURI(location.search); // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}