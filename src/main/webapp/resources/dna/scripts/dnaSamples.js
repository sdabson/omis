/**
 * DNA sample list behavior.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (Oct 28, 2015)
 * @since OMIS 3.0
 */

$(document).ready(function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var dnasTableBody = document.getElementById("dnas");
	var rowLinks = dnasTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
});