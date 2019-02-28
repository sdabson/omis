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
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
	}
	
	if (document.getElementsByClassName("requestDate")) {
		applyDatePicker(document.getElementById("requestDate"));
		applyDatePicker(document.getElementById("requestStartDate"));
		applyDatePicker(document.getElementById("requestEndDate"));
		applyDatePicker(document.getElementById("eligibilityDate"));
		applyDatePicker(document.getElementById("eligibilityStartDate"));
		applyDatePicker(document.getElementById("eligibilityEndDate"));
	}
}