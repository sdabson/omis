function financialDocumentAssociationItemsCreateOnClick() {
	$("#createFinancialDocumentAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/financial/createFinancialDocumentAssociation.html",
		   {
				type: "GET",
				async: false,
				data: {financialDocumentAssociationItemIndex: financialDocumentAssociationItemIndex},
				success: function(data) {
					$("#financialDocumentAssociationItems").append(data);
					financialDocumentAssociationItemRowOnClick(financialDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#financialDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		financialDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function financialDocumentAssociationItemRowOnClick(financialDocumentAssociationItemIndex) {
	assignDatePicker("financialDocumentAssociationItems\\["+financialDocumentAssociationItemIndex+"\\]\\.date");
	applyFileExtensionNamer(financialDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + financialDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(financialDocumentAssociationItemIndex);
	});
	$("#removeFinancialDocumentAssociationLink" + financialDocumentAssociationItemIndex).click(function() {
		
		if ($("#financialDocumentAssociationOperation" + financialDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#financialDocumentAssociationOperation" + financialDocumentAssociationItemIndex).val("REMOVE");
			$("#financialDocumentAssociationItem" + financialDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#financialDocumentAssociationOperation" + financialDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#financialDocumentAssociationOperation" + financialDocumentAssociationItemIndex).val("UPDATE");
			$("#financialDocumentAssociationItem" + financialDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#financialDocumentAssociationItem" + financialDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(financialDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[financialDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/financial/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				financialDocumentAssociationItemIndex: financialDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + financialDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(financialDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + financialDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[financialDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(financialDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeFinancialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#financialDocumentAssociation" + financialDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};

function applyFileExtensionNamer(financialDocumentAssociationIndex) {

	var documentData = document.getElementById("financialDocumentAssociationItems[" + financialDocumentAssociationIndex + "].data");
	var fileExtension = document.getElementById("financialDocumentAssociationItems[" + financialDocumentAssociationIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	};
};

function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};