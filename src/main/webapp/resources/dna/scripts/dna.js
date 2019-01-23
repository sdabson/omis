$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	$("#date").attr("autocomplete", "off");
	$("#date").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#time").attr("autocomplete", "off");
	$("#time").ptTimeSelect();
	applyFormUpdateChecker(document.getElementById("dnaSampleForm"));
});