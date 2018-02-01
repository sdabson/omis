/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * Unit manager review detail screen jquery behavior.
 * 
 * Author: Josh Divine
 * Version: 0.1.0 Jan 30, 2018
 * Since: OMIS 3.0
 */
function unitManagerReviewDocumentAssociationItemsCreateOnClick() {
	$("#createUnitManagerReviewDocumentAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/unitManagerReview/createUnitManagerReviewDocumentAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {documentAssociationItemIndex: currentDocumentAssociationItemIndex},
				success: function(data) {
					$("#unitManagerReviewDocumentAssociationItems").append(data);
					unitManagerReviewDocumentAssociationItemRowOnClick(currentDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#unitManagerReviewDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		currentDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function unitManagerReviewDocumentAssociationItemRowOnClick(unitManagerReviewDocumentAssociationItemIndex) {
	assignDatePicker("unitManagerReviewDocumentAssociationItems\\["+unitManagerReviewDocumentAssociationItemIndex+"\\]\\.fileDate");
	applyFileExtensionNamer(unitManagerReviewDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + unitManagerReviewDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(unitManagerReviewDocumentAssociationItemIndex);
	});
	$("#removeUnitManagerReviewDocumentAssociationLink" + unitManagerReviewDocumentAssociationItemIndex).click(function() {
		if ($("#unitManagerReviewDocumentAssociationOperation" + unitManagerReviewDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#unitManagerReviewDocumentAssociationOperation" + unitManagerReviewDocumentAssociationItemIndex).val("REMOVE");
			$("#unitManagerReviewDocumentAssociationItem" + unitManagerReviewDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#unitManagerReviewDocumentAssociationOperation" + unitManagerReviewDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#unitManagerReviewDocumentAssociationOperation" + unitManagerReviewDocumentAssociationItemIndex).val("UPDATE");
			$("#unitManagerReviewDocumentAssociationItem" +unitManagerReviewDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#unitManagerReviewDocumentAssociationItem" + unitManagerReviewDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};

function documentTagItemsCreateOnClick(unitManagerReviewDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[unitManagerReviewDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/unitManagerReview/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				documentAssociationItemIndex: unitManagerReviewDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + unitManagerReviewDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(unitManagerReviewDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + unitManagerReviewDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[unitManagerReviewDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(unitManagerReviewDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeUnitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#unitManagerReviewDocumentAssociation" + unitManagerReviewDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};

function applyFileExtensionNamer(unitManagerReviewDocumentAssociationIndex) {
	var documentData = document.getElementById("unitManagerReviewDocumentAssociationItems[" + unitManagerReviewDocumentAssociationIndex + "].data");
	var fileExtension = document.getElementById("unitManagerReviewDocumentAssociationItems[" + unitManagerReviewDocumentAssociationIndex + "].fileExtension");
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