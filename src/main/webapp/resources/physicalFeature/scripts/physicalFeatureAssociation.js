function assignPhysicalFeatureAssociationOnClick() {
	$("#originationDate").attr("autocomplete", "off");
	$("#originationDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	var featureClassification = $("#featureClassification");
	featureClassification.change(function() {
		featureClassificationEvent(featureClassification.val());
		return false;
	});
	for (var offenderPhysicalFeaturePhotoIndex = 0; offenderPhysicalFeaturePhotoIndex < currentPhysicalFeaturePhotoIndex; offenderPhysicalFeaturePhotoIndex++) {
		applyPhysicalFeaturePhotoTableRowLinkBehavior(offenderPhysicalFeaturePhotoIndex);
	}
	for (var physicalFeatureAssociationNoteItemIndex = 0; physicalFeatureAssociationNoteItemIndex < currentPhysicalFeatureAssociationNoteItemIndex; physicalFeatureAssociationNoteItemIndex++) {
		physicalFeatureAssociationNoteItemRowOnClick(physicalFeatureAssociationNoteItemIndex);
	}
}

/*
 * Retrieves a new set of values via AJAX for the "feature" drop down menu
 * and redisplays that drop down to show only those features with the 
 * currently selected feature classification.
 */
function featureClassificationEvent(featureClassification) {
	$.ajax(config.ServerConfig.getContextPath() + "/physicalFeature/featureClassificationEvent.html",
			{
				type: "GET",
				async: false,
				data: { featureClassification: featureClassification},
				success: function(data) {
					$("#featureContainer").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
					$("#offenderPhysicalFeaturePhotos").html(jqXHR.responseText );
				}
			});
}

/*
 * Apply the common behavior for the physical feature photo table row with the 
 * specified index number.
 * @param index index of the physical feature photo table row
 */
function applyPhysicalFeaturePhotoTableRowLinkBehavior(index) {
	var physicalFeaturePhotoTableRow = $("#physicalFeaturePhotoRow" + index);
	$(".dateSelect").attr("autocomplete", "off");
	$(".dateSelect").datepicker({
		changeMonth: true,
		changeYear: true
	});
	if ($("#physicalFeaturePhotoAssociationOperation" + index).val() == "CREATE") {
		applyImagePreview(document.getElementById("photoPreviewFieldGroup" + index),
				document.getElementById("photoPreview" + index),
				document.getElementById("photoItems" + index + "PhotoData"));
	}
	$("#removePhysicalFeaturePhoto" + index).click(function() {
		if ($("#physicalFeaturePhotoAssociationOperation" + index).val() == "UPDATE") {
			$("#physicalFeaturePhotoAssociationOperation" + index).val("REMOVE");
			$("#physicalFeaturePhotoRow" + index).addClass("removeRow");
		} else if($("#physicalFeaturePhotoAssociationOperation" + index).val() == "REMOVE") {
			$("#physicalFeaturePhotoAssociationOperation" + index).val("UPDATE");
			$("#physicalFeaturePhotoRow" + index).removeClass("removeRow");
		} else {
			$("#physicalFeaturePhotoRow" + index).remove();
		}
		return false;
	});	
}

/*
 * Adds a physical feature photo table row with the specified file path as the
 * source for a photo that will show in a preview/thumb nail.
 * 
 * @param filePath the file path of the selected photo.
 */
function addPhotoItem(currentIndex) {
		$.ajax(config.ServerConfig.getContextPath() + "/physicalFeature/addPhysicalFeaturePhoto.html",
				{
					type: "GET",
					async: false,
					data: { offenderPhysicalFeaturePhotoIndex: currentIndex},
					success: function(data) {
						$("#offenderPhysicalFeaturePhotos").append(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
						$("#offenderPhysicalFeaturePhotos").html(jqXHR.responseText );
					}
				});
		var filePath = $("#photoItems" + currentIndex + ".photoData").val();
		applyPhysicalFeaturePhotoTableRowLinkBehavior(currentPhysicalFeaturePhotoIndex, filePath);
		currentPhysicalFeaturePhotoIndex++;
		return false;
}
	
/*
 * Function to check that the selected file is indeed of the desired extension,
 * and is not null. If the file extension is found to be valid, allow the 
 * addPhotoItem() function. Otherwise show a dialog prompt.
 *  
 * @param selector the file selector input
 * @param extension a string representation of the desired file extension
 * @param currentIndex a number representation of the current row index
 */
function fileSelectedChanged(selector, extension, currentIndex) {
    var filePath = selector.val();
    if (filePath == null) {
		alert('Please select a ' + extension + ' image to add.');
    } else {
    	var ext = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
	    if(ext != extension) {
	        alert('Only files with the file extension ' + extension + ' are allowed');
	    } else {
    		addPhotoItem(currentIndex, filePath);
	    }
    }
}

/*
 * Limits the amount of characters that are able to be entered into the 
 * specified text area, and adjusts the value of a "counter" to reflect the 
 * amount of characters that remain for use in the field.
 * 
 * @param textAreaId the id of the text area to limit character use
 * @param counterId the id of the field to show remaining characters
 * @param limitNum the amount of characters that are allowed
 */
function limitTextAreaCharacters(textAreaId, counterId, limitNum) {
	if (textAreaId.value.length > limitNum) {
		textAreaId.value = textAreaId.value.substring(0, limitNum);
	} else {
		counterId.value = limitNum - textAreaId.value.length;
	}
}

/*
 * Applies on click functionality for items contained within the physical
 * feature association note items action menu. 
 */
function applyPhysicalFeatureAssociationNoteItemsActionMenuOnClick() {
	$("#createPhysicalFeatureAssociationNoteItemLink").click(function () {
		$.ajax(config.ServerConfig.getContextPath() + "/physicalFeature/createPhysicalFeatureAssociationNoteItem.html",
				   {
						type: "GET",
						async: false,
						data: {currentPhysicalFeatureAssociationNoteItemIndex: currentPhysicalFeatureAssociationNoteItemIndex},
						success: function(data) {
							$("#physicalFeatureAssociationNotesTableBody").append(data);
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#physicalFeatureAssociationNotesTableBody").html(jqXHR.responseText );
						}
					});
		physicalFeatureAssociationNoteItemRowOnClick(currentPhysicalFeatureAssociationNoteItemIndex);
		currentPhysicalFeatureAssociationNoteItemIndex++;
		return false;
	});
}

/*
 * Assign on click functionality for the physical feature association note item
 * row with the specified index.
 * 
 * @param index physical feature association note item index
 */
function physicalFeatureAssociationNoteItemRowOnClick(index) {
	$("#physicalFeatureAssociationNoteItemDate" + index).attr("autocomplete", "off");
	$("#physicalFeatureAssociationNoteItemDate" + index).datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#removeNoteLink" + index).click(function() {
		if ($("#physicalFeatureAssociationNoteOperation" + index).val() == "UPDATE") {
			$("#physicalFeatureAssociationNoteOperation" + index).val("REMOVE");
			$("#physicalFeatureAssociationNoteItemRow" + index).addClass("removeRow");
		} else if($("#physicalFeatureAssociationNoteOperation" + index).val() == "REMOVE") {
			$("#physicalFeatureAssociationNoteOperation" + index).val("UPDATE");
			$("#physicalFeatureAssociationNoteItemRow" + index).removeClass("removeRow");
		} else {
			$("#physicalFeatureAssociationNoteItemRow" + index).remove();
		}
		return false;
	});	
}

function applyPhysicalFeatureAssociationPhotoItemsActionMenuLinkOnClick() {
	$("#addPhotoItem").click(function(){
		addPhotoItem(currentPhysicalFeaturePhotoIndex);
		return false;
	});
}

/*
 * Function to run when the screen is first loaded.
 */
$(document).ready(function() {
	assignPhysicalFeatureAssociationOnClick();
	applyFormUpdateChecker(document.getElementById("physicalFeatureAssociationForm"));
	applyActionMenu(document.getElementById("physicalFeatureAssociationNoteItemsActionMenuLink"), applyPhysicalFeatureAssociationNoteItemsActionMenuOnClick);
	applyActionMenu(document.getElementById("physicalFeatureAssociationActionMenuLink"));
	applyActionMenu(document.getElementById("physicalFeatureAssociationPhotoItemsActionMenuLink"), applyPhysicalFeatureAssociationPhotoItemsActionMenuLinkOnClick);
});