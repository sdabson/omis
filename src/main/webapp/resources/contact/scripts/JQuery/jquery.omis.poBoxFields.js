/*
 * Po Box fields functionality jQuery implementation. 
 * 
 * Author: Yidong Li 
 * Version: 0.1.0 (Oct 22, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the country field.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 */
function applyPoBoxFieldsCountryOnClick(poBoxFieldsPropertyName, stateOptionsUrl, cityOptionsUrl) {
	var country = $("#" + poBoxFieldsPropertyName + "Country");
	country.change(function() {
		$.ajax({
			type: "GET",	
			async: false,
			url: stateOptionsUrl,
			data: {country: country.val(),
				poBoxFieldsPropertyName: poBoxFieldsPropertyName},
			success: function(response) {
				 $("#" + poBoxFieldsPropertyName + "State").html(response);
				 var stateCount = $("#" + poBoxFieldsPropertyName + "State > option").length;
				 /*if (stateCount < 2) {
					 poBoxStateChangeFunction(poBoxFieldsPropertyName, cityOptionsUrl);
				 } else {
					 $("#" + poBoxFieldsPropertyName + "City").html("<option value=\"\">...</option>");
				 }*/
				 poBoxStateChangeFunction(poBoxFieldsPropertyName, cityOptionsUrl);
				 $("#" + poBoxFieldsPropertyName + "ZipCode").html("<option value=\"\">...</option>");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#" + poBoxFieldsPropertyName + "State").html(jqXHR.responseText );
			}
		});
	});
}

/**
 * Applies on click functionality for the state field.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 * @param url uniform resource locator
 */
function applyPoBoxFieldsStateOnClick(poBoxFieldsPropertyName, url) {
	var state = $("#" + poBoxFieldsPropertyName + "State");
	state.change(function() {
		poBoxStateChangeFunction(poBoxFieldsPropertyName, url);
	});
}

/**
 * Function to handle change functionality for state control.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 * @param url uniform resource locator
 */
function poBoxStateChangeFunction(poBoxFieldsPropertyName, url) {
	var state = $("#" + poBoxFieldsPropertyName + "State");
	var country = $("#" + poBoxFieldsPropertyName + "Country");
	$.ajax({
		type: "GET",	
		async: false,
		url: url,
		data: {country: country.val(),
			state: state.val(),
			poBoxFieldsPropertyName: poBoxFieldsPropertyName},
		success: function(response) {
			 $("#" + poBoxFieldsPropertyName + "City").html(response);
			 $("#" + poBoxFieldsPropertyName + "ZipCode").html("<option value=\"\">...</option>");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#" + poBoxFieldsPropertyName + "City").html(jqXHR.responseText );
		}
	});
}

/**
 * Applies on click functionality for the city field.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 * @param url uniform resource locator
 */
function applyPoBoxFieldsCityOnClick(poBoxFieldsPropertyName, url) {
	var city = $("#" + poBoxFieldsPropertyName + "City");
	city.change(function() {
		$.ajax({
			type: "GET",	
			async: false,
			url: url,
			data: {city: city.val(),
				poBoxFieldsPropertyName: poBoxFieldsPropertyName},
			success: function(response) {
				$("#" + poBoxFieldsPropertyName + "ZipCode").html(response);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#" + poBoxFieldsPropertyName + "ZipCode").html(jqXHR.responseText );
			}
		});
	});
}

/**
 * Applies on click functionality for the new city radio button.
 * 
 * @param poBoxFieldsPropertyName po Box fields property name 
 */
function applyPoBoxFieldsNewCityOnClick(poBoxFieldsPropertyName) {
	var falseNewCity = $("#" + poBoxFieldsPropertyName + "FalseNewCity");
	var trueNewCity = $("#" + poBoxFieldsPropertyName + "TrueNewCity");
	trueNewCity.click(function() {
			if (!$("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").addClass("hidden");
			}
			if ($("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").removeClass("hidden");
			}
			newPoBoxZipCodeDisplay(poBoxFieldsPropertyName);
			$("#" + poBoxFieldsPropertyName + "TrueNewZipCode").attr('checked', 'checked');
			$("#" + poBoxFieldsPropertyName + "FalseNewZipCode").prop("disabled", true);
	});
	falseNewCity.click(function() {
			if ($("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").removeClass("hidden");
			}
			if (!$("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").addClass("hidden");
			}
			$("#" + poBoxFieldsPropertyName + "FalseNewZipCode").prop("disabled", false);
	});
}

/**
 * Applies on click functionality for the new zip code radio button.
 * 
 * @param poBoxFieldsPropertyName po box fields property name 
 */
function applyPoBoxFieldsNewZipCodeOnClick(poBoxFieldsPropertyName) {
	var falseNewZipCode = $("#" + poBoxFieldsPropertyName + "FalseNewZipCode");
	var trueNewZipCode = $("#" + poBoxFieldsPropertyName + "TrueNewZipCode");
	trueNewZipCode.click(function() {
		newPoBoxZipCodeDisplay(poBoxFieldsPropertyName);
		/*$("#" + poBoxFieldsPropertyName + "newZipCode").val(true);*/
	});
	falseNewZipCode.click(function() {
		if (!$("#" + poBoxFieldsPropertyName + "NewZipCodeFieldGroup").hasClass("hidden")) {
			$("#" + poBoxFieldsPropertyName + "NewZipCodeFieldGroup").addClass("hidden");
		}
		if ($("#" + poBoxFieldsPropertyName + "ExistingZipCodeFieldGroup").hasClass("hidden")) {
			$("#" + poBoxFieldsPropertyName + "ExistingZipCodeFieldGroup").removeClass("hidden");
		}
		/*$("#" + poBoxFieldsPropertyName + "newZipCode").val(false);*/
	});
}

/**
 * Display new zip code fields with the specified po box fields property name.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 */
function newPoBoxZipCodeDisplay(poBoxFieldsPropertyName) {
	if ($("#" + poBoxFieldsPropertyName + "NewZipCodeFieldGroup").hasClass("hidden")) {
		$("#" + poBoxFieldsPropertyName + "NewZipCodeFieldGroup").removeClass("hidden");
	}
	if (!$("#" + poBoxFieldsPropertyName + "ExistingZipCodeFieldGroup").hasClass("hidden")) {
		$("#" + poBoxFieldsPropertyName + "ExistingZipCodeFieldGroup").addClass("hidden");
	}
}

/**
 * Display new city fields with the specified po box fields property name.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 */
function newCityDisplay(poBoxFieldsPropertyName) {
	if ($("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
		$("#" + poBoxFieldsPropertyName + "NewCityFieldGroup").removeClass("hidden");
	}
	if (!$("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
		$("#" + poBoxFieldsPropertyName + "ExistingCityFieldGroup").addClass("hidden");
	}
}

/**
 * Enables po box fields
 * 
 */
function enablePoBoxFieldControls() {
	$("span.fieldGroup").find("input").prop("disabled", false);
	$("span.fieldGroup").find("select").prop("disabled", false);
}

/**
 * Disables po box fields
 * 
 */
function disablePoBoxFieldControls( ) {
	$("span.fieldGroup").find("input").prop("disabled", true);
	$("span.fieldGroup").find("select").prop("disabled", true);
}
















