/*
 * P:o Box fields.
 * 
 * Author: Yidong Li
 * Version: 0.1.0 (Oct 22, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the po box fields snippet with the 
 * specified property name.
 * 
 * @param poBoxFieldsPropertyName po box fields property name
 */
function applyPoBoxFieldsOnClick(poBoxFieldsPropertyName, stateOptionsUrl, cityOptionsUrl, zipCodeUrl) {
	applyPoBoxFieldsCountryOnClick(poBoxFieldsPropertyName, stateOptionsUrl, cityOptionsUrl);
	applyPoBoxFieldsStateOnClick(poBoxFieldsPropertyName, cityOptionsUrl);
	applyPoBoxFieldsCityOnClick(poBoxFieldsPropertyName, zipCodeUrl);
	applyPoBoxFieldsNewCityOnClick(poBoxFieldsPropertyName);
	applyPoBoxFieldsNewZipCodeOnClick(poBoxFieldsPropertyName);
}