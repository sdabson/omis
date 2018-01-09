/** Caseload list screen behavior.
* Author: Ryan Johns
* Version: 0.1.0 (Jun 19, 2017) 
* Since: OMIS 3.0 */
window.onload = function() {
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	
	for(var j = 0; j < actionMenus.length; j++) {
		applyActionMenu(actionMenus[j]);
	}
};