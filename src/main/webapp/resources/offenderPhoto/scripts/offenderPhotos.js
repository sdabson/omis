/**
 * Behavior for offender photo listing screen.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2013)
 * @since OMIS 3.0
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for (var count = 0; count < rowActionMenus.length; count++) {	 
		applyActionMenu(document.getElementById("photoSummaryActionMenuLink" + count));
	}		
};