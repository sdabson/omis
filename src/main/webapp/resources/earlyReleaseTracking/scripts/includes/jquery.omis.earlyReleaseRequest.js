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
function earlyReleaseRequestNoteAssociationItemsCreateOnClick() {
	$("#createEarlyReleaseRequestNoteAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/earlyReleaseTracking/createEarlyReleaseRequestNoteAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {earlyReleaseRequestNoteAssociationItemIndex: currentEarlyReleaseRequestNoteAssociationItemIndex},
				success: function(data) {
					$("#earlyReleaseRequestNoteAssociationTableBody").append(data);
					earlyReleaseRequestNoteAssociationItemRowOnClick(currentEarlyReleaseRequestNoteAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#earlyReleaseRequestNoteAssociationTableBody").html(jqXHR.responseText );
				}
			});
		currentEarlyReleaseRequestNoteAssociationItemIndex++;
		return false;
	});
};

function earlyReleaseRequestNoteAssociationItemRowOnClick(earlyReleaseRequestNoteAssociationItemIndex) {
	assignDatePicker("earlyReleaseRequestNoteAssociationItemDate" + earlyReleaseRequestNoteAssociationItemIndex);
	$("#removeEarlyReleaseRequestNoteAssociationLink" + earlyReleaseRequestNoteAssociationItemIndex).click(function() {
		if ($("#earlyReleaseRequestNoteAssociationOperation" + earlyReleaseRequestNoteAssociationItemIndex).val() == "UPDATE") {
			$("#earlyReleaseRequestNoteAssociationOperation" + earlyReleaseRequestNoteAssociationItemIndex).val("REMOVE");
			$("#earlyReleaseRequestNoteAssociationItemRow" + earlyReleaseRequestNoteAssociationItemIndex).addClass("removeRow");
		} else if($("#earlyReleaseRequestNoteAssociationOperation" + earlyReleaseRequestNoteAssociationItemIndex).val() == "REMOVE") {
			$("#earlyReleaseRequestNoteAssociationOperation" + earlyReleaseRequestNoteAssociationItemIndex).val("UPDATE");
			$("#earlyReleaseRequestNoteAssociationItemRow" +earlyReleaseRequestNoteAssociationItemIndex).removeClass("removeRow");
		} else {
			$("#earlyReleaseRequestNoteAssociationItemRow" + earlyReleaseRequestNoteAssociationItemIndex).remove();
		}
		return false;
	});
};


function earlyReleaseRequestDocumentAssociationItemsCreateOnClick() {
	$("#createEarlyReleaseRequestDocumentAssociationItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/earlyReleaseTracking/createEarlyReleaseRequestDocumentAssociationItem.html",
		   {
				type: "GET",
				async: false,
				data: {
					earlyReleaseRequestDocumentAssociationItemIndex: currentEarlyReleaseRequestDocumentAssociationItemIndex
				},
				success: function(data) {
					$("#earlyReleaseRequestDocumentAssociationItems").append(data);
					earlyReleaseRequestDocumentAssociationItemRowOnClick(currentEarlyReleaseRequestDocumentAssociationItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#earlyReleaseRequestDocumentAssociationItems").html(jqXHR.responseText );
				}
			});
		currentEarlyReleaseRequestDocumentAssociationItemIndex++;
		currentDocumentTagItemIndexes.push(0);
		return false;
	});
};

