/**
 * Behavior for offender photo listing screen.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (January 28, 2019)
 * @since OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for (var count = 0; count < rowActionMenus.length; count++) {
		applyActionMenu(document.getElementById("photoSummaryActionMenuLink" + count), function(){applyRemoveLinkConfirmation()});
	}		
};