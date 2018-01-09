/**
 * Behavior for address screen.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	$("#country").change(function() {
		populateStates();
		populateCitiesByCountry();
	});
	$("#state").change(function() {
		populateCitiesByState();
		populateZipCodesByState();
	});
	$("#city").change(function() {
		populateZipCodesByCity();
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
};

/** Populates States drop down by country by country. */
function populateStates() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/address/findStatesByCountry.html?country=" + $("#country").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#state").html(data);
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
			+ "/address/findCitiesByCountry.html?country=" + $("#country").val(),
			{
				async: true,
				cache: false,
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

/** Populates cities drop down by State. */
function populateCitiesByState() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/address/findCitiesByState.html?state=" + $("#state").val(),
			{
				async: true,
				cache: false,
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

/** Populates ZIP codes by State. */
function populateZipCodesByState() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/address/findZipCodesByState.html?state=" + $("#state").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#zipCode").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

/** Populates ZIP codes by city. */
function populateZipCodesByCity() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/address/findZipCodesByCity.html?city=" + $("#city").val(),
			{
				async: true,
				cache: false,
				success: function(data) {
					$("#zipCode").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}