
/*
 * Applies action menus to all rows.
 */
function applyRowActionMenus() {
	$(".physicalFeatureAssociationsRowActionMenuLink").each(function() {
		var actionMenuId = $(this).attr("id");
		applyActionMenu(document.getElementById(actionMenuId), applyRemoveLinkConfirmation);
	});
}