function hearingDecisionNotesCreateOnClick() {
	$("#createHearingDecisionNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/boardHearingDecision/addHearingDecisionNote.html",
		   {
				type: "GET",
				async: false,
				data: {hearingDecisionNoteIndex: currentHearingDecisionNoteIndex},
				success: function(data) {
					$("#hearingDecisionNoteTableBody").append(data);
					hearingDecisionNoteItemRowOnClick(currentHearingDecisionNoteIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#hearingDecisionNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentHearingDecisionNoteIndex++;
		return false;
	});
};

function hearingDecisionNoteItemRowOnClick(hearingDecisionNoteIndex) {
	assignDatePicker("hearingDecisionNoteItemDate" + hearingDecisionNoteIndex);
	$("#removeHearingDecisionNoteLink" + hearingDecisionNoteIndex).click(function() {
		if ($("#hearingDecisionNoteOperation" + hearingDecisionNoteIndex).val() == "EDIT") {
			$("#hearingDecisionNoteOperation" + hearingDecisionNoteIndex).val("REMOVE");
			$("#hearingDecisionNoteItemRow" + hearingDecisionNoteIndex).addClass("removeRow");
		} else if($("#hearingDecisionNoteOperation" + hearingDecisionNoteIndex).val() == "REMOVE") {
			$("#hearingDecisionNoteOperation" + hearingDecisionNoteIndex).val("EDIT");
			$("#hearingDecisionNoteItemRow" +hearingDecisionNoteIndex).removeClass("removeRow");
		} else {
			$("#hearingDecisionNoteItemRow" + hearingDecisionNoteIndex).remove();
		}
		return false;
	});
};

function categoryOnChange(itemIndex) {
	$("#boardMemberDecisionItems" + itemIndex + "Category").change(function() {
		if (this.selectedIndex > -1) {
			$.ajax(config.ServerConfig.getContextPath() + "/boardHearingDecision/findHearingDecisionReasonsForDecision.html",
			   {
				   type: "GET",
				   async: false,
				   data: { decision: this.options[this.selectedIndex].value },
				   success: function(data) {
				   		$("#boardMemberDecisionItems" + itemIndex + "DecisionReason").empty().append(data);
				   },
				   error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
				   }
			   }
			);
			updateBoardHearingCategory();
		}
	});
};

function updateBoardHearingCategory() {
	var memberRows = document.getElementsByClassName("boardMemberDecisionItemRow");
	var denyCount = 0;
	var grantCount = 0;
	for(var i = 0; i < memberRows.length; i++) {
		var value = document.getElementById("boardMemberDecisionItems" + memberRows[i].getAttribute("id").replace("boardMemberDecisionItemRow", "") + "Category").value;
		if (value == "DENY") {
			denyCount++;
		} else if (value == "GRANT") {
			grantCount++;
		}
	}
	if ((denyCount > 0 || grantCount > 0) && grantCount != denyCount) {
		var category = "";
		if (denyCount > grantCount) {
			category = "DENY";
		} else {
			category = "GRANT";
		}
		if (category != "") {
			var messageResolver = new common.MessageResolver("omis.boardhearingdecision.msgs.boardHearingDecision");
			var decisionCategory = $("#decsionCategory");
			decisionCategory.text('');
			ui.appendHtml(decisionCategory, messageResolver.getMessage("decisonCategoryLabel." + category));
			$.ajax(config.ServerConfig.getContextPath() + "/boardHearingDecision/findBoardHearingDecisionCategoriesForDecision.html",
				{
				   type: "GET",
				   async: false,
				   data: { decision: category },
				   success: function(data) {
					   var category = $("#category");
					   var selectedItem = category.children(":selected").text();
					   category.empty().append(data);
					   $("#category option").filter(function() {
						    return this.text == selectedItem; 
						}).attr('selected', true);
				   },
				   error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
				   }
			   }
			);
		}
	} else {
		$("#decsionCategory").text('');
		$("#category").empty();
	}
};

function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};