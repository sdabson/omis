/**
 * Javascript for Education
 * 
 * @author Annie Jacques
 * @version 0.1.0 (July 28, 2016)
 * @since OMIS 3.0
 * 
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("attendedStartDate"));
	applyDatePicker(document.getElementById("attendedEndDate"));
	applyDatePicker(document.getElementById("achievementDate"));
	applyDatePicker(document.getElementById("verifiedDate"));
	applySearchUserAccountsAutocomplete(document.getElementById("userInput"),
			document.getElementById("userDisplay"),
			document.getElementById("user"),
			document.getElementById("clearUser"),
			document.getElementById("currentUser"));
	applyActionMenu(document.getElementById("noteItemsActionMenuLink"), noteItemsCreateOnClick);
	applyActionMenu(document.getElementById("achievementItemsActionMenuLink"), achievementItemsCreateOnClick);
	
	checkVerified();
	checkGraduated();
	assignOnClick();
	
	document.getElementById('verified1').onclick = function(){
		checkVerified();
	}
	document.getElementById('graduated1').onclick = function(){
		checkGraduated();
	}
	
	function checkVerified(){
		if(!(document.getElementById('verified1').checked)){
			document.getElementById('userInput').disabled = "true";
			document.getElementById('user').disabled = "true";
			document.getElementById('currentUser').disabled = "true";
			document.getElementById('clearUser').disabled = "true";
			document.getElementById('verifiedDate').disabled = "true";
			document.getElementById('verificationMethod').disabled = "true";
		}
		
		if(document.getElementById('verified1').checked){
			document.getElementById('userInput').removeAttribute("disabled");
			document.getElementById('user').removeAttribute("disabled");
			document.getElementById('currentUser').removeAttribute("disabled");
			document.getElementById('clearUser').removeAttribute("disabled");
			document.getElementById('verifiedDate').removeAttribute("disabled");
			document.getElementById('verificationMethod').removeAttribute("disabled");
		}
	}
	
	function checkGraduated(){
		if(!(document.getElementById('graduated1').checked)){
			document.getElementById('achievementDate').disabled = "true";
			document.getElementById('achievementDescription').disabled = "true";
			document.getElementById('achievementCategory').disabled = "true";
		}
		
		if(document.getElementById('graduated1').checked){
			document.getElementById('achievementDate').removeAttribute("disabled");
			document.getElementById('achievementDescription').removeAttribute("disabled");
			document.getElementById('achievementCategory').removeAttribute("disabled");
		}
	}
}