/**
 * Assigns on click functionality for the note items create link.
 */
function workRestrictionNoteItemsCreateOnClick() {
	$("#createNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/workRestriction/createNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {noteItemIndex: currentNoteItemIndex},
				success: function(data) {
					$("#noteTableBody").append(data);
					noteItemRowOnClick(currentNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#noteTableBody").html(jqXHR.responseText );
				}
			});
		currentNoteItemIndex++;
		return false;
	});
};

/**
 * Assigns on click functionality for the note item row with the
 * specified note item index.
 * 
 * @param noteItemIndex note item index
 */
function noteItemRowOnClick(noteItemIndex) {
	assignDatePicker("noteItemDate" + noteItemIndex);
	$("#removeNoteLink" + noteItemIndex).click(function() {
		if ($("#noteOperation" + noteItemIndex).val() == "UPDATE") {
			$("#noteOperation" + noteItemIndex).val("REMOVE");
			$("#noteItemRow" + noteItemIndex).addClass("removeRow");
		} else if($("#noteOperation" + noteItemIndex).val() == "REMOVE") {
			$("#noteOperation" + noteItemIndex).val("UPDATE");
			$("#noteItemRow" +noteItemIndex).removeClass("removeRow");
		} else {
			$("#noteItemRow" + noteItemIndex).remove();
		}
		return false;
	});
};

/**
 * Assigns the date picker to the element by specified id
 * @param elementId element ID
 */
function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

 

/**
 * Assigns on-click functionality to any existing notes and achievements
 */
function assignOnClick() {
	for (var index = 0; index < currentNoteItemIndex; index++) {
		noteItemRowOnClick(index);
	}
};