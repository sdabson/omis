$(document).ready(function() {
	$("#actionDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#nextReviewDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
});