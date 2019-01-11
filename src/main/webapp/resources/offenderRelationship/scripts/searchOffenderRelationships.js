/**
 * Script for searching for offender relationships.
 * 
 * @author: Stephen Abson
 * @author: Sheronda Vaughn
 */
window.onload = function() {
	
	// Focuses input when label or radio box is clicked
	function applyFieldFocus(radioEltName, labelEltName, inputEltName) {
		var radioElt = document.getElementById(radioEltName);
		var labelElt = document.getElementById(labelEltName);
		var inputElt = document.getElementById(inputEltName);
		radioElt.onfocus = function() {
			inputElt.focus();
		};
		labelElt.onclick = function() {
			inputElt.focus();
		};
		if (radioElt.checked) {
			inputElt.focus();
		}
	}
	
	// Checks radio button when input is focused
	function applyInputFocus(inputEltName, radioEltName) {
		var inputElt = document.getElementById(inputEltName);
		var radioElt = document.getElementById(radioEltName);
		inputElt.onfocus = function() {
			radioElt.checked = true;
		};
	}
	
	// Applies radio button/field focus
	applyFieldFocus("searchTypeNameRadio", "searchTypeNameLabel", "searchTypeName");
	applyInputFocus("searchTypeName", "searchTypeNameRadio");
	applyInputFocus("searchTypeFirstName", "searchTypeNameRadio");
	applyFieldFocus("searchTypeOffenderNumberRadio", "searchTypeOffenderNumberLabel", "searchOffenderNumber");
	applyInputFocus("searchOffenderNumber", "searchTypeOffenderNumberRadio");
	applyFieldFocus("searchTypeSocialSecurityNumberRadio", "searchTypeSocialSecurityNumberLabel", "searchSocialSecurityNumber");
	applyInputFocus("searchSocialSecurityNumber", "searchTypeSocialSecurityNumberRadio");
	applyFieldFocus("searchTypeBirthDateRadio", "searchTypeBirthDateLabel", "searchBirthDate");
	applyInputFocus("searchBirthDate", "searchTypeBirthDateRadio");
	if (config.FeatureToggles.get("offenderrelationship", "allowAddressSearch")) {	
	//	var queryInput = document.getElementById("searchAddress");
	//	var searchAddress = document.getElementById("hiddenAddress");
		// Enable fields with something like this:	
		applyFieldFocus("searchTypeAddressRadio", "searchTypeAddressLabel", "searchAddress");
		applyInputFocus("searchAddress", "searchTypeAddressRadio");
		applyFieldFocus("searchTypeAddressRadio", "searchTypeStateLabel", "searchState");
		applyInputFocus("searchState", "searchTypeAddressRadio");
		applyFieldFocus("searchTypeAddressRadio", "searchTypeCityLabel", "searchCity");
		applyInputFocus("searchCity", "searchTypeAddressRadio");
		//applyValueLabelAutoComplete(queryInput, searchAddress, config.ServerConfig.getContextPath() + "/offenderRelationship/findOffenderRelationshipAddress.json");
		applyFieldFocus("searchTypeAddressRadio", "searchTypeZipCodeLabel", "searchZipCode");
		applyInputFocus("searchZipCode", "searchTypeAddressRadio");
		//   ***also apply behavior for country, State, city, ZIP code look up (possibly)***
		// ...until then, error with message:
	//	alert("Search by address is not yet implemented");
	}
	if (config.FeatureToggles.get("offenderrelationship", "allowTelephoneNumberSearch")) {
		applyFieldFocus("searchTypeTelephoneNumberRadio", "searchTypeTelephoneNumberLabel", "searchTelephoneNumber");
		applyInputFocus("searchTelephoneNumber", "searchTypeTelephoneNumberRadio");
	}
	if (config.FeatureToggles.get("offenderrelationship", "allowOnlineAccountSearch")) {
		applyFieldFocus("searchTypeOnlineAccountRadio", "searchTypeOnlineAccountLabel", "searchOnlineAccount");
		applyInputFocus("searchOnlineAccount", "searchTypeOnlineAccountRadio");
	}
	
	// Applies remove confirmation to results
	var relationRows = document.getElementsByClassName("rowActionMenuItem");
	for(var count =0; count < relationRows.length; count++) {
		applyActionMenu(relationRows[count], function() {
			applyRemoveLinkConfirmation();
			});
	}
};