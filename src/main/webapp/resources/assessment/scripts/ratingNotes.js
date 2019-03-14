window.onload = function() {
	applyActionMenu(document.getElementById("ratingNoteItemsActionMenuLink"), ratingNoteItemsCreateOnClick);
	applyActionMenu(document.getElementById("actionMenuLink"));
	for (var index = 0; index < currentRatingNoteItemIndex; index++) {
		ratingNoteItemRowOnClick(index);
	}
}