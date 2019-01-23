function interstateDetainerActivityItemsCreateOnClick() {
	$("#createInterstateDetainerActivityItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/detainerNotification/createInterstateDetainerActivityItem.html",
		   {
				type: "GET",
				async: false,
				data: {
					interstateDetainerActivityItemIndex: currentInterstateDetainerActivityItemIndex,
					initiatedBy:  $("#initiatedBy").val()
				},
				success: function(data) {
					$("#interstateDetainerActivityItems").append(data);
					interstateDetainerActivityItemRowOnClick(currentInterstateDetainerActivityItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#interstateDetainerActivityItems").html(jqXHR.responseText );
				}
			});
		currentInterstateDetainerActivityItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function interstateDetainerActivityItemRowOnClick(interstateDetainerActivityItemIndex) {
	assignDatePicker("interstateDetainerActivityItems\\["+interstateDetainerActivityItemIndex+"\\]\\.activityDate");
	assignDatePicker("interstateDetainerActivityItems\\["+interstateDetainerActivityItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(interstateDetainerActivityItemIndex);
	applyCategoryOnChange(interstateDetainerActivityItemIndex);
	$("#createDocumentTagItemLink" + interstateDetainerActivityItemIndex).click(function() {
		documentTagItemsCreateOnClick(interstateDetainerActivityItemIndex);
	});
	$("#removeInterstateDetainerActivityLink" + interstateDetainerActivityItemIndex).click(function() {
		if ($("#interstateDetainerActivityOperation" + interstateDetainerActivityItemIndex).val() == "UPDATE") {
			$("#interstateDetainerActivityOperation" + interstateDetainerActivityItemIndex).val("REMOVE");
			$("#interstateDetainerActivityItem" + interstateDetainerActivityItemIndex).addClass("removeContent");
		} else if($("#interstateDetainerActivityOperation" + interstateDetainerActivityItemIndex).val() == "REMOVE") {
			$("#interstateDetainerActivityOperation" + interstateDetainerActivityItemIndex).val("UPDATE");
			$("#interstateDetainerActivityItem" +interstateDetainerActivityItemIndex).removeClass("removeContent");
		} else {
			$("#interstateDetainerActivityItem" + interstateDetainerActivityItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(interstateDetainerActivityItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[interstateDetainerActivityItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/detainerNotification/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				interstateDetainerActivityItemIndex: interstateDetainerActivityItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + interstateDetainerActivityItemIndex).append(data);
				documentTagItemRowOnClick(interstateDetainerActivityItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + interstateDetainerActivityItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[interstateDetainerActivityItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(interstateDetainerActivityItemIndex, documentTagItemIndex) {
	$("#removeInterstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#interstateDetainerActivity" + interstateDetainerActivityItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(interstateDetainerActivityItemIndex) {
	var documentData = document.getElementById("interstateDetainerActivityItems[" + interstateDetainerActivityItemIndex + "].data");
	var fileExtension = document.getElementById("interstateDetainerActivityItems[" + interstateDetainerActivityItemIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
};

function applyInitiatedByOnClick() {
	var initiatedBy = $("#initiatedBy");
	initiatedBy.change(function(){
		initiatedByChangeFunction(initiatedBy);
	});
};

function applyCategoryOnChange(interstateDetainerActivityItemIndex) {
	var category = $("#interstateDetainerActivityItems\\[" + interstateDetainerActivityItemIndex + "\\]\\.category");
	category.change(function(){
		categoryChangeFunction(interstateDetainerActivityItemIndex);
	});
};


function initiatedByChangeFunction(initiatedBy) {
	var data = {
		initiatedBy : initiatedBy.val()
	};
	var url = "showDetainerActivityCategoryOptions.html";
	var currentIndex = currentInterstateDetainerActivityItemIndex;
	
	var currentCategories = [];
	for (var index = 0; index < currentIndex; index++) {
		currentCategories.push($("#interstateDetainerActivityItems\\["+ index +"\\]\\.category").val());
	}
	
	$.ajax({
		type: "GET",	
		async: false,
		url: url,
		traditional: true,
		data: data,
		success: function(response) {
			for (var index = 0; index < currentIndex; index++) {
				 $("#interstateDetainerActivityItems\\["+ index +"\\]\\.category").html(response);
				 $("#interstateDetainerActivityItems\\["+ index +"\\]\\.category").val(currentCategories[index]);
				 categoryChangeFunction(index);
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			for (var index = 0; index < currentIndex; index++) {
				 $("#interstateDetainerActivityItems\\["+ index +"\\]\\.category").html(jqXHR.responseText );
			}
		}
	});
};

function categoryChangeFunction(interstateDetainerActivityItemIndex){
	var category = $("#interstateDetainerActivityItems\\[" + interstateDetainerActivityItemIndex + "\\]\\.category");
	var data = {
			category : category.val(),
			interstateDetainerActivityItemIndex : interstateDetainerActivityItemIndex
		};
		var url = "showDirectionOptions.html";
		
		var currentDirection = $("#interstateDetainerActivityItems\\["+ interstateDetainerActivityItemIndex +"\\]\\.direction").val();
		
		$.ajax({
			type: "GET",	
			async: false,
			url: url,
			traditional: true,
			data: data,
			success: function(response) {
				 $("#interstateDetainerActivityItems\\["+ interstateDetainerActivityItemIndex +"\\]\\.direction").html(response);
				 $("#interstateDetainerActivityItems\\["+ interstateDetainerActivityItemIndex +"\\]\\.direction").val(currentDirection);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#interstateDetainerActivityItems\\["+ interstateDetainerActivityItemIndex +"\\]\\.direction").html(jqXHR.responseText );
			}
		});
	};


function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/**
 * Assigns on click functionality for the detainer note items action menu.
 */
function detainerNoteItemsActionMenuOnClick() {
	$("#createDetainerNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/detainerNotification/createDetainerNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {detainerNoteItemIndex: currentDetainerNoteItemIndex},
				success: function(data) {
					$("#detainerNoteItemsTableBody").append(data);
					detainerNoteItemRowOnClick(currentDetainerNoteItemIndex);
					assignDatePicker("detainerNoteItemDate" + currentDetainerNoteItemIndex);
					applyDynamicHTMLTextCounter(document.getElementById("noteItems[" + currentDetainerNoteItemIndex + "].value"));
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#detainerNoteItemsTableBody").html(jqXHR.responseText );
				}
			});
		currentDetainerNoteItemIndex++;
		return false;
	});
};

/**
 * Assigns on click functionality for the service term note item row with the
 * specified service term note item index.
 * 
 * @param detainerNoteItemIndex service term note item index
 */
function detainerNoteItemRowOnClick(detainerNoteItemIndex) {
	$("#removeLink" + detainerNoteItemIndex).click(function() {
		if ($("#detainerNoteItemOperation" + detainerNoteItemIndex).val() == "UPDATE") {
			$("#detainerNoteItemOperation" + detainerNoteItemIndex).val("REMOVE");
			$("#detainerNoteItemRow" + detainerNoteItemIndex).addClass("removeRow");
		} else if($("#detainerNoteItemOperation" + detainerNoteItemIndex).val() == "REMOVE") {
			$("#detainerNoteItemOperation" + detainerNoteItemIndex).val("UPDATE");
			$("#detainerNoteItemRow" + detainerNoteItemIndex).removeClass("removeRow");
		} else {
			$("#detainerNoteItemRow" + detainerNoteItemIndex).remove();
		}
		return false;
	});
}

function applyFacilitySelectOnClick(facilitySelectElt, complexSelectElt, unitSelectElt) {
	var facilitySelector = $(facilitySelectElt);
	var complexSelector = $(complexSelectElt);
	var unitSelector = $(unitSelectElt);
	var originalComplex = complexSelector.val();
	var originalUnit = unitSelector.val();
	var originalFacility = facilitySelector.val();
	facilitySelector.change(function() {
		if (facilitySelector.val() != '') {
			if (originalFacility != facilitySelector.val()) {
				//Populate complex selector.
				showComplexOptions(facilitySelector.val());
				originalComplex = complexSelector.val();
				//Populate unit selector.
				showUnitOptions(facilitySelector.val(), complexSelector.val());
				originalUnit = unitSelector.val();
				originalFacility = facilitySelector.val();
			}
		} else {
			//Empty complex and unit selector options
			removeSelectOptions(complexSelector);
			removeSelectOptions(unitSelector);
			originalFacility = facilitySelector.val();
		}
	});
	complexSelector.change(function(){
		if (complexSelector.val() != '') {
			if (originalComplex != complexSelector.val()) {
				//Populate unit selector.
				showUnitOptions(facilitySelector.val(), complexSelector.val());
				originalUnit = unitSelector.val();
				originalComplex = complexSelector.val();
			}
		} else {
			//Empty unit selector options
			removeSelectOptions(unitSelector);
		}
		originalComplex = complexSelector.val();
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
 * Submits a jQuery ajax request to re-populate complex options.
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

function applyDetainerAgencyOnClick(){
	var detainerAgencySelect = $('#detainerAgencySelect');
	detainerAgencySelect.change(function(){
		var detainerAgency = detainerAgencySelect.val();
		if(detainerAgency){
			$.ajax({
				type: "GET",
				async: false,
				url: "detainerAgencyDetails.html",
				data: {
					detainerAgency: detainerAgency
				},
				success: function(response) {
					$("#detainerAgencyDetails").html(response);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
					$("#detainerAgencyDetails").html(jqXHR.responseText);
				}
			});
		}
		else{
			$("#detainerAgencyDetails").html('');
		}
	});
}
