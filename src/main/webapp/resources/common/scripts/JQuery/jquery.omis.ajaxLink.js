/* Executes requests through ajax.
 * version 1.1 (Jan 21, 2014)
 * By Ryan Johns
 * dependencies: JQuery (version 1.7), JQuery UI (version 1.8.21)
 * param = {
 *   target: element to target response.
 *   onComplete: handler to fire upon completion.
 *   precondition: function handler must evaluate to true/false. The result 
 *   determines whether or not to fire ajax.
 *   success: function to fire upon completion of ajax call. 
 *   }*/

(function( $ ) {
	
		/** Ajax links.
		 * success event handler for ajax, default behavior routes response to target.
		 * target where to route response, default constructs dialog.
		 * onComplete event handler for completion.  */ 
		$.fn.ajaxLink = function (options) {
			var element = this;
			var settings = {};
			settings = $.extend( {
				target: $('<div class=\"original\"></div>'),
				onComplete: function() { },
				precondition: function() { return true; },
				success: function(html) {
					settings.target.html(html);
					
					if (settings.target.hasClass("original")) {
						settings.target.dialog({height:'auto', width:'auto'});
					}
					
				}
			}, options);
			
			if (!$(element).hasClass("ajaxLink")) {
			$(element).on("click", function() {
				if (settings.precondition()) {
					$.ajax({
						type:'GET',
						url:$(this).attr('href')
					}).success(settings.success)
					.fail(function( jqXHR, textStatus, errorThrown ) {
						$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
					}).complete(settings.onComplete);
				}
				return false;
			});
			
			$(element).addClass("ajaxLink");
			
			return this;
			}
		};

	
		/** Ajax submission.
		 * success event handler for ajax, default behavior routes response to target.
		 * target where to route response, default constructs dialog.
		 * onComplete event handler for completion.  */
		$.fn.ajaxSubmit = function (options) {
			var element = this;
			
			
			var settings = $.extend({
				target:$(element),
				onComplete: function() {},
				success: function() {},
				precondition: function() { return true; },
				onSuccessfulValidation: function() {},
				onFailedValidation: function() {}},options);
			
			$(element).find("form").andSelf().filter("form").on("submit", function() {
				
				if (settings.precondition()) {
					$.ajax({  
						  type: "POST",
						  url: $(this).attr('action'),
						  data: $(this).serialize()
					}).success(function(html) {
								if ($(html).find('.error').length > 0) {
									$(element).html(html);
									
									settings.onFailedValidation.call();
								} else {
									settings.target.html(html).fadeIn();
									
									if (settings.target.hasClass("original")) {
										settings.target.dialog({height:'auto', width:'auto'});
									}
									
								settings.onSuccessfulValidation.call();	
	
								}
					}).fail(function( jqXHR, textStatus, errorThrown ) {
						$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
					}).complete(settings.onComplete);
				}
				
				return false;
			});
			
			return this;
		};
}) (jQuery);