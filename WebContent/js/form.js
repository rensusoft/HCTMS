$(function(){

//��������

    $('.drop_btn').on('click',function(e) {
        var dropOpen = $(this).parent('.drop-con').hasClass('dropBox-open');
        if (dropOpen){
            $('.drop-con').removeClass('dropBox-open');
        }else{
            $('.drop-con').removeClass('dropBox-open');
            $(this).parent('.drop-con').addClass('dropBox-open');
            $(document).one("click", function(){
                $(".drop-con").removeClass('dropBox-open');
            });
        }
        e.stopPropagation();
    });
    $('.dropBox').on('click', function(e){
        e.stopPropagation();
    });



});