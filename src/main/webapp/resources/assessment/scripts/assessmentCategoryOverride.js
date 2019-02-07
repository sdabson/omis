window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("overrideDate"));
	applyStaffAssignmentSearch(document.getElementById("authorizedByInput"), 
			document.getElementById("authorizedBy"),
			document.getElementById("authorizedByDisplay"),
			document.getElementById("authorizedByCurrent"),
			document.getElementById("authorizedByClear"));
	applyActionMenu(document.getElementById("assessmentCategoryOverrideNoteItemsActionMenuLink"), assessmentCategoryOverrideNoteItemsCreateOnClick);
	for (var index = 0; index < currentAssessmentCategoryOverrideNoteItemIndex; index++) {
		assessmentCategoryOverrideNoteItemRowOnClick(index);
	}
}