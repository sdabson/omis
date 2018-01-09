/** Set up tab interface prepare to register functions with event runner.
 * 
 * author Ryan Johns (Jan 14, 2014)
 *  
 * Dependencies -
 *  resources/common/scripts/tabs.js
 *	resources/common/scripts/EventRunner.js 
 *  resources/3rdparty/JQuery/jquery.min.js */

var tabs = new tabSet();
var animated = {O:false, leftDirection:false};

//Variables
var tabId = 0;
var defaultTabLink;

function tabSet() {
	this.createTabs = createTabs;
	this.swapTab = swapTab;
	this.newTab = newTab;
	this.addTab = addTab;
	this.appendTab = appendTab;
}//tabSet

/** swapTab gives focus based on clicked anchor tag. 
 * Clicked anchor is given active class */
function swapTab() {
	$(this).closest('.tabbed_area').find(".active").removeClass("active");
		
	$(this).parents("li.tab_li").addClass("active");
	
	$(this).closest('ul.tabs').animate({scrollLeft:calcCenterScroll($(this).parent().parent(),$(this).closest('ul.tabs').parent())},500);
	
	$(this).closest('div.tabbed_area').find(".tab_content").hide();				
	var content_show = $(this).attr("title");
	$("#"+content_show).show();
	$("#"+content_show+".remove").addClass("active");
	
	
	if(typeof(Storage) !== "undefined") {
		var date = new Date();
		var date2 = new Date(date.getTime() + (4 * 1000 * 60 * 60 * 24));
		localStorage.setItem("activeTabIndex", content_show);
		localStorage.setItem("tabExpiry", date2.getTime());
	}
	return false;
}//swapTab

/** Creates underlying structure for Tabbed interface.
 * ==Note this needs to be sized positioned==
 * @param name name id of tabs. */
function createTabs(name) {
	var tabsDiv = document.createElement('div');
	var tabSet = document.createElement('div');
	var tabsLinks = document.createElement('ul');
	var tabIcons = document.createElement('div');
	link = defaultTabLink.cloneNode();
	$(link).attr('id', name+'_newTab');
	
	$(tabsDiv).attr('class', 'tabbed_area');
	$(tabsDiv).attr('id', name);
	$(tabSet).attr('id', name+'_tabSet');
	$(tabSet).attr('class','tabSet');
	$(tabsLinks).attr('class', 'tabs');
	$(tabsLinks).attr('id', name+'_tabLinks');
	$(tabsDiv).append(tabIcons);
	$(tabsDiv).append(tabsLinks);
	$(tabsDiv).append(tabSet);
	$(tabIcons).attr('class', 'tabIcons');
	$(tabIcons).append(link);
	$(link).click(function(event) { 
		addTab(name, event.target.getAttribute("href"));
		return false;
	})
	$(tabsLinks).scrollContainer({scrollX:true, 
		scrollY:false, 
		elementCss:{}
	});
	$(".tabContainer").append(tabsDiv);
}//createTabs

/** Spawns a new Tab with given path. Default first tab set
 * path a str containing the path to src */
function newTab(path, title, active) {
	active = typeof active !== 'undefined' ? active : true;
	addTab($('.tabbed_area').eq(0).attr("id"), path, title, active);
}

/** Spawns a new Tab 
 *  path a str containing the path to src
 *  name a str containing the name of the div */
