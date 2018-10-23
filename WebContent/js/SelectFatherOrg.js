/**
 * Created by Administrator on 2015/11/16.
 */
function SelectFatherOrg(settings){
    this.selectUlId = "SelectFatherOrg";
    if(settings.selectUlId!=null){
        this.selectUlId = settings.selectUlId;
    }
    this.jsonData = settings.jsonData;
    this.mcInputId = settings.mcInputId;
    this.contentDivId = settings.contentDivId;
    var _this = this;

    this.onClick=function(e, treeId, treeNode){
        var zTree = $.fn.zTree.getZTreeObj(_this.selectUlId),
            nodes = zTree.getSelectedNodes(),
            v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#"+_this.mcInputId);
        cityObj.attr("value", v);
    }
    this.beforeClick=function(treeId, treeNode) {
        var check = (treeNode);
        return check;
    }
     this.setting = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: this.beforeClick,
            onClick: this.onClick
        }
    };
    _this = this;

    this.getJsonTree = function(){
        $(document).ready(function(){
            $.fn.zTree.init($("#"+_this.selectUlId), _this.setting,_this.jsonData);
        });
        this.showMenu();
    };

    this.showMenu = function(){
        var orgObj = $("#"+this.mcInputId);
        var orgOffset = $("#"+this.mcInputId).offset();
        $("#"+this.contentDivId).css({left:orgOffset.left + "px", top:orgOffset.top
        + orgObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", this.onBodyDown);
    };

    this.onBodyDown = function(event){
        if (!(event.target.id == _this.mcInputId || event.target.id == _this.contentDivId || $(event.target).parents("#"+_this.contentDivId).length>0)) {
            _this.hideMenu();
        }
    };
    this.hideMenu = function(){
        $("#"+_this.contentDivId).fadeOut("fast");
        $("body").unbind("mousedown",this.onBodyDown);
    };


}
