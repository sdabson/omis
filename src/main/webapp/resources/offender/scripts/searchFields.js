/**
 * Behavior for offender search fields.
 * 
 * @author Stephen Abson
 */

/**
 * Applies focus behavior for offender search fields behavior.
 * 
 * <p>When selected, the field radio button focuses the input. When clicked,
 * the field label focuses the input and select the radio for the input.
 * When focused, the field input checks the radio button.
 */
function applyOffenderSearchFieldsFocus() {
	
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
	
	// Apply behavior
	applyFieldFocus("searchTypeName", "lastNameLabel", "offenderSearchFields.lastName");
	applyInputFocus("offenderSearchFields.lastName", "searchTypeName");
	applyInputFocus("offenderSearchFields.firstName", "searchTypeName");
	applyFieldFocus("searchTypeOffenderNumber", "offenderNumberLabel", "offenderSearchFields.offenderNumber");
	applyInputFocus("offenderSearchFields.offenderNumber", "searchTypeOffenderNumber");
	applyFieldFocus("searchTypeSocialSecurityNumber", "socialSecurityNumberLabel", "offenderSearchFields.socialSecurityNumber");
	applyInputFocus("offenderSearchFields.socialSecurityNumber", "searchTypeSocialSecurityNumber");
	applyFieldFocus("searchTypeBirthDate", "birthDateLabel", "offenderSearchFields.birthDate");
	applyInputFocus("offenderSearchFields.birthDate", "searchTypeBirthDate");
}