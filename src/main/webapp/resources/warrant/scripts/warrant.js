window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("warrantCauseViolationItemsActionMenuLink"), warrantCauseViolationItemsCreateOnClick);
	applyActionMenu(document.getElementById("warrantNoteItemsActionMenuLink"), warrantNoteItemsCreateOnClick);
	applyDatePicker(document.getElementById("date"));
	applyDatePicker(document.getElementById("arrestDate"));
	applyDatePicker(document.getElementById("contactBy"));
	
	for(var i = 0; i < currentWarrantNoteItemIndex; i++){
		warrantNoteItemRowOnClick(i);
	}
	for(var i = 0; i < currentWarrantCauseViolationItemIndex; i++){
		warrantCauseViolationItemRowOnClick(i);
		displayConditionOptions(i);
	}
	var arrestedFields = document.getElementById('arrestedFields');
	if(document.getElementById('arrested').checked){
		arrestedFields.style.display = "block";
	}
	document.getElementById('arrested').onclick = function(){
		if(this.checked){
			arrestedFields.style.display = "block";
		}
		else{
			arrestedFields.style.display = "none";
		}
	}
	
	applyUserSearch(document.getElementById("issuedByInput"),
			document.getElementById("issuedBy"),
			document.getElementById("issuedByDisplay"),
			null,
			document.getElementById("clearIssuedBy"));
	
}