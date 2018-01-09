/**
 * Functionality for creating and editing offenders.
 * <p>
 * Dependencies
 * <ul>
 *   <li>/resources/common/scripts/jquery.min.js
 * </ul>
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	$("#birthDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#photoDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#birthCountry").change(function() {
		populateStates();
		populateCitiesByCountry();
	});
	$("#birthState").change(function() {
		populateCitiesByState();
	});	
	$("#createNewBirthPlace").click(function() {
		if ($("#createNewBirthPlace").is(':checked')) {
			$("#birthPlaceName").show();
			$("#birthPlace").hide();
		} else {
			$("#birthPlace").show();
			$("#birthPlaceName").hide();
		}		
	});
	$("#countryOfCitizenship").change(function() {
		if ($("#countryOfCitizenship").val() != "") {
			$.ajax(config.ServerConfig.getContextPath()
					+ "/offender/isHomeCountry.json?country=" + $("#countryOfCitizenship").val(),
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
	$("#photoPreviewFieldGroup").hide();
	if (window.FileReader) {
		$("#photoData").change(function(event) {
			var reader = new FileReader();
			reader.onload = function(innerEvent) {
				$("#photoPreviewFieldGroup").show();
				$("#photoPreview").attr("src", innerEvent.target.result);
			};
			reader.readAsDataURL(event.target.files[0]);
		});
	}
});

/** Populates States drop down by country by country. */
function populateStates() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/offender/findStatesByCountry.html?country=" + $("#birthCountry").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#birthState").html(data);
					populateCitiesByState();
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

/** Populates cities drop down by country. */
function populateCitiesByCountry() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/offender/findCitiesByCountry.html?country=" + $("#birthCountry").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#birthPlace").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

/** Populates cities drop down by State. */
function populateCitiesByState() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/offender/findCitiesByState.html?state=" + $("#birthState").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#birthPlace").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}