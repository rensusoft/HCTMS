$(function () {

//菜单添加
    $('body').on('click', '[addtabs]', function () {
        addTabs({
            id: $(this).attr("addtabs"),
            title: $(this).attr('title')?$(this).attr('title'):$(this).html(),
            content: $(this).attr('content'),
            url: $(this).attr('url'),
            close: true
        });
    
    });


    $(".nav-tabs").on("click", ".close-tab", function () {
        id = $(this).prev("a").attr("aria-controls");
        closeTab(id);
    });

});

var addTabs = function (obj) {
	if(obj.url == "/HCTMS"){
		alert("未维护地址！");
		return;
	}

    id = "tab_" + obj.id;
    $("#menuId").val(obj.id);

    $(".active").removeClass("active");

    //如果TAB不存在，创建一个新的TAB
    if (!$("#" + id)[0]) {
        //创建新TAB的title
        title = $('<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + obj.title + '</a></li>');
        //是否允许关闭
        if (obj.close) {
            title.append(' <i class="close-tab glyphicon glyphicon-remove"></i>');
        }
        //创建新TAB的内容
        content = $('<div role="tabpanel" class="tab-pane" id="' + id + '" style="height:100%;"></div>');
        //是否指定TAB内容
        if (obj.content) {
            content.append(obj.content);
        } else {//没有内容，使用IFRAME打开链接
            content.append('<iframe src="' + obj.url + '" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling-x="no" scrolling-y="auto" allowtransparency="yes"></iframe></div>');
        }
        //加入TABS
        $(".nav-tabs").append(title);
        $(".tab-content").append(content);
    }
    //激活TAB
    $("#tab_" + id).addClass('active');
    $("#" + id).addClass("active");
};



var closeTab = function (id) {
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') == "tab_" + id) {
        $("#tab_" + id).prev().addClass('active');
        $("#" + id).prev().addClass('active');
    }
    //关闭TAB
    $("#tab_" + id).remove();
    $("#" + id).remove();
//    tabsdrop($('.nav-tabs'));
};

