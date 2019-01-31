/**
 * Bed placement list behavior.
 * 
 * @author Joel Norris
 * @version 0.1.0 (February 10, 2015)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var bedPlacementRowActionMenus = document.getElementsByClassName("bedPlacementRowActionMenuItem");
	for (i = 0; i < bedPlacementRowActionMenus.length; i++) {
		applyActionMenu(document.getElementById("bedPlacementRowActionMenuLink" + i),
				function() {
					applyRemoveLinkConfirmation();
				});
	}
	applyRemoveLinkConfirmation();
});