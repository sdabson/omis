/**
 * Offender photo join screen behavior.
 * 
 * @author Joel Norris
 * @version 0.1.1 (November 23, 2018)
 * @since OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("photoDate"));
	applyFormUpdateChecker(document.getElementById("offenderPhotoJoinForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("offenderPhotoAssociationNoteItemsActionMenuLink"), offenderPhotoAssociationNoteItemsActionMenuOnClick);
	let photoItems = document.getElementsByClassName("photoItem");
	let noteItems = document.getElementsByClassName("offenderPhotoAssociationNoteItemRow");
	
	applyImageJoin(2, document.getElementById("photoPreview"), document.getElementById("photoData"));
	
	for(i = 0; i < photoItems.length; i++) {
		applyPhotoItemOnClick(i);
		//only one of each left/right input should be checked on load (if any)
		if(document.getElementById("leftImage" + i).checked) {
			enhancedImageEditingModalOnClickSetup(document.getElementById("clickableImageArea0"), document.getElementById("photoItemThumbnail"+i).src, 960, 1080, updateLeftSide);
		}
		if(document.getElementById("rightImage" + i).checked) {
			enhancedImageEditingModalOnClickSetup(document.getElementById("clickableImageArea1"), document.getElementById("photoItemThumbnail"+i).src, 960, 1080, updateRightSide);
		}
	}
	for(i = 0; i < currentOffenderPhotoAssociationNoteItemIndex; i++) {
		if(document.body.contains(document.getElementById("offenderPhotoAssociationNoteItemRow" + i))) {
			offenderPhotoAssociationNoteItemRowOnClick(i);
		}
	}
};

/*
 * Refresh the preview photo, and photo data.
 * 
 * @param data data to refresh
 */
function refreshOffenderPhoto(data) {
	document.getElementById("photoPreview").setAttribute("src", data);
	document.getElementById("photoData").value = data.replace("data:image/jpeg;base64,", "");
}

/*
 * Applies photo item on click functionality for the photo item with
 * the specified index.
 * 
 * @param index photo item index
 */
function applyPhotoItemOnClick(index) {
	let leftImageRadio = document.getElementById("leftImage" + index);
	leftImageRadio.onclick = function() {
		if(leftImageRadio.checked) {
			updateLeftSide(document.getElementById("photoItemThumbnail"+index).src);
			enhancedImageEditingModalOnClickSetup(document.getElementById("clickableImageArea0"), document.getElementById("photoItemThumbnail"+index).src, 960, 1080, updateLeftSide);
			assignPhotoPreviewMouseOver(document.getElementById("photoPreview"), document.getElementsByClassName("photoJoinPreviewHoverArea"));
		}
	}
	let rightImageRadio = document.getElementById("rightImage" + index);
	rightImageRadio.onclick = function() {
		if(rightImageRadio.checked) {
			updateRightSide(document.getElementById("photoItemThumbnail"+index).src);
			enhancedImageEditingModalOnClickSetup(document.getElementById("clickableImageArea1"), document.getElementById("photoItemThumbnail"+index).src, 960, 1080, updateRightSide);
			assignPhotoPreviewMouseOver(document.getElementById("photoPreview"), document.getElementsByClassName("photoJoinPreviewHoverArea"));
		}
	}
}

function updateLeftSide(data) {
	updatePreviewSection(data, document.getElementById("photoPreview"), 0, 960, 1920, 1080, refreshOffenderPhoto);
}

function updateRightSide(data) {
	updatePreviewSection(data, document.getElementById("photoPreview"), 1, 960, 1920, 1080, refreshOffenderPhoto);
}

/*
 * Assigns on click functionality for the service term note items action menu.
 */
function offenderPhotoAssociationNoteItemsActionMenuOnClick() {
	document.getElementById("createOffenderPhotoAssociationNoteItemLink").onclick = function() {
		var request = new XMLHttpRequest();
		var url = config.ServerConfig.getContextPath() + "/offenderPhoto/createOffenderPhotoAssociationNoteItem.html?offenderPhotoAssociationNoteItemIndex=" + currentOffenderPhotoAssociationNoteItemIndex;
		request.open('GET', url, false);
		request.send();
		if (request.status == 200) {
			var data = request.responseText;
			document.getElementById("offenderPhotoAssociationNotesTableBody").insertAdjacentHTML("afterend", data);
			offenderPhotoAssociationNoteItemRowOnClick(currentOffenderPhotoAssociationNoteItemIndex);
		  } else {
			document.getElementById("offenderPhotoAssociationNotesTableBody").innerHTML= request.responseText;
			alert("Error - status: " + request.status + "; status text:" + request.statusText + "; URL: " + url);
		}
		currentOffenderPhotoAssociationNoteItemIndex++;
		return false;
	};
};

/*
 * Assigns on click functionality for the service term note item row with the
 * specified service term note item index.
 * 
 * @param offenderPhotoAssociationNoteItemIndex service term note item index
 */
function offenderPhotoAssociationNoteItemRowOnClick(offenderPhotoAssociationNoteItemIndex) {
	applyDatePicker(document.getElementById("offenderPhotoAssociationNoteItemDate" + offenderPhotoAssociationNoteItemIndex));
	applyTextCounter(document.getElementById("offenderPhotoAssociationNoteItems[" + offenderPhotoAssociationNoteItemIndex + "].value"), document.getElementById("valueCharacterCounter" + offenderPhotoAssociationNoteItemIndex));
	document.getElementById("removeLink" + offenderPhotoAssociationNoteItemIndex).onclick = function() {
		if (document.getElementById("offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).value == "UPDATE") {
			document.getElementById("offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).value = "REMOVE";
			document.getElementById("offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).classList.add("removeRow");
		} else if(document.getElementById("offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).value == "REMOVE") {
			document.getElementById("offenderPhotoAssociationNoteOperation" + offenderPhotoAssociationNoteItemIndex).value = "UPDATE";
			document.getElementById("offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).classList.remove("removeRow");
		} else {
			document.getElementById("offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex).parentNode.removeChild(document.getElementById("offenderPhotoAssociationNoteItemRow" + offenderPhotoAssociationNoteItemIndex));
		}
		return false;
	};
}
