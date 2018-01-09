window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("educationsActionMenuLink"), 
			refreshEducationsListOnClick);
	applyActionMenu(document.getElementById("educationSectionSummaryNoteItemsActionMenuLink"), educationSectionSummaryNoteItemsCreateOnClick);
	for(var i = 0; i < currentEducationSectionSummaryNoteItemIndex; i++){
		educationSectionSummaryNoteItemRowOnClick(i);
	}
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i]);
		
	}
}