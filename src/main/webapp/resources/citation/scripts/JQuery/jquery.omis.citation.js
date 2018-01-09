/**
 * Misdemeanor citation edit screen.
 * 
 * Author: Trevor Isles
 */

/** Populates cities drop down. */
function populateCities() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/city/findByState.html?state=" + $("#state").val()
			+ "&amp;city=" + $("#city").val(),
			{
				async: true,
				success: function(data) {
					$("#city").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}