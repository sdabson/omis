/**
 *  Behavior for creating and editing alerts.
 *  
 *  @author Jason Nelson
 *  @author Stephen Abson
 *  @author Joel Norris
 *  @version 0.1.1 (November 25, 2014)
 *  @since OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("expireDate"));
	applyDatePicker(document.getElementById("resolveDate"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyStaffSearch(document.getElementById("resolveByPersonInput"), 
			document.getElementById("resolveByPerson"),
			document.getElementById("resolveByPersonDisplay"),
			document.getElementById("useCurrentUserAccountForResolutionLink"),
			document.getElementById("clearUserAccountForResolutionLink"));
	applyTextCounter(document.getElementById("description"), document.getElementById("descriptionCharacterCounter"));
	applyTextCounter(document.getElementById("resolveDescription"), document.getElementById("resolveDescriptionCharacterCounter"));
	applyFormUpdateChecker(document.getElementById("alertForm"));
};