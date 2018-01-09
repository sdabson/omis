/**
 * Assigns lab work item row functionality to all existing rows of the lab
 * work table.
 */
function assignTableRowOnClickFunction() {
	for (var labWorkItemIndex = 0; labWorkItemIndex < currentLabWorkIndex; labWorkItemIndex++) {
		applyLabWorkRowBehavior(labWorkItemIndex);
	}
}

/**
 * Add a new lab work item.
 */
function addLabWorkItem() {
	if ($("#addLabWorkLink") != undefined) {
		var defaultOrderDate = $("#defaultOrderdate");
		var defaultOrderedBy = $("#defaultOrderedBy");
		var defaultSampleDate = $("#defaultSampleDate");
		var defaultSampleLab = $("#defaultSampleLab");
		var defaultNothingPerOral = null;
		var defaultNoLeaky = null;
		var defaultNoMeds = null;
		
		$("#addLabWorkLink").click(function() {
			if ($("#defaultNothingPerOral").is(':checked')) {
				defaultNothingPerOral = true;
			}
			if ( $("#defaultNoLeaky").is(':checked')) {
				defaultNoLeaky = true;
			}
			if ($("#defaultNoMeds").is(':checked')) {
				defaultNoMeds = true;
			}
			$.ajax(config.ServerConfig.getContextPath() + "/health/referral/labWork/addLabWorkItem.html",
			   {
					type: "GET",
					async: false,
					data: 	{	labWorkIndex: currentLabWorkIndex,
								facility: facilityId,
								defaultOrderDate: defaultOrderDate.val(),
								defaultOrderedBy: defaultOrderedBy.val(),
								defaultSampleDate: defaultSampleDate.val(),
								defaultSampleLab: defaultSampleLab.val(),
								defaultNothingPerOral: defaultNothingPerOral,
								defaultNoLeaky: defaultNoLeaky,
								defaultNoMeds: defaultNoMeds
							},
					success: function(data) {
						$("#labWorkItemTable > tbody").append(data);
						$("#labWorkProcess" + currentLabWorkIndex).val('true');
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#labWorkItemTable > tbody").html(jqXHR.responseText );
					}
				});
			applyLabWorkRowBehavior(currentLabWorkIndex);
			currentLabWorkIndex++;
			return false;
		});
	}
	for (var labWorkItemIndex = 0; labWorkItemIndex < currentLabWorkIndex; labWorkItemIndex++) {
		applyLabWorkRowBehavior(labWorkItemIndex);
	}
	assignDatePicker($("#defaultSampleDate"));
	var defaultOrderDate = $("#defaultOrderDate");
	$("#defaultOrderDate").datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function() {
			$.ajax({
					type: "GET",
					async: false,
					url: config.ServerConfig.getContextPath() + "/health/referral/labWork/addDefaultOrderedByOptions.html",
					data: {	date: defaultOrderDate.val(),
							facility: facilityId},
					success: function(data) {
						$("#defaultOrderedBy").html(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#defaultOrderedBy").html(jqXHR.responseText );
					}
				});
		}
	});
}

/**
 * Assign jQuery date picker to the specified input control.
 * 
 * @param inputControl input control to assign a jQuery Date Picker
 */
function assignDatePicker(inputControl) {
	$(inputControl).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/**
 * Apply lab work appointment item row behavior.
 * @param labWorkIndex index of lab work
 */
function applyLabWorkRowBehavior(labWorkIndex) {
	var labWorkRow = $("#labWorkAppointmentItem" + labWorkIndex);
	$("#deleteLabWorkItem" + labWorkIndex).click(function() {
		var labWork = $("#labWorkId" + labWorkIndex);
		var labWorkRequirementRequest = $("#labWorkRequirementRequest" +labWorkIndex);
		var labWorkProcess = $("#labWorkProcess" + labWorkIndex);
		if (labWork.val() != "" || labWorkRequirementRequest.val() != "") {
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
	var orderDate = $("#labWorkOrderDate" + labWorkIndex);
	$("#labWorkOrderDate" + labWorkIndex).datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function() {
			$.ajax({
					type: "GET",
					async: false,
					url: config.ServerConfig.getContextPath() + "/health/referral/labWork/addOrderedByOptions.html",
					data: {	date: orderDate.val(),
							facility: facilityId},
					success: function(data) {
						$("#labWorkByProvider" + labWorkIndex).html(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#labWorkByProvider" + labWorkIndex).html(jqXHR.responseText );
					}
				});
		}
	});
	assignDatePicker("#labWorkSampleDate" + labWorkIndex);
	assignDatePicker("#labWorkResultsDate" + labWorkIndex);	
}