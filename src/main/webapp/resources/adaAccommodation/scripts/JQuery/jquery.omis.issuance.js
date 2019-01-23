function assignIssuanceOnClick() {
	$("#day").attr("autocomplete", "off");
	$("#day").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#time").attr("autocomplete", "off");
	$("#time").ptTimeSelect();
};