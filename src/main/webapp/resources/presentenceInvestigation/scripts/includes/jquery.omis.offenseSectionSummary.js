function offenseSectionSummaryAssociableDocumentItemsCreateOnClick() {
	$("#createOffenseSectionSummaryAssociableDocumentItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/createOffenseSectionSummaryAssociableDocumentItem.html",
		   {
				type: "GET",
				async: false,
				data: {offenseSectionSummaryAssociableDocumentItemIndex: currentOffenseSectionSummaryAssociableDocumentItemIndex},
				success: function(data) {
					$("#offenseSectionSummaryAssociableDocumentItems").append(data);
					offenseSectionSummaryAssociableDocumentItemRowOnClick(currentOffenseSectionSummaryAssociableDocumentItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#offenseSectionSummaryAssociableDocumentItems").html(jqXHR.responseText );
				}
			});
		currentOffenseSectionSummaryAssociableDocumentItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function offenseSectionSummaryAssociableDocumentItemRowOnClick(offenseSectionSummaryAssociableDocumentItemIndex) {
	assignDatePicker("offenseSectionSummaryAssociableDocumentItems\\["+offenseSectionSummaryAssociableDocumentItemIndex+"\\]\\.fileDate");
	applyItemFileExtensionNamer(offenseSectionSummaryAssociableDocumentItemIndex);
	$("#createDocumentTagItemLink" + offenseSectionSummaryAssociableDocumentItemIndex).click(function() {
		documentTagItemsCreateOnClick(offenseSectionSummaryAssociableDocumentItemIndex);
	});
	$("#removeOffenseSectionSummaryAssociableDocumentLink" + offenseSectionSummaryAssociableDocumentItemIndex).click(function() {
		if ($("#offenseSectionSummaryAssociableDocumentOperation" + offenseSectionSummaryAssociableDocumentItemIndex).val() == "UPDATE") {
			$("#offenseSectionSummaryAssociableDocumentOperation" + offenseSectionSummaryAssociableDocumentItemIndex).val("REMOVE");
			$("#offenseSectionSummaryAssociableDocumentItem" + offenseSectionSummaryAssociableDocumentItemIndex).addClass("removeContent");
		} else if($("#offenseSectionSummaryAssociableDocumentOperation" + offenseSectionSummaryAssociableDocumentItemIndex).val() == "REMOVE") {
			$("#offenseSectionSummaryAssociableDocumentOperation" + offenseSectionSummaryAssociableDocumentItemIndex).val("UPDATE");
			$("#offenseSectionSummaryAssociableDocumentItem" +offenseSectionSummaryAssociableDocumentItemIndex).removeClass("removeContent");
		} else {
			$("#offenseSectionSummaryAssociableDocumentItem" + offenseSectionSummaryAssociableDocumentItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(offenseSectionSummaryAssociableDocumentItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[offenseSectionSummaryAssociableDocumentItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				offenseSectionSummaryAssociableDocumentItemIndex: offenseSectionSummaryAssociableDocumentItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + offenseSectionSummaryAssociableDocumentItemIndex).append(data);
				documentTagItemRowOnClick(offenseSectionSummaryAssociableDocumentItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + offenseSectionSummaryAssociableDocumentItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[offenseSectionSummaryAssociableDocumentItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(offenseSectionSummaryAssociableDocumentItemIndex, documentTagItemIndex) {
	$("#removeOffenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#offenseSectionSummaryAssociableDocument" + offenseSectionSummaryAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function applyItemFileExtensionNamer(offenseSectionSummaryAssociableDocumentIndex) {
	var documentData = document.getElementById("offenseSectionSummaryAssociableDocumentItems[" + offenseSectionSummaryAssociableDocumentIndex + "].data");
	var fileExtension = document.getElementById("offenseSectionSummaryAssociableDocumentItems[" + offenseSectionSummaryAssociableDocumentIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
};

function applyFileExtensionNamer() {
	var documentData = document.getElementById("documentData");
	var fileExtension = document.getElementById("dataFileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
}





function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};