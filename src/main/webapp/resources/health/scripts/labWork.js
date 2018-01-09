$(document).ready(function() {
	applySearch(document.getElementById("offenderName"),document.getElementById("offender"), ui.search.Autocomplete.OFFENDER_SEARCH);
	applyFormUpdateChecker(document.getElementById("editLabWorkForm"));
	assignLabWorkOnClick();
});