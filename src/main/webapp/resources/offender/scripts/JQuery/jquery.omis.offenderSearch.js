var displayPhoto = $("#displayOffenderPhotoRadioClicked");
displayPhoto.click(function() {
	alert("here");
	if (displayPhoto.is(':checked')) {
		$("#offenderPhotoImg").removeClass("hidden");
	} else {
		$("#offenderPhotoImg").addClass("hidden");
	}
});

function applyDisplayPhotoOnClick() {
	alert("display here..");
	var displayYes = $("#displayOffenderPhotoRadioYes");
	var displayNo = $("displayOffenderPhotoRadioNo");
	displayYes.click(function() {
		if (displayYes.is(':checked')) {
			$("#offenderPhotoImg").removeClass("hidden");
			displayYes.is(':checked');
			alert("display hereYes..");
		} else {
			$("#offenderPhotoImg").addClass("hidden");
			displayNo.is(':checked');
			alert("display hereNo..");
		}
	});
	displayNo.click(function() {
		
		if (displayNo.is(':checked')) {
			$("#offenderPhotoImg").removeClass("hidden");
		} else {
			$("#offenderPhotoImg").addClass("hidden");
		}
	});
};