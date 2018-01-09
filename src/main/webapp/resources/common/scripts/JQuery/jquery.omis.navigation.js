// Generic AJAXified lookup.
// version 1.0 (Jun 11, 2013)
// By 
// dependencies: JQuery (version 1.7), JQuery UI (version 1.8.21)
//
// usage: $(element).navigation()

(function( $ ) {
	$.fn.navigation = function(options) {
		var element = this;
		
		var settings = $.extend( {
			navList : ""
		}, options);
		
		this.addContent = function(label,name, url) {
			
			li = document.createElement('li');
			iframe = document.createElement('iframe');
			divContenter = document.createElement('div');
			
			$(li).attr('id',name);
			$(li).html(label);
			
			$(divContenter).addClass("fullscreen");
			$(iframe).addClass("fullscreen");
			$(divContenter).attr('id','content_'+name);
			
			
			$(this).children().hide();
			$(settings.navList).append(li);
			$(divContenter).append(iframe);
			$(element).append(divContenter);
			$(iframe).attr('src',url);
			
			$(settings.navList).children().removeClass("active");
			$(li).addClass("active");
			
			$(li).click( function() {
				
				$(settings.navList).children().removeClass("active");
				$(this).addClass("active");
				content = 'content_'+$(this).attr('id');
				
				$("li.active").effect('transfer', { to: "#content" , className: "ui-effects-transfer" }, 1000, function() {
					$("#content").children().hide();
					$('#'+ content).show();	
					
				});
				
			
			});
		};
		
		this.removeContent = function(name) {
			
		};
		return this;
	};
	
})(jQuery, window);