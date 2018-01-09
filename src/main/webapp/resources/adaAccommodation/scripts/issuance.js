/**
 * Accommodation issuance detail screen behavior.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("issuanceForm"));
	applyDisableOnSubmit(document.getElementById("issuanceForm"));	
	assignIssuanceOnClick();
	applyUserSearch(document.getElementById("issuerName"),
			document.getElementById("issuer"), 
			document.getElementById("issuerCurrentLabel"),
			document.getElementById("issuedByUser"),
			document.getElementById("clearIssuedByUserLink"));
}