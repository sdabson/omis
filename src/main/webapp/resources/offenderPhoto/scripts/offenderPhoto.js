/**
 * Offender photo detail screen behavior.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 27, 2013)
 * @since OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("photoDate"));
	applyImagePreview(document.getElementById("photoPreviewFieldGroup"),
			document.getElementById("photoPreview"),
			document.getElementById("photoData"));
	applyFormUpdateChecker(document.getElementById("offenderPhotoForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("offenderPhotoAssociationNoteItemsActionMenuLink"), offenderPhotoAssociationNoteItemsActionMenuOnClick);
	assignOnClick();
};