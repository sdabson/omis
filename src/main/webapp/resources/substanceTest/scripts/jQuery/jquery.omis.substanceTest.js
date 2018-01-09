/**
 * JQuery implementation of substance test java script.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 23, 2013)
 * @since OMIS 3.0
 */

/**
 * Assign behavior for form elements.
 */
function assignFormOnClick() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("substanceTestResultsActionMenuLink"), addSubstanceTestResultEvent);
	$("#resultDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	for (var substanceTestResultIndex = 0; substanceTestResultIndex < currentSubstanceTestResultIndex; substanceTestResultIndex++) {
		applySubstanceTestResultLinkBehavior(substanceTestResultIndex);
	}
	if ($("#labInvolved").is(':checked')) {
		$("#labResultDate").datepicker({
			changeMonth: true,
			changeYear: true
		});
		$("#labRequestDate").datepicker({
			changeMonth: true,
			changeYear: true
		});
		applyActionMenu(document.getElementById("labResultsActionMenuLink"), prepareLabResultsActionMenu);
		applySubstanceLabOnCLick();
	}
	addLabResultEvent();
	for (var labResultIndex = 0; labResultIndex < currentLabResultIndex; labResultIndex++) {
		applyLabResultLinkBehavior(labResultIndex);
	}
	$("#labInvolved").click(function() {
		if ($("#labInvolved").is(':checked')) {
			$.ajax({
					type: "GET",
					async: true,
					url: config.ServerConfig.getContextPath() + "/substanceTest/labContent.html",
					beforeSend: function() {
						$("#labResultContainer").html("<img src='"
							+ config.ServerConfig.getContextPath()
							+ "/resources/common/images/ajaxLoader.gif'/>");
					},
					success: function(data) {
						$("#labResultContainer").html(data);
						$("#labResultContainer").show();
						$("#labResultDate").datepicker({
							changeMonth: true,
							changeYear: true
						});
						$("#labRequestDate").datepicker({
							changeMonth: true,
							changeYear: true
						});
						applyActionMenu(document.getElementById("labResultsActionMenuLink"), prepareLabResultsActionMenu);
						applySubstanceLabOnCLick();
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#labResultContainer").html(jqXHR.responseText);
					}
			});
		} else {
			$("#labResultContainer").hide();
			$("#labResultContainer").empty();
		}
	});
}

function applySubstanceLabOnCLick() {
	$("#lab").change(function() {
		$.ajax({
			type: "GET",
			async: true,
			url: config.ServerConfig.getContextPath() + "/substanceTest/displayJustification.html",
			data: {lab: $("#lab").val()},
			success: function(data) {
				$("#justificationContainer").html(data);
				applyStaffSearch(document.getElementById("authorizingStaffInput"),
						document.getElementById("authorizingStaff"),
						document.getElementById("authorizingStaffDisplay"),
						document.getElementById("authorizingStaffCurrent"),
						document.getElementById("authorizingStaffClear"));
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#justificationContainer").html(jqXHR.responseText);
			}
		});
	});
	applyStaffSearch(document.getElementById("authorizingStaffInput"),
			document.getElementById("authorizingStaff"),
			document.getElementById("authorizingStaffDisplay"),
			document.getElementById("authorizingStaffCurrent"),
			document.getElementById("authorizingStaffClear"));
}

function prepareLabResultsActionMenu() {
	addLabResultEvent();
	verifyLocalResultsEvent();
}

function verifyLocalResultsEvent() {
	var positiveOnly;
	$("#verifyLocalResults").click(function() {
		positiveOnly = false;
		verifyResultsEvent(positiveOnly);
		return false;
	});
	$("#verifyLocalPositiveResults").click(function() {
		positiveOnly = true;
		verifyResultsEvent(positiveOnly);
		return false;
	});
}

function verifyResultsEvent(positiveOnly) {
	$.ajax(config.ServerConfig.getContextPath() + "/substanceTest/verifyLocalResults.html",
		{
			type: "GET",
			async: false,
			data: $("#substanceTestForm").serialize() + "&positiveOnly=" + positiveOnly,
			success: function(data) {
				$("#labResults").html(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#labResults").html(jqXHR.responseText );
			}
		});
	currentLabResultIndex = $("#currentLabResultIndex").val();
	for (var labResultIndex = 0; labResultIndex < currentLabResultIndex; labResultIndex++) {
		applyLabResultLinkBehavior(labResultIndex);
	}
}

function addSubstanceTestResultEvent() {
	$("#addSubstanceTestResultLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/substanceTest/addSubstanceTestResult.html",
		   {
				type: "GET",
				async: false,
				data: {substanceTestResultIndex: currentSubstanceTestResultIndex},
				success: function(data) {
					$("#substanceTestResults").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#substanceTestResults").html(jqXHR.responseText );
				}
			});
			applySubstanceTestResultLinkBehavior(currentSubstanceTestResultIndex);
			currentSubstanceTestResultIndex++;
			return false;
	});
}

function addLabResultEvent() {
	$("#addLabResultLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/substanceTest/addLabResult.html",
		   {
				type: "GET",
				async: false,
				data: { labResultIndex: currentLabResultIndex },
				success: function(data) {
					$("#labResults").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#labResults").html(jqXHR.responseText );
				}
			});
			applyLabResultLinkBehavior(currentLabResultIndex);
			currentLabResultIndex++;
			return false;
	});
}

/**
 * Event for showing the admit area.
 */
function admissionEvent(substanceTestResultIndex) {
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/substanceTest/admitToUse.html",
		data: { substanceTestResultIndex: substanceTestResultIndex }, 
		beforeSend: function() {
			$("#admitArea" + substanceTestResultIndex).html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$("#admitArea" + substanceTestResultIndex).html(data);
			$("#admitArea" + substanceTestResultIndex).show();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#admitArea" + substanceTestResultIndex).html(jqXHR.responseText);
		}
	});
}

/**
 * Apply substance test result link behavior.
 * @param substanceTestResultIndex index of substance test result
 */
function applySubstanceTestResultLinkBehavior(substanceTestResultIndex) {
	var substanceTestResultRow = $("#substanceTestResultRow" + substanceTestResultIndex);
	$("#deleteSubstanceTestResult" + substanceTestResultIndex).click(function() {
		substanceTestResultRow.remove();
		return false;
	});
	var admitToUse = $("#admit" + substanceTestResultIndex);
	admitToUse.click(function(){
		if (admitToUse.is(':checked')){
			admissionEvent(substanceTestResultIndex);
		} else {
			$("#admitPrior" + substanceTestResultIndex).attr('checked',false);
			$("#admitArea" + substanceTestResultIndex).hide();
			$("#admitArea" + substanceTestResultIndex).empty();
		}
	});
}

/**
 * Apply crime lab result link behavior.
 * @param labResultIndex index of crime lab result
 */
function applyLabResultLinkBehavior(labResultIndex) {
	var labResultRow = $("#labResultRow" + labResultIndex);
	$("#deleteLabResult" + labResultIndex).click(function() {
		labResultRow.remove();
		return false;
	});
}