function jailAdjustmentSectionSummaryNoteItemsCreateOnClick() {
	$("#createJailAdjustmentSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/jailAdjustmentSummary/createJailAdjustmentSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {jailAdjustmentSectionSummaryNoteItemIndex: currentJailAdjustmentSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#jailAdjustmentSectionSummaryNoteTableBody").append(data);
					jailAdjustmentSectionSummaryNoteItemRowOnClick(currentJailAdjustmentSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#jailAdjustmentSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentJailAdjustmentSectionSummaryNoteItemIndex++;
		return false;
	});
};

function jailAdjustmentSectionSummaryNoteItemRowOnClick(jailAdjustmentSectionSummaryNoteItemIndex) {
	assignDatePicker("jailAdjustmentSectionSummaryNoteItemDate" + jailAdjustmentSectionSummaryNoteItemIndex);
	$("#removeJailAdjustmentSectionSummaryNoteLink" + jailAdjustmentSectionSummaryNoteItemIndex).click(function() {
		if ($("#jailAdjustmentSectionSummaryNoteOperation" + jailAdjustmentSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#jailAdjustmentSectionSummaryNoteOperation" + jailAdjustmentSectionSummaryNoteItemIndex).val("REMOVE");
			$("#jailAdjustmentSectionSummaryNoteItemRow" + jailAdjustmentSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#jailAdjustmentSectionSummaryNoteOperation" + jailAdjustmentSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#jailAdjustmentSectionSummaryNoteOperation" + jailAdjustmentSectionSummaryNoteItemIndex).val("UPDATE");
			$("#jailAdjustmentSectionSummaryNoteItemRow" +jailAdjustmentSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#jailAdjustmentSectionSummaryNoteItemRow" + jailAdjustmentSectionSummaryNoteItemIndex).remove();
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