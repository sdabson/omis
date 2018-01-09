window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("jailAdjustmentSectionSummaryNoteItemsActionMenuLink"), jailAdjustmentSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentJailAdjustmentSectionSummaryNoteItemIndex; i++){
		jailAdjustmentSectionSummaryNoteItemRowOnClick(i);
	}
}