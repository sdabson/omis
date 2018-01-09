function presentenceInvestigationRequestNoteItemsCreateOnClick() {
	$("#createPresentenceInvestigationRequestNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/request/createPresentenceInvestigationRequestNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {presentenceInvestigationRequestNoteItemIndex: currentPresentenceInvestigationRequestNoteItemIndex},
				success: function(data) {
					$("#presentenceInvestigationRequestNoteTableBody").append(data);
					presentenceInvestigationRequestNoteItemRowOnClick(currentPresentenceInvestigationRequestNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#presentenceInvestigationRequestNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentPresentenceInvestigationRequestNoteItemIndex++;
		return false;
	});
};

function presentenceInvestigationRequestNoteItemRowOnClick(presentenceInvestigationRequestNoteItemIndex) {
	assignDatePicker("presentenceInvestigationRequestNoteItemDate" + presentenceInvestigationRequestNoteItemIndex);
	$("#removePresentenceInvestigationRequestNoteLink" + presentenceInvestigationRequestNoteItemIndex).click(function() {
		if ($("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val() == "UPDATE") {
			$("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val("REMOVE");
			$("#presentenceInvestigationRequestNoteItemRow" + presentenceInvestigationRequestNoteItemIndex).addClass("removeRow");
		} else if($("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val() == "REMOVE") {
			$("#presentenceInvestigationRequestNoteOperation" + presentenceInvestigationRequestNoteItemIndex).val("UPDATE");
			$("#presentenceInvestigationRequestNoteItemRow" +presentenceInvestigationRequestNoteItemIndex).removeClass("removeRow");
		} else {
			$("#presentenceInvestigationRequestNoteItemRow" + presentenceInvestigationRequestNoteItemIndex).remove();
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

function applySearchOffendersOnChange(){
	applySearchOffenders($('#searchOffenders').is(':checked'));
	$('#searchOffenders').change(function(){
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/request/displayPersonSearch.html",
		   {
				type: "GET",
				async: false,
				data: {
				},
				success: function(data) {
					$("#searchFields").html(data);
					applySearchOffenders($('#searchOffenders').is(':checked'));
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#searchFields").html(jqXHR.responseText );
				}
			});
		return false;
	});
}

function applySearchOffenders(val){
	if(val == true) {
		applyOffenderSearch(document.getElementById("personInput"),
			document.getElementById("person"),
			document.getElementById("personDisplay"),
			document.getElementById("clearPerson"));
	}
	else {
		applyPersonSearch(document.getElementById("personInput"),
			document.getElementById("person"),
			document.getElementById("personDisplay"),
			document.getElementById("clearPerson"));
	}
}

function applyCreatePersonOnChange(){
	applyCreatePerson($('input[type=radio][name=createPerson]:checked').val());
	$('input[type=radio][name=createPerson]').change(function() {
		applyCreatePerson(this.value);
	});
}

function applyCreatePerson(val){
	if (val == 'true') {
		$('#lastName').prop("disabled", false);
		$('#firstName').prop("disabled", false);
		$('#middleName').prop("disabled", false);
		$('#suffix').prop("disabled", false);
		
		$('#searchOffenders').prop("disabled", true);
		$('#personInput').val("");
		$('#personInput').prop("disabled", true);
		$('#person').val("");
		$('#person').prop("disabled", true);
		$('#personDisplay').html("");
		$('#clearPerson').prop("disabled", true);
	}
	else if (val == 'false') {
		$('#personInput').prop("disabled", false);
		$('#person').prop("disabled", false);
		$('#clearPerson').prop("disabled", false);
		$('#searchOffenders').prop("disabled", false);
		
		$('#lastName').val("");
		$('#lastName').prop("disabled", true);
		$('#firstName').val("");
		$('#firstName').prop("disabled", true);
		$('#middleName').val("");
		$('#middleName').prop("disabled", true);
		$('#suffix').val("");
		$('#suffix').prop("disabled", true);
		
	}
}










