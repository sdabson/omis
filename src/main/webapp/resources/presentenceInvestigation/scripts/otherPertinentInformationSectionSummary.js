window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("otherPertinentInformationSectionSummaryNoteItemsActionMenuLink"), otherPertinentInformationSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentOtherPertinentInformationSectionSummaryNoteItemIndex; i++){
		otherPertinentInformationSectionSummaryNoteItemRowOnClick(i);
	}
}