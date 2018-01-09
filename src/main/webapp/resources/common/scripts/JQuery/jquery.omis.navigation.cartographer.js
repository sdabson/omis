/** Sets up behavior for work items.
 * version 1.0 (Jun 20, 2013)
 * By Ryan Johns
 * dependencies: JQuery (version 1.7), QueueMatrix.js */
(function( $ ) {
	$.fn.cartographer = function(options) {
		
		/* Default settings */
		var settings = $.extend({
			navigationMatrix : new navigationMatrix(4,2),
			test: "test",
			contentElement : $("<div class=\"contentElement\"></div>"),
			contentMapElement : $("<ul class=\"contentMapElement\"></ul>"),
			contentHistoryMapElement : $("<ul class=\"contentMapElement\"></ul>"),
			contentMiniMapElement : $("<ul class=\"contentMiniMap\"></ul>"),
			animationTime : 58,
			easingInFunction:'linear',
			easingOutFunction: 'linear'
		},options);
		
		/* provide hash to contain navigation items. */
		hashMap = new Object();
		/* Effects queue for transitions.*/		myQueue = new CartQueue();
		
		settings.contentMapElement.addClass('expand-up');
		settings.contentMapElement.css({'width':'100%', 'height':'30%'});
		settings.contentHistoryMapElement.css({'width':'100%', 'height':'60%'});
		settings.contentMiniMapElement.addClass('expand-up');
		settings.contentMiniMapElement.css({
			'width':'100px',
			'height':'100%',
	  		'overflow':'hidden'
		});
		
		settings.contentElement.css({'overflow':'hidden'});
		
		/** transitions to target navigation item and queues up transitions. 
		 * @param navigationItem target */
		var transitionElement = function(navigationItem) {
			 var destination = settings.navigationMatrix.getCoordinates(navigationItem);
			 
			 var horizontalMove = destination.x - settings.navigationMatrix.getCurrentCoordinates().x;
			 var verticalMove = destination.y - settings.navigationMatrix.getCurrentCoordinates().y;
			 var currentCoordinate = settings.navigationMatrix.getCurrentCoordinates();
			 var functionList = new Array();
			 var count = 1;
			 var numberOfMoves =  Math.abs(horizontalMove) + Math.abs(verticalMove);
			 
			 
			 for (var x = 0; x < numberOfMoves; x++) {
			 
				 if (horizontalMove > 0 && currentCoordinate.x + 1 <= settings.navigationMatrix.getMaxX(currentCoordinate.y)) {
					 target = transitionRight(functionList);
				 } else if (horizontalMove < 0) {
					 target = transitionLeft(functionList); 
				 } else if (verticalMove > 0) {
					 target = transitionDown(functionList); 
				 } else if (verticalMove < 0) {
					 target = transitionUp(functionList); 
				 }
				 
				 
				 count++;
				 horizontalMove = destination.x - settings.navigationMatrix.getCurrentCoordinates().x;
				 verticalMove = destination.y - settings.navigationMatrix.getCurrentCoordinates().y;
				 currentCoordinate = settings.navigationMatrix.getCurrentCoordinates();
			 }
			 
			 functionList.push(function() { activate(navigationItem); });
			 
			myQueue.appendFunctions.apply(window,functionList);
		};
		
		/** Redraws navigation map and mini-map elements.
		 * @param navigationItems. */
		var displayMaps = function(navigationItems, historicNavigationItems) {
			settings.contentMiniMapElement.empty();
			settings.contentMapElement.empty();
			
			settings.contentElement.children().css({width:'1px',height:'1px'});
			
			$.each(navigationItems,function(index, value) {
				settings.contentMiniMapElement.append(value.contentMiniMapElement);
				settings.contentMapElement.append(value.contentMapElement);
				
				if (!jQuery.contains(settings.contentElement.get(0), value.contentElement.get(0)))
					settings.contentElement.append(value.contentElement);
				
				var cols = settings.navigationMatrix.getCols();
				var rows = settings.navigationMatrix.getRows();
				
				var style = {
						 'float':'left',
						 'display':'inline',
						 'width': ''+(100/cols)-5+'%',
						 'height': ''+(100/rows)-5+'%',
						 'margin': '2.5%',
						 'padding' : '0%',
						 'cursor':'pointer'
					};
				
				value.contentMiniMapElement.css(style);
				value.contentMapElement.css(style);
				value.contentMiniMapElement.find('div').on('click.cartographer',function(e) {
					
						var element = $(e.target).parent();
					var navItem = hashMap[$(element).attr('class')];
					transitionElement(navItem);
					
				});
					
				value.contentMapElement.on('click.cartographer',function(e) {
					var element = $(e.target);
					var navItem = hashMap[$(element).parent().attr('class')];
					transitionElement(navItem);
					
				});
			});
			
			$.each(historicNavigationItems, function(index, value) {
				settings.contentHistoryMapElement.append(value.contentMapElement);
				value.contentMiniMapElement.find('div').off('click.cartographer');
				value.contentMapElement.off('click.cartographer');
				
				var cols = settings.navigationMatrix.getCols();
			
				value.contentMapElement.on('click.cartographer', function(e) {
					var element = $(e.target);
					var navItem = hashMap[$(element).parent().attr('class')];
				
					settings.navigationMatrix.renewItem(navItem);
					value.contentMapElement.off('click.cartographer');
					value.contentMapElement.css({'opacity':'1'});
					displayMaps(settings.navigationMatrix.toArray(), settings.navigationMatrix.historicToArray());
					transitionElement(navItem);
				});
				
				var style = {
						 'float':'left',
						 'display':'inline',
						 'width': ''+(100/cols)-5+'%',
						 'height': ''+(100/5)-5+'%',
						 'margin': '2.5%',
						 'padding' : '0%',
						 'cursor':'pointer',
						 'opacity':'.5'
					};
				
				value.contentMapElement.css(style);
			});
			
		};
		
		/** Marks target navigation Item elements active. 
		 * @param navigationItem target */
		var activate = function(navigationItem) {
			settings.contentMapElement.find('div').removeClass("active");
			settings.contentMiniMapElement.find('div').removeClass("active");
			
			$(navigationItem.contentMapElement).find('div').addClass("active");
			$(navigationItem.contentMiniMapElement).find('div').addClass("active");
		};
		
		/** Transitions left, adds fx to functions list.
		 * @param functions functions list */
		var transitionLeft = function(functions) {
			var navigationItem = settings.navigationMatrix.getCurrent();
			var navigationItemTarget = settings.navigationMatrix.left();			
			$(navigationItemTarget.contentElement).css({
				'left':'-'+($(window).width()+100)+'px',
				'top':'0px',
				'position':'absolute',
				'width':'100%',
				'height':'100%'
			});
			functions.push( function() { return $(navigationItem.contentElement).animate({ left: ''+($(window).width()+100)+'px'}, {queue:true, duration:(settings.animationTime+$(window).width()*.25),easing:settings.easingInFunction, done:function() {
				$(navigationItem.contentElement).css({
					'width':'1px',
					'height':'1px',
					'top':'0px',
					'left':'0px'
				});
			$(navigationItemTarget.contentElement).stop(false,true).animate({left:'0px'}, {queue:true, duration:(settings.animationTime+$(window).width()*.25),easing:settings.easingOutFunction, done:function() {activate(navigationItemTarget);}});
			
			}});});
			return navigationItemTarget;
		};
		
		/** Transitions Right, adds fx to functions list.
		 * @param functions functions list */
		var transitionRight = function(functions) {
			var navigationItem = settings.navigationMatrix.getCurrent();
			var navigationItemTarget = settings.navigationMatrix.right();
			
			$(navigationItemTarget.contentElement).css({
				'left':''+($(window).width()+100)+'px',
				'top':'0px',
				'position':'absolute',
				'width':'100%',
				'height':'100%'
				
			});
			
			functions.push(function() { return $(navigationItem.contentElement).animate({ left: '-'+($(window).width()+100)+'px'}, {queue:true, duration:(settings.animationTime+$(window).width()*.25),easing:settings.easingInFunction, done:function() {
				$(navigationItem.contentElement).css({
					'width':'1px',
					'height':'1px',
					'top':'0px',
					'left':'0px'
				});
			$(navigationItemTarget.contentElement).stop(false,true).animate({left:'0px'}, {queue:true, duration:(settings.animationTime+$(window).width()*.25),easing:settings.easingOutFunction, done:function() {activate(navigationItemTarget);}});
			
			}});});
			return navigationItemTarget;
		};
		
		/** Transitions up, adds fx to functions list.
		 * @param functions functions list */
		var transitionUp = function(functions) {
			var navigationItem = settings.navigationMatrix.getCurrent();
			var navigationItemTarget = settings.navigationMatrix.up();
			$(navigationItemTarget.contentElement).css({
				'left':'0px',
				'top':'-'+($(window).height()+100)+'px',
				'position':'absolute',
				'width':'100%',
				'height':'100%'
			});
			functions.push(function() {return $(navigationItem.contentElement).animate({ top: ''+($(window).width()+100)+'px'}, {queue:true, duration:(settings.animationTime+$(window).height()*.25),easing:settings.easingInFunction, done:function() {
				 $(navigationItem.contentElement).css({
					'width':'1px',
					'height':'1px',
					'top':'0px',
					'left':'0px'
				});
				 $(navigationItemTarget.contentElement).stop(false,true).animate({top:'0px'}, {queue:true, duration:(settings.animationTime+$(window).height()*.25),easing:settings.easingOutFunction, done:function() {activate(navigationItemTarget);}});
				 
				 }});});
			return navigationItemTarget;
		};
		
		/** Transitions down, adds fx to functions list.
		 * @param functions functions list */
		var transitionDown = function(functions) {
			var navigationItem = settings.navigationMatrix.getCurrent();
			var navigationItemTarget = settings.navigationMatrix.down();
			$(navigationItemTarget.contentElement).css({
				'left':'0px',
				'top':''+($(window).height()+100)+'px',
				'position':'absolute',
				'width':'100%',
				'height':'100%'
			});
			functions.push(function() { return $(navigationItem.contentElement).animate({ top: '-'+($(window).height()+100)+'px', height:'1px' }, {queue:true, duration:(settings.animationTime+$(window).height()*.25),easing:settings.easingInFunction, done:function() {
				$(navigationItem.contentElement).css({
					'width':'1px',
					'height':'1px',
					'top':'0px',
					'left':'0px'
				});
			$(navigationItemTarget.contentElement).stop(false,true).animate({top:'0px'}, {queue:true, duration:(settings.animationTime+$(window).height()*.25),easing:settings.easingOutFunction, done:function() {activate(navigationItemTarget);}});
			
			}});});
			return navigationItemTarget;
		};
		
		/* bind key events to transitions. */
		$(window).keydown(function(e) {
			var functionList = new Array();
			
		    if (e.keyCode == 37)
		    	transitionLeft(functionList);
		    if (e.keyCode == 38)
		    	transitionUp(functionList);
		    if (e.keyCode == 39) 
		    	transitionRight(functionList);
		    if (e.keyCode == 40) 
		    	transitionDown(functionList);
		    if (functionList.length > 0) 
			   myQueue.appendFunctions.apply(window, functionList);
		});
		
		var constructNavigationItem = function(url) {
			var contentElement = $("<iframe class=\"contentElement"+ (key+1) +"\" style=\"width:100%;height:100%\" ></iframe>");
			//contentElement.append(contentElementFrame);
			
			
			
			// image content...
			var contentMapElement = $("<li class=\"contentElement"+ (key+1) +"\"><div class=\"boxBorder mapItem\"></div></li>");
				
			var contentMiniMapElement = $("<li class=\"contentElement"+ (key+1) + "\"><div class=\"boxBorder mapItem\"></div></li>");
			//settings.contentElement.append(contentElement);
			contentElement.attr('src',url);
			//settings.contentMapElement.append(contentMapElement);
			//settings.contentMiniMapElement.append(contentMiniMapElement);
			
			var navItem = new navigationItem(contentElement, 
					contentMapElement, contentMiniMapElement);
			
			hashMap["contentElement"+navItem.getKey()] = navItem;
		
			
			settings.navigationMatrix.addItem(navItem);
			
			contentElement.css({'width':'100%','height':'100%', 'left':'0px', 'top':'0px', 'position':'absolute'});
			
			return navItem;
		};
		
		/** adds new navigation item to cartographer. 
		 * @param url for target. */
		this.add = function(url) {
		
			navItem = constructNavigationItem(url);
			displayMaps(settings.navigationMatrix.toArray(), settings.navigationMatrix.historicToArray());
			
			transitionElement(navItem);
		};
		
		/** adds list of navigational items to cartographer.
		 * @param list of urls. */
		this.addURLs = function() {
			var urls;
			var navItem = null;
			if (arguments.length > 1)
				urls = arguments;
			else if (isArray(arguments[0]))
					urls = arguments[0];
			else
				throw "Not valid argument/s";
			
			for(var x=urls.length; x >= 0 ; x--)
				navItem = constructNavigationItem(urls[x]);
			
			displayMaps(settings.navigationMatrix.toArray());
			
			transitionElement(navItem);
		};
	};
	
	/** Custom queue for fireing sequential functions. */
	CartQueue = function() {
			var list = new Array();
			var deferred = $.Deferred();

			/** run function queue with specified function/s. 
			 * @param func function to run
			 * @param (optional 1) function second function to run
			 * ...
			 * @param (optional n) function nth function to run */
		    function run(func) {
		    	 list = [].splice.call(arguments,1);
		    	 
		    	 if (deferred.state() == "resolved")
		    		 deferred = $.Deferred();
		    	 
		    	  if (func) {
		  	        $.when(func()).then(function () {
		  	        	isRunning = true;
		  	        	run.apply(window, list);
		  	        });
		    	  } else { deferred.resolve();}
		  	        return deferred.promise();
		    };
		    
		    /** appends function/s to function queue.
		     * @param (optional 1) function first append.
		     * ...
		     * @param (optional n) function nth to append. */
		    this.appendFunctions = function() {
		    	var newList = [].splice.call(arguments,0);

		    	$.when(deferred).then(run.apply(window, newList));
		    };
		};
}) (jQuery);

