/*
 * Person fields functionality jquery implementation. 
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (June 15, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the birth country field.
 * 
 * @param personFieldsPropertyName personFieldsPropertyName
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 */
function applyPersonFieldsBirthCountryOnClick(personFieldsPropertyName, stateOptionsUrl, cityOptionsUrl) {
	var birthCountry = $("#" + personFieldsPropertyName + "BirthCountry");
	birthCountry.change(function() {
		$.ajax({
			type: "GET",	
			async: false,
			url: stateOptionsUrl,
			data: {country: birthCountry.val(),
				personFieldsPropertyName: personFieldsPropertyName},
			success: function(response) {
				 $("#" + personFieldsPropertyName + "BirthState").html(response);
				 var stateCount = $("#" + personFieldsPropertyName + "State > option").length;
				/* if (stateCount < 2) {
					 personFieldsStateChangeFunction(personFieldsPropertyName, cityOptionsUrl);
				 } else {
					 $("#" + personFieldsPropertyName + "BirthCity").html("<option value=\"\">...</option>");
				 }*/
				 personFieldsStateChangeFunction(personFieldsPropertyName, cityOptionsUrl);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#" + personFieldsPropertyName + "BirthState").html(jqXHR.responseText );
			}
		});
	});
}

/**
 * Applies on click functionality for the birth country field.
 * 
 * @param personFieldsPropertyName personFieldsPropertyName
 * @param url Uniform resource locator
 */
function applyPersonFieldsBirthStateOnClick(personFieldsPropertyName, url) {
	var birthState = $("#" + personFieldsPropertyName + "BirthState");
	birthState.change(function() {
		personFieldsStateChangeFunction(personFieldsPropertyName, url);
	});
}

/**
 * Function handle change functionality for state control.
 * 
 * @param personFieldsPropertyName person fields property name
 * @param url uniform resource locator
 */
function personFieldsStateChangeFunction(personFieldsPropertyName, url) {
	var birthState = $("#" + personFieldsPropertyName + "BirthState");
	var birthCountry = $("#" + personFieldsPropertyName + "BirthCountry");
	$.ajax({
		type: "GET",	
		async: false,
		url: url,
		data: {country: birthCountry.val(),
			state: birthState.val(),
			personFieldsPropertyName: personFieldsPropertyName},
		success: function(response) {
			 $("#" + personFieldsPropertyName + "BirthCity").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#" + personFieldsPropertyName + "BirthCity").html(jqXHR.responseText );
		}
	});
}

function applyPersonFieldsNewBirthCityOnClick(personFieldsPropertyName) {
	var falseNewCity = $("#" + personFieldsPropertyName + "FalseNewCity");
	var trueNewCity = $("#" + personFieldsPropertyName + "TrueNewCity");
	trueNewCity.click(function() {
			if (!$("#" + personFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + personFieldsPropertyName + "ExistingCityFieldGroup").addClass("hidden");
			}
			if ($("#" + personFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + personFieldsPropertyName + "NewCityFieldGroup").removeClass("hidden");
			}
	});
	falseNewCity.click(function() {
			if ($("#" + personFieldsPropertyName + "ExistingCityFieldGroup").hasClass("hidden")) {
				$("#" + personFieldsPropertyName + "ExistingCityFieldGroup").removeClass("hidden");
			}
			if (!$("#" + personFieldsPropertyName + "NewCityFieldGroup").hasClass("hidden")) {
				$("#" + personFieldsPropertyName + "NewCityFieldGroup").addClass("hidden");
			}
	});
}

/*
 * Apply functionality to the deceased checkbox input to either
 * enable or disable the death date input.
 * 
 * @param personFieldsPropertyName person fields property name
 */
function applyDeceasedOnClick(personFieldsPropertyName) {
	deceasedInput = $("#" + personFieldsPropertyName + "Deceased");
	deathDate = $("#" + personFieldsPropertyName + "DeathDate");
	deceasedInput.click(function() {
		if (deceasedInput.prop('checked')) {
			deathDate.removeAttr("disabled");
		} else {
			deathDate.attr("disabled", "disabled");
			deathDate.val("");
		}
	});
}

/*
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 * @param yearRange range of years to use for the date picker
 */
function assignPersonFieldsDatePicker(elementId, yearRange) {
	$("#" + elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: yearRange
	});
};

/*
 * Disable all input controls related to person fields.
 * 
 * @param personFieldsPropertyName person fields property name
 */
function disablePersonFields(personFieldsPropertyName) {
	$('#' + personFieldsPropertyName + 'FirstName').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'LastName').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'MiddleName').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'Suffix').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'Sex').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'BirthDate').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'BirthState').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'BirthCountry').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'BirthCity').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'BirthCityName').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'SocialSecurityNumber').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'StateIdNumber').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'Deceased').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'DeathDate').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'TrueNewCity').attr('disabled','disabled');
	$('#' + personFieldsPropertyName + 'FalseNewCity').attr('disabled','disabled');
};

/*
 * Enable all input controls related to person fields.
 * 
 * @param personFieldsPropertyName person fields property name
 */
function enablePersonFields(personFieldsPropertyName) {
	$('#' + personFieldsPropertyName + 'FirstName').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'LastName').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'MiddleName').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'Suffix').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'Sex').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'BirthDate').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'BirthState').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'BirthCountry').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'BirthCity').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'BirthCityName').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'SocialSecurityNumber').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'StateIdNumber').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'Deceased').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'DeathDate').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'TrueNewCity').removeAttr('disabled');
	$('#' + personFieldsPropertyName + 'FalseNewCity').removeAttr('disabled');
}