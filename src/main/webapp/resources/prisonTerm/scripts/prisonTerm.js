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
	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("date"));
	applyAddTagLink();
	applyRemoveTagLinks();
	if (document.getElementById("documentData")) {
		applyFileExtensionNamer();
	}
	
	if (document.getElementById("removeSentenceCalculationLink")) {
		document.getElementById("removeSentenceCalculationLink").onclick = function(e) {
			e.preventDefault();
			document.getElementById("title").value = "";
			document.getElementById("date").value = "";
			document.getElementById("data").value = "";
			document.getElementById("dataFileExtension").value = "";
			document.getElementById("documentData").classList.remove("hidden");
			document.getElementById("prisonTermDocumentDownloadLink").classList.add("hidden");
			document.getElementById("removeSentenceCalculation").value = "true";
			var removeLinks = document.getElementsByClassName("tagRemoveLink");
			var event = new Event("click");
			for(var x = 0; x < removeLinks.length; x++) {
				removeLinks[x].dispatchEvent(event);
			}
		};
	}
}

function applyFileExtensionNamer() {
	var documentData = document.getElementById("documentData");
	var fileExtension = document.getElementById("dataFileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
}