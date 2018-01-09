/**
 * Location reason terms behavior.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var locationReasonTermsTableBody = document.getElementById("locationReasonTerms");
	var rowLinks = locationReasonTermsTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};