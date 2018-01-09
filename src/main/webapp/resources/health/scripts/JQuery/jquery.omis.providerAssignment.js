/*
 * Applies on click functionality for the provider type radio buttons.
 */
function applyProviderTypeOnClick() {
	$("[name=providerType]").each(function() {
		if ($(this).val() == 'EXTERNAL') {
			$(this).click(function() {
				$.ajax({
					type: "GET",
					async: false,
					url: config.ServerConfig.getContextPath() + "/health/provider/medicalFacilityContent.html",
					success: function(data) {
						$("#medicalFacilityArea").html(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#medicalFacilityArea").html(jqXHR.responseText);
					}
				});
			});
		} else {
			$(this).click(function() {
				$("#medicalFacilityArea").empty();
			});
		}
	});
}

/*
 * Apply date pickers to any controls that require it.
 */
function applyDatePickers() {
	$("#startDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#endDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
}