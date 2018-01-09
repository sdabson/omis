
function agreementAssociableDocumentItemsCreateOnClick() {
	$("#createAgreementAssociableDocumentItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/paroleBoardCondition/createAgreementAssociableDocumentItem.html",
		   {
				type: "GET",
				async: false,
				data: {
					agreementAssociableDocumentItemIndex: currentAgreementAssociableDocumentItemIndex
				},
				success: function(data) {
					$("#agreementAssociableDocumentItems").append(data);
					agreementAssociableDocumentItemRowOnClick(currentAgreementAssociableDocumentItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#agreementAssociableDocumentItems").html(jqXHR.responseText );
				}
			});
		currentAgreementAssociableDocumentItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function agreementAssociableDocumentItemRowOnClick(agreementAssociableDocumentItemIndex) {
	assignDatePicker("agreementAssociableDocumentItems\\[" + agreementAssociableDocumentItemIndex + "\\]\\.date");
	applyFileExtensionNamer(agreementAssociableDocumentItemIndex);
	$("#createDocumentTagItemLink" + agreementAssociableDocumentItemIndex).click(function() {
		documentTagItemsCreateOnClick(agreementAssociableDocumentItemIndex);
	});
	$("#removeAgreementAssociableDocumentLink" + agreementAssociableDocumentItemIndex).click(function() {
		if ($("#agreementAssociableDocumentOperation" + agreementAssociableDocumentItemIndex).val() == "UPDATE") {
			$("#agreementAssociableDocumentOperation" + agreementAssociableDocumentItemIndex).val("REMOVE");
			$("#agreementAssociableDocumentItem" + agreementAssociableDocumentItemIndex).addClass("removeContent");
		} else if($("#agreementAssociableDocumentOperation" + agreementAssociableDocumentItemIndex).val() == "REMOVE") {
			$("#agreementAssociableDocumentOperation" + agreementAssociableDocumentItemIndex).val("UPDATE");
			$("#agreementAssociableDocumentItem" +agreementAssociableDocumentItemIndex).removeClass("removeContent");
		} else {
			$("#agreementAssociableDocumentItem" + agreementAssociableDocumentItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(agreementAssociableDocumentItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[agreementAssociableDocumentItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/paroleBoardCondition/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				agreementAssociableDocumentItemIndex: agreementAssociableDocumentItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + agreementAssociableDocumentItemIndex).append(data);
				documentTagItemRowOnClick(agreementAssociableDocumentItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + agreementAssociableDocumentItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[agreementAssociableDocumentItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(agreementAssociableDocumentItemIndex, documentTagItemIndex) {
	$("#removeAgreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#agreementAssociableDocument" + agreementAssociableDocumentItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};


function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

function applyFileExtensionNamer(agreementAssociableDocumentIndex) {
	var documentData = document.getElementById("agreementAssociableDocumentItems[" + agreementAssociableDocumentIndex + "].data");
	var fileExtension = document.getElementById("agreementAssociableDocumentItems[" + agreementAssociableDocumentIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
};
