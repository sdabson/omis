/*
 * Schedule lab work referral java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Jul 17, 2014)
 * Since: OMIS 3.0
 */
$(document).ready(function() {
	var sampleDate = document.getElementById("sampleDate");
	applySearch(document.getElementById("offenderName"),document.getElementById("offender"), ui.search.Autocomplete.OFFENDER_SEARCH);
	applyFormUpdateChecker(document.getElementById("scheduleLabWorkReferralForm"));
	applyDatePicker("sampleDate");
	applyLabWorkReferralOnClick(labWorkSampleItemIndex, facilityId, sampleDate);
	applyAllLabWorkSampleOnClick(labWorkSampleItemIndex, facilityId);
});