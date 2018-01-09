/**
 * Behavior for the residences screen.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyRemoveLinkConfirmation();
	
	var residencesRows = document.getElementsByClassName("rowActionMenuItem");
	for(var x =0; x < residencesRows.length; x++) {
		applyActionMenu(residencesRows[x], function() {applyRemoveLinkConfirmation();});
	}
}