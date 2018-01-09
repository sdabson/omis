/**
 * Behavior for offense term list screen.
 * 
 * <p>Offense term list screen reports court cases, convictions and sentences.
 * 
 * @author: Stephen Abson
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var courtCasesTableBody = document.getElementById("courtCases");
	var rowLinks = courtCasesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				var dismissDocket = document.getElementsByClassName("dismissDocketLink");
				for (var x = 0; dismissDocket.length; x++) {
					dismissDocket[x].onclick = function() {
						var resolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
						var message = resolver.getMessage("confirmDocketDismiss", null);
						if (confirm(message)) {
							return true;
						} else {
							return false;
						}
					};
				}
				applyRemoveLinkConfirmation();
			});
		}
	}
};