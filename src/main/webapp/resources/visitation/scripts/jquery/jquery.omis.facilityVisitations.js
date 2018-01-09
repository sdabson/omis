/**
 * Facility visitations jQuery implementation.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (March 29, 2017)
 * Since: OMIS 3.0
 */

/**
 * Assign jQuery date picker to the specified input control.
 * 
 * @param inputControl input control to assign a jQuery Date Picker
 */
function assignDatePicker(inputControl) {
	if ( $(inputControl).prop('type') != 'date' ) {
		$(inputControl).datepicker({
			changeMonth: true,
			changeYear: true
		});
	}
};