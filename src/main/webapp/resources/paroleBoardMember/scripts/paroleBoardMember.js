/**
 * Parole board member detail screen behavior.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 Nov 9, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyStaffAssignmentSearch(document.getElementById("staffAssignmentInput"), 
			document.getElementById("staffAssignment"),
			document.getElementById("staffAssignmentDisplay"),
			document.getElementById("staffAssignmentCurrent"),
			document.getElementById("staffAssignmentClear"));
	applyFormUpdateChecker(document.getElementById("paroleBoardMemberForm"));
};