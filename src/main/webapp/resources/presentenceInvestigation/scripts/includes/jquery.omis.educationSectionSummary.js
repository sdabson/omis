function refreshEducationsListOnClick() {
	$("#refreshEducationsLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/educationSummary/refresh.html",
		   {
				type: "GET",
				async: false,
				data: {
					offender: $("#offenderId").val() },
				success: function(data) {
					$("#educationsLists").html(data);
					var rows = document.getElementsByClassName('rowActionMenuItem');
					for(var i = 0; i < rows.length; i++){
						applyActionMenu(rows[i]);
						
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#educationsLists").html(jqXHR.responseText );
				}
			});
		return false;
	});
};


function educationSectionSummaryNoteItemsCreateOnClick() {
	$("#createEducationSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/educationSummary/createEducationSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {educationSectionSummaryNoteItemIndex: currentEducationSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#educationSectionSummaryNoteTableBody").append(data);
					educationSectionSummaryNoteItemRowOnClick(currentEducationSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#educationSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentEducationSectionSummaryNoteItemIndex++;
		return false;
	});
};

function educationSectionSummaryNoteItemRowOnClick(educationSectionSummaryNoteItemIndex) {
	assignDatePicker("educationSectionSummaryNoteItemDate" + educationSectionSummaryNoteItemIndex);
	$("#removeEducationSectionSummaryNoteLink" + educationSectionSummaryNoteItemIndex).click(function() {
		if ($("#educationSectionSummaryNoteOperation" + educationSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#educationSectionSummaryNoteOperation" + educationSectionSummaryNoteItemIndex).val("REMOVE");
			$("#educationSectionSummaryNoteItemRow" + educationSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#educationSectionSummaryNoteOperation" + educationSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#educationSectionSummaryNoteOperation" + educationSectionSummaryNoteItemIndex).val("UPDATE");
			$("#educationSectionSummaryNoteItemRow" +educationSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#educationSectionSummaryNoteItemRow" + educationSectionSummaryNoteItemIndex).remove();
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