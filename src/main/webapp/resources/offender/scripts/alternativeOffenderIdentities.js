/**
 * Behavior for editing alternative person identities.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2015)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyRemoveLinkConfirmation();
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for (var count = 0; count < rowActionMenus.length; count++) {
		var rowActionMenu = rowActionMenus[count];
		if (rowActionMenu.getAttribute("class") != null && rowActionMenu.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowActionMenu, function() {
				applyRemoveLinkConfirmation();
			});
		}			
	}	
});