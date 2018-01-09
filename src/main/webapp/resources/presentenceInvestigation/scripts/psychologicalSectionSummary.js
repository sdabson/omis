window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("psychologicalSectionSummaryNoteItemsActionMenuLink"), psychologicalSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentPsychologicalSectionSummaryNoteItemIndex; i++){
		psychologicalSectionSummaryNoteItemRowOnClick(i);
	}
	psychologicalSectionSummaryDocumentItemsCreateOnClick();
	for(var i = 0; i < currentPsychologicalSectionSummaryDocumentItemIndex; i++){
		psychologicalSectionSummaryDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}