$(function(){
	$('body').on('click', '[addtabs]', function () {
        addTabs({
            id: $(this).attr("addtabs"),
            title: $(this).attr('title')?$(this).attr('title'):$(this).html(),
            content: $(this).attr('content'),
            url: $(this).attr('url'),
            close: true
        });
    });

    //点击子菜单自动生成新页面
    $('.dropBox').on('click', '[addtabs]', function () {
        addTabs({
            id: $(this).attr("addtabs"),
            title: $(this).attr('title')?$(this).attr('title'):$(this).html(),
            content: $(this).attr('content'),
            url: $(this).attr('url'),
            close: true
        });
        //alert('1');
    });


});

var addTabs = function (obj) {

    id = "tab_" + obj.id;

    $('.active',window.parent.document).removeClass("active");

    //如果TAB不存在，创建一个新的TAB
    if (!$("#" + id,window.parent.document)[0]) {
        //创建新TAB的title
        title = $('<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + obj.title + '</a></li>');
        //是否允许关闭
        if (obj.close) {
            title.append('<i class="close-tab glyphicon glyphicon-remove"></i>');
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
        $('.nav-tabs',window.parent.document).append(title);
        $('.tab-content',window.parent.document).append(content);
    }

    //激活TAB
    $('#tab_' + id,window.parent.document).addClass('active');
    $('#' + id,window.parent.document).addClass('active');
};

