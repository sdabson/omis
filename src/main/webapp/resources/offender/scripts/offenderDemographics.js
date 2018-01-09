/**
 * Behavior for offender demographics screen.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	$("#countryOfCitizenship").change(function() {
		if ($("#countryOfCitizenship").val() != "") {
			$.ajax(config.ServerConfig.getContextPath()
					+ "/offender/demographics/isHomeCountry.json?country=" + $("#countryOfCitizenship").val(),
					{
						async: false,
						cache: false,
						success: function(data) {
							$("#alienResidenceLegality").attr("disabled", eval(data));
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Error: " + textStatus + "; " + errorThrown);
						},
						type: "GET"
					}
			);
		} else {
			$("#alienResidenceLegality").attr("disabled", false);
		}
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
};