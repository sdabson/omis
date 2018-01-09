window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("authorizationDate"));
	
	document.getElementById("clearAuthorizedByUserLink").onclick = function() {
		document.getElementById("authorizedByUser").value = "";
		document.getElementById("authorizedByQuery").value = "";
		return false;
	};
	document.getElementById("useCurrentUserAccountLink").onclick = function() {
		document.getElementById("authorizedByUser").value = config.SessionConfig.getUserAccountId();
		document.getElementById("authorizedByQuery").value = config.SessionConfig.getUserAccountLabel();
		return false;
	};
	applyValueLabelAutoComplete(document.getElementById("authorizedByQuery"),  
			document.getElementById("authorizedByUser"), 
			config.ServerConfig.getContextPath() + "/workRestriction/searchUserAccounts.json");

	applyActionMenu(document.getElementById("noteItemsActionMenuLink"), workRestrictionNoteItemsCreateOnClick);
	assignOnClick();

}