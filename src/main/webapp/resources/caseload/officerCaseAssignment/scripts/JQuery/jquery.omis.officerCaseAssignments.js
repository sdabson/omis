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
 * Applies JQuery officer case assignment edit screen behavior.
 *
 * @author: Josh Divine
 * @author: Annie Wahl
 * @version: 0.1.2 (Jan 8, 2019)
 * @since: OMIS 3.0
 */

/**
 * Applies a jQuery date picker to the element with the specified id.
 * 
 * @param inputIdToAssign input ID to assign
 */
function applyDatePicker(inputIdToAssign) {
	$("#" + inputIdToAssign).datepicker({
		changeMonth: true,
		changeYear: true
	});
}

function officerCaseAssignmentShowOffenderAssignmentsOnClick(event, targetElement) {
	var element = event.target;
	var ocaRow = $(element).closest("tr.officerCaseAssignmentClass");
	var viewOffenderAssign = $(targetElement).find(".showHideOffenderAssignmentsLink")[0];
	var ocaId = getUrlVars(viewOffenderAssign.href)['officerCaseAssignment'];
	$(viewOffenderAssign).click(function() {
		if (document.getElementById("subAssignmentsRow" + ocaId) != null) {
			$("#subAssignmentsRow" + ocaId).remove();
		} else {
			$.ajax(viewOffenderAssign.href,
					{async: false, type: "GET",
					cache: false,
					success: function(response) {
						$(response).insertAfter(ocaRow);
						var rowLinks = document.getElementById("subAssignmentsRow" + ocaId).getElementsByTagName("a");
						for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
							var rowLink = rowLinks[rowLinkIndex];
							if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
								applyActionMenu(rowLink, function() {
									applyRemoveLinkConfirmation();
								});
							}
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$(ocaRow).html(jqXHR.responseText );
					}
				});
		}
		return false;
	});
};

function getUrlVars(url) {
    var vars = {};
    var parts = url.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}