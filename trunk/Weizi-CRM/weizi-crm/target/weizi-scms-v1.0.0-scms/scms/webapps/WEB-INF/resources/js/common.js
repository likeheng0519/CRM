$(function () {
	$(".navbar-nav li a").each(function () {
		if (this.href == window.location.href) {
			$(this).parent('li').addClass("current");
		}
	});
    $(function(){
        $(".filter").hide();
        $(".plr15.page").hide();

    });
    $(".advanced-search").click(function(){
        if($(".search-more").is(":visible")==false){
            $(".search-more").show();
            $(".js-search-btn").hide();
        }else{
            $(".search-more").hide();
            $(".js-search-btn").show();
        }
    })
}); 