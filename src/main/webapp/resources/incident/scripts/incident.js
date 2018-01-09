/**
 * Behavior to display incident search form.
 *
 * Author: Yidong Li.
 * 
 * Date: July31, 2015
 */

window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyActionMenu(document.getElementById("addInvolvedPersonLink"), assignAddInvolvedPersonItemActionMenuOnClick);
	applyLocationSearch(
			document.getElementById("existingLocation"),
			document.getElementById("location"),
			document.getElementById("locationDisplay"),
			document.getElementById("clearLocation")
	);
	for (var index = 0; index < currentInvolvedPersonItemIndex; index++) {
		applyInvolvedPersonItemBehavior(index, document.getElementById("items[" + index + "].category").value);
	}
};