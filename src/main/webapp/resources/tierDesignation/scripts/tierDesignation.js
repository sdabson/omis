/**
 * Tier designation form. 
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyFormUpdateChecker(document.getElementById("tierDesignationForm"));
};