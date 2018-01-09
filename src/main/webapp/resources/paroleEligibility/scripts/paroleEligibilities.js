/**
 * Parole eligibility list screen.
 * 
 * Author: Trevor Isles
 * Version: 0.1.0 (Dec 21, 2017)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var paroleEligibilitiesTableBody = document.getElementById("paroleEligibilities");
	var rowLinks = paroleEligibilitiesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
	return false;	
}

