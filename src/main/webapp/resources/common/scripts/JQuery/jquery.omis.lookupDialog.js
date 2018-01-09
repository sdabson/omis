/* Generic AJAXified lookup.
 * version 1.0 (Apr 12, 2013)
 * By Ryan Johns, Joel Norris
 * dependencies: JQuery (version 1.7), JQuery UI (version 1.8.21), 
 * configServer.js, MessageResolver.js
 *
* usage: $(element).lookupDialog(); 
* @Deprecated use applySearch(input, hidden, searchImplementation, [?options]) */


(function( $ ) {
	
	$.fn.lookupDialog = function (options) {
		
		var element = this;
		var dialog = this;
		if (!element.hasClass("lookupDialog")) {
			element.addClass("lookupDialog");
			/* Settings what the plug-in uses to operate.
			 * returnParams - name - the name of what to expect back from service.
			 *                label - what to label it.
			 * url - URL of service being called.
			 * searchParams - paramName - request parameter required by service.
			 *                label - what to display to the user for the input.
			 * findByLabel - label to display for the search dialog. 
			 * callBackFunction - function to perform set.
			 * callBackId - optional ID value for call back to use.
			 * callBackString - optional String for call back to use. */
			var settings = $.extend( {
				bundle : "omis.person.msgs.person",
				returnParams : [{name:'firstName',label:"firstName"},
				                {name:'middleName',label:"middleName"},
				                {name:'lastName', label:"lastName"}],
				url : config.ServerConfig.getContextPath() 
						+ '/personSearch/searchByName.json',
				searchParams : [{label:"firstName", 
					             paramName:'name1'},
				                {label:"lastName", 
					             paramName:'name2'}],
				findByLabel : "findByName",
				assignLabel : "assign",
				callBackString : ['firstName', 'lastName'],
				callBackID : 'personId',
				callBackFunction : function(json, element) {
					$(element).val(json.personId);
				}
	
			},	options);
			
			element.lookupDialog.removeDialog = function() {
				$(dialog).dialog("close");
				$(dialog).remove();
			};
			
			var lookUpMessageResolver = new common.MessageResolver(settings.bundle);
			/* elements needed for lookup. */
			var searchLink = document.createElement("button");
			searchLink.type="button";
			$(searchLink).html(lookUpMessageResolver.getMessage(settings.findByLabel));
			$(searchLink).insertAfter(this);
			$(searchLink).addClass("searchButton");
			var resultContainer = document.createElement('div');
			var resultList = document.createElement('div');
			var lookUp = $(resultList).lookup(settings);
			
			var resultType = document.createElement('h3');
			
			$(resultContainer).append(resultType);
			$(resultContainer).append(resultList);
			
				
				/* Make call to service, and present the user with the resulting
				 * data. */  
			var searchFunction = function (e) {
				var length = 0;
				var url = settings.url;
					
				/* Append user input to url as request parameters. */
				for (var x = 0; x < settings.searchParams.length; x++) {
					if ( x == 0 ) { url += '?'; }
					else { url += '&'; }
					
					url += settings.searchParams[x].paramName + 
					'=' + $(e.data.input).val();
						
					length += $(e.data.input).val().length;
				}
					
					
					
				/* Perform AJAX call to service with url constructed above. 
				 * On success create table with results and post to result list
				 * for user. */
				if (length > 4) {
					clearTimeout(element.data('timer'));
					var wait = setTimeout(function() {
						lookUp.runLookup(url);
					},400);
					element.data('timer', wait);
				}};
				
				/* Construct dialog for user input with defined searchParams. */
				dialog = document.createElement('div');
				for (var x = 0; x < settings.searchParams.length; x++) {
					var temp = document.createElement('label');
					var tempInput = document.createElement('input');
					var span = document.createElement('span');
					var divInput = document.createElement('div');
					
					$(tempInput).type = 'text';
					$(tempInput).attr('id', settings.searchParams[x].paramName);
					$(temp).html(lookUpMessageResolver.getMessage(settings.searchParams[x].label));
					$(temp).attr('for', settings.searchParams[x].paramName);
					
					$(tempInput).keyup({'input': tempInput},searchFunction);
					
					$(span).append(temp);
					$(span).append(tempInput);
					$(divInput).append(span);
					$(dialog).append(divInput);
				}
				
				$(dialog).append(resultContainer);
				$(dialog).insertAfter(this);
				$(dialog).hide();
				
				/* Show dialog when user clicks on searchLink. */
				$(searchLink).on('click', function() {
					$(dialog).dialog({height:'auto',width:'auto', title:lookUpMessageResolver.getMessage(settings.findByLabel)});
				});
		}
			return this;
	};	
	
	
})( jQuery );

 
var unspecifiedSearch = unspecifiedSearch || {
		bundle : "omis.person.msgs.person",
		returnParams : [{name:'firstName',label:'firstName'},
		                {name:'middleName',label:'middleName'},
		                {name:'lastName', label:'lastName'}],
		url : config.ServerConfig.getContextPath() 
				+ '/personSearch/searchByNonSpecified.json',
		searchParams : [{label:'searchCriteria', 
			             paramName:'searchCriteria'}],
		findByLabel : 'findBySearch',
		callBackString : ['firstName', 'lastName'],
		callBackID : 'personId',
		callBackFunction : function(json, element) {
			$(element).val(json.personId);
		}

	};

