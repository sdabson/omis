/**
 * Multi screen mode behavior.
 * 
 * @author: Stephen Abson
 * @author: Ryan Johns
 * @version: 0.1.1 (Jan 12, 2016)
 * @since OMIS 3.0
 */
window.onload = function() {
	var as = document.getElementsByTagName("a");
	for (var i = 0; i < as.length; i++) {
		var elt = as[i];
		if (elt.getAttribute("class") != null
				&& elt.getAttribute("class").indexOf("iconLink") > -1) {
			elt.onclick = function(event) {
				newTab(event.target.getAttribute("href"));
				return false;
			};
		}
	}
	
	setUpTabs(document.getElementById("homeLink"));
	applyActionMenu(document.getElementById("multiMenu"));
	setUpSearch();
};

var setUpSearch = function() {
	applySearch(document.getElementById("offenderSearch"), 
			document.getElementById("offenderSearchResults"), 
			ui.search.AjaxList.OFFENDER_SEARCH, onSearch);
	
	document.getElementById("offenderSearch").addEventListener("focus", function() {
		show(document.getElementById("offenderSearchResults"));
	});
	
	document.getElementById("offenderSearch").addEventListener("focusout", function() {
		hide(document.getElementById("offederSearchResults"));
	});
	
	var offenderSearchResults = document.getElementById("offenderSearchResults");
	applyMouseOutHide(offenderSearchResults);
};

var onSearch = function(target) {
	show(document.getElementById("offenderSearchResults"));
	var anchors = target.getElementsByTagName("a");
	
	for(var x=0; x < anchors.length; x++) {
		anchors[x].onclick = function(event) {
			document.getElementById("offenderSearch").blur();
			
			newTab(event.target);
			hide(document.getElementById("offenderSearchResults"));
			return false;
		}
	}
};