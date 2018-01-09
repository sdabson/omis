/*
 * Security threat group affiliation edit screen.
 * 
 * Author: Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyDatePicker(document.getElementById("verificationDate"));
	document.getElementById("state").onchange = function() {
		populateCities();
	};
	document.getElementById("group").onchange = function() {
		populateChapters();
		populateRanks();
	};
	applyFormUpdateChecker(document.getElementById("securityThreatGroupAffiliationForm"));
	applySearchUserAccountsAutocomplete(
			document.getElementById("verificationUserAccountQuery"),
			document.getElementById("verificationUserAccountLabel"),
			document.getElementById("verificationUserAccount"),
			document.getElementById("clearVerificationUserAccountLink"),
			document.getElementById("useCurrentUserAccountForVerificationLink"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("affiliationNoteItemsActionMenuLink"), affiliationNoteItemsActionMenuOnClick);
	assignOnClick();
	
	$("#createNewChapter").click(createNewChapterClick);
	createNewChapterClick();
	$("#createNewRank").click(createNewRankClick);
	createNewRankClick();
};

/** Populates cities drop down. */
function populateCities() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/city/findByState.html?state=" + $("#state").val()
			+ "&amp;city=" + $("#city").val(),
			{
				async: true,
				success: function(data) {
					$("#city").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

/** Populates chapters drop down. */
function populateChapters() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/stg/findChaptersByGroup.html?group=" 
			+ $("#group").val() + "&amp;chapter=" + $("#chapter").val(),
			{
				async: true,
				success: function(data) {
					$("#chapter").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

/** Populates ranks drop down. */
function populateRanks() {
	$.ajax(config.ServerConfig.getContextPath()
			+ "/stg/findRanksByGroup.html?group=" 
			+ $("#group").val() + "&amp;rank=" + $("#rank").val(),
			{
				async: true,
				success: function(data) {
					$("#rank").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				},
				type: "GET"
			}
	);
}

function createNewChapterClick() {
	if ($("#createNewChapter").is(':checked')) {
		$("#chapterName").show();
		$("#chapter").hide();
	} else {
		$("#chapter").show();
		$("#chapterName").hide();
	}		
}

function createNewRankClick() {
	if ($("#createNewRank").is(':checked')) {
		$("#rankName").show();
		$("#rank").hide();
	} else {
		$("#rank").show();
		$("#rankName").hide();
	}		
}