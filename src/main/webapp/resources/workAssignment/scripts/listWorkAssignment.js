/**
 * Work assignment list screen java script.
 * 
 * Author: Yidong Li
 * Version: 0.1.0 (Aug 25, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("workAssignmentListActionMenuLink"));
	var workAssignmentRows = document.getElementsByClassName("rowActionMenuItem");
	for(var x =0; x < workAssignmentRows.length; x++) {
		applyActionMenu(workAssignmentRows[x], function() {applyRemoveLinkConfirmation();});
	}
}