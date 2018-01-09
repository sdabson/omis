/**
 * Behvaior for screen to edit identification numbers.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("expireDate"));
	applyDatePicker(document.getElementById("issueDate"));
	applyActionMenu(document.getElementById("actionMenuLink"));
}