/**
 * JQuery implementation for visitation association.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (May 08, 2015)
 * Since: OMIS 3.0
 */

/*
 * Assigns the on click functionality for visitation association edit.
 */
function assignOnClick() {
	assignDatePicker("startDate");
	assignDatePicker("endDate");
	assignDatePicker("decisionDate");
};

/*
 * Assigns a jQuery date picker for the element with the specified id.
 * 
 * @param elementId element ID
 */
function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};