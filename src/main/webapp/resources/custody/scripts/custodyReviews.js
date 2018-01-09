$(document).ready(function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLink");
	for (var count = 0; count < rowActionMenus.length; count++) {
		applyActionMenu(document.getElementById("summaryActionMenuLink" + count));
	}
});