/**
 * Probation term listing screen behavior.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 May 25, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	var actionMenu = document.getElementById("actionMenuLink");
	if (actionMenu != null) {
		applyActionMenu(actionMenu);
	}
	var prisonTermsTableBody = document.getElementById("probationTerms");
	var rowLinks = prisonTermsTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};