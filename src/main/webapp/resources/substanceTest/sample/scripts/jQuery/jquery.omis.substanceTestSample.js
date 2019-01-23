/**
 * JQuery implementation for substance test java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (April 30, 2015)
 * Since: OMIS 3.0
 */

/*
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 */
function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/*
 * Assign a time picker to the DOM element with the specified id.
 *  
 * @param elementId DOM element id
 */
function assignTimePicker(elementId) {
	$("#" + elementId).attr("autocomplete", "off");
	$("#" + elementId).ptTimeSelect();
};

/*
 * Assigns on click functionality for substance test sample form.
 */
function assignOnClick() {
	assignDatePicker("collectionDate");
	assignTimePicker("collectionTime");
	$("#takenTrue").click(function() {
		$("#sampleStatusContent").addClass("hidden");
		$("#sampleDetailsContainer").removeClass("hidden");
		$("#submitAndTestContianer").removeClass("hidden");
	});
	$("#takenFalse").click(function() {
		$("#sampleStatusContent").removeClass("hidden");
		$("#sampleDetailsContainer").addClass("hidden");
		$("#submitAndTestContianer").addClass("hidden");
	});
	$("#submitAndContinue").click(function() {
		$("#saveAndContinue").val("true");
	});
	$("#submit").click(function() {
		$("#saveAndContinue").val("false");
	})
};