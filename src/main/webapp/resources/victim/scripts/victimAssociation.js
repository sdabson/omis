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
		applyRemoveLinkClick(itemIndex);
		let noteDisassociateLink = document.getElementById("disassociateLink"+ itemIndex);		
		let noteRemoveLink = document.getElementById("associationRemoveLink"+ itemIndex);
		if (noteDisassociateLink != null) {
			applyDisassociationLinkClick(itemIndex);
		} 					
	}
	
	// Applies behavior for remove link
	function applyRemoveLinkClick(itemIndex) {
		let noteRemoveLink = document.getElementById("associationRemoveLink"+ itemIndex);
		let noteOperation =  document.getElementById("noteItemOperation" + itemIndex );
		let noteRow = document.getElementById("noteItems[" + itemIndex + "].row");	
		if (noteOperation.value == "UPDATE") {
			noteRemoveLink.onclick = function() {
				noteOperation.value = "REMOVE";	
				ui.addClass(noteRow, "removeRow");		
				applyUnremoveLinkClick(itemIndex);
				return false;
			};			
		} else if (noteOperation.value == "CREATE") {
			noteRemoveLink.onclick = function() {
				noteRow.parentElement.removeChild(noteRow);
			return false;
			};			
		} else if (noteOperation.value == "REMOVE") {
			noteRemoveLink.onclick = function() {
			noteOperation.value = "UPDATE";					
			ui.removeClass(noteRow, "removeRow");				
			applyUnremoveLinkClick(itemIndex);
			return false;
			};
		} else {
			alert("Unsupported operation: " + noteOperation.value);
		}			
	}	
	
	// Applies behavior for unremove link
	function applyUnremoveLinkClick(itemIndex) {	
		let noteRemoveLink = document.getElementById("associationRemoveLink"+ itemIndex);
		noteRemoveLink.onclick = function() {
			let noteOperation = document.getElementById("noteItemOperation" + itemIndex );
			let noteRow = document.getElementById("noteItems[" + itemIndex + "].row");
				noteOperation.value = "UPDATE";					
				ui.removeClass(noteRow, "removeRow");
				applyRemoveLinkClick(itemIndex);
			return false;
		};
	}
	
	// Applies behavior for disassociate link
	function applyDisassociationLinkClick(itemIndex) {
		let noteDisassociateLink = document.getElementById("disassociateLink"+ itemIndex);			
		noteDisassociateLink.onclick = function() {
			let noteOperation = document.getElementById("noteItemOperation" + itemIndex );
			noteOperation.value = "REMOVE_ASSOCIATION";
			ui.addOrReplaceClass(noteDisassociateLink, "linkLink", "unlinkLink");
			applyReassociateLinkClick(itemIndex);
			return false;
		};
	}
	
	// Applies behavior for reassociate link
	function applyReassociateLinkClick(itemIndex) {
		let noteDisassociateLink = document.getElementById("disassociateLink"+ itemIndex);			
		noteDisassociateLink.onclick = function() {
			let noteOperation = document.getElementById("noteItemOperation" + itemIndex );
			noteOperation.value = "UPDATE";
			ui.addOrReplaceClass(noteDisassociateLink, "unlinkLink", "linkLink");
			applyDisassociationLinkClick(itemIndex);
			return false;
		};
	}	

	//Applies create operation value
	function assignCreateOperationValue(itemIndex) {
		let noteOperation = document.getElementById("noteItemOperation" + itemIndex );
		noteOperation.value =  "CREATE";
		applyRemoveLinkClick(itemIndex);
	};
	
		
	// Applies behavior for telephone number remove link
	function applyTelephoneNumberRemoveLinkClick(itemIndex) {
		let telephoneNumberRemoveLink = document.getElementById("telephoneNumberItems[" + itemIndex + "].removeLink");
		let telephoneNumberOperation = document.getElementById("telephoneNumberItems[" + itemIndex + "].operation");
		let telephoneNumberRow = document.getElementById("telephoneNumberItems[" + itemIndex + "].row");
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
		let telephoneNumberRemoveLink = document.getElementById("telephoneNumberItems[" + itemIndex + "].removeLink");
		telephoneNumberRemoveLink.onclick = function() {
			let telephoneNumberOperation = document.getElementById("telephoneNumberItems[" + itemIndex + "].operation");
			telephoneNumberOperation.value = "UPDATE";
			let telephoneNumberRow = document.getElementById("telephoneNumberItems[" + itemIndex + "].row");
			ui.removeClass(telephoneNumberRow, "removeRow");
			applyTelephoneNumberRemoveLinkClick(itemIndex);
			return false;
		};
	}
	
	// Applies behavior for online account remove link
	function applyOnlineAccountRemoveLinkClick(itemIndex) {
		let onlineAccountRemoveLink = document.getElementById("onlineAccountItems[" + itemIndex + "].removeLink");
		let onlineAccountOperation = document.getElementById("onlineAccountItems[" + itemIndex + "].operation");
		let onlineAccountRow = document.getElementById("onlineAccountItems[" + itemIndex + "].row");
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
		let onlineAccountRemoveLink = document.getElementById("onlineAccountItems[" + itemIndex + "].removeLink");
		onlineAccountRemoveLink.onclick = function() {
			let onlineAccountOperation = document.getElementById("onlineAccountItems[" + itemIndex + "].operation");
			onlineAccountOperation.value = "UPDATE";
			let onlineAccountRow = document.getElementById("onlineAccountItems[" + itemIndex + "].row");
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
			let mailingAddressEntryFields = document.getElementById("mailingAddressEntryFields");
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
		let existingMailingAddressQuery = document.getElementById("existingMailingAddressQuery");
		let existingMailingAddress = document.getElementById("existingMailingAddress");
		let findAddressesByQueryUrl = config.ServerConfig.getContextPath() + "/victim/association/findAddressesByQuery.json";
		applyValueLabelAutoComplete(existingMailingAddressQuery, existingMailingAddress, findAddressesByQueryUrl); 
		applyAddressFieldsOnClick("mailingAddressFields", "mailingAddressFields/findStates.html", "mailingAddressFields/findCities.html", "mailingAddressFields/findZipCodes.html");
	}
	
	// Apply ZIP code behavior
	if (document.getElementById("poBoxFieldSet") != null) {
		enterPoBoxFields = document.getElementById("enterPoBoxFields");
		enterPoBoxFields.onclick = function() {
			let poBoxEntryFields = document.getElementById("poBoxEntryFields");
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
			let createVictimContactTelephoneNumberLink = document.getElementById("createVictimContactTelephoneNumberLink");
			createVictimContactTelephoneNumberLink.onclick = function() {
				let url = createVictimContactTelephoneNumberLink.getAttribute("href") + "?itemIndex=" + telephoneNumberItemIndex;
				let request = new XMLHttpRequest();
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
			let createVictimContactOnlineAccountLink = document.getElementById("createVictimContactOnlineAccountLink");
			createVictimContactOnlineAccountLink.onclick = function() {
				let url = createVictimContactOnlineAccountLink.getAttribute("href") + "?itemIndex=" + onlineAccountItemIndex;
				let request = new XMLHttpRequest();
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
		let createVictimAssociationNoteLink = document.getElementById("createVictimAssociationNoteLink");
		createVictimAssociationNoteLink.onclick = function() {
			let url = createVictimAssociationNoteLink.getAttribute("href") + "?itemIndex=" + victimNoteItemIndex;
			let request = new XMLHttpRequest();			
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				ui.appendHtml(document.getElementById("victimAssociationNotesBody"), request.responseText);	
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
			}			
			(function(victimNoteItemIndex) {
				applyDatePicker(document.getElementById("noteItemsDate" + victimNoteItemIndex));						
				applyActionMenu(document.getElementById("noteItem" + victimNoteItemIndex + "SummaryActionMenuLink"), function() {					
					assignCreateOperationValue(victimNoteItemIndex);
				});
			}(victimNoteItemIndex));
			victimNoteItemIndex = victimNoteItemIndex +1;
			return false;
		};		
	});
			
	for (let itemIndex = 0; itemIndex < telephoneNumberItemIndex; itemIndex++) {
		if (document.getElementById("telephoneNumberItems[" + itemIndex + "].row") != null) {
			applyTelephoneNumberRowBehavior(itemIndex);
		}
	}
	for (let itemIndex = 0; itemIndex < onlineAccountItemIndex; itemIndex++) {
		if (document.getElementById("onlineAccountItems[" + itemIndex + "].row") != null) {
			applyOnlineAccountRowBehavior(itemIndex);
		}
	}	
	let rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for(let iterator = 0; iterator < rowActionMenus.length; iterator++) {
			(function(iterator) {
				applyDatePicker(document.getElementById("noteItemsDate" + iterator));			
				applyActionMenu(rowActionMenus[iterator], function() {	
					applyNoteRowBehavior(iterator);					
				});
			}(iterator));
	}
	applyFormUpdateChecker(document.getElementById("victimAssociationForm"));
};