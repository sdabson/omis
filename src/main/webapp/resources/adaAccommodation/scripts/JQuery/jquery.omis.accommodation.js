function assignAccommodationNoteOnClick(noteIndex){
	$("#accommodationNote" + noteIndex + "Date").attr("autocomplete", "off");
	$("#accommodationNote" + noteIndex + "Date").datepicker({
		changeMonth: true,
		changeYear: true	
	});	
};

function createAccommodationNotesActionMenuOnClick() {
	$("#createAccommodationNoteLink").click(function() {	
		$.ajax(config.ServerConfig.getContextPath() + "/adaAccommodation/addAccommodationNote.html",
				{async: false, type: "GET",
				data: { noteIndex: currentNoteIndex }, 
				success: function(data) {
					$("#accommodationNotes").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#accommodationNotes").html(jqXHR.responseText );
				}
		});
		assignAccommodationNoteOnClick(currentNoteIndex);
		assignRemoveOperationValue(currentNoteIndex);
		assignCreateOperationValue(currentNoteIndex);
		currentNoteIndex++;
		return false;
	});
};

function assignRemoveOperationValue(noteIndex) {
	$("#removeAccommodationNote" + noteIndex).click(function() {
		var noteRow = $("#accommodationNoteRow" + noteIndex);
		var operation = $("#accommodationNoteOperation" + noteIndex);
		var date = $("#accommodationNote" + noteIndex + "Date");
		var text = $("#accommodationNote" + noteIndex + "Text");
		if(operation.val() == "UPDATE") {
			operation.val("REMOVE");
			noteRow.addClass("remove");
		} else if(operation.val() == "REMOVE") {
			operation.val("UPDATE");
			noteRow.removeClass("remove");
		} else if(operation.val() == "CREATE") {
			noteRow.remove();
		} else {
			console.log("Unknown operation: " + operation.val());
		}
		return false;
	});
};

function assignCreateOperationValue(noteIndex) {
	var operation = $("#accommodationNoteOperation" + noteIndex);
	operation.val("CREATE");
};

function assignOnClick() {
	for (var noteIndex = 0; noteIndex < currentNoteIndex; noteIndex++) {
		var operation = $("#accommodationNoteOperation" + noteIndex);		
		var noteRow = $("#accommodationNoteRow" + noteIndex);
		assignAccommodationNoteOnClick(noteIndex);		
		if(operation.val() == "REMOVE") {
			noteRow.addClass("remove");
		}		
		assignRemoveOperationValue(noteIndex);	
	}	
};