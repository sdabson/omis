window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyOffenderSearch(document.getElementById("otherOffenderInput"), 
		document.getElementById("otherOffender"),
		document.getElementById("otherOffenderDisplay"), 
		document.getElementById("clearLink"));
};