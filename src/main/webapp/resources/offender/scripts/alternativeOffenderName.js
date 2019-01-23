/**
 * Behavior for editing alternative person names.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 9, 2013)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	$("#startDate").attr("autocomplete", "off");
	$("#startDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#endDate").attr("autocomplete", "off");
	$("#endDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
});