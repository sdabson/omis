/**
 * Behavior to edit victim associations.
 * 
 * <p>This script requires jQuery.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	
	// Apply note item row behavior
	function applyNoteRowBehavior(itemIndex) {
		
		// Links
		var noteRemoveLink = document.getElementById("noteItems[" + itemIndex + "].removeLink");
		var noteDisassociateLink = document.getElementById("noteItems[" + itemIndex + "].disassociateLink");
	
		// Applies behavior for remove link
		function applyRemoveLinkClick() {
			var noteOperation = document.getElementById("noteItems[" + itemIndex + "].operation");
			var noteRow = document.getElementById("noteItems[" + itemIndex + "].row");
			if (noteOperation.value == "UPDATE") {
				noteRemoveLink.onclick = function() {
					noteOperation.value = "REMOVE";
					ui.addClass(noteRow, "removeRow");
					applyUnremoveLinkClick();
					return false;
				};
			} else if (noteOperation.value == "CREATE"){
				noteRemoveLink.onclick = function() {
					noteRow.parentElement.removeChild(noteRow);
					return false;
				};
			} else {
				alert("Unsupported operation: " + noteOperation.value);
			}
		}
		
		// Applies behavior for unremove link
		function applyUnremoveLinkClick() {
			noteRemoveLink.onclick = function() {
				var noteOperation = document.getElementById("noteItems[" + itemIndex + "].operation");
				noteOperation.value = "UPDATE";
				var noteRow = document.getElementById("noteItems[" + itemIndex + "].row");
				ui.removeClass(noteRow, "removeRow");
				applyRemoveLinkClick();
				return false;
			};
		}
		
		// Applies behavior for disassociate link
		function applyDisassociationLinkClick() {
			noteDisassociateLink.onclick = function() {
				var noteOperation = document.getElementById("noteItems[" + itemIndex + "].operation");
				noteOperation.value = "REMOVE_ASSOCIATION";
				ui.addOrReplaceClass(noteDisassociateLink, "linkLink", "unlinkLink");
				applyReassociateLinkClick();
				return false;
			};
		}
		
		// Applies behavior for reassociate link
		function applyReassociateLinkClick() {
			noteDisassociateLink.onclick = function() {
				var noteOperation = document.getElementById("noteItems[" + itemIndex + "].operation");
				noteOperation.value = "UPDATE";
				ui.addOrReplaceClass(noteDisassociateLink, "unlinkLink", "linkLink");
				applyDisassociationLinkClick();
				return false;
			};
		}
		
		applyDatePicker(document.getElementById("noteItems[" + itemIndex + "].date"));
		applyRemoveLinkClick();
		if (noteDisassociateLink != null) {
			applyDisassociationLinkClick();
		}
	}
	
	// Applies behavior for telephone number remove link
	function applyTelephoneNumberRemoveLinkClick(itemIndex) {
		var telephoneNumberRemoveLink = document.getElementById("telephoneNumberItems[" + itemIndex + "].removeLink");
		var telephoneNumberOperation = document.getElementById("telephoneNumberItems[" + itemIndex + "].operation");
		var telephoneNumberRow = document.getElementById("telephoneNumberItems[" + itemIndex + "].row");
		if (telephoneNumberOperation.value == "UPDATE") {
			telephoneNumberRemoveLink.onclick = function() {
				telephoneNumberOperation.value = "REMOVE";
				ui.addClass(telephoneNumberRow, "removeRow");
				applyTelephoneNumberUnremoveLinkClick(itemIndex);
				return false;
			};
		} else if (telephoneNumberOperation.value == "CREATE") {
			telephoneNumberRemoveLink.onclick = function() {
				telephoneNumberRow.parentElement.removeChild(telephoneNumberRow);
				return false;
			};
		} else if (telephoneNumberOperation.value == "REMOVE") {
			applyTelephoneNumberUnremoveLinkClick(itemIndex);
		} else {
			alert("Unsupported operation: " + telephoneNumberOperation.value);
		} 
	}
	
	// Applies behavior for telephone number "unremove" link
	function applyTelephoneNumberUnremoveLinkClick(itemIndex) {
		var telephoneNumberRemoveLink = document.getElementById("telephoneNumberItems[" + itemIndex + "].removeLink");
		telephoneNumberRemoveLink.onclick = function() {
			var telephoneNumberOperation = document.getElementById("telephoneNumberItems[" + itemIndex + "].operation");
			telephoneNumberOperation.value = "UPDATE";
			var telephoneNumberRow = document.getElementById("telephoneNumberItems[" + itemIndex + "].row");
			ui.removeClass(telephoneNumberRow, "removeRow");
			applyTelephoneNumberRemoveLinkClick(itemIndex);
			return false;
		};
	}
	
	// Applies behavior for online account remove link
	function applyOnlineAccountRemoveLinkClick(itemIndex) {
		var onlineAccountRemoveLink = document.getElementById("onlineAccountItems[" + itemIndex + "].removeLink");
		var onlineAccountOperation = document.getElementById("onlineAccountItems[" + itemIndex + "].operation");
		var onlineAccountRow = document.getElementById("onlineAccountItems[" + itemIndex + "].row");
		if (onlineAccountOperation.value == "UPDATE") {
			onlineAccountRemoveLink.onclick = function() {
				onlineAccountOperation.value = "REMOVE";
				ui.addClass(onlineAccountRow, "removeRow");
				applyOnlineAccountUnremoveLinkClick(itemIndex);
				return false;
			};
		} else if (onlineAccountOperation.value == "CREATE") {
			onlineAccountRemoveLink.onclick = function() {
				onlineAccountRow.parentElement.removeChild(onlineAccountRow);
				return false;
			};
		} else if (onlineAccountOperation.value == "REMOVE") {
			applyOnlineAccountUnremoveLinkClick(itemIndex);
		} else {
			alert("Unsupported operation: " + onlineAccountOperation.value);
		}
	}
	
	// Applies behavior for online account number "unremove" link
	function applyOnlineAccountUnremoveLinkClick(itemIndex) {
		var onlineAccountRemoveLink = document.getElementById("onlineAccountItems[" + itemIndex + "].removeLink");
		onlineAccountRemoveLink.onclick = function() {
			var onlineAccountOperation = docuemnt.getElementById("onlineAccountItems[" + itemIndex + "].operation");
			onlineAccountOperation.value = "UPDATE";
			var onlineAccountRow = document.getElementById("onlineAccountItems[" + itemIndex + "].row");
			ui.removeClass(onlineAccountRow, "removeRow");
			applyOnlineAccountRemoveLink(itemIndex);
			return false;
		};
	}
	
	// Applies behavior for telephone numbers
	function applyTelephoneNumberRowBehavior(telephoneNumberItemIndex) {
		applyTelephoneNumberRemoveLinkClick(telephoneNumberItemIndex);
	}
	
	// Applies behavior for online accounts
	function applyOnlineAccountRowBehavior(onlineAccountItemIndex) {
		applyOnlineAccountRemoveLinkClick(onlineAccountItemIndex);
	}
	
	// Apply behavior person and victim fields behavior
	if (document.getElementById("personFieldSet") != null) {
		applyPersonFieldsOnClick("personFields", "personFields/findStates.html", "personFields/findCities.html");
	}
	applyDatePicker(document.getElementById("victimFieldsRegisterDate"));
	applyDatePicker(document.getElementById("victimFieldsPacketSendDate"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	// Apply mailing address behavior
	if (document.getElementById("mailingAddressFieldSet") != null) {
		enterMailingAddressFields = document.getElementById("enterMailingAddressFields");
		enterMailingAddressFields.onclick = function() {
			var mailingAddressEntryFields = document.getElementById("mailingAddressEntryFields");
			if (enterMailingAddressFields.checked) {
				if (ui.hasClass(mailingAddressEntryFields, "hidden")) {
					ui.removeClass(mailingAddressEntryFields, "hidden");
				}
			} else {
				if (!ui.hasClass(mailingAddressEntryFields, "hidden")) {
					ui.addClass(mailingAddressEntryFields, "hidden");
				}
			}
		};
		useExistingMailingAddressButton = document.getElementById("useExistingMailingAddressButton");
		useExistingMailingAddressButton.onclick = function() {
			mailingAddressFields = document.getElementById("mailingAddressFields");
			if (!ui.hasClass(mailingAddressFields, "hidden")) {
				ui.addClass(mailingAddressFields, "hidden");
			}
			existingMailingAddressQuery = document.getElementById("existingMailingAddress");
			existingMailingAddressQuery.disabled = false;
		};
		createNewMailingAddressButton = document.getElementById("createNewMailingAddressButton");
		createNewMailingAddressButton.onclick = function() {
			mailingAddressFields = document.getElementById("mailingAddressFields");
			if (ui.hasClass(mailingAddressFields, "hidden")) {
				ui.removeClass(mailingAddressFields, "hidden");
			}
			existingMailingAddressQuery = document.getElementById("existingMailingAddress");
			existingMailingAddressQuery.disabled = true;
		};
		var existingMailingAddressQuery = document.getElementById("existingMailingAddressQuery");
		var existingMailingAddress = document.getElementById("existingMailingAddress");
		var findAddressesByQueryUrl = config.ServerConfig.getContextPath() + "/victim/association/findAddressesByQuery.json";
		applyValueLabelAutoComplete(existingMailingAddressQuery, existingMailingAddress, findAddressesByQueryUrl); 
		applyAddressFieldsOnClick("mailingAddressFields", "mailingAddressFields/findStates.html", "mailingAddressFields/findCities.html", "mailingAddressFields/findZipCodes.html");
	}
	
	// Apply ZIP code behavior
	if (document.getElementById("poBoxFieldSet") != null) {
		enterPoBoxFields = document.getElementById("enterPoBoxFields");
		enterPoBoxFields.onclick = function() {
			var poBoxEntryFields = document.getElementById("poBoxEntryFields");
			if (enterPoBoxFields.checked) {
				if (ui.hasClass(poBoxEntryFields, "hidden")) {
					ui.removeClass(poBoxEntryFields, "hidden");
				}
			} else {
				if (!ui.hasClass(poBoxEntryFields, "hidden")) {
					ui.addClass(poBoxEntryFields, "hidden");
				}
			}
		};
		applyPoBoxFieldsOnClick("poBoxFields", "poBoxFields/findStates.html", "poBoxFields/findCities.html", "poBoxFields/findZipCodes.html");
	}
	
	// Apply telephone number behavior
	if (document.getElementById("telephoneNumberActionMenuLink")) {
		applyActionMenu(document.getElementById("telephoneNumberActionMenuLink"), function() {
			var createVictimContactTelephoneNumberLink = document.getElementById("createVictimContactTelephoneNumberLink");
			createVictimContactTelephoneNumberLink.onclick = function() {
				var url = createVictimContactTelephoneNumberLink.getAttribute("href") + "?itemIndex=" + telephoneNumberItemIndex;
				var request = new XMLHttpRequest();
				request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
				request.send();
				if (request.status == 200) {
					ui.appendHtml(document.getElementById("telephoneNumbersBody"), request.responseText);
					applyTelephoneNumberRowBehavior(telephoneNumberItemIndex);
				} else {
					alert("Error - status: " + request.status + "; URL: " + url);
				}
				telephoneNumberItemIndex = telephoneNumberItemIndex + 1;
				return false;
			};
		});
	}
	
	// Apply online account behavior
	if (document.getElementById("onlineAccountActionMenuLink") != null) {
		applyActionMenu(document.getElementById("onlineAccountActionMenuLink"), function() {
			var createVictimContactOnlineAccountLink = document.getElementById("createVictimContactOnlineAccountLink");
			createVictimContactOnlineAccountLink.onclick = function() {
				var url = createVictimContactOnlineAccountLink.getAttribute("href") + "?itemIndex=" + onlineAccountItemIndex;
				var request = new XMLHttpRequest();
				request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
				request.send();
				if (request.status == 200) {
					ui.appendHtml(document.getElementById("onlineAccountsBody"), request.responseText);
					applyOnlineAccountRowBehavior(onlineAccountItemIndex);
				} else {
					alert("Error - status: " + request.status + "; URL: " + url);
				}
				onlineAccountItemIndex = onlineAccountItemIndex + 1;
				return false;
			};
		});
	}
	
	// Apply note behavior
	applyActionMenu(document.getElementById("notesActionMenuLink"), function() {
		var createVictimAssociationNoteLink = document.getElementById("createVictimAssociationNoteLink");
		createVictimAssociationNoteLink.onclick = function() {
			var url = createVictimAssociationNoteLink.getAttribute("href") + "?itemIndex=" + victimNoteItemIndex;
			var request = new XMLHttpRequest();
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				ui.appendHtml(document.getElementById("victimAssociationNotesBody"), request.responseText);
				applyNoteRowBehavior(victimNoteItemIndex);
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
			}
			victimNoteItemIndex = victimNoteItemIndex + 1;
			return false;
		};
	});
	for (var itemIndex = 0; itemIndex < telephoneNumberItemIndex; itemIndex++) {
		if (document.getElementById("telephoneNumberItems[" + itemIndex + "].row") != null) {
			applyTelephoneNumberRowBehavior(itemIndex);
		}
	}
	for (var itemIndex = 0; itemIndex < onlineAccountItemIndex; itemIndex++) {
		if (document.getElementById("onlineAccountItems[" + itemIndex + "].row") != null) {
			applyOnlineAccountRowBehavior(itemIndex);
		}
	}
	for (var itemIndex = 0; itemIndex < victimNoteItemIndex; itemIndex++) {
		if (document.getElementById("noteItems[" + itemIndex + "].row") != null) {
			applyNoteRowBehavior(itemIndex);
		}
	}
	applyFormUpdateChecker(document.getElementById("victimAssociationForm"));
};