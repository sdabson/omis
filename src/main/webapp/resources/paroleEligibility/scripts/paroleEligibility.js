/**
 * Parole eligibility edit screen.
 * 
 * Author: Trevor Isles
 * Version: 0.1.0 (Dec 21, 2017)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("paroleEligibilityNoteItemsActionMenuLink"), paroleEligibilityNoteItemsActionMenuOnClick);
	assignOnClick();
	applyDatePicker(document.getElementById("hearingEligibilityDate"));
	applyDatePicker(document.getElementById("paroleEligibilityStatusDate"));
	applyDatePicker(document.getElementById("reviewDate"));
	applyFormUpdateChecker(document.getElementById("paroleEligibilityForm"));
};