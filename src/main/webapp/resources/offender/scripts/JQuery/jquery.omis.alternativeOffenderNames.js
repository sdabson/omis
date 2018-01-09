function applyAlternativeNamesRowOnClick() {
	$(".alternativeNameRow > td > .actionMenuItem").each(function () {
		applyActionMenu(document.getElementById($(this).attr("id")));
	});
}