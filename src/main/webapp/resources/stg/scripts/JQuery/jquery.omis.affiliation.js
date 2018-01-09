/**
 * Author: Trevor Isles
 * Version: 0.1.0 (Nov 17, 2016)
 * since: OMIS 3.0
 */

/**
 * Assign all on click functionality for elements found on initial page load.
 */
function assignOnClick() {
	for (var index = 0; index < currentAffiliationNoteItemIndex; index++) {
		affiliationNoteItemRowOnClick(index);
	}
};

/**
 * Assigns on click functionality for the security threat group affiliation 
 * note items action menu.
 */
function affiliationNoteItemsActionMenuOnClick() {
	$("#createAffiliationNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/stg/affiliation/createAffiliationNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {affiliationNoteItemIndex: currentAffiliationNoteItemIndex},
				success: function(data) {
					$("#affiliationNotesTableBody").append(data); 
					affiliationNoteItemRowOnClick(currentAffiliationNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#affiliationNotesTableBody").html(jqXHR.responseText );
				}
			});
		currentAffiliationNoteItemIndex++;
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
 * Assigns on click functionality for the security threat group affiliation
 *  note item row with the specified service term note item index.
 * 
 * @param affiliationNoteItemIndex security threat group affiliation note item 
 * index
 */
function affiliationNoteItemRowOnClick(affiliationNoteItemIndex) {
	assignDatePicker("affiliationNoteItemDate" + affiliationNoteItemIndex);
	$("#removeLink" + affiliationNoteItemIndex).click(function() {
		if ($("#affiliationNoteOperation" + affiliationNoteItemIndex).val() == "UPDATE") {
			$("#affiliationNoteOperation" + affiliationNoteItemIndex).val("REMOVE");
			$("#affiliationNoteItemRow" + affiliationNoteItemIndex).addClass("removeRow");
		} else if($("#affiliationNoteOperation" + affiliationNoteItemIndex).val() == "REMOVE") {
			$("#affiliationNoteOperation" + affiliationNoteItemIndex).val("UPDATE");
			$("#affiliationNoteItemRow" + affiliationNoteItemIndex).removeClass("removeRow");
		} else {
			$("#affiliationNoteItemRow" + affiliationNoteItemIndex).remove();
		}
		return false;
	});
}