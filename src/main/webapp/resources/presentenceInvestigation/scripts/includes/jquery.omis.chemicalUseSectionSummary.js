function chemicalUseSectionSummaryNoteItemsCreateOnClick() {
	$("#createChemicalUseSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/chemicalUseSummary/createChemicalUseSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {chemicalUseSectionSummaryNoteItemIndex: currentChemicalUseSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#chemicalUseSectionSummaryNoteTableBody").append(data);
					chemicalUseSectionSummaryNoteItemRowOnClick(currentChemicalUseSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#chemicalUseSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentChemicalUseSectionSummaryNoteItemIndex++;
		return false;
	});
};

function chemicalUseSectionSummaryNoteItemRowOnClick(chemicalUseSectionSummaryNoteItemIndex) {
	assignDatePicker("chemicalUseSectionSummaryNoteItemDate" + chemicalUseSectionSummaryNoteItemIndex);
	$("#removeChemicalUseSectionSummaryNoteLink" + chemicalUseSectionSummaryNoteItemIndex).click(function() {
		if ($("#chemicalUseSectionSummaryNoteOperation" + chemicalUseSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#chemicalUseSectionSummaryNoteOperation" + chemicalUseSectionSummaryNoteItemIndex).val("REMOVE");
			$("#chemicalUseSectionSummaryNoteItemRow" + chemicalUseSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#chemicalUseSectionSummaryNoteOperation" + chemicalUseSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#chemicalUseSectionSummaryNoteOperation" + chemicalUseSectionSummaryNoteItemIndex).val("UPDATE");
			$("#chemicalUseSectionSummaryNoteItemRow" +chemicalUseSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#chemicalUseSectionSummaryNoteItemRow" + chemicalUseSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};



function chemicalUseSectionSummaryDocumentAssociationItemsCreateOnClick() {
	$("#createChemicalUseSectionSummaryDocumentAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/chemicalUseSummary/createChemicalUseSectionSummaryDocumentAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {chemicalUseSectionSummaryDocumentAssociationItemIndex: currentChemicalUseSectionSummaryDocumentAssociationItemIndex},
				success: function(data) {
					$("#chemicalUseSectionSummaryDocumentAssociationItems").append(data);
					chemicalUseSectionSummaryDocumentAssociationItemRowOnClick(currentChemicalUseSectionSummaryDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#chemicalUseSectionSummaryDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		currentChemicalUseSectionSummaryDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function chemicalUseSectionSummaryDocumentAssociationItemRowOnClick(chemicalUseSectionSummaryDocumentAssociationItemIndex) {
	assignDatePicker("chemicalUseSectionSummaryDocumentAssociationItems\\["+chemicalUseSectionSummaryDocumentAssociationItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(chemicalUseSectionSummaryDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + chemicalUseSectionSummaryDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(chemicalUseSectionSummaryDocumentAssociationItemIndex);
	});
	$("#removeChemicalUseSectionSummaryDocumentAssociationLink" + chemicalUseSectionSummaryDocumentAssociationItemIndex).click(function() {
		if ($("#chemicalUseSectionSummaryDocumentAssociationOperation" + chemicalUseSectionSummaryDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#chemicalUseSectionSummaryDocumentAssociationOperation" + chemicalUseSectionSummaryDocumentAssociationItemIndex).val("REMOVE");
			$("#chemicalUseSectionSummaryDocumentAssociationItem" + chemicalUseSectionSummaryDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#chemicalUseSectionSummaryDocumentAssociationOperation" + chemicalUseSectionSummaryDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#chemicalUseSectionSummaryDocumentAssociationOperation" + chemicalUseSectionSummaryDocumentAssociationItemIndex).val("UPDATE");
			$("#chemicalUseSectionSummaryDocumentAssociationItem" +chemicalUseSectionSummaryDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#chemicalUseSectionSummaryDocumentAssociationItem" + chemicalUseSectionSummaryDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(chemicalUseSectionSummaryDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[chemicalUseSectionSummaryDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/chemicalUseSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				chemicalUseSectionSummaryDocumentAssociationItemIndex: chemicalUseSectionSummaryDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + chemicalUseSectionSummaryDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(chemicalUseSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + chemicalUseSectionSummaryDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[chemicalUseSectionSummaryDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(chemicalUseSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeChemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#chemicalUseSectionSummaryDocumentAssociation" + chemicalUseSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(chemicalUseSectionSummaryDocumentAssociationIndex) {
	var documentData = document.getElementById("chemicalUseSectionSummaryDocumentAssociationItems[" + chemicalUseSectionSummaryDocumentAssociationIndex + "].data");
	var fileExtension = document.getElementById("chemicalUseSectionSummaryDocumentAssociationItems[" + chemicalUseSectionSummaryDocumentAssociationIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
};







function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};