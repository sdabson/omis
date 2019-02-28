/**
 * Search Incident Statement behavior.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Sep 11, 2015)
 * Since: OMIS 3
 */
window.onload = function() {
	applyUserSearch(document.getElementById("reporterInput"),
			document.getElementById("reporter"),
			document.getElementById("reporterDisplay"),
			document.getElementById("reporterCurrent"),
			document.getElementById("reporterClear"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	if (document.getElementById("staffInvolvedPartyInput") instanceof HTMLElement) {
		applyStaffSearch(document.getElementById("staffInvolvedPartyInput"),
				document.getElementById("involvedParty"),
				document.getElementById("involvedPartyDisplay"),
				document.getElementById("staffCurrent"),
				document.getElementById("staffClear"));
	}
	if (document.getElementById("offenderInvolvedPartyInput") instanceof HTMLElement) {
		applyOffenderSearch(document.getElementById("offenderInvolvedPartyInput"),
				document.getElementById("involvedParty"),
				document.getElementById("involvedPartyDisplay"),
				document.getElementById("offenderClear"));
	}
	applyInvolvedPartyOptionOnClick();
}

function applyInvolvedPartyOptionOnClick() {
	let options = document.getElementsByName("involvedPartyOption");
	for(i=0; i < options.length; i++) {
		options[i].onclick = function() {
			let container = document.getElementById("involvedPartyFieldGroup");
			container.innerHTML = "";
			let fieldLabel = document.createElement("label");
			fieldLabel.classList.add("fieldLabel");
			container.append(fieldLabel);
			if(this.value == "OFFENDER") {
			 	let offenderInput = document.createElement("input");
			 	offenderInput.id = "offenderInvolvedPartyInput";
			 	offenderInput.type = "text";
			 	offenderInput.size = "50";
			 	container.appendChild(offenderInput);
			 	
			 	let involvedPartyInput = document.createElement("input");
			 	involvedPartyInput.id = "involvedParty";
			 	involvedPartyInput.name = "involvedParty";
			 	involvedPartyInput.type="hidden";
			 	container.appendChild(involvedPartyInput);
			 	
			 	let clearLink = document.createElement("a");
			 	clearLink.id = "offenderClear";
			 	clearLink.classList.add("clearLink");
			 	container.appendChild(clearLink);
			 	
			 	let displaySpan = document.createElement("span");
			 	displaySpan.id = "involvedPartyDisplay";
			 	container.appendChild(displaySpan);
			 	
			 	applyOffenderSearch(offenderInput,
			 			involvedPartyInput,
			 			displaySpan,
						clearLink);
			} else if(this.value == "STAFF") {
				let staffInput = document.createElement("input");
				staffInput.id = "staffInvolvedPartyInput";
				staffInput.type = "text";
				staffInput.size = "50";
			 	container.appendChild(staffInput);
			 	
			 	let involvedPartyInput = document.createElement("input");
			 	involvedPartyInput.id = "involvedParty";
			 	involvedPartyInput.name = "involvedParty";
			 	involvedPartyInput.type="hidden";
			 	container.appendChild(involvedPartyInput);

			 	let currentLink = document.createElement("a");
			 	currentLink.id = "staffCurrent";
			 	currentLink.classList.add("currentUserAccountLink");
			 	container.appendChild(currentLink);
			 	
			 	let clearLink = document.createElement("a");
			 	clearLink.id = "staffClear";
			 	clearLink.classList.add("clearLink");
			 	container.appendChild(clearLink);
			 	
			 	let displaySpan = document.createElement("span");
			 	displaySpan.id = "involvedPartyDisplay";
			 	container.appendChild(displaySpan);
			 	
			 	applyStaffSearch(staffInput,
			 			involvedPartyInput,
						displaySpan,
						currentLink,
						clearLink);
			} else if(this.value == "OTHER") {
				let involvedPartyNameInput = document.createElement("input");
				involvedPartyNameInput.id = "involvedPartyName";
				involvedPartyNameInput.type = "text";
				involvedPartyNameInput.size = "50";
			 	container.appendChild(involvedPartyNameInput);
			} else {
				
			}		
		}
	}
}