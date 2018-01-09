/**
 * Behavior to edit offender contact.
 * 
 * <p>This script requires jQuery.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 */
window.onload = function() {
	
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
			var onlineAccountOperation = document.getElementById("onlineAccountItems[" + itemIndex + "].operation");
			onlineAccountOperation.value = "UPDATE";
			var onlineAccountRow = document.getElementById("onlineAccountItems[" + itemIndex + "].row");
			ui.removeClass(onlineAccountRow, "removeRow");
			applyOnlineAccountRemoveLinkClick(itemIndex);
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
		var findAddressesByQueryUrl = config.ServerConfig.getContextPath() + "/offenderContact/findAddressesByQuery.json";
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
			var createOffenderContactTelephoneNumberLink = document.getElementById("createOffenderContactTelephoneNumberLink");
			createOffenderContactTelephoneNumberLink.onclick = function() {
				var url = createOffenderContactTelephoneNumberLink.getAttribute("href") + "?itemIndex=" + telephoneNumberItemIndex;
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
			var createOffenderContactOnlineAccountLink = document.getElementById("createOffenderContactOnlineAccountLink");
			createOffenderContactOnlineAccountLink.onclick = function() {
				var url = createOffenderContactOnlineAccountLink.getAttribute("href") + "?itemIndex=" + onlineAccountItemIndex;
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
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("offenderContactForm"));
	
}