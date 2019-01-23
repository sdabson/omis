/**
 * Jquery implementation of separation need java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Aug 28, 2015)
 * Since: OMIS 3.0
 */

function assignOnClick() {
	$("#date").attr("autocomplete", "off");
	$("#date").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#removalDate").attr("autocomplete", "off");
	$("#removalDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#separationNeedNoteItemsActionMenuLink").click(function() {
		separationNeedNoteItemsActionMenuOnClick();
	});
	for (var index = 0; index < currentSeparationNeedNoteItemIndex; index++) {
		separationNeedNoteItemRowOnClick(index);
	}
};

/*
 * Assigns on click functionality for the separation need note items action menu. 
 */
function separationNeedNoteItemsActionMenuOnClick() {
	$("#createSeparationNeedNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/separationNeed/displaySeparationNeedNoteItemRow.html",
				   {
						type: "GET",
						async: false,
						data: {separationNeedNoteItemIndex: currentSeparationNeedNoteItemIndex},
						success: function(data) {
							$("#separationNeedNoteItemTableBody").append(data);
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#separationNeedNoteItemTableBody").html(jqXHR.responseText );
						}
					});
		separationNeedNoteItemRowOnClick(currentSeparationNeedNoteItemIndex);
		currentSeparationNeedNoteItemIndex++;
		return false;
	});
}


/*
 * Assigns on click functionality for the separation need note item row with
 * the specified separation need note item index.
 * 
 * @param separationNeedNoteItemIndex separation need note item index
 */
function separationNeedNoteItemRowOnClick(separationNeedNoteItemIndex) {
	$("#noteDate" + separationNeedNoteItemIndex).attr("autocomplete", "off");
	$("#noteDate" + separationNeedNoteItemIndex).datepicker({
		changeMonth: true,
		changeYear: true
	});
	applyTextCounter(document.getElementById("separationNeedNoteItems["+ separationNeedNoteItemIndex + "].note"), document.getElementById("noteItem" + separationNeedNoteItemIndex + "CharacterCounter"));
	$("#noteRemoveLink" + separationNeedNoteItemIndex).click(function() {
		if (document.getElementById("separationNeedNoteItems[" + separationNeedNoteItemIndex + "].operation").value == "UPDATE") {
			document.getElementById("separationNeedNoteItems[" + separationNeedNoteItemIndex + "].operation").value = "REMOVE";
			$("#separationNeedNoteItemRow" + separationNeedNoteItemIndex).addClass("removeRow");
		} else if(document.getElementById("separationNeedNoteItems[" + separationNeedNoteItemIndex + "].operation").value == "REMOVE") {
			document.getElementById("separationNeedNoteItems[" + separationNeedNoteItemIndex + "].operation").value = "UPDATE";
			$("#separationNeedNoteItemRow" + separationNeedNoteItemIndex).removeClass("removeRow");
		} else if(document.getElementById("separationNeedNoteItems[" + separationNeedNoteItemIndex + "].operation").value == "CREATE") {
			$("#separationNeedNoteItemRow" + separationNeedNoteItemIndex).remove();
		}
		return false;
	});
}