$(document).ready(function() {
	$("#actionDate").attr("autocomplete", "off");
	$("#actionDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#nextReviewDate").attr("autocomplete", "off");
	$("#nextReviewDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
});