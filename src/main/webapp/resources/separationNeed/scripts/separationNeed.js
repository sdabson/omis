/**
 * Separation need java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Aug 28, 2015)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyOffenderSearch(document.getElementById("targetOffenderInput"), 
			document.getElementById("targetOffender"),
			document.getElementById("targetOffenderDisplay"), 
			document.getElementById("targetOffenderClear"));
	applyStaffSearch(document.getElementById("reportingStaffInput"), 
			document.getElementById("reportingStaff"),
			document.getElementById("reportingStaffDisplay"),
			document.getElementById("reportingStaffCurrent"),
			document.getElementById("reportingStaffClear"));
	applyFormUpdateChecker(document.getElementById("separationNeedForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("separationNeedNoteItemsActionMenuLink"), function() {
		separationNeedNoteItemsActionMenuOnClick();
	});
	applyTextCounter(document.getElementById("creationComment"), document.getElementById("creationCommentCharacterCounter"));
	applyTextCounter(document.getElementById("removalComment"), document.getElementById("removalCommentCharacterCounter"));
	assignOnClick();
};

