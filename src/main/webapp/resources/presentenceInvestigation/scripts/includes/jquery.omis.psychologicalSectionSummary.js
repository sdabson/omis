function psychologicalSectionSummaryNoteItemsCreateOnClick() {
	$("#createPsychologicalSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/psychologicalSummary/createPsychologicalSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {psychologicalSectionSummaryNoteItemIndex: currentPsychologicalSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#psychologicalSectionSummaryNoteTableBody").append(data);
					psychologicalSectionSummaryNoteItemRowOnClick(currentPsychologicalSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#psychologicalSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentPsychologicalSectionSummaryNoteItemIndex++;
		return false;
	});
};

function psychologicalSectionSummaryNoteItemRowOnClick(psychologicalSectionSummaryNoteItemIndex) {
	assignDatePicker("psychologicalSectionSummaryNoteItemDate" + psychologicalSectionSummaryNoteItemIndex);
	$("#removePsychologicalSectionSummaryNoteLink" + psychologicalSectionSummaryNoteItemIndex).click(function() {
		if ($("#psychologicalSectionSummaryNoteOperation" + psychologicalSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#psychologicalSectionSummaryNoteOperation" + psychologicalSectionSummaryNoteItemIndex).val("REMOVE");
			$("#psychologicalSectionSummaryNoteItemRow" + psychologicalSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#psychologicalSectionSummaryNoteOperation" + psychologicalSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#psychologicalSectionSummaryNoteOperation" + psychologicalSectionSummaryNoteItemIndex).val("UPDATE");
			$("#psychologicalSectionSummaryNoteItemRow" +psychologicalSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#psychologicalSectionSummaryNoteItemRow" + psychologicalSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};



function psychologicalSectionSummaryDocumentItemsCreateOnClick() {
	$("#createPsychologicalSectionSummaryDocumentItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/psychologicalSummary/createPsychologicalSectionSummaryDocumentItem.html",
		   {
				type: "GET",
				async: false,
				data: {psychologicalSectionSummaryDocumentItemIndex: currentPsychologicalSectionSummaryDocumentItemIndex},
				success: function(data) {
					$("#psychologicalSectionSummaryDocumentItems").append(data);
					psychologicalSectionSummaryDocumentItemRowOnClick(currentPsychologicalSectionSummaryDocumentItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#psychologicalSectionSummaryDocumentItems").html(jqXHR.responseText );
				}
			});
		currentPsychologicalSectionSummaryDocumentItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function psychologicalSectionSummaryDocumentItemRowOnClick(psychologicalSectionSummaryDocumentItemIndex) {
	assignDatePicker("psychologicalSectionSummaryDocumentItems\\["+psychologicalSectionSummaryDocumentItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(psychologicalSectionSummaryDocumentItemIndex);
	$("#createDocumentTagItemLink" + psychologicalSectionSummaryDocumentItemIndex).click(function() {
		documentTagItemsCreateOnClick(psychologicalSectionSummaryDocumentItemIndex);
	});
	$("#removePsychologicalSectionSummaryDocumentLink" + psychologicalSectionSummaryDocumentItemIndex).click(function() {
		if ($("#psychologicalSectionSummaryDocumentOperation" + psychologicalSectionSummaryDocumentItemIndex).val() == "UPDATE") {
			$("#psychologicalSectionSummaryDocumentOperation" + psychologicalSectionSummaryDocumentItemIndex).val("REMOVE");
			$("#psychologicalSectionSummaryDocumentItem" + psychologicalSectionSummaryDocumentItemIndex).addClass("removeContent");
		} else if($("#psychologicalSectionSummaryDocumentOperation" + psychologicalSectionSummaryDocumentItemIndex).val() == "REMOVE") {
			$("#psychologicalSectionSummaryDocumentOperation" + psychologicalSectionSummaryDocumentItemIndex).val("UPDATE");
			$("#psychologicalSectionSummaryDocumentItem" +psychologicalSectionSummaryDocumentItemIndex).removeClass("removeContent");
		} else {
			$("#psychologicalSectionSummaryDocumentItem" + psychologicalSectionSummaryDocumentItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(psychologicalSectionSummaryDocumentItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[psychologicalSectionSummaryDocumentItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/psychologicalSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				psychologicalSectionSummaryDocumentItemIndex: psychologicalSectionSummaryDocumentItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + psychologicalSectionSummaryDocumentItemIndex).append(data);
				documentTagItemRowOnClick(psychologicalSectionSummaryDocumentItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + psychologicalSectionSummaryDocumentItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[psychologicalSectionSummaryDocumentItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(psychologicalSectionSummaryDocumentItemIndex, documentTagItemIndex) {
	$("#removePsychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#psychologicalSectionSummaryDocument" + psychologicalSectionSummaryDocumentItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyFileExtensionNamer(psychologicalSectionSummaryDocumentIndex) {
	var documentData = document.getElementById("psychologicalSectionSummaryDocumentItems[" + psychologicalSectionSummaryDocumentIndex + "].data");
	var fileExtension = document.getElementById("psychologicalSectionSummaryDocumentItems[" + psychologicalSectionSummaryDocumentIndex + "].fileExtension");
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