/**
 * Military service terms java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (May 18, 2015)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var serviceTermRowActionMenus = document.getElementsByClassName("serviceTermActionMenuItem");
	for (i = 0; i < serviceTermRowActionMenus.length; i++) {
		applyActionMenu(document.getElementById("serviceTemrRowActionMenuLink" + i));
	}
	applyRemoveLinkConfirmation();
};