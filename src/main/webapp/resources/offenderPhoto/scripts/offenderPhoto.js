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
		applyOnImageFileChange(
				document.getElementById("photoPreviewFieldGroup"), document.getElementById("photoPreview"),
				document.getElementById("photoFile"), document.getElementById("photoData"),
				refreshOffenderPhoto, function() { 
						assignSingleImageEdit(document.getElementById("photoPreview"), refreshOffenderPhoto, 640, 480);
				},
				640, 480);
	}
	if(document.getElementById("photoPreview").classList.contains("enhancedImageUploadResultImage")) {
			document.getElementById("photoPreview").onload = function() {
				assignSingleImageEdit(document.getElementById("photoPreview"), refreshOffenderPhoto, 640, 480);
			};
	}
	if(document.getElementById("photoData") && document.getElementById("photoData").value != "") {
		assignSingleImageEdit(document.getElementById("photoPreview"), refreshOffenderPhoto, 640, 480);
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