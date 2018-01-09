/**
 * Add a new family association note.
 */
function addFamilyAssociationNoteItem(){
	var noteDate = $("#noteDate");

	$("#addFamilyAssociationNoteItemLink").click(function(){
		$.ajax(this.href,
		   {
				type: "GET",
				async: false,
				data:  {noteItemIndex: familyAssociationNoteIndex},
				success: function(data) 
				{
					$("#noteTable > tbody").append(data);
					assignDatePicker("#noteDate"+familyAssociationNoteIndex);
					applyNoteItemBehavior(familyAssociationNoteIndex);
					familyAssociationNoteIndex++;
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#noteTable > tbody").html(jqXHR.responseText );
				}
			});
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

function applyNoteItemBehavior(familyAssociationNoteIndex) {
	$("#deleteNoteItem" + familyAssociationNoteIndex).click(function() {
		if(document.getElementById("familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).value == "CREATE"){
			$("#familyAssociationNoteItems" + familyAssociationNoteIndex).addClass("hidden");
			$("#familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).val("REMOVE");
			return false;
		}
		if(document.getElementById("familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).value == "REMOVE"){
			$("#familyAssociationNoteItems" + familyAssociationNoteIndex).removeClass("removeRow");
			$("#familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).val("UPDATE");
			return false;
		}
		if(document.getElementById("familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).value == "EDIT"){
			$("#familyAssociationNoteItems" + familyAssociationNoteIndex).addClass("removeRow");
			$("#familyAssociationNoteItemsOperation" + familyAssociationNoteIndex).val("REMOVE");
			return false;
		}
	});
}