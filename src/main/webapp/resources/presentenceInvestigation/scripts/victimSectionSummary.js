window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("victimsActionMenuLink"));
	applyActionMenu(document.getElementById("victimSectionSummaryNoteItemsActionMenuLink"), 
		victimSectionSummaryNoteItemsCreateOnClick);
	var boxes = document.getElementsByClassName("selectVictimCheckbox");
	for(var i = 0; i < boxes.length; i++){
		victimCheckboxOnClick(i);
		
	}
	for(var i = 0; i < currentVictimSectionSummaryNoteItemIndex; i++){
		victimSectionSummaryNoteItemRowOnClick(i);
	}
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++) {
		applyActionMenu(rows[i]);
	}
	
//	for(var l = 0; l < currentVictimSectionSummaryDocketAssociationItemIndex; l++){
//		victimSectionSummaryDocketAssociationItemRowOnClick(l);
//	}
	var victimRows = document.getElementsByClassName('rowActionMenuItem');
	for(var l = 0; l < rows.length; l++){
		applyActionMenu(rows[l]);
	}
}