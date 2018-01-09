/**
 * Jquery implementation of victim documents javascript
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Aug 31, 2017)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the victim documents action menu.
 */
function applyVictimDocumentsActionMenuOnClick() {
	$("#createVictimDocumentItemLink").click(function() {
		$.ajax({
			type: "GET",
			async: true,
			url: config.ServerConfig.getContextPath() + "/victim/document/createDocumentItem.html",
			data: {victimDocumentItemIndex: currentVictimDocumentItemIndex,
				victim: victimId},
			success: function(data) {
				$("#victimDocumentItemsContainer").append(data);
				victimDocumentItemContentOnClick(currentVictimDocumentItemIndex);
				currentVictimDocumentItemIndex++;
				currentDocumentTagItemIndexes.push(0);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#victimDocumentItemsContainer").html(jqXHR.responseText);
			}
		});
		return false;
	});
}

/**
 * Assigns on click functionality for the victim document item with the specified index.
 * 
 * @param victimDocumentItemIndex victim document item index
 */
function victimDocumentItemContentOnClick(victimDocumentItemIndex) {
	assignDatePicker("documentItems\\[" + victimDocumentItemIndex + "\\]\\.date");
	applyFileExtensionNamer(victimDocumentItemIndex);
	$("#createDocumentTagItemLink" + victimDocumentItemIndex).click(function() {
		documentTagLinkOnClick(victimDocumentItemIndex);
	});
	$("#removeVictimDocumentItemLink" + victimDocumentItemIndex).click(function() {
		
		if ($("#victimDocumentOperation" + victimDocumentItemIndex).val() == "UPDATE") {
			$("#victimDocumentOperation" + victimDocumentItemIndex).val("REMOVE");
			$("#victimDocumentItem" + victimDocumentItemIndex).addClass("removeContent");
		} else if($("#victimDocumentOperation" + victimDocumentItemIndex).val() == "REMOVE") {
			$("#victimDocumentOperation" + victimDocumentItemIndex).val("UPDATE");
			$("#victimDocumentItem" + victimDocumentItemIndex).removeClass("removeContent");
		} else {
			$("#victimDocumentItem" + victimDocumentItemIndex).remove();
		}
		return false;
	});
};

/**
 * Assigns on click functionality for document tag item with the specified  victim document item index.
 * 
 * @param victimDocumentItemIndex victim document item index
 */
function documentTagLinkOnClick(victimDocumentItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[victimDocumentItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/victim/document/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				victimDocumentItemIndex: victimDocumentItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + victimDocumentItemIndex).append(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + victimDocumentItemIndex).html(jqXHR.responseText );
			}
		});

	documentTagItemOnClick(victimDocumentItemIndex, documentTagItemIndex);
	currentDocumentTagItemIndexes[victimDocumentItemIndex]++;
	return false;
};

/**
 * Assigns document tag item on click functionality with the specified victim document item index and document tag item index.
 * 
 * @param victimDocumentItemIndex victim document item index
 * @param documentTagItemIndex document tag item index
 * @returns
 */
function documentTagItemOnClick(victimDocumentItemIndex, documentTagItemIndex) {
	$("#removeVictimDocumentAssociation" + victimDocumentItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#victimDocument" + victimDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#victimDocument" + victimDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#victimDocument" + victimDocumentItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#victimDocument" + victimDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#victimDocument" + victimDocumentItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#victimDocument" + victimDocumentItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#victimDocument" + victimDocumentItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};

/**
 * Applies file extension namer to document data.
 * @param victimDocumentItemIndex victim document item index
 */
function applyFileExtensionNamer(victimDocumentItemIndex) {
	var documentData = document.getElementById("documentItems[" + victimDocumentItemIndex + "].documentData");
	var fileExtension = document.getElementById("documentItems[" + victimDocumentItemIndex + "].fileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	};
};

/**
 * Assigns date picker to the specified element.
 * 
 * @param elementId element id
 */
function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};