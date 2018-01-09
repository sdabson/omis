/**
 * Functionality for creating and editing persons.
 * 
 * <p>Dependencies
 * 
 * <ul>
 *   <li>/resources/common/scripts/jquery.min.js
 * </ul>
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2013)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	$("#birthDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#deathDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#birthCountry").change(function() {
		populateStates();
		populateCitiesByCountry();
	});
	$("#birthState").change(function() {
		if ($("#birthState").val() != null && $("#birthState").val() != '') {
			populateCitiesByState();
		} else {
			populateCitiesByCountry();
		}
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
	$("#deceasedYes").change(function() {
		deceasedOnchange();
	});
	$("#deceasedNo").change(function() {
		deceasedOnchange();
	});
	$("#deceasedUnknown").change(function() {
		deceasedOnchange();
	});
	applyActionMenu(document.getElementById("actionMenuLink"));
});

/**
 * Enable or disable date of death when deceased field is changed.
 */
function deceasedOnchange() {
	if ($("#deceasedYes").prop("checked")) {
		$("#deathDate").prop("disabled", false);
	} else {
		$("#deathDate").prop("disabled", true);
		$("#deathDate").val("");
	}
}

/** Populates States drop down. */
function populateStates() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/offender/personalDetails/findStatesByCountry.html?country=" + $("#birthCountry").val(),
			{
				async: false,
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
			+ "/offender/personalDetails/findCitiesByCountry.html?country=" + $("#birthCountry").val(),
			{
				async: false,
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
			+ "/offender/personalDetails/findCitiesByState.html?state=" + $("#birthState").val(),
			{
				async: false,
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