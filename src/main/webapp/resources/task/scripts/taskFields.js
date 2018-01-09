function applyTaskFieldsFunctions(taskFieldsPropertyName) {
	applyActionMenu(document.getElementById("taskAssignmentItemsActionMenuLink"), taskAssignmentItemsCreateOnClick);
	applyDatePicker(document.getElementById(taskFieldsPropertyName + ".originationDate"));
	applyDatePicker(document.getElementById(taskFieldsPropertyName + ".completionDate"));
	assignTimePicker(taskFieldsPropertyName + ".originationTime");
	for(var i = 0; i < currentTaskAssignmentItemIndex; i++){
		taskAssignmentItemRowOnClick(taskFieldsPropertyName, i);
	}
}

