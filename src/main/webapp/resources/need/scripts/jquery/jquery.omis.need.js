/**
 * Apply a jQuery date picker to the element with the specified id.
 * 
 * @param id id
 */
function applyDatePicker(id) {
	$('#'+id).attr("autocomplete", "off");
	$("#" + id).datepicker({
		changeMonth: true,
		changeYear: true
	});
}

function applyObjectiveSourceOnChange() {
	$("#source").change(function() {
		if($(this).val() == "STAFF") {
			$("#staffMemberContentArea").removeClass("hidden");
		} else {
			$("#staffMemberContentArea").addClass("hidden");
		}
	});
}