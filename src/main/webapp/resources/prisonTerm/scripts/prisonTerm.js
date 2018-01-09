/**
 * Prison term detail screen behavior.
 * 
 * Author: Trevor Isles
 * Version: 0.1.0 April 24, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	applyDatePicker(document.getElementById("actionDate"));
	applyDatePicker(document.getElementById("sentenceDate"));
	applyDatePicker(document.getElementById("paroleEligibilityDate"));
	applyDatePicker(document.getElementById("projectedDischargeDate"));
	applyDatePicker(document.getElementById("maximumDischargeDate"));
	applyDatePicker(document.getElementById("verificationDate"));
	
	applyFormUpdateChecker(document.getElementById("prisonTermForm"));
	applyValueLabelAutoComplete(document.getElementById("verificationUserInput"), 
			document.getElementById("verificationUser"), 
			config.ServerConfig.getContextPath() + "/prisonTerm/searchUserAccounts.json");
	
	document.getElementById("verificationUserClear").onclick = function() {
		document.getElementById("verificationUser").value = "";
		document.getElementById("verificationUserInput").value = "";
		return false;
	};
	document.getElementById("useCurrentUserAccountForVerificationLink").onclick = function() {
		document.getElementById("verificationUser").value = config.SessionConfig.getUserAccountId();
		document.getElementById("verificationUserInput").value = config.SessionConfig.getUserAccountLabel();
		return false;
	};

}