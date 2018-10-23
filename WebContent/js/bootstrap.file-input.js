/**
 * 利用label辅助点击file框
 * 可完美解决在ie中的“访问限制”问题（即必须是用户点击才能进行文件上传）
 * 仅对class为custom-file-input应用
 */
$.customFileInput = function() {
    $('input[type=file].custom-file-input').each(function(i,elem){
        var $input = $(this);

        if(!$input.prop("id")) {
            $input.prop("id", "file_" + i);
        }
        var btnTitle = "浏览";
        if ($input.prop('title')) {
            btnTitle = $input.prop('title');
        }

        $input.wrap("<div class='file-input'></div>");

        var $btn = $('<a class="btn btn-primary btn-upload"><label for="' + $input.prop("id") + '">' + btnTitle + '</label></a>');
        $input.before($btn);
        $input.addClass("file-input-opacity");

        $btn.after('<span class="name">未选择文件。</span>');

        $input.change(function() {
            $btn.next('.name').remove();
            //只显示文件名，路径部分截取掉
            $btn.after('<span class="name">'+$(this).val()+'</span>');
        });

    });
};
$(function() {
    $.customFileInput();
    //修正firefox label不能触发输入框点击
    if(navigator.userAgent.indexOf("Firefox") > 0) {
        $(document).on('click', 'label', function(e) {
            if(e.currentTarget === this && e.target.nodeName !== 'INPUT') {
                $(this.control).click();
            }
        });
    }

});