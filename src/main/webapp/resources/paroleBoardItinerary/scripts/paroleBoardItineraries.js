/**
 * Parole board itineraries listing screen behavior.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 Dec 5, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	var actionMenu = document.getElementById("actionMenuLink");
	if (actionMenu != null) {
		applyActionMenu(actionMenu);
	}
	var paroleBoardItinerariesTableBody = document.getElementById("paroleBoardItineraries");
	var rowLinks = paroleBoardItinerariesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
	
	// Focuses input when label or radio box is clicked
	function applyFieldFocus(radioEltName, inputEltName) {
		var radioElt = document.getElementById(radioEltName);
		var inputElt = document.getElementById(inputEltName);
		radioElt.onfocus = function() {
			inputElt.focus();
		};
	}
	
	// Checks radio button when input is focused
	function applyInputFocus(inputEltName, radioEltName) {
		var inputElt = document.getElementById(inputEltName);
		var radioElt = document.getElementById(radioEltName);
		inputElt.onfocus = function() {
			radioElt.checked = true;
		};
	}
	
	applyFieldFocus("singleDateSearch", "date");
	applyInputFocus("date", "singleDateSearch");
	applyFieldFocus("dateRangeSearch", "startDate");
	applyInputFocus("startDate", "dateRangeSearch");
	applyInputFocus("endDate", "dateRangeSearch");
	applyDatePicker("date");
	applyDatePicker("startDate");
	applyDatePicker("endDate");
};