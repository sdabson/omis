/*
 * Person fields functionality.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (June 15, 2015)
 * Since: OMIS 3.0
 */

/**
 * Applies on click functionality for the person fields snippet with the 
 * specified property name.
 * 
 * @param personFieldsPropertyName person fields property name
 * @param stateOptionsUrl state options uniform resource locator
 * @param cityOptionsUrl city options uniform resource locator
 */
function applyPersonFieldsOnClick(personFieldsPropertyName, stateOptionsUrl, cityOptionsUrl) {
	applyPersonFieldsBirthCountryOnClick(personFieldsPropertyName, stateOptionsUrl, cityOptionsUrl);
	applyPersonFieldsBirthStateOnClick(personFieldsPropertyName, cityOptionsUrl);
	applyPersonFieldsNewBirthCityOnClick(personFieldsPropertyName);
	applyDeceasedOnClick(personFieldsPropertyName);
	assignPersonFieldsDatePicker(personFieldsPropertyName + "BirthDate", "1900:"  + new Date().getFullYear());
	assignPersonFieldsDatePicker(personFieldsPropertyName + "DeathDate", "1900:"  + new Date().getFullYear());
}