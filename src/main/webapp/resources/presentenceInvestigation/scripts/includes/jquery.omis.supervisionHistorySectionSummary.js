function supervisionHistoryNoteItemsCreateOnClick() {	
	$("#createSupervisionHistoryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/supervisionHistorySummary/createSupervisionHistoryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {supervisionHistoryNoteItemIndex: currentSupervisionHistoryNoteItemIndex},
				success: function(data) {
					$("#supervisionHistoryNoteTableBody").append(data);
					supervisionHistoryNoteItemRowOnClick(currentSupervisionHistoryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#supervisionHistoryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentSupervisionHistoryNoteItemIndex++;
		return false;
	});
};

function supervisionHistoryNoteItemRowOnClick(supervisionHistoryNoteItemIndex) {
	assignDatePicker("supervisionHistoryNoteItemDate" + supervisionHistoryNoteItemIndex);
	$("#removeSupervisionHistoryNoteLink" + supervisionHistoryNoteItemIndex).click(function() {
		if ($("#supervisionHistoryNoteOperation" + supervisionHistoryNoteItemIndex).val() == "UPDATE") {
			$("#supervisionHistoryNoteOperation" + supervisionHistoryNoteItemIndex).val("REMOVE");
			$("#supervisionHistoryNoteItemRow" + supervisionHistoryNoteItemIndex).addClass("removeRow");
		} else if($("#supervisionHistoryNoteOperation" + supervisionHistoryNoteItemIndex).val() == "REMOVE") {
			$("#supervisionHistoryNoteOperation" + supervisionHistoryNoteItemIndex).val("UPDATE");
			$("#supervisionHistoryNoteItemRow" +supervisionHistoryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#supervisionHistoryNoteItemRow" + supervisionHistoryNoteItemIndex).remove();
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