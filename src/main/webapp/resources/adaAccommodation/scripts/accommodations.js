/**
 * Behavior for the ada accommodations listing screen.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));	
	applyRemoveLinkConfirmation();
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for (var count = 0; count < rowActionMenus.length; count++) {	 
		var rowActionMenu = rowActionMenus[count];
		if (rowActionMenu.getAttribute("class") != null && rowActionMenu.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowActionMenu, applyAccommodationRowActionMenuOnClick, function() {
				applyRemoveLinkConfirmation();
			});
		}		
	}
};