var offenderSearchSingleMode = offenderSearchSingleMode || {
		bundle : "omis.person.msgs.person",
		returnParams : [{name:'offenderNumber', label:'offenderNumber'},
		                {name:'firstName',label:'firstName'},
		                {name:'middleName',label:'middleName'},
		                {name:'lastName', label:'lastName'}],
		url : config.ServerConfig.getContextPath() 
				+ '/personSearch/searchOffenderByNonSpecified.html',
		searchParams : [{label:'searchCriteria', 
			             paramName:'searchCriteria'}],
		callBackString : ['firstName', 'lastName'],
		callBackID : 'personId',
		callBackFunction : function(json, element) {
			location.href = config.ServerConfig.getContextPath()+'/offender/profile.html?offender='+ json.personId;
		}
};

var offenderSearch = offenderSearch || {
		bundle : "omis.person.msgs.person",
		returnParams : [{name:'offenderNumber', label:'offenderNumber'},
		                {name:'firstName',label:'firstName'},
		                {name:'middleName',label:'middleName'},
		                {name:'lastName', label:'lastName'}],
		url : config.ServerConfig.getContextPath() 
				+ '/personSearch/searchOffenderByNonSpecified.html',
		searchParams : [{label:'searchCriteria', 
			             paramName:'searchCriteria'}],
		callBackString : ['firstName', 'lastName'],
		callBackID : 'personId',
		callBackFunction : function(json, element) {
			newTab(config.ServerConfig.getContextPath()+'/offender/profile.html?offender='+ json.personId);
		}
	};

var userSearch = userSearch || {
		bundle : "omis.person.msgs.person",
		returnParams : [{name:'firstName',label:'firstName'},
		                {name:'middleName',label:'middleName'},
		                {name:'lastName', label:'lastName'}],
		url : config.ServerConfig.getContextPath() 
				+ '/personSearch/searchUsersByNonSpecified.html',
		searchParams : [{label:'searchCriteria', 
			             paramName:'searchCriteria'}],
		findByLabel : 'findBySearch',
		callBackString : ['firstName', 'lastName'],
		callBackID : 'userId',
		callBackFunction : function(json, element) {
			$(element).val(json.personId);
		}
};

var tabbedOffenderSearch = tabbedOffenderSearch || {	
		bundle : "omis.person.msgs.person",
	returnParams : [{name:'offenderNumber', label:'offenderNumber'},
	                {name:'firstName',label:'firstName'},
	                {name:'middleName',label:'middleName'},
	                {name:'lastName', label:'lastName'}],
	url : config.ServerConfig.getContextPath() 
			+ '/personSearch/searchOffenderByNonSpecified.html',
	searchParam : {label:'searchCriteria', 
		             paramName:'searchCriteria'},
	callBackString : ['firstName', 'lastName'],
	callBackID : 'personId',
	callBackFunction : function(json, element) {
		newTab(config.ServerConfig.getContextPath()+'/offender/profile.html?offender='+ json.personId);
		//$(resultArea).fadeOut();
	}
};