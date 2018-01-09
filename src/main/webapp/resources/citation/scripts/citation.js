/**
 * Citation detail screen behavior.
 * 
 * Author: Trevor Isles
 * Version: 0.1.0 Aug 23, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("date"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("citationForm"));
	document.getElementById("state").onchange = function() {
		populateCities();
	};

	$("#createNewOffense").click(createNewOffenseClick);
	createNewOffenseClick();
	
	$("#partialDate").change(function() {
		if ($("#partialDate").prop('checked')) {
			$("#partialDateContainer").removeClass("hidden");
			$("#dateContainer").addClass("hidden");
		} else {
			$("#partialDateContainer").addClass("hidden");
			$("#dateContainer").removeClass("hidden");
		}
	});
};

function createNewOffenseClick() {
		if ($("#createNewOffense").is(':checked')) {
			$("#offenseName").show();
			$("#offense").hide();
		} else {
			$("#offense").show();
			$("#offenseName").hide();
		}		
	}
