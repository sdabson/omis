window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("chemicalUseSectionSummaryNoteItemsActionMenuLink"), chemicalUseSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentChemicalUseSectionSummaryNoteItemIndex; i++){
		chemicalUseSectionSummaryNoteItemRowOnClick(i);
	}
	chemicalUseSectionSummaryDocumentAssociationItemsCreateOnClick();
	for(var i = 0; i < currentChemicalUseSectionSummaryDocumentAssociationItemIndex; i++){
		chemicalUseSectionSummaryDocumentAssociationItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}