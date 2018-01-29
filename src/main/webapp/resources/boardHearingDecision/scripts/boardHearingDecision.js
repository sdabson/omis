window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("hearingDecisionNoteItemsActionMenuLink"), hearingDecisionNotesCreateOnClick);
	for (var index = 0; index < currentHearingDecisionNoteIndex; index++) {
		hearingDecisionNoteItemRowOnClick(index);
	}
	var memberRows = document.getElementsByClassName("boardMemberDecisionItemRow");
	for(var i = 0; i < memberRows.length; i++) {
		categoryOnChange(memberRows[i].getAttribute("id").replace("boardMemberDecisionItemRow", ""));
	}
	updateBoardHearingCategory();
}