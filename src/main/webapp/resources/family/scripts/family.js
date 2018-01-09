/**
 * 
 * @author Yidong Li
 * Date: June 19, 2015
 */
window.onload = function() {
	if(existingFamilyMember==false){
		var queryInput = document.getElementById("familySearchAddressQuery");
		var searchAddress = document.getElementById("searchAddress");
		applyActionMenu(document.getElementById("familyEditActionMenuLink"));
		applyDatePicker(document.getElementById("birthDay"));
		applyDatePicker(document.getElementById("startDate"));
		applyDatePicker(document.getElementById("endDate"));
		applyDatePicker(document.getElementById("marriageDateInput"));
		applyDatePicker(document.getElementById("divorceDateInput"));
		applyActionMenu(document.getElementById("telephoneNumbersActionMenuLink"),addFamilyAssociationTelephoneNumberItem);
		applyActionMenu(document.getElementById("onlineAccountActionMenuLink"),addFamilyAssociationEmailItem);
		applyActionMenu(document.getElementById("noteActionMenuLink"),addFamilyAssociationNoteItem);
		applyValueLabelAutoComplete(queryInput, searchAddress, config.ServerConfig.getContextPath() + "/family/findOffenderRelationshipAddress.json");
		var marriageDateContainer = document.getElementById("marriageDateContainer");
		var divorceDateContainer = document.getElementById("divorceDateContainer");
		var relationship = document.getElementById("relationship");
		applyPoBoxFieldsOnClick("poBoxFields", "poBoxFieldsStateOptions.html", "poBoxFieldsCityOptions.html", "poBoxFieldsZipCodeOptions.html");
		applyAddressFieldsOnClick("addressFields", "stateOptions.html", "cityOptions.html", "zipCodeOptions.html");
		applyPersonFieldsOnClick("personFields", "personFieldsStateOptions.html", "personFieldsCityOptions.html");
		applyLocationSearch(
			document.getElementById("existingAddress"),
			document.getElementById("location"),
			document.getElementById("addressDisplay"),
			document.getElementById("clearAddress")
		);
		for (var x = 0; x <= familyAssociationNoteIndex; x++) {
			applyNoteItemBehavior(x);
			applyDatePicker(document.getElementById("noteDate"+x));
		}
		for (var x = 0; x <= familyAssociationTelephoneNumberIndex; x++) {
			applyTelephoneNumberItemBehavior(x);
		}
		for (var x = 0; x <= familyAssociationOnlineAccountIndex; x++) {
			applyEmailItemBehavior(x);
		}

		relationship.onchange = function() {
			if($("#relationship").val()!=""){
			$.ajax({
				url: config.ServerConfig.getContextPath() + "/family/displayMarriageDivorceDate.json?category=" + $("#relationship").val(),
				dataType: "json",
				cache:false,
				success: function(data) {
					if(data==true){
						$("#marriageDateContainer").removeClass("hidden");
						$("#divorceDateContainer").removeClass("hidden");
						$("#marriageDateContainer").addClass("show");
						$("#divorceDateContainer").addClass("show");
					}
					else {
						$("#marriageDateContainer").removeClass("show");
						$("#divorceDateContainer").removeClass("show");
						$("#marriageDateContainer").addClass("hidden");
						$("#divorceDateContainer").addClass("hidden");
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				}})
			}	
		}
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
		var existingAddressInput = document.getElementById("familySearchAddressQuery");
		var addressContainer = document.getElementById("addressContainer");

		if(createNewAddressRadioButton!=null&&useExistingAddressRadioButton!=null){
			createNewAddressRadioButton.onclick = function(){
				existingAddressInput.disabled = true;
				addressContainer.hidden = false;
			}
			useExistingAddressRadioButton.onclick = function(){
				existingAddressInput.disabled = false;
				addressContainer.hidden = true;
			}
		}
		$(".date").each(function(index, elt) {
			$(elt).datepicker({
				changeMonth: true,
				changeYear: true
			});
		});

		var poBoxFieldsContainer = document.getElementById("familyPoBoxFieldsContainer");
		var poBoxCheckBox = document.getElementById("familyCreateScreenEnterPoBox");
		poBoxCheckBox.onclick = function(){
			if(poBoxCheckBox.checked){
				poBoxFieldsContainer.hidden = false;
			}
			else {
				poBoxFieldsContainer.hidden = true;
			}
		}

		var existingAddressRadioButton = document.getElementById("newAddressNo");
		var newAddressRadioButton = document.getElementById("newAddressYes");
		var createScreenAddressContainer = document.getElementById("familyCreateScreenAddressContainer");
		var createScreenAddressFieldContainer = document.getElementById("addressContainer");
		var createScreenAddressCheckBox = document.getElementById("familyCreateScreenEnterAddress");
		if(newAddressRadioButton.checked){
			existingAddressInput.disabled = true;
			createScreenAddressFieldContainer.hidden = false;
		} else if(existingAddressRadioButton.checked){
			existingAddressInput.disabled = false;
			createScreenAddressFieldContainer.hidden = true;
		} 
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
		if(createScreenAddressCheckBox.checked){
			createScreenAddressContainer.hidden = false;
		}
		else {
			createScreenAddressContainer.hidden = true;
		}
		createScreenAddressCheckBox.onclick = function(){
			if(createScreenAddressCheckBox.checked){
				createScreenAddressContainer.hidden = false;
			}
			else {
				createScreenAddressContainer.hidden = true;
			}
		}
	}
	else {
		applyActionMenu(document.getElementById("familyEditActionMenuLink"));
		applyDatePicker(document.getElementById("startDate"));
		applyDatePicker(document.getElementById("endDate"));
		applyDatePicker(document.getElementById("marriageDateInput"));
		applyDatePicker(document.getElementById("divorceDateInput"));
		var marriageDateContainer = document.getElementById("marriageDateContainer");
		var divorceDateContainer = document.getElementById("divorceDateContainer");
		var relationship = document.getElementById("relationship");
		
		var categoryId = document.getElementById("categoryId").value;
		
		relationship.onchange = function() {
			if($("#relationship").val()!=""){
			$.ajax({
				url: config.ServerConfig.getContextPath() + "/family/displayMarriageDivorceDate.json?category=" + $("#relationship").val(),
				dataType: "json",
				cache:false,
				success: function(data) {
					if(data==true){
						$("#marriageDateContainer").removeClass("hidden");
						$("#divorceDateContainer").removeClass("hidden");
						$("#marriageDateContainer").addClass("show");
						$("#divorceDateContainer").addClass("show");
					}
					else {
						$("#marriageDateContainer").removeClass("show");
						$("#divorceDateContainer").removeClass("show");
						$("#marriageDateContainer").addClass("hidden");
						$("#divorceDateContainer").addClass("hidden");
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error: " + textStatus + "; " + errorThrown);
				}})
			}
		} 
	}
};