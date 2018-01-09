window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyDatePicker(document.getElementById("acceptedDate"));
	
	agreementAssociableDocumentItemsCreateOnClick();
	for(var i = 0; i < currentAgreementAssociableDocumentItemIndex; i++){
		agreementAssociableDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
}
