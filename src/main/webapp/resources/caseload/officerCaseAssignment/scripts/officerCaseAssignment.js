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
 * Applies officer case assignment edit screen behavior.
 *
 * @author: Josh Divine
 * @author: Ryan Johns
 * @author: Annie Wahl
 * @author: Trevor Isles
 * @version: 0.1.5 (Dec 6, 2018)
 * @since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("helpMenuLink"));
	applyDatePicker("startDate");
	applyTimePicker(document.getElementById("startTime"));
	applyDatePicker("endDate");
	applyTimePicker(document.getElementById("endTime"));
	applyOfficeFilterOnClick();
	applyUserIDSearch(document.getElementById("assignedTo"),
			document.getElementById("officer"),
			document.getElementById("userAccountCurrentLabel"),
			document.getElementById("currentUserAccountLink"), 
			document.getElementById("clearUserLink")); 
	applyOffenderSearch(document.getElementById("offenderSearch"),
			document.getElementById("selectedOffender"),
			document.getElementById("offenderCurrentLabel"),
			document.getElementById("clearOffenderLink"));
	applyActionMenu(document.getElementById("officerCaseAssignmentNoteItemsActionMenuLink"), officerCaseAssignmentNotesCreateOnClick);
	for (var index = 0; index < currentOfficerCaseAssignmentNoteIndex; index++) {
		officerCaseAssignmentNoteItemRowOnClick(index);
	}
	var allowInterstateCompact = document.getElementById("allowInterstateCompact");
    if (allowInterstateCompact.value =="true") {
    	applyDatePicker("projectedEndDate");
    	document.getElementById("interstateCaseload").onclick = function() {
    		if (this.checked) {
    			var interstateCompactFields = document.getElementById("interstateCompactFields");
    			if (ui.hasClass(interstateCompactFields, "hidden")) {
    				ui.removeClass(interstateCompactFields, "hidden")
    			}
    			var projectedEndDateField = document.getElementById("projectedEndDateField");
    			if (ui.hasClass(projectedEndDateField, "hidden")) {
    				ui.removeClass(projectedEndDateField, "hidden")
    			}
    			var endReasonField = document.getElementById("endReasonField");
    			if (ui.hasClass(endReasonField, "hidden")) {
    				ui.removeClass(endReasonField, "hidden")
    			}
    		} else {
    			var interstateCompactFields = document.getElementById("interstateCompactFields");
    			if (!ui.hasClass(interstateCompactFields, "hidden")) {
    				ui.addClass(interstateCompactFields, "hidden")
    			}
    			var projectedEndDateField = document.getElementById("projectedEndDateField");
    			if (!ui.hasClass(projectedEndDateField, "hidden")) {
    				ui.addClass(projectedEndDateField, "hidden")
    			}
    			var endReasonField = document.getElementById("endReasonField");
    			if (!ui.hasClass(endReasonField, "hidden")) {
    				ui.addClass(endReasonField, "hidden")
    			}
    		}
    	}
    }
};