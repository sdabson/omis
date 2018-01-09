/**
 * Facility visitations java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (March 29, 2017)
 * Since: OMIS 3.0
 */

/**
 * On load functionality for facility visitations list screen.
 */
window.onload = function() {
	assignDatePicker(document.getElementById("date"));
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	for(var i=0, len=actionMenus.length; i<len; i++)
    {
		applyActionMenu(actionMenus[i]);
    }
}