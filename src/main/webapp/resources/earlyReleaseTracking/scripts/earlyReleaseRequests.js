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
window.onload = function() {
	if (document.getElementById("actionMenuLink")) {
		applyActionMenu(document.getElementById("actionMenuLink"));
	}
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
	}
	
	if (document.getElementById("requestDate")) {
		applyDatePicker(document.getElementById("requestDate"));
		applyDatePicker(document.getElementById("requestStartDate"));
		applyDatePicker(document.getElementById("requestEndDate"));
		applyDatePicker(document.getElementById("eligibilityDate"));
		applyDatePicker(document.getElementById("eligibilityStartDate"));
		applyDatePicker(document.getElementById("eligibilityEndDate"));
		
		applyFieldFocus("searchSingleEligibilityDate", "eligibilityDate");
		applyInputFocus("eligibilityDate", "searchSingleEligibilityDate");
		applyFieldFocus("searchEligibilityDateRange", "eligibilityStartDate");
		applyInputFocus("eligibilityStartDate", "searchEligibilityDateRange");
		applyInputFocus("eligibilityEndDate", "searchEligibilityDateRange");

		applyFieldFocus("searchSingleRequestDate", "requestDate");
		applyInputFocus("requestDate", "searchSingleRequestDate");
		applyFieldFocus("searchRequestDateRange", "requestStartDate");
		applyInputFocus("requestStartDate", "searchRequestDateRange");
		applyInputFocus("requestEndDate", "searchRequestDateRange");
		
		applyFieldFocus("response", "releaseStatus");
		applyInputFocus("releaseStatus", "response");
		applyFieldFocus("noResponse", "noResponseLabel");
		applyInputFocus("noResponseLabel", "noResponse");
		
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
	}
}