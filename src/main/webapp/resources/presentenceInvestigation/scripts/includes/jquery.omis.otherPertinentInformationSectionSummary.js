function otherPertinentInformationSectionSummaryNoteItemsCreateOnClick() {
	$("#createOtherPertinentInformationSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/otherPertinentInformationSummary/createOtherPertinentInformationSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {otherPertinentInformationSectionSummaryNoteItemIndex: currentOtherPertinentInformationSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#otherPertinentInformationSectionSummaryNoteTableBody").append(data);
					otherPertinentInformationSectionSummaryNoteItemRowOnClick(currentOtherPertinentInformationSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#otherPertinentInformationSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentOtherPertinentInformationSectionSummaryNoteItemIndex++;
		return false;
	});
};

function otherPertinentInformationSectionSummaryNoteItemRowOnClick(otherPertinentInformationSectionSummaryNoteItemIndex) {
	assignDatePicker("otherPertinentInformationSectionSummaryNoteItemDate" + otherPertinentInformationSectionSummaryNoteItemIndex);
	$("#removeOtherPertinentInformationSectionSummaryNoteLink" + otherPertinentInformationSectionSummaryNoteItemIndex).click(function() {
		if ($("#otherPertinentInformationSectionSummaryNoteOperation" + otherPertinentInformationSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#otherPertinentInformationSectionSummaryNoteOperation" + otherPertinentInformationSectionSummaryNoteItemIndex).val("REMOVE");
			$("#otherPertinentInformationSectionSummaryNoteItemRow" + otherPertinentInformationSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#otherPertinentInformationSectionSummaryNoteOperation" + otherPertinentInformationSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#otherPertinentInformationSectionSummaryNoteOperation" + otherPertinentInformationSectionSummaryNoteItemIndex).val("UPDATE");
			$("#otherPertinentInformationSectionSummaryNoteItemRow" +otherPertinentInformationSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#otherPertinentInformationSectionSummaryNoteItemRow" + otherPertinentInformationSectionSummaryNoteItemIndex).remove();
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