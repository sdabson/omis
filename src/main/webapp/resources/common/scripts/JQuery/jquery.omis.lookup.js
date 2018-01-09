/* Generic AJAXified lookup.
* version 1.0 (Apr 12, 2013)
* By Ryan Johns, Joel Norris
* dependencies: JQuery (version 1.7), JQuery UI (version 1.8.21), 
* configServer.js
*
* usage: $(element).lookup(); 
* @Deprecated use applySearch(input, hidden, searchImplementation, [?options])*/

(function( $ ) {
	/** @Deprecated */
	$.fn.lookup = function (options) {
		var element = this;
		var settings = {};
		settings = $.extend( {
			bundle : "omis.person.msgs.person",
			callBackFunction : function(data, element) {
				//do nothing
			},
		    assignLabel : "assign",
		    autoLoad : false,
		    ajaxSuccess : function(html) {
		    	$(element).html(html);
		    	
		    	var func = (typeof settings.callBackFunction == 'function') ?
			    settings.callBackFunction : new Function(settings.callBackFunction);
				$(element).find("a").each(function() {
					var a = this;
					$(this).on("click", function() {
						$.getJSON($(this).attr("href"),
								function(data) {
									func(data, element, a);
								});
						
						return false;
					});
				});
		    }
		}, options);
		
		this.runLookup = function(url, loadAutomatically) { 
			if (loadAutomatically)
				settings.autoLoad = true;
			else
				settings.autoLoad = false;
			
			$.ajax({
				type:'GET',
				url:url,
				cache:false
			}).success(settings.ajaxSuccess)
			.fail(function( jqXHR, textStatus, errorThrown ) {
				$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
			}); //AJAX success
		};	//runLookup
		return this;
	}; //lookup
}) ( jQuery );