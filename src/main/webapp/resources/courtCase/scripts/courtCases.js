/**
 * Court cases list behavior.
 * 
 * @author Joel Norris
 * @version 0.1.0 (November 13, 2014)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var actionMenuItems = document.getElementsByClassName("actionMenuItem");
	for(var x = 0; actionMenuItems.length; x++) {
		applyActionMenu(actionMenuItems[x], function() {
			var dismissDocket = document.getElementsByClassName("dismissDocketLink");
			for (var x = 0; dismissDocket.length; x++) {
				dismissDocket[x].onclick = function() {
					var resolver = new common.MessageResolver("omis.courtcase.msgs.courtCase");
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
	
	assignCourtCasesOnClick();
});
