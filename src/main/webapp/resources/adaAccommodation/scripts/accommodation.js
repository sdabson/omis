/**
 * Accommodation detail screen behavior.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("accommodationNoteActionMenuLink"), createAccommodationNotesActionMenuOnClick);
	applyDatePicker(document.getElementById("endDate"));	
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("authorizationDate"));	
	//applyFormUpdateChecker(document.getElementById("accommodationForm"));
	applyDisableOnSubmit(document.getElementById("accommodationForm"));
	applyUserIDSearch(document.getElementById("authorizationSignature"),
			document.getElementById("authorizationUser"), 
			document.getElementById("authorizedCurrentLabel"),
			document.getElementById("authorizedByUser"),
			document.getElementById("clearAuthorizedByUserLink"));
	assignOnClick();
	
	var temporaryAuthorizationSelect = document.getElementById("temporaryAuthorization");
	temporaryAuthorizationSelect.addEventListener("change", function(e) {
		var endDate = document.getElementById("endDate");
		var endDateGroup = document.getElementById("endDateGroup");
		var value = e.target.value;
		if (value == "true") {
			endDateGroup.className = endDateGroup.className.replace(/ hidden/,'');
		} else {
			endDateGroup.className += " hidden";
			endDate.value="";
		}
	});
};