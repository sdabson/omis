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

/**
 * Applies officer case assignment list screen behavior.
 *
 * @author: Josh Divine
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var officerCaseAssignmentTableBody = document.getElementById("officerCaseAssignmentListTable");
	var rowLinks = officerCaseAssignmentTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, officerCaseAssignmentShowOffenderAssignmentsOnClick, function() {
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
	
	applyUserIDSearch(document.getElementById("officer"),
			document.getElementById("user"),
			document.getElementById("userAccountCurrentLabel"),
			document.getElementById("currentUserAccountLink"), 
			document.getElementById("clearUserLink")); 
	applyFieldFocus("singleDateSearch", "date");
	applyInputFocus("date", "singleDateSearch");
	applyFieldFocus("dateRangeSearch", "startDate");
	applyInputFocus("startDate", "dateRangeSearch");
	applyInputFocus("endDate", "dateRangeSearch");
	applyDatePicker("date");
	applyDatePicker("startDate");
	applyDatePicker("endDate");
};