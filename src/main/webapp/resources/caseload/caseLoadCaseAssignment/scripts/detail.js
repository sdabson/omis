/*
 * Author: Ryan Johns
 * Version: 0.1.0 (Jan 29, 2014)
 * Since: OMIS 3.0 */
jQuery(document).ready(function($) {
	applySearch(document.getElementById("caseLoadSubjectTitle"),
			document.getElementById("caseLoadAssigned"),
			ui.search.Autocomplete.CASELOAD_SEARCH);
	
	$(".editCaseLoadCaseAssignmentForm").prepend('<input type="text" style="position:absolute; left:-1000;width: 0; height: 0;border:none;"/>');

	$(".editCaseLoadCaseAssignmentForm .date").each(function() {
		var thisPicker = $(this);
		thisPicker.datepicker("destroy");
		if (!thisPicker.hasClass('hasDatePicker')) {
			thisPicker.on("click", function() {
				thisPicker.off("click");
				thisPicker.datepicker();
				thisPicker.focus();
			});
		}
	});
	$(".searchButton").focus();
	$("#ui-datepicker-div").css({"display":"none"});
	
});