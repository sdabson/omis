/**
 * Incident report edit behavior jquery implementation.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Sep 28, 2015)
 * Since: OMIS 3
 */

function assignIncidentStatementOnClick() {
	var facilitySelector = $("#facility");
	var complexSelector = $("#complex");
	var unitSelector = $("#unit");
	var levelSelector = $("#level");
	var sectionSelector = $("#section");
	var roomSelector = $("#room");
	var facilityAreaSelector = $("#facilityArea");
	var originalFacility = facilitySelector.val();
	var originalComplex = complexSelector.val();
	var originalUnit = unitSelector.val();
	var originalFacilityArea = facilityAreaSelector.val();
	var originalLevel = levelSelector.val();
	var originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
	var originalSection = sectionSelector.val();
	var originalSectionName = $("#section option[value='"+ originalSection +"']").text();
	var originalRoom = roomSelector.val();
	var facilityScene = $("#facilityScene");
	var housingScene = $("#housingScene");
	var noInformant = $("#noInformant");
	facilityScene.change(function() {
		if (facilityScene.prop('checked')) {
			$("#facilityContainer").removeClass("hidden");
		} else {
			$("#facilityContainer").addClass("hidden");
		}
	});
	housingScene.change(function() {
		if (housingScene.prop('checked')) {
			$("#housingContainer").removeClass("hidden");
			$("#nonHousingContainer").addClass("hidden");
		} else {
			$("#housingContainer").addClass("hidden");
			$("#nonHousingContainer").removeClass("hidden");
		}
	});
	facilitySelector.change(function() {
		if (facilitySelector.val() != '') {
			//Populate complex selector.
			showComplexOptions(facilitySelector.val());
			//Populate unit selector.
			showUnitOptions(facilitySelector.val(), complexSelector.val());
			originalUnit = unitSelector.val();
			//Populate facility area selector.
			showFacilityAreaOptions(facilitySelector.val(), complexSelector.val());
			originalFacilityArea = facilityAreaSelector.val();
			//Populate section selector.
			showSectionOptions(facilitySelector.val(), complexSelector.val(), "", "");
			originalSection = sectionSelector.val();
			originalSectionName = $("#section option[value='"+ originalSection +"']").text()
			//Populate level selector.
			showLevelOptions(facilitySelector.val(), complexSelector.val(), "", "");
			originalLevel = levelSelector.val();
			originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
			//Populate room selector.
			showRoomOptions(facilitySelector.val(), complexSelector.val(), 
					"", "", "");
			originalRoom = roomSelector.val();
			originalComplex = complexSelector.val();
		} else {
			//Populate facility area selector.
			facilityAreaSelector.find('option').remove().end().append('<option value="">...</option>').val('');
			originalFacilityArea = facilityAreaSelector.val();
		}
	});
	complexSelector.change(function(){
		if (complexSelector.val() != '') {
			if (originalComplex != complexSelector.val()) {
				//Populate unit selector.
				showUnitOptions(facilitySelector.val(), complexSelector.val());
				originalUnit = unitSelector.val();
				//Populate section selector.
				showSectionOptions(facilitySelector.val(), complexSelector.val(), "", "");
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate level selector.
				showLevelOptions(facilitySelector.val(), complexSelector.val(), "", "");
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				showRoomOptions(facilitySelector.val(), complexSelector.val(), 
						"", "", "");
				originalRoom = roomSelector.val();
				originalComplex = complexSelector.val();
			}
		}
		//Populate facility area selector.
		showFacilityAreaOptions(facilitySelector.val(), complexSelector.val());
		originalFacilityArea = facilityAreaSelector.val();
		originalComplex = complexSelector.val();
	});
	unitSelector.change(function(){
		if (unitSelector.val() != '') {
			if (originalUnit != unitSelector.val()) {
				//Populate section selector.
				showSectionOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), "");
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate level selector.
				showLevelOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), "");
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				showRoomOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), "", "");
				originalRoom = roomSelector.val();
				originalUnit = unitSelector.val();
			}
		} 
		originalUnit = unitSelector.val();
	});
	sectionSelector.change(function(){
		if (sectionSelector.val() != '') {
			if (originalSection != sectionSelector.val()) {
				//Populate level selector.
				showLevelOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), sectionSelector.val());
				if (levelSelector.children('option').length == 1) {
					if (originalLevel != null) {
						levelSelector.append($('<option selected="selected"></option>').val(originalLevel).html(originalLevelName));
					}
				}
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
				//Populate room selector.
				showRoomOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), sectionSelector.val(), 
						undefined);
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text();
			}
		} 
		originalSection = sectionSelector.val();
		originalSectionName = $("#section option[value='"+ originalSection +"']").text();
	});
	levelSelector.change(function(){
		if (levelSelector.val != '') {
			if (originalLevel != levelSelector.val()) {
				//Populate section selector.
				showSectionOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), levelSelector.val());
				if (sectionSelector.children('option').length == 1) {
					if (originalSection != null) {
						sectionSelector.append($('<option selected="selected"></option>').val(originalSection).html(originalSectionName));
					}
				}
				originalSection = sectionSelector.val();
				originalSectionName = $("#section option[value='"+ originalSection +"']").text()
				//Populate room selector.
				showRoomOptions(facilitySelector.val(), complexSelector.val(), 
						unitSelector.val(), undefined, 
						levelSelector.val());
				originalLevel = levelSelector.val();
				originalLevelName = $("#level option[value='"+ originalLevel +"']").text();
			}
		}
		originalLevel = levelSelector.val();		
	});
	for (var index = 0; index < currentIncidentStatementNoteItemIndex; index++) {
		applyIncidentStatementNoteItemRowBehavior(index);
	}
	for (var index = 0; index < currentInvolvedPartyItemIndex; index++) {
		applyInvolvedPartyItemRowBehavior(index, document.getElementById("involvedPartyItems[" + index + "].category").value);
	}
	var submissionButtons = document.getElementsByClassName("saveAndSubmitButton");
	$(submissionButtons).each(function() {
		$(this).click(function() {
			$("#submissionCategory").val($(this).attr("id"));
		});
	});
			
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
 * Submits a jQuery ajax request to re-populate unit options.
 * 
 * @param facility facility
 */
