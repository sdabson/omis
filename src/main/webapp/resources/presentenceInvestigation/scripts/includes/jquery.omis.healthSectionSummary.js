function healthSectionSummaryNoteItemsCreateOnClick() {
	$("#createHealthSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/healthSummary/createHealthSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {healthSectionSummaryNoteItemIndex: currentHealthSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#healthSectionSummaryNoteTableBody").append(data);
					healthSectionSummaryNoteItemRowOnClick(currentHealthSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#healthSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentHealthSectionSummaryNoteItemIndex++;
		return false;
	});
};

function healthSectionSummaryNoteItemRowOnClick(healthSectionSummaryNoteItemIndex) {
	assignDatePicker("healthSectionSummaryNoteItemDate" + healthSectionSummaryNoteItemIndex);
	$("#removeHealthSectionSummaryNoteLink" + healthSectionSummaryNoteItemIndex).click(function() {
		if ($("#healthSectionSummaryNoteOperation" + healthSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#healthSectionSummaryNoteOperation" + healthSectionSummaryNoteItemIndex).val("REMOVE");
			$("#healthSectionSummaryNoteItemRow" + healthSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#healthSectionSummaryNoteOperation" + healthSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#healthSectionSummaryNoteOperation" + healthSectionSummaryNoteItemIndex).val("UPDATE");
			$("#healthSectionSummaryNoteItemRow" +healthSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#healthSectionSummaryNoteItemRow" + healthSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};



function healthSectionSummaryDocumentItemsCreateOnClick() {	
	$("#createHealthSectionSummaryDocumentItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/healthSummary/createHealthSectionSummaryDocumentAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {healthSectionSummaryDocumentAssociationItemIndex: currentHealthSectionSummaryDocumentAssociationItemIndex},
				success: function(data) {
					$("#healthSectionSummaryDocumentAssociationItems").append(data);
					healthSectionSummaryDocumentItemRowOnClick(currentHealthSectionSummaryDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#healthSectionSummaryDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		currentHealthSectionSummaryDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function healthSectionSummaryDocumentItemRowOnClick(healthSectionSummaryDocumentAssociationItemIndex) {
	assignDatePicker("healthSectionSummaryDocumentAssociationItems\\["+healthSectionSummaryDocumentAssociationItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(healthSectionSummaryDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + healthSectionSummaryDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(healthSectionSummaryDocumentAssociationItemIndex);
	});
	
	$("#removeHealthSectionSummaryDocumentAssociationLink" + healthSectionSummaryDocumentAssociationItemIndex).click(function() {
		if ($("#healthSectionSummaryDocumentAssociationOperation" + healthSectionSummaryDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#healthSectionSummaryDocumentAssociationOperation" + healthSectionSummaryDocumentAssociationItemIndex).val("REMOVE");
			$("#healthSectionSummaryDocumentAssociationItem" + healthSectionSummaryDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#healthSectionSummaryDocumentAssociationOperation" + healthSectionSummaryDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#healthSectionSummaryDocumentAssociationOperation" + healthSectionSummaryDocumentAssociationItemIndex).val("UPDATE");
			$("#healthSectionSummaryDocumentAssociationItem" +healthSectionSummaryDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#healthSectionSummaryDocumentAssociationItem" + healthSectionSummaryDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(healthSectionSummaryDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[healthSectionSummaryDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/healthSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				healthSectionSummaryDocumentAssociationItemIndex: healthSectionSummaryDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + healthSectionSummaryDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(healthSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + healthSectionSummaryDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[healthSectionSummaryDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(healthSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeHealthSectionSummaryDocument" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#healthSectionSummaryDocumentAssociation" + healthSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(healthSectionSummaryDocumentAssociationItemIndex) {
	var documentData = document.getElementById("healthSectionSummaryDocumentAssociationItems[" + healthSectionSummaryDocumentAssociationItemIndex + "].data");
	var fileExtension = document.getElementById("healthSectionSummaryDocumentAssociationItems[" + healthSectionSummaryDocumentAssociationItemIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
};

function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};