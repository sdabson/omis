/**
 * Incident reports list screen functionality.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (February 26, 2016)
 * Since: OMIS 3.0
 */

/**
 * On load functionality. 
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyIncidentReportRowOnClick();
}