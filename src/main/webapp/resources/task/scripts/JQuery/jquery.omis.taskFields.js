function taskAssignmentItemsCreateOnClick() {
	$("#createTaskAssignmentItemLink").click(function() {
		var taskFieldsPropertyName = $("#taskFieldsPropertyName").val();
		$.ajax(config.ServerConfig.getContextPath() + "/task/createTaskAssignmentItem.html",
		   {
				type: "GET",
				async: false,
				data: {
					taskAssignmentItemIndex: currentTaskAssignmentItemIndex,
					taskFieldsPropertyName: taskFieldsPropertyName
				},
				success: function(data) {
					$("#taskAssignmentItemTableBody").append(data);
					taskAssignmentItemRowOnClick(taskFieldsPropertyName, currentTaskAssignmentItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#taskAssignmentItemTableBody").html(jqXHR.responseText );
				}
			});
		currentTaskAssignmentItemIndex++;
		return false;
	});
};

function taskAssignmentItemRowOnClick(taskFieldsPropertyName, taskAssignmentItemIndex) {
	assignDatePicker("taskAssignmentItemAssignedDate" + taskAssignmentItemIndex);
	applyUserIDSearch(document.getElementById("taskAssignmentInput" + taskAssignmentItemIndex),
			document.getElementById(taskFieldsPropertyName + ".taskAssignmentItems[" + taskAssignmentItemIndex + "].assigneeAccount"),
			document.getElementById("taskAssignmentDisplay" + taskAssignmentItemIndex),
			null,
			document.getElementById("clearAssignedUser" + taskAssignmentItemIndex));
	$("#removeTaskAssignmentLink" + taskAssignmentItemIndex).click(function() {
		if ($("#taskAssignmentOperation" + taskAssignmentItemIndex).val() == "UPDATE") {
			$("#taskAssignmentOperation" + taskAssignmentItemIndex).val("REMOVE");
			$("#taskAssignmentItemRow" + taskAssignmentItemIndex).addClass("removeRow");
		} else if($("#taskAssignmentOperation" + taskAssignmentItemIndex).val() == "REMOVE") {
			$("#taskAssignmentOperation" + taskAssignmentItemIndex).val("UPDATE");
			$("#taskAssignmentItemRow" +taskAssignmentItemIndex).removeClass("removeRow");
		} else {
			$("#taskAssignmentItemRow" + taskAssignmentItemIndex).remove();
		}
		return false;
	});
};

function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

function assignTimePicker(elementId) {
	$(document.getElementById(elementId)).ptTimeSelect();
};