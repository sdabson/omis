/**
 * Offender relationships edit screen java script.
 * 
 * Author: Yidong Li
 * Version: 0.1.0 (January 19, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyDatePicker(document.getElementById("familyAssociationFieldsStartDate"));
	applyDatePicker(document.getElementById("familyAssociationFieldsEndDate"));
	applyDatePicker(document.getElementById("familyAssociationFieldsMarriageDate"));
	applyDatePicker(document.getElementById("familyAssociationFieldsDivorceDate"));
	/*Victim fields*/
	applyDatePicker(document.getElementById("victimFieldsRegisterDate"));
	applyDatePicker(document.getElementById("victimFieldsPacketSendDate"));
	/* Visitation fields */
	applyDatePicker(document.getElementById("visitationAssociationFieldsDecisionDate"));
	applyDatePicker(document.getElementById("visitationAssociationFieldsStartDate"));
	applyDatePicker(document.getElementById("visitationAssociationFieldsEndDate"));
	applyActionMenu(document.getElementById("offenderRelationshipListActionMenuLink"));
	applyTextCounter(document.getElementById("visitationAssociationFieldsNotes"), document.getElementById("notesCharacterCounter"));
	applyTextCounter(document.getElementById("visitationAssociationFieldsGuardianship"), document.getElementById("guardianshipCharacterCounter"));
	
	var marriageDateContainer = document.getElementById("marriageDateContainer");
	var divorceDateContainer = document.getElementById("divorceDateContainer");
	var relationship = document.getElementById("relationship");
	
	marriageDateContainer.style.visibility="hidden";
	divorceDateContainer.style.visibility="hidden";
	
	relationship.onchange = function() {
		if($("#relationship").val()!=""){
		$.ajax({
			url: config.ServerConfig.getContextPath() + "/offenderRelationship/displayMarriageDivorceDate.json?category=" + $("#relationship").val(),
			dataType: "json",
			cache:false,
			success: function(data) {
				if(data==true){
					marriageDateContainer.style.visibility="visible";
					divorceDateContainer.style.visibility="visible";
				}
				else {
					marriageDateContainer.style.visibility="hidden";
					divorceDateContainer.style.visibility="hidden";
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error: " + textStatus + "; " + errorThrown);
			}})
		}	
	}
	
	if(newRelation){
		var queryInput = document.getElementById("offenderRelationshipSearchAddressQuery");
		var searchAddress = document.getElementById("searchAddress");
		applyActionMenu(document.getElementById("offenderRelationshipListActionMenuLink"));
		applyValueLabelAutoComplete(queryInput, searchAddress, config.ServerConfig.getContextPath() + "/offenderRelationship/findOffenderRelationshipAddress.json");
		applyAddressFieldsOnClick("addressFields", "addressFieldsCreateStateOptions.html", "addressFieldsCreateCityOptions.html", "addressFieldsCreateZipCodeOptions.html");
		applyPersonFieldsOnClick("personFields", "personFieldsCreateStateOptions.html", "personFieldsCreateCityOptions.html");
		applyPoBoxFieldsOnClick("poBoxFields", "poBoxFieldsCreateStateOptions.html", "poBoxFieldsCreateCityOptions.html", "poBoxFieldsCreateZipCodeOptions.html");
		applyActionMenu(document.getElementById("telephoneNumbersCreateActionMenuLink"),addOffenderRelationshipCreateTelephoneNumberItem);
		applyActionMenu(document.getElementById("onlineAccountsEditActionMenuLink"),addOffenderRelationshipOnlineAccountItem);
		 
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
		
		var createScreenAddressContainer = document.getElementById("createScreenAddressContainer");
		var createScreenAddressFieldContainer = document.getElementById("createScreenAddressFieldContainer");
		var createScreenAddressCheckBox = document.getElementById("createScreenEnterAddress");
		
		if(newAddressRadioButton!=null&&existingAddressRadioButton!=null){
			newAddressRadioButton.onclick = function(){
				existingAddressInput.disabled = true;
				createScreenAddressFieldContainer.hidden = false;
			}
			existingAddressRadioButton.onclick = function(){
				existingAddressInput.disabled = false;
				createScreenAddressFieldContainer.hidden = true;
			}
		}
		createScreenAddressCheckBox.onclick = function(){
			if(createScreenAddressCheckBox.checked){
				createScreenAddressContainer.hidden = false;
			}
			else {
				createScreenAddressContainer.hidden = true;
			}
		}
		
		var poBoxFieldsContainer = document.getElementById("poBoxFieldsContainer");
		var poBoxCheckBox = document.getElementById("createScreenEnterPoBox");

		poBoxCheckBox.onclick = function(){
			if(poBoxCheckBox.checked){
				poBoxFieldsContainer.hidden = false;
			}
			else {
				poBoxFieldsContainer.hidden = true;
			}
		}
		
	}
	
	var createFamilyMember = document.getElementById("createFamilyMember");
	var familyMemberContainer = document.getElementById("familyMemberContainer");
	createFamilyMember.onchange = function() {
		if(createFamilyMember.checked){
			familyMemberContainer.hidden = false;
		} else {
			familyMemberContainer.hidden = true;
		}
	}
	
	var createVictim = document.getElementById("createVictim");
	var victimContainer = document.getElementById("victimContainer");
	createVictim.onchange = function() {
		if(createVictim.checked){
			victimContainer.hidden = false;
		} else {
			victimContainer.hidden = true;
		}
	}
	
	var createVisitor = document.getElementById("createVisitor");
	var visitorContainer = document.getElementById("visitorContainer");
	createVisitor.onchange = function() {
		if(createVisitor.checked){
			visitorContainer.hidden = false;
		} else {
			visitorContainer.hidden = true;
		}
	}
	
	for (var x = 0; x < offenderRelationshipTelephoneNumberIndex; x++) {
		applyTelephoneNumberItemBehavior(x);
	}
	
	for (var x = 0; x < offenderRelationshipOnlineAccountIndex; x++) {
		applyOnlineAccountItemBehavior(x);
	}
};