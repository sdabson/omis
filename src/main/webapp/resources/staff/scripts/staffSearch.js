/**
 * Staff search detail screen behavior.
 * 
 * Author: Sheronda Vaughn
 * Author: Yidong Li
 */
window.onload = function() {
	applyActionMenu(document.getElementById("staffSearchCriteriaActionMenu"));
	applyActionMenu(document.getElementById("staffSearchResultsActionMenu"));
//	applyFormUpdateChecker(document.getElementById("staffSearchForm"));
	var staffResultsRowActionMenus = document.getElementsByClassName("staffResultsRowActionMenuItem");
	for (i = 0;  i < staffResultsRowActionMenus.length; i++) {
		applyActionMenu(document.getElementById("staffResultsRowActionMenu" + i), function() {applyRemoveLinkConfirmation();});
	}
}