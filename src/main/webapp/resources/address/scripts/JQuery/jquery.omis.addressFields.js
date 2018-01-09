/*
 * Address fields functionality jQuery implementation. 
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (July 09, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the country field.
 * 
 * @param addressFieldsPropertyName address fields property name
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 */
function applyAddressFieldsCountryOnClick(addressFieldsPropertyName, stateOptionsUrl, cityOptionsUrl) {
	var country = $("#" + addressFieldsPropertyName + "Country");
	country.change(function() {
		$.ajax({
			type: "GET",	
			async: false,
			url: stateOptionsUrl,
			data: {country: country.val(),
				addressFieldsPropertyName: addressFieldsPropertyName},
			success: function(response) {
				 $("#" + addressFieldsPropertyName + "State").html(response);
				 var stateCount = $("#" + addressFieldsPropertyName + "State > option").length;
				/* if (stateCount < 2) {
					 addressFieldsStateChangeFunction(addressFieldsPropertyName, cityOptionsUrl);
				 } else {
					 $("#" + addressFieldsPropertyName + "City").html("<option value=\"\">...</option>");
				 }*/
				 addressFieldsStateChangeFunction(addressFieldsPropertyName, cityOptionsUrl);
				 $("#" + addressFieldsPropertyName + "ZipCode").html("<option value=\"\">...</option>");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#" + addressFieldsPropertyName + "State").html(jqXHR.responseText );
			}
		});
	});
}

/**
 * Applies on click functionality for the state field.
 * 
 * @param addressFieldsPropertyName address fields property name
 * @param url uniform resource locator
 */
function applyAddressFieldsStateOnClick(addressFieldsPropertyName, url) {
	var state = $("#" + addressFieldsPropertyName + "State");
	state.change(function() {
		addressFieldsStateChangeFunction(addressFieldsPropertyName, url);
	});
}

/**
 * Function to handle change functionality for state control.
 * 
 * @param addressFieldsPropertyName address fields property name
 * @param url uniform resource locator
 */
function addressFieldsStateChangeFunction(addressFieldsPropertyName, url) {
	var state = $("#" + addressFieldsPropertyName + "State");
	var country = $("#" + addressFieldsPropertyName + "Country");
	$.ajax({
		type: "GET",	
		async: false,
		url: url,
		data: {country: country.val(),
			state: state.val(),
			addressFieldsPropertyName: addressFieldsPropertyName},
		success: function(response) {
			 $("#" + addressFieldsPropertyName + "City").html(response);
			 $("#" + addressFieldsPropertyName + "ZipCode").html("<option value=\"\">...</option>");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#" + addressFieldsPropertyName + "City").html(jqXHR.responseText );
		}
	});
}

/**
 * Applies on click functionality for the city field.
 * 
 * @param addressFieldsPropertyName address fields property name
 * @param url uniform resource locator
 */
function applyAddressFieldsCityOnClick(addressFieldsPropertyName, url) {
	var city = $("#" + addressFieldsPropertyName + "City");
	city.change(function() {
		$.ajax({
			type: "GET",	
			async: false,
			url: url,
			data: {city: city.val(),
				addressFieldsPropertyName: addressFieldsPropertyName},
			success: function(response) {
				$("#" + addressFieldsPropertyName + "ZipCode").html(response);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#" + addressFieldsPropertyName + "ZipCode").html(jqXHR.responseText );
			}
		});
	});
}

/**
 * Applies on click functionality for the new city radio button.
 * 
 * @param addressFieldsPropertyName address fields property name 
 */
