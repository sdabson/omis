window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("financialSectionSummaryNoteItemsActionMenuLink"), financialSectionSummaryNoteItemsCreateOnClick);
	
	financialSectionSummaryDocumentAssociationItemsCreateOnClick();
	for(var i = 0; i < currentFinancialSectionSummaryNoteItemIndex; i++) {
		financialSectionSummaryNoteItemRowOnClick(i);
	}
	for(var i = 0; i < currentFinancialSectionSummaryDocumentAssociationItemIndex; i++) {
		financialSectionSummaryDocumentAssociationItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++) {
			documentTagItemRowOnClick(i,j);
		}
	}
}