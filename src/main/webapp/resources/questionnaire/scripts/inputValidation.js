function applyInputValidator(elt){
	elt.onkeypress = function(evt){
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 32 && (charCode < 48 || charCode > 57) && 
				(charCode < 44 || charCode > 46) && 
				(charCode < 40 || charCode > 41) && (charCode != 97) && (charCode != 8)){
			return false;
		}
		else {
			return true;
		}
	}
	
	elt.addEventListener("blur", function(){
		validateInput(elt);
	});
}

function validateInput(elt){
	var valid = new Array();
	var returnValid; 
	var index = 0;
	
	if(elt.className.indexOf("currency") != -1){
		valid[index] = currencyValidation(elt);
		index++;
	}
	if(elt.className.indexOf("phoneNumber") != -1){
		valid[index] = phoneNumberValidation(elt);
		index++;
	}
	if(elt.className.indexOf("wholeNumber") != -1){
		valid[index] = wholeNumberValidation(elt);
		index++;
	}
	if(elt.className.indexOf("decimalNumber") != -1){
		valid[index] = decimalNumberValidation(elt);
		index++;
	}
	
	
	for(var i = 0; i < valid.length; i++){
		if(valid[i] == true){
			returnValid = true;
			break;
		}
		else{
			returnValid = false;
		}
	}
	
	if(returnValid == true ||  elt.value == null || elt.value == ""){
		elt.style.borderColor = "#000000";
		elt.style.backgroundColor = "#FFFFFF"; 
	}
	else{
		elt.style.borderColor = "#FF0000";
		elt.style.backgroundColor = "#FF8080"; 
		
	}
}

function currencyValidation(elt){
	var pattern = /^(?!0\.00)[1-9]\d{0,2}(,\d{3})*(\.\d\d)?$/;
	
	if(elt.value.trim().match(pattern)) {
		return true;
	}
	else {
		return false;
	}
}

function phoneNumberValidation(elt){
	var pattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	
	if(elt.value.trim().match(pattern)) {
		return true;
	}
	else {
		return false;
	}
}

function wholeNumberValidation(elt){
	var pattern = /^\d+$/;
	
	if(elt.value.trim().match(pattern)) {
		return true;
	}
	else {
		return false;
	}
}

function decimalNumberValidation(elt){
	var pattern = /^\d+(?:\.\d+)?$/;
	
	if(elt.value.trim().match(pattern)) {
		return true;
	}
	else {
		return false;
	}
}



