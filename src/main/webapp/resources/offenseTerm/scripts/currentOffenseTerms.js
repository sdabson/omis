/**
 * Behavior for current offense term list screen.
 * 
 * <p>Current offense term list screen reports current court cases, convictions and sentences.
 * 
 * @author: Josh Divine
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var courtCasesTableBody = document.getElementById("sentences");
	var rowLinks = courtCasesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};