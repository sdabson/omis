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
 * Behavior to list grievances.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var grievancesTableBody = document.getElementById("grievances");
	var rowLinks = grievancesTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
	var queryInput = document.getElementById("grievanceSearchOffenderQuery");
	if (queryInput != null) {
		
		// Adds or replaces oldString in value with newString
		function addOrReplace(value, oldString, newString) {
			if (value != null && value != "") {
				if (value.indexOf(oldString) > -1) {
					if (value.indexOf(newString) > -1) {
						return value;
					} else {
						return value.replace(oldString, newString);
					}
				} else {
					if (value.indexOf(newString) > -1) {
						return value;
					} else {
						return value + " " + newString;
					}
				}
			} else {
				return newString;
			}
		}
		
		// Search fields
		var grievanceSearchOffenderQuery = document.getElementById("grievanceSearchOffenderQuery");
		var searchLocation = document.getElementById("searchLocation");
		
		// Applies behavior for clicking offender search type
		function applyOffenderSearchTypeClick() {
			grievanceSearchOffenderQuery.setAttribute("class", addOrReplace(grievanceSearchOffenderQuery.getAttribute("class"), "hidden", "shown"));
			searchLocation.setAttribute("class", addOrReplace(searchLocation.getAttribute("class"), "shown", "hidden"));
		}
		
		// Applies behavior for clicking location search type
		function applyLocationSearchTypeClick() {
			grievanceSearchOffenderQuery.setAttribute("class", addOrReplace(grievanceSearchOffenderQuery.getAttribute("class"), "shown", "hidden"));
			searchLocation.setAttribute("class", addOrReplace(searchLocation.getAttribute("class"), "hidden", "shown"));
		}
		
		// Applies behavior
		var searchOffender = document.getElementById("searchOffender");
		applyValueLabelAutoComplete(queryInput, searchOffender, config.ServerConfig.getContextPath() + "/grievance/findOffender.json");
		var grievanceOffenderSearchType = document.getElementById("grievanceSearchType.OFFENDER");
		var grievanceLocationSearchType = document.getElementById("grievanceSearchType.LOCATION");
		if (grievanceOffenderSearchType.checked) {
			applyOffenderSearchTypeClick();
		} else if (grievanceLocationSearchType.checked) {
			applyLocationSearchTypeClick();
		}
		grievanceOffenderSearchType.onclick = function() {
			applyOffenderSearchTypeClick();
		}
		grievanceLocationSearchType.onclick = function() {
			applyLocationSearchTypeClick();
		}
	}
};