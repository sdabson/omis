window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("presentenceInvestigationRequestNoteItemsActionMenuLink"), presentenceInvestigationRequestNoteItemsCreateOnClick);
	assignNewTabLinks();
	
	var psiTaskCheckBoxes = document.getElementsByClassName("psiTaskCheckBox");
	for(var i = 0, psiTaskCheckBox; psiTaskCheckBox = psiTaskCheckBoxes[i]; i++){
		psiTaskCheckBoxOnClick(psiTaskCheckBox);
	}
	
	for(var i = 0; i < currentPresentenceInvestigationRequestNoteItemIndex; i++){
		presentenceInvestigationRequestNoteItemRowOnClick(i);
	}
};