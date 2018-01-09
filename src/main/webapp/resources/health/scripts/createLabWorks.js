/*
 * Schedule lab work referral java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Sept. 10, 2014)
 * Since: OMIS 3.0
 */
$(document).ready(function() {
	applySearch(document.getElementById("offenderName"),document.getElementById("offender"), ui.search.Autocomplete.OFFENDER_SEARCH);
	applyFormUpdateChecker(document.getElementById("createLabWorksForm"));
	applyLabWorkItemsOnClick();
});