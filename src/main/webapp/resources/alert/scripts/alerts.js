/**
 * Behavior for alerts screen.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyRemoveLinkConfirmation();
	var alertsTableBody = document.getElementById("alerts");
	var rowLinks = alertsTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
};