function addTab(name, path, title, active) {
	if (active || typeof active == 'undefined') {
		$('#'+name).find(".active").removeClass("active");
	}

	//Create Tab element structure
	var iframe = document.createElement('iframe');
	var tab = document.createElement('li');
	var tabLink = document.createElement('a');
	var button = document.createElement('a');
	var span = document.createElement('span');

	var tabClass = 'tab_li';
	if (active || typeof active == 'undefined') {
		tabClass += ' active';
	}
	
	$(tab).attr('class', tabClass);
	$(button).attr('title', tabId+'.remove');
	$(button).attr('id', tabId+'.remove');
	$(button).attr('class', 'remove');
	$(button).attr('href', '#');
	//$(button).css({display:"inline-block"});
	
	$(button).click(removeTab);
				
	$(tabLink).attr('title', tabId);
	$(tabLink).attr('href', '#');
	$(tabLink).attr('class','swap');
	$(tabLink).attr('id', 'tab'+tabId);
	
	$(span).attr('class', 'tab');
	$(span).focus();
	//makeDraggable(span);
	makedblClick(span);
	
	$(tabLink).click(swapTab);
	
	$(span).append(tabLink);
	$(span).append(button);
	$(tab).append(span);
	$('#'+name+'_tabLinks').append(tab);
	
	$(iframe).attr('id',tabId);
	$(iframe).attr('class', 'tab_content');
	$(iframe).attr('name',tabId);
	$('#'+name+'_tabSet').append(iframe);
		
	
	if (active || typeof active == 'undefined') {
		$('#'+name).find('.tab_content').hide();
		$(iframe).show();
	} else {
		$(iframe).hide();
	}
				
	
	//Changes Tab Description when content of Iframe has loaded.
	if (typeof title == 'undefined') {
	$(iframe).on("load", function() {
		var tabHtml = $(this).contents( ).find( "title").text();

		if (/(.*)((\s)+(.*,)\s(.*)(#)(.*))/.test(tabHtml)) {
			tabHtml = tabHtml.replace(/(.*)((\s)+(.*,)(\s)(.*)(#)(.*))/, "$1<br>$3$4$5$6$7$8");
		}
		$('#tab' + this.id).html(tabHtml);
		$('#tab' + this.id).closest("li").css('background-color', $(this).contents().find("body").css('background-color'));
		var path = $(this).contents().get(0).location.href;
		if(typeof(Storage) !== "undefined") {
			var tabLinksJSON = localStorage.getItem("tabLinks");
			var tabLinks = [];
			if (typeof(tabLinksJSON) !== "undefined" && tabLinksJSON != null) {
				tabLinks = JSON.parse(tabLinksJSON);
			}
			tabLinks[this.id] = path;
			setTimeout(function() {
				var date = new Date();
				var date2 = new Date(date.getTime() + (4 * 1000 * 60 * 60 * 24));
				localStorage.setItem("tabLinks", JSON.stringify(tabLinks));
				localStorage.setItem("tabExpiry", date2.getTime());
			}, 500);
		}
		//this.contentWindow.location.href
	});
	} else {
		$(tabLink).html(title);
	}
	
	$(iframe).attr('src', path);
	
	if(typeof(Storage) !== "undefined") {
		var tabLinksJSON = localStorage.getItem("tabLinks");
		var tabLinks = [];
		if (typeof(tabLinksJSON) !== "undefined" && tabLinksJSON != null) {
			tabLinks = JSON.parse(tabLinksJSON);
		}
		tabLinks[iframe.id] = path;
		var date = new Date();
		var date2 = new Date(date.getTime() + (4 * 1000 * 60 * 60 * 24));
		localStorage.setItem("tabLinks", JSON.stringify(tabLinks));
		localStorage.setItem("tabExpiry", date2.getTime());
		localStorage.setItem("activeTabIndex", JSON.stringify(iframe.id));
	}
	
	if (active) {
		$('#'+name+'_tabLinks').animate({scrollLeft:calcCenterScroll($(tab),$('#'+name+'_tabLinks').parent())}, 500);
	}
	
	tabId++;
}//newTab

/** Moves given tab and content to tabArea.
 * @param tab element
 * @param tabArea destination tabbed area. */
function appendTab(tab, tabArea) {
	var contentId = $(tab).children(':first').attr("title");
	
	var tabAreaOld = $('#'+contentId).closest('div.tabbed_area');
	var ulOld = $(tabAreaOld).find('ul.tabs');
	
	var span = $(tab).parent();
	span.detach();
	
	var contentSrc = "";
	
	if ($('#'+contentId).contents().get(0))
		contentSrc = $('#'+contentId).contents().get(0).location.href;
	else
		contentSrc = $('#'+contentId).attr('src');
	
	var content = $('#'+contentId);
	content.hide().detach();

	content.attr('src', contentSrc);
	
	$(tabArea).find(".tabSet").append(content);
	$(tabArea).find('ul.tabs').append(span);
	
	//Make moved tab active
	span.closest('ul.tabs').find("li.tab_li").removeClass('active');
	$(tab).parent().addClass('active');
	$(tabArea).closest('div.tabbed_area').find(".tab_content").hide();
	
	content.show();

	if (ulOld.children("li.tab_li").length <= 0) {
		if (!tabAreaOld.hasClass("perm")) {
			tabAreaOld.remove();
		
			 $(".tabbed_area").each(function() {
				 if ($(this).hasClass("split")) {
					 $(this).removeClass("split");
				 }
			 });
		} else 
			tabAreaOld.find(".tab_content").show();
		
	} else if ($(tab).parent().hasClass('active')) {
		//Making new tab active in the event that other tabs exist.
		tabNode = ulOld.children(':last').children(':first');
		
		tabNode.parent().addClass('active');
		tabNode.focus();
		innercontent_show = tabNode.children(':first').attr('title');
		$('#'+innercontent_show).show();
	}
	
	$(tabArea).find('ul.tabs').animate({scrollLeft:calcCenterScroll(span,$(tabArea).find('ul.tabs').parent())},500);
}//appendTab

/** Removes tab and content area in relation to this */
function removeTab() {
	$(this).closest('ul.tabs').find(".active").removeClass("active");
	$(this).closest('div.tabbed_area').find(".tab_content").hide();
	var index = this.id.split(".")[0];
	var newIndex;
	
	var tabNode = null;
	if($(this).closest('li.tab_li').prev().length != 0)
		tabNode = $(this).closest('li').prev().children(':first');
	else if ($(this).closest('li.tab_li').next().length != 0)
		tabNode = $(this).closest('li.tab_li').next().children(':first');
	
	if (tabNode != null) {
		tabNode.parent().addClass("active");
		var innercontent_show = tabNode.children(':first').attr("title");
		newIndex = innercontent_show;
		$("#"+innercontent_show).show();
	}
	
	var content_show = $(this).attr("title").split('.remove')[0];

	var ul = $(this).closest('ul.tabs');
	var li = $(this).closest('ul.tabs li');	
	var tabArea = $(this).closest('div.tabbed_area');
	
	li.remove();
	$("#"+content_show).remove();
	
	if (ul.children().length <= 0) {
		if (!tabArea.hasClass("perm")) {
			tabArea.remove();
		
			 $(".tabbed_area").each(function() {
				 if ($(this).hasClass("split")) {
					 $(this).removeClass("split");
				 }
			 });
		} else { 
			tabArea.find(".tab_content").show();
			newIndex = tabArea.attr("id");
		}
	}
	
	if(typeof(Storage) !== "undefined") {
		var tabLinksJSON = localStorage.getItem("tabLinks");
		var tabLinks;
		if (typeof(tabLinksJSON) !== "undefined" && tabLinksJSON != null) {
			tabLinks = JSON.parse(tabLinksJSON);
			tabLinks[index] = null;
			localStorage.setItem("tabLinks", JSON.stringify(tabLinks));
			var date = new Date();
			var date2 = new Date(date.getTime() + (4 * 1000 * 60 * 60 * 24));
			localStorage.setItem("tabExpiry", date2.getTime());
			
			if (tabLinks.some(function(link) { return (link !== null); })) {
				localStorage.setItem("activeTabIndex", JSON.stringify(newIndex));
			} else {
				localStorage.setItem("activeTabIndex", "0");
			}
		}
	}
	
	return false;
}//removeTab

/** Sets up tabs/element layout/prepare drag drop behavior. */
function setUpTabs(element) {
	defaultTabLink = element;
	$("a.tabLink").click(tabs.swapTab);
	
	$(".tabs").each(function() {
		$(this).scrollContainer({scrollX:true, 
			scrollY:false, 
			elementCss:{
				}});
	});
	
	if(typeof(Storage) !== "undefined") {
		var expireDateJSON = localStorage.getItem("tabExpiry");
		var expireDate;
		var today = new Date();
		if (expireDateJSON != null) {
			expireDate = new Date(JSON.parse(expireDateJSON));
		} else {
			expireDate = null;
		}
		if  (expireDate != null && expireDate > today && localStorage.getItem("tabOwner") === sessionStorage.getItem("username") && localStorage.getItem("origin") == window.location.origin + ":" + window.location.port +"//"+ window.location.pathname) {
			var tabLinksJSON = localStorage.getItem("tabLinks");
			var tabLinks;
			var activeIndex = null;
			if (localStorage.getItem("activeTabIndex") != undefined) {
				try{
					activeIndex = JSON.parse(localStorage.getItem("activeTabIndex"));
				}
				catch(exception) {
					activeIndex = null;
					console.log("Unable to parse activeTabIndex");
				}
				
			}
			localStorage.removeItem("tabLinks");
			localStorage.removeItem("tabExpiry");
			localStorage.setItem("tabOwner", sessionStorage.getItem("username"));
			if (activeIndex == null) {
				activeIndex = 0;
			}
			if (tabLinksJSON != null) {
				tabLinks = JSON.parse(tabLinksJSON);
				if (tabLinks.some(function(tabLink) {
					return (tabLink !== null); 
				})) {
				for (var x = 0; x < tabLinks.length; x++) {
					if (tabLinks[x] !== null) {
						newTab(tabLinks[x],undefined,(activeIndex == x));
					}
				}
				} else {
					localStorage.removeItem("tabLinks");
					localStorage.removeItem("tabExpiry");
					localStorage.setItem("tabOwner", sessionStorage.getItem("username"));
					localStorage.setItem("origin", window.location.origin + ":" + window.location.port +"//"+ window.location.pathname);
					newTab(config.ServerConfig.getContextPath() + "/home.html");
				}
			} else {
				localStorage.removeItem("tabLinks");
				localStorage.removeItem("tabExpiry");
				localStorage.setItem("tabOwner", sessionStorage.getItem("username"));
				localStorage.setItem("origin", window.location.origin + ":" + window.location.port +"//"+ window.location.pathname);
				newTab(config.ServerConfig.getContextPath() + "/home.html");
			}
		} else {
			localStorage.removeItem("tabLinks");
			localStorage.removeItem("tabExpiry");
			localStorage.setItem("tabOwner", sessionStorage.getItem("username"));
			localStorage.setItem("origin", window.location.origin + ":" + window.location.port +"//"+ window.location.pathname);
			newTab(config.ServerConfig.getContextPath() + "/home.html");
		}
	}
}//setUpTabs


function makedblClick(span) {
	$(span).dblclick(function() {
		var left = false;
		if ($(this).parents("#tabs2").length < 1)
			left = true;
		
		if (left) {
			if ($(this).parent().parent().children("li.tab_li").length > 1) {
				if (!$("#tabs2").length > 0 && $(this).parents("perm").length < 1) {
					tabs.createTabs('tabs2');
					$(".tabbed_area").each(function() {
						if (!$(this).hasClass("split")) {
							$(this).addClass("split");
						}
					});
			
				}
				tabs.appendTab(this, document.getElementById("tabs2"));
			}
		}
		 
		if (!left){
			tabs.appendTab(this, document.getElementById("offenderTabs"));
			 if ($("#tabs2_tabLinks").children().length <= 0 && $('#tabs2').children().length == 1) {
				 $("#tabs2").remove();
				 
				 $(".tabbed_area").each(function() {
					 if ($(this).hasClass("split")) {
						 $(this).removeClass("split");
					 }
				 });
			 }
		}
		if (animated.leftDirection != left) {
			animated.resize = true;
			animated.leftDirection = left;
		}
		
	});
}
if (typeof window.addEventListener !== 'undefined' && typeof window.postMessage !== 'undefined') {
	window.addEventListener("message", function(event) {
		if (event.data.domain == 'TABS' 
			&& event.data.functionName == 'NEW_TAB'
			&& validateMessage(event)) {

			var tabArea;
			if (event.data.tabArea == 'tabs2') {
				tabArea = 'tabs2';
				if ($("#tabs2").length < 1) {
					tabs.createTabs('tabs2');
					$(".tabbed_area").each(function() {
						if (!$(this).hasClass("split")) {
							$(this).addClass("split");
						}
					});
				}
			} else {
				tabArea = 'offenderTabs';
			}
			
			if (typeof event.data.url !== 'undefined' && typeof event.data.title !== 'undefined') {
				addTab(tabArea, event.data.url, event.data.title, true);
			} else {
				addTab(tabArea, event.data.url, undefined, true);
			}
			return false;
		} else {
			return true;
		}
	});
}

function calcCenterScroll(target, outerElement) {
	var x = outerElement.width();
	var y = target.outerWidth(true);
	var q = 0;
	var m = outerElement.find("ul").children("li.tab_li");
	var z = target.index();
	for(var i = 0; i < z; i++){
		q+= $(m[i]).outerWidth(true);
	}
	
	return Math.max(0, q - (x - y)/2);
}
