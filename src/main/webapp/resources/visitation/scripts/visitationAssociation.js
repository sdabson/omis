/**
 * Visitation association java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (May 08, 2015)
 * Since: OMIS 3.0
 */
$(document).ready(function() {
	assignOnClick();
	applyOffenderSearch(document.getElementById("associatedOffenderInput"),
			document.getElementById("associatedOffender"),
			document.getElementById("associatedOffenderDisplay"),
			document.getElementById("associatedOffenderClear"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyPersonFieldsOnClick("personFields", "stateOptions.html", "cityOptions.html");
	applyTextCounter(document.getElementById("notes"), document.getElementById("notesCharacterCounter"));
	applyTextCounter(document.getElementById("guardianship"), document.getElementById("guardianshipCharacterCounter"));
});