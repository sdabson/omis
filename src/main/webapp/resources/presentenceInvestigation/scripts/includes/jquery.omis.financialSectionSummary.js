function financialSectionSummaryDocumentAssociationItemsCreateOnClick() {
	$("#createFinancialSectionSummaryDocumentAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/financialSummary/createFinancialSectionSummaryDocumentAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {financialSectionSummaryDocumentAssociationItemIndex: currentFinancialSectionSummaryDocumentAssociationItemIndex},
				success: function(data) {
					$("#financialSectionSummaryDocumentAssociationItems").append(data);
					financialSectionSummaryDocumentAssociationItemRowOnClick(currentFinancialSectionSummaryDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#financialSectionSummaryDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		currentFinancialSectionSummaryDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function financialSectionSummaryDocumentAssociationItemRowOnClick(financialSectionSummaryDocumentAssociationItemIndex) {
	assignDatePicker("financialSectionSummaryDocumentAssociationItems\\["+financialSectionSummaryDocumentAssociationItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(financialSectionSummaryDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + financialSectionSummaryDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(financialSectionSummaryDocumentAssociationItemIndex);
	});
	$("#removeFinancialSectionSummaryDocumentAssociationLink" + financialSectionSummaryDocumentAssociationItemIndex).click(function() {
		if ($("#financialSectionSummaryDocumentAssociationOperation" + financialSectionSummaryDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#financialSectionSummaryDocumentAssociationOperation" + financialSectionSummaryDocumentAssociationItemIndex).val("REMOVE");
			$("#financialSectionSummaryDocumentAssociationItem" + financialSectionSummaryDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#financialSectionSummaryDocumentAssociationOperation" + financialSectionSummaryDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#financialSectionSummaryDocumentAssociationOperation" + financialSectionSummaryDocumentAssociationItemIndex).val("UPDATE");
			$("#financialSectionSummaryDocumentAssociationItem" +financialSectionSummaryDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#financialSectionSummaryDocumentAssociationItem" + financialSectionSummaryDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(financialSectionSummaryDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[financialSectionSummaryDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/financialSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				financialSectionSummaryDocumentAssociationItemIndex: financialSectionSummaryDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + financialSectionSummaryDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(financialSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + financialSectionSummaryDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[financialSectionSummaryDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(financialSectionSummaryDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeFinancialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#financialSectionSummaryDocumentAssociation" + financialSectionSummaryDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};

function financialSectionSummaryNoteItemsCreateOnClick() {
	$("#createFinancialSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/financialSummary/createFinancialSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {financialSectionSummaryNoteItemIndex: currentFinancialSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#financialSectionSummaryNoteTableBody").append(data);
					financialSectionSummaryNoteItemRowOnClick(currentFinancialSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#financialSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentFinancialSectionSummaryNoteItemIndex++;
		return false;
	});
};

function financialSectionSummaryNoteItemRowOnClick(financialSectionSummaryNoteItemIndex) {
	assignDatePicker("financialSectionSummaryNoteItemDate" + financialSectionSummaryNoteItemIndex);
	$("#removeFinancialSectionSummaryNoteLink" + financialSectionSummaryNoteItemIndex).click(function() {
		if ($("#financialSectionSummaryNoteOperation" + financialSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#financialSectionSummaryNoteOperation" + financialSectionSummaryNoteItemIndex).val("REMOVE");
			$("#financialSectionSummaryNoteItemRow" + financialSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#financialSectionSummaryNoteOperation" + financialSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#financialSectionSummaryNoteOperation" + financialSectionSummaryNoteItemIndex).val("UPDATE");
			$("#financialSectionSummaryNoteItemRow" +financialSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#financialSectionSummaryNoteItemRow" + financialSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(financialSectionSummaryDocumentAssociationIndex) {
	var documentData = document.getElementById("financialSectionSummaryDocumentAssociationItems[" + financialSectionSummaryDocumentAssociationIndex + "].data");
	var fileExtension = document.getElementById("financialSectionSummaryDocumentAssociationItems[" + financialSectionSummaryDocumentAssociationIndex + "].fileExtension");
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