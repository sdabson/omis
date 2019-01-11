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
 * @version: 0.1.1 (Sep 10, 2018)
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

function applyOfficeFilterOnClick() {
	var filter = $("input[name=officeFilter]");
	filter.click(function() {
		officeFilterChangeFunction(this.value);
	});
};

function officeFilterChangeFunction(filter) {
	var url = "showOfficeOptions.html";
	$.ajax({
		type: "GET",	
		async: false,
		url: url,
		data:{
			officeFilter: filter
		},
		success: function(response) {
			 $("#location").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#location").html(jqXHR.responseText );
		}
	});
};

function officerCaseAssignmentNotesCreateOnClick() {
	$("#createOfficerCaseAssignmentNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/caseload/officerCaseAssignment/addOfficerCaseAssignmentNote.html",
		   {
				type: "GET",
				async: false,
				data: {officerCaseAssignmentNoteIndex: currentOfficerCaseAssignmentNoteIndex},
				success: function(data) {
					$("#officerCaseAssignmentNoteTableBody").append(data);
					officerCaseAssignmentNoteItemRowOnClick(currentOfficerCaseAssignmentNoteIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#officerCaseAssignmentNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentOfficerCaseAssignmentNoteIndex++;
		return false;
	});
};

function officerCaseAssignmentNoteItemRowOnClick(officerCaseAssignmentNoteIndex) {
	applyDatePicker("officerCaseAssignmentNoteItemDate" + officerCaseAssignmentNoteIndex);
	$("#removeOfficerCaseAssignmentNoteLink" + officerCaseAssignmentNoteIndex).click(function() {
		if ($("#officerCaseAssignmentNoteOperation" + officerCaseAssignmentNoteIndex).val() == "EDIT") {
			$("#officerCaseAssignmentNoteOperation" + officerCaseAssignmentNoteIndex).val("REMOVE");
			$("#officerCaseAssignmentNoteItemRow" + officerCaseAssignmentNoteIndex).addClass("removeRow");
		} else if($("#officerCaseAssignmentNoteOperation" + officerCaseAssignmentNoteIndex).val() == "REMOVE") {
			$("#officerCaseAssignmentNoteOperation" + officerCaseAssignmentNoteIndex).val("EDIT");
			$("#officerCaseAssignmentNoteItemRow" +officerCaseAssignmentNoteIndex).removeClass("removeRow");
		} else {
			$("#officerCaseAssignmentNoteItemRow" + officerCaseAssignmentNoteIndex).remove();
		}
		return false;
	});
};