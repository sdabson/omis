window.onload = function(){
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	
	
	document.getElementById("clearAssessorLink").onclick = function() {
		document.getElementById("assessor").value = "";
		document.getElementById("assessorQuery").value = "";
		return false;
	};
	document.getElementById("useCurrentUserAccountLink").onclick = function() {
		document.getElementById("assessor").value = config.SessionConfig.getUserAccountId();
		document.getElementById("assessorQuery").value = config.SessionConfig.getUserAccountLabel();
		return false;
	};
	applyValueLabelAutoComplete(document.getElementById("assessorQuery"),  
			document.getElementById("assessor"), 
			config.ServerConfig.getContextPath() + "/questionnaire/searchUserAccounts.json");
}