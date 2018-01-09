/**
 * Behavior for searching users.
 * 
 * @author: Stephen Abson
 */
window.onload = function() {
	
	// Focuses input when label or radio box is clicked
	function applyFieldFocus(radioEltId, labelEltId, inputEltId) {
		var radioElt = document.getElementById(radioEltId);
		var labelElt = document.getElementById(labelEltId);
		var inputElt = document.getElementById(inputEltId);
		radioElt.onfocus = function() {
			inputElt.focus();
		};
		labelElt.onclick = function() {
			inputElt.focus();
		}
		if (radioElt.checked) {
			inputElt.focus();
		}
	}
	
	// Checks radio button when input is focused
	function applyInputFocus(inputEltId, radioEltId) {
		var inputElt = document.getElementById(inputEltId);
		var radioElt = document.getElementById(radioEltId);
		inputElt.onfocus = function() {
			radioElt.checked = true;
		}
	}
	
	// Apply focus behavior
	applyFieldFocus("searchTypeName", "lastNameLabel", "lastName");
	applyInputFocus("lastName", "searchTypeName");
	applyInputFocus("firstName", "searchTypeName");
	applyFieldFocus("searchTypeUsername", "usernameLabel", "username");
	applyInputFocus("username", "searchTypeUsername");
};