$(function(){
    var li = $('.navbar-nav li');
    li.each(function(){
        $(this).click(function(){
            if( $(this).hasClass('opens')){
                $(this).find('ul').slideUp(350);
                $(this).removeClass('opens');
            }else{
                $(this).find('ul').slideDown(350);
                $(this).addClass('opens');
            }
        })
    });
})