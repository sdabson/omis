/**
 * JQuery implementation for bedPlacement.js functions.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (February 08, 2015)
 * Since: OMIS 3.0
 */

/**
 * Assign the on click functionality for the bed placement form.
 */
function assignOnClick() {
	assignDatePicker("startDate");
	assignDatePicker("endDate");
	assignTimePicker("startTime");
	assignTimePicker("endTime");
	var offenderId = $('#offenderId').val();
	var facility = $("#facility").val();
	var complexSelector = $("#complex");
	var unitSelector = $("#unit");
	var levelSelector = $("#level");
	var sectionSelector = $("#section");
	var roomSelector = $("#room");
	var bedSelector = $("#bed");
	var originalComplex = complexSelector.val();
	var originalUnit = unitSelector.val();
	var originalLevel = levelSelector.val();
	var originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
	var originalSection = sectionSelector.val();
	var originalSectionName = $("#section option[value='"+ originalSection +"']").text();
	var originalRoom = roomSelector.val();
	var originalBed = bedSelector.val();
	complexSelector.change(function(){
		if (complexSelector.val() != '') {
			if (originalComplex != complexSelector.val()) {
				//Populate unit selector.
				filterUnits(facility, complexSelector.val());
				originalUnit = unitSelector.val();
				//Populate section selector.
				filterSections(facility, complexSelector.val(), "", "");
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate level selector.
				filterLevels(facility, complexSelector.val(), "", "");
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				filterRooms(facility, complexSelector.val(), 
						"", "", "");
				originalRoom = roomSelector.val();
				originalBed = removeSelectOptions(bedSelector);
			}
		} else {
			filterUnits(facility, "");
			originalUnit = unitSelector.val();
			//Populate section selector.
			filterSections(facility, "", "", "");
			originalSection = sectionSelector.val();
			originalSectionName = $("#section option[value='"+ originalSection +"']").text()
			//Populate level selector.
			filterLevels(facility, "", "", "");
			originalLevel = levelSelector.val();
			originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
			//Populate room selector.
			filterRooms(facility, "", 
					"", "", "");
		}
		originalComplex = complexSelector.val();
	});
	unitSelector.change(function(){
		if (unitSelector.val() != '') {
			if (originalUnit != unitSelector.val()) {
				//Populate section selector.
				filterSections(facility, complexSelector.val(), 
						unitSelector.val(), "");
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate level selector.
				filterLevels(facility, complexSelector.val(), 
						unitSelector.val(), "");
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				filterRooms(facility, complexSelector.val(), 
						unitSelector.val(), "", "");
				originalRoom = roomSelector.val();
				originalUnit = unitSelector.val();
				originalBed = removeSelectOptions(bedSelector);
			}
		} 
		originalUnit = unitSelector.val();
	});
	sectionSelector.change(function(){
		if (sectionSelector.val() != '') {
			if (originalSection != sectionSelector.val()) {
				//Populate level selector.
				filterLevels(facility, complexSelector.val(), 
						unitSelector.val(), sectionSelector.val());
//				if (levelSelector.children('option').length == 1) {
//					if (originalLevel != null) {
//						levelSelector.append($('<option selected="selected"></option>').val(originalLevel).html(originalLevelName));
//					}
//				}
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				filterRooms(facility, complexSelector.val(), 
						unitSelector.val(), sectionSelector.val(), 
						undefined);
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				originalBed = removeSelectOptions(bedSelector);
			}
		} 
		originalSection = sectionSelector.val();
		originalSectionName = $("#section option[value='"+ originalSection +"']").text()
	});
	levelSelector.change(function(){
		if (levelSelector.val != '') {
			if (originalLevel != levelSelector.val()) {
				//Populate section selector.
				filterSections(facility, complexSelector.val(), 
						unitSelector.val(), levelSelector.val());
//				if (sectionSelector.children('option').length == 1) {
//					if (originalSection != '') {
//						sectionSelector.append($('<option selected="selected"></option>').val(originalSection).html(originalSectionName));
//					}
//				}
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate room selector.
				filterRooms(facility, complexSelector.val(), 
						unitSelector.val(), undefined, 
						levelSelector.val());
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				originalBed = removeSelectOptions(bedSelector);
			}
		}
		originalLevel = levelSelector.val();
	});
	roomSelector.change(function(){
		if (roomSelector.val() != '') {
			if (originalRoom != roomSelector.val()) {
				bedSelection(offenderId);
			}
		} else {
			originalBed = removeSelectOptions(bedSelector);
		}
		originalRoom = roomSelector.val();
	});
}

