
	function singleTree(singleObj){
        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: showIconForTree,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            callback: {
                onClick: onClick
            }
        };

        function showIconForTree(treeId, treeNode) {
            return !treeNode;
        };

        function onClick(e,treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj(singleObj.treeName);
            zTree.expandNode(treeNode);
        };

        var t = $('#' + singleObj.treeName);
        t = $.fn.zTree.init(t, setting, singleObj.zNodes);
    }