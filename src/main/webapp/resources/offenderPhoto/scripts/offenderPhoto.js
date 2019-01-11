/**
 * Offender photo detail screen behavior.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (September 10, 2018)
 * @since OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("photoDate"));
	if(document.getElementById("photoFile")) {
		applyOnImageFileChange(document.getElementById("photoPreviewFieldGroup"), document.getElementById("photoPreview"), document.getElementById("photoFile"), document.getElementById("photoData"), refreshOffenderPhoto, function() { if(allowEnhancedImageEditor) {enhancedImageEditingModalOnClickSetup(document.getElementById("photoPreview"), document.getElementById("photoPreview").src, 960, 1080, refreshOffenderPhoto);}}, 1920, 1080);
	}
	if(document.getElementById("photoPreview").classList.contains("enhancedImageUploadResultImage")) {
		if(allowEnhancedImageEditor) {
			document.getElementById("photoPreview").onload = function() {
				enhancedImageEditingModalOnClickSetup(document.getElementById("photoPreview"), document.getElementById("photoPreview").src, 960, 1080, refreshOffenderPhoto);
			};
		}
	}
	applyFormUpdateChecker(document.getElementById("offenderPhotoForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("offenderPhotoAssociationNoteItemsActionMenuLink"), offenderPhotoAssociationNoteItemsActionMenuOnClick);
	assignOnClick();
};

function refreshOffenderPhoto(data) {
	document.getElementById("photoPreview").setAttribute("src", data);
	document.getElementById("photoData").value = data.replace("data:image/jpeg;base64,", "");
}