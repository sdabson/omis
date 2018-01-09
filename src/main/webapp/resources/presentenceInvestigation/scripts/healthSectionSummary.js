window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("healthSectionSummaryNoteItemsActionMenuLink"), healthSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentHealthSectionSummaryNoteItemIndex; i++){
		healthSectionSummaryNoteItemRowOnClick(i);
	}
	healthSectionSummaryDocumentItemsCreateOnClick();
	for(var i = 0; i < currentHealthSectionSummaryDocumentAssociationItemIndex; i++){
		healthSectionSummaryDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}