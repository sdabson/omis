/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/* 
 * Presentence Investigation Request form behavior.
 * 
 * Author: Ryan Johns
 * Author: Annie Wahl
 * Author: Josh Divine
 * Version: 0.1.3 (Aug 21, 2018) 
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("presentenceInvestigationRequestNoteItemsActionMenuLink"), presentenceInvestigationRequestNoteItemsCreateOnClick);
	applyActionMenu(document.getElementById("presentenceInvestigationDelayItemsActionMenuLink"), presentenceInvestigationDelayItemsCreateOnClick);
	applyActionMenu(document.getElementById("presentenceInvestigationDocketAssociationItemsActionMenuLink"), presentenceInvestigationDocketAssociationItemsCreateOnClick);
	applyDatePicker(document.getElementById("expectedCompletionDate"));
	applyDatePicker(document.getElementById("requestDate"));
	applyDatePicker(document.getElementById("sentenceDate"));
	applyDatePicker(document.getElementById("actualSentenceDate"));
	applyDatePicker(document.getElementById("submissionDate"));
	applySearchUserAccountsAutocomplete(
			document.getElementById("assignedUserInput"), 
			document.getElementById("assignedUserDisplay"), 
			document.getElementById("assignedUserAccount"), 
			document.getElementById("clearAssignedUser"),
			document.getElementById("currentAssignedUser"));
	
	if(document.getElementById("personInput") != null){
		applySearchOffendersOnChange();
		applyCreatePersonOnChange();
	}
//	var docketSearch = document.getElementById("existingDocketValue");
//	if (docketSearch != null) {
//		applyValueLabelAutoComplete(document.getElementById("existingDocketValue"), document.getElementById("existingDocket"), config.ServerConfig.getContextPath() + "/presentenceInvestigation/request/searchDockets.json");
//		applyClear(document.getElementById("clearDocket"), document.getElementById("existingDocketValue"), document.getElementById("existingDocket"));
//		applyFieldFocus("useExistingDocket", "existingDocketValue");
//		applyInputFocus("existingDocketValue", "useExistingDocket");
//	}
//	var existingDocket = document.getElementById("useExistingDocket");
//	var createDocket = document.getElementById("createDocket");
//	if (existingDocket != null && createDocket != null) {
//		existingDocket.onclick = function() {
//			if (this.checked) {
//				document.getElementById("createDocketFieldset").setAttribute("hidden", "hidden")
//			}
//		}
//		createDocket.onclick = function() {
//			if (this.checked) {
//				document.getElementById("createDocketFieldset").removeAttribute("hidden", "hidden")
//			}
//		}
//	}
	
	for(var i = 0; i < currentPresentenceInvestigationRequestNoteItemIndex; i++){
		presentenceInvestigationRequestNoteItemRowOnClick(i);
	}
	for(var i = 0; i < currentPresentenceInvestigationDelayItemIndex; i++){
		presentenceInvestigationDelayItemRowOnClick(i);
	}
	for(var i = 0; i < currentPresentenceInvestigationDocketAssociationItemIndex; i++){
		presentenceInvestigationDocketAssociationItemRowOnClick(i);
	}
};

//Focuses input when label or radio box is clicked
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

/**
 * Clears the data associated with the specified controls
 * @param clear button that on click will be added to
 * @param query query input control
 * @param value hidden value control
 * @param display display control
 */
function applyClear(clear, query, value, display) {
	clear.onclick = function() {
		if (query != null) {
			query.value = "";
		}
		if (value != null) {
			value.value = "";
		}
		if (display != null) {
			display.innerHTML = "";
		}
		return false;
	};
};