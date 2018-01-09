/**
 * Incident report edit behavior.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Sep 11, 2015)
 * Since: OMIS 3
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("involvedPartyItemsActionMenuLink"), assignInvolvedPartyActionMenuOnClick);
	applyActionMenu(document.getElementById("incidentStatementNoteItemsActionMenuLink"), assignIncidentStatementNoteItemActionMenuOnClick);
	applyDatePicker(document.getElementById("statementDate"));
	applyDatePicker(document.getElementById("incidentDate"));
	assignTimePicker("incidentTime");
	applyUserSearch(document.getElementById("reporterInput"),
			document.getElementById("reporter"),
			document.getElementById("reporterDisplay"),
			document.getElementById("reporterCurrent"),
			document.getElementById("reporterClear"));
	assignIncidentStatementOnClick();
	applyTextCounter(document.getElementById("summary"), document.getElementById("summaryCharacterCounter"));
	applyTextCounter(document.getElementById("location"), document.getElementById("locationCharacterCounter"));
	applyInformantSearch();
	applyInformationSourceCategoryOnClick();
	applyDisableOnSubmit(document.getElementById("incidentStatementForm"));
	
	var incidentSummary = document.getElementById("summary");
	incidentSummary.onfocus = function() {
			applySessionExtender(incidentSummary, 
					config.ServerConfig.getContextPath() + "/incident/statement/extendSession.html", 
					300000);
	}

}

///**
// * Applies session extender to the specified element.
// * 
// * @param elt DOM element
// * @param url uniform resource locator
// * @param increment increment to check for session extension (milliseconds)
// */
//function applySessionExtender(elt, url, increment) {
//	var elementChanged = false;
//	elt.onkeypress = function() {
//		elementChanged = true;
//		elt.onkeypress = null;
//	}
//	var refresh = setInterval(function(){
//					elt.onkeypress = function() {
//						elementChanged = true;
//						elt.onkeypress = null;
//					}
//					if(elementChanged) {
//						submitAjaxRequest(url);
//						elementChanged = false;
//					}
//				}, increment);
//	elt.onblur = function() {clearInterval(refresh)};
//}