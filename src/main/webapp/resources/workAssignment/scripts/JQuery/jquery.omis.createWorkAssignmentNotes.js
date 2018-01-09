/**
 * Add a new work assignment note.
 */
function addWorkAssignmentNoteItem(){
	var noteDate = $("#noteDate");
	$("#addWorkAssignmentNoteItemLink").click(function(){
		$.ajax(this.href,
		   {
				type: "GET",
				async: false,
				data:  {noteItemIndex: workAssignmentNoteIndex},
				success: function(data) 
				{
					$("#noteTable > tbody").append(data);
					assignDatePicker("#noteDate"+workAssignmentNoteIndex);
					applyNoteItemBehavior(workAssignmentNoteIndex);
					workAssignmentNoteIndex++;
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#noteTable > tbody").html(jqXHR.responseText );
				}
			});
		applyNewNoteItemBehavior(workAssignmentNoteIndex-1);
		return false;
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

function applyNoteItemBehavior(workAssignmentNoteIndex) {
	$("#deleteNoteItem" + workAssignmentNoteIndex).click(function() {
		if(document.getElementById("workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).value == "CREATE"){
			$("#workAssignmentNoteItems" + workAssignmentNoteIndex).addClass("hidden");
			$("#workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).val("REMOVE");
			return false;
		}
		if(document.getElementById("workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).value == "REMOVE"){
			$("#workAssignmentNoteItems" + workAssignmentNoteIndex).removeClass("removeRow");
			if(workAssignmentNoteIndex<originalWorkAssignmentNoteIndex){
				$("#workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).val("UPDATE");
			}
			else {
				$("#workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).val("CREATE");
			}
			return false;
		}
		if(document.getElementById("workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).value == "UPDATE"){
			$("#workAssignmentNoteItems" + workAssignmentNoteIndex).addClass("removeRow");
			$("#workAssignmentNoteItemsOperation" + workAssignmentNoteIndex).val("REMOVE");
			return false;
		}
	});
}

function applyHighLight(workAssignmentNoteIndex) {
	$("#workAssignmentNoteItems" + workAssignmentNoteIndex).addClass("removeRow");
	return false;
}

function applyNewNoteItemBehavior(workAssignmentNoteIndex) {
	$("#deleteNoteItem" + workAssignmentNoteIndex).click(function() {
		$("#workAssignmentNoteItems" + workAssignmentNoteIndex).remove();
		return false;
	});
}