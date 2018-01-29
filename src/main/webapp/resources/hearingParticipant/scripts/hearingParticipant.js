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
	applyActionMenu(document.getElementById("hearingParticipantNoteItemsActionMenuLink"), hearingParticipantNoteItemsCreateOnClick);
	for (var index = 0; index < currentHearingParticipantNoteItemIndex; index++) {
		hearingParticipantNoteItemRowOnClick(index);
	}
	
	var category = document.getElementById("category");
	var offenderId = document.getElementById("offender").value;
	if (category.value == "STAFF") {
		document.getElementById("searchStaffFields").classList.remove("hidden");
	} else if (category.value == "FAMILY") {
		document.getElementById("searchFamilyFields").classList.remove("hidden");
	} else if (category.value == "VICTIM") {
		document.getElementById("searchVictimFields").classList.remove("hidden");
	} else {
		//I dunno yet.
	}
	
	category.onchange = function(event) {
		if (event.target.value == "STAFF") {
			document.getElementById("searchStaffFields").classList.remove("hidden");
			document.getElementById("searchFamilyFields").classList.add("hidden");
			document.getElementById("searchVictimFields").classList.add("hidden");
		} else if (event.target.value == "FAMILY") {
			document.getElementById("searchFamilyFields").classList.remove("hidden");
			document.getElementById("searchStaffFields").classList.add("hidden");
			document.getElementById("searchVictimFields").classList.add("hidden");
		} else if (event.target.value == "VICTIM") {
			document.getElementById("searchVictimFields").classList.remove("hidden");
			document.getElementById("searchStaffFields").classList.add("hidden");
			document.getElementById("searchFamilyFields").classList.add("hidden");
		} else {
			//I dunno yet.
		}
		document.getElementById("person").value = "";
		document.getElementById("familyInput").value = "";
		document.getElementById("victimInput").value = "";
		document.getElementById("staffInput").value = "";
		document.getElementById("familyDisplay").innerHTML = "";
		document.getElementById("victimDisplay").innerHTML = "";
		document.getElementById("staffDisplay").innerHTML = "";
	}
	
	applyValueLabelAutoComplete(document.getElementById("familyInput"),  
			document.getElementById("person"),
			config.ServerConfig.getContextPath() +
			"/hearingParticipant/searchFamily.json?offender=" + offenderId);
	applyValueLabelAutoComplete(document.getElementById("victimInput"),  
			document.getElementById("person"),
			config.ServerConfig.getContextPath() +
			"/hearingParticipant/searchVictims.json?offender=" + offenderId);
	applyStaffSearch(document.getElementById("staffInput"),
						document.getElementById("person"), 
						document.getElementById("clearStaff"),
						document.getElementById("currentStaff")); 
	document.getElementById("clearFamily").onclick = function() {
		document.getElementById("person").value = "";
		document.getElementById("familyInput").value = "";
		document.getElementById("familyDisplay").innerHTML = "";
	}
	document.getElementById("clearVictim").onclick = function() {
		document.getElementById("person").value = "";
		document.getElementById("victimInput").value = "";
		document.getElementById("victimDisplay").innerHTML = "";
	}
	document.getElementById("clearStaff").onclick = function() {
		document.getElementById("person").value = "";
		document.getElementById("staffInput").value = "";
		document.getElementById("staffDisplay").innerHTML = "";
	}
	
//	applyPersonSearch(document.getElementById("test"),
//						document.getElementById("hidden"), 
//						document.getElementById("clear")); 
}