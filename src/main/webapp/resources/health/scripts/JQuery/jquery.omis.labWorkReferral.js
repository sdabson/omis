/*
 * JQuery implementation for schedule lab work referral java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Jul 17, 2014)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality to elements on the apply lab work referral 
 * form.
 * 
 * @param labWorkSampleIndex the index of the lab work samples
 * @param facilityId the id of the facility
 * @param sampleDate sample date
 */
function applyLabWorkReferralOnClick(labWorkSampleItemIndex, facilityId, sampleDate) {
	
	var defaultOrderDate = $("#defaultOrderDate");
	var defaultOrderedBy = $("#defaultOrderedBy");
	var defaultSampleDate = $("#defaultSampleDate");
	var defaultSampleLab = $("#defaultSampleLab");
	var defaultNothingPerOral;
	var defaultNoLeaky;
	var defaultNoMeds;
	
	applyDatePicker("defaultSampleDate");	
	defaultOrderDate.datepicker({
		onClose: function() {
			$.ajax({
				type: "GET",
				async: false,
				url: config.ServerConfig.getContextPath() + "/health/referral/labWork/addDefaultOrderedByProviderOptions.html",
				data: {	
					facility: facilityId,
					orderDate: defaultOrderDate.val() 
				},
				success: function(data) {
					defaultOrderedBy.html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					defaultOrderedBy.html(jqXHR.responseText);
				}
			});
		},
		changeMonth: true,
		changeYear: true
	});
	
	$("#addLabWorkSampleItemLink").click(function() {
		if ($("#defaultNothingPerOral").is(':checked')) {
			defaultNothingPerOral = true;
		}
		if ( $("#defaultNoLeaky").is(':checked')) {
			defaultNoLeaky = true;
		}
		if ($("#defaultNoMeds").is(':checked')) {
			defaultNoMeds = true;
		}
		$.ajax({
			type: "GET",
			async: false,
			url: config.ServerConfig.getContextPath() + "/health/referral/labWork/addLabWorkSampleItem.html",
			data: {	labWorkSampleItemIndex: labWorkSampleItemIndex,
					facility: facilityId,
					defaultOrderDate: defaultOrderDate.val(),
					defaultOrderedBy: defaultOrderedBy.val(),
					defaultSampleDate: defaultSampleDate.val(),
					defaultSampleLab: defaultSampleLab.val(),
					defaultNothingPerOral: defaultNothingPerOral,
					defaultNoLeaky: defaultNoLeaky,
					defaultNoMeds: defaultNoMeds},
			success: function(data) {
				$("#labWorkSampleItemTable").append(data);
				applyLabWorkSampleItemOnClick(labWorkSampleItemIndex, facilityId);
				$("#labWorkSampleItemProcess" + labWorkSampleItemIndex).val('true');
				labWorkSampleItemIndex++;
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#labWorkSampleItemTable").html(jqXHR.responseText);
			}
		});
		return false;
	});
}

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

/**
 * Applies on click functionality to all lab work sample items for the lab work
 * referral form.
 * 
 * @param labWorkSampleItemIndex lab work sample item index
 */
function applyAllLabWorkSampleOnClick(labWorkSampleItemIndex, facilityId) {
	for (var currentIndex = 0; currentIndex < labWorkSampleItemIndex; currentIndex ++) {
		applyLabWorkSampleItemOnClick(currentIndex, facilityId);
	}
};

/*
 * Apply on click functionality for the lab work sample item row with the 
 * specified lab work sample item index.
 */
function applyLabWorkSampleItemOnClick(labWorkSampleItemIndex, facilityId) {
	$("#deleteLabWorkSampleItem" + labWorkSampleItemIndex).click(function() {
		var labWorkRow = $("#labWorkSampleItemRow" + labWorkSampleItemIndex);
		var labWork = $("#labWorkId" + labWorkSampleItemIndex);
		var labWorkProcess = $("#labWorkSampleItemProcess" + labWorkSampleItemIndex);
		if (labWork.val() != "") {
			if (labWorkProcess.val() == "true") {
				labWorkProcess.val("false");
				labWorkRow.addClass("toBeDeleted");
			} else {
				labWorkProcess.val("true");
				labWorkRow.removeClass("toBeDeleted");
			}
		} else {
			labWorkRow.remove();
		}
		return false;
	});
	var sampleItemSampleDateID = $("#labWorkSampleItemSampleDate" + labWorkSampleItemIndex).attr("id");
	applyDatePicker(sampleItemSampleDateID);
	$("#labWorkSampleItemOrderDate" + labWorkSampleItemIndex).datepicker({
		onClose: function() {
			$.ajax({
				type: "GET",
				async: false,
				url: config.ServerConfig.getContextPath() + "/health/referral/labWork/addOrderedByProviderOptions.html",
				data: {	
					facility: facilityId,
					orderDate: $("#labWorkSampleItemOrderDate" + labWorkSampleItemIndex).val() 
				},
				success: function(data) {
					$("#labWorkSampleItemByProvider" + labWorkSampleItemIndex).html(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#labWorkSampleItemOrderDate" + labWorkSampleItemIndex).html(jqXHR.responseText);
				}
			});
		},
		changeMonth: true,
		changeYear: true
	});
}