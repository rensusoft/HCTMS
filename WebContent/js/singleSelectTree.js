function singleSelectTree(obj){
    var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		view: {
			showIcon: showIconForTree,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick,
			onCheck: onCheck
		}
	}

	function showIconForTree(treeId, treeNode) {
		return !treeNode;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(obj.selTree);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(obj.selTree),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#" + obj.inputText);
		cityObj.attr("value", v);
	}
	var cityObj = $("#" + obj.inputText);
	var cityOffset = $("#" + obj.inputText).offset();
	$("#" + obj.treeContent).css({
		background:'#fff',
		border:'1px solid #ccc',
		position:'absolute',
		overflow:'auto',
		width:'200px',
		top:'25px',
		'max-height':'250px',
		'z-index':'999'
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
	function hideMenu() {
		$("#" + obj.treeContent).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == obj.selBtn || event.target.id == obj.inputText || event.target.id == obj.treeContent || $(event.target).parents("#" + obj.treeContent).length>0)) {
			hideMenu();
		}
	}
	$.fn.zTree.init($("#" + obj.selTree), setting, obj.znodes);
}