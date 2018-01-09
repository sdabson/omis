/**
 * Health request screen behavior.
 * 
 * @author: Stephen Abson
 */
window.onload = function() {
	var offenderRequired = document.getElementById("offenderRequired");
	if (offenderRequired != null && offenderRequired.getAttribute("value") == "true") {
		applySearch(document.getElementById("offenderName"),document.getElementById("offender"), ui.search.Autocomplete.OFFENDER_SEARCH);
	}
}