/**
 * Offender relationship java script.
 * 
 * Author: Joel Norris
 * Author: Sheronda Vaughn
 * Version: 0.1.0 (January 06, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyPersonSearch(document.getElementById("personInput"),
			document.getElementById("person"),
			document.getElementById("personDisplay"),
			document.getElementById("personClear"));
	applyFormUpdateChecker(document.getElementById("createRelationshipsForm"));
	applyPersonFieldsOnClick("personFields", "birthStateOptions.html", "birthCityOptions.html");
	applyOffenderRelationshipOnClick();
	var searchAddressQuery = document.getElementById("searchAddressQuery");
	var searchAddress = document.getElementById("searchAddress");	
	applyValueLabelAutoComplete(searchAddressQuery, searchAddress, config.ServerConfig.getContextPath() + "/offenderRelationship/findAddressQuery.json");
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyAddressFieldsOnClick("addressFields", "addressFieldsStateOptions.html", "addressFieldsCityOptions.html", "addressFieldsZipCodeOptions.html");
	applyPoBoxFieldsOnClick("poBoxFields", "poBoxFieldsStateOptions.html", "poBoxFieldsCityOptions.html", "poBoxFieldsZipCodeOptions.html");
	applyActionMenu(document.getElementById("telephoneNumbersActionMenuLink"), createTelephoneNumbersActionMenuOnClick);
	applyActionMenu(document.getElementById("onlineAccountsActionMenuLink"), createOnlineAccountsActionMenuOnClick);
	assignTelephoneNumberOnClick();
	assignOnlineAccountOnClick();
}