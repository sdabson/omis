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
 * Functionality for offender relationship note items.
 *
 * @author Stephen Abson
 */

/**
 * Applies behavior of offender relationship note items.
 * 
 * @param noteItemsName note items name
 * @param itemIndex note item index
 */
function applyOffenderRelationshipNoteItemBehavior(noteItemsName, itemIndex) {
	var removeLink = document.getElementById(noteItemsName + "[" + itemIndex + "].removeLink");
	removeLink.onclick = function() {
		var operation = document.getElementById(this.getAttribute("id").replace(".removeLink", ".operation"));
		var row = document.getElementById(this.getAttribute("id").replace(".removeLink", ".row"))
		if (operation.value == "CREATE") {
			row.parentNode.removeChild(row);
		} else if (operation.value == "UPDATE") {
			if (!ui.hasClass(row, "removeRow")) {
				ui.addClass(row, "removeRow");
			}
			operation.value = "REMOVE";
		} else if (operation.value == "REMOVE") {
			if (ui.hasClass(row, "removeRow")) {
				ui.removeClass(row, "removeRow");
			}
			operation.value = "UPDATE";
		} else {
			alert("Unsupported operation: " + operation.value);
		}
	}
	applyDatePicker(document.getElementById(noteItemsName + "[" + itemIndex + "].fields.date"));
}