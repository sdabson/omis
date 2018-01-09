/* Author: Ryan Johns
 * Version: 0.1.0 (Oct 7, 2015)
 * Since: OMIS 3.0 */
eventRunner.addEvent(function($) {
	applyPersonSearch( 
			document.getElementById("personWorkerInput"),
			document.getElementById("person"),
			document.getElementById("personWorkerLabel"),
			document.getElementById("clearPerson"));
	
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	
	var actionMenuItems = document.getElementsByClassName("actionMenuItem");
	
	for(var x=0; x < actionMenuItems.length; x++) {
		applyActionMenu(actionMenuItems[x]);
	}
});



