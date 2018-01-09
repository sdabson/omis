/**
 * Charge list behavior.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 15, 2017)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var chargesTableBody = document.getElementById("charges");
	var rowLinks = chargesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};