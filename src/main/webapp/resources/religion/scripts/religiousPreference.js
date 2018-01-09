/*
 * Religious preference detail screen behavior.
 * 
 * Author: Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyDatePicker(document.getElementById("verificationDate"));
	applyFormUpdateChecker(document.getElementById("religiousPreferenceForm"));
	applySearchUserAccountsAutocomplete(
			document.getElementById("verificationUserAccountQuery"),
			document.getElementById("verificationUserAccountLabel"),
			document.getElementById("verificationUserAccount"),
			document.getElementById("clearVerificationUserAccountLink"),
			document.getElementById("useCurrentUserAccountForVerificationLink"));
	applyActionMenu(document.getElementById("actionMenuLink"));
};