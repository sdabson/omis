/**
 * Assign all on click functionality for elements found on inital page load.
 */
function assignOnClick() {
	for (var index = 0; index < currentOffenderPhotoAssociationNoteItemIndex; index++) {
		offenderPhotoAssociationNoteItemRowOnClick(index);
	}
};

/**
 * Assigns on click functionality for the service term note items action menu.
 */
function offenderPhotoAssociationNoteItemsActionMenuOnClick() {
	$("#createOffenderPhotoAssociationNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/offenderPhoto/createOffenderPhotoAssociationNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {offenderPhotoAssociationNoteItemIndex: currentOffenderPhotoAssociationNoteItemIndex},
				success: function(data) {
					$("#offenderPhotoAssociationNotesTableBody").append(data);
					offenderPhotoAssociationNoteItemRowOnClick(currentOffenderPhotoAssociationNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#offenderPhotoAssociationNotesTableBody").html(jqXHR.responseText );
				}
			});
		currentOffenderPhotoAssociationNoteItemIndex++;
		return false;
	});
};

/**
 * Assigns on click functionality for the service term note item row with the
 * specified service term note item index.
 * 
 * @param offenderPhotoAssociationNoteItemIndex service term note item index
 */
function offenderPhotoAssociationNoteItemRowOnClick(offenderPhotoAssociationNoteItemIndex) {
	assignDatePicker("offenderPhotoAssociationNoteItemDate" + offenderPhotoAssociationNoteItemIndex);
	applyTextCounter(document.getElementById("offenderPhotoAssociationNoteItems[" + offenderPhotoAssociationNoteItemIndex + "].value"), document.getElementById("valueCharacterCounter" + offenderPhotoAssociationNoteItemIndex));
	$("#removeLink" + offenderPhotoAssociationNoteItemIndex).click(function() {
		if ($("#offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).val() == "UPDATE") {
			$("#offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).val("REMOVE");
			$("#offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).addClass("removeRow");
		} else if($("#offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).val() == "REMOVE") {
			$("#offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).val("UPDATE");
			$("#offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).removeClass("removeRow");
		} else {
			$("#offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).remove();
		}
		return false;
	});
}

/**
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 */
function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};