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
 * Presentence investigation requests java script.
 * 
 * Author: Joel Norris
 * Author: Annie Wahl
 * Author: Josh Divine
 * Version: 0.1.4 (Feb 27, 2019)
 * Since: OMIS 3.0
 */
window.onload = function() {
	if (document.getElementById("actionMenuLink") != null) {
		applyActionMenu(document.getElementById("actionMenuLink"));
	}
	if (document.getElementById("unsubmittedActionMenuLink") != null) {
		applyActionMenu(document.getElementById("unsubmittedActionMenuLink"));
	}
	if (document.getElementById("submittedActionMenuLink") != null) {
		applyActionMenu(document.getElementById("submittedActionMenuLink"));
	}
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
		
	}
	
	if (document.getElementById("userAccountInput")) {
		applySearchUserAccountsAutocomplete(
				document.getElementById("userAccountInput"),
				document.getElementById("userAccountDisplay"),
				document.getElementById("userAccount"),
				document.getElementById("clearUserAccount"),
				document.getElementById("currentUserAccount"));
		applyDatePicker(document.getElementById("startDate"));
		applyDatePicker(document.getElementById("endDate"));
		
		applyFieldFocus("searchByUser", "userAccountInput");
		applyInputFocus("userAccountInput", "searchByUser");
		applyFieldFocus("searchByName", "lastName");
		applyInputFocus("lastName", "searchByName");
		applyInputFocus("firstName", "searchByName");
		
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