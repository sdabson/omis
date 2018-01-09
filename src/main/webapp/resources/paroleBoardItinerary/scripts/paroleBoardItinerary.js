/**
 * Parole board itinerary detail screen behavior.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 Dec 5, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyFormUpdateChecker(document.getElementById("paroleBoardItineraryForm"));
	applyBoardItineraryBehavior();
	applyActionMenu(document.getElementById("boardMeetingSitesActionMenuLink"), boardMeetingSiteActionMenuOnClick);
	applyActionMenu(document.getElementById("boardItineraryNotesActionMenuLink"), boardItineraryNoteActionMenuOnClick);
};