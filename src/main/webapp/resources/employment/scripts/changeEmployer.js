/**
 * 
 * @author Yidong Li
 * Date: Aug 19, 2015
 */
window.onload = function() {
	var useExistingEmployerRadioButton = document.getElementById("newEmployerNo");
	var createNewEmployerRadioButton = document.getElementById("newEmployerYes");
	var existingEmployerInput = document.getElementById("employerInput");
	var newEmployerInput = document.getElementById("newEmployerInput");
	var changeEmployerContainor = document.getElementById("changeEmployerContainor");
	
	var createNewAddressRadioButton = document.getElementById("newAddressYes");
	var useExistingAddressRadioButton = document.getElementById("newAddressNo");
	var createNewAddressFields = document.getElementById("createAddressFields");
	
	var existingLocationInput = document.getElementById("existingChangeEmployorAddress");
	var addressFieldsContainer = document.getElementById("changeEmployerAddressFieldsContainer");
	var changeEmployerAddressFieldsRadioButtonsContainor = document.getElementById("changeEmployerAddressFieldsRadioButtonsContainor");
	
	if(createEmployer==false){ 
		newEmployerInput.disabled=true;
	}
	else { 
		newEmployerInput.disabled=false;
		if(createAddress==false){ 
			existingLocationInput.disabled = false;
		}
		else { 
			existingLocationInput.disabled = true;
		}
	}

	employerOnClick();
	addressOnClick();
	
	applyEmployerSearch(
		document.getElementById("employerInput"),
		document.getElementById("existingEmployer"),
		document.getElementById("employerDisplay"),
		document.getElementById("clearEmployer")
	);
	
	applyAddressFieldsOnClick("addressFields", "stateOptions.html", "cityOptions.html", "zipCodeOptions.html");
	
	var addressFieldsHouseNumber = document.getElementById("addressFieldsHouseNumber");
	var addressFieldsStreetName = document.getElementById("addressFieldsStreetName");
	var addressFieldsStreetSuffix = document.getElementById("addressFieldsStreetSuffix");
	var addressFieldsAddressUnitDesignator = document.getElementById("addressFieldsAddressUnitDesignator");
	var addressFieldsUnitName = document.getElementById("addressFieldsUnitName");
	var addressFieldsCountry = document.getElementById("addressFieldsCountry");
	var addressFieldsState = document.getElementById("addressFieldsState");
	var addressFieldsCity = document.getElementById("addressFieldsCity");
	var addressFieldsZipCode = document.getElementById("addressFieldsZipCode");
	var addressFieldsFalseNewCity = document.getElementById("addressFieldsFalseNewCity");
	var addressFieldsTrueNewCity = document.getElementById("addressFieldsTrueNewCity");
	var addressFieldsTrueNewZipCode = document.getElementById("addressFieldsTrueNewZipCode");
	
	$("#newEmployerInput").autocomplete({
		source: config.ServerConfig.getContextPath()+"/employment/findOrganizationsByPartialName.json",
		minLength: 2
	});
	
	$("#newEmployerInput").focus();

	var queryInput = document.getElementById("existingChangeEmployorAddress");
	var searchEmploymentAddress = document.getElementById("changeEmployerExistingAddress");
	applyValueLabelAutoComplete(queryInput, searchEmploymentAddress, config.ServerConfig.getContextPath() + "/employment/findAddressesByPartialName.json");

	applyActionMenu(document.getElementById("actionMenuLink"));
}