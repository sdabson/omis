window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rows = document.getElementsByClassName("rowActionMenuItem");
	for(var i = 0, row; row = rows[i]; i++) {
		applyActionMenu(row, applyParoleBoardConditionsActionMenuOnClick);
	}
	
	// Focuses input when label or radio box is clicked
	function applyFieldFocus(radioEltName, inputEltName) {
		var radioElt = document.getElementById(radioEltName);
		var inputElt = document.getElementById(inputEltName);
		radioElt.onfocus = function() {
			inputElt.focus();
		};
	}
	
	// Checks radio button when input is focused
	function applyInputFocus(inputEltName, radioEltName) {
		var inputElt = document.getElementById(inputEltName);
		var radioElt = document.getElementById(radioEltName);
		inputElt.onfocus = function() {
			radioElt.checked = true;
		};
	}
	
	applyFieldFocus("singleDate", "effectiveDate");
	applyInputFocus("effectiveDate", "singleDate");
	applyFieldFocus("dateRangeSearch", "startDate");
	applyInputFocus("startDate", "dateRangeSearch");
	applyInputFocus("endDate", "dateRangeSearch");
	assignDatePicker("effectiveDate");
	assignDatePicker("startDate");
	assignDatePicker("endDate");
	
}