var key = 0;
/** Item containing screen elements for navigation matrix. 
 * @param contentElement element with main content.
 * @param contentMapElement element with large map.
 * @param contentMiniMapElement element with mini-map. */
navigationItem = function(contentElement, contentMapElement, contentMiniMapElement) {
	this.mykey = ++key;

	this.contentElement = contentElement;
	this.contentMiniMapElement = contentMiniMapElement;
	this.contentMapElement = contentMapElement;
	
	this.getKey = function(){ return this.mykey; };
};


/** Navigation Matrix applies functionality to data for elements.
 * @param rows max number of rows.
 * @param cols max number of columns.  */
navigationMatrix = function(cols, rows) {
	
	var data = new queueMatrix(cols, rows);
	
	/* history is draggable to renew */
	var history = new queueMatrix(cols, 5);
	
	var maxRows = rows;
	var maxCols = cols;
	
	var currentCoordinates = new coordinates();
	currentCoordinates.x = 0;
	currentCoordinates.y = 0;
	
	
	/** Adds new navigation item to matrix.
	 * @param navigationItem item to add. */
	this.addItem = function(navigationItem) {
		var historic = null;
		if (navigationItem != null) {
			historic = data.enqueue(navigationItem);
			
			if (historic != null)
				history.enqueue(historic);
		}
	};
	
	/** Removes navigation item from matrix. Item is then moved to historic \
	 * matrix.
	 * @param navigationItem item to remove. */
	this.removeItem = function(navigationItem) {
		data.removeElement(navigationItem);
		history.enqueue(navigationItem);
	};
	
	/** Removes navigation Item from historic and adds it to navigation matrix.
	 * @param navigationItem to renew. */
	this.renewItem = function(navigationItem) {
		history.removeElement(navigationItem);
		this.addItem(navigationItem);
	};
	
	/** Increment current coordinate x.
	 * returns element at current coordinate. */
	this.right = function() {
		var mxX = data.getMaxXCoordinates(currentCoordinates.y);
		
		if (currentCoordinates.x + 1 <= mxX) 
			currentCoordinates.x++;
		else
			currentCoordinates.x = 0;
		
	
		return this.getCurrent();
	};
	
	/** Decrement current coordinate x.
	 * @returns element at current coordinate. */
	this.left = function() {
		var mxX = data.getMaxXCoordinates(currentCoordinates.y);
		
		
		if (currentCoordinates.x - 1 >= 0)
			currentCoordinates.x--;
		else
			currentCoordinates.x = mxX;
		
		return this.getCurrent();
	};
	
	/** Decrement current coordinate y.
	 * @return element at current coordinate. */
	this.up = function() {
		var mxY = data.getMaxYCoordinates(currentCoordinates.x);
		
		if (currentCoordinates.y - 1 >= 0)
			currentCoordinates.y--;
		else
			currentCoordinates.y = mxY; 
		
		return this.getCurrent();
	};
	
	/** Increment current coordinate y.
	 * @return element at current coordinate. */
	this.down = function() {
		var mxY = data.getMaxYCoordinates(currentCoordinates.x);
		
		if (currentCoordinates.y + 1 <= mxY )
			currentCoordinates.y++;
		else
			currentCoordinates.y = 0;
		
		return this.getCurrent();
	};
	
	/** Gets the current coordinate.
	 * @return navigationItem at current coordinate. */
	this.getCurrent = function() {
		return data.get(currentCoordinates.x, currentCoordinates.y);
	};
	
	/** Gets the current coordinates of the given navigation item.
	 * @param navigation item. 
	 * @return coordinates. */
	this.getCoordinates = function(navigationItem) {
		return data.getMatrixCoordinates(navigationItem);
	};
	
	/** Gets the current coordinates.
	 * @return coordinates. */
	this.getCurrentCoordinates = function() {
		return currentCoordinates;
	};
	
	/** Gets data in an array.
	 * @return data. */
	this.toArray = function() {
		return data.toArray();
	};
	
	/** Gets historic data in an array.
	 * @return data. */
	this.historicToArray = function() {
		return history.toArray();
	};
	
	this.size = function() {
		return data.size();
	};
	
	this.getCols = function() { return maxCols; };
	this.getRows = function() { return maxRows; };
	this.getMaxY = function(x) { return data.getMaxYCoordinates(x); };
	this.getMaxX = function(y) { return data.getMaxXCoordinates(y); };
	
};


