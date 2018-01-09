// Provides scroll functionality.
// version 1.0 (Oct 07, 2013)
// By Ryan Johns
// dependencies: JQuery (version 1.7), JQuery UI (version 1.8.21),
//
// usage $(container).scrollContainer();

(function( $) {
	$.fn.scrollContainer = function(options) {
		var element = this;
		
		var settings = $.extend({
			scrollX:true,
			scrollY:true,
			scrollAreaCss:{},
			elementCss:{ overflow:"hidden",
					overflowX:"hidden",
					display:"inline-block"}
		}, options);
		
		var scrollArea = $('<div class="scrollContainer"></div>');
		
		scrollArea.css(settings.scrollAreaCss);
		//element.css(settings.elementCss);
		
		element.wrap(scrollArea);
		
		var closeAll = $('<a class="removeAll tabs"></span>');
		
		if (settings.scrollX) {
			var scrollLeft = $('<span class="left_arrow tabs"></span>');			
			var scrollRight = $('<span class="right_arrow tabs"></span>');
			
			scrollLeft.click(function() {
				element.animate({scrollLeft:"-="+element.width()},500);
			});
		
			scrollRight.click(function() {
				element.animate({scrollLeft:"+="+element.width()},500);
			});
	
			element.parent().prepend(scrollLeft);
			element.parent().append(closeAll);
			element.parent().append(scrollRight);
		}
		
		if (settings.scrollY) {
			var scrollUp = $('<span class="up_arrow"></span>');
			var scrollDown = $('<span class="down_arrow"></span>');
			
			scrollUp.click(function() {
				element.animate({scrollTop:"-=80px"},500);
			});
			
			scrollDown.click(function() {
				element.animate({scrollTop:"+=80px"},500);
			});
			
			element.parent().prepend(scrollUp);
			element.parent().append(scrollDown);
		}
		
		closeAll.click(function() {
			
			$(this).parent().find("a.remove").each(function(x,xel) {
				$(xel).trigger("click");
			});
			return false;
		});
		
		return this;
	};
})(jQuery);