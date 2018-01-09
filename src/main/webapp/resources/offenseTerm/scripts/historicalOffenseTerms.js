/**
 * Behavior for historical offense term list screen.
 * 
 * <p>Historical offense terms are inactive sentences.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var historicalOffenseTermsTableBody = document.getElementById("historicalOffenseTerms");
	var rowLinks = historicalOffenseTermsTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};