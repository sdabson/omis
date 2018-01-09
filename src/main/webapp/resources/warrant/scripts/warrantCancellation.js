window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("date"));
	applyDatePicker(document.getElementById("clearedByDate"));
	
	applyUserSearch(document.getElementById("clearedByInput"),
			document.getElementById("clearedBy"),
			document.getElementById("clearedByDisplay"),
			null,
			document.getElementById("clearClearedBy"));
}