/**
 * Parole board members jQuery implementation.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 (Nov 15, 2017)
 * Since: OMIS 3.0
 */

/**
 * Applies date picker to a field.
 * 
 * @param elt field
 */
function applyDatePicker(elt) {
	$("#" + elt).datepicker({
		changeMonth: true,
		changeYear: true
	});
}