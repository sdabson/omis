/**
 * Security threat group activity edit screen
 *
 * Author: Trevor Isles
 */
window.onload = function() {
	applyDatePicker(document.getElementById("reportDate"));
	applyFormUpdateChecker(document.getElementById("activityForm"));
	applyStaffSearch(document.getElementById("reportedByInput"), 
			document.getElementById("reportedBy"),
			document.getElementById("reportedByDisplay"),
			document.getElementById("useCurrentUserAccountForVerification"),
			document.getElementById("reportedByClear"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("activityNoteItemsActionMenuLink"), activityNoteItemsActionMenuOnClick);
	applyActionMenu(document.getElementById("activityInvolvementItemsActionMenuLink"), activityInvolvementItemsActionMenuOnClick);
	assignOnClick();
};