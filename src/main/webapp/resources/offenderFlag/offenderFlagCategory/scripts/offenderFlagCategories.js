/**
 * Behavior for offender flag categories list screen.
 * 
 * @author Sheronda Vaughn
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rows = document.getElementsByClassName('rowActionMenuLinks');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
	}
}