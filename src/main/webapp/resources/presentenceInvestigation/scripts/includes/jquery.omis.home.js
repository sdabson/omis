function psiTaskCheckBoxOnClick(targetElement) {
	$(targetElement).click(function() {
		if ($(targetElement).val() == "INCOMPLETE") {
			$(targetElement).val("SET_COMPLETE");
		} else if($(targetElement).val() == "SET_COMPLETE") {
			$(targetElement).val("INCOMPLETE");
		}
	});
};

function presentenceInvestigationRequestNoteItemsCreateOnClick() {
	$("#createPresentenceInvestigationRequestNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/request/createPresentenceInvestigationRequestNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {presentenceInvestigationRequestNoteItemIndex: currentPresentenceInvestigationRequestNoteItemIndex},
				success: function(data) {
					$("#presentenceInvestigationRequestNoteTableBody").append(data);
					presentenceInvestigationRequestNoteItemRowOnClick(currentPresentenceInvestigationRequestNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#presentenceInvestigationRequestNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentPresentenceInvestigationRequestNoteItemIndex++;
		return false;
	});
};

function presentenceInvestigationRequestNoteItemRowOnClick(presentenceInvestigationRequestNoteItemIndex) {
	assignDatePicker("presentenceInvestigationRequestNoteItemDate" + presentenceInvestigationRequestNoteItemIndex);
	$("#removePresentenceInvestigationRequestNoteLink" + presentenceInvestigationRequestNoteItemIndex).click(function() {
		if ($("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val() == "UPDATE") {
			$("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val("REMOVE");
			$("#presentenceInvestigationRequestNoteItemRow" + presentenceInvestigationRequestNoteItemIndex).addClass("removeRow");
		} else if($("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val() == "REMOVE") {
			$("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val("UPDATE");
			$("#presentenceInvestigationRequestNoteItemRow" +presentenceInvestigationRequestNoteItemIndex).removeClass("removeRow");
		} else {
			$("#presentenceInvestigationRequestNoteItemRow" + presentenceInvestigationRequestNoteItemIndex).remove();
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