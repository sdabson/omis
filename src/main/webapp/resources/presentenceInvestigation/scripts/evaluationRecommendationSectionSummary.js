window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("evaluationRecommendationSectionSummaryNoteItemsActionMenuLink"), evaluationRecommendationSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentEvaluationRecommendationSectionSummaryNoteItemIndex; i++){
		evaluationRecommendationSectionSummaryNoteItemRowOnClick(i);
	}
}