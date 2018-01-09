/**
 * Behavior for screen to change correctional statuses.
 * 
 * Author: Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("effectiveDate"));
	applyTimePicker(document.getElementById("effectiveTime"));
	applyDatePicker(document.getElementById("endDate"));
	applyTimePicker(document.getElementById("endTime"));
	applyActionMenu(document.getElementById("actionMenuLink"));
};