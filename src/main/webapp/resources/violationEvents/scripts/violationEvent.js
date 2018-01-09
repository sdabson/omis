window.onload = function() {
	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("eventDate"));
	if(document.getElementById("disciplinaryCodeViolationItemsActionMenuLink") != null){
		applyActionMenu(document.getElementById("disciplinaryCodeViolationItemsActionMenuLink"), disciplinaryCodeViolationItemsCreateOnClick);
	}
	if(document.getElementById("conditionViolationItemsActionMenuLink") != null){
		applyActionMenu(document.getElementById("conditionViolationItemsActionMenuLink"), conditionViolationItemsCreateOnClick);
	}
	applyActionMenu(document.getElementById("violationEventNoteItemsActionMenuLink"), violationEventNoteItemsCreateOnClick);
	violationEventDocumentItemsCreateOnClick();
	applyJurisdictionFilterOnClick();
	applyJurisdictionAndEventDateOnClick();
	jurisdictionFilterChangeFunction(document.querySelector('input[name=jurisdictionFilter]:checked').value);
	applyTextCounter(document.getElementById("eventDetails"), document.getElementById("eventDetailsCharacterCounter"));
	for(var i = 0; i < currentDisciplinaryCodeViolationItemIndex; i++){
		disciplinaryCodeViolationItemRowOnClick(i);
	}
	for(var i = 0; i < currentConditionViolationItemIndex; i++){
		conditionViolationItemRowOnClick(i);
	}
	for(var i = 0; i < currentViolationEventNoteItemIndex; i++){
		violationEventNoteItemRowOnClick(i);
	}
	for(var i = 0; i < currentViolationEventDocumentItemIndex; i++){
		violationEventDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}

