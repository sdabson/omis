/**
 * Behavior to request external referral authorization.
 *
 * Author: Stephen Abson.
 */
window.onload = function() {
	// Apply if an offender is required to be captured by form
	var offenderRequired = document.getElementById("offenderRequired");
	if (offenderRequired != null && offenderRequired.getAttribute("value") == "true") {
		var offenderNameElt = document.getElementById("offenderName");
		applyOffenderAutocompleteWithUnit(
				document.getElementById("offender"),
				offenderNameElt,
				document.getElementById("offenderUnitSpan"),
				document.getElementById("offenderUnitLabel"),
				document.getElementById("offenderUnitGroup"));
		offenderNameElt.focus();
	}
	var referredDate = document.getElementById("referredDate");
	if (referredDate != null) {
		applyDatePicker(referredDate);
	}
	var decisionDate = document.getElementById("decisionDate");
	if (decisionDate != null) {
		applyDatePicker(decisionDate);
	}
	var reviewDate = document.getElementById("reviewDate");
	if (reviewDate != null) {
		applyDatePicker(reviewDate);
	}
	// Configure authorization components
	var authorize = document.getElementById("authorize");
	if (authorize != null) {
		
		// Enable elements
		function enableElt(eltName) {
			var theElt = document.getElementById(eltName);
			if (theElt != null) {
				theElt.removeAttribute("disabled");
			}
		}
		
		// Disable elements
		function disableElt(eltName) {
			var theElt = document.getElementById(eltName);
			if (theElt != null) {
				theElt.setAttribute("disabled", "disabled");
			}
		}
		
		// Sets whether status elements are enabled
		function setStatusEnabled(status) {
			if (status.options[status.selectedIndex].getAttribute("value") == "PANEL_REVIEW_REQUIRED") {
				enableReviewComponents();	
			} else {
				disableReviewComponents();
			}
		}
		
		// Sets whether authorized components are enabled
		function setAuthorizeEnabled(authorize) {
			if (authorize.checked) {
				enableAuthorizeComponents();
			} else {
				disableAuthorizeComponents();
			}
		}
		
		// Enable MRP components
		function enableReviewComponents() {
			enableElt("reviewDate");
			enableElt("medicalPanelReviewDecisionStatus");
		}
		
		// Disable MRP components
		function disableReviewComponents() {
			disableElt("reviewDate");
			disableElt("medicalPanelReviewDecisionStatus");
		}
		
		// Enable authorization components
		function enableAuthorizeComponents() {
			enableElt("decisionDate");
			enableElt("authorizedByText");
			enableElt("authorizationNotes");
			enableElt("status");
			var status = document.getElementById("status");
			if (status != null) {
				setStatusEnabled(status);
			} else {
				disableReviewComponents();
			}
		}
		
		// Disable authorization components
		function disableAuthorizeComponents() {
			disableElt("decisionDate");
			disableElt("authorizedByText");
			disableElt("authorizationNotes");
			disableElt("status");
			disableReviewComponents();
		}
		
		// Set up components
		setAuthorizeEnabled(authorize);
		authorize.onchange = function() {
			setAuthorizeEnabled(this);
		};
		var status = document.getElementById("status");
		if (status != null) {
			status.onchange = function() {
				setStatusEnabled(this);
			};
		}
	}
	
	// Fetch providers for medical facility on change
	var medicalFacility = document.getElementById("medicalFacility");
	if (medicalFacility != null) {
		medicalFacility.onchange = function() {
			var request = new XMLHttpRequest();
			var url = config.ServerConfig.getContextPath() + "/health/provider/findByMedicalFacility.html";
			var params = "medicalFacility=" + medicalFacility.options[medicalFacility.selectedIndex].getAttribute("value");
			request.open("GET", url + "?" + params + "&nocache=" + new Date().getTime(), false);
			request.send(null);
			if (request.status == 200) {
				var providerAssignment = document.getElementById("providerAssignment");
				if (providerAssignment != null) {
					providerAssignment.innerHTML = '\n' + request.responseText;
				} else {
					alert("No provider assignments to populate");
				}
			} else {
				alert("Error: " + request.status + " - " + request.statusText);
			}
		};
	}
	var authorizedByText = document.getElementById("authorizedByText"); 
	if (authorizedByText != null) {
		var authorizedBy = document.getElementById("authorizedBy");
		var clearCurrentUserLink = document.getElementById("clearCurrentUserLink");
		var useCurrentUserLink = document.getElementById("useCurrentUserLink");
		var authorizedByLabel = document.getElementById("authorizedByLabel"); 
		applyStaffSearch(authorizedByText, authorizedBy, authorizedByLabel, useCurrentUserLink, clearCurrentUserLink);
	}
};