/** JQuery for education JS
 * 
 * @author: Annie Jacques
 * @version: 0.1.0 (July 28, 2016)
 * @since: OMIS 3.0
 * 
 */




/**
 * Assigns on click functionality for the note items create link.
 */
function noteItemsCreateOnClick() {
	$("#createNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/education/createNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {noteItemIndex: currentNoteItemIndex},
				success: function(data) {
					$("#noteTableBody").append(data);
					noteItemRowOnClick(currentNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#noteTableBody").html(jqXHR.responseText );
				}
			});
		currentNoteItemIndex++;
		return false;
	});
};

/**
 * Assigns on click functionality for the achievement items create link.
 */
function achievementItemsCreateOnClick() {
	$("#createAchievementItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/education/createAchievementItem.html",
		   {
				type: "GET",
				async: false,
				data: {achievementItemIndex: currentAchievementItemIndex},
				success: function(data) {
					$("#achievementTableBody").append(data);
					achievementItemRowOnClick(currentAchievementItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#achievementTableBody").html(jqXHR.responseText );
				}
			});
		currentAchievementItemIndex++;
		return false;
	});
};




/**
 * Assigns on click functionality for the note item row with the
 * specified note item index.
 * 
 * @param noteItemIndex note item index
 */
function noteItemRowOnClick(noteItemIndex) {
	assignDatePicker("noteItemDate" + noteItemIndex);
	$("#removeNoteLink" + noteItemIndex).click(function() {
		if ($("#noteOperation" + noteItemIndex).val() == "UPDATE") {
			$("#noteOperation" + noteItemIndex).val("REMOVE");
			$("#noteItemRow" + noteItemIndex).addClass("removeRow");
		} else if($("#noteOperation" + noteItemIndex).val() == "REMOVE") {
			$("#noteOperation" + noteItemIndex).val("UPDATE");
			$("#noteItemRow" +noteItemIndex).removeClass("removeRow");
		} else {
			$("#noteItemRow" + noteItemIndex).remove();
		}
		return false;
	});
};
	
/**
 * Assigns on click functionality for the achievement item row with the
 * specified achievement item index.
 * 
 * @param achievementItemIndex achievement item index
 */
function achievementItemRowOnClick(achievementItemIndex) {
	assignDatePicker("achievementItemDate" + achievementItemIndex);
	$("#removeAchievementLink" + achievementItemIndex).click(function() {
		if ($("#achievementOperation" + achievementItemIndex).val() == "UPDATE") {
			$("#achievementOperation" + achievementItemIndex).val("REMOVE");
			$("#achievementItemRow" + achievementItemIndex).addClass("removeRow");
		} else if($("#achievementOperation" + achievementItemIndex).val() == "REMOVE") {
			$("#achievementOperation" + achievementItemIndex).val("UPDATE");
			$("#achievementItemRow" + achievementItemIndex).removeClass("removeRow");
		} else {
			$("#achievementItemRow" + achievementItemIndex).remove();
		}
		return false;
	});
};
	
/**
 * Assigns the date picker to the element by specified id
 * @param elementId element ID
 */
function assignDatePicker(elementId) {
	$('#'+elementId).attr("autocomplete", "off");
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};

 

/**
 * Assigns on-click functionality to any existing notes and achievements
 */
function assignOnClick() {
	for (var index = 0; index < currentNoteItemIndex; index++) {
		noteItemRowOnClick(index);
	}
	for (var index = 0; index < currentAchievementItemIndex; index++) {
		achievementItemRowOnClick(index);
	}
};