function earlyReleaseRequestDocumentAssociationItemRowOnClick(earlyReleaseRequestDocumentAssociationItemIndex) {
	//applyAddTagLink();
	//applyRemoveTagLinks();
	assignDatePicker("earlyReleaseRequestDocumentAssociationItems\\[" + earlyReleaseRequestDocumentAssociationItemIndex + "\\]\\.date");
	applyFileExtensionNamer(earlyReleaseRequestDocumentAssociationItemIndex);
	$("#createDocumentTagItemLink" + earlyReleaseRequestDocumentAssociationItemIndex).click(function() {
		documentTagItemsCreateOnClick(earlyReleaseRequestDocumentAssociationItemIndex);
	});
	$("#removeEarlyReleaseRequestDocumentAssociationLink" + earlyReleaseRequestDocumentAssociationItemIndex).click(function() {
		if ($("#earlyReleaseRequestDocumentAssociationOperation" + earlyReleaseRequestDocumentAssociationItemIndex).val() == "UPDATE") {
			$("#earlyReleaseRequestDocumentAssociationOperation" + earlyReleaseRequestDocumentAssociationItemIndex).val("REMOVE");
			$("#earlyReleaseRequestDocumentAssociationItem" + earlyReleaseRequestDocumentAssociationItemIndex).addClass("removeContent");
		} else if($("#earlyReleaseRequestDocumentAssociationOperation" + earlyReleaseRequestDocumentAssociationItemIndex).val() == "REMOVE") {
			$("#earlyReleaseRequestDocumentAssociationOperation" + earlyReleaseRequestDocumentAssociationItemIndex).val("UPDATE");
			$("#earlyReleaseRequestDocumentAssociationItem" +earlyReleaseRequestDocumentAssociationItemIndex).removeClass("removeContent");
		} else {
			$("#earlyReleaseRequestDocumentAssociationItem" + earlyReleaseRequestDocumentAssociationItemIndex).remove();
		}
		return false;
	});
};


