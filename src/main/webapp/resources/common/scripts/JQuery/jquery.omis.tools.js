"use strict";
/** OMIS jquery ui.tools implementations.
 * author Ryan Johns
 * author Stephen Abson
 * version 0.1.0 (Feb 26, 2014)
 * since OMIS 3.0 */
if (typeof ui === 'undefined') {
	var ui = new Object();
}

ui.Tools = new function() {
	this.AJAX = function(url, target,onSuccess, type, dataType, options) {
		$.ajax($.extend({
			dataType: dataType,
			type: type,
			url: url,
			cache:false
		},options))
		.success(onSuccess).fail(function( jqXHR, textStatus, errorThrown ) {
			$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
		});
	};
	
	this.DATE_PICKER = function(target, options) {
		$(target).datepicker($.extend({changeMonth: true, changeYear: true},options));
	};
	
	this.SCROLL_TABLE = function(target, filter, options) {
		if (!$(target).hasClass("dataTable")) {
			var table = $(target).dataTable($.extend({ 
				"sScrollY": "120px",
		        "bPaginate": false,
		        "sDom": '<"top">rt<"bottom"li><"clear">',
		        "bProcessing" : true,
		        "oScroller": {"loadingIndicator": false},
		        "bScrollCollapse": true}, options));
			
			if (filter !== 'undefined') {
				$(filter).on('keyup', function() { 
					table.fnFilter(this.value);
				} );
			}		
		}
	};
	
	this.AJAX_LINK = function (anchor, target,success,options) {
		if (!$(anchor).hasClass("ajaxLink")) {
			target = typeof target !== 'undefined' ? target : '<div class=\"original\"></div>';
			success = typeof success !== 'undefined' ? success:  function(html) {
				settings.target.html(html);
				
				if (settings.target.hasClass("original")) {
					settings.target.dialog({height:'auto', width:'auto'});
				}
				
			};
		
			var settings = {};
			settings = $.extend( {
				target: $(target),
				onComplete: function() { },
				precondition: function() { return true; },
				success: success,
				async: true
			}, options);
			
			
			$(anchor).on("click", function() {
				if (settings.precondition.call()) {
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
			
			$(anchor).addClass("ajaxLink");
			
			return this;
		}};
		
	this.AJAX_SUBMIT = function(form, target, onSuccess, onFailedValidation,precondition, options) {
		onSuccess = typeof onSuccess !== 'undefined' ? onSuccess : function() {};
		onFailedValidation = typeof onFailedValidation !== 'undefined' ? onFailedValidation : function() {};
		precondition = typeof precondition !== 'undefined' ? precondition : function() { return true; };
		
		var settings = $.extend({
			target:$(target),
			onComplete: function() {},
			precondition: precondition,
			onSuccess: onSuccess,
			onFailedValidation: onFailedValidation},options);
		
		$(form).find("form").andSelf().filter("form").on("submit", function() {
			if (settings.precondition.call()) {
				$.ajax({  
					  type: "GET",
					  url: $(form).attr('action'),
					  data: $(form).serialize()
				}).success(function(html) {
							if ($(html).find('.error').length > 0) {
								settings.target.html(html);
								
								settings.onFailedValidation.call();
							} else {
								settings.target.html(html);
								
								if (settings.target.hasClass("original")) {
									settings.target.dialog({height:'auto', width:'auto'});
								}
							settings.onSuccess.call();	
							}}).fail(function( jqXHR, textStatus, errorThrown ) {
					$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
				}).complete(settings.onComplete);
			}
			return false;
		});
		return this;
	};
	
	this.DIALOG = function(element, options) {
		$(element).dialog($.extend({
			height:'auto',
			width:'auto',
			modal:true,
			draggable:false,
			title:$(element).filter('title').text()
		}, options));};
	
	this.ACTION_MENU = function(srcElement, onLoad, embedded) {
		var id = $(srcElement).attr("id");
		if (id === undefined) {
			console.warn("Action menu link has No ID");
			id = "actionMenu_" + new Date().getMilliseconds();
			$(srcElement).attr("id",id);
		}
		
		var isEmbedded = (typeof embedded === 'boolean' ? embedded : false);
		var actionMenuIncrement = 700;
		var mtargetElement = ui.Tools.RETRIEVE_RESOURCE("/resources/common/html/actionMenu.htm");
		var msrcElement = srcElement;
		ui.Tools.INSERT_AFTER(srcElement, mtargetElement);	
		var outTimer;
		var below = true; 
		
		if ($(msrcElement).parents("table.listTable").length > 0) {
			below = false;
		}
		
		
		if (!isEmbedded) {
			$(mtargetElement).on("mouseout", function() {
				clearTimeout(outTimer);
				outTimer = setTimeout(function(){ 
						if ($(mtargetElement).find("input:focus").size() == 0) {
							disableMenu($("#"+findActionMenuLinkId(mtargetElement)).get(0),mtargetElement); 
						}
					}, actionMenuIncrement);
			});
			
			$(mtargetElement).on("blur", function() {
				clearTimeout(outTimer);
				if ($(mtargetElement).find(":focus").size() == 0) {
					disableMenu($("#"+findActionMenuLinkId(mtargetElement)).get(0),mtargetElement); 
				}
			});
			
			/*$(document).not(mtargetElement).click(function() {
				disableMenu($("#"+findActionMenuLinkId(mtargetElement)).get(0),mtargetElement);
			})*/
		
			$(mtargetElement).on("mouseover", function() {
				clearTimeout(outTimer);
			});
			
			$(msrcElement).on("mouseover", function() {
				clearTimeout(outTimer);
			});
			
			$(mtargetElement).on("mouseenter", function() {
				clearTimeout(outTimer);
			});
			
			$(msrcElement).on("mouseenter", function() {
				clearTimeout(outTimer);
			});
			
			$(mtargetElement).on("focusin", function() {
				clearTimeout(outTimer);
			});
		}
		
		$(mtargetElement).addClass(id + "ActionList");
		$(mtargetElement).addClass("hide"); 
		
		if ($(msrcElement).hasClass("actionMenuItem")) {
			$(mtargetElement).addClass("actionListContainer");
		}
		
		$(msrcElement).on("click", function(event) {
			var targetElement = $("."+ id + "ActionList").first();
			var link = event.target;
			
			if (targetElement.hasClass("show")) {
				disableMenu(link,targetElement);
			} else {
				if (!isEmbedded) {
					$(".actionMenuItem.open").removeClass("open");
					$(".actionListContainer").removeClass("show");
					$(".actionListContainer").addClass("hide");
				}
				
				$.ajax({  
					type: "GET",
					cache: false,
					url: $(event.target).attr('href')
				}).success(function(html) {
					targetElement.html(html);
					enableMenu(link,targetElement);
					
					/*positioning open*/
					$(targetElement).position({
						my: "left top-1px",
						at: "left bottom",
						collision: "none",
						of:link
					});
					/*positioning close*/
					if (below) {
						targetElement.find("a.actionItem").each(function(index, element) {
							ui.Tools.ACTION_MENU(element, true);
						});
					}
					
					assignNewTabLinks();
					if (typeof onLoad === 'function') {
						onLoad(event, targetElement);
					}
					
					targetElement.find("a.actionItem").each(function(index, element) {
						ui.Tools.ACTION_MENU(element,function() {}, true);
					});
				}).fail(function( jqXHR, textStatus, errorThrown ) {
						$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
				});
			}
				return false;
			});};
		
		this.RETRIEVE_RESOURCE = function(file) {
			var ret = "";
			$.ajax({ 
					type: "GET",
					 async: false,
				    url: config.ServerConfig.getContextPath() + file
			}).success(function(result) {
				ret = $(result)[0]; 
			}).fail(function( jqXHR, textStatus, errorThrown ) {
				$(document.body).html(errorThrown + " "+ textStatus + jqXHR.responseText); 
			});
			
			return ret;
		};
		
		this.INSERT_AFTER = function(element, newElement) {
			return element.parentNode.insertBefore(newElement, element.nextSibling);
		};
		
		this.GET_ELEMENTS_BY_CLASS_NAME = function(className) {
			return $("."+className);
		};

		/*Author Jonny Santy 9/9/2016 
		 * This function makes a textarea expand to fit
		 * the initial text and/or when a user changes it*/
		this.FIT_TEXTAREA_HEIGHT = function(jq_element)
		{
			    jq_element.each(function(i,e){
			    	var BUFFER = 15;
			        $(e).height(0);
			        $(e).height(e.scrollHeight+BUFFER);
			        });
			    /*implement with the following two lines:
		        $('.foo').keydown(ui.Tools.FIT_TEXTAREA_HEIGHT($('.foo')));
				$('.foo').keydown();
				*/
		};
		
		this.MOUSE_OUT_HIDE = function(elmnt) {
			var outTime = 600;
			var outTimer;
			$(elmnt).on("mouseout", function() {
				clearTimeout(outTimer);
				outTimer = setTimeout(function(){ 
					if ($(elmnt).find("input:focus").size() == 0) {
						hide(elmnt); 
					}
				}, outTime);
			});
		
			$(elmnt).on("focusout", function(event){
				hide(elmnt);
			});
		
			$(elmnt).on("blur", function() {
				clearTimeout(outTimer);
				if ($(elmnt).find(":focus").size() == 0) {
					hide(elmnt); 
				}
			});
		
			$(elmnt).on("mouseover", function() {
				clearTimeout(outTimer);
			});
			
			
			$(elmnt).on("mouseenter", function() {
				clearTimeout(outTimer);
			});
			
			
			$(elmnt).on("focusin", function() {
				clearTimeout(outTimer);
			});
	};
};

function checkDisableActionMenu() {
}

function assignNewTabLinks() {
	var newTabLinks = $(document).find("a.newTab");
	
	for (var x = 0; x < newTabLinks.length; x++) {
		newTabLinks[x].onclick =function(event) {
			var messageData;
			try {
				if ($(this).hasClass("reportLink")) {
					messageData = {domain:'TABS', 
			                   functionName:'NEW_TAB',
			                   url:this.href,
			                   title:this.innerHTML};
				} else {
					if ($(this).hasClass("tabs2")) {
						messageData = {domain:'TABS', 
		                   functionName:'NEW_TAB',
		                   url:this.href,
		                   tabArea:'tabs2'};
					} else {
						messageData = {domain:'TABS', 
						                   functionName:'NEW_TAB',
						                   url:this.href};
					}
				}
				var origin = window.location.origin;
				
				/* IE 11 Fix.*/
				if (typeof origin == 'undefined') {
					window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
					origin = window.location.origin;
				}
				window.top.postMessage(messageData, origin);
				return false;
			} catch (e) {/* Not compatible*/}
		};
	} 
}

function disableMenu(link, trgt) {
	$(link).removeClass("open");
	$(trgt).removeClass("show");
	$(trgt).addClass("hide");
}

function enableMenu(link, trgt) {
	$(link).addClass("open");
	$(trgt).addClass("show");
	$(trgt).removeClass("hide");
}


function findActionMenuLinkId(trgt) {
	var classes = trgt.className.split(" ");
	var idClass;
	var result;
	for (var x = 0; x < classes.length; x++) {
		if (classes[x].indexOf("ActionList") != -1) {
			idClass = classes[x].trim().replace("ActionList", "");
			x = classes.length;
		}
	}
	return idClass;
}

function show(element) {
	$(element).addClass("show");
	$(element).removeClass("hide");
}

function hide(element) {
	$(element).removeClass("show");
	$(element).addClass("hide");
}

/**
 * jQuery implementation of UI function to append HTML.
 * 
 * @param elt element
 * @param newHtml new HTML to append
 */
ui.appendHtmlImpl = function(elt, newHtml) {
	$(elt).append(newHtml);
};