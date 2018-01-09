/**
 * Offender relationships edit screen java script.
 * 
 * Author: Yidong Li
 * Version: 0.1.0 (January 19, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	var personFields = "personFields";
	var queryInput = document.getElementById("offenderRelationshipSearchAddressQuery");
	var searchAddress = document.getElementById("searchAddress");
	applyActionMenu(document.getElementById("offenderRelationshipListActionMenuLink"));
	applyValueLabelAutoComplete(queryInput, searchAddress, config.ServerConfig.getContextPath() + "/offenderRelationship/update/findOffenderRelationshipAddress.json");
	applyPoBoxFieldsOnClick("poBoxFields", "poBoxFieldsEditStateOptions.html", "poBoxFieldsEditCityOptions.html", "poBoxFieldsEditZipCodeOptions.html");
	applyAddressFieldsOnClick("addressFields", "addressFieldsEditStateOptions.html", "addressFieldsEditCityOptions.html", "addressFieldsEditZipCodeOptions.html");
	applyPersonFieldsOnClick("personFields", "personFieldsEditStateOptions.html", "personFieldsEditCityOptions.html");
	applyActionMenu(document.getElementById("telephoneNumbersEditActionMenuLink"),addOffenderRelationshipEditTelephoneNumberItem);
	applyActionMenu(document.getElementById("onlineAccountsEditActionMenuLink"),addOffenderRelationshipOnlineAccountItem);
	
	var currentAddressRadioButton = document.getElementById("currentAddress");
	var existingAddressRadioButton = document.getElementById("existingAddress");
	var newAddressRadioButton = document.getElementById("newAddress");
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
	var existingAddressInput = document.getElementById("offenderRelationshipSearchAddressQuery");
	var addressFieldsContainer = document.getElementById("addressFieldsContainer");
	
	if(currentAddressRadioButton!=null){
		currentAddressRadioButton.onclick = function(){
			existingAddressInput.disabled = true;
			addressFieldsContainer.hidden = true;
		}
		newAddressRadioButton.onclick = function(){
			existingAddressInput.disabled = true;
			addressFieldsContainer.hidden = false;
		}
		existingAddressRadioButton.onclick = function(){
			existingAddressInput.disabled = false;
			addressFieldsContainer.hidden = true;
		}
	}
	else {
		newAddressRadioButton.onclick = function(){
			existingAddressInput.disabled = true;
			addressFieldsContainer.hidden = false;
		}
		existingAddressRadioButton.onclick = function(){
			existingAddressInput.disabled = false;
			addressFieldsContainer.hidden = true;
		}
	}

	for (var x = 0; x <= offenderRelationshipTelephoneNumberIndex; x++) {
		applyTelephoneNumberItemBehavior(x);
	}
	
	for (var x = 0; x <= offenderRelationshipOnlineAccountIndex; x++) {
		applyOnlineAccountItemBehavior(x);
	}
	
	var addressContainer = document.getElementById("addressContainer");
	var addressCheckBox = document.getElementById("enterAddress");
	
	addressCheckBox.onclick = function(){
		if(addressCheckBox.checked){
			addressContainer.hidden = false;
		}
		else {
			addressContainer.hidden = true;
		}
	}
	
	var poBoxFieldsContainer = document.getElementById("poBoxFieldsContainer");
	var poBoxCheckBox = document.getElementById("enterPoBox");
	
	poBoxCheckBox.onclick = function(){
		if(poBoxCheckBox.checked){
			poBoxFieldsContainer.hidden = false;
		}
		else {
			poBoxFieldsContainer.hidden = true;
		}
	}
};