function showComplexOptions(facility) {
	$.ajax({
		type: "GET",
		async: false,
		url: "complexOptions.html",
		data: {facility: facility},
		success: function(response) {
			$("#complex").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#complex").html(jqXHR.responseText );
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
function showRoomOptions(facility, complex, unit, section, level) {
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
function showUnitOptions(facility, complex) {
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
 * Submits a jQuery ajax request to re-populate facility area options.
 * 
 * @param facility facility
 * @param complex complex
 */
function showFacilityAreaOptions(facility, complex) {
	$.ajax({
		type: "GET",
		async: false,
		url: "facilityAreaOptions.html",
		data: {facility: facility,
			complex: complex},
		success: function(response) {
			$("#facilityArea").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#facilityArea").html(jqXHR.responseText );
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
function showSectionOptions(facility, complex, unit, level) {
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
function showLevelOptions(facility, complex, unit, section) {
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
 * Assigns on click functionality to elements of the involved party action menu.
 */
function assignIncidentStatementNoteItemActionMenuOnClick() {
	var createLink = $("#createIncidentStatementNoteItemLink");
	$(createLink).click(function () {
		$.ajax(config.ServerConfig.getContextPath() + "/incident/statement/createIncidentStatementNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {incidentStatementNoteItemIndex: currentIncidentStatementNoteItemIndex,},
				success: function(data) {
					$("#incidentStatementNoteItemsTableBody").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#incidentStatementNoteItemsTableBody").html(jqXHR.responseText );
				}
			});
		applyIncidentStatementNoteItemRowBehavior(currentIncidentStatementNoteItemIndex);
		currentIncidentStatementNoteItemIndex++;
		return false;
	});
}
/**
 * Assigns on click functionality to elements of the involved party action menu.
 */
function assignInvolvedPartyActionMenuOnClick() {
	var elements = document.getElementsByClassName('createLink');
	var links = $(".createLink");
	$(links).each(function() {
		var category = this.id.replace("createInvolvedPartyItemLink", "");
		$(this).click(function () {
			$.ajax(config.ServerConfig.getContextPath() + "/incident/statement/createInvolvedPartyItem.html",
					   {
							type: "GET",
							async: false,
							data: {involvedPartyItemIndex: currentInvolvedPartyItemIndex,
								category: category},
							success: function(data) {
								$("#involvedPartyItemsTableBody").append(data);
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("Error - status: " + textStatus + "; error: "
									+ errorThrown);
								$("#involvedPartyItemsTableBody").html(jqXHR.responseText );
							}
						});
						applyInvolvedPartyItemRowBehavior(currentInvolvedPartyItemIndex, category);
						currentInvolvedPartyItemIndex++;
						return false;
			});
		});
}
/**
 * Assigns behavior to the incident report note item row with the specified index.
 * 
 * @param currentIncidentStatementNoteItemIndex current incident report note item index
 */
function applyIncidentStatementNoteItemRowBehavior(currentIncidentStatementNoteItemIndex) {
	applyDatePicker(document.getElementById("noteDate" + currentIncidentStatementNoteItemIndex));
	$("#incidentStatementNoteItemRemoveLink" + currentIncidentStatementNoteItemIndex).click(function() {
		if (document.getElementById("incidentStatementNoteItems[" + currentIncidentStatementNoteItemIndex + "].operation").value == "UPDATE") {
			document.getElementById("incidentStatementNoteItems[" + currentIncidentStatementNoteItemIndex + "].operation").value = "REMOVE";
			$("#incidentStatementNoteItemRow" + currentIncidentStatementNoteItemIndex).addClass("removeRow");
		} else if(document.getElementById("incidentStatementNoteItems[" + currentIncidentStatementNoteItemIndex + "].operation").value == "REMOVE") {
			document.getElementById("incidentStatementNoteItems[" + currentIncidentStatementNoteItemIndex + "].operation").value = "UPDATE";
			$("#incidentStatementNoteItemRow" + currentIncidentStatementNoteItemIndex).removeClass("removeRow");
		} else if(document.getElementById("incidentStatementNoteItems[" + currentIncidentStatementNoteItemIndex + "].operation").value == "CREATE") {
			$("#incidentStatementNoteItemRow" + currentIncidentStatementNoteItemIndex).remove();
		}
		return false;
	});
}

/**
 * Assigns behavior to the involved party item row with the specified index.
 *  
 * @param involvedPartyItemIndex involved party item index
 */
function applyInvolvedPartyItemRowBehavior(involvedPartyItemIndex, category) {
	$("#involvedPartyRemoveLink" + involvedPartyItemIndex).click(function() {
		if (document.getElementById("involvedPartyItems[" + involvedPartyItemIndex + "].operation").value == "UPDATE") {
			document.getElementById("involvedPartyItems[" + involvedPartyItemIndex + "].operation").setAttribute("value", "REMOVE");
			$("#involvedPartyItemRow" + involvedPartyItemIndex).addClass("removeRow");
		} else if(document.getElementById("involvedPartyItems[" + involvedPartyItemIndex + "].operation").value == "REMOVE") {
			document.getElementById("involvedPartyItems[" + involvedPartyItemIndex + "].operation").setAttribute("value", "UPDATE");
			$("#involvedPartyItemRow" + involvedPartyItemIndex).removeClass("removeRow");
		} else if(document.getElementById("involvedPartyItems[" + involvedPartyItemIndex + "].operation").value == "CREATE") {
			$("#involvedPartyItemRow" + involvedPartyItemIndex).remove();
		}
		return false;
	});
	if (category == "STAFF") {
		applyStaffSearch(document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Input"),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Display"),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Current"),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Clear"));
	} else if (category == "OFFENDER") {
		applyOffenderSearch(document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Input"),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Display"),
				document.getElementById("involvedPartyPerson" + involvedPartyItemIndex + "Clear"));
	}
}

function applyInformantSearch() {
	var category = $('input[name="informationSourceCategory"]:checked').val();
	if (category == "STAFF") {
		applyUserSearch(document.getElementById("informantInput"),
				document.getElementById("informant"),
				document.getElementById("informantDisplay"),
				document.getElementById("informantCurrent"),
				document.getElementById("informantClear"));
	} else if (category == "OFFENDER") {
		applyOffenderSearch(document.getElementById("informantInput"),
				document.getElementById("informant"),
				document.getElementById("informantDisplay"),
				document.getElementById("informantClear"));
	}
}

/**
 * Applies on click functionality for information source category control.
 */
function applyInformationSourceCategoryOnClick() {
	$(".informationSourceCategoryRadioButton").click(function() {
		var category = $(this).val();
		$.ajax({
			type: "GET",
			async: false,
			url: "informantSearch.html",
			data: {category: category},
			success: function(response) {
				$("#informantFieldGroup").html(response);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#informantFieldGroup").html(jqXHR.responseText );
			}
		});
		if (category != "ANONYMOUS") {
			applyInformantSearch();
		}
	});
}

/**
 * Assign a time picker to the DOM element with the specified id.
 *  
 * @param elementId DOM element id
 */
function assignTimePicker(elementId) {
	$("#" + elementId).ptTimeSelect();
}

/**
 * Submit ajax request to extend session.
 * 
 * @param url uniform resource locator
 * @param successFunction function to run on success (Optional)
 */
function submitAjaxRequest(url, successFunction) {
	$.ajax({
		type: "GET",
		async: true,
		url: config.ServerConfig.getContextPath() + url,
		success: function(data) {
			if (successFunction != null) {
				successFunction;
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
		}
	});
}
