/*
 * Religious preferences list screen.
 * 
 * Author: Stephen Abson
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var preferencesTableBody = document.getElementById("preferences");
	var rowLinks = preferencesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};