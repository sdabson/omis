/**
 * Author: Yidong Li.
 * 
 * Date: Aug.29, 2016
 */

window.onload = function() {
	applyDatePicker(document.getElementById("assignmentDate"));
	applyDatePicker(document.getElementById("terminationDate"));
	applyActionMenu(document.getElementById("workAssignmentEditActionMenuLink"));
	applyActionMenu(document.getElementById("workAssignmentNoteActionMenuLink"),addWorkAssignmentNoteItem);
	for (var x = 0; x <= workAssignmentNoteIndex; x++) {
		applyNoteItemBehavior(x);
		applyDatePicker(document.getElementById("noteDate"+x));
	}
}