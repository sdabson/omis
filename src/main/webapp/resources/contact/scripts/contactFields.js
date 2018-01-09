/*
 * Contact fields script.
 *
 * Author: Joel Norris
 * Author: Sheronda Vaughn
 * Version: 0.1.0 (Jan 04, 2016)
 * Since: OMIS 3.0
 */

/**
 * Assign on click functionality for the contact fields form snippet with the
 * specified property name.
 * 
 * @param contactFieldsPropertyName contact fields property name
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 * @param zipCodeOptionsUrl zip code options uniform resource locator
 */
/*function applyContactFieldsOnClick(contactFieldsPropertyName, stateOptionsUrl, cityOptionsUrl, zipCodeOptionsUrl) {
	applyAddressFieldsOnClick(addressFieldsPropertyName + ".mailingAddressFields", stateOptionsUrl, cityOptionsUrl, zipCodeOptionsUrl);
	applyPoBoxFieldsOnClick(poBoxFieldsPropertyName + ".poBoxFields", stateOptionsUrl, cityOptionsUrl, zipCodeOptionsUrl);
}
*/
function createTelephoneNumbersActionMenuOnClick() {
	$("#createTelephoneNumberLink").click(function() {	
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/showTelephoneNumber.html",
		{async: false, type: "GET",
				data: { telephoneNumberIndex: currentTelephoneNumberIndex }, 
				success: function(data) {
					$("#telephoneNumbers").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#telephoneNumbers").html(jqXHR.responseText );
				}
		});
		currentTelephoneNumberIndex++;
		return false;
	});
};
	
function createsOnlineAccountsActionMenuOnClick() {
	$("#createOnlineAccountLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/showOnlineAccount.html",
		{async: false, type: "GET",
				data: { onlineAccountIndex: currentOnlineAccountIndex }, 
				success: function(data) {
					$("#onlineAccounts").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#onlineAccounts").html(jqXHR.responseText );
				}
		});
		currentTelephoneNumberIndex++;
		return false;
	});
};

function assignOnClick(telephoneNumberIndex, onlineAccountIndex) {
};