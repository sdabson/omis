/**
 * Behavior to edit victim notes.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("date"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("victimNoteForm"));
};