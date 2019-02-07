function ratingNoteItemsCreateOnClick() {
	$("#createRatingNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/assessment/rating/notes/createRatingNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {ratingNoteItemIndex: currentRatingNoteItemIndex},
				success: function(data) {
					$("#ratingNoteTableBody").append(data);
					ratingNoteItemRowOnClick(currentRatingNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#ratingNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentRatingNoteItemIndex++;
		return false;
	});
};

function ratingNoteItemRowOnClick(ratingNoteItemIndex) {
	assignDatePicker("ratingNoteItemDate" + ratingNoteItemIndex);
	$("#removeRatingNoteLink" + ratingNoteItemIndex).click(function() {
		if ($("#ratingNoteOperation" + ratingNoteItemIndex).val() == "UPDATE") {
			$("#ratingNoteOperation" + ratingNoteItemIndex).val("REMOVE");
			$("#ratingNoteItemRow" + ratingNoteItemIndex).addClass("removeRow");
		} else if($("#ratingNoteOperation" + ratingNoteItemIndex).val() == "REMOVE") {
			$("#ratingNoteOperation" + ratingNoteItemIndex).val("UPDATE");
			$("#ratingNoteItemRow" +ratingNoteItemIndex).removeClass("removeRow");
		} else {
			$("#ratingNoteItemRow" + ratingNoteItemIndex).remove();
		}
		return false;
	});
};


function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};