/**
 * Behavior for editing locations.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	var organizationName = document.getElementById("organizationName");
	applyValueLabelAutoComplete(organizationName, organizationName, config.ServerConfig.getContextPath() + "/location/findOrganizationsByPartialName.json");
	var addressQuery = document.getElementById("addressQuery");
	var address = document.getElementById("address");
	applyValueLabelAutoComplete(addressQuery, address, config.ServerConfig.getContextPath() + "/location/findAddresses.json");
	if (document.getElementById("useCurrentAddress") != null) {
		document.getElementById("useCurrentAddress").onclick = function() {
			setUseExistingAddressFieldsDisabled(true);
			setCreateNewAddressFieldsHidden(true);
		};
	}
	document.getElementById("useExistingAddress").onclick = function() {
		setUseExistingAddressFieldsDisabled(false);
		setCreateNewAddressFieldsHidden(true);
	};
	document.getElementById("createNewAddress").onclick = function() {
		setUseExistingAddressFieldsDisabled(true);
		setCreateNewAddressFieldsHidden(false);
	};
	document.getElementById("organizationName").focus();
	applyAddressFieldsOnClick("addressFields", config.ServerConfig.getContextPath() + "/location/findStates.html", config.ServerConfig.getContextPath() +  "/location/findCities.html", config.ServerConfig.getContextPath() +  "/location/findZipCodes.html");
};

// Sets whether use existing address fields are disabled
function setUseExistingAddressFieldsDisabled(disabled) {
	if (disabled) {
		document.getElementById("addressQuery").setAttribute("disabled", "disabled");
	} else {
		document.getElementById("addressQuery").removeAttribute("disabled");
	}
}

// Sets whether create new address fields are disabled
function setCreateNewAddressFieldsHidden(hidden) {
	var addressFields = document.getElementById("addressFields");
	if (hidden) {
		if (!ui.hasClass(addressFields, "hidden")) {
			ui.addClass(addressFields, "hidden");
		}
	} else {
		if (ui.hasClass(addressFields, "hidden")) {
			ui.removeClass(addressFields, "hidden");
		}
	}
}