/**
 * 
 * @author Yidong Li
 * Date: Feb 10, 2015
 */
window.onload = function() {
	applyEmployerSearch(
		document.getElementById("employerInput"),
		document.getElementById("employer"),
		document.getElementById("employerDisplay"),
		document.getElementById("clear")
	);
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	applyTimePicker(document.getElementById("startTime"));
	applyTimePicker(document.getElementById("endTime"));
	applyDatePicker(document.getElementById("verificationDate"));

	applyPersonSearch(document.getElementById("supervisorInput"), 
		document.getElementById("supervisor"),
		document.getElementById("supervisorDisplay"), 
		document.getElementById("clear_supervisor"));

	applyFormUpdateChecker(document.getElementById("employmentForm"));

	applySearchUserAccountsAutocomplete(
		document.getElementById("verificationUserInput"),
		document.getElementById("verificationUserAccountLabel"),
		document.getElementById("verificationUserAccount"),
		document.getElementById("clearUserLink"),
		document.getElementById("currentUserLink"));
	
	applyAddressFieldsOnClick("addressFields", "stateOptions.html", "cityOptions.html", "zipCodeOptions.html");
	
	var createNewAddressRadioButton = document.getElementById("newAddressYes");
	var useExistingAddressRadioButton = document.getElementById("newAddressNo");
	var createNewAddressFields = document.getElementById("createAddressFields");
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
	var existingAddressInput = document.getElementById("existingAddress");
	
	var addressFieldsContainer = document.getElementById("addressFieldsContainer");
	if(create==true){
		if(newAddress==true){
			existingAddressInput.disabled = true;
			addressFieldsContainer.hidden = false;
		}
		else{
			if(employerStatus!=true){
				existingAddressInput.disabled = false;
				addressFieldsContainer.hidden = true;
			}
		}
			
		if(createNewAddressRadioButton!=null&&useExistingAddressRadioButton!=null){
			createNewAddressRadioButton.onclick = function(){
				addressFieldsContainer.hidden = false;
				existingAddressInput.disabled = true;
			}
			useExistingAddressRadioButton.onclick = function(){
				addressFieldsContainer.hidden = true;
				existingAddressInput.disabled = false;
			}
		}
		
	}
	
	$("#employerName").autocomplete({
		source: config.ServerConfig.getContextPath()+"/employment/findOrganizationsByPartialName.json",
		minLength: 2
	});
	$("#employerName").focus();
	
	
	var queryInput = document.getElementById("existingAddress");
	var searchEmploymentAddress = document.getElementById("searchEmploymentAddress");
	applyValueLabelAutoComplete(queryInput, searchEmploymentAddress, config.ServerConfig.getContextPath() + "/employment/findAddressesByPartialName.json");
};