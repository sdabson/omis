/*
 * Disciplinary Code form java script.
 * 
 * Author: Annie Jacques
 * Version: 0.1.1 (Sep 5, 2017)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyValueLabelAutoComplete(document.getElementById("codeQuery"),  
			document.getElementById("searchCode"), config.ServerConfig.getContextPath() + "/disciplinaryCode/findCode.json");
	applyTextCounter(document.getElementById("extendedDescription"), document.getElementById("extendedDescriptionCharacterCounter"));
	
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
	
	applyFieldFocus("usingExistingCode", "codeQuery");
	applyInputFocus("codeQuery", "usingExistingCode");
	applyFieldFocus("usingNewCode", "code");
	applyInputFocus("code", "usingNewCode");
	applyInputFocus("description", "usingNewCode");
	applyInputFocus("extendedDescription", "usingNewCode");
	
}