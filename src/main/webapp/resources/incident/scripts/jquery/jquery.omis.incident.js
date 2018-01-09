/**
 * Assigns on click behavior to the involved person item with the specified index and category.
 * 
 * @param index involved person item index
 * @param category involved party category
 */
function applyInvolvedPersonItemBehavior(index, category) {
	$("#involvedPersonRemoveLink" + index).click(function() {
		$("#involvedPersonItem" + index).remove();
	});
	if (category == "STAFF") {
		applyStaffSearch(document.getElementById("involvedPerson" + index + "Input"),
				document.getElementById("involvedPerson" + index),
				document.getElementById("involvedPerson" + index + "Display"),
				document.getElementById("involvedPerson" + index + "Current"),
				document.getElementById("involvedPerson" + index + "Clear"));
	} else if (category == "OFFENDER") {
		applyOffenderSearch(document.getElementById("involvedPerson" + index + "Input"),
				document.getElementById("involvedPerson" + index),
				document.getElementById("involvedPerson" + index + "Display"),
				document.getElementById("involvedPerson" + index + "Clear"));
	} else if (category == "OTHER") {
		applyPersonSearch(document.getElementById("involvedPerson" + index + "Input"),
				document.getElementById("involvedPerson" + index),
				document.getElementById("involvedPerson" + index + "Display"),
				document.getElementById("involvedPerson" + index + "Clear"));
	}
}

/**
 * Assign on click functionality for the add person item action menu.
 */
function assignAddInvolvedPersonItemActionMenuOnClick() {
	//TODO: add person action menu on click (change from action menu example)
	var elements = document.getElementsByClassName('createLink');
	var links = $(".createLink");
	$(links).each(function() {
		var category = this.id.replace("addInvolvedPersonItemLink", "");
		$(this).click(function () {
			$.ajax(config.ServerConfig.getContextPath() + "/incident/report/displayInvolvedPersonItem.html",
					   {
							type: "GET",
							async: false,
							data: {involvedPersonItemIndex: currentInvolvedPersonItemIndex,
								category: category},
							success: function(data) {
								$("#involvedPersonItemsContainer").append(data);
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("Error - status: " + textStatus + "; error: "
									+ errorThrown);
								$("#involvedPersonItemsContainer").html(jqXHR.responseText );
							}
						});
						applyInvolvedPersonItemBehavior(currentInvolvedPersonItemIndex, category);
						currentInvolvedPersonItemIndex++;
						return false;
			});
		});
}