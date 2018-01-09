/**
 * Author: Trevor Isles
 * Version: 0.1.0 (Jan 3, 2017)
 * Since: OMIS 3.0
 */

/**
 * Assign all on click functionality for elements found on initial page load.
 */
function assignOnClick() {
	for (var index = 0; index < currentActivityNoteItemIndex; index++) {
		activityNoteItemRowOnClick(index);
	}
	for (var index = 0; index < currentActivityInvolvementItemIndex; index++) {
		activityInvolvementItemRowOnClick(index);
	}
};

/**
 * Assign all on click functionality for the security threat group activity 
 * note items action menu.
 */
function activityNoteItemsActionMenuOnClick() {
	$("#createActivityNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/stg/activity/createActivityNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {activityNoteItemIndex: currentActivityNoteItemIndex},
				success: function(data) {
					$("#activityNotesTableBody").append(data); 
					activityNoteItemRowOnClick(currentActivityNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#activityNotesTableBody").html(jqXHR.responseText );
				}
			});
		currentActivityNoteItemIndex++;
		return false;
	});
};

/**
 * Assign all on click functionality for the security threat group activity 
 * involvement items action menu.
 */
function activityInvolvementItemsActionMenuOnClick() {
	$("#createActivityInvolvementItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/stg/activity/createActivityInvolvementItem.html",
		   {
				type: "GET",
				async: false,
				data: {activityInvolvementItemIndex: currentActivityInvolvementItemIndex},
				success: function(data) {
					$("#activityInvolvementsTableBody").append(data); 
					activityInvolvementItemRowOnClick(currentActivityInvolvementItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#activityInvolvementsTableBody").html(jqXHR.responseText );
				}
			});
		currentActivityInvolvementItemIndex++;
		return false;
	});
};

/** Applies behavior to show involved offenders link.
 * 
 * 
 */
function showInvolvedOffendersOnClick() {
	$("#showInvolvedOffendersLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/stg/activity/includes/stgActivityInvolvementSummary.html",
			{
				type: "GET",
				async: false,
				data: {activityInvolvementItemIndex: currentActivityInvolvementItemIndex},
				success: function(data) {
					$("#activityInvolvementsTableBody").append(data);
					activityInvolvementItemRowOnClick(currentActivityInvolvementItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: " + errorThrown);
					$("#activityInvolvementsTableBody").html(jqXHR.responseText );
				}
			});
		currentActivityInvolvementItemIndex++;
		return false;
	});
};


/**
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 */
function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/**
 * Assigns on click functionality for the security threat group activity note
 * item row with the specified note item index.
 * 
 * @param activityNoteItemIndex security threat group activity note item index
 */
function activityNoteItemRowOnClick(activityNoteItemIndex) {
	assignDatePicker("activityNoteItemDate" + activityNoteItemIndex);
	$("#removeNoteLink" + activityNoteItemIndex).click(function() {
		if ($("#activityNoteOperation" + activityNoteItemIndex).val() == "UPDATE") {
			$("#activityNoteOperation" + activityNoteItemIndex).val("REMOVE");
			$("#activityNoteItemRow" + activityNoteItemIndex).addClass("removeRow");
		} else if($("#activityNoteOperation" + activityNoteItemIndex).val() == "REMOVE") {
			$("#activityNoteOperation" + activityNoteItemIndex).val("UPDATE");
			$("#activityNoteItemRow" + activityNoteItemIndex).removeClass("removeRow");
		} else {
			$("#activityNoteItemRow" + activityNoteItemIndex).remove();
		}
		return false;
	});
}

/**
 * Assigns on click functionality for the security threat group activity involvement
 * item row with the specified involvement item index.
 * 
 * @param activityInvolvementItemIndex security threat group activity involvement item index
 */
function activityInvolvementItemRowOnClick(activityInvolvementItemIndex) {
	applyOffenderSearch(document.getElementById("activityInvolvementItemOffenderInput" + activityInvolvementItemIndex), 
			document.getElementById("activityInvolvementItemOffender" + activityInvolvementItemIndex),
			document.getElementById("activityInvolvementItemDisplay" + activityInvolvementItemIndex), 
			document.getElementById("activityInvolvementItemClear" + activityInvolvementItemIndex));
	$("#removeLink" + activityInvolvementItemIndex).click(function() {
		if ($("#activityInvolvementOperation" + activityInvolvementItemIndex).val() == "UPDATE") {
			$("#activityInvolvementOperation" + activityInvolvementItemIndex).val("REMOVE");
			$("#activityInvolvementItemRow" + activityInvolvementItemIndex).addClass("removeRow");
		} else if($("#activityInvolvementOperation" + activityInvolvementItemIndex).val() == "REMOVE") {
			$("#activityInvolvementOperation" + activityInvolvementItemIndex).val("UPDATE");
			$("#activityInvolvementItemRow" + activityInvolvementItemIndex).removeClass("removeRow");
		} else {
			$("#activityInvolvementItemRow" + activityInvolvementItemIndex).remove();
		}
		return false;
	});
}