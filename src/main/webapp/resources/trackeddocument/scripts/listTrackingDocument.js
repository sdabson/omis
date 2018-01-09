/**
 * Tracking document list screen java script.
 * 
 * Author: Yidong Li
 * Version: 0.1.0 (Dec 15, 2017)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("trackedDocumentListActionMenuLink"));
	var trackedDocumentRows = document.getElementsByClassName("trackedDocumentListRowActionMenuItem");
	for(var x =0; x < trackedDocumentRows.length; x++) {
		applyActionMenu(trackedDocumentRows[x], function() {applyRemoveLinkConfirmation();});
	}
}