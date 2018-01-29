function boardHearingNoteItemsCreateOnClick() {
	$("#createBoardHearingNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/boardHearing/createBoardHearingNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {boardHearingNoteItemIndex: currentBoardHearingNoteItemIndex},
				success: function(data) {
					$("#boardHearingNoteTableBody").append(data);
					boardHearingNoteItemRowOnClick(currentBoardHearingNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#boardHearingNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentBoardHearingNoteItemIndex++;
		return false;
	});
};

function boardHearingNoteItemRowOnClick(boardHearingNoteItemIndex) {
	assignDatePicker("boardHearingNoteItemDate" + boardHearingNoteItemIndex);
	$("#removeBoardHearingNoteLink" + boardHearingNoteItemIndex).click(function() {
		if ($("#boardHearingNoteOperation" + boardHearingNoteItemIndex).val() == "UPDATE") {
			$("#boardHearingNoteOperation" + boardHearingNoteItemIndex).val("REMOVE");
			$("#boardHearingNoteItemRow" + boardHearingNoteItemIndex).addClass("removeRow");
		} else if($("#boardHearingNoteOperation" + boardHearingNoteItemIndex).val() == "REMOVE") {
			$("#boardHearingNoteOperation" + boardHearingNoteItemIndex).val("UPDATE");
			$("#boardHearingNoteItemRow" +boardHearingNoteItemIndex).removeClass("removeRow");
		} else {
			$("#boardHearingNoteItemRow" + boardHearingNoteItemIndex).remove();
		}
		return false;
	});
};

function applyParoleBoardLocationOnClick() {
	var itinerary = $("#paroleBoardItinerary");
	itinerary.change(function() {
		 if(itinerary.val()){
			 paroleBoardLocationChangeFunction();
		 }
		 else{
			 $("#hearingLocation").html('<option value="">...</option>"');
			 $("#boardMember1").html('<option value="">...</option>"');
			 $("#boardMember2").html('<option value="">...</option>"');
			 $("#boardMember3").html('<option value="">...</option>"');
		 }
	});
}

function paroleBoardLocationChangeFunction() {
	var itinerary = $("#paroleBoardItinerary");
	$.ajax({
		type: "GET",	
		async: false,
		url: "showMeetingSiteOptions.html",
		data:{
			paroleBoardItinerary: itinerary.val()
		},
		success: function(response) {
			 $("#hearingLocation").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			$("#hearingLocation").html(jqXHR.responseText );
		}
	});
	
	$.ajax({
		type: "GET",	
		async: false,
		url: "showBoardMemberOptions.html",
		data:{
			paroleBoardItinerary: itinerary.val()
		},
		success: function(response) {
			 $("#boardMembers").html(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
			 $("#boardMembers").html(jqXHR.responseText);
		}
	});
}

function applyHearingLocationOnClick() {
	var hearingLocation = $("#hearingLocation");
	hearingLocation.change(function() {
		if(hearingLocation.val()){
			hearingLocationChangeFunction();
		}
	});
}

function hearingLocationChangeFunction() {
	var hearingLocation = $("#hearingLocation");
	$.ajax({
		url: "setHearingDate.json",
		dataType: "json",
		type: "GET",
		data: {
			hearingLocation: hearingLocation.val()
		},
		cache: false,
		success: function(data) {
			$("#hearingDate").val(data.value);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status text " + textStatus + "; status " + jqXHR.status + "; URL: " +  "setHearingDate.json");
		}
	});
	
}

function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};