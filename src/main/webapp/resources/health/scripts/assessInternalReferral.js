/**
 * Behavior to assess internal referrals.
 * 
 * Author: Stephen Abson 
 */

// Disables an element
function disableElement(eltId) {
	var elt = document.getElementById(eltId);
	if (elt != null) {
		elt.setAttribute("disabled", "disabled");
	}
}

// Makes an element read only
function makeReadOnly(eltId) {
	var elt = document.getElementById(eltId);
	if (elt != null) {
		elt.setAttribute("readonly", "readonly");
	}
}

// Enables an element
function enableElement(eltId) {
	var elt = document.getElementById(eltId);
	if (elt != null) {
		elt.removeAttribute("disabled");
	}
}

//Returns whether an element is checked
function isElementChecked(eltName) {
	var elt = document.getElementById(eltName);
	if (elt != null) {
		checkedAttr = elt.getAttribute("checked");
		if (elt != null) {
			return checkedAttr != null;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

// Disables all follow up elements
function disableFollowUpElements() {
	disableElement("followUpAsap");
	disableElement("followUpLabsRequired");
	disableElement("followUpRequestProviderLevel");
	disableElement("followUpRequestNotes");
}

// Enables all elements
function enableFollowUpElements() {
	enableElement("followUpAsap");
	enableElement("followUpLabsRequired");
	enableElement("followUpRequestProviderLevel");
	enableElement("followUpRequestNotes");
}

// Enables only lab follow up elements
function enableLabFollowUpElements() {
	enableElement("followUpAsap");
	enableElement("followUpLabsRequired");
	disableElement("followUpRequestProviderLevel");
	enableElement("followUpRequestNotes");
}

// Enables or disables follow up elements depending on referral type
function changeFollowUpReferralType(elt) {
	var eltValue = elt.getAttribute("value");
	if (eltValue == "") {
		disableFollowUpElements();
	} else if (eltValue == "LAB") {
		enableLabFollowUpElements();
	} else {
		enableFollowUpElements();
	}
}

// If an element with the specified name exists, apply change referral type
// functionality
function applyReferralTypeChangeIfExists(eltName) {
	var elt = document.getElementById(eltName);
	if (elt != null) {
		elt.onclick = function() {
			changeFollowUpReferralType(elt);
		};
	}
}

window.onload = function() {
	applyTimePicker(document.getElementById("time"));
	var followUpAlreadyScheduled = document.getElementById("followUpAlreadyScheduled");
	if (followUpAlreadyScheduled != null && followUpAlreadyScheduled) {
		makeReadOnly("followUpAsap");
		makeReadOnly("followUpLabsRequired");
		makeReadOnly("followUpRequestProviderLevel");
		makeReadOnly("followUpRequestNotes");
		makeReadOnly("followUpReferralType.NOT_REQUIRED");
		makeReadOnly("followUpReferralType.INTERNAL_MEDICAL");
		makeReadOnly("followUpReferralType.EXTERNAL_MEDICAL");
		makeReadOnly("followUpReferralType.LAB");
		makeReadOnly("followUpReferralType.INTERNAL_OPTICAL");
		makeReadOnly("followUpReferralType.EXTERNAL_OPTICAL");
	} else {
		applyReferralTypeChangeIfExists("followUpReferralType.NOT_REQUIRED");
		applyReferralTypeChangeIfExists("followUpReferralType.INTERNAL_MEDICAL");
		applyReferralTypeChangeIfExists("followUpReferralType.EXTERNAL_MEDICAL");
		applyReferralTypeChangeIfExists("followUpReferralType.LAB");
		applyReferralTypeChangeIfExists("followUpReferralType.INTERNAL_OPTICAL");
		applyReferralTypeChangeIfExists("followUpReferralType.EXTERNAL_OPTICAL");
		if (isElementChecked("followUpReferralType.LAB")) {
			enableLabFollowUpElements();
		} else if (
				isElementChecked("followUpReferralType.INTERNAL_MEDICAL")
				|| isElementChecked("followUpReferralType.EXTERNAL_MEDICAL")
				|| isElementChecked("followUpReferralType.INTERNAL_OPTICAL")
				|| isElementChecked("followUpReferralType.EXTERNAL_OPTICAL")) {
			enableFollowUpElements();
		} else {
			disableFollowUpElements();
		}
	}
	assignTableRowOnClickFunction();
};