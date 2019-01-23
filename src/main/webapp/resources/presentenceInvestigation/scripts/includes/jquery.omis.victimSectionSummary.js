function victimSectionSummaryNoteItemsCreateOnClick() {
	$("#createVictimSectionSummaryNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/victimSummary/createVictimSectionSummaryNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {victimSectionSummaryNoteItemIndex: currentVictimSectionSummaryNoteItemIndex},
				success: function(data) {
					$("#victimSectionSummaryNoteTableBody").append(data);
					victimSectionSummaryNoteItemRowOnClick(currentVictimSectionSummaryNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#victimSectionSummaryNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentVictimSectionSummaryNoteItemIndex++;
		return false;
	});
};

function victimSectionSummaryNoteItemRowOnClick(victimSectionSummaryNoteItemIndex) {
	assignDatePicker("victimSectionSummaryNoteItemDate" + victimSectionSummaryNoteItemIndex);
	$("#removeVictimSectionSummaryNoteLink" + victimSectionSummaryNoteItemIndex).click(function() {
		if ($("#victimSectionSummaryNoteOperation" + victimSectionSummaryNoteItemIndex).val() == "UPDATE") {
			$("#victimSectionSummaryNoteOperation" + victimSectionSummaryNoteItemIndex).val("REMOVE");
			$("#victimSectionSummaryNoteItemRow" + victimSectionSummaryNoteItemIndex).addClass("removeRow");
		} else if($("#victimSectionSummaryNoteOperation" + victimSectionSummaryNoteItemIndex).val() == "REMOVE") {
			$("#victimSectionSummaryNoteOperation" + victimSectionSummaryNoteItemIndex).val("UPDATE");
			$("#victimSectionSummaryNoteItemRow" +victimSectionSummaryNoteItemIndex).removeClass("removeRow");
		} else {
			$("#victimSectionSummaryNoteItemRow" + victimSectionSummaryNoteItemIndex).remove();
		}
		return false;
	});
};

function victimSectionSummaryDocketAssociationItemRowOnClick(victimSectionSummaryDocketAssociationItemIndex) {
	$("#removeVictimSectionSummaryDocketAssociationLink" + victimSectionSummaryDocketAssociationItemIndex).click(function(){
		if ($("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val() == "UPDATE") {
			$("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val("REMOVE");
			$("#victimSectionSummaryDocketAssociationItemRow" + victimSectionSummaryDocketAssociationItemIndex).addClass("removeRow");
		} else if($("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val() == "REMOVE") {
			$("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val("UPDATE");
			$("#victimSectionSummaryDocketAssociationItemRow" +victimSectionSummaryDocketAssociationItemIndex).removeClass("removeRow");
		} else {
			$("#victimSectionSummaryDocketAssociationItemRow" + victimSectionSummaryDocketAssociationItemIndex).remove();
		}
		return false;
	})
};

function victimCheckboxOnClick(victimSectionSummaryDocketAssociationItemIndex) {
	$("#itemSelected" + victimSectionSummaryDocketAssociationItemIndex).change(function(e){
		if (e.target.checked){
			if ($("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val() == "EXCLUDE"){
				$("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val("INCLUDE");
				$.ajax(config.ServerConfig.getContextPath() + "/presentenceInvestigation/victimSummary/refresh.html",
						   {
								type: "GET",
								async: false,
								data: {
									victim: $("#victim" + victimSectionSummaryDocketAssociationItemIndex).val() }, // Need a person ID here?
								success: function(data) {
									$("#victimDocumentSummariesTableBody").append(data);
									var rows = document.getElementsByClassName('rowActionMenuItem');
									for(var i = 0; i < rows.length; i++){
										applyActionMenu(rows[i]);
									}
								},
								error: function(jqXHR, textStatus, errorThrown) {
									alert("Error - status: " + textStatus + "; error: "
										+ errorThrown);
									$("#victimDocumentSummariesTableBody").html(jqXHR.responseText );
								}
							});
			} else if ($("#victimSectionSummaryDocketAssociationOperation" + victimSectionSummaryDocketAssociationItemIndex).val() == "INCLUDE"){
				$("victimSectionSummaryDocketAssociationItemOperation" + victimSectionSummaryDocketAssociationItemIndex).val("EXCLUDE");
				$("victimSectionSummaryDocketAssociationItemRow" + victimSectionSummaryDocketAssociationItemIndex);
			} else {
				
			}
		} else {
			
		}
	});
}

function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};