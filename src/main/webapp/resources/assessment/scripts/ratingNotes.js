window.onload = function() {
	applyActionMenu(document.getElementById("ratingNoteItemsActionMenuLink"), ratingNoteItemsCreateOnClick);
	for (var index = 0; index < currentRatingNoteItemIndex; index++) {
		ratingNoteItemRowOnClick(index);
	}
}