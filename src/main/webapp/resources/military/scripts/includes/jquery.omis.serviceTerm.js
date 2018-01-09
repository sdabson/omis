/**
 * JQuery implementation for military service term java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (May 18, 2015)
 * Since: OMIS 3.0
 */

/**
 * Assign all on click functionality for elements found on inital page load.
 */
function assignOnClick() {
	assignDatePicker("startDate");
	assignDatePicker("endDate");
	for (var index = 0; index < currentServiceTermNoteItemIndex; index++) {
		serviceTermNoteItemRowOnClick(index);
	}
};

/**
 * Assign a date picker to the DOM element with the specified id.
 * 
 * @param elementId dom element id
 */
function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

/**
 * Assigns on click functionality for the service term note items action menu.
 */
function serviceTermNoteItemsActionMenuOnClick() {
	$("#createServiceTermNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/military/createServiceTermNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {serviceTermNoteItemIndex: currentServiceTermNoteItemIndex},
				success: function(data) {
					$("#serviceTermNotesTableBody").append(data);
					serviceTermNoteItemRowOnClick(currentServiceTermNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#serviceTermNotesTableBody").html(jqXHR.responseText );
				}
			});
		currentServiceTermNoteItemIndex++;
		return false;
	});
};

/**
 * Assigns on click functionality for the service term note item row with the
 * specified service term note item index.
 * 
 * @param serviceTermNoteItemIndex service term note item index
 */
function serviceTermNoteItemRowOnClick(serviceTermNoteItemIndex) {
	assignDatePicker("serviceTermNoteItemDate" + serviceTermNoteItemIndex);
	$("#removeLink" + serviceTermNoteItemIndex).click(function() {
		if ($("#serviceTermNoteOperation" + serviceTermNoteItemIndex).val() == "UPDATE") {
			$("#serviceTermNoteOperation" + serviceTermNoteItemIndex).val("REMOVE");
			$("#serviceTermNoteItemRow" + serviceTermNoteItemIndex).addClass("removeRow");
		} else if($("#serviceTermNoteOperation" + serviceTermNoteItemIndex).val() == "REMOVE") {
			$("#serviceTermNoteOperation" + serviceTermNoteItemIndex).val("UPDATE");
			$("#serviceTermNoteItemRow" + serviceTermNoteItemIndex).removeClass("removeRow");
		} else {
			$("#serviceTermNoteItemRow" + serviceTermNoteItemIndex).remove();
		}
		return false;
	});
}