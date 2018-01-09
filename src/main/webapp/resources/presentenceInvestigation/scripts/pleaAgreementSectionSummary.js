window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("pleaAgreementSectionSummaryNoteItemsActionMenuLink"), pleaAgreementSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentPleaAgreementSectionSummaryNoteItemIndex; i++){
		pleaAgreementSectionSummaryNoteItemRowOnClick(i);
	}
	pleaAgreementSectionSummaryAssociableDocumentItemsCreateOnClick();
	for(var i = 0; i < currentPleaAgreementSectionSummaryAssociableDocumentItemIndex; i++){
		pleaAgreementSectionSummaryAssociableDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}