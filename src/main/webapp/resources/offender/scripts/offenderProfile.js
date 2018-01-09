/**
 * Offender profile java script.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 (Oct 21, 2016)
 * Since: OMIS 3.0
 */

window.onload = function() {
	
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	for(var x =0; x < actionMenus.length; x++) {
		applyActionMenu(actionMenus[x]);
	}
	
};