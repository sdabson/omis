$(document).ready(function() {
	applyStaffSearch(document.getElementById("providerInput"),
			document.getElementById("provider"),
			document.getElementById("providerDisplay"),
			document.getElementById("providerCurrent"),
			document.getElementById("providerClear"));
	applyFormUpdateChecker(document.getElementById("providerAssignmentForm"));
	applyProviderTypeOnClick();
	applyDatePickers();
});