function applyAddressFieldsNewCityOnClick(addressFieldsPropertyName) {
	var falseNewCity = $("#" + addressFieldsPropertyName + "FalseNewCity");
	var trueNewCity = $("#" + addressFieldsPropertyName + "TrueNewCity");
	trueNewCity.click(function() {
			if (!$("#" + addressFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + addressFieldsPropertyName + "ExistingCityFieldGroup").addClass("hidden");
			}
			if ($("#" + addressFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + addressFieldsPropertyName + "NewCityFieldGroup").removeClass("hidden");
			}
			newZipCodeDisplay(addressFieldsPropertyName);
			$("#" + addressFieldsPropertyName + "TrueNewZipCode").attr('checked', 'checked');
			$("#" + addressFieldsPropertyName + "FalseNewZipCode").prop("disabled", true);
	});
	falseNewCity.click(function() {
			if ($("#" + addressFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + addressFieldsPropertyName + "ExistingCityFieldGroup").removeClass("hidden");
			}
			if (!$("#" + addressFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + addressFieldsPropertyName + "NewCityFieldGroup").addClass("hidden");
			}
			$("#" + addressFieldsPropertyName + "FalseNewZipCode").prop("disabled", false);
	});
}

/**
 * Applies on click functionality for the new zip code radio button.
 * 
 * @param addressFieldsPropertyName address fields property name 
 */
function applyAddressFieldsNewZipCodeOnClick(addressFieldsPropertyName) {
	var falseNewZipCode = $("#" + addressFieldsPropertyName + "FalseNewZipCode");
	var trueNewZipCode = $("#" + addressFieldsPropertyName + "TrueNewZipCode");
	trueNewZipCode.click(function() {
		newZipCodeDisplay(addressFieldsPropertyName);
		$("#" + addressFieldsPropertyName + "NewZipCode").val(true);
	});
	falseNewZipCode.click(function() {
		if (!$("#" + addressFieldsPropertyName + "NewZipCodeFieldGroup").hasClass("hidden")) {
			$("#" + addressFieldsPropertyName + "NewZipCodeFieldGroup").addClass("hidden");
		}
		if ($("#" + addressFieldsPropertyName + "ExistingZipCodeFieldGroup").hasClass("hidden")) {
			$("#" + addressFieldsPropertyName + "ExistingZipCodeFieldGroup").removeClass("hidden");
		}
		$("#" + addressFieldsPropertyName + "NewZipCode").val(false);
	});
}

/**
 * Display new zip code fields with the specified address fields property name.
 * 
 * @param addressFieldsPropertyName address fields property name
 */
function newZipCodeDisplay(addressFieldsPropertyName) {
	if ($("#" + addressFieldsPropertyName + "NewZipCodeFieldGroup").hasClass("hidden")) {
		$("#" + addressFieldsPropertyName + "NewZipCodeFieldGroup").removeClass("hidden");
	}
	if (!$("#" + addressFieldsPropertyName + "ExistingZipCodeFieldGroup").hasClass("hidden")) {
		$("#" + addressFieldsPropertyName + "ExistingZipCodeFieldGroup").addClass("hidden");
	}
}

/**
 * Enables all controls that are
 * part of the address fields snippet with the specified address fields property
 * name.
 * 
 * @param addressFieldsPropertyName address fields property name
 */
function enableAddressFieldControls(addressFieldsPropertyName) {
	$('#' + addressFieldsPropertyName + 'HouseNumber').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'StreetName').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'StreetSuffix').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'AddressUnitDesignator').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'UnitName').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'Country').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'State').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'FalseNewCity').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'TrueNewCity').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'City').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'CityName').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'FalseNewZipCode').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'TrueNewZipCode').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'ZipCode').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'ZipCodeValue').attr('disabled','disabled');
	$('#' + addressFieldsPropertyName + 'ZipCodeExtension').attr('disabled','disabled');
}

/**
 * Disables all controls that are
 * part of the address fields snippet with the specified address fields property
 * name.
 * 
 * @param addressFieldsPropertyName address fields property name
 */
function disableAddressFieldControls(addressFieldsPropertyName) {
	$('#' + addressFieldsPropertyName + 'HouseNumber').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'StreetName').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'StreetSuffix').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'AddressUnitDesignator').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'UnitName').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'Country').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'State').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'FalseNewCity').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'TrueNewCity').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'City').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'CityName').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'FalseNewZipCode').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'TrueNewZipCode').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'ZipCode').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'ZipCodeValue').removeAttr('disabled');
	$('#' + addressFieldsPropertyName + 'ZipCodeExtension').removeAttr('disabled');
}