$(document).ready(function() {
    $("a")[0].click();
});

function clickNum(){
	$(".list li").click(function() {
		$(this).addClass('border');
		$(this).siblings().removeClass('border');
	});
}

//关闭弹出层
function closeMyWindows(){
	layer.closeAll();
}