function evaluationRecommendationSectionSummaryNoteItemsCreateOnClick() {
	$("#createEvaluationRecommendationSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/evaluationRecommendationSummary/createEvaluationRecommendationSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {evaluationRecommendationSectionSummaryNoteItemIndex: currentEvaluationRecommendationSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#evaluationRecommendationSectionSummaryNoteTableBody").append(data);
					evaluationRecommendationSectionSummaryNoteItemRowOnClick(currentEvaluationRecommendationSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#evaluationRecommendationSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentEvaluationRecommendationSectionSummaryNoteItemIndex++;
		return false;
	});
};

function evaluationRecommendationSectionSummaryNoteItemRowOnClick(evaluationRecommendationSectionSummaryNoteItemIndex) {
	assignDatePicker("evaluationRecommendationSectionSummaryNoteItemDate" + evaluationRecommendationSectionSummaryNoteItemIndex);
	$("#removeEvaluationRecommendationSectionSummaryNoteLink" + evaluationRecommendationSectionSummaryNoteItemIndex).click(function() {
		if ($("#evaluationRecommendationSectionSummaryNoteOperation" + evaluationRecommendationSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#evaluationRecommendationSectionSummaryNoteOperation" + evaluationRecommendationSectionSummaryNoteItemIndex).val("REMOVE");
			$("#evaluationRecommendationSectionSummaryNoteItemRow" + evaluationRecommendationSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#evaluationRecommendationSectionSummaryNoteOperation" + evaluationRecommendationSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#evaluationRecommendationSectionSummaryNoteOperation" + evaluationRecommendationSectionSummaryNoteItemIndex).val("UPDATE");
			$("#evaluationRecommendationSectionSummaryNoteItemRow" +evaluationRecommendationSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#evaluationRecommendationSectionSummaryNoteItemRow" + evaluationRecommendationSectionSummaryNoteItemIndex).remove();
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