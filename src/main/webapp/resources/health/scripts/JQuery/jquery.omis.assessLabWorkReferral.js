/*
 * JQuery specific javascript for assess lab work referral java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Jul 17, 2014)
 * Since: OMIS 3.0
 */

/**
 * Applies a jQuery date picker to the element with the specified id.
 * 
 * @param inputIdToAssign input ID to assign
 */
function applyDatePicker(inputIdToAssign) {
	$("#" + inputIdToAssign).datepicker({
		changeMonth: true,
		changeYear: true
	});
}

function applyLabWorkAssessmentItemOnClick(labWorkAssessmentItemIndex) {
	$("#deleteLabWorkAssessmentItem" + labWorkAssessmentItemIndex).click(function() {
		var labWorkAssessmentItem = $("#labWorkAssessmentItem" + labWorkAssessmentItemIndex);
		var labWork = $("#labWorkId" + labWorkAssessmentItemIndex);
		var labWorkAssessmentItemProcess = $("#labWorkAssessmentItemProcess" + labWorkAssessmentItemIndex);
		if (labWork.val() != "") {
			if (labWorkAssessmentItemProcess.val() == "true") {
				labWorkAssessmentItemProcess.val("false");
				labWorkAssessmentItem.addClass("toBeDeleted");
			} else {
				labWorkAssessmentItemProcess.val("true");
				labWorkAssessmentItem.removeClass("toBeDeleted");
			}
		} else {
			labWorkAssessmentItem.remove();
		}
		return false;
	});
}