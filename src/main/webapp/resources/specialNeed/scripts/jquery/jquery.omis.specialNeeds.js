function applySpecialNeedsOnClick() {
	$(".rowActionMenuLink").each(function() {
		var linkId = $(this).attr("id");
		applyActionMenu(document.getElementById(linkId), function() {
			applyRemoveLinkConfirmation();
		});
	});
}

function assignSpecialNeedNoteOnClick(noteIndex){
	$("#specialNeedNote" + noteIndex + "Date").attr("autocomplete", "off");
	$("#specialNeedNote" + noteIndex + "Date").datepicker({
		changeMonth: true,
		changeYear: true	
	});	
};

function createSpecialNeedNotesActionMenuOnClick() {
	$("#createSpecialNeedNoteLink").click(function() {	
		$.ajax(config.ServerConfig.getContextPath() + "/specialNeed/createSpecialNeedNote.html",
				{async: false, type: "GET",
				data: { noteIndex: currentNoteIndex }, 
				success: function(data) {
					$("#specialNeedNotes").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#specialNeedNotes").html(jqXHR.responseText );
				}
		});
		assignSpecialNeedNoteOnClick(currentNoteIndex);
		assignRemoveOperationValue(currentNoteIndex);
		assignCreateOperationValue(currentNoteIndex);
		currentNoteIndex++;
		return false;
	});
};

function assignRemoveOperationValue(noteIndex) {
	$("#removeSpecialNeedNote" + noteIndex).click(function() {
		var noteRow = $("#specialNeedNoteRow" + noteIndex);
		var operation = $("#specialNeedNoteOperation" + noteIndex);
		var date = $("#specialNeedNote" + noteIndex + "Date");
		var text = $("#specialNeedNote" + noteIndex + "Value");
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
	var operation = $("#specialNeedNoteOperation" + noteIndex);
	operation.val("CREATE");
};

function assignOnClick() {
	for (var noteIndex = 0; noteIndex < currentNoteIndex; noteIndex++) {
		var operation = $("#specialNeedNoteOperation" + noteIndex);		
		var noteRow = $("#specialNeedNoteRow" + noteIndex);
		assignSpecialNeedNoteOnClick(noteIndex);		
		if(operation.val() == "REMOVE") {
			noteRow.addClass("remove");
		}		
		assignRemoveOperationValue(noteIndex);	
	}	
};