/**
 * Detainer and notifications java script.
 * 
 * Author: Annie Jacques, Joel Norris
 * Version: 0.1.1 (May 25, 2017)
 * Since: OMIS 3.0
 */

window.onload = function() {
	//Apply date pickers to appropriate inputs
	var dateInputs = document.querySelectorAll("input.date");
	for(var i=0, len=dateInputs.length; i<len; i++) {
		applyDatePicker(dateInputs[i]);
    }
	var countableTextAreas = document.querySelectorAll("textarea.countableCharacters");
	for(var count=0, length=countableTextAreas.length; count<length; count++) {
		applyDynamicHTMLTextCounter(countableTextAreas[count]);
	}
	var removeRowLinks = document.querySelectorAll("a.removeRowLink");
	for (var i=0; i<currentDetainerNoteItemIndex; i++) {
		detainerNoteItemRowOnClick(i);
	}
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("detainerNoteItemsActionMenuLink"), detainerNoteItemsActionMenuOnClick);
	applyAddressFieldsOnClick("addressFields", "addressFieldsStateOptions.html", 
			"addressFieldsCityOptions.html", "addressFieldsZipCodeOptions.html");
	applyInitiatedByOnClick();
	interstateDetainerActivityItemsCreateOnClick();
	for(var i = 0; i < currentInterstateDetainerActivityItemIndex; i++){
		interstateDetainerActivityItemRowOnClick(i);
		categoryChangeFunction(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
	if(!(document.getElementById('usingAddress').checked)){
		enableAddressFieldControls("addressFields");
	}
	else if(document.getElementById('usingAddress').checked){
		disableAddressFieldControls("addressFields");
	}
	if(document.getElementById('usingInterstateAgreementDetainer').checked){
		document.getElementById('iasTbl').style.display = "block";
	}
	if(document.getElementById('creatingDetainerAgency').value == "true"){
		document.getElementById('daTbl').style.display = "block";
	}
	document.getElementById('createDetainerAgency').onclick = function(){
		$('#daTbl').toggle();
		
		if(document.getElementById('creatingDetainerAgency').value == "false"){
			document.getElementById('creatingDetainerAgency').value = "true";
			document.getElementById('detainerAgencySelect').disabled = "true";
		}
		else if(document.getElementById('creatingDetainerAgency').value == "true"){
			document.getElementById('creatingDetainerAgency').value = "false";
			document.getElementById('detainerAgencySelect').removeAttribute("disabled");
		}
	}
	document.getElementById('usingAddress').onclick = function() {
		if(!(document.getElementById('usingAddress').checked)){
			enableAddressFieldControls("addressFields");
		}
		else if(document.getElementById('usingAddress').checked){
			disableAddressFieldControls("addressFields");
		}
	}
	$('#usingInterstateAgreementDetainer').change(function() {
		$('#iasTbl').toggle();
	})
	var refusedToSign = document.getElementById("refusedToSign");
	var refusedToSignCommentContainer = document.getElementById("refusedToSignCommentContainer");
	var refusedToSignComment = document.getElementById("refusedToSignComment");
	var waiverRequired = document.getElementById("waiverRequired");
	var waiverRequiredCommentContainer = document.getElementById("waiverRequiredCommentContainer");
	var waiverRequiredComment = document.getElementById("waiverRequiredComment");
	//FIXME: character counter does not update on programmatic clear of text area value - JN
	applyDisplayOnClick(refusedToSign, refusedToSignCommentContainer, function() { if(!refusedToSign.checked){ return clearElement(refusedToSignComment)}});
	applyDisplayOnClick(waiverRequired, waiverRequiredCommentContainer, function() { if(!waiverRequired.checked){ return clearElement(waiverRequiredComment)}});
	applyDisplayOnClick(document.getElementById("processed"), document.getElementById("processingContainer"));
	applyDynamicHTMLTextCounter(document.getElementById("facilityName"));
	var otherFacilityTrue = document.getElementById("otherFacilityTrue");
	var otherFacilityFalse = document.getElementById("otherFacilityFalse");
	var facilityNameContainer = document.getElementById("facilityNameContainer");
	var facilityContainer =  document.getElementById("facilityContainer");
	otherFacilityTrue.addEventListener("click", function() {
		facilityNameContainer.classList.remove("hidden");
		facilityContainer.classList.add("hidden");
		clearElement(document.getElementById("unit"));
		clearElement(document.getElementById("complex"));
		clearElement(document.getElementById("facility"));
	});
	otherFacilityFalse.addEventListener("click", function() {
		facilityNameContainer.classList.add("hidden");
		facilityContainer.classList.remove("hidden");
		clearElement(document.getElementById("facilityName"));
	});
	applyFacilitySelectOnClick(document.getElementById("facility"), document.getElementById("complex"), document.getElementById("unit"))
	applyDetainerAgencyOnClick();
}
	
function clearElement(clearElt) {
	switch (clearElt.tagName.toLowerCase()) {
		case 'input':
	       switch (clearElt.type) {
	          case "radio":
	          case "checkbox": clearElt.checked = false; break; 
	          case "button":
	          case "submit":
	          case "image": 
	          default: clearElt.value = ''; break;
	       } break;
		case 'select': clearElt.selectedIndex = 0; break; 
		case 'textarea': clearElt.value = '';
	}
}

/**
 * Applies hidden class conditionally on the current class list of the specified
 * display element, activated by clicking on the specified click element.
 * 
 * When using the optional function parameter, pass as the following example:
 * function() {return realFunctionDesired(anyParameters);}
 * 
 * @param clickElt element to click
 * @param displayElt element to show or hide with the "hidden" class
 * @param optionalFunction optional function to run upon click
 */
function applyDisplayOnClick(clickElt, displayElt, optionalFunction) {
	clickElt.addEventListener("click", function() {
		if (displayElt.classList.contains("hidden")) {
			displayElt.classList.remove("hidden");
		} else {
			displayElt.classList.add("hidden");
		}

		if (typeof optionalFunction === 'function') {
			optionalFunction();
		}
	});
}