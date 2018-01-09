/**
 * Needs java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (July 15, 2015)
 * Since: OMIS 3.0
 */

window.onload = function() {
	applyStaffSearch(document.getElementById("staffMemberInput"),
			document.getElementById("staffMember"),
			document.getElementById("staffMemberDisplay"),
			document.getElementById("staffMemberCurrent"),
			document.getElementById("staffMemberClear"));
	applyDatePicker("identifiedDate");
	applyObjectiveSourceOnChange();
	applyFormUpdateChecker(document.getElementById("casePlanObjectiveForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
}