/**
 * Behavior to search for victims.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	
	// Focuses input when label or radio box is clicked
	function applyFieldFocus(radioEltName, labelEltName, inputEltName) {
		var radioElt = document.getElementById(radioEltName);
		var labelElt = document.getElementById(labelEltName);
		var inputElt = document.getElementById(inputEltName);
		radioElt.onfocus = function() {
			inputElt.focus();
		};
		labelElt.onclick = function() {
			inputElt.focus();
		};
		if (radioElt.checked) {
			inputElt.focus();
		}
	}
	
	// Checks radio button when input is focused
	function applyInputFocus(inputEltName, radioEltName) {
		var inputElt = document.getElementById(inputEltName);
		var radioElt = document.getElementById(radioEltName);
		inputElt.onfocus = function() {
			radioElt.checked = true;
		};
	}
	
	applyFieldFocus("searchTypeNameRadio", "searchTypeNameLabel", "searchTypeName");
	applyInputFocus("searchTypeName", "searchTypeNameRadio");
	applyInputFocus("firstName", "searchTypeNameRadio");
};