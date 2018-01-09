/**
 * Military service term java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (May 18, 2015)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("serviceTermNoteItemsActionMenuLink"), serviceTermNoteItemsActionMenuOnClick);
	applyFormUpdateChecker(document.getElementById("militaryServiceTermForm"));
	assignOnClick();
};