function documentTagItemsCreateOnClick(earlyReleaseRequestDocumentAssociationItemIndex) {
	var documentTagItemIndex = currentDocumentTagItemIndexes[earlyReleaseRequestDocumentAssociationItemIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/earlyReleaseTracking/createDocumentTagItem.html",
	   {
			type: "GET",
			async: false,
			data: {
				earlyReleaseRequestDocumentAssociationItemIndex: earlyReleaseRequestDocumentAssociationItemIndex,
				documentTagItemIndex: documentTagItemIndex},
			success: function(data) {
				$("#documentTagItems" + earlyReleaseRequestDocumentAssociationItemIndex).append(data);
				documentTagItemRowOnClick(earlyReleaseRequestDocumentAssociationItemIndex, documentTagItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#documentTagItems" + earlyReleaseRequestDocumentAssociationItemIndex).html(jqXHR.responseText );
			}
		});
	currentDocumentTagItemIndexes[earlyReleaseRequestDocumentAssociationItemIndex]++;
	return false;
};

function documentTagItemRowOnClick(earlyReleaseRequestDocumentAssociationItemIndex, documentTagItemIndex) {
	$("#removeEarlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagLink" + documentTagItemIndex).click(function() {
		if ($("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "UPDATE") {
			$("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("REMOVE");
			$("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).addClass("removeContent");
		} else if($("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val() == "REMOVE") {
			$("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagOperation" + documentTagItemIndex).val("UPDATE");
			$("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagItem" +documentTagItemIndex).removeClass("removeContent");
		} else {
			$("#earlyReleaseRequestDocumentAssociation" + earlyReleaseRequestDocumentAssociationItemIndex + "documentTagItem" + documentTagItemIndex).remove();
		}
		return false;
	});
};

function earlyReleaseRequestInternalApprovalItemsCreateOnClick() {
	$("#createEarlyReleaseRequestInternalApprovalItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/earlyReleaseTracking/createEarlyReleaseRequestInternalApprovalItem.html",
		   {
				type: "GET",
				async: false,
				data: {earlyReleaseRequestInternalApprovalItemIndex: currentEarlyReleaseRequestInternalApprovalItemIndex},
				success: function(data) {
					$("#earlyReleaseRequestInternalApprovalItemTableBody").append(data);
					earlyReleaseRequestInternalApprovalItemRowOnClick(currentEarlyReleaseRequestInternalApprovalItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#earlyReleaseRequestInternalApprovalItemTableBody").html(jqXHR.responseText );
				}
			});
		currentEarlyReleaseRequestInternalApprovalItemIndex++;
		return false;
	});
};

function earlyReleaseRequestInternalApprovalItemRowOnClick(earlyReleaseRequestInternalApprovalItemIndex) {
	assignDatePicker("earlyReleaseRequestInternalApprovalItems\\[" + earlyReleaseRequestInternalApprovalItemIndex + "\\]\\.date");
	$("#removeEarlyReleaseRequestInternalApprovalLink" + earlyReleaseRequestInternalApprovalItemIndex).click(function() {
		if ($("#earlyReleaseRequestInternalApprovalOperation" + earlyReleaseRequestInternalApprovalItemIndex).val() == "UPDATE") {
			$("#earlyReleaseRequestInternalApprovalOperation" + earlyReleaseRequestInternalApprovalItemIndex).val("REMOVE");
			$("#earlyReleaseRequestInternalApprovalItemRow" + earlyReleaseRequestInternalApprovalItemIndex).addClass("removeRow");
		} else if($("#earlyReleaseRequestInternalApprovalOperation" + earlyReleaseRequestInternalApprovalItemIndex).val() == "REMOVE") {
			$("#earlyReleaseRequestInternalApprovalOperation" + earlyReleaseRequestInternalApprovalItemIndex).val("UPDATE");
			$("#earlyReleaseRequestInternalApprovalItemRow" +earlyReleaseRequestInternalApprovalItemIndex).removeClass("removeRow");
		} else {
			$("#earlyReleaseRequestInternalApprovalItemRow" + earlyReleaseRequestInternalApprovalItemIndex).remove();
		}
		return false;
	});
};

function earlyReleaseRequestExternalOppositionItemsCreateOnClick() {
	$("#createEarlyReleaseRequestExternalOppositionItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/earlyReleaseTracking/createEarlyReleaseRequestExternalOppositionItem.html",
		   {
				type: "GET",
				async: false,
				data: {earlyReleaseRequestExternalOppositionItemIndex: currentEarlyReleaseRequestExternalOppositionItemIndex},
				success: function(data) {
					$("#earlyReleaseRequestExternalOppositionItemTableBody").append(data);
					earlyReleaseRequestExternalOppositionItemRowOnClick(currentEarlyReleaseRequestExternalOppositionItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#earlyReleaseRequestExternalOppositionItemTableBody").html(jqXHR.responseText );
				}
			});
		currentEarlyReleaseRequestExternalOppositionItemIndex++;
		return false;
	});
};

function earlyReleaseRequestExternalOppositionItemRowOnClick(earlyReleaseRequestExternalOppositionItemIndex) {
	assignDatePicker("earlyReleaseRequestExternalOppositionItems\\[" + earlyReleaseRequestExternalOppositionItemIndex + "\\]\\.date");
	$("#removeEarlyReleaseRequestExternalOppositionLink" + earlyReleaseRequestExternalOppositionItemIndex).click(function() {
		if ($("#earlyReleaseRequestExternalOppositionOperation" + earlyReleaseRequestExternalOppositionItemIndex).val() == "UPDATE") {
			$("#earlyReleaseRequestExternalOppositionOperation" + earlyReleaseRequestExternalOppositionItemIndex).val("REMOVE");
			$("#earlyReleaseRequestExternalOppositionItemRow" + earlyReleaseRequestExternalOppositionItemIndex).addClass("removeRow");
		} else if($("#earlyReleaseRequestExternalOppositionOperation" + earlyReleaseRequestExternalOppositionItemIndex).val() == "REMOVE") {
			$("#earlyReleaseRequestExternalOppositionOperation" + earlyReleaseRequestExternalOppositionItemIndex).val("UPDATE");
			$("#earlyReleaseRequestExternalOppositionItemRow" +earlyReleaseRequestExternalOppositionItemIndex).removeClass("removeRow");
		} else {
			$("#earlyReleaseRequestExternalOppositionItemRow" + earlyReleaseRequestExternalOppositionItemIndex).remove();
		}
		return false;
	});
};

function applyFileExtensionNamer(earlyReleaseRequestDocumentAssociationIndex) {
	var documentData = document.getElementById("earlyReleaseRequestDocumentAssociationItems[" + earlyReleaseRequestDocumentAssociationIndex + "].data");
	var fileExtension = document.getElementById("earlyReleaseRequestDocumentAssociationItems[" + earlyReleaseRequestDocumentAssociationIndex + "].fileExtension");
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