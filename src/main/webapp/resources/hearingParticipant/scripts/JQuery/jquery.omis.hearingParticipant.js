function hearingParticipantNoteItemsCreateOnClick() {
	$("#createHearingParticipantNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/hearingParticipant/createHearingParticipantNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {hearingParticipantNoteItemIndex: currentHearingParticipantNoteItemIndex},
				success: function(data) {
					$("#hearingParticipantNoteTableBody").append(data);
					hearingParticipantNoteItemRowOnClick(currentHearingParticipantNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#hearingParticipantNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentHearingParticipantNoteItemIndex++;
		return false;
	});
};

function hearingParticipantNoteItemRowOnClick(hearingParticipantNoteItemIndex) {
	assignDatePicker("hearingParticipantNoteItemDate" + hearingParticipantNoteItemIndex);
	$("#removeHearingParticipantNoteLink" + hearingParticipantNoteItemIndex).click(function() {
		if ($("#hearingParticipantNoteOperation" + hearingParticipantNoteItemIndex).val() == "UPDATE") {
			$("#hearingParticipantNoteOperation" + hearingParticipantNoteItemIndex).val("REMOVE");
			$("#hearingParticipantNoteItemRow" + hearingParticipantNoteItemIndex).addClass("removeRow");
		} else if($("#hearingParticipantNoteOperation" + hearingParticipantNoteItemIndex).val() == "REMOVE") {
			$("#hearingParticipantNoteOperation" + hearingParticipantNoteItemIndex).val("UPDATE");
			$("#hearingParticipantNoteItemRow" +hearingParticipantNoteItemIndex).removeClass("removeRow");
		} else {
			$("#hearingParticipantNoteItemRow" + hearingParticipantNoteItemIndex).remove();
		}
		return false;
	});
};

function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};