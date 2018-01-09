/*
 * Address fields functionality.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (July 09, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the address fields snippet with the 
 * specified property name.
 * 
 * @param addressFieldsPropertyName address fields property name
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 * @param zipCodeUrl zip code options uniform resource locator
 */
function applyAddressFieldsOnClick(addressFieldsPropertyName, stateOptionsUrl, cityOptionsUrl, zipCodeUrl) {
	applyAddressFieldsCountryOnClick(addressFieldsPropertyName, stateOptionsUrl, cityOptionsUrl);
	applyAddressFieldsStateOnClick(addressFieldsPropertyName, cityOptionsUrl);
	applyAddressFieldsCityOnClick(addressFieldsPropertyName, zipCodeUrl);
	applyAddressFieldsNewCityOnClick(addressFieldsPropertyName);
	applyAddressFieldsNewZipCodeOnClick(addressFieldsPropertyName);
}