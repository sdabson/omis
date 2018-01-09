
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyOffenderSearch(document.getElementById("offenderInput"),
			document.getElementById("offender"),
			document.getElementById("offenderDisplay"),
			document.getElementById("clearOffender"));
	
	newOrExistingOffender();
	
	document.getElementById('newOffender1').onclick = function(){newOrExistingOffender()}
	document.getElementById('newOffender2').onclick = function(){newOrExistingOffender()}
	
	function newOrExistingOffender(){
		if(document.getElementById('newOffender1').checked){
			document.getElementById("lastName").disabled = "true";
			document.getElementById("firstName").disabled = "true";
			document.getElementById("middleName").disabled = "true";
			document.getElementById("suffix").disabled = "true";
			
			document.getElementById("lastName").value = "";
			document.getElementById("firstName").value = "";
			document.getElementById("middleName").value = "";
			document.getElementById("suffix").value = "";
			
			document.getElementById("offenderInput").removeAttribute("disabled");
		}
		if(document.getElementById('newOffender2').checked){
			document.getElementById("lastName").removeAttribute("disabled");
			document.getElementById("firstName").removeAttribute("disabled");
			document.getElementById("middleName").removeAttribute("disabled");
			document.getElementById("suffix").removeAttribute("disabled");
			
			document.getElementById("offenderInput").disabled = "true";
			document.getElementById("offender").value = "";
			document.getElementById("offenderInput").value = "";
			document.getElementById("offenderDisplay").innerHTML = "";
		}
	}
};