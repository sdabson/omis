/**
 * Non Offender-Centric Reporting java script.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 (Dec 8, 2016)
 * Since: OMIS 3.0
 */

window.onload = function() {
	
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	for(var x =0; x < actionMenus.length; x++) {
		applyActionMenu(actionMenus[x]);
	}
	
};