$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	$("#date").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#time").ptTimeSelect();
	applyFormUpdateChecker(document.getElementById("dnaSampleForm"));
});