/**
 * Submits a jQuery ajax request to re-populate bed options.
 * 
 * @param offenderId offender id
 */
function bedSelection(offenderId){
	var roomId = $("#room").val();
	$.ajax({
		type: "GET",	
		async: false,
		url: "bedSelect.html",
		data: {offender: offenderId,
			room: roomId},
		success: function(response) {
			$("#bed").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#bed").html(jqXHR.responseText );
		}
	});
}

/**
 * Submits a jQuery ajax request to re-populate room options.
 * 
 * @param faciilty facility
 * @param complex complex
 * @param unit unit
 * @param section section
 * @param level level
 */
function filterRooms(facility, complex, unit, section, level) {
	$.ajax({
		type: "GET",
		async: false,
		url: "roomOptions.html",
		data: {facility: facility,
			complex: complex,
			unit: unit,
			level: level,
			section: section},
		success: function(response) {
			$("#room").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#room").html(jqXHR.responseText );
		}
	});
}

/**
 * Submits a jQuery ajax request to re-populate unit options.
 * 
 * @param facility facility
 * @param complex complex
 */
function filterUnits(facility, complex) {
	$.ajax({
		type: "GET",
		async: false,
		url: "unitOptions.html",
		data: {facility: facility,
			complex: complex},
		success: function(response) {
			$("#unit").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#unit").html(jqXHR.responseText );
		}
	});
}

/**
 * Submits a jQuery ajax request to re-populate section options.
 * 
 * @param facility facility
 * @param complex complex
 * @param unit unit
 * @param level level
 */
function filterSections(facility, complex, unit, level) {
	$.ajax({
		type: "GET",
		async: false,
		url: "sectionOptions.html",
		data: {facility: facility,
			complex: complex,
			unit: unit,
			level: level},
		success: function(response) {
			$("#section").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#section").html(jqXHR.responseText );
		}
	});
}

/**
 * Submits a jQuery ajax request to re-populate level options.
 * 
 * @param facility facility
 * @param complex complex
 * @param unit unit
 * @param section section
 */
function filterLevels(facility, complex, unit, section) {
	$.ajax({
		type: "GET",
		async: false,
		url: "levelOptions.html",
		data: {facility: facility,
			complex: complex,
			unit: unit,
			section: section},
		success: function(response) {
			$("#level").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#level").html(jqXHR.responseText );
		}
	});
}

/**
 * Adds parameter to a URL.
 * 
 * @param url url
 * @param paramName parameter name
 * @param paramValue parameter value
 * @param optional whether parameter is optional
 * @returns url with parameter added
 */
function addParam(url, paramName, paramValue, optional) {
	if (!optional || (paramValue != null && paramValue != "")) {
		return url + "&" + paramName + "=" + paramValue;
	} else {
		return url;
	}
}

/**
 * 
 * Removes all of the options for a select option but supplies the null option,
 * then returns that value.
 * 
 * @param element jQuery selected element
 * @returns element value after removal of options
 */
function removeSelectOptions(element){
	element.html("<option value=''>...</option>")
	return element.val();
}

/**
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 */
function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/**
 * Assign a time picker to the DOM element with the specified id.
 *  
 * @param elementId DOM element id
 */
function assignTimePicker(elementId) {
	$("#" + elementId).attr("autocomplete", "off");
	$("#" + elementId).ptTimeSelect();
};