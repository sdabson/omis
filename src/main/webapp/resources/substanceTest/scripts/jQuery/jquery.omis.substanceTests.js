function applySubstanceTestsOnClick() {
	$(".rowActionMenuLink").each(function() {
		var linkId = $(this).attr("id");
		applyActionMenu(document.getElementById(linkId), function() {
			applyRemoveLinkConfirmation();
		});
	});
}