/**
 * Jquery implementation of functions for labWork.js
 * 
 * @author: Joel Norris
 * @version: 0.1.0 (Sept. 15, 2014)
 * @since: OMIS 3.0
 */

/**
 * Assign jQuery date picker to the specified input control.
 * 
 * @param inputControl input control to assign a jQuery Date Picker
 */
function assignDatePicker(inputControl) {
	$(inputControl).attr("autocomplete", "off");
	$(inputControl).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

function assignLabWorkOnClick() {
	assignDatePicker("#sampleDate");
	assignDatePicker("#resultsDate");
	var orderDate = $("#orderDate");
	var facilityId = $("#facilityId");
	$("#orderDate").attr("autocomplete", "off");
	$("#orderDate").datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function() {
			$.ajax({
					type: "GET",
					async: false,
					url: config.ServerConfig.getContextPath() + "/health/labWork/addOrderedByProviderOptions.html",
					data: {	orderDate: orderDate.val(),
							facility: facilityId.val()},
					success: function(data) {
						$("#byProvider").html(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#byProvider").html(jqXHR.responseText );
					}
				});
		}
	});
};