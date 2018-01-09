/**
 * Prison term detail screen behavior.
 * 
 * Author: Trevor Isles
 * Version: 0.1.0 April 24, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var prisonTermsTableBody = document.getElementById("prisonTerms");
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