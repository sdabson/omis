function assessmentCategoryOverrideNoteItemsCreateOnClick() {
	$("#createAssessmentCategoryOverrideNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/assessment/rating/createAssessmentCategoryOverrideNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {assessmentCategoryOverrideNoteItemIndex: currentAssessmentCategoryOverrideNoteItemIndex},
				success: function(data) {
					$("#assessmentCategoryOverrideNoteTableBody").append(data);
					assessmentCategoryOverrideNoteItemRowOnClick(currentAssessmentCategoryOverrideNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#assessmentCategoryOverrideNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentAssessmentCategoryOverrideNoteItemIndex++;
		return false;
	});
};

function assessmentCategoryOverrideNoteItemRowOnClick(assessmentCategoryOverrideNoteItemIndex) {
	assignDatePicker("assessmentCategoryOverrideNoteItemDate" + assessmentCategoryOverrideNoteItemIndex);
	$("#removeAssessmentCategoryOverrideNoteLink" + assessmentCategoryOverrideNoteItemIndex).click(function() {
		if ($("#assessmentCategoryOverrideNoteOperation" + assessmentCategoryOverrideNoteItemIndex).val() == "UPDATE") {
			$("#assessmentCategoryOverrideNoteOperation" + assessmentCategoryOverrideNoteItemIndex).val("REMOVE");
			$("#assessmentCategoryOverrideNoteItemRow" + assessmentCategoryOverrideNoteItemIndex).addClass("removeRow");
		} else if($("#assessmentCategoryOverrideNoteOperation" + assessmentCategoryOverrideNoteItemIndex).val() == "REMOVE") {
			$("#assessmentCategoryOverrideNoteOperation" + assessmentCategoryOverrideNoteItemIndex).val("UPDATE");
			$("#assessmentCategoryOverrideNoteItemRow" +assessmentCategoryOverrideNoteItemIndex).removeClass("removeRow");
		} else {
			$("#assessmentCategoryOverrideNoteItemRow" + assessmentCategoryOverrideNoteItemIndex).remove();
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