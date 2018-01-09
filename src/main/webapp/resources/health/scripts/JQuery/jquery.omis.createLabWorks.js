function applyLabWorkItemsOnClick() {
	addLabWorkItem();
	for (var currentLabWorkItemIndex = 0; currentLabWorkItemIndex < labWorkItemIndex; currentLabWorkItemIndex++) {
		applyLabWorkRowItemBehavior(currentLabWorkItemIndex);
	}
}

/**
 * Add a new lab work item.
 */
function addLabWorkItem() {
	if ($("#addLabWorkItemLink") != undefined) {
		var defaultOrderDate = $("#defaultOrderdate");
		var defaultOrderedBy = $("#defaultOrderedBy");
		var defaultSampleDate = $("#defaultSampleDate");
		var defaultSampleLab = $("#defaultSampleLab");
		var defaultNothingPerOral;
		var defaultNoLeaky;
		var defaultNoMeds;
		
		$("#addLabWorkItemLink").click(function() {
			if ($("#defaultNothingPerOral").is(':checked')) {
				defaultNothingPerOral = true;
			}
			if ( $("#defaultNoLeaky").is(':checked')) {
				defaultNoLeaky = true;
			}
			if ($("#defaultNoMeds").is(':checked')) {
				defaultNoMeds = true;
			}
			$.ajax(config.ServerConfig.getContextPath() + "/health/labWork/addLabWorkItem.html",
			   {
					type: "GET",
					async: false,
					data: 	{	index: labWorkItemIndex,
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
						applyLabWorkRowItemBehavior(labWorkItemIndex);
						$("#labWorkItemProcess" + labWorkItemIndex).val('true');
						labWorkItemIndex++;
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#labWorkItemTable > tbody").html(jqXHR.responseText );
					}
				});
			return false;
		});
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
					url: config.ServerConfig.getContextPath() + "/health/labWork/addOrderedByProviderOptions.html",
					data: {	orderDate: defaultOrderDate.val(),
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
function applyLabWorkRowItemBehavior(labWorkItemIndex) {
	var labWorkRow = $("#labWorkItem" + labWorkItemIndex);
	$("#deleteLabWorkItem" + labWorkItemIndex).click(function() {
		var labWork = $("#labWorkId" + labWorkItemIndex);
		var labWorkRequirementRequest = $("#labWorkRequirementRequest" +labWorkItemIndex);
		var labWorkProcess = $("#labWorkProcess" + labWorkItemIndex);
		labWorkRow.remove();
		return false;
	});
	var orderDate = $("#labWorkOrderDate" + labWorkItemIndex);
	var originalOrderDate = $("#labWorkOrderDate" + labWorkItemIndex).val();
	$("#labWorkOrderDate" + labWorkItemIndex).datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function() {
			if (originalOrderDate != $("#labWorkOrderDate" + labWorkItemIndex).val()) {
				$.ajax({
						type: "GET",
						async: false,
						url: config.ServerConfig.getContextPath() + "/health/labWork/addOrderedByProviderOptions.html",
						data: {	orderDate: orderDate.val(),
								facility: facilityId},
						success: function(data) {
							$("#labWorkByProvider" + labWorkItemIndex).html(data);
							originalOrderDate = $("#labWorkOrderDate" + labWorkItemIndex).val();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#labWorkByProvider" + labWorkItemIndex).html(jqXHR.responseText );
						}
					});
			}
		}
	});
	assignDatePicker("#labWorkSampleDate" + labWorkItemIndex);
	assignDatePicker("#labWorkResultsDate" + labWorkItemIndex);	
}