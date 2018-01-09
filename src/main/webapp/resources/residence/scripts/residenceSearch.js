/**
 * Residence search detail screen behavior.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {
	applyActionMenu(document.getElementById("searchCriteriaActionMenu"));
	applyActionMenu(document.getElementById("searchResultsActionMenu"));
	applyDatePicker(document.getElementById("effectiveDate"));
	assignCityOnClick();
};