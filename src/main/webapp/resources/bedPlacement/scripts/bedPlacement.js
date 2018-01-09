/**
 * Bed Placement java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.1 (February 08, 2015)
 * Since: OMIS 3.0
 */

$(document).ready(function() {
	assignOnClick();
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("bedPlacementForm"));
});