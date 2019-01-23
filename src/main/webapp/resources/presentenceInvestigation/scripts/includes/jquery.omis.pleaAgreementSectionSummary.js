function pleaAgreementSectionSummaryNoteItemsCreateOnClick() {
	$("#createPleaAgreementSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/pleaAgreementSummary/createPleaAgreementSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {pleaAgreementSectionSummaryNoteItemIndex: currentPleaAgreementSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#pleaAgreementSectionSummaryNoteTableBody").append(data);
					pleaAgreementSectionSummaryNoteItemRowOnClick(currentPleaAgreementSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#pleaAgreementSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentPleaAgreementSectionSummaryNoteItemIndex++;
		return false;
	});
};

function pleaAgreementSectionSummaryNoteItemRowOnClick(pleaAgreementSectionSummaryNoteItemIndex) {
	assignDatePicker("pleaAgreementSectionSummaryNoteItemDate" + pleaAgreementSectionSummaryNoteItemIndex);
	$("#removePleaAgreementSectionSummaryNoteLink" + pleaAgreementSectionSummaryNoteItemIndex).click(function() {
		if ($("#pleaAgreementSectionSummaryNoteOperation" + pleaAgreementSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#pleaAgreementSectionSummaryNoteOperation" + pleaAgreementSectionSummaryNoteItemIndex).val("REMOVE");
			$("#pleaAgreementSectionSummaryNoteItemRow" + pleaAgreementSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#pleaAgreementSectionSummaryNoteOperation" + pleaAgreementSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#pleaAgreementSectionSummaryNoteOperation" + pleaAgreementSectionSummaryNoteItemIndex).val("UPDATE");
			$("#pleaAgreementSectionSummaryNoteItemRow" +pleaAgreementSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#pleaAgreementSectionSummaryNoteItemRow" + pleaAgreementSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};



function pleaAgreementSectionSummaryAssociableDocumentItemsCreateOnClick() {
	$("#createPleaAgreementSectionSummaryAssociableDocumentItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/pleaAgreementSummary/createPleaAgreementSectionSummaryAssociableDocumentItem.html",
		   {
				type: "GET",
				async: false,
				data: {pleaAgreementSectionSummaryAssociableDocumentItemIndex: currentPleaAgreementSectionSummaryAssociableDocumentItemIndex},
				success: function(data) {
					$("#pleaAgreementSectionSummaryAssociableDocumentItems").append(data);
					pleaAgreementSectionSummaryAssociableDocumentItemRowOnClick(currentPleaAgreementSectionSummaryAssociableDocumentItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#pleaAgreementSectionSummaryAssociableDocumentItems").html(jqXHR.responseText );
				}
			});
		currentPleaAgreementSectionSummaryAssociableDocumentItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function pleaAgreementSectionSummaryAssociableDocumentItemRowOnClick(pleaAgreementSectionSummaryAssociableDocumentItemIndex) {
	assignDatePicker("pleaAgreementSectionSummaryAssociableDocumentItems\\["+pleaAgreementSectionSummaryAssociableDocumentItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(pleaAgreementSectionSummaryAssociableDocumentItemIndex);
	$("#createDocumentTagItemLink" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).click(function() {
		documentTagItemsCreateOnClick(pleaAgreementSectionSummaryAssociableDocumentItemIndex);
	});
	$("#removePleaAgreementSectionSummaryAssociableDocumentLink" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).click(function() {
		if ($("#pleaAgreementSectionSummaryAssociableDocumentOperation" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).val() == "UPDATE") {
			$("#pleaAgreementSectionSummaryAssociableDocumentOperation" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).val("REMOVE");
			$("#pleaAgreementSectionSummaryAssociableDocumentItem" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).addClass("removeContent");
		} else if($("#pleaAgreementSectionSummaryAssociableDocumentOperation" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).val() == "REMOVE") {
			$("#pleaAgreementSectionSummaryAssociableDocumentOperation" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).val("UPDATE");
			$("#pleaAgreementSectionSummaryAssociableDocumentItem" +pleaAgreementSectionSummaryAssociableDocumentItemIndex).removeClass("removeContent");
		} else {
			$("#pleaAgreementSectionSummaryAssociableDocumentItem" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(pleaAgreementSectionSummaryAssociableDocumentItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[pleaAgreementSectionSummaryAssociableDocumentItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/pleaAgreementSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				pleaAgreementSectionSummaryAssociableDocumentItemIndex: pleaAgreementSectionSummaryAssociableDocumentItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).append(data);
				documentTagItemRowOnClick(pleaAgreementSectionSummaryAssociableDocumentItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + pleaAgreementSectionSummaryAssociableDocumentItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[pleaAgreementSectionSummaryAssociableDocumentItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(pleaAgreementSectionSummaryAssociableDocumentItemIndex, documentTagItemIndex) {
	$("#removePleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#pleaAgreementSectionSummaryAssociableDocument" + pleaAgreementSectionSummaryAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(pleaAgreementSectionSummaryAssociableDocumentIndex) {
	var documentData = document.getElementById("pleaAgreementSectionSummaryAssociableDocumentItems[" + pleaAgreementSectionSummaryAssociableDocumentIndex + "].data");
	var fileExtension = document.getElementById("pleaAgreementSectionSummaryAssociableDocumentItems[" + pleaAgreementSectionSummaryAssociableDocumentIndex + "].fileExtension");
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